package javax.mail.search;

import java.util.Date;
import javax.mail.Message;
/* loaded from: classes3.dex */
public final class SentDateTerm extends DateTerm {
    private static final long serialVersionUID = 5647755030530907263L;

    public SentDateTerm(int i, Date date) {
        super(i, date);
    }

    @Override // javax.mail.search.DateTerm, javax.mail.search.ComparisonTerm
    public boolean equals(Object obj) {
        if (!(obj instanceof SentDateTerm)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override // javax.mail.search.SearchTerm
    public boolean match(Message message) {
        try {
            Date sentDate = message.getSentDate();
            if (sentDate != null) {
                return super.match(sentDate);
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }
}
