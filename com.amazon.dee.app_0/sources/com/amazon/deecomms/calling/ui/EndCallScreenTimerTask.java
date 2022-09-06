package com.amazon.deecomms.calling.ui;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.nativemodules.CommsEventEmitterBridge;
import java.util.TimerTask;
/* loaded from: classes12.dex */
public class EndCallScreenTimerTask extends TimerTask {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, EndCallScreenTimerTask.class);
    private final Fragment mFragment;

    public EndCallScreenTimerTask(@NonNull Fragment fragment) {
        this.mFragment = fragment;
    }

    public /* synthetic */ void lambda$run$0$EndCallScreenTimerTask(Activity activity) {
        if (CommsDaggerWrapper.getComponent().getCallManager().isCallUIVisible()) {
            this.mFragment.getFragmentManager().beginTransaction().setCustomAnimations(17432576, 17432577).remove(this.mFragment).commitAllowingStateLoss();
        }
        if (!CommsDaggerWrapper.getComponent().getCallManager().isAnyActiveCallPresent()) {
            if (activity instanceof CallActivity) {
                ((CallActivity) activity).mCallingContentProviderNotifier.notifyObservers();
            } else {
                ((NewCallActivity) activity).mCallingContentProviderNotifier.notifyObservers();
            }
            try {
                CommsEventEmitterBridge.sendCallEndedNotification();
            } catch (Exception e) {
                LOG.e("failed to send CallEndedNotification", e);
            }
            activity.finish();
        }
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        final FragmentActivity activity = this.mFragment.getActivity();
        if (((activity instanceof CallActivity) || (activity instanceof NewCallActivity)) && !activity.isFinishing()) {
            activity.runOnUiThread(new Runnable() { // from class: com.amazon.deecomms.calling.ui.-$$Lambda$EndCallScreenTimerTask$BsLtzAVtgteiGM9qj3U9L4FYDc0
                @Override // java.lang.Runnable
                public final void run() {
                    EndCallScreenTimerTask.this.lambda$run$0$EndCallScreenTimerTask(activity);
                }
            });
        } else {
            LOG.i("Activity is not an instance of CallActivity or is finishing. No action required.");
        }
    }
}
