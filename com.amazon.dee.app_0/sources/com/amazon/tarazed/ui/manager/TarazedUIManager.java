package com.amazon.tarazed.ui.manager;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TarazedUIManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0011\u0010\u0007\u001a\u00020\u0003H¦@ø\u0001\u0000¢\u0006\u0002\u0010\bJ\u0011\u0010\t\u001a\u00020\u0003H¦@ø\u0001\u0000¢\u0006\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lcom/amazon/tarazed/ui/manager/TarazedUIManager;", "", "displayToast", "", "message", "", "showSessionControls", "subscribeToActivityChanges", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unsubscribeFromActivityChanges", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface TarazedUIManager {
    void displayToast(@NotNull String str);

    void showSessionControls();

    @Nullable
    Object subscribeToActivityChanges(@NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object unsubscribeFromActivityChanges(@NotNull Continuation<? super Unit> continuation);
}
