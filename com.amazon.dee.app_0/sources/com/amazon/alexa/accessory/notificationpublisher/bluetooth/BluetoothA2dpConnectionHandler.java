package com.amazon.alexa.accessory.notificationpublisher.bluetooth;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioInteractionScheduler;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StatusEventManager;
import com.amazon.alexa.accessory.notificationpublisher.providers.AccessoryProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.accessory.protocol.Media;
import com.amazon.devicesetupservice.reporting.Radio;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public final class BluetoothA2dpConnectionHandler {
    private static final long DELAY_BETWEEN_MULTIPLE_PAUSES_MS = 500;
    private static final long ISSUE_MEDIA_CONTROL_PAUSE_DELAY_MS = 120;
    private static final long MAX_NUMBER_OF_PAUSE_COMMANDS = 4;
    private static final int MUTE_VOLUME = 0;
    private static final int NO_FLAGS = 0;
    private static final String TAG = "BluetoothA2dpConnectionHandler";
    private static BluetoothA2dpConnectionHandler instance;
    private AudioManager audioManager;
    private final Handler backgroundThreadHandler;
    private BluetoothA2dp btA2dp;
    private BluetoothAdapter btAdapter;
    private BluetoothProfile.ServiceListener btProfileListener;
    private final ReconnectAccessoryA2dpRunnable reconnectRunnable;
    private static final long ISSUE_MEDIA_CONTROL_PLAY_DELAY_MS = TimeUnit.SECONDS.toMillis(1);
    private static final String BACKGROUND_THREAD_NAME = BluetoothA2dpConnectionHandler.class.getSimpleName() + Radio.THREAD;
    private static final IntentFilter btA2dpConnectionChangeIntentFilter = new IntentFilter("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
    private final AccessoryProvider.IssueMediaControlCallback mediaControlPlayCallback = new AccessoryProvider.IssueMediaControlCallback() { // from class: com.amazon.alexa.accessory.notificationpublisher.bluetooth.BluetoothA2dpConnectionHandler.1
        @Override // com.amazon.alexa.accessory.notificationpublisher.providers.AccessoryProvider.IssueMediaControlCallback
        public void onMediaControlIssued(boolean z, Throwable th, Media.MediaControl mediaControl) {
            Log.i(BluetoothA2dpConnectionHandler.TAG, String.format(Locale.US, "mediaControlPlayCallback - Control: %s, Success: %s", Integer.valueOf(mediaControl.getNumber()), Boolean.valueOf(z)));
            if (!z || th != null) {
                Log.d(BluetoothA2dpConnectionHandler.TAG, "mediaControlPlayCallback - Restore volume");
                BluetoothA2dpConnectionHandler.this.restoreMusicVolumeAndResetAttributes();
                return;
            }
            try {
                Thread.sleep(BluetoothA2dpConnectionHandler.ISSUE_MEDIA_CONTROL_PAUSE_DELAY_MS);
            } catch (Exception e) {
                GeneratedOutlineSupport1.outline157("mediaControlPlayCallback - Exception: ", e, BluetoothA2dpConnectionHandler.TAG);
            }
            Log.i(BluetoothA2dpConnectionHandler.TAG, "mediaControlPlayCallback - Call issueMediaControlPause");
            boolean issueMediaControlPause = BluetoothA2dpConnectionHandler.this.issueMediaControlPause();
            BluetoothA2dpConnectionHandler.this.stopAlexaMusicPlayback();
            if (issueMediaControlPause) {
                return;
            }
            Log.w(BluetoothA2dpConnectionHandler.TAG, "mediaControlPlayCallback - Pause Request was not sent to accessory");
            BluetoothA2dpConnectionHandler.this.restoreMusicVolumeAndResetAttributes();
        }
    };
    private final AccessoryProvider.IssueMediaControlCallback mediaControlPauseControlCallback = new AccessoryProvider.IssueMediaControlCallback() { // from class: com.amazon.alexa.accessory.notificationpublisher.bluetooth.BluetoothA2dpConnectionHandler.2
        @Override // com.amazon.alexa.accessory.notificationpublisher.providers.AccessoryProvider.IssueMediaControlCallback
        public void onMediaControlIssued(boolean z, Throwable th, Media.MediaControl mediaControl) {
            Log.i(BluetoothA2dpConnectionHandler.TAG, String.format(Locale.US, "mediaControlPauseControlCallback - Control: %s, Success: %s", Integer.valueOf(mediaControl.getNumber()), Boolean.valueOf(z)));
            BluetoothA2dpConnectionHandler.access$408(BluetoothA2dpConnectionHandler.this);
            if (BluetoothA2dpConnectionHandler.this.numberOfPauseCommands > 4) {
                BluetoothA2dpConnectionHandler.this.restoreMusicVolumeAndResetAttributes();
                BluetoothA2dpConnectionHandler.this.numberOfPauseCommands = 0;
                return;
            }
            try {
                Thread.sleep(500L);
            } catch (Exception e) {
                GeneratedOutlineSupport1.outline157("mediaControlPauseControlCallback - Exception: ", e, BluetoothA2dpConnectionHandler.TAG);
            }
            BluetoothA2dpConnectionHandler.this.issueMediaControlPause();
            BluetoothA2dpConnectionHandler.this.stopAlexaMusicPlayback();
        }
    };
    private final BluetoothA2dpConnectionChangedReceiver btA2dpConnectionChangeReceiver = new BluetoothA2dpConnectionChangedReceiver();
    private String zionMacAddress = "";
    private int volumeAtConnect = 0;
    private int volumeBeforeMute = 0;
    private boolean muteSuccessful = false;
    private int numberOfPauseCommands = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class BluetoothA2dpConnectionChangedReceiver extends BroadcastReceiver {
        BluetoothA2dpConnectionChangedReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public synchronized void onReceive(Context context, Intent intent) {
            String address;
            Log.d(BluetoothA2dpConnectionHandler.TAG, "BluetoothA2dpConnectionChangedReceiver - onReceive");
            Bundle extras = intent.getExtras();
            if (extras != null) {
                try {
                    address = ((BluetoothDevice) extras.getParcelable("android.bluetooth.device.extra.DEVICE")).getAddress();
                } catch (Exception e) {
                    String str = BluetoothA2dpConnectionHandler.TAG;
                    Log.w(str, "BluetoothA2dpConnectionChangedReceiver- Exception getting device address" + e);
                }
                int i = extras.getInt("android.bluetooth.profile.extra.STATE");
                if (Strings.isNullOrEmpty(address)) {
                    Log.w(BluetoothA2dpConnectionHandler.TAG, "BluetoothA2dpConnectionChangedReceiver - Device address is null or empty.");
                    return;
                }
                Log.d(BluetoothA2dpConnectionHandler.TAG, String.format(Locale.US, "BluetoothA2dpConnectionChangedReceiver - Device: %s, Connection State: %d", address, Integer.valueOf(i)));
                if (i == 0) {
                    try {
                        if (BluetoothA2dpConnectionHandler.this.btA2dp != null) {
                            List<BluetoothDevice> connectedDevices = BluetoothA2dpConnectionHandler.this.btA2dp.getConnectedDevices();
                            if (connectedDevices == null) {
                                Log.i(BluetoothA2dpConnectionHandler.TAG, "BluetoothA2dpConnectionChangedReceiver - BT Device list is null, return");
                            } else if (connectedDevices.size() == 0) {
                                Log.i(BluetoothA2dpConnectionHandler.TAG, "BluetoothA2dpConnectionChangedReceiver - No Active A2DP Connections");
                                StatusEventManager.getInstance().onNoA2dpConnectionsActive();
                            } else if (!address.equalsIgnoreCase(BluetoothA2dpConnectionHandler.this.zionMacAddress) && connectedDevices.size() == 1 && connectedDevices.get(0).getAddress().equalsIgnoreCase(BluetoothA2dpConnectionHandler.this.zionMacAddress)) {
                                Log.i(BluetoothA2dpConnectionHandler.TAG, "BluetoothA2dpConnectionChangedReceiver - Reconnect Accessory A2DP");
                                BluetoothA2dpConnectionHandler.this.reconnectAccessoryA2dpToPhone();
                            }
                        }
                    } catch (Exception e2) {
                        String str2 = BluetoothA2dpConnectionHandler.TAG;
                        Log.e(str2, "BluetoothA2dpConnectionChangedReceiver - Exception calling reconnect: " + e2);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class ReconnectAccessoryA2dpRunnable implements Runnable {
        ReconnectAccessoryA2dpRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            BluetoothA2dpConnectionHandler.this.stopAlexaMusicPlayback();
            BluetoothA2dpConnectionHandler bluetoothA2dpConnectionHandler = BluetoothA2dpConnectionHandler.this;
            bluetoothA2dpConnectionHandler.volumeBeforeMute = bluetoothA2dpConnectionHandler.getCurrentMusicVolume();
            BluetoothA2dpConnectionHandler bluetoothA2dpConnectionHandler2 = BluetoothA2dpConnectionHandler.this;
            bluetoothA2dpConnectionHandler2.muteSuccessful = bluetoothA2dpConnectionHandler2.setMusicVolume(0);
            String str = BluetoothA2dpConnectionHandler.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ReconnectAccessoryA2dpRunnable - run - Mute music stream - ");
            outline107.append(BluetoothA2dpConnectionHandler.this.muteSuccessful);
            Log.i(str, outline107.toString());
            if (!BluetoothA2dpConnectionHandler.this.issueMediaControlPlay()) {
                Log.w(BluetoothA2dpConnectionHandler.TAG, "ReconnectAccessoryA2dpRunnable - run - Play Request was not sent to accessory");
                BluetoothA2dpConnectionHandler.this.restoreMusicVolumeAndResetAttributes();
            }
        }
    }

    private BluetoothA2dpConnectionHandler() {
        HandlerThread handlerThread = new HandlerThread(BACKGROUND_THREAD_NAME);
        handlerThread.start();
        this.backgroundThreadHandler = new Handler(handlerThread.getLooper());
        this.reconnectRunnable = new ReconnectAccessoryA2dpRunnable();
        this.audioManager = (AudioManager) DependencyProvider.getContext().getSystemService("audio");
    }

    static /* synthetic */ int access$408(BluetoothA2dpConnectionHandler bluetoothA2dpConnectionHandler) {
        int i = bluetoothA2dpConnectionHandler.numberOfPauseCommands;
        bluetoothA2dpConnectionHandler.numberOfPauseCommands = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getCurrentMusicVolume() {
        Log.d(TAG, "getCurrentMusicVolume");
        try {
            return this.audioManager.getStreamVolume(3);
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline157("getCurrentMusicVolume - Exception: ", e, TAG);
            return 0;
        }
    }

    public static synchronized BluetoothA2dpConnectionHandler getInstance() {
        BluetoothA2dpConnectionHandler bluetoothA2dpConnectionHandler;
        synchronized (BluetoothA2dpConnectionHandler.class) {
            if (instance == null) {
                Log.i(TAG, "getInstance - Creating instance");
                instance = new BluetoothA2dpConnectionHandler();
            }
            bluetoothA2dpConnectionHandler = instance;
        }
        return bluetoothA2dpConnectionHandler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean issueMediaControlPause() {
        Log.d(TAG, "issueMediaControlPause");
        try {
            return AccessoryProvider.issueMediaControlToAccessory(Media.MediaControl.PAUSE, this.mediaControlPauseControlCallback);
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "issueMediaControlPause - Exception: " + e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean issueMediaControlPlay() {
        Log.d(TAG, "issueMediaControlPlay");
        try {
            return AccessoryProvider.issueMediaControlToAccessory(Media.MediaControl.PLAY, this.mediaControlPlayCallback);
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "issueMediaControlPlay - Exception: " + e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reconnectAccessoryA2dpToPhone() {
        Log.i(TAG, "reconnectAccessoryA2dpToPhone");
        this.backgroundThreadHandler.postDelayed(this.reconnectRunnable, ISSUE_MEDIA_CONTROL_PLAY_DELAY_MS);
    }

    public static synchronized void releaseInstance() {
        synchronized (BluetoothA2dpConnectionHandler.class) {
            if (instance != null) {
                Log.i(TAG, "releaseInstance - Releasing instance");
                instance.resetState();
                instance.audioManager = null;
            }
            instance = null;
        }
    }

    private void resetCachedVolumeAttributes() {
        this.volumeBeforeMute = 0;
        this.muteSuccessful = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetState() {
        Log.d(TAG, "resetState");
        try {
            DependencyProvider.getContext().unregisterReceiver(this.btA2dpConnectionChangeReceiver);
            this.backgroundThreadHandler.removeCallbacks(this.reconnectRunnable);
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline157("resetState - Exception when unregistering A2DP Connection change receiver - ", e, TAG);
        }
        try {
            this.btAdapter.closeProfileProxy(2, this.btA2dp);
        } catch (Exception e2) {
            GeneratedOutlineSupport1.outline157("resetState - Exception when closing A2DP proxy - ", e2, TAG);
        }
        this.btProfileListener = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restoreMusicVolumeAndResetAttributes() {
        Log.d(TAG, "restoreMusicVolumeAndResetAttributes");
        try {
            if (this.volumeBeforeMute <= 0 || !this.muteSuccessful) {
                return;
            }
            setMusicVolume(this.volumeBeforeMute);
            resetCachedVolumeAttributes();
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline157("restoreMusicVolumeAndResetAttributes - Exception: ", e, TAG);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean setMusicVolume(int i) {
        int streamVolume;
        try {
            String str = TAG;
            Log.d(str, "setMusicVolume - Setting volume to " + i);
            this.audioManager.setStreamVolume(3, i, 0);
            streamVolume = this.audioManager.getStreamVolume(3);
            String str2 = TAG;
            Log.d(str2, "setMusicVolume - New volume: " + streamVolume);
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline157("setMusicVolume - Exception when setting volume: ", e, TAG);
        }
        return streamVolume == i;
    }

    private synchronized void setupA2dpProfileListener() {
        Log.d(TAG, "setupA2dpProfileListener");
        final Context context = DependencyProvider.getContext();
        if (context == null) {
            Log.w(TAG, "setupA2dpProfileListener - Cannot setup because context is null");
            return;
        }
        this.btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!this.btAdapter.isEnabled()) {
            Log.w(TAG, "setupA2dpProfileListener - Cannot init because Bluetooth adapter is disabled");
            return;
        }
        if (this.btProfileListener == null) {
            this.btProfileListener = new BluetoothProfile.ServiceListener() { // from class: com.amazon.alexa.accessory.notificationpublisher.bluetooth.BluetoothA2dpConnectionHandler.3
                @Override // android.bluetooth.BluetoothProfile.ServiceListener
                public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
                    if (i == 2) {
                        Log.i(BluetoothA2dpConnectionHandler.TAG, "onServiceConnected - Bluetooth A2DP profile connected");
                        BluetoothA2dpConnectionHandler.this.btA2dp = (BluetoothA2dp) bluetoothProfile;
                        context.registerReceiver(BluetoothA2dpConnectionHandler.this.btA2dpConnectionChangeReceiver, BluetoothA2dpConnectionHandler.btA2dpConnectionChangeIntentFilter);
                    }
                }

                @Override // android.bluetooth.BluetoothProfile.ServiceListener
                public void onServiceDisconnected(int i) {
                    Log.i(BluetoothA2dpConnectionHandler.TAG, "onServiceDisconnected - Bluetooth A2DP profile disconnected");
                    BluetoothA2dpConnectionHandler.this.resetState();
                }
            };
        }
        this.btAdapter.getProfileProxy(context, this.btProfileListener, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopAlexaMusicPlayback() {
        Log.d(TAG, "stopAlexaMusicPlayback");
        try {
            AudioInteractionScheduler.getInstance().stop();
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline157("stopAlexaMusicPlayback - Exception: ", e, TAG);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0010, code lost:
        if (r3.btA2dp.getConnectedDevices().size() > 0) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized boolean isAtLeastOneA2dpConnected() {
        /*
            r3 = this;
            monitor-enter(r3)
            r0 = 1
            android.bluetooth.BluetoothA2dp r1 = r3.btA2dp     // Catch: java.lang.Throwable -> L16 java.lang.Exception -> L18
            if (r1 == 0) goto L13
            android.bluetooth.BluetoothA2dp r1 = r3.btA2dp     // Catch: java.lang.Throwable -> L16 java.lang.Exception -> L18
            java.util.List r1 = r1.getConnectedDevices()     // Catch: java.lang.Throwable -> L16 java.lang.Exception -> L18
            int r1 = r1.size()     // Catch: java.lang.Throwable -> L16 java.lang.Exception -> L18
            if (r1 <= 0) goto L13
            goto L14
        L13:
            r0 = 0
        L14:
            monitor-exit(r3)
            return r0
        L16:
            r0 = move-exception
            goto L21
        L18:
            java.lang.String r1 = com.amazon.alexa.accessory.notificationpublisher.bluetooth.BluetoothA2dpConnectionHandler.TAG     // Catch: java.lang.Throwable -> L16
            java.lang.String r2 = "isAtLeastOneA2dpConnected - Exception when getting A2DP connected devices"
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.e(r1, r2)     // Catch: java.lang.Throwable -> L16
            monitor-exit(r3)
            return r0
        L21:
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.accessory.notificationpublisher.bluetooth.BluetoothA2dpConnectionHandler.isAtLeastOneA2dpConnected():boolean");
    }

    public synchronized void onZionDeviceConnectionStateChanged(boolean z, String str) {
        String str2 = TAG;
        Log.d(str2, "onZionDeviceConnectionStateChanged - connected: " + z);
        if (z) {
            this.zionMacAddress = str;
            this.volumeAtConnect = getCurrentMusicVolume();
            AudioInteractionScheduler.getInstance();
            setupA2dpProfileListener();
        } else {
            setMusicVolume(this.volumeAtConnect);
            this.zionMacAddress = "";
        }
    }

    /* renamed from: clone */
    public BluetoothA2dpConnectionHandler m334clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone a singleton");
    }
}
