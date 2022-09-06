package com.amazon.alexa.auth.map;

import android.os.Process;
import android.os.RemoteException;
import com.amazon.alexa.auth.map.AuthorizationServiceProxy;
/* loaded from: classes6.dex */
class MAPAuthorizationServiceProxy extends AuthorizationServiceProxy.Stub {
    private final MAPAccountManagerWrapper accountManagerWrapper;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MAPAuthorizationServiceProxy(MAPAccountManagerWrapper mAPAccountManagerWrapper) {
        this.accountManagerWrapper = mAPAccountManagerWrapper;
    }

    @Override // com.amazon.alexa.auth.map.AuthorizationServiceProxy
    public String getDirectedID() throws RemoteException {
        return this.accountManagerWrapper.getDirectedID();
    }

    @Override // com.amazon.alexa.auth.map.AuthorizationServiceProxy
    public String getMarketplace() throws RemoteException {
        return this.accountManagerWrapper.getMarketplace();
    }

    @Override // com.amazon.alexa.auth.map.AuthorizationServiceProxy
    public String getToken() throws RemoteException {
        return this.accountManagerWrapper.getToken();
    }

    @Override // com.amazon.alexa.auth.map.AuthorizationServiceProxy
    public boolean isLoggedIn() throws RemoteException {
        return this.accountManagerWrapper.isLoggedIn();
    }

    @Override // com.amazon.alexa.auth.map.AuthorizationServiceProxy
    public void killService() throws RemoteException {
        Process.killProcess(Process.myPid());
    }
}
