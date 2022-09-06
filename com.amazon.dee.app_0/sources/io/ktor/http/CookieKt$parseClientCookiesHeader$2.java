package io.ktor.http;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: Cookie.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lkotlin/Pair;", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class CookieKt$parseClientCookiesHeader$2 extends Lambda implements Function1<Pair<? extends String, ? extends String>, Boolean> {
    final /* synthetic */ boolean $skipEscaped;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CookieKt$parseClientCookiesHeader$2(boolean z) {
        super(1);
        this.$skipEscaped = z;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Boolean mo12165invoke(Pair<? extends String, ? extends String> pair) {
        return Boolean.valueOf(invoke2((Pair<String, String>) pair));
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final boolean invoke2(@NotNull Pair<String, String> it2) {
        boolean startsWith$default;
        Intrinsics.checkParameterIsNotNull(it2, "it");
        if (this.$skipEscaped) {
            startsWith$default = StringsKt__StringsJVMKt.startsWith$default(it2.getFirst(), "$", false, 2, null);
            if (startsWith$default) {
                return false;
            }
        }
        return true;
    }
}
