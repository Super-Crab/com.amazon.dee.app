package org.apache.commons.io.filefilter;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.Serializable;
/* loaded from: classes4.dex */
public class SizeFileFilter extends AbstractFileFilter implements Serializable {
    private final boolean acceptLarger;
    private final long size;

    public SizeFileFilter(long j) {
        this(j, true);
    }

    @Override // org.apache.commons.io.filefilter.AbstractFileFilter, org.apache.commons.io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        boolean z = file.length() < this.size;
        return this.acceptLarger ? !z : z;
    }

    @Override // org.apache.commons.io.filefilter.AbstractFileFilter
    public String toString() {
        String str = this.acceptLarger ? ">=" : Config.Compare.LESS_THAN;
        StringBuilder sb = new StringBuilder();
        GeneratedOutlineSupport1.outline180(sb, super.toString(), "(", str);
        return GeneratedOutlineSupport1.outline87(sb, this.size, ")");
    }

    public SizeFileFilter(long j, boolean z) {
        if (j >= 0) {
            this.size = j;
            this.acceptLarger = z;
            return;
        }
        throw new IllegalArgumentException("The size must be non-negative");
    }
}
