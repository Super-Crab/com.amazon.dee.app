package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.ActivityManager;
import android.os.Binder;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.attributes.CORPFMResponse;
import com.amazon.identity.auth.device.api.AuthenticatedURLConnection;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.CustomerAttributeKeys;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.MAPFuture;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;
import com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class av implements as {
    private static final String TAG = "com.amazon.identity.auth.device.av";
    private final ar A;
    private final aq dC;
    private final gm dJ;
    private final f dK;
    private final aw dL;
    private final ed o;
    private final gg w;

    public av(ed edVar) {
        this(edVar, edVar.dV());
    }

    private String aq(String str) {
        try {
            io.i(TAG, "Using backwards compatabile way to get device email");
            Account o = hu.o(this.o, str);
            if (o == null) {
                io.dm(TAG);
                return null;
            }
            return gw.ac(this.o).b(o).cO(aj.ah("com.amazon.kindle"));
        } catch (AuthenticatorException e) {
            io.e(TAG, "AuthenticatorException: ", e);
            return null;
        } catch (OperationCanceledException e2) {
            io.e(TAG, "OperationCanceledException: ", e2);
            return null;
        } catch (IOException e3) {
            io.e(TAG, "IOException: ", e3);
            return null;
        } catch (IllegalArgumentException e4) {
            io.e(TAG, "IllegalArgumentException:", e4);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bundle ar(String str) {
        String format = String.format("Unable to retrieve attribute %s", str);
        io.w(TAG, format);
        return am.e(MAPError.AttributeError.GET_ATTRIBUTE_FAILED, format, 4, format);
    }

    private Bundle as(String str) {
        String format = String.format("The attribute %s is not currently supported", str);
        io.e(TAG, format);
        return am.e(MAPError.AttributeError.KEY_NOT_FOUND, format, 2, format);
    }

    private Bundle d(String str, String str2) {
        return GeneratedOutlineSupport1.outline12("value_key", str, CustomerAttributeStore.KEY_DEFAULT_VALUE, str2);
    }

    @Override // com.amazon.identity.auth.device.as
    public Bundle peekAttribute(String str, String str2) {
        im dl = im.dl(str2);
        if (au.a(dl)) {
            String key = dl.getKey();
            if (CustomerAttributeKeys.KEY_COR.equals(key)) {
                return d(this.w.b(str, AccountConstants.KEY_COR), this.dC.at());
            }
            if (CustomerAttributeKeys.KEY_PFM.equals(key)) {
                return d(this.w.b(str, AccountConstants.KEY_PFM), this.dC.au());
            }
            throw new IllegalStateException(String.format("Key %s not recognized as COR/PFM value", key));
        } else if (au.b(dl)) {
            return a(str, dl);
        } else {
            if (au.c(dl)) {
                io.w(TAG, "KEY_XMAIN_AND_XACB_COOKIES is deprecated! Please use TokenManagement.peekCookies API to get auth cookies for your use case.");
                return a(str, dl.gB());
            } else if (au.d(dl)) {
                return d(this.dJ.O(str, dl.gA()), null);
            } else {
                return as(str2);
            }
        }
    }

    @Override // com.amazon.identity.auth.device.as
    public MAPFuture<Bundle> setAttribute(final String str, final String str2, final String str3, Callback callback) {
        ActivityManager.RunningAppProcessInfo runningAppProcessInfo;
        String[] strArr;
        boolean z;
        final bl blVar = new bl(callback);
        im dl = im.dl(str2);
        if (!dl.getKey().startsWith("com.amazon.dcp.sso.property.account.extratokens.")) {
            io.e(TAG, String.format("The key: %s does not have a valid prefix.", str2));
            z = false;
        } else if (dl.getPackageName() == null) {
            z = true;
        } else {
            ed edVar = this.o;
            int callingPid = Binder.getCallingPid();
            ActivityManager activityManager = (ActivityManager) edVar.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
            if (activityManager.getRunningAppProcesses() != null) {
                Iterator<ActivityManager.RunningAppProcessInfo> it2 = activityManager.getRunningAppProcesses().iterator();
                while (it2.hasNext()) {
                    runningAppProcessInfo = it2.next();
                    if (runningAppProcessInfo.pid == callingPid) {
                        break;
                    }
                }
            }
            runningAppProcessInfo = null;
            if (runningAppProcessInfo == null) {
                io.e("SecurityHelpers", "Calling process could not be found. Cannot find it's package");
                strArr = new String[0];
            } else {
                strArr = runningAppProcessInfo.pkgList;
            }
            int length = strArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    z = false;
                    break;
                } else if (TextUtils.equals(strArr[i], dl.getPackageName())) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            if (!z) {
                io.e(TAG, String.format("Cannot set the key: %s in the calling package.", str2));
            }
        }
        if (!z) {
            String format = String.format("Not authorized to setAttribute for key: %s.", str2);
            io.e(TAG, format);
            am.c(callback, MAPError.AttributeError.SET_ATTRIBUTE_FAILED, format, 5, format);
            return blVar;
        }
        ji.b(new Runnable() { // from class: com.amazon.identity.auth.device.av.2
            @Override // java.lang.Runnable
            public void run() {
                im dl2 = im.dl(str2);
                io.i(av.TAG, String.format("Setting the attribute for key: %s", dl2.gA()));
                av.this.dJ.a(str, dl2.gA(), str3);
                av.this.a(blVar, str3, (String) null);
            }
        });
        return blVar;
    }

    public av(ed edVar, gg ggVar) {
        this.o = edVar;
        this.w = ggVar;
        this.dC = new aq(edVar);
        this.A = ar.h(this.o);
        this.dJ = new gm(this.o, new BackwardsCompatiableDataStorage(this.o));
        this.dK = g.a(this.o);
        this.dL = new aw(this.o);
    }

    static /* synthetic */ void b(av avVar, Callback callback, String str, im imVar, EnumSet enumSet, ej ejVar) throws AuthenticatedURLConnection.AccountNeedsRecoveryException {
        boolean a;
        String b = avVar.dJ.b(str, imVar.gA());
        if (b == null || enumSet.contains(CustomerAttributeStore.GetAttributeOptions.ForceRefresh)) {
            if (mz.bf(avVar.o)) {
                a = avVar.dL.at(str);
            } else {
                a = avVar.a(str, imVar, ejVar);
            }
            if (!a) {
                callback.onError(avVar.ar(imVar.getKey()));
                return;
            }
            b = avVar.dJ.b(str, imVar.gA());
        }
        avVar.a(callback, b, (String) null);
    }

    @Override // com.amazon.identity.auth.device.as
    public MAPFuture<Bundle> a(final String str, final String str2, Callback callback, Bundle bundle, final EnumSet<CustomerAttributeStore.GetAttributeOptions> enumSet, final ej ejVar) {
        final bl blVar = new bl(callback);
        if (enumSet.contains(CustomerAttributeStore.GetAttributeOptions.ForceRefresh)) {
            io.b(TAG, "Packages are force refreshing key %s", str2);
        }
        ji.b(new Runnable() { // from class: com.amazon.identity.auth.device.av.1
            @Override // java.lang.Runnable
            public void run() {
                im dl = im.dl(str2);
                MAPApplicationInformationQueryer.E(av.this.o).br(dl.getPackageName());
                try {
                    try {
                        if (mz.bb(av.this.o)) {
                            ed unused = av.this.o;
                            fp.eD();
                        }
                        if (au.a(dl)) {
                            av.a(av.this, blVar, str, dl.getKey(), enumSet, ejVar);
                        } else if (au.b(dl)) {
                            av.a(av.this, blVar, str, dl, enumSet, ejVar);
                        } else if (au.c(dl)) {
                            io.w(av.TAG, "KEY_XMAIN_AND_XACB_COOKIES is deprecated! Please use TokenManagement.getCookies API to get auth cookies for your use case.");
                            av.b(av.this, blVar, str, dl.gB(), enumSet, ejVar);
                        } else if (au.d(dl)) {
                            av.a(av.this, blVar, str, dl);
                        } else {
                            av.a(av.this, blVar, dl.gA());
                        }
                    } catch (AuthenticatedURLConnection.AccountNeedsRecoveryException e) {
                        Bundle accountRecoverContextBundle = e.getAccountRecoverContextBundle();
                        Bundle ar = av.this.ar(str2);
                        if (accountRecoverContextBundle != null) {
                            io.e(av.TAG, "Database corrupted, need recover account.");
                            ar.putAll(fp.E(accountRecoverContextBundle).eC());
                        }
                        blVar.onError(ar);
                    }
                } finally {
                    MAPApplicationInformationQueryer.E(av.this.o).bs(dl.getPackageName());
                }
            }
        });
        return blVar;
    }

    private Bundle a(String str, im imVar) {
        return d(this.dJ.M(str, imVar.gA()), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Callback callback, String str, String str2) {
        io.i(TAG, String.format("Callback with value empty: %b", Boolean.valueOf(TextUtils.isEmpty(str))));
        callback.onSuccess(d(str, str2));
    }

    private boolean a(String str, im imVar, ej ejVar) throws AuthenticatedURLConnection.AccountNeedsRecoveryException {
        if (!mz.bg(this.o) && ie.q(this.o, imVar.getPackageName())) {
            io.i(TAG, String.format("Forcing a refresh of attribute %s is not supported on the platform before Otter_Congo.", imVar.gA()));
            return true;
        }
        io.i(TAG, String.format("Forcing a refresh of attribute %s", imVar.gA()));
        try {
            return this.dK.a(str, imVar, (Bundle) null, (Callback) null, ejVar).get() != null;
        } catch (MAPCallbackErrorException e) {
            fp a = fp.a(e);
            if (a == null) {
                String str2 = TAG;
                io.e(str2, "Got MAPCallbackErrorException while trying to update credentials. Error Bundle: " + hw.M(e.getErrorBundle()), e);
                return false;
            }
            io.e(TAG, "Got MAPCallbackErrorException while trying to update credentials. Recover bundle thrown");
            throw new AuthenticatedURLConnection.AccountNeedsRecoveryException("Error happened when try to get authentication bundle", a.toBundle());
        } catch (InterruptedException e2) {
            io.e(TAG, "Got InterruptedException while trying to update credentials", e2);
            return false;
        } catch (ExecutionException e3) {
            io.e(TAG, "Got ExecutionException while trying to update credentials", e3);
            return false;
        }
    }

    static /* synthetic */ void a(av avVar, Callback callback, String str, String str2, EnumSet enumSet, ej ejVar) throws AuthenticatedURLConnection.AccountNeedsRecoveryException {
        CORPFMResponse a;
        if (enumSet.contains(CustomerAttributeStore.GetAttributeOptions.ForceRefresh)) {
            io.i(TAG, String.format("Forcing a refresh of attribute %s", str2));
            a = avVar.A.b(str, ejVar);
        } else {
            a = avVar.A.a(str, ejVar);
        }
        if (a == null) {
            callback.onError(avVar.ar(str2));
        } else if (CustomerAttributeKeys.KEY_COR.equals(str2)) {
            avVar.a(callback, a.aj(), avVar.dC.at());
        } else if (CustomerAttributeKeys.KEY_PFM.equals(str2)) {
            avVar.a(callback, a.al(), avVar.dC.au());
        } else {
            throw new IllegalStateException(String.format("Key %s not recognized as COR/PFM value", str2));
        }
    }

    static /* synthetic */ void a(av avVar, Callback callback, String str, im imVar, EnumSet enumSet, ej ejVar) throws AuthenticatedURLConnection.AccountNeedsRecoveryException {
        if (enumSet.contains(CustomerAttributeStore.GetAttributeOptions.ForceRefresh) && !avVar.a(str, imVar, ejVar)) {
            callback.onError(am.e(MAPError.CommonError.INTERNAL_ERROR, "Was unable to successfully refresh the credentials on a platform that supports it", 1, "Was unable to successfully refresh the credentials on a platform that supports it"));
            return;
        }
        String b = avVar.dJ.b(str, imVar.gA());
        if (b == null && !mz.aW(avVar.o)) {
            b = (!ie.l(avVar.o, imVar.getPackageName(), "com.amazon.kindle") || !"com.amazon.dcp.sso.property.deviceemail".equals(imVar.getKey())) ? null : avVar.aq(str);
        }
        avVar.a(callback, b, (String) null);
    }

    static /* synthetic */ void a(av avVar, Callback callback, String str, im imVar) {
        hf.c(callback, avVar.dJ.z(str, imVar.gA()));
    }

    static /* synthetic */ void a(av avVar, Callback callback, String str) {
        callback.onError(avVar.as(str));
    }
}
