package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public enum PrimitiveType {
    BOOLEAN("Boolean"),
    CHAR("Char"),
    BYTE("Byte"),
    SHORT("Short"),
    INT("Int"),
    FLOAT("Float"),
    LONG("Long"),
    DOUBLE("Double");
    
    private final Name arrayTypeName;
    private final Name typeName;
    public static final Set<PrimitiveType> NUMBER_TYPES = Collections.unmodifiableSet(EnumSet.of(CHAR, BYTE, SHORT, INT, FLOAT, LONG, DOUBLE));
    private FqName typeFqName = null;
    private FqName arrayTypeFqName = null;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[2];
        objArr[0] = "kotlin/reflect/jvm/internal/impl/builtins/PrimitiveType";
        if (i == 1 || i == 2) {
            objArr[1] = "getTypeFqName";
        } else if (i == 3) {
            objArr[1] = "getArrayTypeName";
        } else if (i == 4 || i == 5) {
            objArr[1] = "getArrayTypeFqName";
        } else {
            objArr[1] = "getTypeName";
        }
        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", objArr));
    }

    PrimitiveType(String str) {
        this.typeName = Name.identifier(str);
        this.arrayTypeName = Name.identifier(str + "Array");
    }

    @NotNull
    public FqName getArrayTypeFqName() {
        FqName fqName = this.arrayTypeFqName;
        if (fqName != null) {
            if (fqName == null) {
                $$$reportNull$$$0(4);
            }
            return fqName;
        }
        this.arrayTypeFqName = KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAME.child(this.arrayTypeName);
        FqName fqName2 = this.arrayTypeFqName;
        if (fqName2 == null) {
            $$$reportNull$$$0(5);
        }
        return fqName2;
    }

    @NotNull
    public Name getArrayTypeName() {
        Name name = this.arrayTypeName;
        if (name == null) {
            $$$reportNull$$$0(3);
        }
        return name;
    }

    @NotNull
    public FqName getTypeFqName() {
        FqName fqName = this.typeFqName;
        if (fqName != null) {
            if (fqName == null) {
                $$$reportNull$$$0(1);
            }
            return fqName;
        }
        this.typeFqName = KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAME.child(this.typeName);
        FqName fqName2 = this.typeFqName;
        if (fqName2 == null) {
            $$$reportNull$$$0(2);
        }
        return fqName2;
    }

    @NotNull
    public Name getTypeName() {
        Name name = this.typeName;
        if (name == null) {
            $$$reportNull$$$0(0);
        }
        return name;
    }
}
