package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class GetJobDocumentResult implements Serializable {
    private String document;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetJobDocumentResult)) {
            return false;
        }
        GetJobDocumentResult getJobDocumentResult = (GetJobDocumentResult) obj;
        if ((getJobDocumentResult.getDocument() == null) ^ (getDocument() == null)) {
            return false;
        }
        return getJobDocumentResult.getDocument() == null || getJobDocumentResult.getDocument().equals(getDocument());
    }

    public String getDocument() {
        return this.document;
    }

    public int hashCode() {
        return 31 + (getDocument() == null ? 0 : getDocument().hashCode());
    }

    public void setDocument(String str) {
        this.document = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getDocument() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("document: ");
            outline1072.append(getDocument());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public GetJobDocumentResult withDocument(String str) {
        this.document = str;
        return this;
    }
}
