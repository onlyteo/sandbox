package com.onlyteo.sandbox.backend;

import com.onlyteo.sandbox.domain.Foo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FooService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FooService.class);
    private static final Map<String, Foo> REPO = new HashMap<String, Foo>();

    @Inject
    private HttpServletRequest request;

    public Foo read(String id) {
        LOGGER.info("Request ID: {}", request.getAttribute("requestId"));
        if (id == null) {
            throw new IllegalArgumentException("Id is null");
        }
        return REPO.get(id);
    }

    public List<Foo> list() {
        LOGGER.info("Request ID: {}", request.getAttribute("requestId"));
        return new ArrayList<Foo>(REPO.values());
    }

    public Foo create(Foo foo) {
        LOGGER.info("Request ID: {}", request.getAttribute("requestId"));
        if (foo == null) {
            throw new IllegalArgumentException("Foo is null");
        }
        String id = UUID.randomUUID().toString();
        foo.setId(id);
        REPO.put(id, foo);
        return REPO.get(id);
    }

    public Foo update(Foo foo) {
        LOGGER.info("Request ID: {}", request.getAttribute("requestId"));
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
        LOGGER.info("Request ID: {}", request.getAttribute("requestId"));
        if (id == null) {
            throw new IllegalArgumentException("Id is null");
        }
        if (!REPO.containsKey(id)) {
            throw new IllegalStateException("Foo with given id does not exists in repo");
        }
        return REPO.remove(id);
    }
}
