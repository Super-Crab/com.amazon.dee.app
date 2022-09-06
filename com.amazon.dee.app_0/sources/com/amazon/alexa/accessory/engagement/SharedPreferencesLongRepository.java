package com.amazon.alexa.accessory.engagement;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.engagement.LongRepository;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
/* loaded from: classes.dex */
final class SharedPreferencesLongRepository implements LongRepository {
    private final SharedPreferences sharedPreferences;

    /* loaded from: classes.dex */
    static final class Factory implements LongRepository.Factory {
        private final Context context;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Factory(Context context) {
            this.context = context;
        }

        @Override // com.amazon.alexa.accessory.engagement.LongRepository.Factory
        public LongRepository create(String str) {
            return new SharedPreferencesLongRepository(this.context, str);
        }
    }

    SharedPreferencesLongRepository(Context context, String str) {
        this(context.getSharedPreferences(str, 0));
    }

    @Override // com.amazon.alexa.accessory.engagement.LongRepository
    public boolean contains(@NonNull String str) {
        Preconditions.notNull(str, "key");
        return this.sharedPreferences.contains(str);
    }

    @Override // com.amazon.alexa.accessory.engagement.LongRepository
    public long get(@NonNull String str) {
        Preconditions.notNull(str, "key");
        if (contains(str)) {
            return this.sharedPreferences.getLong(str, 0L);
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("No value associated to ", str));
    }

    @Override // com.amazon.alexa.accessory.engagement.LongRepository
    @SuppressLint({"ApplySharedPref"})
    public void put(@NonNull String str, long j) {
        Objects.requireNonNull(str, "key must be non-null.");
        this.sharedPreferences.edit().putLong(str, j).commit();
    }

    @VisibleForTesting
    SharedPreferencesLongRepository(SharedPreferences sharedPreferences) {
        this.sharedPreferences = (SharedPreferences) Objects.requireNonNull(sharedPreferences);
    }
}
