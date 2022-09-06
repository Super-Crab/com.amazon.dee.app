package com.amazon.alexa.enrollment.model;

import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.enrollment.api.model.KidsEnrollmentMetadata;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.ui.introduction.EnrollmentIntroductionActivity;
import com.amazon.alexa.enrollment.ui.kidsenrollment.KidsEnrollmentIntroductionActivity;
import com.amazon.alexa.enrollment.utils.ActivityConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class EnrollmentGatewayImpl implements EnrollmentGateway {
    private static final String TAG = GeneratedOutlineSupport1.outline39(EnrollmentGatewayImpl.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));

    @Override // com.amazon.alexa.enrollment.model.EnrollmentGateway
    public void startKidsVoiceEnrollmentTraining(Context context, KidsEnrollmentMetadata kidsEnrollmentMetadata, String str) {
        Intent intent = new Intent(context, KidsEnrollmentIntroductionActivity.class);
        intent.putExtra(ActivityConstants.SUCCESS_ROUTE_NAME, kidsEnrollmentMetadata.getSuccessRoute());
        intent.putExtra(ActivityConstants.FAILURE_ROUTE_NAME, kidsEnrollmentMetadata.getFailureRoute());
        intent.putExtra(ActivityConstants.ENROLLMENT_CONTEXT, str);
        intent.putExtra("PERSON_ID", kidsEnrollmentMetadata.getPersonId());
        intent.putExtra("PERSON_ECID", kidsEnrollmentMetadata.getPersonECID());
        intent.putExtra(ActivityConstants.CHILD_NAME, kidsEnrollmentMetadata.getChildName());
        intent.putExtra("CHILD_CUSTOMER_ID", kidsEnrollmentMetadata.getChildCustomerId());
        intent.putExtra(ActivityConstants.BACK_ROUTE, kidsEnrollmentMetadata.getBackRoute());
        intent.putExtra(ActivityConstants.CONSENT_COLLECTED_TOAST, kidsEnrollmentMetadata.getShowConsentCollectedToast());
        intent.setFlags(872415232);
        context.startActivity(intent);
    }

    @Override // com.amazon.alexa.enrollment.model.EnrollmentGateway
    public void startVoiceEnrollmentTraining(Context context) {
        Intent intent = new Intent(context, EnrollmentIntroductionActivity.class);
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    @Override // com.amazon.alexa.enrollment.model.EnrollmentGateway
    public void startVoiceEnrollmentTraining(Context context, String str, String str2, String str3, String str4, String str5) {
        Intent intent = new Intent(context, EnrollmentIntroductionActivity.class);
        intent.putExtra(ActivityConstants.SUCCESS_ROUTE_NAME, str);
        intent.putExtra(ActivityConstants.SUCCESS_ROUTE_URI, str2);
        intent.putExtra(ActivityConstants.FAILURE_ROUTE_NAME, str3);
        intent.putExtra(ActivityConstants.FAILURE_ROUTE_URI, str4);
        intent.putExtra(ActivityConstants.ENROLLMENT_CONTEXT, str5);
        intent.setFlags(872415232);
        context.startActivity(intent);
    }
}
