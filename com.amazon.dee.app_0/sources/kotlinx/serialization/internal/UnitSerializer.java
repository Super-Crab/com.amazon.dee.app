package kotlinx.serialization.internal;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.Decoder;
import kotlinx.serialization.Encoder;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialDescriptor;
import org.jetbrains.annotations.NotNull;
/* compiled from: Primitives.kt */
@Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of top-level UnitSerializer() function", replaceWith = @ReplaceWith(expression = "UnitSerializer()", imports = {"kotlinx.serialization.builtins.UnitSerializer"}))
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0011\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0096\u0001J\u001e\u0010\u000b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0002H\u0096\u0001¢\u0006\u0002\u0010\rJ\u001e\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0002H\u0096\u0001¢\u0006\u0002\u0010\u0012R\u0012\u0010\u0004\u001a\u00020\u0005X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, d2 = {"Lkotlinx/serialization/internal/UnitSerializer;", "Lkotlinx/serialization/KSerializer;", "", "()V", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/SerialDescriptor;", "deserialize", "decoder", "Lkotlinx/serialization/Decoder;", "patch", "old", "(Lkotlinx/serialization/Decoder;Lkotlin/Unit;)V", "serialize", "encoder", "Lkotlinx/serialization/Encoder;", "value", "(Lkotlinx/serialization/Encoder;Lkotlin/Unit;)V", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class UnitSerializer implements KSerializer<Unit> {
    public static final UnitSerializer INSTANCE = new UnitSerializer();
    private final /* synthetic */ ObjectSerializer $$delegate_0 = new ObjectSerializer("kotlin.Unit", Unit.INSTANCE);

    private UnitSerializer() {
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    /* renamed from: deserialize */
    public /* bridge */ /* synthetic */ Object mo12413deserialize(Decoder decoder) {
        mo12413deserialize(decoder);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    /* renamed from: deserialize  reason: collision with other method in class */
    public void mo12413deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        this.$$delegate_0.mo12413deserialize(decoder);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return this.$$delegate_0.getDescriptor();
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.DeserializationStrategy
    public /* bridge */ /* synthetic */ Object patch(Decoder decoder, Object obj) {
        patch(decoder, (Unit) obj);
        return Unit.INSTANCE;
    }

    public void patch(@NotNull Decoder decoder, @NotNull Unit old) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        Intrinsics.checkParameterIsNotNull(old, "old");
        this.$$delegate_0.patch(decoder, old);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, @NotNull Unit value) {
        Intrinsics.checkParameterIsNotNull(encoder, "encoder");
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.$$delegate_0.serialize(encoder, value);
    }
}
