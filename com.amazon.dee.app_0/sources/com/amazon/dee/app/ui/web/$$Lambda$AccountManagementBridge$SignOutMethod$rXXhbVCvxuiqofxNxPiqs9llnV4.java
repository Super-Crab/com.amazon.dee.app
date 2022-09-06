package com.amazon.dee.app.ui.web;

import com.amazon.dee.app.services.logging.Log;
import rx.functions.Action1;
/* compiled from: lambda */
/* renamed from: com.amazon.dee.app.ui.web.-$$Lambda$AccountManagementBridge$SignOutMethod$rXXhbVCvxuiqofxNxPiqs9llnV4  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$AccountManagementBridge$SignOutMethod$rXXhbVCvxuiqofxNxPiqs9llnV4 implements Action1 {
    public static final /* synthetic */ $$Lambda$AccountManagementBridge$SignOutMethod$rXXhbVCvxuiqofxNxPiqs9llnV4 INSTANCE = new $$Lambda$AccountManagementBridge$SignOutMethod$rXXhbVCvxuiqofxNxPiqs9llnV4();

    private /* synthetic */ $$Lambda$AccountManagementBridge$SignOutMethod$rXXhbVCvxuiqofxNxPiqs9llnV4() {
    }

    @Override // rx.functions.Action1
    public final void call(Object obj) {
        Log.e(AccountManagementBridge.TAG, (Throwable) obj, "Failed to sign out", new Object[0]);
    }
}
