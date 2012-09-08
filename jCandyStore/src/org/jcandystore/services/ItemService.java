package org.jcandystore.services;

import org.jcandystore.db.PersistenceService;
import org.jcandystore.model.Item;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

@Path("/item")
public class ItemService {
    private EntityManager em;

    public ItemService() {
        em = PersistenceService.getInstance().getEntityManager();
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Item entity) {
        try {
            em.persist(entity);
        } catch (PersistenceException pe) {
            pe.printStackTrace();
        }
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    public void edit(Item entity) {
        try {
            em.merge(entity);
        } catch (PersistenceException pe) {
            pe.printStackTrace();
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        em.remove(em.merge(em.find(Item.class, id)));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Item findByProdId(@PathParam("id") String id) {
        try {
            Query q = em.createNamedQuery("Item.findByProdId").setParameter("prodId", id);
            return (Item) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }        
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Item> findAll() {
        Query query = em.createNamedQuery("Item.findAll");
        return query.getResultList();
    }

    protected EntityManager getEntityManager() {
        return em;
    }

}
