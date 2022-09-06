package com.amazon.dee.app.ui.web;

import android.content.Context;
import android.webkit.CookieManager;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.ui.web.JavaScriptPlayer;
import com.amazon.dee.app.util.Utils;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class AudioBridge extends JavaScriptBridge {
    private static final String KEY_AUDIO_URL = "url";
    private static final String TAG = "com.amazon.dee.app.ui.web.AudioBridge";
    Context context;
    JavaScriptPlayer javaScriptPlayer;
    private Map<String, JavaScriptBridgeMethod> mMethods;

    /* loaded from: classes12.dex */
    private class PlayAudioMethod implements JavaScriptBridgeMethod {
        private PlayAudioMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, final JavaScriptResponse javaScriptResponse) throws JSONException {
            try {
                JavaScriptPlayer.PlayerListener playerListener = new JavaScriptPlayer.PlayerListener() { // from class: com.amazon.dee.app.ui.web.AudioBridge.PlayAudioMethod.1
                    @Override // com.amazon.dee.app.ui.web.JavaScriptPlayer.PlayerListener
                    public void onComplete() {
                        AudioBridge.this.completeRequest(javaScriptResponse);
                    }

                    @Override // com.amazon.dee.app.ui.web.JavaScriptPlayer.PlayerListener
                    public void onError(String str) {
                        AudioBridge.this.javaScriptPlayer.removePlayerListener(this);
                        String str2 = AudioBridge.TAG;
                        Log.e(str2, "failed to play audio: " + str);
                        javaScriptResponse.setError(true);
                        javaScriptResponse.setErrorReason(str);
                    }
                };
                AudioBridge.this.downloadAudioToFile(new URL(jSONObject.getString("url")), "sound");
                AudioBridge.this.javaScriptPlayer.addPlayerListener(playerListener);
                AudioBridge.this.javaScriptPlayer.play(AudioBridge.this.context.getCacheDir().getPath() + "/sound");
            } catch (Exception e) {
                Log.e(AudioBridge.TAG, e, "failed to play audio", new Object[0]);
                javaScriptResponse.setError(true);
                javaScriptResponse.setErrorReason(e.getMessage());
                AudioBridge.this.completeRequest(javaScriptResponse);
            }
        }
    }

    public AudioBridge(Context context, JavaScriptPlayer javaScriptPlayer, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        super(javaScriptInjector, javaScriptResponseQueue);
        this.mMethods = new HashMap();
        this.context = context;
        this.javaScriptPlayer = javaScriptPlayer;
        this.mMethods.put("playAudio", new PlayAudioMethod());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadAudioToFile(URL url, String str) throws IOException {
        FileOutputStream fileOutputStream;
        BufferedInputStream bufferedInputStream = null;
        try {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setRequestProperty("Cookie", CookieManager.getInstance().getCookie(url.toString()));
            fileOutputStream = new FileOutputStream(this.context.getCacheDir().getPath() + "/" + str);
            try {
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(httpsURLConnection.getInputStream());
                try {
                    Utils.copyStream(bufferedInputStream2, fileOutputStream);
                    fileOutputStream.flush();
                    Utils.closeQuietly(bufferedInputStream2);
                    Utils.closeQuietly(fileOutputStream);
                } catch (Throwable th) {
                    bufferedInputStream = bufferedInputStream2;
                    th = th;
                    Utils.closeQuietly(bufferedInputStream);
                    Utils.closeQuietly(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
        }
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public Map<String, JavaScriptBridgeMethod> getExposedMethods() {
        return this.mMethods;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public final String getJavaScriptInterfaceName() {
        return "AudioBridge";
    }
}
