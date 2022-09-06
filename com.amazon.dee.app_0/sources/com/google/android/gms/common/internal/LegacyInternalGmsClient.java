package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IInterface;
import androidx.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
@Deprecated
/* loaded from: classes2.dex */
public abstract class LegacyInternalGmsClient<T extends IInterface> extends GmsClient<T> {
    private final GmsClientEventManager zags;

    public LegacyInternalGmsClient(Context context, int i, ClientSettings clientSettings, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, context.getMainLooper(), i, clientSettings);
        this.zags = new GmsClientEventManager(context.getMainLooper(), this);
        this.zags.registerConnectionCallbacks(connectionCallbacks);
        this.zags.registerConnectionFailedListener(onConnectionFailedListener);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public void checkAvailabilityAndConnect() {
        this.zags.enableCallbacks();
        super.checkAvailabilityAndConnect();
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public void disconnect() {
        this.zags.disableCallbacks();
        super.disconnect();
    }

    @Override // com.google.android.gms.common.internal.GmsClient, com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public int getMinApkVersion() {
        return super.getMinApkVersion();
    }

    public boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        return this.zags.isConnectionCallbacksRegistered(connectionCallbacks);
    }

    public boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return this.zags.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public void onConnectedLocked(@NonNull T t) {
        super.onConnectedLocked(t);
        this.zags.onConnectionSuccess(getConnectionHint());
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public void onConnectionFailed(ConnectionResult connectionResult) {
        super.onConnectionFailed(connectionResult);
        this.zags.onConnectionFailure(connectionResult);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public void onConnectionSuspended(int i) {
        super.onConnectionSuspended(i);
        this.zags.onUnintentionalDisconnection(i);
    }

    public void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.zags.registerConnectionCallbacks(connectionCallbacks);
    }

    public void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.zags.registerConnectionFailedListener(onConnectionFailedListener);
    }

    public void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.zags.unregisterConnectionCallbacks(connectionCallbacks);
    }

    public void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.zags.unregisterConnectionFailedListener(onConnectionFailedListener);
    }
}
