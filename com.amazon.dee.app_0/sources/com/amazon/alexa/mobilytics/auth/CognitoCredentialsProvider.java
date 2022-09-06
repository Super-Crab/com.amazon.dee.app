package com.amazon.alexa.mobilytics.auth;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.alexa.mobilytics.auth.CredentialsProvider;
import com.amazon.alexa.mobilytics.configuration.Endpoint;
import com.amazon.alexa.mobilytics.configuration.KinesisEndpoint;
import com.amazon.alexa.mobilytics.util.Log;
import com.amazon.alexa.mobilytics.util.Utils;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.google.common.base.Preconditions;
import javax.inject.Inject;
import javax.inject.Singleton;
/* loaded from: classes9.dex */
public final class CognitoCredentialsProvider implements CredentialsProvider {
    private static final String TAG = Log.tag(CognitoCredentialsProvider.class);
    final CognitoCachingCredentialsProvider credentialsProvider;

    public AWSCredentialsProvider awsCredentialsProvider() {
        return this.credentialsProvider;
    }

    @Override // com.amazon.alexa.mobilytics.auth.CredentialsProvider
    public String id(boolean z) {
        try {
            return (!z || !TextUtils.isEmpty(this.credentialsProvider.getCachedIdentityId())) ? "Unknown" : Utils.trimString(this.credentialsProvider.getIdentityId(), 55);
        } catch (Exception e) {
            Log.e(TAG, e, "Error fetching ID", new Object[0]);
            return "Unknown";
        }
    }

    @Override // com.amazon.alexa.mobilytics.auth.CredentialsProvider
    public int type() {
        return 0;
    }

    private CognitoCredentialsProvider(@NonNull Context context, @NonNull String str, @NonNull Regions regions) {
        this.credentialsProvider = new CognitoCachingCredentialsProvider(context, str, regions);
    }

    @Singleton
    /* loaded from: classes9.dex */
    public static class Factory implements CredentialsProvider.Factory {
        private final Context context;

        @Inject
        public Factory(Context context) {
            this.context = (Context) Preconditions.checkNotNull(context);
        }

        @Override // com.amazon.alexa.mobilytics.auth.CredentialsProvider.Factory
        public CredentialsProvider create(@NonNull Endpoint endpoint) {
            Preconditions.checkNotNull(endpoint);
            if (endpoint.type() == 0) {
                KinesisEndpoint kinesisEndpoint = (KinesisEndpoint) endpoint;
                return new CognitoCredentialsProvider(this.context, kinesisEndpoint.cognitoIdentityPoolId(), kinesisEndpoint.awsRegion());
            }
            throw new IllegalArgumentException("only Kinesis Endpoints are supported");
        }

        @Override // com.amazon.alexa.mobilytics.auth.CredentialsProvider.Factory
        public CredentialsProvider create(@NonNull String str, @NonNull Regions regions) {
            return new CognitoCredentialsProvider(this.context, str, regions);
        }
    }
}
