package com.amazon.tarazed.ui.html;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BulletSpan;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import com.amazon.alexa.accessory.frames.contacts.ContactsModuleConstants;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: HtmlUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001eB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ5\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0001\u0010\u0011\u001a\u00020\u0012H\u0002¢\u0006\u0002\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0018\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u001dH\u0002¨\u0006\u001f"}, d2 = {"Lcom/amazon/tarazed/ui/html/HtmlUtil;", "", "()V", "applyHtmlToTextView", "", "htmlString", "", "view", "Landroid/widget/TextView;", "backportBulletSpans", "spannableBuilder", "Landroid/text/SpannableStringBuilder;", "bulletSpans", "", "Landroid/text/style/BulletSpan;", "textSize", "", "textColor", "", "(Landroid/text/SpannableStringBuilder;[Landroid/text/style/BulletSpan;FI)V", "finalizeBulletSpanLegacy", ContactsModuleConstants.OUTPUT, "Landroid/text/Editable;", "getSpannableFromHtmlLegacy", "Landroid/text/Spanned;", "markStartOfBulletSpanLegacy", "replaceTemplates", "originalString", "context", "Landroid/content/Context;", "BulletMarker", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class HtmlUtil {
    public static final HtmlUtil INSTANCE = new HtmlUtil();

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: HtmlUtil.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/tarazed/ui/html/HtmlUtil$BulletMarker;", "", "()V", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class BulletMarker {
    }

    private HtmlUtil() {
    }

    private final void backportBulletSpans(SpannableStringBuilder spannableStringBuilder, BulletSpan[] bulletSpanArr, float f, @ColorInt int i) {
        for (BulletSpan bulletSpan : bulletSpanArr) {
            int spanStart = spannableStringBuilder.getSpanStart(bulletSpan);
            int spanEnd = spannableStringBuilder.getSpanEnd(bulletSpan);
            BackportedBulletSpan backportedBulletSpan = new BackportedBulletSpan((int) (f / 2), i, (int) (f * 0.15d));
            spannableStringBuilder.removeSpan(bulletSpan);
            spannableStringBuilder.setSpan(backportedBulletSpan, spanStart, spanEnd, 17);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finalizeBulletSpanLegacy(Editable editable) {
        editable.append("\n");
        Object[] spans = editable.getSpans(0, editable.length(), BulletMarker.class);
        Intrinsics.checkExpressionValueIsNotNull(spans, "output.getSpans(0, outpu…BulletMarker::class.java)");
        BulletMarker bulletMarker = (BulletMarker) ArraysKt.lastOrNull(spans);
        if (bulletMarker != null) {
            editable.setSpan(new BulletSpan(), editable.getSpanStart(bulletMarker), editable.length(), 17);
        }
    }

    private final Spanned getSpannableFromHtmlLegacy(String str) {
        Spanned fromHtml = Html.fromHtml(str, null, HtmlUtil$getSpannableFromHtmlLegacy$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(fromHtml, "Html.fromHtml(htmlString…\n            }\n        })");
        return fromHtml;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void markStartOfBulletSpanLegacy(Editable editable) {
        editable.setSpan(new BulletMarker(), editable.length(), editable.length(), 17);
    }

    private final String replaceTemplates(String str, Context context) {
        String replace$default;
        replace$default = StringsKt__StringsJVMKt.replace$default(str, "$APP_NAME", context.getApplicationInfo().loadLabel(context.getPackageManager()).toString(), false, 4, (Object) null);
        return replace$default;
    }

    public final void applyHtmlToTextView(@NotNull String htmlString, @NotNull TextView view) {
        CharSequence trim;
        Intrinsics.checkParameterIsNotNull(htmlString, "htmlString");
        Intrinsics.checkParameterIsNotNull(view, "view");
        Context context = view.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "view.context");
        String replaceTemplates = replaceTemplates(htmlString, context);
        int i = Build.VERSION.SDK_INT;
        Spanned fromHtml = Html.fromHtml(replaceTemplates, 63);
        Intrinsics.checkExpressionValueIsNotNull(fromHtml, "if (Build.VERSION.SDK_IN…replacedString)\n        }");
        trim = StringsKt__StringsKt.trim(fromHtml);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(trim);
        BulletSpan[] bulletSpans = (BulletSpan[]) spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), BulletSpan.class);
        Intrinsics.checkExpressionValueIsNotNull(bulletSpans, "bulletSpans");
        float textSize = view.getTextSize();
        ColorStateList textColors = view.getTextColors();
        Intrinsics.checkExpressionValueIsNotNull(textColors, "view.textColors");
        backportBulletSpans(spannableStringBuilder, bulletSpans, textSize, textColors.getDefaultColor());
        view.setText(spannableStringBuilder);
    }
}
