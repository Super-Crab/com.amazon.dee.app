package junit.framework;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes3.dex */
public class ComparisonCompactor {
    private static final String DELTA_END = "]";
    private static final String DELTA_START = "[";
    private static final String ELLIPSIS = "...";
    private String fActual;
    private int fContextLength;
    private String fExpected;
    private int fPrefix;
    private int fSuffix;

    public ComparisonCompactor(int i, String str, String str2) {
        this.fContextLength = i;
        this.fExpected = str;
        this.fActual = str2;
    }

    private boolean areStringsEqual() {
        return this.fExpected.equals(this.fActual);
    }

    private String compactString(String str) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(DELTA_START);
        outline107.append(str.substring(this.fPrefix, (str.length() - this.fSuffix) + 1));
        outline107.append(DELTA_END);
        String sb = outline107.toString();
        if (this.fPrefix > 0) {
            sb = GeneratedOutlineSupport1.outline91(new StringBuilder(), computeCommonPrefix(), sb);
        }
        if (this.fSuffix > 0) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(sb);
            outline1072.append(computeCommonSuffix());
            return outline1072.toString();
        }
        return sb;
    }

    private String computeCommonPrefix() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.fPrefix > this.fContextLength ? ELLIPSIS : "");
        sb.append(this.fExpected.substring(Math.max(0, this.fPrefix - this.fContextLength), this.fPrefix));
        return sb.toString();
    }

    private String computeCommonSuffix() {
        int min = Math.min((this.fExpected.length() - this.fSuffix) + 1 + this.fContextLength, this.fExpected.length());
        StringBuilder sb = new StringBuilder();
        String str = this.fExpected;
        sb.append(str.substring((str.length() - this.fSuffix) + 1, min));
        sb.append((this.fExpected.length() - this.fSuffix) + 1 < this.fExpected.length() - this.fContextLength ? ELLIPSIS : "");
        return sb.toString();
    }

    private void findCommonPrefix() {
        this.fPrefix = 0;
        int min = Math.min(this.fExpected.length(), this.fActual.length());
        while (true) {
            int i = this.fPrefix;
            if (i >= min || this.fExpected.charAt(i) != this.fActual.charAt(this.fPrefix)) {
                return;
            }
            this.fPrefix++;
        }
    }

    private void findCommonSuffix() {
        int length = this.fExpected.length() - 1;
        int length2 = this.fActual.length() - 1;
        while (true) {
            int i = this.fPrefix;
            if (length2 < i || length < i || this.fExpected.charAt(length) != this.fActual.charAt(length2)) {
                break;
            }
            length2--;
            length--;
        }
        this.fSuffix = this.fExpected.length() - length;
    }

    public String compact(String str) {
        if (this.fExpected != null && this.fActual != null && !areStringsEqual()) {
            findCommonPrefix();
            findCommonSuffix();
            return Assert.format(str, compactString(this.fExpected), compactString(this.fActual));
        }
        return Assert.format(str, this.fExpected, this.fActual);
    }
}
