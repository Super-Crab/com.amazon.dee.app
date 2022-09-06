package com.amazon.dee.app.elements.bridges;

import android.app.Activity;
import android.view.View;
import com.amazon.dee.app.R;
import com.amazon.dee.app.services.logging.Log;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
@ReactModule(name = "AccessibilityHelper")
/* loaded from: classes12.dex */
public class AccessibilityHelperModule extends ReactContextBaseJavaModule {
    private static final String TAG = Log.tag(AccessibilityHelperModule.class);

    public AccessibilityHelperModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "AccessibilityHelper";
    }

    @ReactMethod
    public void read(String str) {
        View findViewById;
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null || (findViewById = currentActivity.findViewById(R.id.rn_container)) == null) {
            return;
        }
        try {
            findViewById.announceForAccessibility(str);
        } catch (IndexOutOfBoundsException e) {
            String str2 = TAG;
            Log.w(str2, "announceForAccessibility retrying (" + str + "): " + e);
            try {
                findViewById.announceForAccessibility(str);
            } catch (IndexOutOfBoundsException e2) {
                String str3 = TAG;
                Log.w(str3, "announceForAccessibility failed (" + str + "): " + e2);
            }
        }
    }
}
