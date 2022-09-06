package com.amazon.alexa.api;

import android.os.Bundle;
import android.util.Log;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes6.dex */
class bi implements u<String> {
    private static final String a = "bi";

    @Override // com.amazon.alexa.api.u
    /* renamed from: a */
    public Bundle toBundle(String str) {
        Preconditions.notNull(str, "Input string can't be null");
        Bundle bundle = new Bundle();
        bundle.putString("STRING_VALUE", str);
        return bundle;
    }

    @Override // com.amazon.alexa.api.u
    @Nullable
    /* renamed from: a */
    public String mo844createFromBundle(Bundle bundle) {
        Preconditions.notNull(bundle, "Bundle can't be null");
        if (bundle.containsKey("STRING_VALUE")) {
            return bundle.getString("STRING_VALUE");
        }
        Log.w(a, "Could not create String from Bundle");
        return null;
    }
}
