package com.amazon.alexa.handsfree.uservoicerecognition.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.DialogFragment;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentTermsFragment;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes8.dex */
public class ProgressDialogFragment extends DialogFragment {
    private static final String DESC = "desc";
    private static final String TAG = EnrollmentTermsFragment.class.getSimpleName();
    private static final String TITLE = "title";

    public static ProgressDialogFragment createInstance(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("title", str);
        bundle.putSerializable("desc", str2);
        ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
        progressDialogFragment.setArguments(bundle);
        return progressDialogFragment;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$onCreateDialog$0(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        String str = TAG;
        StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Inside key event pressed: ", i, " key event: ");
        outline109.append(keyEvent.getAction());
        Log.i(str, outline109.toString());
        return false;
    }

    @VisibleForTesting
    ProgressDialog createProgressDialog() {
        return new ProgressDialog(getActivity(), getTheme());
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
        String string2 = arguments.getString("desc");
        if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
            ProgressDialog createProgressDialog = createProgressDialog();
            createProgressDialog.setTitle(string);
            createProgressDialog.setMessage(string2);
            createProgressDialog.setIndeterminate(true);
            createProgressDialog.setProgressStyle(0);
            createProgressDialog.setCanceledOnTouchOutside(false);
            createProgressDialog.setCancelable(true);
            createProgressDialog.setOnKeyListener($$Lambda$ProgressDialogFragment$AH9qE529U2LVnhZtaWL8jYvXtEg.INSTANCE);
            return createProgressDialog;
        }
        throw new IllegalArgumentException("Title or description is not valid for dialog");
    }
}
