package com.amazon.alexa.accessory.repositories.nonhfpcalling;

import java.util.UUID;
/* loaded from: classes6.dex */
public interface NonHfpCallController {

    /* loaded from: classes6.dex */
    public enum OperationStatus {
        SUCCESS,
        UNKNOWN,
        INTERNAL,
        UNSUPPORTED,
        USER_CANCELLED,
        NOT_FOUND,
        INVALID,
        BUSY
    }

    OperationStatus handleAcceptCall(UUID uuid);

    OperationStatus handleEndCall(UUID uuid);

    OperationStatus handleRejectCall(UUID uuid);
}
