package com.amazon.alexa.routing.api;
/* loaded from: classes10.dex */
public final class RouteAlias {
    private static final int DEFAULT_PRIORITY = 50;
    private final ParameterMapper parameterMap;
    private int priority;
    private final Route target;
    private final RouteTemplate template;

    public RouteAlias(Route route, String str) {
        this.priority = 50;
        this.target = route;
        this.template = new RouteTemplate(str);
        this.parameterMap = null;
    }

    public ParameterMapper getParameterMap() {
        return this.parameterMap;
    }

    public int getPriority() {
        return this.priority;
    }

    public Route getTarget() {
        return this.target;
    }

    public RouteTemplate getTemplate() {
        return this.template;
    }

    public RouteAlias(Route route, String str, int i) {
        this.priority = 50;
        this.target = route;
        this.template = new RouteTemplate(str);
        this.parameterMap = null;
        this.priority = i;
    }

    public RouteAlias(Route route, String str, ParameterMapper parameterMapper) {
        this.priority = 50;
        this.target = route;
        this.template = new RouteTemplate(str);
        this.parameterMap = parameterMapper;
    }

    public RouteAlias(Route route, String str, int i, ParameterMapper parameterMapper) {
        this.priority = 50;
        this.target = route;
        this.template = new RouteTemplate(str);
        this.parameterMap = parameterMapper;
        this.priority = i;
    }
}
