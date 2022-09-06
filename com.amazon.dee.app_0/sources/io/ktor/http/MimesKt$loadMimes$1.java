package io.ktor.http;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Mimes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00012\u0006\u0010\u0004\u001a\u00020\u0002H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "", "Lio/ktor/http/ContentType;", "it", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class MimesKt$loadMimes$1 extends Lambda implements Function1<String, Pair<? extends String, ? extends ContentType>> {
    public static final MimesKt$loadMimes$1 INSTANCE = new MimesKt$loadMimes$1();

    MimesKt$loadMimes$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @Nullable
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final Pair<String, ContentType> mo12165invoke(@NotNull String it2) {
        CharSequence trim;
        int indexOf$default;
        String removePrefix;
        Intrinsics.checkParameterIsNotNull(it2, "it");
        trim = StringsKt__StringsKt.trim((CharSequence) it2);
        String obj = trim.toString();
        if (obj.length() == 0) {
            return null;
        }
        indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) obj, (char) JsonReaderKt.COMMA, 0, false, 6, (Object) null);
        if (obj != null) {
            String substring = obj.substring(0, indexOf$default);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            String substring2 = obj.substring(indexOf$default + 1);
            Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.String).substring(startIndex)");
            removePrefix = StringsKt__StringsKt.removePrefix(substring, (CharSequence) ".");
            if (removePrefix == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String lowerCase = removePrefix.toLowerCase();
            Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
            return TuplesKt.to(lowerCase, FileContentTypeKt.toContentType(substring2));
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
}
