package javax.mail.search;
/* loaded from: classes3.dex */
public abstract class StringTerm extends SearchTerm {
    private static final long serialVersionUID = 1274042129007696269L;
    protected boolean ignoreCase;
    protected String pattern;

    /* JADX INFO: Access modifiers changed from: protected */
    public StringTerm(String str) {
        this.pattern = str;
        this.ignoreCase = true;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof StringTerm)) {
            return false;
        }
        StringTerm stringTerm = (StringTerm) obj;
        return this.ignoreCase ? stringTerm.pattern.equalsIgnoreCase(this.pattern) && stringTerm.ignoreCase == this.ignoreCase : stringTerm.pattern.equals(this.pattern) && stringTerm.ignoreCase == this.ignoreCase;
    }

    public boolean getIgnoreCase() {
        return this.ignoreCase;
    }

    public String getPattern() {
        return this.pattern;
    }

    public int hashCode() {
        return this.ignoreCase ? this.pattern.hashCode() : ~this.pattern.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean match(String str) {
        int length = str.length() - this.pattern.length();
        for (int i = 0; i <= length; i++) {
            boolean z = this.ignoreCase;
            String str2 = this.pattern;
            if (str.regionMatches(z, i, str2, 0, str2.length())) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StringTerm(String str, boolean z) {
        this.pattern = str;
        this.ignoreCase = z;
    }
}
