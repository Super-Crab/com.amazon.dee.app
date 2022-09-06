package com.amazon.alexa;

import com.amazon.alexa.Kqq;
import com.amazon.alexa.api.AlexaDialogExtras;
import com.amazon.alexa.api.AlexaDialogRequest;
import com.google.auto.value.AutoValue;
/* compiled from: DialogEvent.java */
/* loaded from: classes.dex */
public abstract class QYV extends Kqq.zZm {

    /* compiled from: DialogEvent.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class BIo extends QYV {
        public static BIo zZm(esV esv, AlexaDialogRequest alexaDialogRequest, AlexaDialogExtras alexaDialogExtras) {
            return new Yme(esv, alexaDialogRequest, alexaDialogExtras);
        }
    }

    /* compiled from: DialogEvent.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class JTe extends QYV {
    }

    /* compiled from: DialogEvent.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class Qle extends QYV {
        public static Qle zZm(NEe nEe) {
            return new wmF(nEe);
        }
    }

    /* compiled from: DialogEvent.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class jiA extends QYV {
    }

    /* compiled from: DialogEvent.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class zQM extends QYV {
    }

    /* compiled from: DialogEvent.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class zZm extends QYV {
        public static zZm zZm(esV esv, mqw mqwVar, AlexaDialogRequest alexaDialogRequest, String str) {
            return new lDN(esv, mqwVar, alexaDialogRequest, str);
        }
    }

    /* compiled from: DialogEvent.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class zyO extends QYV {
    }
}
