package org.apache.oro.io;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import org.apache.oro.text.MalformedCachePatternException;
import org.apache.oro.text.PatternCache;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternMatcher;
/* loaded from: classes4.dex */
public abstract class RegexFilenameFilter implements FilenameFilter, FileFilter {
    PatternCache _cache;
    PatternMatcher _matcher;
    Pattern _pattern;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RegexFilenameFilter(PatternCache patternCache, PatternMatcher patternMatcher) {
        this(patternCache, patternMatcher, "");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RegexFilenameFilter(PatternCache patternCache, PatternMatcher patternMatcher, String str) {
        this._cache = patternCache;
        this._matcher = patternMatcher;
        setFilterExpression(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RegexFilenameFilter(PatternCache patternCache, PatternMatcher patternMatcher, String str, int i) {
        this._cache = patternCache;
        this._matcher = patternMatcher;
        setFilterExpression(str, i);
    }

    @Override // java.io.FileFilter
    public boolean accept(File file) {
        boolean matches;
        synchronized (this._matcher) {
            matches = this._matcher.matches(file.getName(), this._pattern);
        }
        return matches;
    }

    @Override // java.io.FilenameFilter
    public boolean accept(File file, String str) {
        boolean matches;
        synchronized (this._matcher) {
            matches = this._matcher.matches(str, this._pattern);
        }
        return matches;
    }

    public void setFilterExpression(String str) throws MalformedCachePatternException {
        this._pattern = this._cache.getPattern(str);
    }

    public void setFilterExpression(String str, int i) throws MalformedCachePatternException {
        this._pattern = this._cache.getPattern(str, i);
    }
}
