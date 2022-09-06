package com.amazon.deecomms.nativemodules;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessorykit.finishsetup.presenters.FASCardPresenter;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.model.Contact;
import com.amazon.deecomms.contacts.operations.ContactsOperationHandler;
import com.amazon.deecomms.contacts.util.ContactsProviderUtils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.nativemodules.model.Person;
import com.amazon.deecomms.nativemodules.util.ContactsDataStoreUtil;
import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import com.amazon.deecomms.ndt.DevicesSource;
import com.amazon.deecomms.ndt.model.DeviceModel;
import com.amazon.deecomms.ndt.model.GetDevicesResponse;
import com.amazon.deecomms.util.Consumer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
/* loaded from: classes12.dex */
public class CommsContactsStoreBridge extends ReactContextBaseJavaModule {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsContactsStoreBridge.class);
    final DevicesSource devicesSource;
    final Context mApplicationContext;
    final CommsIdentityManager mCommsIdentityManager;
    final ContactsDataStoreUtil mContactsDataStoreUtil;
    final ReactBridgeSerializer mReactBridgeSerializer;

    public CommsContactsStoreBridge(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, new ContactsDataStoreUtil(CommsDaggerWrapper.getComponent().getContext()), CommsDaggerWrapper.getComponent().getCommsIdentityManager(), CommsDaggerWrapper.getComponent().getDevicesSource());
    }

    private List<Contact> convertContacts(@NonNull ReadableArray readableArray) {
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        for (int i = 0; i < readableArray.size(); i++) {
            ReadableMap mo6944getMap = readableArray.mo6944getMap(i);
            String string = mo6944getMap.getString(ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME);
            if (ReactBridgeSerializer.PERSON_TYPE_DISCRIMINATOR.equals(string)) {
                arrayList.add(ReactBridgeSerializer.createPersonContact(this.mReactBridgeSerializer.getPersonObjectFromReadableMap(mo6944getMap)));
            } else if (ReactBridgeSerializer.HOMEGROUP_TYPE_DISCRIMINATOR.equals(string)) {
                Contact createHomeGroupContactFromMap = this.mReactBridgeSerializer.createHomeGroupContactFromMap(mo6944getMap);
                if (hashSet.add(createHomeGroupContactFromMap.getHomeGroupIdentity().getId())) {
                    arrayList.add(createHomeGroupContactFromMap);
                }
            } else {
                GeneratedOutlineSupport1.outline159("Invalid contact discriminator: ", string, LOG);
            }
        }
        return arrayList;
    }

    private boolean isContactPresent(@NonNull Person person) {
        Person personByServerIdFromDatabase;
        if (Objects.equals(this.mCommsIdentityManager.getHomeGroupId("isContactPresent", false), person.getHomeGroupCommsId())) {
            GetDevicesResponse memoryData = this.devicesSource.getMemoryData();
            if (memoryData == null) {
                return false;
            }
            List<DeviceModel> activeDropInDevices = memoryData.getActiveDropInDevices();
            if (person.isChild()) {
                String str = person.getCommsIds().get(0);
                return str != null && !str.isEmpty() && Utils.getDevicesForChildAccount(activeDropInDevices, str).size() > 0;
            }
            return !activeDropInDevices.isEmpty();
        } else if (person.isChild()) {
            return false;
        } else {
            String homeGroupCommsId = person.getHomeGroupCommsId();
            if (TextUtils.isEmpty(homeGroupCommsId)) {
                return false;
            }
            String serverContactIdFromCommsId = this.mContactsDataStoreUtil.getServerContactIdFromCommsId(homeGroupCommsId);
            return !TextUtils.isEmpty(serverContactIdFromCommsId) && (personByServerIdFromDatabase = this.mContactsDataStoreUtil.getPersonByServerIdFromDatabase(serverContactIdFromCommsId)) != null && personByServerIdFromDatabase.getCanBeDroppedIn();
        }
    }

    @ReactMethod
    public void deleteContact(String str, Promise promise) {
        HashSet hashSet = new HashSet();
        hashSet.add(str);
        if (ContactsProviderUtils.deleteContacts(this.mApplicationContext, hashSet)) {
            promise.resolve(null);
        } else {
            promise.reject((String) null, "deleteContact failed!");
        }
    }

    @ReactMethod
    public void didHit40xServerError(String str, Promise promise) {
        promise.resolve(Boolean.valueOf(ContactsProviderUtils.isCommsIdUngettable(this.mApplicationContext, str)));
    }

    @ReactMethod
    public void getChildContacts(Promise promise) {
        Person[] childContactsFromDatabase = this.mContactsDataStoreUtil.getChildContactsFromDatabase();
        if (childContactsFromDatabase != null) {
            promise.resolve(this.mReactBridgeSerializer.getPersonMapArrayFromPersons(childContactsFromDatabase));
        } else {
            promise.reject((String) null, "Database error while fetching childContacts");
        }
    }

    @ReactMethod
    public void getContactByCommsId(@Nullable String str, @NonNull Promise promise) {
        CommsLogger commsLogger = LOG;
        commsLogger.d("getContactByCommsId(%s, Promise)", commsLogger.sensitive(str));
        if (TextUtils.isEmpty(str)) {
            MetricsHelper.recordOperationalMetricWithSource(MetricKeys.EMPTY_COMMS_ID, "getContactByCommsId");
            promise.reject((String) null, "commsId parameter cannot be null or empty");
            return;
        }
        String serverContactIdFromCommsId = this.mContactsDataStoreUtil.getServerContactIdFromCommsId(str);
        CommsLogger commsLogger2 = LOG;
        commsLogger2.d("getContactByCommsId() -- serverId = %s", commsLogger2.sensitive(serverContactIdFromCommsId));
        if (TextUtils.isEmpty(serverContactIdFromCommsId)) {
            MetricsHelper.recordOperationalMetricWithSource(MetricKeys.EMPTY_SERVER_ID, "getContactByCommsId");
            promise.reject((String) null, "unable to convert commsId into a contact's server id");
            return;
        }
        getContactById(serverContactIdFromCommsId, promise);
    }

    @ReactMethod
    public void getContactById(String str, Promise promise) {
        Person personByServerIdFromDatabase = this.mContactsDataStoreUtil.getPersonByServerIdFromDatabase(str);
        if (personByServerIdFromDatabase != null) {
            promise.resolve(this.mReactBridgeSerializer.getWritablePersonMapFromPersonObject(personByServerIdFromDatabase));
            return;
        }
        MetricsHelper.recordOperationalMetricWithSource(MetricKeys.GET_CONTACT_FAIL, "getContactById");
        promise.reject((String) null, "Database error while fetching contactById");
    }

    @ReactMethod
    public void getContactStatusByCommsId(@Nullable String str, @NonNull Promise promise) {
        CommsLogger commsLogger = LOG;
        commsLogger.d("getContactStatusByCommsId(%s, Promise)", commsLogger.sensitive(str));
        if (TextUtils.isEmpty(str)) {
            promise.reject((String) null, "commsId parameter cannot be null or empty");
            return;
        }
        String serverContactIdFromCommsId = this.mContactsDataStoreUtil.getServerContactIdFromCommsId(str);
        CommsLogger commsLogger2 = LOG;
        commsLogger2.d("getContactStatusByCommsId() -- serverId = %s", commsLogger2.sensitive(serverContactIdFromCommsId));
        if (TextUtils.isEmpty(serverContactIdFromCommsId)) {
            promise.reject((String) null, "unable to convert commsId into a contact's server id");
        } else {
            getContactStatusById(serverContactIdFromCommsId, promise);
        }
    }

    @ReactMethod
    public void getContactStatusById(String str, Promise promise) {
        if (str != null && !str.isEmpty()) {
            Person personByServerIdFromDatabase = this.mContactsDataStoreUtil.getPersonByServerIdFromDatabase(str);
            if (personByServerIdFromDatabase == null) {
                promise.reject((String) null, "person doesn't exist for that id");
                return;
            }
            boolean isContactPresent = isContactPresent(personByServerIdFromDatabase);
            boolean z = personByServerIdFromDatabase.isAlexaEnabled() && !personByServerIdFromDatabase.isChild();
            boolean canBeDroppedIn = personByServerIdFromDatabase.getCanBeDroppedIn();
            WritableMap createMap = Arguments.createMap();
            createMap.putBoolean(FASCardPresenter.ACTION_PRESENT, isContactPresent);
            createMap.putBoolean("supportsAudio", true);
            createMap.putBoolean("supportsVideo", z);
            createMap.putBoolean("supportsDropIn", canBeDroppedIn);
            promise.resolve(createMap);
            return;
        }
        promise.reject((String) null, "bad input");
    }

    @ReactMethod
    public void getContactsByOwnerCommsIds(ReadableArray readableArray, Promise promise) {
        if (readableArray.size() == 0) {
            promise.resolve(Arguments.createArray());
            return;
        }
        Person[] contactsByOwnerCommsIdFromDatabase = this.mContactsDataStoreUtil.getContactsByOwnerCommsIdFromDatabase(readableArray.getString(0));
        if (contactsByOwnerCommsIdFromDatabase != null) {
            promise.resolve(this.mReactBridgeSerializer.getPersonMapArrayFromPersons(contactsByOwnerCommsIdFromDatabase));
        } else {
            promise.reject((String) null, "Database error while fetching contacts by ownerCommsId");
        }
    }

    @ReactMethod
    public void getContactsForIds(ReadableArray readableArray, Promise promise) {
        String[] strArr = new String[readableArray.size()];
        for (int i = 0; i < readableArray.size(); i++) {
            strArr[i] = readableArray.getString(i);
        }
        Person[] contactsByIds = this.mContactsDataStoreUtil.getContactsByIds(strArr);
        if (contactsByIds != null) {
            promise.resolve(this.mReactBridgeSerializer.getPersonMapArrayFromPersons(contactsByIds));
        } else {
            promise.reject((String) null, "Database error while fetching contacts by contactIds");
        }
    }

    @ReactMethod
    public void getContactsNamesByCommsId(@NonNull ReadableArray readableArray, @NonNull final Promise promise) {
        this.mContactsDataStoreUtil.getContactsNamesByCommsId(new Consumer() { // from class: com.amazon.deecomms.nativemodules.-$$Lambda$CommsContactsStoreBridge$mwH3eU0Yn2D1AfqkfxyLDkSyHec
            @Override // com.amazon.deecomms.util.Consumer
            public final void accept(Object obj) {
                Promise.this.resolve((ReadableMap) obj);
            }
        }, ReactBridgeSerializer.convertReadableArrayToStringArray(readableArray));
    }

    @ReactMethod
    public void getContactsWithBlockStatus(Promise promise) {
        ReadableArray contactsWithBlockStatusArrayFromDatabase = this.mContactsDataStoreUtil.getContactsWithBlockStatusArrayFromDatabase();
        if (contactsWithBlockStatusArrayFromDatabase != null) {
            promise.resolve(contactsWithBlockStatusArrayFromDatabase);
        } else {
            promise.reject((String) null, "Cannot retrieve contacts with block status from Database");
        }
    }

    @ReactMethod
    public void getHomeGroupByCommsId(String str, Promise promise) {
        String homeGroupIdFromCommsIdFromDb = ContactsProviderUtils.getHomeGroupIdFromCommsIdFromDb(this.mApplicationContext, str);
        if (homeGroupIdFromCommsIdFromDb != null) {
            ReadableMap homeGroup = this.mContactsDataStoreUtil.getHomeGroup(homeGroupIdFromCommsIdFromDb);
            if (homeGroup != null) {
                promise.resolve(homeGroup);
                return;
            }
            LOG.e("no home group for the given hg comms id found in the database");
            promise.reject((String) null, "no home group for the given hg comms id found in the database");
            return;
        }
        LOG.i("getHomeGroupByCommsId: no home group for the given commsId found in the database");
        promise.resolve(null);
    }

    @ReactMethod
    public void getHomeGroups(@NonNull Promise promise) {
        ReadableMap allHomeGroups = this.mContactsDataStoreUtil.getAllHomeGroups();
        if (allHomeGroups == null) {
            promise.reject((String) null, "Failed to get homeGroups.");
        } else {
            promise.resolve(allHomeGroups);
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "CommsLocalContactsStore";
    }

    @ReactMethod
    public void getSimpleContacts(Promise promise) {
        ReadableArray contactsAsSimplePersonFromDatabase = this.mContactsDataStoreUtil.getContactsAsSimplePersonFromDatabase();
        if (contactsAsSimplePersonFromDatabase != null) {
            promise.resolve(contactsAsSimplePersonFromDatabase);
        } else {
            promise.reject((String) null, "Database error while fetching simpleContacts");
        }
    }

    @ReactMethod
    public void saveContact(ReadableMap readableMap, Promise promise) {
        Contact createHomeGroupContactFromMap;
        String string = readableMap.getString(ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME);
        if (ReactBridgeSerializer.PERSON_TYPE_DISCRIMINATOR.equals(string)) {
            createHomeGroupContactFromMap = ReactBridgeSerializer.createPersonContact(this.mReactBridgeSerializer.getPersonObjectFromReadableMap(readableMap));
        } else if (ReactBridgeSerializer.HOMEGROUP_TYPE_DISCRIMINATOR.equals(string)) {
            createHomeGroupContactFromMap = this.mReactBridgeSerializer.createHomeGroupContactFromMap(readableMap);
        } else {
            LOG.e("saveContact: Invalid contact discriminator value.");
            promise.reject((String) null, "Invalid contact discriminator value.");
            return;
        }
        if (ContactsProviderUtils.insertOrUpdateContact(this.mApplicationContext, createHomeGroupContactFromMap, ContactsProviderUtils.ContentProviderOperationType.INSERT)) {
            promise.resolve(null);
            return;
        }
        LOG.e("saveContact: Saving contact failed.");
        promise.reject((String) null, "Saving contact failed.");
    }

    @ReactMethod
    public void saveContacts(ReadableArray readableArray, ReadableArray readableArray2, Promise promise) {
        boolean deleteAndInsertContacts;
        if (readableArray2.size() == 0) {
            promise.resolve(Arguments.createArray());
            return;
        }
        boolean z = false;
        String string = readableArray2.getString(0);
        if (string != null && !string.equals(this.mCommsIdentityManager.getCommsId("saveContacts", false)) && !string.equals(this.mCommsIdentityManager.getHomeGroupId("saveContacts", false))) {
            z = true;
        }
        List<Contact> convertContacts = convertContacts(readableArray);
        if (z) {
            deleteAndInsertContacts = ContactsProviderUtils.insertOrUpdateContactListForChild(this.mApplicationContext, convertContacts, string);
        } else {
            deleteAndInsertContacts = ContactsProviderUtils.deleteAndInsertContacts(this.mApplicationContext, convertContacts);
        }
        if (deleteAndInsertContacts) {
            promise.resolve(null);
            return;
        }
        LOG.e("saveContacts: Saving contacts failed.");
        promise.reject((String) null, "Saving contacts failed.");
    }

    @ReactMethod
    public void setDidHit40xServerError(String str, boolean z, Promise promise) {
        if (z) {
            ContactsProviderUtils.markCommsIdUngettable(this.mApplicationContext, str);
        } else {
            ContactsProviderUtils.removeUngettableCommsIDIfObtained(this.mApplicationContext, new String[]{str});
        }
        promise.resolve(null);
    }

    @ReactMethod
    public void setHomeGroupCanBeDroppedInOn(String str, boolean z, Promise promise) {
        if (this.mContactsDataStoreUtil.setHomeGroupCanBeDroppedInOn(str, z)) {
            promise.resolve(null);
            return;
        }
        LOG.e("setHomeGroupCanDropInOn: Could not set drop in on status for homegroup.");
        promise.reject((String) null, "Could not set drop in on status for homegroup.");
    }

    @ReactMethod
    public void setHomeGroupCanDropIn(String str, boolean z, Promise promise) {
        if (this.mContactsDataStoreUtil.setHomeGroupCanDropIn(str, z)) {
            promise.resolve(null);
            return;
        }
        LOG.e("setHomeGroupCanDropIn: Could not set drop in status for homegroup.");
        promise.reject((String) null, "Could not set drop in status for homegroup.");
    }

    @ReactMethod
    public void setHomeGroupIdForMemberIds(String str, ReadableArray readableArray, Promise promise) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < readableArray.size(); i++) {
            arrayList.add(readableArray.getString(i));
        }
        ContactsOperationHandler.updateParentHomeGroupId(this.mApplicationContext, str, arrayList);
        promise.resolve(null);
    }

    @ReactMethod
    public void updateContact(ReadableMap readableMap, Promise promise) {
        String string = readableMap.getString(ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME);
        if (!ReactBridgeSerializer.PERSON_TYPE_DISCRIMINATOR.equals(string)) {
            promise.reject((String) null, "Unrecognizable contact kind : " + string);
            return;
        }
        Person personObjectFromReadableMap = this.mReactBridgeSerializer.getPersonObjectFromReadableMap(readableMap);
        String homeGroupCommsId = personObjectFromReadableMap.getHomeGroupCommsId();
        if (homeGroupCommsId == null && personObjectFromReadableMap.getCommsIds().size() > 0) {
            homeGroupCommsId = ContactsProviderUtils.getHomeGroupIdFromCommsIdFromDb(this.mApplicationContext, personObjectFromReadableMap.getCommsIds().get(0));
        }
        Set<String> contactIdsForCurrentUser = ContactsProviderUtils.getContactIdsForCurrentUser(this.mApplicationContext);
        Contact createPersonContact = ReactBridgeSerializer.createPersonContact(personObjectFromReadableMap);
        ContactsProviderUtils.insertOrUpdateContact(this.mApplicationContext, createPersonContact, contactIdsForCurrentUser.contains(createPersonContact.getId()) ? ContactsProviderUtils.ContentProviderOperationType.UPDATE : ContactsProviderUtils.ContentProviderOperationType.INSERT);
        if (homeGroupCommsId != null) {
            ContactsOperationHandler.updateParentHomeGroupId(this.mApplicationContext, homeGroupCommsId, personObjectFromReadableMap.getCommsIds());
        }
        promise.resolve(null);
    }

    @ReactMethod
    public void updateContactsBlockStatus(ReadableArray readableArray, Promise promise) {
        this.mContactsDataStoreUtil.updateContactsBlockStatus(readableArray);
        promise.resolve(null);
    }

    public CommsContactsStoreBridge(@NonNull ReactApplicationContext reactApplicationContext, @NonNull ContactsDataStoreUtil contactsDataStoreUtil, @NonNull CommsIdentityManager commsIdentityManager, @NonNull DevicesSource devicesSource) {
        super(reactApplicationContext);
        this.mApplicationContext = reactApplicationContext.getApplicationContext();
        this.mContactsDataStoreUtil = contactsDataStoreUtil;
        this.mCommsIdentityManager = commsIdentityManager;
        this.mReactBridgeSerializer = new ReactBridgeSerializer(this.mApplicationContext);
        this.devicesSource = devicesSource;
    }

    public CommsContactsStoreBridge(@NonNull ReactApplicationContext reactApplicationContext, @NonNull ContactsDataStoreUtil contactsDataStoreUtil, @NonNull CommsIdentityManager commsIdentityManager, @NonNull DevicesSource devicesSource, @NonNull ReactBridgeSerializer reactBridgeSerializer) {
        super(reactApplicationContext);
        this.mApplicationContext = reactApplicationContext.getApplicationContext();
        this.mContactsDataStoreUtil = contactsDataStoreUtil;
        this.mCommsIdentityManager = commsIdentityManager;
        this.mReactBridgeSerializer = reactBridgeSerializer;
        this.devicesSource = devicesSource;
    }
}
