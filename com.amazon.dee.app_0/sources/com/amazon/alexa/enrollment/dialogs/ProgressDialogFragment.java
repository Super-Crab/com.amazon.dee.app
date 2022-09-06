package com.amazon.alexa.enrollment.dialogs;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import androidx.fragment.app.DialogFragment;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.ui.AbstractEnrollmentViewFragment;
import com.amazon.alexa.enrollment.ui.training.EnrollmentTrainingViewFragment;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class ProgressDialogFragment extends DialogFragment {
    private static final String TAG = GeneratedOutlineSupport1.outline39(ProgressDialogFragment.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));

    public static ProgressDialogFragment createInstance(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("title", str);
        bundle.putSerializable(DialogConstants.DESC, str2);
        ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
        progressDialogFragment.setArguments(bundle);
        return progressDialogFragment;
    }

    public /* synthetic */ boolean lambda$onCreateDialog$0$ProgressDialogFragment(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        String str = TAG;
        StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Inside key event pressed: ", i, " key event: ");
        outline109.append(keyEvent.getAction());
        Log.i(str, outline109.toString());
        if (i == 4 && keyEvent.getAction() == 1) {
            Log.i(TAG, "Inside onback pressed key event");
            if (!(getTargetFragment() instanceof EnrollmentTrainingViewFragment)) {
                return false;
            }
            ((AbstractEnrollmentViewFragment) getTargetFragment()).finishEnrollmentWithFailureStatus();
            return true;
        }
        return false;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCancelable(false);
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Bundle arguments = getArguments();
        String string = arguments.getString("title");
        String string2 = arguments.getString(DialogConstants.DESC);
        if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
            ProgressDialog progressDialog = new ProgressDialog(getActivity(), getTheme());
            progressDialog.setTitle(arguments.getString("title"));
            progressDialog.setMessage(arguments.getString(DialogConstants.DESC));
            progressDialog.setIndeterminate(true);
            progressDialog.setProgressStyle(0);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(true);
            progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.amazon.alexa.enrollment.dialogs.-$$Lambda$ProgressDialogFragment$9JRSjbZhXfqGGDtj0V55iuvfl6w
                @Override // android.content.DialogInterface.OnKeyListener
                public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    return ProgressDialogFragment.this.lambda$onCreateDialog$0$ProgressDialogFragment(dialogInterface, i, keyEvent);
                }
            });
            return progressDialog;
        }
        throw new IllegalArgumentException("Title or description is not valid for dialog");
    }
}
