package com.amazon.clouddrive.cdasdk;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public final class ProgressUpdate {
    private final long bytesComplete;
    private final long bytesTotal;

    public ProgressUpdate(long j, long j2) {
        this.bytesComplete = j;
        this.bytesTotal = j2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ProgressUpdate)) {
            return false;
        }
        ProgressUpdate progressUpdate = (ProgressUpdate) obj;
        return getBytesComplete() == progressUpdate.getBytesComplete() && getBytesTotal() == progressUpdate.getBytesTotal();
    }

    public long getBytesComplete() {
        return this.bytesComplete;
    }

    public long getBytesTotal() {
        return this.bytesTotal;
    }

    public int hashCode() {
        long bytesComplete = getBytesComplete();
        long bytesTotal = getBytesTotal();
        return ((((int) (bytesComplete ^ (bytesComplete >>> 32))) + 59) * 59) + ((int) ((bytesTotal >>> 32) ^ bytesTotal));
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ProgressUpdate(bytesComplete=");
        outline107.append(getBytesComplete());
        outline107.append(", bytesTotal=");
        outline107.append(getBytesTotal());
        outline107.append(")");
        return outline107.toString();
    }
}
