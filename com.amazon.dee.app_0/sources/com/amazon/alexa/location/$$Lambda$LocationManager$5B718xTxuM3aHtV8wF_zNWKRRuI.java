package com.amazon.alexa.location;

import com.amazon.alexa.location.LocationManager;
import com.amazon.alexa.location.models.ALSGeofence;
import io.reactivex.rxjava3.functions.Function;
import java.util.List;
import java.util.Map;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.location.-$$Lambda$LocationManager$5B718xTxuM3aHtV8wF_zNWKRRuI  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$LocationManager$5B718xTxuM3aHtV8wF_zNWKRRuI implements Function {
    public static final /* synthetic */ $$Lambda$LocationManager$5B718xTxuM3aHtV8wF_zNWKRRuI INSTANCE = new $$Lambda$LocationManager$5B718xTxuM3aHtV8wF_zNWKRRuI();

    private /* synthetic */ $$Lambda$LocationManager$5B718xTxuM3aHtV8wF_zNWKRRuI() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        String id;
        id = ((ALSGeofence) ((List) ((Map) obj).get(LocationManager.Label.NEW)).get(0)).getId();
        return id;
    }
}
