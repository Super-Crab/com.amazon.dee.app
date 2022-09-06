package com.amazon.alexa.routing.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes10.dex */
public final class RoutePath {
    private final RouteContext from;
    private final int options;
    private final RouteContext to;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes10.dex */
    public @interface Options {
        public static final int BACK = 2;
        public static final int EXIT = 4;
        public static final int NAVIGATE = 1;
        public static final int NAVIGATE_WITHOUT_BACKSTACK = 0;
        public static final int NOTIFY_ONLY = 6;
        public static final int REPLACE = 5;
        public static final int UP = 3;
    }

    public RoutePath(RouteContext routeContext, RouteContext routeContext2, int i) {
        this.from = routeContext;
        this.to = routeContext2;
        this.options = i;
    }

    public RouteContext from() {
        return this.from;
    }

    public int getOptions() {
        return this.options;
    }

    public RouteContext to() {
        return this.to;
    }
}
