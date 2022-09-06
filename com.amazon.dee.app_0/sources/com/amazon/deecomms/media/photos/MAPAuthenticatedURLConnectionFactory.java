package com.amazon.deecomms.media.photos;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.clouddrive.auth.AuthenticatedURLConnectionFactory;
import com.amazon.identity.auth.device.api.AuthenticatedURLConnection;
import com.amazon.identity.auth.device.api.AuthenticationMethodFactory;
import com.amazon.identity.auth.device.api.AuthenticationType;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import rx.Subscription;
/* loaded from: classes12.dex */
public class MAPAuthenticatedURLConnectionFactory implements AuthenticatedURLConnectionFactory {
    private static final String className = "com.amazon.deecomms.media.photos.MAPAuthenticatedURLConnectionFactory";
    private final Context mContext;
    private String mDirectedId;
    private EventBus mEventBus;
    private Subscription mUserChangedSubscription;

    public MAPAuthenticatedURLConnectionFactory(@NonNull Context context, @NonNull final IdentityService identityService, @NonNull EventBus eventBus) {
        this.mContext = context;
        this.mEventBus = eventBus;
        UserIdentity user = identityService.getUser(className);
        this.mEventBus.getSubscriber().subscribeFilter($$Lambda$MAPAuthenticatedURLConnectionFactory$Eu76iJD3qkVNf_OM1hMISKZekRU.INSTANCE, new MessageHandler() { // from class: com.amazon.deecomms.media.photos.-$$Lambda$MAPAuthenticatedURLConnectionFactory$hDPw9r-CpoGtLsiYbKO5784Su9k
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                MAPAuthenticatedURLConnectionFactory.this.lambda$new$1$MAPAuthenticatedURLConnectionFactory(identityService, message);
            }
        });
        setCurrentUser(user);
    }

    @Override // com.amazon.clouddrive.auth.AuthenticatedURLConnectionFactory
    public HttpURLConnection createHttpURLConnection(URL url) throws IOException {
        if (!TextUtils.isEmpty(this.mDirectedId)) {
            return AuthenticatedURLConnection.openConnection(url, new AuthenticationMethodFactory(this.mContext, this.mDirectedId).newAuthenticationMethod(AuthenticationType.OAuth));
        }
        throw new IllegalStateException("There is no active account.");
    }

    String getDirectedId() {
        return this.mDirectedId;
    }

    public /* synthetic */ void lambda$new$1$MAPAuthenticatedURLConnectionFactory(IdentityService identityService, Message message) {
        setCurrentUser(identityService.getUser(className));
    }

    @VisibleForTesting
    void setCurrentUser(@Nullable UserIdentity userIdentity) {
        if (userIdentity != null) {
            this.mDirectedId = userIdentity.getDirectedId();
        } else {
            this.mDirectedId = null;
        }
    }
}
