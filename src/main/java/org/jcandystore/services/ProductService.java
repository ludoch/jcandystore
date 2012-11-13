package org.jcandystore.services;

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
import org.jcandystore.db.PersistenceService;
import org.jcandystore.model.Category;
import org.jcandystore.model.Product;

@Path("/product")
public class ProductService {
    private EntityManager em;
    private PersistenceService ps;
    

    public ProductService() {
        em = PersistenceService.getInstance().getEntityManager();
        ps = PersistenceService.getInstance();
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Product entity) {
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
    public void edit(Product entity) {
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
    		em.remove(em.merge(em.find(Product.class, id)));
    		ps.commitTx();
    	} catch (Exception e) {
    		e.printStackTrace();
    		ps.rollbackTx();
    	}
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Product find(@PathParam("id") String id) {
        try {
            Query q = em.createNamedQuery("Product.findByProdId").setParameter("prodId", id);
            return (Product) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }        
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Product> findAll() {
        Query query = em.createNamedQuery("Product.findAll");
        return query.getResultList();
    }

    public List<Product> findByCategory(String category) {
        try {
            Category cat = (Category) em.createNamedQuery("Category.findByCatId")
                    .setParameter("catId", category).getSingleResult();
            Query query = em.createNamedQuery("Product.findByCategory");
            query.setParameter("category", cat);
            return query.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    protected EntityManager getEntityManager() {
        return em;
    }

}
