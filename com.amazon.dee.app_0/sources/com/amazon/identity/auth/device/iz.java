package com.amazon.identity.auth.device;

import android.annotation.SuppressLint;
import android.content.Intent;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class iz {
    @SuppressLint({"InlinedApi"})
    public static Intent createIntent() {
        Intent intent = new Intent();
        intent.setFlags(32);
        return intent;
    }

    public static Intent dy(String str) {
        Intent createIntent = createIntent();
        createIntent.setAction(str);
        return createIntent;
    }
}
