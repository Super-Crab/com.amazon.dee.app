package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.CloudDriveBenefit;
/* loaded from: classes11.dex */
public class CloudDriveBenefitListDeserializer extends ListDeserializer<CloudDriveBenefit> {
    public static final CloudDriveBenefitListDeserializer INSTANCE = new CloudDriveBenefitListDeserializer();

    private CloudDriveBenefitListDeserializer() {
        super(CloudDriveBenefitDeserializer.INSTANCE);
    }
}
