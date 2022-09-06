package com.facebook.react.views.text;

import android.content.Context;
import android.os.Build;
import android.text.BoringLayout;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.LruCache;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactAccessibilityDelegate;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.yoga.YogaConstants;
import com.facebook.yoga.YogaMeasureMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes2.dex */
public class TextLayoutManager {
    private static final boolean DEFAULT_INCLUDE_FONT_PADDING = true;
    private static final boolean ENABLE_MEASURE_LOGGING = false;
    private static final String INCLUDE_FONT_PADDING_KEY = "includeFontPadding";
    private static final String INLINE_VIEW_PLACEHOLDER = "0";
    private static final String MAXIMUM_NUMBER_OF_LINES_KEY = "maximumNumberOfLines";
    private static final String TAG = "TextLayoutManager";
    private static final String TEXT_BREAK_STRATEGY_KEY = "textBreakStrategy";
    private static final int spannableCacheSize = 100;
    private static final TextPaint sTextPaintInstance = new TextPaint(1);
    private static final Object sSpannableCacheLock = new Object();
    private static final LruCache<String, Spannable> sSpannableCache = new LruCache<>(100);
    private static final LruCache<ReadableNativeMap, Spannable> sSpannableCacheV2 = new LruCache<>(100);
    private static final ConcurrentHashMap<Integer, Spannable> sTagToSpannableCache = new ConcurrentHashMap<>();

    /* loaded from: classes2.dex */
    public static class SetSpanOperation {
        protected int end;
        protected int start;
        protected ReactSpan what;

        public SetSpanOperation(int i, int i2, ReactSpan reactSpan) {
            this.start = i;
            this.end = i2;
            this.what = reactSpan;
        }

        public void execute(Spannable spannable, int i) {
            spannable.setSpan(this.what, this.start, this.end, ((i << 16) & 16711680) | ((this.start == 0 ? 18 : 34) & (-16711681)));
        }
    }

    private static void buildSpannableFromFragment(Context context, ReadableArray readableArray, SpannableStringBuilder spannableStringBuilder, List<SetSpanOperation> list) {
        int i;
        int i2 = 0;
        for (int size = readableArray.size(); i2 < size; size = i) {
            ReadableMap mo6944getMap = readableArray.mo6944getMap(i2);
            int length = spannableStringBuilder.length();
            TextAttributeProps textAttributeProps = new TextAttributeProps(new ReactStylesDiffMap(mo6944getMap.mo6945getMap("textAttributes")));
            spannableStringBuilder.append((CharSequence) TextTransform.apply(mo6944getMap.getString("string"), textAttributeProps.mTextTransform));
            int length2 = spannableStringBuilder.length();
            int i3 = mo6944getMap.hasKey("reactTag") ? mo6944getMap.getInt("reactTag") : -1;
            if (mo6944getMap.hasKey(ViewProps.IS_ATTACHMENT) && mo6944getMap.getBoolean(ViewProps.IS_ATTACHMENT)) {
                list.add(new SetSpanOperation(spannableStringBuilder.length() - 1, spannableStringBuilder.length(), new TextInlineViewPlaceholderSpan(i3, (int) PixelUtil.toPixelFromSP(mo6944getMap.getDouble("width")), (int) PixelUtil.toPixelFromSP(mo6944getMap.getDouble("height")))));
            } else if (length2 >= length) {
                if (ReactAccessibilityDelegate.AccessibilityRole.LINK.equals(textAttributeProps.mAccessibilityRole)) {
                    list.add(new SetSpanOperation(length, length2, new ReactClickableSpan(i3, textAttributeProps.mColor)));
                } else if (textAttributeProps.mIsColorSet) {
                    list.add(new SetSpanOperation(length, length2, new ReactForegroundColorSpan(textAttributeProps.mColor)));
                }
                if (textAttributeProps.mIsBackgroundColorSet) {
                    list.add(new SetSpanOperation(length, length2, new ReactBackgroundColorSpan(textAttributeProps.mBackgroundColor)));
                }
                int i4 = Build.VERSION.SDK_INT;
                if (!Float.isNaN(textAttributeProps.getLetterSpacing())) {
                    list.add(new SetSpanOperation(length, length2, new CustomLetterSpacingSpan(textAttributeProps.getLetterSpacing())));
                }
                list.add(new SetSpanOperation(length, length2, new ReactAbsoluteSizeSpan(textAttributeProps.mFontSize)));
                if (textAttributeProps.mFontStyle == -1 && textAttributeProps.mFontWeight == -1 && textAttributeProps.mFontFamily == null) {
                    i = size;
                } else {
                    i = size;
                    list.add(new SetSpanOperation(length, length2, new CustomStyleSpan(textAttributeProps.mFontStyle, textAttributeProps.mFontWeight, textAttributeProps.mFontFeatureSettings, textAttributeProps.mFontFamily, context.getAssets())));
                }
                if (textAttributeProps.mIsUnderlineTextDecorationSet) {
                    list.add(new SetSpanOperation(length, length2, new ReactUnderlineSpan()));
                }
                if (textAttributeProps.mIsLineThroughTextDecorationSet) {
                    list.add(new SetSpanOperation(length, length2, new ReactStrikethroughSpan()));
                }
                if (textAttributeProps.mTextShadowOffsetDx != 0.0f || textAttributeProps.mTextShadowOffsetDy != 0.0f) {
                    list.add(new SetSpanOperation(length, length2, new ShadowStyleSpan(textAttributeProps.mTextShadowOffsetDx, textAttributeProps.mTextShadowOffsetDy, textAttributeProps.mTextShadowRadius, textAttributeProps.mTextShadowColor)));
                }
                if (!Float.isNaN(textAttributeProps.getEffectiveLineHeight())) {
                    list.add(new SetSpanOperation(length, length2, new CustomLineHeightSpan(textAttributeProps.getEffectiveLineHeight())));
                }
                list.add(new SetSpanOperation(length, length2, new ReactTagSpan(i3)));
                i2++;
            }
            i = size;
            i2++;
        }
    }

    private static Layout createLayout(Spannable spannable, BoringLayout.Metrics metrics, float f, YogaMeasureMode yogaMeasureMode, boolean z, int i) {
        int length = spannable.length();
        boolean z2 = yogaMeasureMode == YogaMeasureMode.UNDEFINED || f < 0.0f;
        TextPaint textPaint = sTextPaintInstance;
        float desiredWidth = metrics == null ? Layout.getDesiredWidth(spannable, textPaint) : Float.NaN;
        if (metrics == null && (z2 || (!YogaConstants.isUndefined(desiredWidth) && desiredWidth <= f))) {
            int i2 = Build.VERSION.SDK_INT;
            return StaticLayout.Builder.obtain(spannable, 0, length, textPaint, (int) Math.ceil(desiredWidth)).setAlignment(Layout.Alignment.ALIGN_NORMAL).setLineSpacing(0.0f, 1.0f).setIncludePad(z).setBreakStrategy(i).setHyphenationFrequency(1).build();
        } else if (metrics != null && (z2 || metrics.width <= f)) {
            return BoringLayout.make(spannable, textPaint, metrics.width, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, metrics, z);
        } else {
            int i3 = Build.VERSION.SDK_INT;
            StaticLayout.Builder hyphenationFrequency = StaticLayout.Builder.obtain(spannable, 0, length, textPaint, (int) f).setAlignment(Layout.Alignment.ALIGN_NORMAL).setLineSpacing(0.0f, 1.0f).setIncludePad(z).setBreakStrategy(i).setHyphenationFrequency(1);
            if (Build.VERSION.SDK_INT >= 28) {
                hyphenationFrequency.setUseLineSpacingFromFallbacks(true);
            }
            return hyphenationFrequency.build();
        }
    }

    private static Spannable createSpannableFromAttributedString(Context context, ReadableMap readableMap, @Nullable ReactTextViewManagerCallback reactTextViewManagerCallback) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        ArrayList<SetSpanOperation> arrayList = new ArrayList();
        buildSpannableFromFragment(context, readableMap.getArray("fragments"), spannableStringBuilder, arrayList);
        int i = 0;
        for (SetSpanOperation setSpanOperation : arrayList) {
            setSpanOperation.execute(spannableStringBuilder, i);
            i++;
        }
        if (reactTextViewManagerCallback != null) {
            reactTextViewManagerCallback.onPostProcessSpannable(spannableStringBuilder);
        }
        return spannableStringBuilder;
    }

    public static void deleteCachedSpannableForTag(int i) {
        sTagToSpannableCache.remove(Integer.valueOf(i));
    }

    public static Spannable getOrCreateSpannableForText(Context context, ReadableMap readableMap, @Nullable ReactTextViewManagerCallback reactTextViewManagerCallback) {
        String obj;
        boolean z = ReactFeatureFlags.enableSpannableCacheByReadableNativeMapEquality;
        if (z) {
            synchronized (sSpannableCacheLock) {
                Spannable spannable = sSpannableCacheV2.get((ReadableNativeMap) readableMap);
                if (spannable != null) {
                    return spannable;
                }
                obj = "";
            }
        } else {
            obj = readableMap.toString();
            synchronized (sSpannableCacheLock) {
                Spannable spannable2 = sSpannableCache.get(obj);
                if (spannable2 != null) {
                    return spannable2;
                }
            }
        }
        Spannable createSpannableFromAttributedString = createSpannableFromAttributedString(context, readableMap, reactTextViewManagerCallback);
        if (z) {
            synchronized (sSpannableCacheLock) {
                sSpannableCacheV2.put((ReadableNativeMap) readableMap, createSpannableFromAttributedString);
            }
        } else {
            synchronized (sSpannableCacheLock) {
                sSpannableCache.put(obj, createSpannableFromAttributedString);
            }
        }
        return createSpannableFromAttributedString;
    }

    public static boolean isRTL(ReadableMap readableMap) {
        ReadableArray array = readableMap.getArray("fragments");
        return array.size() > 0 && new TextAttributeProps(new ReactStylesDiffMap(array.mo6944getMap(0).mo6945getMap("textAttributes"))).mLayoutDirection == 1;
    }

    public static WritableArray measureLines(@NonNull Context context, ReadableMap readableMap, ReadableMap readableMap2, float f) {
        TextPaint textPaint = sTextPaintInstance;
        Spannable orCreateSpannableForText = getOrCreateSpannableForText(context, readableMap, null);
        BoringLayout.Metrics isBoring = BoringLayout.isBoring(orCreateSpannableForText, textPaint);
        int textBreakStrategy = TextAttributeProps.getTextBreakStrategy(readableMap2.getString("textBreakStrategy"));
        return FontMetricsUtil.getFontMetrics(orCreateSpannableForText, createLayout(orCreateSpannableForText, isBoring, f, YogaMeasureMode.EXACTLY, readableMap2.hasKey("includeFontPadding") ? readableMap2.getBoolean("includeFontPadding") : true, textBreakStrategy), sTextPaintInstance, context);
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x00ab, code lost:
        if (r11 > r21) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00bf, code lost:
        if (r1 > r23) goto L88;
     */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00cb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long measureText(android.content.Context r18, com.facebook.react.bridge.ReadableMap r19, com.facebook.react.bridge.ReadableMap r20, float r21, com.facebook.yoga.YogaMeasureMode r22, float r23, com.facebook.yoga.YogaMeasureMode r24, com.facebook.react.views.text.ReactTextViewManagerCallback r25, @androidx.annotation.Nullable float[] r26) {
        /*
            Method dump skipped, instructions count: 403
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.TextLayoutManager.measureText(android.content.Context, com.facebook.react.bridge.ReadableMap, com.facebook.react.bridge.ReadableMap, float, com.facebook.yoga.YogaMeasureMode, float, com.facebook.yoga.YogaMeasureMode, com.facebook.react.views.text.ReactTextViewManagerCallback, float[]):long");
    }

    public static void setCachedSpannabledForTag(int i, @NonNull Spannable spannable) {
        sTagToSpannableCache.put(Integer.valueOf(i), spannable);
    }
}
