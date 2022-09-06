package com.google.common.html;

import com.google.common.annotations.GwtCompatible;
import com.google.common.escape.Escaper;
import com.google.common.escape.Escapers;
import kotlin.text.Typography;
import org.apache.logging.log4j.util.Chars;
@GwtCompatible
/* loaded from: classes3.dex */
public final class HtmlEscapers {
    private static final Escaper HTML_ESCAPER = Escapers.builder().addEscape('\"', "&quot;").addEscape(Chars.QUOTE, "&#39;").addEscape(Typography.amp, "&amp;").addEscape(Typography.less, "&lt;").addEscape(Typography.greater, "&gt;").build();

    private HtmlEscapers() {
    }

    public static Escaper htmlEscaper() {
        return HTML_ESCAPER;
    }
}
