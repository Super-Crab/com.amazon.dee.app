package com.amazon.alexa.presence.bleconn.service;

import android.content.Context;
import com.amazon.alexa.presence.bleconn.service.handlers.IdentityRequestedSubscriber;
import com.amazon.alexa.presence.service.PresenceJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes9.dex */
public class PresenceFeatureSuggestionSubscriber implements IdentityRequestedSubscriber {
    private static final Logger LOG = LoggerFactory.getLogger(PresenceFeatureSuggestionSubscriber.class);
    private final Context ctx;

    public PresenceFeatureSuggestionSubscriber(Context context) {
        this.ctx = context;
    }

    @Override // com.amazon.alexa.presence.bleconn.service.handlers.IdentityRequestedSubscriber
    public void notifyIdentityReadRequested(boolean z, boolean z2, Integer num, boolean z3) {
        if (z) {
            LOG.info("Matching relationship found.  Provided? {}.  Matching? {}.  Matching Type? {}.  Response provided? {} ", Boolean.valueOf(z), Boolean.valueOf(z2), num, Boolean.valueOf(z3));
            new PresenceJobService.Helper(this.ctx).schedulePresenceFeatureSuggestNotification();
            return;
        }
        LOG.info("Matching relationship not found.  Provided? {}.  Matching? {}.  Matching Type? {}.  Response provided? {} ", Boolean.valueOf(z), Boolean.valueOf(z2), num, Boolean.valueOf(z3));
    }
}
