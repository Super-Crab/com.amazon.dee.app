package org.apache.commons.lang3.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
/* loaded from: classes4.dex */
public class DiffBuilder implements Builder<DiffResult> {
    private final List<Diff<?>> diffs;
    private final Object left;
    private final boolean objectsTriviallyEqual;
    private final Object right;
    private final ToStringStyle style;

    public DiffBuilder(Object obj, Object obj2, ToStringStyle toStringStyle, boolean z) {
        if (obj != null) {
            if (obj2 != null) {
                this.diffs = new ArrayList();
                this.left = obj;
                this.right = obj2;
                this.style = toStringStyle;
                this.objectsTriviallyEqual = z && (obj == obj2 || obj.equals(obj2));
                return;
            }
            throw new IllegalArgumentException("rhs cannot be null");
        }
        throw new IllegalArgumentException("lhs cannot be null");
    }

    public DiffBuilder append(String str, final boolean z, final boolean z2) {
        if (str != null) {
            if (!this.objectsTriviallyEqual && z != z2) {
                this.diffs.add(new Diff<Boolean>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.1
                    private static final long serialVersionUID = 1;

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getLeft */
                    public Boolean mo12815getLeft() {
                        return Boolean.valueOf(z);
                    }

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getRight */
                    public Boolean mo12816getRight() {
                        return Boolean.valueOf(z2);
                    }
                });
            }
            return this;
        }
        throw new IllegalArgumentException("Field name cannot be null");
    }

    @Override // org.apache.commons.lang3.builder.Builder
    /* renamed from: build  reason: collision with other method in class */
    public DiffResult mo12838build() {
        return new DiffResult(this.left, this.right, this.diffs, this.style);
    }

    public DiffBuilder append(String str, final boolean[] zArr, final boolean[] zArr2) {
        if (str != null) {
            if (!this.objectsTriviallyEqual && !Arrays.equals(zArr, zArr2)) {
                this.diffs.add(new Diff<Boolean[]>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.2
                    private static final long serialVersionUID = 1;

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getLeft  reason: collision with other method in class */
                    public Boolean[] mo12815getLeft() {
                        return ArrayUtils.toObject(zArr);
                    }

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getRight  reason: collision with other method in class */
                    public Boolean[] mo12816getRight() {
                        return ArrayUtils.toObject(zArr2);
                    }
                });
            }
            return this;
        }
        throw new IllegalArgumentException("Field name cannot be null");
    }

    public DiffBuilder append(String str, final byte b, final byte b2) {
        if (str != null) {
            if (!this.objectsTriviallyEqual && b != b2) {
                this.diffs.add(new Diff<Byte>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.3
                    private static final long serialVersionUID = 1;

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getLeft */
                    public Byte mo12815getLeft() {
                        return Byte.valueOf(b);
                    }

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getRight */
                    public Byte mo12816getRight() {
                        return Byte.valueOf(b2);
                    }
                });
            }
            return this;
        }
        throw new IllegalArgumentException("Field name cannot be null");
    }

    public DiffBuilder(Object obj, Object obj2, ToStringStyle toStringStyle) {
        this(obj, obj2, toStringStyle, true);
    }

    public DiffBuilder append(String str, final byte[] bArr, final byte[] bArr2) {
        if (str != null) {
            if (!this.objectsTriviallyEqual && !Arrays.equals(bArr, bArr2)) {
                this.diffs.add(new Diff<Byte[]>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.4
                    private static final long serialVersionUID = 1;

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getLeft  reason: collision with other method in class */
                    public Byte[] mo12815getLeft() {
                        return ArrayUtils.toObject(bArr);
                    }

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getRight  reason: collision with other method in class */
                    public Byte[] mo12816getRight() {
                        return ArrayUtils.toObject(bArr2);
                    }
                });
            }
            return this;
        }
        throw new IllegalArgumentException("Field name cannot be null");
    }

    public DiffBuilder append(String str, final char c, final char c2) {
        if (str != null) {
            if (!this.objectsTriviallyEqual && c != c2) {
                this.diffs.add(new Diff<Character>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.5
                    private static final long serialVersionUID = 1;

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getLeft */
                    public Character mo12815getLeft() {
                        return Character.valueOf(c);
                    }

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getRight */
                    public Character mo12816getRight() {
                        return Character.valueOf(c2);
                    }
                });
            }
            return this;
        }
        throw new IllegalArgumentException("Field name cannot be null");
    }

    public DiffBuilder append(String str, final char[] cArr, final char[] cArr2) {
        if (str != null) {
            if (!this.objectsTriviallyEqual && !Arrays.equals(cArr, cArr2)) {
                this.diffs.add(new Diff<Character[]>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.6
                    private static final long serialVersionUID = 1;

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getLeft  reason: collision with other method in class */
                    public Character[] mo12815getLeft() {
                        return ArrayUtils.toObject(cArr);
                    }

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getRight  reason: collision with other method in class */
                    public Character[] mo12816getRight() {
                        return ArrayUtils.toObject(cArr2);
                    }
                });
            }
            return this;
        }
        throw new IllegalArgumentException("Field name cannot be null");
    }

    public DiffBuilder append(String str, final double d, final double d2) {
        if (str != null) {
            if (!this.objectsTriviallyEqual && Double.doubleToLongBits(d) != Double.doubleToLongBits(d2)) {
                this.diffs.add(new Diff<Double>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.7
                    private static final long serialVersionUID = 1;

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getLeft */
                    public Double mo12815getLeft() {
                        return Double.valueOf(d);
                    }

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getRight */
                    public Double mo12816getRight() {
                        return Double.valueOf(d2);
                    }
                });
            }
            return this;
        }
        throw new IllegalArgumentException("Field name cannot be null");
    }

    public DiffBuilder append(String str, final double[] dArr, final double[] dArr2) {
        if (str != null) {
            if (!this.objectsTriviallyEqual && !Arrays.equals(dArr, dArr2)) {
                this.diffs.add(new Diff<Double[]>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.8
                    private static final long serialVersionUID = 1;

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getLeft  reason: collision with other method in class */
                    public Double[] mo12815getLeft() {
                        return ArrayUtils.toObject(dArr);
                    }

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getRight  reason: collision with other method in class */
                    public Double[] mo12816getRight() {
                        return ArrayUtils.toObject(dArr2);
                    }
                });
            }
            return this;
        }
        throw new IllegalArgumentException("Field name cannot be null");
    }

    public DiffBuilder append(String str, final float f, final float f2) {
        if (str != null) {
            if (!this.objectsTriviallyEqual && Float.floatToIntBits(f) != Float.floatToIntBits(f2)) {
                this.diffs.add(new Diff<Float>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.9
                    private static final long serialVersionUID = 1;

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getLeft */
                    public Float mo12815getLeft() {
                        return Float.valueOf(f);
                    }

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getRight */
                    public Float mo12816getRight() {
                        return Float.valueOf(f2);
                    }
                });
            }
            return this;
        }
        throw new IllegalArgumentException("Field name cannot be null");
    }

    public DiffBuilder append(String str, final float[] fArr, final float[] fArr2) {
        if (str != null) {
            if (!this.objectsTriviallyEqual && !Arrays.equals(fArr, fArr2)) {
                this.diffs.add(new Diff<Float[]>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.10
                    private static final long serialVersionUID = 1;

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getLeft  reason: collision with other method in class */
                    public Float[] mo12815getLeft() {
                        return ArrayUtils.toObject(fArr);
                    }

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getRight  reason: collision with other method in class */
                    public Float[] mo12816getRight() {
                        return ArrayUtils.toObject(fArr2);
                    }
                });
            }
            return this;
        }
        throw new IllegalArgumentException("Field name cannot be null");
    }

    public DiffBuilder append(String str, final int i, final int i2) {
        if (str != null) {
            if (!this.objectsTriviallyEqual && i != i2) {
                this.diffs.add(new Diff<Integer>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.11
                    private static final long serialVersionUID = 1;

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getLeft */
                    public Integer mo12815getLeft() {
                        return Integer.valueOf(i);
                    }

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getRight */
                    public Integer mo12816getRight() {
                        return Integer.valueOf(i2);
                    }
                });
            }
            return this;
        }
        throw new IllegalArgumentException("Field name cannot be null");
    }

    public DiffBuilder append(String str, final int[] iArr, final int[] iArr2) {
        if (str != null) {
            if (!this.objectsTriviallyEqual && !Arrays.equals(iArr, iArr2)) {
                this.diffs.add(new Diff<Integer[]>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.12
                    private static final long serialVersionUID = 1;

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getLeft  reason: collision with other method in class */
                    public Integer[] mo12815getLeft() {
                        return ArrayUtils.toObject(iArr);
                    }

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getRight  reason: collision with other method in class */
                    public Integer[] mo12816getRight() {
                        return ArrayUtils.toObject(iArr2);
                    }
                });
            }
            return this;
        }
        throw new IllegalArgumentException("Field name cannot be null");
    }

    public DiffBuilder append(String str, final long j, final long j2) {
        if (str != null) {
            if (!this.objectsTriviallyEqual && j != j2) {
                this.diffs.add(new Diff<Long>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.13
                    private static final long serialVersionUID = 1;

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getLeft */
                    public Long mo12815getLeft() {
                        return Long.valueOf(j);
                    }

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getRight */
                    public Long mo12816getRight() {
                        return Long.valueOf(j2);
                    }
                });
            }
            return this;
        }
        throw new IllegalArgumentException("Field name cannot be null");
    }

    public DiffBuilder append(String str, final long[] jArr, final long[] jArr2) {
        if (str != null) {
            if (!this.objectsTriviallyEqual && !Arrays.equals(jArr, jArr2)) {
                this.diffs.add(new Diff<Long[]>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.14
                    private static final long serialVersionUID = 1;

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getLeft  reason: collision with other method in class */
                    public Long[] mo12815getLeft() {
                        return ArrayUtils.toObject(jArr);
                    }

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getRight  reason: collision with other method in class */
                    public Long[] mo12816getRight() {
                        return ArrayUtils.toObject(jArr2);
                    }
                });
            }
            return this;
        }
        throw new IllegalArgumentException("Field name cannot be null");
    }

    public DiffBuilder append(String str, final short s, final short s2) {
        if (str != null) {
            if (!this.objectsTriviallyEqual && s != s2) {
                this.diffs.add(new Diff<Short>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.15
                    private static final long serialVersionUID = 1;

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getLeft  reason: collision with other method in class */
                    public Short mo12815getLeft() {
                        return Short.valueOf(s);
                    }

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getRight  reason: collision with other method in class */
                    public Short mo12816getRight() {
                        return Short.valueOf(s2);
                    }
                });
            }
            return this;
        }
        throw new IllegalArgumentException("Field name cannot be null");
    }

    public DiffBuilder append(String str, final short[] sArr, final short[] sArr2) {
        if (str != null) {
            if (!this.objectsTriviallyEqual && !Arrays.equals(sArr, sArr2)) {
                this.diffs.add(new Diff<Short[]>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.16
                    private static final long serialVersionUID = 1;

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getLeft  reason: collision with other method in class */
                    public Short[] mo12815getLeft() {
                        return ArrayUtils.toObject(sArr);
                    }

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getRight  reason: collision with other method in class */
                    public Short[] mo12816getRight() {
                        return ArrayUtils.toObject(sArr2);
                    }
                });
            }
            return this;
        }
        throw new IllegalArgumentException("Field name cannot be null");
    }

    public DiffBuilder append(String str, final Object obj, final Object obj2) {
        if (!this.objectsTriviallyEqual && obj != obj2) {
            Object obj3 = obj != null ? obj : obj2;
            if (obj3.getClass().isArray()) {
                if (obj3 instanceof boolean[]) {
                    return append(str, (boolean[]) obj, (boolean[]) obj2);
                }
                if (obj3 instanceof byte[]) {
                    return append(str, (byte[]) obj, (byte[]) obj2);
                }
                if (obj3 instanceof char[]) {
                    return append(str, (char[]) obj, (char[]) obj2);
                }
                if (obj3 instanceof double[]) {
                    return append(str, (double[]) obj, (double[]) obj2);
                }
                if (obj3 instanceof float[]) {
                    return append(str, (float[]) obj, (float[]) obj2);
                }
                if (obj3 instanceof int[]) {
                    return append(str, (int[]) obj, (int[]) obj2);
                }
                if (obj3 instanceof long[]) {
                    return append(str, (long[]) obj, (long[]) obj2);
                }
                if (obj3 instanceof short[]) {
                    return append(str, (short[]) obj, (short[]) obj2);
                }
                return append(str, (Object[]) obj, (Object[]) obj2);
            } else if (obj != null && obj.equals(obj2)) {
                return this;
            } else {
                this.diffs.add(new Diff<Object>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.17
                    private static final long serialVersionUID = 1;

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getLeft */
                    public Object mo12815getLeft() {
                        return obj;
                    }

                    @Override // org.apache.commons.lang3.tuple.Pair
                    /* renamed from: getRight */
                    public Object mo12816getRight() {
                        return obj2;
                    }
                });
                return this;
            }
        }
        return this;
    }

    public DiffBuilder append(String str, final Object[] objArr, final Object[] objArr2) {
        if (!this.objectsTriviallyEqual && !Arrays.equals(objArr, objArr2)) {
            this.diffs.add(new Diff<Object[]>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.18
                private static final long serialVersionUID = 1;

                @Override // org.apache.commons.lang3.tuple.Pair
                /* renamed from: getLeft  reason: collision with other method in class */
                public Object[] mo12815getLeft() {
                    return objArr;
                }

                @Override // org.apache.commons.lang3.tuple.Pair
                /* renamed from: getRight  reason: collision with other method in class */
                public Object[] mo12816getRight() {
                    return objArr2;
                }
            });
        }
        return this;
    }
}
