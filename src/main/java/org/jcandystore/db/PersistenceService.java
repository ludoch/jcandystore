package org.jcandystore.db;

import com.google.appengine.api.utils.SystemProperty;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Utility class for dealing with persistence.
 *
 * @author ludo
 */
public class PersistenceService {
  private final static String DEFAULT_PU = "CandyPU";
  private static EntityManagerFactory pmf;

  static {
    Map<String, String> properties = new HashMap();
    if (SystemProperty.environment.value()
            == SystemProperty.Environment.Value.Production) {
      properties.put("javax.persistence.jdbc.driver",
              "com.mysql.jdbc.GoogleDriver");
      properties.put("javax.persistence.jdbc.url",
              System.getProperty("cloudsql.url"));
    } else {

      properties.put("javax.persistence.jdbc.driver",
              "com.mysql.jdbc.Driver");
      properties.put("javax.persistence.jdbc.url",
              System.getProperty("cloudsql.url.dev"));
    }
    pmf = Persistence.createEntityManagerFactory(DEFAULT_PU, properties);
  }
  private static ThreadLocal<PersistenceService> instance = new ThreadLocal<PersistenceService>() {
    @Override
    protected PersistenceService initialValue() {
      return new PersistenceService();
    }
  };
  private EntityManager em;
  private EntityTransaction utx;

  private PersistenceService() {
    this.em = pmf.createEntityManager();
    this.utx = em.getTransaction();
  }

  /**
   * Returns an instance of PersistenceService.
   *
   * @return an instance of PersistenceService
   */
  public static PersistenceService getInstance() {
    return instance.get();
  }

  private static void removeInstance() {
    instance.remove();
  }

  /**
   * Returns an instance of EntityManager.
   *
   * @return an instance of EntityManager
   */
  public EntityManager getEntityManager() {
    return em;
  }

  /**
   * Begins a resource transaction.
   */
  public void beginTx() {
    try {
      utx.begin();
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  /**
   * Commits a resource transaction.
   */
  public void commitTx() {
    try { com.sun.jersey.spi.container.servlet.ServletContainer a;
      utx.commit();
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  /**
   * Rolls back a resource transaction.
   */
  public void rollbackTx() {
    try {
      utx.rollback();
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  /**
   * Closes this instance.
   */
  public void close() {
    if (em != null && em.isOpen()) {
      em.close();
    }

    removeInstance();
  }
}
