package com.amazon.alexa.accessoryclient.common.rxipc;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCClient;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: RxIPCClient.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u000e\b\u0001\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0005H\n¢\u0006\u0002\b\u0006¨\u0006\u0007"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/amazon/alexa/accessoryclient/common/query/Query$Request;", "O", "Lcom/amazon/alexa/accessoryclient/common/query/Query$Response;", "invoke", "com/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient$createInternal$observable$1$1$1"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class RxIPCClient$createInternal$observable$1$$special$$inlined$synchronized$lambda$1 extends Lambda implements Function0<String> {
    final /* synthetic */ RxIPCEvent $event$inlined;
    final /* synthetic */ RxIPCEventId $eventId$inlined;
    final /* synthetic */ RxIPCClient.RxTypeEmitter $rxTypeEmitter$inlined;
    final /* synthetic */ RxIPCClient$createInternal$observable$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RxIPCClient$createInternal$observable$1$$special$$inlined$synchronized$lambda$1(RxIPCClient$createInternal$observable$1 rxIPCClient$createInternal$observable$1, RxIPCClient.RxTypeEmitter rxTypeEmitter, RxIPCEventId rxIPCEventId, RxIPCEvent rxIPCEvent) {
        super(0);
        this.this$0 = rxIPCClient$createInternal$observable$1;
        this.$rxTypeEmitter$inlined = rxTypeEmitter;
        this.$eventId$inlined = rxIPCEventId;
        this.$event$inlined = rxIPCEvent;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final String mo12560invoke() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RxIPCClient: create: ");
        outline107.append(this.this$0.$apiIdentifier.name());
        outline107.append(" sending RxIPCEvent: ");
        outline107.append(this.$event$inlined);
        return outline107.toString();
    }
}
