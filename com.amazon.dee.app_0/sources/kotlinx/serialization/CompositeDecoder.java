package kotlinx.serialization;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.modules.SerialModule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Decoding.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\bf\u0018\u0000 /2\u00020\u0001:\u0001/J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0010\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0010\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\rH&J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0018\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&J9\u0010\u001d\u001a\u0004\u0018\u0001H\u001e\"\b\b\u0000\u0010\u001e*\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u000e\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u001e0 H&¢\u0006\u0002\u0010!J\b\u0010\"\u001a\u00020\u000bH\u0016J1\u0010#\u001a\u0002H\u001e\"\u0004\b\u0000\u0010\u001e2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u001e0 H&¢\u0006\u0002\u0010!J\u0018\u0010$\u001a\u00020%2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0018\u0010&\u001a\u00020'2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0018\u0010(\u001a\u00020)2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0010\u0010*\u001a\u00020)2\u0006\u0010\f\u001a\u00020\rH&JC\u0010+\u001a\u0004\u0018\u0001H\u001e\"\b\b\u0000\u0010\u001e*\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u000e\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u001e0 2\b\u0010,\u001a\u0004\u0018\u0001H\u001eH&¢\u0006\u0002\u0010-J9\u0010.\u001a\u0002H\u001e\"\u0004\b\u0000\u0010\u001e2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u001e0 2\u0006\u0010,\u001a\u0002H\u001eH&¢\u0006\u0002\u0010-R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u00060"}, d2 = {"Lkotlinx/serialization/CompositeDecoder;", "", "context", "Lkotlinx/serialization/modules/SerialModule;", "getContext", "()Lkotlinx/serialization/modules/SerialModule;", "updateMode", "Lkotlinx/serialization/UpdateMode;", "getUpdateMode", "()Lkotlinx/serialization/UpdateMode;", "decodeBooleanElement", "", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "index", "", "decodeByteElement", "", "decodeCharElement", "", "decodeCollectionSize", "decodeDoubleElement", "", "decodeElementIndex", "decodeFloatElement", "", "decodeIntElement", "decodeLongElement", "", "decodeNullableSerializableElement", ExifInterface.GPS_DIRECTION_TRUE, "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "(Lkotlinx/serialization/SerialDescriptor;ILkotlinx/serialization/DeserializationStrategy;)Ljava/lang/Object;", "decodeSequentially", "decodeSerializableElement", "decodeShortElement", "", "decodeStringElement", "", "decodeUnitElement", "", "endStructure", "updateNullableSerializableElement", "old", "(Lkotlinx/serialization/SerialDescriptor;ILkotlinx/serialization/DeserializationStrategy;Ljava/lang/Object;)Ljava/lang/Object;", "updateSerializableElement", "Companion", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface CompositeDecoder {
    public static final Companion Companion = Companion.$$INSTANCE;
    @Deprecated(level = DeprecationLevel.WARNING, message = "READ_ALL cannot be longer returned by 'decodeElementIndex', use 'decodeSequentially' instead")
    public static final int READ_ALL = -2;
    public static final int READ_DONE = -1;
    public static final int UNKNOWN_NAME = -3;

    /* compiled from: Decoding.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lkotlinx/serialization/CompositeDecoder$Companion;", "", "()V", "READ_ALL", "", "READ_ALL$annotations", "READ_DONE", "UNKNOWN_NAME", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final int READ_ALL = -2;
        public static final int READ_DONE = -1;
        public static final int UNKNOWN_NAME = -3;

        private Companion() {
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "READ_ALL cannot be longer returned by 'decodeElementIndex', use 'decodeSequentially' instead")
        public static /* synthetic */ void READ_ALL$annotations() {
        }
    }

    /* compiled from: Decoding.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        public static int decodeCollectionSize(CompositeDecoder compositeDecoder, @NotNull SerialDescriptor descriptor) {
            Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
            return -1;
        }

        public static boolean decodeSequentially(CompositeDecoder compositeDecoder) {
            return false;
        }
    }

    boolean decodeBooleanElement(@NotNull SerialDescriptor serialDescriptor, int i);

    byte decodeByteElement(@NotNull SerialDescriptor serialDescriptor, int i);

    char decodeCharElement(@NotNull SerialDescriptor serialDescriptor, int i);

    int decodeCollectionSize(@NotNull SerialDescriptor serialDescriptor);

    double decodeDoubleElement(@NotNull SerialDescriptor serialDescriptor, int i);

    int decodeElementIndex(@NotNull SerialDescriptor serialDescriptor);

    float decodeFloatElement(@NotNull SerialDescriptor serialDescriptor, int i);

    int decodeIntElement(@NotNull SerialDescriptor serialDescriptor, int i);

    long decodeLongElement(@NotNull SerialDescriptor serialDescriptor, int i);

    @Nullable
    <T> T decodeNullableSerializableElement(@NotNull SerialDescriptor serialDescriptor, int i, @NotNull DeserializationStrategy<T> deserializationStrategy);

    boolean decodeSequentially();

    <T> T decodeSerializableElement(@NotNull SerialDescriptor serialDescriptor, int i, @NotNull DeserializationStrategy<T> deserializationStrategy);

    short decodeShortElement(@NotNull SerialDescriptor serialDescriptor, int i);

    @NotNull
    String decodeStringElement(@NotNull SerialDescriptor serialDescriptor, int i);

    void decodeUnitElement(@NotNull SerialDescriptor serialDescriptor, int i);

    void endStructure(@NotNull SerialDescriptor serialDescriptor);

    @NotNull
    SerialModule getContext();

    @NotNull
    UpdateMode getUpdateMode();

    @Nullable
    <T> T updateNullableSerializableElement(@NotNull SerialDescriptor serialDescriptor, int i, @NotNull DeserializationStrategy<T> deserializationStrategy, @Nullable T t);

    <T> T updateSerializableElement(@NotNull SerialDescriptor serialDescriptor, int i, @NotNull DeserializationStrategy<T> deserializationStrategy, T t);
}
