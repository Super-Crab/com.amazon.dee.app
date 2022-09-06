package com.amazon.identity.auth.device.dataobject;

import java.util.Locale;
/* loaded from: classes12.dex */
public class Scope {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private String scopeDescription = null;
    private final String scopeName;

    public Scope(String str) {
        this.scopeName = str;
    }

    public static String getDescription(String str, String str2) {
        return str;
    }

    public synchronized String getScopeDescription() {
        if (this.scopeDescription == null) {
            this.scopeDescription = getDescription(this.scopeName, Locale.getDefault().getLanguage());
        }
        return this.scopeDescription;
    }

    public String getScopeName() {
        return this.scopeName;
    }

    public boolean isLocal() {
        return isLocal(this.scopeName);
    }

    public void setScopeDescription(String str) {
        this.scopeDescription = str;
    }

    public static boolean isLocal(String str) {
        return str.startsWith("device");
    }
}
