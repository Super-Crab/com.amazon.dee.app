package com.amazon.alexa.voice.handsfree;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.utils.security.SignatureVerifier;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes11.dex */
public class HandsFreePackageInfoResolver {
    static final String ACTION_HANDS_FREE_SETUP = "com.amazon.alexa.handsfree.START_SETUP_FLOW";
    static final String ACTION_HANDS_FREE_SETUP_FROM_ALEXA = "com.amazon.alexa.handsfree.START_SETUP_FROM_ALEXA";
    static final String HANDS_FREE_SETUP_ACTIVITY_CLASS_NAME = "com.amazon.alexa.handsfree.setup.HandsFreePermissionActivity";
    static final String HANDS_FREE_SETUP_RECEIVER_CLASS_NAME = "com.amazon.alexa.handsfree.decider.SetupFlowExecutionReceiver";
    private static final String TAG = "HandsFreePackageInfoResolver";
    private final PackageManager mPackageManager;
    private final SignatureVerifier mSignatureVerifier;

    public HandsFreePackageInfoResolver(@NonNull Context context) {
        this(new SignatureVerifier(context), context.getPackageManager());
    }

    @NonNull
    private Intent getIntentByAction(@NonNull String str) {
        return new Intent(str);
    }

    @Nullable
    private String getValidPackageName(@Nullable List<ResolveInfo> list) {
        if (list != null && !list.isEmpty()) {
            if (list.size() > 1) {
                Log.e(TAG, "More than one package matches HandsFree intent");
            }
            for (ResolveInfo resolveInfo : list) {
                if (isValidResolveInfo(resolveInfo)) {
                    return resolveInfo.activityInfo.packageName;
                }
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Ignoring, found Hands-free setup package with invalid signature: ");
                outline107.append(resolveInfo.activityInfo.packageName);
                Log.w(str, outline107.toString());
            }
            Log.d(TAG, "Hands-free not supported");
            return null;
        }
        Log.d(TAG, "HandsFree not supported: HandsFree intent does not resolve");
        return null;
    }

    private boolean isAndroidVersionSupportsHandsFree() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    private boolean isValidResolveInfo(@Nullable ResolveInfo resolveInfo) {
        ActivityInfo activityInfo;
        if (resolveInfo == null || (activityInfo = resolveInfo.activityInfo) == null) {
            return false;
        }
        if (!HANDS_FREE_SETUP_RECEIVER_CLASS_NAME.equals(activityInfo.name) && !HANDS_FREE_SETUP_ACTIVITY_CLASS_NAME.equals(resolveInfo.activityInfo.name)) {
            return false;
        }
        return this.mSignatureVerifier.verify(resolveInfo.activityInfo.packageName);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    @TargetApi(23)
    public String getHandsFreeSetupPackageName() {
        if (!isAndroidVersionSupportsHandsFree()) {
            return null;
        }
        return getValidPackageName(this.mPackageManager.queryBroadcastReceivers(getIntentByAction(ACTION_HANDS_FREE_SETUP), 131072));
    }

    @Nullable
    @TargetApi(23)
    @VisibleForTesting
    String getHandsFreeSetupPackageNameQueryActivity() {
        if (!isAndroidVersionSupportsHandsFree()) {
            return null;
        }
        Intent intentByAction = getIntentByAction(ACTION_HANDS_FREE_SETUP_FROM_ALEXA);
        intentByAction.addCategory("android.intent.category.DEFAULT");
        return getValidPackageName(this.mPackageManager.queryIntentActivities(intentByAction, 131072));
    }

    @Nullable
    public Intent getPermissionsIntent() {
        String handsFreeSetupPackageNameQueryActivity = getHandsFreeSetupPackageNameQueryActivity();
        if (handsFreeSetupPackageNameQueryActivity != null) {
            Intent intent = new Intent(ACTION_HANDS_FREE_SETUP_FROM_ALEXA);
            intent.setComponent(new ComponentName(handsFreeSetupPackageNameQueryActivity, HANDS_FREE_SETUP_ACTIVITY_CLASS_NAME));
            intent.setPackage(handsFreeSetupPackageNameQueryActivity);
            return intent;
        }
        return null;
    }

    @VisibleForTesting
    HandsFreePackageInfoResolver(@NonNull SignatureVerifier signatureVerifier, @NonNull PackageManager packageManager) {
        this.mSignatureVerifier = signatureVerifier;
        this.mPackageManager = packageManager;
    }
}
