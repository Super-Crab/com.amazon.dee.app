package com.amazon.alexa.drive.smart.device.guard;

import android.content.Context;
import com.amazon.alexa.drive.smart.device.SmartDeviceInteractor;
import com.amazon.alexa.drive.smart.device.contract.SmartDeviceContract;
import com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider;
import com.amazon.alexa.drive.smart.device.data.SmartDeviceEndpoint;
import java.net.MalformedURLException;
/* loaded from: classes7.dex */
public class GuardDataProvider extends SmartDeviceDataProvider {
    private static final String SET_GUARD_DEVICE_MODE = "{\"targetMode\": \"%s\"}";

    public GuardDataProvider(Context context, SmartDeviceContract.ServerResponseListener serverResponseListener) {
        super(context, serverResponseListener);
    }

    public void getGuardStatus() throws MalformedURLException {
        startTask(SmartDeviceDataProvider.Task.Builder.newInstance().url(SmartDeviceEndpoint.getGuardModeEndpoint()).method("POST").build());
    }

    public void hasGuard() throws MalformedURLException {
        startTask(SmartDeviceDataProvider.Task.Builder.newInstance().url(SmartDeviceEndpoint.getHasGuardEndpoint()).taskName(SmartDeviceInteractor.HAS_GUARD).method("GET").build());
    }

    public void setGuardStatus(String str) throws MalformedURLException {
        startTask(SmartDeviceDataProvider.Task.Builder.newInstance().url(SmartDeviceEndpoint.getGuardModeEndpoint()).method(SmartDeviceDataProvider.METHOD_HTTP_PUT).message(String.format(SET_GUARD_DEVICE_MODE, str)).build());
    }
}
