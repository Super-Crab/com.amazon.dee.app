package com.amazon.clouddrive.cdasdk.prompto.groups;

import com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceCustomerRequest;
import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class CreateGroupRequest extends PromptoServiceCustomerRequest {
    @JsonProperty(ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME)
    private String kind;
    @JsonProperty("name")
    private String name;

    @Override // com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceCustomerRequest, com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof CreateGroupRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceCustomerRequest, com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CreateGroupRequest)) {
            return false;
        }
        CreateGroupRequest createGroupRequest = (CreateGroupRequest) obj;
        if (!createGroupRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String name = getName();
        String name2 = createGroupRequest.getName();
        if (name != null ? !name.equals(name2) : name2 != null) {
            return false;
        }
        String kind = getKind();
        String kind2 = createGroupRequest.getKind();
        return kind != null ? kind.equals(kind2) : kind2 == null;
    }

    public String getKind() {
        return this.kind;
    }

    public String getName() {
        return this.name;
    }

    @Override // com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceCustomerRequest, com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        String name = getName();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (name == null ? 43 : name.hashCode());
        String kind = getKind();
        int i2 = hashCode2 * 59;
        if (kind != null) {
            i = kind.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty(ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME)
    public void setKind(String str) {
        this.kind = str;
    }

    @JsonProperty("name")
    public void setName(String str) {
        this.name = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceCustomerRequest, com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CreateGroupRequest(name=");
        outline107.append(getName());
        outline107.append(", kind=");
        outline107.append(getKind());
        outline107.append(")");
        return outline107.toString();
    }
}
