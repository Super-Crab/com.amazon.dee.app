package com.amazon.alexa.protocols.storage;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Map;
import java.util.Set;
/* loaded from: classes9.dex */
public interface PersistentStorage {

    /* loaded from: classes9.dex */
    public interface Factory {
        @NonNull
        PersistentStorage create(@NonNull String str);
    }

    /* loaded from: classes9.dex */
    public interface Transaction {
        void commit();

        Transaction remove(@NonNull String str);

        Transaction set(@NonNull String str, long j);

        Transaction set(@NonNull String str, @Nullable String str2);

        Transaction set(@NonNull String str, Map<String, String> map);

        Transaction set(@NonNull String str, @Nullable Set<String> set);

        Transaction set(@NonNull String str, boolean z);
    }

    boolean contains(@NonNull String str);

    @CheckResult
    Transaction edit();

    boolean getBoolean(@NonNull String str);

    boolean getBoolean(@NonNull String str, boolean z);

    long getLong(@NonNull String str, long j);

    @Nullable
    String getString(@NonNull String str);

    @Nullable
    String getString(@NonNull String str, @Nullable String str2);

    Map<String, String> getStringMap(@NonNull String str, @Nullable Map<String, String> map);

    @Nullable
    Set<String> getStringSet(@NonNull String str, @Nullable Set<String> set);
}
