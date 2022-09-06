package com.amazon.devicesetupservice.wififfs;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetup.common.v1.ReportInput;
/* loaded from: classes12.dex */
public class InternalReportInput extends DeviceAuthenticatedInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.wififfs.InternalReportInput");
    private ReportInput reportInputData;

    @Override // com.amazon.devicesetupservice.wififfs.DeviceAuthenticatedInput
    public boolean equals(Object obj) {
        if (!(obj instanceof InternalReportInput)) {
            return false;
        }
        return super.equals(obj) && Helper.equals(this.reportInputData, ((InternalReportInput) obj).reportInputData);
    }

    public ReportInput getReportInputData() {
        return this.reportInputData;
    }

    @Override // com.amazon.devicesetupservice.wififfs.DeviceAuthenticatedInput
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.reportInputData);
    }

    public void setReportInputData(ReportInput reportInput) {
        this.reportInputData = reportInput;
    }
}
