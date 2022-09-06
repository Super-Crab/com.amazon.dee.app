package io.ktor.network.selector;

import java.io.Closeable;
import java.nio.channels.spi.SelectorProvider;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: SelectorManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aT\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\u0004\b\u0001\u0010\u0001*\u00020\u00042\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u0002H\u00020\u0006¢\u0006\u0002\b\b2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u0006¢\u0006\u0002\b\bH\u0086\b¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"buildOrClose", "R", "C", "Ljava/io/Closeable;", "Lio/ktor/network/selector/SelectorManager;", "create", "Lkotlin/Function1;", "Ljava/nio/channels/spi/SelectorProvider;", "Lkotlin/ExtensionFunctionType;", "setup", "(Lio/ktor/network/selector/SelectorManager;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "ktor-network"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class SelectorManagerKt {
    public static final <C extends Closeable, R> R buildOrClose(@NotNull SelectorManager receiver$0, @NotNull Function1<? super SelectorProvider, ? extends C> create, @NotNull Function1<? super C, ? extends R> setup) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(create, "create");
        Intrinsics.checkParameterIsNotNull(setup, "setup");
        C mo12165invoke = create.mo12165invoke(receiver$0.getProvider());
        try {
            return setup.mo12165invoke(mo12165invoke);
        } catch (Throwable th) {
            mo12165invoke.close();
            throw th;
        }
    }
}
