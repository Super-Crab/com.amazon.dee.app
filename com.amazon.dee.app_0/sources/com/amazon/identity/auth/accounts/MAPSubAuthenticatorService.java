package com.amazon.identity.auth.accounts;

import android.accounts.Account;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import com.amazon.dcp.sso.IAmazonAccountAuthenticator;
import com.amazon.dcp.sso.ISubAuthenticatorResponse;
import com.amazon.identity.auth.device.api.MAPInit;
import com.amazon.identity.auth.device.b;
import com.amazon.identity.auth.device.u;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class MAPSubAuthenticatorService extends Service {
    private b ck;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.ck.c.asBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        MAPInit.getInstance(this).initialize();
        this.ck = new b(this) { // from class: com.amazon.identity.auth.accounts.MAPSubAuthenticatorService.1
            private u cl;

            private synchronized u N() {
                if (this.cl == null) {
                    this.cl = u.e(MAPSubAuthenticatorService.this);
                }
                return this.cl;
            }

            @Override // com.amazon.identity.auth.device.b
            public void a(ISubAuthenticatorResponse iSubAuthenticatorResponse, Account account, String str, Bundle bundle, IAmazonAccountAuthenticator iAmazonAccountAuthenticator) {
                N().getAuthToken(iSubAuthenticatorResponse, account.type, account.name, str, bundle, iAmazonAccountAuthenticator);
            }

            @Override // com.amazon.identity.auth.device.b
            public void b(ISubAuthenticatorResponse iSubAuthenticatorResponse, Account account, String str, Bundle bundle, IAmazonAccountAuthenticator iAmazonAccountAuthenticator) {
                N().updateAuthToken(iSubAuthenticatorResponse, account.type, account.name, str, bundle, iAmazonAccountAuthenticator);
            }

            @Override // com.amazon.identity.auth.device.b
            public void a(ISubAuthenticatorResponse iSubAuthenticatorResponse, Account account) {
                N().getAccountRemovalAllowed(iSubAuthenticatorResponse, account.type, account.name);
            }
        };
    }
}
