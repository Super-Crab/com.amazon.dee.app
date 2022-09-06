package org.apache.commons.io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes4.dex */
public class CompositeFileComparator extends AbstractFileComparator implements Serializable {
    private static final Comparator<?>[] NO_COMPARATORS = new Comparator[0];
    private final Comparator<File>[] delegates;

    /* JADX WARN: Type inference failed for: r4v1, types: [java.util.Comparator<java.io.File>[], java.util.Comparator<?>[]] */
    public CompositeFileComparator(Comparator<File>... comparatorArr) {
        if (comparatorArr == null) {
            this.delegates = NO_COMPARATORS;
            return;
        }
        this.delegates = new Comparator[comparatorArr.length];
        System.arraycopy(comparatorArr, 0, this.delegates, 0, comparatorArr.length);
    }

    @Override // org.apache.commons.io.comparator.AbstractFileComparator
    public /* bridge */ /* synthetic */ List sort(List list) {
        return super.sort(list);
    }

    @Override // org.apache.commons.io.comparator.AbstractFileComparator
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(JsonReaderKt.BEGIN_OBJ);
        for (int i = 0; i < this.delegates.length; i++) {
            if (i > 0) {
                sb.append(JsonReaderKt.COMMA);
            }
            sb.append(this.delegates[i]);
        }
        sb.append(JsonReaderKt.END_OBJ);
        return sb.toString();
    }

    @Override // java.util.Comparator
    public int compare(File file, File file2) {
        int i = 0;
        for (Comparator<File> comparator : this.delegates) {
            i = comparator.compare(file, file2);
            if (i != 0) {
                break;
            }
        }
        return i;
    }

    @Override // org.apache.commons.io.comparator.AbstractFileComparator
    public /* bridge */ /* synthetic */ File[] sort(File[] fileArr) {
        return super.sort(fileArr);
    }

    /* JADX WARN: Type inference failed for: r3v6, types: [java.util.Comparator<java.io.File>[], java.util.Comparator<?>[]] */
    public CompositeFileComparator(Iterable<Comparator<File>> iterable) {
        if (iterable == null) {
            this.delegates = NO_COMPARATORS;
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (Comparator<File> comparator : iterable) {
            arrayList.add(comparator);
        }
        this.delegates = (Comparator[]) arrayList.toArray(new Comparator[arrayList.size()]);
    }
}
