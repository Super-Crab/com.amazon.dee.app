package com.amazon.deecomms.common;

import androidx.annotation.Nullable;
import com.amazon.deecomms.ndt.ui.DeviceBottomSheet;
/* loaded from: classes12.dex */
public interface DeviceBottomSheetTarget {
    @Nullable
    DeviceBottomSheet getBottomSheetDialogFragment();

    void setBottomSheetDialogFragment(@Nullable DeviceBottomSheet deviceBottomSheet);
}
