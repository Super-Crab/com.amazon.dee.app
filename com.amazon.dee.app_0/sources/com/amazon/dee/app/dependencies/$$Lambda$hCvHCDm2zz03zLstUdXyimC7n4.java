package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.services.useragent.UserAgent;
import com.amazon.dee.app.services.useragent.UserAgentService;
/* compiled from: lambda */
/* renamed from: com.amazon.dee.app.dependencies.-$$Lambda$hCvHCDm2zz03zLstUdXy-imC7n4  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$hCvHCDm2zz03zLstUdXyimC7n4 implements UserAgentService {
    public static final /* synthetic */ $$Lambda$hCvHCDm2zz03zLstUdXyimC7n4 INSTANCE = new $$Lambda$hCvHCDm2zz03zLstUdXyimC7n4();

    private /* synthetic */ $$Lambda$hCvHCDm2zz03zLstUdXyimC7n4() {
    }

    @Override // com.amazon.dee.app.services.useragent.UserAgentService
    public final String build() {
        return UserAgent.get();
    }
}
