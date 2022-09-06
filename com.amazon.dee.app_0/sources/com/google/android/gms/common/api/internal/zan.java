package com.google.android.gms.common.api.internal;

import android.app.Dialog;
import androidx.annotation.MainThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zan implements Runnable {
    private final zam zadj;
    final /* synthetic */ zal zadk;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zan(zal zalVar, zam zamVar) {
        this.zadk = zalVar;
        this.zadj = zamVar;
    }

    @Override // java.lang.Runnable
    @MainThread
    public final void run() {
        if (!this.zadk.mStarted) {
            return;
        }
        ConnectionResult connectionResult = this.zadj.getConnectionResult();
        if (connectionResult.hasResolution()) {
            zal zalVar = this.zadk;
            zalVar.mLifecycleFragment.startActivityForResult(GoogleApiActivity.zaa(zalVar.getActivity(), connectionResult.getResolution(), this.zadj.zar(), false), 1);
        } else if (this.zadk.zacd.isUserResolvableError(connectionResult.getErrorCode())) {
            zal zalVar2 = this.zadk;
            zalVar2.zacd.zaa(zalVar2.getActivity(), this.zadk.mLifecycleFragment, connectionResult.getErrorCode(), 2, this.zadk);
        } else if (connectionResult.getErrorCode() == 18) {
            Dialog zaa = GoogleApiAvailability.zaa(this.zadk.getActivity(), this.zadk);
            zal zalVar3 = this.zadk;
            zalVar3.zacd.zaa(zalVar3.getActivity().getApplicationContext(), new zao(this, zaa));
        } else {
            this.zadk.zaa(connectionResult, this.zadj.zar());
        }
    }
}
