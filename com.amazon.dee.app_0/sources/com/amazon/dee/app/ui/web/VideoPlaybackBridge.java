package com.amazon.dee.app.ui.web;

import android.app.Activity;
import android.content.Intent;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.ui.video.VideoActivity;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class VideoPlaybackBridge extends JavaScriptBridge {
    public static final String KEY_VIDEO_PROGRESS = "progress";
    public static final String KEY_VIDEO_URL = "videoUrl";
    private static final String TAG = "com.amazon.dee.app.ui.web.VideoPlaybackBridge";
    Activity activity;
    private Map<String, JavaScriptBridgeMethod> mMethods;

    /* loaded from: classes12.dex */
    private class PlayVideoMethod implements JavaScriptBridgeMethod {
        private PlayVideoMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            Intent intent = new Intent(VideoPlaybackBridge.this.activity, VideoActivity.class);
            intent.putExtra(VideoActivity.INTENT_EXTRA_VIDEO_URL, VideoPlaybackBridge.this.getVideoURL(jSONObject));
            intent.putExtra(VideoActivity.INTENT_EXTRA_VIDEO_PROGRESS, VideoPlaybackBridge.this.getVideoProgress(jSONObject));
            VideoPlaybackBridge.this.activity.startActivityForResult(intent, 1);
            VideoPlaybackBridge.this.getJavaScriptInjector().inject("Backbone.trigger('onVideoPlaybackStarted');");
            VideoPlaybackBridge.this.completeRequest(javaScriptResponse);
        }
    }

    public VideoPlaybackBridge(Activity activity, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        super(javaScriptInjector, javaScriptResponseQueue);
        this.mMethods = new HashMap();
        this.activity = activity;
        this.mMethods.put("playVideo", new PlayVideoMethod());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getVideoProgress(JSONObject jSONObject) {
        return jSONObject.optInt("progress", 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getVideoURL(JSONObject jSONObject) throws JSONException {
        return jSONObject.getString(KEY_VIDEO_URL);
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public Map<String, JavaScriptBridgeMethod> getExposedMethods() {
        return this.mMethods;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public final String getJavaScriptInterfaceName() {
        return "VideoPlayback";
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1) {
            if (i2 == -1) {
                this.javaScriptInjector.inject("Backbone.trigger('onVideoPlaybackComplete');");
            } else if (i2 != 0) {
            } else {
                if (intent != null) {
                    int intExtra = intent.getIntExtra(VideoActivity.INTENT_EXTRA_VIDEO_PROGRESS, 0);
                    JavaScriptInjector javaScriptInjector = this.javaScriptInjector;
                    javaScriptInjector.inject("Backbone.trigger('onVideoPaused', {progress: " + intExtra + "});");
                    return;
                }
                Log.e(TAG, "VideoActivity returned cancelled, but included no data!");
            }
        }
    }
}
