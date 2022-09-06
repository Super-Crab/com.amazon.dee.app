package com.amazon.leaderselection;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.leaderselection.t;
/* loaded from: classes12.dex */
class y implements t {
    private final Context a;
    private final String b;
    private SharedPreferences c;

    /* loaded from: classes12.dex */
    private static class a implements t.a {
        private final SharedPreferences.Editor a;

        a(SharedPreferences.Editor editor) {
            this.a = editor;
        }

        @Override // com.amazon.leaderselection.t.a
        public t.a a(@NonNull String str, @Nullable String str2) {
            this.a.putString(str, str2);
            return this;
        }

        @Override // com.amazon.leaderselection.t.a
        public t.a a(@NonNull String str, boolean z) {
            this.a.putBoolean(str, z);
            return this;
        }

        @Override // com.amazon.leaderselection.t.a
        public void a() {
            this.a.apply();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public y(Context context, String str) {
        this.a = context;
        this.b = str;
    }

    private SharedPreferences b() {
        if (this.c == null) {
            this.c = this.a.getSharedPreferences(this.b, 0);
        }
        return this.c;
    }

    @Override // com.amazon.leaderselection.t
    @SuppressLint({"CommitPrefEdits"})
    public t.a a() {
        return new a(b().edit());
    }

    @Override // com.amazon.leaderselection.t
    @Nullable
    public String a(@NonNull String str, @Nullable String str2) {
        return b().getString(str, str2);
    }

    @Override // com.amazon.leaderselection.t
    public boolean a(@NonNull String str, boolean z) {
        return b().getBoolean(str, z);
    }
}
