package com.amazon.clouddrive.utils;
/* loaded from: classes11.dex */
public abstract class Optional<T> {
    private static final Optional NONE = new None();
    private final boolean mHasValue;

    /* loaded from: classes11.dex */
    private static final class None extends Optional {
        @Override // com.amazon.clouddrive.utils.Optional
        public Object get() {
            return null;
        }

        private None() {
            super(false);
        }
    }

    /* loaded from: classes11.dex */
    private static final class Some<V> extends Optional<V> {
        private final V item;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Some.class != obj.getClass()) {
                return false;
            }
            V v = this.item;
            V v2 = ((Some) obj).item;
            if (v != null) {
                if (v.equals(v2)) {
                    return true;
                }
            } else if (v2 == null) {
                return true;
            }
            return false;
        }

        @Override // com.amazon.clouddrive.utils.Optional
        public V get() {
            return this.item;
        }

        public int hashCode() {
            V v = this.item;
            if (v != null) {
                return v.hashCode();
            }
            return 0;
        }

        private Some(V v) {
            super(true);
            this.item = v;
        }
    }

    Optional(boolean z) {
        this.mHasValue = z;
    }

    public static <V> Optional<V> absent() {
        return NONE;
    }

    public static <V> Optional<V> fromNullable(V v) {
        if (v == null) {
            return absent();
        }
        return new Some(v);
    }

    public static <V> Optional<V> of(V v) {
        return new Some(v);
    }

    public abstract T get();

    public final boolean isPresent() {
        return this.mHasValue;
    }
}
