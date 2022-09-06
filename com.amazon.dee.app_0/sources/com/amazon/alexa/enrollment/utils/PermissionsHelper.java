package com.amazon.alexa.enrollment.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.amazon.alexa.enrollment.R;
import com.amazon.alexa.enrollment.dialogs.AlertDialogFragment;
import com.amazon.alexa.enrollment.dialogs.DialogConstants;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
/* loaded from: classes7.dex */
public class PermissionsHelper {
    private static final String TAG = GeneratedOutlineSupport1.outline39(PermissionsHelper.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));

    private void showPermissionsDialog(Fragment fragment, String str, String str2, int i) {
        if (!fragementAdded(fragment)) {
            Log.e(TAG, "Fragment is not added to an activity");
            return;
        }
        AlertDialogFragment createAlertDialogInstance = createAlertDialogInstance(str, str2, getStringResource(fragment, R.string.dialog_settings_button), getStringResource(fragment, R.string.dialog_dont_allow_button));
        createAlertDialogInstance.setTargetFragment(fragment, i);
        createAlertDialogInstance.show(getSupportFragmentManagerForActivity(fragment), DialogConstants.PERMISSIONS_FRAGMENT_TAG);
    }

    public boolean checkPermission(Context context, String str) {
        int checkSelfPermission = checkSelfPermission(context, str);
        if (checkSelfPermission == 0) {
            GeneratedOutlineSupport1.outline158(" App already has the permission ", str);
            return true;
        } else if (checkSelfPermission == -1) {
            GeneratedOutlineSupport1.outline158("App does not already have permission ", str);
            return false;
        } else {
            String str2 = "Hit else case in checkPermission (not expected) for permission " + str + " result was " + checkSelfPermission;
            return false;
        }
    }

    @VisibleForTesting
    int checkSelfPermission(Context context, String str) {
        return ContextCompat.checkSelfPermission(context, str);
    }

    @VisibleForTesting
    AlertDialogFragment createAlertDialogInstance(String str, String str2, String str3, String str4) {
        return AlertDialogFragment.createInstance(str, str2, str3, str4);
    }

    @VisibleForTesting
    boolean fragementAdded(Fragment fragment) {
        return fragment.isAdded();
    }

    @VisibleForTesting
    String getStringResource(Fragment fragment, int i) {
        return fragment.getString(i);
    }

    @VisibleForTesting
    FragmentManager getSupportFragmentManagerForActivity(Fragment fragment) {
        return fragment.getActivity().getSupportFragmentManager();
    }

    public void openAppSettingsPermissions(Fragment fragment) {
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.addCategory("android.intent.category.DEFAULT");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("package:");
        outline107.append(fragment.getContext().getPackageName());
        intent.setData(Uri.parse(outline107.toString()));
        intent.addFlags(268435456);
        intent.addFlags(1073741824);
        intent.addFlags(8388608);
        fragment.getContext().startActivity(intent);
    }

    public void requestPermission(Fragment fragment, String str, String str2, String[] strArr, int i) {
        if (fragment == null) {
            return;
        }
        int length = strArr.length;
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            } else if (fragment.shouldShowRequestPermissionRationale(strArr[i2])) {
                z = true;
                break;
            } else {
                i2++;
            }
        }
        GeneratedOutlineSupport1.outline179(GeneratedOutlineSupport1.outline107("Requesting Permissions "), Arrays.toString(strArr), TAG);
        if (z) {
            Log.i(TAG, "User has checked the never show again. Requesting permission from the user by navigating to App Settings page");
            showPermissionsDialog(fragment, str, str2, i);
            return;
        }
        Log.i(TAG, "Requesting permission from the user");
        requestPermission(fragment, strArr, i);
    }

    @VisibleForTesting
    void requestPermission(Fragment fragment, String[] strArr, int i) {
        fragment.requestPermissions(strArr, i);
    }
}
