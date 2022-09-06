package com.amazon.alexa.audiopersonalization.api;

import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.protocol.Hearing;
/* loaded from: classes6.dex */
public interface AmaApi {
    void getAssessmentMode(String str, SuccessDelegate<Integer> successDelegate, ErrorDelegate errorDelegate);

    void getAudioProfile(String str, SuccessDelegate<Hearing.Audiogram> successDelegate, ErrorMessageDelegate errorMessageDelegate);

    void getBudsInEar(String str, @NonNull SuccessDelegate<Boolean> successDelegate, @NonNull ErrorDelegate errorDelegate);

    void getFeatureStatus(String str, SuccessDelegate<Boolean> successDelegate, ErrorMessageDelegate errorMessageDelegate);

    void getPersonalizationLevel(String str, SuccessDelegate<Integer> successDelegate, ErrorDelegate errorDelegate);

    void setAssessmentMode(int i, String str, CompletableDelegate completableDelegate);

    void setAudioProfile(Hearing.Audiogram audiogram, String str, CompletableDelegate completableDelegate);

    void setFeatureStatus(boolean z, String str, CompletableDelegate completableDelegate);

    void setPersonalizationLevel(int i, String str, CompletableDelegate completableDelegate);

    void subscribeToAudioAssessmentAbort(String str, @NonNull CompletableDelegate completableDelegate);

    void unsubscribeToAudioAssessmentAbort(String str);
}
