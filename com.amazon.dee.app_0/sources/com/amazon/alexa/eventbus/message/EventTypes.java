package com.amazon.alexa.eventbus.message;

import com.amazon.alexa.fitness.metrics.Metrics;
import com.amazon.alexa.mode.debug.EmulateConnection;
import com.android.tools.r8.GeneratedOutlineSupport1;
import edu.umd.cs.findbugs.annotations.SuppressWarnings;
@SuppressWarnings({"MS_SHOULD_BE_FINAL"})
@Deprecated
/* loaded from: classes7.dex */
public final class EventTypes {

    /* loaded from: classes7.dex */
    public static final class DeeApp extends Topic {
        public static final String USER_LOGGED_IN;
        public static final String USER_LOGGED_OUT;

        static {
            Topic.topic = "deeapp";
            USER_LOGGED_IN = Topic.concatenate("userLoggedIn");
            USER_LOGGED_OUT = Topic.concatenate("userLoggedOut");
        }
    }

    /* loaded from: classes7.dex */
    public static final class FFS extends Topic {
        static {
            Topic.topic = "ffs";
        }
    }

    /* loaded from: classes7.dex */
    public static final class TComm extends Topic {
        public static final String ALL;
        public static final String CONNECT;
        public static final String DISCONNECT;
        public static final String MESSAGE;

        static {
            Topic.topic = "tcomm";
            ALL = Topic.concatenate("*");
            CONNECT = Topic.concatenate(EmulateConnection.EXTRA_CONNECT);
            DISCONNECT = Topic.concatenate(Metrics.DISCONNECT);
            MESSAGE = Topic.concatenate("message");
        }
    }

    /* loaded from: classes7.dex */
    public static abstract class Topic {
        public static String topic;

        static String concatenate(String str) {
            return GeneratedOutlineSupport1.outline92(new StringBuilder(), topic, "::", str);
        }
    }
}
