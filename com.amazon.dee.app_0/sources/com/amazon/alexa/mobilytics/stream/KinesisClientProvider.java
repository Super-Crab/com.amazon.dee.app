package com.amazon.alexa.mobilytics.stream;

import androidx.annotation.NonNull;
import com.amazon.alexa.mobilytics.auth.CognitoCredentialsProvider;
import com.amazon.alexa.mobilytics.auth.CredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesis.AmazonKinesisClient;
import com.google.common.base.Preconditions;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes9.dex */
public class KinesisClientProvider {
    public AmazonKinesisClient create(@NonNull CredentialsProvider credentialsProvider, @NonNull Regions regions) {
        Preconditions.checkNotNull(credentialsProvider);
        if (credentialsProvider.type() == 0) {
            AmazonKinesisClient amazonKinesisClient = new AmazonKinesisClient(((CognitoCredentialsProvider) credentialsProvider).awsCredentialsProvider());
            amazonKinesisClient.setRegion(Region.getRegion(regions));
            return amazonKinesisClient;
        }
        throw new IllegalArgumentException("wrong credential provider type");
    }
}
