package com.amazon.comms.ringservice;

import com.amazon.comms.ringservice.util.Capability;
import java.util.List;
/* loaded from: classes12.dex */
public interface Signaling {

    /* loaded from: classes12.dex */
    public interface Channel {

        /* loaded from: classes12.dex */
        public enum State {
            Open,
            Active,
            Closed
        }

        ChannelInfo getInfo();

        State getState();

        void registerListener(SignalingChannelListener signalingChannelListener);

        boolean sendMessage(Message message);

        void unregisterListener();
    }

    /* loaded from: classes12.dex */
    public interface ChannelInfo {
    }

    /* loaded from: classes12.dex */
    public interface ChannelParams {
    }

    /* loaded from: classes12.dex */
    public interface ConfigurationParams {
    }

    /* loaded from: classes12.dex */
    public interface ConnectParams {
    }

    /* loaded from: classes12.dex */
    public interface InitParams {
    }

    /* loaded from: classes12.dex */
    public interface Message {
    }

    /* loaded from: classes12.dex */
    public enum MessageError {
        SEND_ERROR,
        RECV_ERROR
    }

    /* loaded from: classes12.dex */
    public interface MessageErrorInfo {
    }

    /* loaded from: classes12.dex */
    public interface ServerMessage {
    }

    void close();

    boolean connect(ConnectParams connectParams);

    Channel createChannel(ChannelParams channelParams);

    void disablePublish();

    void disconnect();

    void exit();

    boolean init(InitParams initParams);

    void open();

    void sendServerMessage(ServerMessage serverMessage);

    void updateCapabilities(List<Capability> list);

    boolean updateConfigurationParams(ConfigurationParams configurationParams, boolean z);
}
