package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Layout;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewParent;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.text.ReactFontManager;
import com.horcrux.svg.TextProperties;
import java.text.Bidi;
import java.util.ArrayList;
import javax.annotation.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@SuppressLint({"ViewConstructor"})
/* loaded from: classes3.dex */
public class TSpanView extends TextView {
    private static final String FONTS = "fonts/";
    private static final String OTF = ".otf";
    private static final String TTF = ".ttf";
    static final String additionalLigatures = "'hlig', 'cala', ";
    static final String defaultFeatures = "'rlig', 'liga', 'clig', 'calt', 'locl', 'ccmp', 'mark', 'mkmk','kern', ";
    static final String disableDiscretionaryLigatures = "'liga' 0, 'clig' 0, 'dlig' 0, 'hlig' 0, 'cala' 0, ";
    static final String fontWeightTag = "'wght' ";
    private static final double radToDeg = 57.29577951308232d;
    static final String requiredFontFeatures = "'rlig', 'liga', 'clig', 'calt', 'locl', 'ccmp', 'mark', 'mkmk',";
    private static final double tau = 6.283185307179586d;
    private final AssetManager assets;
    private final ArrayList<String> emoji;
    private final ArrayList<Matrix> emojiTransforms;
    private Path mCachedPath;
    @Nullable
    String mContent;
    private TextPathView textPath;

    /* renamed from: com.horcrux.svg.TSpanView$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline = new int[TextProperties.AlignmentBaseline.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$TextProperties$TextAnchor;
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$TextProperties$TextLengthAdjust;

        static {
            try {
                $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline[TextProperties.AlignmentBaseline.baseline.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline[TextProperties.AlignmentBaseline.textBottom.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline[TextProperties.AlignmentBaseline.afterEdge.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline[TextProperties.AlignmentBaseline.textAfterEdge.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline[TextProperties.AlignmentBaseline.alphabetic.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline[TextProperties.AlignmentBaseline.ideographic.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline[TextProperties.AlignmentBaseline.middle.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline[TextProperties.AlignmentBaseline.central.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline[TextProperties.AlignmentBaseline.mathematical.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline[TextProperties.AlignmentBaseline.hanging.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline[TextProperties.AlignmentBaseline.textTop.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline[TextProperties.AlignmentBaseline.beforeEdge.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline[TextProperties.AlignmentBaseline.textBeforeEdge.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline[TextProperties.AlignmentBaseline.bottom.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline[TextProperties.AlignmentBaseline.center.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline[TextProperties.AlignmentBaseline.top.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            $SwitchMap$com$horcrux$svg$TextProperties$TextLengthAdjust = new int[TextProperties.TextLengthAdjust.values().length];
            try {
                $SwitchMap$com$horcrux$svg$TextProperties$TextLengthAdjust[TextProperties.TextLengthAdjust.spacing.ordinal()] = 1;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$horcrux$svg$TextProperties$TextLengthAdjust[TextProperties.TextLengthAdjust.spacingAndGlyphs.ordinal()] = 2;
            } catch (NoSuchFieldError unused18) {
            }
            $SwitchMap$com$horcrux$svg$TextProperties$TextAnchor = new int[TextProperties.TextAnchor.values().length];
            try {
                $SwitchMap$com$horcrux$svg$TextProperties$TextAnchor[TextProperties.TextAnchor.start.ordinal()] = 1;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$horcrux$svg$TextProperties$TextAnchor[TextProperties.TextAnchor.middle.ordinal()] = 2;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$horcrux$svg$TextProperties$TextAnchor[TextProperties.TextAnchor.end.ordinal()] = 3;
            } catch (NoSuchFieldError unused21) {
            }
        }
    }

    public TSpanView(ReactContext reactContext) {
        super(reactContext);
        this.emoji = new ArrayList<>();
        this.emojiTransforms = new ArrayList<>();
        this.assets = this.mContext.getResources().getAssets();
    }

    private void applySpacingAndFeatures(Paint paint, FontData fontData) {
        int i = Build.VERSION.SDK_INT;
        double d = fontData.letterSpacing;
        paint.setLetterSpacing((float) (d / (fontData.fontSize * this.mScale)));
        if (d == FrostVideoEffectController.VIDEO_STRENGTH_CLEAR && fontData.fontVariantLigatures == TextProperties.FontVariantLigatures.normal) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("'rlig', 'liga', 'clig', 'calt', 'locl', 'ccmp', 'mark', 'mkmk','kern', 'hlig', 'cala', ");
            outline107.append(fontData.fontFeatureSettings);
            paint.setFontFeatureSettings(outline107.toString());
        } else {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("'rlig', 'liga', 'clig', 'calt', 'locl', 'ccmp', 'mark', 'mkmk','kern', 'liga' 0, 'clig' 0, 'dlig' 0, 'hlig' 0, 'cala' 0, ");
            outline1072.append(fontData.fontFeatureSettings);
            paint.setFontFeatureSettings(outline1072.toString());
        }
        int i2 = Build.VERSION.SDK_INT;
        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107(fontWeightTag);
        outline1073.append(fontData.absoluteFontWeight);
        outline1073.append(fontData.fontVariationSettings);
        paint.setFontVariationSettings(outline1073.toString());
    }

    private void applyTextPropertiesToPaint(Paint paint, FontData fontData) {
        int i = 0;
        boolean z = fontData.fontWeight == TextProperties.FontWeight.Bold || fontData.absoluteFontWeight >= 550;
        boolean z2 = fontData.fontStyle == TextProperties.FontStyle.italic;
        if (z && z2) {
            i = 3;
        } else if (z) {
            i = 1;
        } else if (z2) {
            i = 2;
        }
        Typeface typeface = null;
        int i2 = fontData.absoluteFontWeight;
        String str = fontData.fontFamily;
        if (str != null && str.length() > 0) {
            String outline75 = GeneratedOutlineSupport1.outline75(FONTS, str, OTF);
            String outline752 = GeneratedOutlineSupport1.outline75(FONTS, str, TTF);
            int i3 = Build.VERSION.SDK_INT;
            Typeface.Builder builder = new Typeface.Builder(this.assets, outline75);
            builder.setFontVariationSettings(fontWeightTag + i2 + fontData.fontVariationSettings);
            builder.setWeight(i2);
            builder.setItalic(z2);
            Typeface build = builder.build();
            if (build == null) {
                Typeface.Builder builder2 = new Typeface.Builder(this.assets, outline752);
                builder2.setFontVariationSettings(fontWeightTag + i2 + fontData.fontVariationSettings);
                builder2.setWeight(i2);
                builder2.setItalic(z2);
                typeface = builder2.build();
            } else {
                typeface = build;
            }
        }
        if (typeface == null) {
            try {
                typeface = ReactFontManager.getInstance().getTypeface(str, i, this.assets);
            } catch (Exception unused) {
            }
        }
        if (Build.VERSION.SDK_INT >= 28) {
            typeface = Typeface.create(typeface, i2, z2);
        }
        paint.setLinearText(true);
        paint.setSubpixelText(true);
        paint.setTypeface(typeface);
        paint.setTextSize((float) (fontData.fontSize * this.mScale));
        int i4 = Build.VERSION.SDK_INT;
        paint.setLetterSpacing(0.0f);
    }

    private void drawWrappedText(Canvas canvas, Paint paint) {
        Layout.Alignment alignment;
        GlyphContext textRootGlyphContext = getTextRootGlyphContext();
        pushGlyphContext();
        FontData font = textRootGlyphContext.getFont();
        TextPaint textPaint = new TextPaint(paint);
        applyTextPropertiesToPaint(textPaint, font);
        applySpacingAndFeatures(textPaint, font);
        double fontSize = textRootGlyphContext.getFontSize();
        int ordinal = font.textAnchor.ordinal();
        if (ordinal == 1) {
            alignment = Layout.Alignment.ALIGN_CENTER;
        } else if (ordinal != 2) {
            alignment = Layout.Alignment.ALIGN_NORMAL;
        } else {
            alignment = Layout.Alignment.ALIGN_OPPOSITE;
        }
        StaticLayout staticLayout = getStaticLayout(textPaint, alignment, true, new SpannableString(this.mContent), (int) PropHelper.fromRelative(this.mInlineSize, canvas.getWidth(), FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, this.mScale, fontSize));
        int lineAscent = staticLayout.getLineAscent(0);
        popGlyphContext();
        canvas.save();
        canvas.translate((float) textRootGlyphContext.nextX(FrostVideoEffectController.VIDEO_STRENGTH_CLEAR), (float) (textRootGlyphContext.nextY() + lineAscent));
        staticLayout.draw(canvas);
        canvas.restore();
    }

    private double getAbsoluteStartOffset(SVGLength sVGLength, double d, double d2) {
        return PropHelper.fromRelative(sVGLength, d, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, this.mScale, d2);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0242, code lost:
        if (r7.equals("sub") != false) goto L75;
     */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0245  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.graphics.Path getLinePath(java.lang.String r67, android.graphics.Paint r68, android.graphics.Canvas r69) {
        /*
            Method dump skipped, instructions count: 1398
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.TSpanView.getLinePath(java.lang.String, android.graphics.Paint, android.graphics.Canvas):android.graphics.Path");
    }

    private StaticLayout getStaticLayout(TextPaint textPaint, Layout.Alignment alignment, boolean z, SpannableString spannableString, int i) {
        int i2 = Build.VERSION.SDK_INT;
        return StaticLayout.Builder.obtain(spannableString, 0, spannableString.length(), textPaint, i).setAlignment(alignment).setLineSpacing(0.0f, 1.0f).setIncludePad(z).setBreakStrategy(1).setHyphenationFrequency(1).build();
    }

    private double getTextAnchorOffset(TextProperties.TextAnchor textAnchor, double d) {
        int ordinal = textAnchor.ordinal();
        return ordinal != 1 ? ordinal != 2 ? FrostVideoEffectController.VIDEO_STRENGTH_CLEAR : -d : (-d) / 2.0d;
    }

    private void setupTextPath() {
        for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
            if (parent.getClass() == TextPathView.class) {
                this.textPath = (TextPathView) parent;
                return;
            } else if (!(parent instanceof TextView)) {
                return;
            }
        }
    }

    public static String visualToLogical(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        Bidi bidi = new Bidi(str, -2);
        if (bidi.isLeftToRight()) {
            return str;
        }
        int runCount = bidi.getRunCount();
        byte[] bArr = new byte[runCount];
        Integer[] numArr = new Integer[runCount];
        for (int i = 0; i < runCount; i++) {
            bArr[i] = (byte) bidi.getRunLevel(i);
            numArr[i] = Integer.valueOf(i);
        }
        Bidi.reorderVisually(bArr, 0, numArr, 0, runCount);
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < runCount; i2++) {
            int intValue = numArr[i2].intValue();
            int runStart = bidi.getRunStart(intValue);
            int runLimit = bidi.getRunLimit(intValue);
            if ((bArr[intValue] & 1) != 0) {
                while (true) {
                    runLimit--;
                    if (runLimit >= runStart) {
                        sb.append(str.charAt(runLimit));
                    }
                }
            } else {
                sb.append((CharSequence) str, runStart, runLimit);
            }
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.TextView, com.horcrux.svg.VirtualView
    public void clearCache() {
        this.mCachedPath = null;
        super.clearCache();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.TextView, com.horcrux.svg.GroupView, com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public void draw(Canvas canvas, Paint paint, float f) {
        if (this.mContent != null) {
            SVGLength sVGLength = this.mInlineSize;
            if (sVGLength != null && sVGLength.value != FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
                if (setupFillPaint(paint, this.fillOpacity * f)) {
                    drawWrappedText(canvas, paint);
                }
                if (!setupStrokePaint(paint, f * this.strokeOpacity)) {
                    return;
                }
                drawWrappedText(canvas, paint);
                return;
            }
            int size = this.emoji.size();
            if (size > 0) {
                applyTextPropertiesToPaint(paint, getTextRootGlyphContext().getFont());
                for (int i = 0; i < size; i++) {
                    canvas.save();
                    canvas.concat(this.emojiTransforms.get(i));
                    canvas.drawText(this.emoji.get(i), 0.0f, 0.0f, paint);
                    canvas.restore();
                }
            }
            drawPath(canvas, paint, f);
            return;
        }
        clip(canvas, paint);
        drawGroup(canvas, paint, f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.TextView, com.horcrux.svg.GroupView, com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public Path getPath(Canvas canvas, Paint paint) {
        Path path = this.mCachedPath;
        if (path != null) {
            return path;
        }
        if (this.mContent == null) {
            this.mCachedPath = getGroupPath(canvas, paint);
            return this.mCachedPath;
        }
        setupTextPath();
        pushGlyphContext();
        this.mCachedPath = getLinePath(visualToLogical(this.mContent), paint, canvas);
        popGlyphContext();
        return this.mCachedPath;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.TextView
    public double getSubtreeTextChunksTotalAdvance(Paint paint) {
        if (!Double.isNaN(this.cachedAdvance)) {
            return this.cachedAdvance;
        }
        String str = this.mContent;
        double d = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        if (str == null) {
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (childAt instanceof TextView) {
                    d += ((TextView) childAt).getSubtreeTextChunksTotalAdvance(paint);
                }
            }
            this.cachedAdvance = d;
            return d;
        } else if (str.length() == 0) {
            this.cachedAdvance = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
            return FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        } else {
            FontData font = getTextRootGlyphContext().getFont();
            applyTextPropertiesToPaint(paint, font);
            applySpacingAndFeatures(paint, font);
            this.cachedAdvance = paint.measureText(str);
            return this.cachedAdvance;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.GroupView, com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public int hitTest(float[] fArr) {
        Region region;
        if (this.mContent == null) {
            return super.hitTest(fArr);
        }
        if (((VirtualView) this).mPath != null && this.mInvertible && this.mTransformInvertible) {
            float[] fArr2 = new float[2];
            this.mInvMatrix.mapPoints(fArr2, fArr);
            this.mInvTransform.mapPoints(fArr2);
            int round = Math.round(fArr2[0]);
            int round2 = Math.round(fArr2[1]);
            initBounds();
            Region region2 = this.mRegion;
            if ((region2 != null && region2.contains(round, round2)) || ((region = this.mStrokeRegion) != null && region.contains(round, round2))) {
                if (getClipPath() != null && !this.mClipRegion.contains(round, round2)) {
                    return -1;
                }
                return getId();
            }
        }
        return -1;
    }

    @Override // com.horcrux.svg.TextView, com.horcrux.svg.VirtualView, android.view.View
    public void invalidate() {
        this.mCachedPath = null;
        super.invalidate();
    }

    @ReactProp(name = "content")
    public void setContent(@Nullable String str) {
        this.mContent = str;
        invalidate();
    }
}
