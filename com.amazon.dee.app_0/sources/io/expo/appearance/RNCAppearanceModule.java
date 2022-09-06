package io.expo.appearance;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes3.dex */
public class RNCAppearanceModule extends ReactContextBaseJavaModule implements LifecycleEventListener {
    private static final String CONFIG_CHANGED_ACTION = "android.intent.action.CONFIGURATION_CHANGED";
    public static final String REACT_CLASS = "RNCAppearance";
    private BroadcastReceiver mBroadcastReceiver;

    public RNCAppearanceModule(@NonNull final ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mBroadcastReceiver = null;
        this.mBroadcastReceiver = new BroadcastReceiver() { // from class: io.expo.appearance.RNCAppearanceModule.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                Configuration configuration = (Configuration) intent.getParcelableExtra("newConfig");
                RNCAppearanceModule rNCAppearanceModule = RNCAppearanceModule.this;
                rNCAppearanceModule.sendEvent(reactApplicationContext, "appearanceChanged", rNCAppearanceModule.getPreferences());
            }
        };
        reactApplicationContext.addLifecycleEventListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WritableMap getPreferences() {
        WritableMap createMap = Arguments.createMap();
        Context currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            currentActivity = getReactApplicationContext();
        }
        createMap.putString("colorScheme", getColorScheme(currentActivity.getResources().getConfiguration()));
        return createMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEvent(ReactContext reactContext, String str, @Nullable WritableMap writableMap) {
        if (reactContext.hasActiveCatalystInstance()) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, RealTimeTextConstants.COLON_SPACE);
            outline113.append(writableMap.toString());
            FLog.i("sendEvent", outline113.toString());
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
        }
    }

    private void updateAndSendAppearancePreferences() {
        sendEvent(getReactApplicationContext(), "appearanceChanged", getPreferences());
    }

    protected String getColorScheme(Configuration configuration) {
        int i = configuration.uiMode & 48;
        return (i == 0 || i == 16) ? "light" : i != 32 ? "no-preference" : "dark";
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    @Nullable
    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put("initialPreferences", getPreferences());
        return hashMap;
    }

    @Override // com.facebook.react.bridge.NativeModule
    @NonNull
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            return;
        }
        try {
            currentActivity.unregisterReceiver(this.mBroadcastReceiver);
        } catch (IllegalArgumentException e) {
            FLog.e(ReactConstants.TAG, "mBroadcastReceiver already unregistered", e);
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            FLog.e(ReactConstants.TAG, "no activity to register receiver");
            return;
        }
        currentActivity.registerReceiver(this.mBroadcastReceiver, new IntentFilter(CONFIG_CHANGED_ACTION));
        updateAndSendAppearancePreferences();
    }
}
