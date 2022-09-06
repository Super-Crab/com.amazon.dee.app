package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.client.alexaservice.audio.ScaledVolumeProcessor;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.fuM;
import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: VolumeCalculatingInputStream.java */
/* loaded from: classes.dex */
public class HSA extends InputStream {
    public static final ZVp BIo = ZVp.LPCM16;
    public static final String zZm = "HSA";
    public final CallableC0205gsF JTe;
    public final Future<Void> LPk;
    public final PipedInputStream Qle;
    public final ScheduledExecutorService jiA = ManagedExecutorFactory.newSingleThreadScheduledExecutor("volume-event-sender");
    public final AlexaClientEventBus zQM;
    public final InputStream zyO;

    /* compiled from: VolumeCalculatingInputStream.java */
    /* loaded from: classes.dex */
    private class zZm implements Runnable {
        public /* synthetic */ zZm(bKF bkf) {
        }

        @Override // java.lang.Runnable
        public void run() {
            Float f = HSA.this.JTe.Qle.get();
            if (f == null || !HSA.this.zQM.zZm(uqh.class)) {
                return;
            }
            HSA.this.zQM.zyO(uqh.zZm(f.floatValue()));
        }
    }

    public HSA(InputStream inputStream, AlexaClientEventBus alexaClientEventBus, ScaledVolumeProcessor scaledVolumeProcessor) {
        this.zyO = inputStream;
        this.zQM = alexaClientEventBus;
        ExecutorService newSingleThreadExecutor = ManagedExecutorFactory.newSingleThreadExecutor("volume_calculator");
        zZm zzm = new zZm(null);
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        this.Qle = new PipedInputStream(1344000);
        try {
            this.Qle.connect(pipedOutputStream);
        } catch (IOException e) {
            Log.e(zZm, "Unable to connect input stream", e);
        }
        this.JTe = new CallableC0205gsF(this.zyO, pipedOutputStream, scaledVolumeProcessor);
        this.LPk = newSingleThreadExecutor.submit(this.JTe);
        alexaClientEventBus.zZm(this);
        this.jiA.scheduleAtFixedRate(zzm, 0L, 30L, TimeUnit.MILLISECONDS);
    }

    @Override // java.io.InputStream
    public synchronized int available() throws IOException {
        return this.Qle.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.zQM.BIo(this);
        ManagedExecutorFactory.shutdown("volume-event-sender");
        this.JTe.JTe = true;
        this.LPk.cancel(true);
        this.Qle.close();
        this.zyO.close();
    }

    @Subscribe
    public void on(fuM.jiA jia) {
        this.zQM.BIo(this);
        ManagedExecutorFactory.shutdown("volume-event-sender");
    }

    @Override // java.io.InputStream
    public synchronized int read() throws IOException {
        return this.Qle.read();
    }

    @Override // java.io.InputStream
    public synchronized int read(@NonNull byte[] bArr, int i, int i2) throws IOException {
        if (this.LPk.isDone()) {
            try {
                this.LPk.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new IOException(e);
            }
        }
        return this.Qle.read(bArr, i, i2);
    }
}
