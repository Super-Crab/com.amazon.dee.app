package com.brentvatne.exoplayer;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
/* loaded from: classes.dex */
public class ReactExoplayerViewManager extends ViewGroupManager<ReactExoplayerView> {
    private static final String PROP_BUFFER_CONFIG = "bufferConfig";
    private static final String PROP_BUFFER_CONFIG_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS = "bufferForPlaybackAfterRebufferMs";
    private static final String PROP_BUFFER_CONFIG_BUFFER_FOR_PLAYBACK_MS = "bufferForPlaybackMs";
    private static final String PROP_BUFFER_CONFIG_MAX_BUFFER_MS = "maxBufferMs";
    private static final String PROP_BUFFER_CONFIG_MIN_BUFFER_MS = "minBufferMs";
    private static final String PROP_CONTROLS = "controls";
    private static final String PROP_DISABLE_FOCUS = "disableFocus";
    private static final String PROP_DRM = "drm";
    private static final String PROP_DRM_HEADERS = "headers";
    private static final String PROP_DRM_LICENSESERVER = "licenseServer";
    private static final String PROP_DRM_TYPE = "type";
    private static final String PROP_FULLSCREEN = "fullscreen";
    private static final String PROP_HIDE_SHUTTER_VIEW = "hideShutterView";
    private static final String PROP_MAXIMUM_BIT_RATE = "maxBitRate";
    private static final String PROP_MIN_LOAD_RETRY_COUNT = "minLoadRetryCount";
    private static final String PROP_MUTED = "muted";
    private static final String PROP_PAUSED = "paused";
    private static final String PROP_PLAY_IN_BACKGROUND = "playInBackground";
    private static final String PROP_PREVENTS_DISPLAY_SLEEP_DURING_VIDEO_PLAYBACK = "preventsDisplaySleepDuringVideoPlayback";
    private static final String PROP_PROGRESS_UPDATE_INTERVAL = "progressUpdateInterval";
    private static final String PROP_RATE = "rate";
    private static final String PROP_REPEAT = "repeat";
    private static final String PROP_REPORT_BANDWIDTH = "reportBandwidth";
    private static final String PROP_RESIZE_MODE = "resizeMode";
    private static final String PROP_SEEK = "seek";
    private static final String PROP_SELECTED_AUDIO_TRACK = "selectedAudioTrack";
    private static final String PROP_SELECTED_AUDIO_TRACK_TYPE = "type";
    private static final String PROP_SELECTED_AUDIO_TRACK_VALUE = "value";
    private static final String PROP_SELECTED_TEXT_TRACK = "selectedTextTrack";
    private static final String PROP_SELECTED_TEXT_TRACK_TYPE = "type";
    private static final String PROP_SELECTED_TEXT_TRACK_VALUE = "value";
    private static final String PROP_SELECTED_VIDEO_TRACK = "selectedVideoTrack";
    private static final String PROP_SELECTED_VIDEO_TRACK_TYPE = "type";
    private static final String PROP_SELECTED_VIDEO_TRACK_VALUE = "value";
    private static final String PROP_SRC = "src";
    private static final String PROP_SRC_HEADERS = "requestHeaders";
    private static final String PROP_SRC_TYPE = "type";
    private static final String PROP_SRC_URI = "uri";
    private static final String PROP_TEXT_TRACKS = "textTracks";
    private static final String PROP_USE_TEXTURE_VIEW = "useTextureView";
    private static final String PROP_VOLUME = "volume";
    private static final String REACT_CLASS = "RCTVideo";
    private ReactExoplayerConfig config;

    public ReactExoplayerViewManager(ReactExoplayerConfig reactExoplayerConfig) {
        this.config = reactExoplayerConfig;
    }

    private int convertToIntDef(String str) {
        if (!TextUtils.isEmpty(str)) {
            return ResizeMode.toResizeMode(Integer.parseInt(str));
        }
        return 0;
    }

    private boolean startsWithValidScheme(String str) {
        return str.startsWith("http://") || str.startsWith("https://") || str.startsWith("content://") || str.startsWith("file://") || str.startsWith("asset://");
    }

    public static Map<String, String> toStringMap(@Nullable ReadableMap readableMap) {
        if (readableMap == null) {
            return null;
        }
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        if (!keySetIterator.hasNextKey()) {
            return null;
        }
        HashMap hashMap = new HashMap();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            hashMap.put(nextKey, readableMap.getString(nextKey));
        }
        return hashMap;
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        String[] strArr;
        MapBuilder.Builder builder = MapBuilder.builder();
        for (String str : VideoEventEmitter.Events) {
            builder.put(str, MapBuilder.of("registrationName", str));
        }
        return builder.build();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Object> getExportedViewConstants() {
        return MapBuilder.of("ScaleNone", Integer.toString(0), "ScaleAspectFit", Integer.toString(0), "ScaleToFill", Integer.toString(3), "ScaleAspectFill", Integer.toString(4));
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @ReactProp(name = PROP_MIN_LOAD_RETRY_COUNT)
    public void minLoadRetryCount(ReactExoplayerView reactExoplayerView, int i) {
        reactExoplayerView.setMinLoadRetryCountModifier(i);
    }

    @ReactProp(name = PROP_BUFFER_CONFIG)
    public void setBufferConfig(ReactExoplayerView reactExoplayerView, @Nullable ReadableMap readableMap) {
        if (readableMap != null) {
            int i = 50000;
            int i2 = readableMap.hasKey(PROP_BUFFER_CONFIG_MIN_BUFFER_MS) ? readableMap.getInt(PROP_BUFFER_CONFIG_MIN_BUFFER_MS) : 50000;
            if (readableMap.hasKey(PROP_BUFFER_CONFIG_MAX_BUFFER_MS)) {
                i = readableMap.getInt(PROP_BUFFER_CONFIG_MAX_BUFFER_MS);
            }
            reactExoplayerView.setBufferConfig(i2, i, readableMap.hasKey(PROP_BUFFER_CONFIG_BUFFER_FOR_PLAYBACK_MS) ? readableMap.getInt(PROP_BUFFER_CONFIG_BUFFER_FOR_PLAYBACK_MS) : 2500, readableMap.hasKey(PROP_BUFFER_CONFIG_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS) ? readableMap.getInt(PROP_BUFFER_CONFIG_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS) : 5000);
        }
    }

    @ReactProp(defaultBoolean = false, name = PROP_CONTROLS)
    public void setControls(ReactExoplayerView reactExoplayerView, boolean z) {
        reactExoplayerView.setControls(z);
    }

    @ReactProp(name = PROP_DRM)
    public void setDRM(ReactExoplayerView reactExoplayerView, @Nullable ReadableMap readableMap) {
        if (readableMap == null || !readableMap.hasKey("type")) {
            return;
        }
        ReadableMap readableMap2 = null;
        String string = readableMap.hasKey("type") ? readableMap.getString("type") : null;
        String string2 = readableMap.hasKey(PROP_DRM_LICENSESERVER) ? readableMap.getString(PROP_DRM_LICENSESERVER) : null;
        if (readableMap.hasKey("headers")) {
            readableMap2 = readableMap.mo6945getMap("headers");
        }
        if (string == null || string2 == null || Util.getDrmUuid(string) == null) {
            return;
        }
        reactExoplayerView.setDrmType(Util.getDrmUuid(string));
        reactExoplayerView.setDrmLicenseUrl(string2);
        if (readableMap2 != null) {
            ArrayList arrayList = new ArrayList();
            ReadableMapKeySetIterator keySetIterator = readableMap2.keySetIterator();
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                arrayList.add(nextKey);
                arrayList.add(readableMap2.getString(nextKey));
            }
            reactExoplayerView.setDrmLicenseHeader((String[]) arrayList.toArray(new String[0]));
        }
        reactExoplayerView.setUseTextureView(false);
    }

    @ReactProp(defaultBoolean = false, name = PROP_DISABLE_FOCUS)
    public void setDisableFocus(ReactExoplayerView reactExoplayerView, boolean z) {
        reactExoplayerView.setDisableFocus(z);
    }

    @ReactProp(defaultBoolean = false, name = PROP_FULLSCREEN)
    public void setFullscreen(ReactExoplayerView reactExoplayerView, boolean z) {
        reactExoplayerView.setFullscreen(z);
    }

    @ReactProp(defaultBoolean = false, name = PROP_HIDE_SHUTTER_VIEW)
    public void setHideShutterView(ReactExoplayerView reactExoplayerView, boolean z) {
        reactExoplayerView.setHideShutterView(z);
    }

    @ReactProp(name = PROP_MAXIMUM_BIT_RATE)
    public void setMaxBitRate(ReactExoplayerView reactExoplayerView, int i) {
        reactExoplayerView.setMaxBitRateModifier(i);
    }

    @ReactProp(defaultBoolean = false, name = PROP_MUTED)
    public void setMuted(ReactExoplayerView reactExoplayerView, boolean z) {
        reactExoplayerView.setMutedModifier(z);
    }

    @ReactProp(defaultBoolean = false, name = "paused")
    public void setPaused(ReactExoplayerView reactExoplayerView, boolean z) {
        reactExoplayerView.setPausedModifier(z);
    }

    @ReactProp(defaultBoolean = false, name = PROP_PLAY_IN_BACKGROUND)
    public void setPlayInBackground(ReactExoplayerView reactExoplayerView, boolean z) {
        reactExoplayerView.setPlayInBackground(z);
    }

    @ReactProp(defaultBoolean = false, name = PROP_PREVENTS_DISPLAY_SLEEP_DURING_VIDEO_PLAYBACK)
    public void setPreventsDisplaySleepDuringVideoPlayback(ReactExoplayerView reactExoplayerView, boolean z) {
        reactExoplayerView.setPreventsDisplaySleepDuringVideoPlayback(z);
    }

    @ReactProp(defaultFloat = 250.0f, name = PROP_PROGRESS_UPDATE_INTERVAL)
    public void setProgressUpdateInterval(ReactExoplayerView reactExoplayerView, float f) {
        reactExoplayerView.setProgressUpdateInterval(f);
    }

    @ReactProp(name = PROP_TEXT_TRACKS)
    public void setPropTextTracks(ReactExoplayerView reactExoplayerView, @Nullable ReadableArray readableArray) {
        reactExoplayerView.setTextTracks(readableArray);
    }

    @ReactProp(name = PROP_RATE)
    public void setRate(ReactExoplayerView reactExoplayerView, float f) {
        reactExoplayerView.setRateModifier(f);
    }

    @ReactProp(defaultBoolean = false, name = "repeat")
    public void setRepeat(ReactExoplayerView reactExoplayerView, boolean z) {
        reactExoplayerView.setRepeatModifier(z);
    }

    @ReactProp(defaultBoolean = false, name = PROP_REPORT_BANDWIDTH)
    public void setReportBandwidth(ReactExoplayerView reactExoplayerView, boolean z) {
        reactExoplayerView.setReportBandwidth(z);
    }

    @ReactProp(name = "resizeMode")
    public void setResizeMode(ReactExoplayerView reactExoplayerView, String str) {
        reactExoplayerView.setResizeModeModifier(convertToIntDef(str));
    }

    @ReactProp(name = "seek")
    public void setSeek(ReactExoplayerView reactExoplayerView, float f) {
        reactExoplayerView.seekTo(Math.round(f * 1000.0f));
    }

    @ReactProp(name = PROP_SELECTED_AUDIO_TRACK)
    public void setSelectedAudioTrack(ReactExoplayerView reactExoplayerView, @Nullable ReadableMap readableMap) {
        Dynamic dynamic;
        String str = null;
        if (readableMap != null) {
            String string = readableMap.hasKey("type") ? readableMap.getString("type") : null;
            if (readableMap.hasKey("value")) {
                str = readableMap.getDynamic("value");
            }
            dynamic = str;
            str = string;
        } else {
            dynamic = null;
        }
        reactExoplayerView.setSelectedAudioTrack(str, dynamic);
    }

    @ReactProp(name = PROP_SELECTED_TEXT_TRACK)
    public void setSelectedTextTrack(ReactExoplayerView reactExoplayerView, @Nullable ReadableMap readableMap) {
        Dynamic dynamic;
        String str = null;
        if (readableMap != null) {
            String string = readableMap.hasKey("type") ? readableMap.getString("type") : null;
            if (readableMap.hasKey("value")) {
                str = readableMap.getDynamic("value");
            }
            dynamic = str;
            str = string;
        } else {
            dynamic = null;
        }
        reactExoplayerView.setSelectedTextTrack(str, dynamic);
    }

    @ReactProp(name = PROP_SELECTED_VIDEO_TRACK)
    public void setSelectedVideoTrack(ReactExoplayerView reactExoplayerView, @Nullable ReadableMap readableMap) {
        Dynamic dynamic;
        String str = null;
        if (readableMap != null) {
            String string = readableMap.hasKey("type") ? readableMap.getString("type") : null;
            if (readableMap.hasKey("value")) {
                str = readableMap.getDynamic("value");
            }
            dynamic = str;
            str = string;
        } else {
            dynamic = null;
        }
        reactExoplayerView.setSelectedVideoTrack(str, dynamic);
    }

    @ReactProp(name = PROP_SRC)
    public void setSrc(ReactExoplayerView reactExoplayerView, @Nullable ReadableMap readableMap) {
        Uri buildRawResourceUri;
        Context applicationContext = reactExoplayerView.getContext().getApplicationContext();
        Map<String, String> map = null;
        String string = readableMap.hasKey("uri") ? readableMap.getString("uri") : null;
        String string2 = readableMap.hasKey("type") ? readableMap.getString("type") : null;
        if (readableMap.hasKey(PROP_SRC_HEADERS)) {
            map = toStringMap(readableMap.mo6945getMap(PROP_SRC_HEADERS));
        }
        if (TextUtils.isEmpty(string)) {
            reactExoplayerView.clearSrc();
        } else if (startsWithValidScheme(string)) {
            Uri parse = Uri.parse(string);
            if (parse == null) {
                return;
            }
            reactExoplayerView.setSrc(parse, string2, map);
        } else {
            int identifier = applicationContext.getResources().getIdentifier(string, "drawable", applicationContext.getPackageName());
            if (identifier == 0) {
                identifier = applicationContext.getResources().getIdentifier(string, "raw", applicationContext.getPackageName());
            }
            if (identifier <= 0 || (buildRawResourceUri = RawResourceDataSource.buildRawResourceUri(identifier)) == null) {
                return;
            }
            reactExoplayerView.setRawSrc(buildRawResourceUri, string2);
        }
    }

    @ReactProp(defaultBoolean = true, name = PROP_USE_TEXTURE_VIEW)
    public void setUseTextureView(ReactExoplayerView reactExoplayerView, boolean z) {
        reactExoplayerView.setUseTextureView(z);
    }

    @ReactProp(defaultFloat = 1.0f, name = "volume")
    public void setVolume(ReactExoplayerView reactExoplayerView, float f) {
        reactExoplayerView.setVolumeModifier(f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    /* renamed from: createViewInstance  reason: collision with other method in class */
    public ReactExoplayerView mo12880createViewInstance(ThemedReactContext themedReactContext) {
        return new ReactExoplayerView(themedReactContext, this.config);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(ReactExoplayerView reactExoplayerView) {
        reactExoplayerView.cleanUpResources();
    }
}
