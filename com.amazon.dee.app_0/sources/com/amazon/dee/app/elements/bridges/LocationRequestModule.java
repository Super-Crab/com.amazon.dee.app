package com.amazon.dee.app.elements.bridges;

import android.location.Location;
import androidx.annotation.NonNull;
import com.amazon.alexa.location.LocationProvider;
import com.amazon.dee.app.elements.CollectionsFactory;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import io.reactivex.rxjava3.functions.Consumer;
@ReactModule(name = "LocationRequest")
/* loaded from: classes12.dex */
public class LocationRequestModule extends ReactContextBaseJavaModule {
    private static final String MODULE_NAME = "LocationRequestModule";
    private final CollectionsFactory collectionsFactory;
    private final LocationProvider locationProvider;

    public LocationRequestModule(@NonNull ReactApplicationContext reactApplicationContext, @NonNull CollectionsFactory collectionsFactory, @NonNull LocationProvider locationProvider) {
        super(reactApplicationContext);
        this.collectionsFactory = collectionsFactory;
        this.locationProvider = locationProvider;
    }

    @ReactMethod
    public void getMostRecentLocation(final Promise promise) {
        this.locationProvider.getMostRecentLocation().subscribe(new Consumer() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$LocationRequestModule$rOncrn_Ekqm83DsBpYZOmMzoOyQ
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                LocationRequestModule.this.lambda$getMostRecentLocation$0$LocationRequestModule(promise, (Location) obj);
            }
        }, new Consumer() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$LocationRequestModule$TqsAKOXkNBZySdyeNkZzNnWG40Q
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Promise.this.reject("Failed to get the most recent location", (Throwable) obj);
            }
        });
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }

    public /* synthetic */ void lambda$getMostRecentLocation$0$LocationRequestModule(Promise promise, Location location) throws Throwable {
        WritableMap createMap = this.collectionsFactory.createMap();
        createMap.putDouble("latitude", location.getLatitude());
        createMap.putDouble("longitude", location.getLongitude());
        if (location.hasAltitude()) {
            createMap.putDouble("altitude", location.getAltitude());
        }
        if (location.hasAccuracy()) {
            createMap.putDouble("accuracy", location.getAccuracy());
        }
        WritableMap createMap2 = this.collectionsFactory.createMap();
        createMap2.putDouble("timestamp", location.getTime());
        createMap2.putMap("coords", createMap);
        promise.resolve(createMap2);
    }
}
