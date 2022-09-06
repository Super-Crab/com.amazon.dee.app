package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.TimeProvider;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
/* compiled from: AlexaMetricInteraction.java */
/* loaded from: classes.dex */
public abstract class OcX extends jDH implements NTV {
    public static final String BIo = "com.amazon.alexa.OcX";
    public final TimeProvider Qle;
    public long jiA;
    public final AlexaClientEventBus zQM;
    public final Map<kQf, nLZ> zyO = new HashMap();

    public OcX(AlexaClientEventBus alexaClientEventBus, TimeProvider timeProvider) {
        this.zQM = alexaClientEventBus;
        this.Qle = timeProvider;
    }

    @Override // com.amazon.alexa.NTV
    public void yPL(kQf kqf) {
        nLZ nlz = this.zyO.get(kqf);
        if (nlz != null) {
            nlz.zQM();
        }
    }

    @Override // com.amazon.alexa.NTV
    public void zZm(kQf kqf, long j, long j2) {
    }

    public boolean zZm(Exception exc, long j) {
        Throwable cause;
        if (j <= 0 || !(exc instanceof ExoPlaybackException)) {
            return false;
        }
        ExoPlaybackException exoPlaybackException = (ExoPlaybackException) exc;
        if (exoPlaybackException.type != 0) {
            return false;
        }
        IOException sourceException = exoPlaybackException.getSourceException();
        if (!(sourceException instanceof HttpDataSource.HttpDataSourceException) || (cause = sourceException.getCause()) == null) {
            return false;
        }
        return (cause instanceof SocketTimeoutException) || (cause instanceof UnknownHostException);
    }
}
