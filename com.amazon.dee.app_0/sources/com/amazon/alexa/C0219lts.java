package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.utils.validation.Preconditions;
import dagger.Lazy;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: UpdateVoiceInteractionTask.java */
/* renamed from: com.amazon.alexa.lts  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0219lts extends QIr {
    public static final String LPk = "lts";
    @Nullable
    public final DialogRequestIdentifier Mlj;
    @Nullable
    public final YOj lOf;
    public final XWx yPL;
    @Nullable
    public final String zzR;

    public C0219lts(AtomicReference<WSC> atomicReference, AlexaClientEventBus alexaClientEventBus, IUt iUt, Lazy<PersistentStorage> lazy, TimeProvider timeProvider, XWx xWx, @Nullable DialogRequestIdentifier dialogRequestIdentifier, @Nullable String str, @Nullable YOj yOj, Map<XWx, WSC> map, Map<DialogRequestIdentifier, XWx> map2) {
        super(atomicReference, alexaClientEventBus, iUt, lazy, timeProvider, map, map2);
        Preconditions.notNull(xWx, "Dialog turn identifier cannot be null");
        this.yPL = xWx;
        this.Mlj = dialogRequestIdentifier;
        this.zzR = str;
        this.lOf = yOj;
    }

    @Override // com.amazon.alexa.LYb
    public String BIo() {
        return LPk;
    }

    @Override // java.lang.Runnable
    public void run() {
        WSC zZm = zZm();
        if (!zQM() || ((nFo) zZm).zQM != this.yPL) {
            return;
        }
        DialogRequestIdentifier dialogRequestIdentifier = this.Mlj;
        if (dialogRequestIdentifier != null) {
            zZm = WSC.zZm(zZm, dialogRequestIdentifier);
            if (!zZm(this.Mlj, this.yPL)) {
                Log.w(LPk, String.format("Attempted to update VoiceInteraction %s that does not exist", this.yPL));
            }
        }
        String str = this.zzR;
        if (str != null) {
            zZm = WSC.zZm(zZm, str);
        }
        YOj yOj = this.lOf;
        if (yOj != null) {
            zZm = WSC.zZm(zZm, yOj);
        }
        BIo(zZm);
    }
}
