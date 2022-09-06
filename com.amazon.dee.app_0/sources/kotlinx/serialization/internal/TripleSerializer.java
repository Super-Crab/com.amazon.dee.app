package kotlinx.serialization.internal;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Triple;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.CompositeDecoder;
import kotlinx.serialization.CompositeEncoder;
import kotlinx.serialization.Decoder;
import kotlinx.serialization.Encoder;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.SerialDescriptorBuilderKt;
import kotlinx.serialization.SerializationException;
import org.jetbrains.annotations.NotNull;
/* compiled from: Tuples.kt */
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This class is used only by the plugin in generated code and should not be used directly. Use corresponding factory functions instead", replaceWith = @ReplaceWith(expression = "TripleSerializer(aSerializer, bSerializer, cSerializer)", imports = {"kotlinx.serialization.builtins.TripleSerializer"}))
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u00032\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00050\u0004B/\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00020\u0004¢\u0006\u0002\u0010\tJ\"\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\"\u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\"\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J*\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0018\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0005H\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u001a"}, d2 = {"Lkotlinx/serialization/internal/TripleSerializer;", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "B", "C", "Lkotlinx/serialization/KSerializer;", "Lkotlin/Triple;", "aSerializer", "bSerializer", "cSerializer", "(Lkotlinx/serialization/KSerializer;Lkotlinx/serialization/KSerializer;Lkotlinx/serialization/KSerializer;)V", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/SerialDescriptor;", "decodeSequentially", "composite", "Lkotlinx/serialization/CompositeDecoder;", "decodeStructure", "deserialize", "decoder", "Lkotlinx/serialization/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/Encoder;", "value", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
@InternalSerializationApi
/* loaded from: classes4.dex */
public final class TripleSerializer<A, B, C> implements KSerializer<Triple<? extends A, ? extends B, ? extends C>> {
    private final KSerializer<A> aSerializer;
    private final KSerializer<B> bSerializer;
    private final KSerializer<C> cSerializer;
    @NotNull
    private final SerialDescriptor descriptor;

    public TripleSerializer(@NotNull KSerializer<A> aSerializer, @NotNull KSerializer<B> bSerializer, @NotNull KSerializer<C> cSerializer) {
        Intrinsics.checkParameterIsNotNull(aSerializer, "aSerializer");
        Intrinsics.checkParameterIsNotNull(bSerializer, "bSerializer");
        Intrinsics.checkParameterIsNotNull(cSerializer, "cSerializer");
        this.aSerializer = aSerializer;
        this.bSerializer = bSerializer;
        this.cSerializer = cSerializer;
        this.descriptor = SerialDescriptorBuilderKt.SerialDescriptor$default("kotlin.Triple", null, new TripleSerializer$descriptor$1(this), 2, null);
    }

    private final Triple<A, B, C> decodeSequentially(CompositeDecoder compositeDecoder) {
        Object decodeSerializableElement = compositeDecoder.decodeSerializableElement(getDescriptor(), 0, this.aSerializer);
        Object decodeSerializableElement2 = compositeDecoder.decodeSerializableElement(getDescriptor(), 1, this.bSerializer);
        Object decodeSerializableElement3 = compositeDecoder.decodeSerializableElement(getDescriptor(), 2, this.cSerializer);
        compositeDecoder.endStructure(getDescriptor());
        return new Triple<>(decodeSerializableElement, decodeSerializableElement2, decodeSerializableElement3);
    }

    private final Triple<A, B, C> decodeStructure(CompositeDecoder compositeDecoder) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        obj = TuplesKt.NULL;
        obj2 = TuplesKt.NULL;
        obj3 = TuplesKt.NULL;
        while (true) {
            int decodeElementIndex = compositeDecoder.decodeElementIndex(getDescriptor());
            if (decodeElementIndex == -1) {
                compositeDecoder.endStructure(getDescriptor());
                obj4 = TuplesKt.NULL;
                if (obj != obj4) {
                    obj5 = TuplesKt.NULL;
                    if (obj2 != obj5) {
                        obj6 = TuplesKt.NULL;
                        if (obj3 != obj6) {
                            return new Triple<>(obj, obj2, obj3);
                        }
                        throw new SerializationException("Element 'third' is missing", null, 2, null);
                    }
                    throw new SerializationException("Element 'second' is missing", null, 2, null);
                }
                throw new SerializationException("Element 'first' is missing", null, 2, null);
            } else if (decodeElementIndex == 0) {
                obj = compositeDecoder.decodeSerializableElement(getDescriptor(), 0, this.aSerializer);
            } else if (decodeElementIndex == 1) {
                obj2 = compositeDecoder.decodeSerializableElement(getDescriptor(), 1, this.bSerializer);
            } else if (decodeElementIndex == 2) {
                obj3 = compositeDecoder.decodeSerializableElement(getDescriptor(), 2, this.cSerializer);
            } else {
                throw new SerializationException(GeneratedOutlineSupport1.outline49("Unexpected index ", decodeElementIndex), null, 2, null);
            }
        }
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.DeserializationStrategy
    public /* bridge */ /* synthetic */ Object patch(Decoder decoder, Object obj) {
        return patch(decoder, (Triple) ((Triple) obj));
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public /* bridge */ /* synthetic */ void serialize(Encoder encoder, Object obj) {
        serialize(encoder, (Triple) ((Triple) obj));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    /* renamed from: deserialize  reason: collision with other method in class */
    public Triple<A, B, C> mo12413deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        CompositeDecoder beginStructure = decoder.beginStructure(getDescriptor(), this.aSerializer, this.bSerializer, this.cSerializer);
        if (beginStructure.decodeSequentially()) {
            return decodeSequentially(beginStructure);
        }
        return decodeStructure(beginStructure);
    }

    @NotNull
    public Triple<A, B, C> patch(@NotNull Decoder decoder, @NotNull Triple<? extends A, ? extends B, ? extends C> old) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        Intrinsics.checkParameterIsNotNull(old, "old");
        return (Triple) KSerializer.DefaultImpls.patch(this, decoder, old);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void serialize(@NotNull Encoder encoder, @NotNull Triple<? extends A, ? extends B, ? extends C> value) {
        Intrinsics.checkParameterIsNotNull(encoder, "encoder");
        Intrinsics.checkParameterIsNotNull(value, "value");
        CompositeEncoder beginStructure = encoder.beginStructure(getDescriptor(), this.aSerializer, this.bSerializer, this.cSerializer);
        beginStructure.encodeSerializableElement(getDescriptor(), 0, this.aSerializer, value.getFirst());
        beginStructure.encodeSerializableElement(getDescriptor(), 1, this.bSerializer, value.getSecond());
        beginStructure.encodeSerializableElement(getDescriptor(), 2, this.cSerializer, value.getThird());
        beginStructure.endStructure(getDescriptor());
    }
}
