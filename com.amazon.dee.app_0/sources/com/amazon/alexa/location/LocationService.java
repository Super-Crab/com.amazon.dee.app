package com.amazon.alexa.location;

import android.content.Intent;
import androidx.annotation.NonNull;
import com.amazon.alexa.location.models.ALSGeofence;
import com.amazon.alexa.location.models.GeoFenceStatus;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.util.List;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public interface LocationService {
    Completable clearGeofences();

    Single<String> createGeofence(double d, double d2, double d3);

    Single<JSONObject> getGeofenceStates();

    Single<JSONObject> getRegisteredGeofences();

    void recordLocationPermission();

    void recordLocationSetting();

    Completable reportStatusToALS(@NonNull List<GeoFenceStatus> list);

    Single<List<ALSGeofence>> restoreGeofences();

    void start();

    Single<List<ALSGeofence>> syncGeofence();

    Observable<String> triggerGeofence(Intent intent, int i, double d, int i2);

    Single<String> updateGeofence(@NonNull String str, double d, double d2, double d3);
}
