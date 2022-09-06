package com.amazon.deecomms.calling.impl;

import android.os.NetworkOnMainThreadException;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.api.CallTarget;
import com.amazon.deecomms.calling.api.ContactCallTarget;
import com.amazon.deecomms.calling.api.DeviceCallTarget;
import com.amazon.deecomms.calling.api.HistoricalCall;
import com.amazon.deecomms.calling.api.RawAddressTarget;
import com.amazon.deecomms.calling.api.enums.CallDirection;
import com.amazon.deecomms.calling.api.enums.CallMediaStream;
import com.amazon.deecomms.calling.api.enums.ContactIdentifierType;
import com.amazon.deecomms.calling.api.enums.DeviceIdentifierType;
import com.amazon.deecomms.calling.impl.GetRecentCommunicationsResponse;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.network.acmsrecipes.GetDevices;
import com.amazon.deecomms.common.util.ContactUtils;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.model.FullContactName;
import com.amazon.deecomms.nativemodules.model.Person;
import com.amazon.deecomms.nativemodules.util.ContactsDataStoreUtil;
import com.amazon.deecomms.ndt.model.DeviceModel;
import com.amazon.deecomms.ndt.model.GetDevicesResponse;
import com.amazon.deecomms.util.ThreadUtils;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.common.base.Optional;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes12.dex */
public class CallHistoryResponseMapper {
    private static final String CONTACT = "CONTACT";
    private static final String DEVICE = "DEVICE";
    private static final String HG_PREFIX_STRING = "amzn1.comms.id.hg";
    private static final String INCOMING_CALL = "INCOMING_CALL";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CallHistoryResponseMapper.class);
    private static final String OUTGOING_DROPIN = "OUTGOING_DROPIN";
    private static final String PCC = "pcc";
    private final String commsID;
    private final ContactsDataStoreUtil contactsDataStoreUtil;
    private Optional<List<DeviceModel>> deviceModels;
    private final GetRecentCommunicationsResponse[] response;

    public CallHistoryResponseMapper(@NonNull GetRecentCommunicationsResponse[] getRecentCommunicationsResponseArr, @NonNull String str, @NonNull ContactsDataStoreUtil contactsDataStoreUtil, @NonNull Optional<List<DeviceModel>> optional) {
        this(getRecentCommunicationsResponseArr, str, contactsDataStoreUtil);
        this.deviceModels = optional;
    }

    @VisibleForTesting
    Optional<CallTarget> buildCallTarget(@NonNull GetRecentCommunicationsResponse getRecentCommunicationsResponse, boolean z) {
        CallTarget callTarget;
        CallTarget deviceCallTarget;
        if (getRecentCommunicationsResponse.getEntityType().equals(CONTACT)) {
            if (isPhoneNumber(getRecentCommunicationsResponse.getAddressValue())) {
                callTarget = new RawAddressTarget(getRecentCommunicationsResponse.getAddressValue(), PCC, getRecentCommunicationsResponse.getContactId());
            } else {
                deviceCallTarget = new ContactCallTarget(getContactIdentifierType(getRecentCommunicationsResponse), getRecentCommunicationsResponse.getAddressValue(), z);
                callTarget = deviceCallTarget;
            }
        } else {
            if (getRecentCommunicationsResponse.getEntityType().equals(DEVICE)) {
                Optional<String> deviceGruuForGivenDSN = getDeviceGruuForGivenDSN(getRecentCommunicationsResponse.getDsn());
                if (deviceGruuForGivenDSN.isPresent()) {
                    deviceCallTarget = new DeviceCallTarget(DeviceIdentifierType.DeviceGruu, deviceGruuForGivenDSN.get(), z);
                    callTarget = deviceCallTarget;
                }
            }
            callTarget = null;
        }
        return Optional.fromNullable(callTarget);
    }

    public List<HistoricalCall> getCallHistory() {
        GetRecentCommunicationsResponse[] getRecentCommunicationsResponseArr;
        if (!ThreadUtils.isRunningOnMainThread()) {
            this.deviceModels = getDevices(this.commsID);
            ArrayList arrayList = new ArrayList();
            for (GetRecentCommunicationsResponse getRecentCommunicationsResponse : this.response) {
                for (GetRecentCommunicationsResponse.ConnectedEntityEventData connectedEntityEventData : getRecentCommunicationsResponse.getConnectedEntityEventData()) {
                    if (getRecentCommunicationsResponse.getEntityType().equals(DEVICE) && !this.deviceModels.isPresent()) {
                        LOG.e("Cannot process device entries");
                    } else {
                        Optional<CallTarget> buildCallTarget = buildCallTarget(getRecentCommunicationsResponse, connectedEntityEventData.getEventType().equalsIgnoreCase(OUTGOING_DROPIN));
                        if (!buildCallTarget.isPresent()) {
                            LOG.e("Unable to build call target");
                        } else {
                            arrayList.add(new HistoricalCall(buildCallTarget.get(), new CallMediaStream[]{CallMediaStream.Audio}, connectedEntityEventData.getLastEventTimeStamp(), getDisplayName(getRecentCommunicationsResponse), connectedEntityEventData.getEventType().equalsIgnoreCase(INCOMING_CALL) ? CallDirection.Incoming : CallDirection.Outgoing));
                        }
                    }
                }
            }
            return arrayList;
        }
        throw new NetworkOnMainThreadException();
    }

    @VisibleForTesting
    ContactIdentifierType getContactIdentifierType(@NonNull GetRecentCommunicationsResponse getRecentCommunicationsResponse) {
        return getRecentCommunicationsResponse.getAddressValue().startsWith(HG_PREFIX_STRING) ? ContactIdentifierType.Homegroupid : ContactIdentifierType.Commsid;
    }

    @VisibleForTesting
    Optional<String> getDeviceGruuForGivenDSN(@NonNull String str) {
        if (this.deviceModels.isPresent()) {
            for (DeviceModel deviceModel : this.deviceModels.get()) {
                if (str.equalsIgnoreCase(deviceModel.getDeviceSerialNumber())) {
                    return Optional.of(deviceModel.getDeviceGruu());
                }
            }
        }
        return Optional.absent();
    }

    @VisibleForTesting
    Optional<List<DeviceModel>> getDevices(@NonNull String str) {
        GetDevicesResponse devices;
        try {
            devices = new GetDevices().getDevices(str);
        } catch (ServiceException | InterruptedException e) {
            LOG.w("Error from GetDevices", e);
        }
        if (devices != null) {
            return Optional.of(devices.getDeviceModels());
        }
        LOG.w("Invalid GetDevices response");
        return Optional.absent();
    }

    @VisibleForTesting
    String getDisplayName(@NonNull GetRecentCommunicationsResponse getRecentCommunicationsResponse) {
        if (getRecentCommunicationsResponse.getEntityType().equals(CONTACT)) {
            if (isPhoneNumber(getRecentCommunicationsResponse.getAddressValue())) {
                return getRecentCommunicationsResponse.getAddressValue();
            }
            return getNameForCommsID(getRecentCommunicationsResponse.getAddressValue());
        } else if (getRecentCommunicationsResponse.getEntityType().equals(DEVICE)) {
            return getNameForDevice(getRecentCommunicationsResponse.getDsn());
        } else {
            return Utils.getStringFromResource(R.string.unknown_contact);
        }
    }

    @VisibleForTesting
    String getNameForCommsID(@NonNull String str) {
        String serverContactIdFromCommsId = this.contactsDataStoreUtil.getServerContactIdFromCommsId(str);
        if (serverContactIdFromCommsId == null) {
            GeneratedOutlineSupport.outline3("Cannot find serverId from commsId ", str, LOG);
            return Utils.getStringFromResource(R.string.unknown_contact);
        }
        Person personByServerIdFromDatabase = this.contactsDataStoreUtil.getPersonByServerIdFromDatabase(serverContactIdFromCommsId);
        if (personByServerIdFromDatabase == null) {
            GeneratedOutlineSupport.outline3("Cannot find contact with serverId ", serverContactIdFromCommsId, LOG);
            return Utils.getStringFromResource(R.string.unknown_contact);
        }
        return ContactUtils.getFullName(new FullContactName(personByServerIdFromDatabase.getName(), personByServerIdFromDatabase.getCompanyName()));
    }

    @VisibleForTesting
    String getNameForDevice(@NonNull String str) {
        if (this.deviceModels.isPresent()) {
            for (DeviceModel deviceModel : this.deviceModels.get()) {
                if (str.equalsIgnoreCase(deviceModel.getDeviceSerialNumber())) {
                    return deviceModel.getDeviceName();
                }
            }
        }
        return Utils.getStringFromResource(R.string.unknown_contact);
    }

    @VisibleForTesting
    boolean isPhoneNumber(@NonNull String str) {
        return str.startsWith("+");
    }

    public CallHistoryResponseMapper(@NonNull GetRecentCommunicationsResponse[] getRecentCommunicationsResponseArr, @NonNull String str, @NonNull ContactsDataStoreUtil contactsDataStoreUtil) {
        this.response = getRecentCommunicationsResponseArr;
        this.commsID = str;
        this.contactsDataStoreUtil = contactsDataStoreUtil;
    }
}
