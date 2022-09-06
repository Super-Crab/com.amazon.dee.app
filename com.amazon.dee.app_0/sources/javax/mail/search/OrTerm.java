package javax.mail.search;

import javax.mail.Message;
/* loaded from: classes3.dex */
public final class OrTerm extends SearchTerm {
    private static final long serialVersionUID = 5380534067523646936L;
    private SearchTerm[] terms;

    public OrTerm(SearchTerm searchTerm, SearchTerm searchTerm2) {
        this.terms = new SearchTerm[2];
        SearchTerm[] searchTermArr = this.terms;
        searchTermArr[0] = searchTerm;
        searchTermArr[1] = searchTerm2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof OrTerm)) {
            return false;
        }
        OrTerm orTerm = (OrTerm) obj;
        if (orTerm.terms.length != this.terms.length) {
            return false;
        }
        int i = 0;
        while (true) {
            SearchTerm[] searchTermArr = this.terms;
            if (i >= searchTermArr.length) {
                return true;
            }
            if (!searchTermArr[i].equals(orTerm.terms[i])) {
                return false;
            }
            i++;
        }
    }

    public SearchTerm[] getTerms() {
        return (SearchTerm[]) this.terms.clone();
    }

    public int hashCode() {
        int i = 0;
        int i2 = 0;
        while (true) {
            SearchTerm[] searchTermArr = this.terms;
            if (i < searchTermArr.length) {
                i2 += searchTermArr[i].hashCode();
                i++;
            } else {
                return i2;
            }
        }
    }

    @Override // javax.mail.search.SearchTerm
    public boolean match(Message message) {
        int i = 0;
        while (true) {
            SearchTerm[] searchTermArr = this.terms;
            if (i < searchTermArr.length) {
                if (searchTermArr[i].match(message)) {
                    return true;
                }
                i++;
            } else {
                return false;
            }
        }
    }

    public OrTerm(SearchTerm[] searchTermArr) {
        this.terms = new SearchTerm[searchTermArr.length];
        for (int i = 0; i < searchTermArr.length; i++) {
            this.terms[i] = searchTermArr[i];
        }
    }
}
