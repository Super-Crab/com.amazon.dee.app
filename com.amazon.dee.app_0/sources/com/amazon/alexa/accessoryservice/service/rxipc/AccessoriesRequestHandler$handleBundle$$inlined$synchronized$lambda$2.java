package com.amazon.alexa.accessoryservice.service.rxipc;

import android.os.Bundle;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCEvent;
import com.amazon.alexa.accessoryclient.common.transformers.BundleUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: AccessoriesRequestHandler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "", "invoke", "com/amazon/alexa/accessoryservice/service/rxipc/AccessoriesRequestHandler$handleBundle$1$2"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class AccessoriesRequestHandler$handleBundle$$inlined$synchronized$lambda$2 extends Lambda implements Function0<String> {
    final /* synthetic */ Bundle $bundle$inlined;
    final /* synthetic */ RxIPCEvent $rxIpcEvent;
    final /* synthetic */ AccessoriesRequestHandler this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AccessoriesRequestHandler$handleBundle$$inlined$synchronized$lambda$2(RxIPCEvent rxIPCEvent, AccessoriesRequestHandler accessoriesRequestHandler, Bundle bundle) {
        super(0);
        this.$rxIpcEvent = rxIPCEvent;
        this.this$0 = accessoriesRequestHandler;
        this.$bundle$inlined = bundle;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final String mo12560invoke() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AccessoriesRequestHandler:  received unexpected RxRNEventId action ");
        outline107.append(this.$rxIpcEvent.getRxIPCEventId().getAction());
        outline107.append(", ");
        outline107.append("ignoring this bundle ");
        outline107.append(BundleUtils.bundleToString(this.$bundle$inlined));
        return outline107.toString();
    }
}
