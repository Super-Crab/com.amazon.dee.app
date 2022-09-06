package kotlinx.serialization.internal;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.DeserializationStrategy;
/* compiled from: Tagged.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0004\n\u0002\b\u0005\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", ExifInterface.GPS_DIRECTION_TRUE, "Tag", "invoke", "()Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class TaggedDecoder$decodeSerializableElement$1 extends Lambda implements Function0<T> {
    final /* synthetic */ DeserializationStrategy $deserializer;
    final /* synthetic */ TaggedDecoder this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TaggedDecoder$decodeSerializableElement$1(TaggedDecoder taggedDecoder, DeserializationStrategy deserializationStrategy) {
        super(0);
        this.this$0 = taggedDecoder;
        this.$deserializer = deserializationStrategy;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [T, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public final T mo12560invoke() {
        return this.this$0.decodeSerializableValue(this.$deserializer);
    }
}
