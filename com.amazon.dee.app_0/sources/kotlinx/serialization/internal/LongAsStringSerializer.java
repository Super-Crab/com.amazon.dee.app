package kotlinx.serialization.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.Decoder;
import kotlinx.serialization.Encoder;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.PrimitiveKind;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.SerialDescriptorBuilderKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: LongAsStringSerializer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0015\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000bJ\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lkotlinx/serialization/internal/LongAsStringSerializer;", "Lkotlinx/serialization/KSerializer;", "", "()V", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/SerialDescriptor;", "deserialize", "decoder", "Lkotlinx/serialization/Decoder;", "(Lkotlinx/serialization/Decoder;)Ljava/lang/Long;", "serialize", "", "encoder", "Lkotlinx/serialization/Encoder;", "value", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LongAsStringSerializer implements KSerializer<Long> {
    public static final LongAsStringSerializer INSTANCE = new LongAsStringSerializer();
    @NotNull
    private static final SerialDescriptor descriptor = SerialDescriptorBuilderKt.PrimitiveDescriptor("kotlinx.serialization.LongAsStringSerializer", PrimitiveKind.STRING.INSTANCE);

    private LongAsStringSerializer() {
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @NotNull
    public Long patch(@NotNull Decoder decoder, long j) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        return (Long) KSerializer.DefaultImpls.patch(this, decoder, Long.valueOf(j));
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public /* bridge */ /* synthetic */ void serialize(Encoder encoder, Object obj) {
        serialize(encoder, ((Number) obj).longValue());
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    /* renamed from: deserialize */
    public Long mo12413deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        return Long.valueOf(Long.parseLong(decoder.decodeString()));
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.DeserializationStrategy
    public /* bridge */ /* synthetic */ Object patch(Decoder decoder, Object obj) {
        return patch(decoder, ((Number) obj).longValue());
    }

    public void serialize(@NotNull Encoder encoder, long j) {
        Intrinsics.checkParameterIsNotNull(encoder, "encoder");
        encoder.encodeString(String.valueOf(j));
    }
}
