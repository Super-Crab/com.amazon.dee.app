package com.amazon.wellness.io.format.abs;

import com.google.protobuf.MessageOrBuilder;
/* loaded from: classes13.dex */
public interface SleepStateV1OrBuilder extends MessageOrBuilder {
    boolean getIntendToSleep();

    int getIntendToSleepLastChangeMsUptimeH();

    int getIntendToSleepLastChangeMsUptimeL();

    int getPercentageAsleep();

    boolean getShouldBeProcessedForSleep();
}
