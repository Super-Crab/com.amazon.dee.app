package com.google.android.gms.common.api.internal;

import android.app.Activity;
import androidx.annotation.MainThread;
import androidx.annotation.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public final class zaa extends ActivityLifecycleObserver {
    private final WeakReference<C0090zaa> zacl;

    public zaa(Activity activity) {
        this(C0090zaa.zaa(activity));
    }

    @Override // com.google.android.gms.common.api.internal.ActivityLifecycleObserver
    public final ActivityLifecycleObserver onStopCallOnce(Runnable runnable) {
        C0090zaa c0090zaa = this.zacl.get();
        if (c0090zaa == null) {
            throw new IllegalStateException("The target activity has already been GC'd");
        }
        c0090zaa.zaa(runnable);
        return this;
    }

    @VisibleForTesting(otherwise = 2)
    private zaa(C0090zaa c0090zaa) {
        this.zacl = new WeakReference<>(c0090zaa);
    }

    @VisibleForTesting(otherwise = 2)
    /* renamed from: com.google.android.gms.common.api.internal.zaa$zaa  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    static class C0090zaa extends LifecycleCallback {
        private List<Runnable> zacm;

        private C0090zaa(LifecycleFragment lifecycleFragment) {
            super(lifecycleFragment);
            this.zacm = new ArrayList();
            this.mLifecycleFragment.addCallback("LifecycleObserverOnStop", this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static C0090zaa zaa(Activity activity) {
            C0090zaa c0090zaa;
            synchronized (activity) {
                LifecycleFragment fragment = LifecycleCallback.getFragment(activity);
                c0090zaa = (C0090zaa) fragment.getCallbackOrNull("LifecycleObserverOnStop", C0090zaa.class);
                if (c0090zaa == null) {
                    c0090zaa = new C0090zaa(fragment);
                }
            }
            return c0090zaa;
        }

        @Override // com.google.android.gms.common.api.internal.LifecycleCallback
        @MainThread
        public void onStop() {
            List<Runnable> list;
            synchronized (this) {
                list = this.zacm;
                this.zacm = new ArrayList();
            }
            for (Runnable runnable : list) {
                runnable.run();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final synchronized void zaa(Runnable runnable) {
            this.zacm.add(runnable);
        }
    }
}
