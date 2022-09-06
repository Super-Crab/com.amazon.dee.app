package com.amazon.alexa.handsfree.protocols.metrics.factories;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import java.util.Objects;
/* loaded from: classes8.dex */
public class HandsFreeSetupMetricData {
    private final String mActionType;
    private final String mComponent;
    private final String mEventType;
    private final String mPageType;
    private final String mSubPageType;

    @VisibleForTesting
    /* loaded from: classes8.dex */
    enum SubPageType {
        NONE
    }

    public HandsFreeSetupMetricData(@NonNull Enum r1, @NonNull Enum r2, @NonNull Enum r3, @NonNull Enum r4, @NonNull Enum r5) {
        this.mActionType = r1.name();
        this.mEventType = r2.name();
        this.mComponent = r3.name();
        this.mPageType = r4.name();
        this.mSubPageType = r5.name();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || HandsFreeSetupMetricData.class != obj.getClass()) {
            return false;
        }
        HandsFreeSetupMetricData handsFreeSetupMetricData = (HandsFreeSetupMetricData) obj;
        return handsFreeSetupMetricData.mActionType.equals(this.mActionType) && handsFreeSetupMetricData.mComponent.equals(this.mComponent) && handsFreeSetupMetricData.mEventType.equals(this.mEventType) && handsFreeSetupMetricData.mPageType.equals(this.mPageType) && handsFreeSetupMetricData.mSubPageType.equals(this.mSubPageType);
    }

    public String getActionType() {
        return this.mActionType;
    }

    public String getComponent() {
        return this.mComponent;
    }

    public String getEventType() {
        return this.mEventType;
    }

    public String getPageType() {
        return this.mPageType;
    }

    public String getSubPageType() {
        return this.mSubPageType;
    }

    public int hashCode() {
        return Objects.hash(this.mActionType, this.mComponent, this.mEventType, this.mPageType, this.mSubPageType);
    }

    public HandsFreeSetupMetricData(@NonNull Enum r7, @NonNull Enum r8, @NonNull Enum r9, @NonNull Enum r10) {
        this(r7, r8, r9, r10, SubPageType.NONE);
    }
}
