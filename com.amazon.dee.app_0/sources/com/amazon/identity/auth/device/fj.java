package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.amazon.identity.auth.device.actor.ActorManagerCommunication;
import com.amazon.identity.auth.device.api.ActorInfo;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPActorManager;
import com.amazon.identity.auth.device.fe;
import com.amazon.identity.auth.device.framework.smartlock.CustomerInformationManager;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.HashSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class fj extends fi {
    final CustomerInformationManager er;
    final fe es;
    private final Context mContext;
    final gz mG;
    final MAPActorManager mH;
    String mI;

    public fj(WebView webView, CustomerInformationManager customerInformationManager, fe feVar) {
        super(webView, "MAPJavaScriptBridge");
        this.mContext = webView.getContext().getApplicationContext();
        this.mG = new gz(this.mContext);
        this.mH = new MAPActorManager(this.mContext);
        this.mI = null;
        this.er = customerInformationManager;
        this.es = feVar;
    }

    public String ew() {
        return this.mI;
    }

    @JavascriptInterface
    public void getCustomerInformationHint(final String str) {
        a("getCustomerInformationHint", new Runnable() { // from class: com.amazon.identity.auth.device.fj.7
            @Override // java.lang.Runnable
            public void run() {
                fj fjVar = fj.this;
                if (fjVar.er == null) {
                    fjVar.c("mapJSCallback", str, "{\"error\":\"Not_Supported\",\"errorMessage\":\"This call is unsupported in MAP\"}");
                    return;
                }
                try {
                    JSONArray jSONArray = new JSONObject(str).getJSONArray("hintTypes");
                    HashSet hashSet = new HashSet();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        hashSet.add(CustomerInformationManager.HintType.valueOf(jSONArray.getString(i)));
                    }
                    fj.this.er.a(hashSet, new CustomerInformationManager.a() { // from class: com.amazon.identity.auth.device.fj.7.1
                        @Override // com.amazon.identity.auth.device.framework.smartlock.CustomerInformationManager.a
                        public void b(fc fcVar) {
                            String str2;
                            try {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("resultType", fcVar.ep().name());
                                if (fcVar.eq() != null) {
                                    JSONObject jSONObject2 = new JSONObject();
                                    fb eq = fcVar.eq();
                                    if (eq.getEmail() != null) {
                                        jSONObject2.put("email", eq.getEmail());
                                    }
                                    if (eq.getName() != null) {
                                        jSONObject2.put("name", eq.getName());
                                    }
                                    if (eq.em() != null) {
                                        jSONObject2.put("namePron", eq.em());
                                    }
                                    if (eq.en() != null) {
                                        jSONObject2.put("phoneNumber", eq.en());
                                    }
                                    jSONObject.put(TtmlNode.TAG_INFORMATION, jSONObject2);
                                }
                                str2 = jSONObject.toString();
                            } catch (JSONException unused) {
                                str2 = "{\"error\":\"General_Error\",\"errorMessage\":\"An internal MAP error exception occurred\"}";
                            }
                            AnonymousClass7 anonymousClass7 = AnonymousClass7.this;
                            fj.this.c("mapJSCallback", str, str2);
                        }
                    });
                } catch (JSONException unused) {
                    fj.this.c("mapJSCallback", str, "{\"error\":\"Invalid_Input_Param\",\"errorMessage\":\"The API input is invalid JSON\"}");
                }
            }
        });
    }

    @JavascriptInterface
    public void getMAPAndroidBridgeVersion(final String str) {
        ji.c(new Runnable() { // from class: com.amazon.identity.auth.device.fj.6
            @Override // java.lang.Runnable
            public void run() {
                if (fj.this.bN("getMAPAndroidBridgeVersion")) {
                    fj fjVar = fj.this;
                    String str2 = str;
                    try {
                        io.i("MAPJavaScriptBridge", "MAP JS bridge getMAPAndroidBridgeVersion is called");
                        JSONObject jSONObject = new JSONObject(str2);
                        io.i("MAPJavaScriptBridge", String.format("MAP JS bridge getMAPAndroidBridgeVersion is called. callbackId: %s , callback name: %s", jSONObject.optString("callingId"), jSONObject.optString("callbackFunctionNameKey", "mapJSCallback")));
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("mapJSVersion", "MAP_Android_1");
                        fjVar.c("mapJSCallback", str2, jSONObject2.toString());
                    } catch (JSONException unused) {
                        fjVar.c("mapJSCallback", str2, "{\"error\":\"Invalid_Input_Param\",\"errorMessage\":\"The API input is invalid JSON\"}");
                    }
                }
            }
        });
    }

    @JavascriptInterface
    public void isSmsRetrieverEnabled(final String str) {
        a("isSmsRetrieverEnabled", new Runnable() { // from class: com.amazon.identity.auth.device.fj.8
            @Override // java.lang.Runnable
            public void run() {
                fj fjVar = fj.this;
                fe feVar = fjVar.es;
                if (feVar == null) {
                    fjVar.c("mapJSCallback", str, "{\"error\":\"Not_Supported\",\"errorMessage\":\"This call is unsupported in MAP\"}");
                } else {
                    feVar.a(new fe.a<ff>() { // from class: com.amazon.identity.auth.device.fj.8.1
                        @Override // com.amazon.identity.auth.device.fe.a
                        /* renamed from: a */
                        public void f(ff ffVar) {
                            String str2;
                            try {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("isSupported", ffVar.isSupported());
                                if (ffVar.eu() != null) {
                                    jSONObject.put("appHash", ffVar.eu());
                                }
                                str2 = jSONObject.toString();
                            } catch (JSONException unused) {
                                str2 = "{\"error\":\"General_Error\",\"errorMessage\":\"An internal MAP error exception occurred\"}";
                            }
                            AnonymousClass8 anonymousClass8 = AnonymousClass8.this;
                            fj.this.c("mapJSCallback", str, str2);
                        }
                    });
                }
            }
        });
    }

    @JavascriptInterface
    public void registerMAPSmsReceiver(final String str) {
        a("registerMAPSmsReceiver", new Runnable() { // from class: com.amazon.identity.auth.device.fj.9
            @Override // java.lang.Runnable
            public void run() {
                fj fjVar = fj.this;
                fe feVar = fjVar.es;
                if (feVar == null) {
                    fjVar.c("mapJSCallback", str, "{\"error\":\"Not_Supported\",\"errorMessage\":\"This call is unsupported in MAP\"}");
                } else {
                    feVar.b(new fe.a<fd>() { // from class: com.amazon.identity.auth.device.fj.9.1
                        @Override // com.amazon.identity.auth.device.fe.a
                        /* renamed from: b */
                        public void f(fd fdVar) {
                            String str2;
                            try {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("isRegistered", fdVar.isRegistered());
                                if (fdVar.es() != null) {
                                    jSONObject.put("sms", fdVar.es());
                                }
                                str2 = jSONObject.toString();
                            } catch (JSONException unused) {
                                str2 = "{\"error\":\"General_Error\",\"errorMessage\":\"An internal MAP error exception occurred\"}";
                            }
                            AnonymousClass9 anonymousClass9 = AnonymousClass9.this;
                            fj.this.c("mapJSCallback", str, str2);
                        }
                    });
                }
            }
        });
    }

    @JavascriptInterface
    public void switchActor(final String str) {
        ji.c(new Runnable() { // from class: com.amazon.identity.auth.device.fj.4
            @Override // java.lang.Runnable
            public void run() {
                MAPActorManager.ActorSwitchMode actorSwitchMode;
                if (fj.this.bN("switchActor")) {
                    final fj fjVar = fj.this;
                    String str2 = str;
                    try {
                        JSONObject jSONObject = new JSONObject(str2);
                        ActorInfo actorInfo = new ActorInfo(jSONObject.optString("program"), jSONObject.optString("cid"), jSONObject.optString("pid"), jSONObject.optString("actor_type"));
                        if (TextUtils.equals(jSONObject.optString(ActorManagerCommunication.SwitchActorAction.KEY_ACTOR_SWITCH_MODE), "Force")) {
                            actorSwitchMode = MAPActorManager.ActorSwitchMode.Force;
                        } else {
                            actorSwitchMode = MAPActorManager.ActorSwitchMode.Normal;
                        }
                        io.i("MAPJavaScriptBridge", String.format("MAP JS bridge switchActor is called with callbackId: %s, callback name: %s", jSONObject.optString("callingId"), jSONObject.optString("callbackFunctionNameKey", "mapJSCallback")));
                        fjVar.mH.switchActor(actorSwitchMode, actorInfo, new Bundle(), new Callback() { // from class: com.amazon.identity.auth.device.fj.5
                            @Override // com.amazon.identity.auth.device.api.Callback
                            public void onError(Bundle bundle) {
                                io.e("MAPJavaScriptBridge", "Switch actor failed.");
                            }

                            @Override // com.amazon.identity.auth.device.api.Callback
                            public void onSuccess(Bundle bundle) {
                                io.i("MAPJavaScriptBridge", "Switch actor succeeds.");
                            }
                        });
                    } catch (JSONException unused) {
                        fjVar.c("mapJSCallback", str2, "{\"error\":\"Invalid_Input_Param\",\"errorMessage\":\"The API input is invalid JSON\"}");
                    }
                }
            }
        });
    }

    @JavascriptInterface
    public void upgradeToken(final String str) {
        ji.c(new Runnable() { // from class: com.amazon.identity.auth.device.fj.1
            @Override // java.lang.Runnable
            public void run() {
                if (fj.this.bN("upgradeToken")) {
                    final fj fjVar = fj.this;
                    final String str2 = str;
                    try {
                        JSONObject jSONObject = new JSONObject(str2);
                        String optString = jSONObject.optString("cid");
                        String optString2 = jSONObject.optString("pid");
                        fjVar.mI = optString2;
                        String optString3 = jSONObject.optString("authCode");
                        io.i("MAPJavaScriptBridge", String.format("MAP JS bridge upgradeToken is called with callbackId: %s, callback name: %s", jSONObject.optString("callingId"), jSONObject.optString("callbackFunctionNameKey", "mapJSCallback")));
                        String.format("accountId: %s, actorId: %s, authCode: %s", optString, optString2, optString3);
                        io.dm("MAPJavaScriptBridge");
                        Bundle bundle = new Bundle();
                        bundle.putString("key_auth_code", optString3);
                        bundle.putString("key_token_type", "token_type_oauth_refresh_token");
                        final JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("returnValue", "MAP_Native_Acknowledged");
                        fjVar.mG.a(optString, optString2, bundle, new Callback() { // from class: com.amazon.identity.auth.device.fj.2
                            @Override // com.amazon.identity.auth.device.api.Callback
                            public void onError(Bundle bundle2) {
                                io.e("MAPJavaScriptBridge", "Token upgrade failed.");
                            }

                            @Override // com.amazon.identity.auth.device.api.Callback
                            public void onSuccess(Bundle bundle2) {
                                io.i("MAPJavaScriptBridge", "Token upgrade succeeds.");
                            }
                        }, new gy() { // from class: com.amazon.identity.auth.device.fj.3
                            @Override // com.amazon.identity.auth.device.gy
                            public void finish(Bundle bundle2) {
                                fj.this.c("mapJSCallback", str2, jSONObject2.toString());
                            }
                        });
                    } catch (JSONException unused) {
                        fjVar.c("mapJSCallback", str2, "{\"error\":\"Invalid_Input_Param\",\"errorMessage\":\"The API input is invalid JSON\"}");
                    }
                }
            }
        });
    }
}
