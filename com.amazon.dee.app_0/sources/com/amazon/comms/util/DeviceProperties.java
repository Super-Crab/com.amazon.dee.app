package com.amazon.comms.util;

import android.content.Context;
import android.view.WindowManager;
import com.amazon.comms.log.CommsLogger;
/* loaded from: classes12.dex */
public class DeviceProperties {
    private static final CommsLogger sLog = CommsLogger.getLogger(DeviceProperties.class);

    /* loaded from: classes12.dex */
    public enum ScreenOrientation {
        LANDSCAPE("landscape"),
        PORTRAIT("portrait");
        
        private final String orientation;

        ScreenOrientation(String str) {
            this.orientation = str;
        }

        public static ScreenOrientation getDefault() {
            return PORTRAIT;
        }

        public String getOrientationString() {
            return this.orientation;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0029, code lost:
        r0 = ((java.lang.Integer) r4.get(android.hardware.camera2.CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int getCameraOrientation(android.content.Context r7) {
        /*
            java.lang.String r0 = "camera"
            java.lang.Object r7 = r7.getSystemService(r0)
            android.hardware.camera2.CameraManager r7 = (android.hardware.camera2.CameraManager) r7
            r0 = 0
            java.lang.String[] r1 = r7.getCameraIdList()     // Catch: android.hardware.camera2.CameraAccessException -> L36
            int r2 = r1.length     // Catch: android.hardware.camera2.CameraAccessException -> L36
            r3 = r0
        Lf:
            if (r3 >= r2) goto L4d
            r4 = r1[r3]     // Catch: android.hardware.camera2.CameraAccessException -> L36
            android.hardware.camera2.CameraCharacteristics r4 = r7.getCameraCharacteristics(r4)     // Catch: android.hardware.camera2.CameraAccessException -> L36
            android.hardware.camera2.CameraCharacteristics$Key r5 = android.hardware.camera2.CameraCharacteristics.LENS_FACING     // Catch: android.hardware.camera2.CameraAccessException -> L36
            java.lang.Object r5 = r4.get(r5)     // Catch: android.hardware.camera2.CameraAccessException -> L36
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch: android.hardware.camera2.CameraAccessException -> L36
            int r5 = r5.intValue()     // Catch: android.hardware.camera2.CameraAccessException -> L36
            r6 = 1
            if (r5 != r6) goto L29
            int r3 = r3 + 1
            goto Lf
        L29:
            android.hardware.camera2.CameraCharacteristics$Key r7 = android.hardware.camera2.CameraCharacteristics.SENSOR_ORIENTATION     // Catch: android.hardware.camera2.CameraAccessException -> L36
            java.lang.Object r7 = r4.get(r7)     // Catch: android.hardware.camera2.CameraAccessException -> L36
            java.lang.Integer r7 = (java.lang.Integer) r7     // Catch: android.hardware.camera2.CameraAccessException -> L36
            int r0 = r7.intValue()     // Catch: android.hardware.camera2.CameraAccessException -> L36
            goto L4d
        L36:
            r7 = move-exception
            com.amazon.comms.log.CommsLogger r1 = com.amazon.comms.util.DeviceProperties.sLog
            java.lang.String r2 = "getCameraOrientation() failed with exception: "
            java.lang.StringBuilder r2 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r2)
            java.lang.String r7 = r7.getMessage()
            r2.append(r7)
            java.lang.String r7 = r2.toString()
            r1.e(r7)
        L4d:
            com.amazon.comms.log.CommsLogger r7 = com.amazon.comms.util.DeviceProperties.sLog
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "getCameraOrientation(): "
            r1.append(r2)
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            r7.i(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.comms.util.DeviceProperties.getCameraOrientation(android.content.Context):int");
    }

    public static int getDeviceRotation(Context context) {
        int rotation = ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation();
        int i = rotation != 1 ? rotation != 2 ? rotation != 3 ? 0 : 270 : 180 : 90;
        CommsLogger commsLogger = sLog;
        commsLogger.i("getDeviceRotation(): " + i);
        return i;
    }

    public static int getScreenRotation(Context context) {
        int cameraOrientation = (getCameraOrientation(context) + getDeviceRotation(context)) % 360;
        CommsLogger commsLogger = sLog;
        commsLogger.i("Get screen rotation status: " + cameraOrientation);
        return cameraOrientation;
    }
}
