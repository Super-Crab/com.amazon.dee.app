package com.amazon.comms.models.gui;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes11.dex */
public class DropInInfo implements Serializable {
    private String dropInTransition;
    private Double frostedTransitionTime;
    private Double totalFrostTime;

    /* loaded from: classes11.dex */
    public static class DropInInfoBuilder {
        private String dropInTransition;
        private Double frostedTransitionTime;
        private Double totalFrostTime;

        DropInInfoBuilder() {
        }

        public DropInInfo build() {
            return new DropInInfo(this.dropInTransition, this.frostedTransitionTime, this.totalFrostTime);
        }

        public DropInInfoBuilder dropInTransition(String str) {
            this.dropInTransition = str;
            return this;
        }

        public DropInInfoBuilder frostedTransitionTime(Double d) {
            this.frostedTransitionTime = d;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DropInInfo.DropInInfoBuilder(dropInTransition=");
            outline107.append(this.dropInTransition);
            outline107.append(", frostedTransitionTime=");
            outline107.append(this.frostedTransitionTime);
            outline107.append(", totalFrostTime=");
            outline107.append(this.totalFrostTime);
            outline107.append(")");
            return outline107.toString();
        }

        public DropInInfoBuilder totalFrostTime(Double d) {
            this.totalFrostTime = d;
            return this;
        }
    }

    DropInInfo(String str, Double d, Double d2) {
        this.dropInTransition = str;
        this.frostedTransitionTime = d;
        this.totalFrostTime = d2;
    }

    public static DropInInfoBuilder builder() {
        return new DropInInfoBuilder();
    }

    public String getDropInTransition() {
        return this.dropInTransition;
    }

    public Double getFrostedTransitionTime() {
        return this.frostedTransitionTime;
    }

    public Double getTotalFrostTime() {
        return this.totalFrostTime;
    }
}
