package com.amazon.alexa.accessory.speech.context;

import android.bluetooth.BluetoothDevice;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.avsclient.context.AccessoryRole;
import com.amazon.alexa.accessory.avsclient.context.BluetoothProfileWatcher;
import com.amazon.alexa.accessory.avsclient.context.IOComponents;
import com.amazon.alexa.accessory.avsclient.context.IOComponentsSupplier;
import com.amazon.alexa.accessory.avsclient.context.TrustedStatesSupplier;
import com.amazon.alexa.accessory.avsclient.utils.JsonConverter;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.speechapi.AlexaConnection;
import com.amazon.alexa.accessory.speechapi.context.AccessoryContextProvider;
import com.amazon.alexa.accessory.speechapi.context.MessageContext;
import com.amazon.alexa.accessory.speechapi.context.MessageContextProvider;
import com.amazon.alexa.accessory.speechapi.context.MessageHeader;
import com.amazon.alexa.accessory.speechapi.context.MessagePayload;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public final class AlexaServicesContextProvider implements AccessoryContextProvider {
    public static final String ACCESSORY_NAME = "AccessoryState";
    public static final String ACCESSORY_NAME_SPACE = "AccessoryKit";
    private static final String TAG = "AlexaServicesContextProvider:";
    private final AccessoryMessageContextProvider accessoryMessageContextProvider;
    private ActiveAccessory activeMicrophoneAccessory;
    private final AlexaConnection alexaConnection;
    private final BluetoothProfileWatcher bluetoothProfileWatcher;
    private volatile IOComponents ioComponents;
    private final IOComponentsMessageContextProvider ioComponentsMessageContextProvider;
    private final IOComponentsSupplier ioComponentsSupplier;
    private final JsonConverter jsonConverter;
    private final TrustedStatesMessageContextProvider trustedStatesMessageContextProvider;
    private final TrustedStatesSupplier trustedStatesSupplier;
    public static final String IOCOMPONENTS_NAME_SPACE = "Alexa.IOComponents";
    public static final String TRUSTED_STATES_NAME = "TrustedStates";
    private static final MessageHeader TRUSTED_STATES_HEADER = MessageHeader.create(IOCOMPONENTS_NAME_SPACE, TRUSTED_STATES_NAME);
    public static final String IOCOMPONENTS_NAME = "IOComponentStates";
    private static final MessageHeader IOCOMPONENTS_HEADER = MessageHeader.create(IOCOMPONENTS_NAME_SPACE, IOCOMPONENTS_NAME);

    @VisibleForTesting
    /* loaded from: classes6.dex */
    final class AccessoryMessageContextProvider implements MessageContextProvider {
        AccessoryMessageContextProvider() {
        }

        @Override // com.amazon.alexa.accessory.speechapi.context.MessageContextProvider
        public MessageContext getMessageContext() {
            MessageContext messageContext = new MessageContext(AlexaServicesContextProvider.getMessageHeader(), AlexaServicesContextProvider.this.getMessagePayload());
            Logger.d("%s getAccessoryMessageContext with header: %s and payload: %s", AlexaServicesContextProvider.TAG, messageContext.getMessageHeader(), messageContext.getMessagePayload());
            return messageContext;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static final class ActiveAccessories {
        private final List<ActiveAccessory> activeAccessories;

        public ActiveAccessories(List<ActiveAccessory> list) {
            this.activeAccessories = list;
        }

        public List<ActiveAccessory> getActiveAccessories() {
            return this.activeAccessories;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static final class ActiveAccessory {
        private final Debug debug;
        private final String role;

        public Debug getDebug() {
            return this.debug;
        }

        public String getRole() {
            return this.role;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public static final class Debug {
            private final String firmwareVersion;
            private final String name;
            private final String token;

            public String getFirmwareVersion() {
                return this.firmwareVersion;
            }

            public String getName() {
                return this.name;
            }

            public String getToken() {
                return this.token;
            }

            private Debug(String str, String str2) {
                this.token = str;
                this.name = str2;
                this.firmwareVersion = null;
            }

            private Debug(String str, String str2, String str3) {
                this.token = str;
                this.name = str2;
                this.firmwareVersion = str3;
            }
        }

        private ActiveAccessory(String str, Debug debug) {
            this.role = str;
            this.debug = debug;
        }
    }

    @VisibleForTesting
    /* loaded from: classes6.dex */
    final class IOComponentsMessageContextProvider implements MessageContextProvider {
        IOComponentsMessageContextProvider() {
        }

        @Override // com.amazon.alexa.accessory.speechapi.context.MessageContextProvider
        public MessageContext getMessageContext() {
            if (AlexaServicesContextProvider.this.ioComponents.getActiveIOComponentList().isEmpty()) {
                AlexaServicesContextProvider alexaServicesContextProvider = AlexaServicesContextProvider.this;
                alexaServicesContextProvider.ioComponents = alexaServicesContextProvider.ioComponentsSupplier.getIOComponentsCachedNoUtterance();
            }
            try {
                if (Logger.shouldLog(Logger.Level.DEBUG)) {
                    Logger.d("%s getIOComponentsMessageContext: %s", AlexaServicesContextProvider.TAG, AlexaServicesContextProvider.this.ioComponents.toJsonObject().toString(4));
                }
                return new MessageContext(AlexaServicesContextProvider.IOCOMPONENTS_HEADER, new MessagePayload(AlexaServicesContextProvider.this.ioComponents.toJsonObject().toString()));
            } catch (JSONException e) {
                Logger.e("Error while converting IOComponents to Json", e);
                return null;
            }
        }
    }

    @VisibleForTesting
    /* loaded from: classes6.dex */
    final class TrustedStatesMessageContextProvider implements MessageContextProvider {
        TrustedStatesMessageContextProvider() {
        }

        @Override // com.amazon.alexa.accessory.speechapi.context.MessageContextProvider
        public MessageContext getMessageContext() {
            try {
                JSONObject jsonObject = AlexaServicesContextProvider.this.trustedStatesSupplier.getContext().toJsonObject();
                if (Logger.shouldLog(Logger.Level.DEBUG)) {
                    Logger.d("%s getTrustedStatesMessageContext: %s", AlexaServicesContextProvider.TAG, jsonObject.toString(4));
                }
                return new MessageContext(AlexaServicesContextProvider.TRUSTED_STATES_HEADER, new MessagePayload(jsonObject.toString()));
            } catch (JSONException e) {
                Logger.e("Error while converting trustedState to Json", e);
                return null;
            }
        }
    }

    public AlexaServicesContextProvider(JsonConverter jsonConverter, BluetoothProfileWatcher bluetoothProfileWatcher, IOComponentsSupplier iOComponentsSupplier, TrustedStatesSupplier trustedStatesSupplier, AlexaConnection alexaConnection) {
        Preconditions.notNull(jsonConverter, "jsonConverter");
        Preconditions.notNull(bluetoothProfileWatcher, "bluetoothWatcher");
        Preconditions.notNull(iOComponentsSupplier, "ioComponentsSupplier");
        Preconditions.notNull(trustedStatesSupplier, "trustedStatesSupplier");
        Preconditions.notNull(alexaConnection, "alexaConnection");
        this.jsonConverter = jsonConverter;
        this.bluetoothProfileWatcher = bluetoothProfileWatcher;
        this.ioComponentsSupplier = iOComponentsSupplier;
        this.trustedStatesSupplier = trustedStatesSupplier;
        this.ioComponents = IOComponents.EMPTY;
        this.alexaConnection = alexaConnection;
        this.ioComponentsMessageContextProvider = new IOComponentsMessageContextProvider();
        this.trustedStatesMessageContextProvider = new TrustedStatesMessageContextProvider();
        this.accessoryMessageContextProvider = new AccessoryMessageContextProvider();
    }

    private static ActiveAccessory createActiveSpeakerAccessory(BluetoothDevice bluetoothDevice) {
        return new ActiveAccessory(AccessoryRole.SPEAKER.name(), new ActiveAccessory.Debug(bluetoothDevice.getAddress(), bluetoothDevice.getName()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static MessageHeader getMessageHeader() {
        return MessageHeader.create(ACCESSORY_NAME_SPACE, ACCESSORY_NAME, UUID.randomUUID().toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MessagePayload getMessagePayload() {
        ArrayList arrayList = new ArrayList();
        ActiveAccessory activeAccessory = this.activeMicrophoneAccessory;
        if (activeAccessory != null) {
            arrayList.add(activeAccessory);
        }
        List<BluetoothDevice> activeDevices = this.bluetoothProfileWatcher.getActiveDevices();
        if (activeDevices.size() == 0) {
            Logger.d("%s No a2dp devices connected", TAG);
        } else if (activeDevices.size() == 1) {
            arrayList.add(createActiveSpeakerAccessory(activeDevices.get(0)));
        } else {
            Logger.e("%s More than 1 a2dp devices connected (%d). This directly breaks the Android contract as defined in the official documentation", TAG, Integer.valueOf(activeDevices.size()));
        }
        return new MessagePayload(this.jsonConverter.toJson(new ActiveAccessories(arrayList)));
    }

    @Override // com.amazon.alexa.accessory.speechapi.context.AccessoryContextProvider
    public void activate() {
        Logger.d("%s activating...", TAG);
        this.bluetoothProfileWatcher.ensureActive(2);
        this.ioComponentsSupplier.activate();
        this.trustedStatesSupplier.activate();
        this.alexaConnection.registerMessageContextProvider(this.ioComponentsMessageContextProvider);
        this.alexaConnection.registerMessageContextProvider(this.trustedStatesMessageContextProvider);
        this.alexaConnection.registerMessageContextProvider(this.accessoryMessageContextProvider);
    }

    @Override // com.amazon.alexa.accessory.speechapi.context.AccessoryContextProvider
    public void clearActiveAccessoryMicrophone() {
        Logger.d("%s clearActiveAccessoryMicrophone", TAG);
        this.activeMicrophoneAccessory = null;
        this.ioComponents = IOComponents.EMPTY;
    }

    @Override // com.amazon.alexa.accessory.speechapi.context.AccessoryContextProvider
    public void deactivate() {
        Logger.d("%s deactivating...", TAG);
        this.bluetoothProfileWatcher.deactivate();
        this.ioComponentsSupplier.deactivate();
        this.trustedStatesSupplier.deactivate();
        this.activeMicrophoneAccessory = null;
        this.ioComponents = IOComponents.EMPTY;
        this.alexaConnection.deregisterMessageContextProvider(this.ioComponentsMessageContextProvider);
        this.alexaConnection.deregisterMessageContextProvider(this.trustedStatesMessageContextProvider);
        this.alexaConnection.deregisterMessageContextProvider(this.accessoryMessageContextProvider);
    }

    @Override // com.amazon.alexa.accessory.speechapi.context.AccessoryContextProvider
    public void setActiveAccessoryMicrophone(String str, String str2, String str3, String str4) {
        Preconditions.mainThread();
        Logger.d("%s setActiveAccessoryMicrophone: %s, %s, %s, %s", TAG, str, str2, str3, str4);
        this.bluetoothProfileWatcher.ensureActive(2);
        this.activeMicrophoneAccessory = new ActiveAccessory(AccessoryRole.MICROPHONE.name(), new ActiveAccessory.Debug(str2, str, str3));
        this.ioComponents = this.ioComponentsSupplier.getIOComponentsCached(str4);
        Logger.d("%s IOComponents from cache set ioComponents to %s", TAG, this.ioComponents);
    }
}
