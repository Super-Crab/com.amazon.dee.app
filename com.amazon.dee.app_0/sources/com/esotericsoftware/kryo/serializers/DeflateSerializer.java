package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.InputChunked;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.io.OutputChunked;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
/* loaded from: classes2.dex */
public class DeflateSerializer extends Serializer {
    private final Serializer serializer;
    private boolean noHeaders = true;
    private int compressionLevel = 4;

    public DeflateSerializer(Serializer serializer) {
        this.serializer = serializer;
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public Object copy(Kryo kryo, Object obj) {
        return this.serializer.copy(kryo, obj);
    }

    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public Object mo6848read(Kryo kryo, Input input, Class cls) {
        return kryo.readObject(new Input(new InflaterInputStream(new InputChunked(input, 256), new Inflater(this.noHeaders)), 256), cls, this.serializer);
    }

    public void setCompressionLevel(int i) {
        this.compressionLevel = i;
    }

    public void setNoHeaders(boolean z) {
        this.noHeaders = z;
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public void write(Kryo kryo, Output output, Object obj) {
        Deflater deflater = new Deflater(this.compressionLevel, this.noHeaders);
        OutputChunked outputChunked = new OutputChunked(output, 256);
        DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(outputChunked, deflater);
        Output output2 = new Output(deflaterOutputStream, 256);
        kryo.writeObject(output2, obj, this.serializer);
        output2.flush();
        try {
            deflaterOutputStream.finish();
            outputChunked.endChunks();
        } catch (IOException e) {
            throw new KryoException(e);
        }
    }
}
