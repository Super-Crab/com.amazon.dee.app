package kotlinx.serialization.internal;

import com.amazon.identity.auth.map.device.token.MAPCookie;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.serialization.CompositeDecoder;
import kotlinx.serialization.CompositeEncoder;
import kotlinx.serialization.Encoder;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.PrimitiveKind;
import kotlinx.serialization.SerialDescriptor;
import org.jetbrains.annotations.NotNull;
/* compiled from: CollectionSerializers.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u0003*\u0014\b\u0003\u0010\u0004*\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00052 \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0007\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00040\u0006B#\b\u0002\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\t¢\u0006\u0002\u0010\u000bJ-\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00028\u00032\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cH\u0004¢\u0006\u0002\u0010\u001eJ-\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u001c2\u0006\u0010\u001a\u001a\u00028\u00032\u0006\u0010!\u001a\u00020\"H\u0004¢\u0006\u0002\u0010#J\u001d\u0010$\u001a\u00020\u00172\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00028\u0002H\u0016¢\u0006\u0002\u0010(J)\u0010)\u001a\u00020\u0017*\u00028\u00032\u0006\u0010 \u001a\u00020\u001c2\u0006\u0010*\u001a\u00028\u00002\u0006\u0010'\u001a\u00028\u0001H&¢\u0006\u0002\u0010+R\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010\u0010\u001a\u0012\u0012\u000e\u0012\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00120\t0\u0011¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000\u0082\u0001\u0002,-¨\u0006."}, d2 = {"Lkotlinx/serialization/internal/MapLikeSerializer;", "Key", MAPCookie.KEY_VALUE, "Collection", "Builder", "", "Lkotlinx/serialization/internal/AbstractCollectionSerializer;", "", "keySerializer", "Lkotlinx/serialization/KSerializer;", "valueSerializer", "(Lkotlinx/serialization/KSerializer;Lkotlinx/serialization/KSerializer;)V", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/SerialDescriptor;", "typeParams", "", "", "getTypeParams", "()[Lkotlinx/serialization/KSerializer;", "[Lkotlinx/serialization/KSerializer;", "readAll", "", "decoder", "Lkotlinx/serialization/CompositeDecoder;", "builder", "startIndex", "", "size", "(Lkotlinx/serialization/CompositeDecoder;Ljava/util/Map;II)V", "readElement", "index", "checkIndex", "", "(Lkotlinx/serialization/CompositeDecoder;ILjava/util/Map;Z)V", "serialize", "encoder", "Lkotlinx/serialization/Encoder;", "value", "(Lkotlinx/serialization/Encoder;Ljava/lang/Object;)V", "insertKeyValuePair", "key", "(Ljava/util/Map;ILjava/lang/Object;Ljava/lang/Object;)V", "Lkotlinx/serialization/internal/LinkedHashMapSerializer;", "Lkotlinx/serialization/internal/HashMapSerializer;", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
@InternalSerializationApi
/* loaded from: classes4.dex */
public abstract class MapLikeSerializer<Key, Value, Collection, Builder extends Map<Key, Value>> extends AbstractCollectionSerializer<Map.Entry<? extends Key, ? extends Value>, Collection, Builder> {
    @JvmField
    @NotNull
    public final KSerializer<Key> keySerializer;
    @NotNull
    private final KSerializer<? extends Object>[] typeParams;
    @JvmField
    @NotNull
    public final KSerializer<Value> valueSerializer;

    public /* synthetic */ MapLikeSerializer(KSerializer kSerializer, KSerializer kSerializer2, DefaultConstructorMarker defaultConstructorMarker) {
        this(kSerializer, kSerializer2);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public abstract SerialDescriptor getDescriptor();

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    @NotNull
    public final KSerializer<? extends Object>[] getTypeParams() {
        return this.typeParams;
    }

    public abstract void insertKeyValuePair(@NotNull Builder builder, int i, Key key, Value value);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public /* bridge */ /* synthetic */ void readAll(CompositeDecoder compositeDecoder, Object obj, int i, int i2) {
        readAll(compositeDecoder, (CompositeDecoder) ((Map) obj), i, i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public /* bridge */ /* synthetic */ void readElement(CompositeDecoder compositeDecoder, int i, Object obj, boolean z) {
        readElement(compositeDecoder, i, (int) ((Map) obj), z);
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer, kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, Collection collection) {
        Intrinsics.checkParameterIsNotNull(encoder, "encoder");
        int collectionSize = collectionSize(collection);
        SerialDescriptor descriptor = getDescriptor();
        KSerializer<? extends Object>[] kSerializerArr = this.typeParams;
        CompositeEncoder beginCollection = encoder.beginCollection(descriptor, collectionSize, (KSerializer[]) Arrays.copyOf(kSerializerArr, kSerializerArr.length));
        Iterator<Map.Entry<? extends Key, ? extends Value>> collectionIterator = collectionIterator(collection);
        int i = 0;
        while (collectionIterator.hasNext()) {
            Map.Entry<? extends Key, ? extends Value> next = collectionIterator.next();
            Key key = next.getKey();
            Value value = next.getValue();
            int i2 = i + 1;
            beginCollection.encodeSerializableElement(getDescriptor(), i, this.keySerializer, key);
            beginCollection.encodeSerializableElement(getDescriptor(), i2, this.valueSerializer, value);
            i = i2 + 1;
        }
        beginCollection.endStructure(getDescriptor());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private MapLikeSerializer(KSerializer<Key> kSerializer, KSerializer<Value> kSerializer2) {
        super(null);
        this.keySerializer = kSerializer;
        this.valueSerializer = kSerializer2;
        this.typeParams = new KSerializer[]{this.keySerializer, this.valueSerializer};
    }

    protected final void readAll(@NotNull CompositeDecoder decoder, @NotNull Builder builder, int i, int i2) {
        IntRange until;
        IntProgression step;
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        Intrinsics.checkParameterIsNotNull(builder, "builder");
        if (i2 >= 0) {
            until = RangesKt___RangesKt.until(0, i2 * 2);
            step = RangesKt___RangesKt.step(until, 2);
            int first = step.getFirst();
            int last = step.getLast();
            int step2 = step.getStep();
            if (step2 >= 0) {
                if (first > last) {
                    return;
                }
            } else if (first < last) {
                return;
            }
            while (true) {
                readElement(decoder, i + first, (int) builder, false);
                if (first == last) {
                    return;
                }
                first += step2;
            }
        } else {
            throw new IllegalArgumentException("Size must be known in advance when using READ_ALL".toString());
        }
    }

    protected final void readElement(@NotNull CompositeDecoder decoder, int i, @NotNull Builder builder, boolean z) {
        int i2;
        Object decodeSerializableElement;
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        Intrinsics.checkParameterIsNotNull(builder, "builder");
        Object decodeSerializableElement2 = decoder.decodeSerializableElement(getDescriptor(), i, this.keySerializer);
        boolean z2 = true;
        if (z) {
            i2 = decoder.decodeElementIndex(getDescriptor());
            if (i2 != i + 1) {
                z2 = false;
            }
            if (!z2) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline53("Value must follow key in a map, index for key: ", i, ", returned index for value: ", i2).toString());
            }
        } else {
            i2 = i + 1;
        }
        if (builder.containsKey(decodeSerializableElement2) && !(this.valueSerializer.getDescriptor().mo12397getKind() instanceof PrimitiveKind)) {
            decodeSerializableElement = decoder.updateSerializableElement(getDescriptor(), i2, this.valueSerializer, MapsKt.getValue(builder, decodeSerializableElement2));
        } else {
            decodeSerializableElement = decoder.decodeSerializableElement(getDescriptor(), i2, this.valueSerializer);
        }
        builder.put(decodeSerializableElement2, decodeSerializableElement);
    }
}
