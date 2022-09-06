package com.amazon.alexa.accessory.frames.provider;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.cachemanager.AppDataCacheEntry;
import com.dee.app.data.api.ElementLocalStorage;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Observable;
/* loaded from: classes.dex */
public final class StorageUtils {
    private static final String TAG = "StorageUtils";
    private static final int TIMEOUT_MILLISECONDS = 1000;

    private StorageUtils() {
    }

    public static Observable<Void> clear(@NonNull String str, @NonNull String str2) {
        return getElementsLocalStorage().remove(str, str2).timeout(1000L, TimeUnit.MILLISECONDS);
    }

    public static Observable<String> get(@NonNull String str, @NonNull String str2) {
        return getElementsLocalStorage().get(str, str2).timeout(1000L, TimeUnit.MILLISECONDS).flatMap($$Lambda$StorageUtils$cg4dAZBjqUGxoIhthuHo5SjbZ64.INSTANCE);
    }

    public static ElementLocalStorage getElementsLocalStorage() {
        return (ElementLocalStorage) GeneratedOutlineSupport1.outline20(ElementLocalStorage.class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Observable lambda$get$1(AppDataCacheEntry appDataCacheEntry) {
        if (appDataCacheEntry == null) {
            return Observable.just(null);
        }
        return Observable.just(appDataCacheEntry.getData());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Observable lambda$put$0(AppDataCacheEntry appDataCacheEntry) {
        if (appDataCacheEntry == null) {
            return Observable.just(null);
        }
        return Observable.just(appDataCacheEntry.getData());
    }

    public static Observable<String> put(@NonNull String str, @NonNull String str2, @NonNull Object obj) throws JSONException {
        Bundle outline13 = GeneratedOutlineSupport1.outline13("jsonify", false);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("key", str2);
        jSONObject.put("value", obj);
        jSONObject.put("timestamp", System.currentTimeMillis());
        jSONObject.put("ttl", 3600000);
        return getElementsLocalStorage().put(str, str2, jSONObject.toString(), outline13).timeout(1000L, TimeUnit.MILLISECONDS).flatMap($$Lambda$StorageUtils$ewC0gStZiaF3PftPeDk_tlAVX4.INSTANCE);
    }
}
