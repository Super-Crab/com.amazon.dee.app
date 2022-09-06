package kotlin.sequences;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.BuilderInference;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"kotlin/sequences/SequencesKt__SequenceBuilderKt", "kotlin/sequences/SequencesKt__SequencesJVMKt", "kotlin/sequences/SequencesKt__SequencesKt", "kotlin/sequences/SequencesKt___SequencesJvmKt", "kotlin/sequences/SequencesKt___SequencesKt"}, k = 4, mv = {1, 1, 16}, xi = 1)
/* loaded from: classes4.dex */
public final class SequencesKt extends SequencesKt___SequencesKt {
    private SequencesKt() {
    }

    @NotNull
    public static /* bridge */ /* synthetic */ <T> Sequence<T> asSequence(@NotNull Iterator<? extends T> it2) {
        return SequencesKt__SequencesKt.asSequence(it2);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ <T> Sequence<T> constrainOnce(@NotNull Sequence<? extends T> sequence) {
        return SequencesKt__SequencesKt.constrainOnce(sequence);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ <T> Sequence<T> drop(@NotNull Sequence<? extends T> sequence, int i) {
        return SequencesKt___SequencesKt.drop(sequence, i);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ <T> Sequence<T> emptySequence() {
        return SequencesKt__SequencesKt.emptySequence();
    }

    @Nullable
    public static /* bridge */ /* synthetic */ <T> T firstOrNull(@NotNull Sequence<? extends T> sequence) {
        return (T) SequencesKt___SequencesKt.firstOrNull(sequence);
    }

    @LowPriorityInOverloadResolution
    @NotNull
    public static /* bridge */ /* synthetic */ <T> Sequence<T> generateSequence(@Nullable T t, @NotNull Function1<? super T, ? extends T> function1) {
        return SequencesKt__SequencesKt.generateSequence(t, function1);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ <T> Sequence<T> generateSequence(@NotNull Function0<? extends T> function0, @NotNull Function1<? super T, ? extends T> function1) {
        return SequencesKt__SequencesKt.generateSequence((Function0) function0, (Function1) function1);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ <T> Sequence<T> plus(@NotNull Sequence<? extends T> sequence, @NotNull Iterable<? extends T> iterable) {
        return SequencesKt___SequencesKt.plus((Sequence) sequence, (Iterable) iterable);
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static /* bridge */ /* synthetic */ <T> Sequence<T> sequence(@BuilderInference @NotNull Function2<? super SequenceScope<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return SequencesKt__SequenceBuilderKt.sequence(function2);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ <T> HashSet<T> toHashSet(@NotNull Sequence<? extends T> sequence) {
        return SequencesKt___SequencesKt.toHashSet(sequence);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ <T> List<T> toList(@NotNull Sequence<? extends T> sequence) {
        return SequencesKt___SequencesKt.toList(sequence);
    }
}
