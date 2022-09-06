package com.amazon.alexa.handsfree.uservoicerecognition.ui.primer;

import com.amazon.alexa.handsfree.uservoicerecognition.ui.start.StartEnrollmentContract;
/* loaded from: classes8.dex */
public interface EnrollmentPrimerContract {

    /* loaded from: classes8.dex */
    public interface View extends StartEnrollmentContract.View {
        void fetchEnrollmentStatus();

        void hideLoading();

        void showInternetAlertDialogWithRetry();

        void showLoading();

        @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.start.StartEnrollmentContract.View
        void showStartEnrollmentVoicePrivacyOptedIn();

        void skipEnrollment();
    }
}
