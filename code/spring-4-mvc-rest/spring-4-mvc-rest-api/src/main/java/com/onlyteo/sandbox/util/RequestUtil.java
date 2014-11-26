package com.onlyteo.sandbox.util;

import java.util.UUID;

public final class RequestUtil {

    private RequestUtil() {
    }

    public static UUID createRequestId() {
        return UUID.randomUUID();
    }
}
