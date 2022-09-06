package com.amazon.wellness.io.format.abs;

import com.google.protobuf.MessageOrBuilder;
/* loaded from: classes13.dex */
public interface HeaderOrBuilder extends MessageOrBuilder {
    CompressionAlgorithm getCompressionAlgorithm();

    int getCompressionAlgorithmValue();

    SoftwareVersionV1 getSoftwareVersion1V1();

    SoftwareVersionV1OrBuilder getSoftwareVersion1V1OrBuilder();

    SoftwareVersionV1 getSoftwareVersion2V1();

    SoftwareVersionV1OrBuilder getSoftwareVersion2V1OrBuilder();

    boolean hasSoftwareVersion1V1();

    boolean hasSoftwareVersion2V1();
}
