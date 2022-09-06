package com.amazon.mobile.heremapsexplore;

import com.amazon.mobile.heremapsexplore.Components.HereMapCircleManager;
import com.amazon.mobile.heremapsexplore.Components.HereMapMarkerManager;
import com.amazon.mobile.heremapsexplore.Utilities.ImageUtils;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/* loaded from: classes13.dex */
public class ReactNativeHereMapsExplorePackage implements ReactPackage {
    @Override // com.facebook.react.ReactPackage
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        return Collections.emptyList();
    }

    @Override // com.facebook.react.ReactPackage
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        ImageUtils.getInstance().setContext(reactApplicationContext);
        return Arrays.asList(new HereMapExploreManager(), new HereMapMarkerManager(), new HereMapCircleManager());
    }
}
