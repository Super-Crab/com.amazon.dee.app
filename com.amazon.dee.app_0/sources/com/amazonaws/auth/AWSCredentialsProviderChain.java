package com.amazonaws.auth;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazonaws.AmazonClientException;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.LinkedList;
import java.util.List;
/* loaded from: classes13.dex */
public class AWSCredentialsProviderChain implements AWSCredentialsProvider {
    private static final Log log = LogFactory.getLog(AWSCredentialsProviderChain.class);
    private AWSCredentialsProvider lastUsedProvider;
    private List<AWSCredentialsProvider> credentialsProviders = new LinkedList();
    private boolean reuseLastProvider = true;

    public AWSCredentialsProviderChain(AWSCredentialsProvider... aWSCredentialsProviderArr) {
        if (aWSCredentialsProviderArr != null && aWSCredentialsProviderArr.length != 0) {
            for (AWSCredentialsProvider aWSCredentialsProvider : aWSCredentialsProviderArr) {
                this.credentialsProviders.add(aWSCredentialsProvider);
            }
            return;
        }
        throw new IllegalArgumentException("No credential providers specified");
    }

    @Override // com.amazonaws.auth.AWSCredentialsProvider
    /* renamed from: getCredentials */
    public AWSCredentials mo6648getCredentials() {
        AWSCredentialsProvider aWSCredentialsProvider;
        if (this.reuseLastProvider && (aWSCredentialsProvider = this.lastUsedProvider) != null) {
            return aWSCredentialsProvider.mo6648getCredentials();
        }
        for (AWSCredentialsProvider aWSCredentialsProvider2 : this.credentialsProviders) {
            try {
                AWSCredentials mo6648getCredentials = aWSCredentialsProvider2.mo6648getCredentials();
                if (mo6648getCredentials.getAWSAccessKeyId() != null && mo6648getCredentials.getAWSSecretKey() != null) {
                    Log log2 = log;
                    log2.debug("Loading credentials from " + aWSCredentialsProvider2.toString());
                    this.lastUsedProvider = aWSCredentialsProvider2;
                    return mo6648getCredentials;
                }
            } catch (Exception e) {
                Log log3 = log;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to load credentials from ");
                outline107.append(aWSCredentialsProvider2.toString());
                outline107.append(RealTimeTextConstants.COLON_SPACE);
                outline107.append(e.getMessage());
                log3.debug(outline107.toString());
            }
        }
        throw new AmazonClientException("Unable to load AWS credentials from any provider in the chain");
    }

    public boolean getReuseLastProvider() {
        return this.reuseLastProvider;
    }

    @Override // com.amazonaws.auth.AWSCredentialsProvider
    public void refresh() {
        for (AWSCredentialsProvider aWSCredentialsProvider : this.credentialsProviders) {
            aWSCredentialsProvider.refresh();
        }
    }

    public void setReuseLastProvider(boolean z) {
        this.reuseLastProvider = z;
    }
}
