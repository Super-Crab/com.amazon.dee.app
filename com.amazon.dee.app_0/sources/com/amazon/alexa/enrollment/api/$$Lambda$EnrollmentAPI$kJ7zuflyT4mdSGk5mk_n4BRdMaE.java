package com.amazon.alexa.enrollment.api;

import com.amazon.alexa.enrollment.api.model.GetVoiceEnrollmentStatusResponse;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.enrollment.api.-$$Lambda$EnrollmentAPI$kJ7zuflyT4mdSGk5mk_n4BRdMaE  reason: invalid class name */
/* loaded from: classes7.dex */
public final /* synthetic */ class $$Lambda$EnrollmentAPI$kJ7zuflyT4mdSGk5mk_n4BRdMaE implements Consumer {
    public static final /* synthetic */ $$Lambda$EnrollmentAPI$kJ7zuflyT4mdSGk5mk_n4BRdMaE INSTANCE = new $$Lambda$EnrollmentAPI$kJ7zuflyT4mdSGk5mk_n4BRdMaE();

    private /* synthetic */ $$Lambda$EnrollmentAPI$kJ7zuflyT4mdSGk5mk_n4BRdMaE() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        EnrollmentAPI.lambda$getVoiceEnrollmentStatus$4((GetVoiceEnrollmentStatusResponse) obj);
    }
}
