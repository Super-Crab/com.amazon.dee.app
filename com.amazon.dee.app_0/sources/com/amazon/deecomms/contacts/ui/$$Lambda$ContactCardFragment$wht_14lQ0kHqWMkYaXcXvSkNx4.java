package com.amazon.deecomms.contacts.ui;

import com.amazon.deecomms.common.util.IdentityValidator;
import com.amazon.deecomms.contacts.model.ContactCardInfo;
import rx.functions.Func2;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactCardFragment$wht_14lQ0kHqWMkYa-XcXvSkNx4  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$ContactCardFragment$wht_14lQ0kHqWMkYaXcXvSkNx4 implements Func2 {
    public static final /* synthetic */ $$Lambda$ContactCardFragment$wht_14lQ0kHqWMkYaXcXvSkNx4 INSTANCE = new $$Lambda$ContactCardFragment$wht_14lQ0kHqWMkYaXcXvSkNx4();

    private /* synthetic */ $$Lambda$ContactCardFragment$wht_14lQ0kHqWMkYaXcXvSkNx4() {
    }

    @Override // rx.functions.Func2
    /* renamed from: call */
    public final Object mo13094call(Object obj, Object obj2) {
        Boolean valueOf;
        ContactCardInfo contactCardInfo = (ContactCardInfo) obj;
        Boolean bool = (Boolean) obj2;
        valueOf = Boolean.valueOf(IdentityValidator.isSelf(r0.getCommsId()) && r1.booleanValue());
        return valueOf;
    }
}
