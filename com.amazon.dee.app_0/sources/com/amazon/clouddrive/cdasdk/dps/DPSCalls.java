package com.amazon.clouddrive.cdasdk.dps;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.dps.collections.DPSCollectionsCalls;
import com.amazon.clouddrive.cdasdk.dps.devices.DPSDevicesCalls;
import com.amazon.clouddrive.cdasdk.dps.event.DPSEventCalls;
import com.amazon.clouddrive.cdasdk.dps.settings.DPSSettingsCalls;
/* loaded from: classes11.dex */
public interface DPSCalls {
    @NonNull
    DPSCollectionsCalls getCollectionCalls();

    @NonNull
    DPSDevicesCalls getDevicesCalls();

    @NonNull
    DPSEventCalls getEventCalls();

    @NonNull
    DPSSettingsCalls getSettingsCalls();
}
