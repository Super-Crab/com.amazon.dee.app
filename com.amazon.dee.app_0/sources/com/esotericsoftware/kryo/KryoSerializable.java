package com.esotericsoftware.kryo;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
/* loaded from: classes2.dex */
public interface KryoSerializable {
    void read(Kryo kryo, Input input);

    void write(Kryo kryo, Output output);
}
