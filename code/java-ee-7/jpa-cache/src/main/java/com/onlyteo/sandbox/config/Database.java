package com.onlyteo.sandbox.config;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import com.onlyteo.sandbox.boundary.ServiceBoundary;
import com.onlyteo.sandbox.entity.ServiceEntity;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Startup
@Singleton
@TransactionManagement(TransactionManagementType.BEAN)
public class Database {

    private static final Logger LOGGER = LoggerFactory.getLogger(Database.class);
    private static final int K = 1000;
    private static final int N = 100;

    @Inject
    private ServiceBoundary boundary;

    @PostConstruct
    public void load() {
        //LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        //StatusPrinter.print(lc);
        long startMillis = System.currentTimeMillis();
        LOGGER.info("Creating entities {}", LoggerFactory.getILoggerFactory().getClass().getName());
        for (int i = 0; i < N; i++) {
            long thousandMillis = System.currentTimeMillis();
            int x = K * i;
            ServiceEntity[] entities = new ServiceEntity[K];
            for (int j = 0; j < K; j++) {
                int y = x + j;
                entities[j] = new ServiceEntity("Hey " + y);
            }
            boundary.createServiceEntiry(entities);
            LOGGER.info("{} entities created (Took {}ms)", x, System.currentTimeMillis() - thousandMillis);
        }
        LOGGER.info("Finished creating entities (Took {}ms)", System.currentTimeMillis() - startMillis);
    }
}
