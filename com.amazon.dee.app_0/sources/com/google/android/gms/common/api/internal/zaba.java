package com.google.android.gms.common.api.internal;

import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zaba implements ResultCallback<Status> {
    private final /* synthetic */ zaaw zahh;
    private final /* synthetic */ StatusPendingResult zahj;
    private final /* synthetic */ boolean zahk;
    private final /* synthetic */ GoogleApiClient zahl;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zaba(zaaw zaawVar, StatusPendingResult statusPendingResult, boolean z, GoogleApiClient googleApiClient) {
        this.zahh = zaawVar;
        this.zahj = statusPendingResult;
        this.zahk = z;
        this.zahl = googleApiClient;
    }

    @Override // com.google.android.gms.common.api.ResultCallback
    public final /* synthetic */ void onResult(@NonNull Status status) {
        Context context;
        Status status2 = status;
        context = this.zahh.mContext;
        Storage.getInstance(context).zaf();
        if (status2.isSuccess() && this.zahh.isConnected()) {
            this.zahh.reconnect();
        }
        this.zahj.setResult(status2);
        if (this.zahk) {
            this.zahl.disconnect();
        }
    }
}
