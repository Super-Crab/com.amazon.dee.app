package com.amazon.alexa;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.client.core.device.PersistentStorage;
import java.util.Map;
import java.util.Set;
/* compiled from: PreferencesStorage.java */
/* loaded from: classes.dex */
public class EKZ implements PersistentStorage {
    public final SharedPreferences zZm;

    /* compiled from: PreferencesStorage.java */
    /* loaded from: classes.dex */
    private static class zZm implements PersistentStorage.Transaction {
        public final SharedPreferences.Editor zZm;

        public zZm(SharedPreferences.Editor editor) {
            this.zZm = editor;
        }

        @Override // com.amazon.alexa.client.core.device.PersistentStorage.Transaction
        public PersistentStorage.Transaction clear() {
            this.zZm.clear();
            return this;
        }

        @Override // com.amazon.alexa.client.core.device.PersistentStorage.Transaction
        public void commitAsynchronously() {
            this.zZm.apply();
        }

        @Override // com.amazon.alexa.client.core.device.PersistentStorage.Transaction
        public void commitSynchronously() {
            this.zZm.commit();
        }

        @Override // com.amazon.alexa.client.core.device.PersistentStorage.Transaction
        public PersistentStorage.Transaction remove(@NonNull String str) {
            this.zZm.remove(str);
            return this;
        }

        @Override // com.amazon.alexa.client.core.device.PersistentStorage.Transaction
        public PersistentStorage.Transaction set(@NonNull String str, @Nullable String str2) {
            this.zZm.putString(str, str2);
            return this;
        }

        @Override // com.amazon.alexa.client.core.device.PersistentStorage.Transaction
        public PersistentStorage.Transaction set(@NonNull String str, boolean z) {
            this.zZm.putBoolean(str, z);
            return this;
        }

        @Override // com.amazon.alexa.client.core.device.PersistentStorage.Transaction
        public PersistentStorage.Transaction set(@NonNull String str, long j) {
            this.zZm.putLong(str, j);
            return this;
        }
    }

    public EKZ(SharedPreferences sharedPreferences) {
        this.zZm = sharedPreferences;
    }

    @Override // com.amazon.alexa.client.core.device.PersistentStorage
    public boolean contains(@NonNull String str) {
        return this.zZm.contains(str);
    }

    @Override // com.amazon.alexa.client.core.device.PersistentStorage
    @SuppressLint({"CommitPrefEdits"})
    public PersistentStorage.Transaction edit() {
        return new zZm(this.zZm.edit());
    }

    @Override // com.amazon.alexa.client.core.device.PersistentStorage
    public Map<String, ?> getAll() {
        return this.zZm.getAll();
    }

    @Override // com.amazon.alexa.client.core.device.PersistentStorage
    public boolean getBoolean(@NonNull String str) {
        return this.zZm.getBoolean(str, false);
    }

    @Override // com.amazon.alexa.client.core.device.PersistentStorage
    public Set<String> getKeys() {
        return this.zZm.getAll().keySet();
    }

    @Override // com.amazon.alexa.client.core.device.PersistentStorage
    public long getLong(@NonNull String str, long j) {
        return this.zZm.getLong(str, j);
    }

    @Override // com.amazon.alexa.client.core.device.PersistentStorage
    @Nullable
    public String getString(@NonNull String str, @Nullable String str2) {
        return this.zZm.getString(str, str2);
    }

    @Override // com.amazon.alexa.client.core.device.PersistentStorage
    public boolean getBoolean(@NonNull String str, boolean z) {
        return this.zZm.getBoolean(str, z);
    }

    @Override // com.amazon.alexa.client.core.device.PersistentStorage
    @Nullable
    public String getString(@NonNull String str) {
        return this.zZm.getString(str, null);
    }
}
