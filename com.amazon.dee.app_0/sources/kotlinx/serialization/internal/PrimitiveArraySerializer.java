package kotlinx.serialization.internal;

import com.amazon.deecomms.common.metrics.MetricKeys;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.CompositeDecoder;
import kotlinx.serialization.CompositeEncoder;
import kotlinx.serialization.Decoder;
import kotlinx.serialization.Encoder;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.internal.PrimitiveArrayBuilder;
import org.jetbrains.annotations.NotNull;
/* compiled from: CollectionSerializers.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010(\n\u0002\b\u0007\b'\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u000e\b\u0002\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005B\u0015\b\u0000\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0002\u0010\bJ\r\u0010\r\u001a\u00028\u0002H\u0004¢\u0006\u0002\u0010\u000eJ\u0013\u0010\u000f\u001a\u00028\u00012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012J\r\u0010\u0013\u001a\u00028\u0001H$¢\u0006\u0002\u0010\u0014J-\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\r\u001a\u00028\u00022\u0006\u0010\u001a\u001a\u00020\u001bH$¢\u0006\u0002\u0010\u001cJ\u001b\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00028\u0001¢\u0006\u0002\u0010!J%\u0010\"\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020#2\u0006\u0010$\u001a\u00028\u00012\u0006\u0010%\u001a\u00020\u0019H$¢\u0006\u0002\u0010&J\u0011\u0010'\u001a\u00020\u0019*\u00028\u0002H\u0004¢\u0006\u0002\u0010(J\u0019\u0010)\u001a\u00020\u0016*\u00028\u00022\u0006\u0010%\u001a\u00020\u0019H\u0004¢\u0006\u0002\u0010*J\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00028\u00000,*\u00028\u0001H\u0004¢\u0006\u0002\u0010-J\u001f\u0010.\u001a\u00020\u0016*\u00028\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010/\u001a\u00028\u0000¢\u0006\u0002\u00100J\u0011\u00101\u001a\u00028\u0001*\u00028\u0002H\u0004¢\u0006\u0002\u00102R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u00063"}, d2 = {"Lkotlinx/serialization/internal/PrimitiveArraySerializer;", "Element", "Array", "Builder", "Lkotlinx/serialization/internal/PrimitiveArrayBuilder;", "Lkotlinx/serialization/internal/ListLikeSerializer;", "primitiveSerializer", "Lkotlinx/serialization/KSerializer;", "(Lkotlinx/serialization/KSerializer;)V", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/SerialDescriptor;", "builder", "()Lkotlinx/serialization/internal/PrimitiveArrayBuilder;", "deserialize", "decoder", "Lkotlinx/serialization/Decoder;", "(Lkotlinx/serialization/Decoder;)Ljava/lang/Object;", MetricKeys.EMPTY_VALUE, "()Ljava/lang/Object;", "readElement", "", "Lkotlinx/serialization/CompositeDecoder;", "index", "", "checkIndex", "", "(Lkotlinx/serialization/CompositeDecoder;ILkotlinx/serialization/internal/PrimitiveArrayBuilder;Z)V", "serialize", "encoder", "Lkotlinx/serialization/Encoder;", "value", "(Lkotlinx/serialization/Encoder;Ljava/lang/Object;)V", "writeContent", "Lkotlinx/serialization/CompositeEncoder;", "content", "size", "(Lkotlinx/serialization/CompositeEncoder;Ljava/lang/Object;I)V", "builderSize", "(Lkotlinx/serialization/internal/PrimitiveArrayBuilder;)I", "checkCapacity", "(Lkotlinx/serialization/internal/PrimitiveArrayBuilder;I)V", "collectionIterator", "", "(Ljava/lang/Object;)Ljava/util/Iterator;", "insert", "element", "(Lkotlinx/serialization/internal/PrimitiveArrayBuilder;ILjava/lang/Object;)V", "toResult", "(Lkotlinx/serialization/internal/PrimitiveArrayBuilder;)Ljava/lang/Object;", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
@InternalSerializationApi
@Deprecated(level = DeprecationLevel.HIDDEN, message = "For internal use")
/* loaded from: classes4.dex */
public abstract class PrimitiveArraySerializer<Element, Array, Builder extends PrimitiveArrayBuilder<Array>> extends ListLikeSerializer<Element, Array, Builder> {
    @NotNull
    private final SerialDescriptor descriptor;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PrimitiveArraySerializer(@NotNull KSerializer<Element> primitiveSerializer) {
        super(primitiveSerializer, null);
        Intrinsics.checkParameterIsNotNull(primitiveSerializer, "primitiveSerializer");
        this.descriptor = new PrimitiveArrayDescriptor(primitiveSerializer.getDescriptor());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public /* bridge */ /* synthetic */ int builderSize(Object obj) {
        return builderSize((PrimitiveArraySerializer<Element, Array, Builder>) ((PrimitiveArrayBuilder) obj));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public /* bridge */ /* synthetic */ void checkCapacity(Object obj, int i) {
        checkCapacity((PrimitiveArraySerializer<Element, Array, Builder>) ((PrimitiveArrayBuilder) obj), i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    @NotNull
    public final Iterator<Element> collectionIterator(Array array) {
        throw new IllegalStateException("This method lead to boxing and must not be used, use writeContents instead".toString());
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer, kotlinx.serialization.DeserializationStrategy
    /* renamed from: deserialize */
    public final Array mo12413deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        return patch(decoder, empty());
    }

    protected abstract Array empty();

    @Override // kotlinx.serialization.internal.ListLikeSerializer, kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.ListLikeSerializer
    public /* bridge */ /* synthetic */ void insert(Object obj, int i, Object obj2) {
        insert((PrimitiveArraySerializer<Element, Array, Builder>) ((PrimitiveArrayBuilder) obj), i, (int) obj2);
    }

    protected abstract void readElement(@NotNull CompositeDecoder compositeDecoder, int i, @NotNull Builder builder, boolean z);

    @Override // kotlinx.serialization.internal.ListLikeSerializer, kotlinx.serialization.internal.AbstractCollectionSerializer, kotlinx.serialization.SerializationStrategy
    public final void serialize(@NotNull Encoder encoder, Array array) {
        Intrinsics.checkParameterIsNotNull(encoder, "encoder");
        int collectionSize = collectionSize(array);
        SerialDescriptor serialDescriptor = this.descriptor;
        KSerializer<?>[] typeParams = getTypeParams();
        CompositeEncoder beginCollection = encoder.beginCollection(serialDescriptor, collectionSize, (KSerializer[]) Arrays.copyOf(typeParams, typeParams.length));
        writeContent(beginCollection, array, collectionSize);
        beginCollection.endStructure(this.descriptor);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public /* bridge */ /* synthetic */ Object toResult(Object obj) {
        return toResult((PrimitiveArraySerializer<Element, Array, Builder>) ((PrimitiveArrayBuilder) obj));
    }

    protected abstract void writeContent(@NotNull CompositeEncoder compositeEncoder, Array array, int i);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    @NotNull
    /* renamed from: builder  reason: collision with other method in class */
    public final Builder mo12398builder() {
        throw new IllegalStateException("Use empty().toBuilder() instead".toString());
    }

    protected final int builderSize(@NotNull Builder builderSize) {
        Intrinsics.checkParameterIsNotNull(builderSize, "$this$builderSize");
        return builderSize.getPosition();
    }

    protected final void checkCapacity(@NotNull Builder checkCapacity, int i) {
        Intrinsics.checkParameterIsNotNull(checkCapacity, "$this$checkCapacity");
        checkCapacity.ensureCapacity(i);
    }

    public final void insert(@NotNull Builder insert, int i, Element element) {
        Intrinsics.checkParameterIsNotNull(insert, "$this$insert");
        throw new IllegalStateException("This method lead to boxing and must not be used, use Builder.append instead".toString());
    }

    protected final Array toResult(@NotNull Builder toResult) {
        Intrinsics.checkParameterIsNotNull(toResult, "$this$toResult");
        return (Array) toResult.build();
    }
}
