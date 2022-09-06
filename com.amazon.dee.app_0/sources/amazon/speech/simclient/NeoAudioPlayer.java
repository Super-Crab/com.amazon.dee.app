package amazon.speech.simclient;

import amazon.csm.neoplayer.NeoPlayer;
import amazon.csm.neoplayer.TrackPlayer;
import amazon.speech.simclient.AudioPlayer;
import amazon.speech.util.Log;
import amazon.speech.util.RuntimeDeviceTypeHelper;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Trace;
import com.amazon.metrics.MetricsUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeoutException;
/* loaded from: classes.dex */
public class NeoAudioPlayer implements AudioPlayer {
    private static final boolean DEBUG = true;
    static String FIRE_TV_FEATURE = "amazon.hardware.fire_tv";
    static int FLAG_ADAPTIVE_VOLUME = 0;
    private static final String FLAG_ADAPTIVE_VOLUME_FIELD = "FLAG_ADAPTIVE_VOLUME";
    static String MANUFACTURER_3P_FACEBOOK = "Facebook";
    static final int MAX_LEVELS = 5;
    static final String METRICS_METADATA_ACTUAL_TIME = "actual_time";
    static final String METRICS_METADATA_BUDGETED_TIME = "budgeted_time";
    static final String METRICS_METADATA_WIFI_PERMISSIONS_MISSING_FROM_PKG = "pkg_needs_wifi_perm";
    static final String METRICS_METADATA_WIFI_SIGNAL = "wifi_signal";
    static final String METRICS_SLOW_MEDIA_FILE_READ = "SIMClientAPI.SIMClient.SlowMediaFileRead";
    static int STREAM_TTS = -2;
    private static final String STREAM_TTS_FIELD = "STREAM_TTS";
    private static final String TAG = "NeoAudioPlayer";
    static TtsPolicyRetriever TTS_POLICY_RETRIEVER;
    private final Context mContext;
    private String mDeviceManufacturer;
    boolean mIsSystemStream;
    private final MetricsUtil mMetricsUtil;
    final NeoPlayer mNeoPlayer;
    private NeoPlayerFactory mNeoPlayerFactory;
    NeoPlayerListener mNeoPlayerListener;
    PackageManager pm;

    /* loaded from: classes.dex */
    public static class NeoPlayerFactory {
        public NeoPlayer create(Context context) {
            return new TrackPlayer(NeoAudioPlayer.getStream(context));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class NeoPlayerListener implements NeoPlayer.Listener {
        private final AudioPlayer.AudioPlayerListener mInternalListener;

        public NeoPlayerListener(AudioPlayer.AudioPlayerListener audioPlayerListener) {
            if (audioPlayerListener != null) {
                this.mInternalListener = audioPlayerListener;
                return;
            }
            throw new IllegalArgumentException("listener cannot be null.");
        }

        @Override // amazon.csm.neoplayer.NeoPlayer.Listener
        public void onPlayerError(Exception exc) {
            this.mInternalListener.onPlayerError(exc);
        }

        @Override // amazon.csm.neoplayer.NeoPlayer.Listener
        public void onPlayerStateChanged(NeoPlayer.NeoPlayerState neoPlayerState) {
            this.mInternalListener.onPlayerStateChanged(true, AudioPlayer.AudioPlayerState.values()[neoPlayerState.ordinal()]);
        }

        @Override // amazon.csm.neoplayer.NeoPlayer.Listener
        public void onSlowReadDetected(long j, long j2) {
            MetricsUtil.MetadataHelper metadataHelper = new MetricsUtil.MetadataHelper(NeoAudioPlayer.METRICS_METADATA_BUDGETED_TIME, Long.toString(j));
            metadataHelper.addMetadata(NeoAudioPlayer.METRICS_METADATA_ACTUAL_TIME, Long.toString(j2));
            try {
                metadataHelper.addMetadata(NeoAudioPlayer.METRICS_METADATA_WIFI_SIGNAL, Integer.toString(NeoAudioPlayer.this.getWifiSignalLevel()));
            } catch (SecurityException unused) {
                String packageName = NeoAudioPlayer.this.mContext.getPackageName();
                String str = NeoAudioPlayer.TAG;
                Log.w(str, "Missing ACCESS_WIFI_STATE permission, pkg=" + packageName);
                metadataHelper.addMetadata(NeoAudioPlayer.METRICS_METADATA_WIFI_PERMISSIONS_MISSING_FROM_PKG, packageName);
            }
            NeoAudioPlayer.this.mMetricsUtil.recordOccurrence(NeoAudioPlayer.this.mContext, "SimClientAPI_Metrics", "SimClientAPI", NeoAudioPlayer.METRICS_SLOW_MEDIA_FILE_READ, metadataHelper);
        }
    }

    static {
        try {
            STREAM_TTS = ((Integer) AudioManager.class.getDeclaredField(STREAM_TTS_FIELD).get(null)).intValue();
            FLAG_ADAPTIVE_VOLUME = ((Integer) AudioAttributes.class.getDeclaredField(FLAG_ADAPTIVE_VOLUME_FIELD).get(null)).intValue();
        } catch (IllegalAccessException | NoSuchFieldException e) {
            Log.e(TAG, "Could not access FLAG_ADAPTIVE_VOLUME.", e);
            STREAM_TTS = -2;
            FLAG_ADAPTIVE_VOLUME = 0;
        }
        TTS_POLICY_RETRIEVER = new TtsPolicyRetriever();
    }

    public NeoAudioPlayer(Context context, MetricsUtil metricsUtil) {
        this(context, metricsUtil, new NeoPlayerFactory(), isSystemStream(context), Build.MANUFACTURER);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getStream(Context context) {
        return TTS_POLICY_RETRIEVER.getTtsStreamTypeForSystem(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getWifiSignalLevel() {
        WifiInfo connectionInfo;
        WifiManager wifiManager = getWifiManager();
        if (wifiManager == null || (connectionInfo = wifiManager.getConnectionInfo()) == null) {
            return -1;
        }
        return calculateSignalLevel(connectionInfo.getRssi());
    }

    private static boolean isSystemStream(Context context) {
        return getStream(context) == 1;
    }

    private static boolean isTtsStream(Context context) {
        return STREAM_TTS == getStream(context);
    }

    int calculateSignalLevel(int i) {
        return WifiManager.calculateSignalLevel(i, 5);
    }

    @Override // amazon.speech.simclient.AudioPlayer
    public long getCurrentPosition() {
        Trace.beginSection("NeoAudioPlayer getCurrentPosition");
        try {
            Log.d(TAG, "Got curr pos");
            return this.mNeoPlayer.getCurrentPosition();
        } finally {
            Trace.endSection();
        }
    }

    protected WifiManager getWifiManager() {
        return (WifiManager) this.mContext.getSystemService("wifi");
    }

    NeoPlayer newNeoPlayer() {
        NeoPlayer create = this.mNeoPlayerFactory.create(this.mContext);
        if (this.pm.hasSystemFeature(FIRE_TV_FEATURE)) {
            Log.d(TAG, "Using Fire_TV AudioAttributes");
            create.setAttributes(new AudioAttributes.Builder().setUsage(13).setContentType(1).setFlags(1).build());
        } else if (MANUFACTURER_3P_FACEBOOK.equals(this.mDeviceManufacturer)) {
            Log.d(TAG, "Using Isolated Audio Channel AudioAttributes for Streaming TTS on 3P device");
            create.setAttributes(new AudioAttributes.Builder().setUsage(13).setContentType(1).setFlags(1).build());
        } else if (this.mIsSystemStream) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Using Stream (");
            outline107.append(getStream(this.mContext));
            outline107.append(") AudioAttributes for Dolby support. Setting content type to CONTENT_TYPE_SPEECH.");
            Log.d(str, outline107.toString());
            create.setAttributes(new AudioAttributes.Builder().setUsage(13).setContentType(1).build());
        } else if (isTtsStream(this.mContext)) {
            String str2 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Using Stream (");
            outline1072.append(getStream(this.mContext));
            outline1072.append(") AudioAttributes for Dolby support. Using FLAG_ADAPTIVE_VOLUME");
            Log.i(str2, outline1072.toString());
            create.setAttributes(new AudioAttributes.Builder().setUsage(13).setContentType(1).setFlags(FLAG_ADAPTIVE_VOLUME).build());
        } else {
            Log.d(TAG, "Using STREAM_MUSIC default attributes");
        }
        return create;
    }

    @Override // amazon.speech.simclient.AudioPlayer
    public void play(InputStream inputStream) {
        Trace.beginSection("NeoAudioPlayer play");
        try {
            if (inputStream != null) {
                Log.d(TAG, "Started player");
                try {
                    this.mNeoPlayer.loadStream(inputStream);
                    this.mNeoPlayer.play();
                } catch (IOException e) {
                    Log.e(TAG, "Could not read from stream.", e);
                } catch (TimeoutException e2) {
                    Log.e(TAG, "Timed out waiting for previous play to stop.", e2);
                }
                return;
            }
            throw new IllegalArgumentException("inputStream cannot be null.");
        } finally {
            Trace.endSection();
        }
    }

    @Override // amazon.speech.simclient.AudioPlayer
    public void release() {
        Trace.beginSection("NeoAudioPlayer release");
        try {
            Log.d(TAG, "Released player");
            removeListener();
            this.mNeoPlayer.release();
        } finally {
            Trace.endSection();
        }
    }

    @Override // amazon.speech.simclient.AudioPlayer
    public synchronized void removeListener() {
        Trace.beginSection("NeoAudioPlayer removeListener");
        Log.d(TAG, "Removed listener");
        if (this.mNeoPlayerListener == null) {
            Trace.endSection();
            return;
        }
        this.mNeoPlayer.setListener(null);
        this.mNeoPlayerListener = null;
        Trace.endSection();
    }

    @Override // amazon.speech.simclient.AudioPlayer
    public void setListener(AudioPlayer.AudioPlayerListener audioPlayerListener) {
        Trace.beginSection("NeoAudioPlayer setListener");
        try {
            Log.d(TAG, "Added listener");
            if (audioPlayerListener != null) {
                this.mNeoPlayerListener = new NeoPlayerListener(audioPlayerListener);
                this.mNeoPlayer.setListener(this.mNeoPlayerListener);
                return;
            }
            throw new IllegalArgumentException("backendPlayerListener cannot be null.");
        } finally {
            Trace.endSection();
        }
    }

    @Override // amazon.speech.simclient.AudioPlayer
    public void setVolume(float f) {
        if (RuntimeDeviceTypeHelper.isDeviceATablet()) {
            Log.d(TAG, "set volume called on tablet");
            return;
        }
        throw new UnsupportedOperationException();
    }

    @Override // amazon.speech.simclient.AudioPlayer
    public void stop() throws TimeoutException {
        Trace.beginSection("NeoAudioPlayer stop");
        try {
            Log.d(TAG, "Stopped player");
            this.mNeoPlayer.stop();
        } finally {
            Trace.endSection();
        }
    }

    public NeoAudioPlayer(Context context, MetricsUtil metricsUtil, NeoPlayerFactory neoPlayerFactory, boolean z, String str) {
        this.mContext = context;
        this.mMetricsUtil = metricsUtil;
        this.pm = this.mContext.getPackageManager();
        this.mNeoPlayerFactory = neoPlayerFactory;
        this.mIsSystemStream = z;
        this.mDeviceManufacturer = str;
        Trace.beginSection("NeoAudioPlayer create");
        try {
            Log.d(TAG, "Made NeoPlayer");
            this.mNeoPlayer = newNeoPlayer();
        } finally {
            Trace.endSection();
        }
    }
}
