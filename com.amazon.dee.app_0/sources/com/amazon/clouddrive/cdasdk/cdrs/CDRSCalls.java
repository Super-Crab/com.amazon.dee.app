package com.amazon.clouddrive.cdasdk.cdrs;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes11.dex */
public interface CDRSCalls {
    @NonNull
    Single<OnboardingContextResponse> createOnboardingContext(@NonNull CreateOnboardingContextRequest createOnboardingContextRequest);
}
