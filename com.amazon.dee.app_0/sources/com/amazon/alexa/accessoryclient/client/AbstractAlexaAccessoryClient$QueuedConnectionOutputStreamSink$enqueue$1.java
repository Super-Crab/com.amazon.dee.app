package com.amazon.alexa.accessoryclient.client;

import android.os.Bundle;
import com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient;
import com.amazon.alexa.accessoryclient.common.transformers.BundleUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AbstractAlexaAccessoryClient.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AbstractAlexaAccessoryClient$QueuedConnectionOutputStreamSink$enqueue$1 extends Lambda implements Function0<String> {
    final /* synthetic */ Bundle $bundle;
    final /* synthetic */ AbstractAlexaAccessoryClient.QueuedConnectionOutputStreamSink this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractAlexaAccessoryClient$QueuedConnectionOutputStreamSink$enqueue$1(AbstractAlexaAccessoryClient.QueuedConnectionOutputStreamSink queuedConnectionOutputStreamSink, Bundle bundle) {
        super(0);
        this.this$0 = queuedConnectionOutputStreamSink;
        this.$bundle = bundle;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final String mo12560invoke() {
        int i;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AlexaAccessoryClient:  OutputStream what=");
        i = this.this$0.what;
        outline107.append(i);
        outline107.append(" not yet connected. ");
        outline107.append("Enqueueing to be sent: ");
        outline107.append(BundleUtils.bundleToString(this.$bundle));
        return outline107.toString();
    }
}
