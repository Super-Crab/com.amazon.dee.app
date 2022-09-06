package com.amazon.alexa;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.utils.validation.Assertions;
import dagger.Lazy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
/* compiled from: VoiceInteractionTaskFactory.java */
@Singleton
/* loaded from: classes.dex */
public class Wiq extends SyN {
    public static final String LPk = "Wiq";

    @Inject
    @VisibleForTesting
    public Wiq(AlexaClientEventBus alexaClientEventBus, IUt iUt, @Named("voice_interaction") Lazy<PersistentStorage> lazy, TimeProvider timeProvider) {
        this.zZm = alexaClientEventBus;
        this.BIo = iUt;
        this.zyO = lazy;
        this.zQM = timeProvider;
        this.jiA = new AtomicReference<>(WSC.BIo);
        this.Qle = new HashMap();
        this.JTe = new HashMap();
    }

    @Override // com.amazon.alexa.SyN
    public LYb BIo(XWx xWx, pRk prk, long j) {
        return new SEX(this.jiA, this.zZm, this.BIo, this.zyO, this.zQM, xWx, prk, j, this.Qle, this.JTe);
    }

    @Override // com.amazon.alexa.SyN
    public LYb zZm() {
        return new DTf(this.jiA, this.zZm, this.BIo, this.zyO, this.zQM, this.Qle, this.JTe);
    }

    @Override // com.amazon.alexa.SyN
    public LYb BIo() {
        return new QAJ(this.jiA, this.zZm, this.BIo, this.zyO, this.zQM, this.Qle, this.JTe);
    }

    @Override // com.amazon.alexa.SyN
    public LYb zZm(XWx xWx, String str, String str2, long j) {
        return new APq(this.jiA, this.zZm, this.BIo, this.zyO, this.zQM, xWx, str, str2, j, this.Qle, this.JTe);
    }

    @Override // com.amazon.alexa.SyN
    public LYb zZm(XWx xWx, @Nullable DialogRequestIdentifier dialogRequestIdentifier, @Nullable String str) {
        return new C0219lts(this.jiA, this.zZm, this.BIo, this.zyO, this.zQM, xWx, dialogRequestIdentifier, str, null, this.Qle, this.JTe);
    }

    @Override // com.amazon.alexa.SyN
    public LYb zZm(XWx xWx, YOj yOj) {
        return new C0219lts(this.jiA, this.zZm, this.BIo, this.zyO, this.zQM, xWx, null, null, yOj, this.Qle, this.JTe);
    }

    @Override // com.amazon.alexa.SyN
    public LYb zZm(DialogRequestIdentifier dialogRequestIdentifier, YOj yOj) {
        XWx xWx = this.JTe.get(dialogRequestIdentifier);
        Assertions.notNull(xWx, "couldn't find the dialogTurnId for " + dialogRequestIdentifier);
        return new C0219lts(this.jiA, this.zZm, this.BIo, this.zyO, this.zQM, xWx, null, null, yOj, this.Qle, this.JTe);
    }

    @Override // com.amazon.alexa.SyN
    public LYb zZm(XWx xWx, pRk prk, long j) {
        return new oJm(this.jiA, this.zZm, this.BIo, this.zyO, this.zQM, xWx, prk, j, this.Qle, this.JTe);
    }

    @Override // com.amazon.alexa.SyN
    public LYb zZm(DialogRequestIdentifier dialogRequestIdentifier, pRk prk, long j) {
        throw new UnsupportedOperationException(C0179Pya.zZm(new StringBuilder(), LPk, " create abandon task with dialogRequestId is not supported for voice"));
    }

    @Override // com.amazon.alexa.SyN
    public LYb zZm(DialogRequestIdentifier dialogRequestIdentifier, pRk prk, @Nullable Map<String, String> map, long j) {
        return new DJs(this.jiA, this.zZm, this.BIo, this.zyO, this.zQM, dialogRequestIdentifier, prk, map, j, this.Qle, this.JTe);
    }

    @Override // com.amazon.alexa.SyN
    public LYb zZm(DialogRequestIdentifier dialogRequestIdentifier, long j) {
        return new tJF(this.jiA, this.zZm, this.BIo, this.zyO, this.zQM, dialogRequestIdentifier, j, this.Qle, this.JTe);
    }
}
