package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class OTAUpdateFile implements Serializable {
    private Map<String, String> attributes;
    private CodeSigning codeSigning;
    private FileLocation fileLocation;
    private String fileName;
    private String fileVersion;

    public OTAUpdateFile addattributesEntry(String str, String str2) {
        if (this.attributes == null) {
            this.attributes = new HashMap();
        }
        if (!this.attributes.containsKey(str)) {
            this.attributes.put(str, str2);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public OTAUpdateFile clearattributesEntries() {
        this.attributes = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof OTAUpdateFile)) {
            return false;
        }
        OTAUpdateFile oTAUpdateFile = (OTAUpdateFile) obj;
        if ((oTAUpdateFile.getFileName() == null) ^ (getFileName() == null)) {
            return false;
        }
        if (oTAUpdateFile.getFileName() != null && !oTAUpdateFile.getFileName().equals(getFileName())) {
            return false;
        }
        if ((oTAUpdateFile.getFileVersion() == null) ^ (getFileVersion() == null)) {
            return false;
        }
        if (oTAUpdateFile.getFileVersion() != null && !oTAUpdateFile.getFileVersion().equals(getFileVersion())) {
            return false;
        }
        if ((oTAUpdateFile.getFileLocation() == null) ^ (getFileLocation() == null)) {
            return false;
        }
        if (oTAUpdateFile.getFileLocation() != null && !oTAUpdateFile.getFileLocation().equals(getFileLocation())) {
            return false;
        }
        if ((oTAUpdateFile.getCodeSigning() == null) ^ (getCodeSigning() == null)) {
            return false;
        }
        if (oTAUpdateFile.getCodeSigning() != null && !oTAUpdateFile.getCodeSigning().equals(getCodeSigning())) {
            return false;
        }
        if ((oTAUpdateFile.getAttributes() == null) ^ (getAttributes() == null)) {
            return false;
        }
        return oTAUpdateFile.getAttributes() == null || oTAUpdateFile.getAttributes().equals(getAttributes());
    }

    public Map<String, String> getAttributes() {
        return this.attributes;
    }

    public CodeSigning getCodeSigning() {
        return this.codeSigning;
    }

    public FileLocation getFileLocation() {
        return this.fileLocation;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getFileVersion() {
        return this.fileVersion;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((getFileName() == null ? 0 : getFileName().hashCode()) + 31) * 31) + (getFileVersion() == null ? 0 : getFileVersion().hashCode())) * 31) + (getFileLocation() == null ? 0 : getFileLocation().hashCode())) * 31) + (getCodeSigning() == null ? 0 : getCodeSigning().hashCode())) * 31;
        if (getAttributes() != null) {
            i = getAttributes().hashCode();
        }
        return hashCode + i;
    }

    public void setAttributes(Map<String, String> map) {
        this.attributes = map;
    }

    public void setCodeSigning(CodeSigning codeSigning) {
        this.codeSigning = codeSigning;
    }

    public void setFileLocation(FileLocation fileLocation) {
        this.fileLocation = fileLocation;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public void setFileVersion(String str) {
        this.fileVersion = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getFileName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("fileName: ");
            outline1072.append(getFileName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getFileVersion() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("fileVersion: ");
            outline1073.append(getFileVersion());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getFileLocation() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("fileLocation: ");
            outline1074.append(getFileLocation());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getCodeSigning() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("codeSigning: ");
            outline1075.append(getCodeSigning());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getAttributes() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("attributes: ");
            outline1076.append(getAttributes());
            outline107.append(outline1076.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public OTAUpdateFile withAttributes(Map<String, String> map) {
        this.attributes = map;
        return this;
    }

    public OTAUpdateFile withCodeSigning(CodeSigning codeSigning) {
        this.codeSigning = codeSigning;
        return this;
    }

    public OTAUpdateFile withFileLocation(FileLocation fileLocation) {
        this.fileLocation = fileLocation;
        return this;
    }

    public OTAUpdateFile withFileName(String str) {
        this.fileName = str;
        return this;
    }

    public OTAUpdateFile withFileVersion(String str) {
        this.fileVersion = str;
        return this;
    }
}
