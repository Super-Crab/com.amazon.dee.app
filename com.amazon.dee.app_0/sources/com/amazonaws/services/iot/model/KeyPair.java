package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class KeyPair implements Serializable {
    private String privateKey;
    private String publicKey;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof KeyPair)) {
            return false;
        }
        KeyPair keyPair = (KeyPair) obj;
        if ((keyPair.getPublicKey() == null) ^ (getPublicKey() == null)) {
            return false;
        }
        if (keyPair.getPublicKey() != null && !keyPair.getPublicKey().equals(getPublicKey())) {
            return false;
        }
        if ((keyPair.getPrivateKey() == null) ^ (getPrivateKey() == null)) {
            return false;
        }
        return keyPair.getPrivateKey() == null || keyPair.getPrivateKey().equals(getPrivateKey());
    }

    public String getPrivateKey() {
        return this.privateKey;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getPublicKey() == null ? 0 : getPublicKey().hashCode()) + 31) * 31;
        if (getPrivateKey() != null) {
            i = getPrivateKey().hashCode();
        }
        return hashCode + i;
    }

    public void setPrivateKey(String str) {
        this.privateKey = str;
    }

    public void setPublicKey(String str) {
        this.publicKey = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getPublicKey() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("PublicKey: ");
            outline1072.append(getPublicKey());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getPrivateKey() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("PrivateKey: ");
            outline1073.append(getPrivateKey());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public KeyPair withPrivateKey(String str) {
        this.privateKey = str;
        return this;
    }

    public KeyPair withPublicKey(String str) {
        this.publicKey = str;
        return this;
    }
}
