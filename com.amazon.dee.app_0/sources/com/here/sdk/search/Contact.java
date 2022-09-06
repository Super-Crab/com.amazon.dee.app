package com.here.sdk.search;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import java.util.List;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class Contact {
    @NonNull
    @Deprecated
    public final List<String> emailAddresses;
    @NonNull
    public final List<EmailAddress> emails;
    @NonNull
    @Deprecated
    public final List<String> landlinePhoneNumbers;
    @NonNull
    public final List<LandlinePhone> landlinePhones;
    @NonNull
    @Deprecated
    public final List<String> mobilePhoneNumbers;
    @NonNull
    public final List<MobilePhone> mobilePhones;
    @NonNull
    @Deprecated
    public final List<String> websiteAddresses;
    @NonNull
    public final List<WebsiteAddress> websites;

    public Contact(@NonNull @Deprecated List<String> list, @NonNull @Deprecated List<String> list2, @NonNull @Deprecated List<String> list3, @NonNull @Deprecated List<String> list4, @NonNull List<LandlinePhone> list5, @NonNull List<MobilePhone> list6, @NonNull List<EmailAddress> list7, @NonNull List<WebsiteAddress> list8) {
        this.landlinePhoneNumbers = list;
        this.mobilePhoneNumbers = list2;
        this.emailAddresses = list3;
        this.websiteAddresses = list4;
        this.landlinePhones = list5;
        this.mobilePhones = list6;
        this.emails = list7;
        this.websites = list8;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Contact)) {
            return false;
        }
        Contact contact = (Contact) obj;
        return Objects.equals(this.landlinePhoneNumbers, contact.landlinePhoneNumbers) && Objects.equals(this.mobilePhoneNumbers, contact.mobilePhoneNumbers) && Objects.equals(this.emailAddresses, contact.emailAddresses) && Objects.equals(this.websiteAddresses, contact.websiteAddresses) && Objects.equals(this.landlinePhones, contact.landlinePhones) && Objects.equals(this.mobilePhones, contact.mobilePhones) && Objects.equals(this.emails, contact.emails) && Objects.equals(this.websites, contact.websites);
    }

    public int hashCode() {
        List<String> list = this.landlinePhoneNumbers;
        int i = 0;
        int hashCode = ((list != null ? list.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
        List<String> list2 = this.mobilePhoneNumbers;
        int hashCode2 = (hashCode + (list2 != null ? list2.hashCode() : 0)) * 31;
        List<String> list3 = this.emailAddresses;
        int hashCode3 = (hashCode2 + (list3 != null ? list3.hashCode() : 0)) * 31;
        List<String> list4 = this.websiteAddresses;
        int hashCode4 = (hashCode3 + (list4 != null ? list4.hashCode() : 0)) * 31;
        List<LandlinePhone> list5 = this.landlinePhones;
        int hashCode5 = (hashCode4 + (list5 != null ? list5.hashCode() : 0)) * 31;
        List<MobilePhone> list6 = this.mobilePhones;
        int hashCode6 = (hashCode5 + (list6 != null ? list6.hashCode() : 0)) * 31;
        List<EmailAddress> list7 = this.emails;
        int hashCode7 = (hashCode6 + (list7 != null ? list7.hashCode() : 0)) * 31;
        List<WebsiteAddress> list8 = this.websites;
        if (list8 != null) {
            i = list8.hashCode();
        }
        return hashCode7 + i;
    }
}
