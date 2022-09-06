package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.ArrayList;
import java.util.Set;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class l {
    private static final String TAG = "l";

    private l() {
    }

    public static void a(final ed edVar, final ab abVar, final z zVar, final String str, final boolean z, final Bundle bundle) {
        fo.i(edVar, new MAPAccountManager(edVar).getAccount());
        ji.b(new Runnable() { // from class: com.amazon.identity.auth.device.l.1
            final /* synthetic */ String aR = null;

            @Override // java.lang.Runnable
            public void run() {
                Account o = hu.o(ed.this, str);
                l.a(ed.this, abVar, zVar, str, "com.amazon.identity.auth.account.added.on.device", z, bundle);
                if (abVar.Q(str)) {
                    String str2 = l.TAG;
                    String.format("%s sends primary account add broadcast", ed.this.getPackageName());
                    io.dm(str2);
                    l.a(zVar, str, o, "com.amazon.dcp.sso.action.account.added", this.aR, z, bundle);
                    return;
                }
                String str3 = l.TAG;
                String.format("%s sends secondary account add broadcast", ed.this.getPackageName());
                io.dm(str3);
                l.a(zVar, str, o, "com.amazon.dcp.sso.action.secondary.account.added", this.aR, z, bundle);
            }
        });
    }

    public static void c(Context context) {
        String str = TAG;
        String.format("%s sends broadcast for account for package changed", context.getPackageName());
        io.dm(str);
        fo.i(context, new MAPAccountManager(context).getAccount());
        aa.f(context).a(null, new Intent(MAPAccountManager.ACCOUNT_FOR_PACKAGE_HAS_CHANGED_INTENT_ACTION), AccountConstants.PERMISSION_AMAZON_ACCOUNT_CHANGED);
    }

    public static void a(Context context, boolean z, String str, Account account, String str2, Set<Integer> set, Bundle bundle) {
        fo.i(context, new MAPAccountManager(context).getAccount());
        Intent a = a(str, (Account) null, "com.amazon.identity.auth.account.removed.on.device", (String) null, false, bundle);
        a(a, set);
        aa.f(context).a(a, AccountConstants.PERMISSION_AMAZON_MULTIPLE_PROFILE_AWARE);
        if (z) {
            a(context, str, account, "com.amazon.dcp.sso.action.account.removed", str2, set, bundle);
        } else {
            a(context, str, account, "com.amazon.dcp.sso.action.secondary.account.removed", str2, set, bundle);
        }
    }

    private static Intent a(String str, Account account, String str2, String str3, boolean z, Bundle bundle) {
        Intent dy = iz.dy(str2);
        if (str3 != null) {
            dy.setPackage(str3);
        }
        if (account != null) {
            dy.putExtra(AccountConstants.EXTRA_AMAZON_ACCOUNT_CHANGED_NAME, account.name);
            dy.putExtra(AccountConstants.EXTRA_AMAZON_ACCOUNT_CHANGED_TYPE, account.type);
        }
        dy.putExtra(MAPAccountManager.KEY_EXTRA_DIRECTED_ID, str);
        if (z) {
            dy.putExtra("com.amazon.identity.auth.device.accountManager.newaccount", z);
        }
        if (bundle != null) {
            dy.putExtra(MAPAccountManager.KEY_CLIENT_EVENT_CONTEXT, bundle);
        }
        return dy;
    }

    private static void a(Context context, String str, Account account, String str2, String str3, Set<Integer> set, Bundle bundle) {
        aa.f(context).a(str, set, a(str, account, str2, str3, false, bundle), AccountConstants.PERMISSION_AMAZON_ACCOUNT_CHANGED);
    }

    private static void a(Intent intent, Set<Integer> set) {
        intent.putIntegerArrayListExtra(MAPAccountManager.KEY_EXTRA_PROFILES_ACCOUNT_ADDED_OR_REMOVED_BELONG_TO, new ArrayList<>(set));
    }

    public static void a(z zVar, String str) {
        Intent dy = iz.dy(MAPAccountManager.ACCOUNT_CHANGED_ON_DEVICE_INTENT_ACTION);
        dy.putExtra(MAPAccountManager.KEY_DIRECTED_ID_POST_ACCOUNT_CHANGE, str);
        zVar.a(str, dy, AccountConstants.PERMISSION_AMAZON_ACCOUNT_CHANGED);
    }

    static /* synthetic */ void a(ed edVar, ab abVar, z zVar, String str, String str2, boolean z, Bundle bundle) {
        Set<Integer> a = abVar.a(edVar, str);
        Intent a2 = a(str, (Account) null, str2, (String) null, z, bundle);
        a(a2, a);
        zVar.a(a2, AccountConstants.PERMISSION_AMAZON_MULTIPLE_PROFILE_AWARE);
    }

    static /* synthetic */ void a(z zVar, String str, Account account, String str2, String str3, boolean z, Bundle bundle) {
        zVar.a(str, a(str, account, str2, str3, z, bundle), AccountConstants.PERMISSION_AMAZON_ACCOUNT_CHANGED);
    }
}
