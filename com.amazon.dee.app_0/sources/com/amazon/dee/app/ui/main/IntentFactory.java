package com.amazon.dee.app.ui.main;

import android.content.Context;
import android.content.Intent;
/* loaded from: classes12.dex */
public class IntentFactory {
    private Context context;

    public IntentFactory(Context context) {
        this.context = context;
    }

    public Intent createIntent(Class<?> cls) {
        return new Intent(this.context, cls);
    }
}
