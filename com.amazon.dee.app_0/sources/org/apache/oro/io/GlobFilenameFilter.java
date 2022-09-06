package org.apache.oro.io;

import org.apache.oro.text.GlobCompiler;
import org.apache.oro.text.PatternCache;
import org.apache.oro.text.PatternCacheLRU;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.Perl5Matcher;
/* loaded from: classes4.dex */
public class GlobFilenameFilter extends RegexFilenameFilter {
    private static final PatternMatcher __MATCHER = new Perl5Matcher();
    private static final PatternCache __CACHE = new PatternCacheLRU(new GlobCompiler());

    public GlobFilenameFilter() {
        super(__CACHE, __MATCHER);
    }

    public GlobFilenameFilter(String str) {
        super(__CACHE, __MATCHER, str);
    }

    public GlobFilenameFilter(String str, int i) {
        super(__CACHE, __MATCHER, str, i);
    }
}
