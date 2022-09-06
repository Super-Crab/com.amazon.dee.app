package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class Denied implements Serializable {
    private ExplicitDeny explicitDeny;
    private ImplicitDeny implicitDeny;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Denied)) {
            return false;
        }
        Denied denied = (Denied) obj;
        if ((denied.getImplicitDeny() == null) ^ (getImplicitDeny() == null)) {
            return false;
        }
        if (denied.getImplicitDeny() != null && !denied.getImplicitDeny().equals(getImplicitDeny())) {
            return false;
        }
        if ((denied.getExplicitDeny() == null) ^ (getExplicitDeny() == null)) {
            return false;
        }
        return denied.getExplicitDeny() == null || denied.getExplicitDeny().equals(getExplicitDeny());
    }

    public ExplicitDeny getExplicitDeny() {
        return this.explicitDeny;
    }

    public ImplicitDeny getImplicitDeny() {
        return this.implicitDeny;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getImplicitDeny() == null ? 0 : getImplicitDeny().hashCode()) + 31) * 31;
        if (getExplicitDeny() != null) {
            i = getExplicitDeny().hashCode();
        }
        return hashCode + i;
    }

    public void setExplicitDeny(ExplicitDeny explicitDeny) {
        this.explicitDeny = explicitDeny;
    }

    public void setImplicitDeny(ImplicitDeny implicitDeny) {
        this.implicitDeny = implicitDeny;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getImplicitDeny() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("implicitDeny: ");
            outline1072.append(getImplicitDeny());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getExplicitDeny() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("explicitDeny: ");
            outline1073.append(getExplicitDeny());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public Denied withExplicitDeny(ExplicitDeny explicitDeny) {
        this.explicitDeny = explicitDeny;
        return this;
    }

    public Denied withImplicitDeny(ImplicitDeny implicitDeny) {
        this.implicitDeny = implicitDeny;
        return this;
    }
}
