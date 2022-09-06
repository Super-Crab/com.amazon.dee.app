package com.amazon.alexa.enrollment.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.DialogFragment;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.ui.training.EnrollmentTrainingViewFragment;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class AlertDialogFragment extends DialogFragment {
    private static final String TAG = GeneratedOutlineSupport1.outline39(AlertDialogFragment.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));

    /* loaded from: classes7.dex */
    public interface OnDialogActionCallback {
        void onNegativeButtonTap(String str, int i);

        void onPositiveButtonTap(String str, int i);
    }

    public static AlertDialogFragment createInstance(String str, String str2, String str3, String str4) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("title", str);
        bundle.putSerializable(DialogConstants.DESC, str2);
        bundle.putSerializable("positiveButtonString", str3);
        bundle.putSerializable("negativeButtonString", str4);
        AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
        alertDialogFragment.setArguments(bundle);
        return alertDialogFragment;
    }

    @VisibleForTesting
    Dialog createDialogWithOneButton(Bundle bundle, final String str) {
        return new AlertDialog.Builder(getActivity()).setTitle(bundle.getString("title")).setMessage(bundle.getString(DialogConstants.DESC)).setPositiveButton(bundle.getString("positiveButtonString"), new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.enrollment.dialogs.-$$Lambda$AlertDialogFragment$YN06Gw6P4VsrjBkTqxlMZTRf3nQ
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AlertDialogFragment.this.lambda$createDialogWithOneButton$3$AlertDialogFragment(str, dialogInterface, i);
            }
        }).create();
    }

    @VisibleForTesting
    Dialog createDialogWithTwoButton(Bundle bundle, final String str) {
        return new AlertDialog.Builder(getActivity()).setTitle(bundle.getString("title")).setMessage(bundle.getString(DialogConstants.DESC)).setPositiveButton(bundle.getString("positiveButtonString"), new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.enrollment.dialogs.-$$Lambda$AlertDialogFragment$meKCBf9biWK7TG2n7aBnx7jYN60
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AlertDialogFragment.this.lambda$createDialogWithTwoButton$1$AlertDialogFragment(str, dialogInterface, i);
            }
        }).setNegativeButton(bundle.getString("negativeButtonString"), new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.enrollment.dialogs.-$$Lambda$AlertDialogFragment$Q-b50wV-kheJO1wwYOInfBMlpXQ
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AlertDialogFragment.this.lambda$createDialogWithTwoButton$2$AlertDialogFragment(str, dialogInterface, i);
            }
        }).create();
    }

    public /* synthetic */ void lambda$createDialogWithOneButton$3$AlertDialogFragment(String str, DialogInterface dialogInterface, int i) {
        if (getTargetFragment() instanceof OnDialogActionCallback) {
            ((OnDialogActionCallback) getTargetFragment()).onPositiveButtonTap(str, getTargetRequestCode());
        }
        dialogInterface.dismiss();
    }

    public /* synthetic */ void lambda$createDialogWithTwoButton$1$AlertDialogFragment(String str, DialogInterface dialogInterface, int i) {
        if (getTargetFragment() instanceof OnDialogActionCallback) {
            ((OnDialogActionCallback) getTargetFragment()).onPositiveButtonTap(str, getTargetRequestCode());
        }
        dialogInterface.dismiss();
    }

    public /* synthetic */ void lambda$createDialogWithTwoButton$2$AlertDialogFragment(String str, DialogInterface dialogInterface, int i) {
        if (getTargetFragment() instanceof OnDialogActionCallback) {
            ((OnDialogActionCallback) getTargetFragment()).onNegativeButtonTap(str, getTargetRequestCode());
        }
        dialogInterface.dismiss();
    }

    public /* synthetic */ boolean lambda$onCreateDialog$0$AlertDialogFragment(String str, DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        String str2 = TAG;
        StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Inside key event pressed: ", i, " key event: ");
        outline109.append(keyEvent.getAction());
        Log.i(str2, outline109.toString());
        if (i == 4 && keyEvent.getAction() == 1) {
            Log.i(TAG, "Inside onback pressed key event");
            if (!(getTargetFragment() instanceof EnrollmentTrainingViewFragment)) {
                return false;
            }
            ((OnDialogActionCallback) getTargetFragment()).onNegativeButtonTap(str, getTargetRequestCode());
            return true;
        }
        return false;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog createDialogWithOneButton;
        Bundle arguments = getArguments();
        final String tag = getTag();
        if (!TextUtils.isEmpty(arguments.getString("title")) && !TextUtils.isEmpty(arguments.getString(DialogConstants.DESC)) && !TextUtils.isEmpty(arguments.getString("positiveButtonString"))) {
            String string = arguments.getString("positiveButtonString");
            String string2 = arguments.getString("negativeButtonString");
            if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                createDialogWithOneButton = createDialogWithTwoButton(arguments, tag);
            } else {
                createDialogWithOneButton = createDialogWithOneButton(arguments, tag);
            }
            createDialogWithOneButton.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.amazon.alexa.enrollment.dialogs.-$$Lambda$AlertDialogFragment$hTkfZ_MAFxkkmh73K4ooyPeanLA
                @Override // android.content.DialogInterface.OnKeyListener
                public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    return AlertDialogFragment.this.lambda$onCreateDialog$0$AlertDialogFragment(tag, dialogInterface, i, keyEvent);
                }
            });
            createDialogWithOneButton.setCancelable(false);
            createDialogWithOneButton.setCanceledOnTouchOutside(false);
            return createDialogWithOneButton;
        }
        throw new IllegalArgumentException("One of the inputs to the dialog is empty");
    }

    public static AlertDialogFragment createInstance(Context context, int i, int i2, int i3, int i4) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("title", context.getString(i));
        bundle.putSerializable(DialogConstants.DESC, context.getString(i2));
        bundle.putSerializable("positiveButtonString", context.getString(i3));
        bundle.putSerializable("negativeButtonString", context.getString(i4));
        AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
        alertDialogFragment.setArguments(bundle);
        return alertDialogFragment;
    }

    public static AlertDialogFragment createInstance(Context context, int i, int i2, int i3) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("title", context.getString(i));
        bundle.putSerializable(DialogConstants.DESC, context.getString(i2));
        bundle.putSerializable("positiveButtonString", context.getString(i3));
        AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
        alertDialogFragment.setArguments(bundle);
        return alertDialogFragment;
    }
}
