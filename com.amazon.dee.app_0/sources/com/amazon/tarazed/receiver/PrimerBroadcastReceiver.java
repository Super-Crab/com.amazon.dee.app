package com.amazon.tarazed.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.tarazed.activity.TarazedPrimerActivity;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: PrimerBroadcastReceiver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/tarazed/receiver/PrimerBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", "primerActivity", "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "Ljava/lang/ref/WeakReference;", "onReceive", "", "context", "Landroid/content/Context;", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class PrimerBroadcastReceiver extends BroadcastReceiver {
    private WeakReference<Activity> primerActivity;

    public PrimerBroadcastReceiver(@NotNull Activity primerActivity) {
        Intrinsics.checkParameterIsNotNull(primerActivity, "primerActivity");
        this.primerActivity = new WeakReference<>(primerActivity);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NotNull Context context, @NotNull Intent intent) {
        Activity activity;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        if (!Intrinsics.areEqual(intent.getAction(), TarazedPrimerActivity.ACTION_CLOSE_PRIMER) || (activity = this.primerActivity.get()) == null) {
            return;
        }
        activity.finish();
    }
}
