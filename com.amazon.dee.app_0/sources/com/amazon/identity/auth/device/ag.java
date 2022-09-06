package com.amazon.identity.auth.device;

import android.accounts.AccountManagerFuture;
import android.os.Bundle;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ag extends cu<Bundle> {
    public ag(AccountManagerFuture<Bundle> accountManagerFuture) {
        super(accountManagerFuture);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.identity.auth.device.cu
    /* renamed from: l */
    public Bundle b(Bundle bundle) throws MAPCallbackErrorException {
        if (!m.h(bundle)) {
            return bundle;
        }
        throw new MAPCallbackErrorException(bundle);
    }
}
