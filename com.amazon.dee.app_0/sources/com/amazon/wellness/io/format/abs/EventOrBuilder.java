package com.amazon.wellness.io.format.abs;

import com.amazon.wellness.io.format.abs.Event;
import com.google.protobuf.MessageOrBuilder;
/* loaded from: classes13.dex */
public interface EventOrBuilder extends MessageOrBuilder {
    int getAbsoluteUptimeMillisecondsHi();

    int getAbsoluteUptimeMillisecondsLo();

    BiometricDataPoint getBiometricDataPoint();

    BiometricDataPointOrBuilder getBiometricDataPointOrBuilder();

    Event.PayloadCase getPayloadCase();

    int getRelativeUptimeMilliseconds();

    SleepDomainTransitionEventV1 getSleepDomainTransitionEventV1();

    SleepDomainTransitionEventV1OrBuilder getSleepDomainTransitionEventV1OrBuilder();

    SynchronizeRealTime getSynchronizeRealTime();

    SynchronizeRealTimeOrBuilder getSynchronizeRealTimeOrBuilder();

    SynchronizeUptime getSynchronizeUptime();

    SynchronizeUptimeOrBuilder getSynchronizeUptimeOrBuilder();

    boolean hasBiometricDataPoint();

    boolean hasSleepDomainTransitionEventV1();

    boolean hasSynchronizeRealTime();

    boolean hasSynchronizeUptime();
}
