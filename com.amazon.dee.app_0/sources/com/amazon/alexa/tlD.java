package com.amazon.alexa;

import android.os.ConditionVariable;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
/* compiled from: MediaSessionWrapper.java */
/* loaded from: classes.dex */
public class tlD implements Runnable {
    public final /* synthetic */ ConditionVariable BIo;
    public final /* synthetic */ OIb zQM;
    public final /* synthetic */ boolean zZm;

    public tlD(OIb oIb, boolean z, ConditionVariable conditionVariable) {
        this.zQM = oIb;
        this.zZm = z;
        this.BIo = conditionVariable;
    }

    @Override // java.lang.Runnable
    public void run() {
        MediaSessionCompat mediaSessionCompat;
        MediaSessionCompat mediaSessionCompat2;
        MediaSessionCompat mediaSessionCompat3;
        MediaSessionCompat mediaSessionCompat4;
        MediaSessionCompat.Callback callback;
        OIb oIb = this.zQM;
        oIb.zyO = oIb.BIo();
        if (this.zQM.jiA()) {
            mediaSessionCompat4 = this.zQM.zyO;
            callback = this.zQM.jiA;
            mediaSessionCompat4.setCallback(callback);
        }
        if (this.zZm) {
            mediaSessionCompat3 = this.zQM.zyO;
            mediaSessionCompat3.setActive(true);
        }
        String str = OIb.zZm;
        StringBuilder zZm = C0179Pya.zZm("creating mediaSession: ");
        mediaSessionCompat = this.zQM.zyO;
        zZm.append(mediaSessionCompat);
        zZm.append(" active: ");
        mediaSessionCompat2 = this.zQM.zyO;
        zZm.append(mediaSessionCompat2.isActive());
        Log.i(str, zZm.toString());
        this.BIo.open();
    }
}
