package org.apache.commons.collections4.functors;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Comparator;
import org.apache.commons.collections4.Predicate;
/* loaded from: classes4.dex */
public class ComparatorPredicate<T> implements Predicate<T>, Serializable {
    private static final long serialVersionUID = -1863209236504077399L;
    private final Comparator<T> comparator;
    private final Criterion criterion;
    private final T object;

    /* renamed from: org.apache.commons.collections4.functors.ComparatorPredicate$1  reason: invalid class name */
    /* loaded from: classes4.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$collections4$functors$ComparatorPredicate$Criterion = new int[Criterion.values().length];

        static {
            try {
                $SwitchMap$org$apache$commons$collections4$functors$ComparatorPredicate$Criterion[Criterion.EQUAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$commons$collections4$functors$ComparatorPredicate$Criterion[Criterion.GREATER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$apache$commons$collections4$functors$ComparatorPredicate$Criterion[Criterion.LESS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$apache$commons$collections4$functors$ComparatorPredicate$Criterion[Criterion.GREATER_OR_EQUAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$apache$commons$collections4$functors$ComparatorPredicate$Criterion[Criterion.LESS_OR_EQUAL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* loaded from: classes4.dex */
    public enum Criterion {
        EQUAL,
        GREATER,
        LESS,
        GREATER_OR_EQUAL,
        LESS_OR_EQUAL
    }

    public ComparatorPredicate(T t, Comparator<T> comparator, Criterion criterion) {
        this.object = t;
        this.comparator = comparator;
        this.criterion = criterion;
    }

    public static <T> Predicate<T> comparatorPredicate(T t, Comparator<T> comparator) {
        return comparatorPredicate(t, comparator, Criterion.EQUAL);
    }

    @Override // org.apache.commons.collections4.Predicate
    public boolean evaluate(T t) {
        int compare = this.comparator.compare(this.object, t);
        int ordinal = this.criterion.ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                if (ordinal != 2) {
                    if (ordinal != 3) {
                        if (ordinal != 4) {
                            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("The current criterion '");
                            outline107.append(this.criterion);
                            outline107.append("' is invalid.");
                            throw new IllegalStateException(outline107.toString());
                        } else if (compare > 0) {
                            return false;
                        }
                    } else if (compare < 0) {
                        return false;
                    }
                } else if (compare >= 0) {
                    return false;
                }
            } else if (compare <= 0) {
                return false;
            }
        } else if (compare != 0) {
            return false;
        }
        return true;
    }

    public static <T> Predicate<T> comparatorPredicate(T t, Comparator<T> comparator, Criterion criterion) {
        if (comparator != null) {
            if (criterion != null) {
                return new ComparatorPredicate(t, comparator, criterion);
            }
            throw new NullPointerException("Criterion must not be null.");
        }
        throw new NullPointerException("Comparator must not be null.");
    }
}
