package com.onlyteo.sandbox.ui;

import com.onlyteo.sandbox.boundary.ServiceBoundary;
import com.onlyteo.sandbox.entity.ServiceEntity;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class PageModel {

    private long millis;

    @Inject
    private ServiceBoundary serviceBoundary;

    public List<ServiceEntity> getHello() {
        long startMillis = System.currentTimeMillis();
        try {
            return serviceBoundary.findServiceEntities();
        } finally {
            millis = System.currentTimeMillis() - startMillis;
        }
    }

    public int getNumberHello() {
        return getHello().size();
    }

    public long getMillis() {
        return millis;
    }
}
