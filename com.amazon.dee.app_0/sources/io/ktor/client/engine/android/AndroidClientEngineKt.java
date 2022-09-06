package io.ktor.client.engine.android;

import io.ktor.client.call.UnsupportedContentTypeException;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.cio.ByteBufferPoolKt;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.io.ByteReadChannel;
import kotlinx.coroutines.io.CoroutinesKt;
import kotlinx.coroutines.io.jvm.javaio.BlockingKt;
import kotlinx.coroutines.io.jvm.javaio.ReadingKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: AndroidClientEngine.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u001c\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0004H\u0000¨\u0006\u000b"}, d2 = {"content", "Lkotlinx/coroutines/io/ByteReadChannel;", "Ljava/net/HttpURLConnection;", "callScope", "Lkotlin/coroutines/CoroutineContext;", "writeTo", "", "Lio/ktor/http/content/OutgoingContent;", "stream", "Ljava/io/OutputStream;", "callContext", "ktor-client-android"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class AndroidClientEngineKt {
    @NotNull
    public static final ByteReadChannel content(@NotNull HttpURLConnection receiver$0, @NotNull CoroutineContext callScope) {
        ByteReadChannel byteReadChannel;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(callScope, "callScope");
        BufferedInputStream bufferedInputStream = null;
        try {
            InputStream inputStream = receiver$0.getInputStream();
            if (inputStream != null) {
                bufferedInputStream = inputStream instanceof BufferedInputStream ? (BufferedInputStream) inputStream : new BufferedInputStream(inputStream, 8192);
            }
        } catch (IOException unused) {
            InputStream errorStream = receiver$0.getErrorStream();
            if (errorStream != null) {
                bufferedInputStream = errorStream instanceof BufferedInputStream ? (BufferedInputStream) errorStream : new BufferedInputStream(errorStream, 8192);
            }
        }
        return (bufferedInputStream == null || (byteReadChannel = ReadingKt.toByteReadChannel(bufferedInputStream, callScope, ByteBufferPoolKt.getKtorDefaultPool())) == null) ? ByteReadChannel.Companion.getEmpty() : byteReadChannel;
    }

    public static final void writeTo(@NotNull OutgoingContent receiver$0, @NotNull OutputStream stream, @NotNull CoroutineContext callContext) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(stream, "stream");
        Intrinsics.checkParameterIsNotNull(callContext, "callContext");
        try {
            if (receiver$0 instanceof OutgoingContent.ByteArrayContent) {
                stream.write(((OutgoingContent.ByteArrayContent) receiver$0).bytes());
            } else if (receiver$0 instanceof OutgoingContent.ReadChannelContent) {
                ByteStreamsKt.copyTo$default(BlockingKt.toInputStream(((OutgoingContent.ReadChannelContent) receiver$0).readFrom(), (Job) callContext.get(Job.Key)), stream, 0, 2, null);
            } else if (receiver$0 instanceof OutgoingContent.WriteChannelContent) {
                ByteStreamsKt.copyTo$default(BlockingKt.toInputStream(CoroutinesKt.writer$default((CoroutineScope) GlobalScope.INSTANCE, callContext, false, (Function2) new AndroidClientEngineKt$writeTo$$inlined$use$lambda$1(null, receiver$0, callContext), 2, (Object) null).mo12310getChannel(), (Job) callContext.get(Job.Key)), stream, 0, 2, null);
            } else {
                throw new UnsupportedContentTypeException(receiver$0);
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(stream, null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(stream, th);
                throw th2;
            }
        }
    }
}
