package com.amazon.alexa.fitness.service.afits;

import com.amazon.alexa.fitness.api.util.DateTime;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.AFITSMetrics;
import com.amazon.alexa.fitness.repository.SessionSummaryCache;
import com.amazon.alexa.fitness.sdk.sample.SampleStore;
import com.amazon.alexa.fitness.service.Startable;
import com.amazon.alexa.fitness.service.hds.model.SessionSummary;
import com.amazon.alexa.fitness.utils.MetricHelperKt;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.UUID;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: WorkoutSessionDeleteHandler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/alexa/fitness/service/afits/WorkoutSessionDeleteHandler;", "Lcom/amazon/alexa/fitness/service/Startable;", "sessionSummaryCache", "Lcom/amazon/alexa/fitness/repository/SessionSummaryCache;", "sampleStore", "Lcom/amazon/alexa/fitness/sdk/sample/SampleStore;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/fitness/repository/SessionSummaryCache;Lcom/amazon/alexa/fitness/sdk/sample/SampleStore;Lcom/amazon/alexa/fitness/logs/ILog;)V", "metrics", "Lcom/amazon/alexa/mobilytics/Mobilytics;", "kotlin.jvm.PlatformType", "start", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class WorkoutSessionDeleteHandler implements Startable {
    private final ILog log;
    private final Mobilytics metrics;
    private final SampleStore sampleStore;
    private final SessionSummaryCache sessionSummaryCache;

    @Inject
    public WorkoutSessionDeleteHandler(@NotNull SessionSummaryCache sessionSummaryCache, @NotNull SampleStore sampleStore, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(sessionSummaryCache, "sessionSummaryCache");
        Intrinsics.checkParameterIsNotNull(sampleStore, "sampleStore");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.sessionSummaryCache = sessionSummaryCache;
        this.sampleStore = sampleStore;
        this.log = log;
        this.metrics = (Mobilytics) GeneratedOutlineSupport1.outline20(Mobilytics.class);
    }

    @Override // com.amazon.alexa.fitness.service.Startable
    public void start() {
        ArrayList<UUID> arrayList = new ArrayList();
        for (SessionSummary sessionSummary : this.sessionSummaryCache.getPendingSessions()) {
            DateTime endTime = sessionSummary.getEndTime();
            if (endTime == null) {
                ILog.DefaultImpls.error$default(this.log, "WorkoutSessionDeleteHandler", "session end time is null, cannot decide expiry", null, 4, null);
            } else if (endTime.until(DateTime.Companion.now()).toHours() >= 24) {
                ILog iLog = this.log;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("session  ");
                outline107.append(sessionSummary.getSessionId());
                outline107.append(" expired, deleting from cache");
                ILog.DefaultImpls.info$default(iLog, "WorkoutSessionDeleteHandler", outline107.toString(), null, 4, null);
                arrayList.add(sessionSummary.getSessionId());
            }
        }
        for (UUID uuid : arrayList) {
            SessionSummaryCache sessionSummaryCache = this.sessionSummaryCache;
            String uuid2 = uuid.toString();
            Intrinsics.checkExpressionValueIsNotNull(uuid2, "it.toString()");
            sessionSummaryCache.deletePendingSessionUpload(uuid2);
            this.sampleStore.deleteAllSamples(uuid);
            Mobilytics metrics = this.metrics;
            Intrinsics.checkExpressionValueIsNotNull(metrics, "metrics");
            MetricHelperKt.recordCounter$default(metrics, AFITSMetrics.Upload.INSTANCE.getExpiredSessionPurged(), 0L, 2, null);
        }
    }
}
