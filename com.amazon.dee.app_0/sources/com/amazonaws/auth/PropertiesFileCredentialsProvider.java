package com.amazonaws.auth;

import com.amazonaws.AmazonClientException;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.IOException;
/* loaded from: classes13.dex */
public class PropertiesFileCredentialsProvider implements AWSCredentialsProvider {
    private final String credentialsFilePath;

    public PropertiesFileCredentialsProvider(String str) {
        if (str != null) {
            this.credentialsFilePath = str;
            return;
        }
        throw new IllegalArgumentException("Credentials file path cannot be null");
    }

    @Override // com.amazonaws.auth.AWSCredentialsProvider
    /* renamed from: getCredentials */
    public AWSCredentials mo6648getCredentials() {
        try {
            return new PropertiesCredentials(new File(this.credentialsFilePath));
        } catch (IOException e) {
            throw new AmazonClientException(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Unable to load AWS credentials from the "), this.credentialsFilePath, " file"), e);
        }
    }

    @Override // com.amazonaws.auth.AWSCredentialsProvider
    public void refresh() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PropertiesFileCredentialsProvider.class.getSimpleName());
        sb.append("(");
        return GeneratedOutlineSupport1.outline91(sb, this.credentialsFilePath, ")");
    }
}
