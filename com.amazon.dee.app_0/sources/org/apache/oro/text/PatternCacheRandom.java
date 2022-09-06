package org.apache.oro.text;

import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.util.CacheRandom;
/* loaded from: classes4.dex */
public final class PatternCacheRandom extends GenericPatternCache {
    public PatternCacheRandom() {
        this(20);
    }

    public PatternCacheRandom(int i) {
        this(i, new Perl5Compiler());
    }

    public PatternCacheRandom(int i, PatternCompiler patternCompiler) {
        super(new CacheRandom(i), patternCompiler);
    }

    public PatternCacheRandom(PatternCompiler patternCompiler) {
        this(20, patternCompiler);
    }
}
