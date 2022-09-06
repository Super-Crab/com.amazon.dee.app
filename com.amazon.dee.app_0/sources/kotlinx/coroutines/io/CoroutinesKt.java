package kotlinx.coroutines.io;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.routing.api.RouteParameter;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Coroutines.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u001aW\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072'\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\r0\t¢\u0006\u0002\b\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001aU\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072'\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\r0\t¢\u0006\u0002\b\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001aW\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072'\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\r0\t¢\u0006\u0002\b\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001aU\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072'\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\r0\t¢\u0006\u0002\b\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001a_\u0010\u0018\u001a\u00020\u0019\"\b\b\u0000\u0010\u001a*\u00020\u001b*\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u00052'\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u0002H\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\r0\t¢\u0006\u0002\b\u000eH\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001aO\u0010\u0000\u001a\u00020\u0001*\u00020\u001b2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052'\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\r0\t¢\u0006\u0002\b\u000eø\u0001\u0000¢\u0006\u0002\u0010\u001f\u001aM\u0010\u0000\u001a\u00020\u0001*\u00020\u001b2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112'\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\r0\t¢\u0006\u0002\b\u000eø\u0001\u0000¢\u0006\u0002\u0010 \u001aO\u0010\u0013\u001a\u00020\u0014*\u00020\u001b2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052'\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\r0\t¢\u0006\u0002\b\u000eø\u0001\u0000¢\u0006\u0002\u0010!\u001aM\u0010\u0013\u001a\u00020\u0014*\u00020\u001b2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112'\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\r0\t¢\u0006\u0002\b\u000eø\u0001\u0000¢\u0006\u0002\u0010\"\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006#"}, d2 = {"reader", "Lkotlinx/coroutines/io/ReaderJob;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "autoFlush", "", RouteParameter.PARENT, "Lkotlinx/coroutines/Job;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/io/ReaderScope;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/coroutines/CoroutineContext;ZLkotlinx/coroutines/Job;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/io/ReaderJob;", "channel", "Lkotlinx/coroutines/io/ByteChannel;", "(Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/io/ByteChannel;Lkotlinx/coroutines/Job;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/io/ReaderJob;", "writer", "Lkotlinx/coroutines/io/WriterJob;", "Lkotlinx/coroutines/io/WriterScope;", "(Lkotlin/coroutines/CoroutineContext;ZLkotlinx/coroutines/Job;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/io/WriterJob;", "(Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/io/ByteChannel;Lkotlinx/coroutines/Job;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/io/WriterJob;", "launchChannel", "Lkotlinx/coroutines/io/ChannelJob;", ExifInterface.LATITUDE_SOUTH, "Lkotlinx/coroutines/CoroutineScope;", "context", "attachJob", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/io/ByteChannel;ZLkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/io/ChannelJob;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;ZLkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/io/ReaderJob;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/io/ByteChannel;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/io/ReaderJob;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;ZLkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/io/WriterJob;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/io/ByteChannel;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/io/WriterJob;", "kotlinx-coroutines-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class CoroutinesKt {
    private static final <S extends CoroutineScope> ChannelJob launchChannel(@NotNull CoroutineScope coroutineScope, CoroutineContext coroutineContext, ByteChannel byteChannel, boolean z, Function2<? super S, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope, coroutineContext, null, new CoroutinesKt$launchChannel$job$1(z, byteChannel, function2, null), 2, null);
        launch$default.invokeOnCompletion(new CoroutinesKt$launchChannel$1(byteChannel));
        return new ChannelJob(launch$default, byteChannel);
    }

    @NotNull
    public static final ReaderJob reader(@NotNull CoroutineScope receiver$0, @NotNull CoroutineContext coroutineContext, @NotNull ByteChannel channel, @NotNull Function2<? super ReaderScope, ? super Continuation<? super Unit>, ? extends Object> block) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "coroutineContext");
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Intrinsics.checkParameterIsNotNull(block, "block");
        return launchChannel(receiver$0, coroutineContext, channel, false, block);
    }

    @NotNull
    public static /* synthetic */ ReaderJob reader$default(CoroutineScope coroutineScope, CoroutineContext coroutineContext, ByteChannel byteChannel, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return reader(coroutineScope, coroutineContext, byteChannel, function2);
    }

    @NotNull
    public static final WriterJob writer(@NotNull CoroutineScope receiver$0, @NotNull CoroutineContext coroutineContext, @NotNull ByteChannel channel, @NotNull Function2<? super WriterScope, ? super Continuation<? super Unit>, ? extends Object> block) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "coroutineContext");
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Intrinsics.checkParameterIsNotNull(block, "block");
        return launchChannel(receiver$0, coroutineContext, channel, false, block);
    }

    @NotNull
    public static /* synthetic */ WriterJob writer$default(CoroutineScope coroutineScope, CoroutineContext coroutineContext, ByteChannel byteChannel, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return writer(coroutineScope, coroutineContext, byteChannel, function2);
    }

    @NotNull
    public static final ReaderJob reader(@NotNull CoroutineScope receiver$0, @NotNull CoroutineContext coroutineContext, boolean z, @NotNull Function2<? super ReaderScope, ? super Continuation<? super Unit>, ? extends Object> block) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "coroutineContext");
        Intrinsics.checkParameterIsNotNull(block, "block");
        return launchChannel(receiver$0, coroutineContext, ByteChannelKt.ByteChannel(z), true, block);
    }

    @NotNull
    public static /* synthetic */ ReaderJob reader$default(CoroutineScope coroutineScope, CoroutineContext coroutineContext, boolean z, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        return reader(coroutineScope, coroutineContext, z, function2);
    }

    @NotNull
    public static final WriterJob writer(@NotNull CoroutineScope receiver$0, @NotNull CoroutineContext coroutineContext, boolean z, @NotNull Function2<? super WriterScope, ? super Continuation<? super Unit>, ? extends Object> block) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "coroutineContext");
        Intrinsics.checkParameterIsNotNull(block, "block");
        return launchChannel(receiver$0, coroutineContext, ByteChannelKt.ByteChannel(z), true, block);
    }

    @NotNull
    public static /* synthetic */ WriterJob writer$default(CoroutineScope coroutineScope, CoroutineContext coroutineContext, boolean z, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        return writer(coroutineScope, coroutineContext, z, function2);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use scope.reader instead")
    @NotNull
    public static final ReaderJob reader(@NotNull CoroutineContext coroutineContext, @NotNull ByteChannel channel, @Nullable Job job, @NotNull Function2<? super ReaderScope, ? super Continuation<? super Unit>, ? extends Object> block) {
        CoroutineContext newCoroutineContext;
        Intrinsics.checkParameterIsNotNull(coroutineContext, "coroutineContext");
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Intrinsics.checkParameterIsNotNull(block, "block");
        if (job != null) {
            newCoroutineContext = CoroutineContextKt.newCoroutineContext(GlobalScope.INSTANCE, coroutineContext.plus(job));
        } else {
            newCoroutineContext = CoroutineContextKt.newCoroutineContext(GlobalScope.INSTANCE, coroutineContext);
        }
        return reader(CoroutineScopeKt.CoroutineScope(newCoroutineContext), EmptyCoroutineContext.INSTANCE, channel, block);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use scope.writer instead")
    @NotNull
    public static final WriterJob writer(@NotNull CoroutineContext coroutineContext, @NotNull ByteChannel channel, @Nullable Job job, @NotNull Function2<? super WriterScope, ? super Continuation<? super Unit>, ? extends Object> block) {
        CoroutineContext newCoroutineContext;
        Intrinsics.checkParameterIsNotNull(coroutineContext, "coroutineContext");
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Intrinsics.checkParameterIsNotNull(block, "block");
        if (job != null) {
            newCoroutineContext = CoroutineContextKt.newCoroutineContext(GlobalScope.INSTANCE, coroutineContext.plus(job));
        } else {
            newCoroutineContext = CoroutineContextKt.newCoroutineContext(GlobalScope.INSTANCE, coroutineContext);
        }
        return writer(CoroutineScopeKt.CoroutineScope(newCoroutineContext), EmptyCoroutineContext.INSTANCE, channel, block);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use scope.reader instead")
    @NotNull
    public static /* synthetic */ ReaderJob reader$default(CoroutineContext coroutineContext, ByteChannel byteChannel, Job job, Function2 function2, int i, Object obj) {
        if ((i & 4) != 0) {
            job = null;
        }
        return reader(coroutineContext, byteChannel, job, function2);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use scope.writer instead")
    @NotNull
    public static /* synthetic */ WriterJob writer$default(CoroutineContext coroutineContext, ByteChannel byteChannel, Job job, Function2 function2, int i, Object obj) {
        if ((i & 4) != 0) {
            job = null;
        }
        return writer(coroutineContext, byteChannel, job, function2);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use scope.reader instead")
    @NotNull
    public static /* synthetic */ ReaderJob reader$default(CoroutineContext coroutineContext, boolean z, Job job, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            job = null;
        }
        return reader(coroutineContext, z, job, function2);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use scope.writer instead")
    @NotNull
    public static /* synthetic */ WriterJob writer$default(CoroutineContext coroutineContext, boolean z, Job job, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            job = null;
        }
        return writer(coroutineContext, z, job, function2);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use scope.reader instead")
    @NotNull
    public static final ReaderJob reader(@NotNull CoroutineContext coroutineContext, boolean z, @Nullable Job job, @NotNull Function2<? super ReaderScope, ? super Continuation<? super Unit>, ? extends Object> block) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "coroutineContext");
        Intrinsics.checkParameterIsNotNull(block, "block");
        ByteChannel ByteChannel = ByteChannelKt.ByteChannel(z);
        ReaderJob reader = reader(coroutineContext, ByteChannel, job, block);
        ByteChannel.attachJob(reader);
        return reader;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use scope.writer instead")
    @NotNull
    public static final WriterJob writer(@NotNull CoroutineContext coroutineContext, boolean z, @Nullable Job job, @NotNull Function2<? super WriterScope, ? super Continuation<? super Unit>, ? extends Object> block) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "coroutineContext");
        Intrinsics.checkParameterIsNotNull(block, "block");
        ByteChannel ByteChannel = ByteChannelKt.ByteChannel(z);
        WriterJob writer = writer(coroutineContext, ByteChannel, job, block);
        ByteChannel.attachJob(writer);
        return writer;
    }
}
