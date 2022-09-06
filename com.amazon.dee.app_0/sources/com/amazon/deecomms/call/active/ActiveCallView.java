package com.amazon.deecomms.call.active;

import android.content.Context;
import android.media.AudioManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.app.RemoteViewManager;
import com.amazon.deecomms.app.SelfViewManager;
import com.amazon.deecomms.calling.ui.CallViewUtils;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.util.ChangeOrientationListener;
import com.android.tools.r8.GeneratedOutlineSupport;
/* loaded from: classes12.dex */
public abstract class ActiveCallView extends FrameLayout {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG + ActiveCallView.class);
    private static boolean mIsThemedUIEnabled;
    protected View.OnClickListener endButtonClickListener;
    protected AudioManager mAudioManager;
    protected TextView mCallStatus;
    protected TextView mContactName;
    private Context mContext;
    protected Call mCurrentCall;
    protected ImageButton mEndCallButton;
    protected ImageButton mMicButton;
    protected View.OnClickListener micButtonClickListener;

    protected ActiveCallView(@NonNull Context context) {
        super(context);
        this.micButtonClickListener = new View.OnClickListener() { // from class: com.amazon.deecomms.call.active.ActiveCallView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                boolean z = !ActiveCallView.this.mCurrentCall.getMediaStatus().isLocalAudioEnabled();
                CommsLogger commsLogger = ActiveCallView.LOG;
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("muteButton onClick:enabled=");
                outline1.append(!z);
                commsLogger.i(outline1.toString());
                CommsDaggerWrapper.getComponent().getCommandProcessor().enableAudio(z);
                ActiveCallView.this.mAudioManager.setMicrophoneMute(!z);
            }
        };
        this.endButtonClickListener = new View.OnClickListener() { // from class: com.amazon.deecomms.call.active.ActiveCallView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CallUtils.endActiveCall(ActiveCallView.this.mContext);
            }
        };
    }

    public static ActiveCallView getInstance(@NonNull Context context, @NonNull SelfViewManager selfViewManager, @Nullable RemoteViewManager remoteViewManager, @Nullable ImageView imageView, int i, boolean z, boolean z2) {
        mIsThemedUIEnabled = z;
        return new ActiveVideoCallView(context, selfViewManager, remoteViewManager, imageView, i, z, z2);
    }

    private String getString(int i) {
        return Utils.getStringFromResource(i);
    }

    public void bind(String str) {
        this.mContactName = (TextView) findViewById(R.id.callParticipantName);
        this.mCallStatus = (TextView) findViewById(R.id.callStatus);
        CallViewUtils.displayNameAndStatus(getContext(), this.mContactName, this.mCallStatus);
        this.mMicButton = (ImageButton) findViewById(R.id.muteButton);
        this.mMicButton.setEnabled(true);
        setMicIconState(!this.mCurrentCall.getMediaStatus().isLocalAudioEnabled());
        this.mMicButton.setOnClickListener(this.micButtonClickListener);
        this.mEndCallButton = (ImageButton) findViewById(R.id.callFinishButton);
        this.mEndCallButton.setOnClickListener(this.endButtonClickListener);
    }

    public void setChangeOrientationListener(ChangeOrientationListener changeOrientationListener) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setMicIconState(boolean z) {
        this.mMicButton.setSelected(z);
        if (z) {
            this.mMicButton.setContentDescription(Utils.getStringFromResource(R.string.unmute_mic_content_description));
        } else {
            this.mMicButton.setContentDescription(Utils.getStringFromResource(R.string.mute_mic_content_description));
        }
        if (mIsThemedUIEnabled) {
            if (!z) {
                this.mMicButton.setColorFilter(ContextCompat.getColor(this.mContext, R.color.fiesta_btn_on));
            } else {
                this.mMicButton.setColorFilter(ContextCompat.getColor(this.mContext, R.color.fiesta_btn_off));
            }
        }
    }

    public ActiveCallView(@NonNull Context context, @NonNull SelfViewManager selfViewManager) {
        super(context);
        this.micButtonClickListener = new View.OnClickListener() { // from class: com.amazon.deecomms.call.active.ActiveCallView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                boolean z = !ActiveCallView.this.mCurrentCall.getMediaStatus().isLocalAudioEnabled();
                CommsLogger commsLogger = ActiveCallView.LOG;
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("muteButton onClick:enabled=");
                outline1.append(!z);
                commsLogger.i(outline1.toString());
                CommsDaggerWrapper.getComponent().getCommandProcessor().enableAudio(z);
                ActiveCallView.this.mAudioManager.setMicrophoneMute(!z);
            }
        };
        this.endButtonClickListener = new View.OnClickListener() { // from class: com.amazon.deecomms.call.active.ActiveCallView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CallUtils.endActiveCall(ActiveCallView.this.mContext);
            }
        };
        this.mContext = context;
        this.mAudioManager = (AudioManager) this.mContext.getSystemService("audio");
        this.mCurrentCall = CommsDaggerWrapper.getComponent().getCurrentCallSipClientState().getCurrentActiveCall();
        selfViewManager.hideScrim();
    }
}
