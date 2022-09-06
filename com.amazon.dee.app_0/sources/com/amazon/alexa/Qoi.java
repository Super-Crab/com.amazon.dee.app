package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.client.core.messages.Header;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: MessageProcessingSequencer.java */
@Singleton
/* loaded from: classes.dex */
public class Qoi {
    public final jSO BIo;
    public final AlexaClientEventBus zZm;
    public final ExecutorService zQM = ManagedExecutorFactory.newSingleThreadExecutor("dependent-messages");
    public final ExecutorService zyO = ManagedExecutorFactory.newSingleThreadExecutor("independent-messages");
    public final Map<DialogRequestIdentifier, Set<UaN>> jiA = new LinkedHashMap();

    @Inject
    public Qoi(AlexaClientEventBus alexaClientEventBus, jSO jso) {
        this.zZm = alexaClientEventBus;
        this.BIo = jso;
        alexaClientEventBus.zZm(this);
    }

    public final void BIo(DialogRequestIdentifier dialogRequestIdentifier) {
        synchronized (this.jiA) {
            Set<UaN> remove = this.jiA.remove(dialogRequestIdentifier);
            if (remove == null) {
                return;
            }
            zZm(remove.iterator());
        }
    }

    @Subscribe
    public synchronized void on(BJt bJt) {
        boolean z;
        Message message = ((mob) bJt).BIo;
        Header header = message.getHeader();
        if (!AvsApiConstants.InteractionModel.zZm.equals(header.getNamespace()) || !AvsApiConstants.InteractionModel.Directives.NewDialogRequest.zZm.equals(header.getName())) {
            z = false;
        } else {
            DialogRequestIdentifier dialogRequestIdentifier = ((xfe) message.getPayload()).zZm;
            if (dialogRequestIdentifier != null && !DialogRequestIdentifier.NONE.equals(dialogRequestIdentifier)) {
                this.BIo.zZm(dialogRequestIdentifier);
            }
            z = true;
        }
        if (z) {
            return;
        }
        if (message.hasDialogRequestIdentifier()) {
            zZm(message);
        } else {
            this.zyO.execute(zZm(message, new nEu(this.zZm, this, null)));
        }
    }

    public synchronized void zZm(DialogRequestIdentifier dialogRequestIdentifier) {
        if (this.BIo.BIo(dialogRequestIdentifier)) {
            this.BIo.zZm();
        }
        BIo(dialogRequestIdentifier);
    }

    public final void zZm() {
        synchronized (this.jiA) {
            Iterator<Set<UaN>> it2 = this.jiA.values().iterator();
            if (it2.hasNext()) {
                Set<UaN> next = it2.next();
                while (it2.hasNext()) {
                    zZm(it2.next().iterator());
                }
                zZm(next.iterator());
            }
            this.jiA.clear();
        }
    }

    public final void zZm(Iterator<UaN> it2) {
        if (it2.hasNext()) {
            UaN next = it2.next();
            it2.remove();
            while (it2.hasNext()) {
                it2.next().cancel(false);
                it2.remove();
            }
            next.cancel(false);
        }
    }

    @Subscribe
    public synchronized void on(Obt obt) {
        zZm();
    }

    @Subscribe
    public synchronized void on(AbstractC0238xdr abstractC0238xdr) {
        this.BIo.zZm();
        zZm();
    }

    public final void zZm(Message message) {
        if (message.hasDialogRequestIdentifier()) {
            DialogRequestIdentifier dialogRequestIdentifier = message.getDialogRequestIdentifier();
            if (!this.BIo.BIo(dialogRequestIdentifier)) {
                return;
            }
            UaN zZm = zZm(message, new nEu(this.zZm, this, dialogRequestIdentifier));
            this.zQM.submit(zZm);
            synchronized (this.jiA) {
                Set<UaN> set = this.jiA.get(dialogRequestIdentifier);
                if (set == null) {
                    set = new LinkedHashSet<>();
                }
                set.add(zZm);
                this.jiA.put(dialogRequestIdentifier, set);
            }
            return;
        }
        throw new IllegalArgumentException("Message lacks a dialog request id");
    }

    public final UaN zZm(Message message, nEu neu) {
        return new UaN(this.zZm, neu, message, neu);
    }
}
