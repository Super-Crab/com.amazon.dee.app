package com.amazon.alexa.redesign.utils.verifier;

import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter;
import com.amazon.alexa.redesign.entity.AlertBannerModel;
import com.amazon.alexa.redesign.entity.CardModel;
import com.amazon.alexa.redesign.utils.HomeMetricsRecorder;
import com.amazon.alexa.redesign.utils.HomeOEInteractor;
/* loaded from: classes10.dex */
public class OutageConditionCallback implements ConditionCallback {
    private boolean listHadAnOutage = false;
    private final Mobilytics mobilytics;
    private final long serverRespLatency;

    public OutageConditionCallback(Mobilytics mobilytics, long j) {
        this.mobilytics = mobilytics;
        this.serverRespLatency = j;
    }

    @Override // com.amazon.alexa.redesign.utils.verifier.ConditionCallback
    public void doThis(CardModel cardModel) {
        MobilyticsMetricsCounter createCounter = this.mobilytics.createCounter("jasper-home-native.outage", HomeMetricsRecorder.APP_COMPONENT, HomeOEInteractor.GET_FEED, OwnerIdentifier.MOBILE_ORG_HOME_FRONTEND);
        createCounter.incrementCounterByValue(this.serverRespLatency);
        this.mobilytics.recordCounter(createCounter);
    }

    @Override // com.amazon.alexa.redesign.utils.verifier.ConditionCallback
    public boolean isTrueWhen(CardModel cardModel) {
        boolean z = cardModel instanceof AlertBannerModel;
        if (z && !this.listHadAnOutage) {
            this.listHadAnOutage = z;
        }
        return z;
    }

    @Override // com.amazon.alexa.redesign.utils.verifier.ConditionCallback
    public void onComplete() {
        this.mobilytics.recordOccurrence("jasper-home-native.outageOccurrence", this.listHadAnOutage, HomeMetricsRecorder.APP_COMPONENT, HomeOEInteractor.GET_FEED, OwnerIdentifier.MOBILE_ORG_HOME_FRONTEND);
    }
}
