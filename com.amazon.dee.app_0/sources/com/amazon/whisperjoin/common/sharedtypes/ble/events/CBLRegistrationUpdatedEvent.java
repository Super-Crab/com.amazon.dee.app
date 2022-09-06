package com.amazon.whisperjoin.common.sharedtypes.ble.events;

import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.registration.CBLRegistrationDetails;
/* loaded from: classes13.dex */
public class CBLRegistrationUpdatedEvent extends ProvisionableEvent<CBLRegistrationDetails> {
    public CBLRegistrationUpdatedEvent(CBLRegistrationDetails cBLRegistrationDetails) {
        super(ProvisionableEvents.CBL_REGISTRATION_DETAILS_UPDATED_EVENT_UUID, cBLRegistrationDetails);
    }
}
