package com.amazon.alexa.location;

import android.location.Location;
import io.reactivex.rxjava3.functions.BiFunction;
import java.util.List;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.location.-$$Lambda$LocationManager$sbQ8tNXLFT8sFM2pK3vrMXgqP5w  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$LocationManager$sbQ8tNXLFT8sFM2pK3vrMXgqP5w implements BiFunction {
    public static final /* synthetic */ $$Lambda$LocationManager$sbQ8tNXLFT8sFM2pK3vrMXgqP5w INSTANCE = new $$Lambda$LocationManager$sbQ8tNXLFT8sFM2pK3vrMXgqP5w();

    private /* synthetic */ $$Lambda$LocationManager$sbQ8tNXLFT8sFM2pK3vrMXgqP5w() {
    }

    @Override // io.reactivex.rxjava3.functions.BiFunction
    public final Object apply(Object obj, Object obj2) {
        return LocationManager.lambda$getGeofenceStates$9((List) obj, (Location) obj2);
    }
}
