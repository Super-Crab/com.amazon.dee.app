package com.amazon.dee.app.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.operational.OperationalEventType;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.tarazed.core.metrics.BizMetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes12.dex */
public final class PermissionsUtils {
    private static final String ACCESS_BACKGROUND_LOCATION_PERMISSION = "android.permission.ACCESS_BACKGROUND_LOCATION";
    public static final String NO_CAMERA = "no camera";
    public static final String NO_PERMISSION = "Permission not found";
    public static final String PERMISSION_CANCELLED = "Permission request was cancelled.";
    private static Mobilytics mobilytics;
    private static SparseArray<Listener> requestListeners = new SparseArray<>();
    private static final String TAG = Log.tag(PermissionsUtils.class);

    /* loaded from: classes12.dex */
    public interface Listener {
        void complete(boolean z);

        void complete(boolean z, String str);
    }

    /* loaded from: classes12.dex */
    public enum LocationPermissionAuthStatus {
        GRANTED("Granted"),
        GRANTED_FOREGROUND_ONLY("AuthorizedWhenInUse"),
        DENIED("Denied");
        
        private final String name;

        LocationPermissionAuthStatus(String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }
    }

    /* loaded from: classes12.dex */
    public enum PermissionAuthStatus {
        DENIED("Denied"),
        GRANTED("Granted"),
        UNDETERMINED("Undetermined");
        
        public final String name;

        PermissionAuthStatus(String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }
    }

    /* loaded from: classes12.dex */
    public enum RequestInfo {
        CAMERA(1024, "camera", new String[]{"android.permission.CAMERA"}, "permissions.camera.", "android.hardware.camera"),
        BLUETOOTH(1025, "bluetooth", new String[]{"android.permission.BLUETOOTH", "android.permission.ACCESS_FINE_LOCATION"}, "permissions.bluetooth."),
        LOCATION(1026, "location", new String[]{"android.permission.ACCESS_FINE_LOCATION"}, "permissions.location."),
        MICROPHONE(1027, "microphone", new String[]{"android.permission.RECORD_AUDIO"}, "permissions.microphone.", "android.hardware.microphone"),
        PHOTO_LIBRARY(1028, "photo_library", PermissionsUtils.getStoragePermissions(), "permissions.photo_library."),
        CONTACTS(1029, "contacts", new String[]{"android.permission.READ_CONTACTS"}, "permissions.contacts."),
        BACKGROUND_LOCATION(1030, "background_location", new String[]{PermissionsUtils.ACCESS_BACKGROUND_LOCATION_PERMISSION}, "permissions.background_location."),
        SMS(1031, "sms", new String[]{"android.permission.SEND_SMS", "android.permission.READ_SMS", "android.permission.RECEIVE_SMS", "android.permission.RECEIVE_MMS"}, "permissions.sms."),
        CALL(1032, "call", PermissionsUtils.getCallPermissions(), "permissions.phone.");
        
        public final String feature;
        public final String metric;
        public final String name;
        public final String[] permissions;
        public final int requestCode;

        RequestInfo(int i, String str, String[] strArr, String str2) {
            this(i, str, strArr, str2, null);
        }

        public static RequestInfo byName(String str) {
            RequestInfo[] values;
            if (str != null) {
                String lowerCase = str.toLowerCase();
                for (RequestInfo requestInfo : values()) {
                    if (lowerCase.equals(requestInfo.name)) {
                        return requestInfo;
                    }
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Invalid permission name. No RequestInfo found for: ", lowerCase));
            }
            throw new IllegalArgumentException("Invalid permission name. The permission name is null.");
        }

        public static RequestInfo byRequestCode(int i) {
            RequestInfo[] values;
            for (RequestInfo requestInfo : values()) {
                if (i == requestInfo.requestCode) {
                    return requestInfo;
                }
            }
            return null;
        }

        RequestInfo(int i, String str, String[] strArr, String str2, String str3) {
            this.name = str;
            this.requestCode = i;
            this.permissions = strArr;
            this.metric = str2;
            this.feature = str3;
        }
    }

    private PermissionsUtils() {
        throw new IllegalStateException("No instances of PermissionsUtils allowed.");
    }

    public static void checkBluetoothPermission(@NonNull Activity activity, Listener listener, String str) {
        if (listener != null) {
            requestListeners.put(RequestInfo.BLUETOOTH.requestCode, listener);
        }
        processRequest(activity, RequestInfo.BLUETOOTH, str);
    }

    public static void checkCameraPermission(@NonNull Activity activity, Listener listener, String str) {
        if (!activity.getPackageManager().hasSystemFeature("android.hardware.camera.any")) {
            listener.complete(false, NO_CAMERA);
            return;
        }
        if (listener != null) {
            requestListeners.put(RequestInfo.CAMERA.requestCode, listener);
        }
        processRequest(activity, RequestInfo.CAMERA, str);
    }

    public static LocationPermissionAuthStatus getBackgroundLocationPermissionAuthStatus(@NonNull Activity activity) {
        if (activity.getPackageManager().checkPermission(ACCESS_BACKGROUND_LOCATION_PERMISSION, activity.getPackageName()) == 0) {
            return LocationPermissionAuthStatus.GRANTED;
        }
        return LocationPermissionAuthStatus.DENIED;
    }

    @VisibleForTesting
    protected static String[] getCallPermissions() {
        int i = Build.VERSION.SDK_INT;
        return new String[]{"android.permission.ANSWER_PHONE_CALLS", "android.permission.CALL_PHONE", "android.permission.RECORD_AUDIO"};
    }

    public static LocationPermissionAuthStatus getLocationPermissionAuthStatus(@NonNull Activity activity) {
        PackageManager packageManager = activity.getPackageManager();
        if (Build.VERSION.SDK_INT >= 29) {
            if (packageManager.checkPermission(ACCESS_BACKGROUND_LOCATION_PERMISSION, activity.getPackageName()) == 0) {
                return LocationPermissionAuthStatus.GRANTED;
            }
            if (packageManager.checkPermission("android.permission.ACCESS_FINE_LOCATION", activity.getPackageName()) == 0) {
                return LocationPermissionAuthStatus.GRANTED_FOREGROUND_ONLY;
            }
        } else if (packageManager.checkPermission("android.permission.ACCESS_FINE_LOCATION", activity.getPackageName()) == 0) {
            return LocationPermissionAuthStatus.GRANTED;
        }
        return LocationPermissionAuthStatus.DENIED;
    }

    public static PermissionAuthStatus getPermissionAuthStatus(@NonNull Activity activity, @Nullable RequestInfo requestInfo) {
        String[] strArr;
        boolean z = false;
        boolean z2 = false;
        for (String str : requestInfo.permissions) {
            if (ContextCompat.checkSelfPermission(activity, str) != 0) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, str)) {
                    z = true;
                } else {
                    z2 = true;
                }
            }
        }
        if (z) {
            return PermissionAuthStatus.DENIED;
        }
        if (z2) {
            return PermissionAuthStatus.UNDETERMINED;
        }
        return PermissionAuthStatus.GRANTED;
    }

    @VisibleForTesting
    protected static String[] getStoragePermissions() {
        return Build.VERSION.SDK_INT >= 29 ? new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_MEDIA_LOCATION"} : new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"};
    }

    public static boolean hasLocationPermission(@NonNull Context context) {
        return hasPermissions(context, RequestInfo.LOCATION.permissions);
    }

    public static boolean hasPermissions(@NonNull Context context, @NonNull String[] strArr) {
        for (String str : strArr) {
            if (ContextCompat.checkSelfPermission(context, str) != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPermissionRequest(int i) {
        return RequestInfo.byRequestCode(i) != null;
    }

    public static void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        RequestInfo byRequestCode;
        boolean z = true;
        for (int i2 : iArr) {
            z &= i2 == 0;
        }
        if (mobilytics != null && (byRequestCode = RequestInfo.byRequestCode(i)) != null) {
            Mobilytics mobilytics2 = mobilytics;
            StringBuilder sb = new StringBuilder();
            sb.append(byRequestCode.metric);
            sb.append(z ? BizMetricsConstants.OS_PERMISSION_GRANTED_TRUE_VALUE : iArr.length == 0 ? "cancelled" : "denied");
            mobilytics.recordOperationalEvent(mobilytics2.createOperationalEvent(sb.toString(), OperationalEventType.DIAGNOSTIC, "Application", TAG, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a"));
        }
        Listener listener = requestListeners.get(i);
        if (listener != null) {
            if (iArr.length == 0) {
                listener.complete(false, PERMISSION_CANCELLED);
            } else {
                listener.complete(z);
            }
        }
    }

    private static boolean permissionShouldBeRequested(@NonNull Activity activity, String str) {
        return ContextCompat.checkSelfPermission(activity, str) != 0;
    }

    private static void processRequest(@NonNull Activity activity, @NonNull RequestInfo requestInfo, String str) {
        List<String> asList = Arrays.asList(requestInfo.permissions);
        ArrayList arrayList = new ArrayList();
        for (String str2 : asList) {
            if (permissionShouldBeRequested(activity, str2)) {
                arrayList.add(str2);
            }
        }
        if (arrayList.size() > 0) {
            ActivityCompat.requestPermissions(activity, (String[]) arrayList.toArray(new String[arrayList.size()]), requestInfo.requestCode);
            return;
        }
        Listener listener = requestListeners.get(requestInfo.requestCode);
        if (listener == null) {
            return;
        }
        listener.complete(true);
    }

    @TargetApi(29)
    public static void requestBackgroundLocationPermission(@NonNull Activity activity, Listener listener, String str) {
        if (listener != null) {
            requestListeners.put(RequestInfo.BACKGROUND_LOCATION.requestCode, listener);
        }
        processRequest(activity, RequestInfo.BACKGROUND_LOCATION, str);
    }

    public static void requestLocationPermission(@NonNull Activity activity, Listener listener, String str) {
        if (listener != null) {
            requestListeners.put(RequestInfo.LOCATION.requestCode, listener);
        }
        processRequest(activity, RequestInfo.LOCATION, str);
    }

    public static void requestPermission(@NonNull Activity activity, Listener listener, RequestInfo requestInfo, String str) {
        if (requestInfo.feature == null || activity.getPackageManager().hasSystemFeature(requestInfo.feature)) {
            if (listener != null) {
                requestListeners.put(requestInfo.requestCode, listener);
            }
            processRequest(activity, requestInfo, str);
        } else if (listener == null) {
        } else {
            listener.complete(false, NO_PERMISSION);
        }
    }

    public static void setMetricsService(Mobilytics mobilytics2) {
        mobilytics = mobilytics2;
    }
}
