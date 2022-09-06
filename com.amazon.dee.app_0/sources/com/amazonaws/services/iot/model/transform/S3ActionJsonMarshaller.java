package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.S3Action;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class S3ActionJsonMarshaller {
    private static S3ActionJsonMarshaller instance;

    S3ActionJsonMarshaller() {
    }

    public static S3ActionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new S3ActionJsonMarshaller();
        }
        return instance;
    }

    public void marshall(S3Action s3Action, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (s3Action.getRoleArn() != null) {
            String roleArn = s3Action.getRoleArn();
            awsJsonWriter.name("roleArn");
            awsJsonWriter.value(roleArn);
        }
        if (s3Action.getBucketName() != null) {
            String bucketName = s3Action.getBucketName();
            awsJsonWriter.name("bucketName");
            awsJsonWriter.value(bucketName);
        }
        if (s3Action.getKey() != null) {
            String key = s3Action.getKey();
            awsJsonWriter.name("key");
            awsJsonWriter.value(key);
        }
        if (s3Action.getCannedAcl() != null) {
            String cannedAcl = s3Action.getCannedAcl();
            awsJsonWriter.name("cannedAcl");
            awsJsonWriter.value(cannedAcl);
        }
        awsJsonWriter.endObject();
    }
}
