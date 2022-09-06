package io.ktor.http.content;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.accessory.frames.contacts.ContactsModuleConstants;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import io.ktor.http.ContentType;
import io.ktor.http.Headers;
import io.ktor.http.HttpStatusCode;
import io.ktor.util.AttributeKey;
import io.ktor.util.Attributes;
import io.ktor.util.AttributesJvmKt;
import io.ktor.util.KtorExperimentalAPI;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.LongRange;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.io.ByteReadChannel;
import kotlinx.coroutines.io.ByteWriteChannel;
import kotlinx.coroutines.io.CoroutinesKt;
import kotlinx.coroutines.io.WriterScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: OutgoingContent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u001e\u001f !\"B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J'\u0010\u0015\u001a\u0004\u0018\u0001H\u0016\"\b\b\u0000\u0010\u0016*\u00020\u00012\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00160\u0018H\u0016¢\u0006\u0002\u0010\u0019J/\u0010\u001a\u001a\u00020\u001b\"\b\b\u0000\u0010\u0016*\u00020\u00012\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00160\u00182\b\u0010\u001c\u001a\u0004\u0018\u0001H\u0016H\u0016¢\u0006\u0002\u0010\u001dR\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014\u0082\u0001\u0005#$%&'\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006("}, d2 = {"Lio/ktor/http/content/OutgoingContent;", "", "()V", "contentLength", "", "getContentLength", "()Ljava/lang/Long;", "contentType", "Lio/ktor/http/ContentType;", "getContentType", "()Lio/ktor/http/ContentType;", "extensionProperties", "Lio/ktor/util/Attributes;", HttpClientModule.ElementsRequestKey.HEADERS, "Lio/ktor/http/Headers;", "getHeaders", "()Lio/ktor/http/Headers;", "status", "Lio/ktor/http/HttpStatusCode;", "getStatus", "()Lio/ktor/http/HttpStatusCode;", "getProperty", ExifInterface.GPS_DIRECTION_TRUE, "key", "Lio/ktor/util/AttributeKey;", "(Lio/ktor/util/AttributeKey;)Ljava/lang/Object;", "setProperty", "", "value", "(Lio/ktor/util/AttributeKey;Ljava/lang/Object;)V", "ByteArrayContent", "NoContent", "ProtocolUpgrade", "ReadChannelContent", "WriteChannelContent", "Lio/ktor/http/content/OutgoingContent$NoContent;", "Lio/ktor/http/content/OutgoingContent$ReadChannelContent;", "Lio/ktor/http/content/OutgoingContent$WriteChannelContent;", "Lio/ktor/http/content/OutgoingContent$ByteArrayContent;", "Lio/ktor/http/content/OutgoingContent$ProtocolUpgrade;", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public abstract class OutgoingContent {
    private Attributes extensionProperties;

    /* compiled from: OutgoingContent.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0005"}, d2 = {"Lio/ktor/http/content/OutgoingContent$ByteArrayContent;", "Lio/ktor/http/content/OutgoingContent;", "()V", "bytes", "", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static abstract class ByteArrayContent extends OutgoingContent {
        public ByteArrayContent() {
            super(null);
        }

        @NotNull
        public abstract byte[] bytes();
    }

    /* compiled from: OutgoingContent.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ktor/http/content/OutgoingContent$NoContent;", "Lio/ktor/http/content/OutgoingContent;", "()V", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static abstract class NoContent extends OutgoingContent {
        public NoContent() {
            super(null);
        }
    }

    /* compiled from: OutgoingContent.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J1\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH§@ø\u0001\u0000¢\u0006\u0002\u0010\u0010R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lio/ktor/http/content/OutgoingContent$ProtocolUpgrade;", "Lio/ktor/http/content/OutgoingContent;", "()V", "status", "Lio/ktor/http/HttpStatusCode;", "getStatus", "()Lio/ktor/http/HttpStatusCode;", "upgrade", "Lkotlinx/coroutines/Job;", "input", "Lkotlinx/coroutines/io/ByteReadChannel;", ContactsModuleConstants.OUTPUT, "Lkotlinx/coroutines/io/ByteWriteChannel;", "engineContext", "Lkotlin/coroutines/CoroutineContext;", "userContext", "(Lkotlinx/coroutines/io/ByteReadChannel;Lkotlinx/coroutines/io/ByteWriteChannel;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static abstract class ProtocolUpgrade extends OutgoingContent {
        public ProtocolUpgrade() {
            super(null);
        }

        @Override // io.ktor.http.content.OutgoingContent
        @Nullable
        public final HttpStatusCode getStatus() {
            return HttpStatusCode.Companion.getSwitchingProtocols();
        }

        @KtorExperimentalAPI
        @Nullable
        public abstract Object upgrade(@NotNull ByteReadChannel byteReadChannel, @NotNull ByteWriteChannel byteWriteChannel, @NotNull CoroutineContext coroutineContext, @NotNull CoroutineContext coroutineContext2, @NotNull Continuation<? super Job> continuation);
    }

    /* compiled from: OutgoingContent.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lio/ktor/http/content/OutgoingContent$ReadChannelContent;", "Lio/ktor/http/content/OutgoingContent;", "()V", "readFrom", "Lkotlinx/coroutines/io/ByteReadChannel;", "range", "Lkotlin/ranges/LongRange;", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static abstract class ReadChannelContent extends OutgoingContent {
        public ReadChannelContent() {
            super(null);
        }

        @NotNull
        public abstract ByteReadChannel readFrom();

        @NotNull
        public ByteReadChannel readFrom(@NotNull LongRange range) {
            Intrinsics.checkParameterIsNotNull(range, "range");
            return range.isEmpty() ? ByteReadChannel.Companion.getEmpty() : CoroutinesKt.writer((CoroutineScope) GlobalScope.INSTANCE, (CoroutineContext) Dispatchers.getUnconfined(), true, (Function2<? super WriterScope, ? super Continuation<? super Unit>, ? extends Object>) new OutgoingContent$ReadChannelContent$readFrom$1(this, range, null)).mo12310getChannel();
        }
    }

    /* compiled from: OutgoingContent.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0019\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, d2 = {"Lio/ktor/http/content/OutgoingContent$WriteChannelContent;", "Lio/ktor/http/content/OutgoingContent;", "()V", "writeTo", "", "channel", "Lkotlinx/coroutines/io/ByteWriteChannel;", "(Lkotlinx/coroutines/io/ByteWriteChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static abstract class WriteChannelContent extends OutgoingContent {
        public WriteChannelContent() {
            super(null);
        }

        @Nullable
        public abstract Object writeTo(@NotNull ByteWriteChannel byteWriteChannel, @NotNull Continuation<? super Unit> continuation);
    }

    private OutgoingContent() {
    }

    @Nullable
    public Long getContentLength() {
        return null;
    }

    @Nullable
    public ContentType getContentType() {
        return null;
    }

    @NotNull
    public Headers getHeaders() {
        return Headers.Companion.getEmpty();
    }

    @Nullable
    public <T> T getProperty(@NotNull AttributeKey<T> key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Attributes attributes = this.extensionProperties;
        if (attributes != null) {
            return (T) attributes.getOrNull(key);
        }
        return null;
    }

    @Nullable
    public HttpStatusCode getStatus() {
        return null;
    }

    public <T> void setProperty(@NotNull AttributeKey<T> key, @Nullable T t) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (t == null && this.extensionProperties == null) {
            return;
        }
        if (t == null) {
            Attributes attributes = this.extensionProperties;
            if (attributes == null) {
                return;
            }
            attributes.remove(key);
            return;
        }
        Attributes attributes2 = this.extensionProperties;
        if (attributes2 == null) {
            attributes2 = AttributesJvmKt.Attributes$default(false, 1, null);
        }
        this.extensionProperties = attributes2;
        attributes2.put(key, t);
    }

    public /* synthetic */ OutgoingContent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
