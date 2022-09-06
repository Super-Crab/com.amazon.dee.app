package com.amazon.alexa.location;

import androidx.annotation.NonNull;
import com.amazon.alexa.location.models.ALSGeofence;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import java.util.List;
/* loaded from: classes9.dex */
public interface LocationDataService {
    Single<List<ALSGeofence>> getGeofences();

    Completable saveGeofences(@NonNull List<ALSGeofence> list);
}
