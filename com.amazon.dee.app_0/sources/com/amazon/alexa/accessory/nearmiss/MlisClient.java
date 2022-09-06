package com.amazon.alexa.accessory.nearmiss;

import io.reactivex.rxjava3.core.Completable;
/* loaded from: classes.dex */
public interface MlisClient {
    Completable upload(NearMissManifest nearMissManifest);
}
