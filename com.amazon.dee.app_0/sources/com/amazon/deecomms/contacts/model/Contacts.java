package com.amazon.deecomms.contacts.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.util.List;
/* loaded from: classes12.dex */
public class Contacts {
    private List<Contact> contacts;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Contacts) {
            return Objects.equal(this.contacts, ((Contacts) obj).contacts);
        }
        return false;
    }

    public List<Contact> getContacts() {
        return this.contacts;
    }

    public int hashCode() {
        return Objects.hashCode(this.contacts);
    }

    public void setContacts(List<Contact> list) {
        this.contacts = list;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("contacts", this.contacts).toString();
    }
}
