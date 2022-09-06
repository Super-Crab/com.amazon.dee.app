package com.brentvatne.exoplayer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.accessibility.CaptioningManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import com.amazon.clouddrive.cdasdk.aps.account.FeatureState;
import com.amazon.tarazed.core.session.sessionEvents.PlaybackStates;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.brentvatne.react.R;
import com.brentvatne.receiver.AudioBecomingNoisyReceiver;
import com.brentvatne.receiver.BecomingNoisyListener;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.drm.DefaultDrmSessionManager;
import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.FrameworkMediaDrm;
import com.google.android.exoplayer2.drm.HttpMediaDrmCallback;
import com.google.android.exoplayer2.drm.UnsupportedDrmException;
import com.google.android.exoplayer2.mediacodec.MediaCodecRenderer;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.MetadataOutput;
import com.google.android.exoplayer2.source.BehindLiveWindowException;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MergingMediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.SingleSampleMediaSource;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.DefaultSsChunkSource;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Util;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
class ReactExoplayerView extends FrameLayout implements LifecycleEventListener, Player.EventListener, BandwidthMeter.EventListener, BecomingNoisyListener, AudioManager.OnAudioFocusChangeListener, MetadataOutput, DrmSessionEventListener {
    private static final CookieManager DEFAULT_COOKIE_MANAGER = new CookieManager();
    private static final int SHOW_PROGRESS = 1;
    private static final String TAG = "ReactExoplayerView";
    private final AudioBecomingNoisyReceiver audioBecomingNoisyReceiver;
    private final AudioManager audioManager;
    private String audioTrackType;
    private Dynamic audioTrackValue;
    private float audioVolume;
    private final DefaultBandwidthMeter bandwidthMeter;
    private int bufferForPlaybackAfterRebufferMs;
    private int bufferForPlaybackMs;
    private final ReactExoplayerConfig config;
    private boolean controls;
    private boolean disableFocus;
    private String[] drmLicenseHeader;
    private String drmLicenseUrl;
    private UUID drmUUID;
    private final VideoEventEmitter eventEmitter;
    private Player.EventListener eventListener;
    private ExoPlayerView exoPlayerView;
    private String extension;
    private boolean hasAudioFocus;
    private boolean isBuffering;
    private boolean isFullscreen;
    private boolean isInBackground;
    private boolean isPaused;
    private boolean loadVideoStarted;
    private float mProgressUpdateInterval;
    private boolean mReportBandwidth;
    private Handler mainHandler;
    private int maxBitRate;
    private int maxBufferMs;
    private DataSource.Factory mediaDataSourceFactory;
    private int minBufferMs;
    private int minLoadRetryCount;
    private boolean muted;
    private boolean playInBackground;
    private View playPauseControlContainer;
    private SimpleExoPlayer player;
    private PlayerControlView playerControlView;
    private boolean playerNeedsSource;
    private boolean preventsDisplaySleepDuringVideoPlayback;
    private final Handler progressHandler;
    private float rate;
    private boolean repeat;
    private Map<String, String> requestHeaders;
    private long resumePosition;
    private int resumeWindow;
    private long seekTime;
    private Uri srcUri;
    private String textTrackType;
    private Dynamic textTrackValue;
    private ReadableArray textTracks;
    private final ThemedReactContext themedReactContext;
    private DefaultTrackSelector trackSelector;
    private String videoTrackType;
    private Dynamic videoTrackValue;

    static {
        DEFAULT_COOKIE_MANAGER.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
    }

    public ReactExoplayerView(ThemedReactContext themedReactContext, ReactExoplayerConfig reactExoplayerConfig) {
        super(themedReactContext);
        this.muted = false;
        this.hasAudioFocus = false;
        this.rate = 1.0f;
        this.audioVolume = 1.0f;
        this.minLoadRetryCount = 3;
        this.maxBitRate = 0;
        this.seekTime = C.TIME_UNSET;
        this.minBufferMs = 50000;
        this.maxBufferMs = 50000;
        this.bufferForPlaybackMs = 2500;
        this.bufferForPlaybackAfterRebufferMs = 5000;
        this.preventsDisplaySleepDuringVideoPlayback = true;
        this.mProgressUpdateInterval = 250.0f;
        this.playInBackground = false;
        this.mReportBandwidth = false;
        this.drmUUID = null;
        this.drmLicenseUrl = null;
        this.drmLicenseHeader = null;
        this.progressHandler = new Handler() { // from class: com.brentvatne.exoplayer.ReactExoplayerView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 1 && ReactExoplayerView.this.player != null && ReactExoplayerView.this.player.getPlaybackState() == 3 && ReactExoplayerView.this.player.getPlayWhenReady()) {
                    long currentPosition = ReactExoplayerView.this.player.getCurrentPosition();
                    ReactExoplayerView.this.eventEmitter.progressChanged(currentPosition, (ReactExoplayerView.this.player.getDuration() * ReactExoplayerView.this.player.getBufferedPercentage()) / 100, ReactExoplayerView.this.player.getDuration(), ReactExoplayerView.this.getPositionInFirstPeriodMsForCurrentWindow(currentPosition));
                    sendMessageDelayed(obtainMessage(1), Math.round(ReactExoplayerView.this.mProgressUpdateInterval));
                }
            }
        };
        this.themedReactContext = themedReactContext;
        this.eventEmitter = new VideoEventEmitter(themedReactContext);
        this.config = reactExoplayerConfig;
        this.bandwidthMeter = reactExoplayerConfig.getBandwidthMeter();
        createViews();
        this.audioManager = (AudioManager) themedReactContext.getSystemService("audio");
        this.themedReactContext.addLifecycleEventListener(this);
        this.audioBecomingNoisyReceiver = new AudioBecomingNoisyReceiver(this.themedReactContext);
    }

    private void addPlayerControl() {
        if (this.player == null) {
            return;
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.playerControlView.setLayoutParams(layoutParams);
        int indexOfChild = indexOfChild(this.playerControlView);
        if (indexOfChild != -1) {
            removeViewAt(indexOfChild);
        }
        addView(this.playerControlView, 1, layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void applyModifiers() {
        setRepeatModifier(this.repeat);
        setMutedModifier(this.muted);
    }

    private DataSource.Factory buildDataSourceFactory(boolean z) {
        return DataSourceUtil.getDefaultDataSourceFactory(this.themedReactContext, z ? this.bandwidthMeter : null, this.requestHeaders);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DrmSessionManager buildDrmSessionManager(UUID uuid, String str, String[] strArr) throws UnsupportedDrmException {
        if (Util.SDK_INT < 18) {
            return null;
        }
        HttpMediaDrmCallback httpMediaDrmCallback = new HttpMediaDrmCallback(str, buildHttpDataSourceFactory(false));
        if (strArr != null) {
            for (int i = 0; i < strArr.length - 1; i += 2) {
                httpMediaDrmCallback.setKeyRequestProperty(strArr[i], strArr[i + 1]);
            }
        }
        return new DefaultDrmSessionManager(uuid, FrameworkMediaDrm.newInstance(uuid), httpMediaDrmCallback, null, false, 3);
    }

    private HttpDataSource.Factory buildHttpDataSourceFactory(boolean z) {
        return DataSourceUtil.getDefaultHttpDataSourceFactory(this.themedReactContext, z ? this.bandwidthMeter : null, this.requestHeaders);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MediaSource buildMediaSource(Uri uri, String str, DrmSessionManager drmSessionManager) {
        int inferContentType = Util.inferContentType(!TextUtils.isEmpty(str) ? GeneratedOutlineSupport1.outline72(".", str) : uri.getLastPathSegment());
        if (inferContentType != 0) {
            if (inferContentType == 1) {
                return new SsMediaSource.Factory(new DefaultSsChunkSource.Factory(this.mediaDataSourceFactory), buildDataSourceFactory(false)).mo7420setDrmSessionManager(drmSessionManager).mo7423setLoadErrorHandlingPolicy(this.config.buildLoadErrorHandlingPolicy(this.minLoadRetryCount)).mo7417createMediaSource(uri);
            }
            if (inferContentType == 2) {
                return new HlsMediaSource.Factory(this.mediaDataSourceFactory).mo7420setDrmSessionManager(drmSessionManager).mo7423setLoadErrorHandlingPolicy(this.config.buildLoadErrorHandlingPolicy(this.minLoadRetryCount)).mo7417createMediaSource(uri);
            }
            if (inferContentType == 3) {
                return new ProgressiveMediaSource.Factory(this.mediaDataSourceFactory).mo7420setDrmSessionManager(drmSessionManager).mo7423setLoadErrorHandlingPolicy(this.config.buildLoadErrorHandlingPolicy(this.minLoadRetryCount)).mo7417createMediaSource(uri);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline49("Unsupported type: ", inferContentType));
        }
        return new DashMediaSource.Factory(new DefaultDashChunkSource.Factory(this.mediaDataSourceFactory), buildDataSourceFactory(false)).mo7420setDrmSessionManager(drmSessionManager).mo7423setLoadErrorHandlingPolicy(this.config.buildLoadErrorHandlingPolicy(this.minLoadRetryCount)).mo7417createMediaSource(uri);
    }

    private MediaSource buildTextSource(String str, Uri uri, String str2, String str3) {
        return new SingleSampleMediaSource.Factory(this.mediaDataSourceFactory).createMediaSource(uri, Format.createTextSampleFormat(str, str2, -1, str3), C.TIME_UNSET);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<MediaSource> buildTextSources() {
        ArrayList<MediaSource> arrayList = new ArrayList<>();
        if (this.textTracks == null) {
            return arrayList;
        }
        for (int i = 0; i < this.textTracks.size(); i++) {
            ReadableMap mo6944getMap = this.textTracks.mo6944getMap(i);
            String string = mo6944getMap.getString("language");
            MediaSource buildTextSource = buildTextSource(mo6944getMap.hasKey("title") ? mo6944getMap.getString("title") : GeneratedOutlineSupport1.outline74(string, " ", i), Uri.parse(mo6944getMap.getString("uri")), mo6944getMap.getString("type"), string);
            if (buildTextSource != null) {
                arrayList.add(buildTextSource);
            }
        }
        return arrayList;
    }

    private void clearProgressMessageHandler() {
        this.progressHandler.removeMessages(1);
    }

    private void clearResumePosition() {
        this.resumeWindow = -1;
        this.resumePosition = C.TIME_UNSET;
    }

    private void createViews() {
        clearResumePosition();
        this.mediaDataSourceFactory = buildDataSourceFactory(true);
        CookieHandler cookieHandler = CookieHandler.getDefault();
        CookieManager cookieManager = DEFAULT_COOKIE_MANAGER;
        if (cookieHandler != cookieManager) {
            CookieHandler.setDefault(cookieManager);
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.exoPlayerView = new ExoPlayerView(getContext());
        this.exoPlayerView.setLayoutParams(layoutParams);
        addView(this.exoPlayerView, 0, layoutParams);
        this.mainHandler = new Handler();
    }

    private WritableArray getAudioTrackInfo() {
        WritableArray createArray = Arguments.createArray();
        MappingTrackSelector.MappedTrackInfo currentMappedTrackInfo = this.trackSelector.getCurrentMappedTrackInfo();
        int trackRendererIndex = getTrackRendererIndex(1);
        if (currentMappedTrackInfo != null && trackRendererIndex != -1) {
            TrackGroupArray trackGroups = currentMappedTrackInfo.getTrackGroups(trackRendererIndex);
            for (int i = 0; i < trackGroups.length; i++) {
                Format format = trackGroups.get(i).getFormat(0);
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("index", i);
                String str = format.id;
                String str2 = "";
                if (str == null) {
                    str = str2;
                }
                createMap.putString("title", str);
                createMap.putString("type", format.sampleMimeType);
                String str3 = format.language;
                if (str3 == null) {
                    str3 = str2;
                }
                createMap.putString("language", str3);
                int i2 = format.bitrate;
                if (i2 != -1) {
                    str2 = String.format(Locale.US, "%.2fMbps", Float.valueOf(i2 / 1000000.0f));
                }
                createMap.putString("bitrate", str2);
                createArray.pushMap(createMap);
            }
        }
        return createArray;
    }

    private int getGroupIndexForDefaultLocale(TrackGroupArray trackGroupArray) {
        if (trackGroupArray.length == 0) {
            return -1;
        }
        String language = Locale.getDefault().getLanguage();
        String iSO3Language = Locale.getDefault().getISO3Language();
        for (int i = 0; i < trackGroupArray.length; i++) {
            String str = trackGroupArray.get(i).getFormat(0).language;
            if (str != null && (str.equals(language) || str.equals(iSO3Language))) {
                return i;
            }
        }
        return 0;
    }

    private WritableArray getTextTrackInfo() {
        WritableArray createArray = Arguments.createArray();
        MappingTrackSelector.MappedTrackInfo currentMappedTrackInfo = this.trackSelector.getCurrentMappedTrackInfo();
        int trackRendererIndex = getTrackRendererIndex(3);
        if (currentMappedTrackInfo != null && trackRendererIndex != -1) {
            TrackGroupArray trackGroups = currentMappedTrackInfo.getTrackGroups(trackRendererIndex);
            for (int i = 0; i < trackGroups.length; i++) {
                Format format = trackGroups.get(i).getFormat(0);
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("index", i);
                String str = format.id;
                if (str == null) {
                    str = "";
                }
                createMap.putString("title", str);
                createMap.putString("type", format.sampleMimeType);
                String str2 = format.language;
                if (str2 == null) {
                    str2 = "";
                }
                createMap.putString("language", str2);
                createArray.pushMap(createMap);
            }
        }
        return createArray;
    }

    private WritableArray getVideoTrackInfo() {
        WritableArray createArray = Arguments.createArray();
        MappingTrackSelector.MappedTrackInfo currentMappedTrackInfo = this.trackSelector.getCurrentMappedTrackInfo();
        int trackRendererIndex = getTrackRendererIndex(2);
        if (currentMappedTrackInfo != null && trackRendererIndex != -1) {
            TrackGroupArray trackGroups = currentMappedTrackInfo.getTrackGroups(trackRendererIndex);
            for (int i = 0; i < trackGroups.length; i++) {
                TrackGroup trackGroup = trackGroups.get(i);
                for (int i2 = 0; i2 < trackGroup.length; i2++) {
                    Format format = trackGroup.getFormat(i2);
                    WritableMap createMap = Arguments.createMap();
                    int i3 = format.width;
                    if (i3 == -1) {
                        i3 = 0;
                    }
                    createMap.putInt("width", i3);
                    int i4 = format.height;
                    if (i4 == -1) {
                        i4 = 0;
                    }
                    createMap.putInt("height", i4);
                    int i5 = format.bitrate;
                    if (i5 == -1) {
                        i5 = 0;
                    }
                    createMap.putInt("bitrate", i5);
                    String str = format.codecs;
                    if (str == null) {
                        str = "";
                    }
                    createMap.putString("codecs", str);
                    String str2 = format.id;
                    if (str2 == null) {
                        str2 = String.valueOf(i2);
                    }
                    createMap.putString("trackId", str2);
                    createArray.pushMap(createMap);
                }
            }
        }
        return createArray;
    }

    private void initializePlayer() {
        new Handler().postDelayed(new Runnable() { // from class: com.brentvatne.exoplayer.ReactExoplayerView.6
            @Override // java.lang.Runnable
            public void run() {
                if (ReactExoplayerView.this.player == null) {
                    ReactExoplayerView.this.trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory());
                    ReactExoplayerView.this.trackSelector.setParameters(ReactExoplayerView.this.trackSelector.buildUponParameters().setMaxVideoBitrate(ReactExoplayerView.this.maxBitRate == 0 ? Integer.MAX_VALUE : ReactExoplayerView.this.maxBitRate));
                    DefaultAllocator defaultAllocator = new DefaultAllocator(true, 65536);
                    DefaultLoadControl.Builder builder = new DefaultLoadControl.Builder();
                    builder.setAllocator(defaultAllocator);
                    builder.setBufferDurationsMs(ReactExoplayerView.this.minBufferMs, ReactExoplayerView.this.maxBufferMs, ReactExoplayerView.this.bufferForPlaybackMs, ReactExoplayerView.this.bufferForPlaybackAfterRebufferMs);
                    builder.setTargetBufferBytes(-1);
                    builder.setPrioritizeTimeOverSizeThresholds(true);
                    DefaultLoadControl createDefaultLoadControl = builder.createDefaultLoadControl();
                    DefaultRenderersFactory extensionRendererMode = new DefaultRenderersFactory(ReactExoplayerView.this.getContext()).setExtensionRendererMode(0);
                    ReactExoplayerView reactExoplayerView = ReactExoplayerView.this;
                    reactExoplayerView.player = new SimpleExoPlayer.Builder(reactExoplayerView.getContext(), extensionRendererMode).setTrackSelector(ReactExoplayerView.this.trackSelector).setBandwidthMeter(ReactExoplayerView.this.bandwidthMeter).setLoadControl(createDefaultLoadControl).build();
                    ReactExoplayerView.this.player.addListener(this);
                    ReactExoplayerView.this.player.addMetadataOutput(this);
                    ReactExoplayerView.this.exoPlayerView.setPlayer(ReactExoplayerView.this.player);
                    ReactExoplayerView.this.audioBecomingNoisyReceiver.setListener(this);
                    ReactExoplayerView.this.bandwidthMeter.addEventListener(new Handler(), this);
                    ReactExoplayerView reactExoplayerView2 = ReactExoplayerView.this;
                    reactExoplayerView2.setPlayWhenReady(!reactExoplayerView2.isPaused);
                    ReactExoplayerView.this.playerNeedsSource = true;
                    ReactExoplayerView.this.player.setPlaybackParameters(new PlaybackParameters(ReactExoplayerView.this.rate, 1.0f));
                }
                if (ReactExoplayerView.this.playerNeedsSource && ReactExoplayerView.this.srcUri != null) {
                    ReactExoplayerView.this.exoPlayerView.invalidateAspectRatio();
                    DrmSessionManager drmSessionManager = null;
                    if (this.drmUUID != null) {
                        try {
                            drmSessionManager = ReactExoplayerView.this.buildDrmSessionManager(this.drmUUID, this.drmLicenseUrl, this.drmLicenseHeader);
                        } catch (UnsupportedDrmException e) {
                            ReactExoplayerView.this.eventEmitter.error(ReactExoplayerView.this.getResources().getString(Util.SDK_INT < 18 ? R.string.error_drm_not_supported : e.reason == 1 ? R.string.error_drm_unsupported_scheme : R.string.error_drm_unknown), e);
                            return;
                        }
                    }
                    ArrayList buildTextSources = ReactExoplayerView.this.buildTextSources();
                    ReactExoplayerView reactExoplayerView3 = ReactExoplayerView.this;
                    MediaSource buildMediaSource = reactExoplayerView3.buildMediaSource(reactExoplayerView3.srcUri, ReactExoplayerView.this.extension, drmSessionManager);
                    if (buildTextSources.size() != 0) {
                        buildTextSources.add(0, buildMediaSource);
                        buildMediaSource = new MergingMediaSource((MediaSource[]) buildTextSources.toArray(new MediaSource[buildTextSources.size()]));
                    }
                    boolean z = ReactExoplayerView.this.resumeWindow != -1;
                    if (z) {
                        ReactExoplayerView.this.player.seekTo(ReactExoplayerView.this.resumeWindow, ReactExoplayerView.this.resumePosition);
                    }
                    ReactExoplayerView.this.player.prepare(buildMediaSource, !z, false);
                    ReactExoplayerView.this.playerNeedsSource = false;
                    ReactExoplayerView reactExoplayerView4 = ReactExoplayerView.this;
                    reactExoplayerView4.reLayout(reactExoplayerView4.exoPlayerView);
                    ReactExoplayerView.this.eventEmitter.loadStart();
                    ReactExoplayerView.this.loadVideoStarted = true;
                }
                ReactExoplayerView.this.initializePlayerControl();
                ReactExoplayerView reactExoplayerView5 = ReactExoplayerView.this;
                reactExoplayerView5.setControls(reactExoplayerView5.controls);
                ReactExoplayerView.this.applyModifiers();
            }
        }, 1L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initializePlayerControl() {
        if (this.playerControlView == null) {
            this.playerControlView = new PlayerControlView(getContext());
        }
        this.playerControlView.setPlayer(this.player);
        this.playerControlView.show();
        this.playPauseControlContainer = this.playerControlView.findViewById(R.id.exo_play_pause_container);
        this.exoPlayerView.setOnClickListener(new View.OnClickListener() { // from class: com.brentvatne.exoplayer.ReactExoplayerView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ReactExoplayerView.this.togglePlayerControlVisibility();
            }
        });
        ((ImageButton) this.playerControlView.findViewById(R.id.exo_play)).setOnClickListener(new View.OnClickListener() { // from class: com.brentvatne.exoplayer.ReactExoplayerView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ReactExoplayerView.this.player != null && ReactExoplayerView.this.player.getPlaybackState() == 4) {
                    ReactExoplayerView.this.player.seekTo(0L);
                }
                ReactExoplayerView.this.setPausedModifier(false);
            }
        });
        ((ImageButton) this.playerControlView.findViewById(R.id.exo_pause)).setOnClickListener(new View.OnClickListener() { // from class: com.brentvatne.exoplayer.ReactExoplayerView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ReactExoplayerView.this.setPausedModifier(true);
            }
        });
        this.eventListener = new Player.EventListener() { // from class: com.brentvatne.exoplayer.ReactExoplayerView.5
            @Override // com.google.android.exoplayer2.Player.EventListener
            public void onPlayerStateChanged(boolean z, int i) {
                ReactExoplayerView reactExoplayerView = ReactExoplayerView.this;
                reactExoplayerView.reLayout(reactExoplayerView.playPauseControlContainer);
                ReactExoplayerView.this.player.removeListener(ReactExoplayerView.this.eventListener);
            }
        };
        this.player.addListener(this.eventListener);
    }

    private static boolean isBehindLiveWindow(ExoPlaybackException exoPlaybackException) {
        Log.e("ExoPlayer Exception", exoPlaybackException.toString());
        if (exoPlaybackException.type != 0) {
            return false;
        }
        for (Throwable sourceException = exoPlaybackException.getSourceException(); sourceException != null; sourceException = sourceException.getCause()) {
            if ((sourceException instanceof BehindLiveWindowException) || (sourceException instanceof HttpDataSource.HttpDataSourceException)) {
                return true;
            }
        }
        return false;
    }

    private void onBuffering(boolean z) {
        if (this.isBuffering == z) {
            return;
        }
        this.isBuffering = z;
        if (z) {
            this.eventEmitter.buffering(true);
        } else {
            this.eventEmitter.buffering(false);
        }
    }

    private void onStopPlayback() {
        if (this.isFullscreen) {
            setFullscreen(false);
        }
        this.audioManager.abandonAudioFocus(this);
    }

    private void pausePlayback() {
        SimpleExoPlayer simpleExoPlayer = this.player;
        if (simpleExoPlayer != null && simpleExoPlayer.getPlayWhenReady()) {
            setPlayWhenReady(false);
        }
        setKeepScreenOn(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reLayout(View view) {
        if (view == null) {
            return;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
        view.layout(view.getLeft(), view.getTop(), view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    private void releasePlayer() {
        if (this.player != null) {
            updateResumePosition();
            this.player.release();
            this.player.removeMetadataOutput(this);
            this.trackSelector = null;
            this.player = null;
        }
        this.progressHandler.removeMessages(1);
        this.themedReactContext.removeLifecycleEventListener(this);
        this.audioBecomingNoisyReceiver.removeListener();
        this.bandwidthMeter.removeEventListener(this);
    }

    private void reloadSource() {
        this.playerNeedsSource = true;
        initializePlayer();
    }

    private boolean requestAudioFocus() {
        return this.disableFocus || this.srcUri == null || this.hasAudioFocus || this.audioManager.requestAudioFocus(this, 3, 1) == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlayWhenReady(boolean z) {
        SimpleExoPlayer simpleExoPlayer = this.player;
        if (simpleExoPlayer == null) {
            return;
        }
        if (z) {
            this.hasAudioFocus = requestAudioFocus();
            if (!this.hasAudioFocus) {
                return;
            }
            this.player.setPlayWhenReady(true);
            return;
        }
        simpleExoPlayer.setPlayWhenReady(false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0012, code lost:
        if (r0 != 4) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void startPlayback() {
        /*
            r3 = this;
            com.google.android.exoplayer2.SimpleExoPlayer r0 = r3.player
            if (r0 == 0) goto L25
            int r0 = r0.getPlaybackState()
            r1 = 1
            if (r0 == r1) goto L21
            r2 = 2
            if (r0 == r2) goto L15
            r2 = 3
            if (r0 == r2) goto L15
            r1 = 4
            if (r0 == r1) goto L21
            goto L28
        L15:
            com.google.android.exoplayer2.SimpleExoPlayer r0 = r3.player
            boolean r0 = r0.getPlayWhenReady()
            if (r0 != 0) goto L28
            r3.setPlayWhenReady(r1)
            goto L28
        L21:
            r3.initializePlayer()
            goto L28
        L25:
            r3.initializePlayer()
        L28:
            boolean r0 = r3.disableFocus
            if (r0 != 0) goto L31
            boolean r0 = r3.preventsDisplaySleepDuringVideoPlayback
            r3.setKeepScreenOn(r0)
        L31:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brentvatne.exoplayer.ReactExoplayerView.startPlayback():void");
    }

    private void startProgressHandler() {
        this.progressHandler.sendEmptyMessage(1);
    }

    private void stopPlayback() {
        onStopPlayback();
        releasePlayer();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void togglePlayerControlVisibility() {
        if (this.player == null) {
            return;
        }
        reLayout(this.playerControlView);
        if (this.playerControlView.isVisible()) {
            this.playerControlView.hide();
        } else {
            this.playerControlView.show();
        }
    }

    private void updateResumePosition() {
        this.resumeWindow = this.player.getCurrentWindowIndex();
        this.resumePosition = this.player.isCurrentWindowSeekable() ? Math.max(0L, this.player.getCurrentPosition()) : C.TIME_UNSET;
    }

    private void videoLoaded() {
        if (this.loadVideoStarted) {
            int i = 0;
            this.loadVideoStarted = false;
            setSelectedAudioTrack(this.audioTrackType, this.audioTrackValue);
            setSelectedVideoTrack(this.videoTrackType, this.videoTrackValue);
            setSelectedTextTrack(this.textTrackType, this.textTrackValue);
            Format videoFormat = this.player.getVideoFormat();
            int i2 = videoFormat != null ? videoFormat.width : 0;
            if (videoFormat != null) {
                i = videoFormat.height;
            }
            this.eventEmitter.load(this.player.getDuration(), this.player.getCurrentPosition(), i2, i, getAudioTrackInfo(), getTextTrackInfo(), getVideoTrackInfo(), videoFormat != null ? videoFormat.id : "-1");
        }
    }

    public void cleanUpResources() {
        stopPlayback();
    }

    public void clearSrc() {
        if (this.srcUri != null) {
            this.player.stop(true);
            this.srcUri = null;
            this.extension = null;
            this.requestHeaders = null;
            this.mediaDataSourceFactory = null;
            clearResumePosition();
        }
    }

    public double getPositionInFirstPeriodMsForCurrentWindow(long j) {
        Timeline.Window window = new Timeline.Window();
        if (!this.player.getCurrentTimeline().isEmpty()) {
            this.player.getCurrentTimeline().getWindow(this.player.getCurrentWindowIndex(), window);
        }
        return window.windowStartTimeMs + j;
    }

    public int getTrackRendererIndex(int i) {
        SimpleExoPlayer simpleExoPlayer = this.player;
        if (simpleExoPlayer != null) {
            int rendererCount = simpleExoPlayer.getRendererCount();
            for (int i2 = 0; i2 < rendererCount; i2++) {
                if (this.player.getRendererType(i2) == i) {
                    return i2;
                }
            }
            return -1;
        }
        return -1;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initializePlayer();
    }

    @Override // com.brentvatne.receiver.BecomingNoisyListener
    public void onAudioBecomingNoisy() {
        this.eventEmitter.audioBecomingNoisy();
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public void onAudioFocusChange(int i) {
        if (i == -2) {
            this.eventEmitter.audioFocusChanged(false);
        } else if (i == -1) {
            this.hasAudioFocus = false;
            this.eventEmitter.audioFocusChanged(false);
            pausePlayback();
            this.audioManager.abandonAudioFocus(this);
        } else if (i == 1) {
            this.hasAudioFocus = true;
            this.eventEmitter.audioFocusChanged(true);
        }
        SimpleExoPlayer simpleExoPlayer = this.player;
        if (simpleExoPlayer != null) {
            if (i == -3) {
                if (this.muted) {
                    return;
                }
                simpleExoPlayer.setVolume(this.audioVolume * 0.8f);
            } else if (i != 1 || this.muted) {
            } else {
                simpleExoPlayer.setVolume(this.audioVolume * 1.0f);
            }
        }
    }

    @Override // com.google.android.exoplayer2.upstream.BandwidthMeter.EventListener
    public void onBandwidthSample(int i, long j, long j2) {
        if (this.mReportBandwidth) {
            SimpleExoPlayer simpleExoPlayer = this.player;
            if (simpleExoPlayer == null) {
                this.eventEmitter.bandwidthReport(j2, 0, 0, "-1");
                return;
            }
            Format videoFormat = simpleExoPlayer.getVideoFormat();
            int i2 = 0;
            int i3 = videoFormat != null ? videoFormat.width : 0;
            if (videoFormat != null) {
                i2 = videoFormat.height;
            }
            this.eventEmitter.bandwidthReport(j2, i2, i3, videoFormat != null ? videoFormat.id : "-1");
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // com.google.android.exoplayer2.drm.DrmSessionEventListener
    public void onDrmKeysLoaded(int i, MediaSource.MediaPeriodId mediaPeriodId) {
    }

    @Override // com.google.android.exoplayer2.drm.DrmSessionEventListener
    public void onDrmKeysRemoved(int i, MediaSource.MediaPeriodId mediaPeriodId) {
    }

    @Override // com.google.android.exoplayer2.drm.DrmSessionEventListener
    public void onDrmKeysRestored(int i, MediaSource.MediaPeriodId mediaPeriodId) {
    }

    @Override // com.google.android.exoplayer2.drm.DrmSessionEventListener
    public void onDrmSessionManagerError(int i, MediaSource.MediaPeriodId mediaPeriodId, Exception exc) {
        this.eventEmitter.error("onDrmSessionManagerError", exc);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        stopPlayback();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        this.isInBackground = true;
        if (this.playInBackground) {
            return;
        }
        setPlayWhenReady(false);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        if (!this.playInBackground || !this.isInBackground) {
            setPlayWhenReady(!this.isPaused);
        }
        this.isInBackground = false;
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onLoadingChanged(boolean z) {
    }

    @Override // com.google.android.exoplayer2.metadata.MetadataOutput
    public void onMetadata(Metadata metadata) {
        this.eventEmitter.timedMetadata(metadata);
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        this.eventEmitter.playbackRateChange(playbackParameters.speed);
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPlayerError(ExoPlaybackException exoPlaybackException) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ExoPlaybackException type : ");
        outline107.append(exoPlaybackException.type);
        String sb = outline107.toString();
        int i = exoPlaybackException.type;
        if (i == 1) {
            Exception rendererException = exoPlaybackException.getRendererException();
            if (rendererException instanceof MediaCodecRenderer.DecoderInitializationException) {
                MediaCodecRenderer.DecoderInitializationException decoderInitializationException = (MediaCodecRenderer.DecoderInitializationException) rendererException;
                if (decoderInitializationException.codecInfo.name == null) {
                    if (decoderInitializationException.getCause() instanceof MediaCodecUtil.DecoderQueryException) {
                        sb = getResources().getString(R.string.error_querying_decoders);
                    } else if (decoderInitializationException.secureDecoderRequired) {
                        sb = getResources().getString(R.string.error_no_secure_decoder, decoderInitializationException.mimeType);
                    } else {
                        sb = getResources().getString(R.string.error_no_decoder, decoderInitializationException.mimeType);
                    }
                } else {
                    sb = getResources().getString(R.string.error_instantiating_decoder, decoderInitializationException.codecInfo.name);
                }
            }
        } else if (i == 0) {
            sb = getResources().getString(R.string.unrecognized_media_format);
        }
        this.eventEmitter.error(sb, exoPlaybackException);
        this.playerNeedsSource = true;
        if (isBehindLiveWindow(exoPlaybackException)) {
            clearResumePosition();
            initializePlayer();
            return;
        }
        updateResumePosition();
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPlayerStateChanged(boolean z, int i) {
        String str = "onStateChanged: playWhenReady=" + z + ", playbackState=";
        if (i == 1) {
            GeneratedOutlineSupport1.outline158(str, "idle");
            this.eventEmitter.idle();
            clearProgressMessageHandler();
            if (z) {
                return;
            }
            setKeepScreenOn(false);
        } else if (i == 2) {
            GeneratedOutlineSupport1.outline158(str, "buffering");
            onBuffering(true);
            clearProgressMessageHandler();
            setKeepScreenOn(this.preventsDisplaySleepDuringVideoPlayback);
        } else if (i != 3) {
            if (i != 4) {
                GeneratedOutlineSupport1.outline158(str, "unknown");
                return;
            }
            GeneratedOutlineSupport1.outline158(str, PlaybackStates.ENDED);
            this.eventEmitter.end();
            onStopPlayback();
            setKeepScreenOn(false);
        } else {
            GeneratedOutlineSupport1.outline158(str, "ready");
            this.eventEmitter.ready();
            onBuffering(false);
            startProgressHandler();
            videoLoaded();
            PlayerControlView playerControlView = this.playerControlView;
            if (playerControlView != null) {
                playerControlView.show();
            }
            setKeepScreenOn(this.preventsDisplaySleepDuringVideoPlayback);
        }
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPositionDiscontinuity(int i) {
        if (this.playerNeedsSource) {
            updateResumePosition();
        }
        if (i == 0 && this.player.getRepeatMode() == 1) {
            this.eventEmitter.end();
        }
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onRepeatModeChanged(int i) {
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onSeekProcessed() {
        this.eventEmitter.seek(this.player.getCurrentPosition(), this.seekTime);
        this.seekTime = C.TIME_UNSET;
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onShuffleModeEnabledChanged(boolean z) {
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onTimelineChanged(Timeline timeline, Object obj, int i) {
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
    }

    public void seekTo(long j) {
        SimpleExoPlayer simpleExoPlayer = this.player;
        if (simpleExoPlayer != null) {
            this.seekTime = j;
            simpleExoPlayer.seekTo(j);
        }
    }

    public void setBufferConfig(int i, int i2, int i3, int i4) {
        this.minBufferMs = i;
        this.maxBufferMs = i2;
        this.bufferForPlaybackMs = i3;
        this.bufferForPlaybackAfterRebufferMs = i4;
        releasePlayer();
        initializePlayer();
    }

    public void setControls(boolean z) {
        this.controls = z;
        if (this.player == null || this.exoPlayerView == null) {
            return;
        }
        if (z) {
            addPlayerControl();
            return;
        }
        int indexOfChild = indexOfChild(this.playerControlView);
        if (indexOfChild == -1) {
            return;
        }
        removeViewAt(indexOfChild);
    }

    public void setDisableFocus(boolean z) {
        this.disableFocus = z;
    }

    public void setDrmLicenseHeader(String[] strArr) {
        this.drmLicenseHeader = strArr;
    }

    public void setDrmLicenseUrl(String str) {
        this.drmLicenseUrl = str;
    }

    public void setDrmType(UUID uuid) {
        this.drmUUID = uuid;
    }

    public void setFullscreen(boolean z) {
        if (z == this.isFullscreen) {
            return;
        }
        this.isFullscreen = z;
        Activity currentActivity = this.themedReactContext.getCurrentActivity();
        if (currentActivity == null) {
            return;
        }
        View decorView = currentActivity.getWindow().getDecorView();
        if (this.isFullscreen) {
            int i = Util.SDK_INT >= 19 ? 4102 : 6;
            this.eventEmitter.fullscreenWillPresent();
            decorView.setSystemUiVisibility(i);
            this.eventEmitter.fullscreenDidPresent();
            return;
        }
        this.eventEmitter.fullscreenWillDismiss();
        decorView.setSystemUiVisibility(0);
        this.eventEmitter.fullscreenDidDismiss();
    }

    public void setHideShutterView(boolean z) {
        this.exoPlayerView.setHideShutterView(z);
    }

    @Override // android.view.View
    public void setId(int i) {
        super.setId(i);
        this.eventEmitter.setViewId(i);
    }

    public void setMaxBitRateModifier(int i) {
        this.maxBitRate = i;
        if (this.player != null) {
            DefaultTrackSelector defaultTrackSelector = this.trackSelector;
            DefaultTrackSelector.ParametersBuilder buildUponParameters = defaultTrackSelector.buildUponParameters();
            int i2 = this.maxBitRate;
            if (i2 == 0) {
                i2 = Integer.MAX_VALUE;
            }
            defaultTrackSelector.setParameters(buildUponParameters.setMaxVideoBitrate(i2));
        }
    }

    public void setMinLoadRetryCountModifier(int i) {
        this.minLoadRetryCount = i;
        releasePlayer();
        initializePlayer();
    }

    public void setMutedModifier(boolean z) {
        this.muted = z;
        this.audioVolume = z ? 0.0f : 1.0f;
        SimpleExoPlayer simpleExoPlayer = this.player;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.setVolume(this.audioVolume);
        }
    }

    public void setPausedModifier(boolean z) {
        this.isPaused = z;
        if (this.player != null) {
            if (!z) {
                startPlayback();
            } else {
                pausePlayback();
            }
        }
    }

    public void setPlayInBackground(boolean z) {
        this.playInBackground = z;
    }

    public void setPreventsDisplaySleepDuringVideoPlayback(boolean z) {
        this.preventsDisplaySleepDuringVideoPlayback = z;
    }

    public void setProgressUpdateInterval(float f) {
        this.mProgressUpdateInterval = f;
    }

    public void setRateModifier(float f) {
        this.rate = f;
        if (this.player != null) {
            this.player.setPlaybackParameters(new PlaybackParameters(this.rate, 1.0f));
        }
    }

    public void setRawSrc(Uri uri, String str) {
        if (uri != null) {
            boolean equals = uri.equals(this.srcUri);
            this.srcUri = uri;
            this.extension = str;
            this.mediaDataSourceFactory = buildDataSourceFactory(true);
            if (equals) {
                return;
            }
            reloadSource();
        }
    }

    public void setRepeatModifier(boolean z) {
        SimpleExoPlayer simpleExoPlayer = this.player;
        if (simpleExoPlayer != null) {
            if (z) {
                simpleExoPlayer.setRepeatMode(1);
            } else {
                simpleExoPlayer.setRepeatMode(0);
            }
        }
        this.repeat = z;
    }

    public void setReportBandwidth(boolean z) {
        this.mReportBandwidth = z;
    }

    public void setResizeModeModifier(int i) {
        this.exoPlayerView.setResizeMode(i);
    }

    public void setSelectedAudioTrack(String str, Dynamic dynamic) {
        this.audioTrackType = str;
        this.audioTrackValue = dynamic;
        setSelectedTrack(1, this.audioTrackType, this.audioTrackValue);
    }

    public void setSelectedTextTrack(String str, Dynamic dynamic) {
        this.textTrackType = str;
        this.textTrackValue = dynamic;
        setSelectedTrack(3, this.textTrackType, this.textTrackValue);
    }

    public void setSelectedTrack(int i, String str, Dynamic dynamic) {
        int trackRendererIndex;
        MappingTrackSelector.MappedTrackInfo currentMappedTrackInfo;
        int groupIndexForDefaultLocale;
        if (this.player == null || (trackRendererIndex = getTrackRendererIndex(i)) == -1 || (currentMappedTrackInfo = this.trackSelector.getCurrentMappedTrackInfo()) == null) {
            return;
        }
        TrackGroupArray trackGroups = currentMappedTrackInfo.getTrackGroups(trackRendererIndex);
        int[] iArr = {0};
        if (TextUtils.isEmpty(str)) {
            str = "default";
        }
        DefaultTrackSelector.Parameters mo7434build = this.trackSelector.getParameters().mo7431buildUpon().setRendererDisabled(trackRendererIndex, true).mo7434build();
        if (str.equals(FeatureState.DISABLED)) {
            this.trackSelector.setParameters(mo7434build);
            return;
        }
        if (str.equals("language")) {
            groupIndexForDefaultLocale = 0;
            while (groupIndexForDefaultLocale < trackGroups.length) {
                String str2 = trackGroups.get(groupIndexForDefaultLocale).getFormat(0).language;
                if (str2 != null && str2.equals(dynamic.asString())) {
                    break;
                }
                groupIndexForDefaultLocale++;
            }
            groupIndexForDefaultLocale = -1;
        } else if (str.equals("title")) {
            groupIndexForDefaultLocale = 0;
            while (groupIndexForDefaultLocale < trackGroups.length) {
                String str3 = trackGroups.get(groupIndexForDefaultLocale).getFormat(0).id;
                if (str3 != null && str3.equals(dynamic.asString())) {
                    break;
                }
                groupIndexForDefaultLocale++;
            }
            groupIndexForDefaultLocale = -1;
        } else if (str.equals("index")) {
            if (dynamic.asInt() < trackGroups.length) {
                groupIndexForDefaultLocale = dynamic.asInt();
            }
            groupIndexForDefaultLocale = -1;
        } else if (str.equals("resolution")) {
            int asInt = dynamic.asInt();
            int i2 = -1;
            for (int i3 = 0; i3 < trackGroups.length; i3++) {
                TrackGroup trackGroup = trackGroups.get(i3);
                int i4 = 0;
                while (true) {
                    if (i4 >= trackGroup.length) {
                        break;
                    } else if (trackGroup.getFormat(i4).height == asInt) {
                        iArr[0] = i4;
                        i2 = i3;
                        break;
                    } else {
                        i4++;
                    }
                }
            }
            groupIndexForDefaultLocale = i2;
        } else if (trackRendererIndex != 3 || Util.SDK_INT <= 18) {
            if (trackRendererIndex == 1) {
                groupIndexForDefaultLocale = getGroupIndexForDefaultLocale(trackGroups);
            }
            groupIndexForDefaultLocale = -1;
        } else {
            CaptioningManager captioningManager = (CaptioningManager) this.themedReactContext.getSystemService("captioning");
            if (captioningManager != null && captioningManager.isEnabled()) {
                groupIndexForDefaultLocale = getGroupIndexForDefaultLocale(trackGroups);
            }
            groupIndexForDefaultLocale = -1;
        }
        if (groupIndexForDefaultLocale == -1 && i == 2 && trackGroups.length != 0) {
            TrackGroup trackGroup2 = trackGroups.get(0);
            iArr = new int[trackGroup2.length];
            for (int i5 = 0; i5 < trackGroup2.length; i5++) {
                iArr[i5] = i5;
            }
            groupIndexForDefaultLocale = 0;
        }
        if (groupIndexForDefaultLocale == -1) {
            this.trackSelector.setParameters(mo7434build);
        } else {
            this.trackSelector.setParameters(this.trackSelector.getParameters().mo7431buildUpon().setRendererDisabled(trackRendererIndex, false).setSelectionOverride(trackRendererIndex, trackGroups, new DefaultTrackSelector.SelectionOverride(groupIndexForDefaultLocale, iArr)).mo7434build());
        }
    }

    public void setSelectedVideoTrack(String str, Dynamic dynamic) {
        this.videoTrackType = str;
        this.videoTrackValue = dynamic;
        setSelectedTrack(2, this.videoTrackType, this.videoTrackValue);
    }

    public void setSrc(Uri uri, String str, Map<String, String> map) {
        if (uri != null) {
            boolean equals = uri.equals(this.srcUri);
            this.srcUri = uri;
            this.extension = str;
            this.requestHeaders = map;
            this.mediaDataSourceFactory = DataSourceUtil.getDefaultDataSourceFactory(this.themedReactContext, this.bandwidthMeter, this.requestHeaders);
            if (equals) {
                return;
            }
            reloadSource();
        }
    }

    public void setTextTracks(ReadableArray readableArray) {
        this.textTracks = readableArray;
        reloadSource();
    }

    public void setUseTextureView(boolean z) {
        this.exoPlayerView.setUseTextureView(z && this.drmUUID == null);
    }

    public void setVolumeModifier(float f) {
        this.audioVolume = f;
        SimpleExoPlayer simpleExoPlayer = this.player;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.setVolume(this.audioVolume);
        }
    }
}
