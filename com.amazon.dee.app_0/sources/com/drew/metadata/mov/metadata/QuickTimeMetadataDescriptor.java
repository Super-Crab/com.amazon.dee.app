package com.drew.metadata.mov.metadata;

import com.drew.metadata.mov.QuickTimeDescriptor;
import com.drew.metadata.mov.QuickTimeDirectory;
/* loaded from: classes2.dex */
public class QuickTimeMetadataDescriptor extends QuickTimeDescriptor {
    public QuickTimeMetadataDescriptor(QuickTimeDirectory quickTimeDirectory) {
        super(quickTimeDirectory);
    }

    @Override // com.drew.metadata.mov.QuickTimeDescriptor, com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        return super.getDescription(i);
    }
}
