package org.apache.commons.lang3.reflect;

import com.drew.metadata.iptc.IptcDirectory;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import org.apache.commons.lang3.Validate;
/* loaded from: classes4.dex */
public abstract class TypeLiteral<T> implements Typed<T> {
    private static final TypeVariable<Class<TypeLiteral>> T = TypeLiteral.class.getTypeParameters()[0];
    public final Type value = (Type) Validate.notNull(TypeUtils.getTypeArguments(TypeLiteral.class, TypeLiteral.class).get(T), "%s does not assign type parameter %s", TypeLiteral.class, TypeUtils.toLongString(T));
    private final String toString = String.format("%s<%s>", TypeLiteral.class.getSimpleName(), TypeUtils.toString(this.value));

    protected TypeLiteral() {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TypeLiteral) {
            return TypeUtils.equals(this.value, ((TypeLiteral) obj).value);
        }
        return false;
    }

    @Override // org.apache.commons.lang3.reflect.Typed
    public Type getType() {
        return this.value;
    }

    public int hashCode() {
        return this.value.hashCode() | IptcDirectory.TAG_BY_LINE;
    }

    public String toString() {
        return this.toString;
    }
}
