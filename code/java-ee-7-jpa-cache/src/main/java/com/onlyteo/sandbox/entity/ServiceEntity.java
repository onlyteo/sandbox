package com.onlyteo.sandbox.entity;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries(
        @NamedQuery(
                name = ServiceEntity.GET_ALL_NAME,
                query = "SELECT e FROM SERVICE_ENTITIES e"))
@Cacheable
@Entity(name = "SERVICE_ENTITIES")
public class ServiceEntity implements Serializable {

    public static final String GET_ALL_NAME = "ServiceEntity.getAll";

    @Id
    private String id;

    public ServiceEntity() {
    }

    public ServiceEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
