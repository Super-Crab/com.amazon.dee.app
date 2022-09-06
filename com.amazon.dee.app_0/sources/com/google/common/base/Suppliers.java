package com.google.common.base;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes12.dex */
public final class Suppliers {

    @VisibleForTesting
    /* loaded from: classes12.dex */
    static class ExpiringMemoizingSupplier<T> implements Supplier<T>, Serializable {
        private static final long serialVersionUID = 0;
        final Supplier<T> delegate;
        final long durationNanos;
        volatile transient long expirationNanos;
        @NullableDecl
        volatile transient T value;

        ExpiringMemoizingSupplier(Supplier<T> supplier, long j, TimeUnit timeUnit) {
            this.delegate = (Supplier) Preconditions.checkNotNull(supplier);
            this.durationNanos = timeUnit.toNanos(j);
            Preconditions.checkArgument(j > 0, "duration (%s %s) must be > 0", j, timeUnit);
        }

        @Override // com.google.common.base.Supplier
        /* renamed from: get */
        public T mo8291get() {
            long j = this.expirationNanos;
            long systemNanoTime = Platform.systemNanoTime();
            if (j == 0 || systemNanoTime - j >= 0) {
                synchronized (this) {
                    if (j == this.expirationNanos) {
                        T mo8291get = this.delegate.mo8291get();
                        this.value = mo8291get;
                        long j2 = systemNanoTime + this.durationNanos;
                        if (j2 == 0) {
                            j2 = 1;
                        }
                        this.expirationNanos = j2;
                        return mo8291get;
                    }
                }
            }
            return this.value;
        }

        public String toString() {
            String valueOf = String.valueOf(this.delegate);
            long j = this.durationNanos;
            StringBuilder sb = new StringBuilder(valueOf.length() + 62);
            sb.append("Suppliers.memoizeWithExpiration(");
            sb.append(valueOf);
            sb.append(", ");
            return GeneratedOutlineSupport1.outline87(sb, j, ", NANOS)");
        }
    }

    @VisibleForTesting
    /* loaded from: classes12.dex */
    static class MemoizingSupplier<T> implements Supplier<T>, Serializable {
        private static final long serialVersionUID = 0;
        final Supplier<T> delegate;
        volatile transient boolean initialized;
        @NullableDecl
        transient T value;

        MemoizingSupplier(Supplier<T> supplier) {
            this.delegate = (Supplier) Preconditions.checkNotNull(supplier);
        }

        @Override // com.google.common.base.Supplier
        /* renamed from: get */
        public T mo8291get() {
            if (!this.initialized) {
                synchronized (this) {
                    if (!this.initialized) {
                        T mo8291get = this.delegate.mo8291get();
                        this.value = mo8291get;
                        this.initialized = true;
                        return mo8291get;
                    }
                }
            }
            return this.value;
        }

        public String toString() {
            Object obj;
            if (this.initialized) {
                String valueOf = String.valueOf(this.value);
                obj = GeneratedOutlineSupport1.outline30(valueOf.length() + 25, "<supplier that returned ", valueOf, Config.Compare.GREATER_THAN);
            } else {
                obj = this.delegate;
            }
            String valueOf2 = String.valueOf(obj);
            return GeneratedOutlineSupport1.outline30(valueOf2.length() + 19, "Suppliers.memoize(", valueOf2, ")");
        }
    }

    @VisibleForTesting
    /* loaded from: classes12.dex */
    static class NonSerializableMemoizingSupplier<T> implements Supplier<T> {
        volatile Supplier<T> delegate;
        volatile boolean initialized;
        @NullableDecl
        T value;

        NonSerializableMemoizingSupplier(Supplier<T> supplier) {
            this.delegate = (Supplier) Preconditions.checkNotNull(supplier);
        }

        @Override // com.google.common.base.Supplier
        /* renamed from: get */
        public T mo8291get() {
            if (!this.initialized) {
                synchronized (this) {
                    if (!this.initialized) {
                        T mo8291get = this.delegate.mo8291get();
                        this.value = mo8291get;
                        this.initialized = true;
                        this.delegate = null;
                        return mo8291get;
                    }
                }
            }
            return this.value;
        }

        public String toString() {
            Object obj = this.delegate;
            if (obj == null) {
                String valueOf = String.valueOf(this.value);
                obj = GeneratedOutlineSupport1.outline30(valueOf.length() + 25, "<supplier that returned ", valueOf, Config.Compare.GREATER_THAN);
            }
            String valueOf2 = String.valueOf(obj);
            return GeneratedOutlineSupport1.outline30(valueOf2.length() + 19, "Suppliers.memoize(", valueOf2, ")");
        }
    }

    /* loaded from: classes12.dex */
    private static class SupplierComposition<F, T> implements Supplier<T>, Serializable {
        private static final long serialVersionUID = 0;
        final Function<? super F, T> function;
        final Supplier<F> supplier;

        SupplierComposition(Function<? super F, T> function, Supplier<F> supplier) {
            this.function = (Function) Preconditions.checkNotNull(function);
            this.supplier = (Supplier) Preconditions.checkNotNull(supplier);
        }

        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof SupplierComposition) {
                SupplierComposition supplierComposition = (SupplierComposition) obj;
                return this.function.equals(supplierComposition.function) && this.supplier.equals(supplierComposition.supplier);
            }
            return false;
        }

        @Override // com.google.common.base.Supplier
        /* renamed from: get */
        public T mo8291get() {
            return this.function.mo8172apply((F) this.supplier.mo8291get());
        }

        public int hashCode() {
            return Objects.hashCode(this.function, this.supplier);
        }

        public String toString() {
            String valueOf = String.valueOf(this.function);
            String valueOf2 = String.valueOf(this.supplier);
            StringBuilder outline106 = GeneratedOutlineSupport1.outline106(valueOf2.length() + valueOf.length() + 21, "Suppliers.compose(", valueOf, ", ", valueOf2);
            outline106.append(")");
            return outline106.toString();
        }
    }

    /* loaded from: classes12.dex */
    private interface SupplierFunction<T> extends Function<Supplier<T>, T> {
    }

    /* loaded from: classes12.dex */
    private enum SupplierFunctionImpl implements SupplierFunction<Object> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "Suppliers.supplierFunction()";
        }

        @Override // com.google.common.base.Function
        /* renamed from: apply */
        public Object mo8172apply(Supplier<Object> supplier) {
            return supplier.mo8291get();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class SupplierOfInstance<T> implements Supplier<T>, Serializable {
        private static final long serialVersionUID = 0;
        @NullableDecl
        final T instance;

        SupplierOfInstance(@NullableDecl T t) {
            this.instance = t;
        }

        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof SupplierOfInstance) {
                return Objects.equal(this.instance, ((SupplierOfInstance) obj).instance);
            }
            return false;
        }

        @Override // com.google.common.base.Supplier
        /* renamed from: get */
        public T mo8291get() {
            return this.instance;
        }

        public int hashCode() {
            return Objects.hashCode(this.instance);
        }

        public String toString() {
            String valueOf = String.valueOf(this.instance);
            return GeneratedOutlineSupport1.outline30(valueOf.length() + 22, "Suppliers.ofInstance(", valueOf, ")");
        }
    }

    /* loaded from: classes12.dex */
    private static class ThreadSafeSupplier<T> implements Supplier<T>, Serializable {
        private static final long serialVersionUID = 0;
        final Supplier<T> delegate;

        ThreadSafeSupplier(Supplier<T> supplier) {
            this.delegate = (Supplier) Preconditions.checkNotNull(supplier);
        }

        @Override // com.google.common.base.Supplier
        /* renamed from: get */
        public T mo8291get() {
            T mo8291get;
            synchronized (this.delegate) {
                mo8291get = this.delegate.mo8291get();
            }
            return mo8291get;
        }

        public String toString() {
            String valueOf = String.valueOf(this.delegate);
            return GeneratedOutlineSupport1.outline30(valueOf.length() + 32, "Suppliers.synchronizedSupplier(", valueOf, ")");
        }
    }

    private Suppliers() {
    }

    public static <F, T> Supplier<T> compose(Function<? super F, T> function, Supplier<F> supplier) {
        return new SupplierComposition(function, supplier);
    }

    public static <T> Supplier<T> memoize(Supplier<T> supplier) {
        if ((supplier instanceof NonSerializableMemoizingSupplier) || (supplier instanceof MemoizingSupplier)) {
            return supplier;
        }
        if (supplier instanceof Serializable) {
            return new MemoizingSupplier(supplier);
        }
        return new NonSerializableMemoizingSupplier(supplier);
    }

    public static <T> Supplier<T> memoizeWithExpiration(Supplier<T> supplier, long j, TimeUnit timeUnit) {
        return new ExpiringMemoizingSupplier(supplier, j, timeUnit);
    }

    public static <T> Supplier<T> ofInstance(@NullableDecl T t) {
        return new SupplierOfInstance(t);
    }

    public static <T> Function<Supplier<T>, T> supplierFunction() {
        return SupplierFunctionImpl.INSTANCE;
    }

    public static <T> Supplier<T> synchronizedSupplier(Supplier<T> supplier) {
        return new ThreadSafeSupplier(supplier);
    }
}
