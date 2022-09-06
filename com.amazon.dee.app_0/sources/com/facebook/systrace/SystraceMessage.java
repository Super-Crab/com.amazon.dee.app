package com.facebook.systrace;
/* loaded from: classes2.dex */
public final class SystraceMessage {
    private static final Builder NOOP_BUILDER = new NoopBuilder();

    /* loaded from: classes2.dex */
    public static abstract class Builder {
        public abstract Builder arg(String str, double d);

        public abstract Builder arg(String str, int i);

        public abstract Builder arg(String str, long j);

        public abstract Builder arg(String str, Object obj);

        public abstract void flush();
    }

    /* loaded from: classes2.dex */
    private interface Flusher {
        void flush(StringBuilder sb);
    }

    /* loaded from: classes2.dex */
    private static class NoopBuilder extends Builder {
        private NoopBuilder() {
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String str, double d) {
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String str, int i) {
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String str, long j) {
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String str, Object obj) {
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public void flush() {
        }
    }

    public static Builder beginSection(long j, String str) {
        return NOOP_BUILDER;
    }

    public static Builder endSection(long j) {
        return NOOP_BUILDER;
    }
}
