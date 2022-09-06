package io.ktor.util.cio;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.uimanager.ViewProps;
import java.io.Closeable;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.io.ByteWriteChannel;
import kotlinx.coroutines.io.WriterScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FileChannels.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/io/WriterScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.util.cio.FileChannelsKt$readChannel$1", f = "FileChannels.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1}, l = {38, 57}, m = "invokeSuspend", n = {"$receiver$iv", "closed$iv", "it", "fileChannel", "$receiver$iv", "closed$iv", "it", "fileChannel", ViewProps.POSITION}, s = {"L$0", "I$0", "L$1", "L$2", "L$0", "I$0", "L$1", "L$2", "L$3"})
/* loaded from: classes3.dex */
public final class FileChannelsKt$readChannel$1 extends SuspendLambda implements Function2<WriterScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $endInclusive;
    final /* synthetic */ RandomAccessFile $file;
    final /* synthetic */ long $fileLength;
    final /* synthetic */ long $start;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    private WriterScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FileChannelsKt$readChannel$1(long j, long j2, long j3, RandomAccessFile randomAccessFile, Continuation continuation) {
        super(2, continuation);
        this.$start = j;
        this.$endInclusive = j2;
        this.$fileLength = j3;
        this.$file = randomAccessFile;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        FileChannelsKt$readChannel$1 fileChannelsKt$readChannel$1 = new FileChannelsKt$readChannel$1(this.$start, this.$endInclusive, this.$fileLength, this.$file, completion);
        fileChannelsKt$readChannel$1.p$ = (WriterScope) obj;
        return fileChannelsKt$readChannel$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(WriterScope writerScope, Continuation<? super Unit> continuation) {
        return ((FileChannelsKt$readChannel$1) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.io.RandomAccessFile, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.Closeable] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        ?? r1;
        Closeable closeable;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i != 0) {
                if (i == 1) {
                    FileChannel fileChannel = (FileChannel) this.L$2;
                    RandomAccessFile randomAccessFile = (RandomAccessFile) this.L$1;
                    closeable = (Closeable) this.L$0;
                    if (obj instanceof Result.Failure) {
                        throw ((Result.Failure) obj).exception;
                    }
                } else if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    Ref.LongRef longRef = (Ref.LongRef) this.L$3;
                    FileChannel fileChannel2 = (FileChannel) this.L$2;
                    RandomAccessFile randomAccessFile2 = (RandomAccessFile) this.L$1;
                    closeable = (Closeable) this.L$0;
                    if (obj instanceof Result.Failure) {
                        throw ((Result.Failure) obj).exception;
                    }
                }
            } else if (!(obj instanceof Result.Failure)) {
                WriterScope writerScope = this.p$;
                if (this.$start >= 0) {
                    if (this.$endInclusive <= this.$fileLength - 1) {
                        r1 = this.$file;
                        try {
                            FileChannel channel = r1.getChannel();
                            Intrinsics.checkExpressionValueIsNotNull(channel, "file.channel");
                            if (this.$start > 0) {
                                channel.position(this.$start);
                            }
                            if (this.$endInclusive == -1) {
                                ByteWriteChannel mo12311getChannel = writerScope.mo12311getChannel();
                                FileChannelsKt$readChannel$1$invokeSuspend$$inlined$use$lambda$1 fileChannelsKt$readChannel$1$invokeSuspend$$inlined$use$lambda$1 = new FileChannelsKt$readChannel$1$invokeSuspend$$inlined$use$lambda$1(channel, null, this, writerScope);
                                this.L$0 = r1;
                                this.I$0 = 0;
                                this.L$1 = r1;
                                this.L$2 = channel;
                                this.label = 1;
                                if (mo12311getChannel.writeSuspendSession(fileChannelsKt$readChannel$1$invokeSuspend$$inlined$use$lambda$1, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else {
                                Ref.LongRef longRef2 = new Ref.LongRef();
                                longRef2.element = this.$start;
                                ByteWriteChannel mo12311getChannel2 = writerScope.mo12311getChannel();
                                FileChannelsKt$readChannel$1$invokeSuspend$$inlined$use$lambda$2 fileChannelsKt$readChannel$1$invokeSuspend$$inlined$use$lambda$2 = new FileChannelsKt$readChannel$1$invokeSuspend$$inlined$use$lambda$2(longRef2, channel, this, writerScope);
                                this.L$0 = r1;
                                this.I$0 = 0;
                                this.L$1 = r1;
                                this.L$2 = channel;
                                this.L$3 = longRef2;
                                this.label = 2;
                                if (mo12311getChannel2.writeWhile(fileChannelsKt$readChannel$1$invokeSuspend$$inlined$use$lambda$2, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            }
                            closeable = r1;
                        } catch (Throwable th) {
                            th = th;
                            try {
                                r1.close();
                                throw th;
                            }
                        }
                    } else {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("endInclusive points to the position out of the file: file size = ");
                        outline107.append(this.$file.length());
                        outline107.append(", endInclusive = ");
                        outline107.append(this.$endInclusive);
                        throw new IllegalArgumentException(outline107.toString().toString());
                    }
                } else {
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("start position shouldn't be negative but it is ");
                    outline1072.append(this.$start);
                    throw new IllegalArgumentException(outline1072.toString().toString());
                }
            } else {
                throw ((Result.Failure) obj).exception;
            }
            Unit unit = Unit.INSTANCE;
            closeable.close();
            return Unit.INSTANCE;
        } catch (Throwable th2) {
            th = th2;
            r1 = coroutine_suspended;
        }
    }
}
