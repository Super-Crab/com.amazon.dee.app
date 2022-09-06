package com.amazon.wellness.io.format.abs;

import com.google.protobuf.MessageOrBuilder;
/* loaded from: classes13.dex */
public interface MovementSummaryV1OrBuilder extends MessageOrBuilder {
    float getMovementSummaryAggregate();

    float getMovementSummaryMean();

    float getMovementSummaryStandardDeviation();

    float getMovementSummaryTotal();

    int getMovementWindowSizeInSeconds();
}
