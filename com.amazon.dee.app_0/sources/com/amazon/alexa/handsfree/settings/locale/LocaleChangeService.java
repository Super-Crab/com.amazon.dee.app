package com.amazon.alexa.handsfree.settings.locale;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.JobIntentService;
import androidx.core.app.SafeDequeueJobIntentService;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes8.dex */
public class LocaleChangeService extends SafeDequeueJobIntentService {
    public static final String EXTRA_LOCALE = "com.amazon.alexa.handsfree.LocaleChangeService.locale";
    private static final int JOB_ID = 300010;
    private static final String TAG = LocaleChangeService.class.getSimpleName();
    private final Initializer mInitializer;
    private LocaleChangeUpdater mLocaleChangeUpdater;

    public LocaleChangeService() {
        this.mInitializer = InitializerProvider.getInitializer();
    }

    public static void enqueueWork(@NonNull Context context, @NonNull Intent intent) {
        JobIntentService.enqueueWork(context, LocaleChangeService.class, (int) JOB_ID, intent);
    }

    @Override // androidx.core.app.SafeDequeueJobIntentService, androidx.core.app.JobIntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mInitializer.initialize(this);
        this.mLocaleChangeUpdater = new LocaleChangeUpdater(this);
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(@NonNull Intent intent) {
        Log.d(TAG, "onHandleWork");
        String[] stringArrayExtra = intent.getStringArrayExtra(EXTRA_LOCALE);
        if (stringArrayExtra == null) {
            Log.e(TAG, "onHandleWork: the locale array is null.");
        } else {
            this.mLocaleChangeUpdater.setVoiceAppLocale(stringArrayExtra);
        }
    }

    @VisibleForTesting
    LocaleChangeService(@NonNull Initializer initializer, @NonNull LocaleChangeUpdater localeChangeUpdater) {
        this.mInitializer = initializer;
        this.mLocaleChangeUpdater = localeChangeUpdater;
    }
}
