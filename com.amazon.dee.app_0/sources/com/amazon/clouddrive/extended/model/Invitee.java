package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.ObjectComparator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import lombok.NonNull;
/* loaded from: classes11.dex */
public final class Invitee implements Comparable<Invitee> {
    @NonNull
    private final InviteAddress address;
    private final String inviteDeliveryType;
    private final String name;

    public Invitee(@NonNull InviteAddress inviteAddress) {
        this(inviteAddress, null);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Invitee)) {
            return false;
        }
        Invitee invitee = (Invitee) obj;
        InviteAddress address = getAddress();
        InviteAddress address2 = invitee.getAddress();
        if (address != null ? !address.equals(address2) : address2 != null) {
            return false;
        }
        String name = getName();
        String name2 = invitee.getName();
        if (name != null ? !name.equals(name2) : name2 != null) {
            return false;
        }
        String inviteDeliveryType = getInviteDeliveryType();
        String inviteDeliveryType2 = invitee.getInviteDeliveryType();
        return inviteDeliveryType != null ? inviteDeliveryType.equals(inviteDeliveryType2) : inviteDeliveryType2 == null;
    }

    @NonNull
    public InviteAddress getAddress() {
        return this.address;
    }

    public String getInviteDeliveryType() {
        return this.inviteDeliveryType;
    }

    public String getName() {
        return this.name;
    }

    public int hashCode() {
        InviteAddress address = getAddress();
        int i = 43;
        int hashCode = address == null ? 43 : address.hashCode();
        String name = getName();
        int hashCode2 = ((hashCode + 59) * 59) + (name == null ? 43 : name.hashCode());
        String inviteDeliveryType = getInviteDeliveryType();
        int i2 = hashCode2 * 59;
        if (inviteDeliveryType != null) {
            i = inviteDeliveryType.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invitee(address=");
        outline107.append(getAddress());
        outline107.append(", name=");
        outline107.append(getName());
        outline107.append(", inviteDeliveryType=");
        outline107.append(getInviteDeliveryType());
        outline107.append(")");
        return outline107.toString();
    }

    public Invitee(@NonNull InviteAddress inviteAddress, String str) {
        this(inviteAddress, str, null);
    }

    @Override // java.lang.Comparable
    public int compareTo(Invitee invitee) {
        if (this.address.compareTo(invitee.address) < 0) {
            return -1;
        }
        if (this.address.compareTo(invitee.address) > 0) {
            return 1;
        }
        if (this.name == null && invitee.name == null) {
            return 0;
        }
        String str = this.name;
        if (str == null) {
            return -1;
        }
        String str2 = invitee.name;
        if (str2 == null) {
            return 1;
        }
        if (str.compareTo(str2) < 0) {
            return -1;
        }
        if (this.name.compareTo(invitee.name) > 0) {
            return 1;
        }
        int compare = ObjectComparator.compare(getInviteDeliveryType(), invitee.getInviteDeliveryType());
        if (compare == 0) {
            return 0;
        }
        return compare;
    }

    public Invitee(@NonNull InviteAddress inviteAddress, String str, String str2) {
        if (inviteAddress != null) {
            this.address = inviteAddress;
            this.name = str;
            this.inviteDeliveryType = str2;
            return;
        }
        throw new IllegalArgumentException("address is null");
    }
}
