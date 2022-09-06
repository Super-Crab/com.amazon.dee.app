package io.ktor.http.content;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Multipart.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0001\u0005J\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0004\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006"}, d2 = {"Lio/ktor/http/content/MultiPartData;", "", "readPart", "Lio/ktor/http/content/PartData;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Empty", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public interface MultiPartData {

    /* compiled from: Multipart.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006"}, d2 = {"Lio/ktor/http/content/MultiPartData$Empty;", "Lio/ktor/http/content/MultiPartData;", "()V", "readPart", "Lio/ktor/http/content/PartData;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Empty implements MultiPartData {
        public static final Empty INSTANCE = new Empty();

        private Empty() {
        }

        @Override // io.ktor.http.content.MultiPartData
        @Nullable
        public Object readPart(@NotNull Continuation<? super PartData> continuation) {
            return null;
        }
    }

    @Nullable
    Object readPart(@NotNull Continuation<? super PartData> continuation);
}
