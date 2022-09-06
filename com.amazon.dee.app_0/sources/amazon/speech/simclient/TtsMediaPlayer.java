package amazon.speech.simclient;

import amazon.speech.model.AlexaTtsAlert;
import amazon.speech.simclient.AudioPlayer;
import amazon.speech.simclient.TtsEventListener;
import amazon.speech.simclient.asp.AspPlaybackReporter;
import amazon.speech.util.AudioFocusHelper;
import amazon.speech.util.AudioManagerWrapper;
import amazon.speech.util.HandlerWrapper;
import amazon.speech.util.InProcTtsStreamProvider;
import amazon.speech.util.RuntimeDeviceTypeHelper;
import amazon.speech.util.TextUtilsWrapper;
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
import android.os.PowerManager;
import android.util.AndroidRuntimeException;
import android.util.Log;
import com.amazon.metrics.MetricsUtil;
import com.amazon.trace.TraceUtil;
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
    static final String METRIC_PROGRAM_NAME = "SimClientAPI";
    static final String METRIC_SOCKET_TIMEOUT = "SIM.TtsMediaPlayer.SocketTimeoutException";
    static final String METRIC_SOCKET_TIMEOUT_SIGNAL_STRENGTH = "SIM.TtsMediaPlayer.SocketTimeoutException.SignalStrength";
    static final String METRIC_SOURCE = "SimClientAPI_Metrics";
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
    static final int NUM_CLEANUP_CONNECTION_THREADS = 3;
    static final int NUM_ESTABLISH_CONNECTION_THREADS = 3;
    static final long PREWARM_MAX_WAIT_MS = 20000;
    public static final String PREWARM_TTS_NAMESPACE = "PREWARM_TTS_NAMESPACE";
    static final String SERVICE_SPEECH_START_ACTION = "amazon.speech.intent.action.TTS_CAPTION_SPEECH_START";
    static final String SERVICE_SPEECH_STOP_ACTION = "amazon.speech.intent.action.TTS_CAPTION_SPEECH_STOP";
    static final String SERVICE_TTS_CAPTION_EXTRA = "tts_caption";
    public static final String TRACE_POINT_TTS_BEGIN = "CSM_tts_begin";
    public static final String TRACE_POINT_TTS_END = "CSM_tts_end";
    public static final String TRACE_POINT_URL_BEGIN = "CSM_tts_url_connection_begin";
    public static final String TRACE_POINT_URL_END = "CSM_tts_url_connection_end";
    static final String TTS_CAPTION_PERMISSION = "amazon.speech.permission.DISPLAY_CAPTIONS";
    static final String TTS_SERVER_URL = "https://tinytts.amazon.com/";
    static final String WARNING_TTS_SPEAK_URI_LATENCY = "SpeakUri time (ms) to post speak runnable:";
    private static ITtsSpeechMarksEmitter sNoOpSpeechMarkEmitter;
    AspPlaybackReporter mAspReporter;
    AudioFocusHelper mAudioFocusHelper;
    AudioPlayer mAudioPlayer;
    AudioPlayerFactory mAudioPlayerFactory;
    String mCaption;
    final ExecutorService mCleanupConnectionExecutor;
    final ConnectionMetricsListener mConnectionMetricsListener;
    final Object mContentTokenLock;
    private final Context mContext;
    AudioPlayer.AudioPlayerListener mCurrentAudioPlayerListener;
    String mCurrentContentToken;
    final ExecutorService mEstablishConnectionExecutor;
    boolean mFocusLockHeld;
    final Object mFutureSync;
    boolean mHasFocus;
    InputStream mInputStream;
    volatile boolean mIsSpeaking;
    HandlerWrapper mListenerHandlerWrapper;
    MetricsUtilWrapper mMetricsUtilWrapper;
    String mNamespace;
    PowerManager mPowerManager;
    final PrewarmCallback mPrewarmCallaback;
    TtsPrewarmTracker mPrewarmTracker;
    ScheduledExecutorService mScheduler;
    private SimClient mSimClient;
    HandlerWrapper mSpeakHandlerWrapper;
    SpeechEventSender mSpeechEventSender;
    SpeechExecutionHolder mSpeechExecutionHolder;
    boolean mSpeechInitiatedWithCaptions;
    ISpeechMarksEmitterHelper mSpeechMarksEmitterHelper;
    private final TimestampWrapper mTimestamp;
    TraceUtil mTraceUtil;
    private final AlexaTtsAlert mTtsAlert;
    TtsAudioFocusChangeListener mTtsAudioFocusChangeListener;
    TtsEventSender mTtsEventSender;
    TtsListener mTtsListener;
    IUrlWrapperFactory mUrlWrapperFactory;
    PowerManager.WakeLock mWakeLock;
    private static final String TAG = GeneratedOutlineSupport1.outline39(TtsMediaPlayer.class, GeneratedOutlineSupport1.outline107("SPCH-"));
    private static final HandlerThread gHandlerThread = new HandlerThread("TtsMediaPlayer.Speak", -16);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: amazon.speech.simclient.TtsMediaPlayer$7  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass7 extends AudioPlayer.AudioPlayerListener {
        boolean _mStarted;
        final /* synthetic */ String val$analyzerStates;
        final /* synthetic */ String val$caption;
        final /* synthetic */ String val$contentToken;
        final /* synthetic */ TtsListener val$listener;
        final /* synthetic */ ITtsSpeechMarksEmitter val$marksEmitter;
        final /* synthetic */ String val$namespace;

        AnonymousClass7(String str, TtsListener ttsListener, String str2, String str3, ITtsSpeechMarksEmitter iTtsSpeechMarksEmitter, String str4) {
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
            TtsMediaPlayer.this.mTraceUtil.recordTrace("CSM_tts_end");
            if (!this._mStarted) {
                TtsMediaPlayer.this.mMetricsUtilWrapper.removeTimer(TtsMediaPlayer.METRIC_TTS_BUFFERING_LATENCY);
            }
            if (!TextUtilsWrapper.isEmpty(this.val$contentToken) && completionCode == TtsEventListener.CompletionCode.SUCCESS) {
                TtsMediaPlayer.this.mSpeechEventSender.sendSpeechEndEvent(this.val$contentToken);
            }
            if (!TextUtilsWrapper.isEmpty(this.val$caption)) {
                TtsMediaPlayer.this.sendSpeechStopNotification();
            }
            TtsMediaPlayer.this.setSpeaking(false, this.val$contentToken, null);
            TtsMediaPlayer.this.mTtsEventSender.fireSpeechCompleted(this.val$listener, currentTimeMillis, completionCode);
            TtsMediaPlayer ttsMediaPlayer = TtsMediaPlayer.this;
            ttsMediaPlayer.mListenerHandlerWrapper.post(new ReleaseFocusRunnable(this.val$namespace));
        }

        @Override // amazon.speech.simclient.AudioPlayer.AudioPlayerListener
        public void onPlayWhenReadyCommitted() {
            String unused = TtsMediaPlayer.TAG;
        }

        @Override // amazon.speech.simclient.AudioPlayer.AudioPlayerListener
        public void onPlayerError(Exception exc) {
            Log.e(TtsMediaPlayer.TAG, "Player error", exc);
            if (!TextUtilsWrapper.isEmpty(this.val$caption)) {
                TtsMediaPlayer.this.sendSpeechStopNotification();
            }
            if (!TextUtilsWrapper.isEmpty(this.val$contentToken)) {
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
            TtsMediaPlayer ttsMediaPlayer2 = TtsMediaPlayer.this;
            ttsMediaPlayer2.mListenerHandlerWrapper.post(new ReleaseFocusRunnable(this.val$namespace));
            TtsMediaPlayer.this.setSpeaking(false, this.val$contentToken, null);
            AudioPlayer audioPlayer2 = TtsMediaPlayer.this.mAudioPlayer;
            if (audioPlayer2 != null) {
                audioPlayer2.release();
                TtsMediaPlayer.this.mAudioPlayer = null;
            }
            MetricsUtil.MetadataHelper metadataHelper = new MetricsUtil.MetadataHelper("exception", exc.getClass().getSimpleName());
            if (exc.getMessage() != null) {
                metadataHelper.addMetadata("message", exc.getMessage());
            }
            TtsMediaPlayer.this.mMetricsUtilWrapper.recordOccurrence(TtsMediaPlayer.METRIC_TTS_PLAYBACK_ERROR, metadataHelper);
            TtsMediaPlayer.this.mMetricsUtilWrapper.removeTimer(TtsMediaPlayer.METRIC_TTS_BUFFERING_LATENCY);
        }

        @Override // amazon.speech.simclient.AudioPlayer.AudioPlayerListener
        public void onPlayerStateChanged(boolean z, AudioPlayer.AudioPlayerState audioPlayerState) {
            String unused = TtsMediaPlayer.TAG;
            String str = "onPlayerStateChanged - playWhenReady=" + z + " playbackState=" + audioPlayerState;
            if (audioPlayerState != AudioPlayer.AudioPlayerState.STATE_BUFFERING && audioPlayerState != AudioPlayer.AudioPlayerState.STATE_PREPARING) {
                if (audioPlayerState == AudioPlayer.AudioPlayerState.STATE_ENDED) {
                    ended(TtsEventListener.CompletionCode.SUCCESS);
                    return;
                } else if (audioPlayerState != AudioPlayer.AudioPlayerState.STATE_READY || this._mStarted) {
                    return;
                } else {
                    TtsMediaPlayer.this.mTraceUtil.recordTrace("CSM_tts_begin");
                    TtsMediaPlayer.this.mMetricsUtilWrapper.stopTimer(TtsMediaPlayer.METRIC_TTS_BUFFERING_LATENCY, null);
                    if (!TextUtilsWrapper.isEmpty(this.val$caption)) {
                        TtsMediaPlayer.this.sendSpeechStartNotification(this.val$caption);
                    }
                    TtsMediaPlayer ttsMediaPlayer = TtsMediaPlayer.this;
                    ttsMediaPlayer.mTtsEventSender.fireSpeechStarted(this.val$listener, ttsMediaPlayer.mTimestamp.currentTimeMillis());
                    this._mStarted = true;
                    TtsMediaPlayer.this.setSpeaking(true, this.val$contentToken, this.val$analyzerStates);
                    synchronized (TtsMediaPlayer.this.mFutureSync) {
                        if (this.mTimeoutFuture != null) {
                            this.mTimeoutFuture.cancel(true);
                        }
                    }
                    if (this.val$marksEmitter != null) {
                        String unused2 = TtsMediaPlayer.TAG;
                        this.val$marksEmitter.start();
                    }
                    synchronized (TtsMediaPlayer.this.mContentTokenLock) {
                        if (!TextUtilsWrapper.isEmpty(TtsMediaPlayer.this.mCurrentContentToken)) {
                            TtsMediaPlayer.this.mSpeechEventSender.sendSpeechStartEvent(TtsMediaPlayer.this.mCurrentContentToken);
                        }
                    }
                    return;
                }
            }
            if (this._mStarted) {
                TtsMediaPlayer.this.mMetricsUtilWrapper.recordOccurrence(TtsMediaPlayer.METRIC_TTS_REBUFFERING, null);
            } else {
                TtsMediaPlayer.this.mMetricsUtilWrapper.startTimer(TtsMediaPlayer.METRIC_TTS_BUFFERING_LATENCY);
            }
            ScheduledFuture<?> scheduledFuture = this.mTimeoutFuture;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(true);
            }
            synchronized (TtsMediaPlayer.this.mFutureSync) {
                this.mTimeoutFuture = TtsMediaPlayer.this.mScheduler.schedule(new Runnable() { // from class: amazon.speech.simclient.TtsMediaPlayer.7.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            AnonymousClass7.this.onTimeoutError();
                        } catch (Throwable th) {
                            Log.wtf(TtsMediaPlayer.TAG, "Error executing runnable!", th);
                        }
                    }
                }, 20L, TimeUnit.SECONDS);
            }
        }

        void onTimeoutError() {
            if (!TextUtilsWrapper.isEmpty(this.val$caption)) {
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
            TtsMediaPlayer ttsMediaPlayer2 = TtsMediaPlayer.this;
            ttsMediaPlayer2.mListenerHandlerWrapper.post(new ReleaseFocusRunnable(this.val$namespace));
            TtsMediaPlayer.this.setSpeaking(false, this.val$contentToken, null);
            TtsMediaPlayer.this.mMetricsUtilWrapper.recordOccurrence(TtsMediaPlayer.METRIC_TTS_PLAYBACK_TIMEOUT, null);
            TtsMediaPlayer.this.mMetricsUtilWrapper.removeTimer(TtsMediaPlayer.METRIC_TTS_BUFFERING_LATENCY);
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class MetricsUtilWrapper {
        private final Context mContext;
        private final MetricsUtil mMetricsUtil;

        public MetricsUtilWrapper(Context context, MetricsUtil metricsUtil) {
            this.mContext = context;
            this.mMetricsUtil = metricsUtil;
        }

        public void addTimer(String str, long j, int i) {
            TtsMediaPlayer ttsMediaPlayer = TtsMediaPlayer.this;
            if (ttsMediaPlayer.isPrewarmTts(ttsMediaPlayer.mNamespace)) {
                return;
            }
            this.mMetricsUtil.addTimer(this.mContext, TtsMediaPlayer.METRIC_SOURCE, TtsMediaPlayer.METRIC_PROGRAM_NAME, str, j, i);
        }

        public MetricsUtil getMetricsUtil() {
            TtsMediaPlayer ttsMediaPlayer = TtsMediaPlayer.this;
            if (ttsMediaPlayer.isPrewarmTts(ttsMediaPlayer.mNamespace)) {
                return null;
            }
            return this.mMetricsUtil;
        }

        public void recordCounter(String str, MetricsUtil.MetadataHelper metadataHelper, double d) {
            TtsMediaPlayer ttsMediaPlayer = TtsMediaPlayer.this;
            if (ttsMediaPlayer.isPrewarmTts(ttsMediaPlayer.mNamespace)) {
                return;
            }
            this.mMetricsUtil.recordCounter(this.mContext, TtsMediaPlayer.METRIC_SOURCE, TtsMediaPlayer.METRIC_PROGRAM_NAME, str, metadataHelper, d);
        }

        public void recordOccurrence(String str, MetricsUtil.MetadataHelper metadataHelper) {
            TtsMediaPlayer ttsMediaPlayer = TtsMediaPlayer.this;
            if (ttsMediaPlayer.isPrewarmTts(ttsMediaPlayer.mNamespace)) {
                return;
            }
            this.mMetricsUtil.recordOccurrence(this.mContext, TtsMediaPlayer.METRIC_SOURCE, TtsMediaPlayer.METRIC_PROGRAM_NAME, str, metadataHelper);
        }

        public void removeTimer(String str) {
            TtsMediaPlayer ttsMediaPlayer = TtsMediaPlayer.this;
            if (ttsMediaPlayer.isPrewarmTts(ttsMediaPlayer.mNamespace)) {
                return;
            }
            this.mMetricsUtil.removeTimer(this.mContext, str);
        }

        public void startTimer(String str) {
            TtsMediaPlayer ttsMediaPlayer = TtsMediaPlayer.this;
            if (ttsMediaPlayer.isPrewarmTts(ttsMediaPlayer.mNamespace)) {
                return;
            }
            this.mMetricsUtil.startTimer(this.mContext, TtsMediaPlayer.METRIC_SOURCE, TtsMediaPlayer.METRIC_PROGRAM_NAME, str);
        }

        public void stopTimer(String str, MetricsUtil.MetadataHelper metadataHelper) {
            TtsMediaPlayer ttsMediaPlayer = TtsMediaPlayer.this;
            if (ttsMediaPlayer.isPrewarmTts(ttsMediaPlayer.mNamespace)) {
                return;
            }
            this.mMetricsUtil.stopTimer(this.mContext, str, metadataHelper);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class NoOpTtsListener implements TtsListener {
        private NoOpTtsListener() {
        }

        @Override // amazon.speech.simclient.TtsListener
        public void onSpeechCompleted() {
            String unused = TtsMediaPlayer.TAG;
        }

        @Override // amazon.speech.simclient.TtsListener
        public void onSpeechError() {
            String unused = TtsMediaPlayer.TAG;
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
            if (TextUtilsWrapper.isEmpty(this.mNamespace) || TtsMediaPlayer.this.isPrewarmTts(this.mNamespace)) {
                return;
            }
            String unused = TtsMediaPlayer.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Releasing audio dialog focus for namespace: ");
            outline107.append(this.mNamespace);
            outline107.toString();
            TtsMediaPlayer.this.mSimClient.releaseAudioDialogFocus(this.mNamespace);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public abstract class SpeakDataSourceSetter {
        SpeakDataSourceSetter() {
        }

        abstract boolean setDataSource(SpeechExecutionHolder speechExecutionHolder) throws IllegalArgumentException, SecurityException, IllegalStateException, IOException;
    }

    /* loaded from: classes.dex */
    public class SpeakRunnable extends SpeechExecutionHolderedRunnable {
        final String analyzerStates;
        String caption;
        final String contentToken;
        final TtsListener listener;
        final String namespace;
        final SpeakDataSourceSetter speakDataSourceSetter;

        public SpeakRunnable(SpeakDataSourceSetter speakDataSourceSetter, String str, TtsListener ttsListener, String str2, String str3, String str4) {
            this.speakDataSourceSetter = speakDataSourceSetter;
            this.contentToken = str;
            this.listener = ttsListener;
            this.namespace = str2;
            this.caption = str3;
            this.analyzerStates = str4;
        }

        @Override // java.lang.Runnable
        public void run() {
            StackTraceElement[] stackTrace;
            try {
                Log.i(TtsMediaPlayer.TAG, "speak() begin run");
                TtsMediaPlayer.this.mNamespace = this.namespace;
                if (TtsMediaPlayer.this.mAudioPlayer == null) {
                    TtsMediaPlayer.this.mAudioPlayer = TtsMediaPlayer.this.mAudioPlayerFactory.createAudioPlayer(TtsMediaPlayer.this.mContext);
                }
                SpeechExecutionHolder speechExecutionHolder = getSpeechExecutionHolder();
                if (speechExecutionHolder != null) {
                    if (!this.speakDataSourceSetter.setDataSource(speechExecutionHolder)) {
                        Log.i(TtsMediaPlayer.TAG, "Setting the data source failed.");
                        TtsMediaPlayer.this.mTtsEventSender.fireSpeechError(this.listener, TtsMediaPlayer.this.mTimestamp.currentTimeMillis(), TtsEventListener.ErrorCode.MEDIA_PLAYBACK_ERROR, TtsMediaPlayer.ERROR_DATASOURCE_EXCEPTION_MESSAGE);
                        return;
                    }
                    TtsMediaPlayer.this.addListener(this.listener, this.contentToken, this.namespace, TtsMediaPlayer.this.mSpeechMarksEmitterHelper.getEmitter(), this.caption, this.analyzerStates);
                    String unused = TtsMediaPlayer.TAG;
                    TtsMediaPlayer.this.mAudioPlayer.play(TtsMediaPlayer.this.mInputStream);
                    if (this.namespace == null || TtsMediaPlayer.this.isPrewarmTts(this.namespace)) {
                        return;
                    }
                    TtsMediaPlayer.this.mSimClient.addAudioDialogFocus(this.namespace);
                    return;
                }
                throw new IllegalStateException("Must set SpeechExecutionHolder before running.");
            } catch (Exception e) {
                Log.e(TtsMediaPlayer.TAG, "MediaPlayer exception", e);
                long currentTimeMillis = TtsMediaPlayer.this.mTimestamp.currentTimeMillis();
                if (!TextUtilsWrapper.isEmpty(e.getMessage())) {
                    GeneratedOutlineSupport1.outline148(e, GeneratedOutlineSupport1.outline107("MediaPlayer exception message: "), TtsMediaPlayer.TAG);
                }
                for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                    String str = TtsMediaPlayer.TAG;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MediaPlayer exception: ");
                    outline107.append(stackTraceElement.toString());
                    Log.e(str, outline107.toString());
                }
                TtsMediaPlayer.this.setSpeaking(false, this.contentToken, null);
                TtsMediaPlayer.this.mTtsEventSender.fireSpeechError(this.listener, currentTimeMillis, TtsEventListener.ErrorCode.MEDIA_PLAYER_EXCEPTION, TtsMediaPlayer.ERROR_MEDIAPLAYER_EXCEPTION_MESSAGE);
                TtsMediaPlayer ttsMediaPlayer = TtsMediaPlayer.this;
                ttsMediaPlayer.mListenerHandlerWrapper.post(new ReleaseFocusRunnable(this.namespace));
                AudioPlayer audioPlayer = TtsMediaPlayer.this.mAudioPlayer;
                if (audioPlayer == null) {
                    return;
                }
                audioPlayer.release();
                TtsMediaPlayer.this.mAudioPlayer = null;
            }
        }
    }

    /* loaded from: classes.dex */
    public class StopRunnable extends SpeechExecutionHolderedRunnable {
        final String localNamespace;
        final TtsEventListener.CompletionCode reason;

        public StopRunnable(String str, TtsEventListener.CompletionCode completionCode) {
            this.localNamespace = str;
            this.reason = completionCode;
        }

        void doSendSpeechInterruptEvent(String str, long j) {
            TtsMediaPlayer.this.mSpeechEventSender.sendSpeechInterruptEvent(str, j);
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (TtsMediaPlayer.this.mAudioPlayer == null) {
                    Log.w(TtsMediaPlayer.TAG, "MediaPlayer not ready to be used.");
                    synchronized (TtsMediaPlayer.this.mContentTokenLock) {
                        TtsMediaPlayer.this.mCurrentContentToken = null;
                    }
                    return;
                }
                long currentPosition = TtsMediaPlayer.this.mAudioPlayer.getCurrentPosition();
                Log.i(TtsMediaPlayer.TAG, "MediaPlayer.stop");
                try {
                    TtsMediaPlayer.this.mAudioPlayer.stop();
                } catch (TimeoutException e) {
                    Log.e(TtsMediaPlayer.TAG, "Failed to stop media player.", e);
                }
                TtsMediaPlayer.this.mSpeechMarksEmitterHelper.clearEmitter();
                synchronized (TtsMediaPlayer.this.mContentTokenLock) {
                    if (!TextUtilsWrapper.isEmpty(TtsMediaPlayer.this.mCurrentContentToken)) {
                        doSendSpeechInterruptEvent(TtsMediaPlayer.this.mCurrentContentToken, currentPosition);
                    }
                }
                if (TtsMediaPlayer.this.mTtsListener != null) {
                    TtsMediaPlayer.this.mTraceUtil.recordTrace("CSM_tts_end");
                    TtsMediaPlayer.this.mTtsEventSender.fireSpeechCompleted(TtsMediaPlayer.this.mTtsListener, TtsMediaPlayer.this.mTimestamp.currentTimeMillis(), this.reason);
                    TtsMediaPlayer.this.mListenerHandlerWrapper.post(new ReleaseFocusRunnable(this.localNamespace));
                    TtsMediaPlayer.this.mTtsListener = null;
                }
                synchronized (TtsMediaPlayer.this.mContentTokenLock) {
                    TtsMediaPlayer.this.mCurrentContentToken = null;
                }
            } catch (Throwable th) {
                synchronized (TtsMediaPlayer.this.mContentTokenLock) {
                    TtsMediaPlayer.this.mCurrentContentToken = null;
                    throw th;
                }
            }
        }
    }

    static {
        gHandlerThread.start();
        sNoOpSpeechMarkEmitter = new ITtsSpeechMarksEmitter() { // from class: amazon.speech.simclient.TtsMediaPlayer.5
            @Override // amazon.speech.simclient.ITtsSpeechMarksEmitter
            public void clear() {
            }

            @Override // amazon.speech.simclient.ITtsSpeechMarksEmitter
            public void errorParsingSpeechmarks(Throwable th) {
            }

            @Override // amazon.speech.simclient.ITtsSpeechMarksEmitter
            public void scheduleSpeechMark(String str) {
            }

            @Override // amazon.speech.simclient.ITtsSpeechMarksEmitter
            public void start() {
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TtsMediaPlayer(Context context, SimClient simClient) {
        this(context, simClient, new SpeechEventSender(simClient, context), new HandlerWrapper(new Handler(Looper.getMainLooper())), new AudioPlayerFactory(), null, new AlexaTtsAlert(context), new AspPlaybackReporter(context), null, null, null, new TimestampWrapper());
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
            if (!TextUtilsWrapper.isEmpty(this.mCaption)) {
                this.mSpeechInitiatedWithCaptions = true;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("speakResource caption=");
                outline107.append(this.mCaption);
                outline107.toString();
            } else {
                this.mSpeechInitiatedWithCaptions = false;
            }
        } else {
            this.mSpeechInitiatedWithCaptions = false;
            this.mCaption = null;
        }
        return this.mCaption;
    }

    private int getWifiSignalLevel() {
        WifiInfo connectionInfo;
        WifiManager wifiManager = (WifiManager) this.mContext.getSystemService("wifi");
        if (wifiManager == null || (connectionInfo = wifiManager.getConnectionInfo()) == null) {
            return -1;
        }
        return WifiManager.calculateSignalLevel(connectionInfo.getRssi(), 5);
    }

    private void initWakeLock() {
        if (this.mPowerManager == null) {
            this.mPowerManager = (PowerManager) this.mContext.getSystemService("power");
        }
        PowerManager powerManager = this.mPowerManager;
        if (powerManager != null) {
            this.mWakeLock = powerManager.newWakeLock(268435462, TAG);
        } else {
            Log.e(TAG, "Could not obtain system service POWER_SERVICE");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPrewarmTts(String str) {
        return PREWARM_TTS_NAMESPACE.equals(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postBadUrlMetric() {
        this.mMetricsUtilWrapper.recordOccurrence(METRIC_BAD_URL, null);
        Log.e(TAG, "Got and posted SIM.TtsMediaPlayer.InvalidURL");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postPrewarmCompleteMetric(long j) {
        this.mMetricsUtilWrapper.addTimer(METRIC_TTS_PREWARM_WAIT_TIME, j, 1);
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
        this.mMetricsUtilWrapper.addTimer(METRIC_TTS_PREWARM_TOTAL_TIME, j, 1);
    }

    private void recordSocketTimeout(String str) {
        MetricsUtil.MetadataHelper metadataHelper = str != null ? new MetricsUtil.MetadataHelper("message", str) : null;
        try {
            this.mMetricsUtilWrapper.recordCounter(METRIC_SOCKET_TIMEOUT_SIGNAL_STRENGTH, metadataHelper, getWifiSignalLevel());
        } catch (SecurityException unused) {
            Log.w(TAG, "Missing ACCESS_WIFI_STATE permission");
            this.mMetricsUtilWrapper.recordOccurrence(METRIC_SOCKET_TIMEOUT, metadataHelper);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void resetPrewarmTracker() {
        this.mPrewarmTracker = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSpeechStartNotification(String str) {
        int checkCallingOrSelfPermission = this.mContext.checkCallingOrSelfPermission(TTS_CAPTION_PERMISSION);
        if (checkCallingOrSelfPermission != 0 || TextUtilsWrapper.isEmpty(str)) {
            if (-1 != checkCallingOrSelfPermission) {
                return;
            }
            Log.e(TAG, "App does not have permission to display captions!");
            return;
        }
        String str2 = "Received caption with Audio: " + str;
        Intent intent = new Intent();
        String str3 = "Sending Speech Start Event for caption: " + str;
        intent.setAction(SERVICE_SPEECH_START_ACTION);
        intent.putExtra(SERVICE_TTS_CAPTION_EXTRA, this.mCaption);
        this.mContext.sendBroadcast(intent, TTS_CAPTION_PERMISSION);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSpeechStopNotification() {
        if (this.mContext.checkCallingOrSelfPermission(TTS_CAPTION_PERMISSION) == 0) {
            Intent intent = new Intent();
            intent.setAction(SERVICE_SPEECH_STOP_ACTION);
            this.mContext.sendBroadcast(intent, TTS_CAPTION_PERMISSION);
            return;
        }
        Log.e(TAG, "App does not have permission to display captions!");
    }

    static boolean usePrewarmTracker(Uri uri) {
        return uri.toString().toLowerCase().startsWith(TTS_SERVER_URL);
    }

    @Override // amazon.speech.simclient.ITtsPlayer
    public synchronized void acquireAudioFocusLock() {
        if (!this.mFocusLockHeld) {
            changeFocus(true, 0L);
            this.mFocusLockHeld = true;
        }
    }

    synchronized void acquireWakeLock() {
        if (this.mWakeLock == null) {
            Log.w(TAG, "acquireWakeLock:  wake lock is null");
            return;
        }
        try {
            if (!this.mWakeLock.isHeld()) {
                this.mWakeLock.acquire();
            }
        } catch (SecurityException unused) {
            Log.w(TAG, "acquireWakeLock failed - does the app have android.permission.WAKE_LOCK?");
        } catch (Exception e) {
            Log.w(TAG, "acquireWakeLock failed.", e);
        }
    }

    protected void addListener(TtsListener ttsListener, String str, String str2, ITtsSpeechMarksEmitter iTtsSpeechMarksEmitter, String str3, String str4) {
        if (this.mCurrentAudioPlayerListener != null) {
            this.mAudioPlayer.removeListener();
        }
        this.mCurrentAudioPlayerListener = new AnonymousClass7(str3, ttsListener, str, str4, iTtsSpeechMarksEmitter, str2);
        this.mAudioPlayer.setListener(this.mCurrentAudioPlayerListener);
    }

    @Override // amazon.speech.simclient.ITtsPlayer
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
        this.mTraceUtil.recordTrace("CSM_tts_url_connection_begin");
        this.mTtsEventSender.fireUriConnectionStarted(ttsListener, this.mTimestamp.currentTimeMillis());
        InputStream inputStream = InProcTtsStreamProvider.getInstance().get(uri);
        if (inputStream != null) {
            Log.i(TAG, "InProcTtsStreamProvider found a stream for " + uri);
            this.mTraceUtil.recordTrace("CSM_tts_url_connection_end");
            this.mTtsEventSender.fireUriConnectionEstablished(ttsListener, this.mTimestamp.currentTimeMillis());
        } else {
            speechExecutionHolder.startEstablishConnection(urlWrapper, this.mConnectionMetricsListener);
            HttpURLConnection waitForReceiveConnection = speechExecutionHolder.waitForReceiveConnection();
            waitForReceiveConnection.setReadTimeout(15000);
            waitForReceiveConnection.setConnectTimeout(15000);
            waitForReceiveConnection.setRequestMethod("GET");
            waitForReceiveConnection.setDoInput(true);
            String str = "";
            try {
                try {
                    str = uri.toString().startsWith(LOCALHOST_URL_PATTERN) ? METRIC_LOCALHOST_URL_CONNECT : METRIC_URL_CONNECT;
                    this.mMetricsUtilWrapper.startTimer(str);
                    waitForReceiveConnection.connect();
                    this.mMetricsUtilWrapper.stopTimer(str, null);
                    try {
                        int responseCode = waitForReceiveConnection.getResponseCode();
                        this.mTraceUtil.recordTrace("CSM_tts_url_connection_end");
                        this.mTtsEventSender.fireUriConnectionEstablished(ttsListener, this.mTimestamp.currentTimeMillis());
                        String str2 = "getSampleSource response " + responseCode;
                        if (responseCode < 400) {
                            inputStream = waitForReceiveConnection.getInputStream();
                            if (inputStream == null) {
                                Log.e(TAG, "UrlWrapper could not be created");
                                throw new IllegalArgumentException("Could not get stream for Uri");
                            }
                        } else {
                            IOException iOException = new IOException(GeneratedOutlineSupport1.outline49("Http Error: ", responseCode));
                            Log.e(TAG, "Http error: " + iOException);
                            throw iOException;
                        }
                    } catch (SocketTimeoutException e) {
                        recordSocketTimeout(e.getMessage());
                        throw e;
                    }
                } catch (UnknownHostException e2) {
                    this.mMetricsUtilWrapper.recordOccurrence(METRIC_BAD_URL, null);
                    throw e2;
                }
            } catch (Throwable th) {
                this.mMetricsUtilWrapper.removeTimer(str);
                throw th;
            }
        }
        if (iTtsSpeechMarksEmitter == null) {
            Log.w(TAG, "Marks emitter null, no speech marks will be broadcast.");
            return inputStream;
        }
        if (isPrewarmTts(this.mNamespace)) {
            iTtsSpeechMarksEmitter = sNoOpSpeechMarkEmitter;
        }
        return AudioWordMarkDemuxer.split(inputStream, iTtsSpeechMarksEmitter).getMp3Stream();
    }

    @Override // amazon.speech.simclient.ITtsMediaPlayer
    public void interrupt() {
        stop(TtsEventListener.CompletionCode.INTERRUPT);
    }

    @Override // amazon.speech.simclient.ITtsPlayer
    public boolean isSpeaking() {
        return this.mIsSpeaking;
    }

    StopRunnable newStopRunnable(String str, TtsEventListener.CompletionCode completionCode) {
        return new StopRunnable(str, completionCode);
    }

    synchronized void postPrewarmRunnable(Runnable runnable) {
        if (this.mPrewarmTracker != null) {
            Log.i(TAG, "Already running prewarm. Skipping...");
        } else {
            this.mPrewarmTracker = new TtsPrewarmTracker(this.mEstablishConnectionExecutor.submit(runnable), 20000L);
        }
    }

    @Override // amazon.speech.simclient.ITtsPlayer
    public void prepare() {
        postPrewarmRunnable(new PrewarmTtsRunnable(TTS_SERVER_URL, this.mPrewarmCallaback));
    }

    public void processEventsOnTestThread() {
        HandlerThread handlerThread = new HandlerThread("TtsMediaPlayer-ListenThread");
        handlerThread.start();
        this.mListenerHandlerWrapper = new HandlerWrapper(new Handler(handlerThread.getLooper()));
    }

    @Override // amazon.speech.simclient.ITtsPlayer
    public synchronized void releaseAudioFocusLock() {
        if (this.mFocusLockHeld) {
            this.mFocusLockHeld = false;
            changeFocus(false, 0L);
        }
    }

    synchronized void releaseWakeLock() {
        if (this.mWakeLock != null) {
            try {
                this.mWakeLock.release();
            } catch (Exception e) {
                String str = TAG;
                Log.w(str, "releaseWakeLock failed: " + e.getLocalizedMessage());
            }
        } else {
            Log.w(TAG, "releaseWakeLock:  wake lock is null");
        }
    }

    @Override // amazon.speech.simclient.ITtsMediaPlayer
    public void setAudioFocusChangeListener(TtsAudioFocusChangeListener ttsAudioFocusChangeListener) {
        if (this.mTtsAudioFocusChangeListener != ttsAudioFocusChangeListener) {
            Log.i(TAG, "setAudioFocusChangeListener: TtsAudioFocusChangeListener changed, set new listener.");
            this.mAudioFocusHelper = new AudioFocusHelper(new AudioManagerWrapper(this.mContext), ttsAudioFocusChangeListener);
            this.mTtsAudioFocusChangeListener = ttsAudioFocusChangeListener;
            return;
        }
        Log.w(TAG, "setAudioFocusChangeListener: TtsAudioFocusChangeListener is identical, ignore.");
    }

    void setMetricsUtilWrapper(Context context, MetricsUtil metricsUtil) {
        this.mMetricsUtilWrapper = new MetricsUtilWrapper(context, metricsUtil);
    }

    @Override // amazon.speech.simclient.ITtsPlayer
    public void setSimClientForTts(SimClient simClient) {
        this.mSimClient = simClient;
        this.mSpeechEventSender = new SpeechEventSender(simClient, this.mContext);
    }

    synchronized void setSpeaking(boolean z, String str, String str2) {
        if (this.mIsSpeaking == z) {
            return;
        }
        long j = 0;
        String str3 = "setSpeaking " + z;
        boolean isPrewarmTts = isPrewarmTts(this.mNamespace);
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
        } else {
            acquireWakeLock();
        }
        if (!isPrewarmTts) {
            this.mTtsAlert.alertTts(z, str, str2);
            if (this.mAspReporter != null) {
                this.mAspReporter.reportAspPlayback(z);
            }
        }
        this.mIsSpeaking = z;
        if (!z) {
            j = 500;
        }
        changeFocus(z, j);
    }

    @Override // amazon.speech.simclient.ITtsPlayer
    public void setVolume(float f) {
        String str = "setVolume() | vol: " + f + " | mAudioPlayer: " + this.mAudioPlayer;
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

    void speak(SpeakDataSourceSetter speakDataSourceSetter, String str, TtsListener ttsListener, String str2, String str3, boolean z, String str4) {
        this.mMetricsUtilWrapper.recordOccurrence(METRIC_SPEAK, null);
        synchronized (this.mContentTokenLock) {
            this.mCurrentContentToken = str;
        }
        postSpeakRunnableSafely(z, new SpeakRunnable(speakDataSourceSetter, str, ttsListener, str2, str3, str4));
    }

    @Override // amazon.speech.simclient.ITtsPlayer
    public void speakResource(int i, int i2, TtsListener ttsListener) {
        speakResource(i, i2, ttsListener, (String) null);
    }

    @Override // amazon.speech.simclient.ITtsPlayer
    public void speakUri(Uri uri, String str, TtsListener ttsListener) {
        speakUri(uri, str, ttsListener, null, null, null);
    }

    void stop(TtsEventListener.CompletionCode completionCode) {
        String str = "stop: " + completionCode;
        if (completionCode != TtsEventListener.CompletionCode.SUCCESS) {
            if (!isSpeaking()) {
                return;
            }
            if (completionCode == TtsEventListener.CompletionCode.CANCEL) {
                this.mMetricsUtilWrapper.recordOccurrence(METRIC_SPEAK_CANCEL, null);
            } else if (completionCode == TtsEventListener.CompletionCode.INTERRUPT) {
                this.mMetricsUtilWrapper.recordOccurrence(METRIC_SPEAK_INTERRUPT, null);
            }
            String str2 = this.mNamespace;
            Log.i(TAG, "stop localNamespace = " + str2 + " with code " + completionCode);
            setSpeaking(false, this.mCurrentContentToken, null);
            postSpeakRunnableSafely(false, newStopRunnable(str2, completionCode));
            return;
        }
        throw new IllegalArgumentException("Calling stop() with reason SUCCESS is invalid");
    }

    @Override // amazon.speech.simclient.ITtsPlayer
    public void speakResource(int i, int i2, TtsListener ttsListener, String str) {
        speakResource(this.mContext.getResources(), i, i2, ttsListener, str);
    }

    @Override // amazon.speech.simclient.ITtsPlayer
    public void speakUri(Uri uri, String str, TtsListener ttsListener, TtsSpeechMarksListener ttsSpeechMarksListener) {
        speakUri(uri, str, ttsListener, ttsSpeechMarksListener, null, null);
    }

    @Override // amazon.speech.simclient.ITtsPlayer
    public void speakResource(Resources resources, int i, int i2, TtsListener ttsListener) {
        speakResource(resources, i, i2, ttsListener, null);
    }

    @Override // amazon.speech.simclient.ITtsPlayer
    public void speakUri(Uri uri, String str, TtsListener ttsListener, String str2) {
        speakUri(uri, str, ttsListener, null, str2, null);
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
            HttpURLConnection connect;
            long currentTimeMillis = System.currentTimeMillis();
            try {
                String unused = TtsMediaPlayer.TAG;
                while (TtsMediaPlayer.connect(this.mUrlWrapper).getInputStream().read() > 0) {
                }
                String unused2 = TtsMediaPlayer.TAG;
                String str = "Pre-warm request HTTP response = " + connect.getResponseCode();
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
                    Log.wtf(TtsMediaPlayer.TAG, "Fatal error parsing URL.");
                    throw new AndroidRuntimeException(e);
                }
            }
            throw new IllegalArgumentException();
        }
    }

    @Override // amazon.speech.simclient.ITtsPlayer
    public void speakResource(final Resources resources, final int i, int i2, TtsListener ttsListener, String str) {
        StringBuilder outline110 = GeneratedOutlineSupport1.outline110("speakResource  ", i, " captionResource = ", i2, " namespace = ");
        outline110.append(str);
        outline110.toString();
        if (isSpeaking()) {
            cancel();
        }
        if (ttsListener == null) {
            Log.i(TAG, "Using null Tts listener");
            ttsListener = new NoOpTtsListener();
        }
        TtsListener ttsListener2 = ttsListener;
        this.mTtsListener = ttsListener2;
        speak(new SpeakDataSourceSetter() { // from class: amazon.speech.simclient.TtsMediaPlayer.6
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // amazon.speech.simclient.TtsMediaPlayer.SpeakDataSourceSetter
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

    @Override // amazon.speech.simclient.ITtsPlayer
    public void speakUri(Uri uri, String str, TtsListener ttsListener, TtsSpeechMarksListener ttsSpeechMarksListener, String str2) {
        speakUri(uri, str, ttsListener, null, str2, null);
    }

    TtsMediaPlayer(Context context, SimClient simClient, SpeechEventSender speechEventSender, HandlerWrapper handlerWrapper, AudioPlayerFactory audioPlayerFactory, HandlerWrapper handlerWrapper2, AlexaTtsAlert alexaTtsAlert, AspPlaybackReporter aspPlaybackReporter, MetricsUtil metricsUtil, TraceUtil traceUtil, PowerManager powerManager, TimestampWrapper timestampWrapper) {
        this.mScheduler = Executors.newScheduledThreadPool(1);
        this.mFutureSync = new Object();
        this.mContentTokenLock = new Object();
        this.mEstablishConnectionExecutor = Executors.newFixedThreadPool(3);
        this.mCleanupConnectionExecutor = Executors.newFixedThreadPool(3);
        this.mConnectionMetricsListener = new ConnectionMetricsListener() { // from class: amazon.speech.simclient.TtsMediaPlayer.1
            @Override // amazon.speech.simclient.TtsMediaPlayer.ConnectionMetricsListener
            public void onBadUrl() {
                TtsMediaPlayer.this.postBadUrlMetric();
            }

            @Override // amazon.speech.simclient.TtsMediaPlayer.ConnectionMetricsListener
            public void onPrewarmWaitComplete(long j) {
                TtsMediaPlayer.this.postPrewarmCompleteMetric(j);
            }
        };
        this.mSpeechMarksEmitterHelper = new ISpeechMarksEmitterHelper() { // from class: amazon.speech.simclient.TtsMediaPlayer.2
            ITtsSpeechMarksEmitter mCurrentEmitter;

            @Override // amazon.speech.simclient.TtsMediaPlayer.ISpeechMarksEmitterHelper
            public synchronized void clearEmitter() {
                if (this.mCurrentEmitter != null) {
                    this.mCurrentEmitter.clear();
                }
                this.mCurrentEmitter = null;
            }

            @Override // amazon.speech.simclient.TtsMediaPlayer.ISpeechMarksEmitterHelper
            public synchronized ITtsSpeechMarksEmitter createEmitter(TtsSpeechMarksListener ttsSpeechMarksListener, HandlerWrapper handlerWrapper3) {
                this.mCurrentEmitter = new TtsSpeechMarksEmitter(ttsSpeechMarksListener, TtsMediaPlayer.this.mListenerHandlerWrapper, TtsMediaPlayer.this.mContext);
                return this.mCurrentEmitter;
            }

            @Override // amazon.speech.simclient.TtsMediaPlayer.ISpeechMarksEmitterHelper
            public synchronized ITtsSpeechMarksEmitter getEmitter() {
                return this.mCurrentEmitter;
            }
        };
        this.mUrlWrapperFactory = new IUrlWrapperFactory() { // from class: amazon.speech.simclient.TtsMediaPlayer.3
            @Override // amazon.speech.simclient.TtsMediaPlayer.IUrlWrapperFactory
            public UrlWrapper create(String str) {
                try {
                    return new UrlWrapper(new URL(str));
                } catch (MalformedURLException e) {
                    Log.e(TtsMediaPlayer.TAG, "malformed url", e);
                    TtsMediaPlayer.this.mMetricsUtilWrapper.recordOccurrence(TtsMediaPlayer.METRIC_BAD_URL, null);
                    return null;
                }
            }
        };
        this.mPrewarmCallaback = new PrewarmCallback() { // from class: amazon.speech.simclient.TtsMediaPlayer.8
            @Override // amazon.speech.simclient.TtsMediaPlayer.PrewarmCallback
            public void onPrewarmComplete(long j) {
                TtsMediaPlayer.this.resetPrewarmTracker();
                TtsMediaPlayer.this.postTotalPrewarmTimeMetric(j);
            }
        };
        if (context != null && simClient != null && speechEventSender != null && audioPlayerFactory != null && alexaTtsAlert != null && timestampWrapper != null) {
            this.mContext = context;
            this.mSimClient = simClient;
            this.mAudioPlayerFactory = audioPlayerFactory;
            this.mSpeechEventSender = speechEventSender;
            this.mListenerHandlerWrapper = handlerWrapper;
            this.mSpeakHandlerWrapper = handlerWrapper2;
            this.mTtsEventSender = new TtsEventSender(handlerWrapper);
            this.mTtsAlert = alexaTtsAlert;
            this.mAspReporter = aspPlaybackReporter;
            setMetricsUtilWrapper(context, metricsUtil == null ? MetricsUtil.getInstance() : metricsUtil);
            traceUtil = traceUtil == null ? TraceUtil.getInstance(this.mContext) : traceUtil;
            this.mTimestamp = timestampWrapper;
            this.mTraceUtil = traceUtil;
            this.mPowerManager = powerManager;
            initWakeLock();
            return;
        }
        throw new IllegalArgumentException("parameters cannot be null");
    }

    @Override // amazon.speech.simclient.ITtsPlayer
    public void speakUri(final Uri uri, String str, TtsListener ttsListener, TtsSpeechMarksListener ttsSpeechMarksListener, String str2, String str3) {
        final long nanoTime = System.nanoTime();
        GeneratedOutlineSupport1.outline163("speakUri namespace = ", str2, TAG);
        if (isSpeaking()) {
            cancel();
        }
        if (uri != null) {
            this.mTtsListener = ttsListener == null ? new NoOpTtsListener() : ttsListener;
            this.mSpeechMarksEmitterHelper.createEmitter(ttsSpeechMarksListener, this.mListenerHandlerWrapper);
            speak(new SpeakDataSourceSetter() { // from class: amazon.speech.simclient.TtsMediaPlayer.4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super();
                }

                @Override // amazon.speech.simclient.TtsMediaPlayer.SpeakDataSourceSetter
                public boolean setDataSource(SpeechExecutionHolder speechExecutionHolder) throws IllegalArgumentException, SecurityException, IllegalStateException, IOException {
                    long nanoTime2 = (System.nanoTime() - nanoTime) / 1000000;
                    if (nanoTime2 > 1000) {
                        String str4 = TtsMediaPlayer.TAG;
                        Log.w(str4, "SpeakUri spinup took " + nanoTime2 + " ms");
                        TtsMediaPlayer.this.mMetricsUtilWrapper.recordOccurrence(TtsMediaPlayer.METRIC_SPEAK_URI_SLOW_SPINUP, new MetricsUtil.MetadataHelper(TtsMediaPlayer.WARNING_TTS_SPEAK_URI_LATENCY, Long.toString(nanoTime2)));
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
}
