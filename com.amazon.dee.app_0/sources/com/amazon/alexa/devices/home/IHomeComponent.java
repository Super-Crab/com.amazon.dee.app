package com.amazon.alexa.devices.home;

import android.content.Intent;
import com.amazon.alexa.devices.AlexaException;
/* loaded from: classes6.dex */
public interface IHomeComponent {
    Intent getHomeActivityIntent() throws AlexaException;

    Intent getSettingsActivityIntent() throws AlexaException;
}
