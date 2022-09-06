package com.amazon.identity.auth.device;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class gp {
    private static final String TAG = "com.amazon.identity.auth.device.gp";
    private final SharedPreferences mSharedPrefs;
    private final String oK;

    @Deprecated
    public gp(Context context, String str) {
        this(context, str, (byte) 0);
    }

    @TargetApi(26)
    public static gp l(Context context, String str) {
        if (new ds(context).dz()) {
            io.i(TAG, "Create DE shared preference, OS supports direct boot.");
            return new gp(context.createDeviceProtectedStorageContext(), str);
        }
        return new gp(context, str);
    }

    @SuppressLint({"CommitPrefEdits"})
    public boolean U(String str, String str2) {
        boolean commit = this.mSharedPrefs.edit().putString(str, str2).commit();
        if (!commit) {
            io.e(TAG, String.format("Failed to set key %s in the local key value store %s", str, this.oK));
        }
        return commit;
    }

    public boolean a(String str, Boolean bool) {
        return a(this.mSharedPrefs.edit().putBoolean(str, bool.booleanValue()));
    }

    @SuppressLint({"CommitPrefEdits"})
    public boolean b(String str, Boolean bool) {
        boolean commit = this.mSharedPrefs.edit().putBoolean(str, bool.booleanValue()).commit();
        if (!commit) {
            io.e(TAG, String.format("Failed to set key %s in the local key value store %s", str, this.oK));
        }
        return commit;
    }

    public boolean contains(String str) {
        return this.mSharedPrefs.contains(str);
    }

    public String cs(String str) {
        return ct(str);
    }

    public String ct(String str) {
        return this.mSharedPrefs.getString(str, null);
    }

    public Boolean cu(String str) {
        return Boolean.valueOf(this.mSharedPrefs.getBoolean(str, false));
    }

    public long cv(String str) {
        return this.mSharedPrefs.getLong(str, 0L);
    }

    public boolean cw(String str) {
        boolean commit = this.mSharedPrefs.edit().remove(str).commit();
        if (!commit) {
            io.e(TAG, String.format("Failed to remove key: %s from value store %s", str, this.oK));
        }
        return commit;
    }

    public boolean e(String str, int i) {
        boolean commit = this.mSharedPrefs.edit().putInt(str, i).commit();
        if (!commit) {
            io.e(TAG, String.format("Failed to set key %s in the local key value store %s", str, this.oK));
        }
        return commit;
    }

    @SuppressLint({"CommitPrefEdits"})
    public boolean fE() {
        boolean commit = this.mSharedPrefs.edit().clear().commit();
        if (!commit) {
            io.e(TAG, String.format("Failed to clear the local key value store %s", this.oK));
        }
        return commit;
    }

    public int getIntValue(String str) {
        return this.mSharedPrefs.getInt(str, 0);
    }

    @Deprecated
    public gp(Context context, String str, byte b) {
        this.mSharedPrefs = context.getSharedPreferences(str, 0);
        this.oK = str;
    }

    @SuppressLint({"CommitPrefEdits"})
    public boolean a(String str, long j) {
        boolean commit = this.mSharedPrefs.edit().putLong(str, j).commit();
        if (!commit) {
            io.e(TAG, String.format("Failed to set key %s in the local key value store %s", str, this.oK));
        }
        return commit;
    }

    private boolean a(SharedPreferences.Editor editor) {
        for (int i = 0; i <= 2; i++) {
            if (editor.commit()) {
                return true;
            }
            io.e(TAG, "Commit failed retrying");
            try {
                Thread.sleep(15L);
            } catch (InterruptedException e) {
                io.e(TAG, "Retry sleep interrupted", e);
            }
        }
        return false;
    }
}
