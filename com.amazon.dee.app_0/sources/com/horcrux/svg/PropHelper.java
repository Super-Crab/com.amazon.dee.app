package com.horcrux.svg;

import com.facebook.react.bridge.ReadableArray;
import com.horcrux.svg.SVGLength;
/* loaded from: classes3.dex */
class PropHelper {
    private static final int inputMatrixDataSize = 6;

    /* renamed from: com.horcrux.svg.PropHelper$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$SVGLength$UnitType = new int[SVGLength.UnitType.values().length];

        static {
            try {
                $SwitchMap$com$horcrux$svg$SVGLength$UnitType[SVGLength.UnitType.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$horcrux$svg$SVGLength$UnitType[SVGLength.UnitType.PX.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$horcrux$svg$SVGLength$UnitType[SVGLength.UnitType.PERCENTAGE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$horcrux$svg$SVGLength$UnitType[SVGLength.UnitType.EMS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$horcrux$svg$SVGLength$UnitType[SVGLength.UnitType.EXS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$horcrux$svg$SVGLength$UnitType[SVGLength.UnitType.CM.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$horcrux$svg$SVGLength$UnitType[SVGLength.UnitType.MM.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$horcrux$svg$SVGLength$UnitType[SVGLength.UnitType.IN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$horcrux$svg$SVGLength$UnitType[SVGLength.UnitType.PT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$horcrux$svg$SVGLength$UnitType[SVGLength.UnitType.PC.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$horcrux$svg$SVGLength$UnitType[SVGLength.UnitType.UNKNOWN.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    PropHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00a7 A[PHI: r8 
      PHI: (r8v3 int) = (r8v1 int), (r8v4 int) binds: [B:50:0x00a3, B:51:0x00a6] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00be  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static double fromRelative(java.lang.String r7, double r8, double r10, double r12) {
        /*
            java.lang.String r7 = r7.trim()
            int r0 = r7.length()
            int r1 = r0 + (-1)
            if (r0 == 0) goto Ld8
            java.lang.String r2 = "normal"
            boolean r2 = r7.equals(r2)
            if (r2 == 0) goto L16
            goto Ld8
        L16:
            int r2 = r7.codePointAt(r1)
            r3 = 37
            r4 = 0
            if (r2 != r3) goto L30
            java.lang.String r7 = r7.substring(r4, r1)
            java.lang.Double r7 = java.lang.Double.valueOf(r7)
            double r10 = r7.doubleValue()
            r12 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r10 = r10 / r12
            double r10 = r10 * r8
            return r10
        L30:
            int r8 = r0 + (-2)
            if (r8 <= 0) goto Lcf
            java.lang.String r9 = r7.substring(r8)
            r1 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r3 = -1
            int r5 = r9.hashCode()
            r6 = 3178(0xc6a, float:4.453E-42)
            if (r5 == r6) goto L98
            r6 = 3240(0xca8, float:4.54E-42)
            if (r5 == r6) goto L8e
            r6 = 3365(0xd25, float:4.715E-42)
            if (r5 == r6) goto L84
            r6 = 3488(0xda0, float:4.888E-42)
            if (r5 == r6) goto L7a
            r6 = 3571(0xdf3, float:5.004E-42)
            if (r5 == r6) goto L70
            r6 = 3588(0xe04, float:5.028E-42)
            if (r5 == r6) goto L66
            r6 = 3592(0xe08, float:5.033E-42)
            if (r5 == r6) goto L5c
            goto La2
        L5c:
            java.lang.String r5 = "px"
            boolean r9 = r9.equals(r5)
            if (r9 == 0) goto La2
            r9 = r4
            goto La3
        L66:
            java.lang.String r5 = "pt"
            boolean r9 = r9.equals(r5)
            if (r9 == 0) goto La2
            r9 = 2
            goto La3
        L70:
            java.lang.String r5 = "pc"
            boolean r9 = r9.equals(r5)
            if (r9 == 0) goto La2
            r9 = 3
            goto La3
        L7a:
            java.lang.String r5 = "mm"
            boolean r9 = r9.equals(r5)
            if (r9 == 0) goto La2
            r9 = 4
            goto La3
        L84:
            java.lang.String r5 = "in"
            boolean r9 = r9.equals(r5)
            if (r9 == 0) goto La2
            r9 = 6
            goto La3
        L8e:
            java.lang.String r5 = "em"
            boolean r9 = r9.equals(r5)
            if (r9 == 0) goto La2
            r9 = 1
            goto La3
        L98:
            java.lang.String r5 = "cm"
            boolean r9 = r9.equals(r5)
            if (r9 == 0) goto La2
            r9 = 5
            goto La3
        La2:
            r9 = r3
        La3:
            switch(r9) {
                case 0: goto La7;
                case 1: goto Lc0;
                case 2: goto Lbe;
                case 3: goto Lbb;
                case 4: goto Lb5;
                case 5: goto Laf;
                case 6: goto La9;
                default: goto La6;
            }
        La6:
            r8 = r0
        La7:
            r12 = r1
            goto Lc0
        La9:
            r12 = 4636033603912859648(0x4056800000000000, double:90.0)
            goto Lc0
        Laf:
            r12 = 4630183578586017914(0x4041b76ed677707a, double:35.43307)
            goto Lc0
        Lb5:
            r12 = 4615161236842447043(0x400c58b1572580c3, double:3.543307)
            goto Lc0
        Lbb:
            r12 = 4624633867356078080(0x402e000000000000, double:15.0)
            goto Lc0
        Lbe:
            r12 = 4608308318706860032(0x3ff4000000000000, double:1.25)
        Lc0:
            java.lang.String r7 = r7.substring(r4, r8)
            java.lang.Double r7 = java.lang.Double.valueOf(r7)
            double r7 = r7.doubleValue()
            double r7 = r7 * r12
        Lcd:
            double r7 = r7 * r10
            return r7
        Lcf:
            java.lang.Double r7 = java.lang.Double.valueOf(r7)
            double r7 = r7.doubleValue()
            goto Lcd
        Ld8:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.PropHelper.fromRelative(java.lang.String, double, double, double):double");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int toMatrixData(ReadableArray readableArray, float[] fArr, float f) {
        int size = readableArray.size();
        if (size != 6) {
            return size;
        }
        fArr[0] = (float) readableArray.getDouble(0);
        fArr[1] = (float) readableArray.getDouble(2);
        fArr[2] = ((float) readableArray.getDouble(4)) * f;
        fArr[3] = (float) readableArray.getDouble(1);
        fArr[4] = (float) readableArray.getDouble(3);
        fArr[5] = ((float) readableArray.getDouble(5)) * f;
        return 6;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static double fromRelative(SVGLength sVGLength, double d, double d2, double d3, double d4) {
        double d5;
        if (sVGLength == null) {
            return d2;
        }
        SVGLength.UnitType unitType = sVGLength.unit;
        double d6 = sVGLength.value;
        switch (unitType.ordinal()) {
            case 1:
            case 5:
                d4 = 1.0d;
                d6 *= d4;
                d5 = d6 * d3;
                break;
            case 2:
                d5 = (d6 / 100.0d) * d;
                break;
            case 3:
                d6 *= d4;
                d5 = d6 * d3;
                break;
            case 4:
                d4 /= 2.0d;
                d6 *= d4;
                d5 = d6 * d3;
                break;
            case 6:
                d4 = 35.43307d;
                d6 *= d4;
                d5 = d6 * d3;
                break;
            case 7:
                d4 = 3.543307d;
                d6 *= d4;
                d5 = d6 * d3;
                break;
            case 8:
                d4 = 90.0d;
                d6 *= d4;
                d5 = d6 * d3;
                break;
            case 9:
                d4 = 1.25d;
                d6 *= d4;
                d5 = d6 * d3;
                break;
            case 10:
                d4 = 15.0d;
                d6 *= d4;
                d5 = d6 * d3;
                break;
            default:
                d5 = d6 * d3;
                break;
        }
        return d5 + d2;
    }
}
