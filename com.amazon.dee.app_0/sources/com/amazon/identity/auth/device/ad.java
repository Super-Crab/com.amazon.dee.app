package com.amazon.identity.auth.device;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import java.util.HashSet;
import java.util.Set;
/* compiled from: DCP */
@TargetApi(17)
/* loaded from: classes12.dex */
public class ad implements z {
    private static final String TAG = "com.amazon.identity.auth.device.ad";
    private final em cx;
    private final Context mContext;
    private final AmazonAccountManager s;
    private final ab x;

    public ad(ed edVar) {
        this(edVar, ab.g(edVar), new em(edVar), new AmazonAccountManager(edVar));
    }

    @Override // com.amazon.identity.auth.device.z
    public String A() {
        da eh = this.cx.eh();
        String accountForMapping = this.x.getAccountForMapping(MultipleAccountManager.PrimaryUserMappingType.createPrimaryMappingForProfile(eh.getUserId()));
        io.a("Detected visible user %s associated to account %s", Integer.toString(eh.getUserId()), accountForMapping);
        return accountForMapping;
    }

    @Override // com.amazon.identity.auth.device.z
    public boolean N(String str) {
        boolean C = this.s.C(str);
        io.i(TAG, "deregisterAllAccountsOnAccountRemoval returns: ".concat(String.valueOf(C)));
        return C;
    }

    @Override // com.amazon.identity.auth.device.z
    public void a(String str, Intent intent, String str2) {
        da eh = this.cx.eh();
        if (!a(str, eh)) {
            io.a("Not Sending broadcast %s to user %s since it is not visible", intent.getAction(), Integer.toString(eh.getUserId()));
        } else {
            a(intent, str2, eh);
        }
    }

    @Override // com.amazon.identity.auth.device.z
    public void b(String str, Intent intent, String str2) {
        a(intent);
        da eg = this.cx.eg();
        da eh = this.cx.eh();
        io.a("Sending broadcast %s to current user %s and main user %s", intent.getAction(), Integer.toString(eh.getUserId()), Integer.toString(eg.getUserId()));
        if (a(str, eh)) {
            ja.a(this.mContext, intent, str2, eh);
        }
        io.i(TAG, "Sending broadcast to User 0 quick setting bar.");
        Intent intent2 = new Intent(intent);
        intent2.setPackage("com.android.systemui");
        ja.a(this.mContext, intent2, null, eg);
    }

    @Override // com.amazon.identity.auth.device.z
    public void c(Bundle bundle, Bundle bundle2) {
        int cy;
        if (bundle.containsKey(MAPAccountManager.KEY_PROFILE_MAPPING)) {
            cy = bundle.getInt(MAPAccountManager.KEY_PROFILE_MAPPING);
        } else if (bundle.containsKey("calling_profile")) {
            cy = bundle.getInt("calling_profile");
        } else {
            io.w(TAG, "No calling profile or mapping profile given. Defaulting to main profile");
            cy = da.cy();
        }
        Integer valueOf = Integer.valueOf(cy);
        if (valueOf != null) {
            boolean z = true;
            if (this.x.getAccountForMapping(MultipleAccountManager.PrimaryUserMappingType.createPrimaryMappingForProfile(valueOf.intValue())) != null) {
                z = false;
            }
            if (!z && !bundle.getBoolean(MAPAccountManager.KEY_DEREGISTERALL_AND_REGISTER_THIS_AS_PRIMARY_ACCOUNT, false)) {
                return;
            }
            bundle2.putString("com.amazon.dcp.sso.property.account.extratokens.account_profiles", Integer.toString(valueOf.intValue()));
        }
    }

    ad(ed edVar, ab abVar, em emVar, AmazonAccountManager amazonAccountManager) {
        this.mContext = edVar;
        this.x = abVar;
        this.cx = emVar;
        this.s = amazonAccountManager;
    }

    @Override // com.amazon.identity.auth.device.z
    public void a(Intent intent, String str) {
        a(intent, str, this.cx.eg());
    }

    private void a(Intent intent, String str, da daVar) {
        io.a("Sending broadcast %s to user %s", intent.getAction(), Integer.toString(daVar.getUserId()));
        a(intent);
        ja.a(this.mContext, intent, str, daVar);
    }

    private void a(Intent intent) {
        String action = intent.getAction();
        if ("com.amazon.dcp.sso.action.account.added".equals(action) || "com.amazon.dcp.sso.action.secondary.account.added".equals(action) || "com.amazon.dcp.sso.action.account.removed".equals(action) || "com.amazon.dcp.sso.action.secondary.account.removed".equals(action) || "com.amazon.identity.auth.account.added.on.device".equals(action) || "com.amazon.identity.auth.account.removed.on.device".equals(action) || "com.amazon.dcp.sso.action.AmazonAccountPropertyService.property.changed".equals(action) || CustomerAttributeStore.COR_PFM_CHANGED_INTENT_ACTION.equals(action)) {
            String str = TAG;
            "Using foreground priority for ".concat(String.valueOf(action));
            io.dm(str);
            intent.addFlags(268435456);
        }
    }

    @Override // com.amazon.identity.auth.device.z
    public MultipleAccountManager.AccountMappingType[] a(String str, int i) {
        return new MultipleAccountManager.AccountMappingType[]{new MultipleAccountManager.PackageMappingType(str), MultipleAccountManager.PrimaryUserMappingType.createPrimaryMappingForProfile(i)};
    }

    private boolean a(String str, da daVar) {
        String str2 = TAG;
        String.format("Directed-id to fire broadcast to : %s; User-id : %s", str, Integer.valueOf(daVar.getUserId()));
        io.dm(str2);
        if (str == null) {
            io.dm(TAG);
            return true;
        } else if (!this.x.doesAccountHaveMapping(str, MultipleAccountManager.PrimaryUserMappingType.createPrimaryMappingForProfile(daVar.getUserId()))) {
            return !this.x.Q(str);
        } else {
            io.dm(TAG);
            return true;
        }
    }

    @Override // com.amazon.identity.auth.device.z
    public void a(String str, Set<Integer> set, Intent intent, String str2) {
        da eh = this.cx.eh();
        if (eh == null) {
            io.e(TAG, "Can not send broadcast if current AndroidUser is null");
            return;
        }
        HashSet hashSet = new HashSet(set);
        Integer valueOf = Integer.valueOf(eh.getUserId());
        if (!hashSet.contains(valueOf)) {
            return;
        }
        a(intent, str2, eh);
        hashSet.remove(valueOf);
    }
}
