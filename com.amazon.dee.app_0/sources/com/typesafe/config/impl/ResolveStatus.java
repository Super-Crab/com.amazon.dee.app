package com.typesafe.config.impl;

import java.util.Collection;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public enum ResolveStatus {
    UNRESOLVED,
    RESOLVED;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final ResolveStatus fromBoolean(boolean z) {
        return z ? RESOLVED : UNRESOLVED;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final ResolveStatus fromValues(Collection<? extends AbstractConfigValue> collection) {
        for (AbstractConfigValue abstractConfigValue : collection) {
            ResolveStatus resolveStatus = abstractConfigValue.resolveStatus();
            ResolveStatus resolveStatus2 = UNRESOLVED;
            if (resolveStatus == resolveStatus2) {
                return resolveStatus2;
            }
        }
        return RESOLVED;
    }
}
