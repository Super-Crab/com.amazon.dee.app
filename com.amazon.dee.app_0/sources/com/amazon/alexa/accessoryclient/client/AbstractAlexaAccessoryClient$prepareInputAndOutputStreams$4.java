package com.amazon.alexa.accessoryclient.client;

import com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AbstractAlexaAccessoryClient.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "serviceMajorVersion", "", "serviceMinorVersion", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AbstractAlexaAccessoryClient$prepareInputAndOutputStreams$4 extends Lambda implements Function2<Integer, Integer, Unit> {
    final /* synthetic */ AbstractAlexaAccessoryClient this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractAlexaAccessoryClient$prepareInputAndOutputStreams$4(AbstractAlexaAccessoryClient abstractAlexaAccessoryClient) {
        super(2);
        this.this$0 = abstractAlexaAccessoryClient;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12248invoke(Integer num, Integer num2) {
        invoke(num.intValue(), num2.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(int i, int i2) {
        int i3;
        int i4;
        AbstractAlexaAccessoryClient abstractAlexaAccessoryClient = this.this$0;
        i3 = abstractAlexaAccessoryClient.majorVersion;
        i4 = this.this$0.minorVersion;
        abstractAlexaAccessoryClient.disconnect(new AbstractAlexaAccessoryClient.ClientVersionNotSupportedByServiceException(i3, i4, i, i2));
    }
}
