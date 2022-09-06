package com.amazon.tarazed.core.logging;

import com.amazonaws.services.logs.model.InputLogEvent;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TarazedLogParser.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "line", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedLogParser$parseLogEvents$1 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ Ref.ObjectRef $currentBatch;
    final /* synthetic */ Ref.LongRef $firstTimestampOfBatch;
    final /* synthetic */ List $logEvents;
    final /* synthetic */ long $logExpiryTimestamp;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedLogParser$parseLogEvents$1(long j, Ref.LongRef longRef, List list, Ref.ObjectRef objectRef) {
        super(1);
        this.$logExpiryTimestamp = j;
        this.$firstTimestampOfBatch = longRef;
        this.$logEvents = list;
        this.$currentBatch = objectRef;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference failed for: r6v9, types: [T, java.util.ArrayList] */
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull String line) {
        String replace$default;
        String str;
        Intrinsics.checkParameterIsNotNull(line, "line");
        replace$default = StringsKt__StringsJVMKt.replace$default(line, (char) TarazedLogFormatter.FILE_SEPARATOR, '\n', false, 4, (Object) null);
        int length = replace$default.length();
        int i = 0;
        while (true) {
            if (i >= length) {
                i = -1;
                break;
            }
            if (replace$default.charAt(i) == ' ') {
                break;
            }
            i++;
        }
        if (i == -1) {
            i = replace$default.length();
        }
        try {
            if (replace$default != null) {
                String substring = replace$default.substring(0, i);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                long parseLong = Long.parseLong(substring);
                if (parseLong <= this.$logExpiryTimestamp) {
                    return;
                }
                Ref.LongRef longRef = this.$firstTimestampOfBatch;
                if (longRef.element == 0) {
                    longRef.element = parseLong;
                }
                if (parseLong - this.$firstTimestampOfBatch.element >= 86400000) {
                    this.$logEvents.add((List) this.$currentBatch.element);
                    this.$currentBatch.element = new ArrayList();
                    this.$firstTimestampOfBatch.element = parseLong;
                }
                int i2 = i + 1;
                if (i2 < replace$default.length()) {
                    str = replace$default.substring(i2, replace$default.length());
                    Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                } else {
                    str = "";
                }
                InputLogEvent withMessage = new InputLogEvent().withTimestamp(Long.valueOf(parseLong)).withMessage(str);
                Intrinsics.checkExpressionValueIsNotNull(withMessage, "InputLogEvent()\n        …    .withMessage(message)");
                ((List) this.$currentBatch.element).add(withMessage);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        } catch (Exception unused) {
        }
    }
}
