package com.amazon.dee.app.ui.main;

import com.amazon.dee.app.services.logging.Log;
import rx.functions.Action1;
/* compiled from: lambda */
/* renamed from: com.amazon.dee.app.ui.main.-$$Lambda$StartupStateMachine$pcwxOlciGUT8DqlJcSc8gdJIk8E  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$StartupStateMachine$pcwxOlciGUT8DqlJcSc8gdJIk8E implements Action1 {
    public static final /* synthetic */ $$Lambda$StartupStateMachine$pcwxOlciGUT8DqlJcSc8gdJIk8E INSTANCE = new $$Lambda$StartupStateMachine$pcwxOlciGUT8DqlJcSc8gdJIk8E();

    private /* synthetic */ $$Lambda$StartupStateMachine$pcwxOlciGUT8DqlJcSc8gdJIk8E() {
    }

    @Override // rx.functions.Action1
    public final void call(Object obj) {
        Log.e(StartupStateMachine.TAG, "Error on authentication.", (Throwable) obj);
    }
}
