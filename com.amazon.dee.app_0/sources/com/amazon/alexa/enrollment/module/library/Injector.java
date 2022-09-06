package com.amazon.alexa.enrollment.module.library;

import android.content.Context;
import android.util.Log;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.enrollment.alexaservices.AlexaStateInteractor;
import com.amazon.alexa.enrollment.alexaservices.AlexaStateTracker;
import com.amazon.alexa.enrollment.exception.EnrollmentComponentNullException;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.model.EnrollmentGateway;
import com.amazon.alexa.enrollment.ui.AbstractEnrollmentActivity;
import com.amazon.alexa.enrollment.ui.complete.EnrollmentCompleteViewFragment;
import com.amazon.alexa.enrollment.ui.introduction.EnrollmentIntroductionViewFragment;
import com.amazon.alexa.enrollment.ui.kidsenrollment.KidsEnrollmentIntroductionActivity;
import com.amazon.alexa.enrollment.ui.kidsenrollment.KidsEnrollmentPopup;
import com.amazon.alexa.enrollment.ui.kidsenrollment.KidsEnrollmentPrimerViewFragment;
import com.amazon.alexa.enrollment.ui.privacySettings.EnrollmentPrivacyTermsActivity;
import com.amazon.alexa.enrollment.ui.privacySettings.EnrollmentPrivacyTermsViewFragment;
import com.amazon.alexa.enrollment.ui.terms.EnrollmentTermsViewFragment;
import com.amazon.alexa.enrollment.ui.training.EnrollmentTrainingViewFragment;
import com.amazon.alexa.enrollment.ui.views.EnrollmentTrainingProgressBar;
import com.amazon.alexa.enrollment.voiceSDK.client.AlexaVoiceSDKClient;
import com.amazon.alexa.enrollment.voiceSDK.userSpeechProviders.EnrollmentUserSpeechProvider;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.routing.api.RoutingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import com.dee.app.http.UrlResolver;
import dagger.Lazy;
/* loaded from: classes7.dex */
public final class Injector {
    private static final String TAG = GeneratedOutlineSupport1.outline39(Injector.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    private static EnrollmentComponent enrollmentComponent;

    private Injector() {
    }

    public static synchronized EnrollmentGateway enrollmentService() {
        EnrollmentGateway enrollmentService;
        synchronized (Injector.class) {
            enrollmentService = enrollmentComponent.enrollmentService();
        }
        return enrollmentService;
    }

    public static synchronized EnrollmentComponent initComponent(Lazy<Context> lazy, Lazy<CoralService> lazy2, Lazy<PersonIdProvider> lazy3, Lazy<Mobilytics> lazy4, Lazy<DeviceInformation> lazy5, Lazy<IdentityService> lazy6, Lazy<EventBus> lazy7, Lazy<RoutingService> lazy8, Lazy<UrlResolver> lazy9, Lazy<EnvironmentService> lazy10) {
        synchronized (Injector.class) {
            if (enrollmentComponent != null) {
                Log.w(TAG, "Enrollment component is already initialized, so not initializing again");
                return enrollmentComponent;
            }
            enrollmentComponent = DaggerEnrollmentComponent.builder().enrollmentLibraryModule(new EnrollmentLibraryModule(lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9, lazy10)).build();
            return enrollmentComponent;
        }
    }

    public static synchronized void inject(EnrollmentIntroductionViewFragment enrollmentIntroductionViewFragment) {
        synchronized (Injector.class) {
            enrollmentComponent.inject(enrollmentIntroductionViewFragment);
        }
    }

    public static synchronized void inject(KidsEnrollmentPrimerViewFragment kidsEnrollmentPrimerViewFragment) {
        synchronized (Injector.class) {
            enrollmentComponent.inject(kidsEnrollmentPrimerViewFragment);
        }
    }

    public static synchronized void inject(KidsEnrollmentPopup kidsEnrollmentPopup) {
        synchronized (Injector.class) {
            enrollmentComponent.inject(kidsEnrollmentPopup);
        }
    }

    public static synchronized void inject(KidsEnrollmentIntroductionActivity kidsEnrollmentIntroductionActivity) {
        synchronized (Injector.class) {
            enrollmentComponent.inject(kidsEnrollmentIntroductionActivity);
        }
    }

    public static synchronized void inject(AlexaStateInteractor alexaStateInteractor) {
        synchronized (Injector.class) {
            enrollmentComponent.inject(alexaStateInteractor);
        }
    }

    public static synchronized void inject(AlexaStateTracker alexaStateTracker) {
        synchronized (Injector.class) {
            enrollmentComponent.inject(alexaStateTracker);
        }
    }

    public static synchronized void inject(EnrollmentUserSpeechProvider enrollmentUserSpeechProvider) {
        synchronized (Injector.class) {
            enrollmentComponent.inject(enrollmentUserSpeechProvider);
        }
    }

    public static synchronized void inject(AlexaVoiceSDKClient alexaVoiceSDKClient) {
        synchronized (Injector.class) {
            enrollmentComponent.inject(alexaVoiceSDKClient);
        }
    }

    public static synchronized void inject(EnrollmentTrainingViewFragment enrollmentTrainingViewFragment) {
        synchronized (Injector.class) {
            enrollmentComponent.inject(enrollmentTrainingViewFragment);
        }
    }

    public static synchronized void inject(EnrollmentTermsViewFragment enrollmentTermsViewFragment) {
        synchronized (Injector.class) {
            enrollmentComponent.inject(enrollmentTermsViewFragment);
        }
    }

    public static synchronized void inject(EnrollmentCompleteViewFragment enrollmentCompleteViewFragment) {
        synchronized (Injector.class) {
            enrollmentComponent.inject(enrollmentCompleteViewFragment);
        }
    }

    public static synchronized void inject(AbstractEnrollmentActivity abstractEnrollmentActivity) {
        synchronized (Injector.class) {
            if (enrollmentComponent != null) {
                enrollmentComponent.inject(abstractEnrollmentActivity);
            } else {
                throw new EnrollmentComponentNullException("Enrollment Component cannot be null");
            }
        }
    }

    public static synchronized void inject(EnrollmentPrivacyTermsViewFragment enrollmentPrivacyTermsViewFragment) {
        synchronized (Injector.class) {
            enrollmentComponent.inject(enrollmentPrivacyTermsViewFragment);
        }
    }

    public static synchronized void inject(EnrollmentPrivacyTermsActivity enrollmentPrivacyTermsActivity) {
        synchronized (Injector.class) {
            enrollmentComponent.inject(enrollmentPrivacyTermsActivity);
        }
    }

    public static synchronized void inject(EnrollmentTrainingProgressBar enrollmentTrainingProgressBar) {
        synchronized (Injector.class) {
            enrollmentComponent.inject(enrollmentTrainingProgressBar);
        }
    }
}
