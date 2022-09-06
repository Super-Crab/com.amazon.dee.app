package com.amazon.deecomms.oobe.fragments;

import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.deecomms.oobe.PCAProfile;
import com.amazon.deecomms.oobe.Person;
import java.util.Objects;
/* loaded from: classes12.dex */
public class PersonItem implements Comparable<PersonItem> {
    public final boolean isPlaceholder;
    public final PCAProfile pcaProfile;
    public final Person person;

    /* loaded from: classes12.dex */
    static class ViewHolder {
        public ImageView arrow;
        public ImageView profileIcon;
        public TextView userIdentification;
    }

    public PersonItem(Person person, PCAProfile pCAProfile) {
        this.person = person;
        this.pcaProfile = pCAProfile;
        this.isPlaceholder = false;
    }

    private int rank(PersonItem personItem) {
        if (personItem.isPlaceholder) {
            return 1;
        }
        Person person = personItem.person;
        return (person == null || person.isChild) ? 2 : 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PersonItem.class != obj.getClass()) {
            return false;
        }
        PersonItem personItem = (PersonItem) obj;
        return this.isPlaceholder == personItem.isPlaceholder && Objects.equals(this.person, personItem.person);
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.isPlaceholder), this.person);
    }

    @Override // java.lang.Comparable
    public int compareTo(@NonNull PersonItem personItem) {
        return Integer.compare(rank(this), rank(personItem));
    }

    public PersonItem() {
        this.person = null;
        this.pcaProfile = null;
        this.isPlaceholder = true;
    }
}
