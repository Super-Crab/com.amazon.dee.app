package com.amazon.alexa.client.core.device;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Map;
import java.util.Set;
/* loaded from: classes6.dex */
public interface PersistentStorage {

    /* loaded from: classes6.dex */
    public interface Transaction {
        Transaction clear();

        void commitAsynchronously();

        void commitSynchronously();

        Transaction remove(@NonNull String str);

        Transaction set(@NonNull String str, long j);

        Transaction set(@NonNull String str, @Nullable String str2);

        Transaction set(@NonNull String str, boolean z);
    }

    boolean contains(@NonNull String str);

    @CheckResult
    Transaction edit();

    Map<String, ?> getAll();

    boolean getBoolean(@NonNull String str);

    boolean getBoolean(@NonNull String str, boolean z);

    Set<String> getKeys();

    long getLong(@NonNull String str, long j);

    @Nullable
    String getString(@NonNull String str);

    @Nullable
    String getString(@NonNull String str, @Nullable String str2);
}
