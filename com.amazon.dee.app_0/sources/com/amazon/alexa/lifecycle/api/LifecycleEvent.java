package com.amazon.alexa.lifecycle.api;
/* loaded from: classes9.dex */
public enum LifecycleEvent {
    APPLICATION_DID_FOREGROUND("application::didForeground"),
    APPLICATION_DID_BACKGROUND("application::didBackground"),
    APPLICATION_WILL_SHUTDOWN("application::willShutdown"),
    APPLICATION_READY("application::ready"),
    APPLICATION_LOADED("application::loaded");
    
    public static final String IS_FOREGROUND = "isForeground";
    public static final String IS_LOADED = "isLoaded";
    public static final String IS_READY = "isReady";
    public final String name;

    LifecycleEvent(String str) {
        this.name = str;
    }
}
