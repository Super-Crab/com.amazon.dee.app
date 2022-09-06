package com.amazon.identity.auth.device;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazon.dee.app.BuildConfig;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.identity.auth.device.activity.GetAuthenticatorResultsActivity;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.callback.RemoteCallbackWrapper;
import com.google.android.gms.fido.Fido;
import com.google.android.gms.fido.common.Transport;
import com.google.android.gms.fido.fido2.Fido2ApiClient;
import com.google.android.gms.fido.fido2.api.common.Attachment;
import com.google.android.gms.fido.fido2.api.common.AuthenticatorSelectionCriteria;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialCreationOptions;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialParameters;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRequestOptions;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRpEntity;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialUserEntity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import java.util.ArrayList;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class fh extends fi {
    Callback g;
    final Context mContext;
    final Fido2ApiClient mr;
    final gg w;

    public fh(WebView webView) {
        super(webView, "AuthenticatorJavaScriptBridge");
        this.mContext = webView.getContext().getApplicationContext();
        this.mr = Fido.getFido2ApiClient(this.mContext);
        this.w = ((gh) ed.M(this.mContext).getSystemService("dcp_data_storage_factory")).dV();
    }

    PublicKeyCredentialRequestOptions a(String str, fk fkVar) {
        PublicKeyCredentialRequestOptions.Builder builder = new PublicKeyCredentialRequestOptions.Builder();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("rpId")) {
                mq.incrementCounterAndRecord("GetAssertionWithAuthenticatorCredential:UsingDefaultRP_ID", new String[0]);
            }
            builder.setRpId(jSONObject.optString("rpId", BuildConfig.RETAIL_HOST));
            builder.setChallenge(Base64.decode(jSONObject.getString("challenge"), 3));
            JSONObject jSONObject2 = jSONObject.getJSONArray("allowCredentials").getJSONObject(0);
            String string = jSONObject2.getString("type");
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(Transport.INTERNAL);
            arrayList.add(new PublicKeyCredentialDescriptor(string, Base64.decode(jSONObject2.getString("id"), 3), arrayList2));
            builder.setAllowList(arrayList);
            return builder.build();
        } catch (JSONException unused) {
            fkVar.s("{\"error\":\"Invalid_Input_Param\",\"errorMessage\":\"The API input is invalid JSON\"}", "JSONException while parsing get assertion options");
            return null;
        }
    }

    PublicKeyCredentialCreationOptions b(String str, fk fkVar) {
        PublicKeyCredentialCreationOptions.Builder builder = new PublicKeyCredentialCreationOptions.Builder();
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = jSONObject.getJSONObject("rp");
            builder.setRp(new PublicKeyCredentialRpEntity(jSONObject2.getString("id"), jSONObject2.getString("name"), (String) null));
            byte[] bArr = new byte[32];
            new Random().nextBytes(bArr);
            builder.setUser(new PublicKeyCredentialUserEntity(bArr, "Amazon Customer", (String) null, "Amazon Customer"));
            builder.setChallenge(Base64.decode(jSONObject.getString("challenge"), 3));
            JSONObject jSONObject3 = jSONObject.getJSONArray("pubKeyCredParams").getJSONObject(0);
            String string = jSONObject3.getString("type");
            int i = jSONObject3.getJSONObject("alg").getInt("code");
            ArrayList arrayList = new ArrayList();
            arrayList.add(new PublicKeyCredentialParameters(string, i));
            builder.setParameters(arrayList);
            AuthenticatorSelectionCriteria.Builder builder2 = new AuthenticatorSelectionCriteria.Builder();
            builder2.setAttachment(Attachment.PLATFORM);
            builder.setAuthenticatorSelection(builder2.build());
        } catch (JSONException unused) {
            fkVar.s("{\"error\":\"Invalid_Input_Param\",\"errorMessage\":\"The API input is invalid JSON\"}", "JSONException occurred while parsing input for credential creation options");
            return null;
        } catch (Exception unused2) {
            fkVar.s("{\"error\":\"General_Error\",\"errorMessage\":\"An internal MAP error exception occurred\"}", "Exception occurred while creating credential creation options");
        }
        return builder.build();
    }

    @JavascriptInterface
    public void createAuthenticatorCredential(final String str) {
        a("createAuthenticatorCredential", str, new fg() { // from class: com.amazon.identity.auth.device.fh.2
            @Override // com.amazon.identity.auth.device.fg
            public void a(final JSONObject jSONObject, final fk fkVar) {
                try {
                    io.i("AuthenticatorJavaScriptBridge", String.format("AuthenticatorJavaScriptBridge createAuthenticatorCredential method is called", new Object[0]));
                    PublicKeyCredentialCreationOptions b = fh.this.b(str, fkVar);
                    if (b == null) {
                        return;
                    }
                    Task registerPendingIntent = fh.this.mr.getRegisterPendingIntent(b);
                    Callback callback = new Callback() { // from class: com.amazon.identity.auth.device.fh.2.1
                        @Override // com.amazon.identity.auth.device.api.Callback
                        public void onError(Bundle bundle) {
                            try {
                                fkVar.s(bundle.getString("error"), bundle.getString("errorMessage"));
                            } catch (Exception unused) {
                                fkVar.s("{\"error\":\"General_Error\",\"errorMessage\":\"An internal MAP error exception occurred\"}", "Exception occurred while calling Fido API");
                            }
                        }

                        @Override // com.amazon.identity.auth.device.api.Callback
                        public void onSuccess(Bundle bundle) {
                            String string = bundle.getString("attestationObject");
                            String string2 = bundle.getString("clientDataJson");
                            String string3 = bundle.getString("credentialId");
                            JSONObject jSONObject2 = new JSONObject();
                            try {
                                jSONObject2.put("attestationObject", string);
                                jSONObject2.put("clientDataJson", string2);
                                jSONObject2.put("credentialId", string3);
                                jSONObject2.put("challenge", jSONObject.getString("challenge"));
                                fkVar.bO(jSONObject2.toString());
                            } catch (JSONException unused) {
                                fkVar.s("{\"error\":\"General_Error\",\"errorMessage\":\"An internal MAP error exception occurred\"}", "JSONException occurred while parsing Fido API response");
                            } catch (Exception unused2) {
                                fkVar.s("{\"error\":\"General_Error\",\"errorMessage\":\"An internal MAP error exception occurred\"}", "Exception occurred while calling Fido API");
                            }
                        }
                    };
                    fh.this.g = callback;
                    final RemoteCallbackWrapper remoteCallbackWrapper = new RemoteCallbackWrapper(callback);
                    registerPendingIntent.addOnSuccessListener(new OnSuccessListener() { // from class: com.amazon.identity.auth.device.fh.2.2
                        @Override // com.google.android.gms.tasks.OnSuccessListener
                        public void onSuccess(Object obj) {
                            if (obj != null && (obj instanceof PendingIntent)) {
                                Intent intent = new Intent(fh.this.mContext, GetAuthenticatorResultsActivity.class);
                                intent.putExtra("requestTypeKey", 0);
                                intent.putExtra("pendingIntentKey", (PendingIntent) obj);
                                intent.putExtra("callbackKey", remoteCallbackWrapper);
                                intent.setFlags(268435456);
                                fh.this.mContext.startActivity(intent);
                                return;
                            }
                            fkVar.s("{\"error\":\"General_Error\",\"errorMessage\":\"An internal MAP error exception occurred\"}", "Exception occurred while calling Fido API");
                        }
                    });
                    registerPendingIntent.addOnFailureListener(new OnFailureListener() { // from class: com.amazon.identity.auth.device.fh.2.3
                        @Override // com.google.android.gms.tasks.OnFailureListener
                        public void onFailure(Exception exc) {
                            fkVar.s("{\"error\":\"General_Error\",\"errorMessage\":\"An internal MAP error exception occurred\"}", "Exception occurred while calling Fido API");
                        }
                    });
                } catch (Exception unused) {
                    fkVar.s("{\"error\":\"General_Error\",\"errorMessage\":\"An internal MAP error exception occurred\"}", "Exception occurred while calling Fido API");
                }
            }
        });
    }

    @JavascriptInterface
    public void getAssertionWithAuthenticatorCredential(final String str) {
        a("getAssertionWithAuthenticatorCredential", str, new fg() { // from class: com.amazon.identity.auth.device.fh.1
            @Override // com.amazon.identity.auth.device.fg
            public void a(JSONObject jSONObject, final fk fkVar) {
                try {
                    io.i("AuthenticatorJavaScriptBridge", String.format("AuthenticatorJavaScriptBridge getAssertionWithAuthenticatorCredential is called", new Object[0]));
                    PublicKeyCredentialRequestOptions a = fh.this.a(str, fkVar);
                    if (a == null) {
                        return;
                    }
                    Task signPendingIntent = fh.this.mr.getSignPendingIntent(a);
                    Callback callback = new Callback() { // from class: com.amazon.identity.auth.device.fh.1.1
                        @Override // com.amazon.identity.auth.device.api.Callback
                        public void onError(Bundle bundle) {
                            try {
                                fkVar.s(bundle.getString("error"), bundle.getString("errorMessage"));
                            } catch (Exception unused) {
                                fkVar.s("{\"error\":\"General_Error\",\"errorMessage\":\"An internal MAP error exception occurred\"}", "Exception occurred while calling Fido API");
                            }
                        }

                        @Override // com.amazon.identity.auth.device.api.Callback
                        public void onSuccess(Bundle bundle) {
                            JSONObject jSONObject2 = new JSONObject();
                            try {
                                String str2 = null;
                                jSONObject2.put("authenticatorData", bundle.containsKey("authenticatorData") ? bundle.getString("authenticatorData") : null);
                                jSONObject2.put("clientDataJson", bundle.containsKey("clientDataJson") ? bundle.getString("clientDataJson") : null);
                                jSONObject2.put("credentialId", bundle.containsKey("credentialId") ? bundle.getString("credentialId") : null);
                                jSONObject2.put("signature", bundle.containsKey("signature") ? bundle.getString("signature") : null);
                                if (bundle.containsKey("userHandle")) {
                                    str2 = bundle.getString("userHandle");
                                }
                                jSONObject2.put("userHandle", str2);
                                fkVar.bO(jSONObject2.toString());
                            } catch (JSONException unused) {
                                fkVar.s("{\"error\":\"General_Error\",\"errorMessage\":\"An internal MAP error exception occurred\"}", "JSONException occurred while parsing Fido API response");
                            } catch (Exception unused2) {
                                fkVar.s("{\"error\":\"General_Error\",\"errorMessage\":\"An internal MAP error exception occurred\"}", "Exception occurred while calling Fido API");
                            }
                        }
                    };
                    fh.this.g = callback;
                    final RemoteCallbackWrapper remoteCallbackWrapper = new RemoteCallbackWrapper(callback);
                    signPendingIntent.addOnSuccessListener(new OnSuccessListener() { // from class: com.amazon.identity.auth.device.fh.1.2
                        @Override // com.google.android.gms.tasks.OnSuccessListener
                        public void onSuccess(Object obj) {
                            if (obj != null && (obj instanceof PendingIntent)) {
                                Intent intent = new Intent(fh.this.mContext, GetAuthenticatorResultsActivity.class);
                                intent.putExtra("requestTypeKey", 1);
                                intent.putExtra("pendingIntentKey", (PendingIntent) obj);
                                intent.putExtra("callbackKey", remoteCallbackWrapper);
                                intent.setFlags(268435456);
                                fh.this.mContext.startActivity(intent);
                                return;
                            }
                            fkVar.s("{\"error\":\"General_Error\",\"errorMessage\":\"An internal MAP error exception occurred\"}", "Exception occurred while calling Fido API");
                        }
                    });
                    signPendingIntent.addOnFailureListener(new OnFailureListener() { // from class: com.amazon.identity.auth.device.fh.1.3
                        @Override // com.google.android.gms.tasks.OnFailureListener
                        public void onFailure(Exception exc) {
                            fkVar.s("{\"error\":\"General_Error\",\"errorMessage\":\"An internal MAP error exception occurred\"}", "Exception occurred while calling Fido API");
                        }
                    });
                } catch (Exception unused) {
                    fkVar.s("{\"error\":\"General_Error\",\"errorMessage\":\"An internal MAP error exception occurred\"}", "Exception occurred while calling Fido API");
                }
            }
        });
    }

    @JavascriptInterface
    public void getCurrentAppInfo(String str) {
        a("getCurrentAppInfo", str, new fg() { // from class: com.amazon.identity.auth.device.fh.3
            @Override // com.amazon.identity.auth.device.fg
            public void a(JSONObject jSONObject, fk fkVar) {
                try {
                    io.i("AuthenticatorJavaScriptBridge", String.format("AuthenticatorJavaScriptBridge getCurrentAppInfo method is called", new Object[0]));
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("appIdentifier", fh.this.mContext.getPackageName());
                    jSONObject2.put("mapVersion", ip.gF());
                    jSONObject2.put(MetricsConfiguration.PLATFORM, "Android");
                    fkVar.bO(jSONObject2.toString());
                } catch (JSONException unused) {
                    fkVar.s("{\"error\":\"Invalid_Input_Param\",\"errorMessage\":\"The API input is invalid JSON\"}", "JSONException while parsing input");
                } catch (Exception unused2) {
                    fkVar.s("{\"error\":\"General_Error\",\"errorMessage\":\"An internal MAP error exception occurred\"}", "Exception occurred while calling API");
                }
            }
        });
    }

    @JavascriptInterface
    public void isAuthenticatorCredentialAvailable(String str) {
        a("isAuthenticatorCredentialAvailable", str, new fg() { // from class: com.amazon.identity.auth.device.fh.4
            @Override // com.amazon.identity.auth.device.fg
            public void a(JSONObject jSONObject, fk fkVar) {
                try {
                    String string = jSONObject.getString("credentialId");
                    io.i("AuthenticatorJavaScriptBridge", String.format("AuthenticatorJavaScriptBridge isAuthenticatorCredentialAvailable method called", new Object[0]));
                    fkVar.bO(Boolean.valueOf("true".equals(fh.this.w.C("fido_authenticator_credential_namespace", string))).toString());
                } catch (JSONException unused) {
                    io.i("AuthenticatorJavaScriptBridge", "Call to isAuthenticatorCredentialAvailable failed because JSONException occurred while parsing input");
                    mq.incrementCounterAndRecord("isAuthenticatorCredentialAvailable:JSONException", new String[0]);
                    fkVar.bO(PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE);
                } catch (Exception unused2) {
                    io.i("AuthenticatorJavaScriptBridge", "Call to isAuthenticatorCredentialAvailable failed because exception was thrown during function call");
                    mq.incrementCounterAndRecord("isAuthenticatorCredentialAvailable:ExceptionOccurred", new String[0]);
                    fkVar.bO(PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE);
                }
            }
        });
    }

    @JavascriptInterface
    public void isUserVerifyingPlatformAuthenticatorAvailable(String str) {
        a("isUserVerifyingPlatformAuthenticatorAvailable", str, new fg() { // from class: com.amazon.identity.auth.device.fh.5
            @Override // com.amazon.identity.auth.device.fg
            public void a(JSONObject jSONObject, final fk fkVar) {
                try {
                    io.i("AuthenticatorJavaScriptBridge", String.format("AuthenticatorJavaScriptBridge isUserVerifyingPlatformAuthenticatorAvailable method is called", new Object[0]));
                    Task isUserVerifyingPlatformAuthenticatorAvailable = fh.this.mr.isUserVerifyingPlatformAuthenticatorAvailable();
                    isUserVerifyingPlatformAuthenticatorAvailable.addOnSuccessListener(new OnSuccessListener<Boolean>() { // from class: com.amazon.identity.auth.device.fh.5.1
                        @Override // com.google.android.gms.tasks.OnSuccessListener
                        /* renamed from: b */
                        public void onSuccess(Boolean bool) {
                            if (bool != null) {
                                io.i("AuthenticatorJavaScriptBridge", "Call to isUserVerifyingPlatformAuthenticatorAvailable succeeded with result = " + bool.toString());
                                mq.incrementCounterAndRecord("isUserVerifyingPlatformAuthenticatorAvailable:Success", new String[0]);
                                fkVar.bO(bool.toString());
                                return;
                            }
                            io.i("AuthenticatorJavaScriptBridge", "Call to isUserVerifyingPlatformAuthenticatorAvailable failed because the Fido method result was null");
                            mq.incrementCounterAndRecord("isUserVerifyingPlatformAuthenticatorAvailable:NullResult", new String[0]);
                            fkVar.bO(PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE);
                        }
                    });
                    isUserVerifyingPlatformAuthenticatorAvailable.addOnFailureListener(new OnFailureListener() { // from class: com.amazon.identity.auth.device.fh.5.2
                        @Override // com.google.android.gms.tasks.OnFailureListener
                        public void onFailure(Exception exc) {
                            io.i("AuthenticatorJavaScriptBridge", "Call to isUserVerifyingPlatformAuthenticatorAvailable failed because OnFailure was invoked with exception = ".concat(String.valueOf(exc)));
                            mq.incrementCounterAndRecord("isUserVerifyingPlatformAuthenticatorAvailable:OnFailureInvoked", new String[0]);
                            fkVar.bO(PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE);
                        }
                    });
                } catch (Exception unused) {
                    io.i("AuthenticatorJavaScriptBridge", "Call to isUserVerifyingPlatformAuthenticatorAvailable failed because exception was thrown during function call");
                    mq.incrementCounterAndRecord("isUserVerifyingPlatformAuthenticatorAvailable:ExceptionOccurred", new String[0]);
                    fkVar.bO(PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE);
                }
            }
        });
    }
}
