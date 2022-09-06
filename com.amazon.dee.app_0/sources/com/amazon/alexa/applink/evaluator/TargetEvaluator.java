package com.amazon.alexa.applink.evaluator;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes6.dex */
public class TargetEvaluator {
    private static final String TAG = "TargetEvaluator";
    private final Context context;

    /* renamed from: com.amazon.alexa.applink.evaluator.TargetEvaluator$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$applink$evaluator$TargetIdentifierType = new int[TargetIdentifierType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$applink$evaluator$TargetIdentifierType[TargetIdentifierType.URI_HTTP_SCHEME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$applink$evaluator$TargetIdentifierType[TargetIdentifierType.URI_CUSTOM_SCHEME.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$applink$evaluator$TargetIdentifierType[TargetIdentifierType.URI_APP_IDENTIFIER_SCHEME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$applink$evaluator$TargetIdentifierType[TargetIdentifierType.URI_ANDROID_INTENT_SCHEME.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public TargetEvaluator(@NonNull Context context) {
        this.context = context;
    }

    @Nullable
    private Intent getFallbackIntent(@NonNull Target target) {
        String catalogId = target.catalogId();
        String identifier = target.identifier();
        int ordinal = target.targetIdentifierType().ordinal();
        if (ordinal == 0 || ordinal == 1) {
            if (target.isMandatoryToLaunchTarget()) {
                Log.i(TAG, "isMandatoryToLaunchTarget is true. no fallback intent");
                return null;
            }
            Log.i(TAG, "set fallback intent");
            return newIntentFromUriString(identifier);
        } else if (ordinal != 3) {
            return null;
        } else {
            if (target.isMandatoryToLaunchTarget()) {
                Log.i(TAG, "isMandatoryToLaunchTarget is true. no fallback intent");
                return null;
            }
            Intent intentFromIntentSchemeString = getIntentFromIntentSchemeString(identifier);
            if (!TextUtils.isEmpty(catalogId) && intentFromIntentSchemeString != null) {
                Log.i(TAG, "set package to null for fallback intent");
                intentFromIntentSchemeString.setPackage(null);
            }
            return intentFromIntentSchemeString;
        }
    }

    @NonNull
    private InstallStatus getInstallStatus(@NonNull Target target) {
        String catalogId = target.catalogId();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("package: ");
        outline107.append(target.catalogId());
        outline107.toString();
        if (TextUtils.isEmpty(catalogId)) {
            return InstallStatus.UNKNOWN;
        }
        boolean z = false;
        try {
            this.context.getPackageManager().getPackageInfo(catalogId, 0);
            z = true;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.i(TAG, "package not installed");
        }
        if (z) {
            return InstallStatus.INSTALLED;
        }
        return InstallStatus.NOT_INSTALLED;
    }

    @Nullable
    private Intent getIntentFromIntentSchemeString(String str) {
        try {
            return Intent.parseUri(str, 1);
        } catch (URISyntaxException unused) {
            Log.e(TAG, "error parsing intent");
            return null;
        }
    }

    @Nullable
    private Intent getLaunchIntent(@NonNull Target target) {
        String catalogId = target.catalogId();
        String identifier = target.identifier();
        int ordinal = target.targetIdentifierType().ordinal();
        if (ordinal == 0 || ordinal == 1) {
            Intent newIntentFromUriString = newIntentFromUriString(identifier);
            if (!TextUtils.isEmpty(catalogId)) {
                Log.i(TAG, "set intent package name");
                newIntentFromUriString.setPackage(catalogId);
                return newIntentFromUriString;
            }
            Log.i(TAG, "no package name specified.");
            return newIntentFromUriString;
        } else if (ordinal == 2) {
            return this.context.getPackageManager().getLaunchIntentForPackage(identifier);
        } else {
            if (ordinal != 3) {
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("some IdentifierType is not handled: ");
                outline107.append(target.targetIdentifierType().toString());
                Log.wtf(str, outline107.toString());
                return null;
            }
            Intent intentFromIntentSchemeString = getIntentFromIntentSchemeString(identifier);
            if (!TextUtils.isEmpty(catalogId) && intentFromIntentSchemeString != null && TextUtils.isEmpty(intentFromIntentSchemeString.getPackage())) {
                Log.i(TAG, "add packageName to the parsed intent.");
                intentFromIntentSchemeString.setPackage(catalogId);
                return intentFromIntentSchemeString;
            }
            Log.i(TAG, "Not adding packageName to the parsed intent.");
            return intentFromIntentSchemeString;
        }
    }

    @NonNull
    private Intent newIntentFromUriString(@NonNull String str) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.setFlags(268435456);
        intent.addFlags(2097152);
        intent.addFlags(67108864);
        return intent;
    }

    @VisibleForTesting
    boolean canLaunchIntent(@Nullable Intent intent) {
        ActivityInfo activityInfo;
        if (intent == null) {
            return false;
        }
        ResolveInfo resolveActivity = this.context.getPackageManager().resolveActivity(intent, intent.getComponent() != null ? 0 : 65536);
        if (resolveActivity != null && (activityInfo = resolveActivity.activityInfo) != null) {
            String str = activityInfo.permission;
            if (str == null) {
                return true;
            }
            boolean z = this.context.getPackageManager().checkPermission(str, this.context.getPackageName()) == 0;
            String.format("permission: (%s) is granted: %b", str, Boolean.valueOf(z));
            return z;
        }
        Log.i(TAG, "No activity available to handle the intent.");
        return false;
    }

    @NonNull
    public EvaluatedTargetState evaluate(@NonNull Target target) {
        InstallStatus installStatus = getInstallStatus(target);
        Intent launchIntent = getLaunchIntent(target);
        Intent fallbackIntent = getFallbackIntent(target);
        boolean z = false;
        boolean z2 = true;
        if (canLaunchIntent(launchIntent)) {
            z = true;
        } else if (canLaunchIntent(fallbackIntent)) {
            launchIntent = fallbackIntent;
        } else {
            launchIntent = null;
            z2 = false;
        }
        return EvaluatedTargetState.create(target, launchIntent, installStatus, z, z2);
    }

    @NonNull
    public List<EvaluatedTargetState> evaluateTargets(@NonNull List<Target> list) {
        ArrayList arrayList = new ArrayList();
        for (Target target : list) {
            arrayList.add(evaluate(target));
        }
        return arrayList;
    }
}
