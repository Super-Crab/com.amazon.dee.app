package com.amazon.alexa;

import com.amazon.alexa.Kqq;
import com.google.auto.value.AutoValue;
/* compiled from: CapabilityAgentInteractionEvent.java */
/* loaded from: classes.dex */
public abstract class pPw extends Kqq.zZm {

    /* compiled from: CapabilityAgentInteractionEvent.java */
    /* loaded from: classes.dex */
    public enum BIo {
        CONNECTION_FAILURE,
        REMOTE_EXCEPTION,
        NULL_SERVICE,
        UNKNOWN
    }

    /* compiled from: CapabilityAgentInteractionEvent.java */
    /* loaded from: classes.dex */
    public enum zQM {
        PREPROCESS,
        PROCESS,
        CANCEL
    }

    /* compiled from: CapabilityAgentInteractionEvent.java */
    /* loaded from: classes.dex */
    public static abstract class zZm extends pPw {

        /* compiled from: CapabilityAgentInteractionEvent.java */
        @AutoValue
        /* loaded from: classes.dex */
        public static abstract class BIo extends zZm {
        }

        /* compiled from: CapabilityAgentInteractionEvent.java */
        @AutoValue
        /* renamed from: com.amazon.alexa.pPw$zZm$zZm  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static abstract class AbstractC0034zZm extends zZm {
            public static AbstractC0034zZm zZm(zQM zqm, BIo bIo) {
                return new Ybj(zqm, bIo, "");
            }
        }
    }
}
