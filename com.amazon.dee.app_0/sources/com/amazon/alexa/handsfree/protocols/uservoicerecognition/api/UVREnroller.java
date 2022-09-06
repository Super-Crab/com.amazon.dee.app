package com.amazon.alexa.handsfree.protocols.uservoicerecognition.api;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.callback.EnrollmentCallback;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UserInfo;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UtteranceInfo;
import java.util.List;
/* loaded from: classes8.dex */
public interface UVREnroller {
    void cancelUserVoiceEnrollment(@NonNull ResponseCallback responseCallback);

    void cancelUtteranceTraining(@NonNull ResponseCallback responseCallback);

    void finishUserVoiceEnrollment(@NonNull ResultCallback<Double> resultCallback);

    @NonNull
    List<UtteranceInfo> getUtterances();

    void startUserVoiceEnrollment(@NonNull UserInfo userInfo, @NonNull ResponseCallback responseCallback);

    void startUtteranceTraining(@NonNull UtteranceInfo utteranceInfo, @NonNull EnrollmentCallback enrollmentCallback);
}
