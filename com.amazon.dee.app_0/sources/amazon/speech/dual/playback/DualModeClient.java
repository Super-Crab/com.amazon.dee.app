package amazon.speech.dual.playback;

import amazon.speech.playback.ITtsPlaybackListener;
import amazon.speech.simclient.common.AsyncServiceConnectionManager;
import amazon.speech.simclient.common.BaseClient;
import amazon.speech.simclient.common.ServiceConnectionManager;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.vizzini.voiceservice.tts.ITTSCallback;
import com.amazon.vizzini.voiceservice.tts.ITTSPlayback;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class DualModeClient extends BaseClient<ITTSPlayback> {
    static final boolean FORCE_INTERNAL_SPEAKER = false;
    private volatile long mUID;
    public static final String TAG = GeneratedOutlineSupport1.outline39(DualModeClient.class, GeneratedOutlineSupport1.outline107("CSM-"));
    static final Intent BIND_INTENT = new Intent("com.amazon.vizzini.PLAY_TTS");

    public DualModeClient(Context context) {
        this(context, new AsyncServiceConnectionManager());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isDualModePlaybackServiceAvailable(Context context) {
        return BaseClient.serviceExists(context, BIND_INTENT, "com.amazon.vizzini.PLAY_TTS");
    }

    ITTSCallback createDualCallback(final ITtsPlaybackListener iTtsPlaybackListener) {
        if (iTtsPlaybackListener != null) {
            return new ITTSCallback.Stub() { // from class: amazon.speech.dual.playback.DualModeClient.4
                @Override // com.amazon.vizzini.voiceservice.tts.ITTSCallback
                public void notifyTTSPlaybackInterrupted(long j) throws RemoteException {
                    Log.i(DualModeClient.TAG, "notifyTTSPlaybackInterrupted()");
                    iTtsPlaybackListener.notifyTTSPlaybackInterrupted(j);
                }

                @Override // com.amazon.vizzini.voiceservice.tts.ITTSCallback
                public void notifyTTSPlaybackStarted(long j) throws RemoteException {
                    Log.i(DualModeClient.TAG, "notifyTTSPlaybackStarted()");
                    iTtsPlaybackListener.notifyTTSPlaybackStarted(j);
                }

                @Override // com.amazon.vizzini.voiceservice.tts.ITTSCallback
                public boolean ttsHeartbeat(long j) throws RemoteException {
                    Log.i(DualModeClient.TAG, "ttsHeartbeat()");
                    return iTtsPlaybackListener.ttsHeartbeat(j);
                }
            };
        }
        throw new IllegalArgumentException("ITtsPlaybackListener cannot be null");
    }

    @Override // amazon.speech.simclient.common.BaseClient
    protected BaseClient<ITTSPlayback>.BaseClientServiceConnection createServiceConnection() {
        return new BaseClient<ITTSPlayback>.BaseClientServiceConnection() { // from class: amazon.speech.dual.playback.DualModeClient.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // amazon.speech.simclient.common.BaseClient.BaseClientServiceConnection
            /* renamed from: convertIBinderToService  reason: avoid collision after fix types in other method */
            public ITTSPlayback mo49convertIBinderToService(IBinder iBinder) {
                Log.i(DualModeClient.TAG, "convertIBinderToService");
                return ITTSPlayback.Stub.asInterface(iBinder);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void endTTS() {
        Log.i(TAG, "endTTS");
        getServiceConnectionManager().addCallback(new ServiceConnectionManager.AutoDisconnectCallback() { // from class: amazon.speech.dual.playback.DualModeClient.3
            @Override // amazon.speech.simclient.common.ServiceConnectionManager.AutoDisconnectCallback
            public void onOneTimeServiceConnected() {
                try {
                    String str = DualModeClient.TAG;
                    Log.i(str, "ending UID: " + DualModeClient.this.mUID);
                    ((ITTSPlayback) DualModeClient.this.getService()).endTTS(DualModeClient.this.mUID);
                } catch (RemoteException unused) {
                    Log.e(DualModeClient.TAG, "DualModeClient endTTS() threw a RemoteException");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void playTTS(final ITtsPlaybackListener iTtsPlaybackListener) {
        if (iTtsPlaybackListener != null) {
            Log.i(TAG, "playTTS");
            getServiceConnectionManager().addCallback(new ServiceConnectionManager.AutoDisconnectCallback() { // from class: amazon.speech.dual.playback.DualModeClient.2
                @Override // amazon.speech.simclient.common.ServiceConnectionManager.AutoDisconnectCallback
                public void onOneTimeServiceConnected() {
                    try {
                        DualModeClient.this.mUID = ((ITTSPlayback) DualModeClient.this.getService()).playTTS(DualModeClient.this.createDualCallback(iTtsPlaybackListener), false);
                        String str = DualModeClient.TAG;
                        Log.i(str, "playing UID: " + DualModeClient.this.mUID);
                    } catch (RemoteException unused) {
                        Log.e(DualModeClient.TAG, "DualModeClient playTTS() threw a RemoteException");
                        iTtsPlaybackListener.notifyTTSPlaybackStarted(DualModeClient.this.mUID);
                        iTtsPlaybackListener.notifyTTSPlaybackInterrupted(DualModeClient.this.mUID);
                    }
                }
            });
            return;
        }
        throw new IllegalArgumentException("ITtsPlaybackListener cannot be null");
    }

    DualModeClient(Context context, ServiceConnectionManager serviceConnectionManager) {
        super(ITTSPlayback.class, context, BIND_INTENT, "com.amazon.vizzini.PLAY_TTS", serviceConnectionManager);
        this.mUID = -1L;
    }
}
