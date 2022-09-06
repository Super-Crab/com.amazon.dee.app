package io.reactivex.rxjava3.observers;

import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.internal.functions.Functions;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.internal.util.VolatileSizeArrayList;
import io.reactivex.rxjava3.observers.BaseTestConsumer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
/* loaded from: classes3.dex */
public abstract class BaseTestConsumer<T, U extends BaseTestConsumer<T, U>> {
    protected boolean checkSubscriptionOnce;
    protected long completions;
    protected Thread lastThread;
    protected CharSequence tag;
    protected boolean timeout;
    protected final List<T> values = new VolatileSizeArrayList();
    protected final List<Throwable> errors = new VolatileSizeArrayList();
    protected final CountDownLatch done = new CountDownLatch(1);

    @NonNull
    public static String valueAndClass(@Nullable Object o) {
        if (o != null) {
            return o + " (class: " + o.getClass().getSimpleName() + ")";
        }
        return "null";
    }

    @NonNull
    public final U assertComplete() {
        long j = this.completions;
        if (j != 0) {
            if (j > 1) {
                throw fail(GeneratedOutlineSupport1.outline56("Multiple completions: ", j));
            }
            return this;
        }
        throw fail("Not completed");
    }

    @NonNull
    public final U assertEmpty() {
        return (U) mo10366assertSubscribed().assertNoValues().assertNoErrors().assertNotComplete();
    }

    @NonNull
    public final U assertError(@NonNull Throwable error) {
        return assertError(Functions.equalsWith(error), true);
    }

    @SafeVarargs
    @NonNull
    public final U assertFailure(@NonNull Class<? extends Throwable> error, @NonNull T... values) {
        return (U) mo10366assertSubscribed().assertValues(values).assertError(error).assertNotComplete();
    }

    @NonNull
    public final U assertNoErrors() {
        if (this.errors.size() == 0) {
            return this;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error(s) present: ");
        outline107.append(this.errors);
        throw fail(outline107.toString());
    }

    @NonNull
    public final U assertNoValues() {
        return assertValueCount(0);
    }

    @NonNull
    public final U assertNotComplete() {
        long j = this.completions;
        int i = (j > 1L ? 1 : (j == 1L ? 0 : -1));
        if (i != 0) {
            if (i > 0) {
                throw fail(GeneratedOutlineSupport1.outline56("Multiple completions: ", j));
            }
            return this;
        }
        throw fail("Completed!");
    }

    @SafeVarargs
    @NonNull
    public final U assertResult(@NonNull T... values) {
        return (U) mo10366assertSubscribed().assertValues(values).assertNoErrors().assertComplete();
    }

    @NonNull
    /* renamed from: assertSubscribed */
    protected abstract U mo10366assertSubscribed();

    @NonNull
    public final U assertValue(@NonNull T value) {
        if (this.values.size() == 1) {
            T t = this.values.get(0);
            if (Objects.equals(value, t)) {
                return this;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("expected: ");
            outline107.append(valueAndClass(value));
            outline107.append(" but was: ");
            outline107.append(valueAndClass(t));
            throw fail(outline107.toString());
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("expected: ");
        outline1072.append(valueAndClass(value));
        outline1072.append(" but was: ");
        outline1072.append(this.values);
        throw fail(outline1072.toString());
    }

    @NonNull
    public final U assertValueAt(int index, @NonNull T value) {
        int size = this.values.size();
        if (size != 0) {
            if (index >= 0 && index < size) {
                T t = this.values.get(index);
                if (Objects.equals(value, t)) {
                    return this;
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("expected: ");
                outline107.append(valueAndClass(value));
                outline107.append(" but was: ");
                outline107.append(valueAndClass(t));
                outline107.append(" at position ");
                outline107.append(index);
                throw fail(outline107.toString());
            }
            throw fail(GeneratedOutlineSupport1.outline54("Index ", index, " is out of range [0, ", size, ")"));
        }
        throw fail("No values");
    }

    @NonNull
    public final U assertValueCount(int count) {
        int size = this.values.size();
        if (size == count) {
            return this;
        }
        throw fail(GeneratedOutlineSupport1.outline53("Value counts differ; expected: ", count, " but was: ", size));
    }

    @NonNull
    public final U assertValueSequence(@NonNull Iterable<? extends T> sequence) {
        boolean hasNext;
        boolean hasNext2;
        Iterator<T> it2 = this.values.iterator();
        Iterator<? extends T> it3 = sequence.iterator();
        int i = 0;
        while (true) {
            hasNext = it3.hasNext();
            hasNext2 = it2.hasNext();
            if (!hasNext2 || !hasNext) {
                break;
            }
            T next = it3.next();
            T next2 = it2.next();
            if (!Objects.equals(next, next2)) {
                StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Values at position ", i, " differ; expected: ");
                outline109.append(valueAndClass(next));
                outline109.append(" but was: ");
                outline109.append(valueAndClass(next2));
                throw fail(outline109.toString());
            }
            i++;
        }
        if (!hasNext2) {
            if (hasNext) {
                throw fail(GeneratedOutlineSupport1.outline52("Fewer values received than expected (", i, ")"));
            }
            return this;
        }
        throw fail(GeneratedOutlineSupport1.outline52("More values received than expected (", i, ")"));
    }

    @SafeVarargs
    @NonNull
    public final U assertValues(@NonNull T... values) {
        int size = this.values.size();
        if (size != values.length) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Value count differs; expected: ");
            outline107.append(values.length);
            outline107.append(" ");
            outline107.append(Arrays.toString(values));
            outline107.append(" but was: ");
            outline107.append(size);
            outline107.append(" ");
            outline107.append(this.values);
            throw fail(outline107.toString());
        }
        for (int i = 0; i < size; i++) {
            T t = this.values.get(i);
            T t2 = values[i];
            if (!Objects.equals(t2, t)) {
                StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Values at position ", i, " differ; expected: ");
                outline109.append(valueAndClass(t2));
                outline109.append(" but was: ");
                outline109.append(valueAndClass(t));
                throw fail(outline109.toString());
            }
        }
        return this;
    }

    @SafeVarargs
    @NonNull
    public final U assertValuesOnly(@NonNull T... values) {
        return (U) mo10366assertSubscribed().assertValues(values).assertNoErrors().assertNotComplete();
    }

    @NonNull
    public final U await() throws InterruptedException {
        if (this.done.getCount() == 0) {
            return this;
        }
        this.done.await();
        return this;
    }

    @NonNull
    public final U awaitCount(int atLeast) {
        long currentTimeMillis = System.currentTimeMillis();
        while (true) {
            if (System.currentTimeMillis() - currentTimeMillis >= 5000) {
                this.timeout = true;
                break;
            } else if (this.done.getCount() == 0 || this.values.size() >= atLeast) {
                break;
            } else {
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return this;
    }

    @NonNull
    public final U awaitDone(long time, @NonNull TimeUnit unit) {
        try {
            if (!this.done.await(time, unit)) {
                this.timeout = true;
                dispose();
            }
            return this;
        } catch (InterruptedException e) {
            dispose();
            throw ExceptionHelper.wrapOrThrow(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void dispose();

    /* JADX INFO: Access modifiers changed from: protected */
    @NonNull
    public final AssertionError fail(@NonNull String message) {
        StringBuilder sb = new StringBuilder(message.length() + 64);
        GeneratedOutlineSupport1.outline180(sb, message, " (", "latch = ");
        sb.append(this.done.getCount());
        sb.append(", ");
        sb.append("values = ");
        sb.append(this.values.size());
        sb.append(", ");
        sb.append("errors = ");
        sb.append(this.errors.size());
        sb.append(", ");
        sb.append("completions = ");
        sb.append(this.completions);
        if (this.timeout) {
            sb.append(", timeout!");
        }
        if (isDisposed()) {
            sb.append(", disposed!");
        }
        CharSequence charSequence = this.tag;
        if (charSequence != null) {
            sb.append(", tag = ");
            sb.append(charSequence);
        }
        sb.append(')');
        AssertionError assertionError = new AssertionError(sb.toString());
        if (!this.errors.isEmpty()) {
            if (this.errors.size() == 1) {
                assertionError.initCause(this.errors.get(0));
            } else {
                assertionError.initCause(new CompositeException(this.errors));
            }
        }
        return assertionError;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract boolean isDisposed();

    @NonNull
    public final List<T> values() {
        return this.values;
    }

    @NonNull
    public final U withTag(@Nullable CharSequence tag) {
        this.tag = tag;
        return this;
    }

    @NonNull
    public final U assertError(@NonNull Class<? extends Throwable> errorClass) {
        return assertError(Functions.isInstanceOf(errorClass), true);
    }

    @NonNull
    public final U assertError(@NonNull Predicate<Throwable> errorPredicate) {
        return assertError(errorPredicate, false);
    }

    public final boolean await(long time, @NonNull TimeUnit unit) throws InterruptedException {
        boolean z = this.done.getCount() == 0 || this.done.await(time, unit);
        this.timeout = !z;
        return z;
    }

    @NonNull
    private U assertError(@NonNull Predicate<Throwable> errorPredicate, boolean exact) {
        int size = this.errors.size();
        if (size != 0) {
            boolean z = false;
            Iterator<Throwable> it2 = this.errors.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                try {
                    if (errorPredicate.test(it2.next())) {
                        z = true;
                        break;
                    }
                } catch (Throwable th) {
                    throw ExceptionHelper.wrapOrThrow(th);
                }
            }
            if (!z) {
                if (exact) {
                    throw fail("Error not present");
                }
                throw fail("No error(s) passed the predicate");
            } else if (size == 1) {
                return this;
            } else {
                if (exact) {
                    throw fail("Error present but other errors as well");
                }
                throw fail("One error passed the predicate but other errors are present as well");
            }
        }
        throw fail("No errors");
    }

    @NonNull
    public final U assertValue(@NonNull Predicate<T> valuePredicate) {
        assertValueAt(0, (Predicate) valuePredicate);
        if (this.values.size() <= 1) {
            return this;
        }
        throw fail("The first value passed the predicate but this consumer received more than one value");
    }

    @NonNull
    public final U assertValueAt(int index, @NonNull Predicate<T> valuePredicate) {
        int size = this.values.size();
        if (size != 0) {
            if (index >= 0 && index < size) {
                T t = this.values.get(index);
                try {
                    if (valuePredicate.test(t)) {
                        return this;
                    }
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Value ");
                    outline107.append(valueAndClass(t));
                    outline107.append(" at position ");
                    outline107.append(index);
                    outline107.append(" did not pass the predicate");
                    throw fail(outline107.toString());
                } catch (Throwable th) {
                    throw ExceptionHelper.wrapOrThrow(th);
                }
            }
            throw fail(GeneratedOutlineSupport1.outline54("Index ", index, " is out of range [0, ", size, ")"));
        }
        throw fail("No values");
    }
}
