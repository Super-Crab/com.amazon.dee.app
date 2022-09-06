package com.amazon.alexa.logging;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: DispatchingLogger.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/amazon/alexa/logging/LogRecord;", "invoke"}, k = 3, mv = {1, 1, 10})
/* loaded from: classes9.dex */
final class DispatchingLogger$log$record$2 extends Lambda implements Function0<LogRecord> {
    final /* synthetic */ Level $level;
    final /* synthetic */ String $message;
    final /* synthetic */ Tag $tag;
    final /* synthetic */ Throwable $tr;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DispatchingLogger$log$record$2(Level level, Tag tag, String str, Throwable th) {
        super(0);
        this.$level = level;
        this.$tag = tag;
        this.$message = str;
        this.$tr = th;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke */
    public final LogRecord mo12560invoke() {
        return new LogRecord(this.$level, this.$tag, this.$message, this.$tr);
    }
}
