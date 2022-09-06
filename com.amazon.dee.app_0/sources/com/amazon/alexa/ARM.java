package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.Kqq;
import com.amazon.alexa.api.AlexaMetricsName;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.utils.validation.Preconditions;
import com.google.auto.value.AutoValue;
import java.util.Map;
/* compiled from: TextInteractionEvent.java */
/* loaded from: classes.dex */
public abstract class ARM extends Kqq.zZm {

    /* compiled from: TextInteractionEvent.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class BIo extends ARM {
        public static BIo zZm(@Nullable String str, DialogRequestIdentifier dialogRequestIdentifier, Pmp pmp, @Nullable pRk prk, @Nullable Map<String, String> map, long j) {
            AlexaMetricsName zZm;
            if (prk != null) {
                prk.zZm(true);
            }
            int ordinal = pmp.ordinal();
            if (ordinal == 0) {
                Preconditions.notNull(prk, "Abandon result should have a reason");
                zZm = prk.zZm();
            } else if (ordinal == 2) {
                Preconditions.notNull(prk, "Failure result should have a reason");
                zZm = prk.zZm();
            } else if (ordinal == 3) {
                zZm = AlexaMetricsName.TextInteraction.SUCCESS;
            } else {
                throw new IllegalArgumentException("Unknown result type " + pmp);
            }
            if (map != null) {
                String name = ClG.STATUS_CODE.name();
                if (map.containsKey(name)) {
                    zZm = zZm.appendToAlexaMetricsName(map.get(name));
                }
            }
            return new uBu(zZm.appendWith(str), str, dialogRequestIdentifier, map, j);
        }
    }

    /* compiled from: TextInteractionEvent.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class zZm extends ARM {
        public static zZm zZm(@Nullable String str, String str2) {
            return new zaQ(AlexaMetricsName.TextInteraction.ATTEMPT.appendWith(str), str, DialogRequestIdentifier.NONE, str2);
        }
    }
}
