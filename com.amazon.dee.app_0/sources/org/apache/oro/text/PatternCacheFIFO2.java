package org.apache.oro.text;

import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.util.CacheFIFO2;
/* loaded from: classes4.dex */
public final class PatternCacheFIFO2 extends GenericPatternCache {
    public PatternCacheFIFO2() {
        this(20);
    }

    public PatternCacheFIFO2(int i) {
        this(i, new Perl5Compiler());
    }

    public PatternCacheFIFO2(int i, PatternCompiler patternCompiler) {
        super(new CacheFIFO2(i), patternCompiler);
    }

    public PatternCacheFIFO2(PatternCompiler patternCompiler) {
        this(20, patternCompiler);
    }
}
