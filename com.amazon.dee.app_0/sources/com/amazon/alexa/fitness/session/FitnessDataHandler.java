package com.amazon.alexa.fitness.session;

import com.amazon.alexa.accessory.repositories.fitness.FitnessDataSource;
import com.amazon.alexa.fitness.sdk.EchoBudSampleData;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessDataHandler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/fitness/session/FitnessDataHandler;", "", "process", "Lcom/amazon/alexa/fitness/sdk/EchoBudSampleData;", "timestamp", "", "fitnessDataSource", "Lcom/amazon/alexa/accessory/repositories/fitness/FitnessDataSource;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface FitnessDataHandler {
    @Nullable
    EchoBudSampleData process(long j, @NotNull FitnessDataSource fitnessDataSource);
}
