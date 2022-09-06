package com.amazon.deecomms.calling.service;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import androidx.annotation.VisibleForTesting;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.alexa.accessoryclient.common.util.MetricsConstants;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.util.DeviceInfo;
import com.android.tools.r8.GeneratedOutlineSupport;
/* loaded from: classes12.dex */
public class BluetoothHeadsetHelper {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, BluetoothHeadsetHelper.class);
    private AudioManager mAudioManager;
    private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private BluetoothHeadset mBluetoothHeadset;
    private BluetoothReceiverConnection mBluetoothReceiverConnection;
    private final Context mContext;
    private SpeakerStateReceiver mSpeakerStateReceiver;
    private final SipClientState sipClientState;
    private boolean started;

    @VisibleForTesting(otherwise = 2)
    /* loaded from: classes12.dex */
    public class BluetoothReceiverConnection extends BroadcastReceiver {
        public BluetoothReceiverConnection() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null && "android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                if (BluetoothHeadsetHelper.this.isBluetoothAvailable()) {
                    if (BluetoothHeadsetHelper.this.mAudioManager.isBluetoothScoOn()) {
                        return;
                    }
                    if (!Utils.isMarshMallowAndAbove() && BluetoothHeadsetHelper.this.sipClientState.getCallState() == SipClientState.CallState.ACTIVE) {
                        return;
                    }
                    BluetoothHeadsetHelper.this.useBluetoothHeadset();
                } else if (!BluetoothHeadsetHelper.this.mAudioManager.isBluetoothScoOn()) {
                } else {
                    BluetoothHeadsetHelper.this.stopUsingBluetoothHeadset();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @VisibleForTesting(otherwise = 2)
    /* loaded from: classes12.dex */
    public class SpeakerStateReceiver extends BroadcastReceiver {
        protected SpeakerStateReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null && CallUtils.INTENT_ACTION_SPEAKER.equals(intent.getAction())) {
                if (BluetoothHeadsetHelper.this.mAudioManager.isSpeakerphoneOn()) {
                    if (!BluetoothHeadsetHelper.this.mAudioManager.isBluetoothScoOn()) {
                        return;
                    }
                    BluetoothHeadsetHelper.this.stopUsingBluetoothHeadset();
                } else if (!BluetoothHeadsetHelper.this.isBluetoothAvailable()) {
                } else {
                    BluetoothHeadsetHelper.this.useBluetoothHeadset();
                }
            }
        }
    }

    public BluetoothHeadsetHelper(Context context, SipClientState sipClientState) {
        this.mContext = context;
        this.mAudioManager = (AudioManager) this.mContext.getSystemService("audio");
        this.sipClientState = sipClientState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isBluetoothAvailable() {
        return this.mBluetoothHeadset != null && !Utils.isFireOS5() && !this.mBluetoothHeadset.getConnectedDevices().isEmpty();
    }

    private void requestBluetoothHeadSetProxy() {
        this.mBluetoothAdapter.getProfileProxy(this.mContext, new BluetoothProfile.ServiceListener() { // from class: com.amazon.deecomms.calling.service.BluetoothHeadsetHelper.1
            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
                BluetoothHeadsetHelper.LOG.i("onServiceConnected");
                if (i == 1) {
                    BluetoothHeadsetHelper.this.mBluetoothHeadset = (BluetoothHeadset) bluetoothProfile;
                    if (!BluetoothHeadsetHelper.this.isBluetoothAvailable()) {
                        return;
                    }
                    BluetoothHeadsetHelper.this.useBluetoothHeadset();
                }
            }

            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceDisconnected(int i) {
                BluetoothHeadsetHelper.LOG.i(MetricsConstants.CLIENT_CONNECTION_ON_SERVICE_DISCONNECTED);
                if (i == 1) {
                    BluetoothHeadsetHelper.this.mBluetoothHeadset = null;
                }
            }
        }, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopUsingBluetoothHeadset() {
        LOG.i("Stop using bluetooth headset");
        this.mAudioManager.stopBluetoothSco();
        this.mAudioManager.setBluetoothScoOn(false);
        if (!DeviceInfo.isPhone(this.mContext)) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Tried to turn speakerphone true; speakerphone was set to ");
            outline1.append(this.mAudioManager.isSpeakerphoneOn());
            commsLogger.i(outline1.toString());
            this.mAudioManager.setSpeakerphoneOn(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void useBluetoothHeadset() {
        LOG.i("Using bluetooth headset");
        this.mAudioManager.setMode(0);
        LOG.i("Audio mode is set to normal");
        this.mAudioManager.startBluetoothSco();
        this.mAudioManager.setBluetoothScoOn(true);
        this.mAudioManager.setSpeakerphoneOn(false);
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Tried to turn speakerphone false; speakerphone was set to ");
        outline1.append(this.mAudioManager.isSpeakerphoneOn());
        commsLogger.i(outline1.toString());
        Utils.setAudioMode(this.mAudioManager);
    }

    public synchronized void start() {
        if (!this.started && this.mBluetoothAdapter != null) {
            requestBluetoothHeadSetProxy();
            this.mBluetoothReceiverConnection = new BluetoothReceiverConnection();
            this.mContext.registerReceiver(this.mBluetoothReceiverConnection, new IntentFilter("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED"));
            this.mSpeakerStateReceiver = new SpeakerStateReceiver();
            LocalBroadcastManager.getInstance(this.mContext).registerReceiver(this.mSpeakerStateReceiver, new IntentFilter(CallUtils.INTENT_ACTION_SPEAKER));
            this.started = true;
        }
    }

    public synchronized void stop() {
        if (this.started) {
            try {
                this.mContext.unregisterReceiver(this.mBluetoothReceiverConnection);
                LocalBroadcastManager.getInstance(this.mContext).unregisterReceiver(this.mSpeakerStateReceiver);
            } catch (IllegalArgumentException unused) {
                LOG.e("Already unregistered");
            }
            if (this.mAudioManager.isBluetoothScoOn()) {
                this.mAudioManager.setBluetoothScoOn(false);
                this.mAudioManager.stopBluetoothSco();
            }
            BluetoothHeadset bluetoothHeadset = this.mBluetoothHeadset;
            if (bluetoothHeadset != null) {
                this.mBluetoothAdapter.closeProfileProxy(1, bluetoothHeadset);
                this.mBluetoothHeadset = null;
            }
            this.started = false;
        }
    }
}
