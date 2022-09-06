package com.amazon.alexa.accessory.repositories.media;

import com.amazon.alexa.accessory.internal.repositories.CompletableResult;
import com.amazon.alexa.accessory.protocol.Media;
import com.amazon.alexa.accessory.repositories.Producer;
/* loaded from: classes6.dex */
public interface MediaProducer extends Producer<ActionHandler> {

    /* loaded from: classes6.dex */
    public interface ActionHandler {
        void handleIssueMediaControl(Media.MediaControl mediaControl, Producer.Result<CompletableResult.Value> result);

        void handlePlaybackStatus(Media.PlaybackStatus playbackStatus, Producer.Result<CompletableResult.Value> result);
    }
}
