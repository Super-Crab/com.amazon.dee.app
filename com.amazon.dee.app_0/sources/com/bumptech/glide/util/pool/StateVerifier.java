package com.bumptech.glide.util.pool;

import androidx.annotation.NonNull;
import com.amazon.alexa.fitness.metrics.MetricsName;
/* loaded from: classes9.dex */
public abstract class StateVerifier {
    private static final boolean DEBUG = false;

    /* loaded from: classes9.dex */
    private static class DebugStateVerifier extends StateVerifier {
        private volatile RuntimeException recycledAtStackTraceException;

        DebugStateVerifier() {
            super();
        }

        @Override // com.bumptech.glide.util.pool.StateVerifier
        void setRecycled(boolean z) {
            if (z) {
                this.recycledAtStackTraceException = new RuntimeException(MetricsName.RELEASED);
            } else {
                this.recycledAtStackTraceException = null;
            }
        }

        @Override // com.bumptech.glide.util.pool.StateVerifier
        public void throwIfRecycled() {
            if (this.recycledAtStackTraceException == null) {
                return;
            }
            throw new IllegalStateException("Already released", this.recycledAtStackTraceException);
        }
    }

    /* loaded from: classes9.dex */
    private static class DefaultStateVerifier extends StateVerifier {
        private volatile boolean isReleased;

        DefaultStateVerifier() {
            super();
        }

        @Override // com.bumptech.glide.util.pool.StateVerifier
        public void setRecycled(boolean z) {
            this.isReleased = z;
        }

        @Override // com.bumptech.glide.util.pool.StateVerifier
        public void throwIfRecycled() {
            if (!this.isReleased) {
                return;
            }
            throw new IllegalStateException("Already released");
        }
    }

    @NonNull
    public static StateVerifier newInstance() {
        return new DefaultStateVerifier();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setRecycled(boolean z);

    public abstract void throwIfRecycled();

    private StateVerifier() {
    }
}
