package com.amazon.whisperjoin.common.sharedtypes.exceptions;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.io.IOException;
/* loaded from: classes13.dex */
public class BLETransportOperationError extends IOException {
    private final Throwable mFailureCause;
    private final Integer mGattStatus;
    private final Operation mOperation;

    /* loaded from: classes13.dex */
    public enum Operation {
        DISCOVER_GATT_SERVICES,
        BIND_SERVICE_CLIENT,
        ENABLE_NOTIFICATION_PROVISIONING_STATUS,
        ENABLE_NOTIFICATION_START_PROVISIONING_REQUEST_RESPONSE,
        ENABLE_NOTIFICATION_PROVISIONING_COMMAND_RESPONSE,
        ENABLE_NOTIFICATION_PROVISIONING_EVENTS,
        INITIATE_WRITE_CHARACTERISTIC_START_PROVISIONING_REQUEST,
        CONFIRM_WRITE_CHARACTERISTIC_START_PROVISIONING_REQUEST,
        READ_CHARACTERISTIC_START_PROVISIONING_REQUEST_RESPONSE,
        INITIATE_WRITE_CHARACTERISTIC_PROVISIONING_COMMAND,
        CONFIRM_WRITE_CHARACTERISTIC_PROVISIONING_COMMAND,
        READ_CHARACTERISTIC_COMMAND_RESPONSE,
        INITIATE_WRITE_CHARACTERISTIC_REQUEST_ADDITIONAL_COMMAND_RESPONSE_PACKET,
        CONFIRM_WRITE_CHARACTERISTIC_REQUEST_ADDITIONAL_COMMAND_RESPONSE_PACKET,
        INITIATE_READ_CHARACTERISTIC,
        CONFIRM_READ_CHARACTERISTIC,
        INITIATE_WRITE_CHARACTERISTIC,
        CONFIRM_WRITE_CHARACTERISTIC,
        ENABLE_NOTIFICATIONS
    }

    public BLETransportOperationError(Throwable th, Integer num, Operation operation) {
        this(null, th, num, operation);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || BLETransportOperationError.class != obj.getClass()) {
            return false;
        }
        BLETransportOperationError bLETransportOperationError = (BLETransportOperationError) obj;
        return this.mGattStatus == bLETransportOperationError.mGattStatus && this.mOperation == bLETransportOperationError.mOperation;
    }

    public Throwable getFailureCause() {
        return this.mFailureCause;
    }

    public Integer getGattStatus() {
        return this.mGattStatus;
    }

    public Operation getOperation() {
        return this.mOperation;
    }

    public int hashCode() {
        return Objects.hashCode(this.mGattStatus, this.mOperation);
    }

    @Override // java.lang.Throwable
    public String toString() {
        return MoreObjects.toStringHelper(this).add("mGattStatus", this.mGattStatus).add("mOperation", this.mOperation).add("mFailureCause", this.mFailureCause).toString();
    }

    public BLETransportOperationError(String str, Integer num, Operation operation) {
        this(str, null, num, operation);
    }

    public BLETransportOperationError(Integer num, Operation operation) {
        this(null, null, num, operation);
    }

    public BLETransportOperationError(Operation operation) {
        this(null, null, null, operation);
    }

    public BLETransportOperationError(Throwable th, Operation operation) {
        this(null, th, null, operation);
    }

    public BLETransportOperationError(String str, Throwable th, Operation operation) {
        this(str, th, null, operation);
    }

    public BLETransportOperationError(String str, Throwable th, Integer num, Operation operation) {
        super(str);
        this.mGattStatus = num;
        this.mOperation = operation;
        this.mFailureCause = th;
    }
}
