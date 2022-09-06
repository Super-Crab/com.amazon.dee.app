package com.amazon.alexa.drive.navigation.traffic;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessorykit.finishsetup.bridge.FinishSetupModule;
import com.amazon.alexa.drive.R;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class TrafficErrorDialogFragment extends DialogFragment {
    private static final int DIALOG_TIMEOUT_MS = 10000;
    private static final String TAG = TrafficErrorDialogFragment.class.getSimpleName();
    private final Runnable dialogCloseRunnable = new Runnable() { // from class: com.amazon.alexa.drive.navigation.traffic.-$$Lambda$TrafficErrorDialogFragment$c1u8f1n6auuac0h7_qYf2oehQj8
        @Override // java.lang.Runnable
        public final void run() {
            TrafficErrorDialogFragment.this.lambda$new$0$TrafficErrorDialogFragment();
        }
    };
    private Handler mHandler;

    void dismissInternal() {
        Log.i(TAG, FinishSetupModule.EVENT_ON_DISMISS);
        if (getHandler() != null) {
            getHandler().removeCallbacks(this.dialogCloseRunnable);
            this.mHandler = null;
        }
    }

    Handler getHandler() {
        return this.mHandler;
    }

    public /* synthetic */ void lambda$new$0$TrafficErrorDialogFragment() {
        if (isVisible()) {
            dismiss();
        }
    }

    public /* synthetic */ void lambda$onCreateView$1$TrafficErrorDialogFragment(View view) {
        dismiss();
    }

    public /* synthetic */ void lambda$onCreateView$2$TrafficErrorDialogFragment(View view) {
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
        Log.i(TAG, "onCreateView");
        View inflate = layoutInflater.inflate(R.layout.fragment_traffic_error, viewGroup);
        ((ImageView) inflate.findViewById(R.id.close_traffic_error)).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.navigation.traffic.-$$Lambda$TrafficErrorDialogFragment$Y97rUhUYB2vXRXcOs-LHpX1yFDI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrafficErrorDialogFragment.this.lambda$onCreateView$1$TrafficErrorDialogFragment(view);
            }
        });
        inflate.findViewById(R.id.traffic_error_container).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.navigation.traffic.-$$Lambda$TrafficErrorDialogFragment$Yx0sYQq6DOlomjJa_uKFChlBIxw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrafficErrorDialogFragment.this.lambda$onCreateView$2$TrafficErrorDialogFragment(view);
            }
        });
        return inflate;
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onDismiss ");
        outline107.append(getHandler());
        Log.i(str, outline107.toString());
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
        this.mHandler = new Handler();
        getHandler().postDelayed(this.dialogCloseRunnable, 10000L);
    }
}
