package com.amazon.deecomms.ndt.model;

import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.enums.DropInAvailability;
import com.amazon.deecomms.ndt.enums.ActiveState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import java.util.List;
/* loaded from: classes12.dex */
public class GetDevicesResponse {
    @JsonProperty("devices")
    private List<DeviceModel> deviceModels;
    @JsonProperty("status")
    private String status;
    @JsonIgnore
    private Long timeStamp;
    private static final Predicate<DeviceModel> byDropInEnabled = $$Lambda$GetDevicesResponse$rbsgEafZ_9JdxxxXmE5oOv9fLE.INSTANCE;
    private static final Predicate<DeviceModel> byActive = $$Lambda$GetDevicesResponse$h_oJpqnGWrtgpO2mc0Al7ZH4Ja4.INSTANCE;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$static$0(DeviceModel deviceModel) {
        return deviceModel != null && DropInAvailability.isEnabled(deviceModel.getDeviceStatus().getDeviceDropInAvailability());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$static$1(DeviceModel deviceModel) {
        return deviceModel != null && deviceModel.getDeviceStatus().getActiveState() == ActiveState.ACTIVE;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GetDevicesResponse)) {
            return false;
        }
        GetDevicesResponse getDevicesResponse = (GetDevicesResponse) obj;
        return Objects.equal(this.deviceModels, getDevicesResponse.deviceModels) && Objects.equal(this.status, getDevicesResponse.status);
    }

    @NonNull
    public List<DeviceModel> getActiveDropInDevices() {
        return FluentIterable.from(getDropInDevices()).filter(byActive).toList();
    }

    public List<DeviceModel> getDeviceModels() {
        return this.deviceModels;
    }

    @NonNull
    public List<DeviceModel> getDropInDevices() {
        return FluentIterable.from(this.deviceModels).filter(byDropInEnabled).toList();
    }

    public String getStatus() {
        return this.status;
    }

    public Long getTimeStamp() {
        return this.timeStamp;
    }

    public int hashCode() {
        return Objects.hashCode(this.deviceModels, this.status);
    }

    public Boolean isCacheValid(long j) {
        if (Math.abs(getTimeStamp().longValue() - System.currentTimeMillis()) >= j) {
            return false;
        }
        return true;
    }

    public void setDeviceModels(List<DeviceModel> list) {
        this.deviceModels = list;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setTimeStamp(Long l) {
        this.timeStamp = l;
    }
}
