package com.amazon.comms.models.gui;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes11.dex */
public class Setting {
    private List<Capability> capabilities;

    /* loaded from: classes11.dex */
    public static class SettingBuilder {
        private List<Capability> capabilities;

        SettingBuilder() {
        }

        public Setting build() {
            return new Setting(this.capabilities);
        }

        public SettingBuilder capabilities(List<Capability> list) {
            this.capabilities = list;
            return this;
        }

        public String toString() {
            return GeneratedOutlineSupport1.outline95(GeneratedOutlineSupport1.outline107("Setting.SettingBuilder(capabilities="), this.capabilities, ")");
        }
    }

    public Setting() {
    }

    public static SettingBuilder builder() {
        return new SettingBuilder();
    }

    public List<Capability> getCapabilities() {
        return this.capabilities;
    }

    public void setCapabilities(List<Capability> list) {
        this.capabilities = list;
    }

    private Setting(List<Capability> list) {
        this.capabilities = list;
    }
}
