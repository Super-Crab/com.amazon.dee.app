package com.amazon.alexa.enrollment.ui.kidsenrollment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.enrollment.R;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.ui.AbstractBottomSheetFragment;
import com.amazon.alexa.enrollment.ui.training.EnrollmentTrainingActivity;
import com.amazon.alexa.enrollment.utils.PermissionsHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class KidsEnrollmentViewModel {
    private static final String TAG = GeneratedOutlineSupport1.outline39(KidsEnrollmentViewModel.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    private Context context;
    private PermissionsHelper permissionsHelper;

    @Inject
    public KidsEnrollmentViewModel(Context context, PermissionsHelper permissionsHelper) {
        this.context = context;
        this.permissionsHelper = permissionsHelper;
    }

    @VisibleForTesting
    Intent getActivityIntent(AbstractBottomSheetFragment abstractBottomSheetFragment) {
        return abstractBottomSheetFragment.getActivity().getIntent();
    }

    @VisibleForTesting
    String getStringResources(AbstractBottomSheetFragment abstractBottomSheetFragment, int i) {
        return abstractBottomSheetFragment.getString(i);
    }

    public boolean isAudioPermissionGranted() {
        return this.permissionsHelper.checkPermission(this.context, "android.permission.RECORD_AUDIO");
    }

    public void moveToTrainingScreen(AbstractBottomSheetFragment abstractBottomSheetFragment) {
        Log.i(TAG, "Moving to enrollment training screens");
        Intent intent = new Intent(abstractBottomSheetFragment.getContext(), EnrollmentTrainingActivity.class);
        intent.putExtras(getActivityIntent(abstractBottomSheetFragment));
        startActivityAndFinish(abstractBottomSheetFragment, intent);
    }

    public void requestAudioPermission(AbstractBottomSheetFragment abstractBottomSheetFragment, int i) {
        requestPermission(abstractBottomSheetFragment, getStringResources(abstractBottomSheetFragment, R.string.permission_title), getStringResources(abstractBottomSheetFragment, R.string.permission_message), new String[]{"android.permission.RECORD_AUDIO"}, i);
    }

    @VisibleForTesting
    void requestPermission(AbstractBottomSheetFragment abstractBottomSheetFragment, String str, String str2, String[] strArr, int i) {
        this.permissionsHelper.requestPermission(abstractBottomSheetFragment, str, str2, strArr, i);
    }

    @VisibleForTesting
    void startActivityAndFinish(AbstractBottomSheetFragment abstractBottomSheetFragment, Intent intent) {
        abstractBottomSheetFragment.getActivity().startActivity(intent);
        abstractBottomSheetFragment.getActivity().finish();
    }
}
