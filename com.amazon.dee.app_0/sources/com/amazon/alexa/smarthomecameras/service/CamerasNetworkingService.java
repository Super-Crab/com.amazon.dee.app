package com.amazon.alexa.smarthomecameras.service;

import com.amazon.alexa.smarthomecameras.constants.NetworkConstants;
import com.dee.app.http.CoralService;
import com.google.gson.JsonObject;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
/* loaded from: classes10.dex */
public class CamerasNetworkingService {
    private final CoralService coralService;

    public CamerasNetworkingService(CoralService coralService) {
        this.coralService = coralService;
    }

    public Observable<JsonObject> getPreferencesForEntityId(String str) {
        return this.coralService.request(NetworkConstants.GET_CAMERAS_PREFERENCES_URL).get().as(JsonObject.class).toObservable().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
    }
}
