package org.apache.oro.text;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.util.Cache;
/* loaded from: classes4.dex */
public abstract class GenericPatternCache implements PatternCache {
    public static final int DEFAULT_CAPACITY = 20;
    Cache _cache;
    PatternCompiler _compiler;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GenericPatternCache(Cache cache, PatternCompiler patternCompiler) {
        this._cache = cache;
        this._compiler = patternCompiler;
    }

    @Override // org.apache.oro.text.PatternCache
    public final synchronized Pattern addPattern(String str) throws MalformedPatternException {
        return addPattern(str, 0);
    }

    @Override // org.apache.oro.text.PatternCache
    public final synchronized Pattern addPattern(String str, int i) throws MalformedPatternException {
        Object element = this._cache.getElement(str);
        if (element != null) {
            Pattern pattern = (Pattern) element;
            if (pattern.getOptions() == i) {
                return pattern;
            }
        }
        Pattern compile = this._compiler.compile(str, i);
        this._cache.addElement(str, compile);
        return compile;
    }

    @Override // org.apache.oro.text.PatternCache
    public final int capacity() {
        return this._cache.capacity();
    }

    @Override // org.apache.oro.text.PatternCache
    public final synchronized Pattern getPattern(String str) throws MalformedCachePatternException {
        return getPattern(str, 0);
    }

    @Override // org.apache.oro.text.PatternCache
    public final synchronized Pattern getPattern(String str, int i) throws MalformedCachePatternException {
        try {
        } catch (MalformedPatternException e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Invalid expression: ");
            stringBuffer.append(str);
            stringBuffer.append("\n");
            stringBuffer.append(e.getMessage());
            throw new MalformedCachePatternException(stringBuffer.toString());
        }
        return addPattern(str, i);
    }

    @Override // org.apache.oro.text.PatternCache
    public final int size() {
        return this._cache.size();
    }
}
