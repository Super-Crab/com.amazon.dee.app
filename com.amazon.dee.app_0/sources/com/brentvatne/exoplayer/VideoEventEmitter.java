package com.brentvatne.exoplayer;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.emsg.EventMessage;
import com.google.android.exoplayer2.metadata.id3.Id3Frame;
import com.google.android.exoplayer2.metadata.id3.TextInformationFrame;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class VideoEventEmitter {
    private static final String EVENT_PROP_AUDIO_TRACKS = "audioTracks";
    private static final String EVENT_PROP_BITRATE = "bitrate";
    private static final String EVENT_PROP_CURRENT_PLAYBACK_TIME = "currentPlaybackTime";
    private static final String EVENT_PROP_CURRENT_TIME = "currentTime";
    private static final String EVENT_PROP_DURATION = "duration";
    private static final String EVENT_PROP_ERROR = "error";
    private static final String EVENT_PROP_ERROR_EXCEPTION = "errorException";
    private static final String EVENT_PROP_ERROR_STRING = "errorString";
    private static final String EVENT_PROP_FAST_FORWARD = "canPlayFastForward";
    private static final String EVENT_PROP_HAS_AUDIO_FOCUS = "hasAudioFocus";
    private static final String EVENT_PROP_HEIGHT = "height";
    private static final String EVENT_PROP_IS_BUFFERING = "isBuffering";
    private static final String EVENT_PROP_NATURAL_SIZE = "naturalSize";
    private static final String EVENT_PROP_ORIENTATION = "orientation";
    private static final String EVENT_PROP_PLAYABLE_DURATION = "playableDuration";
    private static final String EVENT_PROP_PLAYBACK_RATE = "playbackRate";
    private static final String EVENT_PROP_REVERSE = "canPlayReverse";
    private static final String EVENT_PROP_SEEKABLE_DURATION = "seekableDuration";
    private static final String EVENT_PROP_SEEK_TIME = "seekTime";
    private static final String EVENT_PROP_SLOW_FORWARD = "canPlaySlowForward";
    private static final String EVENT_PROP_SLOW_REVERSE = "canPlaySlowReverse";
    private static final String EVENT_PROP_STEP_BACKWARD = "canStepBackward";
    private static final String EVENT_PROP_STEP_FORWARD = "canStepForward";
    private static final String EVENT_PROP_TEXT_TRACKS = "textTracks";
    private static final String EVENT_PROP_TIMED_METADATA = "metadata";
    private static final String EVENT_PROP_TRACK_ID = "trackId";
    private static final String EVENT_PROP_VIDEO_TRACKS = "videoTracks";
    private static final String EVENT_PROP_WIDTH = "width";
    private final RCTEventEmitter eventEmitter;
    private int viewId = -1;
    private static final String EVENT_LOAD_START = "onVideoLoadStart";
    private static final String EVENT_LOAD = "onVideoLoad";
    private static final String EVENT_ERROR = "onVideoError";
    private static final String EVENT_PROGRESS = "onVideoProgress";
    private static final String EVENT_SEEK = "onVideoSeek";
    private static final String EVENT_END = "onVideoEnd";
    private static final String EVENT_FULLSCREEN_WILL_PRESENT = "onVideoFullscreenPlayerWillPresent";
    private static final String EVENT_FULLSCREEN_DID_PRESENT = "onVideoFullscreenPlayerDidPresent";
    private static final String EVENT_FULLSCREEN_WILL_DISMISS = "onVideoFullscreenPlayerWillDismiss";
    private static final String EVENT_FULLSCREEN_DID_DISMISS = "onVideoFullscreenPlayerDidDismiss";
    private static final String EVENT_STALLED = "onPlaybackStalled";
    private static final String EVENT_RESUME = "onPlaybackResume";
    private static final String EVENT_READY = "onReadyForDisplay";
    private static final String EVENT_BUFFER = "onVideoBuffer";
    private static final String EVENT_IDLE = "onVideoIdle";
    private static final String EVENT_TIMED_METADATA = "onTimedMetadata";
    private static final String EVENT_AUDIO_BECOMING_NOISY = "onVideoAudioBecomingNoisy";
    private static final String EVENT_AUDIO_FOCUS_CHANGE = "onAudioFocusChanged";
    private static final String EVENT_PLAYBACK_RATE_CHANGE = "onPlaybackRateChange";
    private static final String EVENT_BANDWIDTH = "onVideoBandwidthUpdate";
    static final String[] Events = {EVENT_LOAD_START, EVENT_LOAD, EVENT_ERROR, EVENT_PROGRESS, EVENT_SEEK, EVENT_END, EVENT_FULLSCREEN_WILL_PRESENT, EVENT_FULLSCREEN_DID_PRESENT, EVENT_FULLSCREEN_WILL_DISMISS, EVENT_FULLSCREEN_DID_DISMISS, EVENT_STALLED, EVENT_RESUME, EVENT_READY, EVENT_BUFFER, EVENT_IDLE, EVENT_TIMED_METADATA, EVENT_AUDIO_BECOMING_NOISY, EVENT_AUDIO_FOCUS_CHANGE, EVENT_PLAYBACK_RATE_CHANGE, EVENT_BANDWIDTH};

    /* JADX INFO: Access modifiers changed from: package-private */
    public VideoEventEmitter(ReactContext reactContext) {
        this.eventEmitter = (RCTEventEmitter) reactContext.getJSModule(RCTEventEmitter.class);
    }

    private void receiveEvent(String str, WritableMap writableMap) {
        this.eventEmitter.receiveEvent(this.viewId, str, writableMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void audioBecomingNoisy() {
        receiveEvent(EVENT_AUDIO_BECOMING_NOISY, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void audioFocusChanged(boolean z) {
        WritableMap createMap = Arguments.createMap();
        createMap.putBoolean(EVENT_PROP_HAS_AUDIO_FOCUS, z);
        receiveEvent(EVENT_AUDIO_FOCUS_CHANGE, createMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void bandwidthReport(double d, int i, int i2, String str) {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble(EVENT_PROP_BITRATE, d);
        createMap.putInt("width", i2);
        createMap.putInt("height", i);
        createMap.putString(EVENT_PROP_TRACK_ID, str);
        receiveEvent(EVENT_BANDWIDTH, createMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void buffering(boolean z) {
        WritableMap createMap = Arguments.createMap();
        createMap.putBoolean(EVENT_PROP_IS_BUFFERING, z);
        receiveEvent(EVENT_BUFFER, createMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void end() {
        receiveEvent(EVENT_END, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void error(String str, Exception exc) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString(EVENT_PROP_ERROR_STRING, str);
        createMap.putString(EVENT_PROP_ERROR_EXCEPTION, exc.toString());
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putMap("error", createMap);
        receiveEvent(EVENT_ERROR, createMap2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void fullscreenDidDismiss() {
        receiveEvent(EVENT_FULLSCREEN_DID_DISMISS, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void fullscreenDidPresent() {
        receiveEvent(EVENT_FULLSCREEN_DID_PRESENT, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void fullscreenWillDismiss() {
        receiveEvent(EVENT_FULLSCREEN_WILL_DISMISS, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void fullscreenWillPresent() {
        receiveEvent(EVENT_FULLSCREEN_WILL_PRESENT, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void idle() {
        receiveEvent(EVENT_IDLE, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void load(double d, double d2, int i, int i2, WritableArray writableArray, WritableArray writableArray2, WritableArray writableArray3, String str) {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("duration", d / 1000.0d);
        createMap.putDouble(EVENT_PROP_CURRENT_TIME, d2 / 1000.0d);
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putInt("width", i);
        createMap2.putInt("height", i2);
        if (i > i2) {
            createMap2.putString(EVENT_PROP_ORIENTATION, "landscape");
        } else {
            createMap2.putString(EVENT_PROP_ORIENTATION, "portrait");
        }
        createMap.putMap(EVENT_PROP_NATURAL_SIZE, createMap2);
        createMap.putString(EVENT_PROP_TRACK_ID, str);
        createMap.putArray(EVENT_PROP_VIDEO_TRACKS, writableArray3);
        createMap.putArray(EVENT_PROP_AUDIO_TRACKS, writableArray);
        createMap.putArray(EVENT_PROP_TEXT_TRACKS, writableArray2);
        createMap.putBoolean(EVENT_PROP_FAST_FORWARD, true);
        createMap.putBoolean(EVENT_PROP_SLOW_FORWARD, true);
        createMap.putBoolean(EVENT_PROP_SLOW_REVERSE, true);
        createMap.putBoolean(EVENT_PROP_REVERSE, true);
        createMap.putBoolean(EVENT_PROP_FAST_FORWARD, true);
        createMap.putBoolean(EVENT_PROP_STEP_BACKWARD, true);
        createMap.putBoolean(EVENT_PROP_STEP_FORWARD, true);
        receiveEvent(EVENT_LOAD, createMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void loadStart() {
        receiveEvent(EVENT_LOAD_START, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void playbackRateChange(float f) {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble(EVENT_PROP_PLAYBACK_RATE, f);
        receiveEvent(EVENT_PLAYBACK_RATE_CHANGE, createMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void progressChanged(double d, double d2, double d3, double d4) {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble(EVENT_PROP_CURRENT_TIME, d / 1000.0d);
        createMap.putDouble(EVENT_PROP_PLAYABLE_DURATION, d2 / 1000.0d);
        createMap.putDouble(EVENT_PROP_SEEKABLE_DURATION, d3 / 1000.0d);
        createMap.putDouble(EVENT_PROP_CURRENT_PLAYBACK_TIME, d4);
        receiveEvent(EVENT_PROGRESS, createMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void ready() {
        receiveEvent(EVENT_READY, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void seek(long j, long j2) {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble(EVENT_PROP_CURRENT_TIME, j / 1000.0d);
        createMap.putDouble(EVENT_PROP_SEEK_TIME, j2 / 1000.0d);
        receiveEvent(EVENT_SEEK, createMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setViewId(int i) {
        this.viewId = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void timedMetadata(Metadata metadata) {
        WritableArray createArray = Arguments.createArray();
        for (int i = 0; i < metadata.length(); i++) {
            Metadata.Entry entry = metadata.get(i);
            if (entry instanceof Id3Frame) {
                Id3Frame id3Frame = (Id3Frame) entry;
                String str = id3Frame instanceof TextInformationFrame ? ((TextInformationFrame) id3Frame).value : "";
                String str2 = id3Frame.id;
                WritableMap createMap = Arguments.createMap();
                createMap.putString("identifier", str2);
                createMap.putString("value", str);
                createArray.pushMap(createMap);
            } else if (entry instanceof EventMessage) {
                EventMessage eventMessage = (EventMessage) entry;
                WritableMap createMap2 = Arguments.createMap();
                createMap2.putString("identifier", eventMessage.schemeIdUri);
                createMap2.putString("value", eventMessage.value);
                createArray.pushMap(createMap2);
            }
        }
        WritableMap createMap3 = Arguments.createMap();
        createMap3.putArray("metadata", createArray);
        receiveEvent(EVENT_TIMED_METADATA, createMap3);
    }
}
