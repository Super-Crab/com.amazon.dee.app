package com.amazon.tarazed.core.logging.api;

import com.amazon.tarazed.core.types.Environment;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.sun.mail.imap.IMAPStore;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: LogUploadRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J3\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/amazon/tarazed/core/logging/api/LogUploadRequest;", "", IMAPStore.ID_ENVIRONMENT, "Lcom/amazon/tarazed/core/types/Environment;", "deviceSerialNumber", "", "deviceType", "deviceFirmwareVersion", "(Lcom/amazon/tarazed/core/types/Environment;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDeviceFirmwareVersion", "()Ljava/lang/String;", "getDeviceSerialNumber", "getDeviceType", "getEnvironment", "()Lcom/amazon/tarazed/core/types/Environment;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class LogUploadRequest {
    @Nullable
    private final String deviceFirmwareVersion;
    @NotNull
    private final String deviceSerialNumber;
    @NotNull
    private final String deviceType;
    @NotNull
    private final Environment environment;

    public LogUploadRequest(@NotNull Environment environment, @NotNull String deviceSerialNumber, @NotNull String deviceType, @Nullable String str) {
        Intrinsics.checkParameterIsNotNull(environment, "environment");
        Intrinsics.checkParameterIsNotNull(deviceSerialNumber, "deviceSerialNumber");
        Intrinsics.checkParameterIsNotNull(deviceType, "deviceType");
        this.environment = environment;
        this.deviceSerialNumber = deviceSerialNumber;
        this.deviceType = deviceType;
        this.deviceFirmwareVersion = str;
    }

    public static /* synthetic */ LogUploadRequest copy$default(LogUploadRequest logUploadRequest, Environment environment, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            environment = logUploadRequest.environment;
        }
        if ((i & 2) != 0) {
            str = logUploadRequest.deviceSerialNumber;
        }
        if ((i & 4) != 0) {
            str2 = logUploadRequest.deviceType;
        }
        if ((i & 8) != 0) {
            str3 = logUploadRequest.deviceFirmwareVersion;
        }
        return logUploadRequest.copy(environment, str, str2, str3);
    }

    @NotNull
    public final Environment component1() {
        return this.environment;
    }

    @NotNull
    public final String component2() {
        return this.deviceSerialNumber;
    }

    @NotNull
    public final String component3() {
        return this.deviceType;
    }

    @Nullable
    public final String component4() {
        return this.deviceFirmwareVersion;
    }

    @NotNull
    public final LogUploadRequest copy(@NotNull Environment environment, @NotNull String deviceSerialNumber, @NotNull String deviceType, @Nullable String str) {
        Intrinsics.checkParameterIsNotNull(environment, "environment");
        Intrinsics.checkParameterIsNotNull(deviceSerialNumber, "deviceSerialNumber");
        Intrinsics.checkParameterIsNotNull(deviceType, "deviceType");
        return new LogUploadRequest(environment, deviceSerialNumber, deviceType, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof LogUploadRequest)) {
                return false;
            }
            LogUploadRequest logUploadRequest = (LogUploadRequest) obj;
            return Intrinsics.areEqual(this.environment, logUploadRequest.environment) && Intrinsics.areEqual(this.deviceSerialNumber, logUploadRequest.deviceSerialNumber) && Intrinsics.areEqual(this.deviceType, logUploadRequest.deviceType) && Intrinsics.areEqual(this.deviceFirmwareVersion, logUploadRequest.deviceFirmwareVersion);
        }
        return true;
    }

    @Nullable
    public final String getDeviceFirmwareVersion() {
        return this.deviceFirmwareVersion;
    }

    @NotNull
    public final String getDeviceSerialNumber() {
        return this.deviceSerialNumber;
    }

    @NotNull
    public final String getDeviceType() {
        return this.deviceType;
    }

    @NotNull
    public final Environment getEnvironment() {
        return this.environment;
    }

    public int hashCode() {
        Environment environment = this.environment;
        int i = 0;
        int hashCode = (environment != null ? environment.hashCode() : 0) * 31;
        String str = this.deviceSerialNumber;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.deviceType;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.deviceFirmwareVersion;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode3 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("LogUploadRequest(environment=");
        outline107.append(this.environment);
        outline107.append(", deviceSerialNumber=");
        outline107.append(this.deviceSerialNumber);
        outline107.append(", deviceType=");
        outline107.append(this.deviceType);
        outline107.append(", deviceFirmwareVersion=");
        return GeneratedOutlineSupport1.outline91(outline107, this.deviceFirmwareVersion, ")");
    }
}
