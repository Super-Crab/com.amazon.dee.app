package com.amazon.alexa.fitness.service.hds;

import com.amazon.alexa.fitness.service.hds.model.SessionSummary;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: HdsClient.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/HdsClient;", "", "uploadSession", "", "sessionSummary", "Lcom/amazon/alexa/fitness/service/hds/model/SessionSummary;", "isRetry", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface HdsClient {
    void uploadSession(@NotNull SessionSummary sessionSummary, boolean z);
}
