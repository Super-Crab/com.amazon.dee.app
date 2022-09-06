package io.ktor.http;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: FileContentType.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "Lio/ktor/http/ContentType;", "", "<name for destructuring parameter 0>", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class FileContentTypeKt$extensionsByContentType$1 extends Lambda implements Function1<Pair<? extends String, ? extends ContentType>, Pair<? extends ContentType, ? extends String>> {
    public static final FileContentTypeKt$extensionsByContentType$1 INSTANCE = new FileContentTypeKt$extensionsByContentType$1();

    FileContentTypeKt$extensionsByContentType$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Pair<? extends ContentType, ? extends String> mo12165invoke(Pair<? extends String, ? extends ContentType> pair) {
        return invoke2((Pair<String, ContentType>) pair);
    }

    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final Pair<ContentType, String> invoke2(@NotNull Pair<String, ContentType> pair) {
        Intrinsics.checkParameterIsNotNull(pair, "<name for destructuring parameter 0>");
        return TuplesKt.to(pair.component2(), pair.component1());
    }
}
