package org.apache.oro.text;

import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.util.CacheFIFO;
/* loaded from: classes4.dex */
public final class PatternCacheFIFO extends GenericPatternCache {
    public PatternCacheFIFO() {
        this(20);
    }

    public PatternCacheFIFO(int i) {
        this(i, new Perl5Compiler());
    }

    public PatternCacheFIFO(int i, PatternCompiler patternCompiler) {
        super(new CacheFIFO(i), patternCompiler);
    }

    public PatternCacheFIFO(PatternCompiler patternCompiler) {
        this(20, patternCompiler);
    }
}
