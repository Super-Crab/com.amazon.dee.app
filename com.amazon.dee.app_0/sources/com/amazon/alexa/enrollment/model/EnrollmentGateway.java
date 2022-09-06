package com.amazon.alexa.enrollment.model;

import android.content.Context;
import com.amazon.alexa.enrollment.api.model.KidsEnrollmentMetadata;
/* loaded from: classes7.dex */
public interface EnrollmentGateway {
    void startKidsVoiceEnrollmentTraining(Context context, KidsEnrollmentMetadata kidsEnrollmentMetadata, String str);

    void startVoiceEnrollmentTraining(Context context);

    void startVoiceEnrollmentTraining(Context context, String str, String str2, String str3, String str4, String str5);
}
