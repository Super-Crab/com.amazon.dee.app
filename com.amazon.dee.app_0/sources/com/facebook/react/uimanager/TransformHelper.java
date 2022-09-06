package com.facebook.react.uimanager;

import com.amazon.alexa.accessorykit.ModelTransformer;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
/* loaded from: classes2.dex */
public class TransformHelper {
    private static ThreadLocal<double[]> sHelperMatrix = new ThreadLocal<double[]>() { // from class: com.facebook.react.uimanager.TransformHelper.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public double[] initialValue() {
            return new double[16];
        }
    };

    private static double convertToRadians(ReadableMap readableMap, String str) {
        double d;
        boolean z = true;
        if (readableMap.getType(str) == ReadableType.String) {
            String string = readableMap.getString(str);
            if (string.endsWith("rad")) {
                string = GeneratedOutlineSupport1.outline50(string, -3, 0);
            } else if (string.endsWith("deg")) {
                string = GeneratedOutlineSupport1.outline50(string, -3, 0);
                z = false;
            }
            d = Float.parseFloat(string);
        } else {
            d = readableMap.getDouble(str);
        }
        return z ? d : MatrixMathHelper.degreesToRadians(d);
    }

    public static void processTransform(ReadableArray readableArray, double[] dArr) {
        double[] dArr2 = sHelperMatrix.get();
        MatrixMathHelper.resetIdentityMatrix(dArr);
        if (readableArray.size() == 16 && readableArray.getType(0) == ReadableType.Number) {
            for (int i = 0; i < readableArray.size(); i++) {
                dArr[i] = readableArray.getDouble(i);
            }
            return;
        }
        int size = readableArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            ReadableMap mo6944getMap = readableArray.mo6944getMap(i2);
            String nextKey = mo6944getMap.keySetIterator().nextKey();
            MatrixMathHelper.resetIdentityMatrix(dArr2);
            if ("matrix".equals(nextKey)) {
                ReadableArray array = mo6944getMap.getArray(nextKey);
                for (int i3 = 0; i3 < 16; i3++) {
                    dArr2[i3] = array.getDouble(i3);
                }
            } else if ("perspective".equals(nextKey)) {
                MatrixMathHelper.applyPerspective(dArr2, mo6944getMap.getDouble(nextKey));
            } else if ("rotateX".equals(nextKey)) {
                MatrixMathHelper.applyRotateX(dArr2, convertToRadians(mo6944getMap, nextKey));
            } else if ("rotateY".equals(nextKey)) {
                MatrixMathHelper.applyRotateY(dArr2, convertToRadians(mo6944getMap, nextKey));
            } else if (!"rotate".equals(nextKey) && !"rotateZ".equals(nextKey)) {
                if (ModelTransformer.KEY_BATTERY_SCALE.equals(nextKey)) {
                    double d = mo6944getMap.getDouble(nextKey);
                    MatrixMathHelper.applyScaleX(dArr2, d);
                    MatrixMathHelper.applyScaleY(dArr2, d);
                } else if (ViewProps.SCALE_X.equals(nextKey)) {
                    MatrixMathHelper.applyScaleX(dArr2, mo6944getMap.getDouble(nextKey));
                } else if (ViewProps.SCALE_Y.equals(nextKey)) {
                    MatrixMathHelper.applyScaleY(dArr2, mo6944getMap.getDouble(nextKey));
                } else {
                    boolean equals = "translate".equals(nextKey);
                    double d2 = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
                    if (equals) {
                        ReadableArray array2 = mo6944getMap.getArray(nextKey);
                        double d3 = array2.getDouble(0);
                        double d4 = array2.getDouble(1);
                        if (array2.size() > 2) {
                            d2 = array2.getDouble(2);
                        }
                        MatrixMathHelper.applyTranslate3D(dArr2, d3, d4, d2);
                    } else if (ViewProps.TRANSLATE_X.equals(nextKey)) {
                        MatrixMathHelper.applyTranslate2D(dArr2, mo6944getMap.getDouble(nextKey), FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
                    } else if (ViewProps.TRANSLATE_Y.equals(nextKey)) {
                        MatrixMathHelper.applyTranslate2D(dArr2, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, mo6944getMap.getDouble(nextKey));
                    } else if ("skewX".equals(nextKey)) {
                        MatrixMathHelper.applySkewX(dArr2, convertToRadians(mo6944getMap, nextKey));
                    } else if ("skewY".equals(nextKey)) {
                        MatrixMathHelper.applySkewY(dArr2, convertToRadians(mo6944getMap, nextKey));
                    } else {
                        throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline72("Unsupported transform type: ", nextKey));
                    }
                }
            } else {
                MatrixMathHelper.applyRotateZ(dArr2, convertToRadians(mo6944getMap, nextKey));
            }
            MatrixMathHelper.multiplyInto(dArr, dArr, dArr2);
        }
    }
}
