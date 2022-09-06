package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import kotlin.DeprecationLevel;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: VersionRequirement.kt */
/* loaded from: classes4.dex */
public final class VersionRequirement {
    public static final Companion Companion = new Companion(null);
    @Nullable
    private final Integer errorCode;
    @NotNull
    private final ProtoBuf.VersionRequirement.VersionKind kind;
    @NotNull
    private final DeprecationLevel level;
    @Nullable
    private final String message;
    @NotNull
    private final Version version;

    /* compiled from: VersionRequirement.kt */
    /* loaded from: classes4.dex */
    public static final class Companion {

        /* loaded from: classes4.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ProtoBuf.VersionRequirement.Level.values().length];

            static {
                $EnumSwitchMapping$0[ProtoBuf.VersionRequirement.Level.WARNING.ordinal()] = 1;
                $EnumSwitchMapping$0[ProtoBuf.VersionRequirement.Level.ERROR.ordinal()] = 2;
                $EnumSwitchMapping$0[ProtoBuf.VersionRequirement.Level.HIDDEN.ordinal()] = 3;
            }
        }

        private Companion() {
        }

        @NotNull
        public final List<VersionRequirement> create(@NotNull MessageLite proto, @NotNull NameResolver nameResolver, @NotNull VersionRequirementTable table) {
            List<Integer> ids;
            Intrinsics.checkParameterIsNotNull(proto, "proto");
            Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
            Intrinsics.checkParameterIsNotNull(table, "table");
            if (proto instanceof ProtoBuf.Class) {
                ids = ((ProtoBuf.Class) proto).getVersionRequirementList();
            } else if (proto instanceof ProtoBuf.Constructor) {
                ids = ((ProtoBuf.Constructor) proto).getVersionRequirementList();
            } else if (proto instanceof ProtoBuf.Function) {
                ids = ((ProtoBuf.Function) proto).getVersionRequirementList();
            } else if (proto instanceof ProtoBuf.Property) {
                ids = ((ProtoBuf.Property) proto).getVersionRequirementList();
            } else if (!(proto instanceof ProtoBuf.TypeAlias)) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unexpected declaration: ");
                outline107.append(proto.getClass());
                throw new IllegalStateException(outline107.toString());
            } else {
                ids = ((ProtoBuf.TypeAlias) proto).getVersionRequirementList();
            }
            Intrinsics.checkExpressionValueIsNotNull(ids, "ids");
            ArrayList arrayList = new ArrayList();
            for (Integer id : ids) {
                Companion companion = VersionRequirement.Companion;
                Intrinsics.checkExpressionValueIsNotNull(id, "id");
                VersionRequirement create = companion.create(id.intValue(), nameResolver, table);
                if (create != null) {
                    arrayList.add(create);
                }
            }
            return arrayList;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final VersionRequirement create(int i, @NotNull NameResolver nameResolver, @NotNull VersionRequirementTable table) {
            DeprecationLevel deprecationLevel;
            Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
            Intrinsics.checkParameterIsNotNull(table, "table");
            ProtoBuf.VersionRequirement versionRequirement = table.get(i);
            String str = null;
            if (versionRequirement != null) {
                Version decode = Version.Companion.decode(versionRequirement.hasVersion() ? Integer.valueOf(versionRequirement.getVersion()) : null, versionRequirement.hasVersionFull() ? Integer.valueOf(versionRequirement.getVersionFull()) : null);
                ProtoBuf.VersionRequirement.Level level = versionRequirement.getLevel();
                if (level == null) {
                    Intrinsics.throwNpe();
                }
                int i2 = WhenMappings.$EnumSwitchMapping$0[level.ordinal()];
                if (i2 == 1) {
                    deprecationLevel = DeprecationLevel.WARNING;
                } else if (i2 == 2) {
                    deprecationLevel = DeprecationLevel.ERROR;
                } else if (i2 != 3) {
                    throw new NoWhenBranchMatchedException();
                } else {
                    deprecationLevel = DeprecationLevel.HIDDEN;
                }
                DeprecationLevel deprecationLevel2 = deprecationLevel;
                Integer valueOf = versionRequirement.hasErrorCode() ? Integer.valueOf(versionRequirement.getErrorCode()) : null;
                if (versionRequirement.hasMessage()) {
                    str = nameResolver.getString(versionRequirement.getMessage());
                }
                ProtoBuf.VersionRequirement.VersionKind versionKind = versionRequirement.getVersionKind();
                Intrinsics.checkExpressionValueIsNotNull(versionKind, "info.versionKind");
                return new VersionRequirement(decode, versionKind, deprecationLevel2, valueOf, str);
            }
            return null;
        }
    }

    /* compiled from: VersionRequirement.kt */
    /* loaded from: classes4.dex */
    public static final class Version {
        public static final Companion Companion = new Companion(null);
        @JvmField
        @NotNull
        public static final Version INFINITY = new Version(256, 256, 256);
        private final int major;
        private final int minor;
        private final int patch;

        /* compiled from: VersionRequirement.kt */
        /* loaded from: classes4.dex */
        public static final class Companion {
            private Companion() {
            }

            @NotNull
            public final Version decode(@Nullable Integer num, @Nullable Integer num2) {
                if (num2 != null) {
                    return new Version(num2.intValue() & 255, (num2.intValue() >> 8) & 255, (num2.intValue() >> 16) & 255);
                }
                if (num != null) {
                    return new Version(num.intValue() & 7, (num.intValue() >> 3) & 15, (num.intValue() >> 7) & 127);
                }
                return Version.INFINITY;
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        public Version(int i, int i2, int i3) {
            this.major = i;
            this.minor = i2;
            this.patch = i3;
        }

        @NotNull
        public final String asString() {
            StringBuilder sb;
            int i;
            if (this.patch == 0) {
                sb = new StringBuilder();
                sb.append(this.major);
                sb.append('.');
                i = this.minor;
            } else {
                sb = new StringBuilder();
                sb.append(this.major);
                sb.append('.');
                sb.append(this.minor);
                sb.append('.');
                i = this.patch;
            }
            sb.append(i);
            return sb.toString();
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (!(obj instanceof Version)) {
                    return false;
                }
                Version version = (Version) obj;
                return this.major == version.major && this.minor == version.minor && this.patch == version.patch;
            }
            return true;
        }

        public int hashCode() {
            return (((this.major * 31) + this.minor) * 31) + this.patch;
        }

        @NotNull
        public String toString() {
            return asString();
        }

        public /* synthetic */ Version(int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, i2, (i4 & 4) != 0 ? 0 : i3);
        }
    }

    public VersionRequirement(@NotNull Version version, @NotNull ProtoBuf.VersionRequirement.VersionKind kind, @NotNull DeprecationLevel level, @Nullable Integer num, @Nullable String str) {
        Intrinsics.checkParameterIsNotNull(version, "version");
        Intrinsics.checkParameterIsNotNull(kind, "kind");
        Intrinsics.checkParameterIsNotNull(level, "level");
        this.version = version;
        this.kind = kind;
        this.level = level;
        this.errorCode = num;
        this.message = str;
    }

    @NotNull
    public final ProtoBuf.VersionRequirement.VersionKind getKind() {
        return this.kind;
    }

    @NotNull
    public final Version getVersion() {
        return this.version;
    }

    @NotNull
    public String toString() {
        String str;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("since ");
        outline107.append(this.version);
        outline107.append(Chars.SPACE);
        outline107.append(this.level);
        String str2 = "";
        if (this.errorCode != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(" error ");
            outline1072.append(this.errorCode);
            str = outline1072.toString();
        } else {
            str = str2;
        }
        outline107.append(str);
        if (this.message != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107(RealTimeTextConstants.COLON_SPACE);
            outline1073.append(this.message);
            str2 = outline1073.toString();
        }
        outline107.append(str2);
        return outline107.toString();
    }
}
