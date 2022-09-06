package com.amazon.identity.auth.device;

import android.content.Intent;
import android.os.Bundle;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import java.util.Set;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class v implements z {
    private static final String TAG = "com.amazon.identity.auth.device.v";
    private final ed o;
    private final ab x;

    public v(ed edVar) {
        this(edVar, ab.g(edVar));
    }

    @Override // com.amazon.identity.auth.device.z
    public String A() {
        return this.x.getAccountForMapping(new MultipleAccountManager.SessionUserMappingType(), new MultipleAccountManager.PrimaryUserMappingType());
    }

    @Override // com.amazon.identity.auth.device.z
    public boolean N(String str) {
        boolean Q = this.x.Q(str);
        io.i(TAG, "deregisterAllAccountsOnAccountRemoval returns: ".concat(String.valueOf(Q)));
        return Q;
    }

    @Override // com.amazon.identity.auth.device.z
    public void a(String str, Intent intent, String str2) {
        ja.a(this.o, intent, str2, null);
    }

    @Override // com.amazon.identity.auth.device.z
    public void b(String str, Intent intent, String str2) {
        a(str, intent, str2);
    }

    @Override // com.amazon.identity.auth.device.z
    public void c(Bundle bundle, Bundle bundle2) {
    }

    v(ed edVar, ab abVar) {
        this.o = edVar;
        this.x = abVar;
    }

    @Override // com.amazon.identity.auth.device.z
    public MultipleAccountManager.AccountMappingType[] a(String str, int i) {
        return new MultipleAccountManager.AccountMappingType[]{MultipleAccountManager.SessionPackageMappingType.createSessionPackageMappingInstance(this.o), new MultipleAccountManager.PackageMappingType(str), new MultipleAccountManager.SessionUserMappingType(), MultipleAccountManager.PrimaryUserMappingType.createPrimaryMappingForProfile(i)};
    }

    @Override // com.amazon.identity.auth.device.z
    public void a(String str, Set<Integer> set, Intent intent, String str2) {
        a(str, intent, str2);
    }

    @Override // com.amazon.identity.auth.device.z
    public void a(Intent intent, String str) {
        io.dm(TAG);
    }
}
