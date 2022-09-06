package com.amazon.device.messaging.development;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.amazon.device.messaging.ADMConstants;
import com.amazon.device.messaging.InvalidManifestException;
import com.amazon.device.messaging.ManifestChecker;
import com.amazon.device.messaging.ReceiverChecker;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import java.io.IOException;
/* loaded from: classes12.dex */
public final class ADMManifest {
    private static final String ADM_PACKAGE_RECEIVER_PERMISSION_FORMAT_STRING = "%s.permission.RECEIVE_ADM_MESSAGE";
    private static final String API_KEY_CONSTANT = "AmazonAPIKey";
    private static final String API_KEY_FILENAME = "api_key.txt";
    @FireOsSdk
    public static final String DEFAULT_MESSAGE_HANDLER_CLASS_NAME = ".ADMMessageHandler";
    @FireOsSdk
    public static final String PERMISSION_RECEIVE_MESSAGES = "com.amazon.device.messaging.permission.RECEIVE";
    @FireOsSdk
    public static final String PERMISSION_SEND_MESSAGES = "com.amazon.device.messaging.permission.SEND";

    private ADMManifest() {
    }

    private static boolean apiKeyInAssets(Context context) {
        try {
            for (String str : context.getAssets().list("")) {
                if (API_KEY_FILENAME.equals(str)) {
                    return true;
                }
            }
        } catch (IOException unused) {
        }
        return false;
    }

    private static boolean apiKeyInManifest(PackageInfo packageInfo) {
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        Bundle bundle = new Bundle();
        if (applicationInfo != null && applicationInfo.metaData != null) {
            bundle = applicationInfo.metaData;
        }
        for (String str : bundle.keySet()) {
            if ("AmazonAPIKey".equals(str)) {
                return true;
            }
        }
        return false;
    }

    private static void checkAmazonAPIKey(Context context, PackageInfo packageInfo) {
        if (apiKeyInAssets(context) || apiKeyInManifest(packageInfo)) {
            return;
        }
        throw new IllegalStateException("Must have an API Key");
    }

    @FireOsSdk
    public static void checkManifestAuthoredProperly(Context context) {
        String packageName = context.getPackageName();
        try {
            new ManifestChecker.Builder().setPackageName(packageName).setUseMessagingPermission("com.amazon.device.messaging.permission.RECEIVE").addExtraPermission("android.permission.WAKE_LOCK").addReceiverChecker(new ReceiverChecker.Builder().setAction(ADMConstants.LowLevel.ACTION_APP_REGISTRATION_EVENT).setSenderPermission("com.amazon.device.messaging.permission.SEND").setShouldThrowIfMissing(true).setShouldLogIfMissing(true).build()).addReceiverChecker(new ReceiverChecker.Builder().setAction(ADMConstants.LowLevel.ACTION_RECEIVE_ADM_MESSAGE).setSenderPermission("com.amazon.device.messaging.permission.SEND").setShouldThrowIfMissing(true).setShouldLogIfMissing(true).build()).build().check(context);
            try {
                checkAmazonAPIKey(context, context.getPackageManager().getPackageInfo(packageName, 4224));
            } catch (PackageManager.NameNotFoundException unused) {
                throw new IllegalStateException(String.format("Could not find entries for package: %s", packageName));
            }
        } catch (InvalidManifestException e) {
            throw new IllegalStateException(e);
        }
    }

    @FireOsSdk
    public static String getReceiverPermission(Context context) {
        return getReceiverPermission(context.getPackageName());
    }

    public static String getReceiverPermission(String str) {
        return String.format(ADM_PACKAGE_RECEIVER_PERMISSION_FORMAT_STRING, str);
    }
}
