package com.amazon.alexa;

import android.annotation.SuppressLint;
import android.location.Location;
/* compiled from: AndroidLocationComponent.java */
/* loaded from: classes.dex */
public class vZM implements Runnable {
    public final /* synthetic */ SCB zZm;

    public vZM(SCB scb) {
        this.zZm = scb;
    }

    @Override // java.lang.Runnable
    @SuppressLint({"MissingPermission"})
    public void run() {
        if (this.zZm.jiA()) {
            for (String str : this.zZm.zQM().getProviders(false)) {
                this.zZm.zQM().requestLocationUpdates(str, 60000L, 10.0f, this.zZm);
            }
            Location zZm = SCB.zZm(this.zZm);
            if (zZm == null) {
                return;
            }
            this.zZm.onLocationChanged(zZm);
        }
    }
}
