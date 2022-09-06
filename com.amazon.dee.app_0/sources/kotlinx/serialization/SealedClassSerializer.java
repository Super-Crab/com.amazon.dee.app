package kotlinx.serialization;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.Grouping;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.PolymorphicKind;
import kotlinx.serialization.internal.AbstractPolymorphicSerializer;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
/* compiled from: SealedSerializer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003BG\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0014\u0010\b\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00070\t\u0012\u0014\u0010\n\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u000b0\t¢\u0006\u0002\u0010\fJ \u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u000b2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0005H\u0016J%\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u000b2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001dR\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR*\u0010\u000f\u001a\u001e\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0007\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u000b0\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u0012X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\"\u0010\u0015\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u000b0\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lkotlinx/serialization/SealedClassSerializer;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/serialization/internal/AbstractPolymorphicSerializer;", "serialName", "", "baseClass", "Lkotlin/reflect/KClass;", "subclasses", "", "subclassSerializers", "Lkotlinx/serialization/KSerializer;", "(Ljava/lang/String;Lkotlin/reflect/KClass;[Lkotlin/reflect/KClass;[Lkotlinx/serialization/KSerializer;)V", "getBaseClass", "()Lkotlin/reflect/KClass;", "class2Serializer", "", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/SerialDescriptor;", "serialName2Serializer", "findPolymorphicSerializer", "decoder", "Lkotlinx/serialization/CompositeDecoder;", "klassName", "encoder", "Lkotlinx/serialization/Encoder;", "value", "(Lkotlinx/serialization/Encoder;Ljava/lang/Object;)Lkotlinx/serialization/KSerializer;", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
@InternalSerializationApi
/* loaded from: classes4.dex */
public final class SealedClassSerializer<T> extends AbstractPolymorphicSerializer<T> {
    @NotNull
    private final KClass<T> baseClass;
    private final Map<KClass<? extends T>, KSerializer<? extends T>> class2Serializer;
    @NotNull
    private final SerialDescriptor descriptor;
    private final Map<String, KSerializer<? extends T>> serialName2Serializer;

    public SealedClassSerializer(@NotNull String serialName, @NotNull KClass<T> baseClass, @NotNull KClass<? extends T>[] subclasses, @NotNull KSerializer<? extends T>[] subclassSerializers) {
        List zip;
        Map<KClass<? extends T>, KSerializer<? extends T>> map;
        int mapCapacity;
        Intrinsics.checkParameterIsNotNull(serialName, "serialName");
        Intrinsics.checkParameterIsNotNull(baseClass, "baseClass");
        Intrinsics.checkParameterIsNotNull(subclasses, "subclasses");
        Intrinsics.checkParameterIsNotNull(subclassSerializers, "subclassSerializers");
        this.baseClass = baseClass;
        this.descriptor = SerialDescriptorBuilderKt.SerialDescriptor(serialName, PolymorphicKind.SEALED.INSTANCE, new SealedClassSerializer$descriptor$1(this, subclassSerializers));
        if (subclasses.length == subclassSerializers.length) {
            zip = ArraysKt___ArraysKt.zip(subclasses, subclassSerializers);
            map = MapsKt__MapsKt.toMap(zip);
            this.class2Serializer = map;
            final Set<Map.Entry<KClass<? extends T>, KSerializer<? extends T>>> entrySet = this.class2Serializer.entrySet();
            Grouping<Map.Entry<? extends KClass<? extends T>, ? extends KSerializer<? extends T>>, String> grouping = new Grouping<Map.Entry<? extends KClass<? extends T>, ? extends KSerializer<? extends T>>, String>() { // from class: kotlinx.serialization.SealedClassSerializer$$special$$inlined$groupingBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // kotlin.collections.Grouping
                public String keyOf(Map.Entry<? extends KClass<? extends T>, ? extends KSerializer<? extends T>> entry) {
                    return ((KSerializer) entry.getValue()).getDescriptor().getSerialName();
                }

                @Override // kotlin.collections.Grouping
                @NotNull
                public Iterator<Map.Entry<? extends KClass<? extends T>, ? extends KSerializer<? extends T>>> sourceIterator() {
                    return entrySet.iterator();
                }
            };
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            Iterator<Map.Entry<? extends KClass<? extends T>, ? extends KSerializer<? extends T>>> sourceIterator = grouping.sourceIterator();
            while (sourceIterator.hasNext()) {
                Map.Entry<? extends KClass<? extends T>, ? extends KSerializer<? extends T>> next = sourceIterator.next();
                String keyOf = grouping.keyOf(next);
                Object obj = linkedHashMap.get(keyOf);
                if (obj == null) {
                    linkedHashMap.containsKey(keyOf);
                }
                Map.Entry<? extends KClass<? extends T>, ? extends KSerializer<? extends T>> entry = next;
                Map.Entry entry2 = (Map.Entry) obj;
                String str = keyOf;
                if (entry2 == null) {
                    linkedHashMap.put(keyOf, entry);
                } else {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Multiple sealed subclasses of '");
                    outline107.append(getBaseClass());
                    outline107.append("' have the same serial name '");
                    outline107.append(str);
                    outline107.append("':");
                    outline107.append(" '");
                    outline107.append((KClass) entry2.getKey());
                    outline107.append("', '");
                    outline107.append(entry.getKey());
                    outline107.append(Chars.QUOTE);
                    throw new IllegalStateException(outline107.toString().toString());
                }
            }
            mapCapacity = MapsKt__MapsJVMKt.mapCapacity(linkedHashMap.size());
            LinkedHashMap linkedHashMap2 = new LinkedHashMap(mapCapacity);
            for (Map.Entry entry3 : linkedHashMap.entrySet()) {
                linkedHashMap2.put(entry3.getKey(), (KSerializer) ((Map.Entry) entry3.getValue()).getValue());
            }
            this.serialName2Serializer = linkedHashMap2;
            return;
        }
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("Arrays of classes and serializers must have the same length,", " got arrays: ");
        String arrays = Arrays.toString(subclasses);
        Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
        outline113.append(arrays);
        outline113.append(", ");
        String arrays2 = Arrays.toString(subclassSerializers);
        Intrinsics.checkExpressionValueIsNotNull(arrays2, "java.util.Arrays.toString(this)");
        outline113.append(arrays2);
        outline113.append('\n');
        outline113.append("Please ensure that @Serializable annotation is present on each sealed subclass");
        throw new IllegalArgumentException(outline113.toString().toString());
    }

    @Override // kotlinx.serialization.internal.AbstractPolymorphicSerializer
    @NotNull
    public KSerializer<? extends T> findPolymorphicSerializer(@NotNull CompositeDecoder decoder, @NotNull String klassName) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        Intrinsics.checkParameterIsNotNull(klassName, "klassName");
        KSerializer<? extends T> kSerializer = this.serialName2Serializer.get(klassName);
        return kSerializer != null ? kSerializer : super.findPolymorphicSerializer(decoder, klassName);
    }

    @Override // kotlinx.serialization.internal.AbstractPolymorphicSerializer
    @NotNull
    public KClass<T> getBaseClass() {
        return this.baseClass;
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // kotlinx.serialization.internal.AbstractPolymorphicSerializer
    @NotNull
    public KSerializer<? extends T> findPolymorphicSerializer(@NotNull Encoder encoder, @NotNull T value) {
        Intrinsics.checkParameterIsNotNull(encoder, "encoder");
        Intrinsics.checkParameterIsNotNull(value, "value");
        KSerializer<? extends T> kSerializer = this.class2Serializer.get(Reflection.getOrCreateKotlinClass(value.getClass()));
        return kSerializer != null ? kSerializer : super.findPolymorphicSerializer(encoder, (Encoder) value);
    }
}
