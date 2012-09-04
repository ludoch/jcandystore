package org.jcandystore.services;

import org.jcandystore.db.PersistenceService;
import org.jcandystore.model.Product;

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

@Path("/product")
public class ProductService {
    private EntityManager em;
    
    public ProductService() {
        em = PersistenceService.getInstance().getEntityManager();
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Product entity) {
        try {
            em.persist(entity);
        } catch (PersistenceException pe) {
            pe.printStackTrace();
        }
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    public void edit(Product entity) {
        try {
        em.merge(entity);
        } catch (PersistenceException pe) {
            pe.printStackTrace();
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        em.remove(em.merge(em.find(Product.class, id)));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Product find(@PathParam("id") String id) {
        Query q = em.createNamedQuery("Product.findByProdId")
                .setParameter("prodId", id);
        return (Product) q.getSingleResult();
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Product> findAll() {
        Query query = em.createNamedQuery("Product.findAll");
        return query.getResultList();
    }

    protected EntityManager getEntityManager() {
        return em;
    }    
    
}
