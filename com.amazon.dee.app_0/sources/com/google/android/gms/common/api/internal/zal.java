package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes2.dex */
public abstract class zal extends LifecycleCallback implements DialogInterface.OnCancelListener {
    protected volatile boolean mStarted;
    protected final GoogleApiAvailability zacd;
    protected final AtomicReference<zam> zadf;
    private final Handler zadg;

    /* JADX INFO: Access modifiers changed from: protected */
    public zal(LifecycleFragment lifecycleFragment) {
        this(lifecycleFragment, GoogleApiAvailability.getInstance());
    }

    private static int zaa(@Nullable zam zamVar) {
        if (zamVar == null) {
            return -1;
        }
        return zamVar.zar();
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onActivityResult(int i, int i2, Intent intent) {
        zam zamVar = this.zadf.get();
        boolean z = true;
        if (i != 1) {
            if (i == 2) {
                int isGooglePlayServicesAvailable = this.zacd.isGooglePlayServicesAvailable(getActivity());
                if (isGooglePlayServicesAvailable != 0) {
                    z = false;
                }
                if (zamVar == null) {
                    return;
                }
                if (zamVar.getConnectionResult().getErrorCode() == 18 && isGooglePlayServicesAvailable == 18) {
                    return;
                }
            }
            z = false;
        } else if (i2 != -1) {
            if (i2 == 0) {
                int i3 = 13;
                if (intent != null) {
                    i3 = intent.getIntExtra("<<ResolutionFailureErrorDetail>>", 13);
                }
                zam zamVar2 = new zam(new ConnectionResult(i3, null), zaa(zamVar));
                this.zadf.set(zamVar2);
                zamVar = zamVar2;
            }
            z = false;
        }
        if (z) {
            zaq();
        } else if (zamVar == null) {
        } else {
            zaa(zamVar.getConnectionResult(), zamVar.zar());
        }
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        zaa(new ConnectionResult(13, null), zaa(this.zadf.get()));
        zaq();
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.zadf.set(bundle.getBoolean("resolving_error", false) ? new zam(new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution")), bundle.getInt("failed_client_id", -1)) : null);
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        zam zamVar = this.zadf.get();
        if (zamVar != null) {
            bundle.putBoolean("resolving_error", true);
            bundle.putInt("failed_client_id", zamVar.zar());
            bundle.putInt("failed_status", zamVar.getConnectionResult().getErrorCode());
            bundle.putParcelable("failed_resolution", zamVar.getConnectionResult().getResolution());
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStart() {
        super.onStart();
        this.mStarted = true;
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStop() {
        super.onStop();
        this.mStarted = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void zaa(ConnectionResult connectionResult, int i);

    public final void zab(ConnectionResult connectionResult, int i) {
        zam zamVar = new zam(connectionResult, i);
        if (this.zadf.compareAndSet(null, zamVar)) {
            this.zadg.post(new zan(this, zamVar));
        }
    }

    protected abstract void zao();

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zaq() {
        this.zadf.set(null);
        zao();
    }

    @VisibleForTesting
    private zal(LifecycleFragment lifecycleFragment, GoogleApiAvailability googleApiAvailability) {
        super(lifecycleFragment);
        this.zadf = new AtomicReference<>(null);
        this.zadg = new com.google.android.gms.internal.base.zap(Looper.getMainLooper());
        this.zacd = googleApiAvailability;
    }
}
