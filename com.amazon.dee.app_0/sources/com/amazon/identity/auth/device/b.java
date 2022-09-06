package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.amazon.dcp.sso.IAmazonAccountAuthenticator;
import com.amazon.dcp.sso.ISubAuthenticator;
import com.amazon.dcp.sso.ISubAuthenticatorResponse;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class b {
    private static final String TAG = "com.amazon.identity.auth.device.b";
    public a c = new a(this, (byte) 0);
    protected final Context mContext;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public class a extends ISubAuthenticator.Stub {
        private a() {
        }

        @Override // com.amazon.dcp.sso.ISubAuthenticator
        public void getAccountRemovalAllowed(ISubAuthenticatorResponse iSubAuthenticatorResponse, String str, String str2) throws RemoteException {
            b.a(b.this);
            b.this.a(iSubAuthenticatorResponse, new Account(str2, str));
        }

        @Override // com.amazon.dcp.sso.ISubAuthenticator
        public void getAuthToken(ISubAuthenticatorResponse iSubAuthenticatorResponse, String str, String str2, String str3, Bundle bundle, IAmazonAccountAuthenticator iAmazonAccountAuthenticator) {
            b.a(b.this);
            if (iSubAuthenticatorResponse == null || iAmazonAccountAuthenticator == null) {
                io.e(b.TAG, "Invalid parameter passed to getAuthToken");
            } else {
                b.this.a(iSubAuthenticatorResponse, new Account(str2, str), str3, bundle, iAmazonAccountAuthenticator);
            }
        }

        @Override // com.amazon.dcp.sso.ISubAuthenticator.Stub, android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            try {
                return super.onTransact(i, parcel, parcel2, i2);
            } catch (RuntimeException e) {
                io.e(b.TAG, "Unexpected Runtime Exception", e);
                throw e;
            }
        }

        @Override // com.amazon.dcp.sso.ISubAuthenticator
        public void updateAuthToken(ISubAuthenticatorResponse iSubAuthenticatorResponse, String str, String str2, String str3, Bundle bundle, IAmazonAccountAuthenticator iAmazonAccountAuthenticator) {
            b.a(b.this);
            if (iSubAuthenticatorResponse == null || iAmazonAccountAuthenticator == null) {
                io.e(b.TAG, "Invalid parameter passed to updateAuthTokens");
            } else {
                b.this.b(iSubAuthenticatorResponse, new Account(str2, str), str3, bundle, iAmazonAccountAuthenticator);
            }
        }

        /* synthetic */ a(b bVar, byte b) {
            this();
        }
    }

    public b(Context context) {
        this.mContext = context;
    }

    static /* synthetic */ void a(b bVar) {
        ja.aD(bVar.mContext);
    }

    public abstract void a(ISubAuthenticatorResponse iSubAuthenticatorResponse, Account account);

    public abstract void a(ISubAuthenticatorResponse iSubAuthenticatorResponse, Account account, String str, Bundle bundle, IAmazonAccountAuthenticator iAmazonAccountAuthenticator);

    public void b(ISubAuthenticatorResponse iSubAuthenticatorResponse, Account account, String str, Bundle bundle, IAmazonAccountAuthenticator iAmazonAccountAuthenticator) {
        try {
            iSubAuthenticatorResponse.onError(6, "Unsupported for token type: ".concat(String.valueOf(str)));
        } catch (RemoteException e) {
            io.e(TAG, "An unexpected remote exception occured during updateAuthTokens.", e);
        }
    }
}
