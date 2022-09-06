package com.amazon.alexa.location;

import com.amazon.alexa.location.LocationManager;
import com.amazon.alexa.location.models.ALSGeofence;
import io.reactivex.rxjava3.functions.Function;
import java.util.List;
import java.util.Map;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.location.-$$Lambda$LocationManager$iGSgOfg356XrvnQihZNVyvGxbS8  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$LocationManager$iGSgOfg356XrvnQihZNVyvGxbS8 implements Function {
    public static final /* synthetic */ $$Lambda$LocationManager$iGSgOfg356XrvnQihZNVyvGxbS8 INSTANCE = new $$Lambda$LocationManager$iGSgOfg356XrvnQihZNVyvGxbS8();

    private /* synthetic */ $$Lambda$LocationManager$iGSgOfg356XrvnQihZNVyvGxbS8() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        String id;
        id = ((ALSGeofence) ((List) ((Map) obj).get(LocationManager.Label.NEW)).get(0)).getId();
        return id;
    }
}
