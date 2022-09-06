package com.amazon.identity.auth.device;

import android.net.Uri;
import android.os.RemoteException;
import com.amazon.dcp.sso.IAmazonAccountAuthenticator;
import com.amazon.dcp.sso.IWebserviceCallback;
import java.util.HashMap;
import java.util.Map;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ai implements s {
    private static final String TAG = "com.amazon.identity.auth.device.ai";
    private final IAmazonAccountAuthenticator cI;

    public ai(IAmazonAccountAuthenticator iAmazonAccountAuthenticator) {
        this.cI = iAmazonAccountAuthenticator;
    }

    @Override // com.amazon.identity.auth.device.s
    public void a(String str, md mdVar, final t tVar, ej ejVar) {
        try {
            IAmazonAccountAuthenticator iAmazonAccountAuthenticator = this.cI;
            Uri parse = Uri.parse(mdVar.getUrl());
            String iB = mdVar.iB();
            int iC = mdVar.iC();
            HashMap hashMap = new HashMap(iC);
            for (int i = 0; i < iC; i++) {
                hashMap.put(mdVar.o(i), mdVar.p(i));
            }
            iAmazonAccountAuthenticator.callGetCredentialsWebservice(parse, iB, hashMap, mdVar.iD(), new IWebserviceCallback.Stub() { // from class: com.amazon.identity.auth.device.ai.1
                @Override // com.amazon.dcp.sso.IWebserviceCallback
                public void onAuthenticationFailed() throws RemoteException {
                    tVar.onAuthenticationFailed();
                }

                @Override // com.amazon.dcp.sso.IWebserviceCallback
                public void onBadResponse() throws RemoteException {
                    tVar.onBadResponse();
                }

                @Override // com.amazon.dcp.sso.IWebserviceCallback
                public void onInvalidRequest() throws RemoteException {
                    tVar.onInvalidRequest();
                }

                @Override // com.amazon.dcp.sso.IWebserviceCallback
                public void onNetworkError() throws RemoteException {
                    tVar.onNetworkError();
                }

                @Override // com.amazon.dcp.sso.IWebserviceCallback
                public void onResponseReceived(long j, Map map, byte[] bArr) throws RemoteException {
                    tVar.c(ai.a(j, map, bArr));
                }
            });
        } catch (RemoteException unused) {
            io.e(TAG, "RemoteException calling AmazonAccountAuthenticator.callGetCredentialsWebservice");
        }
    }

    static /* synthetic */ lb a(long j, Map map, byte[] bArr) {
        ld ldVar = new ld();
        me meVar = new me();
        meVar.a(j);
        for (Map.Entry entry : map.entrySet()) {
            meVar.addHeader((String) entry.getKey(), (String) entry.getValue());
        }
        ldVar.c(meVar);
        if (ldVar.hd()) {
            ldVar.b(bArr, bArr.length);
        }
        ldVar.hh();
        return ldVar.hf();
    }
}
