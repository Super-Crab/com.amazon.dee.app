package kotlinx.serialization.json;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.UnstableDefault;
import kotlinx.serialization.UpdateMode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: JsonConfiguration.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b+\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 =2\u00020\u0001:\u0001=Bu\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\u000e\u0010!\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b\"J\u000e\u0010#\u001a\u00020\u000bHÀ\u0003¢\u0006\u0002\b$J\u000e\u0010%\u001a\u00020\u000fHÀ\u0003¢\u0006\u0002\b&J\u000e\u0010'\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b(J\u000e\u0010)\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b*J\u000e\u0010+\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b,J\u000e\u0010-\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b.J\u000e\u0010/\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b0J\u000e\u00101\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b2J\u000e\u00103\u001a\u00020\u000bHÀ\u0003¢\u0006\u0002\b4J\u000e\u00105\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b6Jw\u00107\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000fHÆ\u0001J\u0013\u00108\u001a\u00020\u00032\b\u00109\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010:\u001a\u00020;HÖ\u0001J\t\u0010<\u001a\u00020\u000bHÖ\u0001R\u0014\u0010\u0007\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\r\u001a\u00020\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0014\u0010\u0004\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0014\u0010\n\u001a\u00020\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0014\u0010\u0005\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u0014\u0010\b\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012R\u0014\u0010\u0006\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012R\u0014\u0010\t\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012R\u001c\u0010\u000e\u001a\u00020\u000f8\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010\f\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0012¨\u0006>"}, d2 = {"Lkotlinx/serialization/json/JsonConfiguration;", "", "encodeDefaults", "", "ignoreUnknownKeys", "isLenient", "serializeSpecialFloatingPointValues", "allowStructuredMapKeys", "prettyPrint", "unquotedPrint", "indent", "", "useArrayPolymorphism", "classDiscriminator", "updateMode", "Lkotlinx/serialization/UpdateMode;", "(ZZZZZZZLjava/lang/String;ZLjava/lang/String;Lkotlinx/serialization/UpdateMode;)V", "getAllowStructuredMapKeys$kotlinx_serialization_runtime", "()Z", "getClassDiscriminator$kotlinx_serialization_runtime", "()Ljava/lang/String;", "getEncodeDefaults$kotlinx_serialization_runtime", "getIgnoreUnknownKeys$kotlinx_serialization_runtime", "getIndent$kotlinx_serialization_runtime", "isLenient$kotlinx_serialization_runtime", "getPrettyPrint$kotlinx_serialization_runtime", "getSerializeSpecialFloatingPointValues$kotlinx_serialization_runtime", "getUnquotedPrint$kotlinx_serialization_runtime", "updateMode$annotations", "()V", "getUpdateMode$kotlinx_serialization_runtime", "()Lkotlinx/serialization/UpdateMode;", "getUseArrayPolymorphism$kotlinx_serialization_runtime", "component1", "component1$kotlinx_serialization_runtime", "component10", "component10$kotlinx_serialization_runtime", "component11", "component11$kotlinx_serialization_runtime", "component2", "component2$kotlinx_serialization_runtime", "component3", "component3$kotlinx_serialization_runtime", "component4", "component4$kotlinx_serialization_runtime", "component5", "component5$kotlinx_serialization_runtime", "component6", "component6$kotlinx_serialization_runtime", "component7", "component7$kotlinx_serialization_runtime", "component8", "component8$kotlinx_serialization_runtime", "component9", "component9$kotlinx_serialization_runtime", "copy", "equals", "other", "hashCode", "", "toString", "Companion", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class JsonConfiguration {
    private final boolean allowStructuredMapKeys;
    @NotNull
    private final String classDiscriminator;
    private final boolean encodeDefaults;
    private final boolean ignoreUnknownKeys;
    @NotNull
    private final String indent;
    private final boolean isLenient;
    private final boolean prettyPrint;
    private final boolean serializeSpecialFloatingPointValues;
    private final boolean unquotedPrint;
    @NotNull
    private final UpdateMode updateMode;
    private final boolean useArrayPolymorphism;
    public static final Companion Companion = new Companion(null);
    private static final String defaultIndent = "    ";
    private static final String defaultDiscriminator = "type";
    @NotNull
    private static final JsonConfiguration Default = new JsonConfiguration(false, false, false, false, false, false, false, null, false, null, null, 2047, null);
    @NotNull
    private static final JsonConfiguration Stable = new JsonConfiguration(true, false, false, false, true, false, false, defaultIndent, false, defaultDiscriminator, null, 1024, null);

    /* compiled from: JsonConfiguration.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\u0002\u001a\u0004\b\n\u0010\u0007R\u0016\u0010\u000b\u001a\u00020\f8\u0002X\u0083D¢\u0006\b\n\u0000\u0012\u0004\b\r\u0010\u0002R\u0016\u0010\u000e\u001a\u00020\f8\u0002X\u0083D¢\u0006\b\n\u0000\u0012\u0004\b\u000f\u0010\u0002¨\u0006\u0010"}, d2 = {"Lkotlinx/serialization/json/JsonConfiguration$Companion;", "", "()V", "Default", "Lkotlinx/serialization/json/JsonConfiguration;", "Default$annotations", "getDefault", "()Lkotlinx/serialization/json/JsonConfiguration;", "Stable", "Stable$annotations", "getStable", "defaultDiscriminator", "", "defaultDiscriminator$annotations", "defaultIndent", "defaultIndent$annotations", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @UnstableDefault
        public static /* synthetic */ void Default$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void Stable$annotations() {
        }

        @JvmStatic
        private static /* synthetic */ void defaultDiscriminator$annotations() {
        }

        @JvmStatic
        private static /* synthetic */ void defaultIndent$annotations() {
        }

        @NotNull
        public final JsonConfiguration getDefault() {
            return JsonConfiguration.Default;
        }

        @NotNull
        public final JsonConfiguration getStable() {
            return JsonConfiguration.Stable;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @UnstableDefault
    public JsonConfiguration() {
        this(false, false, false, false, false, false, false, null, false, null, null, 2047, null);
    }

    @UnstableDefault
    public JsonConfiguration(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, @NotNull String indent, boolean z8, @NotNull String classDiscriminator, @NotNull UpdateMode updateMode) {
        Intrinsics.checkParameterIsNotNull(indent, "indent");
        Intrinsics.checkParameterIsNotNull(classDiscriminator, "classDiscriminator");
        Intrinsics.checkParameterIsNotNull(updateMode, "updateMode");
        this.encodeDefaults = z;
        this.ignoreUnknownKeys = z2;
        this.isLenient = z3;
        this.serializeSpecialFloatingPointValues = z4;
        this.allowStructuredMapKeys = z5;
        this.prettyPrint = z6;
        this.unquotedPrint = z7;
        this.indent = indent;
        this.useArrayPolymorphism = z8;
        this.classDiscriminator = classDiscriminator;
        this.updateMode = updateMode;
        if (!this.useArrayPolymorphism || Intrinsics.areEqual(this.classDiscriminator, defaultDiscriminator)) {
            if (!this.prettyPrint && !Intrinsics.areEqual(this.indent, defaultIndent)) {
                throw new IllegalArgumentException("Indent should not be specified when default printing mode is used".toString());
            }
            return;
        }
        throw new IllegalArgumentException("Class discriminator should not be specified when array polymorphism is specified".toString());
    }

    @NotNull
    public static final JsonConfiguration getDefault() {
        return Default;
    }

    @NotNull
    public static final JsonConfiguration getStable() {
        return Stable;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Custom update modes are not fully supported")
    public static /* synthetic */ void updateMode$annotations() {
    }

    public final boolean component1$kotlinx_serialization_runtime() {
        return this.encodeDefaults;
    }

    @NotNull
    public final String component10$kotlinx_serialization_runtime() {
        return this.classDiscriminator;
    }

    @NotNull
    public final UpdateMode component11$kotlinx_serialization_runtime() {
        return this.updateMode;
    }

    public final boolean component2$kotlinx_serialization_runtime() {
        return this.ignoreUnknownKeys;
    }

    public final boolean component3$kotlinx_serialization_runtime() {
        return this.isLenient;
    }

    public final boolean component4$kotlinx_serialization_runtime() {
        return this.serializeSpecialFloatingPointValues;
    }

    public final boolean component5$kotlinx_serialization_runtime() {
        return this.allowStructuredMapKeys;
    }

    public final boolean component6$kotlinx_serialization_runtime() {
        return this.prettyPrint;
    }

    public final boolean component7$kotlinx_serialization_runtime() {
        return this.unquotedPrint;
    }

    @NotNull
    public final String component8$kotlinx_serialization_runtime() {
        return this.indent;
    }

    public final boolean component9$kotlinx_serialization_runtime() {
        return this.useArrayPolymorphism;
    }

    @NotNull
    public final JsonConfiguration copy(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, @NotNull String indent, boolean z8, @NotNull String classDiscriminator, @NotNull UpdateMode updateMode) {
        Intrinsics.checkParameterIsNotNull(indent, "indent");
        Intrinsics.checkParameterIsNotNull(classDiscriminator, "classDiscriminator");
        Intrinsics.checkParameterIsNotNull(updateMode, "updateMode");
        return new JsonConfiguration(z, z2, z3, z4, z5, z6, z7, indent, z8, classDiscriminator, updateMode);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof JsonConfiguration)) {
                return false;
            }
            JsonConfiguration jsonConfiguration = (JsonConfiguration) obj;
            return this.encodeDefaults == jsonConfiguration.encodeDefaults && this.ignoreUnknownKeys == jsonConfiguration.ignoreUnknownKeys && this.isLenient == jsonConfiguration.isLenient && this.serializeSpecialFloatingPointValues == jsonConfiguration.serializeSpecialFloatingPointValues && this.allowStructuredMapKeys == jsonConfiguration.allowStructuredMapKeys && this.prettyPrint == jsonConfiguration.prettyPrint && this.unquotedPrint == jsonConfiguration.unquotedPrint && Intrinsics.areEqual(this.indent, jsonConfiguration.indent) && this.useArrayPolymorphism == jsonConfiguration.useArrayPolymorphism && Intrinsics.areEqual(this.classDiscriminator, jsonConfiguration.classDiscriminator) && Intrinsics.areEqual(this.updateMode, jsonConfiguration.updateMode);
        }
        return true;
    }

    public final boolean getAllowStructuredMapKeys$kotlinx_serialization_runtime() {
        return this.allowStructuredMapKeys;
    }

    @NotNull
    public final String getClassDiscriminator$kotlinx_serialization_runtime() {
        return this.classDiscriminator;
    }

    public final boolean getEncodeDefaults$kotlinx_serialization_runtime() {
        return this.encodeDefaults;
    }

    public final boolean getIgnoreUnknownKeys$kotlinx_serialization_runtime() {
        return this.ignoreUnknownKeys;
    }

    @NotNull
    public final String getIndent$kotlinx_serialization_runtime() {
        return this.indent;
    }

    public final boolean getPrettyPrint$kotlinx_serialization_runtime() {
        return this.prettyPrint;
    }

    public final boolean getSerializeSpecialFloatingPointValues$kotlinx_serialization_runtime() {
        return this.serializeSpecialFloatingPointValues;
    }

    public final boolean getUnquotedPrint$kotlinx_serialization_runtime() {
        return this.unquotedPrint;
    }

    @NotNull
    public final UpdateMode getUpdateMode$kotlinx_serialization_runtime() {
        return this.updateMode;
    }

    public final boolean getUseArrayPolymorphism$kotlinx_serialization_runtime() {
        return this.useArrayPolymorphism;
    }

    public int hashCode() {
        boolean z = this.encodeDefaults;
        int i = 1;
        if (z) {
            z = true;
        }
        int i2 = z ? 1 : 0;
        int i3 = z ? 1 : 0;
        int i4 = i2 * 31;
        boolean z2 = this.ignoreUnknownKeys;
        if (z2) {
            z2 = true;
        }
        int i5 = z2 ? 1 : 0;
        int i6 = z2 ? 1 : 0;
        int i7 = (i4 + i5) * 31;
        boolean z3 = this.isLenient;
        if (z3) {
            z3 = true;
        }
        int i8 = z3 ? 1 : 0;
        int i9 = z3 ? 1 : 0;
        int i10 = (i7 + i8) * 31;
        boolean z4 = this.serializeSpecialFloatingPointValues;
        if (z4) {
            z4 = true;
        }
        int i11 = z4 ? 1 : 0;
        int i12 = z4 ? 1 : 0;
        int i13 = (i10 + i11) * 31;
        boolean z5 = this.allowStructuredMapKeys;
        if (z5) {
            z5 = true;
        }
        int i14 = z5 ? 1 : 0;
        int i15 = z5 ? 1 : 0;
        int i16 = (i13 + i14) * 31;
        boolean z6 = this.prettyPrint;
        if (z6) {
            z6 = true;
        }
        int i17 = z6 ? 1 : 0;
        int i18 = z6 ? 1 : 0;
        int i19 = (i16 + i17) * 31;
        boolean z7 = this.unquotedPrint;
        if (z7) {
            z7 = true;
        }
        int i20 = z7 ? 1 : 0;
        int i21 = z7 ? 1 : 0;
        int i22 = (i19 + i20) * 31;
        String str = this.indent;
        int i23 = 0;
        int hashCode = (i22 + (str != null ? str.hashCode() : 0)) * 31;
        boolean z8 = this.useArrayPolymorphism;
        if (!z8) {
            i = z8 ? 1 : 0;
        }
        int i24 = (hashCode + i) * 31;
        String str2 = this.classDiscriminator;
        int hashCode2 = (i24 + (str2 != null ? str2.hashCode() : 0)) * 31;
        UpdateMode updateMode = this.updateMode;
        if (updateMode != null) {
            i23 = updateMode.hashCode();
        }
        return hashCode2 + i23;
    }

    public final boolean isLenient$kotlinx_serialization_runtime() {
        return this.isLenient;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("JsonConfiguration(encodeDefaults=");
        outline107.append(this.encodeDefaults);
        outline107.append(", ignoreUnknownKeys=");
        outline107.append(this.ignoreUnknownKeys);
        outline107.append(", isLenient=");
        outline107.append(this.isLenient);
        outline107.append(", serializeSpecialFloatingPointValues=");
        outline107.append(this.serializeSpecialFloatingPointValues);
        outline107.append(", allowStructuredMapKeys=");
        outline107.append(this.allowStructuredMapKeys);
        outline107.append(", prettyPrint=");
        outline107.append(this.prettyPrint);
        outline107.append(", unquotedPrint=");
        outline107.append(this.unquotedPrint);
        outline107.append(", indent=");
        outline107.append(this.indent);
        outline107.append(", useArrayPolymorphism=");
        outline107.append(this.useArrayPolymorphism);
        outline107.append(", classDiscriminator=");
        outline107.append(this.classDiscriminator);
        outline107.append(", updateMode=");
        outline107.append(this.updateMode);
        outline107.append(")");
        return outline107.toString();
    }

    public /* synthetic */ JsonConfiguration(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, String str, boolean z8, String str2, UpdateMode updateMode, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z, (i & 2) != 0 ? false : z2, (i & 4) != 0 ? false : z3, (i & 8) != 0 ? false : z4, (i & 16) != 0 ? false : z5, (i & 32) != 0 ? false : z6, (i & 64) != 0 ? false : z7, (i & 128) != 0 ? defaultIndent : str, (i & 256) == 0 ? z8 : false, (i & 512) != 0 ? defaultDiscriminator : str2, (i & 1024) != 0 ? UpdateMode.OVERWRITE : updateMode);
    }
}
