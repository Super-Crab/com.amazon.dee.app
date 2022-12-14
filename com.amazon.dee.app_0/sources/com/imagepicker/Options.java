package com.imagepicker;

import android.text.TextUtils;
import com.facebook.react.bridge.ReadableMap;
import com.lwansbrough.RCTCamera.RCTCameraModule;
/* loaded from: classes3.dex */
public class Options {
    int durationLimit;
    Boolean includeBase64;
    Boolean includeExtra;
    int maxHeight;
    int maxWidth;
    String mediaType;
    int quality;
    Boolean saveToPhotos;
    int selectionLimit;
    Boolean useFrontCamera;
    int videoQuality;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Options(ReadableMap readableMap) {
        this.videoQuality = 1;
        this.useFrontCamera = false;
        this.mediaType = readableMap.getString("mediaType");
        this.selectionLimit = readableMap.getInt("selectionLimit");
        this.includeBase64 = Boolean.valueOf(readableMap.getBoolean("includeBase64"));
        this.includeExtra = Boolean.valueOf(readableMap.getBoolean("includeExtra"));
        String string = readableMap.getString("videoQuality");
        if (!TextUtils.isEmpty(string) && !string.toLowerCase().equals(RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_HIGH)) {
            this.videoQuality = 0;
        }
        if (readableMap.getString("cameraType").equals("front")) {
            this.useFrontCamera = true;
        }
        this.quality = (int) (readableMap.getDouble("quality") * 100.0d);
        this.maxHeight = readableMap.getInt("maxHeight");
        this.maxWidth = readableMap.getInt("maxWidth");
        this.saveToPhotos = Boolean.valueOf(readableMap.getBoolean("saveToPhotos"));
        this.durationLimit = readableMap.getInt("durationLimit");
    }
}
