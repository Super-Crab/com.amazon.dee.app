package com.dee.app.data.reactnative.bridges;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.cachemanager.AppDataCacheEntry;
import com.dee.app.data.api.ElementLocalStorage;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableNativeMap;
import java.util.HashMap;
import java.util.Map;
import rx.Completable;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
/* loaded from: classes2.dex */
public class ElementLocalStorageModule extends ReactContextBaseJavaModule {
    private static final Completable.CompletableSubscriber CACHE_ERROR_OBSERVER = new Completable.CompletableSubscriber() { // from class: com.dee.app.data.reactnative.bridges.ElementLocalStorageModule.1
        @Override // rx.Completable.CompletableSubscriber
        public void onCompleted() {
        }

        @Override // rx.Completable.CompletableSubscriber
        public void onError(Throwable th) {
            Log.e(ElementLocalStorageModule.LOG_TAG, "Async cache write error", th);
        }

        @Override // rx.Completable.CompletableSubscriber
        public void onSubscribe(Subscription subscription) {
        }
    };
    static final String ENCRYPT_KEY = "encrypt";
    static final String EVICTION_TIER_1 = "1";
    static final String EVICTION_TIER_2 = "2";
    static final String EVICTION_TIER_KEY = "evictionTier";
    static final String KEY_KEY = "key";
    static final String LOG_TAG = "ElementLocalStorageModule";
    static final String MODIFICATION_DATE_KEY = "modificationDate";
    static final String VALUE_KEY = "value";
    private String moduleName;
    private ElementLocalStorage storage;

    public ElementLocalStorageModule(ReactApplicationContext reactApplicationContext, ElementLocalStorage elementLocalStorage, String str) {
        super(reactApplicationContext);
        this.storage = elementLocalStorage;
        this.moduleName = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$get$0(String str, Promise promise, AppDataCacheEntry appDataCacheEntry) {
        if (appDataCacheEntry == null) {
            Log.w(LOG_TAG, String.format("Unable to find cache with entry %s", str));
            promise.resolve(null);
            return;
        }
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("key", str);
        writableNativeMap.putBoolean("encrypt", appDataCacheEntry.encrypt());
        writableNativeMap.putString("value", appDataCacheEntry.getData());
        writableNativeMap.putDouble("modificationDate", appDataCacheEntry.getModificationDate());
        writableNativeMap.putString("evictionTier", appDataCacheEntry.getEvictionTier() == 0 ? "1" : "2");
        promise.resolve(writableNativeMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$put$1(String str, Promise promise, AppDataCacheEntry appDataCacheEntry) {
        if (appDataCacheEntry == null) {
            Log.w(LOG_TAG, String.format("Unable to put cache with entry %s", str));
        }
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("key", str);
        writableNativeMap.putBoolean("encrypt", appDataCacheEntry.encrypt());
        writableNativeMap.putString("value", appDataCacheEntry.getData());
        writableNativeMap.putDouble("modificationDate", appDataCacheEntry.getModificationDate());
        writableNativeMap.putString("evictionTier", appDataCacheEntry.getEvictionTier() == 0 ? "1" : "2");
        promise.resolve(writableNativeMap);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @ReactMethod
    public void clear(String str, final Promise promise) {
        try {
            if (!TextUtils.isEmpty(str)) {
                this.storage.clear(str).toCompletable().subscribe(new Action0() { // from class: com.dee.app.data.reactnative.bridges.-$$Lambda$ElementLocalStorageModule$eNQ2vUQTU-TO8PbLns123yHm4BI
                    @Override // rx.functions.Action0
                    public final void call() {
                        Promise.this.resolve(true);
                    }
                }, $$Lambda$ElementLocalStorageModule$VAKuDcAEXaxrYLs4TfML0inuM4.INSTANCE);
            } else {
                promise.reject("Must specify prefix", new IllegalArgumentException());
            }
        } catch (IllegalArgumentException e) {
            Log.e(LOG_TAG, "Failed to clear cache in RN cache module.", e);
            promise.reject("Failed to clear cache in RN cache module.", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @ReactMethod
    public void get(final String str, ReadableMap readableMap, final Promise promise) {
        try {
            Bundle bundle = Arguments.toBundle(readableMap);
            bundle.putBoolean("extractValueFromJson", false);
            Observable<AppDataCacheEntry> observable = this.storage.get(str, bundle);
            Action1<? super AppDataCacheEntry> action1 = new Action1() { // from class: com.dee.app.data.reactnative.bridges.-$$Lambda$ElementLocalStorageModule$xBD7lsYIJatDXfQHSx3fV1fADA4
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    ElementLocalStorageModule.lambda$get$0(str, promise, (AppDataCacheEntry) obj);
                }
            };
            promise.getClass();
            observable.subscribe(action1, new $$Lambda$IojaaVBHKpeWgNLCvPDG5j29FH4(promise));
        } catch (IllegalArgumentException e) {
            Log.e(LOG_TAG, "Error getting key", e);
            promise.reject("Error getting key", e);
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    @Nullable
    public Map<String, Object> getConstants() {
        HashMap outline134 = GeneratedOutlineSupport1.outline134("keyKey", "key", "encryptedKey", "encrypt");
        outline134.put("valueKey", "value");
        outline134.put("modificationDateKey", "modificationDate");
        outline134.put("evictionTierKey", "evictionTier");
        outline134.put("evictionTier1", "1");
        outline134.put("evictionTier2", "2");
        return outline134;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return this.moduleName;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @ReactMethod
    public void put(final String str, String str2, ReadableMap readableMap, final Promise promise) {
        try {
            Bundle bundle = Arguments.toBundle(readableMap);
            bundle.putBoolean("jsonify", false);
            Observable<AppDataCacheEntry> put = this.storage.put(str, str2, bundle);
            Action1<? super AppDataCacheEntry> action1 = new Action1() { // from class: com.dee.app.data.reactnative.bridges.-$$Lambda$ElementLocalStorageModule$5D_evZ3uPhIrFtBsorkz6OqPKdI
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    ElementLocalStorageModule.lambda$put$1(str, promise, (AppDataCacheEntry) obj);
                }
            };
            promise.getClass();
            put.subscribe(action1, new $$Lambda$IojaaVBHKpeWgNLCvPDG5j29FH4(promise));
        } catch (IllegalArgumentException e) {
            Log.e(LOG_TAG, "Error putting key", e);
            promise.reject("Error putting key", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @ReactMethod
    public void remove(String str, Promise promise) {
        try {
            this.storage.remove(str).toCompletable().subscribe(CACHE_ERROR_OBSERVER);
            promise.resolve(str);
        } catch (IllegalArgumentException e) {
            Log.e(LOG_TAG, "Failed to remove key in RN cache module.", e);
            promise.reject("Failed to remove key in RN cache module.", e);
        }
    }
}
