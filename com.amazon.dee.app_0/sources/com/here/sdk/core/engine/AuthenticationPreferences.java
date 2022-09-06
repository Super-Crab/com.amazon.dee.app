package com.here.sdk.core.engine;
@Deprecated
/* loaded from: classes3.dex */
public enum AuthenticationPreferences {
    USE_SERVER_TIME(0),
    USE_SYSTEM_TIME(1);
    
    public final int value;

    AuthenticationPreferences(int i) {
        this.value = i;
    }
}
