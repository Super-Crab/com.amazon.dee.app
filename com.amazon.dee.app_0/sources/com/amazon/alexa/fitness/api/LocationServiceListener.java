package com.amazon.alexa.fitness.api;

import android.location.Location;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: LocationService.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/fitness/api/LocationServiceListener;", "", "onLocationStatusChanged", "", "isDataAvailable", "", "onLocationUpdated", "location", "Landroid/location/Location;", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface LocationServiceListener {

    /* compiled from: LocationService.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class DefaultImpls {
        public static void onLocationStatusChanged(LocationServiceListener locationServiceListener, boolean z) {
        }

        public static void onLocationUpdated(LocationServiceListener locationServiceListener, @NotNull Location location) {
            Intrinsics.checkParameterIsNotNull(location, "location");
        }
    }

    void onLocationStatusChanged(boolean z);

    void onLocationUpdated(@NotNull Location location);
}
