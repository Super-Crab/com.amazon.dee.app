package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.Kqq;
import com.amazon.alexa.api.AlexaMetricsName;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.utils.validation.Preconditions;
import com.google.auto.value.AutoValue;
import java.util.Map;
/* compiled from: VoiceInteractionEvent.java */
/* loaded from: classes.dex */
public abstract class BaP extends Kqq.zZm {

    /* compiled from: VoiceInteractionEvent.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class BIo extends BaP {
    }

    /* compiled from: VoiceInteractionEvent.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class zQM extends BaP {
        public static zQM zZm(@Nullable String str, DialogRequestIdentifier dialogRequestIdentifier, Pmp pmp, @Nullable pRk prk, @Nullable Map<String, String> map, long j) {
            AlexaMetricsName zZm;
            if (prk != null) {
                prk.zZm(false);
            }
            int ordinal = pmp.ordinal();
            if (ordinal == 0) {
                Preconditions.notNull(prk, "Abandon result should have a reason");
                zZm = prk.zZm();
            } else if (ordinal == 1) {
                Preconditions.notNull(prk, "Cancel result should have a reason");
                zZm = prk.zZm();
            } else if (ordinal == 2) {
                Preconditions.notNull(prk, "Failure result should have a reason");
                zZm = prk.zZm();
            } else if (ordinal == 3) {
                zZm = AlexaMetricsName.VoiceInteraction.SUCCESS;
            } else {
                throw new IllegalArgumentException("Unknown result type " + pmp);
            }
            if (map != null) {
                String name = ClG.STATUS_CODE.name();
                if (map.containsKey(name)) {
                    zZm = zZm.appendToAlexaMetricsName(map.get(name));
                }
            }
            return new mqC(zZm.appendWith(str), str, dialogRequestIdentifier, map, j);
        }
    }

    /* compiled from: VoiceInteractionEvent.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class zZm extends BaP {
        public static zZm zZm(@Nullable String str, DialogRequestIdentifier dialogRequestIdentifier, String str2) {
            return new Qbg(AlexaMetricsName.VoiceInteraction.ATTEMPT.appendWith(str), str, dialogRequestIdentifier, str2);
        }
    }

    public abstract DialogRequestIdentifier BIo();

    public abstract String zQM();

    public abstract String zyO();
}
