package org.apache.commons.lang3.text;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
/* loaded from: classes4.dex */
public class StrTokenizer implements ListIterator<String>, Cloneable {
    private static final StrTokenizer CSV_TOKENIZER_PROTOTYPE = new StrTokenizer();
    private static final StrTokenizer TSV_TOKENIZER_PROTOTYPE;
    private char[] chars;
    private StrMatcher delimMatcher;
    private boolean emptyAsNull;
    private boolean ignoreEmptyTokens;
    private StrMatcher ignoredMatcher;
    private StrMatcher quoteMatcher;
    private int tokenPos;
    private String[] tokens;
    private StrMatcher trimmerMatcher;

    static {
        CSV_TOKENIZER_PROTOTYPE.setDelimiterMatcher(StrMatcher.commaMatcher());
        CSV_TOKENIZER_PROTOTYPE.setQuoteMatcher(StrMatcher.doubleQuoteMatcher());
        CSV_TOKENIZER_PROTOTYPE.setIgnoredMatcher(StrMatcher.noneMatcher());
        CSV_TOKENIZER_PROTOTYPE.setTrimmerMatcher(StrMatcher.trimMatcher());
        CSV_TOKENIZER_PROTOTYPE.setEmptyTokenAsNull(false);
        CSV_TOKENIZER_PROTOTYPE.setIgnoreEmptyTokens(false);
        TSV_TOKENIZER_PROTOTYPE = new StrTokenizer();
        TSV_TOKENIZER_PROTOTYPE.setDelimiterMatcher(StrMatcher.tabMatcher());
        TSV_TOKENIZER_PROTOTYPE.setQuoteMatcher(StrMatcher.doubleQuoteMatcher());
        TSV_TOKENIZER_PROTOTYPE.setIgnoredMatcher(StrMatcher.noneMatcher());
        TSV_TOKENIZER_PROTOTYPE.setTrimmerMatcher(StrMatcher.trimMatcher());
        TSV_TOKENIZER_PROTOTYPE.setEmptyTokenAsNull(false);
        TSV_TOKENIZER_PROTOTYPE.setIgnoreEmptyTokens(false);
    }

    public StrTokenizer() {
        this.delimMatcher = StrMatcher.splitMatcher();
        this.quoteMatcher = StrMatcher.noneMatcher();
        this.ignoredMatcher = StrMatcher.noneMatcher();
        this.trimmerMatcher = StrMatcher.noneMatcher();
        this.emptyAsNull = false;
        this.ignoreEmptyTokens = true;
        this.chars = null;
    }

    private void addToken(List<String> list, String str) {
        if (StringUtils.isEmpty(str)) {
            if (isIgnoreEmptyTokens()) {
                return;
            }
            if (isEmptyTokenAsNull()) {
                str = null;
            }
        }
        list.add(str);
    }

    private void checkTokenized() {
        if (this.tokens == null) {
            char[] cArr = this.chars;
            if (cArr == null) {
                List<String> list = tokenize(null, 0, 0);
                this.tokens = (String[]) list.toArray(new String[list.size()]);
                return;
            }
            List<String> list2 = tokenize(cArr, 0, cArr.length);
            this.tokens = (String[]) list2.toArray(new String[list2.size()]);
        }
    }

    private static StrTokenizer getCSVClone() {
        return (StrTokenizer) CSV_TOKENIZER_PROTOTYPE.clone();
    }

    public static StrTokenizer getCSVInstance() {
        return getCSVClone();
    }

    private static StrTokenizer getTSVClone() {
        return (StrTokenizer) TSV_TOKENIZER_PROTOTYPE.clone();
    }

    public static StrTokenizer getTSVInstance() {
        return getTSVClone();
    }

    private boolean isQuote(char[] cArr, int i, int i2, int i3, int i4) {
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = i + i5;
            if (i6 >= i2 || cArr[i6] != cArr[i3 + i5]) {
                return false;
            }
        }
        return true;
    }

    private int readNextToken(char[] cArr, int i, int i2, StrBuilder strBuilder, List<String> list) {
        while (i < i2) {
            int max = Math.max(getIgnoredMatcher().isMatch(cArr, i, i, i2), getTrimmerMatcher().isMatch(cArr, i, i, i2));
            if (max == 0 || getDelimiterMatcher().isMatch(cArr, i, i, i2) > 0 || getQuoteMatcher().isMatch(cArr, i, i, i2) > 0) {
                break;
            }
            i += max;
        }
        if (i >= i2) {
            addToken(list, "");
            return -1;
        }
        int isMatch = getDelimiterMatcher().isMatch(cArr, i, i, i2);
        if (isMatch > 0) {
            addToken(list, "");
            return i + isMatch;
        }
        int isMatch2 = getQuoteMatcher().isMatch(cArr, i, i, i2);
        if (isMatch2 > 0) {
            return readWithQuotes(cArr, i + isMatch2, i2, strBuilder, list, i, isMatch2);
        }
        return readWithQuotes(cArr, i, i2, strBuilder, list, 0, 0);
    }

    private int readWithQuotes(char[] cArr, int i, int i2, StrBuilder strBuilder, List<String> list, int i3, int i4) {
        int i5;
        strBuilder.clear();
        boolean z = i4 > 0;
        int i6 = i;
        int i7 = 0;
        while (i6 < i2) {
            if (z) {
                int i8 = i7;
                if (isQuote(cArr, i6, i2, i3, i4)) {
                    int i9 = i6 + i4;
                    if (isQuote(cArr, i9, i2, i3, i4)) {
                        strBuilder.append(cArr, i6, i4);
                        i6 += i4 * 2;
                        i7 = strBuilder.size();
                    } else {
                        i7 = i8;
                        z = false;
                        i6 = i9;
                    }
                } else {
                    i5 = i6 + 1;
                    strBuilder.append(cArr[i6]);
                    i7 = strBuilder.size();
                    i6 = i5;
                }
            } else {
                int i10 = i7;
                int isMatch = getDelimiterMatcher().isMatch(cArr, i6, i, i2);
                if (isMatch > 0) {
                    addToken(list, strBuilder.substring(0, i10));
                    return i6 + isMatch;
                } else if (i4 <= 0 || !isQuote(cArr, i6, i2, i3, i4)) {
                    int isMatch2 = getIgnoredMatcher().isMatch(cArr, i6, i, i2);
                    if (isMatch2 <= 0) {
                        isMatch2 = getTrimmerMatcher().isMatch(cArr, i6, i, i2);
                        if (isMatch2 > 0) {
                            strBuilder.append(cArr, i6, isMatch2);
                        } else {
                            i5 = i6 + 1;
                            strBuilder.append(cArr[i6]);
                            i7 = strBuilder.size();
                            i6 = i5;
                        }
                    }
                    i6 += isMatch2;
                    i7 = i10;
                } else {
                    i6 += i4;
                    i7 = i10;
                    z = true;
                }
            }
        }
        addToken(list, strBuilder.substring(0, i7));
        return -1;
    }

    public Object clone() {
        try {
            return cloneReset();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    Object cloneReset() throws CloneNotSupportedException {
        StrTokenizer strTokenizer = (StrTokenizer) super.clone();
        char[] cArr = strTokenizer.chars;
        if (cArr != null) {
            strTokenizer.chars = (char[]) cArr.clone();
        }
        strTokenizer.reset();
        return strTokenizer;
    }

    public String getContent() {
        char[] cArr = this.chars;
        if (cArr == null) {
            return null;
        }
        return new String(cArr);
    }

    public StrMatcher getDelimiterMatcher() {
        return this.delimMatcher;
    }

    public StrMatcher getIgnoredMatcher() {
        return this.ignoredMatcher;
    }

    public StrMatcher getQuoteMatcher() {
        return this.quoteMatcher;
    }

    public String[] getTokenArray() {
        checkTokenized();
        return (String[]) this.tokens.clone();
    }

    public List<String> getTokenList() {
        checkTokenized();
        ArrayList arrayList = new ArrayList(this.tokens.length);
        for (String str : this.tokens) {
            arrayList.add(str);
        }
        return arrayList;
    }

    public StrMatcher getTrimmerMatcher() {
        return this.trimmerMatcher;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public boolean hasNext() {
        checkTokenized();
        return this.tokenPos < this.tokens.length;
    }

    @Override // java.util.ListIterator
    public boolean hasPrevious() {
        checkTokenized();
        return this.tokenPos > 0;
    }

    public boolean isEmptyTokenAsNull() {
        return this.emptyAsNull;
    }

    public boolean isIgnoreEmptyTokens() {
        return this.ignoreEmptyTokens;
    }

    @Override // java.util.ListIterator
    public int nextIndex() {
        return this.tokenPos;
    }

    public String nextToken() {
        if (hasNext()) {
            String[] strArr = this.tokens;
            int i = this.tokenPos;
            this.tokenPos = i + 1;
            return strArr[i];
        }
        return null;
    }

    @Override // java.util.ListIterator
    public int previousIndex() {
        return this.tokenPos - 1;
    }

    public String previousToken() {
        if (hasPrevious()) {
            String[] strArr = this.tokens;
            int i = this.tokenPos - 1;
            this.tokenPos = i;
            return strArr[i];
        }
        return null;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("remove() is unsupported");
    }

    public StrTokenizer reset() {
        this.tokenPos = 0;
        this.tokens = null;
        return this;
    }

    public StrTokenizer setDelimiterChar(char c) {
        return setDelimiterMatcher(StrMatcher.charMatcher(c));
    }

    public StrTokenizer setDelimiterMatcher(StrMatcher strMatcher) {
        if (strMatcher == null) {
            this.delimMatcher = StrMatcher.noneMatcher();
        } else {
            this.delimMatcher = strMatcher;
        }
        return this;
    }

    public StrTokenizer setDelimiterString(String str) {
        return setDelimiterMatcher(StrMatcher.stringMatcher(str));
    }

    public StrTokenizer setEmptyTokenAsNull(boolean z) {
        this.emptyAsNull = z;
        return this;
    }

    public StrTokenizer setIgnoreEmptyTokens(boolean z) {
        this.ignoreEmptyTokens = z;
        return this;
    }

    public StrTokenizer setIgnoredChar(char c) {
        return setIgnoredMatcher(StrMatcher.charMatcher(c));
    }

    public StrTokenizer setIgnoredMatcher(StrMatcher strMatcher) {
        if (strMatcher != null) {
            this.ignoredMatcher = strMatcher;
        }
        return this;
    }

    public StrTokenizer setQuoteChar(char c) {
        return setQuoteMatcher(StrMatcher.charMatcher(c));
    }

    public StrTokenizer setQuoteMatcher(StrMatcher strMatcher) {
        if (strMatcher != null) {
            this.quoteMatcher = strMatcher;
        }
        return this;
    }

    public StrTokenizer setTrimmerMatcher(StrMatcher strMatcher) {
        if (strMatcher != null) {
            this.trimmerMatcher = strMatcher;
        }
        return this;
    }

    public int size() {
        checkTokenized();
        return this.tokens.length;
    }

    public String toString() {
        if (this.tokens == null) {
            return "StrTokenizer[not tokenized yet]";
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("StrTokenizer");
        outline107.append(getTokenList());
        return outline107.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<String> tokenize(char[] cArr, int i, int i2) {
        if (cArr != null && i2 != 0) {
            StrBuilder strBuilder = new StrBuilder();
            ArrayList arrayList = new ArrayList();
            int i3 = i;
            while (i3 >= 0 && i3 < i2) {
                i3 = readNextToken(cArr, i3, i2, strBuilder, arrayList);
                if (i3 >= i2) {
                    addToken(arrayList, "");
                }
            }
            return arrayList;
        }
        return Collections.emptyList();
    }

    public static StrTokenizer getCSVInstance(String str) {
        StrTokenizer cSVClone = getCSVClone();
        cSVClone.reset(str);
        return cSVClone;
    }

    public static StrTokenizer getTSVInstance(String str) {
        StrTokenizer tSVClone = getTSVClone();
        tSVClone.reset(str);
        return tSVClone;
    }

    @Override // java.util.ListIterator
    public void add(String str) {
        throw new UnsupportedOperationException("add() is unsupported");
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public String next() {
        if (hasNext()) {
            String[] strArr = this.tokens;
            int i = this.tokenPos;
            this.tokenPos = i + 1;
            return strArr[i];
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.ListIterator
    public String previous() {
        if (hasPrevious()) {
            String[] strArr = this.tokens;
            int i = this.tokenPos - 1;
            this.tokenPos = i;
            return strArr[i];
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.ListIterator
    public void set(String str) {
        throw new UnsupportedOperationException("set() is unsupported");
    }

    public StrTokenizer reset(String str) {
        reset();
        if (str != null) {
            this.chars = str.toCharArray();
        } else {
            this.chars = null;
        }
        return this;
    }

    public static StrTokenizer getCSVInstance(char[] cArr) {
        StrTokenizer cSVClone = getCSVClone();
        cSVClone.reset(cArr);
        return cSVClone;
    }

    public static StrTokenizer getTSVInstance(char[] cArr) {
        StrTokenizer tSVClone = getTSVClone();
        tSVClone.reset(cArr);
        return tSVClone;
    }

    public StrTokenizer reset(char[] cArr) {
        reset();
        this.chars = ArrayUtils.clone(cArr);
        return this;
    }

    public StrTokenizer(String str) {
        this.delimMatcher = StrMatcher.splitMatcher();
        this.quoteMatcher = StrMatcher.noneMatcher();
        this.ignoredMatcher = StrMatcher.noneMatcher();
        this.trimmerMatcher = StrMatcher.noneMatcher();
        this.emptyAsNull = false;
        this.ignoreEmptyTokens = true;
        if (str != null) {
            this.chars = str.toCharArray();
        } else {
            this.chars = null;
        }
    }

    public StrTokenizer(String str, char c) {
        this(str);
        setDelimiterChar(c);
    }

    public StrTokenizer(String str, String str2) {
        this(str);
        setDelimiterString(str2);
    }

    public StrTokenizer(String str, StrMatcher strMatcher) {
        this(str);
        setDelimiterMatcher(strMatcher);
    }

    public StrTokenizer(String str, char c, char c2) {
        this(str, c);
        setQuoteChar(c2);
    }

    public StrTokenizer(String str, StrMatcher strMatcher, StrMatcher strMatcher2) {
        this(str, strMatcher);
        setQuoteMatcher(strMatcher2);
    }

    public StrTokenizer(char[] cArr) {
        this.delimMatcher = StrMatcher.splitMatcher();
        this.quoteMatcher = StrMatcher.noneMatcher();
        this.ignoredMatcher = StrMatcher.noneMatcher();
        this.trimmerMatcher = StrMatcher.noneMatcher();
        this.emptyAsNull = false;
        this.ignoreEmptyTokens = true;
        this.chars = ArrayUtils.clone(cArr);
    }

    public StrTokenizer(char[] cArr, char c) {
        this(cArr);
        setDelimiterChar(c);
    }

    public StrTokenizer(char[] cArr, String str) {
        this(cArr);
        setDelimiterString(str);
    }

    public StrTokenizer(char[] cArr, StrMatcher strMatcher) {
        this(cArr);
        setDelimiterMatcher(strMatcher);
    }

    public StrTokenizer(char[] cArr, char c, char c2) {
        this(cArr, c);
        setQuoteChar(c2);
    }

    public StrTokenizer(char[] cArr, StrMatcher strMatcher, StrMatcher strMatcher2) {
        this(cArr, strMatcher);
        setQuoteMatcher(strMatcher2);
    }
}
