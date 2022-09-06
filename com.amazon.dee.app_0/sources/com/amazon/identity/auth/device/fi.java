package com.amazon.identity.auth.device;

import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.net.URL;
import java.util.Map;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class fi {
    WebView el;
    String mTag;
    Map<String, String> mz = GeneratedOutlineSupport1.outline136();

    /* JADX INFO: Access modifiers changed from: package-private */
    public fi(WebView webView, String str) {
        this.el = webView;
        this.mTag = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, String str2, String str3, String str4) {
        if (!str.equals("isUserVerifyingPlatformAuthenticatorAvailable") && !str.equals("isAuthenticatorCredentialAvailable")) {
            a("mapJSCallback", str2, str3, str4);
        } else {
            c("mapJSCallback", str2, PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean bN(String str) {
        URL dJ = jl.dJ(this.el.getUrl());
        if (dJ != null) {
            String host = dJ.getHost();
            String path = dJ.getPath();
            StringBuilder sb = new StringBuilder("web page host: ");
            sb.append(host);
            sb.append(", path: ");
            sb.append(path);
            io.dm("JavaScriptBridgeBase");
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            mq.incrementCounterAndRecord(GeneratedOutlineSupport1.outline93(sb2, ":", host, ":", path), new String[0]);
            for (String str2 : EnvironmentUtils.cg()) {
                if (host.endsWith(str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(String str, String str2, String str3) {
        io.i(this.mTag, "loadCallbackFunction");
        final String d = d(str, str2, str3);
        String str4 = this.mTag;
        "Loading callback javascript: ".concat(String.valueOf(d));
        io.dm(str4);
        ji.c(new Runnable() { // from class: com.amazon.identity.auth.device.fi.1
            @Override // java.lang.Runnable
            public void run() {
                fi.this.el.loadUrl(d);
            }
        });
    }

    String d(String str, String str2, String str3) {
        return String.format("javascript:if (typeof %1$s !== 'undefined' && typeof %1$s === 'function'){%1$s(%2$s,%3$s);}", str, str2, str3);
    }

    void e(final String str, String str2, String str3) {
        try {
            if (this.mz.containsKey(str)) {
                String str4 = this.mTag;
                io.i(str4, str2 + " inject token failed because the token map already contains an entry for the callbackId.");
                mq.incrementCounterAndRecord(str2 + ":TokenAlreadyInjected", new String[0]);
                b(str2, str3, "{\"error\":\"General_Error\",\"errorMessage\":\"An internal MAP error exception occurred\"}", "Unauthorized to call");
            } else if (this.mz.size() >= 15) {
                String str5 = this.mTag;
                io.i(str5, str2 + " inject token failed due to too many token keys in the token map.");
                mq.incrementCounterAndRecord(str2 + ":TooManyTokens", new String[0]);
                b(str2, str3, "{\"error\":\"General_Error\",\"errorMessage\":\"An internal MAP error exception occurred\"}", "Unauthorized to call");
            } else {
                String uuid = UUID.randomUUID().toString();
                WebView webView = this.el;
                webView.loadUrl("javascript: var newDiv = document.createElement(\"div\");newDiv.innerText = \"" + uuid + "\";newDiv.setAttribute(\"id\",\"" + str + "\");newDiv.style.display=\"none\";document.body.appendChild(newDiv);");
                this.mz.put(str, uuid);
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.amazon.identity.auth.device.fi.4
                    @Override // java.lang.Runnable
                    public void run() {
                        fi.this.mz.remove(str);
                    }
                }, 10000L);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("callingId", str);
                jSONObject.put("originalFunction", str2);
                c("injectTokenCallback", str3, jSONObject.toString());
            }
        } catch (Exception unused) {
            String str6 = this.mTag;
            io.i(str6, str2 + " inject token call failed because an exception was thrown.");
            mq.incrementCounterAndRecord(str2 + ":InjectTokenException", new String[0]);
            b(str2, str3, "{\"error\":\"General_Error\",\"errorMessage\":\"An internal MAP error exception occurred\"}", "Unauthorized to call");
        }
    }

    boolean q(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("token", null);
            String string = jSONObject.getString("callingId");
            if (optString == null) {
                String str3 = this.mTag;
                io.i(str3, "Injecting token for " + str2 + " call.");
                e(string, str2, str);
                return false;
            } else if (!r(optString, string)) {
                String str4 = this.mTag;
                io.i(str4, str2 + " call failed due to authentication error.");
                mq.incrementCounterAndRecord(str2 + ":InvalidAuthToken", new String[0]);
                b(str2, str, "{\"error\":\"General_Error\",\"errorMessage\":\"An internal MAP error exception occurred\"}", "Unauthorized to call");
                return false;
            } else {
                String str5 = this.mTag;
                io.i(str5, str2 + " call was successfully authenticated.");
                mq.incrementCounterAndRecord(str2 + ":SuccessfulAuth", new String[0]);
                return true;
            }
        } catch (JSONException unused) {
            String str6 = this.mTag;
            io.i(str6, str2 + " call failed due to invalid input");
            b(str2, "Invalid JSON input", "{\"error\":\"Invalid_Input_Param\",\"errorMessage\":\"The API input is invalid JSON\"}", "Unauthorized to call");
            return false;
        } catch (Exception unused2) {
            String str7 = this.mTag;
            io.i(str7, str2 + " call failed due exception being thrown while authenticating the call");
            b(str2, str, "{\"error\":\"General_Error\",\"errorMessage\":\"An internal MAP error exception occurred\"}", "Unauthorized to call");
            return false;
        }
    }

    boolean r(String str, String str2) {
        String remove = this.mz.remove(str2);
        if (remove != null) {
            return remove.equals(str);
        }
        return false;
    }

    void a(String str, String str2, String str3, String str4) {
        try {
            JSONObject jSONObject = new JSONObject(str3);
            jSONObject.put("errorMessage", str4);
            c(str, str2, jSONObject.toString());
        } catch (Exception unused) {
            c(str, str2, "{\"error\":\"General_Error\",\"errorMessage\":\"An internal MAP error exception occurred\"}");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(final String str, final Runnable runnable) {
        ji.c(new Runnable() { // from class: com.amazon.identity.auth.device.fi.2
            @Override // java.lang.Runnable
            public void run() {
                if (fi.this.bN(str)) {
                    runnable.run();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(final String str, final String str2, final fg fgVar) {
        ji.c(new Runnable() { // from class: com.amazon.identity.auth.device.fi.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (!fi.this.bN(str)) {
                        return;
                    }
                    if (str2 == null) {
                        String str3 = fi.this.mTag;
                        io.i(str3, "Call to " + str + " failed because JSON input was null");
                        mq.incrementCounterAndRecord(str + ":NullInput", new String[0]);
                        fi.this.b(str, null, "{\"error\":\"Invalid_Input_Param\",\"errorMessage\":\"The API input is invalid JSON\"}", "JSON input was null");
                        return;
                    }
                    JSONObject jSONObject = new JSONObject(str2);
                    if (!jSONObject.getString("callingId").matches("\\w+\\d+")) {
                        fi.this.b(str, "\"Invalid JSON Input\"", "{\"error\":\"Invalid_Input_Param\",\"errorMessage\":\"The API input is invalid JSON\"}", "Invalid callbackId");
                    } else if (!fi.this.q(str2, str)) {
                    } else {
                        fgVar.a(jSONObject, new fk() { // from class: com.amazon.identity.auth.device.fi.3.1
                            @Override // com.amazon.identity.auth.device.fk
                            public void bO(String str4) {
                                AnonymousClass3 anonymousClass3 = AnonymousClass3.this;
                                fi.this.c("mapJSCallback", str2, str4);
                            }

                            @Override // com.amazon.identity.auth.device.fk
                            public void s(String str4, String str5) {
                                AnonymousClass3 anonymousClass3 = AnonymousClass3.this;
                                fi.this.a("mapJSCallback", str2, str4, str5);
                            }
                        });
                    }
                } catch (JSONException unused) {
                    fi.this.b(str, "\"Invalid JSON Input\"", "{\"error\":\"Invalid_Input_Param\",\"errorMessage\":\"The API input is invalid JSON\"}", "JSONException while parsing input");
                } catch (Exception unused2) {
                    fi.this.b(str, str2, "{\"error\":\"General_Error\",\"errorMessage\":\"An internal MAP error exception occurred\"}", "Exception thrown while executing function");
                }
            }
        });
    }
}
