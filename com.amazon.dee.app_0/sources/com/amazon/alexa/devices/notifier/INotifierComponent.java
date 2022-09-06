package com.amazon.alexa.devices.notifier;

import com.amazon.alexa.devices.AlexaException;
/* loaded from: classes6.dex */
public interface INotifierComponent {
    <N> void publish(AlexaNotification<N> alexaNotification) throws AlexaException;
}
