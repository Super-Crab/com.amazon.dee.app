package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes13.dex */
public class CreateSecurityProfileRequest extends AmazonWebServiceRequest implements Serializable {
    private Map<String, AlertTarget> alertTargets;
    private List<Behavior> behaviors;
    private String securityProfileDescription;
    private String securityProfileName;
    private List<Tag> tags;

    public CreateSecurityProfileRequest addalertTargetsEntry(String str, AlertTarget alertTarget) {
        if (this.alertTargets == null) {
            this.alertTargets = new HashMap();
        }
        if (!this.alertTargets.containsKey(str)) {
            this.alertTargets.put(str, alertTarget);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public CreateSecurityProfileRequest clearalertTargetsEntries() {
        this.alertTargets = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateSecurityProfileRequest)) {
            return false;
        }
        CreateSecurityProfileRequest createSecurityProfileRequest = (CreateSecurityProfileRequest) obj;
        if ((createSecurityProfileRequest.getSecurityProfileName() == null) ^ (getSecurityProfileName() == null)) {
            return false;
        }
        if (createSecurityProfileRequest.getSecurityProfileName() != null && !createSecurityProfileRequest.getSecurityProfileName().equals(getSecurityProfileName())) {
            return false;
        }
        if ((createSecurityProfileRequest.getSecurityProfileDescription() == null) ^ (getSecurityProfileDescription() == null)) {
            return false;
        }
        if (createSecurityProfileRequest.getSecurityProfileDescription() != null && !createSecurityProfileRequest.getSecurityProfileDescription().equals(getSecurityProfileDescription())) {
            return false;
        }
        if ((createSecurityProfileRequest.getBehaviors() == null) ^ (getBehaviors() == null)) {
            return false;
        }
        if (createSecurityProfileRequest.getBehaviors() != null && !createSecurityProfileRequest.getBehaviors().equals(getBehaviors())) {
            return false;
        }
        if ((createSecurityProfileRequest.getAlertTargets() == null) ^ (getAlertTargets() == null)) {
            return false;
        }
        if (createSecurityProfileRequest.getAlertTargets() != null && !createSecurityProfileRequest.getAlertTargets().equals(getAlertTargets())) {
            return false;
        }
        if ((createSecurityProfileRequest.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        return createSecurityProfileRequest.getTags() == null || createSecurityProfileRequest.getTags().equals(getTags());
    }

    public Map<String, AlertTarget> getAlertTargets() {
        return this.alertTargets;
    }

    public List<Behavior> getBehaviors() {
        return this.behaviors;
    }

    public String getSecurityProfileDescription() {
        return this.securityProfileDescription;
    }

    public String getSecurityProfileName() {
        return this.securityProfileName;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((getSecurityProfileName() == null ? 0 : getSecurityProfileName().hashCode()) + 31) * 31) + (getSecurityProfileDescription() == null ? 0 : getSecurityProfileDescription().hashCode())) * 31) + (getBehaviors() == null ? 0 : getBehaviors().hashCode())) * 31) + (getAlertTargets() == null ? 0 : getAlertTargets().hashCode())) * 31;
        if (getTags() != null) {
            i = getTags().hashCode();
        }
        return hashCode + i;
    }

    public void setAlertTargets(Map<String, AlertTarget> map) {
        this.alertTargets = map;
    }

    public void setBehaviors(Collection<Behavior> collection) {
        if (collection == null) {
            this.behaviors = null;
        } else {
            this.behaviors = new ArrayList(collection);
        }
    }

    public void setSecurityProfileDescription(String str) {
        this.securityProfileDescription = str;
    }

    public void setSecurityProfileName(String str) {
        this.securityProfileName = str;
    }

    public void setTags(Collection<Tag> collection) {
        if (collection == null) {
            this.tags = null;
        } else {
            this.tags = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getSecurityProfileName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("securityProfileName: ");
            outline1072.append(getSecurityProfileName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getSecurityProfileDescription() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("securityProfileDescription: ");
            outline1073.append(getSecurityProfileDescription());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getBehaviors() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("behaviors: ");
            outline1074.append(getBehaviors());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getAlertTargets() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("alertTargets: ");
            outline1075.append(getAlertTargets());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getTags() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("tags: ");
            outline1076.append(getTags());
            outline107.append(outline1076.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CreateSecurityProfileRequest withAlertTargets(Map<String, AlertTarget> map) {
        this.alertTargets = map;
        return this;
    }

    public CreateSecurityProfileRequest withBehaviors(Behavior... behaviorArr) {
        if (getBehaviors() == null) {
            this.behaviors = new ArrayList(behaviorArr.length);
        }
        for (Behavior behavior : behaviorArr) {
            this.behaviors.add(behavior);
        }
        return this;
    }

    public CreateSecurityProfileRequest withSecurityProfileDescription(String str) {
        this.securityProfileDescription = str;
        return this;
    }

    public CreateSecurityProfileRequest withSecurityProfileName(String str) {
        this.securityProfileName = str;
        return this;
    }

    public CreateSecurityProfileRequest withTags(Tag... tagArr) {
        if (getTags() == null) {
            this.tags = new ArrayList(tagArr.length);
        }
        for (Tag tag : tagArr) {
            this.tags.add(tag);
        }
        return this;
    }

    public CreateSecurityProfileRequest withBehaviors(Collection<Behavior> collection) {
        setBehaviors(collection);
        return this;
    }

    public CreateSecurityProfileRequest withTags(Collection<Tag> collection) {
        setTags(collection);
        return this;
    }
}
