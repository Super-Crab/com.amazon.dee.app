package com.amazon.dee.app.ui.web;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.ArrayMap;
import com.amazon.dee.app.services.alexadevicebackground.BackgroundImage;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.ui.clouddrive.AlexaDeviceBackgroundImageActivity;
import com.google.gson.Gson;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class AlexaDeviceBackgroundImageBridge extends JavaScriptBridge {
    private static final String JS_INTERFACE_NAME = "BackgroundImageSelector";
    public static final String KEY_NODE_ID = "nodeId";
    private static final String TAG = "AlexaDeviceBackgroundImageBridge";
    private Activity activity;
    Gson gson;
    private final ArrayMap<String, JavaScriptBridgeMethod> mMethods;
    private JavaScriptResponse removeImageResponse;
    private JavaScriptResponse selectImageResponse;

    /* loaded from: classes12.dex */
    private class RemoveBackGroundImage implements JavaScriptBridgeMethod {
        private RemoveBackGroundImage() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            AlexaDeviceBackgroundImageBridge.this.removeImageResponse = javaScriptResponse;
            BackgroundImage backgroundImage = (BackgroundImage) AlexaDeviceBackgroundImageBridge.this.gson.fromJson(jSONObject.toString(), (Class<Object>) BackgroundImage.class);
            if (AlexaDeviceBackgroundImageBridge.this.activity == null) {
                String unused = AlexaDeviceBackgroundImageBridge.TAG;
                AlexaDeviceBackgroundImageBridge.this.removeImageResponse.setError(true);
                AlexaDeviceBackgroundImageBridge alexaDeviceBackgroundImageBridge = AlexaDeviceBackgroundImageBridge.this;
                alexaDeviceBackgroundImageBridge.completeRequest(alexaDeviceBackgroundImageBridge.removeImageResponse);
            } else if (backgroundImage == null || TextUtils.isEmpty(backgroundImage.getBackgroundImageID())) {
                Log.e(AlexaDeviceBackgroundImageBridge.TAG, "Could not remove photo with no node ID");
                AlexaDeviceBackgroundImageBridge.this.removeImageResponse.setError(true);
                AlexaDeviceBackgroundImageBridge alexaDeviceBackgroundImageBridge2 = AlexaDeviceBackgroundImageBridge.this;
                alexaDeviceBackgroundImageBridge2.completeRequest(alexaDeviceBackgroundImageBridge2.removeImageResponse);
            } else {
                Intent intent = new Intent(AlexaDeviceBackgroundImageBridge.this.activity, AlexaDeviceBackgroundImageActivity.class);
                intent.putExtra(AlexaDeviceBackgroundImageActivity.ACTION_MODE, 1);
                intent.putExtra(BackgroundImage.BACKGROUND_IMAGE_MODEL, backgroundImage);
                intent.addFlags(536870912);
                AlexaDeviceBackgroundImageBridge.this.activity.startActivityForResult(intent, 4);
            }
        }
    }

    /* loaded from: classes12.dex */
    private class SelectBackGroundImage implements JavaScriptBridgeMethod {
        private SelectBackGroundImage() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            AlexaDeviceBackgroundImageBridge.this.selectImageResponse = javaScriptResponse;
            Intent intent = new Intent(AlexaDeviceBackgroundImageBridge.this.activity, AlexaDeviceBackgroundImageActivity.class);
            intent.putExtra(AlexaDeviceBackgroundImageActivity.ACTION_MODE, 0);
            intent.addFlags(536870912);
            intent.putExtra(BackgroundImage.BACKGROUND_IMAGE_MODEL, (BackgroundImage) AlexaDeviceBackgroundImageBridge.this.gson.fromJson(jSONObject.toString(), (Class<Object>) BackgroundImage.class));
            AlexaDeviceBackgroundImageBridge.this.activity.startActivityForResult(intent, 3);
        }
    }

    public AlexaDeviceBackgroundImageBridge(Activity activity, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, Gson gson) {
        super(javaScriptInjector, javaScriptResponseQueue);
        this.mMethods = new ArrayMap<>();
        this.activity = activity;
        this.mMethods.put("selectBackgroundImage", new SelectBackGroundImage());
        this.mMethods.put("removeBackgroundImage", new RemoveBackGroundImage());
        this.gson = gson;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public Map<String, JavaScriptBridgeMethod> getExposedMethods() {
        return this.mMethods;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public String getJavaScriptInterfaceName() {
        return JS_INTERFACE_NAME;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 3) {
            if (i2 != -1) {
                return;
            }
            if (intent != null) {
                BackgroundImage backgroundImage = (BackgroundImage) intent.getParcelableExtra(BackgroundImage.BACKGROUND_IMAGE_MODEL);
                if (backgroundImage == null) {
                    Log.e(TAG, "Did not get node id for uploaded file");
                    JavaScriptResponse javaScriptResponse = this.selectImageResponse;
                    if (javaScriptResponse == null) {
                        return;
                    }
                    javaScriptResponse.setError(true);
                    completeRequest(this.selectImageResponse);
                    return;
                }
                try {
                    JSONObject jSONObject = new JSONObject(this.gson.toJson(backgroundImage));
                    if (this.selectImageResponse != null) {
                        this.selectImageResponse.setResponse(jSONObject);
                    }
                } catch (JSONException unused) {
                    this.selectImageResponse.setError(true);
                }
            } else {
                JavaScriptResponse javaScriptResponse2 = this.selectImageResponse;
                if (javaScriptResponse2 != null) {
                    javaScriptResponse2.setError(true);
                }
            }
            JavaScriptResponse javaScriptResponse3 = this.selectImageResponse;
            if (javaScriptResponse3 == null) {
                return;
            }
            completeRequest(javaScriptResponse3);
        } else if (i != 4 || i2 != -1) {
        } else {
            if (intent != null) {
                BackgroundImage backgroundImage2 = (BackgroundImage) intent.getParcelableExtra(BackgroundImage.BACKGROUND_IMAGE_MODEL);
                if (backgroundImage2 == null) {
                    Log.e(TAG, "Did not get node id for removed file");
                    JavaScriptResponse javaScriptResponse4 = this.removeImageResponse;
                    if (javaScriptResponse4 == null) {
                        return;
                    }
                    javaScriptResponse4.setError(true);
                    completeRequest(this.removeImageResponse);
                    return;
                }
                try {
                    JSONObject jSONObject2 = new JSONObject(this.gson.toJson(backgroundImage2));
                    if (this.removeImageResponse != null) {
                        this.removeImageResponse.setResponse(jSONObject2);
                    }
                } catch (JSONException unused2) {
                    JavaScriptResponse javaScriptResponse5 = this.removeImageResponse;
                    if (javaScriptResponse5 != null) {
                        javaScriptResponse5.setError(true);
                    }
                }
                JavaScriptResponse javaScriptResponse6 = this.removeImageResponse;
                if (javaScriptResponse6 == null) {
                    return;
                }
                completeRequest(javaScriptResponse6);
                return;
            }
            JavaScriptResponse javaScriptResponse7 = this.removeImageResponse;
            if (javaScriptResponse7 == null) {
                return;
            }
            javaScriptResponse7.setError(true);
            completeRequest(this.removeImageResponse);
        }
    }
}
