package com.amazon.alexa.biloba.model;

import com.amazon.alexa.wakeword.speakerverification.profile.SpeakerVerificationProfileProvider;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes6.dex */
public class PersonIdentitySetupRequest {
    @SerializedName("metadata")
    PersonIdentitySetupRequestMetadata metadata;
    @SerializedName(SpeakerVerificationProfileProvider.COLUMN_PERSON_ID)
    PersonIdentityPersonId personId;
    @SerializedName("requestorId")
    String requestorId;

    public PersonIdentitySetupRequest(String str, String str2, PersonIdentitySetupRequestMetadata personIdentitySetupRequestMetadata) {
        this.personId = new PersonIdentityPersonId(str);
        this.requestorId = str2;
        this.metadata = personIdentitySetupRequestMetadata;
    }
}
