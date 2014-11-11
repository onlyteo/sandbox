package com.onlyteo.sandbox.backend.services;

import com.onlyteo.sandbox.backend.domain.Foo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FooService {

    private static final Map<String, Foo> REPO = new HashMap<String, Foo>();

    public Foo read(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Id is null");
        }
        return REPO.get(id);
    }

    public List<Foo> list() {
        return new ArrayList<Foo>(REPO.values());
    }

    public Foo create(Foo foo) {
        if (foo == null) {
            throw new IllegalArgumentException("Foo is null");
        }
        String id = UUID.randomUUID().toString();
        foo.setId(id);
        REPO.put(id, foo);
        return REPO.get(id);
    }

    public Foo update(Foo foo) {
        if (foo == null) {
            throw new IllegalArgumentException("Foo is null");
        }
        String id = foo.getId();
        if (id == null) {
            throw new IllegalArgumentException("Id is null");
        }
        if (!REPO.containsKey(id)) {
            throw new IllegalStateException("Foo with given id does not exists in repo");
        }
        REPO.replace(id, foo);
        return REPO.get(id);
    }

    public Foo delete(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Id is null");
        }
        if (!REPO.containsKey(id)) {
            throw new IllegalStateException("Foo with given id does not exists in repo");
        }
        return REPO.remove(id);
    }
}
