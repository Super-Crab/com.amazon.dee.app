package com.dee.app.data.reactnative;

import com.dee.app.data.api.ElementLocalStorage;
import com.dee.app.data.reactnative.bridges.ElementLocalStorageModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes2.dex */
public class ElementsDataLocalCache extends ElementLocalStorageModule {
    public static final int VERSION_NUMBER = 1;

    public ElementsDataLocalCache(ReactApplicationContext reactApplicationContext, ElementLocalStorage elementLocalStorage) {
        super(reactApplicationContext, elementLocalStorage, "NativeDataCache");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.dee.app.data.reactnative.bridges.ElementLocalStorageModule
    @ReactMethod
    public void clear(String str, Promise promise) {
        super.clear(str, promise);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.dee.app.data.reactnative.bridges.ElementLocalStorageModule
    @ReactMethod
    public void get(String str, ReadableMap readableMap, Promise promise) {
        super.get(str, readableMap, promise);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.dee.app.data.reactnative.bridges.ElementLocalStorageModule
    @ReactMethod
    public void put(String str, String str2, ReadableMap readableMap, Promise promise) {
        super.put(str, str2, readableMap, promise);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.dee.app.data.reactnative.bridges.ElementLocalStorageModule
    @ReactMethod
    public void remove(String str, Promise promise) {
        super.remove(str, promise);
    }
}
