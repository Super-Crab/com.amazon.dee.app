package com.amazon.comms.calling.service;

import com.amazon.comms.calling.service.Call;
import com.google.common.base.Predicate;
import javax.annotation.Nonnull;
/* loaded from: classes11.dex */
public final class CallFilters {
    public static final Predicate<Call> DEFAULT = new Predicate<Call>() { // from class: com.amazon.comms.calling.service.CallFilters.1
        @Override // com.google.common.base.Predicate
        public boolean apply(@Nonnull Call call) {
            return true;
        }
    };
    public static final Predicate<Call> INCOMING_CALLS_ONLY = new Predicate<Call>() { // from class: com.amazon.comms.calling.service.CallFilters.2
        @Override // com.google.common.base.Predicate
        public boolean apply(@Nonnull Call call) {
            return call.getOrigin() == Call.Side.Remote;
        }
    };
    public static final Predicate<Call> OUTGOING_CALLS_ONLY = new Predicate<Call>() { // from class: com.amazon.comms.calling.service.CallFilters.3
        @Override // com.google.common.base.Predicate
        public boolean apply(@Nonnull Call call) {
            return call.getOrigin() == Call.Side.Local;
        }
    };
    public static final Predicate<Call> ACCEPTED_CALLS_ONLY = new Predicate<Call>() { // from class: com.amazon.comms.calling.service.CallFilters.4
        @Override // com.google.common.base.Predicate
        public boolean apply(@Nonnull Call call) {
            return call.getState() == Call.State.Active;
        }
    };

    private CallFilters() {
    }
}
