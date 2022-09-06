package com.esotericsoftware.kryo.serializers;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.InputChunked;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.io.OutputChunked;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import com.esotericsoftware.kryo.util.ObjectMap;
import com.esotericsoftware.minlog.Log;
/* loaded from: classes2.dex */
public class CompatibleFieldSerializer<T> extends FieldSerializer<T> {
    public CompatibleFieldSerializer(Kryo kryo, Class cls) {
        super(kryo, cls);
    }

    @Override // com.esotericsoftware.kryo.serializers.FieldSerializer, com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public T mo6848read(Kryo kryo, Input input, Class<T> cls) {
        T create = create(kryo, input, cls);
        kryo.reference(create);
        ObjectMap graphContext = kryo.getGraphContext();
        FieldSerializer.CachedField[] cachedFieldArr = (FieldSerializer.CachedField[]) graphContext.get(this);
        boolean z = true;
        if (cachedFieldArr == null) {
            int readVarInt = input.readVarInt(true);
            if (Log.TRACE) {
                Log.trace("kryo", "Read " + readVarInt + " field names.");
            }
            String[] strArr = new String[readVarInt];
            for (int i = 0; i < readVarInt; i++) {
                strArr[i] = input.readString();
            }
            cachedFieldArr = new FieldSerializer.CachedField[readVarInt];
            FieldSerializer.CachedField[] fields = getFields();
            int length = strArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                String str = strArr[i2];
                int length2 = fields.length;
                int i3 = 0;
                while (true) {
                    if (i3 < length2) {
                        if (fields[i3].field.getName().equals(str)) {
                            cachedFieldArr[i2] = fields[i3];
                            break;
                        }
                        i3++;
                    } else if (Log.TRACE) {
                        Log.trace("kryo", "Ignore obsolete field: " + str);
                    }
                }
            }
            graphContext.put(this, cachedFieldArr);
        }
        InputChunked inputChunked = new InputChunked(input, 1024);
        if (getGenerics() == null) {
            z = false;
        }
        for (FieldSerializer.CachedField cachedField : cachedFieldArr) {
            if (cachedField != null && z) {
                cachedField = getField(cachedField.field.getName());
            }
            if (cachedField == null) {
                if (Log.TRACE) {
                    Log.trace("kryo", "Skip obsolete field.");
                }
                inputChunked.nextChunks();
            } else {
                cachedField.read(inputChunked, create);
                inputChunked.nextChunks();
            }
        }
        return create;
    }

    @Override // com.esotericsoftware.kryo.serializers.FieldSerializer, com.esotericsoftware.kryo.Serializer
    public void write(Kryo kryo, Output output, T t) {
        FieldSerializer.CachedField[] fields = getFields();
        ObjectMap graphContext = kryo.getGraphContext();
        if (!graphContext.containsKey(this)) {
            graphContext.put(this, null);
            if (Log.TRACE) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Write ");
                outline107.append(fields.length);
                outline107.append(" field names.");
                Log.trace("kryo", outline107.toString());
            }
            output.writeVarInt(fields.length, true);
            for (FieldSerializer.CachedField cachedField : fields) {
                output.writeString(cachedField.field.getName());
            }
        }
        OutputChunked outputChunked = new OutputChunked(output, 1024);
        for (FieldSerializer.CachedField cachedField2 : fields) {
            cachedField2.write(outputChunked, t);
            outputChunked.endChunks();
        }
    }
}
