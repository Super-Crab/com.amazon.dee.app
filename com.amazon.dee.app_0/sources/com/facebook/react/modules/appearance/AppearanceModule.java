package com.facebook.react.modules.appearance;

import android.content.Context;
import androidx.annotation.Nullable;
import com.facebook.fbreact.specs.NativeAppearanceSpec;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
@ReactModule(name = AppearanceModule.NAME)
/* loaded from: classes2.dex */
public class AppearanceModule extends NativeAppearanceSpec {
    private static final String APPEARANCE_CHANGED_EVENT_NAME = "appearanceChanged";
    public static final String NAME = "Appearance";
    private String mColorScheme;
    @Nullable
    private final OverrideColorScheme mOverrideColorScheme;

    /* loaded from: classes2.dex */
    public interface OverrideColorScheme {
        String getScheme();
    }

    public AppearanceModule(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, null);
    }

    private String colorSchemeForCurrentConfiguration(Context context) {
        OverrideColorScheme overrideColorScheme = this.mOverrideColorScheme;
        if (overrideColorScheme != null) {
            return overrideColorScheme.getScheme();
        }
        int i = context.getResources().getConfiguration().uiMode & 48;
        return (i == 16 || i != 32) ? "light" : "dark";
    }

    @Override // com.facebook.fbreact.specs.NativeAppearanceSpec
    public void addListener(String str) {
    }

    public void emitAppearanceChanged(String str) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("colorScheme", str);
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        if (reactApplicationContextIfActiveOrWarn != null) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactApplicationContextIfActiveOrWarn.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(APPEARANCE_CHANGED_EVENT_NAME, createMap);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeAppearanceSpec
    public String getColorScheme() {
        this.mColorScheme = colorSchemeForCurrentConfiguration(getReactApplicationContext());
        return this.mColorScheme;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public void onConfigurationChanged(Context context) {
        String colorSchemeForCurrentConfiguration = colorSchemeForCurrentConfiguration(context);
        if (!this.mColorScheme.equals(colorSchemeForCurrentConfiguration)) {
            this.mColorScheme = colorSchemeForCurrentConfiguration;
            emitAppearanceChanged(this.mColorScheme);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeAppearanceSpec
    public void removeListeners(double d) {
    }

    public AppearanceModule(ReactApplicationContext reactApplicationContext, @Nullable OverrideColorScheme overrideColorScheme) {
        super(reactApplicationContext);
        this.mColorScheme = "light";
        this.mOverrideColorScheme = overrideColorScheme;
        this.mColorScheme = colorSchemeForCurrentConfiguration(reactApplicationContext);
    }
}
