package com.amazon.alexa.accessory.notificationpublisher.storage;

import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.dee.app.cachemanager.AppDataCacheEntry;
import com.dee.app.cachemanager.Cache;
import com.dee.app.cachemanager.CacheMetadata;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.google.common.base.Optional;
import com.google.common.base.Strings;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Completable;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
/* loaded from: classes.dex */
public final class NativeLocalStorageModule {
    private static final Completable.CompletableSubscriber CACHE_ERROR_OBSERVER = new Completable.CompletableSubscriber() { // from class: com.amazon.alexa.accessory.notificationpublisher.storage.NativeLocalStorageModule.1
        @Override // rx.Completable.CompletableSubscriber
        public void onCompleted() {
        }

        @Override // rx.Completable.CompletableSubscriber
        public void onError(Throwable th) {
            Log.e(NativeLocalStorageModule.TAG, "Async cache write error", th);
        }

        @Override // rx.Completable.CompletableSubscriber
        public void onSubscribe(Subscription subscription) {
        }
    };
    private static final String TAG = "NativeLocalStorageModule";
    private static final int TIMEOUT_MILLISECONDS = 1000;
    private Cache<AppDataCacheEntry> dataStore;

    public NativeLocalStorageModule(Cache<AppDataCacheEntry> cache) {
        this.dataStore = cache;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void clear(@NonNull String str, @Nullable AsyncLocalStorageCallback asyncLocalStorageCallback) {
        try {
            if (!Strings.isNullOrEmpty(str)) {
                this.dataStore.clear(str).toCompletable().subscribe(CACHE_ERROR_OBSERVER);
            } else if (asyncLocalStorageCallback != null) {
                asyncLocalStorageCallback.onError(new IllegalArgumentException("Must specify prefix"));
            }
            if (asyncLocalStorageCallback != null) {
                asyncLocalStorageCallback.onComplete(true);
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to clear cache in RN cache module.", e);
            if (asyncLocalStorageCallback != null) {
                asyncLocalStorageCallback.onError(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void clearSync(@NonNull String str) throws IllegalArgumentException, RxBlockingCallException {
        try {
            try {
                if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                    MetricsRecorder.getInstance().recordCounterWithDebounce("FocusFilter_blocking_call_run_on_main_thread_clearSync");
                    String str2 = TAG;
                    Log.d(str2, "clearSync is running on main thread, prefix: " + str);
                }
                if (!Strings.isNullOrEmpty(str)) {
                    this.dataStore.clear(str).timeout(1000L, TimeUnit.MILLISECONDS).toBlocking().singleOrDefault(null);
                } else {
                    throw new IllegalArgumentException("Must specify prefix");
                }
            } catch (IllegalArgumentException e) {
                throw e;
            }
        } catch (Exception e2) {
            throw new RxBlockingCallException("clearSync failed with Exception", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void get(@NonNull String str, ReadableMap readableMap, @NonNull final GetValueCallback getValueCallback) {
        this.dataStore.get(str, CacheMetadata.EMPTY).subscribe(new Action1<Optional<AppDataCacheEntry>>() { // from class: com.amazon.alexa.accessory.notificationpublisher.storage.NativeLocalStorageModule.2
            @Override // rx.functions.Action1
            public void call(Optional<AppDataCacheEntry> optional) {
                if (!optional.isPresent()) {
                    getValueCallback.onValueNotPresent();
                    return;
                }
                AppDataCacheEntry appDataCacheEntry = optional.get();
                if (appDataCacheEntry == null) {
                    getValueCallback.onValueNotPresent();
                    return;
                }
                try {
                    getValueCallback.onValuePresent(new JSONObject(appDataCacheEntry.getData()).get("value"));
                } catch (JSONException e) {
                    getValueCallback.onError(e);
                }
            }
        }, new Action1<Throwable>() { // from class: com.amazon.alexa.accessory.notificationpublisher.storage.NativeLocalStorageModule.3
            @Override // rx.functions.Action1
            public void call(Throwable th) {
                getValueCallback.onError(th);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized Object getSync(@NonNull String str, ReadableMap readableMap) throws JSONException, RxBlockingCallException {
        try {
            try {
                if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                    MetricsRecorder.getInstance().recordCounterWithDebounce("FocusFilter_blocking_call_run_on_main_thread_getSync");
                    String str2 = TAG;
                    Log.d(str2, "getSync is running on main thread, key: " + str);
                }
                Optional<AppDataCacheEntry> single = this.dataStore.get(str, CacheMetadata.EMPTY).timeout(1000L, TimeUnit.MILLISECONDS).toBlocking().single();
                if (single != null && single.isPresent()) {
                    AppDataCacheEntry appDataCacheEntry = single.get();
                    if (appDataCacheEntry == null) {
                        return null;
                    }
                    return new JSONObject(appDataCacheEntry.getData()).get("value");
                }
                return null;
            } catch (JSONException e) {
                throw e;
            }
        } catch (Exception e2) {
            throw new RxBlockingCallException("getSync failed", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void put(@NonNull String str, @NonNull String str2, @Nullable ReadableMap readableMap, @Nullable final PutValueCallback putValueCallback) {
        if (Strings.isNullOrEmpty(str)) {
            Log.e(TAG, "Cannot put value with empty key argument.");
            putValueCallback.onError(new IllegalArgumentException("Cannot put value with empty key argument."));
        } else if (Strings.isNullOrEmpty(str2)) {
            Log.e(TAG, "Cannot put data with empty value argument.");
            putValueCallback.onError(new IllegalArgumentException("Cannot put data with empty value argument."));
        } else {
            if (readableMap == null) {
                readableMap = new WritableNativeMap();
            }
            int i = 1;
            boolean z = readableMap.hasKey("encrypt") ? readableMap.getBoolean("encrypt") : true;
            int i2 = (!readableMap.hasKey("evictionTier") || !readableMap.getString("evictionTier").equals("1")) ? 1 : 0;
            if (i2 != 0) {
                i = 0;
            }
            final AppDataCacheEntry appDataCacheEntry = new AppDataCacheEntry(str2, i2, z);
            if (putValueCallback != null) {
                this.dataStore.put(str, appDataCacheEntry, new CacheMetadata(i)).toCompletable().subscribe(new Action0() { // from class: com.amazon.alexa.accessory.notificationpublisher.storage.NativeLocalStorageModule.4
                    @Override // rx.functions.Action0
                    public void call() {
                        try {
                            putValueCallback.onComplete(new JSONObject(appDataCacheEntry.getData()).get("value"));
                        } catch (JSONException e) {
                            putValueCallback.onError(e);
                        }
                    }
                }, new Action1<Throwable>() { // from class: com.amazon.alexa.accessory.notificationpublisher.storage.NativeLocalStorageModule.5
                    @Override // rx.functions.Action1
                    public void call(Throwable th) {
                        putValueCallback.onError(th);
                    }
                });
            } else {
                this.dataStore.put(str, appDataCacheEntry, new CacheMetadata(i));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized Object putSync(@NonNull String str, @NonNull String str2, @Nullable ReadableMap readableMap) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        AppDataCacheEntry appDataCacheEntry;
        try {
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                MetricsRecorder.getInstance().recordCounterWithDebounce("FocusFilter_blocking_call_run_on_main_thread_putSync");
                Log.d(TAG, "putSync is running on main thread, key: " + str);
            }
            if (!Strings.isNullOrEmpty(str)) {
                if (!Strings.isNullOrEmpty(str2)) {
                    if (readableMap == null) {
                        readableMap = new WritableNativeMap();
                    }
                    int i = 1;
                    boolean z = readableMap.hasKey("encrypt") ? readableMap.getBoolean("encrypt") : true;
                    int i2 = (!readableMap.hasKey("evictionTier") || !readableMap.getString("evictionTier").equals("1")) ? 1 : 0;
                    if (i2 != 0) {
                        i = 0;
                    }
                    appDataCacheEntry = new AppDataCacheEntry(str2, i2, z);
                    this.dataStore.put(str, appDataCacheEntry, new CacheMetadata(i)).timeout(1000L, TimeUnit.MILLISECONDS).toBlocking().singleOrDefault(null);
                } else {
                    Log.e(TAG, "Cannot put data with empty value argument.");
                    throw new IllegalArgumentException("Cannot put data with empty value argument.");
                }
            } else {
                Log.e(TAG, "Cannot put value with empty key argument.");
                throw new IllegalArgumentException("Cannot put value with empty key argument.");
            }
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (JSONException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new RxBlockingCallException("putSync failed with Exception", e3);
        }
        return new JSONObject(appDataCacheEntry.getData()).get("value");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void remove(@NonNull String str, @Nullable AsyncLocalStorageCallback asyncLocalStorageCallback) {
        try {
            this.dataStore.remove(str, CacheMetadata.EMPTY).toCompletable().subscribe(CACHE_ERROR_OBSERVER);
            if (asyncLocalStorageCallback != null) {
                asyncLocalStorageCallback.onComplete(str);
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to remove key in RN cache module.", e);
            if (asyncLocalStorageCallback != null) {
                asyncLocalStorageCallback.onError(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void removeSync(@NonNull String str) throws RxBlockingCallException {
        try {
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                MetricsRecorder.getInstance().recordCounterWithDebounce("FocusFilter_blocking_call_run_on_main_thread_removeSync");
                String str2 = TAG;
                Log.d(str2, "removeSync is running on main thread, key: " + str);
            }
            this.dataStore.remove(str, CacheMetadata.EMPTY).timeout(1000L, TimeUnit.MILLISECONDS).toBlocking().singleOrDefault(null);
        } catch (Exception e) {
            throw new RxBlockingCallException("removeSync failed with Exception", e);
        }
    }
}
