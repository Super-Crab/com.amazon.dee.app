package com.amazon.alexa.location;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import rx.Observable;
import rx.subjects.PublishSubject;
/* loaded from: classes9.dex */
public class UserChangeSubjectFactory {
    private static final String IDENTITY_CHANGED = "identity:changed";
    private static final String TAG = "UserChangeSubjectFactory";
    private final EventBus eventBus;
    private final IdentityService identityService;
    private PublishSubject<UserIdentity> subject = PublishSubject.create();

    public UserChangeSubjectFactory(EventBus eventBus, IdentityService identityService) {
        this.eventBus = eventBus;
        this.identityService = identityService;
        init();
    }

    private void init() {
        this.eventBus.getSubscriber().subscribeFilter($$Lambda$UserChangeSubjectFactory$UCn33GuPsy9KXa98o6CcKvr2lKI.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.location.-$$Lambda$UserChangeSubjectFactory$uiHxCYO_u5PeSzouWw19luuvDOY
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                UserChangeSubjectFactory.this.lambda$init$1$UserChangeSubjectFactory(message);
            }
        });
    }

    public Observable<UserIdentity> getSubject() {
        return this.subject.distinctUntilChanged();
    }

    public /* synthetic */ void lambda$init$1$UserChangeSubjectFactory(Message message) {
        this.subject.onNext(this.identityService.getUser(TAG));
    }
}
