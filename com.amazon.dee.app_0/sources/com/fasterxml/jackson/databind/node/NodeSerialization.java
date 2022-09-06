package com.fasterxml.jackson.databind.node;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
/* loaded from: classes2.dex */
class NodeSerialization implements Serializable, Externalizable {
    private static final long serialVersionUID = 1;
    public byte[] json;

    public NodeSerialization() {
    }

    public static NodeSerialization from(Object obj) {
        try {
            return new NodeSerialization(InternalNodeMapper.valueToBytes(obj));
        } catch (IOException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to JDK serialize `");
            outline107.append(obj.getClass().getSimpleName());
            outline107.append("` value: ");
            outline107.append(e.getMessage());
            throw new IllegalArgumentException(outline107.toString(), e);
        }
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput objectInput) throws IOException {
        int readInt = objectInput.readInt();
        this.json = new byte[readInt];
        objectInput.readFully(this.json, 0, readInt);
    }

    protected Object readResolve() {
        try {
            return InternalNodeMapper.bytesToNode(this.json);
        } catch (IOException e) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("Failed to JDK deserialize `JsonNode` value: ")), e);
        }
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeInt(this.json.length);
        objectOutput.write(this.json);
    }

    public NodeSerialization(byte[] bArr) {
        this.json = bArr;
    }
}
