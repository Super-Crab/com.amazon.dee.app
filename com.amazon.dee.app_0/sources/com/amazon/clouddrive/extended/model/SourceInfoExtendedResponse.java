package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.BaseSourceInfo;
import com.amazon.clouddrive.model.SourceInfoResponse;
/* loaded from: classes11.dex */
public class SourceInfoExtendedResponse extends SourceInfoResponse {
    private Boolean pushEndpoint;

    @Override // com.amazon.clouddrive.model.SourceInfo, com.amazon.clouddrive.model.BaseSourceInfo
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof SourceInfoExtendedResponse) && compareTo((BaseSourceInfo) ((SourceInfoExtendedResponse) obj)) == 0;
    }

    @Override // com.amazon.clouddrive.model.SourceInfo, com.amazon.clouddrive.model.BaseSourceInfo
    public int hashCode() {
        return (((isPushEndpoint() == null ? 0 : isPushEndpoint().hashCode()) + 1) * 31) + super.hashCode();
    }

    public Boolean isPushEndpoint() {
        return this.pushEndpoint;
    }

    public void setPushEndpoint(Boolean bool) {
        this.pushEndpoint = bool;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.SourceInfo, com.amazon.clouddrive.model.BaseSourceInfo, java.lang.Comparable
    public int compareTo(BaseSourceInfo baseSourceInfo) {
        if (baseSourceInfo == null) {
            return -1;
        }
        if (baseSourceInfo == this) {
            return 0;
        }
        if (!(baseSourceInfo instanceof SourceInfoExtendedResponse)) {
            return 1;
        }
        Boolean isPushEndpoint = isPushEndpoint();
        Boolean isPushEndpoint2 = ((SourceInfoExtendedResponse) baseSourceInfo).isPushEndpoint();
        if (isPushEndpoint != isPushEndpoint2) {
            if (isPushEndpoint == null) {
                return -1;
            }
            if (isPushEndpoint2 == null) {
                return 1;
            }
            int compareTo = isPushEndpoint.compareTo(isPushEndpoint2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return super.compareTo(baseSourceInfo);
    }
}
