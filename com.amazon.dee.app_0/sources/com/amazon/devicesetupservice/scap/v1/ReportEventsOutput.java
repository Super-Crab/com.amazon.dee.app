package com.amazon.devicesetupservice.scap.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class ReportEventsOutput extends BaseOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.scap.v1.ReportEventsOutput");
    private Action action;

    @Override // com.amazon.devicesetupservice.scap.v1.BaseOutput
    public boolean equals(Object obj) {
        if (!(obj instanceof ReportEventsOutput)) {
            return false;
        }
        return super.equals(obj) && Helper.equals(this.action, ((ReportEventsOutput) obj).action);
    }

    public Action getAction() {
        return this.action;
    }

    @Override // com.amazon.devicesetupservice.scap.v1.BaseOutput
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.action);
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
