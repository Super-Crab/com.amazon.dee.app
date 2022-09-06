package com.amazon.tarazed.ui.html;

import android.text.Editable;
import android.text.Html;
import com.amazon.alexa.accessory.frames.contacts.ContactsModuleConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.xml.sax.XMLReader;
/* compiled from: HtmlUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u00052\u000e\u0010\u0007\u001a\n \u0006*\u0004\u0018\u00010\b0\b2\u000e\u0010\t\u001a\n \u0006*\u0004\u0018\u00010\n0\nH\n¢\u0006\u0002\b\u000b"}, d2 = {"<anonymous>", "", "opening", "", "tag", "", "kotlin.jvm.PlatformType", ContactsModuleConstants.OUTPUT, "Landroid/text/Editable;", "<anonymous parameter 3>", "Lorg/xml/sax/XMLReader;", "handleTag"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
final class HtmlUtil$getSpannableFromHtmlLegacy$1 implements Html.TagHandler {
    public static final HtmlUtil$getSpannableFromHtmlLegacy$1 INSTANCE = new HtmlUtil$getSpannableFromHtmlLegacy$1();

    HtmlUtil$getSpannableFromHtmlLegacy$1() {
    }

    @Override // android.text.Html.TagHandler
    public final void handleTag(boolean z, String str, Editable output, XMLReader xMLReader) {
        if (!Intrinsics.areEqual(str, "li")) {
            return;
        }
        if (z) {
            HtmlUtil htmlUtil = HtmlUtil.INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(output, "output");
            htmlUtil.markStartOfBulletSpanLegacy(output);
            return;
        }
        HtmlUtil htmlUtil2 = HtmlUtil.INSTANCE;
        Intrinsics.checkExpressionValueIsNotNull(output, "output");
        htmlUtil2.finalizeBulletSpanLegacy(output);
    }
}
