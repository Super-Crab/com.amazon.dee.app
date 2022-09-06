package com.amazon.tarazed.dialog;

import android.content.Context;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: AlertDialogBuilderProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/dialog/AlertDialogBuilderProvider;", "", "provideDialogBuilder", "Lcom/amazon/tarazed/dialog/AlertDialogBuilder;", "context", "Landroid/content/Context;", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface AlertDialogBuilderProvider {
    @NotNull
    AlertDialogBuilder provideDialogBuilder(@NotNull Context context);
}
