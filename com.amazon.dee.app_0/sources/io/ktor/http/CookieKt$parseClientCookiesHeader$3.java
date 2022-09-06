package io.ktor.http;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: Cookie.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "", "it", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class CookieKt$parseClientCookiesHeader$3 extends Lambda implements Function1<Pair<? extends String, ? extends String>, Pair<? extends String, ? extends String>> {
    public static final CookieKt$parseClientCookiesHeader$3 INSTANCE = new CookieKt$parseClientCookiesHeader$3();

    CookieKt$parseClientCookiesHeader$3() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Pair<? extends String, ? extends String> mo12165invoke(Pair<? extends String, ? extends String> pair) {
        return invoke2((Pair<String, String>) pair);
    }

    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final Pair<String, String> invoke2(@NotNull Pair<String, String> it2) {
        boolean startsWith$default;
        boolean endsWith$default;
        String removeSurrounding;
        Intrinsics.checkParameterIsNotNull(it2, "it");
        startsWith$default = StringsKt__StringsJVMKt.startsWith$default(it2.getSecond(), EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED, false, 2, null);
        if (startsWith$default) {
            endsWith$default = StringsKt__StringsJVMKt.endsWith$default(it2.getSecond(), EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED, false, 2, null);
            if (!endsWith$default) {
                return it2;
            }
            removeSurrounding = StringsKt__StringsKt.removeSurrounding(it2.getSecond(), (CharSequence) EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            return Pair.copy$default(it2, null, removeSurrounding, 1, null);
        }
        return it2;
    }
}
