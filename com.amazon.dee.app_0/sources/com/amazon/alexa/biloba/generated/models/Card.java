package com.amazon.alexa.biloba.generated.models;

import com.amazon.alexa.biloba.membership.PermissionConstants;
import com.amazon.alexa.biloba.model.Action;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.dee.app.services.bluetooth.DefaultBluetoothService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
/* loaded from: classes6.dex */
public class Card {
    @SerializedName("id")
    private String id = null;
    @SerializedName("localizedTitle")
    private String localizedTitle = null;
    @SerializedName("localizedDescription")
    private String localizedDescription = null;
    @SerializedName("category")
    private CategoryEnum category = null;
    @SerializedName("type")
    private TypeEnum type = null;
    @SerializedName(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ACTIONS)
    private List<Action> actions = new ArrayList();
    @SerializedName("iconUrl")
    private String iconUrl = null;
    @SerializedName("iconAltText")
    private String iconAltText = null;
    @SerializedName(DefaultBluetoothService.AUDIO_DEVICE_NAME)
    private String deviceName = null;
    @SerializedName("localizedDateTime")
    private LocalizedDateTime localizedDateTime = null;
    @SerializedName("utcDateTime")
    private Date utcDateTime = null;

    @JsonAdapter(Adapter.class)
    /* loaded from: classes6.dex */
    public enum CategoryEnum {
        NOTIFICATION_ALERT("NOTIFICATION_ALERT"),
        FEATURE_DISCOVERY_TIP("FEATURE_DISCOVERY_TIP");
        
        private String value;

        /* loaded from: classes6.dex */
        public static class Adapter extends TypeAdapter<CategoryEnum> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public CategoryEnum mo8353read(JsonReader jsonReader) throws IOException {
                return CategoryEnum.fromValue(String.valueOf(jsonReader.nextString()));
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, CategoryEnum categoryEnum) throws IOException {
                jsonWriter.value(categoryEnum.getValue());
            }
        }

        CategoryEnum(String str) {
            this.value = str;
        }

        public static CategoryEnum fromValue(String str) {
            CategoryEnum[] values;
            for (CategoryEnum categoryEnum : values()) {
                if (String.valueOf(categoryEnum.value).equals(str)) {
                    return categoryEnum;
                }
            }
            return null;
        }

        public String getValue() {
            return this.value;
        }

        @Override // java.lang.Enum
        public String toString() {
            return String.valueOf(this.value);
        }
    }

    @JsonAdapter(Adapter.class)
    /* loaded from: classes6.dex */
    public enum TypeEnum {
        EMERGENCY_DETECTED("EMERGENCY_DETECTED"),
        VERIFIED_FALL("VERIFIED_FALL"),
        VERIFIED_NO_FALL("VERIFIED_NO_FALL"),
        UNVERIFIED_FALL("UNVERIFIED_FALL"),
        EMERGENCY_HELPLINE_CALL_STARTED("EMERGENCY_HELPLINE_CALL_STARTED"),
        EMERGENCY_HELPLINE_CALL_ENDED("EMERGENCY_HELPLINE_CALL_ENDED"),
        NO_ACTIVITY_DETECTED("NO_ACTIVITY_DETECTED"),
        FIRST_ACTIVITY_OF_THE_DAY("FIRST_ACTIVITY_OF_THE_DAY"),
        INVITE_CARE_GIVER("INVITE_CARE_GIVER"),
        CARE_PLUS_UPSELL_FOR_FD_SENSORS("CARE_PLUS_UPSELL_FOR_FD_SENSORS"),
        EC_SETUP_NEEDED("EC_SETUP_NEEDED"),
        EC_AND_COMMS_SETUP_DONE("EC_AND_COMMS_SETUP_DONE"),
        FINISH_CARE_PLUS_SETUP("FINISH_CARE_PLUS_SETUP"),
        FINISH_COMMS_SETUP("FINISH_COMMS_SETUP"),
        TEST_EMERGENCY_HELPLINE("TEST_EMERGENCY_HELPLINE"),
        ENABLE_REMOTE_MANAGEMENT("ENABLE_REMOTE_MANAGEMENT"),
        REMOTE_MANAGEMENT(PermissionConstants.REMOTE_MANAGEMENT),
        LEARN_ABOUT_NOTIFICATION("LEARN_ABOUT_NOTIFICATION"),
        LEARN_ABOUT_ACTIVITY_FEED("LEARN_ABOUT_ACTIVITY_FEED"),
        DEVICE_SETUP_NEEDED("DEVICE_SETUP_NEEDED"),
        LEARN_ABOUT_SEND_A_MESSAGE("LEARN_ABOUT_SEND_A_MESSAGE"),
        LEARN_ABOUT_SHARE_A_PHOTO("LEARN_ABOUT_SHARE_A_PHOTO"),
        LEARN_ABOUT_TIMEZONE("LEARN_ABOUT_TIMEZONE"),
        CARE_PLUS_UPSELL_DO_MORE("CARE_PLUS_UPSELL_DO_MORE");
        
        private String value;

        /* loaded from: classes6.dex */
        public static class Adapter extends TypeAdapter<TypeEnum> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public TypeEnum mo8353read(JsonReader jsonReader) throws IOException {
                return TypeEnum.fromValue(String.valueOf(jsonReader.nextString()));
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, TypeEnum typeEnum) throws IOException {
                jsonWriter.value(typeEnum.getValue());
            }
        }

        TypeEnum(String str) {
            this.value = str;
        }

        public static TypeEnum fromValue(String str) {
            TypeEnum[] values;
            for (TypeEnum typeEnum : values()) {
                if (String.valueOf(typeEnum.value).equals(str)) {
                    return typeEnum;
                }
            }
            return null;
        }

        public String getValue() {
            return this.value;
        }

        @Override // java.lang.Enum
        public String toString() {
            return String.valueOf(this.value);
        }
    }

    private String toIndentedString(Object obj) {
        return obj == null ? "null" : obj.toString().replace("\n", "\n    ");
    }

    public Card actions(List<Action> list) {
        this.actions = list;
        return this;
    }

    public Card addActionsItem(Action action) {
        this.actions.add(action);
        return this;
    }

    public Card category(CategoryEnum categoryEnum) {
        this.category = categoryEnum;
        return this;
    }

    public Card deviceName(String str) {
        this.deviceName = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Card.class != obj.getClass()) {
            return false;
        }
        Card card = (Card) obj;
        return Objects.equals(this.id, card.id) && Objects.equals(this.localizedTitle, card.localizedTitle) && Objects.equals(this.localizedDescription, card.localizedDescription) && Objects.equals(this.category, card.category) && Objects.equals(this.type, card.type) && Objects.equals(this.actions, card.actions) && Objects.equals(this.iconUrl, card.iconUrl) && Objects.equals(this.iconAltText, card.iconAltText) && Objects.equals(this.deviceName, card.deviceName) && Objects.equals(this.localizedDateTime, card.localizedDateTime) && Objects.equals(this.utcDateTime, card.utcDateTime);
    }

    public List<Action> getActions() {
        return this.actions;
    }

    public CategoryEnum getCategory() {
        return this.category;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public String getIconAltText() {
        return this.iconAltText;
    }

    public String getIconUrl() {
        return this.iconUrl;
    }

    public String getId() {
        return this.id;
    }

    public LocalizedDateTime getLocalizedDateTime() {
        return this.localizedDateTime;
    }

    public String getLocalizedDescription() {
        return this.localizedDescription;
    }

    public String getLocalizedTitle() {
        return this.localizedTitle;
    }

    public TypeEnum getType() {
        return this.type;
    }

    public Date getUtcDateTime() {
        return this.utcDateTime;
    }

    public int hashCode() {
        return Objects.hash(this.id, this.localizedTitle, this.localizedDescription, this.category, this.type, this.actions, this.iconUrl, this.iconAltText, this.deviceName, this.localizedDateTime, this.utcDateTime);
    }

    public Card iconAltText(String str) {
        this.iconAltText = str;
        return this;
    }

    public Card iconUrl(String str) {
        this.iconUrl = str;
        return this;
    }

    public Card id(String str) {
        this.id = str;
        return this;
    }

    public Card localizedDateTime(LocalizedDateTime localizedDateTime) {
        this.localizedDateTime = localizedDateTime;
        return this;
    }

    public Card localizedDescription(String str) {
        this.localizedDescription = str;
        return this;
    }

    public Card localizedTitle(String str) {
        this.localizedTitle = str;
        return this;
    }

    public void setActions(List<Action> list) {
        this.actions = list;
    }

    public void setCategory(CategoryEnum categoryEnum) {
        this.category = categoryEnum;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public void setIconAltText(String str) {
        this.iconAltText = str;
    }

    public void setIconUrl(String str) {
        this.iconUrl = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setLocalizedDateTime(LocalizedDateTime localizedDateTime) {
        this.localizedDateTime = localizedDateTime;
    }

    public void setLocalizedDescription(String str) {
        this.localizedDescription = str;
    }

    public void setLocalizedTitle(String str) {
        this.localizedTitle = str;
    }

    public void setType(TypeEnum typeEnum) {
        this.type = typeEnum;
    }

    public void setUtcDateTime(Date date) {
        this.utcDateTime = date;
    }

    public String toString() {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("class Card {\n", "    id: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.id), "\n", "    localizedTitle: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.localizedTitle), "\n", "    localizedDescription: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.localizedDescription), "\n", "    category: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.category), "\n", "    type: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.type), "\n", "    actions: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.actions), "\n", "    iconUrl: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.iconUrl), "\n", "    iconAltText: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.iconAltText), "\n", "    deviceName: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.deviceName), "\n", "    localizedDateTime: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.localizedDateTime), "\n", "    utcDateTime: ");
        return GeneratedOutlineSupport1.outline92(outline113, toIndentedString(this.utcDateTime), "\n", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    public Card type(TypeEnum typeEnum) {
        this.type = typeEnum;
        return this;
    }

    public Card utcDateTime(Date date) {
        this.utcDateTime = date;
        return this;
    }
}
