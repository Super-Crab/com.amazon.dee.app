package com.amazon.alexa.drive.orientation;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessorykit.finishsetup.bridge.FinishSetupModule;
import com.amazon.alexa.accessorykit.finishsetup.presenters.FASCardPresenter;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.drive.orientation.OrientationConstants;
/* loaded from: classes7.dex */
public class LandscapeErrorFragment extends DialogFragment {
    private static final String TAG = LandscapeErrorFragment.class.getSimpleName();
    private LandscapeErrorFragmentListener mListener;

    /* loaded from: classes7.dex */
    public interface LandscapeErrorFragmentListener {
        void onErrorDismiss();
    }

    @Override // android.app.DialogFragment
    public void dismiss() {
        Log.i(TAG, FASCardPresenter.ACTION_DISMISS);
        super.dismiss();
    }

    void dismissInternal() {
        Log.i(TAG, FinishSetupModule.EVENT_ON_DISMISS);
        if (getLandscapeErrorFragmentListener() != null) {
            getLandscapeErrorFragmentListener().onErrorDismiss();
        }
    }

    LandscapeErrorFragmentListener getLandscapeErrorFragmentListener() {
        return this.mListener;
    }

    public /* synthetic */ void lambda$onCreateView$0$LandscapeErrorFragment(View view) {
        dismiss();
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        Log.i(TAG, "onCreate");
        super.onCreate(bundle);
        setStyle(1, R.style.ErrorDialogTheme);
    }

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate;
        Log.i(TAG, "onCreateView");
        OrientationConstants.Orientation orientation = (OrientationConstants.Orientation) getArguments().getSerializable(OrientationConstants.LANDSCAPE_ERROR_DIALOG_ORIENTATION);
        if (orientation != null && orientation.equals(OrientationConstants.Orientation.LANDSCAPE_LEFT)) {
            inflate = layoutInflater.inflate(R.layout.fragment_landscape_left_error, viewGroup);
        } else {
            inflate = layoutInflater.inflate(R.layout.fragment_landscape_right_error, viewGroup);
        }
        inflate.findViewById(R.id.landscape_error_container).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.orientation.-$$Lambda$LandscapeErrorFragment$4d5EU1S7hLhVlmDG6EQkICLEY1o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LandscapeErrorFragment.this.lambda$onCreateView$0$LandscapeErrorFragment(view);
            }
        });
        return inflate;
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        Log.i(TAG, FinishSetupModule.EVENT_ON_DISMISS);
        super.onDismiss(dialogInterface);
        dismissInternal();
    }

    @Override // android.app.Fragment
    public void onPause() {
        Log.i(TAG, "onPause");
        super.onPause();
        dismiss();
    }

    @Override // android.app.Fragment
    public void onResume() {
        Log.i(TAG, "onResume");
        WindowManager.LayoutParams attributes = getDialog().getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        getDialog().getWindow().setAttributes(attributes);
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setLandscapeErrorFragmentListener(LandscapeErrorFragmentListener landscapeErrorFragmentListener) {
        this.mListener = landscapeErrorFragmentListener;
    }
}
