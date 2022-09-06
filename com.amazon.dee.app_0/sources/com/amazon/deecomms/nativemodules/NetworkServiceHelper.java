package com.amazon.deecomms.nativemodules;

import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CommsComponent;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
/* loaded from: classes12.dex */
public class NetworkServiceHelper extends ReactContextBaseJavaModule {
    private final CommsComponent mCommsComponent;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkServiceHelper(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        CommsComponent component = CommsDaggerWrapper.getComponent();
        this.mCommsComponent = component;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "CommsNetworkServiceHelper";
    }

    @ReactMethod
    public void retrieveRequestInfo(Promise promise) {
        CommsInternal commsInternal = this.mCommsComponent.getCommsInternal();
        WritableMap createMap = Arguments.createMap();
        createMap.putString(Constants.ACMS_HEADER_CLIENT_ID, commsInternal.getClientID());
        createMap.putString("User-Agent", Utils.getUserAgent());
        createMap.putString("Accept-Language", commsInternal.getLocale());
        createMap.putString(Constants.ACCEPT_PFM, this.mCommsComponent.getCommsIdentityManager().getPreferredMarketplace(false));
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putString("url", this.mCommsComponent.getAppUrl().getACMSServiceURL());
        createMap2.putMap(HttpClientModule.ElementsRequestKey.HEADERS, createMap);
        promise.resolve(createMap2);
    }

    @ReactMethod
    public void setupCookiesForCVF(Promise promise) {
        this.mCommsComponent.getCommsIdentityManager().getDirectedId("setupCookiesForCVF", false);
        WritableMap createMap = Arguments.createMap();
        createMap.putString("url", this.mCommsComponent.getAppUrl().getACMSServiceURL());
        promise.resolve(createMap);
    }

    public NetworkServiceHelper(ReactApplicationContext reactApplicationContext, CommsComponent commsComponent) {
        super(reactApplicationContext);
        this.mCommsComponent = commsComponent;
    }
}
