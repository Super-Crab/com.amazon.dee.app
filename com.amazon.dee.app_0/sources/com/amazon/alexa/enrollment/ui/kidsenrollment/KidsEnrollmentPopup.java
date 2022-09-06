package com.amazon.alexa.enrollment.ui.kidsenrollment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.enrollment.R;
import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.module.library.Injector;
import com.amazon.alexa.enrollment.ui.AbstractBottomSheetFragment;
import com.amazon.alexa.enrollment.ui.DebounceOnClickListener;
import com.amazon.alexa.enrollment.utils.ActivityConstants;
import com.amazon.alexa.enrollment.utils.EnrollmentThemeUtil;
import com.amazon.alexa.enrollment.utils.PermissionsHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class KidsEnrollmentPopup extends AbstractBottomSheetFragment {
    private static final int AUDIO_PERMISSION_REQUEST_CODE = 1;
    private static final String TAG = GeneratedOutlineSupport1.outline39(KidsEnrollmentPopup.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    @Inject
    EnrollmentMetricsRecorder enrollmentMetricsRecorder;
    @Inject
    EnrollmentThemeUtil enrollmentThemeUtil;
    private Bundle extras;
    private DebounceOnClickListener getStartedButtonClickListener = new DebounceOnClickListener() { // from class: com.amazon.alexa.enrollment.ui.kidsenrollment.KidsEnrollmentPopup.1
        @Override // com.amazon.alexa.enrollment.ui.DebounceOnClickListener
        public void debounceClick(View view) {
            Log.i(KidsEnrollmentPopup.TAG, "user clicked Get Started button in popup page");
            KidsEnrollmentPopup.this.getStartedOnClick();
        }
    };
    private DebounceOnClickListener laterButtonClickListener = new DebounceOnClickListener() { // from class: com.amazon.alexa.enrollment.ui.kidsenrollment.KidsEnrollmentPopup.2
        @Override // com.amazon.alexa.enrollment.ui.DebounceOnClickListener
        public void debounceClick(View view) {
            Log.i(KidsEnrollmentPopup.TAG, "user clicked Later button in popup page");
            KidsEnrollmentPopup.this.laterButtonOnClick();
        }
    };
    @Inject
    PermissionsHelper permissionsHelper;
    private View popupView;
    @Inject
    KidsEnrollmentViewModel viewModel;

    public KidsEnrollmentPopup() {
    }

    @VisibleForTesting
    void getStartedOnClick() {
        this.enrollmentMetricsRecorder.recordUserClickInteraction(MetricsConstants.UserInteractionMetrics.KIDS_POPUP_GET_STARTED_CLICK);
        if (this.viewModel.isAudioPermissionGranted()) {
            Log.i(TAG, "audio permission already granted");
            this.enrollmentMetricsRecorder.recordUserClickInteraction(MetricsConstants.UserInteractionMetrics.AUDIO_PERMISSION_GRANTED);
            this.viewModel.moveToTrainingScreen(this);
            return;
        }
        Log.i(TAG, "audio permission not granted by user, requesting permission for audio");
        this.viewModel.requestAudioPermission(this, 1);
    }

    @VisibleForTesting
    String getStringResource(int i) {
        return getString(i);
    }

    @VisibleForTesting
    void injectDependency() {
        Injector.inject(this);
    }

    @VisibleForTesting
    void laterButtonOnClick() {
        Log.i(TAG, "inside laterButtonOnClick");
        this.enrollmentMetricsRecorder.recordUserClickInteraction(MetricsConstants.UserInteractionMetrics.KIDS_POPUP_LATER_CLICK);
        this.extras = getArguments();
        Log.i(TAG, "failing kids voice enrollment");
        finishEnrollmentWithFailureStatus();
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        injectDependency();
        Log.i(TAG, "inside onCreateView");
        this.enrollmentThemeUtil.setTheme(getContext());
        this.popupView = layoutInflater.inflate(R.layout.kids_enrollment_popup_window, viewGroup, false);
        this.extras = getArguments();
        setMosaicThemeColor();
        updateUI();
        return this.popupView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "inside onDestroy");
        this.getStartedButtonClickListener.cleanUp();
        this.laterButtonClickListener.cleanUp();
    }

    @Override // com.amazon.alexa.enrollment.ui.AbstractBottomSheetFragment, com.amazon.alexa.enrollment.dialogs.AlertDialogFragment.OnDialogActionCallback
    public void onNegativeButtonTap(String str, int i) {
        String str2 = TAG;
        Log.i(str2, "Negative button tap for dialog with tag: " + str + " and requestCode: " + i);
        Log.i(TAG, "Failing kids voice enrollment");
        finishEnrollmentWithFailureStatus();
    }

    @Override // com.amazon.alexa.enrollment.ui.AbstractBottomSheetFragment, com.amazon.alexa.enrollment.dialogs.AlertDialogFragment.OnDialogActionCallback
    public void onPositiveButtonTap(String str, int i) {
        String str2 = TAG;
        Log.i(str2, "Positive button tap for dialog with tag: " + str + " and requestCode: " + i);
        this.permissionsHelper.openAppSettingsPermissions(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (i == 1) {
            if (iArr.length > 0 && iArr[0] == 0) {
                Log.i(TAG, "user granted Audio Permission, proceeding for enrollment");
                this.enrollmentMetricsRecorder.recordUserClickInteraction(MetricsConstants.UserInteractionMetrics.AUDIO_PERMISSION_GRANTED);
                this.viewModel.moveToTrainingScreen(this);
                return;
            }
            Log.i(TAG, "user denied permission, so failing the enrollment");
            this.enrollmentMetricsRecorder.recordUserClickInteraction(MetricsConstants.UserInteractionMetrics.AUDIO_PERMISSION_NOT_GRANTED);
            finishEnrollmentWithFailureStatus();
        }
    }

    @VisibleForTesting
    void setMosaicThemeColor() {
        this.enrollmentThemeUtil.setBackgroundColorToView(getContext(), R.attr.mosaicBackground, this.popupView);
        this.enrollmentThemeUtil.setTextColor(getContext(), R.attr.mosaicNeutral10, (TextView) this.popupView.findViewById(R.id.kids_popup_description), (TextView) this.popupView.findViewById(R.id.kids_popup_help_text));
        this.enrollmentThemeUtil.setTextColor(getContext(), R.attr.mosaicAction10, (TextView) this.popupView.findViewById(R.id.kids_popup_later_button));
        this.enrollmentThemeUtil.setBackgroundToView(getContext(), R.drawable.primary_button_mosaic_background, this.popupView.findViewById(R.id.kids_popup_get_started_button));
    }

    @VisibleForTesting
    void updateUI() {
        Log.i(TAG, "inside updateUI");
        this.popupView.findViewById(R.id.kids_popup_get_started_button).setOnClickListener(this.getStartedButtonClickListener);
        this.popupView.findViewById(R.id.kids_popup_later_button).setOnClickListener(this.laterButtonClickListener);
        String string = this.extras.getString(ActivityConstants.CHILD_NAME);
        String stringResource = getStringResource(R.string.kids_popup_description);
        Log.i(TAG, "obtained child name and text description to be changed on popup window");
        String format = String.format(stringResource, string);
        Log.i(TAG, "edited the popup description");
        String stringResource2 = getStringResource(R.string.kids_popup_help_text);
        Log.i(TAG, "obtained help text to be changed with child name");
        String format2 = String.format(stringResource2, string);
        Log.i(TAG, "edited the popup help text");
        ((TextView) this.popupView.findViewById(R.id.kids_popup_description)).setText(format);
        ((TextView) this.popupView.findViewById(R.id.kids_popup_help_text)).setText(format2);
        Log.i(TAG, "replaced description and help text in Popup window");
    }

    public KidsEnrollmentPopup(KidsEnrollmentViewModel kidsEnrollmentViewModel, PermissionsHelper permissionsHelper, EnrollmentThemeUtil enrollmentThemeUtil, EnrollmentMetricsRecorder enrollmentMetricsRecorder, View view, Bundle bundle) {
        this.viewModel = kidsEnrollmentViewModel;
        this.permissionsHelper = permissionsHelper;
        this.enrollmentThemeUtil = enrollmentThemeUtil;
        this.enrollmentMetricsRecorder = enrollmentMetricsRecorder;
        this.popupView = view;
        this.extras = bundle;
    }
}
