package org.jcandystore.services;

import org.jcandystore.db.PersistenceService;
import org.jcandystore.model.Account;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/account")
public class AccountService {
    private EntityManager em;
    private PersistenceService ps;
    
    
    public AccountService() {
        em = PersistenceService.getInstance().getEntityManager();
        ps = PersistenceService.getInstance();
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Account entity) {
        try {
        	ps.beginTx();
            em.persist(entity);
            ps.commitTx();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            ps.rollbackTx();
        }
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    public void edit(Account entity) {
        try {
        	ps.beginTx();
        	em.merge(entity);
        	ps.commitTx();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            ps.rollbackTx();
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
    	try {
    		ps.beginTx();
    		em.remove(em.merge(em.find(Account.class, id)));
    		ps.commitTx();
    	} catch (Exception e) {
    		e.printStackTrace();
    		ps.rollbackTx();
        }
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Account find(@PathParam("id") String id) {
        Query q = em.createNamedQuery("Account.findByUserId")
                .setParameter("userId", id);
        return (Account) q.getSingleResult();
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Account> findAll() {
        Query query = em.createNamedQuery("Account.findAll");
        return query.getResultList();
    }

    protected EntityManager getEntityManager() {
        return em;
    }    
    
}
