package com.google.i18n.phonenumbers;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.i18n.phonenumbers.Phonenumber;
import java.util.Arrays;
/* loaded from: classes3.dex */
public final class PhoneNumberMatch {
    private final Phonenumber.PhoneNumber number;
    private final String rawString;
    private final int start;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PhoneNumberMatch(int i, String str, Phonenumber.PhoneNumber phoneNumber) {
        if (i >= 0) {
            if (str != null && phoneNumber != null) {
                this.start = i;
                this.rawString = str;
                this.number = phoneNumber;
                return;
            }
            throw new NullPointerException();
        }
        throw new IllegalArgumentException("Start index must be >= 0.");
    }

    public int end() {
        return this.rawString.length() + this.start;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PhoneNumberMatch)) {
            return false;
        }
        PhoneNumberMatch phoneNumberMatch = (PhoneNumberMatch) obj;
        return this.rawString.equals(phoneNumberMatch.rawString) && this.start == phoneNumberMatch.start && this.number.equals(phoneNumberMatch.number);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.start), this.rawString, this.number});
    }

    public Phonenumber.PhoneNumber number() {
        return this.number;
    }

    public String rawString() {
        return this.rawString;
    }

    public int start() {
        return this.start;
    }

    public String toString() {
        int start = start();
        int end = end();
        String valueOf = String.valueOf(this.rawString);
        StringBuilder sb = new StringBuilder(valueOf.length() + 43);
        sb.append("PhoneNumberMatch [");
        sb.append(start);
        sb.append(",");
        sb.append(end);
        return GeneratedOutlineSupport1.outline91(sb, ") ", valueOf);
    }
}
