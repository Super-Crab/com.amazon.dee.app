package com.amazon.alexa.photos;

import io.reactivex.rxjava3.functions.Predicate;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.photos.-$$Lambda$MessagePublisher$4GWTF7-h3Znw2jSrM60v31sW8Js  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$MessagePublisher$4GWTF7h3Znw2jSrM60v31sW8Js implements Predicate {
    public static final /* synthetic */ $$Lambda$MessagePublisher$4GWTF7h3Znw2jSrM60v31sW8Js INSTANCE = new $$Lambda$MessagePublisher$4GWTF7h3Znw2jSrM60v31sW8Js();

    private /* synthetic */ $$Lambda$MessagePublisher$4GWTF7h3Znw2jSrM60v31sW8Js() {
    }

    @Override // io.reactivex.rxjava3.functions.Predicate
    public final boolean test(Object obj) {
        return ((PhotosUploaderEventMessage) obj).eventType;
    }
}
