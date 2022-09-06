package com.amazon.alexa.api;

import android.os.Bundle;
import android.util.Log;
import com.amazon.alexa.api.Bundles;
import com.amazon.alexa.client.annotations.Nullable;
/* loaded from: classes6.dex */
public abstract class ApiCallFailure {
    private static final String TAG = "ApiCallFailure";
    @Nullable
    private final Exception exception;
    private final FailureType failureType;
    @Nullable
    private final String message;

    /* renamed from: com.amazon.alexa.api.ApiCallFailure$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$ApiCallFailure$FailureType = new int[FailureType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$ApiCallFailure$FailureType[FailureType.NETWORK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$ApiCallFailure$FailureType[FailureType.TIMEOUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$ApiCallFailure$FailureType[FailureType.INTERNAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$ApiCallFailure$FailureType[FailureType.AUTHORIZATION.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$ApiCallFailure$FailureType[FailureType.MESSAGING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$ApiCallFailure$FailureType[FailureType.SERVER_ERROR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$ApiCallFailure$FailureType[FailureType.UNKNOWN.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public enum ApiCallFailureKeys implements Bundles.Key {
        FAILURE_TYPE,
        MESSAGE,
        EXCEPTION,
        HTTP_STATUS_CODE
    }

    /* loaded from: classes6.dex */
    public static class AuthorizationFailure extends ApiCallFailure {
        private AuthorizationFailure(FailureType failureType, @Nullable String str, @Nullable Exception exc) {
            super(failureType, str, exc, null);
        }

        public static AuthorizationFailure create(@Nullable String str) {
            return new AuthorizationFailure(FailureType.AUTHORIZATION, str, null);
        }
    }

    /* loaded from: classes6.dex */
    public enum FailureType {
        UNKNOWN,
        TIMEOUT,
        MESSAGING,
        INTERNAL,
        NETWORK,
        SERVER_ERROR,
        AUTHORIZATION
    }

    /* loaded from: classes6.dex */
    public static class InternalFailure extends ApiCallFailure {
        private InternalFailure(FailureType failureType, @Nullable String str, @Nullable Exception exc) {
            super(failureType, str, exc, null);
        }

        public static InternalFailure create(@Nullable String str) {
            return create(str, null);
        }

        public static InternalFailure create(@Nullable String str, @Nullable Exception exc) {
            return new InternalFailure(FailureType.INTERNAL, str, exc);
        }
    }

    /* loaded from: classes6.dex */
    public static class MessagingFailure extends ApiCallFailure {
        private MessagingFailure(FailureType failureType, @Nullable String str, @Nullable Exception exc) {
            super(failureType, str, exc, null);
        }

        public static MessagingFailure create(@Nullable String str) {
            return create(str, null);
        }

        public static MessagingFailure create(@Nullable String str, @Nullable Exception exc) {
            return new MessagingFailure(FailureType.MESSAGING, str, exc);
        }
    }

    /* loaded from: classes6.dex */
    public static class NetworkFailure extends ApiCallFailure {
        private NetworkFailure(FailureType failureType, @Nullable String str, @Nullable Exception exc) {
            super(failureType, str, exc, null);
        }

        public static NetworkFailure create(@Nullable String str, @Nullable Exception exc) {
            return new NetworkFailure(FailureType.NETWORK, str, exc);
        }
    }

    /* loaded from: classes6.dex */
    public static class ServerErrorFailure extends ApiCallFailure {
        @Nullable
        private final Integer httpErrorCode;

        private ServerErrorFailure(FailureType failureType, @Nullable String str, @Nullable Exception exc, @Nullable Integer num) {
            super(failureType, str, exc, null);
            this.httpErrorCode = num;
        }

        public static ServerErrorFailure create(@Nullable String str) {
            return create(str, null, null);
        }

        public static ServerErrorFailure create(@Nullable String str, @Nullable Exception exc, @Nullable Integer num) {
            return new ServerErrorFailure(FailureType.SERVER_ERROR, str, exc, num);
        }

        public static ServerErrorFailure create(@Nullable String str, @Nullable Integer num) {
            return create(str, null, num);
        }

        void addUniqueFieldsToBundle(Bundle bundle) {
            if (this.httpErrorCode != null) {
                bundle.putInt(ApiCallFailureKeys.HTTP_STATUS_CODE.name(), this.httpErrorCode.intValue());
            }
        }

        @Nullable
        public Integer getHttpErrorCode() {
            return this.httpErrorCode;
        }
    }

    /* loaded from: classes6.dex */
    public static class TimeoutFailure extends ApiCallFailure {
        private TimeoutFailure(FailureType failureType, @Nullable String str, @Nullable Exception exc) {
            super(failureType, str, exc, null);
        }

        public static TimeoutFailure create(@Nullable String str) {
            return new TimeoutFailure(FailureType.TIMEOUT, str, null);
        }

        public static TimeoutFailure create(@Nullable String str, @Nullable Exception exc) {
            return new TimeoutFailure(FailureType.TIMEOUT, str, exc);
        }
    }

    /* loaded from: classes6.dex */
    public static class UnknownFailure extends ApiCallFailure {
        private UnknownFailure(FailureType failureType, @Nullable String str, @Nullable Exception exc) {
            super(failureType, str, exc, null);
        }

        public static UnknownFailure create(@Nullable String str) {
            return create(str, null);
        }

        public static UnknownFailure create(@Nullable String str, @Nullable Exception exc) {
            return new UnknownFailure(FailureType.UNKNOWN, str, exc);
        }
    }

    private ApiCallFailure(FailureType failureType, @Nullable String str, @Nullable Exception exc) {
        this.failureType = failureType;
        this.message = str;
        this.exception = exc;
    }

    /* synthetic */ ApiCallFailure(FailureType failureType, String str, Exception exc, AnonymousClass1 anonymousClass1) {
        this(failureType, str, exc);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ApiCallFailure createFromBundle(@Nullable Bundle bundle) {
        FailureType failureType;
        if (bundle == null) {
            return UnknownFailure.create(null);
        }
        try {
            failureType = FailureType.valueOf(Bundles.getOptionalString(bundle, ApiCallFailureKeys.FAILURE_TYPE));
        } catch (IllegalArgumentException unused) {
            Log.e(TAG, "Missing or unknown failure type in bundle");
            failureType = FailureType.UNKNOWN;
        }
        String optionalString = Bundles.getOptionalString(bundle, ApiCallFailureKeys.MESSAGE);
        Exception exc = (Exception) Bundles.getOptionalSerializable(bundle, ApiCallFailureKeys.EXCEPTION, Exception.class);
        switch (failureType.ordinal()) {
            case 1:
                return TimeoutFailure.create(optionalString, exc);
            case 2:
                return MessagingFailure.create(optionalString, exc);
            case 3:
                return InternalFailure.create(optionalString, exc);
            case 4:
                return NetworkFailure.create(optionalString, exc);
            case 5:
                return ServerErrorFailure.create(optionalString, exc, Integer.valueOf(Bundles.getInteger(bundle, ApiCallFailureKeys.HTTP_STATUS_CODE)));
            case 6:
                return AuthorizationFailure.create(optionalString);
            default:
                return UnknownFailure.create(optionalString, exc);
        }
    }

    public AuthorizationFailure asAuthorizationFailure() {
        return (AuthorizationFailure) this;
    }

    public InternalFailure asInternalFailure() {
        return (InternalFailure) this;
    }

    public MessagingFailure asMessagingFailure() {
        return (MessagingFailure) this;
    }

    public NetworkFailure asNetworkFailure() {
        return (NetworkFailure) this;
    }

    public ServerErrorFailure asServerErrorFailure() {
        return (ServerErrorFailure) this;
    }

    public TimeoutFailure asTimeoutFailure() {
        return (TimeoutFailure) this;
    }

    public UnknownFailure asUnknownFailure() {
        return (UnknownFailure) this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(ApiCallFailureKeys.FAILURE_TYPE.name(), getFailureType().name());
        if (this.message != null) {
            bundle.putString(ApiCallFailureKeys.MESSAGE.name(), this.message);
        }
        if (this.exception != null) {
            bundle.putSerializable(ApiCallFailureKeys.EXCEPTION.name(), this.exception);
        }
        if (FailureType.SERVER_ERROR.equals(this.failureType)) {
            asServerErrorFailure().addUniqueFieldsToBundle(bundle);
        }
        return bundle;
    }

    @Nullable
    public Exception getException() {
        return this.exception;
    }

    public FailureType getFailureType() {
        return this.failureType;
    }

    @Nullable
    public String getMessage() {
        return this.message;
    }
}
