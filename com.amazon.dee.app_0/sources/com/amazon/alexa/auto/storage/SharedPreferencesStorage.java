package com.amazon.alexa.auto.storage;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import java.util.Map;
import java.util.Set;
/* loaded from: classes6.dex */
public class SharedPreferencesStorage implements PersistentStorage {
    private final SharedPreferences preferences;

    /* loaded from: classes6.dex */
    private static class PreferencesTransaction implements PersistentStorage.Transaction {
        private final SharedPreferences.Editor editor;

        PreferencesTransaction(SharedPreferences.Editor editor) {
            this.editor = editor;
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
        public PersistentStorage.Transaction set(@NonNull String str, long j) {
            this.editor.putLong(str, j);
            return this;
        }

        @Override // com.amazon.alexa.protocols.storage.PersistentStorage.Transaction
        public PersistentStorage.Transaction set(@NonNull String str, @Nullable String str2) {
            this.editor.putString(str, str2);
            return this;
        }

        @Override // com.amazon.alexa.protocols.storage.PersistentStorage.Transaction
        public PersistentStorage.Transaction set(@NonNull String str, Map<String, String> map) {
            throw new UnsupportedOperationException("setting a String-String Map is not supported in SharedPreferenceStorage");
        }

        @Override // com.amazon.alexa.protocols.storage.PersistentStorage.Transaction
        public PersistentStorage.Transaction set(@NonNull String str, @Nullable Set<String> set) {
            throw new UnsupportedOperationException("setting a String Set is not supported in SharedPreferencesStorage");
        }

        @Override // com.amazon.alexa.protocols.storage.PersistentStorage.Transaction
        public PersistentStorage.Transaction set(@NonNull String str, boolean z) {
            this.editor.putBoolean(str, z);
            return this;
        }
    }

    public SharedPreferencesStorage(SharedPreferences sharedPreferences) {
        this.preferences = sharedPreferences;
    }

    @Override // com.amazon.alexa.protocols.storage.PersistentStorage
    public boolean contains(@NonNull String str) {
        return this.preferences.contains(str);
    }

    @Override // com.amazon.alexa.protocols.storage.PersistentStorage
    @SuppressLint({"CommitPrefEdits"})
    public PersistentStorage.Transaction edit() {
        return new PreferencesTransaction(this.preferences.edit());
    }

    @Override // com.amazon.alexa.protocols.storage.PersistentStorage
    public boolean getBoolean(@NonNull String str) {
        return this.preferences.getBoolean(str, false);
    }

    @Override // com.amazon.alexa.protocols.storage.PersistentStorage
    public boolean getBoolean(@NonNull String str, boolean z) {
        return this.preferences.getBoolean(str, z);
    }

    @Override // com.amazon.alexa.protocols.storage.PersistentStorage
    public long getLong(@NonNull String str, long j) {
        return this.preferences.getLong(str, j);
    }

    @Override // com.amazon.alexa.protocols.storage.PersistentStorage
    @Nullable
    public String getString(@NonNull String str) {
        return this.preferences.getString(str, null);
    }

    @Override // com.amazon.alexa.protocols.storage.PersistentStorage
    @Nullable
    public String getString(@NonNull String str, @Nullable String str2) {
        return this.preferences.getString(str, str2);
    }

    @Override // com.amazon.alexa.protocols.storage.PersistentStorage
    public Map<String, String> getStringMap(@NonNull String str, @Nullable Map<String, String> map) {
        throw new UnsupportedOperationException("getStringMap is not supported in SharedPreferencesStorage");
    }

    @Override // com.amazon.alexa.protocols.storage.PersistentStorage
    @Nullable
    public Set<String> getStringSet(@NonNull String str, @Nullable Set<String> set) {
        throw new UnsupportedOperationException("getStringSet is not supported in SharedPreferencesStorage");
    }
}
