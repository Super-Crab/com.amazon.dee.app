package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import androidx.annotation.Nullable;
import androidx.collection.LongSparseArray;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.TextDelegate;
import com.airbnb.lottie.animation.content.ContentGroup;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TextKeyframeAnimation;
import com.airbnb.lottie.model.DocumentData;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.FontCharacter;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.model.content.ShapeGroup;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
/* loaded from: classes.dex */
public class TextLayer extends BaseLayer {
    private final LongSparseArray<String> codePointCache;
    @Nullable
    private BaseKeyframeAnimation<Integer, Integer> colorAnimation;
    private final LottieComposition composition;
    private final Map<FontCharacter, List<ContentGroup>> contentsForCharacter;
    private final Paint fillPaint;
    private final LottieDrawable lottieDrawable;
    private final Matrix matrix;
    private final RectF rectF;
    private final StringBuilder stringBuilder;
    @Nullable
    private BaseKeyframeAnimation<Integer, Integer> strokeColorAnimation;
    private final Paint strokePaint;
    @Nullable
    private BaseKeyframeAnimation<Float, Float> strokeWidthAnimation;
    private final TextKeyframeAnimation textAnimation;
    @Nullable
    private BaseKeyframeAnimation<Float, Float> trackingAnimation;

    /* renamed from: com.airbnb.lottie.model.layer.TextLayer$3  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$lottie$model$DocumentData$Justification = new int[DocumentData.Justification.values().length];

        static {
            try {
                $SwitchMap$com$airbnb$lottie$model$DocumentData$Justification[DocumentData.Justification.LEFT_ALIGN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$DocumentData$Justification[DocumentData.Justification.RIGHT_ALIGN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$DocumentData$Justification[DocumentData.Justification.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextLayer(LottieDrawable lottieDrawable, Layer layer) {
        super(lottieDrawable, layer);
        AnimatableFloatValue animatableFloatValue;
        AnimatableFloatValue animatableFloatValue2;
        AnimatableColorValue animatableColorValue;
        AnimatableColorValue animatableColorValue2;
        this.stringBuilder = new StringBuilder(2);
        this.rectF = new RectF();
        this.matrix = new Matrix();
        this.fillPaint = new Paint(1) { // from class: com.airbnb.lottie.model.layer.TextLayer.1
            {
                setStyle(Paint.Style.FILL);
            }
        };
        this.strokePaint = new Paint(1) { // from class: com.airbnb.lottie.model.layer.TextLayer.2
            {
                setStyle(Paint.Style.STROKE);
            }
        };
        this.contentsForCharacter = new HashMap();
        this.codePointCache = new LongSparseArray<>();
        this.lottieDrawable = lottieDrawable;
        this.composition = layer.getComposition();
        this.textAnimation = layer.getText().mo271createAnimation();
        this.textAnimation.addUpdateListener(this);
        addAnimation(this.textAnimation);
        AnimatableTextProperties textProperties = layer.getTextProperties();
        if (textProperties != null && (animatableColorValue2 = textProperties.color) != null) {
            this.colorAnimation = animatableColorValue2.mo271createAnimation();
            this.colorAnimation.addUpdateListener(this);
            addAnimation(this.colorAnimation);
        }
        if (textProperties != null && (animatableColorValue = textProperties.stroke) != null) {
            this.strokeColorAnimation = animatableColorValue.mo271createAnimation();
            this.strokeColorAnimation.addUpdateListener(this);
            addAnimation(this.strokeColorAnimation);
        }
        if (textProperties != null && (animatableFloatValue2 = textProperties.strokeWidth) != null) {
            this.strokeWidthAnimation = animatableFloatValue2.mo271createAnimation();
            this.strokeWidthAnimation.addUpdateListener(this);
            addAnimation(this.strokeWidthAnimation);
        }
        if (textProperties == null || (animatableFloatValue = textProperties.tracking) == null) {
            return;
        }
        this.trackingAnimation = animatableFloatValue.mo271createAnimation();
        this.trackingAnimation.addUpdateListener(this);
        addAnimation(this.trackingAnimation);
    }

    private void applyJustification(DocumentData.Justification justification, Canvas canvas, float f) {
        int ordinal = justification.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                canvas.translate(-f, 0.0f);
            } else if (ordinal != 2) {
            } else {
                canvas.translate((-f) / 2.0f, 0.0f);
            }
        }
    }

    private String codePointToString(String str, int i) {
        int codePointAt = str.codePointAt(i);
        int charCount = Character.charCount(codePointAt) + i;
        while (charCount < str.length()) {
            int codePointAt2 = str.codePointAt(charCount);
            if (!isModifier(codePointAt2)) {
                break;
            }
            charCount += Character.charCount(codePointAt2);
            codePointAt = (codePointAt * 31) + codePointAt2;
        }
        long j = codePointAt;
        if (this.codePointCache.containsKey(j)) {
            return this.codePointCache.get(j);
        }
        this.stringBuilder.setLength(0);
        while (i < charCount) {
            int codePointAt3 = str.codePointAt(i);
            this.stringBuilder.appendCodePoint(codePointAt3);
            i += Character.charCount(codePointAt3);
        }
        String sb = this.stringBuilder.toString();
        this.codePointCache.put(j, sb);
        return sb;
    }

    private void drawCharacter(String str, Paint paint, Canvas canvas) {
        if (paint.getColor() == 0) {
            return;
        }
        if (paint.getStyle() == Paint.Style.STROKE && paint.getStrokeWidth() == 0.0f) {
            return;
        }
        canvas.drawText(str, 0, str.length(), 0.0f, 0.0f, paint);
    }

    private void drawCharacterAsGlyph(FontCharacter fontCharacter, Matrix matrix, float f, DocumentData documentData, Canvas canvas) {
        List<ContentGroup> contentsForCharacter = getContentsForCharacter(fontCharacter);
        for (int i = 0; i < contentsForCharacter.size(); i++) {
            Path path = contentsForCharacter.get(i).getPath();
            path.computeBounds(this.rectF, false);
            this.matrix.set(matrix);
            this.matrix.preTranslate(0.0f, Utils.dpScale() * ((float) (-documentData.baselineShift)));
            this.matrix.preScale(f, f);
            path.transform(this.matrix);
            if (documentData.strokeOverFill) {
                drawGlyph(path, this.fillPaint, canvas);
                drawGlyph(path, this.strokePaint, canvas);
            } else {
                drawGlyph(path, this.strokePaint, canvas);
                drawGlyph(path, this.fillPaint, canvas);
            }
        }
    }

    private void drawCharacterFromFont(String str, DocumentData documentData, Canvas canvas) {
        if (documentData.strokeOverFill) {
            drawCharacter(str, this.fillPaint, canvas);
            drawCharacter(str, this.strokePaint, canvas);
            return;
        }
        drawCharacter(str, this.strokePaint, canvas);
        drawCharacter(str, this.fillPaint, canvas);
    }

    private void drawFontTextLine(String str, DocumentData documentData, Canvas canvas, float f) {
        int i = 0;
        while (i < str.length()) {
            String codePointToString = codePointToString(str, i);
            i += codePointToString.length();
            drawCharacterFromFont(codePointToString, documentData, canvas);
            float measureText = this.fillPaint.measureText(codePointToString, 0, 1);
            float f2 = documentData.tracking / 10.0f;
            BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation = this.trackingAnimation;
            if (baseKeyframeAnimation != null) {
                f2 += baseKeyframeAnimation.mo268getValue().floatValue();
            }
            canvas.translate((f2 * f) + measureText, 0.0f);
        }
    }

    private void drawGlyph(Path path, Paint paint, Canvas canvas) {
        if (paint.getColor() == 0) {
            return;
        }
        if (paint.getStyle() == Paint.Style.STROKE && paint.getStrokeWidth() == 0.0f) {
            return;
        }
        canvas.drawPath(path, paint);
    }

    private void drawGlyphTextLine(String str, DocumentData documentData, Matrix matrix, Font font, Canvas canvas, float f, float f2) {
        for (int i = 0; i < str.length(); i++) {
            FontCharacter fontCharacter = this.composition.getCharacters().get(FontCharacter.hashFor(str.charAt(i), font.getFamily(), font.getStyle()));
            if (fontCharacter != null) {
                drawCharacterAsGlyph(fontCharacter, matrix, f2, documentData, canvas);
                float dpScale = Utils.dpScale() * ((float) fontCharacter.getWidth()) * f2 * f;
                float f3 = documentData.tracking / 10.0f;
                BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation = this.trackingAnimation;
                if (baseKeyframeAnimation != null) {
                    f3 += baseKeyframeAnimation.mo268getValue().floatValue();
                }
                canvas.translate((f3 * f) + dpScale, 0.0f);
            }
        }
    }

    private void drawTextGlyphs(DocumentData documentData, Matrix matrix, Font font, Canvas canvas) {
        float f = ((float) documentData.size) / 100.0f;
        float scale = Utils.getScale(matrix);
        String str = documentData.text;
        float dpScale = Utils.dpScale() * ((float) documentData.lineHeight);
        List<String> textLines = getTextLines(str);
        int size = textLines.size();
        for (int i = 0; i < size; i++) {
            String str2 = textLines.get(i);
            float textLineWidthForGlyphs = getTextLineWidthForGlyphs(str2, font, f, scale);
            canvas.save();
            applyJustification(documentData.justification, canvas, textLineWidthForGlyphs);
            canvas.translate(0.0f, (i * dpScale) - (((size - 1) * dpScale) / 2.0f));
            drawGlyphTextLine(str2, documentData, matrix, font, canvas, scale, f);
            canvas.restore();
        }
    }

    private void drawTextWithFont(DocumentData documentData, Font font, Matrix matrix, Canvas canvas) {
        float scale = Utils.getScale(matrix);
        Typeface typeface = this.lottieDrawable.getTypeface(font.getFamily(), font.getStyle());
        if (typeface == null) {
            return;
        }
        String str = documentData.text;
        TextDelegate textDelegate = this.lottieDrawable.getTextDelegate();
        if (textDelegate != null) {
            str = textDelegate.getTextInternal(str);
        }
        this.fillPaint.setTypeface(typeface);
        this.fillPaint.setTextSize((float) (documentData.size * Utils.dpScale()));
        this.strokePaint.setTypeface(this.fillPaint.getTypeface());
        this.strokePaint.setTextSize(this.fillPaint.getTextSize());
        float dpScale = Utils.dpScale() * ((float) documentData.lineHeight);
        List<String> textLines = getTextLines(str);
        int size = textLines.size();
        for (int i = 0; i < size; i++) {
            String str2 = textLines.get(i);
            applyJustification(documentData.justification, canvas, this.strokePaint.measureText(str2));
            canvas.translate(0.0f, (i * dpScale) - (((size - 1) * dpScale) / 2.0f));
            drawFontTextLine(str2, documentData, canvas, scale);
            canvas.setMatrix(matrix);
        }
    }

    private List<ContentGroup> getContentsForCharacter(FontCharacter fontCharacter) {
        if (this.contentsForCharacter.containsKey(fontCharacter)) {
            return this.contentsForCharacter.get(fontCharacter);
        }
        List<ShapeGroup> shapes = fontCharacter.getShapes();
        int size = shapes.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(new ContentGroup(this.lottieDrawable, this, shapes.get(i)));
        }
        this.contentsForCharacter.put(fontCharacter, arrayList);
        return arrayList;
    }

    private float getTextLineWidthForGlyphs(String str, Font font, float f, float f2) {
        float f3 = 0.0f;
        for (int i = 0; i < str.length(); i++) {
            FontCharacter fontCharacter = this.composition.getCharacters().get(FontCharacter.hashFor(str.charAt(i), font.getFamily(), font.getStyle()));
            if (fontCharacter != null) {
                f3 = (float) ((fontCharacter.getWidth() * f * Utils.dpScale() * f2) + f3);
            }
        }
        return f3;
    }

    private List<String> getTextLines(String str) {
        return Arrays.asList(str.replaceAll("\r\n", StringUtils.CR).replaceAll("\n", StringUtils.CR).split(StringUtils.CR));
    }

    private boolean isModifier(int i) {
        return Character.getType(i) == 16 || Character.getType(i) == 27 || Character.getType(i) == 6 || Character.getType(i) == 28 || Character.getType(i) == 19;
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.model.KeyPathElement
    public <T> void addValueCallback(T t, @Nullable LottieValueCallback<T> lottieValueCallback) {
        BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation;
        BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation2;
        BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation3;
        BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation4;
        super.addValueCallback(t, lottieValueCallback);
        if (t == LottieProperty.COLOR && (baseKeyframeAnimation4 = this.colorAnimation) != null) {
            baseKeyframeAnimation4.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.STROKE_COLOR && (baseKeyframeAnimation3 = this.strokeColorAnimation) != null) {
            baseKeyframeAnimation3.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.STROKE_WIDTH && (baseKeyframeAnimation2 = this.strokeWidthAnimation) != null) {
            baseKeyframeAnimation2.setValueCallback(lottieValueCallback);
        } else if (t != LottieProperty.TEXT_TRACKING || (baseKeyframeAnimation = this.trackingAnimation) == null) {
        } else {
            baseKeyframeAnimation.setValueCallback(lottieValueCallback);
        }
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    void drawLayer(Canvas canvas, Matrix matrix, int i) {
        canvas.save();
        if (!this.lottieDrawable.useTextGlyphs()) {
            canvas.setMatrix(matrix);
        }
        DocumentData mo268getValue = this.textAnimation.mo268getValue();
        Font font = this.composition.getFonts().get(mo268getValue.fontName);
        if (font == null) {
            canvas.restore();
            return;
        }
        BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation = this.colorAnimation;
        if (baseKeyframeAnimation != null) {
            this.fillPaint.setColor(baseKeyframeAnimation.mo268getValue().intValue());
        } else {
            this.fillPaint.setColor(mo268getValue.color);
        }
        BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2 = this.strokeColorAnimation;
        if (baseKeyframeAnimation2 != null) {
            this.strokePaint.setColor(baseKeyframeAnimation2.mo268getValue().intValue());
        } else {
            this.strokePaint.setColor(mo268getValue.strokeColor);
        }
        int intValue = ((this.transform.getOpacity() == null ? 100 : this.transform.getOpacity().mo268getValue().intValue()) * 255) / 100;
        this.fillPaint.setAlpha(intValue);
        this.strokePaint.setAlpha(intValue);
        BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation3 = this.strokeWidthAnimation;
        if (baseKeyframeAnimation3 != null) {
            this.strokePaint.setStrokeWidth(baseKeyframeAnimation3.mo268getValue().floatValue());
        } else {
            this.strokePaint.setStrokeWidth((float) (mo268getValue.strokeWidth * Utils.dpScale() * Utils.getScale(matrix)));
        }
        if (this.lottieDrawable.useTextGlyphs()) {
            drawTextGlyphs(mo268getValue, matrix, font, canvas);
        } else {
            drawTextWithFont(mo268getValue, font, matrix, canvas);
        }
        canvas.restore();
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.animation.content.DrawingContent
    public void getBounds(RectF rectF, Matrix matrix, boolean z) {
        super.getBounds(rectF, matrix, z);
        rectF.set(0.0f, 0.0f, this.composition.getBounds().width(), this.composition.getBounds().height());
    }
}
