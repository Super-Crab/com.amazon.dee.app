package com.amazon.dee.app.storage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import java.util.Map;
import java.util.Set;
/* loaded from: classes12.dex */
public class PreferencesStorage implements PersistentStorage {
    SharedPreferences preferences;
    JsonConverter serializer;

    /* loaded from: classes12.dex */
    public static class Factory implements PersistentStorage.Factory {
        private final Context context;
        private final JsonConverter serializer;

        public Factory(Context context, JsonConverter jsonConverter) {
            this.context = context;
            this.serializer = jsonConverter;
        }

        @Override // com.amazon.alexa.protocols.storage.PersistentStorage.Factory
        @NonNull
        public PersistentStorage create(@NonNull String str) {
            return new PreferencesStorage(this.context.getSharedPreferences(str, 0), this.serializer);
        }
    }

    /* loaded from: classes12.dex */
    static class PreferencesTransaction implements PersistentStorage.Transaction {
        SharedPreferences.Editor editor;
        JsonConverter serializer;

        PreferencesTransaction(SharedPreferences.Editor editor, JsonConverter jsonConverter) {
            this.editor = editor;
            this.serializer = jsonConverter;
        }

        @Override // com.amazon.alexa.protocols.storage.PersistentStorage.Transaction
        public void commit() {
            this.editor.apply();
        }

        @Override // com.amazon.alexa.protocols.storage.PersistentStorage.Transaction
        public PersistentStorage.Transaction remove(@NonNull String str) {
            this.editor.remove(str);
            return this;
        }

        @Override // com.amazon.alexa.protocols.storage.PersistentStorage.Transaction
        public PersistentStorage.Transaction set(@NonNull String str, @Nullable String str2) {
            this.editor.putString(str, str2);
            return this;
        }

        @Override // com.amazon.alexa.protocols.storage.PersistentStorage.Transaction
        public PersistentStorage.Transaction set(@NonNull String str, @Nullable Set<String> set) {
            this.editor.putStringSet(str, set);
            return this;
        }

        @Override // com.amazon.alexa.protocols.storage.PersistentStorage.Transaction
        public PersistentStorage.Transaction set(@NonNull String str, boolean z) {
            this.editor.putBoolean(str, z);
            return this;
        }

        @Override // com.amazon.alexa.protocols.storage.PersistentStorage.Transaction
        public PersistentStorage.Transaction set(@NonNull String str, long j) {
            this.editor.putLong(str, j);
            return this;
        }

        @Override // com.amazon.alexa.protocols.storage.PersistentStorage.Transaction
        public PersistentStorage.Transaction set(@NonNull String str, Map<String, String> map) {
            this.editor.putString(str, this.serializer.toJson(map));
            return this;
        }
    }

    public PreferencesStorage(SharedPreferences sharedPreferences, JsonConverter jsonConverter) {
        this.preferences = sharedPreferences;
        this.serializer = jsonConverter;
    }

    @Override // com.amazon.alexa.protocols.storage.PersistentStorage
    public boolean contains(@NonNull String str) {
        return this.preferences.contains(str);
    }

    @Override // com.amazon.alexa.protocols.storage.PersistentStorage
    @SuppressLint({"CommitPrefEdits"})
    public PersistentStorage.Transaction edit() {
        return new PreferencesTransaction(this.preferences.edit(), this.serializer);
    }

    @Override // com.amazon.alexa.protocols.storage.PersistentStorage
    public boolean getBoolean(@NonNull String str) {
        return this.preferences.getBoolean(str, false);
    }

    @Override // com.amazon.alexa.protocols.storage.PersistentStorage
    public long getLong(@NonNull String str, long j) {
        return this.preferences.getLong(str, j);
    }

    @Override // com.amazon.alexa.protocols.storage.PersistentStorage
    @Nullable
    public String getString(@NonNull String str, @Nullable String str2) {
        return this.preferences.getString(str, str2);
    }

    @Override // com.amazon.alexa.protocols.storage.PersistentStorage
    public Map<String, String> getStringMap(@NonNull String str, @Nullable Map<String, String> map) {
        String string = this.preferences.getString(str, null);
        return TextUtils.isEmpty(string) ? map : (Map) this.serializer.fromJson(string, Map.class);
    }

    @Override // com.amazon.alexa.protocols.storage.PersistentStorage
    @Nullable
    public Set<String> getStringSet(@NonNull String str, @Nullable Set<String> set) {
        return this.preferences.getStringSet(str, set);
    }

    @Override // com.amazon.alexa.protocols.storage.PersistentStorage
    public boolean getBoolean(@NonNull String str, boolean z) {
        return this.preferences.getBoolean(str, z);
    }

    @Override // com.amazon.alexa.protocols.storage.PersistentStorage
    @Nullable
    public String getString(@NonNull String str) {
        return this.preferences.getString(str, null);
    }
}
