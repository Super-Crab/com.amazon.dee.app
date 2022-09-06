package com.amazon.alexa.fitness.time;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
/* compiled from: ISO8601.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "s", "h", "", "m", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
final class ISO8601$Companion$serializeDateTimeInLocalTimeZone$buildOffset$1 extends Lambda implements Function3<String, Integer, Integer, String> {
    final /* synthetic */ boolean $zulu;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ISO8601$Companion$serializeDateTimeInLocalTimeZone$buildOffset$1(boolean z) {
        super(3);
        this.$zulu = z;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ String invoke(String str, Integer num, Integer num2) {
        return invoke(str, num.intValue(), num2.intValue());
    }

    @NotNull
    public final String invoke(@NotNull String s, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(s, "s");
        if (i == 0 && i2 == 0 && this.$zulu) {
            return "Z";
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {s, Integer.valueOf(Math.abs(i)), Integer.valueOf(Math.abs(i2))};
        String format = String.format("%s%02d:%02d", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        return format;
    }
}
