package com.amazon.whisperjoin.common.sharedtypes.protobuf;

import com.amazon.whispercloak.SecureMessage;
import com.amazon.whisperjoin.common.sharedtypes.ble.StartProvisioningRequest;
import com.amazon.whisperjoin.common.sharedtypes.ble.StartProvisioningResponse;
import com.amazon.whisperjoin.common.sharedtypes.ble.WhisperJoinBlePacket;
import com.amazon.whisperjoin.common.sharedtypes.ble.commands.ProvisioningCommand;
import com.amazon.whisperjoin.common.sharedtypes.ble.commands.ProvisioningCommandResponse;
import com.amazon.whisperjoin.common.sharedtypes.ble.events.CBLRegistrationUpdatedEvent;
import com.amazon.whisperjoin.common.sharedtypes.ble.events.ProvisionableCommandInterfaceUpdatedEvent;
import com.amazon.whisperjoin.common.sharedtypes.ble.events.ProvisionableEventInterfaceUpdatedEvent;
import com.amazon.whisperjoin.common.sharedtypes.ble.events.ProvisionableEventNotification;
import com.amazon.whisperjoin.common.sharedtypes.ble.events.ProvisioningDoneFailureEvent;
import com.amazon.whisperjoin.common.sharedtypes.ble.events.VisibleNetworksUpdatedEvent;
import com.amazon.whisperjoin.common.sharedtypes.ble.events.WifiConnectionUpdatedEvent;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.AuthenticatedEcdheKeyExchangeRequest;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.AuthenticatedEcdheKeyExchangeResponse;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.ProvisionableDeviceAuthenticationData;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.UnauthenticatedEcdheKeyExchangeRequest;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.UnauthenticatedEcdheKeyExchangeResponse;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.ble.ProtoStartProvisioningRequest;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.ble.ProtoStartProvisioningResponse;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.ble.ProtoWhisperJoinBlePacket;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.ble.events.ProtoCBLRegistrationUpdatedEvent;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.ble.events.ProtoProvisionableCommandInterfaceUpdatedEvent;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.ble.events.ProtoProvisionableEventInterfaceUpdatedEvent;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.ble.events.ProtoProvisionableEventNotification;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.ble.events.ProtoProvisioningDoneFailureEvent;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.ble.events.ProtoVisibleNetworksUpdatedEvent;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.ble.events.ProtoWifiConnectionUpdatedEvent;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.ble.events.commands.ProtoProvisioningCommand;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.ble.events.commands.ProtoProvisioningCommandResponse;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.ProtoProvisioningStatus;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.ProtoBoolean;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.ProtoInteger;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.ProtoString;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.configuration.ProtoConfigurationKeySet;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.configuration.ProtoDataMap;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.crypto.ProtoAuthenticatedEcdheKeyExchangeRequest;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.crypto.ProtoAuthenticatedEcdheKeyExchangeResponse;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.crypto.ProtoProvisionableDeviceAuthenticationData;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.crypto.ProtoSecureMessage;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.crypto.ProtoUnauthenticatedEcdheKeyExchangeRequest;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.crypto.ProtoUnauthenticatedEcdheKeyExchangeResponse;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.provisioning.ProtoDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.provisioning.ProtoProvisionableEventTypeCollection;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.provisioning.ProtoProvisioningFailure;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.provisioning.ProtoUuidCollection;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.registration.ProtoCBLRegistrationDetails;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.registration.ProtoCBLRegistrationRequest;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.wifi.ProtoWifiConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.wifi.ProtoWifiConnectionDetails;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.wifi.ProtoWifiNetwork;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.wifi.ProtoWifiScanResultCollection;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.ProvisioningStatus;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration.ConfigurationKeySet;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration.DataMap;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.DeviceDetailsProtoData;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.ProvisionableEventTypeCollection;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.ProvisioningFailure;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.UuidCollection;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.registration.CBLRegistrationDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.registration.CBLRegistrationRequest;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConnectionDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiScanResultCollection;
import com.amazon.whisperjoin.common.sharedtypes.utility.Serializer;
import com.google.common.reflect.TypeToken;
/* loaded from: classes13.dex */
public class ProtocolBuffersSerializer implements Serializer {
    private final TypeSerializer<WifiConfiguration> mWifiConfigurationSerializer = new ProtoWifiConfiguration();
    private final TypeSerializer<WifiNetwork> mWifiNetworkSerializer = new ProtoWifiNetwork();
    private final TypeSerializer<WifiScanResultCollection> mWifiScanResultCollectionSerializer = new ProtoWifiScanResultCollection();
    private final TypeSerializer<WifiConnectionDetails> mWifiConnectionDetailsSerializer = new ProtoWifiConnectionDetails();
    private final TypeSerializer<SecureMessage> mSecureMessageSerializer = new ProtoSecureMessage();
    private final TypeSerializer<UuidCollection> mUuidCollectionSerializer = new ProtoUuidCollection();
    private final TypeSerializer<CBLRegistrationDetails> mCBLRegistrationDetailsSerializer = new ProtoCBLRegistrationDetails();
    private final TypeSerializer<CBLRegistrationRequest> mCBLRegistrationRequestSerializer = new ProtoCBLRegistrationRequest();
    private final TypeSerializer<ProvisionableEventTypeCollection> mProvisionableEventTypeCollectionSerializer = new ProtoProvisionableEventTypeCollection();
    private final TypeSerializer<ProvisioningStatus> mProvisioningStatusSerializer = new ProtoProvisioningStatus();
    private final TypeSerializer<UnauthenticatedEcdheKeyExchangeRequest> mUnauthenticatedEcdheKeyExchangeRequestSerializer = new ProtoUnauthenticatedEcdheKeyExchangeRequest();
    private final TypeSerializer<UnauthenticatedEcdheKeyExchangeResponse> mUnauthenticatedEcdheKeyExchangeResponseSerializer = new ProtoUnauthenticatedEcdheKeyExchangeResponse();
    private final TypeSerializer<AuthenticatedEcdheKeyExchangeRequest> mAuthenticatedEcdheKeyExchangeRequestSerializer = new ProtoAuthenticatedEcdheKeyExchangeRequest();
    private final TypeSerializer<AuthenticatedEcdheKeyExchangeResponse> mAuthenticatedEcdheKeyExchangeResponseSerializer = new ProtoAuthenticatedEcdheKeyExchangeResponse();
    private final TypeSerializer<ConfigurationKeySet> mConfigurationKeySetSerializer = new ProtoConfigurationKeySet();
    private final TypeSerializer<DataMap> mDataMapTypeSerializer = new ProtoDataMap();
    private final TypeSerializer<DeviceDetailsProtoData> mDeviceDetailsSerializer = new ProtoDeviceDetails((ProtoDataMap) this.mDataMapTypeSerializer, (ProtoWifiConnectionDetails) this.mWifiConnectionDetailsSerializer);
    private final TypeSerializer<ProvisioningCommand> mProvisioningCommandSerializer = new ProtoProvisioningCommand();
    private final TypeSerializer<ProvisioningCommandResponse> mProvisioningCommandResponseSerializer = new ProtoProvisioningCommandResponse();
    private final TypeSerializer<StartProvisioningRequest> mStartProvisioningRequestSerializer = new ProtoStartProvisioningRequest();
    private final TypeSerializer<WhisperJoinBlePacket> mWhisperJoinBlePacketSerializer = new ProtoWhisperJoinBlePacket();
    private final TypeSerializer<StartProvisioningResponse> mStartProvisioningResponseSerializer = new ProtoStartProvisioningResponse();
    private final TypeSerializer<ProvisionableEventNotification> mProvisionableEventNotificationSerializer = new ProtoProvisionableEventNotification();
    private final TypeSerializer<ProvisionableDeviceAuthenticationData> mProvisionableDeviceAuthenticationDataTypeSerializer = new ProtoProvisionableDeviceAuthenticationData();
    private final TypeSerializer<CBLRegistrationUpdatedEvent> mCBLRegistrationUpdatedEventSerializer = new ProtoCBLRegistrationUpdatedEvent();
    private final TypeSerializer<ProvisionableCommandInterfaceUpdatedEvent> mProvisionableCommandInterfaceUpdatedEventSerializer = new ProtoProvisionableCommandInterfaceUpdatedEvent();
    private final TypeSerializer<ProvisionableEventInterfaceUpdatedEvent> mProvisionableEventInterfaceUpdatedEventSerializer = new ProtoProvisionableEventInterfaceUpdatedEvent();
    private final TypeSerializer<VisibleNetworksUpdatedEvent> mVisibleNetworksUpdatedEventSerializer = new ProtoVisibleNetworksUpdatedEvent();
    private final TypeSerializer<WifiConnectionUpdatedEvent> mWifiConnectionUpdatedEventSerializer = new ProtoWifiConnectionUpdatedEvent();
    private final TypeSerializer<ProvisioningFailure> mProvisioningFailureTypeSerializer = new ProtoProvisioningFailure();
    private final TypeSerializer<ProvisioningDoneFailureEvent> mProvisioningDoneFailureEventSerializer = new ProtoProvisioningDoneFailureEvent((ProtoProvisioningFailure) this.mProvisioningFailureTypeSerializer);
    private final TypeSerializer<Integer> mIntegerSerializer = new ProtoInteger();
    private final TypeSerializer<String> mStringSerializer = new ProtoString();
    private final TypeSerializer<Boolean> mBooleanSerializer = new ProtoBoolean();

    @Override // com.amazon.whisperjoin.common.sharedtypes.utility.Serializer
    public <T> T deserialize(byte[] bArr, TypeToken<T> typeToken) {
        if (typeToken.equals(TypeToken.of(WifiConfiguration.class))) {
            return (T) this.mWifiConfigurationSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.equals(TypeToken.of(WifiNetwork.class))) {
            return (T) this.mWifiNetworkSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.equals(TypeToken.of(WifiScanResultCollection.class))) {
            return (T) this.mWifiScanResultCollectionSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.equals(TypeToken.of(WifiConnectionDetails.class))) {
            return (T) this.mWifiConnectionDetailsSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.equals(TypeToken.of(SecureMessage.class))) {
            return (T) this.mSecureMessageSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.equals(TypeToken.of(Boolean.class))) {
            return (T) this.mBooleanSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.equals(TypeToken.of(Integer.class))) {
            return (T) this.mIntegerSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.equals(TypeToken.of(String.class))) {
            return (T) this.mStringSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.equals(TypeToken.of(UuidCollection.class))) {
            return (T) this.mUuidCollectionSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.equals(TypeToken.of(CBLRegistrationDetails.class))) {
            return (T) this.mCBLRegistrationDetailsSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.equals(TypeToken.of(CBLRegistrationRequest.class))) {
            return (T) this.mCBLRegistrationRequestSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.equals(TypeToken.of(ProvisionableEventTypeCollection.class))) {
            return (T) this.mProvisionableEventTypeCollectionSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.equals(TypeToken.of(CBLRegistrationUpdatedEvent.class))) {
            return (T) this.mCBLRegistrationUpdatedEventSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.equals(TypeToken.of(ProvisionableCommandInterfaceUpdatedEvent.class))) {
            return (T) this.mProvisionableCommandInterfaceUpdatedEventSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.equals(TypeToken.of(ProvisionableEventInterfaceUpdatedEvent.class))) {
            return (T) this.mProvisionableEventInterfaceUpdatedEventSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.equals(TypeToken.of(VisibleNetworksUpdatedEvent.class))) {
            return (T) this.mVisibleNetworksUpdatedEventSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.equals(TypeToken.of(WifiConnectionUpdatedEvent.class))) {
            return (T) this.mWifiConnectionUpdatedEventSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.equals(TypeToken.of(ProvisioningFailure.class))) {
            return (T) this.mProvisioningFailureTypeSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.equals(TypeToken.of(ProvisioningDoneFailureEvent.class))) {
            return (T) this.mProvisioningDoneFailureEventSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.equals(TypeToken.of(ProvisioningStatus.class))) {
            return (T) this.mProvisioningStatusSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.getType().equals(UnauthenticatedEcdheKeyExchangeRequest.class)) {
            return (T) this.mUnauthenticatedEcdheKeyExchangeRequestSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.getType().equals(UnauthenticatedEcdheKeyExchangeResponse.class)) {
            return (T) this.mUnauthenticatedEcdheKeyExchangeResponseSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.getType().equals(AuthenticatedEcdheKeyExchangeRequest.class)) {
            return (T) this.mAuthenticatedEcdheKeyExchangeRequestSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.getType().equals(AuthenticatedEcdheKeyExchangeResponse.class)) {
            return (T) this.mAuthenticatedEcdheKeyExchangeResponseSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.equals(TypeToken.of(ConfigurationKeySet.class))) {
            return (T) this.mConfigurationKeySetSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.equals(TypeToken.of(DataMap.class))) {
            return (T) this.mDataMapTypeSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.equals(TypeToken.of(DeviceDetailsProtoData.class))) {
            return (T) this.mDeviceDetailsSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.equals(TypeToken.of(ProvisioningCommand.class))) {
            return (T) this.mProvisioningCommandSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.equals(TypeToken.of(ProvisioningCommandResponse.class))) {
            return (T) this.mProvisioningCommandResponseSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.equals(TypeToken.of(StartProvisioningRequest.class))) {
            return (T) this.mStartProvisioningRequestSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.equals(TypeToken.of(WhisperJoinBlePacket.class))) {
            return (T) this.mWhisperJoinBlePacketSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.equals(TypeToken.of(StartProvisioningResponse.class))) {
            return (T) this.mStartProvisioningResponseSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.equals(TypeToken.of(ProvisionableEventNotification.class))) {
            return (T) this.mProvisionableEventNotificationSerializer.mo5427deserialize(bArr);
        }
        if (typeToken.equals(TypeToken.of(ProvisionableDeviceAuthenticationData.class))) {
            return (T) this.mProvisionableDeviceAuthenticationDataTypeSerializer.mo5427deserialize(bArr);
        }
        throw new RuntimeException("Could not find a serializer to deserialize object.");
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.utility.Serializer
    public <T> byte[] serialize(T t) {
        if (t == null) {
            return null;
        }
        if (t instanceof WifiConfiguration) {
            return this.mWifiConfigurationSerializer.serialize((WifiConfiguration) t);
        }
        if (t instanceof WifiNetwork) {
            return this.mWifiNetworkSerializer.serialize((WifiNetwork) t);
        }
        if (t instanceof WifiScanResultCollection) {
            return this.mWifiScanResultCollectionSerializer.serialize((WifiScanResultCollection) t);
        }
        if (t instanceof WifiConnectionDetails) {
            return this.mWifiConnectionDetailsSerializer.serialize((WifiConnectionDetails) t);
        }
        if (t instanceof SecureMessage) {
            return this.mSecureMessageSerializer.serialize((SecureMessage) t);
        }
        if (t instanceof Boolean) {
            return this.mBooleanSerializer.serialize((Boolean) t);
        }
        if (t instanceof Integer) {
            return this.mIntegerSerializer.serialize((Integer) t);
        }
        if (t instanceof String) {
            return this.mStringSerializer.serialize((String) t);
        }
        if (t instanceof UuidCollection) {
            return this.mUuidCollectionSerializer.serialize((UuidCollection) t);
        }
        if (t instanceof CBLRegistrationDetails) {
            return this.mCBLRegistrationDetailsSerializer.serialize((CBLRegistrationDetails) t);
        }
        if (t instanceof CBLRegistrationRequest) {
            return this.mCBLRegistrationRequestSerializer.serialize((CBLRegistrationRequest) t);
        }
        if (t instanceof ProvisionableEventTypeCollection) {
            return this.mProvisionableEventTypeCollectionSerializer.serialize((ProvisionableEventTypeCollection) t);
        }
        if (t instanceof CBLRegistrationUpdatedEvent) {
            return this.mCBLRegistrationUpdatedEventSerializer.serialize((CBLRegistrationUpdatedEvent) t);
        }
        if (t instanceof ProvisionableCommandInterfaceUpdatedEvent) {
            return this.mProvisionableCommandInterfaceUpdatedEventSerializer.serialize((ProvisionableCommandInterfaceUpdatedEvent) t);
        }
        if (t instanceof ProvisionableEventInterfaceUpdatedEvent) {
            return this.mProvisionableEventInterfaceUpdatedEventSerializer.serialize((ProvisionableEventInterfaceUpdatedEvent) t);
        }
        if (t instanceof VisibleNetworksUpdatedEvent) {
            return this.mVisibleNetworksUpdatedEventSerializer.serialize((VisibleNetworksUpdatedEvent) t);
        }
        if (t instanceof WifiConnectionUpdatedEvent) {
            return this.mWifiConnectionUpdatedEventSerializer.serialize((WifiConnectionUpdatedEvent) t);
        }
        if (t instanceof ProvisioningFailure) {
            return this.mProvisioningFailureTypeSerializer.serialize((ProvisioningFailure) t);
        }
        if (t instanceof ProvisioningDoneFailureEvent) {
            return this.mProvisioningDoneFailureEventSerializer.serialize((ProvisioningDoneFailureEvent) t);
        }
        if (t instanceof ProvisioningStatus) {
            return this.mProvisioningStatusSerializer.serialize((ProvisioningStatus) t);
        }
        if (t instanceof UnauthenticatedEcdheKeyExchangeRequest) {
            return this.mUnauthenticatedEcdheKeyExchangeRequestSerializer.serialize((UnauthenticatedEcdheKeyExchangeRequest) t);
        }
        if (t instanceof UnauthenticatedEcdheKeyExchangeResponse) {
            return this.mUnauthenticatedEcdheKeyExchangeResponseSerializer.serialize((UnauthenticatedEcdheKeyExchangeResponse) t);
        }
        if (t instanceof AuthenticatedEcdheKeyExchangeRequest) {
            return this.mAuthenticatedEcdheKeyExchangeRequestSerializer.serialize((AuthenticatedEcdheKeyExchangeRequest) t);
        }
        if (t instanceof AuthenticatedEcdheKeyExchangeResponse) {
            return this.mAuthenticatedEcdheKeyExchangeResponseSerializer.serialize((AuthenticatedEcdheKeyExchangeResponse) t);
        }
        if (t instanceof ConfigurationKeySet) {
            return this.mConfigurationKeySetSerializer.serialize((ConfigurationKeySet) t);
        }
        if (t instanceof DataMap) {
            return this.mDataMapTypeSerializer.serialize((DataMap) t);
        }
        if (t instanceof DeviceDetailsProtoData) {
            return this.mDeviceDetailsSerializer.serialize((DeviceDetailsProtoData) t);
        }
        if (t instanceof ProvisioningCommand) {
            return this.mProvisioningCommandSerializer.serialize((ProvisioningCommand) t);
        }
        if (t instanceof ProvisioningCommandResponse) {
            return this.mProvisioningCommandResponseSerializer.serialize((ProvisioningCommandResponse) t);
        }
        if (t instanceof StartProvisioningRequest) {
            return this.mStartProvisioningRequestSerializer.serialize((StartProvisioningRequest) t);
        }
        if (t instanceof WhisperJoinBlePacket) {
            return this.mWhisperJoinBlePacketSerializer.serialize((WhisperJoinBlePacket) t);
        }
        if (t instanceof StartProvisioningResponse) {
            return this.mStartProvisioningResponseSerializer.serialize((StartProvisioningResponse) t);
        }
        if (t instanceof ProvisionableEventNotification) {
            return this.mProvisionableEventNotificationSerializer.serialize((ProvisionableEventNotification) t);
        }
        if (t instanceof ProvisionableDeviceAuthenticationData) {
            return this.mProvisionableDeviceAuthenticationDataTypeSerializer.serialize((ProvisionableDeviceAuthenticationData) t);
        }
        throw new RuntimeException("Could not find a serializer to serialize object.");
    }
}
