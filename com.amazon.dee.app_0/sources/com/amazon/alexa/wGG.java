package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.ExecutorService;
import javax.inject.Inject;
import org.greenrobot.eventbus.EventBus;
/* compiled from: AsynchronousAlexaClientEventBus.java */
/* loaded from: classes.dex */
public class wGG extends AlexaClientEventBus {
    public static final String zQM = "wGG";
    public final ExecutorService zyO;

    /* compiled from: AsynchronousAlexaClientEventBus.java */
    /* loaded from: classes.dex */
    private static class BIo implements Runnable {
        public final Object BIo;
        public final EventBus zZm;

        public BIo(EventBus eventBus, Kqq kqq) {
            this.zZm = eventBus;
            this.BIo = kqq;
        }

        @Override // java.lang.Runnable
        public void run() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("post sticky ");
            outline107.append(this.BIo);
            outline107.toString();
            this.zZm.postSticky(this.BIo);
        }
    }

    /* compiled from: AsynchronousAlexaClientEventBus.java */
    /* loaded from: classes.dex */
    private static class zZm implements Runnable {
        public final Object BIo;
        public final EventBus zZm;

        public zZm(EventBus eventBus, Kqq kqq) {
            this.zZm = eventBus;
            this.BIo = kqq;
        }

        @Override // java.lang.Runnable
        public void run() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("post ");
            outline107.append(this.BIo);
            outline107.toString();
            this.zZm.post(this.BIo);
        }
    }

    @Inject
    public wGG(EventBus eventBus, gSO gso) {
        super(eventBus, gso);
        this.zyO = ManagedExecutorFactory.newSingleThreadExecutor("event-bus", ManagedExecutorFactory.Group.EVENT_BUS);
    }

    @Override // com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus
    public void BIo(Kqq kqq) {
        this.zyO.submit(new BIo(this.zZm, kqq));
    }

    @Override // com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus
    public void zZm(Kqq kqq) {
        this.zyO.submit(new zZm(this.zZm, kqq));
    }
}
