package com.amazon.alexa.enrollment.ui.training;

import android.content.Context;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.alexa.enrollment.R;
import com.amazon.alexa.enrollment.dialogs.AlertDialogFragment;
import com.amazon.alexa.enrollment.dialogs.DialogConstants;
import com.amazon.alexa.enrollment.exception.EnrollmentDialogException;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.ui.AbstractEnrollmentViewFragment;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class EnrollmentTrainingDialogHelper {
    public static final int GENERIC_ERROR_REQUEST_CODE = 4;
    public static final int INVALID_STATUS_REQUEST_CODE = 6;
    public static final int NON_RETRYABLE_EXCEPTION_REQUEST_CODE = 3;
    public static final int QUALITY_EXCEPTION_REQUEST_CODE = 2;
    public static final int SILENCE_EXCEPTION_REQUEST_CODE = 1;
    public static final int SKIP_DIALOG_REQUEST_CODE = 5;
    private static final String TAG = GeneratedOutlineSupport1.outline39(EnrollmentTrainingDialogHelper.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));

    @VisibleForTesting
    AlertDialogFragment createSingleButtonDialog(Context context, int i, int i2) {
        return AlertDialogFragment.createInstance(context, i, i2, R.string.dialog_positive_btn);
    }

    @VisibleForTesting
    AlertDialogFragment createTwoButtonDialog(Context context, int i, int i2) {
        return AlertDialogFragment.createInstance(context, i, i2, R.string.dialog_retry_btn, R.string.dialog_negative_btn);
    }

    @VisibleForTesting
    void renderDialogFragment(AbstractEnrollmentViewFragment abstractEnrollmentViewFragment, AlertDialogFragment alertDialogFragment, int i, String str) {
        if (!abstractEnrollmentViewFragment.isAdded()) {
            Log.e(TAG, "Fragment is not attached to activity");
            return;
        }
        FragmentActivity activity = abstractEnrollmentViewFragment.getActivity();
        if (activity == null) {
            Log.w(TAG, "activity inside renderDialogFragment is null");
            return;
        }
        FragmentManager supportFragmentManager = activity.getSupportFragmentManager();
        Fragment findFragmentByTag = supportFragmentManager.findFragmentByTag(str);
        if (findFragmentByTag != null) {
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            beginTransaction.remove(findFragmentByTag);
            beginTransaction.commit();
        }
        alertDialogFragment.setTargetFragment(abstractEnrollmentViewFragment, i);
        alertDialogFragment.show(supportFragmentManager, str);
    }

    public void showErrorDialog(AbstractEnrollmentViewFragment abstractEnrollmentViewFragment, Throwable th) {
        AlertDialogFragment createSingleButtonDialog;
        int i;
        Context context = abstractEnrollmentViewFragment.getContext();
        if (th instanceof EnrollmentDialogException) {
            EnrollmentDialogException enrollmentDialogException = (EnrollmentDialogException) th;
            int titleResId = enrollmentDialogException.getTitleResId();
            int messageResId = enrollmentDialogException.getMessageResId();
            if (enrollmentDialogException.hasNegativeButton()) {
                createSingleButtonDialog = createTwoButtonDialog(context, titleResId, messageResId);
            } else {
                createSingleButtonDialog = createSingleButtonDialog(context, titleResId, messageResId);
            }
            i = enrollmentDialogException.getRequestCode();
        } else {
            createSingleButtonDialog = createSingleButtonDialog(context, R.string.fe_handle_non_retry_title, R.string.fe_handle_non_retry_msg);
            i = 4;
        }
        renderDialogFragment(abstractEnrollmentViewFragment, createSingleButtonDialog, i, "dialog");
    }

    public void showSkipDialog(AbstractEnrollmentViewFragment abstractEnrollmentViewFragment) {
        renderDialogFragment(abstractEnrollmentViewFragment, AlertDialogFragment.createInstance(abstractEnrollmentViewFragment.getContext(), R.string.skip_dialog_title, R.string.skip_dialog_desc, R.string.skip_dialog_back, R.string.skip_dialog_proceed), 5, DialogConstants.SKIP_DIALOG_TAG);
    }
}
