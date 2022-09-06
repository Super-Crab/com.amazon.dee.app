package com.amazon.alexa.enrollment.ui.introduction;

import com.amazon.alexa.enrollment.api.model.GetVoiceEnrollmentEligibilityResponse;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.enrollment.ui.introduction.-$$Lambda$EnrollmentIntroductionViewModel$wo11k2R96pTDNObZMP9qvb0nmfU  reason: invalid class name */
/* loaded from: classes7.dex */
public final /* synthetic */ class $$Lambda$EnrollmentIntroductionViewModel$wo11k2R96pTDNObZMP9qvb0nmfU implements Function {
    public static final /* synthetic */ $$Lambda$EnrollmentIntroductionViewModel$wo11k2R96pTDNObZMP9qvb0nmfU INSTANCE = new $$Lambda$EnrollmentIntroductionViewModel$wo11k2R96pTDNObZMP9qvb0nmfU();

    private /* synthetic */ $$Lambda$EnrollmentIntroductionViewModel$wo11k2R96pTDNObZMP9qvb0nmfU() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        Boolean valueOf;
        valueOf = Boolean.valueOf(((GetVoiceEnrollmentEligibilityResponse) obj).isVoiceEnrollmentEligibility());
        return valueOf;
    }
}
