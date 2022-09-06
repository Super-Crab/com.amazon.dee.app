package com.amazon.alexa.accessory.internal.http;

import com.amazon.alexa.accessory.internal.util.WrapperUtil;
import java.net.URL;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.http.-$$Lambda$HttpCall$1bGQlxmzsIbnSzFTWmQQtNfJrZI  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$HttpCall$1bGQlxmzsIbnSzFTWmQQtNfJrZI implements WrapperUtil.ParamedSupplier {
    public static final /* synthetic */ $$Lambda$HttpCall$1bGQlxmzsIbnSzFTWmQQtNfJrZI INSTANCE = new $$Lambda$HttpCall$1bGQlxmzsIbnSzFTWmQQtNfJrZI();

    private /* synthetic */ $$Lambda$HttpCall$1bGQlxmzsIbnSzFTWmQQtNfJrZI() {
    }

    @Override // com.amazon.alexa.accessory.internal.util.WrapperUtil.ParamedSupplier
    public final Object get(Object obj) {
        return ((URL) obj).openConnection();
    }
}
