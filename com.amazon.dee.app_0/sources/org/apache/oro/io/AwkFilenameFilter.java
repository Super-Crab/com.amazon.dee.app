package org.apache.oro.io;

import org.apache.oro.text.PatternCache;
import org.apache.oro.text.PatternCacheLRU;
import org.apache.oro.text.awk.AwkCompiler;
import org.apache.oro.text.awk.AwkMatcher;
import org.apache.oro.text.regex.PatternMatcher;
/* loaded from: classes4.dex */
public class AwkFilenameFilter extends RegexFilenameFilter {
    private static final PatternMatcher __MATCHER = new AwkMatcher();
    private static final PatternCache __CACHE = new PatternCacheLRU(new AwkCompiler());

    public AwkFilenameFilter() {
        super(__CACHE, __MATCHER);
    }

    public AwkFilenameFilter(String str) {
        super(__CACHE, __MATCHER, str);
    }

    public AwkFilenameFilter(String str, int i) {
        super(__CACHE, __MATCHER, str, i);
    }
}
