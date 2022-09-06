package com.amazon.alexa.handsfree.ui.views;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
@SuppressLint({"ValidFragment"})
/* loaded from: classes8.dex */
public class AlertDialogFragment extends DialogFragment {
    private static final String TAG = AlertDialogFragment.class.getSimpleName();
    @NonNull
    private OnDialogActionCallback mDialogActionCallback;

    /* loaded from: classes8.dex */
    public interface OnDialogActionCallback {
        void onDismiss(String str, int i);

        void onNegativeButtonTap(String str, int i);

        void onPositiveButtonTap(String str, int i);
    }

    @SuppressLint({"ValidFragment"})
    public AlertDialogFragment(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull OnDialogActionCallback onDialogActionCallback) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("title", str);
        bundle.putSerializable("description", str2);
        bundle.putSerializable("positiveButtonString", str3);
        bundle.putSerializable("negativeButtonString", str4);
        setArguments(bundle);
        this.mDialogActionCallback = onDialogActionCallback;
    }

    private DialogInterface.OnClickListener getNegativeButtonClickListener(@NonNull final String str, final int i) {
        return new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.handsfree.ui.views.-$$Lambda$AlertDialogFragment$6klj7AiRQJ-MCauM8OTCo1GcdmM
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                AlertDialogFragment.this.lambda$getNegativeButtonClickListener$2$AlertDialogFragment(str, i, dialogInterface, i2);
            }
        };
    }

    private DialogInterface.OnClickListener getPositiveButtonClickListener(@NonNull final String str, final int i) {
        return new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.handsfree.ui.views.-$$Lambda$AlertDialogFragment$xN-Ln7-rXa7fGgpmgGtP-KBchrM
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                AlertDialogFragment.this.lambda$getPositiveButtonClickListener$1$AlertDialogFragment(str, i, dialogInterface, i2);
            }
        };
    }

    @VisibleForTesting
    Dialog createDialogWithOneButton(@NonNull Bundle bundle, @NonNull String str) {
        return getAlertDialogBuilder(bundle).setPositiveButton(bundle.getString("positiveButtonString"), getPositiveButtonClickListener(str, getTargetRequestCode())).create();
    }

    @VisibleForTesting
    Dialog createDialogWithTwoButton(@NonNull Bundle bundle, @NonNull String str) {
        int targetRequestCode = getTargetRequestCode();
        return getAlertDialogBuilder(bundle).setPositiveButton(bundle.getString("positiveButtonString"), getPositiveButtonClickListener(str, targetRequestCode)).setNegativeButton(bundle.getString("negativeButtonString"), getNegativeButtonClickListener(str, targetRequestCode)).create();
    }

    @VisibleForTesting
    AlertDialog.Builder getAlertDialogBuilder(@NonNull Bundle bundle) {
        return new AlertDialog.Builder(getActivity()).setTitle(bundle.getString("title")).setMessage(bundle.getString("description"));
    }

    public /* synthetic */ void lambda$getNegativeButtonClickListener$2$AlertDialogFragment(String str, int i, DialogInterface dialogInterface, int i2) {
        this.mDialogActionCallback.onNegativeButtonTap(str, i);
        dialogInterface.dismiss();
    }

    public /* synthetic */ void lambda$getPositiveButtonClickListener$1$AlertDialogFragment(String str, int i, DialogInterface dialogInterface, int i2) {
        this.mDialogActionCallback.onPositiveButtonTap(str, i);
        dialogInterface.dismiss();
    }

    public /* synthetic */ boolean lambda$onCreateDialog$0$AlertDialogFragment(String str, DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        String str2 = TAG;
        StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Inside key event pressed: ", i, " key event: ");
        outline109.append(keyEvent.getAction());
        Log.i(str2, outline109.toString());
        if (i == 4 && keyEvent.getAction() == 1) {
            Log.i(TAG, "Inside onback pressed key event");
            this.mDialogActionCallback.onDismiss(str, getTargetRequestCode());
            dialogInterface.dismiss();
            return true;
        }
        return false;
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(@NonNull Bundle bundle) {
        Dialog createDialogWithOneButton;
        Log.i(TAG, "entering AlertDialogFragment onCreateDialog method");
        Bundle arguments = getArguments();
        final String tag = getTag();
        String string = arguments.getString("positiveButtonString");
        String string2 = arguments.getString("negativeButtonString");
        if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
            Log.d(TAG, "Both positive and negative button is not null, showing two button dialog");
            createDialogWithOneButton = createDialogWithTwoButton(arguments, tag);
        } else {
            createDialogWithOneButton = createDialogWithOneButton(arguments, tag);
        }
        createDialogWithOneButton.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.amazon.alexa.handsfree.ui.views.-$$Lambda$AlertDialogFragment$6FEA5Qsbt2oZzmdN0ueayREfD34
            @Override // android.content.DialogInterface.OnKeyListener
            public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return AlertDialogFragment.this.lambda$onCreateDialog$0$AlertDialogFragment(tag, dialogInterface, i, keyEvent);
            }
        });
        createDialogWithOneButton.setCancelable(false);
        createDialogWithOneButton.setCanceledOnTouchOutside(false);
        return createDialogWithOneButton;
    }

    public void show(@NonNull Fragment fragment, @NonNull FragmentManager fragmentManager) {
        if (!fragment.isAdded()) {
            Log.e(TAG, "Fragment is not attached to activity, not showing alert dialog");
            return;
        }
        Fragment findFragmentByTag = fragmentManager.findFragmentByTag("dialog");
        if (findFragmentByTag != null) {
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            beginTransaction.remove(findFragmentByTag);
            beginTransaction.commit();
        }
        setTargetFragment(fragment, 0);
        show(fragmentManager, "dialog");
    }
}
