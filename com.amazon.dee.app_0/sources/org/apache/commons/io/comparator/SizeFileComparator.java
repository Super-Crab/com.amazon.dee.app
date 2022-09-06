package org.apache.commons.io.comparator;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.io.FileUtils;
/* loaded from: classes4.dex */
public class SizeFileComparator extends AbstractFileComparator implements Serializable {
    public static final Comparator<File> SIZE_COMPARATOR = new SizeFileComparator();
    public static final Comparator<File> SIZE_REVERSE = new ReverseComparator(SIZE_COMPARATOR);
    public static final Comparator<File> SIZE_SUMDIR_COMPARATOR = new SizeFileComparator(true);
    public static final Comparator<File> SIZE_SUMDIR_REVERSE = new ReverseComparator(SIZE_SUMDIR_COMPARATOR);
    private final boolean sumDirectoryContents;

    public SizeFileComparator() {
        this.sumDirectoryContents = false;
    }

    @Override // org.apache.commons.io.comparator.AbstractFileComparator
    public /* bridge */ /* synthetic */ List sort(List list) {
        return super.sort(list);
    }

    @Override // org.apache.commons.io.comparator.AbstractFileComparator
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[sumDirectoryContents=");
        return GeneratedOutlineSupport1.outline97(sb, this.sumDirectoryContents, "]");
    }

    @Override // java.util.Comparator
    public int compare(File file, File file2) {
        long length;
        long length2;
        if (file.isDirectory()) {
            length = (!this.sumDirectoryContents || !file.exists()) ? 0L : FileUtils.sizeOfDirectory(file);
        } else {
            length = file.length();
        }
        if (file2.isDirectory()) {
            length2 = (!this.sumDirectoryContents || !file2.exists()) ? 0L : FileUtils.sizeOfDirectory(file2);
        } else {
            length2 = file2.length();
        }
        int i = ((length - length2) > 0L ? 1 : ((length - length2) == 0L ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        return i > 0 ? 1 : 0;
    }

    @Override // org.apache.commons.io.comparator.AbstractFileComparator
    public /* bridge */ /* synthetic */ File[] sort(File[] fileArr) {
        return super.sort(fileArr);
    }

    public SizeFileComparator(boolean z) {
        this.sumDirectoryContents = z;
    }
}
