package com.facebook.react.fabric.mounting.mountitems;

import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.fabric.mounting.MountingManager;
import com.facebook.systrace.Systrace;
/* loaded from: classes2.dex */
public class BatchMountItem implements MountItem {
    static final String TAG = "FabricBatchMountItem";
    private final int mCommitNumber;
    @NonNull
    private final MountItem[] mMountItems;
    private final int mRootTag;
    private final int mSize;

    public BatchMountItem(int i, MountItem[] mountItemArr, int i2, int i3) {
        int length = mountItemArr == null ? 0 : mountItemArr.length;
        if (i2 >= 0 && i2 <= length) {
            this.mRootTag = i;
            this.mMountItems = mountItemArr;
            this.mSize = i2;
            this.mCommitNumber = i3;
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline53("Invalid size received by parameter size: ", i2, " items.size = ", length));
    }

    private void beginMarkers(String str) {
        StringBuilder outline115 = GeneratedOutlineSupport1.outline115("FabricUIManager::", str, " - ");
        outline115.append(this.mSize);
        outline115.append(" items");
        Systrace.beginSection(0L, outline115.toString());
        int i = this.mCommitNumber;
        if (i > 0) {
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_BATCH_EXECUTION_START, null, i);
        }
    }

    private void endMarkers() {
        int i = this.mCommitNumber;
        if (i > 0) {
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_BATCH_EXECUTION_END, null, i);
        }
        Systrace.endSection(0L);
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(@NonNull MountingManager mountingManager) {
        beginMarkers("mountViews");
        for (int i = 0; i < this.mSize; i++) {
            try {
                this.mMountItems[i].execute(mountingManager);
            } catch (RuntimeException e) {
                StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Caught exception executing mountItem @", i, RealTimeTextConstants.COLON_SPACE);
                outline109.append(this.mMountItems[i].toString());
                FLog.e(TAG, outline109.toString(), e);
                throw e;
            }
        }
        endMarkers();
    }

    public int getRootTag() {
        return this.mRootTag;
    }

    public boolean shouldSchedule() {
        return this.mSize != 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < this.mSize) {
            if (sb.length() > 0) {
                sb.append("\n");
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BatchMountItem [S:");
            outline107.append(this.mRootTag);
            outline107.append("] (");
            sb.append(outline107.toString());
            int i2 = i + 1;
            sb.append(i2);
            sb.append("/");
            sb.append(this.mSize);
            sb.append("): ");
            sb.append(this.mMountItems[i]);
            i = i2;
        }
        return sb.toString();
    }
}
