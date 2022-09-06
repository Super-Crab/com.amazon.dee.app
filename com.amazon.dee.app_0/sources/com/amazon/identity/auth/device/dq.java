package com.amazon.identity.auth.device;

import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;
import com.amazon.identity.auth.accounts.SessionUserChangedToAccountForPackageChangedAdpater;
import com.amazon.identity.auth.device.bootstrapSSO.BootstrapSSOService;
import com.amazon.identity.auth.device.framework.IsolatedModeSwitcher;
import com.amazon.identity.auth.device.storage.DatabaseCleaner;
import com.amazon.identity.auth.device.storage.DirtyDataSyncingService;
import com.amazon.identity.auth.device.storage.LambortishClock;
import com.amazon.identity.auth.device.userdictionary.UserDictionaryHelper;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class dq {
    private static final String TAG = "com.amazon.identity.auth.device.dq";
    private final ds bb;
    private final ed o;

    public dq(Context context) {
        this.o = ed.M(context.getApplicationContext());
        this.bb = new ds(context);
    }

    public static int G(Context context) {
        return new gp(context, "SSOInfo.config").getIntValue("SSOInfo.config");
    }

    private void a(Class<?> cls, int i) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append(i == 2 ? "Disabling " : "Enabling ");
        sb.append(cls.getSimpleName());
        io.dm(str);
        try {
            this.o.getPackageManager().setComponentEnabledSetting(new ComponentName(this.o, cls), i, 1);
        } catch (IllegalArgumentException unused) {
            io.a("Component Class %s not found in manifest", cls.getSimpleName());
        }
    }

    public void dc() {
        if (jk.gR()) {
            io.e(TAG, "Not migrating because we are running unit tests");
        } else if (!ji.gP()) {
            io.i(TAG, "Get DataStorage instance for initialization");
            gg dV = ((gh) this.o.getSystemService("dcp_data_storage_factory")).dV();
            io.i(TAG, "Initialize DataStorage instance");
            dV.initialize();
            io.i(TAG, "Setup DataStorage instance");
            dV.setup();
            a(this.o, 3);
            IsolatedModeSwitcher.switchAppToSSOModeIfNecessary(this.o);
            ab.g(this.o).O();
            be.k(this.o);
            io.dm(TAG);
            if (LambortishClock.ChangeTimestampsBroadcastReceiver.d(this.o)) {
                a(LambortishClock.ChangeTimestampsBroadcastReceiver.class, 1);
            }
            io.dm(TAG);
            io.dm(TAG);
            if (!DirtyDataSyncingService.d(this.o)) {
                a(DirtyDataSyncingService.class, 2);
            }
            if (!DatabaseCleaner.DatabaseCleaningService.d(this.o)) {
                a(DatabaseCleaner.DatabaseCleaningService.class, 2);
            }
            if (!SessionUserChangedToAccountForPackageChangedAdpater.a(this.bb)) {
                a(SessionUserChangedToAccountForPackageChangedAdpater.class, 2);
            }
            io.dm(TAG);
            io.dm(TAG);
            if (BootstrapSSOService.p(this.o)) {
                a(BootstrapSSOService.class, 1);
            }
            io.dm(TAG);
            a(this.o, 4);
            a(this.o, 5);
            UserDictionaryHelper.af(this.o);
            e(ed.M(this.o).dV());
        } else {
            io.e(TAG, "Cannot do MAP init tasks on the main thread!");
            throw new IllegalStateException("Cannot do MAP init tasks on the main thread!");
        }
    }

    void e(gg ggVar) {
        try {
            io.i(TAG, "Start update legacy authportal domain in database if needed");
            if (!(ggVar instanceof gk)) {
                return;
            }
            for (String str : ggVar.getAccounts()) {
                String str2 = TAG;
                "Fix database for account: ".concat(String.valueOf(str));
                io.dm(str2);
                String b = ggVar.b(str, "authDomain");
                String da = hr.da(b);
                if (!TextUtils.equals(b, da)) {
                    String str3 = TAG;
                    "Fix legacy corrupted db for account: ".concat(String.valueOf(str));
                    io.dm(str3);
                    ggVar.a(str, "authDomain", da);
                    mq.incrementCounterAndRecord("DetectFixLegacyAuthPortalLWADomain", new String[0]);
                }
            }
            io.i(TAG, "Legacy authportal domain in database is up to database");
        } catch (Exception e) {
            io.e(TAG, "Cannot fix legacy authportal domain in database", e);
        }
    }

    static void a(Context context, int i) {
        if (!mz.iQ() || jk.gR()) {
            new gp(context, "SSOInfo.config").e("SSOInfo.config", i);
        }
    }
}
