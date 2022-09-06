package com.amazon.alexa.location.phase3;

import com.amazon.alexa.location.phase3.sensor.osgeofence.OsGeofenceTriggerEvent;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.location.phase3.-$$Lambda$Phase3LocationService$Pw26v4OqdFF6AeKfEnxARnx-sqE  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$Phase3LocationService$Pw26v4OqdFF6AeKfEnxARnxsqE implements Function {
    public static final /* synthetic */ $$Lambda$Phase3LocationService$Pw26v4OqdFF6AeKfEnxARnxsqE INSTANCE = new $$Lambda$Phase3LocationService$Pw26v4OqdFF6AeKfEnxARnxsqE();

    private /* synthetic */ $$Lambda$Phase3LocationService$Pw26v4OqdFF6AeKfEnxARnxsqE() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        String str;
        str = ((OsGeofenceTriggerEvent) obj).geofenceId;
        return str;
    }
}
