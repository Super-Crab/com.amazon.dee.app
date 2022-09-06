package com.amazon.wellness.io.format.abs;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
/* loaded from: classes13.dex */
public interface SoftwareVersionV1OrBuilder extends MessageOrBuilder {
    int getBuildVersion();

    int getMajorVersion();

    int getMinorVersion();

    int getPatchVersion();

    String getVersionDescriptor();

    ByteString getVersionDescriptorBytes();
}
