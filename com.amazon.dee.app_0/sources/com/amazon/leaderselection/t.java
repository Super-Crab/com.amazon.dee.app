package com.amazon.leaderselection;

import com.amazon.alexa.client.annotations.CheckResult;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.Nullable;
/* loaded from: classes12.dex */
interface t {

    /* loaded from: classes12.dex */
    public interface a {
        a a(@NonNull String str, @Nullable String str2);

        a a(@NonNull String str, boolean z);

        void a();
    }

    @CheckResult
    a a();

    @Nullable
    String a(@NonNull String str, @Nullable String str2);

    boolean a(@NonNull String str, boolean z);
}
