package com.onlyteo.sandbox.boundary;

import com.onlyteo.sandbox.config.Database;
import com.onlyteo.sandbox.entity.ServiceEntity;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class ServiceBoundary {

    private static final Logger LOGGER = LoggerFactory.getLogger(Database.class);

    @PersistenceContext
    private EntityManager entityManager;

    public List<ServiceEntity> findServiceEntities() {
        LOGGER.info("Getting entities");
        long startMillis = System.currentTimeMillis();
        TypedQuery<ServiceEntity> query = entityManager.createNamedQuery(ServiceEntity.GET_ALL_NAME, ServiceEntity.class);
        List<ServiceEntity> entities = query.getResultList();
        LOGGER.info("Finished getting entities (Took {}ms)", System.currentTimeMillis() - startMillis);
        return entities;
    }

    public void createServiceEntiry(ServiceEntity... entities) {
        Arrays.stream(entities).forEach(e -> entityManager.persist(e));
    }
}
