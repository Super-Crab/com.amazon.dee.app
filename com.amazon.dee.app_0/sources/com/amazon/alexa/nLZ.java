package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.utils.TimeProvider;
/* compiled from: MetricPlayItem.java */
/* loaded from: classes.dex */
public class nLZ {
    public static final String zZm = "nLZ";
    public final kQf BIo;
    public int HvC;
    public boolean JTe;
    public long LPk;
    public long Mlj;
    public boolean Qle;
    public long dMe;
    public boolean jiA;
    public long lOf;
    public long uzr;
    public long yPL;
    public final TimeProvider zQM;
    public final long zyO;
    public long zzR;

    public nLZ(kQf kqf, long j, TimeProvider timeProvider) {
        this.BIo = kqf;
        this.zQM = timeProvider;
        this.zyO = j;
    }

    public boolean BIo() {
        return this.Mlj != 0;
    }

    public void Qle() {
        if (!this.Qle) {
            Log.e(zZm, "Must call startMeasuringTimeBetweenPlaybackStartingAndPlaybackStarted first.");
            return;
        }
        if (this.Mlj != 0) {
            return;
        }
        Log.i(zZm, "Stops measuring time between play and playback started");
        this.Mlj = zZm() - this.LPk;
    }

    public void jiA() {
        if (this.jiA) {
            Log.i(zZm, "Stops measuring buffering");
            this.jiA = false;
            this.dMe = (zZm() - this.uzr) + this.dMe;
            return;
        }
        Log.e(zZm, "Must start measuring buffering");
    }

    public void zQM() {
        if (!this.jiA) {
            Log.i(zZm, "Starts measuring buffering");
            this.jiA = true;
            this.uzr = zZm();
            this.HvC++;
            return;
        }
        Log.w(zZm, "Already measuring buffering");
    }

    @VisibleForTesting
    public long zZm() {
        return this.zQM.elapsedRealTime();
    }

    public void zyO() {
        if (this.Qle) {
            Log.w(zZm, "Already measuring time between play and playback started");
            return;
        }
        Log.i(zZm, "Starts measuring time between play and playback started");
        this.Qle = true;
        this.LPk = zZm();
    }
}
