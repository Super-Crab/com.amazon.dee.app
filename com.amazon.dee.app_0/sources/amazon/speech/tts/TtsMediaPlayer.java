package amazon.speech.tts;

import amazon.speech.playback.ITtsPlaybackController;
import amazon.speech.playback.ITtsPlaybackListener;
import amazon.speech.simclient.focus.ExplicitFocusClient;
import amazon.speech.simclient.metrics.MetricsClient;
import amazon.speech.tts.AudioPlayer;
import amazon.speech.tts.TtsEventListener;
import amazon.speech.tts.asp.AspPlaybackReporter;
import amazon.speech.tts.playback.DefaultPlaybackController;
import amazon.speech.util.AudioFocusHelper;
import amazon.speech.util.AudioManagerWrapper;
import amazon.speech.util.HandlerWrapper;
import amazon.speech.util.InProcTtsStreamProvider;
import amazon.speech.util.Log;
import amazon.speech.util.RuntimeDeviceTypeHelper;
import amazon.speech.util.TimestampWrapper;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Trace;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class TtsMediaPlayer implements ITtsMediaPlayer {
    protected static final long AUDIO_PLAYER_PREPARE_TIMEOUT = 20;
    private static final boolean DEBUG = true;
    private static final int DUCKING_BUFFER_MS = 500;
    static final String ERROR_DATASOURCE_EXCEPTION_MESSAGE = "An error occurred while setting the datasource";
    static final String ERROR_MEDIAPLAYER_EXCEPTION_MESSAGE = "A MediaPlayer Exception occurred";
    static final String ERROR_MEDIA_PLAYBACK_MESSAGE = "An error occurred while playing audio";
    static final String ERROR_MEDIA_PREPARATION_TIMEOUT_MESSAGE = "A timeout occured while preparing to play TTS";
    static final String ERROR_NO_RESOURCE_MESSAGE = "Could not find resource";
    static final int HTTP_CONNECT_TIMEOUT = 15000;
    static final int HTTP_READ_TIMEOUT = 15000;
    static final String LOCALHOST_URL_PATTERN = "http://127.0.0.1";
    static final String METRICS_METADATA_WIFI_PERMISSIONS_MISSING_FROM_PKG = "pkg_needs_wifi_perm";
    static final String METRICS_METADATA_WIFI_SIGNAL = "wifi_signal";
    static final String METRIC_BAD_URL = " SIM.TtsMediaPlayer.InvalidURL";
    static final String METRIC_LOCALHOST_URL_CONNECT = "SIM.TtsMediaPlayer.urlConnectLocalhost";
    static final String METRIC_PROGRAM_NAME = "TtsMediaPlayer";
    static final String METRIC_SOCKET_TIMEOUT = "SIM.TtsMediaPlayer.SocketTimeoutException";
    static final String METRIC_SOCKET_TIMEOUT_SIGNAL_STRENGTH = "SIM.TtsMediaPlayer.SocketTimeoutException.SignalStrength";
    static final String METRIC_SOURCE = "TtsMediaPlayer_Metrics";
    static final String METRIC_SPEAK = "ttsSpeak";
    static final String METRIC_SPEAK_CANCEL = "ttsSpeakCancelled";
    static final String METRIC_SPEAK_INTERRUPT = "ttsSpeakInterrupted";
    static final String METRIC_SPEAK_URI_SLOW_SPINUP = "SIM.TtsMediaPlayer.ttsSpeakUriSlowSpinup";
    static final long METRIC_SPEAK_URI_SPINUP_THRESHOLD_MS = 1000;
    static final String METRIC_TTS_BUFFERING_LATENCY = "SIM.TtsMediaPlayer.BufferingLatency";
    static final String METRIC_TTS_PLAYBACK_ERROR = "SIM.TtsMediaPlayer.PlaybackError";
    static final String METRIC_TTS_PLAYBACK_ERROR_EXCEPTION = "exception";
    static final String METRIC_TTS_PLAYBACK_ERROR_MESSAGE = "message";
    static final String METRIC_TTS_PLAYBACK_TIMEOUT = "SIM.TtsMediaPlayer.BufferingTimeout";
    static final String METRIC_TTS_PREWARM_TOTAL_TIME = "SIM.TtsMediaPlayer.PrewarmTotalTime";
    static final String METRIC_TTS_PREWARM_WAIT_TIME = "SIM.TtsMediaPlayer.PrewarmWaitTime";
    static final String METRIC_TTS_REBUFFERING = "SIM.TtsMediaPlayer.RebufferingAttempts";
    static final String METRIC_URL_CONNECT = "SIM.TtsMediaPlayer.urlConnect";
    private static final int MIN_HTTP_ERROR = 400;
    static final int NUM_CLEANUP_CONNECTION_THREADS = 3;
    static final int NUM_ESTABLISH_CONNECTION_THREADS = 3;
    private static final int ONE_MILLION = 1000000;
    static final long PLAYBACK_CALLBACK_TIMEOUT_MS = 2000;
    static final long PREWARM_MAX_WAIT_MS = 20000;
    static final String SERVICE_SPEECH_START_ACTION = "amazon.speech.intent.action.TTS_CAPTION_SPEECH_START";
    static final String SERVICE_SPEECH_STOP_ACTION = "amazon.speech.intent.action.TTS_CAPTION_SPEECH_STOP";
    static final String SERVICE_TTS_CAPTION_EXTRA = "tts_caption";
    private static final String TAG = "TtsMediaPlayer";
    public static final String TRACE_POINT_TTS_BEGIN = "CSM_tts_begin";
    public static final String TRACE_POINT_TTS_END = "CSM_tts_end";
    public static final String TRACE_POINT_URL_BEGIN = "CSM_tts_url_connection_begin";
    public static final String TRACE_POINT_URL_END = "CSM_tts_url_connection_end";
    static final String TTS_CAPTION_PERMISSION = "amazon.speech.permission.DISPLAY_CAPTIONS";
    static final String TTS_SERVER_URL = "https://tinytts.amazon.com/";
    static final String WARNING_TTS_SPEAK_URI_LATENCY = "SpeakUri time (ms) to post speak runnable:";
    private static final HandlerThread gHandlerThread = new HandlerThread("TtsMediaPlayer.Speak", -16);
    private long mActiveUID;
    private final AspPlaybackReporter mAspReporter;
    AudioFocusHelper mAudioFocusHelper;
    AudioPlayer mAudioPlayer;
    AudioPlayerFactory mAudioPlayerFactory;
    String mCaption;
    final ExecutorService mCleanupConnectionExecutor;
    final ConnectionMetricsListener mConnectionMetricsListener;
    private final Context mContext;
    AudioPlayer.AudioPlayerListener mCurrentAudioPlayerListener;
    String mCurrentContentToken;
    final ExecutorService mEstablishConnectionExecutor;
    ExplicitFocusClient mExplicitFocusClient;
    boolean mFocusLockHeld;
    final Object mFutureSync;
    boolean mHasFocus;
    InputStream mInputStream;
    boolean mIsSpeakPending;
    boolean mIsSpeaking;
    private HandlerWrapper mListenerHandlerWrapper;
    final MetricsClient mMetricsClient;
    String mNamespace;
    ITtsPlaybackController mPlaybackController;
    ITtsPlaybackListener mPlaybackListener;
    final PrewarmCallback mPrewarmCallaback;
    TtsPrewarmTracker mPrewarmTracker;
    ScheduledExecutorService mScheduler;
    private HandlerWrapper mSpeakHandlerWrapper;
    private SpeechEventSender mSpeechEventSender;
    SpeechExecutionHolder mSpeechExecutionHolder;
    boolean mSpeechInitiatedWithCaptions;
    ISpeechMarksEmitterHelper mSpeechMarksEmitterHelper;
    private final TimestampWrapper mTimestamp;
    private final AlexaTtsAlert mTtsAlert;
    TtsEventSender mTtsEventSender;
    TtsListener mTtsListener;
    IUrlWrapperFactory mUrlWrapperFactory;
    private final WakeLockManager mWakeLockManager;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: amazon.speech.tts.TtsMediaPlayer$9  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass9 extends AudioPlayer.AudioPlayerListener {
        boolean mStarted;
        final /* synthetic */ String val$analyzerStates;
        final /* synthetic */ String val$caption;
        final /* synthetic */ String val$contentToken;
        final /* synthetic */ TtsListener val$listener;
        final /* synthetic */ ITtsSpeechMarksEmitter val$marksEmitter;
        final /* synthetic */ String val$namespace;

        AnonymousClass9(String str, TtsListener ttsListener, String str2, String str3, ITtsSpeechMarksEmitter iTtsSpeechMarksEmitter, String str4) {
            this.val$caption = str;
            this.val$listener = ttsListener;
            this.val$contentToken = str2;
            this.val$analyzerStates = str3;
            this.val$marksEmitter = iTtsSpeechMarksEmitter;
            this.val$namespace = str4;
        }

        void ended(TtsEventListener.CompletionCode completionCode) {
            String str = TtsMediaPlayer.TAG;
            Log.i(str, "ended, " + completionCode);
            long currentTimeMillis = TtsMediaPlayer.this.mTimestamp.currentTimeMillis();
            TtsMediaPlayer.this.recordTrace("CSM_tts_end");
            if (!TextUtils.isEmpty(this.val$contentToken) && completionCode == TtsEventListener.CompletionCode.SUCCESS) {
                TtsMediaPlayer.this.mSpeechEventSender.sendSpeechEndEvent(this.val$contentToken);
            }
            if (!TextUtils.isEmpty(this.val$caption)) {
                TtsMediaPlayer.this.sendSpeechStopNotification();
            }
            TtsMediaPlayer.this.setSpeaking(false, this.val$contentToken, null);
            TtsMediaPlayer.this.mTtsEventSender.fireSpeechCompleted(this.val$listener, currentTimeMillis, completionCode);
            TtsMediaPlayer.this.mListenerHandlerWrapper.post(new ReleaseFocusRunnable(this.val$namespace));
        }

        @Override // amazon.speech.tts.AudioPlayer.AudioPlayerListener
        public void onPlayWhenReadyCommitted() {
            Log.d(TtsMediaPlayer.TAG, "onPlayWhenReadyCommitted");
        }

        @Override // amazon.speech.tts.AudioPlayer.AudioPlayerListener
        public void onPlayerError(Exception exc) {
            Log.e(TtsMediaPlayer.TAG, "Player error", exc);
            if (!TextUtils.isEmpty(this.val$caption)) {
                TtsMediaPlayer.this.sendSpeechStopNotification();
            }
            if (!TextUtils.isEmpty(this.val$contentToken)) {
                AudioPlayer audioPlayer = TtsMediaPlayer.this.mAudioPlayer;
                TtsMediaPlayer.this.mSpeechEventSender.sendSpeechInterruptEvent(this.val$contentToken, audioPlayer == null ? 0L : audioPlayer.getCurrentPosition());
            }
            InputStream inputStream = TtsMediaPlayer.this.mInputStream;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception unused) {
                }
            }
            TtsMediaPlayer ttsMediaPlayer = TtsMediaPlayer.this;
            ttsMediaPlayer.mTtsEventSender.fireSpeechError(this.val$listener, ttsMediaPlayer.mTimestamp.currentTimeMillis(), TtsEventListener.ErrorCode.MEDIA_PLAYBACK_ERROR, TtsMediaPlayer.ERROR_MEDIA_PLAYBACK_MESSAGE);
            TtsMediaPlayer.this.mListenerHandlerWrapper.post(new ReleaseFocusRunnable(this.val$namespace));
            TtsMediaPlayer.this.setSpeaking(false, this.val$contentToken, null);
            AudioPlayer audioPlayer2 = TtsMediaPlayer.this.mAudioPlayer;
            if (audioPlayer2 != null) {
                audioPlayer2.release();
                TtsMediaPlayer.this.mAudioPlayer = null;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("exception", exc.getClass().getSimpleName());
            if (exc.getMessage() != null) {
                hashMap.put("message", exc.getMessage());
            }
            TtsMediaPlayer.this.mMetricsClient.obtainCounter(TtsMediaPlayer.METRIC_TTS_PLAYBACK_ERROR).addMetadata(hashMap).setCount(1L).record();
        }

        @Override // amazon.speech.tts.AudioPlayer.AudioPlayerListener
        public void onPlayerStateChanged(boolean z, AudioPlayer.AudioPlayerState audioPlayerState) {
            String str = TtsMediaPlayer.TAG;
            Log.d(str, "onPlayerStateChanged - playWhenReady=" + z + " playbackState=" + audioPlayerState);
            if (audioPlayerState != AudioPlayer.AudioPlayerState.STATE_BUFFERING && audioPlayerState != AudioPlayer.AudioPlayerState.STATE_PREPARING) {
                if (audioPlayerState == AudioPlayer.AudioPlayerState.STATE_ENDED) {
                    ended(TtsEventListener.CompletionCode.SUCCESS);
                    return;
                } else if (audioPlayerState != AudioPlayer.AudioPlayerState.STATE_READY || this.mStarted) {
                    return;
                } else {
                    TtsMediaPlayer.this.recordTrace("CSM_tts_begin");
                    System.currentTimeMillis();
                    if (!TextUtils.isEmpty(this.val$caption)) {
                        TtsMediaPlayer.this.sendSpeechStartNotification(this.val$caption);
                    }
                    TtsMediaPlayer ttsMediaPlayer = TtsMediaPlayer.this;
                    ttsMediaPlayer.mTtsEventSender.fireSpeechStarted(this.val$listener, ttsMediaPlayer.mTimestamp.currentTimeMillis());
                    this.mStarted = true;
                    TtsMediaPlayer.this.setSpeaking(true, this.val$contentToken, this.val$analyzerStates);
                    synchronized (TtsMediaPlayer.this.mFutureSync) {
                        if (this.mTimeoutFuture != null) {
                            this.mTimeoutFuture.cancel(true);
                        }
                    }
                    if (this.val$marksEmitter != null) {
                        Log.d(TtsMediaPlayer.TAG, "onPlayerStateChanged starting SpeechMarksEmitter");
                        this.val$marksEmitter.start();
                    }
                    if (TextUtils.isEmpty(TtsMediaPlayer.this.mCurrentContentToken)) {
                        return;
                    }
                    TtsMediaPlayer.this.mSpeechEventSender.sendSpeechStartEvent(TtsMediaPlayer.this.mCurrentContentToken);
                    return;
                }
            }
            if (this.mStarted) {
                TtsMediaPlayer.this.mMetricsClient.obtainCounter(TtsMediaPlayer.METRIC_TTS_REBUFFERING).setCount(1L).record();
            } else {
                System.currentTimeMillis();
            }
            ScheduledFuture<?> scheduledFuture = this.mTimeoutFuture;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(true);
            }
            synchronized (TtsMediaPlayer.this.mFutureSync) {
                this.mTimeoutFuture = TtsMediaPlayer.this.mScheduler.schedule(new Runnable() { // from class: amazon.speech.tts.TtsMediaPlayer.9.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            AnonymousClass9.this.onTimeoutError();
                        } catch (Throwable th) {
                            Log.wtf(TtsMediaPlayer.TAG, "Error executing runnable!", th);
                        }
                    }
                }, 20L, TimeUnit.SECONDS);
            }
        }

        void onTimeoutError() {
            if (!TextUtils.isEmpty(this.val$caption)) {
                TtsMediaPlayer.this.sendSpeechStopNotification();
            }
            InputStream inputStream = TtsMediaPlayer.this.mInputStream;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception unused) {
                }
            }
            TtsMediaPlayer ttsMediaPlayer = TtsMediaPlayer.this;
            ttsMediaPlayer.mTtsEventSender.fireSpeechError(this.val$listener, ttsMediaPlayer.mTimestamp.currentTimeMillis(), TtsEventListener.ErrorCode.MEDIA_PREPARATION_TIMEOUT, TtsMediaPlayer.ERROR_MEDIA_PREPARATION_TIMEOUT_MESSAGE);
            TtsMediaPlayer.this.mListenerHandlerWrapper.post(new ReleaseFocusRunnable(this.val$namespace));
            TtsMediaPlayer.this.setSpeaking(false, this.val$contentToken, null);
            AudioPlayer audioPlayer = TtsMediaPlayer.this.mAudioPlayer;
            if (audioPlayer != null) {
                audioPlayer.release();
                TtsMediaPlayer.this.mAudioPlayer = null;
            }
            TtsMediaPlayer.this.mMetricsClient.obtainCounter(TtsMediaPlayer.METRIC_TTS_PLAYBACK_TIMEOUT).setCount(1L).record();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public interface ConnectionMetricsListener {
        void onBadUrl();

        void onPrewarmWaitComplete(long j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface ISpeechMarksEmitterHelper {
        void clearEmitter();

        ITtsSpeechMarksEmitter createEmitter(TtsSpeechMarksListener ttsSpeechMarksListener, HandlerWrapper handlerWrapper);

        ITtsSpeechMarksEmitter getEmitter();
    }

    /* loaded from: classes.dex */
    interface IUrlWrapperFactory {
        UrlWrapper create(String str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class NoOpTtsListener implements TtsListener {
        private NoOpTtsListener() {
        }

        @Override // amazon.speech.tts.TtsListener
        public void onSpeechCompleted() {
            Log.d(TtsMediaPlayer.TAG, "NoOpTtsListener - onSpeechCompleted");
        }

        @Override // amazon.speech.tts.TtsListener
        public void onSpeechError() {
            Log.d(TtsMediaPlayer.TAG, "NoOpTtsListener - onSpeechError");
        }
    }

    /* loaded from: classes.dex */
    private static class NoOpTtsSpeechMarksListener implements TtsSpeechMarksListener {
        private NoOpTtsSpeechMarksListener() {
        }

        @Override // amazon.speech.tts.TtsSpeechMarksListener
        public void onSpeechMark(String str) {
            Log.d(TtsMediaPlayer.TAG, "NoOpTtsSpeechMarksListener - onSpeechMark");
        }
    }

    /* loaded from: classes.dex */
    interface PrewarmCallback {
        void onPrewarmComplete(long j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class ReleaseFocusRunnable implements Runnable {
        final String mNamespace;

        public ReleaseFocusRunnable(String str) {
            this.mNamespace = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!TextUtils.isEmpty(this.mNamespace)) {
                String str = TtsMediaPlayer.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Releasing audio dialog focus for namespace: ");
                outline107.append(this.mNamespace);
                Log.d(str, outline107.toString());
                boolean releaseFocus = TtsMediaPlayer.this.mExplicitFocusClient.releaseFocus(this.mNamespace, 3);
                TtsMediaPlayer.this.mPlaybackController.TTSEnded();
                if (releaseFocus) {
                    return;
                }
                Log.e(TtsMediaPlayer.TAG, "Failure sending releaseAudioDialogFocus");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public abstract class SpeakDataSourceSetter {
        SpeakDataSourceSetter() {
        }

        abstract boolean setDataSource(SpeechExecutionHolder speechExecutionHolder) throws IllegalArgumentException, SecurityException, IllegalStateException, IOException;
    }

    static {
        gHandlerThread.start();
    }

    public TtsMediaPlayer(Context context, Configuration configuration) {
        this(context, new SpeechEventSender(context), new HandlerWrapper(new Handler(Looper.getMainLooper())), new AudioPlayerFactory(configuration.getFlagTTSAttributes()), null, new AlexaTtsAlert(context), new AspPlaybackReporter(context), new TimestampWrapper(), new DefaultPlaybackController(), configuration);
        this.mSpeakHandlerWrapper = new HandlerWrapper(new Handler(gHandlerThread.getLooper()));
    }

    static HttpURLConnection connect(UrlWrapper urlWrapper) throws IOException {
        HttpURLConnection openConnection = urlWrapper.openConnection();
        openConnection.setReadTimeout(15000);
        openConnection.setConnectTimeout(15000);
        openConnection.setRequestMethod("GET");
        openConnection.setDoInput(true);
        openConnection.connect();
        return openConnection;
    }

    private String getCaption(Resources resources, int i) {
        if (i != 0) {
            this.mCaption = resources.getString(i);
            if (!TextUtils.isEmpty(this.mCaption)) {
                this.mSpeechInitiatedWithCaptions = true;
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("speakResource caption=");
                outline107.append(this.mCaption);
                Log.d(str, outline107.toString());
            } else {
                this.mSpeechInitiatedWithCaptions = false;
            }
        } else {
            this.mSpeechInitiatedWithCaptions = false;
            this.mCaption = null;
        }
        return this.mCaption;
    }

    private long getWifiSignalLevel() {
        WifiInfo connectionInfo;
        WifiManager wifiManager = (WifiManager) this.mContext.getSystemService("wifi");
        if (wifiManager == null || (connectionInfo = wifiManager.getConnectionInfo()) == null) {
            return -1L;
        }
        return WifiManager.calculateSignalLevel(connectionInfo.getRssi(), 5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postBadUrlMetric() {
        this.mMetricsClient.obtainCounter(METRIC_BAD_URL).setCount(1L).record();
        Log.e(TAG, "Got and posted SIM.TtsMediaPlayer.InvalidURL");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postPrewarmCompleteMetric(long j) {
        this.mMetricsClient.obtainTimer(METRIC_TTS_PREWARM_WAIT_TIME).setDuration(j).record();
    }

    private synchronized void postSpeakRunnableSafely(boolean z, SpeechExecutionHolderedRunnable speechExecutionHolderedRunnable) {
        if (this.mSpeechExecutionHolder != null) {
            this.mSpeechExecutionHolder.abortPayload();
        }
        this.mSpeechExecutionHolder = new SpeechExecutionHolder(speechExecutionHolderedRunnable, this.mSpeakHandlerWrapper, this.mCleanupConnectionExecutor, this.mEstablishConnectionExecutor, 15000L, z ? this.mPrewarmTracker : null);
        this.mSpeechExecutionHolder.executePayload();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postTotalPrewarmTimeMetric(long j) {
        this.mMetricsClient.obtainTimer(METRIC_TTS_PREWARM_TOTAL_TIME).setDuration(j).record();
    }

    private void recordSocketTimeout(String str) {
        HashMap hashMap = new HashMap();
        if (str != null) {
            hashMap.put("message", str);
        }
        try {
            this.mMetricsClient.obtainCounter(METRIC_SOCKET_TIMEOUT_SIGNAL_STRENGTH).setCount(getWifiSignalLevel()).addMetadata(hashMap).record();
        } catch (SecurityException unused) {
            Log.w(TAG, "Missing ACCESS_WIFI_STATE permission");
            this.mMetricsClient.obtainCounter(METRIC_SOCKET_TIMEOUT).addMetadata(hashMap).record();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordTrace(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "Attempted to record a trace with an empty name, ignoring");
            return;
        }
        Trace.beginSection(str);
        Trace.endSection();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void resetPrewarmTracker() {
        this.mPrewarmTracker = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSpeechStartNotification(String str) {
        int checkCallingOrSelfPermission = this.mContext.checkCallingOrSelfPermission(TTS_CAPTION_PERMISSION);
        if (checkCallingOrSelfPermission != 0 || TextUtils.isEmpty(str)) {
            if (-1 != checkCallingOrSelfPermission) {
                return;
            }
            Log.e(TAG, "App does not have permission to display captions!");
            return;
        }
        String str2 = TAG;
        Log.d(str2, "Received caption with Audio: " + str);
        Intent intent = new Intent();
        String str3 = TAG;
        Log.d(str3, "Sending Speech Start Event for caption: " + str);
        intent.setAction(SERVICE_SPEECH_START_ACTION);
        intent.putExtra(SERVICE_TTS_CAPTION_EXTRA, this.mCaption);
        this.mContext.sendBroadcast(intent, TTS_CAPTION_PERMISSION);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSpeechStopNotification() {
        if (this.mContext.checkCallingOrSelfPermission(TTS_CAPTION_PERMISSION) == 0) {
            Intent intent = new Intent();
            Log.d(TAG, "Sending  Speech end intent to SpeechUI");
            intent.setAction(SERVICE_SPEECH_STOP_ACTION);
            this.mContext.sendBroadcast(intent, TTS_CAPTION_PERMISSION);
            return;
        }
        Log.e(TAG, "App does not have permission to display captions!");
    }

    static boolean usePrewarmTracker(Uri uri) {
        return uri.toString().toLowerCase().startsWith(TTS_SERVER_URL);
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public synchronized void acquireAudioFocusLock() {
        if (!this.mFocusLockHeld) {
            changeFocus(true, 0L);
            this.mFocusLockHeld = true;
        }
    }

    synchronized void acquireWakeLock() {
        this.mWakeLockManager.acquire();
    }

    protected void addListener(TtsListener ttsListener, String str, String str2, ITtsSpeechMarksEmitter iTtsSpeechMarksEmitter, String str3, String str4) {
        if (this.mCurrentAudioPlayerListener != null) {
            this.mAudioPlayer.removeListener();
        }
        this.mCurrentAudioPlayerListener = new AnonymousClass9(str3, ttsListener, str, str4, iTtsSpeechMarksEmitter, str2);
        this.mAudioPlayer.setListener(this.mCurrentAudioPlayerListener);
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void cancel() {
        stop(TtsEventListener.CompletionCode.CANCEL);
    }

    void changeFocus(boolean z, long j) {
        if (this.mFocusLockHeld || this.mHasFocus == z) {
            return;
        }
        this.mHasFocus = z;
        this.mAudioFocusHelper.changeAudioFocus(z, j);
    }

    InputStream getSourceInputStream(Uri uri, UrlWrapper urlWrapper, ITtsSpeechMarksEmitter iTtsSpeechMarksEmitter, TtsListener ttsListener, SpeechExecutionHolder speechExecutionHolder) throws IOException, InterruptedException {
        Log.d(TAG, "getSourceInputStream");
        recordTrace("CSM_tts_url_connection_begin");
        this.mTtsEventSender.fireUriConnectionStarted(ttsListener, this.mTimestamp.currentTimeMillis());
        InputStream inputStream = InProcTtsStreamProvider.getInstance().get(uri);
        if (inputStream != null) {
            String str = TAG;
            Log.i(str, "InProcTtsStreamProvider found a stream for " + uri);
            recordTrace("CSM_tts_url_connection_end");
            this.mTtsEventSender.fireUriConnectionEstablished(ttsListener, this.mTimestamp.currentTimeMillis());
        } else {
            speechExecutionHolder.startEstablishConnection(urlWrapper, this.mConnectionMetricsListener);
            HttpURLConnection waitForReceiveConnection = speechExecutionHolder.waitForReceiveConnection();
            waitForReceiveConnection.setReadTimeout(15000);
            waitForReceiveConnection.setConnectTimeout(15000);
            waitForReceiveConnection.setRequestMethod("GET");
            waitForReceiveConnection.setDoInput(true);
            try {
                String str2 = uri.toString().startsWith(LOCALHOST_URL_PATTERN) ? METRIC_LOCALHOST_URL_CONNECT : METRIC_URL_CONNECT;
                long currentTimeMillis = System.currentTimeMillis();
                waitForReceiveConnection.connect();
                this.mMetricsClient.obtainTimer(str2).setDuration(System.currentTimeMillis() - currentTimeMillis).record();
                try {
                    int responseCode = waitForReceiveConnection.getResponseCode();
                    recordTrace("CSM_tts_url_connection_end");
                    this.mTtsEventSender.fireUriConnectionEstablished(ttsListener, this.mTimestamp.currentTimeMillis());
                    String str3 = TAG;
                    Log.d(str3, "getSampleSource response " + responseCode);
                    if (responseCode < 400) {
                        inputStream = waitForReceiveConnection.getInputStream();
                        if (inputStream == null) {
                            Log.e(TAG, "UrlWrapper could not be created");
                            throw new IllegalArgumentException("Could not get stream for Uri");
                        }
                    } else {
                        IOException iOException = new IOException(GeneratedOutlineSupport1.outline49("Http Error: ", responseCode));
                        String str4 = TAG;
                        Log.e(str4, "Http error: " + iOException);
                        throw iOException;
                    }
                } catch (SocketTimeoutException e) {
                    recordSocketTimeout(e.getMessage());
                    throw e;
                }
            } catch (UnknownHostException e2) {
                this.mMetricsClient.obtainCounter(METRIC_BAD_URL).setCount(1L).record();
                throw e2;
            }
        }
        if (iTtsSpeechMarksEmitter == null) {
            Log.w(TAG, "Marks emitter null, no speech marks will be broadcast.");
            return inputStream;
        }
        Log.d(TAG, "Demuxing; marks emitter present");
        return AudioWordMarkDemuxer.split(inputStream, iTtsSpeechMarksEmitter).getMp3Stream();
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void interrupt() {
        stop(TtsEventListener.CompletionCode.INTERRUPT);
    }

    public synchronized boolean isSpeakPending() {
        return this.mIsSpeakPending;
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public synchronized boolean isSpeaking() {
        return this.mIsSpeaking;
    }

    synchronized void postPrewarmRunnable(Runnable runnable) {
        if (this.mPrewarmTracker != null) {
            Log.i(TAG, "Already running prewarm. Skipping...");
        } else {
            this.mPrewarmTracker = new TtsPrewarmTracker(this.mEstablishConnectionExecutor.submit(runnable), 20000L);
        }
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void prepare() {
        postPrewarmRunnable(new PrewarmTtsRunnable(TTS_SERVER_URL, this.mPrewarmCallaback));
    }

    void processEventsOnTestThread() {
        HandlerThread handlerThread = new HandlerThread("TtsMediaPlayer-ListenThread");
        handlerThread.start();
        this.mListenerHandlerWrapper = new HandlerWrapper(new Handler(handlerThread.getLooper()));
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public synchronized void releaseAudioFocusLock() {
        if (this.mFocusLockHeld) {
            this.mFocusLockHeld = false;
            changeFocus(false, 0L);
        }
    }

    synchronized void releaseWakeLock() {
        this.mWakeLockManager.release();
    }

    void setAudioFocusChangeListener(TtsAudioFocusChangeListener ttsAudioFocusChangeListener) {
        this.mAudioFocusHelper = new AudioFocusHelper(new AudioManagerWrapper(this.mContext), ttsAudioFocusChangeListener);
    }

    synchronized void setSpeakPending(boolean z) {
        if (this.mIsSpeakPending == z) {
            return;
        }
        String str = TAG;
        Log.d(str, "setSpeakPending " + z);
        if (z && isSpeaking()) {
            Log.e(TAG, "we should not be in the mIsSpeakPending == true state when isSpeaking is true");
            this.mIsSpeakPending = false;
        }
        this.mIsSpeakPending = z;
    }

    synchronized void setSpeaking(boolean z, String str, String str2) {
        if (this.mIsSpeaking == z) {
            return;
        }
        long j = 0;
        Log.d(TAG, "setSpeaking " + z);
        if (!z) {
            releaseWakeLock();
            if (this.mSpeechMarksEmitterHelper != null) {
                this.mSpeechMarksEmitterHelper.clearEmitter();
            }
            this.mNamespace = null;
            synchronized (this.mFutureSync) {
                if (this.mCurrentAudioPlayerListener != null && this.mCurrentAudioPlayerListener.mTimeoutFuture != null) {
                    try {
                        this.mCurrentAudioPlayerListener.mTimeoutFuture.cancel(true);
                    } catch (Exception unused) {
                        Log.w(TAG, "future cancellation failed.");
                    }
                }
            }
            this.mActiveUID = -1L;
        } else {
            acquireWakeLock();
            setSpeakPending(false);
        }
        this.mTtsAlert.alertTts(z, str, str2);
        if (this.mAspReporter != null) {
            this.mAspReporter.reportAspPlayback(z);
        }
        this.mIsSpeaking = z;
        if (!z) {
            j = 500;
        }
        changeFocus(z, j);
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void setVolume(float f) {
        String str = TAG;
        Log.d(str, "setVolume() | vol: " + f);
        if (RuntimeDeviceTypeHelper.isDeviceATablet()) {
            AudioPlayer audioPlayer = this.mAudioPlayer;
            if (audioPlayer == null) {
                return;
            }
            audioPlayer.setVolume(f);
            return;
        }
        throw new UnsupportedOperationException();
    }

    void speak(final SpeakDataSourceSetter speakDataSourceSetter, final String str, final TtsListener ttsListener, final String str2, final String str3, boolean z, final String str4) {
        this.mMetricsClient.obtainCounter(METRIC_SPEAK).setCount(1L).record();
        this.mCurrentContentToken = str;
        setSpeakPending(true);
        postSpeakRunnableSafely(z, new SpeechExecutionHolderedRunnable() { // from class: amazon.speech.tts.TtsMediaPlayer.8
            @Override // java.lang.Runnable
            public void run() {
                StackTraceElement[] stackTrace;
                final CountDownLatch countDownLatch = new CountDownLatch(1);
                TtsMediaPlayer.this.mPlaybackListener = new ITtsPlaybackListener() { // from class: amazon.speech.tts.TtsMediaPlayer.8.1
                    boolean mStartedPlayback = false;

                    @Override // amazon.speech.playback.ITtsPlaybackListener
                    public void notifyTTSPlaybackInterrupted(long j) {
                        Log.i(TtsMediaPlayer.TAG, "notifyTTSPlaybackInterrupted");
                        if (j == TtsMediaPlayer.this.mActiveUID) {
                            TtsMediaPlayer.this.interrupt();
                            TtsMediaPlayer.this.mActiveUID = -1L;
                        }
                    }

                    @Override // amazon.speech.playback.ITtsPlaybackListener
                    public void notifyTTSPlaybackStarted(long j) {
                        Log.i(TtsMediaPlayer.TAG, "notifyTTSPlaybackStarted");
                        countDownLatch.countDown();
                        if (TtsMediaPlayer.this.mActiveUID != -1) {
                            Log.e(TtsMediaPlayer.TAG, "Trying to start playback while another TTS is active");
                        }
                        TtsMediaPlayer.this.mActiveUID = j;
                    }

                    @Override // amazon.speech.playback.ITtsPlaybackListener
                    public boolean ttsHeartbeat(long j) {
                        boolean z2 = j == TtsMediaPlayer.this.mActiveUID;
                        boolean isSpeaking = TtsMediaPlayer.this.isSpeaking();
                        boolean isSpeakPending = TtsMediaPlayer.this.isSpeakPending();
                        String str5 = TtsMediaPlayer.TAG;
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ttsHeartbeat, valid (");
                        outline107.append(TtsMediaPlayer.this.mActiveUID);
                        outline107.append(") ");
                        outline107.append(z2);
                        outline107.append(" isSpeaking ");
                        outline107.append(isSpeaking);
                        outline107.append(" speakPending: ");
                        outline107.append(isSpeakPending);
                        Log.i(str5, outline107.toString());
                        return z2 && (isSpeakPending || isSpeaking);
                    }
                };
                TtsMediaPlayer ttsMediaPlayer = TtsMediaPlayer.this;
                ttsMediaPlayer.mPlaybackController.TTSStarted(ttsMediaPlayer.mPlaybackListener);
                try {
                    Log.i(TtsMediaPlayer.TAG, "awaiting playback with timeout");
                    countDownLatch.await(2000L, TimeUnit.MILLISECONDS);
                } catch (InterruptedException unused) {
                    Log.i(TtsMediaPlayer.TAG, "playback timed out, continuing to speak");
                }
                try {
                    Log.i(TtsMediaPlayer.TAG, "speak() begin run");
                    TtsMediaPlayer.this.mNamespace = str2;
                    if (TtsMediaPlayer.this.mAudioPlayer == null) {
                        TtsMediaPlayer.this.mAudioPlayer = TtsMediaPlayer.this.mAudioPlayerFactory.createAudioPlayer(TtsMediaPlayer.this.mContext, TtsMediaPlayer.this.mMetricsClient);
                    }
                    SpeechExecutionHolder speechExecutionHolder = getSpeechExecutionHolder();
                    if (speechExecutionHolder != null) {
                        if (!speakDataSourceSetter.setDataSource(speechExecutionHolder)) {
                            Log.i(TtsMediaPlayer.TAG, "Setting the data source failed.");
                            TtsMediaPlayer.this.setSpeakPending(false);
                            TtsMediaPlayer.this.mTtsEventSender.fireSpeechError(ttsListener, TtsMediaPlayer.this.mTimestamp.currentTimeMillis(), TtsEventListener.ErrorCode.MEDIA_PLAYBACK_ERROR, TtsMediaPlayer.ERROR_DATASOURCE_EXCEPTION_MESSAGE);
                            return;
                        }
                        TtsMediaPlayer.this.addListener(ttsListener, str, str2, TtsMediaPlayer.this.mSpeechMarksEmitterHelper.getEmitter(), str3, str4);
                        Log.d(TtsMediaPlayer.TAG, "Finished adding listener.");
                        TtsMediaPlayer.this.mAudioPlayer.play(TtsMediaPlayer.this.mInputStream);
                        if (str2 == null || TtsMediaPlayer.this.mExplicitFocusClient.acquireFocus(str2, 3)) {
                            return;
                        }
                        Log.e(TtsMediaPlayer.TAG, "Failure sending addAudioDialogFocus");
                        return;
                    }
                    throw new IllegalStateException("Must set SpeechExecutionHolder before running.");
                } catch (Exception e) {
                    Log.e(TtsMediaPlayer.TAG, "MediaPlayer exception", e);
                    long currentTimeMillis = TtsMediaPlayer.this.mTimestamp.currentTimeMillis();
                    if (!TextUtils.isEmpty(e.getMessage())) {
                        String str5 = TtsMediaPlayer.TAG;
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MediaPlayer exception message: ");
                        outline107.append(e.getMessage());
                        Log.e(str5, outline107.toString());
                    }
                    for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                        String str6 = TtsMediaPlayer.TAG;
                        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("MediaPlayer exception: ");
                        outline1072.append(stackTraceElement.toString());
                        Log.e(str6, outline1072.toString());
                    }
                    TtsMediaPlayer.this.setSpeaking(false, str, null);
                    TtsMediaPlayer.this.setSpeakPending(false);
                    TtsMediaPlayer.this.mTtsEventSender.fireSpeechError(ttsListener, currentTimeMillis, TtsEventListener.ErrorCode.MEDIA_PLAYER_EXCEPTION, TtsMediaPlayer.ERROR_MEDIAPLAYER_EXCEPTION_MESSAGE);
                    TtsMediaPlayer.this.mListenerHandlerWrapper.post(new ReleaseFocusRunnable(str2));
                    AudioPlayer audioPlayer = TtsMediaPlayer.this.mAudioPlayer;
                    if (audioPlayer == null) {
                        return;
                    }
                    audioPlayer.release();
                    TtsMediaPlayer.this.mAudioPlayer = null;
                }
            }
        });
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void speakFromInputStream(InputStream inputStream, String str, TtsListener ttsListener, String str2) {
        speakFromInputStream(inputStream, str, ttsListener, null, str2);
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void speakResource(int i, int i2, TtsListener ttsListener) {
        speakResource(i, i2, ttsListener, (String) null);
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void speakUri(Uri uri, String str, TtsListener ttsListener) {
        speakUri(uri, str, ttsListener, null, null);
    }

    void stop(final TtsEventListener.CompletionCode completionCode) {
        String str = TAG;
        Log.d(str, "stop: " + completionCode);
        if (completionCode != TtsEventListener.CompletionCode.SUCCESS) {
            if (!isSpeaking() && !isSpeakPending()) {
                return;
            }
            if (completionCode == TtsEventListener.CompletionCode.CANCEL) {
                this.mMetricsClient.obtainCounter(METRIC_SPEAK_CANCEL).setCount(1L).record();
            } else if (completionCode == TtsEventListener.CompletionCode.INTERRUPT) {
                this.mMetricsClient.obtainCounter(METRIC_SPEAK_INTERRUPT).setCount(1L).record();
            }
            final String str2 = this.mNamespace;
            String str3 = TAG;
            Log.i(str3, "stop localNamespace = " + str2 + " with code " + completionCode);
            setSpeaking(false, this.mCurrentContentToken, null);
            setSpeakPending(false);
            postSpeakRunnableSafely(false, new SpeechExecutionHolderedRunnable() { // from class: amazon.speech.tts.TtsMediaPlayer.7
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (TtsMediaPlayer.this.mAudioPlayer == null) {
                            Log.w(TtsMediaPlayer.TAG, "MediaPlayer not ready to be used.");
                            return;
                        }
                        long currentPosition = TtsMediaPlayer.this.mAudioPlayer.getCurrentPosition();
                        Log.i(TtsMediaPlayer.TAG, "MediaPlayer.stop");
                        try {
                            TtsMediaPlayer.this.mAudioPlayer.stop();
                        } catch (TimeoutException e) {
                            Log.e(TtsMediaPlayer.TAG, "Failed to stop media player.", e);
                            if (TtsMediaPlayer.this.mAudioPlayer != null) {
                                TtsMediaPlayer.this.mAudioPlayer.release();
                                TtsMediaPlayer.this.mAudioPlayer = null;
                            }
                        }
                        TtsMediaPlayer.this.mSpeechMarksEmitterHelper.clearEmitter();
                        if (!TextUtils.isEmpty(TtsMediaPlayer.this.mCurrentContentToken)) {
                            TtsMediaPlayer.this.mSpeechEventSender.sendSpeechInterruptEvent(TtsMediaPlayer.this.mCurrentContentToken, currentPosition);
                        }
                        if (TtsMediaPlayer.this.mTtsListener != null) {
                            TtsMediaPlayer.this.recordTrace("CSM_tts_end");
                            TtsMediaPlayer.this.mTtsEventSender.fireSpeechCompleted(TtsMediaPlayer.this.mTtsListener, TtsMediaPlayer.this.mTimestamp.currentTimeMillis(), completionCode);
                            TtsMediaPlayer.this.mListenerHandlerWrapper.post(new ReleaseFocusRunnable(str2));
                            TtsMediaPlayer.this.mTtsListener = null;
                        }
                    } finally {
                        TtsMediaPlayer.this.mCurrentContentToken = null;
                    }
                }
            });
            return;
        }
        throw new IllegalArgumentException("Calling stop() with reason SUCCESS is invalid");
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void teardown() {
        this.mExplicitFocusClient.teardown();
        this.mSpeechEventSender.teardown();
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void speakFromInputStream(final InputStream inputStream, String str, TtsListener ttsListener, TtsSpeechMarksListener ttsSpeechMarksListener, String str2) {
        String str3 = TAG;
        Log.d(str3, "speakFromInputStream namespace = " + str2);
        if (isSpeaking() || isSpeakPending()) {
            cancel();
        }
        if (inputStream != null) {
            this.mTtsListener = ttsListener == null ? new NoOpTtsListener() : ttsListener;
            this.mSpeechMarksEmitterHelper.createEmitter(ttsSpeechMarksListener, this.mListenerHandlerWrapper);
            speak(new SpeakDataSourceSetter() { // from class: amazon.speech.tts.TtsMediaPlayer.6
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super();
                }

                @Override // amazon.speech.tts.TtsMediaPlayer.SpeakDataSourceSetter
                boolean setDataSource(SpeechExecutionHolder speechExecutionHolder) throws IllegalArgumentException, SecurityException, IllegalStateException, IOException {
                    TtsMediaPlayer ttsMediaPlayer = TtsMediaPlayer.this;
                    if (ttsMediaPlayer.mAudioPlayer != null) {
                        ttsMediaPlayer.mInputStream = inputStream;
                        return true;
                    }
                    IllegalStateException illegalStateException = new IllegalStateException("Media Player not ready");
                    Log.e(TtsMediaPlayer.TAG, "Media Player not ready", illegalStateException);
                    throw illegalStateException;
                }
            }, str, ttsListener, str2, null, false, null);
            return;
        }
        throw new IllegalArgumentException("InputStream cannot be null");
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void speakResource(int i, int i2, TtsListener ttsListener, String str) {
        speakResource(this.mContext.getResources(), i, i2, ttsListener, str);
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void speakUri(Uri uri, String str, TtsListener ttsListener, TtsSpeechMarksListener ttsSpeechMarksListener) {
        speakUri(uri, str, ttsListener, ttsSpeechMarksListener, null);
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void speakResource(Resources resources, int i, int i2, TtsListener ttsListener) {
        speakResource(resources, i, i2, ttsListener, null);
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void speakUri(Uri uri, String str, TtsListener ttsListener, String str2) {
        speakUri(uri, str, ttsListener, null, str2);
    }

    /* loaded from: classes.dex */
    static class PrewarmTtsRunnable implements Runnable {
        final PrewarmCallback mPrewarmCallaback;
        final UrlWrapper mUrlWrapper;

        PrewarmTtsRunnable(UrlWrapper urlWrapper, PrewarmCallback prewarmCallback) {
            if (urlWrapper != null && prewarmCallback != null) {
                this.mUrlWrapper = urlWrapper;
                this.mPrewarmCallaback = prewarmCallback;
                return;
            }
            throw new IllegalArgumentException();
        }

        @Override // java.lang.Runnable
        public void run() {
            long currentTimeMillis = System.currentTimeMillis();
            try {
                Log.d(TtsMediaPlayer.TAG, "Pre-warming SSL connection.");
                HttpURLConnection connect = TtsMediaPlayer.connect(this.mUrlWrapper);
                while (connect.getInputStream().read() > 0) {
                }
                String str = TtsMediaPlayer.TAG;
                Log.d(str, "Pre-warm request HTTP response = " + connect.getResponseCode());
            } catch (IOException e) {
                Log.w(TtsMediaPlayer.TAG, "Exception thrown while pre-warming conenction", e);
            }
            this.mPrewarmCallaback.onPrewarmComplete(System.currentTimeMillis() - currentTimeMillis);
        }

        PrewarmTtsRunnable(String str, PrewarmCallback prewarmCallback) {
            if (str != null && prewarmCallback != null) {
                try {
                    this.mUrlWrapper = new UrlWrapper(new URL(str));
                    this.mPrewarmCallaback = prewarmCallback;
                    return;
                } catch (MalformedURLException e) {
                    Log.wtf(TtsMediaPlayer.TAG, "Fatal error parsing URL.", e);
                    throw new AndroidRuntimeException(e);
                }
            }
            throw new IllegalArgumentException();
        }
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void speakResource(final Resources resources, final int i, int i2, TtsListener ttsListener, String str) {
        String str2 = TAG;
        StringBuilder outline110 = GeneratedOutlineSupport1.outline110("speakResource  ", i, " captionResource = ", i2, " namespace = ");
        outline110.append(str);
        Log.d(str2, outline110.toString());
        if (isSpeaking() || isSpeakPending()) {
            cancel();
        }
        if (ttsListener == null) {
            Log.i(TAG, "Using null Tts listener");
            ttsListener = new NoOpTtsListener();
        }
        TtsListener ttsListener2 = ttsListener;
        this.mTtsListener = ttsListener2;
        speak(new SpeakDataSourceSetter() { // from class: amazon.speech.tts.TtsMediaPlayer.5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // amazon.speech.tts.TtsMediaPlayer.SpeakDataSourceSetter
            public boolean setDataSource(SpeechExecutionHolder speechExecutionHolder) throws IllegalArgumentException, SecurityException, IllegalStateException, IOException {
                if (TtsMediaPlayer.this.mAudioPlayer != null) {
                    InputStream openRawResource = resources.openRawResource(i);
                    if (openRawResource != null) {
                        TtsMediaPlayer.this.mInputStream = openRawResource;
                        return true;
                    }
                    IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Bad resource id.");
                    Log.e(TtsMediaPlayer.TAG, "error getting resource", illegalArgumentException);
                    throw illegalArgumentException;
                }
                IllegalStateException illegalStateException = new IllegalStateException("Media Player not ready");
                Log.e(TtsMediaPlayer.TAG, "Media Player not ready", illegalStateException);
                throw illegalStateException;
            }
        }, null, ttsListener2, str, getCaption(resources, i2), false, null);
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void speakUri(Uri uri, String str, TtsListener ttsListener, TtsSpeechMarksListener ttsSpeechMarksListener, String str2) {
        speakUri(uri, str, ttsListener, ttsSpeechMarksListener, str2, null);
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void speakUri(final Uri uri, String str, TtsListener ttsListener, TtsSpeechMarksListener ttsSpeechMarksListener, String str2, String str3) {
        final long nanoTime = System.nanoTime();
        String str4 = TAG;
        Log.i(str4, "speakUri namespace = " + str2);
        if (isSpeaking() || isSpeakPending()) {
            cancel();
        }
        if (uri != null) {
            this.mTtsListener = ttsListener == null ? new NoOpTtsListener() : ttsListener;
            this.mSpeechMarksEmitterHelper.createEmitter(ttsSpeechMarksListener, this.mListenerHandlerWrapper);
            speak(new SpeakDataSourceSetter() { // from class: amazon.speech.tts.TtsMediaPlayer.4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super();
                }

                @Override // amazon.speech.tts.TtsMediaPlayer.SpeakDataSourceSetter
                public boolean setDataSource(SpeechExecutionHolder speechExecutionHolder) throws IllegalArgumentException, SecurityException, IllegalStateException, IOException {
                    long nanoTime2 = (System.nanoTime() - nanoTime) / 1000000;
                    if (nanoTime2 > 1000) {
                        String str5 = TtsMediaPlayer.TAG;
                        Log.w(str5, "SpeakUri spinup took " + nanoTime2 + " ms");
                        HashMap hashMap = new HashMap();
                        hashMap.put(TtsMediaPlayer.WARNING_TTS_SPEAK_URI_LATENCY, Long.toString(nanoTime2));
                        TtsMediaPlayer.this.mMetricsClient.obtainCounter(TtsMediaPlayer.METRIC_SPEAK_URI_SLOW_SPINUP).setCount(1L).addMetadata(hashMap).record();
                    }
                    TtsMediaPlayer ttsMediaPlayer = TtsMediaPlayer.this;
                    if (ttsMediaPlayer.mAudioPlayer != null) {
                        UrlWrapper create = ttsMediaPlayer.mUrlWrapperFactory.create(uri.toString());
                        if (create == null) {
                            Log.e(TtsMediaPlayer.TAG, "UrlWrapper could not be created");
                            return false;
                        }
                        try {
                            TtsMediaPlayer.this.mInputStream = TtsMediaPlayer.this.getSourceInputStream(uri, create, TtsMediaPlayer.this.mSpeechMarksEmitterHelper.getEmitter(), TtsMediaPlayer.this.mTtsListener, speechExecutionHolder);
                            return true;
                        } catch (EOFException e) {
                            e = e;
                            Log.e(TtsMediaPlayer.TAG, "Connection was terminated, could not create stream.", e);
                            return false;
                        } catch (InterruptedException e2) {
                            Log.e(TtsMediaPlayer.TAG, "Interrupted waiting for connection, could not create stream.", e2);
                            return false;
                        } catch (ConnectException e3) {
                            e = e3;
                            Log.e(TtsMediaPlayer.TAG, "Connection was terminated, could not create stream.", e);
                            return false;
                        }
                    }
                    IllegalStateException illegalStateException = new IllegalStateException("Media Player not ready");
                    Log.e(TtsMediaPlayer.TAG, "Media Player not ready", illegalStateException);
                    throw illegalStateException;
                }
            }, str, this.mTtsListener, str2, null, usePrewarmTracker(uri), str3);
            return;
        }
        throw new IllegalArgumentException("uri cannot be null");
    }

    TtsMediaPlayer(Context context, SpeechEventSender speechEventSender, HandlerWrapper handlerWrapper, AudioPlayerFactory audioPlayerFactory, HandlerWrapper handlerWrapper2, AlexaTtsAlert alexaTtsAlert, AspPlaybackReporter aspPlaybackReporter, TimestampWrapper timestampWrapper, ITtsPlaybackController iTtsPlaybackController, Configuration configuration) {
        this.mScheduler = Executors.newScheduledThreadPool(1);
        this.mFutureSync = new Object();
        this.mEstablishConnectionExecutor = Executors.newFixedThreadPool(3);
        this.mCleanupConnectionExecutor = Executors.newFixedThreadPool(3);
        this.mConnectionMetricsListener = new ConnectionMetricsListener() { // from class: amazon.speech.tts.TtsMediaPlayer.1
            @Override // amazon.speech.tts.TtsMediaPlayer.ConnectionMetricsListener
            public void onBadUrl() {
                TtsMediaPlayer.this.postBadUrlMetric();
            }

            @Override // amazon.speech.tts.TtsMediaPlayer.ConnectionMetricsListener
            public void onPrewarmWaitComplete(long j) {
                TtsMediaPlayer.this.postPrewarmCompleteMetric(j);
            }
        };
        this.mSpeechMarksEmitterHelper = new ISpeechMarksEmitterHelper() { // from class: amazon.speech.tts.TtsMediaPlayer.2
            ITtsSpeechMarksEmitter mCurrentEmitter;

            @Override // amazon.speech.tts.TtsMediaPlayer.ISpeechMarksEmitterHelper
            public synchronized void clearEmitter() {
                if (this.mCurrentEmitter != null) {
                    this.mCurrentEmitter.clear();
                }
                this.mCurrentEmitter = null;
            }

            @Override // amazon.speech.tts.TtsMediaPlayer.ISpeechMarksEmitterHelper
            public synchronized ITtsSpeechMarksEmitter createEmitter(TtsSpeechMarksListener ttsSpeechMarksListener, HandlerWrapper handlerWrapper3) {
                if (ttsSpeechMarksListener == null) {
                    ttsSpeechMarksListener = new NoOpTtsSpeechMarksListener();
                }
                this.mCurrentEmitter = new TtsSpeechMarksEmitter(ttsSpeechMarksListener, TtsMediaPlayer.this.mListenerHandlerWrapper, TtsMediaPlayer.this.mContext);
                return this.mCurrentEmitter;
            }

            @Override // amazon.speech.tts.TtsMediaPlayer.ISpeechMarksEmitterHelper
            public synchronized ITtsSpeechMarksEmitter getEmitter() {
                return this.mCurrentEmitter;
            }
        };
        this.mUrlWrapperFactory = new IUrlWrapperFactory() { // from class: amazon.speech.tts.TtsMediaPlayer.3
            @Override // amazon.speech.tts.TtsMediaPlayer.IUrlWrapperFactory
            public UrlWrapper create(String str) {
                try {
                    return new UrlWrapper(new URL(str));
                } catch (MalformedURLException e) {
                    Log.e(TtsMediaPlayer.TAG, "malformed url", e);
                    TtsMediaPlayer.this.mMetricsClient.obtainCounter(TtsMediaPlayer.METRIC_BAD_URL).setCount(1L).record();
                    return null;
                }
            }
        };
        this.mActiveUID = -1L;
        this.mPrewarmCallaback = new PrewarmCallback() { // from class: amazon.speech.tts.TtsMediaPlayer.10
            @Override // amazon.speech.tts.TtsMediaPlayer.PrewarmCallback
            public void onPrewarmComplete(long j) {
                TtsMediaPlayer.this.resetPrewarmTracker();
                TtsMediaPlayer.this.postTotalPrewarmTimeMetric(j);
            }
        };
        if (context != null && speechEventSender != null && audioPlayerFactory != null && alexaTtsAlert != null && timestampWrapper != null && iTtsPlaybackController != null) {
            this.mContext = context;
            this.mAudioPlayerFactory = audioPlayerFactory;
            this.mSpeechEventSender = speechEventSender;
            this.mListenerHandlerWrapper = handlerWrapper;
            this.mSpeakHandlerWrapper = handlerWrapper2;
            this.mTtsAlert = alexaTtsAlert;
            this.mAspReporter = aspPlaybackReporter;
            this.mTimestamp = timestampWrapper;
            this.mPlaybackController = iTtsPlaybackController;
            this.mTtsEventSender = new TtsEventSender(handlerWrapper);
            this.mExplicitFocusClient = new ExplicitFocusClient(context);
            this.mAudioFocusHelper = new AudioFocusHelper(new AudioManagerWrapper(this.mContext), new TtsAudioFocusChangeListener(this, configuration.getIgnoreAudioFocusChange()));
            MetricsClient metricsClient = configuration.getMetricsClient();
            this.mMetricsClient = metricsClient == null ? new MetricsClient(context, METRIC_SOURCE, "TtsMediaPlayer") : metricsClient;
            WakeLockManager wakeLockManager = configuration.getWakeLockManager();
            this.mWakeLockManager = wakeLockManager == null ? new DefaultWakeLockManager(context) : wakeLockManager;
            return;
        }
        throw new IllegalArgumentException("parameters cannot be null");
    }
}
