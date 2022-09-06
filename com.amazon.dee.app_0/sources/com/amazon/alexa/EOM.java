package com.amazon.alexa;

import com.amazon.alexa.Kqq;
import com.amazon.alexa.utils.validation.Preconditions;
import com.google.auto.value.AutoValue;
/* compiled from: UpdateVoiceInteractionProgressEvent.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class EOM extends Kqq.zZm {
    public static EOM zZm(XWx xWx, YOj yOj) {
        Preconditions.notNull(xWx, "dialogTurnIdentifier is null");
        return new jcN(xWx, null, yOj);
    }
}
