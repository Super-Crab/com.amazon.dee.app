package com.amazon.alexa.voice.handsfree.initialization;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.voice.handsfree.metrics.permission.PermissionCheckJobIntentService;
/* loaded from: classes11.dex */
public class InitializationService extends Service {
    private static final String MTK_MICROPHONE_PERMISSION_EXTRA_KEY = "VOICE_APP_MICROPHONE_PERMISSION_STATE";
    private static final String MTK_MICROPHONE_PERMISSION_EXTRA_VALUE = "MICROPHONE_PERMISSION_NEEDED";
    private static final String TAG = InitializationService.class.getSimpleName();
    private final Initializer mInitializer;

    public InitializationService() {
        this(InitializerProvider.getInitializer());
    }

    void enqueuePermissionCheck(@NonNull Context context, @NonNull Intent intent) {
        PermissionCheckJobIntentService.enqueueWork(context, intent);
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(@NonNull Intent intent) {
        Log.d(TAG, "onCreate: Initialize via initialization service.");
        this.mInitializer.initialize(this);
        if (intent.hasExtra(MTK_MICROPHONE_PERMISSION_EXTRA_KEY)) {
            String stringExtra = intent.getStringExtra(MTK_MICROPHONE_PERMISSION_EXTRA_KEY);
            String str = TAG;
            Log.d(str, "onBind: the extras is " + stringExtra);
            if (!stringExtra.equals(MTK_MICROPHONE_PERMISSION_EXTRA_VALUE)) {
                return null;
            }
            Log.d(TAG, String.format("received %s MTK microphone permission extra value", MTK_MICROPHONE_PERMISSION_EXTRA_VALUE));
            enqueuePermissionCheck(this, intent);
            return null;
        }
        return null;
    }

    @VisibleForTesting
    InitializationService(@NonNull Initializer initializer) {
        this.mInitializer = initializer;
    }
}
