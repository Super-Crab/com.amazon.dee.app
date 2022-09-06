package kotlinx.atomicfu;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlinx.atomicfu.LockFreedomTestEnvironment;
/* compiled from: LockFreedomTestEnvironment.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"kotlinx/atomicfu/LockFreedomTestEnvironment$testThread$1", "Lkotlinx/atomicfu/LockFreedomTestEnvironment$TestThread;", "Lkotlinx/atomicfu/LockFreedomTestEnvironment;", "operation", "", "atomicfu"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class LockFreedomTestEnvironment$testThread$1 extends LockFreedomTestEnvironment.TestThread {
    final /* synthetic */ String $name;
    final /* synthetic */ Function1 $operation;
    final /* synthetic */ LockFreedomTestEnvironment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LockFreedomTestEnvironment$testThread$1(LockFreedomTestEnvironment lockFreedomTestEnvironment, Function1 function1, String str, String str2) {
        super(str2);
        this.this$0 = lockFreedomTestEnvironment;
        this.$operation = function1;
        this.$name = str;
    }

    @Override // kotlinx.atomicfu.LockFreedomTestEnvironment.TestThread
    public void operation() {
        this.$operation.mo12165invoke(this);
    }
}
