package com.amazon.alexa.drive.entertainment.view;

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
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessorykit.finishsetup.bridge.FinishSetupModule;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract;
import com.amazon.alexa.drive.entertainment.model.MediaErrorPayload;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class MediaErrorDialogFragment extends DialogFragment {
    private static final int DIALOG_TIMEOUT_MS = 10000;
    private static final String TAG = MediaErrorDialogFragment.class.getSimpleName();
    private final Runnable dialogCloseRunnable = new Runnable() { // from class: com.amazon.alexa.drive.entertainment.view.MediaErrorDialogFragment.1
        @Override // java.lang.Runnable
        public void run() {
            if (MediaErrorDialogFragment.this.isVisible()) {
                MediaErrorDialogFragment.this.dismiss();
            }
        }
    };
    private Handler mHandler;
    private NowPlayingScreenContract.View.MediaErrorDialogListener mediaErrorDialogListener;

    void dismissInternal() {
        Log.i(TAG, FinishSetupModule.EVENT_ON_DISMISS);
        if (getHandler() != null) {
            getHandler().removeCallbacks(this.dialogCloseRunnable);
            this.mHandler = null;
        }
        if (getMediaErrorDialogListener() != null) {
            getMediaErrorDialogListener().handleMediaErrorDialogClose();
        }
    }

    Handler getHandler() {
        return this.mHandler;
    }

    NowPlayingScreenContract.View.MediaErrorDialogListener getMediaErrorDialogListener() {
        return this.mediaErrorDialogListener;
    }

    public /* synthetic */ void lambda$onCreateView$0$MediaErrorDialogFragment(View view) {
        dismiss();
    }

    public /* synthetic */ void lambda$onCreateView$1$MediaErrorDialogFragment(View view) {
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
        View inflate = layoutInflater.inflate(R.layout.fragment_media_error, viewGroup);
        ((ImageView) inflate.findViewById(R.id.close_media_error)).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$MediaErrorDialogFragment$cEzag8mLhPp8cwHe_5SshhWFWpA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MediaErrorDialogFragment.this.lambda$onCreateView$0$MediaErrorDialogFragment(view);
            }
        });
        inflate.findViewById(R.id.media_error_container).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$MediaErrorDialogFragment$bkHn8wxNhngYDeXzzd_YJoqSDsk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MediaErrorDialogFragment.this.lambda$onCreateView$1$MediaErrorDialogFragment(view);
            }
        });
        MediaErrorPayload mediaErrorPayload = (MediaErrorPayload) getArguments().getSerializable(EntertainmentConstants.ENTERTAINMENT_ITEM_ATTR_MEDIA_ERROR_DIALOG_PAYLOAD);
        if (mediaErrorPayload != null) {
            Log.i(TAG, "mediaErrorPayload not null");
            ((TextView) inflate.findViewById(R.id.media_error_title)).setText(mediaErrorPayload.getTitle());
            ((TextView) inflate.findViewById(R.id.media_error_subtitle)).setText(mediaErrorPayload.getSubtitle());
        }
        return inflate;
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onDismiss ");
        outline107.append(getHandler());
        outline107.append(" getMediaErrorDialogListener ");
        outline107.append(getMediaErrorDialogListener());
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMediaErrorDialogListener(NowPlayingScreenContract.View.MediaErrorDialogListener mediaErrorDialogListener) {
        String str = TAG;
        Log.i(str, "setMediaErrorDialogListener " + mediaErrorDialogListener);
        this.mediaErrorDialogListener = mediaErrorDialogListener;
    }
}
