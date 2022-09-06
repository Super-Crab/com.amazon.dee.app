package com.amazon.alexa.enrollment.ui.kidsenrollment;

import android.content.Context;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.enrollment.R;
import com.amazon.alexa.enrollment.dialogs.AlertDialogFragment;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.ui.AbstractEnrollmentViewFragment;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class KidsEnrollmentDialogHelper {
    public static final int KIDS_ALERT_POPUP_WITH_1_BUTTON = 1;
    public static final int KIDS_ALERT_POPUP_WITH_2_BUTTON = 2;
    private static final String TAG = GeneratedOutlineSupport1.outline39(KidsEnrollmentDialogHelper.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));

    @VisibleForTesting
    AlertDialogFragment createOneButtonDialog(Context context, int i, int i2) {
        return AlertDialogFragment.createInstance(context, i, i2, R.string.handle_skip_positive_button);
    }

    @VisibleForTesting
    AlertDialogFragment createTwoButtonDialog(Context context, int i, int i2) {
        return AlertDialogFragment.createInstance(context, i, i2, R.string.handle_skip_positive_button, R.string.handle_skip_negative_button);
    }

    @VisibleForTesting
    void renderDialogFragment(AbstractEnrollmentViewFragment abstractEnrollmentViewFragment, AlertDialogFragment alertDialogFragment, String str, int i) {
        if (!abstractEnrollmentViewFragment.isAdded()) {
            Log.e(TAG, "Fragment is not attached to activity");
            return;
        }
        String str2 = TAG;
        Log.i(str2, "inside renderDialogFragment with dialog tag: " + str);
        alertDialogFragment.setTargetFragment(abstractEnrollmentViewFragment, i);
        alertDialogFragment.show(abstractEnrollmentViewFragment.getActivity().getSupportFragmentManager(), str);
    }

    public void showAlertPopup(AbstractEnrollmentViewFragment abstractEnrollmentViewFragment, int i) {
        AlertDialogFragment createOneButtonDialog;
        String str = TAG;
        Log.i(str, "inside showAlertPopup, with number of Buttons: " + i);
        Context context = abstractEnrollmentViewFragment.getContext();
        int i2 = 2;
        if (i == 2) {
            createOneButtonDialog = createTwoButtonDialog(context, R.string.handle_skip_click_title, R.string.handle_skip_click_message);
            Log.i(TAG, "Alert popup with 2 buttons are created");
        } else {
            createOneButtonDialog = createOneButtonDialog(context, R.string.handle_skip_click_title, R.string.handle_skip_click_message);
            i2 = 1;
            Log.i(TAG, "Alert popup with 1 button(s) are created");
        }
        renderDialogFragment(abstractEnrollmentViewFragment, createOneButtonDialog, "dialog", i2);
    }
}
