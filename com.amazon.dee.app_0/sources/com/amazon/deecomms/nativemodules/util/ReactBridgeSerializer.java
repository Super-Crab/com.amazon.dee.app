package com.amazon.deecomms.nativemodules.util;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.frames.contacts.ContactsModuleConstants;
import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import com.amazon.alexa.drive.metrics.NavigationMetrics;
import com.amazon.alexa.smarthomecameras.routing.CamerasRouteParameter;
import com.amazon.comms.log.CommsLogger;
import com.amazon.dee.app.services.bluetooth.DefaultBluetoothService;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.amazon.dee.app.ui.web.JavaScriptResponse;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.enums.DeviceCommsAvailability;
import com.amazon.deecomms.calling.enums.DropInAvailability;
import com.amazon.deecomms.calling.ui.CallViewUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.ContactUtils;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.amazon.deecomms.contacts.model.Contact;
import com.amazon.deecomms.contacts.model.ContactAddress;
import com.amazon.deecomms.contacts.model.ContactEmailAddress;
import com.amazon.deecomms.contacts.model.ContactName;
import com.amazon.deecomms.contacts.model.ContactPhoneNumber;
import com.amazon.deecomms.contacts.model.ContactUploadInfo;
import com.amazon.deecomms.contacts.model.EmailAddressType;
import com.amazon.deecomms.contacts.model.IdentityRawData;
import com.amazon.deecomms.contacts.model.PhoneNumberType;
import com.amazon.deecomms.contacts.presence.model.PresenceDocument;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.nativemodules.AmazonPhotosDownloadRequest;
import com.amazon.deecomms.nativemodules.AmazonPhotosUploadRequest;
import com.amazon.deecomms.nativemodules.model.BridgeContact;
import com.amazon.deecomms.nativemodules.model.MediaMetadata;
import com.amazon.deecomms.nativemodules.model.Person;
import com.amazon.deecomms.ndt.enums.ActiveState;
import com.amazon.deecomms.ndt.model.DeviceModel;
import com.amazon.deecomms.ndt.model.DeviceStatusModel;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.google.common.base.Strings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
/* loaded from: classes12.dex */
public class ReactBridgeSerializer {
    public static final String CONTACT_DISCRIMINATOR_PROPERTY_NAME = "kind";
    public static final String HOMEGROUP_TYPE_DISCRIMINATOR = "home-group";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ContactsDataStoreUtil.class);
    public static final String PERSON_TYPE_DISCRIMINATOR = "person";
    private final CommsIdentityManager mCommsIdentityManager = CommsDaggerWrapper.getComponent().getCommsIdentityManager();
    private Context mContext;

    public ReactBridgeSerializer(@NonNull Context context) {
        this.mContext = context;
    }

    @NonNull
    private List<ContactEmailAddress> convertContactEmails(@NonNull ContactUploadInfo contactUploadInfo) {
        ArrayList arrayList = new ArrayList();
        List<ContactUploadInfo.EmailAddress> emails = contactUploadInfo.getEmails();
        if (emails == null) {
            return arrayList;
        }
        for (ContactUploadInfo.EmailAddress emailAddress : emails) {
            ContactEmailAddress contactEmailAddress = new ContactEmailAddress();
            contactEmailAddress.setEmailAddress(emailAddress.getEmailAddress());
            contactEmailAddress.setRawType(emailAddress.getRawType());
            contactEmailAddress.setType(EmailAddressType.fromAddressBookType(emailAddress.getRawType()));
            arrayList.add(contactEmailAddress);
        }
        return arrayList;
    }

    @NonNull
    private List<ContactPhoneNumber> convertContactPhoneNumbers(@NonNull ContactUploadInfo contactUploadInfo) {
        ArrayList arrayList = new ArrayList();
        if (contactUploadInfo.getNumbers() == null) {
            return arrayList;
        }
        for (ContactUploadInfo.PhoneNumber phoneNumber : contactUploadInfo.getNumbers()) {
            ContactPhoneNumber contactPhoneNumber = new ContactPhoneNumber();
            contactPhoneNumber.setPhoneNumber(phoneNumber.getNumber());
            contactPhoneNumber.setRawType(phoneNumber.getRawType());
            contactPhoneNumber.setType(PhoneNumberType.fromAddressBookType(phoneNumber.getRawType()));
            arrayList.add(contactPhoneNumber);
        }
        return arrayList;
    }

    @NonNull
    private List<ContactAddress> convertReadableArrayToAddressArray(ReadableArray readableArray) {
        ArrayList arrayList = new ArrayList();
        if (readableArray != null) {
            for (int i = 0; i < readableArray.size(); i++) {
                arrayList.add(getAddressObject(readableArray.mo6944getMap(i)));
            }
        }
        return arrayList;
    }

    @NonNull
    private List<ContactEmailAddress> convertReadableArrayToEmailAddressArray(ReadableArray readableArray) {
        ArrayList arrayList = new ArrayList();
        if (readableArray != null) {
            for (int i = 0; i < readableArray.size(); i++) {
                arrayList.add(getEmailAddressObject(readableArray.mo6944getMap(i)));
            }
        }
        return arrayList;
    }

    @NonNull
    public static List<String> convertReadableArrayToStringArray(ReadableArray readableArray) {
        ArrayList arrayList = new ArrayList();
        if (readableArray != null) {
            for (int i = 0; i < readableArray.size(); i++) {
                arrayList.add(readableArray.getString(i));
            }
        }
        return arrayList;
    }

    @NonNull
    private WritableArray convertStringArrayToWritableArray(@NonNull List<String> list) {
        WritableArray createArray = Arguments.createArray();
        for (String str : list) {
            createArray.pushString(str);
        }
        return createArray;
    }

    @NonNull
    private WritableArray createAddressArray(@NonNull List<ContactAddress> list) {
        WritableArray createArray = Arguments.createArray();
        for (ContactAddress contactAddress : list) {
            createArray.pushMap(createAddressMap(contactAddress));
        }
        return createArray;
    }

    public static WritableMap createMediaDownloadFailureMap(@NonNull AmazonPhotosDownloadRequest amazonPhotosDownloadRequest, @NonNull Throwable th) {
        return createMediaDownloadFailureMap(amazonPhotosDownloadRequest, th.toString());
    }

    public static WritableMap createMediaDownloadSuccessMap(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5, @NonNull String str6, @NonNull String str7, @NonNull Integer num, @NonNull Integer num2) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("conversationId", str);
        createMap.putString(AuthorizationResponseParser.CLIENT_ID_STATE, str2);
        createMap.putString("dimension", str3);
        createMap.putString(AlexaDeviceBackgroundImageBridge.KEY_NODE_ID, str4);
        createMap.putString("localMediaPath", "file://" + str5);
        createMap.putString("mediaType", str6);
        createMap.putInt("width", num2.intValue());
        createMap.putInt("height", num.intValue());
        createMap.putString("fileExtension", str7);
        return createMap;
    }

    public static WritableMap createMediaUploadFailureMap(@NonNull AmazonPhotosUploadRequest amazonPhotosUploadRequest, @NonNull Throwable th) {
        return createMediaUploadFailureMap(amazonPhotosUploadRequest, th.toString());
    }

    public static WritableMap createMediaUploadSuccessMap(@NonNull AmazonPhotosUploadRequest amazonPhotosUploadRequest, @NonNull String str, @NonNull String str2) {
        return createMediaUploadSuccessMap(amazonPhotosUploadRequest.getConversationId(), amazonPhotosUploadRequest.getClientId(), amazonPhotosUploadRequest.getPhotoMessageMediaDataId(), amazonPhotosUploadRequest.getMediaPath(), str, str2);
    }

    public static Contact createPersonContact(@NonNull Person person) {
        BridgeContact bridgeContact = new BridgeContact();
        bridgeContact.setId(person.getId());
        bridgeContact.setShouldDisplay(person.shouldDisplay());
        bridgeContact.setCommsIds(person.getCommsIds());
        bridgeContact.setSourceDeviceId(person.getSourceDeviceId());
        bridgeContact.setDeviceContactId(person.getDeviceContactId());
        bridgeContact.setOwnerCommsId(person.getOwnerCommsId());
        if (!Strings.isNullOrEmpty(person.getAor())) {
            ArrayList arrayList = new ArrayList();
            IdentityRawData identityRawData = new IdentityRawData();
            identityRawData.setAor(person.getAor());
            identityRawData.setUser(person.getCommsIds().get(0));
            arrayList.add(identityRawData);
            bridgeContact.setCommsIdentities(arrayList);
        }
        bridgeContact.setAlexaEnabled(person.isAlexaEnabled());
        bridgeContact.setBlocked(person.isBlocked());
        bridgeContact.setCanDropInOnMe(person.getCanDropIn());
        bridgeContact.setCanIDropInOnHim(person.getCanBeDroppedIn());
        bridgeContact.setBulkImport(person.isBulkImport());
        bridgeContact.setContactName(person.getName());
        bridgeContact.setCompany(person.getCompanyName());
        bridgeContact.setPhoneNumbers(person.getPhoneNumbers());
        bridgeContact.setEmails(person.getEmails());
        bridgeContact.setIsChild(person.isChild());
        bridgeContact.setRelationship(person.getRelationship());
        bridgeContact.setReferenceContactId(person.getReferenceContactId());
        bridgeContact.setReferenceCommsId(person.getReferenceCommsId());
        bridgeContact.setIsLinked(person.isLinked());
        bridgeContact.setContactSourceType(person.getContactSourceType());
        bridgeContact.setAddresses(person.getAddresses());
        bridgeContact.setIsMerged(person.isMerged());
        bridgeContact.setShouldMerge(person.shouldMerge());
        bridgeContact.setMergedContactIdentifiers(person.getMergeContactIdentifiers());
        return bridgeContact;
    }

    private static String getStringOrNull(@NonNull ReadableMap readableMap, @NonNull String str) {
        if (readableMap.hasKey(str)) {
            return readableMap.getString(str);
        }
        return null;
    }

    @NonNull
    private WritableMap populateName(@NonNull WritableMap writableMap, @NonNull ContactName contactName) {
        writableMap.putString("firstName", contactName.getFirstName());
        writableMap.putString("lastName", contactName.getLastName());
        writableMap.putString("nickname", contactName.getNickName());
        writableMap.putString(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME, contactName.getNickName());
        writableMap.putString(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_NICK_NAME, contactName.getSourceNickName());
        writableMap.putString(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_FIRST_NAME, contactName.getPhoneticFirstName());
        writableMap.putString(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_LAST_NAME, contactName.getPhoneticLastName());
        writableMap.putString(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PRONUNCIATION_FIRST_NAME, contactName.getPronunciationFirstName());
        writableMap.putString(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PRONUNCIATION_LAST_NAME, contactName.getPronunciationLastName());
        return writableMap;
    }

    private static boolean readNullableBoolean(@NonNull ReadableMap readableMap, @NonNull String str, boolean z) {
        return (!readableMap.hasKey(str) || readableMap.isNull(str)) ? z : readableMap.getBoolean(str);
    }

    public static WritableMap toWritableMap(@NonNull Map<String, Object> map) {
        return Arguments.makeNativeMap(map);
    }

    @NonNull
    public List<ContactPhoneNumber> convertReadableArrayToPhoneNumberArray(@NonNull ReadableArray readableArray) {
        ArrayList arrayList = new ArrayList();
        if (readableArray != null) {
            for (int i = 0; i < readableArray.size(); i++) {
                arrayList.add(getPhoneNumberObject(readableArray.mo6944getMap(i)));
            }
        }
        return arrayList;
    }

    @NonNull
    WritableMap createAddressMap(@NonNull ContactAddress contactAddress) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("value", contactAddress.getValue());
        createMap.putString(ContactProviderContract.AddressEntry.COLUMN_ADDRESS_RAW_TYPE, contactAddress.getRawType());
        createMap.putString("type", contactAddress.getType());
        return createMap;
    }

    @NonNull
    public WritableMap createContactNameMap(@NonNull ContactName contactName) {
        return populateName(Arguments.createMap(), contactName);
    }

    @NonNull
    public WritableArray createEmailAddressArray(@NonNull List<ContactEmailAddress> list) {
        WritableArray createArray = Arguments.createArray();
        for (ContactEmailAddress contactEmailAddress : list) {
            createArray.pushMap(createEmailAddressMap(contactEmailAddress));
        }
        return createArray;
    }

    @NonNull
    @VisibleForTesting
    WritableMap createEmailAddressMap(@NonNull ContactEmailAddress contactEmailAddress) {
        WritableMap createMap = Arguments.createMap();
        String rawType = contactEmailAddress.getRawType();
        createMap.putString(ContactProviderContract.EMAIL_ADDRESS_PATH, contactEmailAddress.getEmailAddress());
        createMap.putString("email", contactEmailAddress.getEmailAddress());
        if (contactEmailAddress.getType() != null) {
            EmailAddressType type = contactEmailAddress.getType();
            createMap.putString("type", contactEmailAddress.getType().getAcmsType());
            createMap.putString("displayTypeName", ((type == EmailAddressType.Custom || type == EmailAddressType.Other) && !StringUtils.isEmpty(rawType)) ? rawType : this.mContext.getString(type.getDisplayResId()));
        } else {
            createMap.putString("displayTypeName", rawType);
        }
        createMap.putString(ContactProviderContract.AddressEntry.COLUMN_ADDRESS_RAW_TYPE, rawType);
        return createMap;
    }

    public Contact createHomeGroupContactFromMap(@NonNull ReadableMap readableMap) {
        BridgeContact bridgeContact = new BridgeContact();
        bridgeContact.setId(readableMap.getString("id"));
        bridgeContact.setHomeGroup(true);
        bridgeContact.setShouldDisplay(false);
        bridgeContact.setAlexaEnabled(true);
        bridgeContact.setBulkImport(false);
        bridgeContact.setSourceDeviceId(null);
        bridgeContact.setDeviceContactId(null);
        bridgeContact.setCompany(null);
        bridgeContact.setPhoneNumbers(null);
        bridgeContact.setEmails(null);
        bridgeContact.setOwnerCommsId(this.mCommsIdentityManager.getCommsId("createHomeGroupContactFromMap", false));
        ArrayList arrayList = new ArrayList();
        String string = readableMap.getString("homeGroupCommsId");
        arrayList.add(string);
        bridgeContact.setCommsIds(arrayList);
        IdentityRawData identityRawData = new IdentityRawData();
        identityRawData.setUser(string);
        bridgeContact.setHomeGroupIdentity(identityRawData);
        ReadableArray array = readableMap.getArray("members");
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < array.size(); i++) {
            String string2 = array.getString(i);
            IdentityRawData identityRawData2 = new IdentityRawData();
            identityRawData2.setUser(string2);
            arrayList2.add(identityRawData2);
        }
        bridgeContact.setCommsIdentities(arrayList2);
        bridgeContact.setCanDropInOnMe(readableMap.getBoolean("canDropIn"));
        bridgeContact.setCanIDropInOnHim(readableMap.getBoolean("canBeDroppedInOn"));
        if (!readableMap.isNull(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_BLOCKED)) {
            bridgeContact.setBlocked(readableMap.getBoolean(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_BLOCKED));
        }
        bridgeContact.setContactName(new ContactName(readableMap.getString("name"), null, null));
        bridgeContact.setIsMerged(false);
        bridgeContact.setShouldMerge(false);
        bridgeContact.setAddresses(new ArrayList());
        bridgeContact.setMergedContactIdentifiers(new ArrayList());
        return bridgeContact;
    }

    @NonNull
    public WritableMap createMediaMetadataMap(@NonNull MediaMetadata mediaMetadata) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("fileName", mediaMetadata.getFileName());
        createMap.putInt("duration", mediaMetadata.getDuration());
        return createMap;
    }

    @Nullable
    public ContactName createPersonContactName(ReadableMap readableMap) {
        if (readableMap == null) {
            return null;
        }
        String string = readableMap.hasKey("firstName") ? readableMap.getString("firstName") : null;
        String string2 = readableMap.hasKey("lastName") ? readableMap.getString("lastName") : null;
        String string3 = readableMap.hasKey(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_FIRST_NAME) ? readableMap.getString(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_FIRST_NAME) : null;
        String string4 = readableMap.hasKey(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_LAST_NAME) ? readableMap.getString(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_LAST_NAME) : null;
        String string5 = readableMap.hasKey(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PRONUNCIATION_FIRST_NAME) ? readableMap.getString(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PRONUNCIATION_FIRST_NAME) : null;
        String string6 = readableMap.hasKey(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PRONUNCIATION_LAST_NAME) ? readableMap.getString(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PRONUNCIATION_LAST_NAME) : null;
        String str = "nickname";
        if (!readableMap.hasKey(str)) {
            str = ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME;
        }
        String string7 = readableMap.hasKey(str) ? readableMap.getString(str) : null;
        String string8 = readableMap.hasKey(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_NICK_NAME) ? readableMap.getString(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_NICK_NAME) : null;
        return new ContactName(StringUtils.isEmpty(string) ? null : string, StringUtils.isEmpty(string2) ? null : string2, StringUtils.isEmpty(string3) ? null : string3, StringUtils.isEmpty(string4) ? null : string4, string5, string6, StringUtils.isEmpty(string7) ? null : string7, StringUtils.isEmpty(string8) ? null : string8);
    }

    @NonNull
    public WritableArray createPhoneNumberArray(@NonNull List<ContactPhoneNumber> list) {
        WritableArray createArray = Arguments.createArray();
        for (ContactPhoneNumber contactPhoneNumber : list) {
            createArray.pushMap(createPhoneNumberMap(contactPhoneNumber));
        }
        return createArray;
    }

    @NonNull
    @VisibleForTesting
    WritableMap createPhoneNumberMap(@NonNull ContactPhoneNumber contactPhoneNumber) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("number", contactPhoneNumber.getPhoneNumber());
        String rawType = contactPhoneNumber.getRawType();
        createMap.putBoolean("isCOBOEnabled", contactPhoneNumber.isCOBOEnabled());
        if (contactPhoneNumber.getType() != null) {
            createMap.putString("type", contactPhoneNumber.getType().getAcmsType());
            createMap.putString("displayTypeName", CallViewUtils.getPhoneNumberType(this.mContext, contactPhoneNumber));
        } else {
            createMap.putString("displayTypeName", rawType);
        }
        createMap.putString(ContactProviderContract.AddressEntry.COLUMN_ADDRESS_RAW_TYPE, rawType);
        return createMap;
    }

    @NonNull
    public WritableArray createRelationshipArray(@NonNull List<ContactUploadInfo.Relationship> list) {
        WritableArray createArray = Arguments.createArray();
        if (list == null) {
            return createArray;
        }
        for (ContactUploadInfo.Relationship relationship : list) {
            createArray.pushMap(createRelationshipMap(relationship));
        }
        return createArray;
    }

    @NonNull
    @VisibleForTesting
    WritableMap createRelationshipMap(@NonNull ContactUploadInfo.Relationship relationship) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString(SettingsStorageModule.FILTER_SETTINGS_CONTACT_NAME_KEY, relationship.getContactName());
        createMap.putString("relationShipType", relationship.getRelationType());
        return createMap;
    }

    public WritableMap getAddressBookContact(@NonNull String str, @NonNull ContactName contactName, @Nullable String str2, @NonNull List<ContactPhoneNumber> list, @NonNull List<ContactEmailAddress> list2, @NonNull List<ContactUploadInfo.Relationship> list3) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_DEVICE_CONTACT_ID, str);
        contactName.setNickName(contactName.getSourceNickName());
        populateName(createMap, contactName);
        createMap.putString("companyName", str2);
        createMap.putArray("numbers", createPhoneNumberArray(list));
        createMap.putArray("emails", createEmailAddressArray(list2));
        createMap.putArray("relationships", createRelationshipArray(list3));
        return createMap;
    }

    @NonNull
    ContactAddress getAddressObject(@NonNull ReadableMap readableMap) {
        ContactAddress contactAddress = new ContactAddress();
        contactAddress.setValue(readableMap.getString("value"));
        contactAddress.setRawType(readableMap.getString(ContactProviderContract.AddressEntry.COLUMN_ADDRESS_RAW_TYPE));
        contactAddress.setType(readableMap.getString("type"));
        return contactAddress;
    }

    public List<DeviceModel> getDeviceModelListFromArray(ReadableArray readableArray) {
        ArrayList arrayList = new ArrayList();
        Iterator<Object> it2 = readableArray.toArrayList().iterator();
        while (it2.hasNext()) {
            HashMap hashMap = (HashMap) it2.next();
            DeviceModel deviceModel = new DeviceModel();
            DeviceStatusModel deviceStatusModel = new DeviceStatusModel();
            deviceModel.setDeviceGruu((String) hashMap.get("deviceGruu"));
            deviceModel.setDeviceName((String) hashMap.get(DefaultBluetoothService.AUDIO_DEVICE_NAME));
            deviceStatusModel.setActiveState(ActiveState.valueOf((String) hashMap.get("deviceStatus")));
            deviceStatusModel.setDeviceDropInAvailability(DropInAvailability.valueOf((String) hashMap.get("dropInAvailability")));
            deviceStatusModel.setDeviceCommsAvailability(DeviceCommsAvailability.ON);
            deviceStatusModel.setVideoEnabled(((Boolean) hashMap.get("isVideoCapable")).booleanValue());
            deviceStatusModel.setOnline(((Boolean) hashMap.get("isOnLine")).booleanValue());
            deviceStatusModel.setRegistered(((Boolean) hashMap.get("canDropIn")).booleanValue());
            deviceModel.setDeviceStatus(deviceStatusModel);
            arrayList.add(deviceModel);
        }
        return arrayList;
    }

    @NonNull
    @VisibleForTesting
    ContactEmailAddress getEmailAddressObject(@NonNull ReadableMap readableMap) {
        ContactEmailAddress contactEmailAddress = new ContactEmailAddress();
        contactEmailAddress.setEmailAddress(readableMap.getString("email"));
        String str = ContactProviderContract.AddressEntry.COLUMN_ADDRESS_RAW_TYPE;
        if (!readableMap.hasKey(str)) {
            str = "displayTypeName";
        }
        contactEmailAddress.setRawType(readableMap.getString(str));
        String string = readableMap.getString("type");
        if (string != null) {
            char c = 65535;
            int hashCode = string.hashCode();
            if (hashCode != 2255103) {
                if (hashCode != 2702129) {
                    if (hashCode != 76517104) {
                        if (hashCode == 2029746065 && string.equals("Custom")) {
                            c = 3;
                        }
                    } else if (string.equals("Other")) {
                        c = 2;
                    }
                } else if (string.equals(NavigationMetrics.CardType.WORK)) {
                    c = 1;
                }
            } else if (string.equals("Home")) {
                c = 0;
            }
            if (c == 0) {
                contactEmailAddress.setType(EmailAddressType.Home);
            } else if (c == 1) {
                contactEmailAddress.setType(EmailAddressType.Work);
            } else if (c == 2) {
                contactEmailAddress.setType(EmailAddressType.Other);
            } else if (c != 3) {
                LOG.e("Unknown email type");
            } else {
                contactEmailAddress.setType(EmailAddressType.Custom);
            }
        }
        return contactEmailAddress;
    }

    @NonNull
    public ReadableArray getPersonMapArrayFromPersons(@NonNull Person[] personArr) {
        WritableArray createArray = Arguments.createArray();
        for (Person person : personArr) {
            createArray.pushMap(getWritablePersonMapFromPersonObject(person));
        }
        return createArray;
    }

    @NonNull
    public Person getPersonObjectFromReadableMap(@NonNull ReadableMap readableMap) {
        Person person = new Person();
        person.setId(readableMap.getString("id"));
        person.setName(createPersonContactName(readableMap.mo6945getMap("name")));
        person.setCommsIds(convertReadableArrayToStringArray(readableMap.getArray("commsIds")));
        if (readableMap.hasKey(ContactProviderContract.PhoneNumberEntry.COLUMN_AOR)) {
            person.setAor(readableMap.getString(ContactProviderContract.PhoneNumberEntry.COLUMN_AOR));
        }
        person.setDeviceContactId(readableMap.getString(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_DEVICE_CONTACT_ID));
        person.setOwnerCommsId(readableMap.getString("ownerCommsId"));
        person.setSourceDeviceId(readableMap.getString(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_DEVICE_ID));
        person.setHomeGroupCommsId(readableMap.getString("homeGroupCommsId"));
        boolean z = false;
        person.setBlocked(!readableMap.isNull(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_BLOCKED) && readableMap.getBoolean(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_BLOCKED));
        person.setAlexaEnabled(!readableMap.isNull(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_ALEXA_ENABLED) && readableMap.getBoolean(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_ALEXA_ENABLED));
        person.setBulkImport(!readableMap.isNull("isBulkImport") && readableMap.getBoolean("isBulkImport"));
        person.setCanDropIn(!readableMap.isNull("canDropIn") && readableMap.getBoolean("canDropIn"));
        person.setCanBeDroppedIn(!readableMap.isNull("canBeDroppedInOn") && readableMap.getBoolean("canBeDroppedInOn"));
        person.setEmails(convertReadableArrayToEmailAddressArray(readableMap.getArray("emails")));
        person.setPhoneNumbers(convertReadableArrayToPhoneNumberArray(readableMap.getArray(ContactsModuleConstants.CONTACT_PHONE_NUMBERS)));
        person.setCompanyName(readableMap.getString("companyName"));
        person.setChild(!readableMap.isNull(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_CHILD) && readableMap.getBoolean(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_CHILD));
        person.setRelationship(readableMap.getString(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_RELATIONSHIP));
        person.setReferenceContactId(readableMap.getString(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_REFERENCE_CONTACT_ID));
        person.setReferenceCommsId(readableMap.getString(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_REFERENCE_COMMS_ID));
        person.setLinked(!readableMap.isNull(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_LINKED) && readableMap.getBoolean(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_LINKED));
        person.setContactSourceType(readableMap.getString(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_CONTACT_SOURCE_TYPE));
        person.setIsMerged(!readableMap.isNull(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_MERGED) && readableMap.getBoolean(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_MERGED));
        person.setShouldMerge(!readableMap.isNull(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SHOULD_MERGE) ? readableMap.getBoolean(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SHOULD_MERGE) : true);
        if (!readableMap.isNull(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SHOULD_DISPLAY) && readableMap.getBoolean(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SHOULD_DISPLAY)) {
            z = true;
        }
        person.setShouldDisplay(z);
        person.setAddresses(convertReadableArrayToAddressArray(readableMap.getArray("addresses")));
        person.setMergeContactIdentifiers(convertReadableArrayToStringArray(readableMap.getArray("mergedContactIdentifiers")));
        return person;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00a8, code lost:
        if (r5.equals("Home") != false) goto L11;
     */
    @androidx.annotation.NonNull
    @androidx.annotation.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    com.amazon.deecomms.contacts.model.ContactPhoneNumber getPhoneNumberObject(@androidx.annotation.NonNull com.facebook.react.bridge.ReadableMap r5) {
        /*
            Method dump skipped, instructions count: 452
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer.getPhoneNumberObject(com.facebook.react.bridge.ReadableMap):com.amazon.deecomms.contacts.model.ContactPhoneNumber");
    }

    public List<PresenceDocument> getPresenceDocumentsFromArray(ReadableArray readableArray) {
        ArrayList arrayList = new ArrayList();
        Iterator<Object> it2 = readableArray.toArrayList().iterator();
        while (it2.hasNext()) {
            HashMap hashMap = (HashMap) it2.next();
            PresenceDocument presenceDocument = new PresenceDocument();
            presenceDocument.setContactId((String) hashMap.get("contactId"));
            presenceDocument.setContactHomegroupId((String) hashMap.get("contactHomegroupId"));
            presenceDocument.setContactName((String) hashMap.get(SettingsStorageModule.FILTER_SETTINGS_CONTACT_NAME_KEY));
            presenceDocument.setPresenceStatus((String) hashMap.get("contactPresenceStatus"));
            arrayList.add(presenceDocument);
        }
        return arrayList;
    }

    @NonNull
    public WritableMap getWritablePersonMapFromPersonObject(@NonNull Person person) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString(CONTACT_DISCRIMINATOR_PROPERTY_NAME, PERSON_TYPE_DISCRIMINATOR);
        createMap.putString("id", person.getId());
        createMap.putArray("commsIds", convertStringArrayToWritableArray(person.getCommsIds()));
        if (!person.getCommsIds().isEmpty()) {
            createMap.putString("commsId", person.getCommsIds().get(0));
        }
        createMap.putString(ContactProviderContract.PhoneNumberEntry.COLUMN_AOR, person.getAor());
        createMap.putString(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_DEVICE_CONTACT_ID, person.getDeviceContactId());
        createMap.putString("ownerCommsId", person.getOwnerCommsId());
        createMap.putString(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_DEVICE_ID, person.getSourceDeviceId());
        createMap.putString("homeGroupCommsId", person.getHomeGroupCommsId());
        createMap.putBoolean(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_BLOCKED, person.isBlocked());
        createMap.putBoolean(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_ALEXA_ENABLED, person.isAlexaEnabled());
        createMap.putBoolean("isBulkImport", person.isBulkImport());
        createMap.putBoolean("canDropIn", person.getCanDropIn());
        createMap.putBoolean("canBeDroppedInOn", person.getCanBeDroppedIn());
        createMap.putMap("name", createContactNameMap(person.getName()));
        createMap.putString(CamerasRouteParameter.DISPLAY_NAME, ContactUtils.getFullName(person.getName()));
        createMap.putArray("emails", createEmailAddressArray(person.getEmails()));
        createMap.putArray(ContactsModuleConstants.CONTACT_PHONE_NUMBERS, createPhoneNumberArray(person.getPhoneNumbers()));
        createMap.putString("companyName", person.getCompanyName());
        createMap.putBoolean(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_CHILD, person.isChild());
        createMap.putString(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_RELATIONSHIP, person.getRelationship());
        createMap.putString(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_REFERENCE_CONTACT_ID, person.getReferenceContactId());
        createMap.putString(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_REFERENCE_COMMS_ID, person.getReferenceCommsId());
        createMap.putBoolean(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_LINKED, person.isLinked());
        createMap.putString(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_CONTACT_SOURCE_TYPE, person.getContactSourceType());
        createMap.putBoolean(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_MERGED, person.isMerged());
        createMap.putBoolean(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SHOULD_MERGE, person.shouldMerge());
        createMap.putBoolean(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SHOULD_DISPLAY, person.shouldDisplay());
        createMap.putArray("addresses", createAddressArray(person.getAddresses()));
        createMap.putArray("mergedContactIdentifiers", convertStringArrayToWritableArray(person.getMergeContactIdentifiers()));
        return createMap;
    }

    public static WritableMap createMediaDownloadFailureMap(@NonNull AmazonPhotosDownloadRequest amazonPhotosDownloadRequest, @NonNull String str) {
        return createMediaDownloadFailureMap(amazonPhotosDownloadRequest.getConversationId(), amazonPhotosDownloadRequest.getClientId(), amazonPhotosDownloadRequest.getDimension(), amazonPhotosDownloadRequest.getNodeId(), str);
    }

    public static WritableMap createMediaUploadFailureMap(@NonNull AmazonPhotosUploadRequest amazonPhotosUploadRequest, @NonNull String str) {
        return createMediaUploadFailureMap(amazonPhotosUploadRequest.getConversationId(), amazonPhotosUploadRequest.getClientId(), amazonPhotosUploadRequest.getPhotoMessageMediaDataId(), amazonPhotosUploadRequest.getMediaPath(), str);
    }

    public static WritableMap createMediaUploadSuccessMap(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5, @NonNull String str6) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("conversationId", str);
        createMap.putString(AuthorizationResponseParser.CLIENT_ID_STATE, str2);
        createMap.putString("photoMessageMediaDataId", str3);
        createMap.putString("mediaPath", str4);
        createMap.putString(AlexaDeviceBackgroundImageBridge.KEY_NODE_ID, str5);
        createMap.putString(MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY, str6);
        return createMap;
    }

    public static WritableMap createMediaDownloadFailureMap(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull Throwable th) {
        return createMediaDownloadFailureMap(str, str2, str3, str4, th.toString());
    }

    public static WritableMap createMediaUploadFailureMap(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("conversationId", str);
        createMap.putString(AuthorizationResponseParser.CLIENT_ID_STATE, str2);
        createMap.putString("photoMessageMediaDataId", str3);
        createMap.putString("mediaPath", str4);
        createMap.putString(JavaScriptResponse.KEY_ERROR_REASON, str5);
        return createMap;
    }

    public WritableMap getAddressBookContact(@NonNull ContactUploadInfo contactUploadInfo) {
        ContactUploadInfo.Name name = contactUploadInfo.getName();
        if (name == null) {
            contactUploadInfo.setContactName(null, null);
            name = contactUploadInfo.getName();
        }
        return getAddressBookContact(contactUploadInfo.getDeviceContactId(), new ContactName(name.getFirstName(), name.getLastName(), name.getPhoneticFirstName(), name.getPhoneticLastName(), null, null, null, name.getNickName()), contactUploadInfo.getCompany(), convertContactPhoneNumbers(contactUploadInfo), convertContactEmails(contactUploadInfo), contactUploadInfo.getRelationships());
    }

    public static WritableMap createMediaDownloadFailureMap(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("conversationId", str);
        createMap.putString(AuthorizationResponseParser.CLIENT_ID_STATE, str2);
        createMap.putString("dimension", str3);
        createMap.putString(AlexaDeviceBackgroundImageBridge.KEY_NODE_ID, str4);
        createMap.putString(JavaScriptResponse.KEY_ERROR_REASON, str5);
        return createMap;
    }

    public static WritableMap createMediaUploadFailureMap(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull Throwable th) {
        return createMediaUploadFailureMap(str, str2, str3, str4, th.toString());
    }
}
