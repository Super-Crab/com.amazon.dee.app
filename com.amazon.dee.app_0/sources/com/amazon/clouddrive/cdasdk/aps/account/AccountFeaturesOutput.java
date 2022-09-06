package com.amazon.clouddrive.cdasdk.aps.account;

import com.amazon.alexa.smarthomecameras.constants.NetworkConstants;
import com.amazon.clouddrive.cdasdk.aps.common.APSOutput;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class AccountFeaturesOutput extends APSOutput {
    @JsonProperty("canInvite")
    private Boolean canInvite;
    @JsonProperty(NetworkConstants.FEATURES_KEY)
    private List<AccountFeature> features;
    @JsonProperty("onboarded")
    private Boolean onboarded;

    @Override // com.amazon.clouddrive.cdasdk.aps.common.APSOutput
    protected boolean canEqual(Object obj) {
        return obj instanceof AccountFeaturesOutput;
    }

    @Override // com.amazon.clouddrive.cdasdk.aps.common.APSOutput
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AccountFeaturesOutput)) {
            return false;
        }
        AccountFeaturesOutput accountFeaturesOutput = (AccountFeaturesOutput) obj;
        if (!accountFeaturesOutput.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        List<AccountFeature> features = getFeatures();
        List<AccountFeature> features2 = accountFeaturesOutput.getFeatures();
        if (features != null ? !features.equals(features2) : features2 != null) {
            return false;
        }
        Boolean onboarded = getOnboarded();
        Boolean onboarded2 = accountFeaturesOutput.getOnboarded();
        if (onboarded != null ? !onboarded.equals(onboarded2) : onboarded2 != null) {
            return false;
        }
        Boolean canInvite = getCanInvite();
        Boolean canInvite2 = accountFeaturesOutput.getCanInvite();
        return canInvite != null ? canInvite.equals(canInvite2) : canInvite2 == null;
    }

    public Boolean getCanInvite() {
        return this.canInvite;
    }

    public List<AccountFeature> getFeatures() {
        return this.features;
    }

    public Boolean getOnboarded() {
        return this.onboarded;
    }

    @Override // com.amazon.clouddrive.cdasdk.aps.common.APSOutput
    public int hashCode() {
        int hashCode = super.hashCode();
        List<AccountFeature> features = getFeatures();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (features == null ? 43 : features.hashCode());
        Boolean onboarded = getOnboarded();
        int hashCode3 = (hashCode2 * 59) + (onboarded == null ? 43 : onboarded.hashCode());
        Boolean canInvite = getCanInvite();
        int i2 = hashCode3 * 59;
        if (canInvite != null) {
            i = canInvite.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("canInvite")
    public void setCanInvite(Boolean bool) {
        this.canInvite = bool;
    }

    @JsonProperty(NetworkConstants.FEATURES_KEY)
    public void setFeatures(List<AccountFeature> list) {
        this.features = list;
    }

    @JsonProperty("onboarded")
    public void setOnboarded(Boolean bool) {
        this.onboarded = bool;
    }

    @Override // com.amazon.clouddrive.cdasdk.aps.common.APSOutput
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AccountFeaturesOutput(features=");
        outline107.append(getFeatures());
        outline107.append(", onboarded=");
        outline107.append(getOnboarded());
        outline107.append(", canInvite=");
        outline107.append(getCanInvite());
        outline107.append(")");
        return outline107.toString();
    }
}
