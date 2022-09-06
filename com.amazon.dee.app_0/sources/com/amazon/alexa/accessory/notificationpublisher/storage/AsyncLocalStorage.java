package com.amazon.alexa.accessory.notificationpublisher.storage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.google.common.base.Strings;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class AsyncLocalStorage {
    private static final String LOG_TAG = "AsyncLocalStorage";
    private NativeLocalStorageModule storageModule;
    private String tag;

    public AsyncLocalStorage(@NonNull NativeLocalStorageModule nativeLocalStorageModule, @Nullable String str) throws IllegalArgumentException {
        if (nativeLocalStorageModule != null) {
            this.storageModule = nativeLocalStorageModule;
            this.tag = str;
            return;
        }
        throw new IllegalArgumentException("Cannot create AsyncLocalStorage without valid NativeLocalStorageModule");
    }

    private static String buildDataEntryString(@NonNull String str, @Nullable Object obj) {
        if (obj == null) {
            obj = JSONObject.NULL;
        }
        String str2 = null;
        try {
            str2 = new JSONObject().put("value", obj).put("key", str).put("timestamp", new Date().getTime()).put("ttl", 60000).toString();
            String str3 = LOG_TAG;
            Log.d(str3, "buildDataEntryString -- dataEntryString: " + str2);
            return str2;
        } catch (JSONException e) {
            Log.e(LOG_TAG, "buildDataEntryString -- JSON exception", e);
            return str2;
        }
    }

    private String buildKey(@NonNull String str) {
        return Strings.isNullOrEmpty(this.tag) ? str : GeneratedOutlineSupport1.outline92(new StringBuilder(), this.tag, ".", str);
    }

    public void clear(@Nullable AsyncLocalStorageCallback asyncLocalStorageCallback) {
        this.storageModule.clear(this.tag, asyncLocalStorageCallback);
    }

    public void clearSync() throws IllegalArgumentException, RxBlockingCallException {
        this.storageModule.clearSync(this.tag);
    }

    public void get(@NonNull String str, @Nullable ReadableMap readableMap, @NonNull GetValueCallback getValueCallback) {
        String buildKey = buildKey(str);
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString(HttpClientModule.ElementsRequestKey.MODULE, this.tag);
        writableNativeMap.putString("evictionTier", "1");
        this.storageModule.get(buildKey, writableNativeMap, getValueCallback);
    }

    public Object getSync(@NonNull String str, @Nullable ReadableMap readableMap) throws JSONException, RxBlockingCallException {
        String buildKey = buildKey(str);
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString(HttpClientModule.ElementsRequestKey.MODULE, this.tag);
        writableNativeMap.putString("evictionTier", "1");
        return this.storageModule.getSync(buildKey, writableNativeMap);
    }

    public void put(@NonNull String str, @Nullable Object obj) {
        put(str, obj, null, null);
    }

    public Object putSync(@NonNull String str, @Nullable Object obj, @Nullable ReadableMap readableMap) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        String buildKey = buildKey(str);
        return this.storageModule.putSync(buildKey, buildDataEntryString(buildKey, obj), readableMap);
    }

    public void remove(@NonNull String str, boolean z, @Nullable AsyncLocalStorageCallback asyncLocalStorageCallback) {
        this.storageModule.remove(buildKey(str), asyncLocalStorageCallback);
    }

    public void removeSync(@NonNull String str, boolean z) throws RxBlockingCallException {
        this.storageModule.removeSync(buildKey(str));
    }

    public void put(@NonNull String str, @Nullable Object obj, @Nullable ReadableMap readableMap, @Nullable PutValueCallback putValueCallback) {
        String buildKey = buildKey(str);
        this.storageModule.put(buildKey, buildDataEntryString(buildKey, obj), readableMap, putValueCallback);
    }
}
