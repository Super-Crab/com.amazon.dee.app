package com.amazon.alexa.photos;

import io.reactivex.rxjava3.functions.Predicate;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$MessagePublisher$7-VhEOvUKDJyjq2gVJwWJSCnoL8  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$MessagePublisher$7VhEOvUKDJyjq2gVJwWJSCnoL8 implements Predicate {
    public static final /* synthetic */ $$Lambda$MessagePublisher$7VhEOvUKDJyjq2gVJwWJSCnoL8 INSTANCE = new $$Lambda$MessagePublisher$7VhEOvUKDJyjq2gVJwWJSCnoL8();

    private /* synthetic */ $$Lambda$MessagePublisher$7VhEOvUKDJyjq2gVJwWJSCnoL8() {
    }

    @Override // io.reactivex.rxjava3.functions.Predicate
    public final boolean test(Object obj) {
        return MessagePublisher.lambda$initializeMessageFilter$0((PhotosUploaderEventMessage) obj);
    }
}
