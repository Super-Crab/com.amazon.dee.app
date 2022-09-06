package com.amazon.comms.ringservice;

import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.Participant;
import com.amazon.comms.util.ListenerSet;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
/* loaded from: classes12.dex */
public class ParticipantImpl implements Participant {
    @Nullable
    private boolean dropInPermission;
    @Nullable
    private String endpointDescription;
    @Nullable
    private String name;
    @Nonnull
    private final Call.Side origin;
    private final ListenerSet<Participant.Listener> participantListeners = new ListenerSet<>();
    @Nullable
    private String providerSpecifiedId;
    @Nullable
    private String providerSpecifiedName;
    @Nullable
    private final String uri;

    /* loaded from: classes12.dex */
    public static class ParticipantImplBuilder {
        private boolean dropInPermission;
        private String endpointDescription;
        private String name;
        private Call.Side origin;
        private String providerSpecifiedId;
        private String providerSpecifiedName;
        private String uri;

        ParticipantImplBuilder() {
        }

        public ParticipantImpl build() {
            return new ParticipantImpl(this.origin, this.uri, this.name, this.providerSpecifiedId, this.providerSpecifiedName, this.endpointDescription, this.dropInPermission);
        }

        public ParticipantImplBuilder dropInPermission(boolean z) {
            this.dropInPermission = z;
            return this;
        }

        public ParticipantImplBuilder endpointDescription(String str) {
            this.endpointDescription = str;
            return this;
        }

        public ParticipantImplBuilder name(String str) {
            this.name = str;
            return this;
        }

        public ParticipantImplBuilder origin(Call.Side side) {
            this.origin = side;
            return this;
        }

        public ParticipantImplBuilder providerSpecifiedId(String str) {
            this.providerSpecifiedId = str;
            return this;
        }

        public ParticipantImplBuilder providerSpecifiedName(String str) {
            this.providerSpecifiedName = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ParticipantImpl.ParticipantImplBuilder(origin=");
            outline107.append(this.origin);
            outline107.append(", uri=");
            outline107.append(this.uri);
            outline107.append(", name=");
            outline107.append(this.name);
            outline107.append(", providerSpecifiedId=");
            outline107.append(this.providerSpecifiedId);
            outline107.append(", providerSpecifiedName=");
            outline107.append(this.providerSpecifiedName);
            outline107.append(", endpointDescription=");
            outline107.append(this.endpointDescription);
            outline107.append(", dropInPermission=");
            return GeneratedOutlineSupport1.outline97(outline107, this.dropInPermission, ")");
        }

        public ParticipantImplBuilder uri(String str) {
            this.uri = str;
            return this;
        }
    }

    ParticipantImpl(@Nonnull Call.Side side, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable boolean z) {
        if (side != null) {
            this.origin = side;
            this.uri = str;
            this.name = str2;
            this.providerSpecifiedId = str3;
            this.providerSpecifiedName = str4;
            this.endpointDescription = str5;
            this.dropInPermission = z;
            return;
        }
        throw new IllegalArgumentException("origin is null");
    }

    public static ParticipantImplBuilder builder() {
        return new ParticipantImplBuilder();
    }

    private void notifyListeners() {
        this.participantListeners.notify(new ListenerSet.Notifier<Participant.Listener>() { // from class: com.amazon.comms.ringservice.ParticipantImpl.1
            @Override // com.amazon.comms.util.ListenerSet.Notifier
            public void notify(Participant.Listener listener) {
                listener.onUpdated(ParticipantImpl.this);
            }
        });
    }

    @Override // com.amazon.comms.calling.service.Participant
    public String getEndpointDescription() {
        return this.endpointDescription;
    }

    @Override // com.amazon.comms.calling.service.Participant
    public String getName() {
        return this.name;
    }

    @Override // com.amazon.comms.calling.service.Participant
    public String getProviderSpecifiedId() {
        return this.providerSpecifiedId;
    }

    @Override // com.amazon.comms.calling.service.Participant
    public String getProviderSpecifiedName() {
        return this.providerSpecifiedName;
    }

    @Override // com.amazon.comms.calling.service.Participant
    public String getUri() {
        return this.uri;
    }

    @Override // com.amazon.comms.calling.service.Participant
    public boolean hasDropInPermission() {
        return this.dropInPermission;
    }

    @Override // com.amazon.comms.calling.service.Participant
    public void registerListener(Participant.Listener listener) {
        this.participantListeners.add(listener);
    }

    public void setDropInPermission(boolean z) {
        this.dropInPermission = z;
        notifyListeners();
    }

    public void setEndpointDescription(String str) {
        this.endpointDescription = str;
        notifyListeners();
    }

    public void setName(String str) {
        this.name = str;
        notifyListeners();
    }

    public void setProviderSpecifiedId(@Nullable String str) {
        this.providerSpecifiedId = str;
    }

    public void setProviderSpecifiedName(@Nullable String str) {
        this.providerSpecifiedName = str;
    }

    @Override // com.amazon.comms.calling.service.Participant
    public void unregisterListener(Participant.Listener listener) {
        this.participantListeners.remove(listener);
    }
}
