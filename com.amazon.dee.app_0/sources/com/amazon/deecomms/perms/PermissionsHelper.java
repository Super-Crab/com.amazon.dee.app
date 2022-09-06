package com.amazon.deecomms.perms;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Arrays;
/* loaded from: classes12.dex */
public final class PermissionsHelper {
    public static final int CONTACTS_CODE = 134;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, PermissionsHelper.class);
    public static final int NATIVE_CALLING_PERMISSIONS_CODE = 335;
    public static final int SEND_SMS_AND_PHONE_CODE = 334;

    private PermissionsHelper() {
    }

    public static boolean checkGrantedPermissions(int[] iArr) {
        if (iArr.length == 0) {
            LOG.i("No permissions were requested or granted properly");
            return false;
        }
        for (int i : iArr) {
            if (i == -1) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkPermission(Context context, String str) {
        int checkSelfPermission = ContextCompat.checkSelfPermission(context, str);
        if (checkSelfPermission == 0) {
            GeneratedOutlineSupport1.outline159(" App already has the permission ", str, LOG);
            return true;
        } else if (checkSelfPermission == -1) {
            GeneratedOutlineSupport1.outline159("App does not already have permission ", str, LOG);
            return false;
        } else {
            CommsLogger commsLogger = LOG;
            commsLogger.w("Hit else case in checkPermission (not expected) for permission " + str + " result was " + checkSelfPermission);
            return false;
        }
    }

    public static String[] checkPermissions(Context context, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < strArr.length; i++) {
            if (!checkPermission(context, strArr[i])) {
                arrayList.add(strArr[i]);
            }
        }
        String[] strArr2 = new String[arrayList.size()];
        arrayList.toArray(strArr2);
        return strArr2;
    }

    public static String[] filterGrantedPermissions(String[] strArr) {
        Context context = CommsDaggerWrapper.getComponent().getContext();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < strArr.length; i++) {
            if (!checkPermission(context, strArr[i])) {
                arrayList.add(strArr[i]);
            }
        }
        String[] strArr2 = new String[arrayList.size()];
        arrayList.toArray(strArr2);
        return strArr2;
    }

    public static String getDeniedCallingPermissionsRationale(Context context, boolean z) {
        Resources resources = context.getResources();
        if (z) {
            return resources.getString(R.string.video_call_permission_settings);
        }
        return resources.getString(R.string.audio_permission_settings_microphone);
    }

    public static String[] getPermissionListForAudio() {
        return new String[]{"android.permission.RECORD_AUDIO"};
    }

    public static String[] getPermissionListForVideoCalling() {
        return new String[]{"android.permission.RECORD_AUDIO", "android.permission.CAMERA"};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$showPermissionNotAvailableDialog$1(AlertDialog alertDialog, Context context, DialogInterface dialogInterface) {
        alertDialog.getButton(-2).setTextColor(ContextCompat.getColor(context, R.color.fiesta_btn_normal));
        alertDialog.getButton(-1).setTextColor(ContextCompat.getColor(context, R.color.fiesta_btn_normal));
    }

    public static void requestPermission(Activity activity, String str, String str2, int i, String str3, String str4, @NonNull AlertSource alertSource, boolean z, @Nullable DialogInterface.OnDismissListener onDismissListener) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, str2)) {
            CommsLogger commsLogger = LOG;
            commsLogger.i("User has checked the never show again.Requesting permission " + str2 + " from the user by navigating to App settings page");
            showPermissionNotAvailableDialog(activity, str, str3, str4, alertSource, z, onDismissListener);
            return;
        }
        CommsLogger commsLogger2 = LOG;
        commsLogger2.i("Requesting permission " + str2 + " from the user");
        ActivityCompat.requestPermissions(activity, new String[]{str2}, i);
    }

    public static void requestPermissions(Activity activity, String[] strArr, int i) {
        ActivityCompat.requestPermissions(activity, strArr, i);
    }

    public static void showPermissionNotAvailableDialog(Activity activity, String str, String str2, String str3, @NonNull AlertSource alertSource, boolean z, @Nullable DialogInterface.OnDismissListener onDismissListener) {
        showPermissionNotAvailableDialog(activity, str, str2, str3, alertSource, 0, 0, z, onDismissListener);
    }

    public static void showPermissionNotAvailableDialog(Activity activity, String str, String str2, String str3, @NonNull AlertSource alertSource, int i, int i2, final boolean z, @Nullable DialogInterface.OnDismissListener onDismissListener) {
        final Context context = CommsDaggerWrapper.getComponent().getContext();
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.perms.PermissionsHelper.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i3) {
                Intent intent = new Intent();
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.addCategory("android.intent.category.DEFAULT");
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("package:");
                outline1.append(context.getPackageName());
                intent.setData(Uri.parse(outline1.toString()));
                intent.addFlags(268435456);
                intent.addFlags(1073741824);
                intent.addFlags(8388608);
                context.startActivity(intent);
                if (z) {
                    PermissionsHelper.LOG.i("User is taken to the app settings page for enabling permissions. Rejecting the incoming call");
                    CallUtils.rejectCall(context);
                }
                dialogInterface.dismiss();
            }
        };
        MetricsHelper.recordAlertDialogMetric(str2, str3, alertSource);
        AlertDialog.Builder negativeButton = new AlertDialog.Builder(activity, i).setMessage(str).setPositiveButton(context.getString(R.string.dialog_settings_button), onClickListener).setNegativeButton(context.getString(R.string.dialog_cancel_button), $$Lambda$PermissionsHelper$5tWlnMgvFdNFjP7pDQKW9bsMNP4.INSTANCE);
        View view = null;
        if (i2 != 0) {
            view = activity.getLayoutInflater().inflate(i2, (ViewGroup) null);
        }
        final AlertDialog create = negativeButton.setView(view).setOnDismissListener(onDismissListener).create();
        create.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.amazon.deecomms.perms.-$$Lambda$PermissionsHelper$imFthaC-DuHpA9zraiCbzzGX8OY
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                PermissionsHelper.lambda$showPermissionNotAvailableDialog$1(create, context, dialogInterface);
            }
        });
        create.show();
    }

    public static void requestPermission(Activity activity, String str, String[] strArr, int i, String str2, String str3, @NonNull AlertSource alertSource, boolean z, @Nullable DialogInterface.OnDismissListener onDismissListener) {
        int length = strArr.length;
        boolean z2 = false;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(activity, strArr[i2])) {
                z2 = true;
                break;
            } else {
                i2++;
            }
        }
        GeneratedOutlineSupport1.outline177(GeneratedOutlineSupport.outline1("Requesting Permissions "), Arrays.toString(strArr), LOG);
        if (z2) {
            LOG.i("User has denied permissions. Requesting permissions from the user by navigating to App settings page");
            showPermissionNotAvailableDialog(activity, str, str2, str3, alertSource, z, onDismissListener);
            return;
        }
        LOG.i("Requesting permission from the user");
        ActivityCompat.requestPermissions(activity, strArr, i);
    }

    public static void requestPermission(Fragment fragment, String str, String[] strArr, int i, String str2, String str3, @NonNull AlertSource alertSource, boolean z, @Nullable DialogInterface.OnDismissListener onDismissListener) {
        if (fragment == null) {
            LOG.w("PermissionsHelper.requestPermission has a null fragment - this is unusual");
            return;
        }
        int length = strArr.length;
        boolean z2 = false;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            } else if (fragment.shouldShowRequestPermissionRationale(strArr[i2])) {
                z2 = true;
                break;
            } else {
                i2++;
            }
        }
        GeneratedOutlineSupport1.outline177(GeneratedOutlineSupport.outline1("Requesting Permissions "), Arrays.toString(strArr), LOG);
        if (z2) {
            LOG.i("User has checked the never show again. Requesting permission from the user by navigating to App Settings page");
            showPermissionNotAvailableDialog(fragment.getActivity(), str, str2, str3, alertSource, z, onDismissListener);
            return;
        }
        LOG.i("Requesting permission from the user");
        fragment.requestPermissions(strArr, i);
    }
}
