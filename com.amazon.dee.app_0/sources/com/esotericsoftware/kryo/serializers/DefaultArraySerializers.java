package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Registration;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.minlog.Log;
import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
/* loaded from: classes2.dex */
public class DefaultArraySerializers {

    /* loaded from: classes2.dex */
    public static class BooleanArraySerializer extends Serializer<boolean[]> {
        public BooleanArraySerializer() {
            setAcceptsNull(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public boolean[] copy(Kryo kryo, boolean[] zArr) {
            boolean[] zArr2 = new boolean[zArr.length];
            System.arraycopy(zArr, 0, zArr2, 0, zArr2.length);
            return zArr2;
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read  reason: avoid collision after fix types in other method */
        public boolean[] mo6848read(Kryo kryo, Input input, Class<boolean[]> cls) {
            int readVarInt = input.readVarInt(true);
            if (readVarInt == 0) {
                return null;
            }
            int i = readVarInt - 1;
            boolean[] zArr = new boolean[i];
            for (int i2 = 0; i2 < i; i2++) {
                zArr[i2] = input.readBoolean();
            }
            return zArr;
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, boolean[] zArr) {
            if (zArr == null) {
                output.writeVarInt(0, true);
                return;
            }
            output.writeVarInt(zArr.length + 1, true);
            for (boolean z : zArr) {
                output.writeBoolean(z);
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class ByteArraySerializer extends Serializer<byte[]> {
        public ByteArraySerializer() {
            setAcceptsNull(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public byte[] copy(Kryo kryo, byte[] bArr) {
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
            return bArr2;
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read  reason: avoid collision after fix types in other method */
        public byte[] mo6848read(Kryo kryo, Input input, Class<byte[]> cls) {
            int readVarInt = input.readVarInt(true);
            if (readVarInt == 0) {
                return null;
            }
            return input.readBytes(readVarInt - 1);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, byte[] bArr) {
            if (bArr == null) {
                output.writeVarInt(0, true);
                return;
            }
            output.writeVarInt(bArr.length + 1, true);
            output.writeBytes(bArr);
        }
    }

    /* loaded from: classes2.dex */
    public static class CharArraySerializer extends Serializer<char[]> {
        public CharArraySerializer() {
            setAcceptsNull(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public char[] copy(Kryo kryo, char[] cArr) {
            char[] cArr2 = new char[cArr.length];
            System.arraycopy(cArr, 0, cArr2, 0, cArr2.length);
            return cArr2;
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read  reason: avoid collision after fix types in other method */
        public char[] mo6848read(Kryo kryo, Input input, Class<char[]> cls) {
            int readVarInt = input.readVarInt(true);
            if (readVarInt == 0) {
                return null;
            }
            return input.readChars(readVarInt - 1);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, char[] cArr) {
            if (cArr == null) {
                output.writeVarInt(0, true);
                return;
            }
            output.writeVarInt(cArr.length + 1, true);
            output.writeChars(cArr);
        }
    }

    /* loaded from: classes2.dex */
    public static class DoubleArraySerializer extends Serializer<double[]> {
        public DoubleArraySerializer() {
            setAcceptsNull(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public double[] copy(Kryo kryo, double[] dArr) {
            double[] dArr2 = new double[dArr.length];
            System.arraycopy(dArr, 0, dArr2, 0, dArr2.length);
            return dArr2;
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read  reason: avoid collision after fix types in other method */
        public double[] mo6848read(Kryo kryo, Input input, Class<double[]> cls) {
            int readVarInt = input.readVarInt(true);
            if (readVarInt == 0) {
                return null;
            }
            return input.readDoubles(readVarInt - 1);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, double[] dArr) {
            if (dArr == null) {
                output.writeVarInt(0, true);
                return;
            }
            output.writeVarInt(dArr.length + 1, true);
            output.writeDoubles(dArr);
        }
    }

    /* loaded from: classes2.dex */
    public static class FloatArraySerializer extends Serializer<float[]> {
        public FloatArraySerializer() {
            setAcceptsNull(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public float[] copy(Kryo kryo, float[] fArr) {
            float[] fArr2 = new float[fArr.length];
            System.arraycopy(fArr, 0, fArr2, 0, fArr2.length);
            return fArr2;
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read  reason: avoid collision after fix types in other method */
        public float[] mo6848read(Kryo kryo, Input input, Class<float[]> cls) {
            int readVarInt = input.readVarInt(true);
            if (readVarInt == 0) {
                return null;
            }
            return input.readFloats(readVarInt - 1);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, float[] fArr) {
            if (fArr == null) {
                output.writeVarInt(0, true);
                return;
            }
            output.writeVarInt(fArr.length + 1, true);
            output.writeFloats(fArr);
        }
    }

    /* loaded from: classes2.dex */
    public static class IntArraySerializer extends Serializer<int[]> {
        public IntArraySerializer() {
            setAcceptsNull(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public int[] copy(Kryo kryo, int[] iArr) {
            int[] iArr2 = new int[iArr.length];
            System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
            return iArr2;
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read  reason: avoid collision after fix types in other method */
        public int[] mo6848read(Kryo kryo, Input input, Class<int[]> cls) {
            int readVarInt = input.readVarInt(true);
            if (readVarInt == 0) {
                return null;
            }
            return input.readInts(readVarInt - 1, false);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, int[] iArr) {
            if (iArr == null) {
                output.writeVarInt(0, true);
                return;
            }
            output.writeVarInt(iArr.length + 1, true);
            output.writeInts(iArr, false);
        }
    }

    /* loaded from: classes2.dex */
    public static class LongArraySerializer extends Serializer<long[]> {
        public LongArraySerializer() {
            setAcceptsNull(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public long[] copy(Kryo kryo, long[] jArr) {
            long[] jArr2 = new long[jArr.length];
            System.arraycopy(jArr, 0, jArr2, 0, jArr2.length);
            return jArr2;
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read  reason: avoid collision after fix types in other method */
        public long[] mo6848read(Kryo kryo, Input input, Class<long[]> cls) {
            int readVarInt = input.readVarInt(true);
            if (readVarInt == 0) {
                return null;
            }
            return input.readLongs(readVarInt - 1, false);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, long[] jArr) {
            if (jArr == null) {
                output.writeVarInt(0, true);
                return;
            }
            output.writeVarInt(jArr.length + 1, true);
            output.writeLongs(jArr, false);
        }
    }

    /* loaded from: classes2.dex */
    public static class ObjectArraySerializer extends Serializer<Object[]> {
        private boolean elementsAreSameType;
        private boolean elementsCanBeNull = true;
        private Class[] generics;
        private final Class type;

        public ObjectArraySerializer(Kryo kryo, Class cls) {
            setAcceptsNull(true);
            this.type = cls;
            if ((cls.getComponentType().getModifiers() & 16) != 0) {
                setElementsAreSameType(true);
            }
        }

        public void setElementsAreSameType(boolean z) {
            this.elementsAreSameType = z;
        }

        public void setElementsCanBeNull(boolean z) {
            this.elementsCanBeNull = z;
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void setGenerics(Kryo kryo, Class[] clsArr) {
            if (Log.TRACE) {
                Log.trace("kryo", "setting generics for ObjectArraySerializer");
            }
            this.generics = clsArr;
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public Object[] copy(Kryo kryo, Object[] objArr) {
            Object[] objArr2 = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), objArr.length);
            int length = objArr.length;
            for (int i = 0; i < length; i++) {
                objArr2[i] = kryo.copy(objArr[i]);
            }
            return objArr2;
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read  reason: avoid collision after fix types in other method */
        public Object[] mo6848read(Kryo kryo, Input input, Class<Object[]> cls) {
            int readVarInt = input.readVarInt(true);
            if (readVarInt == 0) {
                return null;
            }
            Object[] objArr = (Object[]) Array.newInstance(cls.getComponentType(), readVarInt - 1);
            kryo.reference(objArr);
            Class componentType = objArr.getClass().getComponentType();
            int i = 0;
            if (!this.elementsAreSameType && !Modifier.isFinal(componentType.getModifiers())) {
                int length = objArr.length;
                while (i < length) {
                    Registration readClass = kryo.readClass(input);
                    if (readClass != null) {
                        readClass.getSerializer().setGenerics(kryo, this.generics);
                        objArr[i] = kryo.readObject(input, readClass.getType(), readClass.getSerializer());
                    } else {
                        objArr[i] = null;
                    }
                    i++;
                }
            } else {
                Serializer serializer = kryo.getSerializer(componentType);
                serializer.setGenerics(kryo, this.generics);
                int length2 = objArr.length;
                while (i < length2) {
                    if (this.elementsCanBeNull) {
                        objArr[i] = kryo.readObjectOrNull(input, componentType, serializer);
                    } else {
                        objArr[i] = kryo.readObject(input, componentType, serializer);
                    }
                    i++;
                }
            }
            return objArr;
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Object[] objArr) {
            int i = 0;
            if (objArr == null) {
                output.writeVarInt(0, true);
                return;
            }
            output.writeVarInt(objArr.length + 1, true);
            Class<?> componentType = objArr.getClass().getComponentType();
            if (!this.elementsAreSameType && !Modifier.isFinal(componentType.getModifiers())) {
                int length = objArr.length;
                while (i < length) {
                    if (objArr[i] != null) {
                        kryo.getSerializer(objArr[i].getClass()).setGenerics(kryo, this.generics);
                    }
                    kryo.writeClassAndObject(output, objArr[i]);
                    i++;
                }
                return;
            }
            Serializer serializer = kryo.getSerializer(componentType);
            serializer.setGenerics(kryo, this.generics);
            int length2 = objArr.length;
            while (i < length2) {
                if (this.elementsCanBeNull) {
                    kryo.writeObjectOrNull(output, objArr[i], serializer);
                } else {
                    kryo.writeObject(output, objArr[i], serializer);
                }
                i++;
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class ShortArraySerializer extends Serializer<short[]> {
        public ShortArraySerializer() {
            setAcceptsNull(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public short[] copy(Kryo kryo, short[] sArr) {
            short[] sArr2 = new short[sArr.length];
            System.arraycopy(sArr, 0, sArr2, 0, sArr2.length);
            return sArr2;
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read  reason: avoid collision after fix types in other method */
        public short[] mo6848read(Kryo kryo, Input input, Class<short[]> cls) {
            int readVarInt = input.readVarInt(true);
            if (readVarInt == 0) {
                return null;
            }
            return input.readShorts(readVarInt - 1);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, short[] sArr) {
            if (sArr == null) {
                output.writeVarInt(0, true);
                return;
            }
            output.writeVarInt(sArr.length + 1, true);
            output.writeShorts(sArr);
        }
    }

    /* loaded from: classes2.dex */
    public static class StringArraySerializer extends Serializer<String[]> {
        public StringArraySerializer() {
            setAcceptsNull(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public String[] copy(Kryo kryo, String[] strArr) {
            String[] strArr2 = new String[strArr.length];
            System.arraycopy(strArr, 0, strArr2, 0, strArr2.length);
            return strArr2;
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read  reason: avoid collision after fix types in other method */
        public String[] mo6848read(Kryo kryo, Input input, Class<String[]> cls) {
            int readVarInt = input.readVarInt(true);
            if (readVarInt == 0) {
                return null;
            }
            int i = readVarInt - 1;
            String[] strArr = new String[i];
            int i2 = 0;
            if (!kryo.getReferences() || !kryo.getReferenceResolver().useReferences(String.class)) {
                while (i2 < i) {
                    strArr[i2] = input.readString();
                    i2++;
                }
            } else {
                Serializer serializer = kryo.getSerializer(String.class);
                while (i2 < i) {
                    strArr[i2] = (String) kryo.readObjectOrNull(input, String.class, serializer);
                    i2++;
                }
            }
            return strArr;
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, String[] strArr) {
            int i = 0;
            if (strArr == null) {
                output.writeVarInt(0, true);
                return;
            }
            output.writeVarInt(strArr.length + 1, true);
            if (kryo.getReferences() && kryo.getReferenceResolver().useReferences(String.class)) {
                Serializer serializer = kryo.getSerializer(String.class);
                int length = strArr.length;
                while (i < length) {
                    kryo.writeObjectOrNull(output, strArr[i], serializer);
                    i++;
                }
                return;
            }
            int length2 = strArr.length;
            while (i < length2) {
                output.writeString(strArr[i]);
                i++;
            }
        }
    }
}
