package com.amazon.dee.app.services.metrics.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.dee.app.services.logging.Log;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public final class CognitoIdentityProvider {
    private static final String COGNITO_ID_UNKNOWN = "Unknown";
    private static final int IDENTITY_ID_MAX_LEN = 55;
    private static final String TAG = Log.tag(CognitoIdentityProvider.class);
    private AWSCredentialsProvider awsCredentialsProvider;

    public CognitoIdentityProvider(@NonNull AWSCredentialsProvider aWSCredentialsProvider) {
        this.awsCredentialsProvider = aWSCredentialsProvider;
    }

    @Nullable
    public String getCognitoId() {
        AWSCredentialsProvider aWSCredentialsProvider = this.awsCredentialsProvider;
        if (aWSCredentialsProvider instanceof CognitoCachingCredentialsProvider) {
            String cachedIdentityId = ((CognitoCachingCredentialsProvider) aWSCredentialsProvider).getCachedIdentityId();
            if (cachedIdentityId == null) {
                try {
                    cachedIdentityId = ((CognitoCachingCredentialsProvider) this.awsCredentialsProvider).getIdentityId();
                } catch (Exception e) {
                    String str = TAG;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error in fetching cognito details ");
                    outline107.append(e.getMessage());
                    Log.e(str, outline107.toString());
                    return "Unknown";
                }
            }
            int i = 55;
            if (cachedIdentityId.length() < 55) {
                i = cachedIdentityId.length();
            }
            return cachedIdentityId.substring(0, i);
        }
        return null;
    }
}
