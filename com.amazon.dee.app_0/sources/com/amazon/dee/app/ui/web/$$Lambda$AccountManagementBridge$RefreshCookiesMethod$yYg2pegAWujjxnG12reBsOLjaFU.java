package com.amazon.dee.app.ui.web;

import com.amazon.dee.app.services.logging.Log;
import rx.functions.Action1;
/* compiled from: lambda */
/* renamed from: com.amazon.dee.app.ui.web.-$$Lambda$AccountManagementBridge$RefreshCookiesMethod$yYg2pegAWujjxnG12reBsOLjaFU  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$AccountManagementBridge$RefreshCookiesMethod$yYg2pegAWujjxnG12reBsOLjaFU implements Action1 {
    public static final /* synthetic */ $$Lambda$AccountManagementBridge$RefreshCookiesMethod$yYg2pegAWujjxnG12reBsOLjaFU INSTANCE = new $$Lambda$AccountManagementBridge$RefreshCookiesMethod$yYg2pegAWujjxnG12reBsOLjaFU();

    private /* synthetic */ $$Lambda$AccountManagementBridge$RefreshCookiesMethod$yYg2pegAWujjxnG12reBsOLjaFU() {
    }

    @Override // rx.functions.Action1
    public final void call(Object obj) {
        Log.e(AccountManagementBridge.TAG, (Throwable) obj, "Failed to sign out", new Object[0]);
    }
}
