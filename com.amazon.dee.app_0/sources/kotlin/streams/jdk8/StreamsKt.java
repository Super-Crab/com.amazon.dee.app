package kotlin.streams.jdk8;

import androidx.exifinterface.media.ExifInterface;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
/* compiled from: Streams.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\u001a\u0012\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u0007\u001a\u0012\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001*\u00020\u0005H\u0007\u001a\u0012\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001*\u00020\u0007H\u0007\u001a\u001e\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\b0\u0001\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\tH\u0007\u001a\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\t\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u0001H\u0007\u001a\u0012\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\f*\u00020\u0003H\u0007\u001a\u0012\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\f*\u00020\u0005H\u0007\u001a\u0012\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\f*\u00020\u0007H\u0007\u001a\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\b0\f\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\tH\u0007¨\u0006\r"}, d2 = {"asSequence", "Lkotlin/sequences/Sequence;", "", "Ljava/util/stream/DoubleStream;", "", "Ljava/util/stream/IntStream;", "", "Ljava/util/stream/LongStream;", ExifInterface.GPS_DIRECTION_TRUE, "Ljava/util/stream/Stream;", "asStream", "toList", "", "kotlin-stdlib-jdk8"}, k = 2, mv = {1, 1, 16}, pn = "kotlin.streams")
@JvmName(name = "StreamsKt")
/* loaded from: classes4.dex */
public final class StreamsKt {
    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <T> Sequence<T> asSequence(@NotNull final Stream<T> asSequence) {
        Intrinsics.checkParameterIsNotNull(asSequence, "$this$asSequence");
        return new Sequence<T>() { // from class: kotlin.streams.jdk8.StreamsKt$asSequence$$inlined$Sequence$1
            @Override // kotlin.sequences.Sequence
            @NotNull
            public Iterator<T> iterator() {
                return asSequence.iterator();
            }
        };
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <T> Stream<T> asStream(@NotNull final Sequence<? extends T> asStream) {
        Intrinsics.checkParameterIsNotNull(asStream, "$this$asStream");
        Stream<T> stream = StreamSupport.stream(new Supplier<Spliterator<T>>() { // from class: kotlin.streams.jdk8.StreamsKt$asStream$1
            @Override // java.util.function.Supplier
            public final Spliterator<T> get() {
                return Spliterators.spliteratorUnknownSize(Sequence.this.iterator(), 16);
            }
        }, 16, false);
        Intrinsics.checkExpressionValueIsNotNull(stream, "StreamSupport.stream({ S…literator.ORDERED, false)");
        return stream;
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <T> List<T> toList(@NotNull Stream<T> toList) {
        Intrinsics.checkParameterIsNotNull(toList, "$this$toList");
        Object collect = toList.collect(Collectors.toList());
        Intrinsics.checkExpressionValueIsNotNull(collect, "collect(Collectors.toList<T>())");
        return (List) collect;
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final Sequence<Integer> asSequence(@NotNull final IntStream asSequence) {
        Intrinsics.checkParameterIsNotNull(asSequence, "$this$asSequence");
        return new Sequence<Integer>() { // from class: kotlin.streams.jdk8.StreamsKt$asSequence$$inlined$Sequence$2
            @Override // kotlin.sequences.Sequence
            @NotNull
            public Iterator<Integer> iterator() {
                return asSequence.iterator();
            }
        };
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final List<Integer> toList(@NotNull IntStream toList) {
        List<Integer> asList;
        Intrinsics.checkParameterIsNotNull(toList, "$this$toList");
        int[] array = toList.toArray();
        Intrinsics.checkExpressionValueIsNotNull(array, "toArray()");
        asList = ArraysKt___ArraysJvmKt.asList(array);
        return asList;
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final Sequence<Long> asSequence(@NotNull final LongStream asSequence) {
        Intrinsics.checkParameterIsNotNull(asSequence, "$this$asSequence");
        return new Sequence<Long>() { // from class: kotlin.streams.jdk8.StreamsKt$asSequence$$inlined$Sequence$3
            @Override // kotlin.sequences.Sequence
            @NotNull
            public Iterator<Long> iterator() {
                return asSequence.iterator();
            }
        };
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final List<Long> toList(@NotNull LongStream toList) {
        List<Long> asList;
        Intrinsics.checkParameterIsNotNull(toList, "$this$toList");
        long[] array = toList.toArray();
        Intrinsics.checkExpressionValueIsNotNull(array, "toArray()");
        asList = ArraysKt___ArraysJvmKt.asList(array);
        return asList;
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final Sequence<Double> asSequence(@NotNull final DoubleStream asSequence) {
        Intrinsics.checkParameterIsNotNull(asSequence, "$this$asSequence");
        return new Sequence<Double>() { // from class: kotlin.streams.jdk8.StreamsKt$asSequence$$inlined$Sequence$4
            @Override // kotlin.sequences.Sequence
            @NotNull
            public Iterator<Double> iterator() {
                return asSequence.iterator();
            }
        };
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final List<Double> toList(@NotNull DoubleStream toList) {
        List<Double> asList;
        Intrinsics.checkParameterIsNotNull(toList, "$this$toList");
        double[] array = toList.toArray();
        Intrinsics.checkExpressionValueIsNotNull(array, "toArray()");
        asList = ArraysKt___ArraysJvmKt.asList(array);
        return asList;
    }
}
