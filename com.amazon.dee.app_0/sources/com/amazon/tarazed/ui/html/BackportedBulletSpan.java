package com.amazon.tarazed.ui.html;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.Spanned;
import android.text.style.LeadingMarginSpan;
import androidx.annotation.ColorInt;
import androidx.annotation.IntRange;
import androidx.annotation.Px;
import com.amazon.appmanager.lib.DefaultPreloadManager;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: BackportedBulletSpan.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000  2\u00020\u0001:\u0001 B#\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006Jj\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\f2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\fH\u0016R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/amazon/tarazed/ui/html/BackportedBulletSpan;", "Landroid/text/style/LeadingMarginSpan;", "gapWidth", "", "color", "bulletRadius", "(III)V", "getBulletRadius", "()I", "getColor", "getGapWidth", "wantColor", "", "drawLeadingMargin", "", "canvas", "Landroid/graphics/Canvas;", "paint", "Landroid/graphics/Paint;", ReactProperties.HereMapMarker.X, "dir", ViewProps.TOP, "baseline", ViewProps.BOTTOM, "text", "", "start", "end", DefaultPreloadManager.METRIC_PATH_FIRST, TtmlNode.TAG_LAYOUT, "Landroid/text/Layout;", "getLeadingMargin", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class BackportedBulletSpan implements LeadingMarginSpan {
    public static final Companion Companion = new Companion(null);
    private static final int STANDARD_BULLET_RADIUS = 4;
    private static final int STANDARD_COLOR = 0;
    public static final int STANDARD_GAP_WIDTH = 2;
    private final int bulletRadius;
    private final int color;
    private final int gapWidth;
    private final boolean wantColor;

    /* compiled from: BackportedBulletSpan.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/amazon/tarazed/ui/html/BackportedBulletSpan$Companion;", "", "()V", "STANDARD_BULLET_RADIUS", "", "STANDARD_COLOR", "STANDARD_GAP_WIDTH", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BackportedBulletSpan() {
        this(0, 0, 0, 7, null);
    }

    public BackportedBulletSpan(@Px int i, @ColorInt int i2, @IntRange(from = 0) int i3) {
        this.gapWidth = i;
        this.color = i2;
        this.bulletRadius = i3;
        this.wantColor = this.color != 0;
    }

    @Override // android.text.style.LeadingMarginSpan
    public void drawLeadingMargin(@NotNull Canvas canvas, @NotNull Paint paint, int i, int i2, int i3, int i4, int i5, @NotNull CharSequence text, int i6, int i7, boolean z, @Nullable Layout layout) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        Intrinsics.checkParameterIsNotNull(paint, "paint");
        Intrinsics.checkParameterIsNotNull(text, "text");
        if (((Spanned) text).getSpanStart(this) == i6) {
            Paint.Style style = paint.getStyle();
            int i8 = 0;
            if (this.wantColor) {
                i8 = paint.getColor();
                paint.setColor(this.color);
            }
            paint.setStyle(Paint.Style.FILL);
            float lineBaseline = layout != null ? layout.getLineBaseline(layout.getLineForOffset(i6)) - (this.bulletRadius * 2.0f) : (i3 + i5) / 2.0f;
            int i9 = this.bulletRadius;
            canvas.drawCircle((i2 * i9) + i, lineBaseline, i9, paint);
            if (this.wantColor) {
                paint.setColor(i8);
            }
            paint.setStyle(style);
        }
    }

    public final int getBulletRadius() {
        return this.bulletRadius;
    }

    public final int getColor() {
        return this.color;
    }

    public final int getGapWidth() {
        return this.gapWidth;
    }

    @Override // android.text.style.LeadingMarginSpan
    public int getLeadingMargin(boolean z) {
        return (this.bulletRadius * 2) + this.gapWidth;
    }

    public /* synthetic */ BackportedBulletSpan(int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 2 : i, (i4 & 2) != 0 ? 0 : i2, (i4 & 4) != 0 ? 4 : i3);
    }
}
