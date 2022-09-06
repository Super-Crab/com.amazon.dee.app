package kotlin.reflect.jvm.internal.impl.builtins;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;
/* JADX WARN: Init of enum UBYTE can be incorrect */
/* JADX WARN: Init of enum UINT can be incorrect */
/* JADX WARN: Init of enum ULONG can be incorrect */
/* JADX WARN: Init of enum USHORT can be incorrect */
/* compiled from: UnsignedType.kt */
/* loaded from: classes2.dex */
public enum UnsignedType {
    UBYTE(r2),
    USHORT(r2),
    UINT(r2),
    ULONG(r2);
    
    @NotNull
    private final ClassId arrayClassId;
    @NotNull
    private final ClassId classId;
    @NotNull
    private final Name typeName;

    static {
        Intrinsics.checkExpressionValueIsNotNull(ClassId.fromString("kotlin/UByte"), "ClassId.fromString(\"kotlin/UByte\")");
        Intrinsics.checkExpressionValueIsNotNull(ClassId.fromString("kotlin/UShort"), "ClassId.fromString(\"kotlin/UShort\")");
        Intrinsics.checkExpressionValueIsNotNull(ClassId.fromString("kotlin/UInt"), "ClassId.fromString(\"kotlin/UInt\")");
        Intrinsics.checkExpressionValueIsNotNull(ClassId.fromString("kotlin/ULong"), "ClassId.fromString(\"kotlin/ULong\")");
    }

    UnsignedType(ClassId classId) {
        this.classId = classId;
        Name shortClassName = this.classId.getShortClassName();
        Intrinsics.checkExpressionValueIsNotNull(shortClassName, "classId.shortClassName");
        this.typeName = shortClassName;
        FqName packageFqName = this.classId.getPackageFqName();
        this.arrayClassId = new ClassId(packageFqName, Name.identifier(this.typeName.asString() + "Array"));
    }

    @NotNull
    public final ClassId getArrayClassId() {
        return this.arrayClassId;
    }

    @NotNull
    public final ClassId getClassId() {
        return this.classId;
    }

    @NotNull
    public final Name getTypeName() {
        return this.typeName;
    }
}
