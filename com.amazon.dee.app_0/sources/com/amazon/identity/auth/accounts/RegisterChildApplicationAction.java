package com.amazon.identity.auth.accounts;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.amazon.dcp.sso.ISubAuthenticatorResponse;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.bm;
import com.amazon.identity.auth.device.du;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.ej;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;
import com.amazon.identity.auth.device.framework.RemoteMAPException;
import com.amazon.identity.auth.device.hw;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.m;
import com.amazon.identity.auth.device.mb;
import com.amazon.identity.auth.device.p;
import com.amazon.identity.auth.device.u;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class RegisterChildApplicationAction {
    private static final String TAG = "com.amazon.identity.auth.accounts.RegisterChildApplicationAction";
    private final bm cy = new bm();
    private final bm cz = new bm();
    private final Context mContext;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class NotChildApplicationException extends Exception {
        private static final long serialVersionUID = 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a implements ISubAuthenticatorResponse {
        private final Callback cA;

        public a(Callback callback) {
            this.cA = callback;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            throw new UnsupportedOperationException("asBinder is not supported in SubAuthenticatorCallbackAdapter");
        }

        @Override // com.amazon.dcp.sso.ISubAuthenticatorResponse
        public void onError(int i, String str) throws RemoteException {
            m.a(this.cA, MAPError.CommonError.INTERNAL_ERROR, str, i, str, null);
        }

        @Override // com.amazon.dcp.sso.ISubAuthenticatorResponse
        public void onResult(Bundle bundle) throws RemoteException {
            m.a(this.cA, bundle);
        }
    }

    public RegisterChildApplicationAction(Context context) {
        this.mContext = ed.M(context);
    }

    private mb Y(String str) {
        Long dD;
        Long l = null;
        String str2 = null;
        for (du duVar : MAPApplicationInformationQueryer.E(this.mContext).cX()) {
            try {
                if (TextUtils.equals(duVar.getDeviceType(), str) && (dD = duVar.dD()) != null && (l == null || l.longValue() < dD.longValue())) {
                    try {
                        str2 = duVar.getPackageName();
                        l = dD;
                    } catch (RemoteMAPException e) {
                        e = e;
                        l = dD;
                        io.w(TAG, "Couldn't determine device type for " + duVar.getPackageName(), e);
                    }
                }
            } catch (RemoteMAPException e2) {
                e = e2;
            }
        }
        return new mb(l, str2);
    }

    public void c(String str, String str2, Bundle bundle, Callback callback, ej ejVar) throws NotChildApplicationException {
        if (p.a(this.mContext, str2)) {
            Callback b = this.cy.b(str2, callback);
            if (b == null) {
                io.i(TAG, String.format("Register child app request already in flight for device type %s", str2));
                return;
            }
            a aVar = new a(b);
            String string = hw.K(bundle).getString("override_dsn");
            mb Y = Y(str2);
            Long iw = Y.iw();
            String ix = Y.ix();
            String str3 = TAG;
            Object[] objArr = new Object[3];
            objArr[0] = str2;
            objArr[1] = iw != null ? Long.toString(iw.longValue()) : "None";
            objArr[2] = ix;
            io.i(str3, String.format("Registering child application with device type %s, version %s, and component id %s", objArr));
            u.a(this.mContext, str2, string, iw, ix).a(aVar, str, ejVar);
            return;
        }
        throw new NotChildApplicationException();
    }
}
