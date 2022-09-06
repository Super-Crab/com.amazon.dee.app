package com.amazon.alexa;

import android.content.Context;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.PJz;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: MediaSessionWrapper.java */
/* loaded from: classes.dex */
public class OIb {
    public static final String zZm = "OIb";
    public final Context BIo;
    public HandlerThread JTe = new HandlerThread("media-session-handler-thread");
    public Handler LPk;
    public volatile boolean Qle;
    public MediaSessionCompat.Callback jiA;
    public final AlexaClientEventBus zQM;
    public MediaSessionCompat zyO;

    public OIb(Context context, AlexaClientEventBus alexaClientEventBus) {
        this.BIo = context;
        this.JTe.start();
        this.LPk = new Handler(this.JTe.getLooper());
        this.zQM = alexaClientEventBus;
        alexaClientEventBus.zZm(this);
    }

    public boolean JTe() {
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        ConditionVariable conditionVariable = new ConditionVariable();
        zZm(new RunnableC0244zij(this, atomicBoolean, conditionVariable));
        conditionVariable.block(1000L);
        return atomicBoolean.get();
    }

    public boolean LPk() {
        ConditionVariable conditionVariable = new ConditionVariable();
        zZm(new ubH(this, conditionVariable));
        return conditionVariable.block(1000L);
    }

    public boolean Qle() {
        return this.zyO != null;
    }

    public boolean jiA() {
        StringBuilder zZm2 = C0179Pya.zZm("hasCallback: ");
        zZm2.append(this.jiA != null);
        zZm2.toString();
        return this.jiA != null;
    }

    @Subscribe
    public void on(Zbv zbv) {
        if (PJz.BIo.PERSISTENT == ((ApR) zbv).BIo) {
            this.Qle = true;
            zZm(true);
        }
    }

    public void yPL() {
        if (!LPk()) {
            Log.w(zZm, "Media Session not released");
        }
        this.jiA = null;
        HandlerThread handlerThread = this.JTe;
        if (handlerThread != null) {
            handlerThread.quit();
            this.JTe = null;
        }
        this.zQM.BIo(this);
    }

    public void zQM() {
        zZm(3);
    }

    public MediaSessionCompat.Token zyO() {
        AtomicReference atomicReference = new AtomicReference();
        ConditionVariable conditionVariable = new ConditionVariable();
        zZm(new bIE(this, atomicReference, conditionVariable));
        conditionVariable.block(1000L);
        return (MediaSessionCompat.Token) atomicReference.get();
    }

    @VisibleForTesting
    public MediaSessionCompat BIo() {
        Bundle bundle = new Bundle();
        bundle.putString("sourceapp", this.BIo.getPackageName());
        StringBuilder zZm2 = C0179Pya.zZm("MediaSessionWrapper passing package ");
        zZm2.append(this.BIo.getPackageName());
        zZm2.append("to MediaSessionCompact");
        zZm2.toString();
        return new MediaSessionCompat(this.BIo, zZm, null, null, bundle);
    }

    public void zZm() {
        ConditionVariable conditionVariable = new ConditionVariable();
        zZm(new tlD(this, this.Qle, conditionVariable));
        conditionVariable.block(1000L);
    }

    @Subscribe
    public void on(fxz fxzVar) {
        this.Qle = false;
        zZm(false);
    }

    public void zZm(MediaSessionCompat.Callback callback) {
        this.jiA = callback;
        zZm(new ZMt(this, callback));
    }

    public void zZm(int i) {
        zZm(new PCi(this, i));
    }

    public void zZm(boolean z) {
        zZm(new VJr(this, z));
    }

    public void zZm(PlaybackStateCompat playbackStateCompat) {
        zZm(new VIZ(this, playbackStateCompat));
    }

    public void zZm(MediaMetadataCompat mediaMetadataCompat) {
        zZm(new CPb(this, mediaMetadataCompat));
    }

    @VisibleForTesting
    public void zZm(Runnable runnable) {
        Handler handler = this.LPk;
        if (handler != null) {
            handler.post(runnable);
        } else {
            Log.w(zZm, "The handler thread is null. Can't proceed.");
        }
    }
}
