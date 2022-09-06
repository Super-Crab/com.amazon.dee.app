package com.amazon.alexa.permissions;

import android.app.Activity;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.permissions.api.PermissionsListener;
import com.amazon.alexa.permissions.api.PermissionsService;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes9.dex */
public class DefaultPermissionsService implements PermissionsService {
    private static final String ACCESS_BACKGROUND_LOCATION = "android.permission.ACCESS_BACKGROUND_LOCATION";
    private static final int ANDROID_Q = 29;
    private static final int REQUEST_CODE_MAX = 999;
    private static final int REQUEST_CODE_MIN = 100;
    private static final String TAG = "PermissionsService";
    private final Activity activity;
    @VisibleForTesting
    Map<Integer, PermissionsListener> requestMap = new ConcurrentHashMap();

    public DefaultPermissionsService(Activity activity) {
        this.activity = activity;
    }

    public boolean isRequested(int i) {
        return this.requestMap.containsKey(Integer.valueOf(i));
    }

    public void onResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (!this.requestMap.containsKey(Integer.valueOf(i))) {
            Log.w(TAG, "onResult called but did not recognize request code; ignoring");
            return;
        }
        PermissionsListener remove = this.requestMap.remove(Integer.valueOf(i));
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] != 0) {
                arrayList.add(strArr[i2]);
            }
        }
        if (arrayList.isEmpty()) {
            remove.onGranted();
            return;
        }
        int i3 = Build.VERSION.SDK_INT;
        ArrayList arrayList2 = new ArrayList();
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            String str = (String) arrayList.get(i4);
            if (!this.activity.shouldShowRequestPermissionRationale(str)) {
                arrayList2.add(str);
            }
        }
        if (!arrayList2.isEmpty()) {
            remove.onBlocked(arrayList, arrayList2);
        } else {
            remove.onDenied(arrayList);
        }
    }

    @Override // com.amazon.alexa.permissions.api.PermissionsService
    public void request(@NonNull String[] strArr, @NonNull PermissionsListener permissionsListener) {
        int nextInt;
        do {
            nextInt = new Random().nextInt(900) + 100;
        } while (this.requestMap.containsKey(Integer.valueOf(nextInt)));
        this.requestMap.put(Integer.valueOf(nextInt), permissionsListener);
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (ContextCompat.checkSelfPermission(this.activity, str) != 0) {
                arrayList.add(str);
            }
            if (arrayList.contains(ACCESS_BACKGROUND_LOCATION) && Build.VERSION.SDK_INT < 29) {
                arrayList.remove(ACCESS_BACKGROUND_LOCATION);
            }
        }
        if (!arrayList.isEmpty()) {
            ActivityCompat.requestPermissions(this.activity, (String[]) arrayList.toArray(new String[arrayList.size()]), nextInt);
            return;
        }
        this.requestMap.remove(Integer.valueOf(nextInt)).onGranted();
    }
}
