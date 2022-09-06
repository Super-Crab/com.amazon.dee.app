package kotlinx.serialization.internal;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.DeserializationStrategy;
import org.jetbrains.annotations.Nullable;
/* compiled from: Tagged.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002\"\u0004\b\u0001\u0010\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", ExifInterface.GPS_DIRECTION_TRUE, "", "Tag", "invoke", "()Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class TaggedDecoder$updateNullableSerializableElement$1 extends Lambda implements Function0<T> {
    final /* synthetic */ DeserializationStrategy $deserializer;
    final /* synthetic */ Object $old;
    final /* synthetic */ TaggedDecoder this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TaggedDecoder$updateNullableSerializableElement$1(TaggedDecoder taggedDecoder, DeserializationStrategy deserializationStrategy, Object obj) {
        super(0);
        this.this$0 = taggedDecoder;
        this.$deserializer = deserializationStrategy;
        this.$old = obj;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [T, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function0
    @Nullable
    /* renamed from: invoke */
    public final T mo12560invoke() {
        return this.this$0.updateNullableSerializableValue(this.$deserializer, this.$old);
    }
}
