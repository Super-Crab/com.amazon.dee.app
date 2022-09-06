package com.google.common.base;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.ForOverride;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.io.Serializable;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes12.dex */
public abstract class Converter<A, B> implements Function<A, B> {
    private final boolean handleNullAutomatically;
    @RetainedWith
    @NullableDecl
    @LazyInit
    private transient Converter<B, A> reverse;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static final class ConverterComposition<A, B, C> extends Converter<A, C> implements Serializable {
        private static final long serialVersionUID = 0;
        final Converter<A, B> first;
        final Converter<B, C> second;

        ConverterComposition(Converter<A, B> converter, Converter<B, C> converter2) {
            this.first = converter;
            this.second = converter2;
        }

        @Override // com.google.common.base.Converter
        @NullableDecl
        A correctedDoBackward(@NullableDecl C c) {
            return this.first.correctedDoBackward(this.second.correctedDoBackward(c));
        }

        @Override // com.google.common.base.Converter
        @NullableDecl
        C correctedDoForward(@NullableDecl A a) {
            return this.second.correctedDoForward(this.first.correctedDoForward(a));
        }

        @Override // com.google.common.base.Converter
        protected A doBackward(C c) {
            throw new AssertionError();
        }

        @Override // com.google.common.base.Converter
        protected C doForward(A a) {
            throw new AssertionError();
        }

        @Override // com.google.common.base.Converter, com.google.common.base.Function
        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof ConverterComposition) {
                ConverterComposition converterComposition = (ConverterComposition) obj;
                return this.first.equals(converterComposition.first) && this.second.equals(converterComposition.second);
            }
            return false;
        }

        public int hashCode() {
            return this.second.hashCode() + (this.first.hashCode() * 31);
        }

        public String toString() {
            String valueOf = String.valueOf(this.first);
            String valueOf2 = String.valueOf(this.second);
            return GeneratedOutlineSupport1.outline31(valueOf2.length() + valueOf.length() + 10, valueOf, ".andThen(", valueOf2, ")");
        }
    }

    /* loaded from: classes12.dex */
    private static final class FunctionBasedConverter<A, B> extends Converter<A, B> implements Serializable {
        private final Function<? super B, ? extends A> backwardFunction;
        private final Function<? super A, ? extends B> forwardFunction;

        @Override // com.google.common.base.Converter
        protected A doBackward(B b) {
            return this.backwardFunction.mo8172apply(b);
        }

        @Override // com.google.common.base.Converter
        protected B doForward(A a) {
            return this.forwardFunction.mo8172apply(a);
        }

        @Override // com.google.common.base.Converter, com.google.common.base.Function
        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof FunctionBasedConverter) {
                FunctionBasedConverter functionBasedConverter = (FunctionBasedConverter) obj;
                return this.forwardFunction.equals(functionBasedConverter.forwardFunction) && this.backwardFunction.equals(functionBasedConverter.backwardFunction);
            }
            return false;
        }

        public int hashCode() {
            return this.backwardFunction.hashCode() + (this.forwardFunction.hashCode() * 31);
        }

        public String toString() {
            String valueOf = String.valueOf(this.forwardFunction);
            String valueOf2 = String.valueOf(this.backwardFunction);
            StringBuilder outline106 = GeneratedOutlineSupport1.outline106(valueOf2.length() + valueOf.length() + 18, "Converter.from(", valueOf, ", ", valueOf2);
            outline106.append(")");
            return outline106.toString();
        }

        private FunctionBasedConverter(Function<? super A, ? extends B> function, Function<? super B, ? extends A> function2) {
            this.forwardFunction = (Function) Preconditions.checkNotNull(function);
            this.backwardFunction = (Function) Preconditions.checkNotNull(function2);
        }
    }

    /* loaded from: classes12.dex */
    private static final class IdentityConverter<T> extends Converter<T, T> implements Serializable {
        static final IdentityConverter<?> INSTANCE = new IdentityConverter<>();
        private static final long serialVersionUID = 0;

        private IdentityConverter() {
        }

        private Object readResolve() {
            return INSTANCE;
        }

        @Override // com.google.common.base.Converter
        <S> Converter<T, S> doAndThen(Converter<T, S> converter) {
            return (Converter) Preconditions.checkNotNull(converter, "otherConverter");
        }

        @Override // com.google.common.base.Converter
        protected T doBackward(T t) {
            return t;
        }

        @Override // com.google.common.base.Converter
        protected T doForward(T t) {
            return t;
        }

        @Override // com.google.common.base.Converter
        /* renamed from: reverse */
        public IdentityConverter<T> mo7513reverse() {
            return this;
        }

        public String toString() {
            return "Converter.identity()";
        }
    }

    /* loaded from: classes12.dex */
    private static final class ReverseConverter<A, B> extends Converter<B, A> implements Serializable {
        private static final long serialVersionUID = 0;
        final Converter<A, B> original;

        ReverseConverter(Converter<A, B> converter) {
            this.original = converter;
        }

        @Override // com.google.common.base.Converter
        @NullableDecl
        B correctedDoBackward(@NullableDecl A a) {
            return this.original.correctedDoForward(a);
        }

        @Override // com.google.common.base.Converter
        @NullableDecl
        A correctedDoForward(@NullableDecl B b) {
            return this.original.correctedDoBackward(b);
        }

        @Override // com.google.common.base.Converter
        protected B doBackward(A a) {
            throw new AssertionError();
        }

        @Override // com.google.common.base.Converter
        protected A doForward(B b) {
            throw new AssertionError();
        }

        @Override // com.google.common.base.Converter, com.google.common.base.Function
        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof ReverseConverter) {
                return this.original.equals(((ReverseConverter) obj).original);
            }
            return false;
        }

        public int hashCode() {
            return ~this.original.hashCode();
        }

        @Override // com.google.common.base.Converter
        /* renamed from: reverse */
        public Converter<A, B> mo7513reverse() {
            return this.original;
        }

        public String toString() {
            String valueOf = String.valueOf(this.original);
            return GeneratedOutlineSupport1.outline29(valueOf.length() + 10, valueOf, ".reverse()");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Converter() {
        this(true);
    }

    public static <A, B> Converter<A, B> from(Function<? super A, ? extends B> function, Function<? super B, ? extends A> function2) {
        return new FunctionBasedConverter(function, function2);
    }

    public static <T> Converter<T, T> identity() {
        return IdentityConverter.INSTANCE;
    }

    public final <C> Converter<A, C> andThen(Converter<B, C> converter) {
        return doAndThen(converter);
    }

    @Override // com.google.common.base.Function
    @CanIgnoreReturnValue
    @NullableDecl
    @Deprecated
    /* renamed from: apply */
    public final B mo8172apply(@NullableDecl A a) {
        return convert(a);
    }

    @CanIgnoreReturnValue
    @NullableDecl
    public final B convert(@NullableDecl A a) {
        return correctedDoForward(a);
    }

    @CanIgnoreReturnValue
    public Iterable<B> convertAll(final Iterable<? extends A> iterable) {
        Preconditions.checkNotNull(iterable, "fromIterable");
        return new Iterable<B>() { // from class: com.google.common.base.Converter.1
            @Override // java.lang.Iterable
            public Iterator<B> iterator() {
                return new Iterator<B>() { // from class: com.google.common.base.Converter.1.1
                    private final Iterator<? extends A> fromIterator;

                    {
                        this.fromIterator = iterable.iterator();
                    }

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return this.fromIterator.hasNext();
                    }

                    @Override // java.util.Iterator
                    public B next() {
                        return (B) Converter.this.convert(this.fromIterator.next());
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        this.fromIterator.remove();
                    }
                };
            }
        };
    }

    @NullableDecl
    A correctedDoBackward(@NullableDecl B b) {
        if (this.handleNullAutomatically) {
            if (b != null) {
                return (A) Preconditions.checkNotNull(doBackward(b));
            }
            return null;
        }
        return doBackward(b);
    }

    @NullableDecl
    B correctedDoForward(@NullableDecl A a) {
        if (this.handleNullAutomatically) {
            if (a != null) {
                return (B) Preconditions.checkNotNull(doForward(a));
            }
            return null;
        }
        return doForward(a);
    }

    <C> Converter<A, C> doAndThen(Converter<B, C> converter) {
        return new ConverterComposition(this, (Converter) Preconditions.checkNotNull(converter));
    }

    @ForOverride
    protected abstract A doBackward(B b);

    @ForOverride
    protected abstract B doForward(A a);

    @Override // com.google.common.base.Function
    public boolean equals(@NullableDecl Object obj) {
        return super.equals(obj);
    }

    @CanIgnoreReturnValue
    /* renamed from: reverse */
    public Converter<B, A> mo7513reverse() {
        Converter<B, A> converter = this.reverse;
        if (converter == null) {
            ReverseConverter reverseConverter = new ReverseConverter(this);
            this.reverse = reverseConverter;
            return reverseConverter;
        }
        return converter;
    }

    Converter(boolean z) {
        this.handleNullAutomatically = z;
    }
}
