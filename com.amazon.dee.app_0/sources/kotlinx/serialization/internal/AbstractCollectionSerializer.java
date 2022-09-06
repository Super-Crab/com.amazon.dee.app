package kotlinx.serialization.internal;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.CompositeDecoder;
import kotlinx.serialization.Decoder;
import kotlinx.serialization.Encoder;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
/* compiled from: CollectionSerializers.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010(\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u00032\b\u0012\u0004\u0012\u0002H\u00020\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0005J\r\u0010\n\u001a\u00028\u0002H$¢\u0006\u0002\u0010\u000bJ\u0015\u0010\f\u001a\u00028\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0016¢\u0006\u0002\u0010\u000fJ\u001b\u0010\u0010\u001a\u00028\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00028\u0001¢\u0006\u0002\u0010\u0012J-\u0010\u0013\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u00152\u0006\u0010\n\u001a\u00028\u00022\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H$¢\u0006\u0002\u0010\u0019J/\u0010\u001a\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u00172\u0006\u0010\n\u001a\u00028\u00022\b\b\u0002\u0010\u001c\u001a\u00020\u001dH$¢\u0006\u0002\u0010\u001eJ\u001d\u0010\u001f\u001a\u00020\u00172\u0006\u0010\r\u001a\u00020\u00152\u0006\u0010\n\u001a\u00028\u0002H\u0002¢\u0006\u0002\u0010 J\u001d\u0010!\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00028\u0001H&¢\u0006\u0002\u0010%J\u0011\u0010&\u001a\u00020\u0017*\u00028\u0002H$¢\u0006\u0002\u0010'J\u0019\u0010(\u001a\u00020\u0014*\u00028\u00022\u0006\u0010\u0018\u001a\u00020\u0017H$¢\u0006\u0002\u0010)J\u0017\u0010*\u001a\b\u0012\u0004\u0012\u00028\u00000+*\u00028\u0001H$¢\u0006\u0002\u0010,J\u0011\u0010-\u001a\u00020\u0017*\u00028\u0001H$¢\u0006\u0002\u0010'J\u0011\u0010.\u001a\u00028\u0002*\u00028\u0001H$¢\u0006\u0002\u0010/J\u0011\u00100\u001a\u00028\u0001*\u00028\u0002H$¢\u0006\u0002\u0010/R\u001c\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t\u0082\u0001\u000212¨\u00063"}, d2 = {"Lkotlinx/serialization/internal/AbstractCollectionSerializer;", "Element", "Collection", "Builder", "Lkotlinx/serialization/KSerializer;", "()V", "typeParams", "", "getTypeParams", "()[Lkotlinx/serialization/KSerializer;", "builder", "()Ljava/lang/Object;", "deserialize", "decoder", "Lkotlinx/serialization/Decoder;", "(Lkotlinx/serialization/Decoder;)Ljava/lang/Object;", "patch", "old", "(Lkotlinx/serialization/Decoder;Ljava/lang/Object;)Ljava/lang/Object;", "readAll", "", "Lkotlinx/serialization/CompositeDecoder;", "startIndex", "", "size", "(Lkotlinx/serialization/CompositeDecoder;Ljava/lang/Object;II)V", "readElement", "index", "checkIndex", "", "(Lkotlinx/serialization/CompositeDecoder;ILjava/lang/Object;Z)V", "readSize", "(Lkotlinx/serialization/CompositeDecoder;Ljava/lang/Object;)I", "serialize", "encoder", "Lkotlinx/serialization/Encoder;", "value", "(Lkotlinx/serialization/Encoder;Ljava/lang/Object;)V", "builderSize", "(Ljava/lang/Object;)I", "checkCapacity", "(Ljava/lang/Object;I)V", "collectionIterator", "", "(Ljava/lang/Object;)Ljava/util/Iterator;", "collectionSize", "toBuilder", "(Ljava/lang/Object;)Ljava/lang/Object;", "toResult", "Lkotlinx/serialization/internal/ListLikeSerializer;", "Lkotlinx/serialization/internal/MapLikeSerializer;", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
@InternalSerializationApi
/* loaded from: classes4.dex */
public abstract class AbstractCollectionSerializer<Element, Collection, Builder> implements KSerializer<Collection> {
    private AbstractCollectionSerializer() {
    }

    public static /* synthetic */ void readElement$default(AbstractCollectionSerializer abstractCollectionSerializer, CompositeDecoder compositeDecoder, int i, Object obj, boolean z, int i2, Object obj2) {
        if (obj2 == null) {
            if ((i2 & 8) != 0) {
                z = true;
            }
            abstractCollectionSerializer.readElement(compositeDecoder, i, obj, z);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readElement");
    }

    private final int readSize(CompositeDecoder compositeDecoder, Builder builder) {
        int decodeCollectionSize = compositeDecoder.decodeCollectionSize(getDescriptor());
        checkCapacity(builder, decodeCollectionSize);
        return decodeCollectionSize;
    }

    /* renamed from: builder */
    protected abstract Builder mo12398builder();

    protected abstract int builderSize(Builder builder);

    protected abstract void checkCapacity(Builder builder, int i);

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public abstract Iterator<Element> collectionIterator(Collection collection);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int collectionSize(Collection collection);

    @Override // kotlinx.serialization.DeserializationStrategy
    /* renamed from: deserialize */
    public Collection mo12413deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        return patch(decoder, toResult(mo12398builder()));
    }

    @NotNull
    public abstract KSerializer<?>[] getTypeParams();

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.DeserializationStrategy
    public final Collection patch(@NotNull Decoder decoder, Collection collection) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        Builder builder = toBuilder(collection);
        int builderSize = builderSize(builder);
        CompositeDecoder beginStructure = decoder.beginStructure(getDescriptor(), new KSerializer[0]);
        if (beginStructure.decodeSequentially()) {
            readAll(beginStructure, builder, builderSize, readSize(beginStructure, builder));
        } else {
            while (true) {
                int decodeElementIndex = beginStructure.decodeElementIndex(getDescriptor());
                if (decodeElementIndex == -1) {
                    break;
                }
                readElement$default(this, beginStructure, builderSize + decodeElementIndex, builder, false, 8, null);
            }
        }
        beginStructure.endStructure(getDescriptor());
        return toResult(builder);
    }

    protected abstract void readAll(@NotNull CompositeDecoder compositeDecoder, Builder builder, int i, int i2);

    protected abstract void readElement(@NotNull CompositeDecoder compositeDecoder, int i, Builder builder, boolean z);

    @Override // kotlinx.serialization.SerializationStrategy
    public abstract void serialize(@NotNull Encoder encoder, Collection collection);

    protected abstract Builder toBuilder(Collection collection);

    protected abstract Collection toResult(Builder builder);

    public /* synthetic */ AbstractCollectionSerializer(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
