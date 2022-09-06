package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.util.regex.Pattern;
import org.apache.commons.io.IOCase;
/* loaded from: classes4.dex */
public class RegexFileFilter extends AbstractFileFilter implements Serializable {
    private final Pattern pattern;

    public RegexFileFilter(String str) {
        if (str != null) {
            this.pattern = Pattern.compile(str);
            return;
        }
        throw new IllegalArgumentException("Pattern is missing");
    }

    @Override // org.apache.commons.io.filefilter.AbstractFileFilter, org.apache.commons.io.filefilter.IOFileFilter, java.io.FilenameFilter
    public boolean accept(File file, String str) {
        return this.pattern.matcher(str).matches();
    }

    public RegexFileFilter(String str, IOCase iOCase) {
        if (str != null) {
            int i = 0;
            if (iOCase != null && !iOCase.isCaseSensitive()) {
                i = 2;
            }
            this.pattern = Pattern.compile(str, i);
            return;
        }
        throw new IllegalArgumentException("Pattern is missing");
    }

    public RegexFileFilter(String str, int i) {
        if (str != null) {
            this.pattern = Pattern.compile(str, i);
            return;
        }
        throw new IllegalArgumentException("Pattern is missing");
    }

    public RegexFileFilter(Pattern pattern) {
        if (pattern != null) {
            this.pattern = pattern;
            return;
        }
        throw new IllegalArgumentException("Pattern is missing");
    }
}
