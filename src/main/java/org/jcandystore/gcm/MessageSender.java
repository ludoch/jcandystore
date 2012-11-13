package org.jcandystore.gcm;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.jcandystore.model.Orders;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class MessageSender {

    private static Sender sender = null;

    public static void sendMessageToAllDevices(HttpServletResponse resp, Orders order) {
        List<String> devices = Datastore.getDevices();
        for (String deviceId : devices) {
            MessageSender.sendSingleMessage(deviceId, resp, order);
        }
    }

    public static void sendSingleMessage(String regId, HttpServletResponse resp, Orders order) {
        System.err.println("Sending message to device " + regId);

        if (sender == null) {
            sender = new Sender("AIzaSyAibtCTKNsXHLttDTWrRdsBrOR1e7Rqi7s");
        }

        Message.Builder builder = new Message.Builder();
        builder.addData("user", order.getUserId());
        builder.addData("total", order.getTotalPrice().toString());
        builder.addData("id", order.getOrderId().toString());
        Message message = builder.build();

        Result result;
        try {
            result = sender.sendNoRetry(message, regId);
        } catch (com.google.android.gcm.server.InvalidRequestException e) {
            System.err.println("InvalidRequestException. GCM status code: " + e.getHttpStatusCode());
            e.printStackTrace();
            taskDone(resp);
            return;
        } catch (IOException e) {
            System.err.println("Exception posting " + message);
            e.printStackTrace(System.err);
            taskDone(resp);
            return;
        }
        if (result == null) {
            retryTask(resp);
            return;
        }
        if (result.getMessageId() != null) {
            System.err.println("Succesfully sent message to device " + regId);
            String canonicalRegId = result.getCanonicalRegistrationId();
            if (canonicalRegId != null) {
                // same device has more than on registration id: update it
                System.err.println("canonicalRegId " + canonicalRegId);
                Datastore.updateRegistration(regId, canonicalRegId);
            }
        } else {
            String error = result.getErrorCodeName();
            if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
                // application has been removed from device - unregister it
                Datastore.unregister(regId);
            } else {
                System.out.println("Error sending message to device " + regId + ": "
                        + error);
            }
        }
    }

    /**
     * Indicates to App Engine that this task should be retried.
     */
    private static void retryTask(HttpServletResponse resp) {
        resp.setStatus(500);
    }

    /**
     * Indicates to App Engine that this task is done.
     */
    private static void taskDone(HttpServletResponse resp) {
        resp.setStatus(200);
    }
}
