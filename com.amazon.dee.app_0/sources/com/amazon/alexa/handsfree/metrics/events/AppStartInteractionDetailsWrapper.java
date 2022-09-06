package com.amazon.alexa.handsfree.metrics.events;

import androidx.annotation.Nullable;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.AppStartInteractionDetails;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes8.dex */
public class AppStartInteractionDetailsWrapper extends AppStartInteractionDetails {
    private String mReferralDetails;
    private String mReferralSource;
    private String mReferralType;

    @JsonCreator
    public AppStartInteractionDetailsWrapper(@JsonProperty("referralType") String str, @Nullable @JsonProperty("referralSource") String str2, @Nullable @JsonProperty("referralDetails") String str3) {
        super(str, str2, str3);
        this.mReferralType = str;
        this.mReferralDetails = str3;
        this.mReferralSource = str2;
    }

    @Override // com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.AppStartInteractionDetails
    @JsonProperty(JsonFields.REFERRAL_DETAILS)
    public String getReferralDetails() {
        return this.mReferralDetails;
    }

    @Override // com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.AppStartInteractionDetails
    @JsonProperty(JsonFields.REFERRAL_SOURCE)
    public String getReferralSource() {
        return this.mReferralSource;
    }

    @Override // com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.AppStartInteractionDetails
    @JsonProperty(JsonFields.REFERRAL_TYPE)
    public String getReferralType() {
        return this.mReferralType;
    }
}
