package com.amazon.dee.app.ui.web;

import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.ui.web.NavigationBridge;
import com.amazon.deecomms.api.CommsServiceV2;
import dagger.Lazy;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class NavigationBridge extends JavaScriptBridge {
    private static final String NAVIGATION_BRIDGE_FUNCTION_NAME_NAVIGATE = "navigate";
    private static final String NAVIGATION_BRIDGE_FUNCTION_NAME_PRE_NAVIGATE = "prenavigate";
    private static final String NAVIGATION_BRIDGE_FUNCTION_NAME_SET_AT_TOP = "setAtTop";
    private static final String TAG = Log.tag(NavigationBridge.class);
    Lazy<CommsServiceV2> commsServiceV2;
    IdentityService identityService;
    ArrayMap<String, JavaScriptBridgeMethod> methods;
    RoutingService routingService;
    WebViewDelegate webViewDelegate;

    /* loaded from: classes12.dex */
    class NavigateMethod implements JavaScriptBridgeMethod {
        NavigateMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            Log.i(NavigationBridge.TAG, String.format("[DEPRECATED] NavigateMethod called with requestParams:\n%s\n", jSONObject));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class PrenavigateMethod implements JavaScriptBridgeMethod {
        PrenavigateMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            NavigationBridge.this.getJavaScriptInterfaceName();
            String str = "NavigateMethod called: " + jSONObject;
            final String optString = jSONObject.optString("route", null);
            final String optString2 = jSONObject.optString(RouteParameter.PARENT);
            JSONObject jSONObject2 = jSONObject.getJSONObject("options");
            final boolean optBoolean = jSONObject2.optBoolean("replace");
            final boolean optBoolean2 = jSONObject2.optBoolean("clearStack");
            final boolean optBoolean3 = jSONObject2.optBoolean("noStackPush");
            final boolean optBoolean4 = jSONObject2.optBoolean("back");
            NavigationBridge.this.webViewDelegate.runOnUiThread(new Runnable() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$NavigationBridge$PrenavigateMethod$7kKkhXLhQEYmDIHZHh5Xan_jhN4
                @Override // java.lang.Runnable
                public final void run() {
                    NavigationBridge.PrenavigateMethod.this.lambda$execute$0$NavigationBridge$PrenavigateMethod(optBoolean4, optString, optString2, optBoolean, optBoolean3, optBoolean2);
                }
            });
        }

        public /* synthetic */ void lambda$execute$0$NavigationBridge$PrenavigateMethod(boolean z, String str, String str2, boolean z2, boolean z3, boolean z4) {
            RoutingService.RoutingBuilder route;
            if (z) {
                if (str == null) {
                    NavigationBridge.this.routingService.navigateBackward();
                    return;
                } else {
                    NavigationBridge.this.routingService.popUntil(str);
                    return;
                }
            }
            if (TextUtils.isEmpty(str) || str.equalsIgnoreCase("cards")) {
                String unused = NavigationBridge.TAG;
                route = NavigationBridge.this.routingService.route(RouteName.HOME);
            } else {
                String unused2 = NavigationBridge.TAG;
                String.format("Route is \"%s\"", str);
                route = NavigationBridge.this.routingService.match(str);
                if (route == null) {
                    route = NavigationBridge.this.routingService.route("web").with(RouteParameter.ROUTE, str).with(RouteParameter.PARENT, str2);
                }
            }
            if (!z2 && !z3) {
                String unused3 = NavigationBridge.TAG;
                String.format("replace: %s\nnoStackPush: %s", str, Boolean.valueOf(z3));
                route.addToBackStack();
            }
            if (z4) {
                String unused4 = NavigationBridge.TAG;
                String.format("clearStack: %s", Boolean.valueOf(z4));
                route.clearBackStack();
            }
            route.navigate();
        }
    }

    /* loaded from: classes12.dex */
    class SetAtTopMethod implements JavaScriptBridgeMethod {
        SetAtTopMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            Log.i(NavigationBridge.TAG, String.format("setAtTop called with requestParams:\n%s\n(This function is not yet implemented)", jSONObject));
        }
    }

    public NavigationBridge(JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, RoutingService routingService, Lazy<CommsServiceV2> lazy, WebViewDelegate webViewDelegate, IdentityService identityService) {
        super(javaScriptInjector, javaScriptResponseQueue);
        this.methods = new ArrayMap<>();
        this.routingService = routingService;
        this.commsServiceV2 = lazy;
        this.webViewDelegate = webViewDelegate;
        this.identityService = identityService;
        this.methods.put(NAVIGATION_BRIDGE_FUNCTION_NAME_NAVIGATE, new NavigateMethod());
        this.methods.put(NAVIGATION_BRIDGE_FUNCTION_NAME_SET_AT_TOP, new SetAtTopMethod());
        this.methods.put(NAVIGATION_BRIDGE_FUNCTION_NAME_PRE_NAVIGATE, new PrenavigateMethod());
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public Map<String, JavaScriptBridgeMethod> getExposedMethods() {
        return this.methods;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public final String getJavaScriptInterfaceName() {
        return "NavigationBridge";
    }
}
