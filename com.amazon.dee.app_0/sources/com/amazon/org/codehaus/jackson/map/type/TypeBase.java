package com.amazon.org.codehaus.jackson.map.type;

import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.map.JsonSerializableWithType;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.TypeSerializer;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
/* loaded from: classes13.dex */
public abstract class TypeBase extends JavaType implements JsonSerializableWithType {
    volatile String _canonicalName;

    @Deprecated
    protected TypeBase(Class<?> cls, int i) {
        super(cls, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static StringBuilder _classSignature(Class<?> cls, StringBuilder sb, boolean z) {
        if (cls.isPrimitive()) {
            if (cls == Boolean.TYPE) {
                sb.append(Matrix.MATRIX_TYPE_ZERO);
            } else if (cls == Byte.TYPE) {
                sb.append('B');
            } else if (cls == Short.TYPE) {
                sb.append('S');
            } else if (cls == Character.TYPE) {
                sb.append('C');
            } else if (cls == Integer.TYPE) {
                sb.append('I');
            } else if (cls == Long.TYPE) {
                sb.append('J');
            } else if (cls == Float.TYPE) {
                sb.append('F');
            } else if (cls == Double.TYPE) {
                sb.append('D');
            } else if (cls == Void.TYPE) {
                sb.append('V');
            } else {
                throw new IllegalStateException(GeneratedOutlineSupport1.outline38(cls, GeneratedOutlineSupport1.outline107("Unrecognized primitive type: ")));
            }
        } else {
            sb.append(Matrix.MATRIX_TYPE_RANDOM_LT);
            String name = cls.getName();
            int length = name.length();
            for (int i = 0; i < length; i++) {
                char charAt = name.charAt(i);
                if (charAt == '.') {
                    charAt = '/';
                }
                sb.append(charAt);
            }
            if (z) {
                sb.append(';');
            }
        }
        return sb;
    }

    protected abstract String buildCanonicalName();

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public abstract StringBuilder getErasedSignature(StringBuilder sb);

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public abstract StringBuilder getGenericSignature(StringBuilder sb);

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public <T> T getTypeHandler() {
        return (T) this._typeHandler;
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public <T> T getValueHandler() {
        return (T) this._valueHandler;
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonSerializable
    public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeString(toCanonical());
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonSerializableWithType
    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonProcessingException {
        typeSerializer.writeTypePrefixForScalar(this, jsonGenerator);
        serialize(jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffixForScalar(this, jsonGenerator);
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public String toCanonical() {
        String str = this._canonicalName;
        return str == null ? buildCanonicalName() : str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TypeBase(Class<?> cls, int i, Object obj, Object obj2) {
        super(cls, i);
        this._valueHandler = obj;
        this._typeHandler = obj2;
    }
}
