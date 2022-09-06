package kotlinx.serialization.json.internal;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt___StringsKt;
import kotlinx.serialization.CompositeDecoder;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.SerialDescriptorKt;
import kotlinx.serialization.UpdateMode;
import kotlinx.serialization.builtins.AbstractDecoder;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonInput;
import kotlinx.serialization.modules.SerialModule;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: StreamingJsonInput.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000º\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ1\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u001a\u0010\u001e\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001f\"\u0006\u0012\u0002\b\u00030 H\u0016¢\u0006\u0002\u0010!J\b\u0010\"\u001a\u00020#H\u0016J\b\u0010$\u001a\u00020%H\u0016J\b\u0010&\u001a\u00020'H\u0016J\b\u0010(\u001a\u00020)H\u0016J\u0010\u0010*\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010+\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\u001dH\u0016J\b\u0010-\u001a\u00020.H\u0016J\b\u0010/\u001a\u00020\u0011H\u0016J\b\u00100\u001a\u000201H\u0016J\u0010\u00102\u001a\u00020\u00112\u0006\u00103\u001a\u00020%H\u0002J\b\u00104\u001a\u000205H\u0016J\u0010\u00106\u001a\u00020\u00112\u0006\u00103\u001a\u00020%H\u0002J\b\u00107\u001a\u00020#H\u0016J\n\u00108\u001a\u0004\u0018\u000109H\u0016J\u0018\u0010:\u001a\u00020\u00112\u0006\u00103\u001a\u00020%2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J!\u0010;\u001a\u0002H<\"\u0004\b\u0000\u0010<2\f\u0010=\u001a\b\u0012\u0004\u0012\u0002H<0>H\u0016¢\u0006\u0002\u0010?J\b\u0010@\u001a\u00020AH\u0016J\b\u0010B\u001a\u00020CH\u0016J\u0010\u0010D\u001a\u00020E2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J9\u0010F\u001a\u0002H<\"\u0004\b\u0000\u0010<*\u00020C2\u0006\u0010G\u001a\u00020C2\u0017\u0010H\u001a\u0013\u0012\u0004\u0012\u00020C\u0012\u0004\u0012\u0002H<0I¢\u0006\u0002\bJH\u0082\b¢\u0006\u0002\u0010KR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\b8\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u00158VX\u0096\u0004¢\u0006\f\u0012\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019¨\u0006L"}, d2 = {"Lkotlinx/serialization/json/internal/StreamingJsonInput;", "Lkotlinx/serialization/json/JsonInput;", "Lkotlinx/serialization/builtins/AbstractDecoder;", "json", "Lkotlinx/serialization/json/Json;", "mode", "Lkotlinx/serialization/json/internal/WriteMode;", "reader", "Lkotlinx/serialization/json/internal/JsonReader;", "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/json/internal/WriteMode;Lkotlinx/serialization/json/internal/JsonReader;)V", PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, "Lkotlinx/serialization/json/JsonConfiguration;", "context", "Lkotlinx/serialization/modules/SerialModule;", "getContext", "()Lkotlinx/serialization/modules/SerialModule;", "currentIndex", "", "getJson", "()Lkotlinx/serialization/json/Json;", "updateMode", "Lkotlinx/serialization/UpdateMode;", "updateMode$annotations", "()V", "getUpdateMode", "()Lkotlinx/serialization/UpdateMode;", "beginStructure", "Lkotlinx/serialization/CompositeDecoder;", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "typeParams", "", "Lkotlinx/serialization/KSerializer;", "(Lkotlinx/serialization/SerialDescriptor;[Lkotlinx/serialization/KSerializer;)Lkotlinx/serialization/CompositeDecoder;", "decodeBoolean", "", "decodeByte", "", "decodeChar", "", "decodeDouble", "", "decodeElementIndex", "decodeEnum", "enumDescriptor", "decodeFloat", "", "decodeInt", "decodeJson", "Lkotlinx/serialization/json/JsonElement;", "decodeListIndex", "tokenClass", "decodeLong", "", "decodeMapIndex", "decodeNotNullMark", "decodeNull", "", "decodeObjectIndex", "decodeSerializableValue", ExifInterface.GPS_DIRECTION_TRUE, "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "(Lkotlinx/serialization/DeserializationStrategy;)Ljava/lang/Object;", "decodeShort", "", "decodeString", "", "endStructure", "", "parse", "type", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class StreamingJsonInput extends AbstractDecoder implements JsonInput {
    private final JsonConfiguration configuration;
    @NotNull
    private final SerialModule context;
    private int currentIndex;
    @NotNull
    private final Json json;
    private final WriteMode mode;
    @JvmField
    @NotNull
    public final JsonReader reader;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[WriteMode.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[WriteMode.LIST.ordinal()] = 1;
            $EnumSwitchMapping$0[WriteMode.MAP.ordinal()] = 2;
            $EnumSwitchMapping$0[WriteMode.POLY_OBJ.ordinal()] = 3;
            $EnumSwitchMapping$1 = new int[WriteMode.values().length];
            $EnumSwitchMapping$1[WriteMode.LIST.ordinal()] = 1;
            $EnumSwitchMapping$1[WriteMode.MAP.ordinal()] = 2;
            $EnumSwitchMapping$1[WriteMode.POLY_OBJ.ordinal()] = 3;
        }
    }

    public StreamingJsonInput(@NotNull Json json, @NotNull WriteMode mode, @NotNull JsonReader reader) {
        Intrinsics.checkParameterIsNotNull(json, "json");
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        Intrinsics.checkParameterIsNotNull(reader, "reader");
        this.json = json;
        this.mode = mode;
        this.reader = reader;
        this.context = getJson().getContext();
        this.currentIndex = -1;
        this.configuration = getJson().configuration;
    }

    private final int decodeListIndex(byte b) {
        if (b != 4 && this.currentIndex != -1) {
            JsonReader jsonReader = this.reader;
            if (jsonReader.tokenClass != 9) {
                jsonReader.fail("Expected end of the array or comma", jsonReader.tokenPosition);
                throw null;
            }
        }
        boolean z = true;
        if (!this.reader.getCanBeginValue()) {
            JsonReader jsonReader2 = this.reader;
            if (b == 4) {
                z = false;
            }
            int i = jsonReader2.currentPosition;
            if (z) {
                return -1;
            }
            jsonReader2.fail("Unexpected trailing comma", i);
            throw null;
        }
        this.currentIndex++;
        return this.currentIndex;
    }

    private final int decodeMapIndex(byte b) {
        boolean z = true;
        if (b != 4 && this.currentIndex % 2 == 1) {
            JsonReader jsonReader = this.reader;
            if (jsonReader.tokenClass != 7) {
                jsonReader.fail("Expected end of the object or comma", jsonReader.tokenPosition);
                throw null;
            }
        }
        if (this.currentIndex % 2 == 0) {
            JsonReader jsonReader2 = this.reader;
            if (jsonReader2.tokenClass != 5) {
                jsonReader2.fail("Expected ':' after the key", jsonReader2.tokenPosition);
                throw null;
            }
            jsonReader2.nextToken();
        }
        if (!this.reader.getCanBeginValue()) {
            JsonReader jsonReader3 = this.reader;
            if (b == 4) {
                z = false;
            }
            int i = jsonReader3.currentPosition;
            if (z) {
                return -1;
            }
            jsonReader3.fail("Unexpected trailing comma", i);
            throw null;
        }
        this.currentIndex++;
        return this.currentIndex;
    }

    private final int decodeObjectIndex(byte b, SerialDescriptor serialDescriptor) {
        if (b == 4 && !this.reader.getCanBeginValue()) {
            JsonReader.fail$default(this.reader, "Unexpected trailing comma", 0, 2, null);
            throw null;
        }
        while (this.reader.getCanBeginValue()) {
            this.currentIndex++;
            String decodeString = decodeString();
            JsonReader jsonReader = this.reader;
            if (jsonReader.tokenClass != 5) {
                jsonReader.fail("Expected ':'", jsonReader.tokenPosition);
                throw null;
            }
            jsonReader.nextToken();
            int elementIndex = serialDescriptor.getElementIndex(decodeString);
            if (elementIndex != -3) {
                return elementIndex;
            }
            if (this.configuration.getIgnoreUnknownKeys$kotlinx_serialization_runtime()) {
                this.reader.skipElement();
                JsonReader jsonReader2 = this.reader;
                if (jsonReader2.tokenClass == 4) {
                    jsonReader2.nextToken();
                    JsonReader jsonReader3 = this.reader;
                    boolean canBeginValue = jsonReader3.getCanBeginValue();
                    int i = this.reader.currentPosition;
                    if (!canBeginValue) {
                        jsonReader3.fail("Unexpected trailing comma", i);
                        throw null;
                    }
                }
            } else {
                JsonReader.fail$default(this.reader, GeneratedOutlineSupport1.outline76("Encountered an unknown key '", decodeString, "'. You can enable 'JsonConfiguration.ignoreUnknownKeys' property", " to ignore unknown keys"), 0, 2, null);
                throw null;
            }
        }
        return -1;
    }

    private final <T> T parse(@NotNull String str, String str2, Function1<? super String, ? extends T> function1) {
        return function1.mo12165invoke(str);
    }

    public static /* synthetic */ void updateMode$annotations() {
    }

    @Override // kotlinx.serialization.builtins.AbstractDecoder, kotlinx.serialization.Decoder
    @NotNull
    public CompositeDecoder beginStructure(@NotNull SerialDescriptor descriptor, @NotNull KSerializer<?>... typeParams) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(typeParams, "typeParams");
        WriteMode switchMode = WriteModeKt.switchMode(getJson(), descriptor);
        if (switchMode.begin != 0) {
            JsonReader jsonReader = this.reader;
            if (jsonReader.tokenClass == switchMode.beginTc) {
                jsonReader.nextToken();
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Expected '");
                outline107.append(switchMode.begin);
                outline107.append(", kind: ");
                outline107.append(descriptor.mo12397getKind());
                outline107.append(Chars.QUOTE);
                jsonReader.fail(outline107.toString(), jsonReader.tokenPosition);
                throw null;
            }
        }
        int i = WhenMappings.$EnumSwitchMapping$0[switchMode.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return new StreamingJsonInput(getJson(), switchMode, this.reader);
        }
        return this.mode == switchMode ? this : new StreamingJsonInput(getJson(), switchMode, this.reader);
    }

    @Override // kotlinx.serialization.builtins.AbstractDecoder, kotlinx.serialization.Decoder
    public boolean decodeBoolean() {
        if (this.configuration.isLenient$kotlinx_serialization_runtime()) {
            return StringOpsKt.toBooleanStrict(this.reader.takeString());
        }
        return StringOpsKt.toBooleanStrict(this.reader.takeBooleanStringUnquoted());
    }

    @Override // kotlinx.serialization.builtins.AbstractDecoder, kotlinx.serialization.Decoder
    public byte decodeByte() {
        return Byte.parseByte(this.reader.takeString());
    }

    @Override // kotlinx.serialization.builtins.AbstractDecoder, kotlinx.serialization.Decoder
    public char decodeChar() {
        char single;
        single = StringsKt___StringsKt.single(this.reader.takeString());
        return single;
    }

    @Override // kotlinx.serialization.builtins.AbstractDecoder, kotlinx.serialization.CompositeDecoder
    public int decodeCollectionSize(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        return JsonInput.DefaultImpls.decodeCollectionSize(this, descriptor);
    }

    @Override // kotlinx.serialization.builtins.AbstractDecoder, kotlinx.serialization.Decoder
    public double decodeDouble() {
        return Double.parseDouble(this.reader.takeString());
    }

    @Override // kotlinx.serialization.CompositeDecoder
    public int decodeElementIndex(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        JsonReader jsonReader = this.reader;
        byte b = jsonReader.tokenClass;
        if (b == 4) {
            boolean z = this.currentIndex != -1;
            JsonReader jsonReader2 = this.reader;
            int i = jsonReader2.currentPosition;
            if (z) {
                jsonReader2.nextToken();
            } else {
                jsonReader.fail("Unexpected leading comma", i);
                throw null;
            }
        }
        int i2 = WhenMappings.$EnumSwitchMapping$1[this.mode.ordinal()];
        if (i2 != 1) {
            if (i2 == 2) {
                return decodeMapIndex(b);
            }
            if (i2 != 3) {
                return decodeObjectIndex(b, descriptor);
            }
            this.currentIndex++;
            int i3 = this.currentIndex;
            if (i3 == 0) {
                return 0;
            }
            return i3 != 1 ? -1 : 1;
        }
        return decodeListIndex(b);
    }

    @Override // kotlinx.serialization.builtins.AbstractDecoder, kotlinx.serialization.Decoder
    public int decodeEnum(@NotNull SerialDescriptor enumDescriptor) {
        Intrinsics.checkParameterIsNotNull(enumDescriptor, "enumDescriptor");
        return SerialDescriptorKt.getElementIndexOrThrow(enumDescriptor, decodeString());
    }

    @Override // kotlinx.serialization.builtins.AbstractDecoder, kotlinx.serialization.Decoder
    public float decodeFloat() {
        return Float.parseFloat(this.reader.takeString());
    }

    @Override // kotlinx.serialization.builtins.AbstractDecoder, kotlinx.serialization.Decoder
    public int decodeInt() {
        return Integer.parseInt(this.reader.takeString());
    }

    @Override // kotlinx.serialization.json.JsonInput
    @NotNull
    public JsonElement decodeJson() {
        return new JsonParser(getJson().configuration, this.reader).read();
    }

    @Override // kotlinx.serialization.builtins.AbstractDecoder, kotlinx.serialization.Decoder
    public long decodeLong() {
        return Long.parseLong(this.reader.takeString());
    }

    @Override // kotlinx.serialization.builtins.AbstractDecoder, kotlinx.serialization.Decoder
    public boolean decodeNotNullMark() {
        return this.reader.tokenClass != 10;
    }

    @Override // kotlinx.serialization.builtins.AbstractDecoder, kotlinx.serialization.Decoder
    @Nullable
    public Void decodeNull() {
        JsonReader jsonReader = this.reader;
        if (jsonReader.tokenClass != 10) {
            jsonReader.fail("Expected 'null' literal", jsonReader.tokenPosition);
            throw null;
        }
        jsonReader.nextToken();
        return null;
    }

    @Override // kotlinx.serialization.builtins.AbstractDecoder, kotlinx.serialization.Decoder
    @Nullable
    public <T> T decodeNullableSerializableValue(@NotNull DeserializationStrategy<T> deserializer) {
        Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
        return (T) JsonInput.DefaultImpls.decodeNullableSerializableValue(this, deserializer);
    }

    @Override // kotlinx.serialization.builtins.AbstractDecoder, kotlinx.serialization.CompositeDecoder
    public boolean decodeSequentially() {
        return JsonInput.DefaultImpls.decodeSequentially(this);
    }

    @Override // kotlinx.serialization.builtins.AbstractDecoder, kotlinx.serialization.Decoder
    public <T> T decodeSerializableValue(@NotNull DeserializationStrategy<T> deserializer) {
        Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
        return (T) PolymorphicKt.decodeSerializableValuePolymorphic(this, deserializer);
    }

    @Override // kotlinx.serialization.builtins.AbstractDecoder, kotlinx.serialization.Decoder
    public short decodeShort() {
        return Short.parseShort(this.reader.takeString());
    }

    @Override // kotlinx.serialization.builtins.AbstractDecoder, kotlinx.serialization.Decoder
    @NotNull
    public String decodeString() {
        if (this.configuration.isLenient$kotlinx_serialization_runtime()) {
            return this.reader.takeString();
        }
        return this.reader.takeStringQuoted();
    }

    @Override // kotlinx.serialization.builtins.AbstractDecoder, kotlinx.serialization.CompositeDecoder
    public void endStructure(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        WriteMode writeMode = this.mode;
        if (writeMode.end != 0) {
            JsonReader jsonReader = this.reader;
            if (jsonReader.tokenClass == writeMode.endTc) {
                jsonReader.nextToken();
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Expected '");
            outline107.append(this.mode.end);
            outline107.append(Chars.QUOTE);
            jsonReader.fail(outline107.toString(), jsonReader.tokenPosition);
            throw null;
        }
    }

    @Override // kotlinx.serialization.builtins.AbstractDecoder, kotlinx.serialization.Decoder, kotlinx.serialization.CompositeDecoder
    @NotNull
    public SerialModule getContext() {
        return this.context;
    }

    @Override // kotlinx.serialization.json.JsonInput
    @NotNull
    public Json getJson() {
        return this.json;
    }

    @Override // kotlinx.serialization.builtins.AbstractDecoder, kotlinx.serialization.Decoder, kotlinx.serialization.CompositeDecoder
    @NotNull
    public UpdateMode getUpdateMode() {
        return this.configuration.getUpdateMode$kotlinx_serialization_runtime();
    }

    @Override // kotlinx.serialization.builtins.AbstractDecoder, kotlinx.serialization.Decoder
    @Nullable
    public <T> T updateNullableSerializableValue(@NotNull DeserializationStrategy<T> deserializer, @Nullable T t) {
        Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
        return (T) JsonInput.DefaultImpls.updateNullableSerializableValue(this, deserializer, t);
    }

    @Override // kotlinx.serialization.builtins.AbstractDecoder, kotlinx.serialization.Decoder
    public <T> T updateSerializableValue(@NotNull DeserializationStrategy<T> deserializer, T t) {
        Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
        return (T) JsonInput.DefaultImpls.updateSerializableValue(this, deserializer, t);
    }
}
