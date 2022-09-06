package com.facebook.react.animated;

import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes2.dex */
class InterpolationAnimatedNode extends ValueAnimatedNode {
    public static final String EXTRAPOLATE_TYPE_CLAMP = "clamp";
    public static final String EXTRAPOLATE_TYPE_EXTEND = "extend";
    public static final String EXTRAPOLATE_TYPE_IDENTITY = "identity";
    private final String mExtrapolateLeft;
    private final String mExtrapolateRight;
    private final boolean mHasStringOutput;
    private final double[] mInputRange;
    private int mNumVals;
    private final double[] mOutputRange;
    private double[][] mOutputs;
    @Nullable
    private ValueAnimatedNode mParent;
    private String mPattern;
    private final Matcher mSOutputMatcher;
    private boolean mShouldRound;
    private static final String fpRegex = "[+-]?(\\d+\\.?\\d*|\\.\\d+)([eE][+-]?\\d+)?";
    private static final Pattern fpPattern = Pattern.compile(fpRegex);

    public InterpolationAnimatedNode(ReadableMap readableMap) {
        this.mInputRange = fromDoubleArray(readableMap.getArray("inputRange"));
        ReadableArray array = readableMap.getArray("outputRange");
        this.mHasStringOutput = array.getType(0) == ReadableType.String;
        if (this.mHasStringOutput) {
            int size = array.size();
            this.mOutputRange = new double[size];
            this.mPattern = array.getString(0);
            this.mShouldRound = this.mPattern.startsWith("rgb");
            this.mSOutputMatcher = fpPattern.matcher(this.mPattern);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < size; i++) {
                Matcher matcher = fpPattern.matcher(array.getString(i));
                ArrayList arrayList2 = new ArrayList();
                arrayList.add(arrayList2);
                while (matcher.find()) {
                    arrayList2.add(Double.valueOf(Double.parseDouble(matcher.group())));
                }
                this.mOutputRange[i] = ((Double) arrayList2.get(0)).doubleValue();
            }
            this.mNumVals = ((ArrayList) arrayList.get(0)).size();
            this.mOutputs = new double[this.mNumVals];
            for (int i2 = 0; i2 < this.mNumVals; i2++) {
                double[] dArr = new double[size];
                this.mOutputs[i2] = dArr;
                for (int i3 = 0; i3 < size; i3++) {
                    dArr[i3] = ((Double) ((ArrayList) arrayList.get(i3)).get(i2)).doubleValue();
                }
            }
        } else {
            this.mOutputRange = fromDoubleArray(array);
            this.mSOutputMatcher = null;
        }
        this.mExtrapolateLeft = readableMap.getString("extrapolateLeft");
        this.mExtrapolateRight = readableMap.getString("extrapolateRight");
    }

    private static int findRangeIndex(double d, double[] dArr) {
        int i = 1;
        while (i < dArr.length - 1 && dArr[i] < d) {
            i++;
        }
        return i - 1;
    }

    private static double[] fromDoubleArray(ReadableArray readableArray) {
        double[] dArr = new double[readableArray.size()];
        for (int i = 0; i < dArr.length; i++) {
            dArr[i] = readableArray.getDouble(i);
        }
        return dArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0057 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x009b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x009c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static double interpolate(double r16, double r18, double r20, double r22, double r24, java.lang.String r26, java.lang.String r27) {
        /*
            r0 = r26
            r1 = r27
            int r2 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            r3 = 0
            java.lang.String r4 = "Invalid extrapolation type "
            java.lang.String r5 = "clamp"
            java.lang.String r6 = "identity"
            java.lang.String r7 = "extend"
            r8 = 94742715(0x5a5a8bb, float:1.5578507E-35)
            r9 = -135761730(0xfffffffff7e870be, float:-9.428903E33)
            r10 = -1289044198(0xffffffffb32abf1a, float:-3.9755015E-8)
            r11 = -1
            r12 = 2
            r13 = 1
            if (r2 >= 0) goto L58
            int r14 = r26.hashCode()
            if (r14 == r10) goto L38
            if (r14 == r9) goto L30
            if (r14 == r8) goto L28
            goto L40
        L28:
            boolean r14 = r0.equals(r5)
            if (r14 == 0) goto L40
            r14 = r13
            goto L41
        L30:
            boolean r14 = r0.equals(r6)
            if (r14 == 0) goto L40
            r14 = r3
            goto L41
        L38:
            boolean r14 = r0.equals(r7)
            if (r14 == 0) goto L40
            r14 = r12
            goto L41
        L40:
            r14 = r11
        L41:
            if (r14 == 0) goto L57
            if (r14 == r13) goto L54
            if (r14 != r12) goto L48
            goto L58
        L48:
            com.facebook.react.bridge.JSApplicationIllegalArgumentException r1 = new com.facebook.react.bridge.JSApplicationIllegalArgumentException
            java.lang.String r2 = "for left extrapolation"
            java.lang.String r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline75(r4, r0, r2)
            r1.<init>(r0)
            throw r1
        L54:
            r14 = r18
            goto L5a
        L57:
            return r16
        L58:
            r14 = r16
        L5a:
            int r0 = (r14 > r20 ? 1 : (r14 == r20 ? 0 : -1))
            if (r0 <= 0) goto L97
            int r0 = r27.hashCode()
            if (r0 == r10) goto L79
            if (r0 == r9) goto L71
            if (r0 == r8) goto L69
            goto L80
        L69:
            boolean r0 = r1.equals(r5)
            if (r0 == 0) goto L80
            r11 = r13
            goto L80
        L71:
            boolean r0 = r1.equals(r6)
            if (r0 == 0) goto L80
            r11 = r3
            goto L80
        L79:
            boolean r0 = r1.equals(r7)
            if (r0 == 0) goto L80
            r11 = r12
        L80:
            if (r11 == 0) goto L96
            if (r11 == r13) goto L93
            if (r11 != r12) goto L87
            goto L97
        L87:
            com.facebook.react.bridge.JSApplicationIllegalArgumentException r0 = new com.facebook.react.bridge.JSApplicationIllegalArgumentException
            java.lang.String r2 = "for right extrapolation"
            java.lang.String r1 = com.android.tools.r8.GeneratedOutlineSupport1.outline75(r4, r1, r2)
            r0.<init>(r1)
            throw r0
        L93:
            r14 = r20
            goto L97
        L96:
            return r14
        L97:
            int r0 = (r22 > r24 ? 1 : (r22 == r24 ? 0 : -1))
            if (r0 != 0) goto L9c
            return r22
        L9c:
            int r0 = (r18 > r20 ? 1 : (r18 == r20 ? 0 : -1))
            if (r0 != 0) goto La4
            if (r2 > 0) goto La3
            return r22
        La3:
            return r24
        La4:
            double r0 = r24 - r22
            double r14 = r14 - r18
            double r14 = r14 * r0
            double r0 = r20 - r18
            double r14 = r14 / r0
            double r14 = r14 + r22
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.animated.InterpolationAnimatedNode.interpolate(double, double, double, double, double, java.lang.String, java.lang.String):double");
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public void onAttachedToNode(AnimatedNode animatedNode) {
        if (this.mParent == null) {
            if (animatedNode instanceof ValueAnimatedNode) {
                this.mParent = (ValueAnimatedNode) animatedNode;
                return;
            }
            throw new IllegalArgumentException("Parent is of an invalid type");
        }
        throw new IllegalStateException("Parent already attached");
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public void onDetachedFromNode(AnimatedNode animatedNode) {
        if (animatedNode == this.mParent) {
            this.mParent = null;
            return;
        }
        throw new IllegalArgumentException("Invalid parent node provided");
    }

    @Override // com.facebook.react.animated.ValueAnimatedNode, com.facebook.react.animated.AnimatedNode
    public String prettyPrint() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("InterpolationAnimatedNode[");
        outline107.append(this.mTag);
        outline107.append("] super: ");
        outline107.append(super.prettyPrint());
        return outline107.toString();
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public void update() {
        ValueAnimatedNode valueAnimatedNode = this.mParent;
        if (valueAnimatedNode == null) {
            return;
        }
        double value = valueAnimatedNode.getValue();
        this.mValue = interpolate(value, this.mInputRange, this.mOutputRange, this.mExtrapolateLeft, this.mExtrapolateRight);
        if (!this.mHasStringOutput) {
            return;
        }
        if (this.mNumVals > 1) {
            StringBuffer stringBuffer = new StringBuffer(this.mPattern.length());
            this.mSOutputMatcher.reset();
            int i = 0;
            while (this.mSOutputMatcher.find()) {
                int i2 = i + 1;
                double interpolate = interpolate(value, this.mInputRange, this.mOutputs[i], this.mExtrapolateLeft, this.mExtrapolateRight);
                if (this.mShouldRound) {
                    boolean z = i2 == 4;
                    if (z) {
                        interpolate *= 1000.0d;
                    }
                    int round = (int) Math.round(interpolate);
                    this.mSOutputMatcher.appendReplacement(stringBuffer, z ? Double.toString(round / 1000.0d) : Integer.toString(round));
                } else {
                    int i3 = (int) interpolate;
                    this.mSOutputMatcher.appendReplacement(stringBuffer, ((double) i3) != interpolate ? Double.toString(interpolate) : Integer.toString(i3));
                }
                i = i2;
            }
            this.mSOutputMatcher.appendTail(stringBuffer);
            this.mAnimatedObject = stringBuffer.toString();
            return;
        }
        this.mAnimatedObject = this.mSOutputMatcher.replaceFirst(String.valueOf(this.mValue));
    }

    static double interpolate(double d, double[] dArr, double[] dArr2, String str, String str2) {
        int findRangeIndex = findRangeIndex(d, dArr);
        int i = findRangeIndex + 1;
        return interpolate(d, dArr[findRangeIndex], dArr[i], dArr2[findRangeIndex], dArr2[i], str, str2);
    }
}
