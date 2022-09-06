package com.horcrux.svg;

import android.graphics.Path;
import android.graphics.RectF;
import java.util.ArrayList;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
/* loaded from: classes3.dex */
class PathParser {
    static ArrayList<PathElement> elements;
    private static int i;
    private static int l;
    private static Path mPath;
    private static boolean mPenDown;
    private static float mPenDownX;
    private static float mPenDownY;
    private static float mPenX;
    private static float mPenY;
    private static float mPivotX;
    private static float mPivotY;
    static float mScale;
    private static String s;

    PathParser() {
    }

    private static void arc(float f, float f2, float f3, boolean z, boolean z2, float f4, float f5) {
        arcTo(f, f2, f3, z, z2, f4 + mPenX, f5 + mPenY);
    }

    private static void arcTo(float f, float f2, float f3, boolean z, boolean z2, float f4, float f5) {
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        float f11 = mPenX;
        float f12 = mPenY;
        float abs = Math.abs(f2 == 0.0f ? f == 0.0f ? f5 - f12 : f : f2);
        float abs2 = Math.abs(f == 0.0f ? f4 - f11 : f);
        if (abs2 != 0.0f && abs != 0.0f && (f4 != f11 || f5 != f12)) {
            float radians = (float) Math.toRadians(f3);
            double d = radians;
            float cos = (float) Math.cos(d);
            float sin = (float) Math.sin(d);
            float f13 = f4 - f11;
            float f14 = f5 - f12;
            float f15 = ((sin * f14) / 2.0f) + ((cos * f13) / 2.0f);
            float f16 = -sin;
            float f17 = ((cos * f14) / 2.0f) + ((f16 * f13) / 2.0f);
            float f18 = abs2 * abs2;
            float f19 = abs * abs * f15 * f15;
            if ((((f18 * abs) * abs) - ((f18 * f17) * f17)) - f19 < 0.0f) {
                float sqrt = (float) Math.sqrt(1.0f - (f8 / f6));
                abs2 *= sqrt;
                abs *= sqrt;
                f10 = f13 / 2.0f;
                f9 = f14 / 2.0f;
            } else {
                float sqrt2 = (float) Math.sqrt(f8 / (f7 + f19));
                if (z == z2) {
                    sqrt2 = -sqrt2;
                }
                float f20 = (((-sqrt2) * f17) * abs2) / abs;
                float f21 = ((sqrt2 * f15) * abs) / abs2;
                f9 = (f21 * cos) + (f20 * sin) + (f14 / 2.0f);
                f10 = (f13 / 2.0f) + ((cos * f20) - (sin * f21));
            }
            float f22 = cos / abs2;
            float f23 = sin / abs2;
            float f24 = f16 / abs;
            float f25 = cos / abs;
            float f26 = -f10;
            float f27 = -f9;
            float f28 = abs;
            float f29 = abs2;
            float atan2 = (float) Math.atan2((f25 * f27) + (f24 * f26), (f27 * f23) + (f26 * f22));
            float f30 = f13 - f10;
            float f31 = f14 - f9;
            float atan22 = (float) Math.atan2((f25 * f31) + (f24 * f30), (f23 * f31) + (f22 * f30));
            float f32 = f10 + f11;
            float f33 = f9 + f12;
            float f34 = f13 + f11;
            float f35 = f14 + f12;
            setPenDown();
            mPivotX = f34;
            mPenX = f34;
            mPivotY = f35;
            mPenY = f35;
            if (f29 == f28 && radians == 0.0f) {
                float degrees = (float) Math.toDegrees(atan2);
                float abs3 = Math.abs((degrees - ((float) Math.toDegrees(atan22))) % 360.0f);
                if (!z ? abs3 > 180.0f : abs3 < 180.0f) {
                    abs3 = 360.0f - abs3;
                }
                if (!z2) {
                    abs3 = -abs3;
                }
                float f36 = mScale;
                mPath.arcTo(new RectF((f32 - f29) * f36, (f33 - f29) * f36, (f32 + f29) * f36, (f33 + f29) * f36), degrees, abs3);
                elements.add(new PathElement(ElementType.kCGPathElementAddCurveToPoint, new Point[]{new Point(f34, f35)}));
                return;
            }
            arcToBezier(f32, f33, f29, f28, atan2, atan22, z2, radians);
            return;
        }
        lineTo(f4, f5);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0068 A[LOOP:0: B:12:0x0066->B:13:0x0068, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void arcToBezier(float r24, float r25, float r26, float r27, float r28, float r29, boolean r30, float r31) {
        /*
            Method dump skipped, instructions count: 286
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.PathParser.arcToBezier(float, float, float, float, float, float, boolean, float):void");
    }

    private static void close() {
        if (mPenDown) {
            mPenX = mPenDownX;
            mPenY = mPenDownY;
            mPenDown = false;
            mPath.close();
            elements.add(new PathElement(ElementType.kCGPathElementCloseSubpath, new Point[]{new Point(mPenX, mPenY)}));
        }
    }

    private static void cubicTo(float f, float f2, float f3, float f4, float f5, float f6) {
        setPenDown();
        mPenX = f5;
        mPenY = f6;
        Path path = mPath;
        float f7 = mScale;
        path.cubicTo(f * f7, f2 * f7, f3 * f7, f4 * f7, f5 * f7, f6 * f7);
        elements.add(new PathElement(ElementType.kCGPathElementAddCurveToPoint, new Point[]{new Point(f, f2), new Point(f3, f4), new Point(f5, f6)}));
    }

    private static void curve(float f, float f2, float f3, float f4, float f5, float f6) {
        float f7 = mPenX;
        float f8 = mPenY;
        curveTo(f + f7, f2 + f8, f3 + f7, f4 + f8, f5 + f7, f6 + f8);
    }

    private static void curveTo(float f, float f2, float f3, float f4, float f5, float f6) {
        mPivotX = f3;
        mPivotY = f4;
        cubicTo(f, f2, f3, f4, f5, f6);
    }

    private static boolean is_absolute(char c) {
        return Character.isUpperCase(c);
    }

    private static boolean is_cmd(char c) {
        switch (c) {
            case 'A':
            case 'C':
            case 'H':
            case 'L':
            case 'M':
            case 'Q':
            case 'S':
            case 'T':
            case 'V':
            case 'Z':
            case 'a':
            case 'c':
            case 'h':
            case 'l':
            case 'm':
            case 'q':
            case 's':
            case 't':
            case 'v':
            case 'z':
                return true;
            default:
                return false;
        }
    }

    private static boolean is_number_start(char c) {
        return (c >= '0' && c <= '9') || c == '.' || c == '-' || c == '+';
    }

    private static void line(float f, float f2) {
        lineTo(f + mPenX, f2 + mPenY);
    }

    private static void lineTo(float f, float f2) {
        setPenDown();
        mPenX = f;
        mPivotX = f;
        mPenY = f2;
        mPivotY = f2;
        Path path = mPath;
        float f3 = mScale;
        path.lineTo(f * f3, f3 * f2);
        elements.add(new PathElement(ElementType.kCGPathElementAddLineToPoint, new Point[]{new Point(f, f2)}));
    }

    private static void move(float f, float f2) {
        moveTo(f + mPenX, f2 + mPenY);
    }

    private static void moveTo(float f, float f2) {
        mPenX = f;
        mPivotX = f;
        mPenDownX = f;
        mPenY = f2;
        mPivotY = f2;
        mPenDownY = f2;
        Path path = mPath;
        float f3 = mScale;
        path.moveTo(f * f3, f3 * f2);
        elements.add(new PathElement(ElementType.kCGPathElementMoveToPoint, new Point[]{new Point(f, f2)}));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Path parse(String str) {
        boolean z;
        elements = new ArrayList<>();
        mPath = new Path();
        if (str == null) {
            return mPath;
        }
        l = str.length();
        s = str;
        i = 0;
        mPenX = 0.0f;
        mPenY = 0.0f;
        mPivotX = 0.0f;
        mPivotY = 0.0f;
        mPenDownX = 0.0f;
        mPenDownY = 0.0f;
        mPenDown = false;
        char c = ' ';
        while (i < l) {
            skip_spaces();
            if (i < l) {
                boolean z2 = c != ' ';
                char charAt = s.charAt(i);
                if (!z2 && charAt != 'M' && charAt != 'm') {
                    throw new Error(String.format("Unexpected character '%c' (i=%d, s=%s)", Character.valueOf(charAt), Integer.valueOf(i), s));
                }
                if (is_cmd(charAt)) {
                    i++;
                    z = false;
                    c = charAt;
                } else if (!is_number_start(charAt) || !z2) {
                    throw new Error(String.format("Unexpected character '%c' (i=%d, s=%s)", Character.valueOf(charAt), Integer.valueOf(i), s));
                } else {
                    if (c == 'Z' || c == 'z') {
                        throw new Error(String.format("Unexpected number after 'z' (s=%s)", s));
                    }
                    if (c == 'M' || c == 'm') {
                        c = is_absolute(c) ? Matrix.MATRIX_TYPE_RANDOM_LT : 'l';
                        z = true;
                    } else {
                        z = false;
                    }
                }
                boolean is_absolute = is_absolute(c);
                switch (c) {
                    case 'A':
                        arcTo(parse_list_number(), parse_list_number(), parse_list_number(), parse_flag(), parse_flag(), parse_list_number(), parse_list_number());
                        break;
                    case 'C':
                        curveTo(parse_list_number(), parse_list_number(), parse_list_number(), parse_list_number(), parse_list_number(), parse_list_number());
                        break;
                    case 'H':
                        lineTo(parse_list_number(), mPenY);
                        break;
                    case 'L':
                        lineTo(parse_list_number(), parse_list_number());
                        break;
                    case 'M':
                        moveTo(parse_list_number(), parse_list_number());
                        break;
                    case 'Q':
                        quadraticBezierCurveTo(parse_list_number(), parse_list_number(), parse_list_number(), parse_list_number());
                        break;
                    case 'S':
                        smoothCurveTo(parse_list_number(), parse_list_number(), parse_list_number(), parse_list_number());
                        break;
                    case 'T':
                        smoothQuadraticBezierCurveTo(parse_list_number(), parse_list_number());
                        break;
                    case 'V':
                        lineTo(mPenX, parse_list_number());
                        break;
                    case 'Z':
                    case 'z':
                        close();
                        break;
                    case 'a':
                        arc(parse_list_number(), parse_list_number(), parse_list_number(), parse_flag(), parse_flag(), parse_list_number(), parse_list_number());
                        break;
                    case 'c':
                        curve(parse_list_number(), parse_list_number(), parse_list_number(), parse_list_number(), parse_list_number(), parse_list_number());
                        break;
                    case 'h':
                        line(parse_list_number(), 0.0f);
                        break;
                    case 'l':
                        line(parse_list_number(), parse_list_number());
                        break;
                    case 'm':
                        move(parse_list_number(), parse_list_number());
                        break;
                    case 'q':
                        quadraticBezierCurve(parse_list_number(), parse_list_number(), parse_list_number(), parse_list_number());
                        break;
                    case 's':
                        smoothCurve(parse_list_number(), parse_list_number(), parse_list_number(), parse_list_number());
                        break;
                    case 't':
                        smoothQuadraticBezierCurve(parse_list_number(), parse_list_number());
                        break;
                    case 'v':
                        line(0.0f, parse_list_number());
                        break;
                    default:
                        throw new Error(String.format("Unexpected comand '%c' (s=%s)", Character.valueOf(c), s));
                }
                if (z) {
                    c = is_absolute ? 'M' : 'm';
                }
            } else {
                return mPath;
            }
        }
        return mPath;
    }

    private static boolean parse_flag() {
        skip_spaces();
        char charAt = s.charAt(i);
        if (charAt != '0' && charAt != '1') {
            throw new Error(String.format("Unexpected flag '%c' (i=%d, s=%s)", Character.valueOf(charAt), Integer.valueOf(i), s));
        }
        i++;
        int i2 = i;
        if (i2 < l && s.charAt(i2) == ',') {
            i++;
        }
        skip_spaces();
        return charAt == '1';
    }

    private static float parse_list_number() {
        if (i != l) {
            float parse_number = parse_number();
            skip_spaces();
            parse_list_separator();
            return parse_number;
        }
        throw new Error(String.format("Unexpected end (s=%s)", s));
    }

    private static void parse_list_separator() {
        int i2 = i;
        if (i2 >= l || s.charAt(i2) != ',') {
            return;
        }
        i++;
    }

    private static float parse_number() {
        char charAt;
        skip_spaces();
        int i2 = i;
        if (i2 != l) {
            char charAt2 = s.charAt(i2);
            if (charAt2 == '-' || charAt2 == '+') {
                i++;
                charAt2 = s.charAt(i);
            }
            if (charAt2 >= '0' && charAt2 <= '9') {
                skip_digits();
                int i3 = i;
                if (i3 < l) {
                    charAt2 = s.charAt(i3);
                }
            } else if (charAt2 != '.') {
                throw new Error(String.format("Invalid number formating character '%c' (i=%d, s=%s)", Character.valueOf(charAt2), Integer.valueOf(i), s));
            }
            if (charAt2 == '.') {
                i++;
                skip_digits();
                int i4 = i;
                if (i4 < l) {
                    charAt2 = s.charAt(i4);
                }
            }
            if (charAt2 == 'e' || charAt2 == 'E') {
                int i5 = i;
                if (i5 + 1 < l && (charAt = s.charAt(i5 + 1)) != 'm' && charAt != 'x') {
                    i++;
                    char charAt3 = s.charAt(i);
                    if (charAt3 == '+' || charAt3 == '-') {
                        i++;
                        skip_digits();
                    } else if (charAt3 >= '0' && charAt3 <= '9') {
                        skip_digits();
                    } else {
                        throw new Error(String.format("Invalid number formating character '%c' (i=%d, s=%s)", Character.valueOf(charAt3), Integer.valueOf(i), s));
                    }
                }
            }
            String substring = s.substring(i2, i);
            float parseFloat = Float.parseFloat(substring);
            if (!Float.isInfinite(parseFloat) && !Float.isNaN(parseFloat)) {
                return parseFloat;
            }
            throw new Error(String.format("Invalid number '%s' (start=%d, i=%d, s=%s)", substring, Integer.valueOf(i2), Integer.valueOf(i), s));
        }
        throw new Error(String.format("Unexpected end (s=%s)", s));
    }

    private static void quadraticBezierCurve(float f, float f2, float f3, float f4) {
        float f5 = mPenX;
        float f6 = mPenY;
        quadraticBezierCurveTo(f + f5, f2 + f6, f3 + f5, f4 + f6);
    }

    private static void quadraticBezierCurveTo(float f, float f2, float f3, float f4) {
        mPivotX = f;
        mPivotY = f2;
        float f5 = f * 2.0f;
        float f6 = f2 * 2.0f;
        float f7 = (mPenX + f5) / 3.0f;
        float f8 = (mPenY + f6) / 3.0f;
        cubicTo(f7, f8, (f3 + f5) / 3.0f, (f4 + f6) / 3.0f, f3, f4);
    }

    private static double round(double d) {
        double pow = Math.pow(10.0d, 4.0d);
        return Math.round(d * pow) / pow;
    }

    private static void setPenDown() {
        if (!mPenDown) {
            mPenDownX = mPenX;
            mPenDownY = mPenY;
            mPenDown = true;
        }
    }

    private static void skip_digits() {
        while (true) {
            int i2 = i;
            if (i2 >= l || !Character.isDigit(s.charAt(i2))) {
                return;
            }
            i++;
        }
    }

    private static void skip_spaces() {
        while (true) {
            int i2 = i;
            if (i2 >= l || !Character.isWhitespace(s.charAt(i2))) {
                return;
            }
            i++;
        }
    }

    private static void smoothCurve(float f, float f2, float f3, float f4) {
        float f5 = mPenX;
        float f6 = mPenY;
        smoothCurveTo(f + f5, f2 + f6, f3 + f5, f4 + f6);
    }

    private static void smoothCurveTo(float f, float f2, float f3, float f4) {
        mPivotX = f;
        mPivotY = f2;
        cubicTo((mPenX * 2.0f) - mPivotX, (mPenY * 2.0f) - mPivotY, f, f2, f3, f4);
    }

    private static void smoothQuadraticBezierCurve(float f, float f2) {
        smoothQuadraticBezierCurveTo(f + mPenX, f2 + mPenY);
    }

    private static void smoothQuadraticBezierCurveTo(float f, float f2) {
        quadraticBezierCurveTo((mPenX * 2.0f) - mPivotX, (mPenY * 2.0f) - mPivotY, f, f2);
    }
}
