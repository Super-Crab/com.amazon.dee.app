package kotlinx.serialization.json.internal;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.accessory.frames.contacts.ContactsModuleConstants;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.CompositeEncoder;
import kotlinx.serialization.Encoder;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.builtins.AbstractEncoder;
import kotlinx.serialization.internal.AbstractPolymorphicSerializer;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementSerializer;
import kotlinx.serialization.json.JsonExceptionsKt;
import kotlinx.serialization.json.JsonOutput;
import kotlinx.serialization.modules.SerialModule;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: StreamingJsonOutput.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002:\u0001FB3\b\u0010\u0012\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u000e\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000b¢\u0006\u0002\u0010\fB-\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u000e\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000b¢\u0006\u0002\u0010\u000fJ1\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u001a\u0010 \u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030!0\u000b\"\u0006\u0012\u0002\b\u00030!H\u0016¢\u0006\u0002\u0010\"J\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0017H\u0016J\u0010\u0010&\u001a\u00020$2\u0006\u0010%\u001a\u00020'H\u0016J\u0010\u0010(\u001a\u00020$2\u0006\u0010%\u001a\u00020)H\u0016J\u0010\u0010*\u001a\u00020$2\u0006\u0010%\u001a\u00020+H\u0016J\u0018\u0010,\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020.H\u0016J\u0018\u0010/\u001a\u00020$2\u0006\u00100\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020.H\u0016J\u0010\u00101\u001a\u00020$2\u0006\u0010%\u001a\u000202H\u0016J\u0010\u00103\u001a\u00020$2\u0006\u0010%\u001a\u00020.H\u0016J\u0010\u00104\u001a\u00020$2\u0006\u00105\u001a\u000206H\u0016J\u0010\u00107\u001a\u00020$2\u0006\u0010%\u001a\u000208H\u0016J\b\u00109\u001a\u00020$H\u0016J)\u0010:\u001a\u00020$\"\u0004\b\u0000\u0010;2\f\u0010<\u001a\b\u0012\u0004\u0012\u0002H;0=2\u0006\u0010%\u001a\u0002H;H\u0016¢\u0006\u0002\u0010>J\u0010\u0010?\u001a\u00020$2\u0006\u0010%\u001a\u00020@H\u0016J\u0010\u0010A\u001a\u00020$2\u0006\u0010%\u001a\u00020BH\u0016J\u0010\u0010C\u001a\u00020$2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010D\u001a\u00020$2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0018\u0010E\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020.H\u0016R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u00020\u0013X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006G"}, d2 = {"Lkotlinx/serialization/json/internal/StreamingJsonOutput;", "Lkotlinx/serialization/json/JsonOutput;", "Lkotlinx/serialization/builtins/AbstractEncoder;", ContactsModuleConstants.OUTPUT, "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "json", "Lkotlinx/serialization/json/Json;", "mode", "Lkotlinx/serialization/json/internal/WriteMode;", "modeReuseCache", "", "(Ljava/lang/StringBuilder;Lkotlinx/serialization/json/Json;Lkotlinx/serialization/json/internal/WriteMode;[Lkotlinx/serialization/json/JsonOutput;)V", "composer", "Lkotlinx/serialization/json/internal/StreamingJsonOutput$Composer;", "(Lkotlinx/serialization/json/internal/StreamingJsonOutput$Composer;Lkotlinx/serialization/json/Json;Lkotlinx/serialization/json/internal/WriteMode;[Lkotlinx/serialization/json/JsonOutput;)V", PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, "Lkotlinx/serialization/json/JsonConfiguration;", "context", "Lkotlinx/serialization/modules/SerialModule;", "getContext", "()Lkotlinx/serialization/modules/SerialModule;", "forceQuoting", "", "getJson", "()Lkotlinx/serialization/json/Json;", "[Lkotlinx/serialization/json/JsonOutput;", "writePolymorphic", "beginStructure", "Lkotlinx/serialization/CompositeEncoder;", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "typeSerializers", "Lkotlinx/serialization/KSerializer;", "(Lkotlinx/serialization/SerialDescriptor;[Lkotlinx/serialization/KSerializer;)Lkotlinx/serialization/CompositeEncoder;", "encodeBoolean", "", "value", "encodeByte", "", "encodeChar", "", "encodeDouble", "", "encodeElement", "index", "", "encodeEnum", "enumDescriptor", "encodeFloat", "", "encodeInt", "encodeJson", "element", "Lkotlinx/serialization/json/JsonElement;", "encodeLong", "", "encodeNull", "encodeSerializableValue", ExifInterface.GPS_DIRECTION_TRUE, "serializer", "Lkotlinx/serialization/SerializationStrategy;", "(Lkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)V", "encodeShort", "", "encodeString", "", "encodeTypeInfo", "endStructure", "shouldEncodeElementDefault", "Composer", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class StreamingJsonOutput extends AbstractEncoder implements JsonOutput {
    private final Composer composer;
    private final JsonConfiguration configuration;
    @NotNull
    private final SerialModule context;
    private boolean forceQuoting;
    @NotNull
    private final Json json;
    private final WriteMode mode;
    private final JsonOutput[] modeReuseCache;
    private boolean writePolymorphic;

    /* compiled from: StreamingJsonOutput.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\u0010\f\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0010J\u0016\u0010\u0012\u001a\n \u0013*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0014\u001a\u00020\u000bJ\u0016\u0010\u0012\u001a\n \u0013*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u0012\u001a\n \u0013*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0014\u001a\u00020\u0016J\u0016\u0010\u0012\u001a\n \u0013*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0014\u001a\u00020\u0017J\u0016\u0010\u0012\u001a\n \u0013*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0014\u001a\u00020\u0018J\u0016\u0010\u0012\u001a\n \u0013*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0014\u001a\u00020\tJ\u0016\u0010\u0012\u001a\n \u0013*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0014\u001a\u00020\u0019J\u0016\u0010\u0012\u001a\n \u0013*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0014\u001a\u00020\u001aJ\u0016\u0010\u0012\u001a\n \u0013*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0014\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u001bJ\u0006\u0010\u001e\u001a\u00020\u0010J\u0006\u0010\u001f\u001a\u00020\u0010R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00060\u0003j\u0002`\u00048\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006 "}, d2 = {"Lkotlinx/serialization/json/internal/StreamingJsonOutput$Composer;", "", "sb", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "json", "Lkotlinx/serialization/json/Json;", "(Ljava/lang/StringBuilder;Lkotlinx/serialization/json/Json;)V", ModelTransformer.KEY_BATTERY_LEVEL, "", "<set-?>", "", "writingFirst", "getWritingFirst", "()Z", "indent", "", "nextItem", "print", "kotlin.jvm.PlatformType", "v", "", "", "", "", "", "", "", "printQuoted", "value", "space", "unIndent", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Composer {
        private final Json json;
        private int level;
        @JvmField
        @NotNull
        public final StringBuilder sb;
        private boolean writingFirst;

        public Composer(@NotNull StringBuilder sb, @NotNull Json json) {
            Intrinsics.checkParameterIsNotNull(sb, "sb");
            Intrinsics.checkParameterIsNotNull(json, "json");
            this.sb = sb;
            this.json = json;
            this.writingFirst = true;
        }

        public final boolean getWritingFirst() {
            return this.writingFirst;
        }

        public final void indent() {
            this.writingFirst = true;
            this.level++;
        }

        public final void nextItem() {
            this.writingFirst = false;
            if (this.json.configuration.getPrettyPrint$kotlinx_serialization_runtime()) {
                print("\n");
                int i = this.level;
                for (int i2 = 0; i2 < i; i2++) {
                    print(this.json.configuration.getIndent$kotlinx_serialization_runtime());
                }
            }
        }

        public final StringBuilder print(char c) {
            StringBuilder sb = this.sb;
            sb.append(c);
            return sb;
        }

        public final void printQuoted(@NotNull String value) {
            Intrinsics.checkParameterIsNotNull(value, "value");
            StringOpsKt.printQuoted(this.sb, value);
        }

        public final void space() {
            if (this.json.configuration.getPrettyPrint$kotlinx_serialization_runtime()) {
                print(Chars.SPACE);
            }
        }

        public final void unIndent() {
            this.level--;
        }

        public final StringBuilder print(@NotNull String v) {
            Intrinsics.checkParameterIsNotNull(v, "v");
            StringBuilder sb = this.sb;
            sb.append(v);
            return sb;
        }

        public final StringBuilder print(float f) {
            StringBuilder sb = this.sb;
            sb.append(f);
            return sb;
        }

        public final StringBuilder print(double d) {
            StringBuilder sb = this.sb;
            sb.append(d);
            return sb;
        }

        public final StringBuilder print(byte b) {
            StringBuilder sb = this.sb;
            sb.append(Byte.valueOf(b));
            return sb;
        }

        public final StringBuilder print(short s) {
            StringBuilder sb = this.sb;
            sb.append(Short.valueOf(s));
            return sb;
        }

        public final StringBuilder print(int i) {
            StringBuilder sb = this.sb;
            sb.append(i);
            return sb;
        }

        public final StringBuilder print(long j) {
            StringBuilder sb = this.sb;
            sb.append(j);
            return sb;
        }

        public final StringBuilder print(boolean z) {
            StringBuilder sb = this.sb;
            sb.append(z);
            return sb;
        }
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[WriteMode.values().length];

        static {
            $EnumSwitchMapping$0[WriteMode.LIST.ordinal()] = 1;
            $EnumSwitchMapping$0[WriteMode.MAP.ordinal()] = 2;
            $EnumSwitchMapping$0[WriteMode.POLY_OBJ.ordinal()] = 3;
        }
    }

    public StreamingJsonOutput(@NotNull Composer composer, @NotNull Json json, @NotNull WriteMode mode, @NotNull JsonOutput[] modeReuseCache) {
        Intrinsics.checkParameterIsNotNull(composer, "composer");
        Intrinsics.checkParameterIsNotNull(json, "json");
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        Intrinsics.checkParameterIsNotNull(modeReuseCache, "modeReuseCache");
        this.composer = composer;
        this.json = json;
        this.mode = mode;
        this.modeReuseCache = modeReuseCache;
        this.context = getJson().getContext();
        this.configuration = getJson().configuration;
        int ordinal = this.mode.ordinal();
        JsonOutput[] jsonOutputArr = this.modeReuseCache;
        if (jsonOutputArr[ordinal] == null && jsonOutputArr[ordinal] == this) {
            return;
        }
        this.modeReuseCache[ordinal] = this;
    }

    private final void encodeTypeInfo(SerialDescriptor serialDescriptor) {
        this.composer.nextItem();
        encodeString(this.configuration.getClassDiscriminator$kotlinx_serialization_runtime());
        this.composer.print(JsonReaderKt.COLON);
        this.composer.space();
        encodeString(serialDescriptor.getSerialName());
    }

    @Override // kotlinx.serialization.builtins.AbstractEncoder, kotlinx.serialization.Encoder
    @NotNull
    public CompositeEncoder beginCollection(@NotNull SerialDescriptor descriptor, int i, @NotNull KSerializer<?>... typeSerializers) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(typeSerializers, "typeSerializers");
        return JsonOutput.DefaultImpls.beginCollection(this, descriptor, i, typeSerializers);
    }

    @Override // kotlinx.serialization.builtins.AbstractEncoder, kotlinx.serialization.Encoder
    @NotNull
    public CompositeEncoder beginStructure(@NotNull SerialDescriptor descriptor, @NotNull KSerializer<?>... typeSerializers) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(typeSerializers, "typeSerializers");
        WriteMode switchMode = WriteModeKt.switchMode(getJson(), descriptor);
        char c = switchMode.begin;
        if (c != 0) {
            this.composer.print(c);
            this.composer.indent();
        }
        if (this.writePolymorphic) {
            this.writePolymorphic = false;
            encodeTypeInfo(descriptor);
        }
        if (this.mode == switchMode) {
            return this;
        }
        JsonOutput jsonOutput = this.modeReuseCache[switchMode.ordinal()];
        return jsonOutput != null ? jsonOutput : new StreamingJsonOutput(this.composer, getJson(), switchMode, this.modeReuseCache);
    }

    @Override // kotlinx.serialization.builtins.AbstractEncoder, kotlinx.serialization.Encoder
    public void encodeBoolean(boolean z) {
        if (this.forceQuoting) {
            encodeString(String.valueOf(z));
        } else {
            this.composer.print(z);
        }
    }

    @Override // kotlinx.serialization.builtins.AbstractEncoder, kotlinx.serialization.Encoder
    public void encodeByte(byte b) {
        if (this.forceQuoting) {
            encodeString(String.valueOf((int) b));
        } else {
            this.composer.print(b);
        }
    }

    @Override // kotlinx.serialization.builtins.AbstractEncoder, kotlinx.serialization.Encoder
    public void encodeChar(char c) {
        encodeString(String.valueOf(c));
    }

    @Override // kotlinx.serialization.builtins.AbstractEncoder, kotlinx.serialization.Encoder
    public void encodeDouble(double d) {
        if (this.forceQuoting) {
            encodeString(String.valueOf(d));
        } else {
            this.composer.print(d);
        }
        if (!this.configuration.getSerializeSpecialFloatingPointValues$kotlinx_serialization_runtime()) {
            if (!Double.isInfinite(d) && !Double.isNaN(d)) {
                return;
            }
            Double valueOf = Double.valueOf(d);
            String sb = this.composer.sb.toString();
            Intrinsics.checkExpressionValueIsNotNull(sb, "composer.sb.toString()");
            throw JsonExceptionsKt.InvalidFloatingPoint(valueOf, "double", sb);
        }
    }

    @Override // kotlinx.serialization.builtins.AbstractEncoder
    public boolean encodeElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        int i2 = WhenMappings.$EnumSwitchMapping$0[this.mode.ordinal()];
        if (i2 != 1) {
            boolean z = false;
            if (i2 != 2) {
                if (i2 != 3) {
                    if (!this.composer.getWritingFirst()) {
                        this.composer.print(JsonReaderKt.COMMA);
                    }
                    this.composer.nextItem();
                    encodeString(descriptor.getElementName(i));
                    this.composer.print(JsonReaderKt.COLON);
                    this.composer.space();
                } else {
                    if (i == 0) {
                        this.forceQuoting = true;
                    }
                    if (i == 1) {
                        this.composer.print(JsonReaderKt.COMMA);
                        this.composer.space();
                        this.forceQuoting = false;
                    }
                }
            } else if (!this.composer.getWritingFirst()) {
                if (i % 2 == 0) {
                    this.composer.print(JsonReaderKt.COMMA);
                    this.composer.nextItem();
                    z = true;
                } else {
                    this.composer.print(JsonReaderKt.COLON);
                    this.composer.space();
                }
                this.forceQuoting = z;
            } else {
                this.forceQuoting = true;
                this.composer.nextItem();
            }
        } else {
            if (!this.composer.getWritingFirst()) {
                this.composer.print(JsonReaderKt.COMMA);
            }
            this.composer.nextItem();
        }
        return true;
    }

    @Override // kotlinx.serialization.builtins.AbstractEncoder, kotlinx.serialization.Encoder
    public void encodeEnum(@NotNull SerialDescriptor enumDescriptor, int i) {
        Intrinsics.checkParameterIsNotNull(enumDescriptor, "enumDescriptor");
        encodeString(enumDescriptor.getElementName(i));
    }

    @Override // kotlinx.serialization.builtins.AbstractEncoder, kotlinx.serialization.Encoder
    public void encodeFloat(float f) {
        if (this.forceQuoting) {
            encodeString(String.valueOf(f));
        } else {
            this.composer.print(f);
        }
        if (!this.configuration.getSerializeSpecialFloatingPointValues$kotlinx_serialization_runtime()) {
            if (!Float.isInfinite(f) && !Float.isNaN(f)) {
                return;
            }
            Float valueOf = Float.valueOf(f);
            String sb = this.composer.sb.toString();
            Intrinsics.checkExpressionValueIsNotNull(sb, "composer.sb.toString()");
            throw JsonExceptionsKt.InvalidFloatingPoint(valueOf, "float", sb);
        }
    }

    @Override // kotlinx.serialization.builtins.AbstractEncoder, kotlinx.serialization.Encoder
    public void encodeInt(int i) {
        if (this.forceQuoting) {
            encodeString(String.valueOf(i));
        } else {
            this.composer.print(i);
        }
    }

    @Override // kotlinx.serialization.json.JsonOutput
    public void encodeJson(@NotNull JsonElement element) {
        Intrinsics.checkParameterIsNotNull(element, "element");
        encodeSerializableValue(JsonElementSerializer.INSTANCE, element);
    }

    @Override // kotlinx.serialization.builtins.AbstractEncoder, kotlinx.serialization.Encoder
    public void encodeLong(long j) {
        if (this.forceQuoting) {
            encodeString(String.valueOf(j));
        } else {
            this.composer.print(j);
        }
    }

    @Override // kotlinx.serialization.builtins.AbstractEncoder, kotlinx.serialization.CompositeEncoder
    @Deprecated(level = DeprecationLevel.ERROR, message = "This method is deprecated for removal. Please remove it from your implementation and delegate to default method instead")
    public void encodeNonSerializableElement(@NotNull SerialDescriptor descriptor, int i, @NotNull Object value) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(value, "value");
        JsonOutput.DefaultImpls.encodeNonSerializableElement(this, descriptor, i, value);
    }

    @Override // kotlinx.serialization.builtins.AbstractEncoder, kotlinx.serialization.Encoder
    public void encodeNotNullMark() {
        JsonOutput.DefaultImpls.encodeNotNullMark(this);
    }

    @Override // kotlinx.serialization.builtins.AbstractEncoder, kotlinx.serialization.Encoder
    public void encodeNull() {
        this.composer.print("null");
    }

    @Override // kotlinx.serialization.builtins.AbstractEncoder, kotlinx.serialization.Encoder
    public <T> void encodeNullableSerializableValue(@NotNull SerializationStrategy<? super T> serializer, @Nullable T t) {
        Intrinsics.checkParameterIsNotNull(serializer, "serializer");
        JsonOutput.DefaultImpls.encodeNullableSerializableValue(this, serializer, t);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.builtins.AbstractEncoder, kotlinx.serialization.Encoder
    public <T> void encodeSerializableValue(@NotNull SerializationStrategy<? super T> serializer, T t) {
        Intrinsics.checkParameterIsNotNull(serializer, "serializer");
        if ((serializer instanceof AbstractPolymorphicSerializer) && !getJson().configuration.getUseArrayPolymorphism$kotlinx_serialization_runtime()) {
            AbstractPolymorphicSerializer abstractPolymorphicSerializer = (AbstractPolymorphicSerializer) serializer;
            if (t != 0) {
                KSerializer<? extends T> findPolymorphicSerializer = abstractPolymorphicSerializer.findPolymorphicSerializer((Encoder) this, (StreamingJsonOutput) t);
                if (findPolymorphicSerializer != null) {
                    PolymorphicKt.validateIfSealed((KSerializer) serializer, findPolymorphicSerializer, getJson().configuration.getClassDiscriminator$kotlinx_serialization_runtime());
                    PolymorphicKt.checkKind(findPolymorphicSerializer.getDescriptor().mo12397getKind());
                    this.writePolymorphic = true;
                    findPolymorphicSerializer.serialize(this, t);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<T>");
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Any");
        }
        serializer.serialize(this, t);
    }

    @Override // kotlinx.serialization.builtins.AbstractEncoder, kotlinx.serialization.Encoder
    public void encodeShort(short s) {
        if (this.forceQuoting) {
            encodeString(String.valueOf((int) s));
        } else {
            this.composer.print(s);
        }
    }

    @Override // kotlinx.serialization.builtins.AbstractEncoder, kotlinx.serialization.Encoder
    public void encodeString(@NotNull String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        if (this.configuration.getUnquotedPrint$kotlinx_serialization_runtime() && !StringOpsKt.shouldBeQuoted(value)) {
            this.composer.print(value);
        } else {
            this.composer.printQuoted(value);
        }
    }

    @Override // kotlinx.serialization.CompositeEncoder
    public void endStructure(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        if (this.mode.end != 0) {
            this.composer.unIndent();
            this.composer.nextItem();
            this.composer.print(this.mode.end);
        }
    }

    @Override // kotlinx.serialization.builtins.AbstractEncoder, kotlinx.serialization.Encoder, kotlinx.serialization.CompositeEncoder
    @NotNull
    public SerialModule getContext() {
        return this.context;
    }

    @Override // kotlinx.serialization.json.JsonOutput
    @NotNull
    public Json getJson() {
        return this.json;
    }

    @Override // kotlinx.serialization.builtins.AbstractEncoder, kotlinx.serialization.CompositeEncoder
    public boolean shouldEncodeElementDefault(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        return this.configuration.getEncodeDefaults$kotlinx_serialization_runtime();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public StreamingJsonOutput(@NotNull StringBuilder output, @NotNull Json json, @NotNull WriteMode mode, @NotNull JsonOutput[] modeReuseCache) {
        this(new Composer(output, json), json, mode, modeReuseCache);
        Intrinsics.checkParameterIsNotNull(output, "output");
        Intrinsics.checkParameterIsNotNull(json, "json");
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        Intrinsics.checkParameterIsNotNull(modeReuseCache, "modeReuseCache");
    }
}
