package com.amazon.dee.app.ui.web;

import android.os.Bundle;
import android.util.ArrayMap;
import com.amazon.alexa.identity.AccountException;
import com.amazon.alexa.identity.api.AccountService;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.dee.app.ui.web.AccountManagementBridge;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsService;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
/* loaded from: classes12.dex */
public class AccountManagementBridge extends JavaScriptBridge {
    static final String KEY_LINK_CODE = "linkCode";
    static final String KEY_PREAUTHORIZED_LINK_CODE = "preauthorizedLinkCode";
    static final String KEY_PREAUTHORIZED_LINK_CODE_EXPIRY = "preauthorizedLinkCodeExpiry";
    static final String MESSAGE_NO_LINK_CODE_PROVIDED = "Received bridge call to submit link code, but there was no code supplied!";
    private static final String TAG = Log.tag(AccountManagementBridge.class);
    AccountService accountService;
    IdentityService identityService;
    JavaScriptDelegate javaScriptDelegate;
    MAPAccountManager mapAccountManager;
    ArrayMap<String, JavaScriptBridgeMethod> methods;
    MetricsService metricsService;
    RoutingService routingService;
    WebViewDelegate webViewDelegate;

    /* loaded from: classes12.dex */
    class AuthorizeLinkCodeMethod implements JavaScriptBridgeMethod {
        AuthorizeLinkCodeMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, final JavaScriptResponse javaScriptResponse) throws JSONException {
            try {
                Callback callback = new Callback() { // from class: com.amazon.dee.app.ui.web.AccountManagementBridge.AuthorizeLinkCodeMethod.1
                    @Override // com.amazon.identity.auth.device.api.Callback
                    public void onError(Bundle bundle) {
                        String str = Integer.valueOf(bundle.getInt("com.amazon.dcp.sso.ErrorCode")) + RealTimeTextConstants.COLON_SPACE + bundle.getString("com.amazon.dcp.sso.ErrorMessage");
                        Log.e(AccountManagementBridge.TAG, "AuthorizeLinkCodeMethod: Failed to authorize link code using MAP. Error:");
                        Log.e(AccountManagementBridge.TAG, str);
                        javaScriptResponse.setError(true);
                        javaScriptResponse.setErrorReason(str);
                        AccountManagementBridge.this.completeRequest(javaScriptResponse);
                    }

                    @Override // com.amazon.identity.auth.device.api.Callback
                    public void onSuccess(Bundle bundle) {
                        AccountManagementBridge.this.completeRequest(javaScriptResponse);
                    }
                };
                if (!jSONObject.has(AccountManagementBridge.KEY_LINK_CODE)) {
                    Log.e(AccountManagementBridge.TAG, AccountManagementBridge.MESSAGE_NO_LINK_CODE_PROVIDED);
                    javaScriptResponse.setError(true);
                    AccountManagementBridge.this.completeRequest(javaScriptResponse);
                } else {
                    String string = jSONObject.getString(AccountManagementBridge.KEY_LINK_CODE);
                    Bundle bundle = new Bundle();
                    bundle.putString("com.amazon.dcp.sso.property.account.acctId", AccountManagementBridge.this.mapAccountManager.getAccount());
                    bundle.putString(MAPAccountManager.KEY_LINK_CODE, string);
                    AccountManagementBridge.this.mapAccountManager.authorizeLinkCode(bundle, callback);
                }
            } catch (Exception e) {
                Log.e(AccountManagementBridge.TAG, e, "Exception when trying to submit link code", new Object[0]);
                javaScriptResponse.setError(true);
                AccountManagementBridge.this.completeRequest(javaScriptResponse);
            }
        }
    }

    /* loaded from: classes12.dex */
    class GeneratePreauthorizedLinkCodeMethod implements JavaScriptBridgeMethod {
        GeneratePreauthorizedLinkCodeMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, final JavaScriptResponse javaScriptResponse) throws JSONException {
            final JSONObject jSONObject2 = new JSONObject();
            Log.i(AccountManagementBridge.TAG, String.format("GeneratePreauthorizedLinkCodeMethod called with requestParams:\n%s\n", jSONObject));
            try {
                Callback callback = new Callback() { // from class: com.amazon.dee.app.ui.web.AccountManagementBridge.GeneratePreauthorizedLinkCodeMethod.1
                    @Override // com.amazon.identity.auth.device.api.Callback
                    public void onError(Bundle bundle) {
                        String str = (String) bundle.get("com.amazon.dcp.sso.ErrorMessage");
                        String str2 = AccountManagementBridge.TAG;
                        Log.e(str2, "GeneratePreauthorizedLinkCodeMethod: " + ((Integer) bundle.get("com.amazon.dcp.sso.ErrorCode")) + RealTimeTextConstants.COLON_SPACE + str);
                        javaScriptResponse.setError(true);
                        javaScriptResponse.setErrorReason(str);
                        AccountManagementBridge.this.completeRequest(javaScriptResponse);
                    }

                    @Override // com.amazon.identity.auth.device.api.Callback
                    public void onSuccess(Bundle bundle) {
                        try {
                            jSONObject2.put(AccountManagementBridge.KEY_PREAUTHORIZED_LINK_CODE, bundle.get(MAPAccountManager.KEY_PRE_AUTHORIZED_LINK_CODE));
                            jSONObject2.put(AccountManagementBridge.KEY_PREAUTHORIZED_LINK_CODE_EXPIRY, bundle.get(MAPAccountManager.KEY_LINK_CODE_TIME_TO_LIVE));
                            javaScriptResponse.setResponse(jSONObject2);
                            AccountManagementBridge.this.completeRequest(javaScriptResponse);
                        } catch (JSONException e) {
                            Log.e(AccountManagementBridge.TAG, e, "JSONException when trying to build response", new Object[0]);
                            javaScriptResponse.setError(true);
                            javaScriptResponse.setErrorReason(e.getMessage());
                            AccountManagementBridge.this.completeRequest(javaScriptResponse);
                        }
                    }
                };
                Bundle bundle = new Bundle();
                bundle.putString("com.amazon.dcp.sso.property.account.acctId", AccountManagementBridge.this.mapAccountManager.getAccount());
                AccountManagementBridge.this.mapAccountManager.generatePreAuthorizedLinkCode(bundle, callback);
            } catch (Exception e) {
                Log.e(AccountManagementBridge.TAG, e, "Exception when trying to get preauthorized code", new Object[0]);
                javaScriptResponse.setError(true);
                AccountManagementBridge.this.completeRequest(javaScriptResponse);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class RefreshCookiesMethod implements JavaScriptBridgeMethod {
        RefreshCookiesMethod() {
        }

        static /* synthetic */ void lambda$null$2(Void r0) {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, final JavaScriptResponse javaScriptResponse) {
            if (AccountManagementBridge.this.identityService.isAuthenticated(AccountManagementBridge.TAG)) {
                String unused = AccountManagementBridge.TAG;
                String str = "RefreshCookiesMethod called over JS Bridge: " + jSONObject;
                AccountManagementBridge.this.identityService.refresh(AccountManagementBridge.TAG).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$AccountManagementBridge$RefreshCookiesMethod$aHpocYyCpUUgsijjBFe1OOfHtIA
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        AccountManagementBridge.RefreshCookiesMethod.this.lambda$execute$0$AccountManagementBridge$RefreshCookiesMethod(javaScriptResponse, (UserIdentity) obj);
                    }
                }, new Action1() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$AccountManagementBridge$RefreshCookiesMethod$LvTjKAH1NtmWnwpq1o7hNe6zdvI
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        AccountManagementBridge.RefreshCookiesMethod.this.lambda$execute$4$AccountManagementBridge$RefreshCookiesMethod(javaScriptResponse, (Throwable) obj);
                    }
                });
                return;
            }
            javaScriptResponse.setErrorReason("Failed to refresh cookies. User is not authenticated");
            javaScriptResponse.setError(true);
            AccountManagementBridge.this.completeRequest(javaScriptResponse);
        }

        public /* synthetic */ void lambda$execute$0$AccountManagementBridge$RefreshCookiesMethod(JavaScriptResponse javaScriptResponse, UserIdentity userIdentity) {
            AccountManagementBridge.this.completeRequest(javaScriptResponse);
        }

        public /* synthetic */ void lambda$execute$4$AccountManagementBridge$RefreshCookiesMethod(JavaScriptResponse javaScriptResponse, Throwable th) {
            if (!(th instanceof AccountException)) {
                Log.e(AccountManagementBridge.TAG, th, "Failed to refresh cookies.", new Object[0]);
            } else if (((AccountException) th).error == 1) {
                Log.e(AccountManagementBridge.TAG, th, "Failed to refresh cookies due to auth error, signing out.", new Object[0]);
                AccountManagementBridge.this.accountService.signOut().observeOn(AndroidSchedulers.mainThread()).doOnTerminate(new Action0() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$AccountManagementBridge$RefreshCookiesMethod$ge2pyOqowYbgar4B07YvI6KrwEM
                    @Override // rx.functions.Action0
                    public final void call() {
                        AccountManagementBridge.RefreshCookiesMethod.this.lambda$null$1$AccountManagementBridge$RefreshCookiesMethod();
                    }
                }).subscribe($$Lambda$AccountManagementBridge$RefreshCookiesMethod$UAgh67Ys74nnPUu4qpysrMg7pew.INSTANCE, $$Lambda$AccountManagementBridge$RefreshCookiesMethod$yYg2pegAWujjxnG12reBsOLjaFU.INSTANCE);
            } else {
                Log.e(AccountManagementBridge.TAG, th, "Failed to refresh cookies.", new Object[0]);
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to refresh cookies. ");
            outline107.append(th.getLocalizedMessage());
            javaScriptResponse.setErrorReason(outline107.toString());
            javaScriptResponse.setError(true);
            AccountManagementBridge.this.completeRequest(javaScriptResponse);
        }

        public /* synthetic */ void lambda$null$1$AccountManagementBridge$RefreshCookiesMethod() {
            AccountManagementBridge.this.routingService.route(RouteName.HOME).clearBackStack().navigate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class RefreshUserIdentityMethod implements JavaScriptBridgeMethod {
        private RefreshUserIdentityMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, final JavaScriptResponse javaScriptResponse) throws JSONException {
            try {
                AccountManagementBridge.this.identityService.refresh(AccountManagementBridge.TAG).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$AccountManagementBridge$RefreshUserIdentityMethod$poIr9ZGoAHOk9o_Y2IzNLJe6sV4
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        AccountManagementBridge.RefreshUserIdentityMethod.this.lambda$execute$0$AccountManagementBridge$RefreshUserIdentityMethod(javaScriptResponse, (UserIdentity) obj);
                    }
                }, new Action1() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$AccountManagementBridge$RefreshUserIdentityMethod$YOK6Z2ptVoG6keXCnruhjoypCss
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        AccountManagementBridge.RefreshUserIdentityMethod.this.lambda$execute$1$AccountManagementBridge$RefreshUserIdentityMethod(javaScriptResponse, (Throwable) obj);
                    }
                });
            } catch (Exception e) {
                Log.e(AccountManagementBridge.TAG, e, "Exception trying to refresh user ID", new Object[0]);
                javaScriptResponse.setError(true);
                javaScriptResponse.setErrorReason("Error refreshing user ID: " + e.getLocalizedMessage());
                AccountManagementBridge.this.completeRequest(javaScriptResponse);
            }
        }

        public /* synthetic */ void lambda$execute$0$AccountManagementBridge$RefreshUserIdentityMethod(JavaScriptResponse javaScriptResponse, UserIdentity userIdentity) {
            Log.i(AccountManagementBridge.TAG, "User ID successfully refreshed. New PFM is {}", userIdentity.getMarketplace());
            AccountManagementBridge.this.completeRequest(javaScriptResponse);
        }

        public /* synthetic */ void lambda$execute$1$AccountManagementBridge$RefreshUserIdentityMethod(JavaScriptResponse javaScriptResponse, Throwable th) {
            Log.e(AccountManagementBridge.TAG, th, "Error trying to refresh user ID", new Object[0]);
            javaScriptResponse.setError(true);
            javaScriptResponse.setErrorReason("Error refreshing user ID: " + th.getLocalizedMessage());
            AccountManagementBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class SignInMethod implements JavaScriptBridgeMethod {
        SignInMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, final JavaScriptResponse javaScriptResponse) {
            String str = AccountManagementBridge.TAG;
            Log.e(str, "SignInMethod called over JSBridge: " + jSONObject);
            AccountManagementBridge.this.webViewDelegate.runOnUiThread(new Runnable() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$AccountManagementBridge$SignInMethod$wRNvw0SVi5kTzLUk4_eu3pWkxgs
                @Override // java.lang.Runnable
                public final void run() {
                    AccountManagementBridge.SignInMethod.this.lambda$execute$2$AccountManagementBridge$SignInMethod(javaScriptResponse);
                }
            });
        }

        public /* synthetic */ void lambda$execute$2$AccountManagementBridge$SignInMethod(final JavaScriptResponse javaScriptResponse) {
            AccountManagementBridge.this.accountService.signIn().subscribe(new Action1() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$AccountManagementBridge$SignInMethod$XHeuLrnDIJ4a_OcwPJDMU-ED0jY
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    AccountManagementBridge.SignInMethod.this.lambda$null$0$AccountManagementBridge$SignInMethod(javaScriptResponse, (UserIdentity) obj);
                }
            }, new Action1() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$AccountManagementBridge$SignInMethod$WWvHN4GcFfB9ns97427LRlj1JX0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    AccountManagementBridge.SignInMethod.this.lambda$null$1$AccountManagementBridge$SignInMethod(javaScriptResponse, (Throwable) obj);
                }
            });
        }

        public /* synthetic */ void lambda$null$0$AccountManagementBridge$SignInMethod(JavaScriptResponse javaScriptResponse, UserIdentity userIdentity) {
            AccountManagementBridge.this.completeRequest(javaScriptResponse);
            AccountManagementBridge.this.routingService.route(RouteName.HOME).clearBackStack().navigate();
        }

        public /* synthetic */ void lambda$null$1$AccountManagementBridge$SignInMethod(JavaScriptResponse javaScriptResponse, Throwable th) {
            AccountManagementBridge.this.errorResponse(javaScriptResponse, th.getMessage(), th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class SignOutMethod implements JavaScriptBridgeMethod {
        SignOutMethod() {
        }

        static /* synthetic */ void lambda$execute$1(Void r0) {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, final JavaScriptResponse javaScriptResponse) throws JSONException {
            if (AccountManagementBridge.this.identityService.isAuthenticated(AccountManagementBridge.TAG)) {
                String unused = AccountManagementBridge.TAG;
                String str = "SignOutMethod called from JS Bridge: " + jSONObject;
                AccountManagementBridge.this.metricsService.recordEvent(AlexaMetricsConstants.MetricEvents.SIGN_OUT_ATTEMPT, AccountManagementBridge.TAG, null);
                AccountManagementBridge.this.accountService.signOut().observeOn(AndroidSchedulers.mainThread()).doOnTerminate(new Action0() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$AccountManagementBridge$SignOutMethod$cgapgJZWik2dT485B3HwxeG_QiM
                    @Override // rx.functions.Action0
                    public final void call() {
                        AccountManagementBridge.SignOutMethod.this.lambda$execute$0$AccountManagementBridge$SignOutMethod(javaScriptResponse);
                    }
                }).subscribe($$Lambda$AccountManagementBridge$SignOutMethod$qqQ1iDhypg3msUFl55HDvhS3xXg.INSTANCE, $$Lambda$AccountManagementBridge$SignOutMethod$rXXhbVCvxuiqofxNxPiqs9llnV4.INSTANCE);
                return;
            }
            AccountManagementBridge.this.completeRequest(javaScriptResponse);
        }

        public /* synthetic */ void lambda$execute$0$AccountManagementBridge$SignOutMethod(JavaScriptResponse javaScriptResponse) {
            AccountManagementBridge.this.routingService.route(RouteName.HOME).clearBackStack().navigate();
            AccountManagementBridge.this.completeRequest(javaScriptResponse);
        }
    }

    public AccountManagementBridge(MAPAccountManager mAPAccountManager, AccountService accountService, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, RoutingService routingService, IdentityService identityService, JavaScriptDelegate javaScriptDelegate, WebViewDelegate webViewDelegate, MetricsService metricsService) {
        super(javaScriptInjector, javaScriptResponseQueue);
        this.methods = new ArrayMap<>();
        this.mapAccountManager = mAPAccountManager;
        this.accountService = accountService;
        this.routingService = routingService;
        this.identityService = identityService;
        this.javaScriptDelegate = javaScriptDelegate;
        this.webViewDelegate = webViewDelegate;
        this.metricsService = metricsService;
        this.methods.put("signIn", new SignInMethod());
        this.methods.put("signOut", new SignOutMethod());
        this.methods.put("refreshCookie", new RefreshCookiesMethod());
        this.methods.put("generatePreauthorizedLinkCode", new GeneratePreauthorizedLinkCodeMethod());
        this.methods.put("authorizeLinkCode", new AuthorizeLinkCodeMethod());
        this.methods.put("refreshUserIdentity", new RefreshUserIdentityMethod());
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public Map<String, JavaScriptBridgeMethod> getExposedMethods() {
        return this.methods;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public final String getJavaScriptInterfaceName() {
        return "AccountManager";
    }
}
