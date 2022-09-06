package io.ktor.http.content;

import io.ktor.http.content.PartData;
import io.ktor.util.InputJvmKt;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: MultipartJvm.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljava/io/InputStream;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class MultipartJvmKt$streamProvider$1 extends Lambda implements Function0<InputStream> {
    final /* synthetic */ PartData.FileItem $this_streamProvider;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultipartJvmKt$streamProvider$1(PartData.FileItem fileItem) {
        super(0);
        this.$this_streamProvider = fileItem;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke */
    public final InputStream mo12560invoke() {
        return InputJvmKt.asStream(this.$this_streamProvider.getProvider().mo12560invoke());
    }
}
