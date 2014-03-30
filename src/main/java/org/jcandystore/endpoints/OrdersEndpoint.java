package org.jcandystore.endpoints;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.jcandystore.db.PersistenceService;
import org.jcandystore.model.Orders;

@Api(name = "ordersendpoint", namespace =
        @ApiNamespace(ownerDomain = "jcandystore.org", ownerName = "jcandystore.org", packagePath = "model"))
public class OrdersEndpoint {

  /**
   * This method lists all the entities inserted in datastore. It uses HTTP GET
   * method and paging support.
   *
   * @return A CollectionResponse class containing the list of all entities
   * persisted and a cursor to the next page.
   */
  @SuppressWarnings({"unchecked", "unused"})
  @ApiMethod(name = "listOrders")
  public CollectionResponse<Orders> listOrders(
          @Nullable @Named("first") Integer first,
          @Nullable @Named("limit") Integer limit) {

    EntityManager mgr = null;
    Cursor cursor = null;
    List<Orders> execute = null;

    try {
      mgr = getEntityManager();
      Query query = mgr.createQuery("select from Orders as Orders");


      if (first != null) {
      query.setFirstResult(first);
      }
      if (limit != null) {
        query.setMaxResults(limit);
      }

      execute = (List<Orders>) query.getResultList();


      // Tight loop for fetching all entities from datastore and accomodate
      // for lazy fetch.
      for (Orders obj : execute)
				;
    } finally {
      mgr.close();
    }

    return CollectionResponse.<Orders>builder().setItems(execute)
            .setNextPageToken(""+first).build();
  }

  /**
   * This method gets the entity having primary key id. It uses HTTP GET method.
   *
   * @param id the primary key of the java bean.
   * @return The entity with primary key id.
   */
  @ApiMethod(name = "getOrders")
  public Orders getOrders(@Named("id") Long id) {
    EntityManager mgr = getEntityManager();
    Orders orders = null;
    try {
      orders = mgr.find(Orders.class, id);
    } finally {
      mgr.close();
    }
    return orders;
  }

  /**
   * This inserts a new entity into App Engine datastore. If the entity already
   * exists in the datastore, an exception is thrown. It uses HTTP POST method.
   *
   * @param orders the entity to be inserted.
   * @return The inserted entity.
   */
  @ApiMethod(name = "insertOrders")
  public Orders insertOrders(Orders orders) {
    EntityManager mgr = getEntityManager();
    try {
      if (containsOrders(orders)) {
        throw new EntityExistsException("Object already exists");
      }
      mgr.persist(orders);
    } finally {
      mgr.close();
    }
    return orders;
  }

  /**
   * This method is used for updating an existing entity. If the entity does not
   * exist in the datastore, an exception is thrown. It uses HTTP PUT method.
   *
   * @param orders the entity to be updated.
   * @return The updated entity.
   */
  @ApiMethod(name = "updateOrders")
  public Orders updateOrders(Orders orders) {
    EntityManager mgr = getEntityManager();
    try {
      if (!containsOrders(orders)) {
        throw new EntityNotFoundException("Object does not exist");
      }
      mgr.persist(orders);
    } finally {
      mgr.close();
    }
    return orders;
  }

  /**
   * This method removes the entity with primary key id. It uses HTTP DELETE
   * method.
   *
   * @param id the primary key of the entity to be deleted.
   */
  @ApiMethod(name = "removeOrders")
  public void removeOrders(@Named("id") Long id) {
    EntityManager mgr = getEntityManager();
    try {
      Orders orders = mgr.find(Orders.class, id);
      mgr.remove(orders);
    } finally {
      mgr.close();
    }
  }

  private boolean containsOrders(Orders orders) {
    EntityManager mgr = getEntityManager();
    boolean contains = true;
    try {
      Orders item = mgr.find(Orders.class, orders.getOrderId());
      if (item == null) {
        contains = false;
      }
    } finally {
      mgr.close();
    }
    return contains;
  }

  private static EntityManager getEntityManager() {
    return PersistenceService.getInstance().getEntityManager();
  }
}
