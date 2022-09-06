package com.amazon.dee.app.ui.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.protocols.usermessage.UserMessageService;
import com.amazon.dee.app.R;
import com.amazon.dee.app.services.usermessage.DefaultUserMessageService;
/* loaded from: classes12.dex */
public final class MissingBrowserDialog {
    private MissingBrowserDialog() {
        throw new UnsupportedOperationException();
    }

    static /* synthetic */ void lambda$show$0() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$show$1(Activity activity) {
        Intent intent = new Intent("android.settings.APPLICATION_SETTINGS");
        intent.addFlags(268435456);
        try {
            activity.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            Intent intent2 = new Intent("android.settings.SETTINGS");
            intent2.addFlags(268435456);
            activity.startActivity(intent2);
        }
    }

    public static void show(@NonNull Activity activity) {
        show(activity, new DefaultUserMessageService(activity));
    }

    @VisibleForTesting
    static void show(@NonNull final Activity activity, UserMessageService userMessageService) {
        userMessageService.message(R.string.missing_default_browser_error_message).withCancel(R.string.cancel_button, $$Lambda$MissingBrowserDialog$ZPVrWT9z2xounBfk6Kh2nXVgOPM.INSTANCE).withAction(R.string.settings_button, new Runnable() { // from class: com.amazon.dee.app.ui.util.-$$Lambda$MissingBrowserDialog$PJCN50pMKiFc3dbHs9qyQNznVnw
            @Override // java.lang.Runnable
            public final void run() {
                MissingBrowserDialog.lambda$show$1(activity);
            }
        }).show();
    }
}
