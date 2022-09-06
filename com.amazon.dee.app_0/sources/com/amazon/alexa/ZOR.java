package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
/* compiled from: DialogInteractionAuthority.java */
/* loaded from: classes.dex */
public abstract class ZOR {
    public ExecutorService BIo;
    public SyN zQM;
    public AlexaClientEventBus zZm;
    public Queue<LYb> zyO = new LinkedList();
    public boolean jiA = false;

    public ZOR(AlexaClientEventBus alexaClientEventBus, SyN syN, ExecutorService executorService) {
        this.zZm = alexaClientEventBus;
        this.zQM = syN;
        this.BIo = executorService;
        alexaClientEventBus.zZm(this);
    }

    public void BIo() {
        this.BIo.shutdown();
        try {
            if (this.BIo.awaitTermination(3L, TimeUnit.SECONDS)) {
                return;
            }
            this.BIo.shutdownNow();
            if (this.BIo.awaitTermination(3L, TimeUnit.SECONDS)) {
                return;
            }
            Log.e(zZm(), "DialogInteractionAuthority executor service did not terminate");
        } catch (InterruptedException unused) {
            this.BIo.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public void zQM() {
        this.zZm.BIo(this);
        this.BIo.execute(this.zQM.BIo());
        BIo();
    }

    public abstract String zZm();

    public void zZm(MyZ myZ) {
        this.BIo.execute(this.zQM.zZm());
        this.jiA = true;
        while (!this.zyO.isEmpty()) {
            this.BIo.execute(this.zyO.poll());
        }
    }

    public void zZm(LYb lYb) {
        if (!this.jiA) {
            this.zyO.add(lYb);
        } else {
            this.BIo.execute(lYb);
        }
    }
}
