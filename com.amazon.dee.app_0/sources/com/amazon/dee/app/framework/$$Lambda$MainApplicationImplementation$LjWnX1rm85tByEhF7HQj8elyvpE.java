package com.amazon.dee.app.framework;

import com.amazon.alexa.sharing.api.SharingClient;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: lambda */
/* renamed from: com.amazon.dee.app.framework.-$$Lambda$MainApplicationImplementation$LjWnX1rm85tByEhF7HQj8elyvpE  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$MainApplicationImplementation$LjWnX1rm85tByEhF7HQj8elyvpE implements Runnable {
    public static final /* synthetic */ $$Lambda$MainApplicationImplementation$LjWnX1rm85tByEhF7HQj8elyvpE INSTANCE = new $$Lambda$MainApplicationImplementation$LjWnX1rm85tByEhF7HQj8elyvpE();

    private /* synthetic */ $$Lambda$MainApplicationImplementation$LjWnX1rm85tByEhF7HQj8elyvpE() {
    }

    @Override // java.lang.Runnable
    public final void run() {
        ((SharingClient) GeneratedOutlineSupport1.outline20(SharingClient.class)).startListening();
    }
}
