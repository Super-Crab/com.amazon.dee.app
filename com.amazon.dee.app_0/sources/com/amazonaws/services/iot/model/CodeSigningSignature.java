package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.nio.ByteBuffer;
/* loaded from: classes13.dex */
public class CodeSigningSignature implements Serializable {
    private ByteBuffer inlineDocument;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CodeSigningSignature)) {
            return false;
        }
        CodeSigningSignature codeSigningSignature = (CodeSigningSignature) obj;
        if ((codeSigningSignature.getInlineDocument() == null) ^ (getInlineDocument() == null)) {
            return false;
        }
        return codeSigningSignature.getInlineDocument() == null || codeSigningSignature.getInlineDocument().equals(getInlineDocument());
    }

    public ByteBuffer getInlineDocument() {
        return this.inlineDocument;
    }

    public int hashCode() {
        return 31 + (getInlineDocument() == null ? 0 : getInlineDocument().hashCode());
    }

    public void setInlineDocument(ByteBuffer byteBuffer) {
        this.inlineDocument = byteBuffer;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getInlineDocument() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("inlineDocument: ");
            outline1072.append(getInlineDocument());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CodeSigningSignature withInlineDocument(ByteBuffer byteBuffer) {
        this.inlineDocument = byteBuffer;
        return this;
    }
}
