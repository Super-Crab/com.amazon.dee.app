package com.amazon.alexa.handsfree.settings.client;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.EnumMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
/* loaded from: classes8.dex */
public class AlexaAudioProviderServiceSecurityHelper {
    private static final String METRIC_NAME_FORMAT_INVALID_PACKAGE = "InsecurePackageName:%s";
    private static final String METRIC_SOURCE = "AlexaAudioProviderServiceSecurityHelper";
    private static final String OEM_SIGNED_PACKAGE = "android";
    private static final String TAG = "AlexaAudioSvcSecurity";
    private Context mContext;
    private final MetricsBuilderProvider mMetricsBuilderProvider;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public enum Priority {
        OEM_SIGNATURE_SECURE_PRIORITY,
        SYSTEM_SECURE_PRIORITY
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaAudioProviderServiceSecurityHelper(@NonNull Context context) {
        this(context, MetricsBuilderProvider.getInstance(context));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public ResolveInfo getSecureResolveInfo(@NonNull Intent intent) {
        PackageManager packageManager = this.mContext.getPackageManager();
        if (packageManager == null) {
            Log.e(TAG, "Failed to get secure resolve info because package manager is null");
            return null;
        }
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 128);
        EnumMap enumMap = new EnumMap(Priority.class);
        if (queryIntentServices != null && queryIntentServices.size() > 0) {
            for (ResolveInfo resolveInfo : queryIntentServices) {
                if (this.mContext.getPackageName().equals(resolveInfo.serviceInfo.packageName)) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Found a resolveInfo with the same package name [");
                    outline107.append(this.mContext.getPackageName());
                    outline107.append("]");
                    Log.d(TAG, outline107.toString());
                    return resolveInfo;
                } else if (packageManager.checkSignatures(OEM_SIGNED_PACKAGE, resolveInfo.serviceInfo.packageName) == 0) {
                    Log.d(TAG, "Found a resolveInfo with the same OEM signature");
                    enumMap.putIfAbsent(Priority.OEM_SIGNATURE_SECURE_PRIORITY, resolveInfo);
                } else if ((resolveInfo.serviceInfo.applicationInfo.flags & 1) != 0) {
                    Log.d(TAG, "Found a resolveInfo with system flag");
                    enumMap.putIfAbsent(Priority.SYSTEM_SECURE_PRIORITY, resolveInfo);
                } else {
                    this.mMetricsBuilderProvider.newBuilder().withPerformanceMetric(METRIC_SOURCE, String.format(Locale.US, METRIC_NAME_FORMAT_INVALID_PACKAGE, resolveInfo.serviceInfo.packageName)).emit(this.mContext);
                }
            }
        }
        return (ResolveInfo) Optional.ofNullable(enumMap.get(Priority.OEM_SIGNATURE_SECURE_PRIORITY)).orElse(Optional.ofNullable(enumMap.get(Priority.SYSTEM_SECURE_PRIORITY)).orElse(null));
    }

    @VisibleForTesting
    AlexaAudioProviderServiceSecurityHelper(@NonNull Context context, @NonNull MetricsBuilderProvider metricsBuilderProvider) {
        this.mContext = context;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
    }
}
