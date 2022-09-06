package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.feature.consumer.api.FeatureFlagConsumer;
import com.amazon.alexa.utils.TimeProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Provider;
/* compiled from: FeatureFlagConfigurationAuthority.java */
/* loaded from: classes.dex */
public class HHC implements Runnable {
    public final /* synthetic */ gSO zZm;

    public HHC(gSO gso) {
        this.zZm = gso;
    }

    @Override // java.lang.Runnable
    public void run() {
        TimeProvider timeProvider;
        FeatureFlagConsumer featureFlagConsumer;
        TimeProvider timeProvider2;
        Provider provider;
        timeProvider = this.zZm.Qle;
        long elapsedRealTime = timeProvider.elapsedRealTime();
        featureFlagConsumer = this.zZm.zQM;
        featureFlagConsumer.load(gSO.BIo);
        timeProvider2 = this.zZm.Qle;
        long elapsedRealTime2 = timeProvider2.elapsedRealTime() - elapsedRealTime;
        String str = gSO.zZm;
        GeneratedOutlineSupport1.outline153("Feature loading took: ", elapsedRealTime2);
        provider = this.zZm.jiA;
        ((AlexaClientEventBus) provider.mo10268get()).zyO(new Emg(elapsedRealTime2));
    }
}
