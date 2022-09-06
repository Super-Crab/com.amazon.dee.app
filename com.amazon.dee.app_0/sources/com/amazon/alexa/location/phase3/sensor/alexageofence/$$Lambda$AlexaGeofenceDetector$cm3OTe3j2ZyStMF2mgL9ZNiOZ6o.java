package com.amazon.alexa.location.phase3.sensor.alexageofence;

import android.util.Log;
import io.reactivex.rxjava3.functions.Predicate;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.location.phase3.sensor.alexageofence.-$$Lambda$AlexaGeofenceDetector$cm3OTe3j2ZyStMF2mgL9ZNiOZ6o  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$AlexaGeofenceDetector$cm3OTe3j2ZyStMF2mgL9ZNiOZ6o implements Predicate {
    public static final /* synthetic */ $$Lambda$AlexaGeofenceDetector$cm3OTe3j2ZyStMF2mgL9ZNiOZ6o INSTANCE = new $$Lambda$AlexaGeofenceDetector$cm3OTe3j2ZyStMF2mgL9ZNiOZ6o();

    private /* synthetic */ $$Lambda$AlexaGeofenceDetector$cm3OTe3j2ZyStMF2mgL9ZNiOZ6o() {
    }

    @Override // io.reactivex.rxjava3.functions.Predicate
    public final boolean test(Object obj) {
        Throwable th = (Throwable) obj;
        Log.e(AlexaGeofenceDetector.TAG, "Error syncing states to persistent storage. The detector will still work, but storage might be off-sync.");
        return true;
    }
}
