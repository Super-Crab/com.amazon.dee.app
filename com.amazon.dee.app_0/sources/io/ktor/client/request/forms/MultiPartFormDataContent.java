package io.ktor.client.request.forms;

import io.ktor.http.ContentType;
import io.ktor.http.content.OutgoingContent;
import io.ktor.http.content.PartData;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FormDataContent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0019\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0010R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lio/ktor/client/request/forms/MultiPartFormDataContent;", "Lio/ktor/http/content/OutgoingContent$WriteChannelContent;", "parts", "", "Lio/ktor/http/content/PartData;", "(Ljava/util/List;)V", "boundary", "", "contentType", "Lio/ktor/http/ContentType;", "getContentType", "()Lio/ktor/http/ContentType;", "writeTo", "", "channel", "Lkotlinx/coroutines/io/ByteWriteChannel;", "(Lkotlinx/coroutines/io/ByteWriteChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class MultiPartFormDataContent extends OutgoingContent.WriteChannelContent {
    private final String boundary;
    @NotNull
    private final ContentType contentType;
    private final List<PartData> parts;

    /* JADX WARN: Multi-variable type inference failed */
    public MultiPartFormDataContent(@NotNull List<? extends PartData> parts) {
        String generateBoundary;
        Intrinsics.checkParameterIsNotNull(parts, "parts");
        this.parts = parts;
        generateBoundary = FormDataContentKt.generateBoundary();
        this.boundary = generateBoundary;
        this.contentType = ContentType.MultiPart.INSTANCE.getFormData().withParameter("boundary", this.boundary);
    }

    @Override // io.ktor.http.content.OutgoingContent
    @NotNull
    public ContentType getContentType() {
        return this.contentType;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:1|(2:3|(4:5|6|7|8))|181|6|7|8|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x015d, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x015e, code lost:
        r5 = r10;
     */
    /* JADX WARN: Not initialized variable reg: 10, insn: 0x015e: MOVE  (r5 I:??[OBJECT, ARRAY]) = (r10 I:??[OBJECT, ARRAY]), block:B:67:0x015e */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0211 A[Catch: all -> 0x032c, TryCatch #7 {all -> 0x032c, blocks: (B:101:0x01f9, B:102:0x020b, B:104:0x0211, B:108:0x027d, B:112:0x0295, B:114:0x0299, B:128:0x030f, B:118:0x02c3, B:120:0x02c7, B:123:0x02e4, B:125:0x02e8), top: B:176:0x01f9 }] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x027d A[Catch: all -> 0x032c, TryCatch #7 {all -> 0x032c, blocks: (B:101:0x01f9, B:102:0x020b, B:104:0x0211, B:108:0x027d, B:112:0x0295, B:114:0x0299, B:128:0x030f, B:118:0x02c3, B:120:0x02c7, B:123:0x02e4, B:125:0x02e8), top: B:176:0x01f9 }] */
    /* JADX WARN: Removed duplicated region for block: B:10:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0299 A[Catch: all -> 0x032c, TryCatch #7 {all -> 0x032c, blocks: (B:101:0x01f9, B:102:0x020b, B:104:0x0211, B:108:0x027d, B:112:0x0295, B:114:0x0299, B:128:0x030f, B:118:0x02c3, B:120:0x02c7, B:123:0x02e4, B:125:0x02e8), top: B:176:0x01f9 }] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x02c3 A[Catch: all -> 0x032c, TryCatch #7 {all -> 0x032c, blocks: (B:101:0x01f9, B:102:0x020b, B:104:0x0211, B:108:0x027d, B:112:0x0295, B:114:0x0299, B:128:0x030f, B:118:0x02c3, B:120:0x02c7, B:123:0x02e4, B:125:0x02e8), top: B:176:0x01f9 }] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0325 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x032e A[Catch: all -> 0x0173, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x0173, blocks: (B:94:0x01be, B:97:0x01c6, B:135:0x032e, B:69:0x0169, B:93:0x01b7, B:72:0x016e, B:73:0x0172), top: B:167:0x0169 }] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0360 A[LOOP:0: B:140:0x035a->B:142:0x0360, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0386 A[DONT_GENERATE, LOOP:1: B:148:0x0380->B:150:0x0386, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01c6 A[Catch: all -> 0x0173, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x0173, blocks: (B:94:0x01be, B:97:0x01c6, B:135:0x032e, B:69:0x0169, B:93:0x01b7, B:72:0x016e, B:73:0x0172), top: B:167:0x0169 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:107:0x027a -> B:102:0x020b). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:131:0x0326 -> B:132:0x0328). Please submit an issue!!! */
    @Override // io.ktor.http.content.OutgoingContent.WriteChannelContent
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object writeTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteWriteChannel r26, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r27) {
        /*
            Method dump skipped, instructions count: 984
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.request.forms.MultiPartFormDataContent.writeTo(kotlinx.coroutines.io.ByteWriteChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
