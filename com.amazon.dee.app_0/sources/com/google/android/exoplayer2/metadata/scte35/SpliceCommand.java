package com.google.android.exoplayer2.metadata.scte35;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.metadata.Metadata;
/* loaded from: classes2.dex */
public abstract class SpliceCommand implements Metadata.Entry {
    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SCTE-35 splice command: type=");
        outline107.append(getClass().getSimpleName());
        return outline107.toString();
    }
}
