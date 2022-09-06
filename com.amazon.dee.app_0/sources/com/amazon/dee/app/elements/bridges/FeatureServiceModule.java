package com.amazon.dee.app.elements.bridges;

import com.amazon.alexa.featureservice.api.PlatformFeatureServiceV2;
import com.amazon.dee.app.elements.ElementsUtils;
import com.amazon.dee.app.services.logging.Log;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.module.annotations.ReactModule;
import java.util.Arrays;
@ReactModule(name = "ElementsFeatureServiceV2")
/* loaded from: classes12.dex */
public class FeatureServiceModule extends ReactContextBaseJavaModule {
    private static final String TAG = Log.tag(FeatureServiceModule.class);
    private final PlatformFeatureServiceV2 featureService;

    public FeatureServiceModule(ReactApplicationContext reactApplicationContext, PlatformFeatureServiceV2 platformFeatureServiceV2) {
        super(reactApplicationContext);
        this.featureService = platformFeatureServiceV2;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "ElementsFeatureServiceV2";
    }

    @ReactMethod
    public void getTreatmentAndRecordTrigger(String str, String str2, Promise promise) {
        promise.resolve(this.featureService.getTreatmentAndRecordTrigger(str, str2));
    }

    @ReactMethod
    public void prefetchFeatures(ReadableArray readableArray, Promise promise) {
        Object[] array = ElementsUtils.deepReadableArrayToList(readableArray).toArray();
        Log.i(TAG, "Pre-fetching features for Elements.");
        this.featureService.prefetchFeatures((String[]) Arrays.copyOf(array, array.length, String[].class));
        promise.resolve("");
    }

    @ReactMethod
    public void syncFeatureCache(Promise promise) {
        String serializedFeatureCache = this.featureService.getSerializedFeatureCache();
        Log.i(TAG, "featureServiceV2::syncFeatureCache");
        promise.resolve(serializedFeatureCache);
    }
}
