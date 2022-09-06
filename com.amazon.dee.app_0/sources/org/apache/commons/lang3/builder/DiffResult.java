package org.apache.commons.lang3.builder;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes4.dex */
public class DiffResult implements Iterable<Diff<?>> {
    private static final String DIFFERS_STRING = "differs from";
    public static final String OBJECTS_SAME_STRING = "";
    private final List<Diff<?>> diffs;
    private final Object lhs;
    private final Object rhs;
    private final ToStringStyle style;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DiffResult(Object obj, Object obj2, List<Diff<?>> list, ToStringStyle toStringStyle) {
        if (obj != null) {
            if (obj2 == null) {
                throw new IllegalArgumentException("Right hand object cannot be null");
            }
            if (list != null) {
                this.diffs = list;
                this.lhs = obj;
                this.rhs = obj2;
                if (toStringStyle == null) {
                    this.style = ToStringStyle.DEFAULT_STYLE;
                    return;
                } else {
                    this.style = toStringStyle;
                    return;
                }
            }
            throw new IllegalArgumentException("List of differences cannot be null");
        }
        throw new IllegalArgumentException("Left hand object cannot be null");
    }

    public List<Diff<?>> getDiffs() {
        return Collections.unmodifiableList(this.diffs);
    }

    public int getNumberOfDiffs() {
        return this.diffs.size();
    }

    public ToStringStyle getToStringStyle() {
        return this.style;
    }

    @Override // java.lang.Iterable
    public Iterator<Diff<?>> iterator() {
        return this.diffs.iterator();
    }

    public String toString() {
        return toString(this.style);
    }

    public String toString(ToStringStyle toStringStyle) {
        if (this.diffs.size() == 0) {
            return "";
        }
        ToStringBuilder toStringBuilder = new ToStringBuilder(this.lhs, toStringStyle);
        ToStringBuilder toStringBuilder2 = new ToStringBuilder(this.rhs, toStringStyle);
        for (Diff<?> diff : this.diffs) {
            toStringBuilder.append(diff.getFieldName(), diff.mo12815getLeft());
            toStringBuilder2.append(diff.getFieldName(), diff.mo12816getRight());
        }
        return String.format("%s %s %s", toStringBuilder.mo12838build(), DIFFERS_STRING, toStringBuilder2.mo12838build());
    }
}
