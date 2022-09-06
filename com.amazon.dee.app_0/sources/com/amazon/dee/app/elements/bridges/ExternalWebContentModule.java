package com.amazon.dee.app.elements.bridges;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.commscore.metrics.MetricKeys;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.ui.external.ChromeCustomTabHelper;
import com.amazon.dee.app.ui.external.ExternalUIActivity;
import com.amazon.dee.app.ui.web.ExternalUILauncherBridge;
import com.amazon.dee.app.ui.web.WebConstants;
import com.amazon.dee.app.util.Utils;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import java.util.ArrayList;
@ReactModule(name = "ExternalWebContent")
/* loaded from: classes12.dex */
public class ExternalWebContentModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    private static final String KEY_LAUNCH_HOSTS = "launchHosts";
    private static final String KEY_LAUNCH_THIRD_PARTY_URL_WITH_IN_APP_BROWSER_TAB = "launchThirdPartyURLWithInAppBrowserTab";
    private static final String KEY_LAUNCH_URL = "launchURL";
    private static final String KEY_LAUNCH_URL_REGEX = "launchRegex";
    private static final String MODULE_NAME = "NativeExternalWebContent";
    private static final String TAG = Log.tag(ExternalWebContentModule.class);
    private volatile Promise currentPromise;
    private EnvironmentService environmentService;

    public ExternalWebContentModule(ReactApplicationContext reactApplicationContext, EnvironmentService environmentService) {
        super(reactApplicationContext);
        this.environmentService = environmentService;
        reactApplicationContext.addActivityEventListener(this);
    }

    private void clearCurrentPromise() {
        this.currentPromise = null;
    }

    private boolean launchActivity(Intent intent, int i) {
        try {
            Activity currentActivity = getCurrentActivity();
            if (currentActivity == null) {
                return false;
            }
            currentActivity.startActivityForResult(intent, i);
            return true;
        } catch (ActivityNotFoundException unused) {
            Log.i(TAG, String.format("Could not launch an activity with specified intent: %s", intent));
            return false;
        }
    }

    @ReactMethod
    public void closeExternalWebViews(Promise promise) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            currentActivity.sendBroadcast(new Intent(ExternalUIActivity.BROADCAST_CLOSE));
        }
        promise.resolve(MetricKeys.SUFFIX_SUCCESS);
    }

    @VisibleForTesting
    Promise getCurrentPromise() {
        return this.currentPromise;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void launchExternalURL(ReadableMap readableMap, Promise promise) {
        if (this.currentPromise != null) {
            promise.reject("cannot launch external url", "waiting for active window to resolve");
            return;
        }
        String string = readableMap.hasKey(KEY_LAUNCH_URL) ? readableMap.getString(KEY_LAUNCH_URL) : null;
        if (string == null) {
            promise.reject("cannot launch external url", "url is missing");
            return;
        }
        String string2 = readableMap.hasKey(KEY_LAUNCH_URL_REGEX) ? readableMap.getString(KEY_LAUNCH_URL_REGEX) : null;
        try {
            Activity currentActivity = getCurrentActivity();
            if (currentActivity != null) {
                this.currentPromise = promise;
                Intent intent = new Intent(currentActivity, ExternalUIActivity.class);
                intent.putExtra("android.intent.extra.TEXT", string);
                intent.putExtra("URL_REGEX_KEY", string2);
                intent.putExtra("BRIDGE_ACTION_KEY", ExternalUILauncherBridge.BridgeAction.EXTERNAL.toString());
                if (launchActivity(intent, 11)) {
                    return;
                }
                this.currentPromise = null;
                Log.i(TAG, "Unable to launch the ExternalUIActivity");
                throw new ActivityNotFoundException();
            }
            promise.reject("cannot launch external web view", "activity is null");
        } catch (Exception e) {
            Log.e(TAG, e, "Couldn't create or launch the intent.", new Object[0]);
            promise.reject("cannot launch external url", "Couldn't create or launch the intent.", e);
            this.currentPromise = null;
        }
    }

    @ReactMethod
    public void launchThirdPartyURL(ReadableMap readableMap, Promise promise) {
        if (this.currentPromise != null) {
            promise.reject("cannot launch third party url", "waiting for active window to resolve");
            return;
        }
        ArrayList<String> arrayList = null;
        String string = readableMap.hasKey(KEY_LAUNCH_URL) ? readableMap.getString(KEY_LAUNCH_URL) : null;
        if (string == null) {
            promise.reject("cannot launch third party url", "url is missing");
            return;
        }
        ReadableArray array = readableMap.hasKey(KEY_LAUNCH_HOSTS) ? readableMap.getArray(KEY_LAUNCH_HOSTS) : null;
        if (array != null) {
            arrayList = Utils.objectArrayToStringArray(array.toArrayList());
        }
        Activity currentActivity = getCurrentActivity();
        ChromeCustomTabHelper chromeCustomTabHelper = new ChromeCustomTabHelper(currentActivity, string, arrayList, this.environmentService);
        if (!chromeCustomTabHelper.isAllowedUrl()) {
            promise.reject("cannot launch third party url", "url is not valid");
        } else if (currentActivity != null) {
            this.currentPromise = promise;
            if (readableMap.hasKey(KEY_LAUNCH_THIRD_PARTY_URL_WITH_IN_APP_BROWSER_TAB) && readableMap.getBoolean(KEY_LAUNCH_THIRD_PARTY_URL_WITH_IN_APP_BROWSER_TAB)) {
                chromeCustomTabHelper.openCustomTabForResult(9);
                return;
            }
            Intent intent = new Intent(currentActivity, ExternalUIActivity.class);
            intent.putExtra("android.intent.extra.TEXT", string);
            intent.putExtra(WebConstants.ExternalUIConstants.HOST_ALLOWLIST_KEY, arrayList);
            intent.putExtra("BRIDGE_ACTION_KEY", ExternalUILauncherBridge.BridgeAction.THIRD_PARTY.toString());
            if (launchActivity(intent, 11)) {
                return;
            }
            Log.i(TAG, "Unable to launch the ExternalUIActivity");
            throw new ActivityNotFoundException();
        } else {
            promise.reject("cannot launch third party web view", "activity is null");
        }
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        Promise currentPromise = getCurrentPromise();
        if (currentPromise == null) {
            return;
        }
        if (i != 9 && i != 11) {
            return;
        }
        try {
            if (i2 != -1 && i2 != 0) {
                currentPromise.reject("launch web content failed", "result code was not RESULT_OK or RESULT_CANCELED: " + i2);
            } else if (intent != null && intent.getExtras() != null && intent.getExtras().containsKey(ExternalUIActivity.EXTERNAL_URL_LOAD_ERROR_CODE)) {
                currentPromise.reject("launch web content failed", "error code: " + intent.getExtras().getInt(ExternalUIActivity.EXTERNAL_URL_LOAD_ERROR_CODE) + ", error message: " + intent.getExtras().getString(ExternalUIActivity.EXTERNAL_URL_LOAD_RESULT_DESCRIPTION, ""));
            } else {
                currentPromise.resolve(MetricKeys.SUFFIX_SUCCESS);
            }
        } finally {
            clearCurrentPromise();
        }
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }
}
