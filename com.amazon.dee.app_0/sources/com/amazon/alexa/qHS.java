package com.amazon.alexa;

import android.util.Log;
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
/* compiled from: TextInteractionTaskFactory.java */
@Singleton
/* loaded from: classes.dex */
public class qHS extends SyN {
    public static final String LPk = "qHS";

    @Inject
    @VisibleForTesting
    public qHS(AlexaClientEventBus alexaClientEventBus, IUt iUt, @Named("text_interaction") Lazy<PersistentStorage> lazy, TimeProvider timeProvider) {
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
        Log.e(LPk, "cancel task is not supported for text");
        throw new UnsupportedOperationException(C0179Pya.zZm(new StringBuilder(), LPk, " cancel task is not supported for text"));
    }

    @Override // com.amazon.alexa.SyN
    public LYb zZm() {
        return new zef(this.jiA, this.zZm, this.BIo, this.zyO, this.zQM, this.Qle, this.JTe);
    }

    @Override // com.amazon.alexa.SyN
    public LYb zZm(XWx xWx, String str, String str2, long j) {
        return new VlP(this.jiA, this.zZm, this.BIo, this.zyO, this.zQM, xWx, str, str2, j, this.Qle, this.JTe);
    }

    @Override // com.amazon.alexa.SyN
    public LYb BIo() {
        return new C0182Vlp(this.jiA, this.zZm, this.BIo, this.zyO, this.zQM, this.Qle, this.JTe);
    }

    @Override // com.amazon.alexa.SyN
    public LYb zZm(XWx xWx, @Nullable DialogRequestIdentifier dialogRequestIdentifier, @Nullable String str) {
        return new C0210jOD(this.jiA, this.zZm, this.BIo, this.zyO, this.zQM, xWx, dialogRequestIdentifier, str, null, this.Qle, this.JTe);
    }

    @Override // com.amazon.alexa.SyN
    public LYb zZm(XWx xWx, YOj yOj) {
        throw new UnsupportedOperationException(C0179Pya.zZm(new StringBuilder(), LPk, " update progress task with dialog turn id is not supported for text"));
    }

    @Override // com.amazon.alexa.SyN
    public LYb zZm(DialogRequestIdentifier dialogRequestIdentifier, YOj yOj) {
        throw new UnsupportedOperationException(C0179Pya.zZm(new StringBuilder(), LPk, " update progress task with dialog request id is not supported for text"));
    }

    @Override // com.amazon.alexa.SyN
    public LYb zZm(XWx xWx, pRk prk, long j) {
        return new hyp(this.jiA, this.zZm, this.BIo, this.zyO, this.zQM, xWx, prk, j, this.Qle, this.JTe);
    }

    @Override // com.amazon.alexa.SyN
    public LYb zZm(DialogRequestIdentifier dialogRequestIdentifier, pRk prk, long j) {
        XWx xWx = this.JTe.get(dialogRequestIdentifier);
        Assertions.notNull(xWx, "couldn't find the dialogTurnId for " + dialogRequestIdentifier);
        return new hyp(this.jiA, this.zZm, this.BIo, this.zyO, this.zQM, xWx, prk, j, this.Qle, this.JTe);
    }

    @Override // com.amazon.alexa.SyN
    public LYb zZm(DialogRequestIdentifier dialogRequestIdentifier, pRk prk, @Nullable Map<String, String> map, long j) {
        return new gJe(this.jiA, this.zZm, this.BIo, this.zyO, this.zQM, dialogRequestIdentifier, prk, map, j, this.Qle, this.JTe);
    }

    @Override // com.amazon.alexa.SyN
    public LYb zZm(DialogRequestIdentifier dialogRequestIdentifier, long j) {
        return new HGC(this.jiA, this.zZm, this.BIo, this.zyO, this.zQM, dialogRequestIdentifier, j, this.Qle, this.JTe);
    }
}
