package com.amazon.alexa.accessorykit.findmy;

import android.location.Location;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes6.dex */
public interface LocationProvider {
    Single<Location> getLatestLocation();
}
