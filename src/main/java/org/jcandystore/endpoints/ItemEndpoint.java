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
import org.jcandystore.model.Item;

@Api(name = "itemendpoint", namespace =
        @ApiNamespace(ownerDomain = "jcandystore.org", ownerName = "jcandystore.org", packagePath = "model"))
public class ItemEndpoint {

  /**
   * This method lists all the entities inserted in datastore. It uses HTTP GET
   * method and paging support.
   *
   * @return A CollectionResponse class containing the list of all entities
   * persisted and a cursor to the next page.
   */
  @SuppressWarnings({"unchecked", "unused"})
  @ApiMethod(name = "listItem")
  public CollectionResponse<Item> listItem(
          @Nullable @Named("first") Integer first,
          @Nullable @Named("limit") Integer limit) {

    EntityManager mgr = null;
    Cursor cursor = null;
    List<Item> execute = null;

    try {
      mgr = getEntityManager();
      Query query = mgr.createQuery("select from Item as Item");


      if (first != null) {
      query.setFirstResult(first);
      }
      if (limit != null) {
        query.setMaxResults(limit);
      }

      execute = (List<Item>) query.getResultList();

      

      // Tight loop for fetching all entities from datastore and accomodate
      // for lazy fetch.
      for (Item obj : execute)
				;
    } finally {
      mgr.close();
    }

    return CollectionResponse.<Item>builder().setItems(execute)
            .setNextPageToken(""+first).build();
  }

  /**
   * This method gets the entity having primary key id. It uses HTTP GET method.
   *
   * @param id the primary key of the java bean.
   * @return The entity with primary key id.
   */
  @ApiMethod(name = "getItem")
  public Item getItem(@Named("id") String id) {
    EntityManager mgr = getEntityManager();
    Item item = null;
    try {
      item = mgr.find(Item.class, id);
    } finally {
      mgr.close();
    }
    return item;
  }

  /**
   * This inserts a new entity into App Engine datastore. If the entity already
   * exists in the datastore, an exception is thrown. It uses HTTP POST method.
   *
   * @param item the entity to be inserted.
   * @return The inserted entity.
   */
  @ApiMethod(name = "insertItem")
  public Item insertItem(Item item) {
    EntityManager mgr = getEntityManager();
    try {
      if (containsItem(item)) {
        throw new EntityExistsException("Object already exists");
      }
      mgr.persist(item);
    } finally {
      mgr.close();
    }
    return item;
  }

  /**
   * This method is used for updating an existing entity. If the entity does not
   * exist in the datastore, an exception is thrown. It uses HTTP PUT method.
   *
   * @param item the entity to be updated.
   * @return The updated entity.
   */
  @ApiMethod(name = "updateItem")
  public Item updateItem(Item item) {
    EntityManager mgr = getEntityManager();
    try {
      if (!containsItem(item)) {
        throw new EntityNotFoundException("Object does not exist");
      }
      mgr.persist(item);
    } finally {
      mgr.close();
    }
    return item;
  }

  /**
   * This method removes the entity with primary key id. It uses HTTP DELETE
   * method.
   *
   * @param id the primary key of the entity to be deleted.
   */
  @ApiMethod(name = "removeItem")
  public void removeItem(@Named("id") String id) {
    EntityManager mgr = getEntityManager();
    try {
      Item item = mgr.find(Item.class, id);
      mgr.remove(item);
    } finally {
      mgr.close();
    }
  }

  private boolean containsItem(Item item) {
    EntityManager mgr = getEntityManager();
    boolean contains = true;
    try {
      Item item1 = mgr.find(Item.class, item.getItemId());
      if (item1 == null) {
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
