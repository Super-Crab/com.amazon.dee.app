package com.amazon.wellness.io.format.abs;

import com.google.protobuf.MessageOrBuilder;
/* loaded from: classes13.dex */
public interface SleepDomainTransitionEventV1OrBuilder extends MessageOrBuilder {
    SleepDomainStateV1 getNewState();

    int getNewStateValue();

    SleepDomainStateV1 getPreviousState();

    int getPreviousStateValue();
}
