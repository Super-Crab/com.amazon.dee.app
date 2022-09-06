package org.reactnative.facedetector;

import androidx.annotation.Nullable;
import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.reactnative.facedetector.tasks.FileFaceDetectionAsyncTask;
/* loaded from: classes5.dex */
public class FaceDetectorModule extends ReactContextBaseJavaModule {
    private static final String TAG = "RNFaceDetector";
    private static ReactApplicationContext mScopedContext;

    public FaceDetectorModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        mScopedContext = reactApplicationContext;
    }

    @ReactMethod
    public void detectFaces(ReadableMap readableMap, Promise promise) {
        new FileFaceDetectionAsyncTask(mScopedContext, readableMap, promise).execute(new Void[0]);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    @Nullable
    public Map<String, Object> getConstants() {
        return Collections.unmodifiableMap(new HashMap<String, Object>() { // from class: org.reactnative.facedetector.FaceDetectorModule.1
            {
                put(DriveModeMetrics.SubComponentType.MODE, getFaceDetectionModeConstants());
                put("Landmarks", getFaceDetectionLandmarksConstants());
                put("Classifications", getFaceDetectionClassificationsConstants());
            }

            private Map<String, Object> getFaceDetectionClassificationsConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() { // from class: org.reactnative.facedetector.FaceDetectorModule.1.2
                    {
                        put("all", Integer.valueOf(RNFaceDetector.ALL_CLASSIFICATIONS));
                        put("none", Integer.valueOf(RNFaceDetector.NO_CLASSIFICATIONS));
                    }
                });
            }

            private Map<String, Object> getFaceDetectionLandmarksConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() { // from class: org.reactnative.facedetector.FaceDetectorModule.1.3
                    {
                        put("all", Integer.valueOf(RNFaceDetector.ALL_LANDMARKS));
                        put("none", Integer.valueOf(RNFaceDetector.NO_LANDMARKS));
                    }
                });
            }

            private Map<String, Object> getFaceDetectionModeConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() { // from class: org.reactnative.facedetector.FaceDetectorModule.1.1
                    {
                        put("fast", Integer.valueOf(RNFaceDetector.FAST_MODE));
                        put("accurate", Integer.valueOf(RNFaceDetector.ACCURATE_MODE));
                    }
                });
            }
        });
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return TAG;
    }
}
