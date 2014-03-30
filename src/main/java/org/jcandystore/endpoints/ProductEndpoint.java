package org.jcandystore.endpoints;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.jcandystore.db.PersistenceService;
import org.jcandystore.model.Product;

@Api(name = "productendpoint",
        namespace = @ApiNamespace(
                ownerDomain = "jcandystore.org",
                ownerName = "jcandystore.org",
                packagePath = "model"),
        title = "JCandyStore Products",
        description = "This is the Cloud Endpoints for JCandyStore Products. (Live Demo at Devoxx 2013!)",
        documentationLink = "http://www.devoxx.be/#/",
        version = "v1")
public class ProductEndpoint {

  /**
   * This method lists all the entities inserted in Cloud SQL. It uses HTTP GET
   * method and paging support.
   *
   * @param first
   * @return A CollectionResponse class containing the list of all entities
   * persisted and a cursor to the next page.
   */
  @ApiMethod(name = "listProduct")
  public CollectionResponse<Product> listProduct(
          @Nullable @Named("first") Integer first,
          @Nullable @Named("limit") Integer limit) {

    EntityManager mgr = getEntityManager();;
    Query query = mgr.createNamedQuery("Product.findAll");
    if (first != null) {
      query.setFirstResult(first);
    }
    if (limit != null) {
      query.setMaxResults(limit);
    }
    List<Product> execute = (List<Product>) query.getResultList();
    // Tight loop for fetching all entities from datastore and accomodate
    // for lazy fetch.
    //  for (Product obj : execute)
    return CollectionResponse.<Product>builder().setItems(execute)
            .setNextPageToken("" + first).build();
  }

  /**
   * This method gets the entity having primary key id. It uses HTTP GET method.
   *
   * @param id the primary key of the java bean.
   * @return The entity with primary key id.
   */
  @ApiMethod(name = "getProduct")
  public Product getProduct(@Named("id") String id) {
    EntityManager mgr = getEntityManager();
    Product product = null;
    product = mgr.find(Product.class, id);

    return product;
  }

  /**
   * This inserts a new entity into Cloud SQL. If the entity already
   * exists in the datastore, an exception is thrown. It uses HTTP POST method.
   *
   * @param product the entity to be inserted.
   * @return The inserted entity.
   */
  @ApiMethod(name = "insertProduct")
  public Product insertProduct(Product product) {
    EntityManager mgr = getEntityManager();
    if (containsProduct(product)) {
      throw new EntityExistsException("Object already exists");
    }
    mgr.persist(product);

    return product;
  }

  /**
   * This method is used for updating an existing entity. If the entity does not
   * exist in the datastore, an exception is thrown. It uses HTTP PUT method.
   *
   * @param product the entity to be updated.
   * @return The updated entity.
   */
  @ApiMethod(name = "updateProduct")
  public Product updateProduct(Product product) {
    EntityManager mgr = getEntityManager();
    if (!containsProduct(product)) {
      throw new EntityNotFoundException("Object does not exist");
    }
    mgr.persist(product);

    return product;
  }

  /**
   * This method removes the entity with primary key id. It uses HTTP DELETE
   * method.
   *
   * @param id the primary key of the entity to be deleted.
   */
  @ApiMethod(name = "removeProduct")
  public void removeProduct(@Named("id") String id) {
    EntityManager mgr = getEntityManager();
    Product product = mgr.find(Product.class, id);
    mgr.remove(product);

  }

  private boolean containsProduct(Product product) {
    EntityManager mgr = getEntityManager();
    boolean contains = true;
    Product item = mgr.find(Product.class, product.getProdId());
    if (item == null) {
      contains = false;
    }

    return contains;
  }

  private EntityManager em;
//  private PersistenceService ps;

  public ProductEndpoint() {
    em = PersistenceService.getInstance().getEntityManager();
    /*PersistenceService ps =*/ PersistenceService.getInstance();
  }

  private EntityManager getEntityManager() {
    return em;
  }
}
