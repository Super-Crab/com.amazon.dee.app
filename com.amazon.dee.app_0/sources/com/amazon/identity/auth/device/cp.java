package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.features.Feature;
import java.util.EnumMap;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class cp extends co {
    private static final String gM = "com.amazon.identity.auth.device.cp";
    private static cp iz;
    private final EnumMap<Feature, Boolean> gO = new EnumMap<>(Feature.class);
    private final co iA;

    public cp(co coVar) {
        if (coVar != null) {
            this.iA = coVar;
            return;
        }
        throw new IllegalArgumentException("delegateFeatureSet is null");
    }

    public static synchronized cp a(co coVar) {
        cp cpVar;
        synchronized (cp.class) {
            if (iz == null) {
                iz = new cp(coVar);
            }
            cpVar = iz;
        }
        return cpVar;
    }

    @Override // com.amazon.identity.auth.device.co
    public synchronized boolean a(Feature feature) {
        Boolean bool = this.gO.get(feature);
        if (bool != null) {
            return bool.booleanValue();
        }
        boolean a = this.iA.a(feature);
        this.gO.put((EnumMap<Feature, Boolean>) feature, (Feature) Boolean.valueOf(a));
        io.i(gM, String.format("Caching feature %s as %s", feature.toString(), Boolean.valueOf(a)));
        return a;
    }

    public synchronized void a(Feature feature, Context context) {
        boolean fetchValue = feature.fetchValue(context);
        this.gO.put((EnumMap<Feature, Boolean>) feature, (Feature) Boolean.valueOf(fetchValue));
        io.i(gM, String.format("Resetting feature cache %s as %s", feature.toString(), Boolean.valueOf(fetchValue)));
    }
}
