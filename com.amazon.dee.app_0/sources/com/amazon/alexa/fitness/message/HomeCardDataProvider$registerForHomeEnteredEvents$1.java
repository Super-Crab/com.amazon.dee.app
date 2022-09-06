package com.amazon.alexa.fitness.message;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.google.common.base.Optional;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: HomeCardDataProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a&\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u0012\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u00012\u000e\u0010\u0004\u001a\n \u0003*\u0004\u0018\u00010\u00050\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lcom/google/common/base/Optional;", "Lcom/amazon/alexa/eventbus/api/MultiFilterSubscriber;", "kotlin.jvm.PlatformType", "eventBus", "Lcom/amazon/alexa/eventbus/api/EventBus;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
final class HomeCardDataProvider$registerForHomeEnteredEvents$1 extends Lambda implements Function1<EventBus, Optional<MultiFilterSubscriber>> {
    public static final HomeCardDataProvider$registerForHomeEnteredEvents$1 INSTANCE = new HomeCardDataProvider$registerForHomeEnteredEvents$1();

    HomeCardDataProvider$registerForHomeEnteredEvents$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final Optional<MultiFilterSubscriber> mo12165invoke(EventBus eventBus) {
        return Optional.fromNullable(eventBus != null ? eventBus.getSubscriber() : null);
    }
}
