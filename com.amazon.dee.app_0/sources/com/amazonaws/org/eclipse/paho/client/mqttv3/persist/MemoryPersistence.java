package com.amazonaws.org.eclipse.paho.client.mqttv3.persist;

import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttPersistable;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import java.util.Enumeration;
import java.util.Hashtable;
/* loaded from: classes13.dex */
public class MemoryPersistence implements MqttClientPersistence {
    private Hashtable data;

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void clear() throws MqttPersistenceException {
        this.data.clear();
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void close() throws MqttPersistenceException {
        this.data.clear();
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public boolean containsKey(String str) throws MqttPersistenceException {
        return this.data.containsKey(str);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public MqttPersistable get(String str) throws MqttPersistenceException {
        return (MqttPersistable) this.data.get(str);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public Enumeration keys() throws MqttPersistenceException {
        return this.data.keys();
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void open(String str, String str2) throws MqttPersistenceException {
        this.data = new Hashtable();
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void put(String str, MqttPersistable mqttPersistable) throws MqttPersistenceException {
        this.data.put(str, mqttPersistable);
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void remove(String str) throws MqttPersistenceException {
        this.data.remove(str);
    }
}
