package com.amazon.alexa.handsfree.settings.voxsettings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.ResultReceiver;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.JobIntentService;
/* loaded from: classes8.dex */
public class VoxSettingsEnqueuer {
    @VisibleForTesting
    ResultReceiver getParceledReceiver(@NonNull ResultReceiver resultReceiver) {
        Parcel obtain = Parcel.obtain();
        resultReceiver.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        ResultReceiver resultReceiver2 = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return resultReceiver2;
    }

    public void getShowOnLockscreen(@NonNull Context context, @NonNull ResultReceiver resultReceiver) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.amazon.alexa.handsfree.voxconfig.EXTRA_RESULT_RECEIVER", getParceledReceiver(resultReceiver));
        sendIntent(VoxSettingsJobIntentService.REQUEST_GET, context, bundle);
    }

    @VisibleForTesting
    void sendIntent(@NonNull String str, @NonNull Context context, @Nullable Bundle bundle) {
        Intent intent = new Intent(context, VoxSettingsJobIntentService.class);
        intent.setAction(str);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        JobIntentService.enqueueWork(context, VoxSettingsJobIntentService.class, 30033, intent);
    }

    public void syncShowOnLockscreenPref(@NonNull Context context) {
        Bundle bundle = new Bundle();
        bundle.putInt("retryAttempt", 0);
        syncShowOnLockscreenPrefWithBundle(context, bundle);
    }

    public void syncShowOnLockscreenPrefWithBundle(@NonNull Context context, @NonNull Bundle bundle) {
        sendIntent(VoxSettingsJobIntentService.REQUEST_SET, context, bundle);
    }

    public void updateLegacyShowOnLockscreenPref(@NonNull Context context, boolean z, @NonNull ResultReceiver resultReceiver) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_KEY, z);
        bundle.putInt("retryAttempt", 0);
        bundle.putParcelable("com.amazon.alexa.handsfree.voxconfig.SET_RESULT_RECEIVER", getParceledReceiver(resultReceiver));
        syncShowOnLockscreenPrefWithBundle(context, bundle);
    }

    public void updateShowOnLockscreenPref(@NonNull Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_VALUE_KEY, str);
        bundle.putInt("retryAttempt", 0);
        syncShowOnLockscreenPrefWithBundle(context, bundle);
    }

    public void updateShowOnLockscreenPref(@NonNull Context context, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_KEY, z);
        bundle.putInt("retryAttempt", 0);
        syncShowOnLockscreenPrefWithBundle(context, bundle);
    }

    public void updateShowOnLockscreenPref(@NonNull Context context, String str, @NonNull ResultReceiver resultReceiver) {
        Bundle bundle = new Bundle();
        bundle.putString(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_VALUE_KEY, str);
        bundle.putInt("retryAttempt", 0);
        bundle.putParcelable("com.amazon.alexa.handsfree.voxconfig.SET_RESULT_RECEIVER", getParceledReceiver(resultReceiver));
        syncShowOnLockscreenPrefWithBundle(context, bundle);
    }
}
