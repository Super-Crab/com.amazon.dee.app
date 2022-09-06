package com.amazon.tarazed.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.amazon.client.metrics.thirdparty.MetricsServiceConstants;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.dagger.injectors.LibraryInjector;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ToggleQAModeReceiver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\u0018\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\f8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0019"}, d2 = {"Lcom/amazon/tarazed/receiver/ToggleQAModeReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "injected", "", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "getLogger$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "setLogger$TarazedAndroidLibrary_release", "(Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;)V", "sharedPreferences", "Landroid/content/SharedPreferences;", "getSharedPreferences$TarazedAndroidLibrary_release", "()Landroid/content/SharedPreferences;", "setSharedPreferences$TarazedAndroidLibrary_release", "(Landroid/content/SharedPreferences;)V", "injectIfNeeded", "", "onReceive", "context", "Landroid/content/Context;", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ToggleQAModeReceiver extends BroadcastReceiver {
    public static final Companion Companion = new Companion(null);
    private static final String INTENT_ACTION = "com.amazon.tarazed.TOGGLE_QA_MODE";
    private static final String KEY_ENABLE = "ENABLE";
    @NotNull
    public static final String QA_MODE_PREFERENCE_KEY = "QA_MODE";
    private static final String TAG = "ToggleQAModeReceiver";
    private boolean injected;
    @Inject
    @NotNull
    public TarazedSessionLogger logger;
    @Inject
    @NotNull
    public SharedPreferences sharedPreferences;

    /* compiled from: ToggleQAModeReceiver.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/tarazed/receiver/ToggleQAModeReceiver$Companion;", "", "()V", MetricsServiceConstants.INTENT_ACTION, "", "KEY_ENABLE", "QA_MODE_PREFERENCE_KEY", "TAG", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final void injectIfNeeded() {
        if (this.injected) {
            return;
        }
        LibraryInjector.getComponent().inject(this);
        this.injected = true;
    }

    @NotNull
    public final TarazedSessionLogger getLogger$TarazedAndroidLibrary_release() {
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        if (tarazedSessionLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        return tarazedSessionLogger;
    }

    @NotNull
    public final SharedPreferences getSharedPreferences$TarazedAndroidLibrary_release() {
        SharedPreferences sharedPreferences = this.sharedPreferences;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
        }
        return sharedPreferences;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NotNull Context context, @NotNull Intent intent) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        injectIfNeeded();
        if (Intrinsics.areEqual(intent.getAction(), INTENT_ACTION)) {
            TarazedSessionLogger tarazedSessionLogger = this.logger;
            if (tarazedSessionLogger == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            tarazedSessionLogger.i(TAG, "Toggle QA mode broadcast received");
            SharedPreferences sharedPreferences = this.sharedPreferences;
            if (sharedPreferences == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
            }
            boolean z = sharedPreferences.getBoolean(QA_MODE_PREFERENCE_KEY, false);
            boolean booleanExtra = intent.getBooleanExtra(KEY_ENABLE, false);
            if (z == booleanExtra) {
                TarazedSessionLogger tarazedSessionLogger2 = this.logger;
                if (tarazedSessionLogger2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("logger");
                }
                tarazedSessionLogger2.i(TAG, "QA mode already set to desired state " + booleanExtra + ", ignoring");
                return;
            }
            TarazedSessionLogger tarazedSessionLogger3 = this.logger;
            if (tarazedSessionLogger3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            tarazedSessionLogger3.i(TAG, "Toggling QA mode: " + booleanExtra);
            SharedPreferences sharedPreferences2 = this.sharedPreferences;
            if (sharedPreferences2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
            }
            GeneratedOutlineSupport1.outline143(sharedPreferences2, QA_MODE_PREFERENCE_KEY, booleanExtra);
        }
    }

    public final void setLogger$TarazedAndroidLibrary_release(@NotNull TarazedSessionLogger tarazedSessionLogger) {
        Intrinsics.checkParameterIsNotNull(tarazedSessionLogger, "<set-?>");
        this.logger = tarazedSessionLogger;
    }

    public final void setSharedPreferences$TarazedAndroidLibrary_release(@NotNull SharedPreferences sharedPreferences) {
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "<set-?>");
        this.sharedPreferences = sharedPreferences;
    }
}
