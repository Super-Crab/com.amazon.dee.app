package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveResponse;
import com.amazon.clouddrive.model.ObjectComparator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
/* loaded from: classes11.dex */
public final class ListContactsResponse implements CloudDriveResponse {
    private final List<Contact> contacts;
    private final int count;
    private final String nextToken;

    /* loaded from: classes11.dex */
    public static class ListContactsResponseBuilder {
        private ArrayList<Contact> contacts;
        private int count;
        private String nextToken;

        ListContactsResponseBuilder() {
        }

        public ListContactsResponse build() {
            ArrayList<Contact> arrayList = this.contacts;
            int size = arrayList == null ? 0 : arrayList.size();
            return new ListContactsResponse(this.count, this.nextToken, size != 0 ? size != 1 ? Collections.unmodifiableList(new ArrayList(this.contacts)) : Collections.singletonList(this.contacts.get(0)) : Collections.emptyList());
        }

        public ListContactsResponseBuilder clearContacts() {
            ArrayList<Contact> arrayList = this.contacts;
            if (arrayList != null) {
                arrayList.clear();
            }
            return this;
        }

        public ListContactsResponseBuilder contact(Contact contact) {
            if (this.contacts == null) {
                this.contacts = new ArrayList<>();
            }
            this.contacts.add(contact);
            return this;
        }

        public ListContactsResponseBuilder contacts(Collection<? extends Contact> collection) {
            if (this.contacts == null) {
                this.contacts = new ArrayList<>();
            }
            this.contacts.addAll(collection);
            return this;
        }

        public ListContactsResponseBuilder count(int i) {
            this.count = i;
            return this;
        }

        public ListContactsResponseBuilder nextToken(String str) {
            this.nextToken = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ListContactsResponse.ListContactsResponseBuilder(count=");
            outline107.append(this.count);
            outline107.append(", nextToken=");
            outline107.append(this.nextToken);
            outline107.append(", contacts=");
            outline107.append(this.contacts);
            outline107.append(")");
            return outline107.toString();
        }
    }

    ListContactsResponse(int i, String str, List<Contact> list) {
        this.count = i;
        this.nextToken = str;
        this.contacts = list;
    }

    public static ListContactsResponseBuilder builder() {
        return new ListContactsResponseBuilder();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListContactsResponse)) {
            return false;
        }
        ListContactsResponse listContactsResponse = (ListContactsResponse) obj;
        if (getCount() != listContactsResponse.getCount()) {
            return false;
        }
        String nextToken = getNextToken();
        String nextToken2 = listContactsResponse.getNextToken();
        if (nextToken != null ? !nextToken.equals(nextToken2) : nextToken2 != null) {
            return false;
        }
        List<Contact> contacts = getContacts();
        List<Contact> contacts2 = listContactsResponse.getContacts();
        return contacts != null ? contacts.equals(contacts2) : contacts2 == null;
    }

    public List<Contact> getContacts() {
        return this.contacts;
    }

    public int getCount() {
        return this.count;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        String nextToken = getNextToken();
        int i = 43;
        int count = ((getCount() + 59) * 59) + (nextToken == null ? 43 : nextToken.hashCode());
        List<Contact> contacts = getContacts();
        int i2 = count * 59;
        if (contacts != null) {
            i = contacts.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ListContactsResponse(count=");
        outline107.append(getCount());
        outline107.append(", nextToken=");
        outline107.append(getNextToken());
        outline107.append(", contacts=");
        outline107.append(getContacts());
        outline107.append(")");
        return outline107.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (cloudDriveResponse == this) {
            return 0;
        }
        if (!(cloudDriveResponse instanceof ListContactsResponse)) {
            return ListContactsResponse.class.getName().compareTo(cloudDriveResponse.getClass().getName());
        }
        ListContactsResponse listContactsResponse = (ListContactsResponse) cloudDriveResponse;
        int compare = ObjectComparator.compare(Integer.valueOf(getCount()), Integer.valueOf(listContactsResponse.getCount()));
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getContacts(), listContactsResponse.getContacts());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getNextToken(), listContactsResponse.getNextToken());
        if (compare3 == 0) {
            return 0;
        }
        return compare3;
    }
}
