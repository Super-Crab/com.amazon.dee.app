package io.ktor.client.utils;

import io.ktor.http.ContentType;
import io.ktor.http.Headers;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.content.OutgoingContent;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.LongRange;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.io.ByteReadChannel;
import kotlinx.coroutines.io.ByteWriteChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Content.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¨\u0006\u0005"}, d2 = {"wrapHeaders", "Lio/ktor/http/content/OutgoingContent;", "block", "Lkotlin/Function1;", "Lio/ktor/http/Headers;", "ktor-client-core"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class ContentKt {
    @NotNull
    public static final OutgoingContent wrapHeaders(@NotNull final OutgoingContent receiver$0, @NotNull final Function1<? super Headers, ? extends Headers> block) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(block, "block");
        if (receiver$0 instanceof OutgoingContent.NoContent) {
            return new OutgoingContent.NoContent(block) { // from class: io.ktor.client.utils.ContentKt$wrapHeaders$1
                final /* synthetic */ Function1 $block;
                @NotNull
                private final Headers headers;

                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    this.$block = block;
                    this.headers = (Headers) block.mo12165invoke(OutgoingContent.this.getHeaders());
                }

                @Override // io.ktor.http.content.OutgoingContent
                @Nullable
                public Long getContentLength() {
                    return OutgoingContent.this.getContentLength();
                }

                @Override // io.ktor.http.content.OutgoingContent
                @Nullable
                public ContentType getContentType() {
                    return OutgoingContent.this.getContentType();
                }

                @Override // io.ktor.http.content.OutgoingContent
                @NotNull
                public Headers getHeaders() {
                    return this.headers;
                }

                @Override // io.ktor.http.content.OutgoingContent
                @Nullable
                public HttpStatusCode getStatus() {
                    return OutgoingContent.this.getStatus();
                }
            };
        }
        if (receiver$0 instanceof OutgoingContent.ReadChannelContent) {
            return new OutgoingContent.ReadChannelContent(block) { // from class: io.ktor.client.utils.ContentKt$wrapHeaders$2
                final /* synthetic */ Function1 $block;
                @NotNull
                private final Headers headers;

                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    this.$block = block;
                    this.headers = (Headers) block.mo12165invoke(OutgoingContent.this.getHeaders());
                }

                @Override // io.ktor.http.content.OutgoingContent
                @Nullable
                public Long getContentLength() {
                    return OutgoingContent.this.getContentLength();
                }

                @Override // io.ktor.http.content.OutgoingContent
                @Nullable
                public ContentType getContentType() {
                    return OutgoingContent.this.getContentType();
                }

                @Override // io.ktor.http.content.OutgoingContent
                @NotNull
                public Headers getHeaders() {
                    return this.headers;
                }

                @Override // io.ktor.http.content.OutgoingContent
                @Nullable
                public HttpStatusCode getStatus() {
                    return OutgoingContent.this.getStatus();
                }

                @Override // io.ktor.http.content.OutgoingContent.ReadChannelContent
                @NotNull
                public ByteReadChannel readFrom() {
                    return ((OutgoingContent.ReadChannelContent) OutgoingContent.this).readFrom();
                }

                @Override // io.ktor.http.content.OutgoingContent.ReadChannelContent
                @NotNull
                public ByteReadChannel readFrom(@NotNull LongRange range) {
                    Intrinsics.checkParameterIsNotNull(range, "range");
                    return ((OutgoingContent.ReadChannelContent) OutgoingContent.this).readFrom(range);
                }
            };
        }
        if (receiver$0 instanceof OutgoingContent.WriteChannelContent) {
            return new OutgoingContent.WriteChannelContent(block) { // from class: io.ktor.client.utils.ContentKt$wrapHeaders$3
                final /* synthetic */ Function1 $block;
                @NotNull
                private final Headers headers;

                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    this.$block = block;
                    this.headers = (Headers) block.mo12165invoke(OutgoingContent.this.getHeaders());
                }

                @Override // io.ktor.http.content.OutgoingContent
                @Nullable
                public Long getContentLength() {
                    return OutgoingContent.this.getContentLength();
                }

                @Override // io.ktor.http.content.OutgoingContent
                @Nullable
                public ContentType getContentType() {
                    return OutgoingContent.this.getContentType();
                }

                @Override // io.ktor.http.content.OutgoingContent
                @NotNull
                public Headers getHeaders() {
                    return this.headers;
                }

                @Override // io.ktor.http.content.OutgoingContent
                @Nullable
                public HttpStatusCode getStatus() {
                    return OutgoingContent.this.getStatus();
                }

                @Override // io.ktor.http.content.OutgoingContent.WriteChannelContent
                @Nullable
                public Object writeTo(@NotNull ByteWriteChannel byteWriteChannel, @NotNull Continuation<? super Unit> continuation) {
                    return ((OutgoingContent.WriteChannelContent) OutgoingContent.this).writeTo(byteWriteChannel, continuation);
                }
            };
        }
        if (receiver$0 instanceof OutgoingContent.ByteArrayContent) {
            return new OutgoingContent.ByteArrayContent(block) { // from class: io.ktor.client.utils.ContentKt$wrapHeaders$4
                final /* synthetic */ Function1 $block;
                @NotNull
                private final Headers headers;

                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    this.$block = block;
                    this.headers = (Headers) block.mo12165invoke(OutgoingContent.this.getHeaders());
                }

                @Override // io.ktor.http.content.OutgoingContent.ByteArrayContent
                @NotNull
                public byte[] bytes() {
                    return ((OutgoingContent.ByteArrayContent) OutgoingContent.this).bytes();
                }

                @Override // io.ktor.http.content.OutgoingContent
                @Nullable
                public Long getContentLength() {
                    return OutgoingContent.this.getContentLength();
                }

                @Override // io.ktor.http.content.OutgoingContent
                @Nullable
                public ContentType getContentType() {
                    return OutgoingContent.this.getContentType();
                }

                @Override // io.ktor.http.content.OutgoingContent
                @NotNull
                public Headers getHeaders() {
                    return this.headers;
                }

                @Override // io.ktor.http.content.OutgoingContent
                @Nullable
                public HttpStatusCode getStatus() {
                    return OutgoingContent.this.getStatus();
                }
            };
        }
        if (!(receiver$0 instanceof OutgoingContent.ProtocolUpgrade)) {
            throw new NoWhenBranchMatchedException();
        }
        return new OutgoingContent.ProtocolUpgrade(block) { // from class: io.ktor.client.utils.ContentKt$wrapHeaders$5
            final /* synthetic */ Function1 $block;
            @NotNull
            private final Headers headers;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.$block = block;
                this.headers = (Headers) block.mo12165invoke(OutgoingContent.this.getHeaders());
            }

            @Override // io.ktor.http.content.OutgoingContent
            @Nullable
            public Long getContentLength() {
                return OutgoingContent.this.getContentLength();
            }

            @Override // io.ktor.http.content.OutgoingContent
            @Nullable
            public ContentType getContentType() {
                return OutgoingContent.this.getContentType();
            }

            @Override // io.ktor.http.content.OutgoingContent
            @NotNull
            public Headers getHeaders() {
                return this.headers;
            }

            @Override // io.ktor.http.content.OutgoingContent.ProtocolUpgrade
            @Nullable
            public Object upgrade(@NotNull ByteReadChannel byteReadChannel, @NotNull ByteWriteChannel byteWriteChannel, @NotNull CoroutineContext coroutineContext, @NotNull CoroutineContext coroutineContext2, @NotNull Continuation<? super Job> continuation) {
                return ((OutgoingContent.ProtocolUpgrade) OutgoingContent.this).upgrade(byteReadChannel, byteWriteChannel, coroutineContext, coroutineContext2, continuation);
            }
        };
    }
}
