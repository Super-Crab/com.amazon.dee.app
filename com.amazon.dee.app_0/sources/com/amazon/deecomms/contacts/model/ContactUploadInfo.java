package com.amazon.deecomms.contacts.model;

import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.common.base.Objects;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes12.dex */
public class ContactUploadInfo {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ContactUploadInfo.class);
    private String company;
    private String deviceContactId;
    private List<EmailAddress> emails;
    private Name name;
    private List<PhoneNumber> numbers;
    private String relationship;
    private List<Relationship> relationships;
    private String serverContactId;

    /* loaded from: classes12.dex */
    public class EmailAddress {
        private String emailAddress;
        private String rawType;

        public EmailAddress(String str, String str2) {
            this.rawType = str2;
            this.emailAddress = str;
        }

        public String getEmailAddress() {
            return this.emailAddress;
        }

        public String getRawType() {
            return this.rawType;
        }

        public int hashCode() {
            return Objects.hashCode(this.rawType, this.emailAddress);
        }
    }

    /* loaded from: classes12.dex */
    public class Name {
        private String firstName;
        private String lastName;
        private String nickName;
        private String phoneticFirstName;
        private String phoneticLastName;
        private String pronunciationFirstName;
        private String pronunciationLastName;

        public Name(ContactUploadInfo contactUploadInfo, String str, String str2) {
            this(str, str2, null, null, null, null, null);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Name.class != obj.getClass()) {
                return false;
            }
            Name name = (Name) obj;
            return Objects.equal(this.firstName, name.firstName) && Objects.equal(this.lastName, name.lastName) && Objects.equal(this.phoneticFirstName, name.phoneticFirstName) && Objects.equal(this.phoneticLastName, name.phoneticLastName) && Objects.equal(this.pronunciationFirstName, name.pronunciationFirstName) && Objects.equal(this.pronunciationLastName, name.pronunciationLastName) && Objects.equal(this.nickName, name.nickName);
        }

        public String getFirstName() {
            return this.firstName;
        }

        public String getLastName() {
            return this.lastName;
        }

        public String getNickName() {
            return this.nickName;
        }

        public String getPhoneticFirstName() {
            return this.phoneticFirstName;
        }

        public String getPhoneticLastName() {
            return this.phoneticLastName;
        }

        public String getPronunciationFirstName() {
            return this.pronunciationFirstName;
        }

        public String getPronunciationLastName() {
            return this.pronunciationLastName;
        }

        public int hashCode() {
            return Objects.hashCode(this.firstName, this.lastName, this.phoneticFirstName, this.phoneticLastName, this.pronunciationFirstName, this.pronunciationLastName, this.nickName);
        }

        public void setFirstName(String str) {
            this.firstName = str;
        }

        public void setLastName(String str) {
            this.lastName = str;
        }

        public void setNickName(String str) {
            this.nickName = str;
        }

        public void setPhoneticFirstName(String str) {
            this.phoneticFirstName = str;
        }

        public void setPhoneticLastName(String str) {
            this.phoneticLastName = str;
        }

        public void setPronunciationFirstName(String str) {
            this.pronunciationFirstName = str;
        }

        public void setPronunciationLastName(String str) {
            this.pronunciationLastName = str;
        }

        public Name(ContactUploadInfo contactUploadInfo, String str, String str2, String str3) {
            this(str, str2, null, null, null, null, str3);
        }

        public Name(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
            this.firstName = str;
            this.lastName = str2;
            this.phoneticFirstName = str3;
            this.phoneticLastName = str4;
            this.pronunciationFirstName = str5;
            this.pronunciationLastName = str6;
            this.nickName = str7;
        }
    }

    /* loaded from: classes12.dex */
    public class PhoneNumber {
        private String number;
        private String rawType;

        public PhoneNumber(String str, String str2) {
            this.rawType = str2;
            this.number = str;
        }

        public String getNumber() {
            return this.number;
        }

        public String getRawType() {
            return this.rawType;
        }

        public int hashCode() {
            return Objects.hashCode(this.rawType, this.number);
        }
    }

    /* loaded from: classes12.dex */
    public class Relationship {
        private String contactName;
        private String relationShipType;

        public Relationship(String str, String str2) {
            this.relationShipType = str2;
            this.contactName = str;
        }

        public String getContactName() {
            return this.contactName;
        }

        public String getRelationType() {
            return this.relationShipType;
        }

        public int hashCode() {
            return Objects.hashCode(this.relationShipType, this.contactName);
        }
    }

    public void addEmailAddress(String str, String str2) {
        if (this.emails == null) {
            this.emails = new ArrayList();
        }
        if (this.emails.size() < 25) {
            this.emails.add(new EmailAddress(str, str2));
            return;
        }
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("The email count being imported ");
        outline1.append(this.emails.size());
        outline1.append(" is greater than the permitted amount");
        commsLogger.w(outline1.toString());
    }

    public void addPhoneNumber(String str, String str2) {
        if (this.numbers == null) {
            this.numbers = new ArrayList();
        }
        if (this.numbers.size() < 25) {
            this.numbers.add(new PhoneNumber(str, str2));
        }
    }

    public void addRelationShip(String str, String str2) {
        if (this.relationships == null) {
            this.relationships = new ArrayList();
        }
        this.relationships.add(new Relationship(str, str2));
    }

    public String getCompany() {
        return this.company;
    }

    public String getDeviceContactId() {
        return this.deviceContactId;
    }

    public List<EmailAddress> getEmails() {
        return this.emails;
    }

    public Name getName() {
        return this.name;
    }

    public List<PhoneNumber> getNumbers() {
        return this.numbers;
    }

    public String getRelationship() {
        return this.relationship;
    }

    public List<Relationship> getRelationships() {
        return this.relationships;
    }

    public String getServerContactId() {
        return this.serverContactId;
    }

    public int hashCode() {
        return Objects.hashCode(this.name, this.numbers, this.emails, this.company);
    }

    public void setCompany(String str) {
        this.company = str;
    }

    public void setContactName(String str, String str2) {
        this.name = new Name(this, str, str2);
    }

    public void setDeviceContactId(String str) {
        this.deviceContactId = str;
    }

    public void setEmailAddresses(List<EmailAddress> list) {
        this.emails = list;
    }

    public void setPhoneNumbers(List<PhoneNumber> list) {
        this.numbers = list;
    }

    public void setRelationship(String str) {
        this.relationship = str;
    }

    public void setServerContactId(String str) {
        this.serverContactId = str;
    }

    public void setContactName(String str, String str2, String str3) {
        this.name = new Name(str, str2, null, null, null, null, str3);
    }

    public void setContactName(String str, String str2, String str3, String str4, String str5) {
        this.name = new Name(str, str2, str3, str4, null, null, str5);
    }
}
