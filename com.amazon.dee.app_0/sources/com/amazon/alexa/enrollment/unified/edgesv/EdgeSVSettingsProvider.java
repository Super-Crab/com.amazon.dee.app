package com.amazon.alexa.enrollment.unified.edgesv;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.enrollment.unified.api.SettingsProvider;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.wakeword.speakerverification.SpeakerVerificationAuthority;
import com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class EdgeSVSettingsProvider implements SettingsProvider {
    private final PersonIdProvider mPersonIdProvider;
    private final SpeakerVerificationAuthority mSpeakerVerificationAuthority;

    public EdgeSVSettingsProvider(@NonNull Context context, @NonNull SpeakerVerificationMetricsListener speakerVerificationMetricsListener) {
        this(new SpeakerVerificationAuthority(context, speakerVerificationMetricsListener), (PersonIdProvider) GeneratedOutlineSupport1.outline21(PersonIdProvider.class));
    }

    @Override // com.amazon.alexa.enrollment.unified.api.SettingsProvider
    public boolean isUVREnrolled() {
        PersonIdProvider personIdProvider = this.mPersonIdProvider;
        if (personIdProvider == null || personIdProvider.getPersonId() == null) {
            return false;
        }
        return this.mSpeakerVerificationAuthority.hasProfile(this.mPersonIdProvider.getPersonId());
    }

    @Override // com.amazon.alexa.enrollment.unified.api.SettingsProvider
    public void removeUVRModel(@NonNull ResponseCallback responseCallback) {
        PersonIdProvider personIdProvider = this.mPersonIdProvider;
        if (personIdProvider == null) {
            responseCallback.onError(new CallbackErrorMetadata("PersonIdProvider not available in ComponentRegistry!"));
        } else if (personIdProvider.getPersonId() == null) {
            responseCallback.onError(new CallbackErrorMetadata("No Person ID!"));
        } else if (!isUVREnrolled()) {
            responseCallback.onError(new CallbackErrorMetadata("Current user is not enrolled!"));
        } else if (this.mSpeakerVerificationAuthority.deleteUserData(this.mPersonIdProvider.getPersonId())) {
            responseCallback.onSuccess();
        } else {
            responseCallback.onError(new CallbackErrorMetadata("Unable to delete model!"));
        }
    }

    @Override // com.amazon.alexa.enrollment.unified.api.SettingsProvider
    public void isUVREnrolled(@NonNull ResultCallback<Boolean> resultCallback) {
        PersonIdProvider personIdProvider = this.mPersonIdProvider;
        if (personIdProvider != null && personIdProvider.getPersonId() != null) {
            resultCallback.onResult(Boolean.valueOf(isUVREnrolled()));
        } else {
            resultCallback.onError(new CallbackErrorMetadata("Unable to get PersonId!"));
        }
    }

    @VisibleForTesting
    EdgeSVSettingsProvider(@NonNull SpeakerVerificationAuthority speakerVerificationAuthority, @Nullable PersonIdProvider personIdProvider) {
        this.mSpeakerVerificationAuthority = speakerVerificationAuthority;
        this.mPersonIdProvider = personIdProvider;
    }
}
