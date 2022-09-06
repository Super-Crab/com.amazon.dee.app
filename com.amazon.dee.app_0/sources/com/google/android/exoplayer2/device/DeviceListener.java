package com.google.android.exoplayer2.device;
/* loaded from: classes2.dex */
public interface DeviceListener {
    default void onDeviceInfoChanged(DeviceInfo deviceInfo) {
    }

    default void onDeviceVolumeChanged(int i, boolean z) {
    }
}
