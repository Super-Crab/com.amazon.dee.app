package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
@KeepForSdk
/* loaded from: classes2.dex */
public final class ListenerHolder<L> {
    private final zaa zajj;
    private volatile L zajk;
    private final ListenerKey<L> zajl;

    @KeepForSdk
    /* loaded from: classes2.dex */
    public static final class ListenerKey<L> {
        private final L zajk;
        private final String zajn;

        /* JADX INFO: Access modifiers changed from: package-private */
        @KeepForSdk
        public ListenerKey(L l, String str) {
            this.zajk = l;
            this.zajn = str;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ListenerKey)) {
                return false;
            }
            ListenerKey listenerKey = (ListenerKey) obj;
            return this.zajk == listenerKey.zajk && this.zajn.equals(listenerKey.zajn);
        }

        public final int hashCode() {
            return this.zajn.hashCode() + (System.identityHashCode(this.zajk) * 31);
        }
    }

    @KeepForSdk
    /* loaded from: classes2.dex */
    public interface Notifier<L> {
        @KeepForSdk
        void notifyListener(L l);

        @KeepForSdk
        void onNotifyListenerFailed();
    }

    /* loaded from: classes2.dex */
    private final class zaa extends com.google.android.gms.internal.base.zap {
        public zaa(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            boolean z = true;
            if (message.what != 1) {
                z = false;
            }
            Preconditions.checkArgument(z);
            ListenerHolder.this.notifyListenerInternal((Notifier) message.obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @KeepForSdk
    public ListenerHolder(@NonNull Looper looper, @NonNull L l, @NonNull String str) {
        this.zajj = new zaa(looper);
        this.zajk = (L) Preconditions.checkNotNull(l, "Listener must not be null");
        this.zajl = new ListenerKey<>(l, Preconditions.checkNotEmpty(str));
    }

    @KeepForSdk
    public final void clear() {
        this.zajk = null;
    }

    @NonNull
    @KeepForSdk
    public final ListenerKey<L> getListenerKey() {
        return this.zajl;
    }

    @KeepForSdk
    public final boolean hasListener() {
        return this.zajk != null;
    }

    @KeepForSdk
    public final void notifyListener(Notifier<? super L> notifier) {
        Preconditions.checkNotNull(notifier, "Notifier must not be null");
        this.zajj.sendMessage(this.zajj.obtainMessage(1, notifier));
    }

    @KeepForSdk
    final void notifyListenerInternal(Notifier<? super L> notifier) {
        Object obj = (L) this.zajk;
        if (obj == null) {
            notifier.onNotifyListenerFailed();
            return;
        }
        try {
            notifier.notifyListener(obj);
        } catch (RuntimeException e) {
            notifier.onNotifyListenerFailed();
            throw e;
        }
    }
}
