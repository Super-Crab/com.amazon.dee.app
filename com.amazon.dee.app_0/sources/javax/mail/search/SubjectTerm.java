package javax.mail.search;

import javax.mail.Message;
/* loaded from: classes3.dex */
public final class SubjectTerm extends StringTerm {
    private static final long serialVersionUID = 7481568618055573432L;

    public SubjectTerm(String str) {
        super(str);
    }

    @Override // javax.mail.search.StringTerm
    public boolean equals(Object obj) {
        if (!(obj instanceof SubjectTerm)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override // javax.mail.search.SearchTerm
    public boolean match(Message message) {
        try {
            String subject = message.getSubject();
            if (subject != null) {
                return super.match(subject);
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }
}
