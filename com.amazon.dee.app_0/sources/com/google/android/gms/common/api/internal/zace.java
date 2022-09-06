package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.signin.SignInOptions;
import java.util.Set;
/* loaded from: classes2.dex */
public final class zace extends com.google.android.gms.signin.internal.zac implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private static Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zad, SignInOptions> zaki = com.google.android.gms.signin.zaa.zaph;
    private final Context mContext;
    private final Handler mHandler;
    private Set<Scope> mScopes;
    private final Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zad, SignInOptions> zaau;
    private ClientSettings zaet;
    private com.google.android.gms.signin.zad zagb;
    private zach zakj;

    @WorkerThread
    public zace(Context context, Handler handler, @NonNull ClientSettings clientSettings) {
        this(context, handler, clientSettings, zaki);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zac(com.google.android.gms.signin.internal.zaj zajVar) {
        ConnectionResult connectionResult = zajVar.getConnectionResult();
        if (connectionResult.isSuccess()) {
            ResolveAccountResponse zacx = zajVar.zacx();
            ConnectionResult connectionResult2 = zacx.getConnectionResult();
            if (!connectionResult2.isSuccess()) {
                String valueOf = String.valueOf(connectionResult2);
                Log.wtf("SignInCoordinator", GeneratedOutlineSupport1.outline29(valueOf.length() + 48, "Sign-in succeeded with resolve account failure: ", valueOf), new Exception());
                this.zakj.zag(connectionResult2);
                this.zagb.disconnect();
                return;
            }
            this.zakj.zaa(zacx.getAccountAccessor(), this.mScopes);
        } else {
            this.zakj.zag(connectionResult);
        }
        this.zagb.disconnect();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    @WorkerThread
    public final void onConnected(@Nullable Bundle bundle) {
        this.zagb.zaa(this);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
    @WorkerThread
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        this.zakj.zag(connectionResult);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    @WorkerThread
    public final void onConnectionSuspended(int i) {
        this.zagb.disconnect();
    }

    @WorkerThread
    public final void zaa(zach zachVar) {
        com.google.android.gms.signin.zad zadVar = this.zagb;
        if (zadVar != null) {
            zadVar.disconnect();
        }
        this.zaet.setClientSessionId(Integer.valueOf(System.identityHashCode(this)));
        Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zad, SignInOptions> abstractClientBuilder = this.zaau;
        Context context = this.mContext;
        Looper looper = this.mHandler.getLooper();
        ClientSettings clientSettings = this.zaet;
        this.zagb = abstractClientBuilder.buildClient(context, looper, clientSettings, clientSettings.getSignInOptions(), this, this);
        this.zakj = zachVar;
        Set<Scope> set = this.mScopes;
        if (set != null && !set.isEmpty()) {
            this.zagb.connect();
        } else {
            this.mHandler.post(new zacf(this));
        }
    }

    @Override // com.google.android.gms.signin.internal.zac, com.google.android.gms.signin.internal.zad
    @BinderThread
    public final void zab(com.google.android.gms.signin.internal.zaj zajVar) {
        this.mHandler.post(new zacg(this, zajVar));
    }

    public final com.google.android.gms.signin.zad zabq() {
        return this.zagb;
    }

    public final void zabs() {
        com.google.android.gms.signin.zad zadVar = this.zagb;
        if (zadVar != null) {
            zadVar.disconnect();
        }
    }

    @WorkerThread
    public zace(Context context, Handler handler, @NonNull ClientSettings clientSettings, Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zad, SignInOptions> abstractClientBuilder) {
        this.mContext = context;
        this.mHandler = handler;
        this.zaet = (ClientSettings) Preconditions.checkNotNull(clientSettings, "ClientSettings must not be null");
        this.mScopes = clientSettings.getRequiredScopes();
        this.zaau = abstractClientBuilder;
    }
}
