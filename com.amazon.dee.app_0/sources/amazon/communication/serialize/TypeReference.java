package amazon.communication.serialize;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
/* loaded from: classes.dex */
public abstract class TypeReference<T> implements Comparable<TypeReference<T>> {
    final Type _type;

    @FireOsSdk
    protected TypeReference() {
        Type genericSuperclass = TypeReference.class.getGenericSuperclass();
        if (!(genericSuperclass instanceof Class)) {
            this._type = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
            return;
        }
        throw new IllegalArgumentException("Internal error: TypeReference constructed without actual type information");
    }

    @FireOsSdk
    public int compareTo(TypeReference<T> typeReference) {
        return 0;
    }

    @Override // java.lang.Comparable
    @FireOsSdk
    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return compareTo((TypeReference) ((TypeReference) obj));
    }

    @FireOsSdk
    public Type getType() {
        return this._type;
    }
}
