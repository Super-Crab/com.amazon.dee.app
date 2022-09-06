package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ValidateSecurityProfileBehaviorsRequest extends AmazonWebServiceRequest implements Serializable {
    private List<Behavior> behaviors;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ValidateSecurityProfileBehaviorsRequest)) {
            return false;
        }
        ValidateSecurityProfileBehaviorsRequest validateSecurityProfileBehaviorsRequest = (ValidateSecurityProfileBehaviorsRequest) obj;
        if ((validateSecurityProfileBehaviorsRequest.getBehaviors() == null) ^ (getBehaviors() == null)) {
            return false;
        }
        return validateSecurityProfileBehaviorsRequest.getBehaviors() == null || validateSecurityProfileBehaviorsRequest.getBehaviors().equals(getBehaviors());
    }

    public List<Behavior> getBehaviors() {
        return this.behaviors;
    }

    public int hashCode() {
        return 31 + (getBehaviors() == null ? 0 : getBehaviors().hashCode());
    }

    public void setBehaviors(Collection<Behavior> collection) {
        if (collection == null) {
            this.behaviors = null;
        } else {
            this.behaviors = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getBehaviors() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("behaviors: ");
            outline1072.append(getBehaviors());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ValidateSecurityProfileBehaviorsRequest withBehaviors(Behavior... behaviorArr) {
        if (getBehaviors() == null) {
            this.behaviors = new ArrayList(behaviorArr.length);
        }
        for (Behavior behavior : behaviorArr) {
            this.behaviors.add(behavior);
        }
        return this;
    }

    public ValidateSecurityProfileBehaviorsRequest withBehaviors(Collection<Behavior> collection) {
        setBehaviors(collection);
        return this;
    }
}
