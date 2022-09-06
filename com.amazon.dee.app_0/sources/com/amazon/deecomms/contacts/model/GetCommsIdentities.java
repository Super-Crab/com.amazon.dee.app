package com.amazon.deecomms.contacts.model;

import androidx.annotation.NonNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes12.dex */
public final class GetCommsIdentities {

    /* loaded from: classes12.dex */
    public static class Response {
        @NonNull
        @JsonProperty("identities")
        private List<Identity> identities;

        @NonNull
        public List<Identity> getIdentities() {
            return this.identities;
        }
    }

    private GetCommsIdentities() {
    }
}
