package com.amazon.alexa.accessoryclient.common.connection;
/* loaded from: classes6.dex */
public final class Constants {

    /* loaded from: classes6.dex */
    public static class ConnectionChannels {
        public static final int initialize = 0;
        public static final int main = 2;
        public static final int teardown = 1;

        private ConnectionChannels() {
        }
    }

    /* loaded from: classes6.dex */
    public static final class Keys {
        public static final String CLIENT_CONNECTION_ACCEPTED = "clientConnectionAccepted";
        public static final String MAJOR_VERSION_KEY = "majorVersion";
        public static final String MINOR_VERSION_KEY = "minorVersion";
        public static final String TOP_LEVEL_BUNDLE_DATA_KEY = "data";
        public static final String UUID_KEY = "uuid";
    }

    private Constants() {
    }
}
