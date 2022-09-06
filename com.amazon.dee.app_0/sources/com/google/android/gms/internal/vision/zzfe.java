package com.google.android.gms.internal.vision;

import java.util.List;
/* loaded from: classes2.dex */
public final class zzfe extends RuntimeException {
    private final List<String> zzos;

    public zzfe(zzdx zzdxVar) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
        this.zzos = null;
    }
}
