package org.joda.time.convert;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class ConverterSet {
    private final Converter[] iConverters;
    private Entry[] iSelectEntries = new Entry[16];

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class Entry {
        final Converter iConverter;
        final Class<?> iType;

        Entry(Class<?> cls, Converter converter) {
            this.iType = cls;
            this.iConverter = converter;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConverterSet(Converter[] converterArr) {
        this.iConverters = converterArr;
    }

    private static Converter selectSlow(ConverterSet converterSet, Class<?> cls) {
        Converter[] converterArr = converterSet.iConverters;
        int length = converterArr.length;
        ConverterSet converterSet2 = converterSet;
        int i = length;
        while (true) {
            length--;
            if (length < 0) {
                if (cls == null || i == 0) {
                    return null;
                }
                if (i == 1) {
                    return converterArr[0];
                }
                ConverterSet converterSet3 = converterSet2;
                Converter[] converterArr2 = converterArr;
                int i2 = i;
                while (true) {
                    int i3 = i - 1;
                    if (i3 < 0) {
                        break;
                    }
                    Class<?> supportedType = converterArr2[i3].getSupportedType();
                    Converter[] converterArr3 = converterArr2;
                    int i4 = i3;
                    int i5 = i2;
                    while (true) {
                        i2--;
                        if (i2 >= 0) {
                            if (i2 != i4 && converterArr3[i2].getSupportedType().isAssignableFrom(supportedType)) {
                                converterSet3 = converterSet3.remove(i2, (Converter[]) null);
                                converterArr3 = converterSet3.iConverters;
                                i5 = converterArr3.length;
                                i4 = i5 - 1;
                            }
                        }
                    }
                    i2 = i5;
                    i = i4;
                    converterArr2 = converterArr3;
                }
                if (i2 == 1) {
                    return converterArr2[0];
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to find best converter for type \"");
                GeneratedOutlineSupport1.outline146(cls, outline107, "\" from remaining set: ");
                for (int i6 = 0; i6 < i2; i6++) {
                    Converter converter = converterArr2[i6];
                    Class<?> supportedType2 = converter.getSupportedType();
                    outline107.append(converter.getClass().getName());
                    outline107.append(JsonReaderKt.BEGIN_LIST);
                    outline107.append(supportedType2 == null ? null : supportedType2.getName());
                    outline107.append("], ");
                }
                throw new IllegalStateException(outline107.toString());
            }
            Converter converter2 = converterArr[length];
            Class<?> supportedType3 = converter2.getSupportedType();
            if (supportedType3 == cls) {
                return converter2;
            }
            if (supportedType3 == null || (cls != null && !supportedType3.isAssignableFrom(cls))) {
                converterSet2 = converterSet2.remove(length, (Converter[]) null);
                converterArr = converterSet2.iConverters;
                i = converterArr.length;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConverterSet add(Converter converter, Converter[] converterArr) {
        Converter[] converterArr2 = this.iConverters;
        int length = converterArr2.length;
        for (int i = 0; i < length; i++) {
            Converter converter2 = converterArr2[i];
            if (converter.equals(converter2)) {
                if (converterArr != null) {
                    converterArr[0] = null;
                }
                return this;
            } else if (converter.getSupportedType() == converter2.getSupportedType()) {
                Converter[] converterArr3 = new Converter[length];
                for (int i2 = 0; i2 < length; i2++) {
                    if (i2 != i) {
                        converterArr3[i2] = converterArr2[i2];
                    } else {
                        converterArr3[i2] = converter;
                    }
                }
                if (converterArr != null) {
                    converterArr[0] = converter2;
                }
                return new ConverterSet(converterArr3);
            }
        }
        Converter[] converterArr4 = new Converter[length + 1];
        System.arraycopy(converterArr2, 0, converterArr4, 0, length);
        converterArr4[length] = converter;
        if (converterArr != null) {
            converterArr[0] = null;
        }
        return new ConverterSet(converterArr4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void copyInto(Converter[] converterArr) {
        Converter[] converterArr2 = this.iConverters;
        System.arraycopy(converterArr2, 0, converterArr, 0, converterArr2.length);
    }

    ConverterSet remove(int i, Converter[] converterArr) {
        Converter[] converterArr2 = this.iConverters;
        int length = converterArr2.length;
        if (i < length) {
            if (converterArr != null) {
                converterArr[0] = converterArr2[i];
            }
            Converter[] converterArr3 = new Converter[length - 1];
            int i2 = 0;
            for (int i3 = 0; i3 < length; i3++) {
                if (i3 != i) {
                    converterArr3[i2] = converterArr2[i3];
                    i2++;
                }
            }
            return new ConverterSet(converterArr3);
        }
        throw new IndexOutOfBoundsException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConverterSet remove(Converter converter, Converter[] converterArr) {
        Converter[] converterArr2 = this.iConverters;
        int length = converterArr2.length;
        for (int i = 0; i < length; i++) {
            if (converter.equals(converterArr2[i])) {
                return remove(i, converterArr);
            }
        }
        if (converterArr != null) {
            converterArr[0] = null;
        }
        return this;
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
        */
    /* JADX WARN: Removed duplicated region for block: B:36:0x001f A[EDGE_INSN: B:36:0x001f->B:15:0x001f ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0012  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x001d -> B:6:0x000e). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x005a -> B:28:0x0052). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    org.joda.time.convert.Converter select(java.lang.Class<?> r10) throws java.lang.IllegalStateException {
        /*
            r9 = this;
            org.joda.time.convert.ConverterSet$Entry[] r0 = r9.iSelectEntries
            int r1 = r0.length
            r2 = 0
            if (r10 != 0) goto L7
            goto L1d
        L7:
            int r3 = r10.hashCode()
            int r4 = r1 + (-1)
            r3 = r3 & r4
        Le:
            r4 = r0[r3]
            if (r4 == 0) goto L1f
            java.lang.Class<?> r5 = r4.iType
            if (r5 != r10) goto L19
            org.joda.time.convert.Converter r10 = r4.iConverter
            return r10
        L19:
            int r3 = r3 + 1
            if (r3 < r1) goto Le
        L1d:
            r3 = r2
            goto Le
        L1f:
            org.joda.time.convert.Converter r4 = selectSlow(r9, r10)
            org.joda.time.convert.ConverterSet$Entry r5 = new org.joda.time.convert.ConverterSet$Entry
            r5.<init>(r10, r4)
            java.lang.Object r10 = r0.clone()
            org.joda.time.convert.ConverterSet$Entry[] r10 = (org.joda.time.convert.ConverterSet.Entry[]) r10
            r10[r3] = r5
            r0 = r2
        L31:
            if (r0 >= r1) goto L3d
            r3 = r10[r0]
            if (r3 != 0) goto L3a
            r9.iSelectEntries = r10
            return r4
        L3a:
            int r0 = r0 + 1
            goto L31
        L3d:
            int r0 = r1 << 1
            org.joda.time.convert.ConverterSet$Entry[] r3 = new org.joda.time.convert.ConverterSet.Entry[r0]
            r5 = r2
        L42:
            if (r5 >= r1) goto L61
            r6 = r10[r5]
            java.lang.Class<?> r7 = r6.iType
            if (r7 != 0) goto L4b
            goto L5a
        L4b:
            int r7 = r7.hashCode()
            int r8 = r0 + (-1)
            r7 = r7 & r8
        L52:
            r8 = r3[r7]
            if (r8 == 0) goto L5c
            int r7 = r7 + 1
            if (r7 < r0) goto L52
        L5a:
            r7 = r2
            goto L52
        L5c:
            r3[r7] = r6
            int r5 = r5 + 1
            goto L42
        L61:
            r9.iSelectEntries = r3
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.joda.time.convert.ConverterSet.select(java.lang.Class):org.joda.time.convert.Converter");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int size() {
        return this.iConverters.length;
    }
}
