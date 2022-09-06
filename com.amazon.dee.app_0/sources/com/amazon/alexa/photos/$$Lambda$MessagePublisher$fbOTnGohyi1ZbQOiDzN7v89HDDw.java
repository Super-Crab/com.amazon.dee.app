package com.amazon.alexa.photos;

import io.reactivex.rxjava3.functions.Predicate;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$MessagePublisher$fbOTnGohyi1ZbQOiDzN7v89HDDw  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$MessagePublisher$fbOTnGohyi1ZbQOiDzN7v89HDDw implements Predicate {
    public static final /* synthetic */ $$Lambda$MessagePublisher$fbOTnGohyi1ZbQOiDzN7v89HDDw INSTANCE = new $$Lambda$MessagePublisher$fbOTnGohyi1ZbQOiDzN7v89HDDw();

    private /* synthetic */ $$Lambda$MessagePublisher$fbOTnGohyi1ZbQOiDzN7v89HDDw() {
    }

    @Override // io.reactivex.rxjava3.functions.Predicate
    public final boolean test(Object obj) {
        return MessagePublisher.lambda$initializeMessageFilter$1((PhotosUploaderEventMessage) obj);
    }
}
