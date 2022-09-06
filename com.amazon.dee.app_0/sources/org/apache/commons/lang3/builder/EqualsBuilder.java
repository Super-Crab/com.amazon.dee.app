package org.apache.commons.lang3.builder;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;
/* loaded from: classes4.dex */
public class EqualsBuilder implements Builder<Boolean> {
    private static final ThreadLocal<Set<Pair<IDKey, IDKey>>> REGISTRY = new ThreadLocal<>();
    private boolean isEquals = true;

    static Pair<IDKey, IDKey> getRegisterPair(Object obj, Object obj2) {
        return Pair.of(new IDKey(obj), new IDKey(obj2));
    }

    static Set<Pair<IDKey, IDKey>> getRegistry() {
        return REGISTRY.get();
    }

    static boolean isRegistered(Object obj, Object obj2) {
        Set<Pair<IDKey, IDKey>> registry = getRegistry();
        Pair<IDKey, IDKey> registerPair = getRegisterPair(obj, obj2);
        return registry != null && (registry.contains(registerPair) || registry.contains(Pair.of(registerPair.mo12815getLeft(), registerPair.mo12816getRight())));
    }

    private static void reflectionAppend(Object obj, Object obj2, Class<?> cls, EqualsBuilder equalsBuilder, boolean z, String[] strArr) {
        if (isRegistered(obj, obj2)) {
            return;
        }
        try {
            register(obj, obj2);
            Field[] declaredFields = cls.getDeclaredFields();
            AccessibleObject.setAccessible(declaredFields, true);
            for (int i = 0; i < declaredFields.length && equalsBuilder.isEquals; i++) {
                Field field = declaredFields[i];
                if (!ArrayUtils.contains(strArr, field.getName()) && field.getName().indexOf(36) == -1 && ((z || !Modifier.isTransient(field.getModifiers())) && !Modifier.isStatic(field.getModifiers()))) {
                    try {
                        equalsBuilder.append(field.get(obj), field.get(obj2));
                    } catch (IllegalAccessException unused) {
                        throw new InternalError("Unexpected IllegalAccessException");
                    }
                }
            }
        } finally {
            unregister(obj, obj2);
        }
    }

    public static boolean reflectionEquals(Object obj, Object obj2, Collection<String> collection) {
        return reflectionEquals(obj, obj2, ReflectionToStringBuilder.toNoNullStringArray(collection));
    }

    static void register(Object obj, Object obj2) {
        synchronized (EqualsBuilder.class) {
            if (getRegistry() == null) {
                REGISTRY.set(new HashSet());
            }
        }
        getRegistry().add(getRegisterPair(obj, obj2));
    }

    static void unregister(Object obj, Object obj2) {
        Set<Pair<IDKey, IDKey>> registry = getRegistry();
        if (registry != null) {
            registry.remove(getRegisterPair(obj, obj2));
            synchronized (EqualsBuilder.class) {
                Set<Pair<IDKey, IDKey>> registry2 = getRegistry();
                if (registry2 != null && registry2.isEmpty()) {
                    REGISTRY.remove();
                }
            }
        }
    }

    public EqualsBuilder append(Object obj, Object obj2) {
        if (this.isEquals && obj != obj2) {
            if (obj != null && obj2 != null) {
                if (!obj.getClass().isArray()) {
                    this.isEquals = obj.equals(obj2);
                } else if (obj.getClass() != obj2.getClass()) {
                    setEquals(false);
                } else if (obj instanceof long[]) {
                    append((long[]) obj, (long[]) obj2);
                } else if (obj instanceof int[]) {
                    append((int[]) obj, (int[]) obj2);
                } else if (obj instanceof short[]) {
                    append((short[]) obj, (short[]) obj2);
                } else if (obj instanceof char[]) {
                    append((char[]) obj, (char[]) obj2);
                } else if (obj instanceof byte[]) {
                    append((byte[]) obj, (byte[]) obj2);
                } else if (obj instanceof double[]) {
                    append((double[]) obj, (double[]) obj2);
                } else if (obj instanceof float[]) {
                    append((float[]) obj, (float[]) obj2);
                } else if (obj instanceof boolean[]) {
                    append((boolean[]) obj, (boolean[]) obj2);
                } else {
                    append((Object[]) obj, (Object[]) obj2);
                }
                return this;
            }
            setEquals(false);
            return this;
        }
        return this;
    }

    public EqualsBuilder appendSuper(boolean z) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = z;
        return this;
    }

    public boolean isEquals() {
        return this.isEquals;
    }

    public void reset() {
        this.isEquals = true;
    }

    protected void setEquals(boolean z) {
        this.isEquals = z;
    }

    public static boolean reflectionEquals(Object obj, Object obj2, String... strArr) {
        return reflectionEquals(obj, obj2, false, null, strArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.apache.commons.lang3.builder.Builder
    /* renamed from: build */
    public Boolean mo12838build() {
        return Boolean.valueOf(isEquals());
    }

    public static boolean reflectionEquals(Object obj, Object obj2, boolean z) {
        return reflectionEquals(obj, obj2, z, null, new String[0]);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
        if (r2.isInstance(r11) == false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x002a, code lost:
        if (r1.isInstance(r12) == false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x002d, code lost:
        r1 = r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean reflectionEquals(java.lang.Object r11, java.lang.Object r12, boolean r13, java.lang.Class<?> r14, java.lang.String... r15) {
        /*
            if (r11 != r12) goto L4
            r11 = 1
            return r11
        L4:
            r0 = 0
            if (r11 == 0) goto L61
            if (r12 != 0) goto Lb
            goto L61
        Lb:
            java.lang.Class r1 = r11.getClass()
            java.lang.Class r2 = r12.getClass()
            boolean r3 = r1.isInstance(r12)
            if (r3 == 0) goto L20
            boolean r3 = r2.isInstance(r11)
            if (r3 != 0) goto L2e
            goto L2d
        L20:
            boolean r3 = r2.isInstance(r11)
            if (r3 == 0) goto L61
            boolean r3 = r1.isInstance(r12)
            if (r3 != 0) goto L2d
            goto L2e
        L2d:
            r1 = r2
        L2e:
            org.apache.commons.lang3.builder.EqualsBuilder r10 = new org.apache.commons.lang3.builder.EqualsBuilder
            r10.<init>()
            boolean r2 = r1.isArray()     // Catch: java.lang.IllegalArgumentException -> L61
            if (r2 == 0) goto L3d
            r10.append(r11, r12)     // Catch: java.lang.IllegalArgumentException -> L61
            goto L5c
        L3d:
            r4 = r11
            r5 = r12
            r6 = r1
            r7 = r10
            r8 = r13
            r9 = r15
            reflectionAppend(r4, r5, r6, r7, r8, r9)     // Catch: java.lang.IllegalArgumentException -> L61
        L46:
            java.lang.Class r2 = r1.getSuperclass()     // Catch: java.lang.IllegalArgumentException -> L61
            if (r2 == 0) goto L5c
            if (r1 == r14) goto L5c
            java.lang.Class r1 = r1.getSuperclass()     // Catch: java.lang.IllegalArgumentException -> L61
            r2 = r11
            r3 = r12
            r4 = r1
            r5 = r10
            r6 = r13
            r7 = r15
            reflectionAppend(r2, r3, r4, r5, r6, r7)     // Catch: java.lang.IllegalArgumentException -> L61
            goto L46
        L5c:
            boolean r11 = r10.isEquals()
            return r11
        L61:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals(java.lang.Object, java.lang.Object, boolean, java.lang.Class, java.lang.String[]):boolean");
    }

    public EqualsBuilder append(long j, long j2) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = j == j2;
        return this;
    }

    public EqualsBuilder append(int i, int i2) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = i == i2;
        return this;
    }

    public EqualsBuilder append(short s, short s2) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = s == s2;
        return this;
    }

    public EqualsBuilder append(char c, char c2) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = c == c2;
        return this;
    }

    public EqualsBuilder append(byte b, byte b2) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = b == b2;
        return this;
    }

    public EqualsBuilder append(double d, double d2) {
        return !this.isEquals ? this : append(Double.doubleToLongBits(d), Double.doubleToLongBits(d2));
    }

    public EqualsBuilder append(float f, float f2) {
        return !this.isEquals ? this : append(Float.floatToIntBits(f), Float.floatToIntBits(f2));
    }

    public EqualsBuilder append(boolean z, boolean z2) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = z == z2;
        return this;
    }

    public EqualsBuilder append(Object[] objArr, Object[] objArr2) {
        if (this.isEquals && objArr != objArr2) {
            if (objArr != null && objArr2 != null) {
                if (objArr.length != objArr2.length) {
                    setEquals(false);
                    return this;
                }
                for (int i = 0; i < objArr.length && this.isEquals; i++) {
                    append(objArr[i], objArr2[i]);
                }
                return this;
            }
            setEquals(false);
            return this;
        }
        return this;
    }

    public EqualsBuilder append(long[] jArr, long[] jArr2) {
        if (this.isEquals && jArr != jArr2) {
            if (jArr != null && jArr2 != null) {
                if (jArr.length != jArr2.length) {
                    setEquals(false);
                    return this;
                }
                for (int i = 0; i < jArr.length && this.isEquals; i++) {
                    append(jArr[i], jArr2[i]);
                }
                return this;
            }
            setEquals(false);
            return this;
        }
        return this;
    }

    public EqualsBuilder append(int[] iArr, int[] iArr2) {
        if (this.isEquals && iArr != iArr2) {
            if (iArr != null && iArr2 != null) {
                if (iArr.length != iArr2.length) {
                    setEquals(false);
                    return this;
                }
                for (int i = 0; i < iArr.length && this.isEquals; i++) {
                    append(iArr[i], iArr2[i]);
                }
                return this;
            }
            setEquals(false);
            return this;
        }
        return this;
    }

    public EqualsBuilder append(short[] sArr, short[] sArr2) {
        if (this.isEquals && sArr != sArr2) {
            if (sArr != null && sArr2 != null) {
                if (sArr.length != sArr2.length) {
                    setEquals(false);
                    return this;
                }
                for (int i = 0; i < sArr.length && this.isEquals; i++) {
                    append(sArr[i], sArr2[i]);
                }
                return this;
            }
            setEquals(false);
            return this;
        }
        return this;
    }

    public EqualsBuilder append(char[] cArr, char[] cArr2) {
        if (this.isEquals && cArr != cArr2) {
            if (cArr != null && cArr2 != null) {
                if (cArr.length != cArr2.length) {
                    setEquals(false);
                    return this;
                }
                for (int i = 0; i < cArr.length && this.isEquals; i++) {
                    append(cArr[i], cArr2[i]);
                }
                return this;
            }
            setEquals(false);
            return this;
        }
        return this;
    }

    public EqualsBuilder append(byte[] bArr, byte[] bArr2) {
        if (this.isEquals && bArr != bArr2) {
            if (bArr != null && bArr2 != null) {
                if (bArr.length != bArr2.length) {
                    setEquals(false);
                    return this;
                }
                for (int i = 0; i < bArr.length && this.isEquals; i++) {
                    append(bArr[i], bArr2[i]);
                }
                return this;
            }
            setEquals(false);
            return this;
        }
        return this;
    }

    public EqualsBuilder append(double[] dArr, double[] dArr2) {
        if (this.isEquals && dArr != dArr2) {
            if (dArr != null && dArr2 != null) {
                if (dArr.length != dArr2.length) {
                    setEquals(false);
                    return this;
                }
                for (int i = 0; i < dArr.length && this.isEquals; i++) {
                    append(dArr[i], dArr2[i]);
                }
                return this;
            }
            setEquals(false);
            return this;
        }
        return this;
    }

    public EqualsBuilder append(float[] fArr, float[] fArr2) {
        if (this.isEquals && fArr != fArr2) {
            if (fArr != null && fArr2 != null) {
                if (fArr.length != fArr2.length) {
                    setEquals(false);
                    return this;
                }
                for (int i = 0; i < fArr.length && this.isEquals; i++) {
                    append(fArr[i], fArr2[i]);
                }
                return this;
            }
            setEquals(false);
            return this;
        }
        return this;
    }

    public EqualsBuilder append(boolean[] zArr, boolean[] zArr2) {
        if (this.isEquals && zArr != zArr2) {
            if (zArr != null && zArr2 != null) {
                if (zArr.length != zArr2.length) {
                    setEquals(false);
                    return this;
                }
                for (int i = 0; i < zArr.length && this.isEquals; i++) {
                    append(zArr[i], zArr2[i]);
                }
                return this;
            }
            setEquals(false);
            return this;
        }
        return this;
    }
}
