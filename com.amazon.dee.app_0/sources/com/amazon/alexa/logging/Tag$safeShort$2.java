package com.amazon.alexa.logging;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Tag.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 10})
/* loaded from: classes9.dex */
public final class Tag$safeShort$2 extends Lambda implements Function0<String> {
    final /* synthetic */ Tag this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Tag$safeShort$2(Tag tag) {
        super(0);
        this.this$0 = tag;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final String mo12560invoke() {
        return Tag.Companion.safe$AlexaMobileAndroidLogging_release(this.this$0.getShort$AlexaMobileAndroidLogging_release());
    }
}
