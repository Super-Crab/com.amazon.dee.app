package com.amazon.alexa.dialog.impl;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.WindowManager;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.dialog.api.Dialog;
import java.lang.ref.WeakReference;
/* loaded from: classes7.dex */
public class Dialog implements com.amazon.alexa.dialog.api.Dialog {
    private static final String TAG = "Dialog";
    private static WeakReference<Activity> mainActivity;
    private static final Object mainActivityLock = new Object();
    @VisibleForTesting
    AlertDialog alertDialog;
    @VisibleForTesting
    Runnable cancelRunnable;
    @VisibleForTesting
    boolean cancelable = true;
    @VisibleForTesting
    Context context;
    @VisibleForTesting
    CharSequence message;
    @VisibleForTesting
    Runnable negativeRunnable;
    @VisibleForTesting
    CharSequence negativeText;
    @VisibleForTesting
    Runnable neutralRunnable;
    @VisibleForTesting
    CharSequence neutralText;
    @VisibleForTesting
    Runnable positiveRunnable;
    @VisibleForTesting
    CharSequence positiveText;
    @VisibleForTesting
    CharSequence title;

    /* loaded from: classes7.dex */
    public static class Builder implements Dialog.Builder {
        @VisibleForTesting
        Dialog dialog = new Dialog();

        @Override // com.amazon.alexa.dialog.api.Dialog.Builder
        public boolean show() {
            return mo1156build().show();
        }

        @Override // com.amazon.alexa.dialog.api.Dialog.Builder
        /* renamed from: build  reason: collision with other method in class */
        public Dialog mo1156build() {
            return this.dialog;
        }

        @Override // com.amazon.alexa.dialog.api.Dialog.Builder
        /* renamed from: setCancelable  reason: collision with other method in class */
        public Builder mo1157setCancelable(boolean z) {
            this.dialog.cancelable = z;
            return this;
        }

        @Override // com.amazon.alexa.dialog.api.Dialog.Builder
        /* renamed from: setContext  reason: collision with other method in class */
        public Builder mo1158setContext(Context context) {
            this.dialog.context = context;
            return this;
        }

        @Override // com.amazon.alexa.dialog.api.Dialog.Builder
        /* renamed from: setMessage  reason: collision with other method in class */
        public Builder mo1159setMessage(CharSequence charSequence) {
            this.dialog.message = charSequence;
            return this;
        }

        @Override // com.amazon.alexa.dialog.api.Dialog.Builder
        /* renamed from: setNegativeButton  reason: collision with other method in class */
        public Builder mo1160setNegativeButton(CharSequence charSequence, Runnable runnable) {
            Dialog dialog = this.dialog;
            dialog.negativeText = charSequence;
            dialog.negativeRunnable = runnable;
            return this;
        }

        @Override // com.amazon.alexa.dialog.api.Dialog.Builder
        /* renamed from: setNeutralButton  reason: collision with other method in class */
        public Builder mo1161setNeutralButton(CharSequence charSequence, Runnable runnable) {
            Dialog dialog = this.dialog;
            dialog.neutralText = charSequence;
            dialog.neutralRunnable = runnable;
            return this;
        }

        @Override // com.amazon.alexa.dialog.api.Dialog.Builder
        /* renamed from: setOnCancelAction  reason: collision with other method in class */
        public Builder mo1162setOnCancelAction(Runnable runnable) {
            this.dialog.cancelRunnable = runnable;
            return this;
        }

        @Override // com.amazon.alexa.dialog.api.Dialog.Builder
        /* renamed from: setPositiveButton  reason: collision with other method in class */
        public Builder mo1163setPositiveButton(CharSequence charSequence, Runnable runnable) {
            Dialog dialog = this.dialog;
            dialog.positiveText = charSequence;
            dialog.positiveRunnable = runnable;
            return this;
        }

        @Override // com.amazon.alexa.dialog.api.Dialog.Builder
        /* renamed from: setTitle  reason: collision with other method in class */
        public Builder mo1164setTitle(CharSequence charSequence) {
            this.dialog.title = charSequence;
            return this;
        }
    }

    private AlertDialog buildAlertDialog() {
        return buildAlertDialog(null);
    }

    public static void setMainActivity(Activity activity) {
        synchronized (mainActivityLock) {
            mainActivity = new WeakReference<>(activity);
        }
    }

    @Override // com.amazon.alexa.dialog.api.Dialog
    public Context getContext() {
        Context context = this.context;
        return context != null ? context : mainActivity.get();
    }

    @Override // com.amazon.alexa.dialog.api.Dialog
    public boolean isShowing() {
        AlertDialog alertDialog = this.alertDialog;
        if (alertDialog != null) {
            return alertDialog.isShowing();
        }
        return false;
    }

    public /* synthetic */ void lambda$buildAlertDialog$0$Dialog(DialogInterface dialogInterface, int i) {
        this.negativeRunnable.run();
    }

    public /* synthetic */ void lambda$buildAlertDialog$1$Dialog(DialogInterface dialogInterface, int i) {
        this.neutralRunnable.run();
    }

    public /* synthetic */ void lambda$buildAlertDialog$2$Dialog(DialogInterface dialogInterface, int i) {
        this.positiveRunnable.run();
    }

    public /* synthetic */ void lambda$buildAlertDialog$3$Dialog(DialogInterface dialogInterface) {
        this.cancelRunnable.run();
    }

    @Override // com.amazon.alexa.dialog.api.Dialog
    public boolean show() {
        try {
            this.alertDialog = buildAlertDialog();
            this.alertDialog.show();
            return true;
        } catch (WindowManager.BadTokenException | NullPointerException e) {
            String str = TAG;
            Log.e(str, "Exception caught while showing Dialog: " + e);
            return false;
        }
    }

    @VisibleForTesting
    AlertDialog buildAlertDialog(AlertDialog.Builder builder) {
        if (builder == null) {
            Context context = this.context;
            if (context != null) {
                builder = new AlertDialog.Builder(context);
            } else {
                synchronized (mainActivityLock) {
                    builder = new AlertDialog.Builder(mainActivity.get());
                }
            }
        }
        builder.setCancelable(this.cancelable);
        CharSequence charSequence = this.message;
        if (charSequence != null) {
            builder.setMessage(charSequence);
        }
        CharSequence charSequence2 = this.title;
        if (charSequence2 != null) {
            builder.setTitle(charSequence2);
        }
        CharSequence charSequence3 = this.negativeText;
        if (charSequence3 != null && this.negativeRunnable != null) {
            builder.setNegativeButton(charSequence3, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.dialog.impl.-$$Lambda$Dialog$LdSBLCL5cVJPS4jc4V-Fd_fc-eQ
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    Dialog.this.lambda$buildAlertDialog$0$Dialog(dialogInterface, i);
                }
            });
        }
        CharSequence charSequence4 = this.neutralText;
        if (charSequence4 != null && this.neutralRunnable != null) {
            builder.setNeutralButton(charSequence4, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.dialog.impl.-$$Lambda$Dialog$Dfo8DpwoCtY2L327aAlNO3t-ilM
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    Dialog.this.lambda$buildAlertDialog$1$Dialog(dialogInterface, i);
                }
            });
        }
        CharSequence charSequence5 = this.positiveText;
        if (charSequence5 != null && this.positiveRunnable != null) {
            builder.setPositiveButton(charSequence5, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.dialog.impl.-$$Lambda$Dialog$4sIzZ58ttrQ0uB_Zii6E0ledznY
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    Dialog.this.lambda$buildAlertDialog$2$Dialog(dialogInterface, i);
                }
            });
        }
        if (this.cancelRunnable != null) {
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.amazon.alexa.dialog.impl.-$$Lambda$Dialog$DC93wcggHSy2QNnp8f_l2YTVcnQ
                @Override // android.content.DialogInterface.OnCancelListener
                public final void onCancel(DialogInterface dialogInterface) {
                    Dialog.this.lambda$buildAlertDialog$3$Dialog(dialogInterface);
                }
            });
        }
        this.alertDialog = builder.create();
        return this.alertDialog;
    }
}
