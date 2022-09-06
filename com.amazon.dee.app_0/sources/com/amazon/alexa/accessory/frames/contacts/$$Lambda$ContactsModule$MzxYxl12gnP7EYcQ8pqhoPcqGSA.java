package com.amazon.alexa.accessory.frames.contacts;

import com.amazon.alexa.accessory.frames.topContact.TopContactManager;
import com.amazon.alexa.accessory.frames.utils.Log;
import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import rx.functions.Action1;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.frames.contacts.-$$Lambda$ContactsModule$MzxYxl12gnP7EYcQ8pqhoPcqGSA  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$ContactsModule$MzxYxl12gnP7EYcQ8pqhoPcqGSA implements Action1 {
    public static final /* synthetic */ $$Lambda$ContactsModule$MzxYxl12gnP7EYcQ8pqhoPcqGSA INSTANCE = new $$Lambda$ContactsModule$MzxYxl12gnP7EYcQ8pqhoPcqGSA();

    private /* synthetic */ $$Lambda$ContactsModule$MzxYxl12gnP7EYcQ8pqhoPcqGSA() {
    }

    @Override // rx.functions.Action1
    public final void call(Object obj) {
        Log.d(ContactsModule.TAG, String.format("successfully cleanup %s %s - %s", SettingsStorageModule.DEVICE_ACCOUNT_ID_KEY, TopContactManager.deviceAccountId, ((Void) obj).toString()));
    }
}
