package org.apache.commons.collections4.comparators;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes4.dex */
public class FixedOrderComparator<T> implements Comparator<T>, Serializable {
    private static final long serialVersionUID = 82794675842863201L;
    private final Map<T, Integer> map = new HashMap();
    private int counter = 0;
    private boolean isLocked = false;
    private UnknownObjectBehavior unknownObjectBehavior = UnknownObjectBehavior.EXCEPTION;

    /* renamed from: org.apache.commons.collections4.comparators.FixedOrderComparator$1  reason: invalid class name */
    /* loaded from: classes4.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$collections4$comparators$FixedOrderComparator$UnknownObjectBehavior = new int[UnknownObjectBehavior.values().length];

        static {
            try {
                $SwitchMap$org$apache$commons$collections4$comparators$FixedOrderComparator$UnknownObjectBehavior[UnknownObjectBehavior.BEFORE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$commons$collections4$comparators$FixedOrderComparator$UnknownObjectBehavior[UnknownObjectBehavior.AFTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$apache$commons$collections4$comparators$FixedOrderComparator$UnknownObjectBehavior[UnknownObjectBehavior.EXCEPTION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes4.dex */
    public enum UnknownObjectBehavior {
        BEFORE,
        AFTER,
        EXCEPTION
    }

    public FixedOrderComparator() {
    }

    public boolean add(T t) {
        checkLocked();
        Map<T, Integer> map = this.map;
        int i = this.counter;
        this.counter = i + 1;
        return map.put(t, Integer.valueOf(i)) == null;
    }

    public boolean addAsEqual(T t, T t2) {
        checkLocked();
        Integer num = this.map.get(t);
        if (num != null) {
            return this.map.put(t2, num) == null;
        }
        throw new IllegalArgumentException(t + " not known to " + this);
    }

    protected void checkLocked() {
        if (!isLocked()) {
            return;
        }
        throw new UnsupportedOperationException("Cannot modify a FixedOrderComparator after a comparison");
    }

    @Override // java.util.Comparator
    public int compare(T t, T t2) {
        this.isLocked = true;
        Integer num = this.map.get(t);
        Integer num2 = this.map.get(t2);
        if (num != null && num2 != null) {
            return num.compareTo(num2);
        }
        int ordinal = this.unknownObjectBehavior.ordinal();
        if (ordinal == 0) {
            if (num != null) {
                return 1;
            }
            return num2 == null ? 0 : -1;
        } else if (ordinal == 1) {
            if (num != null) {
                return -1;
            }
            return num2 == null ? 0 : 1;
        } else if (ordinal == 2) {
            if (num != null) {
                t = t2;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("Attempting to compare unknown object ", t));
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown unknownObjectBehavior: ");
            outline107.append(this.unknownObjectBehavior);
            throw new UnsupportedOperationException(outline107.toString());
        }
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !obj.getClass().equals(FixedOrderComparator.class)) {
            return false;
        }
        FixedOrderComparator fixedOrderComparator = (FixedOrderComparator) obj;
        Map<T, Integer> map = this.map;
        if (map != null ? map.equals(fixedOrderComparator.map) : fixedOrderComparator.map == null) {
            UnknownObjectBehavior unknownObjectBehavior = this.unknownObjectBehavior;
            if (unknownObjectBehavior != null) {
                UnknownObjectBehavior unknownObjectBehavior2 = fixedOrderComparator.unknownObjectBehavior;
                if (unknownObjectBehavior == unknownObjectBehavior2 && this.counter == fixedOrderComparator.counter && this.isLocked == fixedOrderComparator.isLocked && unknownObjectBehavior == unknownObjectBehavior2) {
                    return true;
                }
            } else if (fixedOrderComparator.unknownObjectBehavior == null) {
                return true;
            }
        }
        return false;
    }

    public UnknownObjectBehavior getUnknownObjectBehavior() {
        return this.unknownObjectBehavior;
    }

    public int hashCode() {
        Map<T, Integer> map = this.map;
        int i = 0;
        int hashCode = (629 + (map == null ? 0 : map.hashCode())) * 37;
        UnknownObjectBehavior unknownObjectBehavior = this.unknownObjectBehavior;
        if (unknownObjectBehavior != null) {
            i = unknownObjectBehavior.hashCode();
        }
        return ((((hashCode + i) * 37) + this.counter) * 37) + (!this.isLocked ? 1 : 0);
    }

    public boolean isLocked() {
        return this.isLocked;
    }

    public void setUnknownObjectBehavior(UnknownObjectBehavior unknownObjectBehavior) {
        checkLocked();
        if (unknownObjectBehavior != null) {
            this.unknownObjectBehavior = unknownObjectBehavior;
            return;
        }
        throw new NullPointerException("Unknown object behavior must not be null");
    }

    public FixedOrderComparator(T... tArr) {
        if (tArr != null) {
            for (T t : tArr) {
                add(t);
            }
            return;
        }
        throw new NullPointerException("The list of items must not be null");
    }

    public FixedOrderComparator(List<T> list) {
        if (list != null) {
            for (T t : list) {
                add(t);
            }
            return;
        }
        throw new NullPointerException("The list of items must not be null");
    }
}
