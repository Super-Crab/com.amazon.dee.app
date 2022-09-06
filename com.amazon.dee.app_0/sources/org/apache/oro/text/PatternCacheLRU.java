package org.apache.oro.text;

import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.util.CacheLRU;
/* loaded from: classes4.dex */
public final class PatternCacheLRU extends GenericPatternCache {
    public PatternCacheLRU() {
        this(20);
    }

    public PatternCacheLRU(int i) {
        this(i, new Perl5Compiler());
    }

    public PatternCacheLRU(int i, PatternCompiler patternCompiler) {
        super(new CacheLRU(i), patternCompiler);
    }

    public PatternCacheLRU(PatternCompiler patternCompiler) {
        this(20, patternCompiler);
    }
}
