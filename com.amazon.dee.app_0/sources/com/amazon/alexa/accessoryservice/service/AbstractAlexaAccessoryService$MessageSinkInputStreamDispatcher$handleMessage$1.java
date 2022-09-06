package com.amazon.alexa.accessoryservice.service;

import android.os.Message;
import com.amazon.alexa.accessoryclient.common.transformers.BundleUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
/* compiled from: AbstractAlexaAccessoryService.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class AbstractAlexaAccessoryService$MessageSinkInputStreamDispatcher$handleMessage$1 extends Lambda implements Function0<String> {
    final /* synthetic */ Message $message;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractAlexaAccessoryService$MessageSinkInputStreamDispatcher$handleMessage$1(Message message) {
        super(0);
        this.$message = message;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final String mo12560invoke() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AbstractAlexaAccessoryService:  received message ");
        outline107.append(this.$message);
        outline107.append(", what = ");
        outline107.append(this.$message.what);
        outline107.append(Chars.SPACE);
        outline107.append("with bundle: ");
        outline107.append(BundleUtils.bundleToString(this.$message.getData()));
        return outline107.toString();
    }
}
