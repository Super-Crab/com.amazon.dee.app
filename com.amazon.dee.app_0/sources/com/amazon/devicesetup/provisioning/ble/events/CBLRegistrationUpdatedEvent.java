package com.amazon.devicesetup.provisioning.ble.events;

import com.amazon.devicesetup.provisioning.data.registration.CBLRegistrationDetails;
import com.amazon.whisperjoin.common.sharedtypes.ble.events.ProvisionableEvent;
/* loaded from: classes12.dex */
public class CBLRegistrationUpdatedEvent extends ProvisionableEvent<CBLRegistrationDetails> {
    public CBLRegistrationUpdatedEvent(CBLRegistrationDetails cBLRegistrationDetails) {
        super(ProvisionableEvents.CBL_REGISTRATION_DETAILS_UPDATED_EVENT_UUID, cBLRegistrationDetails);
    }
}
