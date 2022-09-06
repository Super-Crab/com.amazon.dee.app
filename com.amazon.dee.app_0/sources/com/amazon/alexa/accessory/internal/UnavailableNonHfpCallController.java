package com.amazon.alexa.accessory.internal;

import com.amazon.alexa.accessory.repositories.nonhfpcalling.NonHfpCallController;
import java.util.UUID;
/* loaded from: classes.dex */
public class UnavailableNonHfpCallController implements NonHfpCallController {
    @Override // com.amazon.alexa.accessory.repositories.nonhfpcalling.NonHfpCallController
    public NonHfpCallController.OperationStatus handleAcceptCall(UUID uuid) {
        return NonHfpCallController.OperationStatus.UNSUPPORTED;
    }

    @Override // com.amazon.alexa.accessory.repositories.nonhfpcalling.NonHfpCallController
    public NonHfpCallController.OperationStatus handleEndCall(UUID uuid) {
        return NonHfpCallController.OperationStatus.UNSUPPORTED;
    }

    @Override // com.amazon.alexa.accessory.repositories.nonhfpcalling.NonHfpCallController
    public NonHfpCallController.OperationStatus handleRejectCall(UUID uuid) {
        return NonHfpCallController.OperationStatus.UNSUPPORTED;
    }
}
