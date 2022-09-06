package com.amazon.alexa.voice.handsfree.initialization;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes11.dex */
public class InitializationActivity extends Activity {
    private static final String TAG = InitializationActivity.class.getSimpleName();
    private Initializer mInitializer = InitializerProvider.getInitializer();

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Log.d(TAG, "onCreate: Initialize via initialization activity.");
        this.mInitializer.initialize(this);
        finish();
    }

    @VisibleForTesting
    void setInitializer(@NonNull Initializer initializer) {
        this.mInitializer = initializer;
    }
}
