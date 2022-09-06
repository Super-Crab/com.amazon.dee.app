package com.amazon.identity.auth.device.bootstrapSSO;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.bg;
import com.amazon.identity.auth.device.bh;
import com.amazon.identity.auth.device.bj;
import com.amazon.identity.auth.device.bl;
import com.amazon.identity.auth.device.bootstrapSSO.IBootstrapSSOService;
import com.amazon.identity.auth.device.ej;
import com.amazon.identity.auth.device.framework.IsolatedModeSwitcher;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.mq;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ExecutionException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class BootstrapSSOService extends Service {
    private static final String TAG = BootstrapSSOService.class.getName();
    private final IBinder hc = new IBootstrapSSOService.Stub() { // from class: com.amazon.identity.auth.device.bootstrapSSO.BootstrapSSOService.1
        @Override // com.amazon.identity.auth.device.bootstrapSSO.IBootstrapSSOService
        public Bundle bootstrapForPackage(Bundle bundle) throws RemoteException {
            Context applicationContext = BootstrapSSOService.this.getApplicationContext();
            return BootstrapSSOService.this.a(applicationContext, new MAPAccountManager(applicationContext), bundle.getString("appPackageName"), new bg(applicationContext));
        }
    };

    private Bundle c(int i, String str) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("bootstrapSuccess", false);
        bundle.putInt("com.amazon.dcp.sso.ErrorCode", i);
        bundle.putString("com.amazon.dcp.sso.ErrorMessage", str);
        return bundle;
    }

    public static boolean p(Context context) {
        return !IsolatedModeSwitcher.isAppInStaticIsolatedMode(context);
    }

    protected Bundle a(Context context, MAPAccountManager mAPAccountManager, String str, bg bgVar) {
        String primaryAccount = mAPAccountManager.getPrimaryAccount();
        if (primaryAccount == null) {
            return c(MAPAccountManager.BootstrapError.NO_ACCOUNT.value(), "No Account Registered");
        }
        if (!a(context, str, Binder.getCallingUid())) {
            return c(MAPAccountManager.BootstrapError.FRAUDULENT_PACKAGE.value(), "Package name does not match caller");
        }
        Set<String> c = bj.c(context, str);
        if (c.isEmpty()) {
            return c(MAPAccountManager.BootstrapError.NO_SIGNATURE.value(), "Signature couldn't be obtained");
        }
        ej by = ej.by("BootstrapSSO");
        bh bhVar = new bh(context, primaryAccount, c.iterator().next(), str, bj.c(context, context.getPackageName()).iterator().next());
        bl blVar = new bl();
        bgVar.a(primaryAccount, bhVar, blVar, by);
        try {
            Bundle bundle = blVar.get();
            bundle.putBoolean("bootstrapSuccess", true);
            return bundle;
        } catch (MAPCallbackErrorException e) {
            return e.getErrorBundle();
        } catch (InterruptedException e2) {
            io.e(TAG, "Bootstrap call was interrupted", e2);
            return c(MAPAccountManager.BootstrapError.UNCATEGORIZED_ERROR.value(), "Bootstrap call was interrupted");
        } catch (ExecutionException e3) {
            io.e(TAG, "Unexpected error calling bootstrap", e3);
            return c(MAPAccountManager.BootstrapError.UNCATEGORIZED_ERROR.value(), "Unexpected error calling bootstrap");
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        io.dm(TAG);
        return this.hc;
    }

    private boolean a(Context context, String str, int i) {
        String[] packagesForUid;
        PackageManager packageManager = context.getPackageManager();
        try {
            packagesForUid = packageManager.getPackagesForUid(i);
        } catch (Exception e) {
            io.w(TAG, "PackageManager call failed; retrying", e);
            mq.b("PackageManagerError", new String[0]);
            packagesForUid = packageManager.getPackagesForUid(i);
        }
        if (packagesForUid == null) {
            io.e(TAG, "Could not get packages for uid");
            mq.b("PackageManagerErrorAfterRetry", new String[0]);
            return false;
        }
        return Arrays.asList(packagesForUid).contains(str);
    }
}
