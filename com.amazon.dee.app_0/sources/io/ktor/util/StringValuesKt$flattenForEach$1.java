package io.ktor.util;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: StringValues.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "name", "", EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS, "", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class StringValuesKt$flattenForEach$1 extends Lambda implements Function2<String, List<? extends String>, Unit> {
    final /* synthetic */ Function2 $block;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StringValuesKt$flattenForEach$1(Function2 function2) {
        super(2);
        this.$block = function2;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12248invoke(String str, List<? extends String> list) {
        invoke2(str, (List<String>) list);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull String name, @NotNull List<String> items) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(items, "items");
        for (String str : items) {
            this.$block.mo12248invoke(name, str);
        }
    }
}
