package org.reactnative.facedetector;

import android.graphics.PointF;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.Landmark;
/* loaded from: classes5.dex */
public class FaceDetectorUtils {
    private static final String[] landmarkNames = {"bottomMouthPosition", "leftCheekPosition", "leftEarPosition", "leftEarTipPosition", "leftEyePosition", "leftMouthPosition", "noseBasePosition", "rightCheekPosition", "rightEarPosition", "rightEarTipPosition", "rightEyePosition", "rightMouthPosition"};

    public static WritableMap changeAnglesDirection(WritableMap writableMap) {
        writableMap.putDouble("rollAngle", ((-writableMap.getDouble("rollAngle")) + 360.0d) % 360.0d);
        writableMap.putDouble("yawAngle", ((-writableMap.getDouble("yawAngle")) + 360.0d) % 360.0d);
        return writableMap;
    }

    public static WritableMap mapFromPoint(PointF pointF, double d, double d2, int i, int i2, int i3, int i4) {
        WritableMap createMap = Arguments.createMap();
        Float valueOf = Float.valueOf(pointF.x);
        Float valueOf2 = Float.valueOf(pointF.y);
        float f = pointF.x;
        float f2 = i / 2;
        if (f < f2) {
            Float.valueOf(valueOf.floatValue() + (i3 / 2));
        } else if (f > f2) {
            Float.valueOf(valueOf.floatValue() - (i3 / 2));
        }
        float f3 = pointF.y;
        float f4 = i2 / 2;
        if (f3 < f4) {
            Float.valueOf(valueOf2.floatValue() + (i4 / 2));
        } else if (f3 > f4) {
            Float.valueOf(valueOf2.floatValue() - (i4 / 2));
        }
        createMap.putDouble(ReactProperties.HereMapMarker.X, pointF.x * d);
        createMap.putDouble(ReactProperties.HereMapMarker.Y, pointF.y * d2);
        return createMap;
    }

    public static WritableMap positionMirroredHorizontally(ReadableMap readableMap, int i, double d) {
        WritableMap createMap = Arguments.createMap();
        createMap.merge(readableMap);
        createMap.putDouble(ReactProperties.HereMapMarker.X, valueMirroredHorizontally(readableMap.getDouble(ReactProperties.HereMapMarker.X), i, d));
        return createMap;
    }

    public static WritableMap positionTranslatedHorizontally(ReadableMap readableMap, double d) {
        WritableMap createMap = Arguments.createMap();
        createMap.merge(readableMap);
        createMap.putDouble(ReactProperties.HereMapMarker.X, readableMap.getDouble(ReactProperties.HereMapMarker.X) + d);
        return createMap;
    }

    public static WritableMap rotateFaceX(WritableMap writableMap, int i, double d) {
        String[] strArr;
        ReadableMap mo6945getMap = writableMap.mo6945getMap("bounds");
        WritableMap positionTranslatedHorizontally = positionTranslatedHorizontally(positionMirroredHorizontally(mo6945getMap.mo6945getMap("origin"), i, d), -mo6945getMap.mo6945getMap("size").getDouble("width"));
        WritableMap createMap = Arguments.createMap();
        createMap.merge(mo6945getMap);
        createMap.putMap("origin", positionTranslatedHorizontally);
        for (String str : landmarkNames) {
            ReadableMap mo6945getMap2 = writableMap.hasKey(str) ? writableMap.mo6945getMap(str) : null;
            if (mo6945getMap2 != null) {
                writableMap.putMap(str, positionMirroredHorizontally(mo6945getMap2, i, d));
            }
        }
        writableMap.putMap("bounds", createMap);
        return writableMap;
    }

    public static WritableMap serializeFace(Face face) {
        return serializeFace(face, 1.0d, 1.0d, 0, 0, 0, 0);
    }

    public static double valueMirroredHorizontally(double d, int i, double d2) {
        return (i - (d / d2)) * d2;
    }

    public static WritableMap serializeFace(Face face, double d, double d2, int i, int i2, int i3, int i4) {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("faceID", face.getId());
        createMap.putDouble("rollAngle", face.getEulerZ());
        createMap.putDouble("yawAngle", face.getEulerY());
        if (face.getIsSmilingProbability() >= 0.0f) {
            createMap.putDouble("smilingProbability", face.getIsSmilingProbability());
        }
        if (face.getIsLeftEyeOpenProbability() >= 0.0f) {
            createMap.putDouble("leftEyeOpenProbability", face.getIsLeftEyeOpenProbability());
        }
        if (face.getIsRightEyeOpenProbability() >= 0.0f) {
            createMap.putDouble("rightEyeOpenProbability", face.getIsRightEyeOpenProbability());
        }
        for (Landmark landmark : face.getLandmarks()) {
            createMap.putMap(landmarkNames[landmark.getType()], mapFromPoint(landmark.getPosition(), d, d2, i, i2, i3, i4));
        }
        WritableMap createMap2 = Arguments.createMap();
        Float valueOf = Float.valueOf(face.getPosition().x);
        Float valueOf2 = Float.valueOf(face.getPosition().y);
        float f = i / 2;
        if (face.getPosition().x < f) {
            valueOf = Float.valueOf(valueOf.floatValue() + (i3 / 2));
        } else if (face.getPosition().x > f) {
            valueOf = Float.valueOf(valueOf.floatValue() - (i3 / 2));
        }
        float f2 = i2 / 2;
        if (face.getPosition().y < f2) {
            valueOf2 = Float.valueOf(valueOf2.floatValue() + (i4 / 2));
        } else if (face.getPosition().y > f2) {
            valueOf2 = Float.valueOf(valueOf2.floatValue() - (i4 / 2));
        }
        createMap2.putDouble(ReactProperties.HereMapMarker.X, valueOf.floatValue() * d);
        createMap2.putDouble(ReactProperties.HereMapMarker.Y, valueOf2.floatValue() * d2);
        WritableMap createMap3 = Arguments.createMap();
        createMap3.putDouble("width", face.getWidth() * d);
        createMap3.putDouble("height", face.getHeight() * d2);
        WritableMap createMap4 = Arguments.createMap();
        createMap4.putMap("origin", createMap2);
        createMap4.putMap("size", createMap3);
        createMap.putMap("bounds", createMap4);
        return createMap;
    }
}
