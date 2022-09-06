package com.amazon.dee.app.framework;

import com.amazon.alexa.presence.api.PresenceService;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: lambda */
/* renamed from: com.amazon.dee.app.framework.-$$Lambda$MainApplicationImplementation$N33yvH5ixEDTSih2MpBIJQbiftM  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$MainApplicationImplementation$N33yvH5ixEDTSih2MpBIJQbiftM implements Runnable {
    public static final /* synthetic */ $$Lambda$MainApplicationImplementation$N33yvH5ixEDTSih2MpBIJQbiftM INSTANCE = new $$Lambda$MainApplicationImplementation$N33yvH5ixEDTSih2MpBIJQbiftM();

    private /* synthetic */ $$Lambda$MainApplicationImplementation$N33yvH5ixEDTSih2MpBIJQbiftM() {
    }

    @Override // java.lang.Runnable
    public final void run() {
        ((PresenceService) GeneratedOutlineSupport1.outline20(PresenceService.class)).start();
    }
}
