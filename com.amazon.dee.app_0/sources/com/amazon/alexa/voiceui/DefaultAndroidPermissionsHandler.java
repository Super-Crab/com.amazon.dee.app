package com.amazon.alexa.voiceui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.voice.ui.permissions.AndroidPermissionsChecker;
import com.amazon.alexa.voice.ui.permissions.AndroidPermissionsRequester;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes11.dex */
class DefaultAndroidPermissionsHandler implements AndroidPermissionsRequester, AndroidPermissionsChecker {
    @VisibleForTesting
    static final String ACTION_REQUEST_PERMISSIONS = "com.amazon.alexa.intent.action.REQUEST_REQUIRED_PERMISSIONS";
    private static final String[] MINIMUM_PERMISSIONS = {"android.permission.RECORD_AUDIO"};
    private final Context context;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public DefaultAndroidPermissionsHandler(Context context) {
        this.context = context;
    }

    private boolean hasPermissions(@NonNull String[] strArr) {
        int i = Build.VERSION.SDK_INT;
        for (String str : strArr) {
            if (ContextCompat.checkSelfPermission(this.context, str) != 0) {
                return false;
            }
        }
        return true;
    }

    @Override // com.amazon.alexa.voice.ui.permissions.AndroidPermissionsChecker
    public boolean hasMinimumRequiredPermission() {
        return hasPermissions(MINIMUM_PERMISSIONS);
    }

    @Override // com.amazon.alexa.voice.ui.permissions.AndroidPermissionsRequester
    public void requestPermissions() {
        Intent intent = new Intent(ACTION_REQUEST_PERMISSIONS);
        intent.setPackage(this.context.getPackageName());
        this.context.startActivity(intent);
    }
}
