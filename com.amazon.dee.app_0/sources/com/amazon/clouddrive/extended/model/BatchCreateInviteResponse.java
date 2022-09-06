package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveResponse;
import com.amazon.clouddrive.model.ObjectComparator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
/* loaded from: classes11.dex */
public final class BatchCreateInviteResponse implements CloudDriveResponse {
    private final List<Error> errors;
    private final List<Invite> invites;

    /* loaded from: classes11.dex */
    public static class BatchCreateInviteResponseBuilder {
        private ArrayList<Error> errors;
        private ArrayList<Invite> invites;

        BatchCreateInviteResponseBuilder() {
        }

        public BatchCreateInviteResponse build() {
            ArrayList<Invite> arrayList = this.invites;
            int size = arrayList == null ? 0 : arrayList.size();
            List unmodifiableList = size != 0 ? size != 1 ? Collections.unmodifiableList(new ArrayList(this.invites)) : Collections.singletonList(this.invites.get(0)) : Collections.emptyList();
            ArrayList<Error> arrayList2 = this.errors;
            int size2 = arrayList2 == null ? 0 : arrayList2.size();
            return new BatchCreateInviteResponse(unmodifiableList, size2 != 0 ? size2 != 1 ? Collections.unmodifiableList(new ArrayList(this.errors)) : Collections.singletonList(this.errors.get(0)) : Collections.emptyList());
        }

        public BatchCreateInviteResponseBuilder clearErrors() {
            ArrayList<Error> arrayList = this.errors;
            if (arrayList != null) {
                arrayList.clear();
            }
            return this;
        }

        public BatchCreateInviteResponseBuilder clearInvites() {
            ArrayList<Invite> arrayList = this.invites;
            if (arrayList != null) {
                arrayList.clear();
            }
            return this;
        }

        public BatchCreateInviteResponseBuilder error(Error error) {
            if (this.errors == null) {
                this.errors = new ArrayList<>();
            }
            this.errors.add(error);
            return this;
        }

        public BatchCreateInviteResponseBuilder errors(Collection<? extends Error> collection) {
            if (this.errors == null) {
                this.errors = new ArrayList<>();
            }
            this.errors.addAll(collection);
            return this;
        }

        public BatchCreateInviteResponseBuilder invite(Invite invite) {
            if (this.invites == null) {
                this.invites = new ArrayList<>();
            }
            this.invites.add(invite);
            return this;
        }

        public BatchCreateInviteResponseBuilder invites(Collection<? extends Invite> collection) {
            if (this.invites == null) {
                this.invites = new ArrayList<>();
            }
            this.invites.addAll(collection);
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BatchCreateInviteResponse.BatchCreateInviteResponseBuilder(invites=");
            outline107.append(this.invites);
            outline107.append(", errors=");
            outline107.append(this.errors);
            outline107.append(")");
            return outline107.toString();
        }
    }

    /* loaded from: classes11.dex */
    public static final class Error implements Comparable<Error> {
        private final InviteAddress address;
        private final String message;

        /* loaded from: classes11.dex */
        public static class ErrorBuilder {
            private InviteAddress address;
            private String message;

            ErrorBuilder() {
            }

            public ErrorBuilder address(InviteAddress inviteAddress) {
                this.address = inviteAddress;
                return this;
            }

            public Error build() {
                return new Error(this.address, this.message);
            }

            public ErrorBuilder message(String str) {
                this.message = str;
                return this;
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BatchCreateInviteResponse.Error.ErrorBuilder(address=");
                outline107.append(this.address);
                outline107.append(", message=");
                return GeneratedOutlineSupport1.outline91(outline107, this.message, ")");
            }
        }

        Error(InviteAddress inviteAddress, String str) {
            this.address = inviteAddress;
            this.message = str;
        }

        public static ErrorBuilder builder() {
            return new ErrorBuilder();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Error)) {
                return false;
            }
            Error error = (Error) obj;
            InviteAddress address = getAddress();
            InviteAddress address2 = error.getAddress();
            if (address != null ? !address.equals(address2) : address2 != null) {
                return false;
            }
            String message = getMessage();
            String message2 = error.getMessage();
            return message != null ? message.equals(message2) : message2 == null;
        }

        public InviteAddress getAddress() {
            return this.address;
        }

        public String getMessage() {
            return this.message;
        }

        public int hashCode() {
            InviteAddress address = getAddress();
            int i = 43;
            int hashCode = address == null ? 43 : address.hashCode();
            String message = getMessage();
            int i2 = (hashCode + 59) * 59;
            if (message != null) {
                i = message.hashCode();
            }
            return i2 + i;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BatchCreateInviteResponse.Error(address=");
            outline107.append(getAddress());
            outline107.append(", message=");
            outline107.append(getMessage());
            outline107.append(")");
            return outline107.toString();
        }

        @Override // java.lang.Comparable
        public int compareTo(Error error) {
            if (this.address.compareTo(error.address) < 0) {
                return -1;
            }
            if (this.address.compareTo(error.address) > 0) {
                return 1;
            }
            if (this.message.compareTo(error.message) < 0) {
                return -1;
            }
            return this.message.compareTo(error.message) > 0 ? 1 : 0;
        }
    }

    /* loaded from: classes11.dex */
    public static final class Invite implements Comparable<Invite> {
        private final InviteAddress address;
        private final String createdDate;
        private final String groupId;
        private final String inviteId;
        private final String invitedBy;
        private final String modifiedDate;
        private final String name;
        private final int sendAttemptCount;
        private final String sentDate;
        private final Status status;
        private final long version;

        /* loaded from: classes11.dex */
        public static class InviteBuilder {
            private InviteAddress address;
            private String createdDate;
            private String groupId;
            private String inviteId;
            private String invitedBy;
            private String modifiedDate;
            private String name;
            private int sendAttemptCount;
            private String sentDate;
            private Status status;
            private long version;

            InviteBuilder() {
            }

            public InviteBuilder address(InviteAddress inviteAddress) {
                this.address = inviteAddress;
                return this;
            }

            public Invite build() {
                return new Invite(this.groupId, this.invitedBy, this.inviteId, this.name, this.address, this.status, this.sendAttemptCount, this.createdDate, this.modifiedDate, this.sentDate, this.version);
            }

            public InviteBuilder createdDate(String str) {
                this.createdDate = str;
                return this;
            }

            public InviteBuilder groupId(String str) {
                this.groupId = str;
                return this;
            }

            public InviteBuilder inviteId(String str) {
                this.inviteId = str;
                return this;
            }

            public InviteBuilder invitedBy(String str) {
                this.invitedBy = str;
                return this;
            }

            public InviteBuilder modifiedDate(String str) {
                this.modifiedDate = str;
                return this;
            }

            public InviteBuilder name(String str) {
                this.name = str;
                return this;
            }

            public InviteBuilder sendAttemptCount(int i) {
                this.sendAttemptCount = i;
                return this;
            }

            public InviteBuilder sentDate(String str) {
                this.sentDate = str;
                return this;
            }

            public InviteBuilder status(Status status) {
                this.status = status;
                return this;
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BatchCreateInviteResponse.Invite.InviteBuilder(groupId=");
                outline107.append(this.groupId);
                outline107.append(", invitedBy=");
                outline107.append(this.invitedBy);
                outline107.append(", inviteId=");
                outline107.append(this.inviteId);
                outline107.append(", name=");
                outline107.append(this.name);
                outline107.append(", address=");
                outline107.append(this.address);
                outline107.append(", status=");
                outline107.append(this.status);
                outline107.append(", sendAttemptCount=");
                outline107.append(this.sendAttemptCount);
                outline107.append(", createdDate=");
                outline107.append(this.createdDate);
                outline107.append(", modifiedDate=");
                outline107.append(this.modifiedDate);
                outline107.append(", sentDate=");
                outline107.append(this.sentDate);
                outline107.append(", version=");
                return GeneratedOutlineSupport1.outline87(outline107, this.version, ")");
            }

            public InviteBuilder version(long j) {
                this.version = j;
                return this;
            }
        }

        /* loaded from: classes11.dex */
        public static final class Status implements Comparable<Status> {
            private final String message;
            private final String reasonCode;
            private final String state;

            /* loaded from: classes11.dex */
            public static class StatusBuilder {
                private String message;
                private String reasonCode;
                private String state;

                StatusBuilder() {
                }

                public Status build() {
                    return new Status(this.state, this.reasonCode, this.message);
                }

                public StatusBuilder message(String str) {
                    this.message = str;
                    return this;
                }

                public StatusBuilder reasonCode(String str) {
                    this.reasonCode = str;
                    return this;
                }

                public StatusBuilder state(String str) {
                    this.state = str;
                    return this;
                }

                public String toString() {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BatchCreateInviteResponse.Invite.Status.StatusBuilder(state=");
                    outline107.append(this.state);
                    outline107.append(", reasonCode=");
                    outline107.append(this.reasonCode);
                    outline107.append(", message=");
                    return GeneratedOutlineSupport1.outline91(outline107, this.message, ")");
                }
            }

            Status(String str, String str2, String str3) {
                this.state = str;
                this.reasonCode = str2;
                this.message = str3;
            }

            public static StatusBuilder builder() {
                return new StatusBuilder();
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Status)) {
                    return false;
                }
                Status status = (Status) obj;
                String state = getState();
                String state2 = status.getState();
                if (state != null ? !state.equals(state2) : state2 != null) {
                    return false;
                }
                String reasonCode = getReasonCode();
                String reasonCode2 = status.getReasonCode();
                if (reasonCode != null ? !reasonCode.equals(reasonCode2) : reasonCode2 != null) {
                    return false;
                }
                String message = getMessage();
                String message2 = status.getMessage();
                return message != null ? message.equals(message2) : message2 == null;
            }

            public String getMessage() {
                return this.message;
            }

            public String getReasonCode() {
                return this.reasonCode;
            }

            public String getState() {
                return this.state;
            }

            public int hashCode() {
                String state = getState();
                int i = 43;
                int hashCode = state == null ? 43 : state.hashCode();
                String reasonCode = getReasonCode();
                int hashCode2 = ((hashCode + 59) * 59) + (reasonCode == null ? 43 : reasonCode.hashCode());
                String message = getMessage();
                int i2 = hashCode2 * 59;
                if (message != null) {
                    i = message.hashCode();
                }
                return i2 + i;
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BatchCreateInviteResponse.Invite.Status(state=");
                outline107.append(getState());
                outline107.append(", reasonCode=");
                outline107.append(getReasonCode());
                outline107.append(", message=");
                outline107.append(getMessage());
                outline107.append(")");
                return outline107.toString();
            }

            @Override // java.lang.Comparable
            public int compareTo(Status status) {
                if (this.state.compareTo(status.state) < 0) {
                    return -1;
                }
                if (this.state.compareTo(status.state) > 0) {
                    return 1;
                }
                if (this.reasonCode.compareTo(status.reasonCode) < 0) {
                    return -1;
                }
                if (this.reasonCode.compareTo(status.reasonCode) > 0) {
                    return 1;
                }
                int compare = ObjectComparator.compare(getState(), status.getState());
                if (compare == 0) {
                    return 0;
                }
                return compare;
            }
        }

        Invite(String str, String str2, String str3, String str4, InviteAddress inviteAddress, Status status, int i, String str5, String str6, String str7, long j) {
            this.groupId = str;
            this.invitedBy = str2;
            this.inviteId = str3;
            this.name = str4;
            this.address = inviteAddress;
            this.status = status;
            this.sendAttemptCount = i;
            this.createdDate = str5;
            this.modifiedDate = str6;
            this.sentDate = str7;
            this.version = j;
        }

        public static InviteBuilder builder() {
            return new InviteBuilder();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Invite)) {
                return false;
            }
            Invite invite = (Invite) obj;
            String groupId = getGroupId();
            String groupId2 = invite.getGroupId();
            if (groupId != null ? !groupId.equals(groupId2) : groupId2 != null) {
                return false;
            }
            String invitedBy = getInvitedBy();
            String invitedBy2 = invite.getInvitedBy();
            if (invitedBy != null ? !invitedBy.equals(invitedBy2) : invitedBy2 != null) {
                return false;
            }
            String inviteId = getInviteId();
            String inviteId2 = invite.getInviteId();
            if (inviteId != null ? !inviteId.equals(inviteId2) : inviteId2 != null) {
                return false;
            }
            String name = getName();
            String name2 = invite.getName();
            if (name != null ? !name.equals(name2) : name2 != null) {
                return false;
            }
            InviteAddress address = getAddress();
            InviteAddress address2 = invite.getAddress();
            if (address != null ? !address.equals(address2) : address2 != null) {
                return false;
            }
            Status status = getStatus();
            Status status2 = invite.getStatus();
            if (status != null ? !status.equals(status2) : status2 != null) {
                return false;
            }
            if (getSendAttemptCount() != invite.getSendAttemptCount()) {
                return false;
            }
            String createdDate = getCreatedDate();
            String createdDate2 = invite.getCreatedDate();
            if (createdDate != null ? !createdDate.equals(createdDate2) : createdDate2 != null) {
                return false;
            }
            String modifiedDate = getModifiedDate();
            String modifiedDate2 = invite.getModifiedDate();
            if (modifiedDate != null ? !modifiedDate.equals(modifiedDate2) : modifiedDate2 != null) {
                return false;
            }
            String sentDate = getSentDate();
            String sentDate2 = invite.getSentDate();
            if (sentDate != null ? !sentDate.equals(sentDate2) : sentDate2 != null) {
                return false;
            }
            return getVersion() == invite.getVersion();
        }

        public InviteAddress getAddress() {
            return this.address;
        }

        public String getCreatedDate() {
            return this.createdDate;
        }

        public String getGroupId() {
            return this.groupId;
        }

        public String getInviteId() {
            return this.inviteId;
        }

        public String getInvitedBy() {
            return this.invitedBy;
        }

        public String getModifiedDate() {
            return this.modifiedDate;
        }

        public String getName() {
            return this.name;
        }

        public int getSendAttemptCount() {
            return this.sendAttemptCount;
        }

        public String getSentDate() {
            return this.sentDate;
        }

        public Status getStatus() {
            return this.status;
        }

        public long getVersion() {
            return this.version;
        }

        public int hashCode() {
            String groupId = getGroupId();
            int i = 43;
            int hashCode = groupId == null ? 43 : groupId.hashCode();
            String invitedBy = getInvitedBy();
            int hashCode2 = ((hashCode + 59) * 59) + (invitedBy == null ? 43 : invitedBy.hashCode());
            String inviteId = getInviteId();
            int hashCode3 = (hashCode2 * 59) + (inviteId == null ? 43 : inviteId.hashCode());
            String name = getName();
            int hashCode4 = (hashCode3 * 59) + (name == null ? 43 : name.hashCode());
            InviteAddress address = getAddress();
            int hashCode5 = (hashCode4 * 59) + (address == null ? 43 : address.hashCode());
            Status status = getStatus();
            int sendAttemptCount = getSendAttemptCount() + (((hashCode5 * 59) + (status == null ? 43 : status.hashCode())) * 59);
            String createdDate = getCreatedDate();
            int hashCode6 = (sendAttemptCount * 59) + (createdDate == null ? 43 : createdDate.hashCode());
            String modifiedDate = getModifiedDate();
            int hashCode7 = (hashCode6 * 59) + (modifiedDate == null ? 43 : modifiedDate.hashCode());
            String sentDate = getSentDate();
            int i2 = hashCode7 * 59;
            if (sentDate != null) {
                i = sentDate.hashCode();
            }
            long version = getVersion();
            return ((i2 + i) * 59) + ((int) (version ^ (version >>> 32)));
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BatchCreateInviteResponse.Invite(groupId=");
            outline107.append(getGroupId());
            outline107.append(", invitedBy=");
            outline107.append(getInvitedBy());
            outline107.append(", inviteId=");
            outline107.append(getInviteId());
            outline107.append(", name=");
            outline107.append(getName());
            outline107.append(", address=");
            outline107.append(getAddress());
            outline107.append(", status=");
            outline107.append(getStatus());
            outline107.append(", sendAttemptCount=");
            outline107.append(getSendAttemptCount());
            outline107.append(", createdDate=");
            outline107.append(getCreatedDate());
            outline107.append(", modifiedDate=");
            outline107.append(getModifiedDate());
            outline107.append(", sentDate=");
            outline107.append(getSentDate());
            outline107.append(", version=");
            outline107.append(getVersion());
            outline107.append(")");
            return outline107.toString();
        }

        @Override // java.lang.Comparable
        public int compareTo(Invite invite) {
            if (this.groupId.compareTo(invite.groupId) < 0) {
                return -1;
            }
            if (this.groupId.compareTo(invite.groupId) > 0) {
                return 1;
            }
            if (this.invitedBy.compareTo(invite.invitedBy) < 0) {
                return -1;
            }
            if (this.invitedBy.compareTo(invite.invitedBy) > 0) {
                return 1;
            }
            if (this.inviteId.compareTo(invite.inviteId) < 0) {
                return -1;
            }
            if (this.inviteId.compareTo(invite.inviteId) > 0) {
                return 1;
            }
            if (this.name.compareTo(invite.name) < 0) {
                return -1;
            }
            if (this.name.compareTo(invite.name) > 0) {
                return 1;
            }
            if (this.address.compareTo(invite.address) < 0) {
                return -1;
            }
            if (this.address.compareTo(invite.address) > 0) {
                return 1;
            }
            if (this.status.compareTo(invite.status) < 0) {
                return -1;
            }
            if (this.status.compareTo(invite.status) > 0) {
                return 1;
            }
            int i = this.sendAttemptCount;
            int i2 = invite.sendAttemptCount;
            if (i < i2) {
                return -1;
            }
            if (i > i2) {
                return 1;
            }
            int compare = ObjectComparator.compare(this.createdDate, invite.createdDate);
            if (compare != 0) {
                return compare;
            }
            int compare2 = ObjectComparator.compare(this.modifiedDate, invite.modifiedDate);
            if (compare2 != 0) {
                return compare2;
            }
            int compare3 = ObjectComparator.compare(this.sentDate, invite.sentDate);
            if (compare3 != 0) {
                return compare3;
            }
            long j = this.version;
            long j2 = invite.version;
            if (j < j2) {
                return -1;
            }
            return j > j2 ? 1 : 0;
        }
    }

    BatchCreateInviteResponse(List<Invite> list, List<Error> list2) {
        this.invites = list;
        this.errors = list2;
    }

    public static BatchCreateInviteResponseBuilder builder() {
        return new BatchCreateInviteResponseBuilder();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BatchCreateInviteResponse)) {
            return false;
        }
        BatchCreateInviteResponse batchCreateInviteResponse = (BatchCreateInviteResponse) obj;
        List<Invite> invites = getInvites();
        List<Invite> invites2 = batchCreateInviteResponse.getInvites();
        if (invites != null ? !invites.equals(invites2) : invites2 != null) {
            return false;
        }
        List<Error> errors = getErrors();
        List<Error> errors2 = batchCreateInviteResponse.getErrors();
        return errors != null ? errors.equals(errors2) : errors2 == null;
    }

    public List<Error> getErrors() {
        return this.errors;
    }

    public List<Invite> getInvites() {
        return this.invites;
    }

    public int hashCode() {
        List<Invite> invites = getInvites();
        int i = 43;
        int hashCode = invites == null ? 43 : invites.hashCode();
        List<Error> errors = getErrors();
        int i2 = (hashCode + 59) * 59;
        if (errors != null) {
            i = errors.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BatchCreateInviteResponse(invites=");
        outline107.append(getInvites());
        outline107.append(", errors=");
        outline107.append(getErrors());
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
        if (!(cloudDriveResponse instanceof BatchCreateInviteResponse)) {
            return BatchCreateInviteResponse.class.getName().compareTo(cloudDriveResponse.getClass().getName());
        }
        BatchCreateInviteResponse batchCreateInviteResponse = (BatchCreateInviteResponse) cloudDriveResponse;
        int compareCollections = ObjectComparator.compareCollections(this.invites, batchCreateInviteResponse.invites);
        if (compareCollections != 0) {
            return compareCollections;
        }
        int compareCollections2 = ObjectComparator.compareCollections(this.errors, batchCreateInviteResponse.errors);
        if (compareCollections2 == 0) {
            return 0;
        }
        return compareCollections2;
    }
}
