package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetupservice.ProvisioneeIdentifier;
import java.util.List;
/* loaded from: classes12.dex */
public class GetCustomerProvisioneesSetupStatusInput extends AuthenticatedInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.GetCustomerProvisioneesSetupStatusInput");
    private List<ProvisioneeIdentifier> provisioneeIdentifiers;

    @Override // com.amazon.devicesetupservice.v1.AuthenticatedInput
    public boolean equals(Object obj) {
        if (!(obj instanceof GetCustomerProvisioneesSetupStatusInput)) {
            return false;
        }
        return super.equals(obj) && Helper.equals(this.provisioneeIdentifiers, ((GetCustomerProvisioneesSetupStatusInput) obj).provisioneeIdentifiers);
    }

    public List<ProvisioneeIdentifier> getProvisioneeIdentifiers() {
        return this.provisioneeIdentifiers;
    }

    @Override // com.amazon.devicesetupservice.v1.AuthenticatedInput
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.provisioneeIdentifiers);
    }

    public void setProvisioneeIdentifiers(List<ProvisioneeIdentifier> list) {
        this.provisioneeIdentifiers = list;
    }
}
