package com.amazon.alexa.fitness.dagger;

import com.amazon.alexa.fitness.sdk.FitnessSdk;
import com.amazon.alexa.fitness.service.AlexaFitnessCapabilityAgentService;
import com.amazon.alexa.fitness.service.AlexaFitnessManagerImpl;
/* loaded from: classes8.dex */
public interface StaticReleasePackageComponent {
    AlexaFitnessCapabilityAgentService.SubComponent alexaFitnessCapabilityAgentServiceSubComponent();

    AlexaFitnessManagerImpl.SubComponent alexaFitnessManagerSubComponent();

    FitnessSdk.SubComponent fitnessSdkSubComponent();
}
