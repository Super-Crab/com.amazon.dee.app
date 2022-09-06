package com.amazon.deecomms.services.conversation;

import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.deecomms.util.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.services.conversation.-$$Lambda$CommsConversationUIDelegate$s3kFGZ94ZxMjQSKO9jHIwt3MB-I  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$CommsConversationUIDelegate$s3kFGZ94ZxMjQSKO9jHIwt3MBI implements Consumer {
    public static final /* synthetic */ $$Lambda$CommsConversationUIDelegate$s3kFGZ94ZxMjQSKO9jHIwt3MBI INSTANCE = new $$Lambda$CommsConversationUIDelegate$s3kFGZ94ZxMjQSKO9jHIwt3MBI();

    private /* synthetic */ $$Lambda$CommsConversationUIDelegate$s3kFGZ94ZxMjQSKO9jHIwt3MBI() {
    }

    @Override // com.amazon.deecomms.util.Consumer
    public final void accept(Object obj) {
        ((RoutingService.RoutingBuilder) obj).navigate();
    }
}
