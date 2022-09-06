package com.amazon.clouddrive.cdasdk.dps;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.ClientConfig;
import com.amazon.clouddrive.cdasdk.dps.collections.DPSCollectionsCalls;
import com.amazon.clouddrive.cdasdk.dps.collections.DPSCollectionsCallsImpl;
import com.amazon.clouddrive.cdasdk.dps.devices.DPSDevicesCalls;
import com.amazon.clouddrive.cdasdk.dps.devices.DPSDevicesCallsImpl;
import com.amazon.clouddrive.cdasdk.dps.event.DPSEventCalls;
import com.amazon.clouddrive.cdasdk.dps.event.DPSEventCallsImpl;
import com.amazon.clouddrive.cdasdk.dps.settings.DPSSettingsCalls;
import com.amazon.clouddrive.cdasdk.dps.settings.DPSSettingsCallsImpl;
import retrofit2.Retrofit;
/* loaded from: classes11.dex */
public class DPSCallsImpl implements DPSCalls {
    @NonNull
    private final DPSCollectionsCalls collectionsCalls;
    @NonNull
    private final DPSDevicesCalls devicesCalls;
    @NonNull
    private final DPSEventCalls eventCalls;
    @NonNull
    private final DPSSettingsCalls settingsCalls;

    public DPSCallsImpl(@NonNull ClientConfig clientConfig, @NonNull Retrofit retrofit) {
        DPSCallUtil dPSCallUtil = new DPSCallUtil(clientConfig);
        this.devicesCalls = new DPSDevicesCallsImpl(dPSCallUtil, retrofit);
        this.settingsCalls = new DPSSettingsCallsImpl(dPSCallUtil, retrofit);
        this.collectionsCalls = new DPSCollectionsCallsImpl(dPSCallUtil, retrofit);
        this.eventCalls = new DPSEventCallsImpl(dPSCallUtil, retrofit);
    }

    @Override // com.amazon.clouddrive.cdasdk.dps.DPSCalls
    @NonNull
    public DPSCollectionsCalls getCollectionCalls() {
        return this.collectionsCalls;
    }

    @Override // com.amazon.clouddrive.cdasdk.dps.DPSCalls
    @NonNull
    public DPSDevicesCalls getDevicesCalls() {
        return this.devicesCalls;
    }

    @Override // com.amazon.clouddrive.cdasdk.dps.DPSCalls
    @NonNull
    public DPSEventCalls getEventCalls() {
        return this.eventCalls;
    }

    @Override // com.amazon.clouddrive.cdasdk.dps.DPSCalls
    @NonNull
    public DPSSettingsCalls getSettingsCalls() {
        return this.settingsCalls;
    }
}
