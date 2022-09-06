package com.amazon.alexa;

import com.amazon.alexa.Kqq;
import com.amazon.alexa.api.ApiCallFailure;
import com.amazon.alexa.api.ApiCallback;
import com.amazon.alexa.api.ExtendedClient;
import com.google.auto.value.AutoValue;
/* compiled from: ApiCallEvent.java */
/* loaded from: classes.dex */
public abstract class NEv extends Kqq.zZm {

    /* compiled from: ApiCallEvent.java */
    /* loaded from: classes.dex */
    public static abstract class BIo extends NEv {
        public abstract ApiCallback zQM();

        public abstract ExtendedClient zyO();
    }

    /* compiled from: ApiCallEvent.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class jiA extends NEv {
    }

    /* compiled from: ApiCallEvent.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class zQM extends NEv {
        public static zQM zZm(eOP eop) {
            return new ptH(eop);
        }
    }

    /* compiled from: ApiCallEvent.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class zZm extends NEv {
        public static zZm zZm(eOP eop, ApiCallFailure apiCallFailure) {
            return new cUA(eop, apiCallFailure);
        }
    }

    /* compiled from: ApiCallEvent.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class zyO extends Kqq.zZm {
    }

    public abstract eOP BIo();
}
