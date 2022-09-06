package org.apache.commons.lang3.tuple;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Map;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
/* loaded from: classes4.dex */
public abstract class Pair<L, R> implements Map.Entry<L, R>, Comparable<Pair<L, R>>, Serializable {
    private static final long serialVersionUID = 4954918890077093841L;

    public static <L, R> Pair<L, R> of(L l, R r) {
        return new ImmutablePair(l, r);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return compareTo((Pair) ((Pair) obj));
    }

    @Override // java.util.Map.Entry
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return ObjectUtils.equals(getKey(), entry.getKey()) && ObjectUtils.equals(getValue(), entry.getValue());
    }

    @Override // java.util.Map.Entry
    public final L getKey() {
        return mo12815getLeft();
    }

    /* renamed from: getLeft */
    public abstract L mo12815getLeft();

    /* renamed from: getRight */
    public abstract R mo12816getRight();

    @Override // java.util.Map.Entry
    public R getValue() {
        return mo12816getRight();
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        int i = 0;
        int hashCode = getKey() == null ? 0 : getKey().hashCode();
        if (getValue() != null) {
            i = getValue().hashCode();
        }
        return hashCode ^ i;
    }

    public String toString() {
        StringBuilder outline104 = GeneratedOutlineSupport1.outline104('(');
        outline104.append(mo12815getLeft());
        outline104.append(JsonReaderKt.COMMA);
        outline104.append(mo12816getRight());
        outline104.append(')');
        return outline104.toString();
    }

    public int compareTo(Pair<L, R> pair) {
        return new CompareToBuilder().append(mo12815getLeft(), pair.mo12815getLeft()).append(mo12816getRight(), pair.mo12816getRight()).toComparison();
    }

    public String toString(String str) {
        return String.format(str, mo12815getLeft(), mo12816getRight());
    }
}
