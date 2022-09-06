package com.dee.app.data.reactnative;

import com.dee.app.data.api.ElementLocalStorage;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/* loaded from: classes2.dex */
public class AlexaDataAPIReactNativePackage implements ReactPackage {
    private final ElementsDataService client;
    private ElementLocalStorage dataCache;
    private ElementLocalStorage dataStore;

    public AlexaDataAPIReactNativePackage(ElementsDataService elementsDataService, ElementLocalStorage elementLocalStorage, ElementLocalStorage elementLocalStorage2) {
        this.client = elementsDataService;
        this.dataCache = elementLocalStorage;
        this.dataStore = elementLocalStorage2;
    }

    @Override // com.facebook.react.ReactPackage
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        return Arrays.asList(new HttpClientModule(reactApplicationContext, this.client), new ElementsDataLocalCache(reactApplicationContext, this.dataCache), new ElementsDataLocalStorage(reactApplicationContext, this.dataStore));
    }

    @Override // com.facebook.react.ReactPackage
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        return Collections.emptyList();
    }
}
