package com.esotericsoftware.kryo.serializers;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import com.esotericsoftware.reflectasm.FieldAccess;
/* loaded from: classes2.dex */
class AsmCacheFields {

    /* loaded from: classes2.dex */
    static final class AsmBooleanField extends AsmCachedField {
        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public void copy(Object obj, Object obj2) {
            FieldAccess fieldAccess = this.access;
            int i = this.accessIndex;
            fieldAccess.setBoolean(obj2, i, fieldAccess.getBoolean(obj, i));
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public void read(Input input, Object obj) {
            this.access.setBoolean(obj, this.accessIndex, input.readBoolean());
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public void write(Output output, Object obj) {
            output.writeBoolean(this.access.getBoolean(obj, this.accessIndex));
        }
    }

    /* loaded from: classes2.dex */
    static final class AsmByteField extends AsmCachedField {
        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public void copy(Object obj, Object obj2) {
            FieldAccess fieldAccess = this.access;
            int i = this.accessIndex;
            fieldAccess.setByte(obj2, i, fieldAccess.getByte(obj, i));
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public void read(Input input, Object obj) {
            this.access.setByte(obj, this.accessIndex, input.readByte());
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public void write(Output output, Object obj) {
            output.writeByte(this.access.getByte(obj, this.accessIndex));
        }
    }

    /* loaded from: classes2.dex */
    static abstract class AsmCachedField extends FieldSerializer.CachedField {
        AsmCachedField() {
        }
    }

    /* loaded from: classes2.dex */
    static final class AsmCharField extends AsmCachedField {
        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public void copy(Object obj, Object obj2) {
            FieldAccess fieldAccess = this.access;
            int i = this.accessIndex;
            fieldAccess.setChar(obj2, i, fieldAccess.getChar(obj, i));
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public void read(Input input, Object obj) {
            this.access.setChar(obj, this.accessIndex, input.readChar());
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public void write(Output output, Object obj) {
            output.writeChar(this.access.getChar(obj, this.accessIndex));
        }
    }

    /* loaded from: classes2.dex */
    static final class AsmDoubleField extends AsmCachedField {
        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public void copy(Object obj, Object obj2) {
            FieldAccess fieldAccess = this.access;
            int i = this.accessIndex;
            fieldAccess.setDouble(obj2, i, fieldAccess.getDouble(obj, i));
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public void read(Input input, Object obj) {
            this.access.setDouble(obj, this.accessIndex, input.readDouble());
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public void write(Output output, Object obj) {
            output.writeDouble(this.access.getDouble(obj, this.accessIndex));
        }
    }

    /* loaded from: classes2.dex */
    static final class AsmFloatField extends AsmCachedField {
        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public void copy(Object obj, Object obj2) {
            FieldAccess fieldAccess = this.access;
            int i = this.accessIndex;
            fieldAccess.setFloat(obj2, i, fieldAccess.getFloat(obj, i));
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public void read(Input input, Object obj) {
            this.access.setFloat(obj, this.accessIndex, input.readFloat());
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public void write(Output output, Object obj) {
            output.writeFloat(this.access.getFloat(obj, this.accessIndex));
        }
    }

    /* loaded from: classes2.dex */
    static final class AsmIntField extends AsmCachedField {
        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public void copy(Object obj, Object obj2) {
            FieldAccess fieldAccess = this.access;
            int i = this.accessIndex;
            fieldAccess.setInt(obj2, i, fieldAccess.getInt(obj, i));
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public void read(Input input, Object obj) {
            if (this.varIntsEnabled) {
                this.access.setInt(obj, this.accessIndex, input.readInt(false));
            } else {
                this.access.setInt(obj, this.accessIndex, input.readInt());
            }
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public void write(Output output, Object obj) {
            if (this.varIntsEnabled) {
                output.writeInt(this.access.getInt(obj, this.accessIndex), false);
            } else {
                output.writeInt(this.access.getInt(obj, this.accessIndex));
            }
        }
    }

    /* loaded from: classes2.dex */
    static final class AsmLongField extends AsmCachedField {
        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public void copy(Object obj, Object obj2) {
            FieldAccess fieldAccess = this.access;
            int i = this.accessIndex;
            fieldAccess.setLong(obj2, i, fieldAccess.getLong(obj, i));
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public void read(Input input, Object obj) {
            if (this.varIntsEnabled) {
                this.access.setLong(obj, this.accessIndex, input.readLong(false));
            } else {
                this.access.setLong(obj, this.accessIndex, input.readLong());
            }
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public void write(Output output, Object obj) {
            if (this.varIntsEnabled) {
                output.writeLong(this.access.getLong(obj, this.accessIndex), false);
            } else {
                output.writeLong(this.access.getLong(obj, this.accessIndex));
            }
        }
    }

    /* loaded from: classes2.dex */
    static final class AsmObjectField extends ObjectField {
        public AsmObjectField(FieldSerializer fieldSerializer) {
            super(fieldSerializer);
        }

        @Override // com.esotericsoftware.kryo.serializers.ObjectField, com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public void copy(Object obj, Object obj2) {
            try {
                if (this.accessIndex != -1) {
                    this.access.set(obj2, this.accessIndex, this.kryo.copy(this.access.get(obj, this.accessIndex)));
                    return;
                }
                throw new KryoException("Unknown acess index");
            } catch (KryoException e) {
                StringBuilder sb = new StringBuilder();
                sb.append(this);
                sb.append(" (");
                GeneratedOutlineSupport1.outline147(this.type, sb, ")", e);
                throw e;
            } catch (RuntimeException e2) {
                KryoException kryoException = new KryoException(e2);
                StringBuilder sb2 = new StringBuilder();
                sb2.append(this);
                sb2.append(" (");
                GeneratedOutlineSupport1.outline147(this.type, sb2, ")", kryoException);
                throw kryoException;
            }
        }

        @Override // com.esotericsoftware.kryo.serializers.ObjectField
        public Object getField(Object obj) throws IllegalArgumentException, IllegalAccessException {
            int i = this.accessIndex;
            if (i != -1) {
                return this.access.get(obj, i);
            }
            throw new KryoException("Unknown acess index");
        }

        @Override // com.esotericsoftware.kryo.serializers.ObjectField
        public void setField(Object obj, Object obj2) throws IllegalArgumentException, IllegalAccessException {
            int i = this.accessIndex;
            if (i != -1) {
                this.access.set(obj, i, obj2);
                return;
            }
            throw new KryoException("Unknown acess index");
        }
    }

    /* loaded from: classes2.dex */
    static final class AsmShortField extends AsmCachedField {
        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public void copy(Object obj, Object obj2) {
            FieldAccess fieldAccess = this.access;
            int i = this.accessIndex;
            fieldAccess.setShort(obj2, i, fieldAccess.getShort(obj, i));
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public void read(Input input, Object obj) {
            this.access.setShort(obj, this.accessIndex, input.readShort());
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public void write(Output output, Object obj) {
            output.writeShort(this.access.getShort(obj, this.accessIndex));
        }
    }

    /* loaded from: classes2.dex */
    static final class AsmStringField extends AsmCachedField {
        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public void copy(Object obj, Object obj2) {
            FieldAccess fieldAccess = this.access;
            int i = this.accessIndex;
            fieldAccess.set(obj2, i, fieldAccess.getString(obj, i));
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public void read(Input input, Object obj) {
            this.access.set(obj, this.accessIndex, input.readString());
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public void write(Output output, Object obj) {
            output.writeString(this.access.getString(obj, this.accessIndex));
        }
    }

    AsmCacheFields() {
    }
}
