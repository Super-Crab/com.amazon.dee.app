package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.CloudDriveGrant;
/* loaded from: classes11.dex */
public class CloudDriveGrantListDeserializer extends ListDeserializer<CloudDriveGrant> {
    public static final CloudDriveGrantListDeserializer INSTANCE = new CloudDriveGrantListDeserializer();

    private CloudDriveGrantListDeserializer() {
        super(CloudDriveGrantDeserializer.INSTANCE);
    }
}
