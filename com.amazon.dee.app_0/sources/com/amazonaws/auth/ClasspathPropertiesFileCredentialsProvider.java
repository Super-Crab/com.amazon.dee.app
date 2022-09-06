package com.amazonaws.auth;

import com.amazonaws.AmazonClientException;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
@Deprecated
/* loaded from: classes13.dex */
public class ClasspathPropertiesFileCredentialsProvider implements AWSCredentialsProvider {
    private static String defaultPropertiesFile = "AwsCredentials.properties";
    private final String credentialsFilePath;

    public ClasspathPropertiesFileCredentialsProvider() {
        this(defaultPropertiesFile);
    }

    @Override // com.amazonaws.auth.AWSCredentialsProvider
    /* renamed from: getCredentials */
    public AWSCredentials mo6648getCredentials() {
        InputStream resourceAsStream = ClasspathPropertiesFileCredentialsProvider.class.getResourceAsStream(this.credentialsFilePath);
        if (resourceAsStream != null) {
            try {
                return new PropertiesCredentials(resourceAsStream);
            } catch (IOException e) {
                throw new AmazonClientException(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Unable to load AWS credentials from the "), this.credentialsFilePath, " file on the classpath"), e);
            }
        }
        throw new AmazonClientException(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Unable to load AWS credentials from the "), this.credentialsFilePath, " file on the classpath"));
    }

    @Override // com.amazonaws.auth.AWSCredentialsProvider
    public void refresh() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ClasspathPropertiesFileCredentialsProvider.class.getSimpleName());
        sb.append("(");
        return GeneratedOutlineSupport1.outline91(sb, this.credentialsFilePath, ")");
    }

    public ClasspathPropertiesFileCredentialsProvider(String str) {
        if (str != null) {
            if (!str.startsWith("/")) {
                this.credentialsFilePath = GeneratedOutlineSupport1.outline72("/", str);
                return;
            } else {
                this.credentialsFilePath = str;
                return;
            }
        }
        throw new IllegalArgumentException("Credentials file path cannot be null");
    }
}
