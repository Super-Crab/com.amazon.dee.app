package com.amazon.alexa.enrollment.module.library;

import com.amazon.alexa.enrollment.alexaservices.AlexaStateInteractor;
import com.amazon.alexa.enrollment.alexaservices.AlexaStateTracker;
import com.amazon.alexa.enrollment.model.EnrollmentGateway;
import com.amazon.alexa.enrollment.ui.AbstractEnrollmentActivity;
import com.amazon.alexa.enrollment.ui.complete.EnrollmentCompleteViewFragment;
import com.amazon.alexa.enrollment.ui.introduction.EnrollmentIntroductionViewFragment;
import com.amazon.alexa.enrollment.ui.kidsenrollment.KidsEnrollmentIntroductionActivity;
import com.amazon.alexa.enrollment.ui.kidsenrollment.KidsEnrollmentPopup;
import com.amazon.alexa.enrollment.ui.kidsenrollment.KidsEnrollmentPrimerViewFragment;
import com.amazon.alexa.enrollment.ui.privacySettings.EnrollmentPrivacyTermsViewFragment;
import com.amazon.alexa.enrollment.ui.terms.EnrollmentTermsViewFragment;
import com.amazon.alexa.enrollment.ui.training.EnrollmentTrainingViewFragment;
import com.amazon.alexa.enrollment.ui.views.EnrollmentTrainingProgressBar;
import com.amazon.alexa.enrollment.voiceSDK.client.AlexaVoiceSDKClient;
import com.amazon.alexa.enrollment.voiceSDK.userSpeechProviders.EnrollmentUserSpeechProvider;
import dagger.Component;
@Component(modules = {EnrollmentLibraryModule.class, EnrollmentViewModelModule.class})
/* loaded from: classes7.dex */
public interface EnrollmentComponent {
    EnrollmentGateway enrollmentService();

    void inject(AlexaStateInteractor alexaStateInteractor);

    void inject(AlexaStateTracker alexaStateTracker);

    void inject(AbstractEnrollmentActivity abstractEnrollmentActivity);

    void inject(EnrollmentCompleteViewFragment enrollmentCompleteViewFragment);

    void inject(EnrollmentIntroductionViewFragment enrollmentIntroductionViewFragment);

    void inject(KidsEnrollmentIntroductionActivity kidsEnrollmentIntroductionActivity);

    void inject(KidsEnrollmentPopup kidsEnrollmentPopup);

    void inject(KidsEnrollmentPrimerViewFragment kidsEnrollmentPrimerViewFragment);

    void inject(EnrollmentPrivacyTermsViewFragment enrollmentPrivacyTermsViewFragment);

    void inject(EnrollmentTermsViewFragment enrollmentTermsViewFragment);

    void inject(EnrollmentTrainingViewFragment enrollmentTrainingViewFragment);

    void inject(EnrollmentTrainingProgressBar enrollmentTrainingProgressBar);

    void inject(AlexaVoiceSDKClient alexaVoiceSDKClient);

    void inject(EnrollmentUserSpeechProvider enrollmentUserSpeechProvider);
}
