package com.amazon.alexa.mobilytics.s3;

import androidx.annotation.NonNull;
import com.amazon.alexa.mobilytics.auth.CognitoCredentialsProvider;
import com.amazon.alexa.mobilytics.auth.CredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.google.common.base.Preconditions;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes9.dex */
public class S3ClientProvider {
    public AmazonS3 create(@NonNull CredentialsProvider credentialsProvider, @NonNull Regions regions) {
        Preconditions.checkNotNull(credentialsProvider);
        if (credentialsProvider.type() == 0) {
            return new AmazonS3Client(((CognitoCredentialsProvider) credentialsProvider).awsCredentialsProvider(), Region.getRegion(regions));
        }
        throw new IllegalArgumentException("wrong credential provider type");
    }
}
