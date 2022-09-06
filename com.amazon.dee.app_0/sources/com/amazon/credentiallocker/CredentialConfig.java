package com.amazon.credentiallocker;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.util.Map;
/* loaded from: classes12.dex */
public class CredentialConfig implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.credentiallocker.CredentialConfig");
    private Map<String, String> credentialContent;
    private String credentialId;

    public boolean equals(Object obj) {
        if (!(obj instanceof CredentialConfig)) {
            return false;
        }
        CredentialConfig credentialConfig = (CredentialConfig) obj;
        return Helper.equals(this.credentialContent, credentialConfig.credentialContent) && Helper.equals(this.credentialId, credentialConfig.credentialId);
    }

    public Map<String, String> getCredentialContent() {
        return this.credentialContent;
    }

    public String getCredentialId() {
        return this.credentialId;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.credentialContent, this.credentialId);
    }

    public void setCredentialContent(Map<String, String> map) {
        this.credentialContent = map;
    }

    public void setCredentialId(String str) {
        this.credentialId = str;
    }
}
