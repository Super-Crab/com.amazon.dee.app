package org.apache.logging.log4j.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.status.StatusLogger;
/* loaded from: classes4.dex */
public class SortedArrayStringMap implements IndexedStringMap {
    private static final int DEFAULT_INITIAL_CAPACITY = 4;
    private static final String FROZEN = "Frozen collection cannot be modified";
    private static final int HASHVAL = 31;
    private static final Method getObjectInputFilter;
    private static final Method newObjectInputFilter;
    private static final long serialVersionUID = -5748905872274478116L;
    private static final Method setObjectInputFilter;
    private boolean immutable;
    private transient boolean iterating;
    private transient String[] keys;
    private transient int size;
    private int threshold;
    private transient Object[] values;
    private static final TriConsumer<String, Object, StringMap> PUT_ALL = new TriConsumer<String, Object, StringMap>() { // from class: org.apache.logging.log4j.util.SortedArrayStringMap.1
        @Override // org.apache.logging.log4j.util.TriConsumer
        public void accept(String str, Object obj, StringMap stringMap) {
            stringMap.putValue(str, obj);
        }
    };
    private static final String[] EMPTY = new String[0];

    static {
        Method[] methods;
        int i = 0;
        Method method = null;
        Method method2 = null;
        Method method3 = null;
        for (Method method4 : ObjectInputStream.class.getMethods()) {
            if (method4.getName().equals("setObjectInputFilter")) {
                method2 = method4;
            } else if (method4.getName().equals("getObjectInputFilter")) {
                method3 = method4;
            }
        }
        if (method2 != null) {
            try {
                Method[] methods2 = Class.forName("org.apache.logging.log4j.util.internal.DefaultObjectInputFilter").getMethods();
                int length = methods2.length;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    Method method5 = methods2[i];
                    if (method5.getName().equals("newInstance") && Modifier.isStatic(method5.getModifiers())) {
                        method = method5;
                        break;
                    }
                    i++;
                }
            } catch (ClassNotFoundException unused) {
            }
        }
        newObjectInputFilter = method;
        setObjectInputFilter = method2;
        getObjectInputFilter = method3;
    }

    public SortedArrayStringMap() {
        this(4);
    }

    private void assertNoConcurrentModification() {
        if (!this.iterating) {
            return;
        }
        throw new ConcurrentModificationException();
    }

    private void assertNotFrozen() {
        if (!this.immutable) {
            return;
        }
        throw new UnsupportedOperationException(FROZEN);
    }

    private static int ceilingNextPowerOfTwo(int i) {
        return 1 << (32 - Integer.numberOfLeadingZeros(i - 1));
    }

    private void ensureCapacity() {
        int i = this.size;
        int i2 = this.threshold;
        if (i >= i2) {
            resize(i2 * 2);
        }
    }

    private void handleSerializationException(Throwable th, int i, String str) {
        StatusLogger.getLogger().warn("Ignoring {} for key[{}] ('{}')", String.valueOf(th), Integer.valueOf(i), this.keys[i]);
    }

    private void inflateTable(int i) {
        this.threshold = i;
        this.keys = new String[i];
        this.values = new Object[i];
    }

    private void initFrom0(SortedArrayStringMap sortedArrayStringMap) {
        if (this.keys.length < sortedArrayStringMap.size) {
            int i = sortedArrayStringMap.threshold;
            this.keys = new String[i];
            this.values = new Object[i];
        }
        System.arraycopy(sortedArrayStringMap.keys, 0, this.keys, 0, sortedArrayStringMap.size);
        System.arraycopy(sortedArrayStringMap.values, 0, this.values, 0, sortedArrayStringMap.size);
        this.size = sortedArrayStringMap.size;
        this.threshold = sortedArrayStringMap.threshold;
    }

    private void insertAt(int i, String str, Object obj) {
        ensureCapacity();
        String[] strArr = this.keys;
        int i2 = i + 1;
        System.arraycopy(strArr, i, strArr, i2, this.size - i);
        Object[] objArr = this.values;
        System.arraycopy(objArr, i, objArr, i2, this.size - i);
        this.keys[i] = str;
        this.values[i] = obj;
        this.size++;
    }

    private static byte[] marshall(Object obj) throws IOException {
        if (obj == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        try {
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            objectOutputStream.close();
            return byteArray;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    objectOutputStream.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    private void merge(SortedArrayStringMap sortedArrayStringMap) {
        String[] strArr = this.keys;
        Object[] objArr = this.values;
        int i = sortedArrayStringMap.size + this.size;
        this.threshold = ceilingNextPowerOfTwo(i);
        int length = this.keys.length;
        int i2 = this.threshold;
        if (length < i2) {
            this.keys = new String[i2];
            this.values = new Object[i2];
        }
        boolean z = false;
        if (sortedArrayStringMap.size() > size()) {
            System.arraycopy(strArr, 0, this.keys, sortedArrayStringMap.size, this.size);
            System.arraycopy(objArr, 0, this.values, sortedArrayStringMap.size, this.size);
            System.arraycopy(sortedArrayStringMap.keys, 0, this.keys, 0, sortedArrayStringMap.size);
            System.arraycopy(sortedArrayStringMap.values, 0, this.values, 0, sortedArrayStringMap.size);
            this.size = sortedArrayStringMap.size;
        } else {
            System.arraycopy(strArr, 0, this.keys, 0, this.size);
            System.arraycopy(objArr, 0, this.values, 0, this.size);
            System.arraycopy(sortedArrayStringMap.keys, 0, this.keys, this.size, sortedArrayStringMap.size);
            System.arraycopy(sortedArrayStringMap.values, 0, this.values, this.size, sortedArrayStringMap.size);
            z = true;
        }
        for (int i3 = this.size; i3 < i; i3++) {
            int indexOfKey = indexOfKey(this.keys[i3]);
            if (indexOfKey < 0) {
                insertAt(~indexOfKey, this.keys[i3], this.values[i3]);
            } else if (z) {
                String[] strArr2 = this.keys;
                strArr2[indexOfKey] = strArr2[i3];
                Object[] objArr2 = this.values;
                objArr2[indexOfKey] = objArr2[i3];
            }
        }
        Arrays.fill(this.keys, this.size, i, (Object) null);
        Arrays.fill(this.values, this.size, i, (Object) null);
    }

    private int nullKeyIndex() {
        return (this.size <= 0 || this.keys[0] != null) ? -1 : 0;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        if (!(objectInputStream instanceof FilteredObjectInputStream) && setObjectInputFilter == null) {
            throw new IllegalArgumentException("readObject requires a FilteredObjectInputStream or an ObjectInputStream that accepts an ObjectInputFilter");
        }
        objectInputStream.defaultReadObject();
        String[] strArr = EMPTY;
        this.keys = strArr;
        this.values = strArr;
        int readInt = objectInputStream.readInt();
        if (readInt >= 0) {
            int readInt2 = objectInputStream.readInt();
            if (readInt2 >= 0) {
                if (readInt2 > 0) {
                    inflateTable(readInt);
                } else {
                    this.threshold = readInt;
                }
                for (int i = 0; i < readInt2; i++) {
                    this.keys[i] = (String) objectInputStream.readObject();
                    try {
                        byte[] bArr = (byte[]) objectInputStream.readObject();
                        this.values[i] = bArr == null ? null : unmarshall(bArr, objectInputStream);
                    } catch (Exception | LinkageError e) {
                        handleSerializationException(e, i, this.keys[i]);
                        this.values[i] = null;
                    }
                }
                this.size = readInt2;
                return;
            }
            throw new InvalidObjectException(GeneratedOutlineSupport1.outline49("Illegal mappings count: ", readInt2));
        }
        throw new InvalidObjectException(GeneratedOutlineSupport1.outline49("Illegal capacity: ", readInt));
    }

    private void resize(int i) {
        String[] strArr = this.keys;
        Object[] objArr = this.values;
        this.keys = new String[i];
        this.values = new Object[i];
        System.arraycopy(strArr, 0, this.keys, 0, this.size);
        System.arraycopy(objArr, 0, this.values, 0, this.size);
        this.threshold = i;
    }

    private static Object unmarshall(byte[] bArr, ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream2;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        if (objectInputStream instanceof FilteredObjectInputStream) {
            objectInputStream2 = new FilteredObjectInputStream(byteArrayInputStream, ((FilteredObjectInputStream) objectInputStream).getAllowedClasses());
        } else {
            try {
                Object invoke = newObjectInputFilter.invoke(null, getObjectInputFilter.invoke(objectInputStream, new Object[0]));
                objectInputStream2 = new ObjectInputStream(byteArrayInputStream);
                setObjectInputFilter.invoke(objectInputStream2, invoke);
            } catch (IllegalAccessException | InvocationTargetException unused) {
                throw new StreamCorruptedException("Unable to set ObjectInputFilter on stream");
            }
        }
        try {
            return objectInputStream2.readObject();
        } finally {
            objectInputStream2.close();
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        String[] strArr = this.keys;
        if (strArr == EMPTY) {
            objectOutputStream.writeInt(ceilingNextPowerOfTwo(this.threshold));
        } else {
            objectOutputStream.writeInt(strArr.length);
        }
        objectOutputStream.writeInt(this.size);
        if (this.size > 0) {
            for (int i = 0; i < this.size; i++) {
                objectOutputStream.writeObject(this.keys[i]);
                try {
                    objectOutputStream.writeObject(marshall(this.values[i]));
                } catch (Exception e) {
                    handleSerializationException(e, i, this.keys[i]);
                    objectOutputStream.writeObject(null);
                }
            }
        }
    }

    @Override // org.apache.logging.log4j.util.StringMap
    public void clear() {
        if (this.keys == EMPTY) {
            return;
        }
        assertNotFrozen();
        assertNoConcurrentModification();
        Arrays.fill(this.keys, 0, this.size, (Object) null);
        Arrays.fill(this.values, 0, this.size, (Object) null);
        this.size = 0;
    }

    @Override // org.apache.logging.log4j.util.ReadOnlyStringMap
    public boolean containsKey(String str) {
        return indexOfKey(str) >= 0;
    }

    @Override // org.apache.logging.log4j.util.StringMap
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SortedArrayStringMap)) {
            return false;
        }
        SortedArrayStringMap sortedArrayStringMap = (SortedArrayStringMap) obj;
        if (size() != sortedArrayStringMap.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (!Objects.equals(this.keys[i], sortedArrayStringMap.keys[i]) || !Objects.equals(this.values[i], sortedArrayStringMap.values[i])) {
                return false;
            }
        }
        return true;
    }

    @Override // org.apache.logging.log4j.util.ReadOnlyStringMap
    public <V> void forEach(BiConsumer<String, ? super V> biConsumer) {
        this.iterating = true;
        for (int i = 0; i < this.size; i++) {
            try {
                biConsumer.accept(this.keys[i], this.values[i]);
            } finally {
                this.iterating = false;
            }
        }
    }

    @Override // org.apache.logging.log4j.util.StringMap
    public void freeze() {
        this.immutable = true;
    }

    @Override // org.apache.logging.log4j.util.IndexedReadOnlyStringMap
    public String getKeyAt(int i) {
        if (i < 0 || i >= this.size) {
            return null;
        }
        return this.keys[i];
    }

    @Override // org.apache.logging.log4j.util.ReadOnlyStringMap
    public <V> V getValue(String str) {
        int indexOfKey = indexOfKey(str);
        if (indexOfKey < 0) {
            return null;
        }
        return (V) this.values[indexOfKey];
    }

    @Override // org.apache.logging.log4j.util.IndexedReadOnlyStringMap
    public <V> V getValueAt(int i) {
        if (i < 0 || i >= this.size) {
            return null;
        }
        return (V) this.values[i];
    }

    @Override // org.apache.logging.log4j.util.StringMap
    public int hashCode() {
        int i = this.size;
        return ((((1147 + i) * 31) + hashCode(this.keys, i)) * 31) + hashCode(this.values, this.size);
    }

    @Override // org.apache.logging.log4j.util.IndexedReadOnlyStringMap
    public int indexOfKey(String str) {
        String[] strArr = this.keys;
        if (strArr == EMPTY) {
            return -1;
        }
        if (str == null) {
            return nullKeyIndex();
        }
        int i = 0;
        if (this.size > 0 && strArr[0] == null) {
            i = 1;
        }
        return Arrays.binarySearch(this.keys, i, this.size, str);
    }

    @Override // org.apache.logging.log4j.util.ReadOnlyStringMap
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override // org.apache.logging.log4j.util.StringMap
    public boolean isFrozen() {
        return this.immutable;
    }

    @Override // org.apache.logging.log4j.util.StringMap
    public void putAll(ReadOnlyStringMap readOnlyStringMap) {
        if (readOnlyStringMap == this || readOnlyStringMap == null || readOnlyStringMap.isEmpty()) {
            return;
        }
        assertNotFrozen();
        assertNoConcurrentModification();
        if (readOnlyStringMap instanceof SortedArrayStringMap) {
            if (this.size == 0) {
                initFrom0((SortedArrayStringMap) readOnlyStringMap);
                return;
            } else {
                merge((SortedArrayStringMap) readOnlyStringMap);
                return;
            }
        }
        readOnlyStringMap.forEach(PUT_ALL, this);
    }

    @Override // org.apache.logging.log4j.util.StringMap
    public void putValue(String str, Object obj) {
        assertNotFrozen();
        assertNoConcurrentModification();
        if (this.keys == EMPTY) {
            inflateTable(this.threshold);
        }
        int indexOfKey = indexOfKey(str);
        if (indexOfKey >= 0) {
            this.keys[indexOfKey] = str;
            this.values[indexOfKey] = obj;
            return;
        }
        insertAt(~indexOfKey, str, obj);
    }

    @Override // org.apache.logging.log4j.util.StringMap
    public void remove(String str) {
        int indexOfKey;
        if (this.keys != EMPTY && (indexOfKey = indexOfKey(str)) >= 0) {
            assertNotFrozen();
            assertNoConcurrentModification();
            String[] strArr = this.keys;
            int i = indexOfKey + 1;
            System.arraycopy(strArr, i, strArr, indexOfKey, (this.size - 1) - indexOfKey);
            Object[] objArr = this.values;
            System.arraycopy(objArr, i, objArr, indexOfKey, (this.size - 1) - indexOfKey);
            String[] strArr2 = this.keys;
            int i2 = this.size;
            strArr2[i2 - 1] = null;
            this.values[i2 - 1] = null;
            this.size = i2 - 1;
        }
    }

    @Override // org.apache.logging.log4j.util.ReadOnlyStringMap
    public int size() {
        return this.size;
    }

    @Override // org.apache.logging.log4j.util.ReadOnlyStringMap
    public Map<String, String> toMap() {
        HashMap hashMap = new HashMap(size());
        for (int i = 0; i < size(); i++) {
            Object valueAt = getValueAt(i);
            hashMap.put(getKeyAt(i), valueAt == null ? null : String.valueOf(valueAt));
        }
        return hashMap;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(256);
        sb.append(JsonReaderKt.BEGIN_OBJ);
        for (int i = 0; i < this.size; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(this.keys[i]);
            sb.append(Chars.EQ);
            Object[] objArr = this.values;
            sb.append(objArr[i] == this ? "(this map)" : objArr[i]);
        }
        sb.append(JsonReaderKt.END_OBJ);
        return sb.toString();
    }

    public SortedArrayStringMap(int i) {
        String[] strArr = EMPTY;
        this.keys = strArr;
        this.values = strArr;
        if (i >= 0) {
            this.threshold = ceilingNextPowerOfTwo(i == 0 ? 1 : i);
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Initial capacity must be at least zero but was ", i));
    }

    private static int hashCode(Object[] objArr, int i) {
        int i2 = 1;
        for (int i3 = 0; i3 < i; i3++) {
            i2 = (i2 * 31) + (objArr[i3] == null ? 0 : objArr[i3].hashCode());
        }
        return i2;
    }

    @Override // org.apache.logging.log4j.util.ReadOnlyStringMap
    public <V, T> void forEach(TriConsumer<String, ? super V, T> triConsumer, T t) {
        this.iterating = true;
        for (int i = 0; i < this.size; i++) {
            try {
                triConsumer.accept(this.keys[i], this.values[i], t);
            } finally {
                this.iterating = false;
            }
        }
    }

    public SortedArrayStringMap(ReadOnlyStringMap readOnlyStringMap) {
        String[] strArr = EMPTY;
        this.keys = strArr;
        this.values = strArr;
        if (readOnlyStringMap instanceof SortedArrayStringMap) {
            initFrom0((SortedArrayStringMap) readOnlyStringMap);
        } else if (readOnlyStringMap == null) {
        } else {
            resize(ceilingNextPowerOfTwo(readOnlyStringMap.size()));
            readOnlyStringMap.forEach(PUT_ALL, this);
        }
    }

    public SortedArrayStringMap(Map<String, ?> map) {
        String[] strArr = EMPTY;
        this.keys = strArr;
        this.values = strArr;
        resize(ceilingNextPowerOfTwo(map.size()));
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            putValue(entry.getKey(), entry.getValue());
        }
    }
}
