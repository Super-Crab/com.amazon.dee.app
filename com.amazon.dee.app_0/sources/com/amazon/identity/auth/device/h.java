package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.alexa.accessory.notificationpublisher.utils.NotificationConstants;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import com.amazon.identity.auth.accounts.RegisterChildApplicationAction;
import com.amazon.identity.auth.attributes.CORPFMResponse;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.CookieKeys;
import com.amazon.identity.auth.device.api.CustomerAttributeKeys;
import com.amazon.identity.auth.device.api.DeviceDataKeys;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.MAPFuture;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.identity.auth.device.api.RegistrationType;
import com.amazon.identity.auth.device.api.SigninOption;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.identity.auth.device.c;
import com.amazon.identity.auth.device.callback.RemoteCallbackWrapper;
import com.amazon.identity.auth.device.endpoint.OpenIdRequest;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.framework.IsolatedModeSwitcher;
import com.amazon.identity.auth.device.gg;
import com.amazon.identity.auth.device.i;
import com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage;
import com.amazon.identity.auth.device.token.MAPCookie;
import com.amazon.identity.auth.device.token.OAuthTokenManager;
import com.amazon.identity.auth.device.userdictionary.UserDictionaryHelper;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.device.x;
import com.amazon.identity.kcpsdk.auth.RegisterDeviceRequest;
import com.amazon.identity.platform.setting.PlatformSettings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class h implements f {
    private static h n;
    private final ar A;
    private final OAuthTokenManager B;
    private final gd D;
    private final ae E;
    private final eh F;
    private final ed o;
    private final al p;
    private final x q;
    private final RegisterChildApplicationAction r;
    private final AmazonAccountManager s;
    private final j t;
    private final i u;
    private final n v;
    private final gg w;
    private final ab x;
    private final z y;
    private final w z;
    public static final c m = new c();
    private static final String TAG = h.class.getName();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* renamed from: com.amazon.identity.auth.device.h$7  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] V = new int[SigninOption.values().length];

        static {
            try {
                V[SigninOption.WebviewSignin.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                V[SigninOption.WebviewCreateAccount.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                V[SigninOption.MyAccountSignin.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                V[SigninOption.WebviewForgotPassword.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                V[SigninOption.WebviewConfirmCredentials.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a {
        String an;
        boolean ao;
        Account ap;
        Set<Integer> aq;

        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }
    }

    public h(ed edVar) {
        this(edVar, new x(edVar), new RegisterChildApplicationAction(edVar), new al(edVar), new AmazonAccountManager(edVar), new j(edVar), new i(edVar), edVar.dV(), ab.g(edVar), aa.f(edVar), ar.h(edVar), new w(), new OAuthTokenManager(edVar), new ge(edVar).fa(), new ae(edVar), new n(edVar), new eh());
    }

    public static void generateNewInstance(Context context) {
        n = new h(ed.M(context.getApplicationContext()));
    }

    private Intent s(String str) {
        Intent intent = new Intent(str);
        List<ResolveInfo> f = new ek(this.o).f(intent);
        ActivityInfo activityInfo = !hz.isEmpty(f) ? f.get(0).activityInfo : null;
        if (activityInfo == null) {
            return null;
        }
        intent.setComponent(new ComponentName(activityInfo.packageName, activityInfo.name));
        return intent;
    }

    @Override // com.amazon.identity.auth.device.f
    public Set<String> getAccounts() {
        return this.s.p();
    }

    @Override // com.amazon.identity.auth.device.f
    public String getPrimaryAccount() {
        return this.x.getAccountForMapping(MultipleAccountManager.PrimaryUserMappingType.createPrimaryMappingForProfile(da.cA()));
    }

    @Override // com.amazon.identity.auth.device.f
    public boolean isAccountRegistered(String str) {
        return getAccounts().contains(str);
    }

    @Override // com.amazon.identity.auth.device.f
    public boolean isDeviceRegistered() {
        return this.s.n();
    }

    @Override // com.amazon.identity.auth.device.f
    public String r(String str) {
        return this.x.getAccountForMapping(this.y.a(str, da.cA()));
    }

    private Map<String, Map<String, String>> d(Bundle bundle) {
        Map<String, Map<String, String>> dN = kg.dN(bundle.getString(AccountConstants.KEY_DEVICE_CREDENTIALS));
        bundle.remove(AccountConstants.KEY_DEVICE_CREDENTIALS);
        return dN == null ? Collections.emptyMap() : dN;
    }

    private void e() {
        Set<String> accounts = this.s.getAccounts();
        if (accounts != null) {
            for (String str : accounts) {
                this.s.G(str);
            }
            this.x.P();
        }
    }

    public void c(final Callback callback) {
        this.u.a((String) null, true, this.t, new i.a() { // from class: com.amazon.identity.auth.device.h.1
            @Override // com.amazon.identity.auth.device.i.a
            public void a(MAPError mAPError, String str, MAPAccountManager.RegistrationError registrationError, String str2, Bundle bundle) {
                String str3 = h.TAG;
                io.i(str3, "Failed to transfer account. Error : " + registrationError.getName());
                h.a(callback, registrationError.getName());
            }

            @Override // com.amazon.identity.auth.device.i.a
            public void b(String str, String str2, Bundle bundle) {
                io.i(h.TAG, "Remote transfer of device successfully completed. Proceeding with local operations...");
                h.a(h.this, callback, bundle, str2);
            }

            @Override // com.amazon.identity.auth.device.i.a
            public void t(String str) {
                io.i(h.TAG, "Failed to transfer account since account already exists.");
            }
        }, ej.by("RemoteAccountTransfer"));
    }

    public static synchronized h b(Context context) {
        h hVar;
        synchronized (h.class) {
            if (n == null || jk.gR()) {
                generateNewInstance(context);
            }
            hVar = n;
        }
        return hVar;
    }

    private CORPFMResponse c(Bundle bundle) {
        String string = bundle.getString(AccountConstants.KEY_COR);
        String string2 = bundle.getString(AccountConstants.KEY_COR_SOURCE);
        String string3 = bundle.getString(AccountConstants.KEY_PFM);
        bundle.remove(AccountConstants.KEY_COR);
        bundle.remove(AccountConstants.KEY_COR_SOURCE);
        bundle.remove(AccountConstants.KEY_PFM);
        if (!ar.b(string, string2, string3)) {
            io.i(TAG, "Registering account did not return cor/pfm.");
            return null;
        }
        io.i(TAG, String.format("Using COR/SourceOfCor/PFM/ returned when registering the account: %s, %s, %s", string, string2, string3));
        return new CORPFMResponse(string, string3, string2, Long.valueOf(this.F.currentTimeMillis()));
    }

    private Bundle b(Bundle bundle, Callback callback, ej ejVar) {
        Intent t = ii.t(this.o, AuthPortalUIActivity.class.getName());
        if (ejVar != null) {
            ejVar.e(t);
        }
        if (t != null) {
            t.putExtras(bundle);
            if (bundle.getBoolean(MAPAccountManager.KEY_IS_CALLBACK_FROM_3P_PARAM) || bundle.containsKey(MAPAccountManager.KEY_FEDERATED_AUTH_CONFIG)) {
                t.putExtra(NotificationConstants.REQUEST_TYPE, OpenIdRequest.REQUEST_TYPE.CALLBACK_FOR_3P_LOGIN.toString());
            } else if (!bundle.containsKey(NotificationConstants.REQUEST_TYPE)) {
                t.putExtra(NotificationConstants.REQUEST_TYPE, OpenIdRequest.REQUEST_TYPE.SIGN_IN.toString());
            }
            t.setFlags(131072);
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable(MAPAccountManager.KEY_INTENT, t);
            t.putExtra("callback", new RemoteCallbackWrapper(callback));
            return bundle2;
        }
        throw new RuntimeException("No activity can handle the intent. Probably because you do not declare AuthPortalUIActivity in android manifest");
    }

    private boolean d(Callback callback) {
        if (PlatformSettings.aU(this.o).f("ignore.deregister", false).booleanValue()) {
            io.i(TAG, "Ignoring deregister based on system property ignore.deregister");
            a(callback, false, false);
            return true;
        }
        return false;
    }

    private boolean e(Bundle bundle) {
        return bundle.getBoolean("com.amazon.dcp.sso.AddAccount.options.AddAsSecondary") || bundle.getBoolean("com.amazon.dcp.sso.AddAccount.options.AddAsPrimary");
    }

    @Override // com.amazon.identity.auth.device.f
    public void a(Activity activity, SigninOption signinOption, Bundle bundle, Callback callback, ej ejVar) {
        String str = TAG;
        io.i(str, "registerAccountWithUI SigninOption:" + signinOption.name());
        if (bundle == null) {
            bundle = new Bundle();
        }
        hw.N(bundle);
        boolean z = bundle.getBoolean(MAPAccountManager.KEY_DEREGISTERALL_AND_REGISTER_THIS_AS_PRIMARY_ACCOUNT, false);
        if (this.s.n() && !e(bundle) && !z) {
            m.b(callback, this.s.o());
            return;
        }
        Bundle bundle2 = null;
        int i = AnonymousClass7.V[signinOption.ordinal()];
        if (i != 1) {
            if (i == 2) {
                bundle.putString(NotificationConstants.REQUEST_TYPE, OpenIdRequest.REQUEST_TYPE.REGISTER.toString());
                bundle2 = b(bundle, callback, ejVar);
            } else if (i == 3) {
                bundle2 = a(bundle, callback);
            } else if (i != 4) {
                m.a(callback, MAPError.CommonError.BAD_REQUEST, String.format("Sign-in option %s is not supported", signinOption.name()), 7, String.format("Signin Options %s is not supported", signinOption.name()));
            } else {
                bundle.putString(NotificationConstants.REQUEST_TYPE, OpenIdRequest.REQUEST_TYPE.FORGOT_PASSWORD.toString());
                bundle2 = b(bundle, callback, ejVar);
            }
        } else if (bundle.containsKey(MAPAccountManager.KEY_AUTHENTICATION_CHALLENGE)) {
            bundle.putString(NotificationConstants.REQUEST_TYPE, OpenIdRequest.REQUEST_TYPE.SIGN_IN.toString());
            bundle2 = b(bundle, callback, ejVar);
        } else {
            if (!mz.bj(this.o) && !mz.bk(this.o)) {
                bundle2 = a(bundle, callback);
            }
            if (bundle2 == null) {
                bundle.putString(NotificationConstants.REQUEST_TYPE, OpenIdRequest.REQUEST_TYPE.SIGN_IN.toString());
                bundle2 = b(bundle, callback, ejVar);
            }
        }
        a(activity, callback, bundle2, "Could not find the sign in UI. If the option passed in was MyAccount, you are on a 3rd party device. Otherwise, this more than likely represents a bug.");
    }

    h(ed edVar, x xVar, RegisterChildApplicationAction registerChildApplicationAction, al alVar, AmazonAccountManager amazonAccountManager, j jVar, i iVar, gg ggVar, ab abVar, z zVar, ar arVar, w wVar, OAuthTokenManager oAuthTokenManager, gd gdVar, ae aeVar, n nVar, eh ehVar) {
        this.o = edVar;
        this.q = xVar;
        this.r = registerChildApplicationAction;
        this.p = alVar;
        this.s = amazonAccountManager;
        this.t = jVar;
        this.u = iVar;
        this.w = ggVar;
        this.x = abVar;
        this.y = zVar;
        this.A = arVar;
        this.z = wVar;
        this.B = oAuthTokenManager;
        this.D = gdVar;
        this.E = aeVar;
        this.v = nVar;
        this.F = ehVar;
    }

    public void c(final Bundle bundle, final Callback callback, final ej ejVar) {
        if (!mz.f(this.o)) {
            io.e(TAG, "BootstrapWithADPToken API is only supported for isolated applications for now.");
            m.a(callback, MAPError.CommonError.UNSUPPORTED_OPERATION, "BootstrapWithADPToken API is only supported for isolated applications for now.", MAPAccountManager.RegistrationError.BAD_REQUEST.value(), "BootstrapWithADPToken API is only supported for isolated applications for now.", null);
        } else if (!getAccounts().isEmpty()) {
            io.e(TAG, "Registered account found on device. bootstrap API works only on unregistered devices");
            m.b(callback, this.s.o());
        } else {
            m.a(new c.b() { // from class: com.amazon.identity.auth.device.h.4
                @Override // com.amazon.identity.auth.device.c.b
                public Bundle a(final Callback callback2) {
                    if (!h.this.getAccounts().isEmpty()) {
                        io.e(h.TAG, "Registered account found on device. bootstrap API works only on unregistered devices");
                        m.b(callback, h.this.s.o());
                        return null;
                    }
                    return h.a(h.this, RegistrationType.FROM_ADP_TOKEN, bundle, new Callback() { // from class: com.amazon.identity.auth.device.h.4.1
                        @Override // com.amazon.identity.auth.device.api.Callback
                        public void onError(Bundle bundle2) {
                            callback2.onError(bundle2);
                        }

                        @Override // com.amazon.identity.auth.device.api.Callback
                        public void onSuccess(Bundle bundle2) {
                            h.this.w.g("dcp.third.party.device.state", "serial.number", bundle.getString(DeviceDataKeys.KEY_DEVICE_SERIAL_NUMBER));
                            ch.bP().P();
                            callback2.onSuccess(bundle2);
                        }
                    }, ejVar);
                }
            }, callback, "BootstrapMAPWithADPToken");
        }
    }

    @Override // com.amazon.identity.auth.device.f
    public void b(Activity activity, SigninOption signinOption, Bundle bundle, Callback callback, ej ejVar) {
        a((Context) activity, signinOption, bundle, callback, ejVar);
    }

    private Bundle b(Bundle bundle) {
        String string = bundle.getString("com.amazon.dcp.sso.token.oauth.amazon.access_token");
        Bundle bundle2 = new Bundle();
        if (!TextUtils.isEmpty(string)) {
            bundle2.putString("com.amazon.dcp.sso.token.oauth.amazon.access_token", string);
            bundle2.putString(AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_EXPIRES_AT, bundle.getString(AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_EXPIRES_AT));
            bundle2.putString(AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN, bundle.getString(AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN));
        }
        bundle.remove("com.amazon.dcp.sso.token.oauth.amazon.access_token");
        bundle.remove(AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_EXPIRES_AT);
        bundle.remove(AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN);
        return bundle2;
    }

    private void b(Bundle bundle, Bundle bundle2) {
        bundle.putString(AccountConstants.KEY_ACCOUNT_UUID, UUID.randomUUID().toString());
        for (String str : bundle2.keySet()) {
            if (str.startsWith("com.amazon.dcp.sso.property.account.extratokens")) {
                bundle.putString(str, bundle2.getString(str));
            }
        }
        bundle.putString("force_refresh_dms_to_oauth_done_once", "true");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Set<String> set, Callback callback, ej ejVar, Bundle bundle) {
        io.i(TAG, "Deregister all accounts initiated");
        for (String str : set) {
            if (this.s.D(str)) {
                try {
                    bl blVar = new bl();
                    b(str, blVar, ejVar, bundle);
                    if (!jk.gR()) {
                        blVar.get();
                    }
                } catch (MAPCallbackErrorException e) {
                    String str2 = TAG;
                    io.e(str2, "MAP Error calling deregister. Error: " + hw.M(e.getErrorBundle()), e);
                } catch (InterruptedException e2) {
                    io.e(TAG, "InterruptedException calling deregister.", e2);
                } catch (ExecutionException e3) {
                    io.e(TAG, "ExecutionException calling deregister", e3);
                }
            }
        }
        a(callback, true, true);
    }

    private void a(Context context, Callback callback, Bundle bundle, String str) {
        if (bundle != null && context != null) {
            a(context, bundle, callback);
        } else if (bundle != null) {
            m.a(callback, bundle);
        } else {
            callback.onError(hf.a(MAPError.CommonError.UI_NOT_FOUND, str, MAPAccountManager.RegistrationError.UI_NOT_FOUND, str));
        }
    }

    private void a(Context context, Bundle bundle, Callback callback) {
        Intent intent = (Intent) bundle.getParcelable(MAPAccountManager.KEY_INTENT);
        bundle.remove(MAPAccountManager.KEY_INTENT);
        if (intent == null) {
            io.e(TAG, "Failed to locate an activity containing the sign-in UI");
            m.a(callback, MAPError.CommonError.UI_NOT_FOUND, "Failed to locate an activity containing the sign-in UI", 6, "Failed to locate an activity containing the sign-in UI");
            return;
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        context.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bundle b(final String str, final Callback callback, ej ejVar, final Bundle bundle) {
        io.i(TAG, "Starting Deregistration");
        final Account o = hu.o(this.o, str);
        final boolean Q = this.x.Q(str);
        final Set<Integer> a2 = this.x.a(this.o, str);
        this.q.a(this.p.aa(), new x.d() { // from class: com.amazon.identity.auth.device.h.2
            @Override // com.amazon.identity.auth.device.x.d
            public void onResult(Bundle bundle2) {
                boolean z = bundle2.getBoolean("booleanResult");
                if (z) {
                    io.i(h.TAG, "Device deregistration success");
                } else {
                    io.w(h.TAG, "Device deregistration failed");
                }
                ed edVar = h.this.o;
                boolean z2 = Q;
                String str2 = str;
                Account account = o;
                Set set = a2;
                Bundle bundle3 = bundle;
                l.a(edVar, z2, str2, account, (String) null, set, bundle3 != null ? bundle3.getBundle(MAPAccountManager.KEY_CLIENT_EVENT_CONTEXT) : null);
                IsolatedModeSwitcher.switchAppToSSOModeIfNecessary(h.this.o);
                h.this.a(callback, true, z);
            }
        }, str, ejVar, bundle);
        return null;
    }

    private Bundle a(Bundle bundle, Callback callback) {
        Intent s = s(AccountConstants.ACTION_ACCOUNT_ADD_INTENT);
        if (s == null) {
            io.i(TAG, "No intent for MyAccount.");
            return null;
        }
        io.i(TAG, "Register with My Account");
        s.putExtras(bundle);
        s.putExtra("accountAuthenticatorResponse", d.b(callback));
        s.putExtra(AccountConstants.KEY_ADD_ACCOUNT_AUTHTOKENTYPE, bundle.getString(AccountConstants.KEY_ADD_ACCOUNT_AUTHTOKENTYPE));
        s.putExtra(AccountConstants.KEY_ADD_ACCOUNT_REQUIREDFEATURES, bundle.getStringArray(AccountConstants.KEY_ADD_ACCOUNT_REQUIREDFEATURES));
        s.putExtra(AccountConstants.KEY_ADD_ACCOUNT_OPTIONS, bundle.getBundle(AccountConstants.KEY_ADD_ACCOUNT_OPTIONS));
        s.putExtra(AccountConstants.KEY_ADD_ACCOUNT_CALLER_INFORMATION, this.o.getPackageName());
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable(MAPAccountManager.KEY_INTENT, s);
        return bundle2;
    }

    @Override // com.amazon.identity.auth.device.f
    public MAPFuture<Bundle> b(final String str, final String str2, final Bundle bundle, Callback callback, final ej ejVar) {
        io.i(TAG, "renameDevice logic called");
        bl blVar = new bl(callback);
        m.a(new c.b() { // from class: com.amazon.identity.auth.device.h.3
            @Override // com.amazon.identity.auth.device.c.b
            public Bundle a(Callback callback2) {
                h.this.E.d(str, str2, bundle, callback2, ejVar);
                return null;
            }
        }, blVar, "RenameDevice");
        return blVar;
    }

    @Override // com.amazon.identity.auth.device.f
    public void b(final Activity activity, final String str, final Bundle bundle, final Callback callback, final ej ejVar) {
        ji.b(new Runnable() { // from class: com.amazon.identity.auth.device.h.5
            @Override // java.lang.Runnable
            public void run() {
                ArrayList<String> stringArrayList = bundle.getStringArrayList(MAPAccountManager.KEY_ENSURE_ACCOUNT_STATE_ATTRIBUTES);
                try {
                    String string = new TokenManagement(h.this.o).getToken(str, "com.amazon.dcp.sso.token.oauth.amazon.access_token", null, null).get().getString("value_key");
                    HashSet hashSet = new HashSet();
                    hashSet.addAll(stringArrayList);
                    if (hashSet.contains("user_id")) {
                        hashSet.remove("user_id");
                        hashSet.add("id");
                    }
                    ArrayList<String> c = new bd(string, bundle, hashSet, ejVar).c(stringArrayList);
                    if (c == null) {
                        io.e(h.TAG, "Cannot fetch user profile from Panda");
                        callback.onError(hf.a(MAPError.CommonError.INTERNAL_ERROR, "MAP cannot get user profile from Panda", MAPAccountManager.RegistrationError.INTERNAL_ERROR.value(), "MAP cannot get user profile from Panda"));
                    } else if (c.isEmpty()) {
                        h.a(callback, stringArrayList);
                    } else {
                        bundle.putStringArrayList(MAPAccountManager.KEY_ACCOUNT_MISSING_ATTRIBUTES, c);
                        bundle.putBoolean(AccountConstants.KEY_SET_COOKIE_FOR_AUTHENTICATE_ACCOUNT_WITH_UI, true);
                        bundle.putString("com.amazon.dcp.sso.property.account.acctId", str);
                        bundle.putBoolean("isAccountStateFixUpFlow", true);
                        h.this.b(activity, SigninOption.WebviewSignin, bundle, callback, ejVar);
                    }
                } catch (Exception unused) {
                    io.e(h.TAG, "Cannot get access token");
                    callback.onError(hf.a(MAPError.CommonError.INTERNAL_ERROR, "MAP cannot get an access token to ensure the account state", MAPAccountManager.RegistrationError.INTERNAL_ERROR.value(), "MAP cannot get access token for ensuring the account state"));
                }
            }
        });
    }

    @Override // com.amazon.identity.auth.device.f
    public void a(final RegistrationType registrationType, final Bundle bundle, Callback callback, final ej ejVar) {
        in.a(registrationType, "RegistrationType");
        if (bundle == null) {
            bundle = new Bundle();
        }
        String str = TAG;
        io.i(str, "registerAccount:" + registrationType.getName());
        if (registrationType == RegistrationType.FROM_ADP_TOKEN) {
            c(bundle, callback, ejVar);
        } else if (bundle.getBoolean(MAPAccountManager.KEY_DEREGISTERALL_AND_REGISTER_THIS_AS_PRIMARY_ACCOUNT, false) && d(new bl())) {
            a(MAPError.AccountError.DEREGISTER_FAILED, "Cannot deregister all accounts because the system property, ignore.deregister, is set to true", MAPAccountManager.RegistrationError.DEREGISTER_FAILED, callback, (Bundle) null, "Cannot deregister all accounts before register primary account, ignore.deregister is set in system property.");
        } else {
            m.a(new c.b() { // from class: com.amazon.identity.auth.device.h.12
                @Override // com.amazon.identity.auth.device.c.b
                public Bundle a(Callback callback2) {
                    return h.a(h.this, registrationType, bundle, callback2, ejVar);
                }
            }, callback, "AddAccount");
        }
    }

    @Override // com.amazon.identity.auth.device.f
    public MAPFuture<Bundle> a(String str, String str2, Bundle bundle, Callback callback, ej ejVar) {
        in.a(str, "directedId");
        in.a(str2, "deviceType");
        io.i(TAG, "registerChildApplication device type:".concat(str2));
        bl blVar = new bl(callback);
        try {
            this.r.c(str, str2, bundle, blVar, ejVar);
        } catch (RegisterChildApplicationAction.NotChildApplicationException unused) {
            String format = String.format("%s is not a child application device type", str2);
            m.a(blVar, MAPError.AttributeError.NOT_A_CHILD_DEVICE_TYPE, format, MAPAccountManager.RegistrationError.REGISTER_FAILED.value(), format, null);
        }
        return blVar;
    }

    @Override // com.amazon.identity.auth.device.f
    public MAPFuture<Bundle> a(final String str, Callback callback, final ej ejVar, Bundle bundle) {
        io.i(TAG, "deregisterAccount logic called");
        bl blVar = new bl(callback);
        final Bundle K = hw.K(bundle);
        io.i(TAG, "Deregister initiated");
        if (!d(blVar)) {
            if (!this.s.D(str)) {
                a((Callback) blVar, true, true);
            } else {
                final String o = this.s.o();
                if (str.equals(o)) {
                    io.i(TAG, "Deregistering a default primary");
                    K.putBoolean("DeregisteringDefaultPrimary", true);
                }
                m.a(new c.b() { // from class: com.amazon.identity.auth.device.h.15
                    @Override // com.amazon.identity.auth.device.c.b
                    public Bundle a(final Callback callback2) {
                        return h.this.b(str, new Callback() { // from class: com.amazon.identity.auth.device.h.15.1
                            @Override // com.amazon.identity.auth.device.api.Callback
                            public void onError(Bundle bundle2) {
                                AnonymousClass15 anonymousClass15 = AnonymousClass15.this;
                                h.a(h.this, str, o);
                                callback2.onError(bundle2);
                            }

                            @Override // com.amazon.identity.auth.device.api.Callback
                            public void onSuccess(Bundle bundle2) {
                                AnonymousClass15 anonymousClass15 = AnonymousClass15.this;
                                h.a(h.this, str, o);
                                callback2.onSuccess(bundle2);
                            }
                        }, ejVar, K);
                    }
                }, blVar, "DeregisterAccount");
            }
        }
        return blVar;
    }

    @Override // com.amazon.identity.auth.device.f
    public MAPFuture<Bundle> a(Callback callback, final ej ejVar, Bundle bundle) {
        io.i(TAG, "deregisterDevice logic called");
        bl blVar = new bl(callback);
        final Bundle K = hw.K(bundle);
        K.putBoolean("DeregisteringDevice", true);
        final Set<String> accounts = getAccounts();
        if (!d(blVar)) {
            m.a(new c.b() { // from class: com.amazon.identity.auth.device.h.8
                @Override // com.amazon.identity.auth.device.c.b
                public Bundle a(Callback callback2) {
                    return h.this.a(accounts, callback2, ejVar, K);
                }
            }, blVar, "DeregisterAccountsInner");
        }
        return blVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bundle a(Set<String> set, final Callback callback, final ej ejVar, final Bundle bundle) {
        for (String str : set) {
            if (this.y.N(str)) {
                if (!this.s.D(str)) {
                    a(callback, true, true);
                    return null;
                }
                io.i(TAG, "deregisterAllAccountsInner - deregister the primary first");
                b(str, new Callback() { // from class: com.amazon.identity.auth.device.h.9
                    @Override // com.amazon.identity.auth.device.api.Callback
                    public void onError(Bundle bundle2) {
                        callback.onError(bundle2);
                    }

                    @Override // com.amazon.identity.auth.device.api.Callback
                    public void onSuccess(Bundle bundle2) {
                        Set<String> accounts = h.this.getAccounts();
                        bl blVar = new bl();
                        h.this.b(accounts, blVar, ejVar, bundle);
                        try {
                            if (!jk.gR()) {
                                blVar.get();
                            }
                        } catch (MAPCallbackErrorException e) {
                            String str2 = h.TAG;
                            io.e(str2, "MAP Error calling deregisterAllAccountsManually. Error: " + hw.M(e.getErrorBundle()), e);
                        } catch (InterruptedException e2) {
                            io.e(h.TAG, "InterruptedException calling deregisterAllAccountsManually.", e2);
                        } catch (ExecutionException e3) {
                            io.e(h.TAG, "ExecutionException calling deregisterAllAccountsManually", e3);
                        }
                        callback.onSuccess(bundle2);
                    }
                }, ejVar, bundle);
                return null;
            }
        }
        b(set, callback, ejVar, bundle);
        return null;
    }

    @Override // com.amazon.identity.auth.device.f
    public MAPFuture<Bundle> a(final String str, final im imVar, Bundle bundle, Callback callback, ej ejVar) {
        final bl blVar = new bl(callback);
        if (!a(str, blVar)) {
            return blVar;
        }
        if (!(au.b(imVar) || au.c(imVar))) {
            m.a(blVar, MAPError.CommonError.BAD_REQUEST, String.format("The key %s is not valid", imVar.getKey()), MAPAccountManager.RegistrationError.UNRECOGNIZED.value(), String.format("key %s is not valid", imVar.getKey()), null);
            return blVar;
        }
        this.u.a(new i.a() { // from class: com.amazon.identity.auth.device.h.10
            @Override // com.amazon.identity.auth.device.i.a
            public void a(MAPError mAPError, String str2, MAPAccountManager.RegistrationError registrationError, String str3, Bundle bundle2) {
                m.a(blVar, mAPError, str2, registrationError.value(), str3, bundle2);
            }

            @Override // com.amazon.identity.auth.device.i.a
            public void b(String str2, String str3, Bundle bundle2) {
                h.this.a(str, imVar.getPackageName(), bundle2);
                Bundle bundle3 = new Bundle();
                hu.c(h.this.o, str, bundle3);
                bundle3.putString("authtoken", h.this.w.b(str, imVar.getKey()));
                blVar.onSuccess(bundle3);
            }

            @Override // com.amazon.identity.auth.device.i.a
            public void t(String str2) {
                m.b(blVar, str2);
            }
        }, str, imVar.getPackageName(), bundle != null ? bundle : new Bundle(), ejVar);
        return blVar;
    }

    @Override // com.amazon.identity.auth.device.f
    @Deprecated
    public void a(Activity activity, String str, Bundle bundle, Callback callback, ej ejVar) {
        a((Context) activity, str, true, bundle, callback, ejVar);
    }

    @Deprecated
    private void a(Context context, String str, boolean z, Bundle bundle, Callback callback, ej ejVar) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        hw.N(bundle);
        io.a(TAG, "Confirm Credential called with options: %s.", bundle.toString());
        if (TextUtils.isEmpty(str)) {
            callback.onError(hf.a(MAPError.CommonError.BAD_REQUEST, "Cannot confirm credentials because the directedId is empty", MAPAccountManager.RegistrationError.BAD_REQUEST, "Cannot confirm credential given empty directedId."));
        } else if (z && !this.s.D(str)) {
            String format = String.format("Customer %s is not registered.", str);
            callback.onError(hf.a(MAPError.AccountError.CUSTOMER_NOT_FOUND, format, MAPAccountManager.RegistrationError.CUSTOMER_NOT_FOUND.value(), format));
        } else {
            bundle.putString(NotificationConstants.REQUEST_TYPE, OpenIdRequest.REQUEST_TYPE.CONFIRM_CREDENTIAL.toString());
            bundle.putString("directedid", str);
            Bundle b = b(bundle, callback, ejVar);
            if (context != null) {
                a(context, b, callback);
            } else {
                m.a(callback, b);
            }
        }
    }

    @Override // com.amazon.identity.auth.device.f
    public void a(final Bundle bundle, final Callback callback, final ej ejVar) {
        if (bundle != null && ((bundle.containsKey("com.amazon.dcp.sso.property.account.acctId") || bundle.containsKey(MAPAccountManager.KEY_AMAZON_ACCOUNT_LOGIN_NAME)) && bundle.containsKey(MAPAccountManager.KEY_AMAZON_ACCOUNT_PASSWORD))) {
            if (bundle.containsKey(MAPAccountManager.KEY_AMAZON_ACCOUNT_LOGIN_NAME) && bundle.containsKey("com.amazon.dcp.sso.property.account.acctId")) {
                callback.onError(hf.a(MAPError.CommonError.BAD_REQUEST, "Cannot pass in both a login and directedId to the authenticateAccount API.", MAPAccountManager.RegistrationError.BAD_REQUEST, "Cannot pass in both login and directedId to authenticateAccount API."));
                return;
            } else {
                ji.b(new Runnable() { // from class: com.amazon.identity.auth.device.h.11
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            callback.onSuccess(h.this.v.a(bundle, ejVar));
                        } catch (MAPCallbackErrorException e) {
                            callback.onError(e.getErrorBundle());
                        }
                    }
                });
                return;
            }
        }
        callback.onError(hf.a(MAPError.CommonError.BAD_REQUEST, "A login/directedId and password are required to authenticate/confirm credentials.", MAPAccountManager.RegistrationError.BAD_REQUEST, "A login/directedId and password are required to authenticate/confirmCredential."));
    }

    private void a(Context context, SigninOption signinOption, Bundle bundle, Callback callback, ej ejVar) {
        in.a(signinOption, "option");
        String str = TAG;
        new StringBuilder("authenticateAccountWithUI SigninOption:").append(signinOption.name());
        io.dm(str);
        Bundle bundle2 = bundle != null ? bundle : new Bundle();
        hw.N(bundle2);
        if (bundle2.getBoolean(AccountConstants.KEY_SET_COOKIE_FOR_AUTHENTICATE_ACCOUNT_WITH_UI)) {
            String[] a2 = a(bundle.getString("com.amazon.dcp.sso.property.account.acctId"), bundle.getString("com.amazon.identity.ap.domain"), callback);
            if (a2 == null) {
                return;
            }
            bundle2.putStringArray("InjectCookiesToAuthPortalUIActivity", a2);
        }
        Bundle bundle3 = null;
        int i = AnonymousClass7.V[signinOption.ordinal()];
        if (i == 1) {
            bundle2.putString(NotificationConstants.REQUEST_TYPE, OpenIdRequest.REQUEST_TYPE.AUTHENTICATE.toString());
            bundle3 = b(bundle2, callback, ejVar);
        } else if (i == 4) {
            bundle2.putString(NotificationConstants.REQUEST_TYPE, OpenIdRequest.REQUEST_TYPE.FORGOT_PASSWORD.toString());
            bundle3 = b(bundle2, callback, ejVar);
        } else if (i != 5) {
            m.a(callback, MAPError.CommonError.BAD_REQUEST, String.format("Sign-in option %s is not supported", signinOption.name()), 7, String.format("Signin Options %s is not supported", signinOption.name()));
        } else {
            a(context, bundle.getString("com.amazon.dcp.sso.property.account.acctId"), false, bundle, callback, ejVar);
            return;
        }
        a(context, callback, bundle3, "Could not find the sign in UI. This more than likely represents a bug.");
    }

    private gg.a a(final boolean z, final boolean z2, final boolean z3, final String str, final Bundle bundle, final Bundle bundle2, final List<a> list) {
        return new gg.a() { // from class: com.amazon.identity.auth.device.h.14
            @Override // com.amazon.identity.auth.device.gg.a
            public void onSuccess() {
                io.dm(h.TAG);
                h.this.x.P();
                boolean z4 = z;
                if (!z4 || (z4 && z2)) {
                    ji.b(new Runnable() { // from class: com.amazon.identity.auth.device.h.14.1
                        @Override // java.lang.Runnable
                        public void run() {
                            AnonymousClass14 anonymousClass14 = AnonymousClass14.this;
                            ar.a(str, h.this.y);
                            ax.b(h.this.o, str, bundle.getString("com.amazon.dcp.sso.property.devicename"));
                            boolean booleanValue = Boolean.valueOf(bundle.getString(CustomerAttributeKeys.KEY_IS_ANONYMOUS)).booleanValue();
                            if (h.this.s.A(str) || booleanValue) {
                                return;
                            }
                            String string = bundle.getString("com.amazon.dcp.sso.property.deviceemail");
                            String string2 = bundle.getString("com.amazon.dcp.sso.token.devicedevicetype");
                            AnonymousClass14 anonymousClass142 = AnonymousClass14.this;
                            h.a(h.this, string, str, string2);
                        }
                    });
                    h.this.a(list, bundle2);
                    l.a(h.this.o, h.this.x, h.this.y, str, z3, bundle2);
                }
            }
        };
    }

    private void a(Bundle bundle) {
        Set<String> accounts = this.s.getAccounts();
        if (accounts != null) {
            for (String str : accounts) {
                this.s.G(str);
                a(a(accounts), bundle);
            }
            this.x.P();
        }
    }

    private List<a> a(Set<String> set) {
        ArrayList arrayList = new ArrayList();
        if (set != null) {
            for (String str : set) {
                boolean Q = this.x.Q(str);
                Account o = hu.o(this.o, str);
                Set<Integer> a2 = this.x.a(this.o, str);
                a aVar = new a((byte) 0);
                aVar.ao = Q;
                aVar.ap = o;
                aVar.an = str;
                aVar.aq = a2;
                arrayList.add(aVar);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<a> list, Bundle bundle) {
        for (a aVar : list) {
            l.a(this.o, aVar.ao, aVar.an, aVar.ap, (String) null, aVar.aq, bundle);
        }
    }

    private void a(Bundle bundle, Bundle bundle2) {
        String str;
        String string = bundle2.getString(AccountConstants.KEY_CUSTOMER_REGION);
        String str2 = null;
        if (!TextUtils.isEmpty(string)) {
            str2 = hr.cX(string);
            str = "customer region (home region)";
        } else {
            str = null;
        }
        if (TextUtils.isEmpty(str2)) {
            String string2 = bundle2.getString("com.amazon.dcp.sso.token.device.accountpool");
            if (!TextUtils.isEmpty(string2)) {
                str2 = hr.cY(string2);
                str = "account pool";
            }
        }
        if (!TextUtils.isEmpty(str2)) {
            String ba = EnvironmentUtils.cd().ba(str2);
            io.i(TAG, String.format("Marking the amazon domain for added account: %s. It's generated base on %s.", ba, str));
            bundle2.putString("key_auth_portal_endpoint", ba);
            bundle2.putString("authDomain", ba);
        }
        bundle2.putString("key_panda_endpoint", EnvironmentUtils.cd().getPandaHost(hr.I(bundle)));
        bundle2.putString("key_panda_marketplace_header", EnvironmentUtils.cd().A(bundle));
        bundle2.putString("x-amzn-identity-auth-domain", hr.H(bundle));
    }

    private List<MAPCookie> a(String str, Bundle bundle) {
        List<MAPCookie> arrayList = new ArrayList<>();
        String string = bundle.getString(AccountConstants.KEY_WEBSITE_COOKIES_JSON_ARRAY);
        String str2 = TAG;
        "Cookies from registration: ".concat(String.valueOf(string));
        io.dm(str2);
        if (TextUtils.isEmpty(string)) {
            return arrayList;
        }
        try {
            try {
                arrayList = new ha(this.o).a(str, new JSONArray(string));
            } catch (JSONException e) {
                String str3 = TAG;
                io.e(str3, "Failed to parse the cookie JSONArray : " + e.getMessage());
            }
            bundle.remove(AccountConstants.KEY_WEBSITE_COOKIES_JSON_ARRAY);
            return arrayList;
        } catch (JSONException e2) {
            String str4 = TAG;
            io.e(str4, "String to JSONArray Conversion failed : " + e2.getMessage());
            return arrayList;
        }
    }

    private Map<String, String> a(String str, Bundle bundle, List<MAPCookie> list, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.B.F(bundle));
        ha.a(list, hashMap);
        hashMap.putAll(this.D.a(str, list, str2));
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Callback callback, boolean z, boolean z2) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("booleanResult", z);
        bundle.putBoolean(MAPAccountManager.KEY_SERVER_SIDE_DEREGISTRATION_RESULT, z2);
        callback.onSuccess(bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        BackwardsCompatiableDataStorage backwardsCompatiableDataStorage = new BackwardsCompatiableDataStorage(this.o, this.w);
        String b = backwardsCompatiableDataStorage.b(str, "com.amazon.dcp.sso.property.devicename");
        String b2 = backwardsCompatiableDataStorage.b(str, "com.amazon.dcp.sso.property.deviceemail");
        if (str2 == null) {
            fz fzVar = new fz(str, null, null);
            for (String str3 : bundle.keySet()) {
                fzVar.v(str3, bundle.getString(str3));
            }
            backwardsCompatiableDataStorage.a(fzVar);
        } else {
            gm gmVar = new gm(this.o, backwardsCompatiableDataStorage);
            fz fzVar2 = new fz(str, null, null);
            for (String str4 : bundle.keySet()) {
                fzVar2.v(gv.W(str2, str4), bundle.getString(str4));
            }
            gmVar.a(fzVar2);
        }
        String b3 = backwardsCompatiableDataStorage.b(str, "com.amazon.dcp.sso.property.devicename");
        String b4 = backwardsCompatiableDataStorage.b(str, "com.amazon.dcp.sso.property.deviceemail");
        if (!TextUtils.equals(b, b3) && ie.q(this.o, str2)) {
            io.dm(TAG);
            ax.a(this.o, str, b3);
        }
        if (TextUtils.equals(b2, b4) || !ie.l(this.o, str2, "com.amazon.kindle")) {
            return;
        }
        io.dm(TAG);
        ax.c(this.o, str, b4);
    }

    protected boolean a(String str, Callback callback) {
        if (str == null || !this.s.D(str)) {
            m.a(callback, MAPError.AccountError.CUSTOMER_NOT_FOUND, "Customer account does not exist or directedId is null", 7, "The provided account does not exist", null);
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(MAPError mAPError, String str, MAPAccountManager.RegistrationError registrationError, Callback callback, Bundle bundle, String str2) {
        io.e(TAG, "Error msg:".concat(String.valueOf(str2)));
        m.a(callback, mAPError, str, registrationError.value(), str2, bundle);
    }

    @Override // com.amazon.identity.auth.device.f
    public void a(Context context, Bundle bundle, Bundle bundle2, Callback callback, ej ejVar) {
        if (!bundle2.containsKey(MAPAccountManager.KEY_LINK_CODE) && !bundle2.containsKey(MAPAccountManager.KEY_PRE_AUTHORIZED_LINK_CODE)) {
            a(context, SigninOption.WebviewConfirmCredentials, bundle2, callback, (ej) null);
        } else {
            a(RegistrationType.WITH_LINK_CODE, bundle2, callback, ejVar);
        }
    }

    private String[] a(String str, String str2, Callback callback) {
        TokenManagement tokenManagement = new TokenManagement(this.o);
        Bundle bundle = new Bundle();
        bundle.putBoolean(CookieKeys.Options.KEY_FORCE_REFRESH, true);
        bundle.putString("domain", str2);
        try {
            String[] stringArray = tokenManagement.getCookies(str, str2, bundle, null).get().getStringArray(CookieKeys.KEY_COOKIES);
            if (stringArray != null && stringArray.length != 0) {
                return stringArray;
            }
            callback.onError(hf.a(MAPError.TokenError.COOKIE_REFRESH_FAILED, "Cookie force refresh failed before launching the AuthPortal UI", MAPAccountManager.RegistrationError.INTERNAL_ERROR.value(), "Cookie force refresh fail before lauching auth portal ui for fix up page"));
            return null;
        } catch (MAPCallbackErrorException e) {
            Bundle errorBundle = e.getErrorBundle();
            if (errorBundle != null) {
                String str3 = TAG;
                io.e(str3, "Cannot refresh the cookie to start auth portal attributes fix up flow. Error Code:" + errorBundle.getInt("com.amazon.dcp.sso.ErrorCode") + " Error message:" + errorBundle.getString("com.amazon.dcp.sso.ErrorMessage"));
            }
            callback.onError(hf.a(MAPError.TokenError.COOKIE_REFRESH_FAILED, "Cookie force refresh failed before launching the AuthPortal UI", MAPAccountManager.RegistrationError.INTERNAL_ERROR.value(), "Cookie force refresh fail before lauching auth portal ui for fix up page"));
            return null;
        } catch (InterruptedException unused) {
            callback.onError(hf.a(MAPError.TokenError.COOKIE_REFRESH_FAILED, "Cookie force refresh failed before launching the AuthPortal UI", MAPAccountManager.RegistrationError.INTERNAL_ERROR.value(), "Cookie force refresh fail before lauching auth portal ui for fix up page"));
            return null;
        } catch (ExecutionException unused2) {
            callback.onError(hf.a(MAPError.TokenError.COOKIE_REFRESH_FAILED, "Cookie force refresh failed before launching the AuthPortal UI", MAPAccountManager.RegistrationError.INTERNAL_ERROR.value(), "Cookie force refresh fail before lauching auth portal ui for fix up page"));
            return null;
        }
    }

    static /* synthetic */ void a(h hVar, Callback callback, Bundle bundle, String str) {
        Map<String, String> map;
        String str2;
        if (bundle == null) {
            io.e(TAG, "No userdata returned. The account cannot be created.");
            m.a(callback, MAPError.CommonError.BAD_REQUEST, "No userdata given. Cannot construct an account", 7, "No userdata given. Cannot construct an account");
            return;
        }
        String string = bundle.getString("com.amazon.dcp.sso.property.account.acctId");
        Bundle bundle2 = new Bundle();
        hVar.e();
        hVar.y.c(new Bundle(), bundle);
        hVar.a(new Bundle(), bundle);
        String a2 = hu.a(hVar.w, str);
        CORPFMResponse c = hVar.c(bundle);
        Map<String, Map<String, String>> d = hVar.d(bundle);
        List<MAPCookie> a3 = hVar.a(string, bundle);
        Bundle b = hVar.b(bundle);
        if (!b.isEmpty()) {
            String ba = EnvironmentUtils.cd().ba(hr.H(null));
            map = hVar.a(string, b, a3, ba);
            str2 = ba;
        } else {
            map = null;
            str2 = null;
        }
        Map<String, String> L = hw.L(bundle);
        hVar.A.a(c, L);
        fz fzVar = new fz(string, L, map);
        for (Map.Entry<String, Map<String, String>> entry : d.entrySet()) {
            u.a(hVar.o, fzVar, entry.getKey(), entry.getValue());
        }
        if (!new BackwardsCompatiableDataStorage(hVar.o, hVar.w).a(a2, fzVar, hVar.a(false, true, true, string, bundle, (Bundle) null, Collections.emptyList()))) {
            m.b(callback, string);
            return;
        }
        hu.c(a2, string, bundle2);
        if (str2 != null && !hz.isEmpty(hVar.D.D(str2, null))) {
            hVar.D.a(str2, (String) null, (List<MAPCookie>) null);
        }
        io.i(TAG, "Emitting broadcast indicating an account transfer.");
        l.a(hVar.y, string);
        io.i(TAG, "MAP finished adding account locally and will do the other necessary work asynchronously in the data propogation callback");
        callback.onSuccess(bundle2);
    }

    static /* synthetic */ void a(Callback callback, String str) {
        m.a(callback, MAPError.CommonError.BAD_REQUEST, str, 7, str);
    }

    static /* synthetic */ Bundle a(h hVar, final RegistrationType registrationType, final Bundle bundle, final Callback callback, final ej ejVar) {
        io.i(TAG, "Starting Registration: ".concat(String.valueOf(registrationType)));
        i.a aVar = new i.a() { // from class: com.amazon.identity.auth.device.h.13
            @Override // com.amazon.identity.auth.device.i.a
            public void a(MAPError mAPError, String str, MAPAccountManager.RegistrationError registrationError, String str2, Bundle bundle2) {
                io.dm(h.TAG);
                h.this.a(mAPError, str, registrationError, callback, bundle2, str2);
            }

            @Override // com.amazon.identity.auth.device.i.a
            public void b(String str, final String str2, final Bundle bundle2) {
                ji.b(new Runnable() { // from class: com.amazon.identity.auth.device.h.13.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AnonymousClass13 anonymousClass13 = AnonymousClass13.this;
                        h.a(h.this, registrationType, bundle, callback, str2, bundle2, ejVar);
                    }
                });
            }

            @Override // com.amazon.identity.auth.device.i.a
            public void t(String str) {
                m.b(callback, str);
            }
        };
        String o = hVar.s.o();
        if (o != null && registrationType == RegistrationType.WITH_DEVICE_SECRET) {
            io.i(TAG, "Already registered. Returning success for register via device secret");
            Bundle bundle2 = new Bundle();
            hu.c(hVar.o, o, bundle2);
            callback.onSuccess(bundle2);
            return null;
        }
        hVar.u.a(aVar, registrationType, bundle, hVar.t, ejVar);
        return null;
    }

    static /* synthetic */ void a(h hVar, RegistrationType registrationType, final Bundle bundle, Callback callback, String str, Bundle bundle2, final ej ejVar) {
        Map<String, String> map;
        String str2;
        fz fzVar;
        String str3;
        String str4;
        if (bundle2 == null) {
            io.e(TAG, "No userdata returned. The account cannot be created.");
            m.a(callback, MAPError.CommonError.BAD_REQUEST, "No userdata given. Cannot construct an account", 7, "No userdata given. Cannot construct an account");
            return;
        }
        String string = bundle2.getString("com.amazon.dcp.sso.property.account.acctId");
        boolean z = bundle.getBoolean(AccountConstants.KEY_RECOVERY_ATTEMPT);
        Bundle bundle3 = new Bundle();
        if (hVar.s.q().isEmpty()) {
            bundle2.putString(AccountConstants.KEY_SESSION_USER_AMAZON_ACCOUNT, "true");
        }
        Bundle bundle4 = bundle.getBundle(MAPAccountManager.KEY_CLIENT_EVENT_CONTEXT);
        bundle2.putString(CustomerAttributeKeys.KEY_IS_ANONYMOUS, Boolean.valueOf(registrationType == RegistrationType.ANONYMOUS).toString());
        boolean z2 = !string.equals(bundle.getString("com.amazon.dcp.sso.property.account.acctId"));
        if (z) {
            bundle3.putBoolean(AccountConstants.KEY_RECOVERY_ATTEMPT, true);
            if (z2) {
                hVar.a(bundle4);
                bundle3.putString(MAPAccountManager.KEY_ACCOUNT_RECOVERY_BY_USING_NEW_ACCOUNT, string);
            }
        }
        hVar.y.c(bundle, bundle2);
        if (hVar.s.n() && hVar.e(bundle) && !TextUtils.equals(hVar.s.o(), string) && (!z || (!bundle.containsKey(MAPAccountManager.KEY_LINK_CODE) && !bundle.containsKey(MAPAccountManager.KEY_PRE_AUTHORIZED_LINK_CODE)))) {
            bundle2.putString(AccountConstants.KEY_SECONDARY_AMAZON_ACCOUNT, "true");
            if (bundle.getBoolean("com.amazon.dcp.sso.AddAccount.options.AddAsPrimary")) {
                bundle2.putString(AccountConstants.KEY_DEVICE_ACCOUNT_ROLE, RegisterDeviceRequest.DeviceAccountRole.PRIMARY.name());
            }
        }
        if (hVar.s.E(string)) {
            hVar.a(string, (String) null, bundle2);
            if (!z) {
                io.w(TAG, "An account has been registered multiple times and this is not a recovery.");
                m.b(callback, string);
                return;
            }
        }
        BackwardsCompatiableDataStorage backwardsCompatiableDataStorage = new BackwardsCompatiableDataStorage(hVar.o, hVar.w);
        hVar.b(bundle2, bundle);
        if (registrationType == RegistrationType.REGISTER_DELEGATED_ACCOUNT) {
            bundle2.putString("com.amazon.dcp.sso.property.account.delegateeaccount", bundle.getString("com.amazon.dcp.sso.property.account.delegateeaccount"));
            String H = hr.H(bundle);
            if (!TextUtils.isEmpty(H)) {
                bundle2.putString(AccountConstants.KEY_DELEGATION_DOMAIN, H);
            }
        }
        hVar.a(bundle, bundle2);
        String a2 = hu.a(hVar.w, str);
        CORPFMResponse c = hVar.c(bundle2);
        Map<String, Map<String, String>> d = hVar.d(bundle2);
        List<MAPCookie> a3 = hVar.a(string, bundle2);
        Bundle b = hVar.b(bundle2);
        if (!b.isEmpty()) {
            String ba = EnvironmentUtils.cd().ba(hr.H(bundle));
            map = hVar.a(string, b, a3, ba);
            str2 = ba;
        } else {
            map = null;
            str2 = null;
        }
        Map<String, String> L = hw.L(bundle2);
        hVar.A.a(c, L);
        fz fzVar2 = new fz(string, L, map);
        for (Map.Entry<String, Map<String, String>> entry : d.entrySet()) {
            u.a(hVar.o, fzVar2, entry.getKey(), entry.getValue());
        }
        boolean z3 = bundle.getBoolean("com.amazon.identity.auth.device.accountManager.newaccount", false);
        if (bundle.getBoolean(MAPAccountManager.KEY_DEREGISTERALL_AND_REGISTER_THIS_AS_PRIMARY_ACCOUNT, false)) {
            ArrayList arrayList = new ArrayList(hVar.s.getAccounts());
            fzVar = fzVar2;
            str3 = str2;
            str4 = a2;
            gg.a a4 = hVar.a(z, z2, z3, string, bundle2, bundle4, hVar.a(hVar.s.getAccounts()));
            hVar.x.P();
            if (!backwardsCompatiableDataStorage.a(str4, fzVar, a4, arrayList)) {
                m.a(callback, MAPError.AccountError.REPLACE_ACCOUNTS_FAILED, "Failed to replace accounts on device", MAPAccountManager.RegistrationError.INTERNAL_ERROR.value(), "Failed to replace accounts on device", null);
                return;
            }
        } else {
            fzVar = fzVar2;
            str3 = str2;
            str4 = a2;
            if (!backwardsCompatiableDataStorage.a(str4, fzVar, hVar.a(z, z2, z3, string, bundle2, bundle4, Collections.emptyList()))) {
                m.b(callback, string);
                return;
            }
        }
        hVar.x.P();
        hu.c(str4, string, bundle3);
        if (str3 != null) {
            String str5 = str3;
            if (!hz.isEmpty(hVar.D.D(str5, null))) {
                hVar.D.a(str5, (String) null, (List<MAPCookie>) null);
            }
        }
        io.i(TAG, "MAP finished adding account locally and will do the other necessary work asynchronously in the data propogation callback");
        fz fzVar3 = fzVar;
        if (!bundle.getBoolean(MAPAccountManager.KEY_DISABLE_USERNAME_AUTO_SUGGESTION)) {
            final String ca = fzVar3.ca("com.amazon.dcp.sso.token.oauth.amazon.access_token");
            if (TextUtils.isEmpty(ca)) {
                io.e(TAG, "access token is null after sign in!");
            } else {
                ji.b(new Runnable() { // from class: com.amazon.identity.auth.device.h.6
                    @Override // java.lang.Runnable
                    public void run() {
                        Collection<String> a5 = h.a(ca, bundle, ejVar);
                        UserDictionaryHelper af = UserDictionaryHelper.af(h.this.o);
                        for (String str6 : a5) {
                            af.cU(str6);
                        }
                    }
                });
            }
        }
        callback.onSuccess(bundle3);
    }

    static /* synthetic */ void a(h hVar, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            io.e(TAG, "The central device email is missing. Please check that the capability EMAIL_ALIAS_SUPPORTED is defined for the following device type in DMS: ".concat(String.valueOf(str3)));
            mq.b("CentralDeviceEmailIsMissing", str3);
        }
        ax.c(hVar.o, str2, str);
    }

    static /* synthetic */ void a(h hVar, String str, String str2) {
        io.i(TAG, "Notify if the default primary has changed");
        if (str.equals(str2)) {
            String o = hVar.s.o();
            if (TextUtils.isEmpty(o) || o.equals(str2)) {
                return;
            }
            mq.incrementCounterAndRecord("DefaultPrimaryAccountChanged", new String[0]);
            String.format("The default primary account %s has been deregistered and the new one is %s", str2, o);
            io.gC();
            l.a(hVar.o, hVar.x, hVar.y, o, false, (Bundle) null);
        }
    }

    static /* synthetic */ void a(Callback callback, ArrayList arrayList) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(MAPAccountManager.KEY_ENSURE_ACCOUNT_STATE_ATTRIBUTES, arrayList);
        callback.onSuccess(bundle);
    }

    static /* synthetic */ Collection a(String str, Bundle bundle, ej ejVar) {
        HashSet hashSet = new HashSet();
        JSONObject bn = new bd(str, bundle, new HashSet(Collections.singletonList("email")), ejVar).bn();
        if (bn == null) {
            io.e(TAG, "cannot get user profile");
        } else {
            String optString = bn.optString("email");
            if (!TextUtils.isEmpty(optString)) {
                String str2 = TAG;
                "User has email login: ".concat(String.valueOf(optString));
                io.dm(str2);
                hashSet.add(optString);
            }
            if (hashSet.isEmpty()) {
                io.e(TAG, "Account has no login claim");
            }
        }
        return hashSet;
    }
}
