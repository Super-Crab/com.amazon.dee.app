package kotlinx.serialization.json;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.DecodingKt;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.EncodingKt;
import kotlinx.serialization.ImplicitReflectionSerializer;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.StringFormat;
import kotlinx.serialization.UnstableDefault;
import kotlinx.serialization.json.internal.ContextValidator;
import kotlinx.serialization.json.internal.JsonReader;
import kotlinx.serialization.json.internal.StreamingJsonInput;
import kotlinx.serialization.json.internal.StreamingJsonOutput;
import kotlinx.serialization.json.internal.TreeJsonInputKt;
import kotlinx.serialization.json.internal.TreeJsonOutputKt;
import kotlinx.serialization.json.internal.WriteMode;
import kotlinx.serialization.modules.EmptyModule;
import kotlinx.serialization.modules.SerialModule;
import kotlinx.serialization.modules.SerialModuleExtensionsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: Json.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 +2\u00020\u0001:\u0001+B \b\u0017\u0012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006¢\u0006\u0002\u0010\u0007B\u0007\b\u0017¢\u0006\u0002\u0010\bB\u000f\b\u0012\u0012\u0006\u0010\t\u001a\u00020\u0004¢\u0006\u0002\u0010\nB\u0019\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ'\u0010\u0013\u001a\u0002H\u0014\"\u0004\b\u0000\u0010\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00140\u00162\u0006\u0010\u0017\u001a\u00020\u0018¢\u0006\u0002\u0010\u0019J\"\u0010\u0013\u001a\u0002H\u0014\"\n\b\u0000\u0010\u0014\u0018\u0001*\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0018H\u0087\b¢\u0006\u0002\u0010\u001cJ)\u0010\u001d\u001a\u0002H\u0014\"\u0004\b\u0000\u0010\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00140\u00162\u0006\u0010\u001e\u001a\u00020\u001fH\u0016¢\u0006\u0002\u0010 J\u000e\u0010!\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u001fJ)\u0010\"\u001a\u00020\u001f\"\u0004\b\u0000\u0010\u00142\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00140$2\u0006\u0010%\u001a\u0002H\u0014H\u0016¢\u0006\u0002\u0010&J\"\u0010'\u001a\u00020\u0018\"\n\b\u0000\u0010\u0014\u0018\u0001*\u00020\u001a2\u0006\u0010%\u001a\u0002H\u0014H\u0087\b¢\u0006\u0002\u0010(J'\u0010'\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u00142\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00140$2\u0006\u0010%\u001a\u0002H\u0014¢\u0006\u0002\u0010)J\b\u0010*\u001a\u00020\u0005H\u0002R\u0010\u0010\u000b\u001a\u00020\f8\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\u00020\u000eX\u0096\u0004¢\u0006\n\n\u0002\b\u0012\u001a\u0004\b\u0010\u0010\u0011¨\u0006,"}, d2 = {"Lkotlinx/serialization/json/Json;", "Lkotlinx/serialization/StringFormat;", "block", "Lkotlin/Function1;", "Lkotlinx/serialization/json/JsonBuilder;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;)V", "()V", "builder", "(Lkotlinx/serialization/json/JsonBuilder;)V", PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, "Lkotlinx/serialization/json/JsonConfiguration;", "context", "Lkotlinx/serialization/modules/SerialModule;", "(Lkotlinx/serialization/json/JsonConfiguration;Lkotlinx/serialization/modules/SerialModule;)V", "getContext", "()Lkotlinx/serialization/modules/SerialModule;", "context$1", "fromJson", ExifInterface.GPS_DIRECTION_TRUE, "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "json", "Lkotlinx/serialization/json/JsonElement;", "(Lkotlinx/serialization/DeserializationStrategy;Lkotlinx/serialization/json/JsonElement;)Ljava/lang/Object;", "", "tree", "(Lkotlinx/serialization/json/JsonElement;)Ljava/lang/Object;", "parse", "string", "", "(Lkotlinx/serialization/DeserializationStrategy;Ljava/lang/String;)Ljava/lang/Object;", "parseJson", "stringify", "serializer", "Lkotlinx/serialization/SerializationStrategy;", "value", "(Lkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)Ljava/lang/String;", "toJson", "(Ljava/lang/Object;)Lkotlinx/serialization/json/JsonElement;", "(Lkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)Lkotlinx/serialization/json/JsonElement;", "validateConfiguration", "Default", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class Json implements StringFormat {
    private static final String message = "Top-level JSON instances are deprecated for removal in the favour of user-configured one. You can either use a Json top-level object, configure your own instance  via 'Json {}' builder-like constructor, 'Json(JsonConfiguration)' constructor or by tweaking stable configuration 'Json(JsonConfiguration.Stable.copy(prettyPrint = true))'";
    @JvmField
    @NotNull
    public final JsonConfiguration configuration;
    @NotNull
    private final SerialModule context$1;
    public static final Default Default = new Default(null);
    @NotNull
    private static final Json plain = new Json(new JsonConfiguration(false, false, false, false, false, false, false, null, true, null, null, 1791, null), null, 2, null);
    @NotNull
    private static final Json unquoted = new Json(new JsonConfiguration(false, true, true, true, false, false, true, null, true, null, null, 1713, null), null, 2, null);
    @NotNull
    private static final Json indented = new Json(new JsonConfiguration(false, false, false, false, false, true, false, null, true, null, null, 1759, null), null, 2, null);
    @NotNull
    private static final Json nonstrict = new Json(new JsonConfiguration(false, true, true, true, false, false, false, null, true, null, null, 1777, null), null, 2, null);
    private static final Json jsonInstance = new Json(JsonConfiguration.Companion.getDefault(), null, 2, null);

    /* compiled from: Json.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0087\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J'\u0010\u0018\u001a\u0002H\u0019\"\u0004\b\u0000\u0010\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00190\u001b2\u0006\u0010\u001c\u001a\u00020\u001d¢\u0006\u0002\u0010\u001eJ\"\u0010\u0018\u001a\u0002H\u0019\"\n\b\u0000\u0010\u0019\u0018\u0001*\u00020\u001f2\u0006\u0010 \u001a\u00020\u001dH\u0087\b¢\u0006\u0002\u0010!J)\u0010\"\u001a\u0002H\u0019\"\u0004\b\u0000\u0010\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00190\u001b2\u0006\u0010#\u001a\u00020\u000eH\u0016¢\u0006\u0002\u0010$J\u000e\u0010%\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020\u000eJ)\u0010&\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u00192\f\u0010'\u001a\b\u0012\u0004\u0012\u0002H\u00190(2\u0006\u0010)\u001a\u0002H\u0019H\u0016¢\u0006\u0002\u0010*J\"\u0010+\u001a\u00020\u001d\"\n\b\u0000\u0010\u0019\u0018\u0001*\u00020\u001f2\u0006\u0010)\u001a\u0002H\u0019H\u0087\b¢\u0006\u0002\u0010,J'\u0010+\u001a\u00020\u001d\"\u0004\b\u0000\u0010\u00192\f\u0010'\u001a\b\u0012\u0004\u0012\u0002H\u00190(2\u0006\u0010)\u001a\u0002H\u0019¢\u0006\u0002\u0010-R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\u0002\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000R\u001c\u0010\u000f\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0002\u001a\u0004\b\u0011\u0010\u000bR\u001c\u0010\u0012\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0002\u001a\u0004\b\u0014\u0010\u000bR\u001c\u0010\u0015\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0002\u001a\u0004\b\u0017\u0010\u000b¨\u0006."}, d2 = {"Lkotlinx/serialization/json/Json$Default;", "Lkotlinx/serialization/StringFormat;", "()V", "context", "Lkotlinx/serialization/modules/SerialModule;", "getContext", "()Lkotlinx/serialization/modules/SerialModule;", "indented", "Lkotlinx/serialization/json/Json;", "indented$annotations", "getIndented", "()Lkotlinx/serialization/json/Json;", "jsonInstance", "message", "", "nonstrict", "nonstrict$annotations", "getNonstrict", "plain", "plain$annotations", "getPlain", "unquoted", "unquoted$annotations", "getUnquoted", "fromJson", ExifInterface.GPS_DIRECTION_TRUE, "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "json", "Lkotlinx/serialization/json/JsonElement;", "(Lkotlinx/serialization/DeserializationStrategy;Lkotlinx/serialization/json/JsonElement;)Ljava/lang/Object;", "", "tree", "(Lkotlinx/serialization/json/JsonElement;)Ljava/lang/Object;", "parse", "string", "(Lkotlinx/serialization/DeserializationStrategy;Ljava/lang/String;)Ljava/lang/Object;", "parseJson", "stringify", "serializer", "Lkotlinx/serialization/SerializationStrategy;", "value", "(Lkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)Ljava/lang/String;", "toJson", "(Ljava/lang/Object;)Lkotlinx/serialization/json/JsonElement;", "(Lkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)Lkotlinx/serialization/json/JsonElement;", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
    @UnstableDefault
    /* loaded from: classes4.dex */
    public static final class Default implements StringFormat {
        private Default() {
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = Json.message)
        @UnstableDefault
        public static /* synthetic */ void indented$annotations() {
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = Json.message)
        @UnstableDefault
        public static /* synthetic */ void nonstrict$annotations() {
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = Json.message)
        @UnstableDefault
        public static /* synthetic */ void plain$annotations() {
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = Json.message)
        @UnstableDefault
        public static /* synthetic */ void unquoted$annotations() {
        }

        public final <T> T fromJson(@NotNull DeserializationStrategy<T> deserializer, @NotNull JsonElement json) {
            Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
            Intrinsics.checkParameterIsNotNull(json, "json");
            return (T) TreeJsonInputKt.readJson(Json.jsonInstance, json, deserializer);
        }

        @Override // kotlinx.serialization.SerialFormat
        @NotNull
        public SerialModule getContext() {
            return Json.jsonInstance.getContext();
        }

        @NotNull
        public final Json getIndented() {
            return Json.indented;
        }

        @NotNull
        public final Json getNonstrict() {
            return Json.nonstrict;
        }

        @NotNull
        public final Json getPlain() {
            return Json.plain;
        }

        @NotNull
        public final Json getUnquoted() {
            return Json.unquoted;
        }

        @Override // kotlinx.serialization.StringFormat
        public <T> T parse(@NotNull DeserializationStrategy<T> deserializer, @NotNull String string) {
            Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
            Intrinsics.checkParameterIsNotNull(string, "string");
            return (T) Json.jsonInstance.parse(deserializer, string);
        }

        @NotNull
        public final JsonElement parseJson(@NotNull String string) {
            Intrinsics.checkParameterIsNotNull(string, "string");
            return (JsonElement) parse(JsonElementSerializer.INSTANCE, string);
        }

        @Override // kotlinx.serialization.StringFormat
        @NotNull
        public <T> String stringify(@NotNull SerializationStrategy<? super T> serializer, T t) {
            Intrinsics.checkParameterIsNotNull(serializer, "serializer");
            return Json.jsonInstance.stringify(serializer, t);
        }

        @NotNull
        public final <T> JsonElement toJson(@NotNull SerializationStrategy<? super T> serializer, T t) {
            Intrinsics.checkParameterIsNotNull(serializer, "serializer");
            return TreeJsonOutputKt.writeJson(Json.jsonInstance, t, serializer);
        }

        public /* synthetic */ Default(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @ImplicitReflectionSerializer
        @NotNull
        public final /* synthetic */ <T> T fromJson(@NotNull JsonElement tree) {
            Intrinsics.checkParameterIsNotNull(tree, "tree");
            SerialModule context = getContext();
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            return (T) fromJson(SerialModuleExtensionsKt.getContextualOrDefault(context, Reflection.getOrCreateKotlinClass(Object.class)), tree);
        }

        @ImplicitReflectionSerializer
        @NotNull
        public final /* synthetic */ <T> JsonElement toJson(@NotNull T value) {
            Intrinsics.checkParameterIsNotNull(value, "value");
            SerialModule context = getContext();
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            return toJson(SerialModuleExtensionsKt.getContextualOrDefault(context, Reflection.getOrCreateKotlinClass(Object.class)), value);
        }
    }

    public Json(@NotNull JsonConfiguration configuration, @NotNull SerialModule context) {
        Intrinsics.checkParameterIsNotNull(configuration, "configuration");
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.configuration = configuration;
        this.context$1 = SerialModuleExtensionsKt.plus(context, JsonKt.access$getDefaultJsonModule$p());
        validateConfiguration();
    }

    private final void validateConfiguration() {
        if (this.configuration.getUseArrayPolymorphism$kotlinx_serialization_runtime()) {
            return;
        }
        getContext().dumpTo(new ContextValidator(this.configuration.getClassDiscriminator$kotlinx_serialization_runtime()));
    }

    public final <T> T fromJson(@NotNull DeserializationStrategy<T> deserializer, @NotNull JsonElement json) {
        Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
        Intrinsics.checkParameterIsNotNull(json, "json");
        return (T) TreeJsonInputKt.readJson(this, json, deserializer);
    }

    @Override // kotlinx.serialization.SerialFormat
    @NotNull
    public SerialModule getContext() {
        return this.context$1;
    }

    @Override // kotlinx.serialization.StringFormat
    public <T> T parse(@NotNull DeserializationStrategy<T> deserializer, @NotNull String string) {
        Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
        Intrinsics.checkParameterIsNotNull(string, "string");
        JsonReader jsonReader = new JsonReader(string);
        T t = (T) DecodingKt.decode(new StreamingJsonInput(this, WriteMode.OBJ, jsonReader), deserializer);
        if (jsonReader.isDone()) {
            return t;
        }
        throw new IllegalStateException(("Reader has not consumed the whole input: " + jsonReader).toString());
    }

    @NotNull
    public final JsonElement parseJson(@NotNull String string) {
        Intrinsics.checkParameterIsNotNull(string, "string");
        return (JsonElement) parse(JsonElementSerializer.INSTANCE, string);
    }

    @Override // kotlinx.serialization.StringFormat
    @NotNull
    public <T> String stringify(@NotNull SerializationStrategy<? super T> serializer, T t) {
        Intrinsics.checkParameterIsNotNull(serializer, "serializer");
        StringBuilder sb = new StringBuilder();
        EncodingKt.encode(new StreamingJsonOutput(sb, this, WriteMode.OBJ, new JsonOutput[WriteMode.values().length]), serializer, t);
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "result.toString()");
        return sb2;
    }

    @NotNull
    public final <T> JsonElement toJson(@NotNull SerializationStrategy<? super T> serializer, T t) {
        Intrinsics.checkParameterIsNotNull(serializer, "serializer");
        return TreeJsonOutputKt.writeJson(this, t, serializer);
    }

    @ImplicitReflectionSerializer
    @NotNull
    public final /* synthetic */ <T> T fromJson(@NotNull JsonElement tree) {
        Intrinsics.checkParameterIsNotNull(tree, "tree");
        SerialModule context = getContext();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) fromJson(SerialModuleExtensionsKt.getContextualOrDefault(context, Reflection.getOrCreateKotlinClass(Object.class)), tree);
    }

    @ImplicitReflectionSerializer
    @NotNull
    public final /* synthetic */ <T> JsonElement toJson(@NotNull T value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        SerialModule context = getContext();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return toJson(SerialModuleExtensionsKt.getContextualOrDefault(context, Reflection.getOrCreateKotlinClass(Object.class)), value);
    }

    public /* synthetic */ Json(JsonConfiguration jsonConfiguration, SerialModule serialModule, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? JsonConfiguration.Companion.getStable() : jsonConfiguration, (i & 2) != 0 ? EmptyModule.INSTANCE : serialModule);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    @kotlinx.serialization.UnstableDefault
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public Json(@org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super kotlinx.serialization.json.JsonBuilder, kotlin.Unit> r2) {
        /*
            r1 = this;
            java.lang.String r0 = "block"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            kotlinx.serialization.json.JsonBuilder r0 = new kotlinx.serialization.json.JsonBuilder
            r0.<init>()
            r2.mo12165invoke(r0)
            r1.<init>(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.json.Json.<init>(kotlin.jvm.functions.Function1):void");
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Default constructor is deprecated, please specify the desired configuration explicitly or use Json(JsonConfiguration.Default)", replaceWith = @ReplaceWith(expression = "Json(JsonConfiguration.Default)", imports = {}))
    public Json() {
        this(new JsonConfiguration(false, false, false, false, false, false, false, null, true, null, null, 1791, null), null, 2, null);
    }

    private Json(JsonBuilder jsonBuilder) {
        this(jsonBuilder.buildConfiguration(), jsonBuilder.buildModule());
    }
}
