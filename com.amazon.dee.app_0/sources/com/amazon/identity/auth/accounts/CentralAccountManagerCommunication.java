package com.amazon.identity.auth.accounts;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.device.af;
import com.amazon.identity.auth.device.ag;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.MAPFuture;
import com.amazon.identity.auth.device.api.RegistrationType;
import com.amazon.identity.auth.device.api.SigninOption;
import com.amazon.identity.auth.device.bl;
import com.amazon.identity.auth.device.co;
import com.amazon.identity.auth.device.ct;
import com.amazon.identity.auth.device.cu;
import com.amazon.identity.auth.device.dn;
import com.amazon.identity.auth.device.ds;
import com.amazon.identity.auth.device.du;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.ej;
import com.amazon.identity.auth.device.f;
import com.amazon.identity.auth.device.features.Feature;
import com.amazon.identity.auth.device.framework.IPCCommand;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;
import com.amazon.identity.auth.device.framework.RemoteMAPException;
import com.amazon.identity.auth.device.h;
import com.amazon.identity.auth.device.hq;
import com.amazon.identity.auth.device.hu;
import com.amazon.identity.auth.device.hw;
import com.amazon.identity.auth.device.ie;
import com.amazon.identity.auth.device.im;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.lm;
import com.amazon.identity.auth.device.m;
import com.amazon.identity.auth.device.mq;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class CentralAccountManagerCommunication implements f {
    private static final String TAG = "com.amazon.identity.auth.accounts.CentralAccountManagerCommunication";
    private final hq aZ;
    private co ax;
    private final dn ba;
    private ds bb;
    private h bc;
    private final ed o;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class AuthenticateAccountAction implements IPCCommand {
        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            h b = h.b(edVar);
            ej d = ej.d(bundle, "AuthenticateAccount:");
            b.a(bundle, mq.a(d, callback), d);
            return null;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class DeregisterAccountAction implements IPCCommand {
        public static final String KEY_DEREG_DATA = "deregData";
        public static final String KEY_DIRECTED_ID = "directedId";

        public static Bundle parametersToBundle(String str, ej ejVar, Bundle bundle) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("directedId", str);
            ejVar.D(bundle2);
            bundle2.putBundle("deregData", bundle);
            return bundle2;
        }

        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String string = bundle.getString("directedId");
            Bundle bundle2 = bundle.getBundle("deregData");
            h b = h.b(edVar);
            ej d = ej.d(bundle, "DeregisterAccount");
            b.a(string, mq.a(callback, d, "com.amazon.dcp.sso.ErrorCode", MAPAccountManager.RegistrationError.UNRECOGNIZED), d, bundle2);
            return null;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class DeregisterDeviceAction implements IPCCommand {
        public static final String KEY_DEREG_DATA = "deregData";

        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            Bundle bundle2 = (bundle != null ? bundle : new Bundle()).getBundle("deregData");
            h b = h.b(edVar);
            ej d = ej.d(bundle, "DeregisterDevice");
            b.a(mq.a(callback, d, "com.amazon.dcp.sso.ErrorCode", MAPAccountManager.RegistrationError.UNRECOGNIZED), d, bundle2);
            return null;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class GetAccountAction implements IPCCommand {
        public static final String KEY_PACKAGE_NAME = "packageName";
        public static final String KEY_VALUE = "value";

        public static String getResult(Bundle bundle) {
            return bundle.getString("value");
        }

        public static Bundle parametersToBundle(String str) {
            return GeneratedOutlineSupport1.outline11(KEY_PACKAGE_NAME, str);
        }

        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String string = bundle.getString(KEY_PACKAGE_NAME);
            h b = h.b(edVar);
            Bundle bundle2 = new Bundle();
            bundle2.putString("value", b.r(string));
            return bundle2;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class GetAccountsAction implements IPCCommand {
        public static final String KEY_VALUES = "values";

        public static Set<String> getResult(Bundle bundle) {
            String[] stringArray = bundle.getStringArray("values");
            HashSet hashSet = new HashSet();
            if (stringArray != null) {
                hashSet.addAll(Arrays.asList(stringArray));
            }
            return hashSet;
        }

        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            h b = h.b(edVar);
            Bundle bundle2 = new Bundle();
            bundle2.putStringArray("values", (String[]) b.getAccounts().toArray(new String[0]));
            return bundle2;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class GetPrimaryAccountAction implements IPCCommand {
        public static final String KEY_VALUE = "value";

        public static String getResult(Bundle bundle) {
            return bundle.getString("value");
        }

        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            h b = h.b(edVar);
            Bundle bundle2 = new Bundle();
            bundle2.putString("value", b.getPrimaryAccount());
            return bundle2;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class IsAccountRegisteredAction implements IPCCommand {
        public static final String KEY_DIRECTED_ID = "directed_id";
        public static final String KEY_VALUE = "value";

        public static boolean getResult(Bundle bundle) {
            return bundle.getBoolean("value");
        }

        public static Bundle parametersToBundle(String str) {
            return GeneratedOutlineSupport1.outline11("directed_id", str);
        }

        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String string = bundle.getString("directed_id");
            h b = h.b(edVar);
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean("value", b.isAccountRegistered(string));
            return bundle2;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class IsDeviceRegisteredAction implements IPCCommand {
        public static final String KEY_CALLING_APPLICATION_INFO_BUNDLE = "calling.app.info";
        public static final String KEY_VALUE = "value";

        public static boolean getResult(Bundle bundle) {
            return bundle.getBoolean("value");
        }

        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            h b = h.b(edVar);
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean("value", b.isDeviceRegistered());
            return bundle2;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class RegisterAccountAction implements IPCCommand {
        public static final String KEY_REG_DATA = "regData";
        public static final String KEY_REG_TYPE = "regType";

        public static Bundle parametersToBundle(RegistrationType registrationType, Bundle bundle, ej ejVar) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(KEY_REG_TYPE, registrationType.getName());
            bundle2.putBundle(KEY_REG_DATA, bundle);
            ejVar.D(bundle2);
            return bundle2;
        }

        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            RegistrationType fromName = RegistrationType.fromName(bundle.getString(KEY_REG_TYPE));
            Bundle bundle2 = bundle.getBundle(KEY_REG_DATA);
            h b = h.b(edVar);
            ej d = ej.d(bundle, "RegisterAccount:" + mq.b(fromName));
            d.bA("Count");
            b.a(fromName, hw.K(bundle2), mq.a(callback, d, "com.amazon.dcp.sso.ErrorCode", MAPAccountManager.RegistrationError.UNRECOGNIZED), d);
            return null;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class RegisterChildApplication implements IPCCommand {
        public static final String KEY_DEVICE_TYPE = "device_type";
        public static final String KEY_DIRECTED_ID = "directed_id";
        public static final String KEY_OPTIONS = "options";

        public static Bundle parametersToBundle(String str, String str2, Bundle bundle, ej ejVar) {
            Bundle outline12 = GeneratedOutlineSupport1.outline12("directed_id", str, "device_type", str2);
            outline12.putBundle("options", bundle);
            ejVar.D(outline12);
            return outline12;
        }

        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String string = bundle.getString("directed_id");
            String string2 = bundle.getString("device_type");
            Bundle bundle2 = bundle.getBundle("options");
            h b = h.b(edVar);
            ej d = ej.d(bundle, "RegisterChildApplication");
            b.a(string, string2, bundle2, mq.a(d, callback), d);
            return null;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class RenameDeviceAction implements IPCCommand {
        public static final String KEY_DEVICE_NAME_ERROR = "deviceNameError";
        public static final String KEY_DIRECTED_ID = "directedId";
        public static final String KEY_NEW_DEVICE_NAME = "newDeviceName";
        public static final String KEY_OPTIONS = "options";

        public static Bundle parametersToBundle(String str, String str2, Bundle bundle, ej ejVar) {
            Bundle outline12 = GeneratedOutlineSupport1.outline12("directedId", str, KEY_NEW_DEVICE_NAME, str2);
            outline12.putBundle("options", bundle);
            ejVar.D(outline12);
            return outline12;
        }

        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String string = bundle.getString("directedId");
            String string2 = bundle.getString(KEY_NEW_DEVICE_NAME);
            Bundle bundle2 = bundle.getBundle("options");
            h b = h.b(edVar);
            ej d = ej.d(bundle, "RenameDevice");
            b.b(string, string2, bundle2, mq.a(d, callback), d);
            return null;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class UpdateCredentialsAction implements IPCCommand {
        public static final String KEY_DIRECTED_ID = "directedId";
        public static final String KEY_KEY = "key";
        public static final String KEY_OPTIONS = "options";

        public static Bundle parametersToBundle(String str, String str2, Bundle bundle) {
            Bundle outline12 = GeneratedOutlineSupport1.outline12("directedId", str, "key", str2);
            outline12.putBundle("options", bundle);
            return outline12;
        }

        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            h.b(edVar).a(bundle.getString("directedId"), im.dl(bundle.getString("key")), bundle.getBundle("options"), callback, ej.d(bundle, "UpdateCredentials"));
            return null;
        }
    }

    public CentralAccountManagerCommunication(Context context) {
        this(context, new dn(context, "com.amazon.dcp.sso.ErrorCode", "com.amazon.dcp.sso.ErrorMessage", Integer.valueOf(MAPAccountManager.RegistrationError.REGISTER_FAILED.value())));
    }

    private String H(String str) {
        for (du duVar : MAPApplicationInformationQueryer.E(this.o).cY()) {
            try {
            } catch (RemoteMAPException e) {
                String str2 = TAG;
                io.w(str2, "Couldn't determine device type for " + duVar.getPackageName(), e);
            }
            if (TextUtils.equals(duVar.getDeviceType(), str)) {
                return duVar.getPackageName();
            }
            continue;
        }
        return null;
    }

    private AccountManagerCallback<Bundle> e(Callback callback) {
        if (callback == null) {
            return null;
        }
        return new af(callback, this.o);
    }

    private synchronized h t() {
        if (this.bc == null) {
            this.bc = h.b(this.o);
        }
        return this.bc;
    }

    static /* synthetic */ Bundle u() {
        return m.a(MAPError.AccountError.DEREGISTER_FAILED, "Deregister failed. Could not remove the account", MAPAccountManager.RegistrationError.DEREGISTER_FAILED.value(), "Could not remove the account");
    }

    static /* synthetic */ Bundle v() {
        return new Bundle();
    }

    @Override // com.amazon.identity.auth.device.f
    public void a(Activity activity, SigninOption signinOption, Bundle bundle, Callback callback, ej ejVar) {
        t().a(activity, signinOption, hw.K(bundle), callback, ejVar);
    }

    @Override // com.amazon.identity.auth.device.f
    public void b(Activity activity, SigninOption signinOption, Bundle bundle, Callback callback, ej ejVar) {
        t().b(activity, signinOption, hw.K(bundle), callback, ejVar);
    }

    @Override // com.amazon.identity.auth.device.f
    public Set<String> getAccounts() {
        if (this.bb.dl()) {
            return GetAccountsAction.getResult(this.ba.a(GetAccountsAction.class, (Bundle) null));
        }
        return t().getAccounts();
    }

    @Override // com.amazon.identity.auth.device.f
    public String getPrimaryAccount() {
        if (this.bb.dl()) {
            return GetPrimaryAccountAction.getResult(this.ba.a(GetPrimaryAccountAction.class, (Bundle) null));
        }
        return t().getPrimaryAccount();
    }

    @Override // com.amazon.identity.auth.device.f
    public boolean isAccountRegistered(String str) {
        if (this.bb.dl()) {
            return IsAccountRegisteredAction.getResult(this.ba.a(IsAccountRegisteredAction.class, IsAccountRegisteredAction.parametersToBundle(str)));
        }
        return t().isAccountRegistered(str);
    }

    @Override // com.amazon.identity.auth.device.f
    public boolean isDeviceRegistered() {
        if (this.bb.dl()) {
            return IsDeviceRegisteredAction.getResult(this.ba.a(IsDeviceRegisteredAction.class, (Bundle) null));
        }
        return t().isDeviceRegistered();
    }

    @Override // com.amazon.identity.auth.device.f
    public String r(String str) {
        if (this.bb.dl()) {
            return GetAccountAction.getResult(this.ba.a(GetAccountAction.class, GetAccountAction.parametersToBundle(str)));
        }
        return t().r(str);
    }

    @Override // com.amazon.identity.auth.device.f
    public void a(RegistrationType registrationType, Bundle bundle, Callback callback, ej ejVar) {
        if (this.bb.dt() || this.bb.du()) {
            bundle.remove("com.amazon.dcp.sso.AddAccount.options.AddAsSecondary");
        }
        if (registrationType == RegistrationType.FROM_ADP_TOKEN) {
            t().c(bundle, callback, ejVar);
        } else if (this.bb.dl()) {
            this.ba.a(RegisterAccountAction.class, RegisterAccountAction.parametersToBundle(registrationType, bundle, ejVar), callback);
        } else {
            bundle.putString(AccountConstants.KEY_REGISTRATION_TYPE, registrationType.getName());
            if (registrationType.equals(RegistrationType.FROM_AUTH_TOKEN) && !this.ax.a(Feature.RegistrationViaAuthToken)) {
                io.e(TAG, "Registration via auth token is not supported on this platform.");
                callback.onError(m.a(MAPError.CommonError.UNSUPPORTED_OPERATION, "Registration via auth token is not supported on this platform.", MAPAccountManager.RegistrationError.BAD_REQUEST.value(), "Registration via auth token is not supported on this platform."));
                return;
            }
            this.aZ.a(AccountConstants.AMAZON_ACCOUNT_TYPE, bundle, e(callback));
        }
    }

    @Override // com.amazon.identity.auth.device.f
    public void b(Activity activity, String str, Bundle bundle, Callback callback, ej ejVar) {
        t().b(activity, str, bundle, callback, ejVar);
    }

    public CentralAccountManagerCommunication(Context context, dn dnVar) {
        this.o = ed.M(context);
        this.aZ = (hq) this.o.getSystemService("dcp_account_manager");
        this.bb = (ds) this.o.getSystemService("sso_platform");
        this.ba = dnVar;
        this.ax = this.o.dW();
    }

    @Override // com.amazon.identity.auth.device.f
    public MAPFuture<Bundle> b(String str, String str2, Bundle bundle, Callback callback, ej ejVar) {
        if (this.bb.dl()) {
            bl blVar = new bl(callback);
            this.ba.a(RenameDeviceAction.class, RenameDeviceAction.parametersToBundle(str, str2, bundle, ejVar), blVar);
            return blVar;
        }
        return t().b(str, str2, bundle, callback, ejVar);
    }

    @Override // com.amazon.identity.auth.device.f
    @Deprecated
    public void a(Activity activity, String str, Bundle bundle, Callback callback, ej ejVar) {
        t().a(activity, str, hw.K(bundle), callback, ejVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0153  */
    @Override // com.amazon.identity.auth.device.f
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.amazon.identity.auth.device.api.MAPFuture<android.os.Bundle> a(java.lang.String r10, java.lang.String r11, android.os.Bundle r12, com.amazon.identity.auth.device.api.Callback r13, final com.amazon.identity.auth.device.ej r14) {
        /*
            Method dump skipped, instructions count: 352
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.accounts.CentralAccountManagerCommunication.a(java.lang.String, java.lang.String, android.os.Bundle, com.amazon.identity.auth.device.api.Callback, com.amazon.identity.auth.device.ej):com.amazon.identity.auth.device.api.MAPFuture");
    }

    @Override // com.amazon.identity.auth.device.f
    public MAPFuture<Bundle> a(String str, im imVar, Bundle bundle, Callback callback, ej ejVar) {
        String gA;
        bl blVar = new bl(callback);
        if (this.bb.dl()) {
            this.ba.a(UpdateCredentialsAction.class, UpdateCredentialsAction.parametersToBundle(str, imVar.gA(), bundle), blVar);
            return blVar;
        }
        Account o = hu.o(this.o, str);
        if (o == null) {
            m.a(blVar, MAPError.AccountError.CUSTOMER_NOT_FOUND, "Account given does not exist or was already deregistered", MAPAccountManager.RegistrationError.CUSTOMER_NOT_FOUND.value(), "Account given does not exist or was already deregistered", null);
            return blVar;
        }
        String gA2 = imVar.gA();
        if (!this.bb.dl()) {
            if (ie.q(this.o, imVar.getPackageName())) {
                gA2 = "com.amazon.dcp.sso.token.devicedevicetype";
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(imVar.getPackageName());
                stringBuffer.append(".tokens.");
                if (imVar.getKey().equals("com.amazon.dcp.sso.token.devicedevicetype")) {
                    stringBuffer.append("device_type");
                } else if (imVar.getKey().equals("com.amazon.dcp.sso.token.device.deviceserialname")) {
                    stringBuffer.append("dsn");
                } else if (imVar.getKey().equals("com.amazon.dcp.sso.property.deviceemail")) {
                    stringBuffer.append("email");
                } else if (imVar.getKey().equals("com.amazon.dcp.sso.property.devicename")) {
                    stringBuffer.append("device_name");
                } else if (imVar.getKey().equals("com.amazon.dcp.sso.property.username")) {
                    stringBuffer.append("user_name");
                } else {
                    gA = imVar.gA();
                    gA2 = gA;
                }
                gA = stringBuffer.toString();
                gA2 = gA;
            }
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("com.amazon.dcp.sso.AddAccount.options.URL", lm.getDefaultUrl());
        return a(o, gA2, bundle, callback);
    }

    @Override // com.amazon.identity.auth.device.f
    public void a(Context context, Bundle bundle, Bundle bundle2, Callback callback, ej ejVar) {
        if (!bundle2.containsKey(MAPAccountManager.KEY_LINK_CODE) && !bundle2.containsKey(MAPAccountManager.KEY_PRE_AUTHORIZED_LINK_CODE)) {
            t().a(context, bundle, bundle2, callback, ejVar);
        } else {
            a(RegistrationType.WITH_LINK_CODE, bundle2, callback, ejVar);
        }
    }

    private MAPFuture<Bundle> a(Account account, String str, Bundle bundle, Callback callback) {
        return new ag(this.aZ.a(account, str, bundle, e(callback)));
    }

    @Override // com.amazon.identity.auth.device.f
    public void a(Bundle bundle, Callback callback, ej ejVar) {
        if (this.bb.dl()) {
            ejVar.D(bundle);
            this.ba.a(AuthenticateAccountAction.class, bundle, callback);
            return;
        }
        t().a(bundle, callback, ejVar);
    }

    @Override // com.amazon.identity.auth.device.f
    public MAPFuture<Bundle> a(String str, Callback callback, ej ejVar, Bundle bundle) {
        if (this.bb.dl()) {
            bl blVar = new bl(callback);
            this.ba.a(DeregisterAccountAction.class, DeregisterAccountAction.parametersToBundle(str, ejVar, bundle), blVar);
            return blVar;
        }
        Account o = hu.o(this.o, str);
        if (o == null) {
            Bundle a = m.a(MAPError.AccountError.ACCOUNT_ALREADY_DEREGISTERED, "Account given does not exist or was already deregistered", MAPAccountManager.RegistrationError.ALREADY_DEREGISTERED.value(), "Account given does not exist or was already deregistered");
            bl blVar2 = new bl(callback);
            blVar2.onSuccess(a);
            return blVar2;
        }
        return new cu<Boolean>(this.aZ.a(o, callback == null ? null : new ct<Boolean>(callback) { // from class: com.amazon.identity.auth.accounts.CentralAccountManagerCommunication.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.amazon.identity.auth.device.ct
            public void a(Callback callback2, Boolean bool) {
                if (bool != null && bool.booleanValue()) {
                    callback2.onSuccess(CentralAccountManagerCommunication.v());
                } else {
                    callback2.onError(CentralAccountManagerCommunication.u());
                }
            }
        })) { // from class: com.amazon.identity.auth.accounts.CentralAccountManagerCommunication.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.amazon.identity.auth.device.cu
            /* renamed from: a */
            public Bundle b(Boolean bool) throws MAPCallbackErrorException {
                if (bool != null && bool.booleanValue()) {
                    return CentralAccountManagerCommunication.v();
                }
                throw new MAPCallbackErrorException(CentralAccountManagerCommunication.u());
            }
        };
    }

    @Override // com.amazon.identity.auth.device.f
    public MAPFuture<Bundle> a(Callback callback, ej ejVar, Bundle bundle) {
        if (this.bb.dl()) {
            bl blVar = new bl(callback);
            Bundle bundle2 = new Bundle();
            ejVar.D(bundle2);
            bundle2.putBundle("deregData", bundle);
            this.ba.a(DeregisterDeviceAction.class, bundle2, blVar);
            return blVar;
        }
        return a(((AmazonAccountManager) this.o.getSystemService("dcp_amazon_account_man")).o(), callback, ejVar, bundle);
    }
}
