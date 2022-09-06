package com.amazon.alexa.fitness.repository;

import com.amazon.alexa.fitness.sdk.AccessoryFitnessSession;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessSessionRepository.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H&¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/fitness/repository/FitnessSessionRepository;", "", "deleteAccessoryFitnessSession", "", "getAccessoryFitnessSession", "Lcom/amazon/alexa/fitness/sdk/AccessoryFitnessSession;", "saveAccessoryFitnessSession", "accessoryFitnessSession", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface FitnessSessionRepository {
    void deleteAccessoryFitnessSession();

    @Nullable
    AccessoryFitnessSession getAccessoryFitnessSession();

    void saveAccessoryFitnessSession(@NotNull AccessoryFitnessSession accessoryFitnessSession);
}
