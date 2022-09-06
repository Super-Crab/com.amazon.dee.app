package com.amazonaws.org.eclipse.paho.client.mqttv3.internal;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
/* loaded from: classes13.dex */
public class ResourceBundleCatalog extends MessageCatalog {
    private ResourceBundle bundle = ResourceBundle.getBundle("com.amazonaws.org.eclipse.paho.client.mqttv3.internal.nls.messages");

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.internal.MessageCatalog
    protected String getLocalizedMessage(int i) {
        try {
            return this.bundle.getString(Integer.toString(i));
        } catch (MissingResourceException unused) {
            return "MqttException";
        }
    }
}
