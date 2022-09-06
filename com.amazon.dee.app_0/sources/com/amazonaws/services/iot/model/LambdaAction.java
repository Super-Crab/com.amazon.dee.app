package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class LambdaAction implements Serializable {
    private String functionArn;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof LambdaAction)) {
            return false;
        }
        LambdaAction lambdaAction = (LambdaAction) obj;
        if ((lambdaAction.getFunctionArn() == null) ^ (getFunctionArn() == null)) {
            return false;
        }
        return lambdaAction.getFunctionArn() == null || lambdaAction.getFunctionArn().equals(getFunctionArn());
    }

    public String getFunctionArn() {
        return this.functionArn;
    }

    public int hashCode() {
        return 31 + (getFunctionArn() == null ? 0 : getFunctionArn().hashCode());
    }

    public void setFunctionArn(String str) {
        this.functionArn = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getFunctionArn() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("functionArn: ");
            outline1072.append(getFunctionArn());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public LambdaAction withFunctionArn(String str) {
        this.functionArn = str;
        return this;
    }
}
