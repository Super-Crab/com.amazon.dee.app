package kotlinx.coroutines.io.jvm.javaio;

import kotlin.Metadata;
import kotlinx.coroutines.Job;
/* compiled from: Blocking.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0011\u0010\u0002\u001a\u00020\u0003H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010\u0004\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0005"}, d2 = {"kotlinx/coroutines/io/jvm/javaio/OutputAdapter$loop$1", "Lkotlinx/coroutines/io/jvm/javaio/BlockingAdapter;", "loop", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class OutputAdapter$loop$1 extends BlockingAdapter {
    final /* synthetic */ Job $parent;
    final /* synthetic */ OutputAdapter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OutputAdapter$loop$1(OutputAdapter outputAdapter, Job job, Job job2) {
        super(job2);
        this.this$0 = outputAdapter;
        this.$parent = job;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0106, code lost:
        throw new java.lang.IllegalStateException("Already suspended or in finished state");
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x006e A[Catch: all -> 0x0054, TryCatch #0 {all -> 0x0054, blocks: (B:13:0x002e, B:32:0x005d, B:33:0x0068, B:35:0x006e, B:39:0x0080, B:42:0x008a, B:43:0x008d, B:45:0x0097, B:48:0x009d, B:58:0x00c0, B:60:0x00c6, B:63:0x00dc, B:64:0x00dd, B:66:0x00e1, B:36:0x0076, B:38:0x007c, B:69:0x00ff, B:70:0x0106, B:16:0x0033, B:17:0x0037, B:21:0x004a, B:24:0x004f, B:25:0x0053), top: B:84:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0076 A[Catch: all -> 0x0054, TryCatch #0 {all -> 0x0054, blocks: (B:13:0x002e, B:32:0x005d, B:33:0x0068, B:35:0x006e, B:39:0x0080, B:42:0x008a, B:43:0x008d, B:45:0x0097, B:48:0x009d, B:58:0x00c0, B:60:0x00c6, B:63:0x00dc, B:64:0x00dd, B:66:0x00e1, B:36:0x0076, B:38:0x007c, B:69:0x00ff, B:70:0x0106, B:16:0x0033, B:17:0x0037, B:21:0x004a, B:24:0x004f, B:25:0x0053), top: B:84:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x008a A[Catch: all -> 0x0054, TryCatch #0 {all -> 0x0054, blocks: (B:13:0x002e, B:32:0x005d, B:33:0x0068, B:35:0x006e, B:39:0x0080, B:42:0x008a, B:43:0x008d, B:45:0x0097, B:48:0x009d, B:58:0x00c0, B:60:0x00c6, B:63:0x00dc, B:64:0x00dd, B:66:0x00e1, B:36:0x0076, B:38:0x007c, B:69:0x00ff, B:70:0x0106, B:16:0x0033, B:17:0x0037, B:21:0x004a, B:24:0x004f, B:25:0x0053), top: B:84:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0097 A[Catch: all -> 0x0054, TryCatch #0 {all -> 0x0054, blocks: (B:13:0x002e, B:32:0x005d, B:33:0x0068, B:35:0x006e, B:39:0x0080, B:42:0x008a, B:43:0x008d, B:45:0x0097, B:48:0x009d, B:58:0x00c0, B:60:0x00c6, B:63:0x00dc, B:64:0x00dd, B:66:0x00e1, B:36:0x0076, B:38:0x007c, B:69:0x00ff, B:70:0x0106, B:16:0x0033, B:17:0x0037, B:21:0x004a, B:24:0x004f, B:25:0x0053), top: B:84:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x009c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00c0 A[Catch: all -> 0x0054, TRY_ENTER, TryCatch #0 {all -> 0x0054, blocks: (B:13:0x002e, B:32:0x005d, B:33:0x0068, B:35:0x006e, B:39:0x0080, B:42:0x008a, B:43:0x008d, B:45:0x0097, B:48:0x009d, B:58:0x00c0, B:60:0x00c6, B:63:0x00dc, B:64:0x00dd, B:66:0x00e1, B:36:0x0076, B:38:0x007c, B:69:0x00ff, B:70:0x0106, B:16:0x0033, B:17:0x0037, B:21:0x004a, B:24:0x004f, B:25:0x0053), top: B:84:0x0022 }] */
    /* JADX WARN: Type inference failed for: r2v0, types: [int, kotlinx.coroutines.io.jvm.javaio.OutputAdapter$loop$1] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:62:0x00db -> B:31:0x005c). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:65:0x00df -> B:31:0x005c). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:67:0x00fc -> B:31:0x005c). Please submit an issue!!! */
    @Override // kotlinx.coroutines.io.jvm.javaio.BlockingAdapter
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object loop(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            Method dump skipped, instructions count: 309
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.jvm.javaio.OutputAdapter$loop$1.loop(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
