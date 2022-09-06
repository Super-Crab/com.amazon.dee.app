package com.amazon.photos.discovery;

import android.content.Context;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.CDClient;
import com.amazon.photos.discovery.dedupe.DedupeStage;
import com.amazon.photos.discovery.internal.util.AndroidLogger;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DiscoveryConfiguration.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0018\u0018\u00002\u00020\u0001Bk\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0012¢\u0006\u0002\u0010\u0015B?\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0016B5\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\u0017Be\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0018\u001a\u00020\u0005\u0012\u0006\u0010\u0019\u001a\u00020\u001a\u0012\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0014\u001a\u00020\u0012¢\u0006\u0002\u0010\u001cR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010%\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\u0013\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0011\u0010\u0019\u001a\u00020\u001a¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b0\u0010-R\u0011\u0010\u0014\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b1\u0010-¨\u00062"}, d2 = {"Lcom/amazon/photos/discovery/DiscoveryConfiguration;", "", "applicationContext", "Landroid/content/Context;", "accountId", "", "dedupeDestages", "", "Lcom/amazon/photos/discovery/dedupe/DedupeStage;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "cdClient", "Lcom/amazon/clouddrive/cdasdk/CDClient;", "logger", "Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "crashReporter", "Lcom/amazon/photos/discovery/CrashReporter;", "useMonitor", "", "onlyScanCamera", "useVisualDigest", "(Landroid/content/Context;Ljava/lang/String;Ljava/util/List;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Lcom/amazon/clouddrive/cdasdk/CDClient;Lcom/amazon/clouddrive/android/core/interfaces/Logger;Lcom/amazon/photos/discovery/CrashReporter;ZZZ)V", "(Landroid/content/Context;Ljava/lang/String;Ljava/util/List;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Lcom/amazon/clouddrive/cdasdk/CDClient;Lcom/amazon/photos/discovery/CrashReporter;)V", "(Landroid/content/Context;Ljava/lang/String;Ljava/util/List;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Lcom/amazon/clouddrive/cdasdk/CDClient;)V", "directedId", "scanBatchSize", "", "dedupeStages", "(Landroid/content/Context;Ljava/lang/String;ILjava/util/List;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Lcom/amazon/clouddrive/android/core/interfaces/Logger;Lcom/amazon/clouddrive/cdasdk/CDClient;Lcom/amazon/photos/discovery/CrashReporter;ZZZ)V", "getApplicationContext", "()Landroid/content/Context;", "getCdClient", "()Lcom/amazon/clouddrive/cdasdk/CDClient;", "getCrashReporter", "()Lcom/amazon/photos/discovery/CrashReporter;", "getDedupeStages", "()Ljava/util/List;", "hashedDirectedId", "getHashedDirectedId", "()Ljava/lang/String;", "getLogger", "()Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "getMetrics", "()Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "getOnlyScanCamera", "()Z", "getScanBatchSize", "()I", "getUseMonitor", "getUseVisualDigest", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DiscoveryConfiguration {
    @NotNull
    private final Context applicationContext;
    @NotNull
    private final CDClient cdClient;
    @Nullable
    private final CrashReporter crashReporter;
    @NotNull
    private final List<DedupeStage> dedupeStages;
    @NotNull
    private final String hashedDirectedId;
    @NotNull
    private final Logger logger;
    @NotNull
    private final Metrics metrics;
    private final boolean onlyScanCamera;
    private final int scanBatchSize;
    private final boolean useMonitor;
    private final boolean useVisualDigest;

    public DiscoveryConfiguration(@NotNull Context applicationContext, @NotNull String directedId, int i, @NotNull List<DedupeStage> dedupeStages, @NotNull Metrics metrics, @NotNull Logger logger, @NotNull CDClient cdClient, @Nullable CrashReporter crashReporter, boolean z, boolean z2, boolean z3) {
        Intrinsics.checkParameterIsNotNull(applicationContext, "applicationContext");
        Intrinsics.checkParameterIsNotNull(directedId, "directedId");
        Intrinsics.checkParameterIsNotNull(dedupeStages, "dedupeStages");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(cdClient, "cdClient");
        this.applicationContext = applicationContext;
        this.scanBatchSize = i;
        this.dedupeStages = dedupeStages;
        this.metrics = metrics;
        this.logger = logger;
        this.cdClient = cdClient;
        this.crashReporter = crashReporter;
        this.useMonitor = z;
        this.onlyScanCamera = z2;
        this.useVisualDigest = z3;
        this.hashedDirectedId = String.valueOf(directedId.hashCode());
    }

    @NotNull
    public final Context getApplicationContext() {
        return this.applicationContext;
    }

    @NotNull
    public final CDClient getCdClient() {
        return this.cdClient;
    }

    @Nullable
    public final CrashReporter getCrashReporter() {
        return this.crashReporter;
    }

    @NotNull
    public final List<DedupeStage> getDedupeStages() {
        return this.dedupeStages;
    }

    @NotNull
    public final String getHashedDirectedId() {
        return this.hashedDirectedId;
    }

    @NotNull
    public final Logger getLogger() {
        return this.logger;
    }

    @NotNull
    public final Metrics getMetrics() {
        return this.metrics;
    }

    public final boolean getOnlyScanCamera() {
        return this.onlyScanCamera;
    }

    public final int getScanBatchSize() {
        return this.scanBatchSize;
    }

    public final boolean getUseMonitor() {
        return this.useMonitor;
    }

    public final boolean getUseVisualDigest() {
        return this.useVisualDigest;
    }

    public /* synthetic */ DiscoveryConfiguration(Context context, String str, List list, Metrics metrics, CDClient cDClient, Logger logger, CrashReporter crashReporter, boolean z, boolean z2, boolean z3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, str, list, metrics, cDClient, (i & 32) != 0 ? null : logger, (i & 64) != 0 ? null : crashReporter, (i & 128) != 0 ? true : z, (i & 256) != 0 ? false : z2, (i & 512) != 0 ? false : z3);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DiscoveryConfiguration(@NotNull Context applicationContext, @NotNull String accountId, @NotNull List<DedupeStage> dedupeDestages, @NotNull Metrics metrics, @NotNull CDClient cdClient, @Nullable Logger logger, @Nullable CrashReporter crashReporter, boolean z, boolean z2, boolean z3) {
        this(applicationContext, accountId, 200, dedupeDestages, metrics, logger != null ? logger : new AndroidLogger(), cdClient, crashReporter, z, z2, z3);
        Intrinsics.checkParameterIsNotNull(applicationContext, "applicationContext");
        Intrinsics.checkParameterIsNotNull(accountId, "accountId");
        Intrinsics.checkParameterIsNotNull(dedupeDestages, "dedupeDestages");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(cdClient, "cdClient");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DiscoveryConfiguration(@NotNull Context applicationContext, @NotNull String accountId, @NotNull List<DedupeStage> dedupeDestages, @NotNull Metrics metrics, @NotNull CDClient cdClient, @Nullable CrashReporter crashReporter) {
        this(applicationContext, accountId, 200, dedupeDestages, metrics, new AndroidLogger(), cdClient, crashReporter, true, false, false);
        Intrinsics.checkParameterIsNotNull(applicationContext, "applicationContext");
        Intrinsics.checkParameterIsNotNull(accountId, "accountId");
        Intrinsics.checkParameterIsNotNull(dedupeDestages, "dedupeDestages");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(cdClient, "cdClient");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DiscoveryConfiguration(@NotNull Context applicationContext, @NotNull String accountId, @NotNull List<DedupeStage> dedupeDestages, @NotNull Metrics metrics, @NotNull CDClient cdClient) {
        this(applicationContext, accountId, 200, dedupeDestages, metrics, new AndroidLogger(), cdClient, null, true, false, false);
        Intrinsics.checkParameterIsNotNull(applicationContext, "applicationContext");
        Intrinsics.checkParameterIsNotNull(accountId, "accountId");
        Intrinsics.checkParameterIsNotNull(dedupeDestages, "dedupeDestages");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(cdClient, "cdClient");
    }
}
