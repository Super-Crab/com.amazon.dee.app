package com.amazon.alexa.fitness.view.message;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.amazon.alexa.fitness.ui.R;
import com.amazon.comms.config.util.DeviceConfigConstants;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CustomToast.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/alexa/fitness/view/message/CustomToast;", "", "()V", "Companion", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class CustomToast {
    public static final Companion Companion = new Companion(null);
    @Nullable
    private static Context context;
    private static Toast currentNotification;
    private static boolean isBackgrounded;

    /* compiled from: CustomToast.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0010\u001a\u00020\u0011J\u0018\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0017"}, d2 = {"Lcom/amazon/alexa/fitness/view/message/CustomToast$Companion;", "", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "currentNotification", "Landroid/widget/Toast;", "isBackgrounded", "", "()Z", "setBackgrounded", "(Z)V", "hideNotifications", "", "showNotification", "messageId", "", "toastStyle", "Lcom/amazon/alexa/fitness/view/message/ToastStyle;", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Companion {

        @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
        /* loaded from: classes8.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ToastStyle.values().length];
            public static final /* synthetic */ int[] $EnumSwitchMapping$1;

            static {
                $EnumSwitchMapping$0[ToastStyle.CLASSIC.ordinal()] = 1;
                $EnumSwitchMapping$0[ToastStyle.CUSTOM.ordinal()] = 2;
                $EnumSwitchMapping$1 = new int[ToastStyle.values().length];
                $EnumSwitchMapping$1[ToastStyle.CLASSIC.ordinal()] = 1;
                $EnumSwitchMapping$1[ToastStyle.CUSTOM.ordinal()] = 2;
            }
        }

        private Companion() {
        }

        public static /* synthetic */ void showNotification$default(Companion companion, int i, ToastStyle toastStyle, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                toastStyle = ToastStyle.CUSTOM;
            }
            companion.showNotification(i, toastStyle);
        }

        @Nullable
        public final Context getContext() {
            return CustomToast.context;
        }

        public final void hideNotifications() {
            setBackgrounded(true);
            Toast toast = CustomToast.currentNotification;
            if (toast != null) {
                toast.cancel();
                Log.i("CustomToast", "cancelling toast notification");
            }
        }

        public final boolean isBackgrounded() {
            return CustomToast.isBackgrounded;
        }

        public final void setBackgrounded(boolean z) {
            CustomToast.isBackgrounded = z;
        }

        public final void setContext(@Nullable Context context) {
            CustomToast.context = context;
        }

        public final void showNotification(int i, @NotNull ToastStyle toastStyle) {
            Context context;
            View inflate;
            Intrinsics.checkParameterIsNotNull(toastStyle, "toastStyle");
            if (isBackgrounded() || (context = getContext()) == null) {
                return;
            }
            try {
                Toast toast = CustomToast.currentNotification;
                if (toast != null) {
                    toast.cancel();
                }
                LayoutInflater from = LayoutInflater.from(context);
                int i2 = WhenMappings.$EnumSwitchMapping$0[toastStyle.ordinal()];
                if (i2 == 1) {
                    inflate = from.inflate(R.layout.classic_toast, (ViewGroup) null);
                    Intrinsics.checkExpressionValueIsNotNull(inflate, "layoutInflater.inflate(R…yout.classic_toast, null)");
                } else if (i2 != 2) {
                    throw new NoWhenBranchMatchedException();
                } else {
                    inflate = from.inflate(R.layout.custom_toast, (ViewGroup) null);
                    Intrinsics.checkExpressionValueIsNotNull(inflate, "layoutInflater.inflate(R…ayout.custom_toast, null)");
                }
                ((TextView) inflate.findViewById(R.id.errorTextView)).setText(i);
                final Toast toast2 = new Toast(context);
                int i3 = WhenMappings.$EnumSwitchMapping$1[toastStyle.ordinal()];
                if (i3 == 1) {
                    toast2.setGravity(55, 0, (int) context.getResources().getDimension(R.dimen.classic_toast_top_margin));
                } else if (i3 == 2) {
                    toast2.setGravity(55, 0, 0);
                }
                toast2.setDuration(1);
                toast2.setView(inflate);
                CustomToast.currentNotification = toast2;
                toast2.show();
                Boolean.valueOf(new Handler().postDelayed(new Runnable() { // from class: com.amazon.alexa.fitness.view.message.CustomToast$Companion$showNotification$1$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        toast2.cancel();
                    }
                }, DeviceConfigConstants.DEFAULT_CALL_END_TO_SHUTDOWN_TIMEOUT_MS));
            } catch (Exception e) {
                Integer.valueOf(Log.e("CustomToast", String.valueOf(e.getMessage())));
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
