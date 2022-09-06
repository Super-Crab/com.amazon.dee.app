package kotlinx.serialization.internal;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.CompositeDecoder;
import kotlinx.serialization.CompositeEncoder;
import kotlinx.serialization.Decoder;
import kotlinx.serialization.Encoder;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import org.jetbrains.annotations.NotNull;
/* compiled from: Tuples.kt */
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This class is used only by the plugin in generated code and should not be used directly. Use corresponding factory functions instead")
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u00032\b\u0012\u0004\u0012\u0002H\u00030\u0004B#\b\u0002\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004¢\u0006\u0002\u0010\u0007J\u0015\u0010\u0010\u001a\u00028\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0016¢\u0006\u0002\u0010\u0013J\u001d\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000e\u001a\u00028\u0002H\u0016¢\u0006\u0002\u0010\u0018J\u001d\u0010\u0019\u001a\u00028\u00022\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\u000e\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u001aR\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0016\u0010\u000b\u001a\u00028\u0000*\u00028\u0002X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0016\u0010\u000e\u001a\u00028\u0001*\u00028\u0002X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\r\u0082\u0001\u0002\u001b\u001c¨\u0006\u001d"}, d2 = {"Lkotlinx/serialization/internal/KeyValueSerializer;", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "R", "Lkotlinx/serialization/KSerializer;", "keySerializer", "valueSerializer", "(Lkotlinx/serialization/KSerializer;Lkotlinx/serialization/KSerializer;)V", "getKeySerializer", "()Lkotlinx/serialization/KSerializer;", "getValueSerializer", "key", "getKey", "(Ljava/lang/Object;)Ljava/lang/Object;", "value", "getValue", "deserialize", "decoder", "Lkotlinx/serialization/Decoder;", "(Lkotlinx/serialization/Decoder;)Ljava/lang/Object;", "serialize", "", "encoder", "Lkotlinx/serialization/Encoder;", "(Lkotlinx/serialization/Encoder;Ljava/lang/Object;)V", "toResult", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlinx/serialization/internal/MapEntrySerializer;", "Lkotlinx/serialization/internal/PairSerializer;", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
@InternalSerializationApi
/* loaded from: classes4.dex */
public abstract class KeyValueSerializer<K, V, R> implements KSerializer<R> {
    @NotNull
    private final KSerializer<K> keySerializer;
    @NotNull
    private final KSerializer<V> valueSerializer;

    private KeyValueSerializer(KSerializer<K> kSerializer, KSerializer<V> kSerializer2) {
        this.keySerializer = kSerializer;
        this.valueSerializer = kSerializer2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.DeserializationStrategy
    /* renamed from: deserialize */
    public R mo12413deserialize(@NotNull Decoder decoder) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        CompositeDecoder beginStructure = decoder.beginStructure(getDescriptor(), this.keySerializer, this.valueSerializer);
        if (!beginStructure.decodeSequentially()) {
            obj = TuplesKt.NULL;
            obj2 = TuplesKt.NULL;
            while (true) {
                int decodeElementIndex = beginStructure.decodeElementIndex(getDescriptor());
                if (decodeElementIndex == -1) {
                    beginStructure.endStructure(getDescriptor());
                    obj3 = TuplesKt.NULL;
                    if (obj != obj3) {
                        obj4 = TuplesKt.NULL;
                        if (obj2 != obj4) {
                            return (R) mo12394toResult(obj, obj2);
                        }
                        throw new SerializationException("Element 'value' is missing", null, 2, null);
                    }
                    throw new SerializationException("Element 'key' is missing", null, 2, null);
                } else if (decodeElementIndex == 0) {
                    obj = beginStructure.decodeSerializableElement(getDescriptor(), 0, this.keySerializer);
                } else if (decodeElementIndex == 1) {
                    obj2 = beginStructure.decodeSerializableElement(getDescriptor(), 1, this.valueSerializer);
                } else {
                    throw new SerializationException(GeneratedOutlineSupport1.outline49("Invalid index: ", decodeElementIndex), null, 2, null);
                }
            }
        } else {
            return (R) mo12394toResult(beginStructure.decodeSerializableElement(getDescriptor(), 0, this.keySerializer), beginStructure.decodeSerializableElement(getDescriptor(), 1, this.valueSerializer));
        }
    }

    public abstract K getKey(R r);

    @NotNull
    protected final KSerializer<K> getKeySerializer() {
        return this.keySerializer;
    }

    public abstract V getValue(R r);

    @NotNull
    protected final KSerializer<V> getValueSerializer() {
        return this.valueSerializer;
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.DeserializationStrategy
    public R patch(@NotNull Decoder decoder, R r) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        return (R) KSerializer.DefaultImpls.patch(this, decoder, r);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, R r) {
        Intrinsics.checkParameterIsNotNull(encoder, "encoder");
        CompositeEncoder beginStructure = encoder.beginStructure(getDescriptor(), this.keySerializer, this.valueSerializer);
        beginStructure.encodeSerializableElement(getDescriptor(), 0, this.keySerializer, getKey(r));
        beginStructure.encodeSerializableElement(getDescriptor(), 1, this.valueSerializer, getValue(r));
        beginStructure.endStructure(getDescriptor());
    }

    /* renamed from: toResult */
    public abstract R mo12394toResult(K k, V v);

    public /* synthetic */ KeyValueSerializer(KSerializer kSerializer, KSerializer kSerializer2, DefaultConstructorMarker defaultConstructorMarker) {
        this(kSerializer, kSerializer2);
    }
}
