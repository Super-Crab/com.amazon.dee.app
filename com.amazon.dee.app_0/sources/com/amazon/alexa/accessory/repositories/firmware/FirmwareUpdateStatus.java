package com.amazon.alexa.accessory.repositories.firmware;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes6.dex */
public final class FirmwareUpdateStatus {
    private static final FirmwareUpdateStatus IDLE = new FirmwareUpdateStatus(0, null, 0.0f, 0);
    private static final int STATE_COMPLETED = 2;
    private static final int STATE_FAILED = 3;
    private static final int STATE_IDLE = 0;
    private static final int STATE_TRANSFERRING = 1;
    public final Throwable cause;
    public final int deviceId;
    public final float progress;
    public final int state;

    public FirmwareUpdateStatus(int i, Throwable th, float f, int i2) {
        this.state = i;
        this.cause = th;
        this.progress = f;
        this.deviceId = i2;
    }

    public static FirmwareUpdateStatus completed(int i) {
        return new FirmwareUpdateStatus(2, null, 0.0f, i);
    }

    public static FirmwareUpdateStatus completedWithError(Throwable th, int i) {
        return new FirmwareUpdateStatus(3, th, 0.0f, i);
    }

    public static FirmwareUpdateStatus idle() {
        return IDLE;
    }

    public static FirmwareUpdateStatus transferring(float f, int i) {
        return new FirmwareUpdateStatus(1, null, f, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || FirmwareUpdateStatus.class != obj.getClass()) {
            return false;
        }
        FirmwareUpdateStatus firmwareUpdateStatus = (FirmwareUpdateStatus) obj;
        if (this.state == firmwareUpdateStatus.state && Float.compare(firmwareUpdateStatus.progress, this.progress) == 0 && this.deviceId == firmwareUpdateStatus.deviceId) {
            if ((this.cause == null) == (firmwareUpdateStatus.cause == null)) {
                return true;
            }
        }
        return false;
    }

    public Throwable getCause() {
        return this.cause;
    }

    public int getDeviceId() {
        return this.deviceId;
    }

    public float getProgress() {
        return this.progress;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.state), Float.valueOf(this.progress), Integer.valueOf(this.deviceId));
    }

    public boolean isCompleted() {
        return this.state == 2;
    }

    public boolean isCompletedWithError() {
        return this.state == 3;
    }

    public boolean isIdle() {
        return this.state == 0;
    }

    public boolean isInProgress() {
        return this.state == 1;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FirmwareUpdateStatus{state=");
        outline107.append(this.state);
        outline107.append(", cause=");
        outline107.append(this.cause);
        outline107.append(", progress=");
        outline107.append(this.progress);
        outline107.append(", deviceId=");
        return GeneratedOutlineSupport1.outline85(outline107, this.deviceId, JsonReaderKt.END_OBJ);
    }
}
