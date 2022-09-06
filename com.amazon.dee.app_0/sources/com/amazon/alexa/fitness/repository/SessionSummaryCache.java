package com.amazon.alexa.fitness.repository;

import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.fitness.service.hds.model.SessionSummary;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SessionSummaryCache.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H&J\u001a\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005H&¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/fitness/repository/SessionSummaryCache;", "", "deletePendingSessionUpload", "", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "", "getPendingSessions", "", "Lcom/amazon/alexa/fitness/service/hds/model/SessionSummary;", "savePendingSessionUpload", "sessionSummary", "lastException", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface SessionSummaryCache {
    void deletePendingSessionUpload(@NotNull String str);

    @NotNull
    Set<SessionSummary> getPendingSessions();

    void savePendingSessionUpload(@NotNull SessionSummary sessionSummary, @Nullable String str);
}
