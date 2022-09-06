package com.amazon.dee.app.services.usermessage;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AlertDialog;
import com.amazon.alexa.protocols.usermessage.UserMessageService;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.usermessage.DefaultUserMessageService;
import java.lang.ref.WeakReference;
/* loaded from: classes12.dex */
public class DefaultUserMessageService implements UserMessageService {
    private static final String LOG_TAG = "DefaultUserMessageService";
    private final WeakReference<Activity> weakActivity;

    public DefaultUserMessageService(@Nullable Activity activity) {
        this.weakActivity = new WeakReference<>(activity);
    }

    @Override // com.amazon.alexa.protocols.usermessage.UserMessageService
    @NonNull
    public UserMessageService.MessageBuilder message(@StringRes int i) {
        return new AlertDialogMessageBuilder(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class AlertDialogMessageBuilder implements UserMessageService.MessageBuilder {
        Runnable actionRunnable;
        int actionTextId;
        @VisibleForTesting
        AlertDialog.Builder builder;
        Runnable cancelRunnable;
        int cancelTextId;
        Runnable dismissRunnable;
        CharSequence text;
        int textId;

        AlertDialogMessageBuilder(@StringRes int i) {
            this.textId = i;
        }

        static /* synthetic */ void lambda$configureAlertDialog$1(DialogInterface dialogInterface, int i) {
        }

        @VisibleForTesting
        void configureAlertDialog(AlertDialog.Builder builder) {
            if (this.dismissRunnable != null) {
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.amazon.dee.app.services.usermessage.-$$Lambda$DefaultUserMessageService$AlertDialogMessageBuilder$fQzGCDuQscXzmuqGQvv1OAkKDu8
                    @Override // android.content.DialogInterface.OnDismissListener
                    public final void onDismiss(DialogInterface dialogInterface) {
                        DefaultUserMessageService.AlertDialogMessageBuilder.this.lambda$configureAlertDialog$0$DefaultUserMessageService$AlertDialogMessageBuilder(dialogInterface);
                    }
                });
            }
            if (this.actionRunnable == null) {
                builder.setPositiveButton(17039370, $$Lambda$DefaultUserMessageService$AlertDialogMessageBuilder$k1rj9Ujdzxxj_WYpEWaAmBNOn2g.INSTANCE);
            } else {
                builder.setPositiveButton(this.actionTextId, new DialogInterface.OnClickListener() { // from class: com.amazon.dee.app.services.usermessage.-$$Lambda$DefaultUserMessageService$AlertDialogMessageBuilder$Cy4vkWA3xFpyavkylAXG-kERvMc
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        DefaultUserMessageService.AlertDialogMessageBuilder.this.lambda$configureAlertDialog$2$DefaultUserMessageService$AlertDialogMessageBuilder(dialogInterface, i);
                    }
                });
            }
            if (this.cancelRunnable != null) {
                builder.setNegativeButton(this.cancelTextId, new DialogInterface.OnClickListener() { // from class: com.amazon.dee.app.services.usermessage.-$$Lambda$DefaultUserMessageService$AlertDialogMessageBuilder$dJLpnebxlk-u751YCGuEG_fZdGw
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        DefaultUserMessageService.AlertDialogMessageBuilder.this.lambda$configureAlertDialog$3$DefaultUserMessageService$AlertDialogMessageBuilder(dialogInterface, i);
                    }
                });
            }
        }

        @VisibleForTesting
        void configureAlertDialogMessage(AlertDialog.Builder builder) {
            CharSequence charSequence = this.text;
            if (charSequence != null) {
                builder.setMessage(charSequence);
            } else {
                builder.setMessage(this.textId);
            }
        }

        public /* synthetic */ void lambda$configureAlertDialog$0$DefaultUserMessageService$AlertDialogMessageBuilder(DialogInterface dialogInterface) {
            this.dismissRunnable.run();
        }

        public /* synthetic */ void lambda$configureAlertDialog$2$DefaultUserMessageService$AlertDialogMessageBuilder(DialogInterface dialogInterface, int i) {
            this.actionRunnable.run();
        }

        public /* synthetic */ void lambda$configureAlertDialog$3$DefaultUserMessageService$AlertDialogMessageBuilder(DialogInterface dialogInterface, int i) {
            this.cancelRunnable.run();
        }

        @Override // com.amazon.alexa.protocols.usermessage.UserMessageService.MessageBuilder
        public void show() {
            Activity activity = (Activity) DefaultUserMessageService.this.weakActivity.get();
            if (activity == null || activity.isFinishing()) {
                Log.w(DefaultUserMessageService.LOG_TAG, "Failed to display dialog, activity is null");
                return;
            }
            this.builder = new AlertDialog.Builder(activity);
            configureAlertDialog(this.builder);
            try {
                configureAlertDialogMessage(this.builder);
                this.builder.show();
            } catch (WindowManager.BadTokenException e) {
                String str = DefaultUserMessageService.LOG_TAG;
                Log.e(str, "Caught bad token exception trying to show dialog: " + e);
            }
        }

        @Override // com.amazon.alexa.protocols.usermessage.UserMessageService.MessageBuilder
        @NonNull
        public UserMessageService.MessageBuilder withAction(@StringRes int i, @NonNull Runnable runnable) {
            this.actionTextId = i;
            this.actionRunnable = runnable;
            return this;
        }

        @Override // com.amazon.alexa.protocols.usermessage.UserMessageService.MessageBuilder
        @NonNull
        public UserMessageService.MessageBuilder withCancel(@StringRes int i, @NonNull Runnable runnable) {
            this.cancelTextId = i;
            this.cancelRunnable = runnable;
            return this;
        }

        @Override // com.amazon.alexa.protocols.usermessage.UserMessageService.MessageBuilder
        @NonNull
        public UserMessageService.MessageBuilder withDismiss(@NonNull Runnable runnable) {
            this.dismissRunnable = runnable;
            return this;
        }

        AlertDialogMessageBuilder(CharSequence charSequence) {
            this.text = charSequence;
        }
    }

    @Override // com.amazon.alexa.protocols.usermessage.UserMessageService
    @NonNull
    public UserMessageService.MessageBuilder message(CharSequence charSequence) {
        return new AlertDialogMessageBuilder(charSequence);
    }
}
