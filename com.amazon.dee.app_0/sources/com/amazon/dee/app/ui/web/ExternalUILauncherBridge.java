package com.amazon.dee.app.ui.web;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.ui.external.ChromeCustomTabHelper;
import com.amazon.dee.app.ui.external.ExternalUIActivity;
import com.amazon.dee.app.ui.web.WebConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class ExternalUILauncherBridge extends JavaScriptBridge {
    private static final String KEY_LAUNCH_HOSTS = "launchHosts";
    private static final String KEY_LAUNCH_URL = "launchURL";
    private static final String KEY_LAUNCH_URL_REGEX = "launchRegex";
    private static final String TAG = "com.amazon.dee.app.ui.web.ExternalUILauncherBridge";
    Activity activity;
    EnvironmentService environmentService;
    private Map<String, JavaScriptBridgeMethod> mMethods;
    WebViewDelegate webViewDelegate;

    /* loaded from: classes12.dex */
    public enum BridgeAction {
        EXTERNAL("launchExternal"),
        THIRD_PARTY("launchThirdParty"),
        THIRD_PARTY_IN_APP("launchThirdPartyURLWithInAppBrowserTab"),
        CLOSE("closeExternalWebViews");
        
        private final String mName;

        BridgeAction(String str) {
            this.mName = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mName;
        }
    }

    /* loaded from: classes12.dex */
    private class CloseExternalWebViewsMethod implements JavaScriptBridgeMethod {
        private CloseExternalWebViewsMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            ExternalUILauncherBridge.this.activity.sendBroadcast(new Intent(ExternalUIActivity.BROADCAST_CLOSE));
            ExternalUILauncherBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* loaded from: classes12.dex */
    private class LaunchExternalURLMethod implements JavaScriptBridgeMethod {
        private LaunchExternalURLMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            String optString = jSONObject.optString(ExternalUILauncherBridge.KEY_LAUNCH_URL);
            String optString2 = jSONObject.optString(ExternalUILauncherBridge.KEY_LAUNCH_URL_REGEX);
            try {
                String str = ExternalUILauncherBridge.TAG;
                Log.i(str, "Launching this URL on current web view: " + optString);
                ExternalUILauncherBridge.this.launchExternalUIActivity(optString, optString2);
            } catch (Exception e) {
                Log.e(ExternalUILauncherBridge.TAG, e, "Couldn't create or launch the intent.", new Object[0]);
                javaScriptResponse.setError(true);
                javaScriptResponse.setErrorReason(e.getMessage());
            }
            ExternalUILauncherBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* loaded from: classes12.dex */
    private class LaunchThirdPartyURLMethod implements JavaScriptBridgeMethod {
        private LaunchThirdPartyURLMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            String optString = jSONObject.optString(ExternalUILauncherBridge.KEY_LAUNCH_URL);
            JSONArray optJSONArray = jSONObject.optJSONArray(ExternalUILauncherBridge.KEY_LAUNCH_HOSTS);
            ArrayList arrayList = new ArrayList();
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    arrayList.add(optJSONArray.getString(i));
                }
            }
            try {
                Log.i(ExternalUILauncherBridge.TAG, "Launching this URL on current web view: " + optString);
                ExternalUILauncherBridge.this.launchThirdPartyUIActivity(optString, arrayList);
            } catch (Exception e) {
                Log.e(ExternalUILauncherBridge.TAG, e, "Couldn't create or launch the intent.", new Object[0]);
                javaScriptResponse.setError(true);
                javaScriptResponse.setErrorReason(e.getMessage());
            }
            ExternalUILauncherBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* loaded from: classes12.dex */
    private class LaunchThirdPartyURLWithInAppBrowserTabMethod implements JavaScriptBridgeMethod {
        private LaunchThirdPartyURLWithInAppBrowserTabMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            String optString = jSONObject.optString(ExternalUILauncherBridge.KEY_LAUNCH_URL);
            JSONArray optJSONArray = jSONObject.optJSONArray(ExternalUILauncherBridge.KEY_LAUNCH_HOSTS);
            ArrayList arrayList = new ArrayList();
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    arrayList.add(optJSONArray.getString(i));
                }
            }
            try {
                Log.i(ExternalUILauncherBridge.TAG, "Launching this URL on current web view: " + optString);
                ExternalUILauncherBridge.this.launchThirdPartyURLWithInAppBrowserTabUIActivity(optString, arrayList);
            } catch (Exception e) {
                Log.e(ExternalUILauncherBridge.TAG, e, "Couldn't create or launch the intent.", new Object[0]);
                javaScriptResponse.setError(true);
                javaScriptResponse.setErrorReason(e.getMessage());
            }
            ExternalUILauncherBridge.this.completeRequest(javaScriptResponse);
        }
    }

    public ExternalUILauncherBridge(Activity activity, WebViewDelegate webViewDelegate, EnvironmentService environmentService, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        super(javaScriptInjector, javaScriptResponseQueue);
        this.mMethods = new HashMap();
        this.activity = activity;
        this.webViewDelegate = webViewDelegate;
        this.environmentService = environmentService;
        this.mMethods.put("launchExternalURL", new LaunchExternalURLMethod());
        this.mMethods.put("launchThirdPartyURL", new LaunchThirdPartyURLMethod());
        this.mMethods.put("launchThirdPartyURLWithInAppBrowserTab", new LaunchThirdPartyURLWithInAppBrowserTabMethod());
        this.mMethods.put("closeExternalWebViews", new CloseExternalWebViewsMethod());
    }

    private boolean launchActivity(Intent intent) {
        try {
            this.activity.startActivityForResult(intent, 2);
            return true;
        } catch (ActivityNotFoundException unused) {
            Log.i(TAG, String.format("Could not launch an activity with specified intent: %s", intent));
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchExternalUIActivity(String str, String str2) {
        Intent intent = new Intent(this.activity, ExternalUIActivity.class);
        intent.putExtra("android.intent.extra.TEXT", str);
        intent.putExtra("URL_REGEX_KEY", str2);
        intent.putExtra("BRIDGE_ACTION_KEY", BridgeAction.EXTERNAL.toString());
        if (launchActivity(intent)) {
            return;
        }
        Log.i(TAG, "Unable to launch the ExternalUIActivity");
        throw new ActivityNotFoundException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchThirdPartyUIActivity(String str, ArrayList<String> arrayList) {
        ChromeCustomTabHelper chromeCustomTabHelper = new ChromeCustomTabHelper(this.activity, str, arrayList, this.environmentService);
        if (chromeCustomTabHelper.isCalendarLink(str)) {
            chromeCustomTabHelper.openCustomTab();
            return;
        }
        Intent intent = new Intent(this.activity, ExternalUIActivity.class);
        intent.putExtra("android.intent.extra.TEXT", str);
        intent.putExtra(WebConstants.ExternalUIConstants.HOST_ALLOWLIST_KEY, arrayList);
        intent.putExtra("BRIDGE_ACTION_KEY", BridgeAction.THIRD_PARTY.toString());
        if (launchActivity(intent)) {
            return;
        }
        Log.i(TAG, "Unable to launch the ExternalUIActivity");
        throw new ActivityNotFoundException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchThirdPartyURLWithInAppBrowserTabUIActivity(String str, ArrayList<String> arrayList) {
        new ChromeCustomTabHelper(this.activity, str, arrayList, this.environmentService).openCustomTab();
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public Map<String, JavaScriptBridgeMethod> getExposedMethods() {
        return this.mMethods;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public final String getJavaScriptInterfaceName() {
        return "ExternalUILauncherBridge";
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        String stringExtra;
        if (i == 2) {
            this.javaScriptInjector.inject("Backbone.trigger('ExternalUIFinished');");
            if (intent == null || (stringExtra = intent.getStringExtra("RETURN_URL")) == null) {
                return;
            }
            this.webViewDelegate.loadUrl(stringExtra);
        }
    }
}
