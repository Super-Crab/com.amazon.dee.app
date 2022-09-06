package com.amazon.alexa.fitness.api;

import com.dee.app.metrics.MetricsConstants;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: UserPreferenceStore.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0003H&¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/fitness/api/UserPreferenceStore;", "", MetricsConstants.Method.CACHE_GET, "", "key", "Lcom/amazon/alexa/fitness/api/UserPreferenceKey;", "set", "", "value", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface UserPreferenceStore {
    boolean get(@NotNull UserPreferenceKey userPreferenceKey);

    void set(@NotNull UserPreferenceKey userPreferenceKey, boolean z);
}
