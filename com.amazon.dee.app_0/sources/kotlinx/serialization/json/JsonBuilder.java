package kotlinx.serialization.json;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.UnstableDefault;
import kotlinx.serialization.modules.EmptyModule;
import kotlinx.serialization.modules.SerialModule;
import org.jetbrains.annotations.NotNull;
/* compiled from: Json.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u00104\u001a\u000205J\u0006\u00106\u001a\u00020\u001eR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000eR\u001a\u0010\u0018\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0006\"\u0004\b\u0019\u0010\bR\u001a\u0010\u001a\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0006\"\u0004\b\u001c\u0010\bR\u001a\u0010\u001d\u001a\u00020\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0006\"\u0004\b%\u0010\bR$\u0010&\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b'\u0010\u0002\u001a\u0004\b(\u0010\u0006\"\u0004\b)\u0010\bR$\u0010*\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b+\u0010\u0002\u001a\u0004\b,\u0010\u0006\"\u0004\b-\u0010\bR\u001a\u0010.\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0006\"\u0004\b0\u0010\bR\u001a\u00101\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0006\"\u0004\b3\u0010\b¨\u00067"}, d2 = {"Lkotlinx/serialization/json/JsonBuilder;", "", "()V", "allowStructuredMapKeys", "", "getAllowStructuredMapKeys", "()Z", "setAllowStructuredMapKeys", "(Z)V", "classDiscriminator", "", "getClassDiscriminator", "()Ljava/lang/String;", "setClassDiscriminator", "(Ljava/lang/String;)V", "encodeDefaults", "getEncodeDefaults", "setEncodeDefaults", "ignoreUnknownKeys", "getIgnoreUnknownKeys", "setIgnoreUnknownKeys", "indent", "getIndent", "setIndent", "isLenient", "setLenient", "prettyPrint", "getPrettyPrint", "setPrettyPrint", "serialModule", "Lkotlinx/serialization/modules/SerialModule;", "getSerialModule", "()Lkotlinx/serialization/modules/SerialModule;", "setSerialModule", "(Lkotlinx/serialization/modules/SerialModule;)V", "serializeSpecialFloatingPointValues", "getSerializeSpecialFloatingPointValues", "setSerializeSpecialFloatingPointValues", "strictMode", "strictMode$annotations", "getStrictMode", "setStrictMode", "unquoted", "unquoted$annotations", "getUnquoted", "setUnquoted", "unquotedPrint", "getUnquotedPrint", "setUnquotedPrint", "useArrayPolymorphism", "getUseArrayPolymorphism", "setUseArrayPolymorphism", "buildConfiguration", "Lkotlinx/serialization/json/JsonConfiguration;", "buildModule", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
@UnstableDefault
/* loaded from: classes4.dex */
public final class JsonBuilder {
    private boolean allowStructuredMapKeys;
    private boolean ignoreUnknownKeys;
    private boolean isLenient;
    private boolean prettyPrint;
    private boolean serializeSpecialFloatingPointValues;
    private boolean unquoted;
    private boolean unquotedPrint;
    private boolean useArrayPolymorphism;
    private boolean encodeDefaults = true;
    private boolean strictMode = true;
    @NotNull
    private String indent = "    ";
    @NotNull
    private String classDiscriminator = "type";
    @NotNull
    private SerialModule serialModule = EmptyModule.INSTANCE;

    @Deprecated(level = DeprecationLevel.ERROR, message = "'strictMode = true' is replaced with 3 new configuration parameters: 'ignoreUnknownKeys = false' to fail if an unknown key is encountered, 'serializeSpecialFloatingPointValues = false' to fail on 'NaN' and 'Infinity' values, 'isLenient = false' to prohibit parsing of any non-compliant or malformed JSON")
    public static /* synthetic */ void strictMode$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "'unquoted' is deprecated in the favour of 'unquotedPrint'", replaceWith = @ReplaceWith(expression = "unquotedPrint", imports = {}))
    public static /* synthetic */ void unquoted$annotations() {
    }

    @NotNull
    public final JsonConfiguration buildConfiguration() {
        return new JsonConfiguration(this.encodeDefaults, this.ignoreUnknownKeys, this.isLenient, this.serializeSpecialFloatingPointValues, this.allowStructuredMapKeys, this.prettyPrint, this.unquotedPrint, this.indent, this.useArrayPolymorphism, this.classDiscriminator, null, 1024, null);
    }

    @NotNull
    public final SerialModule buildModule() {
        return this.serialModule;
    }

    public final boolean getAllowStructuredMapKeys() {
        return this.allowStructuredMapKeys;
    }

    @NotNull
    public final String getClassDiscriminator() {
        return this.classDiscriminator;
    }

    public final boolean getEncodeDefaults() {
        return this.encodeDefaults;
    }

    public final boolean getIgnoreUnknownKeys() {
        return this.ignoreUnknownKeys;
    }

    @NotNull
    public final String getIndent() {
        return this.indent;
    }

    public final boolean getPrettyPrint() {
        return this.prettyPrint;
    }

    @NotNull
    public final SerialModule getSerialModule() {
        return this.serialModule;
    }

    public final boolean getSerializeSpecialFloatingPointValues() {
        return this.serializeSpecialFloatingPointValues;
    }

    public final boolean getStrictMode() {
        return this.strictMode;
    }

    public final boolean getUnquoted() {
        return this.unquoted;
    }

    public final boolean getUnquotedPrint() {
        return this.unquotedPrint;
    }

    public final boolean getUseArrayPolymorphism() {
        return this.useArrayPolymorphism;
    }

    public final boolean isLenient() {
        return this.isLenient;
    }

    public final void setAllowStructuredMapKeys(boolean z) {
        this.allowStructuredMapKeys = z;
    }

    public final void setClassDiscriminator(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.classDiscriminator = str;
    }

    public final void setEncodeDefaults(boolean z) {
        this.encodeDefaults = z;
    }

    public final void setIgnoreUnknownKeys(boolean z) {
        this.ignoreUnknownKeys = z;
    }

    public final void setIndent(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.indent = str;
    }

    public final void setLenient(boolean z) {
        this.isLenient = z;
    }

    public final void setPrettyPrint(boolean z) {
        this.prettyPrint = z;
    }

    public final void setSerialModule(@NotNull SerialModule serialModule) {
        Intrinsics.checkParameterIsNotNull(serialModule, "<set-?>");
        this.serialModule = serialModule;
    }

    public final void setSerializeSpecialFloatingPointValues(boolean z) {
        this.serializeSpecialFloatingPointValues = z;
    }

    public final void setStrictMode(boolean z) {
        this.strictMode = z;
    }

    public final void setUnquoted(boolean z) {
        this.unquoted = z;
    }

    public final void setUnquotedPrint(boolean z) {
        this.unquotedPrint = z;
    }

    public final void setUseArrayPolymorphism(boolean z) {
        this.useArrayPolymorphism = z;
    }
}
