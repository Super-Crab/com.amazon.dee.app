package com.amazon.alexa;

import androidx.annotation.Nullable;
/* compiled from: SendRequestCallback.java */
/* loaded from: classes.dex */
public interface vHh {

    /* compiled from: SendRequestCallback.java */
    /* loaded from: classes.dex */
    public static class zZm {
        @Nullable
        public final Exception jiA;
        public final EnumC0036zZm zQM;
        @Nullable
        public final Integer zyO;
        public static final zZm zZm = new zZm(EnumC0036zZm.NO_NETWORK, null, null);
        public static final zZm BIo = new zZm(EnumC0036zZm.INVALID_AUTHORIZATION, null, null);

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: SendRequestCallback.java */
        /* renamed from: com.amazon.alexa.vHh$zZm$zZm  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public enum EnumC0036zZm {
            NO_NETWORK,
            INVALID_AUTHORIZATION,
            AVS_REQUEST_FAILED,
            IO_EXCEPTION
        }

        public zZm(EnumC0036zZm enumC0036zZm, @Nullable Integer num, @Nullable Exception exc) {
            this.zQM = enumC0036zZm;
            this.zyO = num;
            this.jiA = exc;
        }
    }
}
