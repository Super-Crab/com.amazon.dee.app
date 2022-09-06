package com.amazon.alexa.devices.directive;

import android.os.ResultReceiver;
import com.amazon.alexa.devices.AlexaException;
/* loaded from: classes6.dex */
public interface IDirectiveComponent {
    void deregisterListener(int i) throws AlexaException;

    int registerListener(ResultReceiver resultReceiver) throws AlexaException;
}
