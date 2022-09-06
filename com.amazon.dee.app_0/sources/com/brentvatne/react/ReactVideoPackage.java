package com.brentvatne.react;

import com.brentvatne.exoplayer.DefaultReactExoplayerConfig;
import com.brentvatne.exoplayer.ReactExoplayerConfig;
import com.brentvatne.exoplayer.ReactExoplayerViewManager;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.Collections;
import java.util.List;
/* loaded from: classes13.dex */
public class ReactVideoPackage implements ReactPackage {
    private ReactExoplayerConfig config;

    public ReactVideoPackage() {
    }

    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    @Override // com.facebook.react.ReactPackage
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        return Collections.emptyList();
    }

    @Override // com.facebook.react.ReactPackage
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        if (this.config == null) {
            this.config = new DefaultReactExoplayerConfig(reactApplicationContext);
        }
        return Collections.singletonList(new ReactExoplayerViewManager(this.config));
    }

    public ReactVideoPackage(ReactExoplayerConfig reactExoplayerConfig) {
        this.config = reactExoplayerConfig;
    }
}
