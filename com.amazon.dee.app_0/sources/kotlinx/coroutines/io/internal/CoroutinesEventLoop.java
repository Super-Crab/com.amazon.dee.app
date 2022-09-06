package kotlinx.coroutines.io.internal;

import com.dee.app.metrics.MetricsConstants;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: EventLoopExperimental.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0002\u0006\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\u0003\u001a\u00020\u0004H ¢\u0006\u0002\b\u0005\u0082\u0001\u0002\b\t¨\u0006\n"}, d2 = {"Lkotlinx/coroutines/io/internal/CoroutinesEventLoop;", "", "()V", "processEventLoop", "", "processEventLoop$kotlinx_coroutines_io", "FutureReflectionImpl", "Stub", "Lkotlinx/coroutines/io/internal/CoroutinesEventLoop$Stub;", "Lkotlinx/coroutines/io/internal/CoroutinesEventLoop$FutureReflectionImpl;", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public abstract class CoroutinesEventLoop {

    /* compiled from: EventLoopExperimental.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\n\u001a\u00020\u000bH\u0010¢\u0006\u0002\b\fR\u0014\u0010\u0003\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/io/internal/CoroutinesEventLoop$FutureReflectionImpl;", "Lkotlinx/coroutines/io/internal/CoroutinesEventLoop;", "()V", "clazz", "Ljava/lang/Class;", "isApplicable", "", "()Z", MetricsConstants.NativeFetch.METHOD, "Ljava/lang/reflect/Method;", "processEventLoop", "", "processEventLoop$kotlinx_coroutines_io", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public static final class FutureReflectionImpl extends CoroutinesEventLoop {
        public static final FutureReflectionImpl INSTANCE = new FutureReflectionImpl();
        private static final Class<?> clazz;
        private static final boolean isApplicable;
        private static final Method method;

        /* JADX WARN: Removed duplicated region for block: B:26:0x0063  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x0068 A[SYNTHETIC] */
        static {
            /*
                kotlinx.coroutines.io.internal.CoroutinesEventLoop$FutureReflectionImpl r0 = new kotlinx.coroutines.io.internal.CoroutinesEventLoop$FutureReflectionImpl
                r0.<init>()
                kotlinx.coroutines.io.internal.CoroutinesEventLoop.FutureReflectionImpl.INSTANCE = r0
                r0 = 0
                java.lang.String r1 = "kotlinx.coroutines.EventLoopKt"
                java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch: java.lang.Throwable -> Lf
                goto L10
            Lf:
                r1 = r0
            L10:
                kotlinx.coroutines.io.internal.CoroutinesEventLoop.FutureReflectionImpl.clazz = r1
                java.lang.Class<?> r1 = kotlinx.coroutines.io.internal.CoroutinesEventLoop.FutureReflectionImpl.clazz
                r2 = 1
                r3 = 0
                if (r1 == 0) goto L6f
                java.lang.reflect.Method[] r1 = r1.getMethods()
                if (r1 == 0) goto L6f
                int r4 = r1.length
                r7 = r0
                r5 = r3
                r6 = r5
            L22:
                if (r5 >= r4) goto L6b
                r8 = r1[r5]
                java.lang.String r9 = "it"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r9)
                java.lang.String r9 = r8.getName()
                java.lang.String r10 = "processNextEventInCurrentThread"
                boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual(r9, r10)
                if (r9 == 0) goto L60
                java.lang.Class r9 = r8.getReturnType()
                java.lang.Class r10 = java.lang.Long.TYPE
                boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual(r9, r10)
                if (r9 == 0) goto L60
                java.lang.Class[] r9 = r8.getParameterTypes()
                java.lang.String r10 = "it.parameterTypes"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r10)
                int r9 = r9.length
                if (r9 != 0) goto L51
                r9 = r2
                goto L52
            L51:
                r9 = r3
            L52:
                if (r9 == 0) goto L60
                int r9 = r8.getModifiers()
                boolean r9 = java.lang.reflect.Modifier.isStatic(r9)
                if (r9 == 0) goto L60
                r9 = r2
                goto L61
            L60:
                r9 = r3
            L61:
                if (r9 == 0) goto L68
                if (r6 == 0) goto L66
                goto L6f
            L66:
                r6 = r2
                r7 = r8
            L68:
                int r5 = r5 + 1
                goto L22
            L6b:
                if (r6 != 0) goto L6e
                goto L6f
            L6e:
                r0 = r7
            L6f:
                kotlinx.coroutines.io.internal.CoroutinesEventLoop.FutureReflectionImpl.method = r0
                java.lang.reflect.Method r0 = kotlinx.coroutines.io.internal.CoroutinesEventLoop.FutureReflectionImpl.method
                if (r0 == 0) goto L76
                goto L77
            L76:
                r2 = r3
            L77:
                kotlinx.coroutines.io.internal.CoroutinesEventLoop.FutureReflectionImpl.isApplicable = r2
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.internal.CoroutinesEventLoop.FutureReflectionImpl.<clinit>():void");
        }

        private FutureReflectionImpl() {
            super(null);
        }

        public final boolean isApplicable() {
            return isApplicable;
        }

        @Override // kotlinx.coroutines.io.internal.CoroutinesEventLoop
        public long processEventLoop$kotlinx_coroutines_io() {
            Method method2 = method;
            if (method2 == null) {
                Intrinsics.throwNpe();
            }
            Object invoke = method2.invoke(null, new Object[0]);
            if (invoke != null) {
                return ((Long) invoke).longValue();
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Long");
        }
    }

    /* compiled from: EventLoopExperimental.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\u0003\u001a\u00020\u0004H\u0010¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lkotlinx/coroutines/io/internal/CoroutinesEventLoop$Stub;", "Lkotlinx/coroutines/io/internal/CoroutinesEventLoop;", "()V", "processEventLoop", "", "processEventLoop$kotlinx_coroutines_io", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public static final class Stub extends CoroutinesEventLoop {
        public static final Stub INSTANCE = new Stub();

        private Stub() {
            super(null);
        }

        @Override // kotlinx.coroutines.io.internal.CoroutinesEventLoop
        public long processEventLoop$kotlinx_coroutines_io() {
            return Long.MAX_VALUE;
        }
    }

    private CoroutinesEventLoop() {
    }

    public abstract long processEventLoop$kotlinx_coroutines_io();

    public /* synthetic */ CoroutinesEventLoop(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
