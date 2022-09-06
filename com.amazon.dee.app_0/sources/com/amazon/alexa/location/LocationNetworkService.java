package com.amazon.alexa.location;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.location.models.ALSGeofence;
import com.amazon.alexa.location.models.ALSTriggerEvent;
import com.amazon.alexa.location.models.GeoFenceStatus;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import java.util.List;
/* loaded from: classes9.dex */
public interface LocationNetworkService {
    Single<ALSGeofence> createGeofence(@NonNull String str, @NonNull String str2, double d, double d2, double d3);

    Single<List<ALSGeofence>> getGeofencesByDevice(@NonNull String str, @NonNull String str2, @Nullable String str3);

    Completable reportGeofenceStatus(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull List<GeoFenceStatus> list);

    Single<String> triggerGeofence(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, ALSTriggerEvent aLSTriggerEvent, long j);

    Single<ALSGeofence> updateGeofence(@NonNull String str, @NonNull String str2, @NonNull String str3, double d, double d2, double d3);
}
