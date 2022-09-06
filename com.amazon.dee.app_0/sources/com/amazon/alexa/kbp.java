package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.Kqq;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.google.auto.value.AutoValue;
import java.util.Map;
/* compiled from: FinishDialogInteractionEvent.java */
/* loaded from: classes.dex */
public abstract class kbp extends Kqq.zZm {

    /* compiled from: FinishDialogInteractionEvent.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class BIo extends kbp {
    }

    /* compiled from: FinishDialogInteractionEvent.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class jiA extends kbp {
    }

    /* compiled from: FinishDialogInteractionEvent.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class zZm extends kbp {
        public static zZm zZm(XWx xWx, mMl mml) {
            return zZm(xWx, null, mml, false);
        }

        public static zZm zZm(@Nullable XWx xWx, @Nullable DialogRequestIdentifier dialogRequestIdentifier, mMl mml, boolean z) {
            return new C0192cdA(xWx, dialogRequestIdentifier, mml, z);
        }
    }

    /* compiled from: FinishDialogInteractionEvent.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class zyO extends kbp {
        public static zyO zZm(@Nullable String str, pRk prk) {
            return zZm(str, "", prk, false);
        }

        public static zyO zZm(@Nullable String str, String str2, pRk prk) {
            return zZm(str, str2, prk, false);
        }

        public static zyO zZm(@Nullable String str, String str2, pRk prk, boolean z) {
            return new WwG(str, str2, prk, z);
        }
    }

    /* compiled from: FinishDialogInteractionEvent.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class zQM extends kbp {
        public static zQM zZm(DialogRequestIdentifier dialogRequestIdentifier, bij bijVar) {
            bijVar.zZm(false);
            return new gMf(dialogRequestIdentifier, bijVar, null, false);
        }

        public static zQM zZm(DialogRequestIdentifier dialogRequestIdentifier, bij bijVar, @Nullable Map<String, String> map, boolean z) {
            bijVar.zZm(z);
            return new gMf(dialogRequestIdentifier, bijVar, map, z);
        }

        public static zQM zZm(DialogRequestIdentifier dialogRequestIdentifier, bij bijVar, boolean z) {
            bijVar.zZm(z);
            return new gMf(dialogRequestIdentifier, bijVar, null, z);
        }
    }
}
