package com.amazon.alexa.enrollment.ui.introduction;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import com.amazon.alexa.enrollment.R;
import com.amazon.alexa.enrollment.api.EnrollmentAPI;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.ui.AbstractEnrollmentViewFragment;
import com.amazon.alexa.enrollment.ui.privacySettings.EnrollmentPrivacyTermsActivity;
import com.amazon.alexa.enrollment.ui.training.EnrollmentTrainingActivity;
import com.amazon.alexa.enrollment.utils.PermissionsHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class EnrollmentIntroductionViewModel {
    private static final String TAG = GeneratedOutlineSupport1.outline39(EnrollmentIntroductionViewModel.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    private final Context context;
    private final EnrollmentAPI enrollmentAPI;
    private final PermissionsHelper permissionsHelper;

    @Inject
    public EnrollmentIntroductionViewModel(Context context, EnrollmentAPI enrollmentAPI, PermissionsHelper permissionsHelper) {
        this.context = context;
        this.enrollmentAPI = enrollmentAPI;
        this.permissionsHelper = permissionsHelper;
    }

    private boolean checkVoiceHistoryRetentionPeriodForVoiceEnrollment(String str) {
        String str2 = TAG;
        Log.i(str2, "VoiceHistoryRetentionPeriod setting value " + str);
        return "\"P30D\"".equalsIgnoreCase(str);
    }

    @VisibleForTesting
    Intent getActivityIntent(AbstractEnrollmentViewFragment abstractEnrollmentViewFragment) {
        return abstractEnrollmentViewFragment.getActivity().getIntent();
    }

    @VisibleForTesting
    String getStringResources(Fragment fragment, int i) {
        return fragment.getString(i);
    }

    public EnrollmentIntroductionUiModel getUIModel() {
        return new EnrollmentIntroductionUiModel(false, isAudioPermissionGranted());
    }

    public boolean isAudioPermissionGranted() {
        return this.permissionsHelper.checkPermission(this.context, "android.permission.RECORD_AUDIO");
    }

    public Single<Boolean> isEligibleForVoiceEnrollment(String str) {
        return this.enrollmentAPI.getVoiceEnrollmentEligibility(str).map($$Lambda$EnrollmentIntroductionViewModel$wo11k2R96pTDNObZMP9qvb0nmfU.INSTANCE);
    }

    public Single<Boolean> isUserOptInForVoicePrivacySettings() {
        return this.enrollmentAPI.getSettingsValueForKey("Alexa.VoiceHistory.retentionPeriod").map(new Function() { // from class: com.amazon.alexa.enrollment.ui.introduction.-$$Lambda$EnrollmentIntroductionViewModel$j8f-mlwFszRzwxsXd2llIy6TQfA
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return EnrollmentIntroductionViewModel.this.lambda$isUserOptInForVoicePrivacySettings$1$EnrollmentIntroductionViewModel((String) obj);
            }
        });
    }

    public /* synthetic */ Boolean lambda$isUserOptInForVoicePrivacySettings$1$EnrollmentIntroductionViewModel(String str) throws Throwable {
        return Boolean.valueOf(checkVoiceHistoryRetentionPeriodForVoiceEnrollment(str));
    }

    public void moveToPrivacyTermsScreen(AbstractEnrollmentViewFragment abstractEnrollmentViewFragment) {
        Log.i(TAG, "Moving to enrollment privacy screen");
        Intent intent = new Intent(abstractEnrollmentViewFragment.getContext(), EnrollmentPrivacyTermsActivity.class);
        intent.putExtras(getActivityIntent(abstractEnrollmentViewFragment));
        startActivityAndFinish(abstractEnrollmentViewFragment, intent);
    }

    public void moveToTrainingScreen(AbstractEnrollmentViewFragment abstractEnrollmentViewFragment) {
        Log.i(TAG, "Moving to enrollment training screens");
        Intent intent = new Intent(abstractEnrollmentViewFragment.getContext(), EnrollmentTrainingActivity.class);
        intent.putExtras(getActivityIntent(abstractEnrollmentViewFragment));
        startActivityAndFinish(abstractEnrollmentViewFragment, intent);
    }

    public void requestAudioPermission(Fragment fragment, int i) {
        String stringResources = getStringResources(fragment, R.string.permission_message);
        this.permissionsHelper.requestPermission(fragment, getStringResources(fragment, R.string.permission_title), stringResources, new String[]{"android.permission.RECORD_AUDIO"}, i);
    }

    @VisibleForTesting
    void startActivityAndFinish(AbstractEnrollmentViewFragment abstractEnrollmentViewFragment, Intent intent) {
        abstractEnrollmentViewFragment.getActivity().startActivity(intent);
        abstractEnrollmentViewFragment.getActivity().finish();
    }
}
