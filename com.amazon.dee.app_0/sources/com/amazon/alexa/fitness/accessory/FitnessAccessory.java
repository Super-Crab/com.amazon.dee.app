package com.amazon.alexa.fitness.accessory;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.amazon.alexa.fitness.model.device.DeviceType;
import com.amazon.dee.app.services.bluetooth.DefaultBluetoothService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessAccessorySessionMonitor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\tHÆ\u0003J1\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\tHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001d"}, d2 = {"Lcom/amazon/alexa/fitness/accessory/FitnessAccessory;", "", ModelTransformer.KEY_ACCESSORY, "Lcom/amazon/alexa/accessory/Accessory;", DefaultBluetoothService.AUDIO_DEVICE_NAME, "", "deviceType", "Lcom/amazon/alexa/fitness/model/device/DeviceType;", "firmwareVersion", "", "(Lcom/amazon/alexa/accessory/Accessory;Ljava/lang/String;Lcom/amazon/alexa/fitness/model/device/DeviceType;I)V", "getAccessory", "()Lcom/amazon/alexa/accessory/Accessory;", "getDeviceName", "()Ljava/lang/String;", "getDeviceType", "()Lcom/amazon/alexa/fitness/model/device/DeviceType;", "getFirmwareVersion", "()I", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class FitnessAccessory {
    @NotNull
    private final Accessory accessory;
    @NotNull
    private final String deviceName;
    @NotNull
    private final DeviceType deviceType;
    private final int firmwareVersion;

    public FitnessAccessory(@NotNull Accessory accessory, @NotNull String deviceName, @NotNull DeviceType deviceType, int i) {
        Intrinsics.checkParameterIsNotNull(accessory, "accessory");
        Intrinsics.checkParameterIsNotNull(deviceName, "deviceName");
        Intrinsics.checkParameterIsNotNull(deviceType, "deviceType");
        this.accessory = accessory;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.firmwareVersion = i;
    }

    public static /* synthetic */ FitnessAccessory copy$default(FitnessAccessory fitnessAccessory, Accessory accessory, String str, DeviceType deviceType, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            accessory = fitnessAccessory.accessory;
        }
        if ((i2 & 2) != 0) {
            str = fitnessAccessory.deviceName;
        }
        if ((i2 & 4) != 0) {
            deviceType = fitnessAccessory.deviceType;
        }
        if ((i2 & 8) != 0) {
            i = fitnessAccessory.firmwareVersion;
        }
        return fitnessAccessory.copy(accessory, str, deviceType, i);
    }

    @NotNull
    public final Accessory component1() {
        return this.accessory;
    }

    @NotNull
    public final String component2() {
        return this.deviceName;
    }

    @NotNull
    public final DeviceType component3() {
        return this.deviceType;
    }

    public final int component4() {
        return this.firmwareVersion;
    }

    @NotNull
    public final FitnessAccessory copy(@NotNull Accessory accessory, @NotNull String deviceName, @NotNull DeviceType deviceType, int i) {
        Intrinsics.checkParameterIsNotNull(accessory, "accessory");
        Intrinsics.checkParameterIsNotNull(deviceName, "deviceName");
        Intrinsics.checkParameterIsNotNull(deviceType, "deviceType");
        return new FitnessAccessory(accessory, deviceName, deviceType, i);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof FitnessAccessory)) {
                return false;
            }
            FitnessAccessory fitnessAccessory = (FitnessAccessory) obj;
            return Intrinsics.areEqual(this.accessory, fitnessAccessory.accessory) && Intrinsics.areEqual(this.deviceName, fitnessAccessory.deviceName) && Intrinsics.areEqual(this.deviceType, fitnessAccessory.deviceType) && this.firmwareVersion == fitnessAccessory.firmwareVersion;
        }
        return true;
    }

    @NotNull
    public final Accessory getAccessory() {
        return this.accessory;
    }

    @NotNull
    public final String getDeviceName() {
        return this.deviceName;
    }

    @NotNull
    public final DeviceType getDeviceType() {
        return this.deviceType;
    }

    public final int getFirmwareVersion() {
        return this.firmwareVersion;
    }

    public int hashCode() {
        Accessory accessory = this.accessory;
        int i = 0;
        int hashCode = (accessory != null ? accessory.hashCode() : 0) * 31;
        String str = this.deviceName;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        DeviceType deviceType = this.deviceType;
        if (deviceType != null) {
            i = deviceType.hashCode();
        }
        return ((hashCode2 + i) * 31) + this.firmwareVersion;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FitnessAccessory(accessory=");
        outline107.append(this.accessory);
        outline107.append(", deviceName=");
        outline107.append(this.deviceName);
        outline107.append(", deviceType=");
        outline107.append(this.deviceType);
        outline107.append(", firmwareVersion=");
        return GeneratedOutlineSupport1.outline86(outline107, this.firmwareVersion, ")");
    }

    public /* synthetic */ FitnessAccessory(Accessory accessory, String str, DeviceType deviceType, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(accessory, str, deviceType, (i2 & 8) != 0 ? Integer.MAX_VALUE : i);
    }
}
