package com.amazon.identity.auth.device;

import android.accounts.AccountAuthenticatorResponse;
import android.accounts.IAccountAuthenticatorResponse;
import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPError;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class d {
    public static AccountAuthenticatorResponse b(final Callback callback) {
        IAccountAuthenticatorResponse.Stub stub = new IAccountAuthenticatorResponse.Stub() { // from class: com.amazon.identity.auth.device.d.1
            @Override // android.accounts.IAccountAuthenticatorResponse
            public void onError(int i, String str) throws RemoteException {
                m.a(Callback.this, MAPError.CommonError.INTERNAL_ERROR, str, i, str);
            }

            @Override // android.accounts.IAccountAuthenticatorResponse
            public void onRequestContinued() throws RemoteException {
            }

            @Override // android.accounts.IAccountAuthenticatorResponse
            public void onResult(Bundle bundle) throws RemoteException {
                m.a(Callback.this, bundle);
            }
        };
        Parcel obtain = Parcel.obtain();
        obtain.writeStrongBinder(stub.asBinder());
        obtain.setDataPosition(0);
        return new AccountAuthenticatorResponse(obtain);
    }
}
