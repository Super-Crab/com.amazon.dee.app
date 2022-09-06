package io.ktor.http.content;

import io.ktor.http.ContentDisposition;
import io.ktor.http.HttpHeaders;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Multipart.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/http/ContentDisposition;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class PartData$contentDisposition$2 extends Lambda implements Function0<ContentDisposition> {
    final /* synthetic */ PartData this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PartData$contentDisposition$2(PartData partData) {
        super(0);
        this.this$0 = partData;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    @Nullable
    /* renamed from: invoke */
    public final ContentDisposition mo12560invoke() {
        String str = this.this$0.getHeaders().get(HttpHeaders.INSTANCE.getContentDisposition());
        if (str != null) {
            return ContentDisposition.Companion.parse(str);
        }
        return null;
    }
}
