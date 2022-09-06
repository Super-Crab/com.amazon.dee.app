package com.amazon.alexa.fitness.repository;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: RecoveryRepository.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0004\u001a\u00020\u0005H&J\n\u0010\t\u001a\u0004\u0018\u00010\bH&J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\bH&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\bH&¨\u0006\u000e"}, d2 = {"Lcom/amazon/alexa/fitness/repository/RecoveryRepository;", "", "deleteComponentState", "", "key", "", "deleteSession", "getComponentState", "", "getSession", "saveComponentState", "state", "saveSession", "session", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface RecoveryRepository {
    void deleteComponentState(@NotNull String str);

    void deleteSession();

    @Nullable
    byte[] getComponentState(@NotNull String str);

    @Nullable
    byte[] getSession();

    void saveComponentState(@NotNull String str, @NotNull byte[] bArr);

    void saveSession(@NotNull byte[] bArr);
}
