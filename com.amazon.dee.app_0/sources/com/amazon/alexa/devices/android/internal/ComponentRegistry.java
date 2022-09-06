package com.amazon.alexa.devices.android.internal;

import android.util.Log;
import com.amazon.alexa.devices.AlexaException;
import com.amazon.alexa.devices.Version;
import com.amazon.alexa.devices.android.AndroidAlexa;
import com.amazon.alexa.devices.attentionstate.AttentionStateComponent;
import com.amazon.alexa.devices.attentionstate.internal.AttentionStateComponentImpl;
import com.amazon.alexa.devices.devicecontrol.PowerControlComponent;
import com.amazon.alexa.devices.devicecontrol.internal.PowerControlComponentImpl;
import com.amazon.alexa.devices.directive.IDirectiveComponent;
import com.amazon.alexa.devices.directive.internal.DirectiveComponent;
import com.amazon.alexa.devices.donotdisturb.DoNotDisturbSettingsComponent;
import com.amazon.alexa.devices.environment.IAlexaEnvironmentComponent;
import com.amazon.alexa.devices.environment.internal.AlexaEnvironmentComponent;
import com.amazon.alexa.devices.home.IHomeComponent;
import com.amazon.alexa.devices.home.internal.HomeComponent;
import com.amazon.alexa.devices.notifier.INotifierComponent;
import com.amazon.alexa.devices.notifier.internal.NotifierComponent;
import com.amazon.alexa.devices.oobe.OOBEComponent;
import com.amazon.alexa.devices.oobe.internal.AlexaOOBEComponent;
import com.amazon.alexa.devices.presentation.ICustomApplication;
import com.amazon.alexa.devices.presentation.ILocalSkillEndpoint;
import com.amazon.alexa.devices.presentation.internal.CustomApplicationComponent;
import com.amazon.alexa.devices.presentation.internal.LocalSkillEndpointComponent;
import com.amazon.alexa.devices.settings.distanceUnits.DistanceUnitComponent;
import com.amazon.alexa.devices.settings.distanceUnits.internal.DistanceUnitComponentImpl;
import com.amazon.alexa.devices.settings.location.DeviceLocationComponent;
import com.amazon.alexa.devices.settings.location.internal.DeviceLocationComponentImpl;
import com.amazon.alexa.devices.settings.temperature.TemperatureUnitComponent;
import com.amazon.alexa.devices.settings.temperature.internal.TemperatureUnitComponentImpl;
import com.amazon.alexa.devices.speechrecognizer.SpeechRecognizerComponent;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
public class ComponentRegistry {
    private static final String TAG = "ComponentRegistry";
    private static final Map<Class<?>, Class<? extends AndroidAlexa.BaseProxyClient>> mApiComponentClasses = new HashMap<Class<?>, Class<? extends AndroidAlexa.BaseProxyClient>>() { // from class: com.amazon.alexa.devices.android.internal.ComponentRegistry.1
        {
            put(IAlexaEnvironmentComponent.class, AlexaEnvironmentComponent.class);
            put(ICustomApplication.class, CustomApplicationComponent.class);
            put(IDirectiveComponent.class, DirectiveComponent.class);
            put(IHomeComponent.class, HomeComponent.class);
            put(ILocalSkillEndpoint.class, LocalSkillEndpointComponent.class);
            put(SpeechRecognizerComponent.class, com.amazon.alexa.devices.speechrecognizer.internal.SpeechRecognizerComponent.class);
            put(INotifierComponent.class, NotifierComponent.class);
            put(DoNotDisturbSettingsComponent.class, com.amazon.alexa.devices.donotdisturb.internal.DoNotDisturbSettingsComponent.class);
            put(OOBEComponent.class, AlexaOOBEComponent.class);
            put(TemperatureUnitComponent.class, TemperatureUnitComponentImpl.class);
            put(DistanceUnitComponent.class, DistanceUnitComponentImpl.class);
            put(DeviceLocationComponent.class, DeviceLocationComponentImpl.class);
            put(AttentionStateComponent.class, AttentionStateComponentImpl.class);
            put(PowerControlComponent.class, PowerControlComponentImpl.class);
        }
    };
    private Version mVersion;

    public ComponentRegistry(Version version) {
        this.mVersion = version;
    }

    public <T> T createApiComponent(Class<T> cls, Object... objArr) throws AlexaException {
        Class<? extends AndroidAlexa.BaseProxyClient> cls2 = mApiComponentClasses.get(cls);
        if (cls2 != null) {
            try {
                Class<?>[] clsArr = new Class[objArr.length];
                for (int i = 0; i < objArr.length; i++) {
                    clsArr[i] = objArr[i].getClass();
                }
                return (T) cls2.getDeclaredConstructor(clsArr).newInstance(objArr);
            } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                String str = TAG;
                Log.e(str, "Error creating API Component for class: " + cls, e);
            }
        }
        return null;
    }
}
