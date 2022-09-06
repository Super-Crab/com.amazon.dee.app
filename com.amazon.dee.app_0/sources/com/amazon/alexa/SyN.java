package com.amazon.alexa;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.utils.TimeProvider;
import dagger.Lazy;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: AbstractInteractionTaskFactory.java */
/* loaded from: classes.dex */
public abstract class SyN {
    @VisibleForTesting
    public IUt BIo;
    public Map<DialogRequestIdentifier, XWx> JTe;
    public Map<XWx, WSC> Qle;
    @VisibleForTesting
    public AtomicReference<WSC> jiA;
    @VisibleForTesting
    public TimeProvider zQM;
    @VisibleForTesting
    public AlexaClientEventBus zZm;
    @VisibleForTesting
    public Lazy<PersistentStorage> zyO;

    public abstract LYb BIo();

    public abstract LYb BIo(XWx xWx, pRk prk, long j);

    public abstract LYb zZm();

    public abstract LYb zZm(XWx xWx, YOj yOj);

    public abstract LYb zZm(XWx xWx, @Nullable DialogRequestIdentifier dialogRequestIdentifier, @Nullable String str);

    public abstract LYb zZm(XWx xWx, pRk prk, long j);

    public abstract LYb zZm(XWx xWx, String str, String str2, long j);

    public abstract LYb zZm(DialogRequestIdentifier dialogRequestIdentifier, long j);

    public abstract LYb zZm(DialogRequestIdentifier dialogRequestIdentifier, YOj yOj);

    public abstract LYb zZm(DialogRequestIdentifier dialogRequestIdentifier, pRk prk, long j);

    public abstract LYb zZm(DialogRequestIdentifier dialogRequestIdentifier, pRk prk, @Nullable Map<String, String> map, long j);
}
