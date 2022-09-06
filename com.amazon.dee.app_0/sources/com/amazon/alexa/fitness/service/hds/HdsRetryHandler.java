package com.amazon.alexa.fitness.service.hds;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.fitness.service.Startable;
import kotlin.Metadata;
/* compiled from: HdsRetryHandler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H'J\b\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/HdsRetryHandler;", "Lcom/amazon/alexa/fitness/service/Startable;", "retryPendingUploads", "", "start", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface HdsRetryHandler extends Startable {
    @VisibleForTesting(otherwise = 2)
    void retryPendingUploads();

    @Override // com.amazon.alexa.fitness.service.Startable
    void start();
}
