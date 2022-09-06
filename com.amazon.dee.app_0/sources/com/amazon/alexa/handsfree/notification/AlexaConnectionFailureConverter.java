package com.amazon.alexa.handsfree.notification;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.metrics.NotificationEventMetadata;
import java.util.Optional;
/* loaded from: classes8.dex */
public class AlexaConnectionFailureConverter {
    @VisibleForTesting
    static final String CONNECTION_FAILURE_ALEXA_LEADER_MISSED = "ALEXA_LEADER_IS_MISSED";
    @VisibleForTesting
    static final String CONNECTION_FAILURE_AUTHORIZATION_MISSED = "AUTHORIZATION_IS_MISSED";
    @VisibleForTesting
    static final String CONNECTION_FAILURE_UNKNOWN = "UNKNOWN";

    public Optional<NotificationEventMetadata.ConnectionFailureReason> getConnectionFailureReasonFromString(@NonNull String str) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode == -119857430) {
            if (str.equals(CONNECTION_FAILURE_AUTHORIZATION_MISSED)) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode != 433141802) {
            if (hashCode == 1276819062 && str.equals(CONNECTION_FAILURE_ALEXA_LEADER_MISSED)) {
                c = 0;
            }
            c = 65535;
        } else {
            if (str.equals("UNKNOWN")) {
                c = 2;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c == 1) {
                return Optional.of(NotificationEventMetadata.ConnectionFailureReason.AUTHORIZATION_IS_MISSED);
            }
            if (c != 2) {
                return Optional.empty();
            }
            return Optional.of(NotificationEventMetadata.ConnectionFailureReason.UNKNOWN_REASON);
        }
        return Optional.of(NotificationEventMetadata.ConnectionFailureReason.ALEXA_LEADER_IS_MISSED);
    }
}
