package com.amazon.alexa;

import com.amazon.alexa.Kqq;
import com.google.auto.value.AutoValue;
/* compiled from: RecordingEvent.java */
/* loaded from: classes.dex */
public abstract class fuM extends Kqq.zZm {

    /* compiled from: RecordingEvent.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class BIo extends fuM {
        public BIo() {
            super(null);
        }
    }

    /* compiled from: RecordingEvent.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class jiA extends fuM {
        public jiA() {
            super(null);
        }
    }

    /* compiled from: RecordingEvent.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class zQM extends fuM {
        public zQM() {
            super(null);
        }

        public static zQM zZm(zyO zyo) {
            return new VJa(zyo);
        }
    }

    /* compiled from: RecordingEvent.java */
    /* loaded from: classes.dex */
    public enum zZm {
        BUTTON_PRESS,
        WAKEWORD
    }

    /* compiled from: RecordingEvent.java */
    /* loaded from: classes.dex */
    public enum zyO {
        BUTTON_PRESS,
        STOP_CAPTURE,
        API,
        OTHER
    }

    public /* synthetic */ fuM(C0184XwD c0184XwD) {
    }
}
