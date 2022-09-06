package com.amazon.alexa.voice.routing.parameters;

import android.content.Intent;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
/* loaded from: classes11.dex */
public class ReferrerProvider {
    public static final String REFERER_KEY = "referer";
    private String referrer = "UNKNOWN";
    private String lastKnownReferrer = "UNKNOWN";

    @VisibleForTesting
    String getReferrer() {
        return this.referrer;
    }

    public String provideAndReset() {
        String str = this.referrer;
        reset();
        return str;
    }

    public String provideLastKnownReferrer() {
        return this.lastKnownReferrer;
    }

    public void reset() {
        this.referrer = "UNKNOWN";
    }

    public void update(Intent intent) {
        String stringExtra = intent.getStringExtra("referer");
        if (!TextUtils.isEmpty(stringExtra)) {
            this.referrer = stringExtra;
            this.lastKnownReferrer = stringExtra;
        }
    }
}
