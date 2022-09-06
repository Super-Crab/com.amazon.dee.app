package com.esotericsoftware.kryo.serializers;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import com.esotericsoftware.minlog.Log;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
/* loaded from: classes2.dex */
public class TaggedFieldSerializer<T> extends FieldSerializer<T> {
    private boolean[] deprecated;
    private int[] tags;
    private int writeFieldCount;

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: classes2.dex */
    public @interface Tag {
        int value();
    }

    public TaggedFieldSerializer(Kryo kryo, Class cls) {
        super(kryo, cls);
    }

    @Override // com.esotericsoftware.kryo.serializers.FieldSerializer
    protected void initializeCachedFields() {
        FieldSerializer.CachedField[] fields = getFields();
        int length = fields.length;
        for (int i = 0; i < length; i++) {
            if (fields[i].getField().getAnnotation(Tag.class) == null) {
                if (Log.TRACE) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Ignoring field without tag: ");
                    outline107.append(fields[i]);
                    Log.trace("kryo", outline107.toString());
                }
                super.removeField(fields[i]);
            }
        }
        FieldSerializer.CachedField[] fields2 = getFields();
        this.tags = new int[fields2.length];
        this.deprecated = new boolean[fields2.length];
        this.writeFieldCount = fields2.length;
        int length2 = fields2.length;
        for (int i2 = 0; i2 < length2; i2++) {
            Field field = fields2[i2].getField();
            this.tags[i2] = ((Tag) field.getAnnotation(Tag.class)).value();
            if (field.getAnnotation(Deprecated.class) != null) {
                this.deprecated[i2] = true;
                this.writeFieldCount--;
            }
        }
        this.removedFields.clear();
    }

    @Override // com.esotericsoftware.kryo.serializers.FieldSerializer, com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public T mo6848read(Kryo kryo, Input input, Class<T> cls) {
        T create = create(kryo, input, cls);
        kryo.reference(create);
        int readVarInt = input.readVarInt(true);
        int[] iArr = this.tags;
        FieldSerializer.CachedField[] fields = getFields();
        for (int i = 0; i < readVarInt; i++) {
            int readVarInt2 = input.readVarInt(true);
            FieldSerializer.CachedField cachedField = null;
            int length = iArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                } else if (iArr[i2] == readVarInt2) {
                    cachedField = fields[i2];
                    break;
                } else {
                    i2++;
                }
            }
            if (cachedField != null) {
                cachedField.read(input, create);
            } else {
                StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Unknown field tag: ", readVarInt2, " (");
                outline109.append(getType().getName());
                outline109.append(")");
                throw new KryoException(outline109.toString());
            }
        }
        return create;
    }

    @Override // com.esotericsoftware.kryo.serializers.FieldSerializer
    public void removeField(String str) {
        super.removeField(str);
        initializeCachedFields();
    }

    @Override // com.esotericsoftware.kryo.serializers.FieldSerializer, com.esotericsoftware.kryo.Serializer
    public void write(Kryo kryo, Output output, T t) {
        FieldSerializer.CachedField[] fields = getFields();
        output.writeVarInt(this.writeFieldCount, true);
        int length = fields.length;
        for (int i = 0; i < length; i++) {
            if (!this.deprecated[i]) {
                output.writeVarInt(this.tags[i], true);
                fields[i].write(output, t);
            }
        }
    }

    @Override // com.esotericsoftware.kryo.serializers.FieldSerializer
    public void removeField(FieldSerializer.CachedField cachedField) {
        super.removeField(cachedField);
        initializeCachedFields();
    }
}
