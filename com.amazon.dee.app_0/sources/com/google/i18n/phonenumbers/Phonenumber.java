package com.google.i18n.phonenumbers;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes3.dex */
public final class Phonenumber {

    /* loaded from: classes3.dex */
    public static class PhoneNumber implements Serializable {
        private static final long serialVersionUID = 1;
        private boolean hasCountryCode;
        private boolean hasCountryCodeSource;
        private boolean hasExtension;
        private boolean hasItalianLeadingZero;
        private boolean hasNationalNumber;
        private boolean hasNumberOfLeadingZeros;
        private boolean hasPreferredDomesticCarrierCode;
        private boolean hasRawInput;
        private int countryCode_ = 0;
        private long nationalNumber_ = 0;
        private String extension_ = "";
        private boolean italianLeadingZero_ = false;
        private int numberOfLeadingZeros_ = 1;
        private String rawInput_ = "";
        private String preferredDomesticCarrierCode_ = "";
        private CountryCodeSource countryCodeSource_ = CountryCodeSource.FROM_NUMBER_WITH_PLUS_SIGN;

        /* loaded from: classes3.dex */
        public enum CountryCodeSource {
            FROM_NUMBER_WITH_PLUS_SIGN,
            FROM_NUMBER_WITH_IDD,
            FROM_NUMBER_WITHOUT_PLUS_SIGN,
            FROM_DEFAULT_COUNTRY
        }

        public final PhoneNumber clear() {
            clearCountryCode();
            clearNationalNumber();
            clearExtension();
            clearItalianLeadingZero();
            clearNumberOfLeadingZeros();
            clearRawInput();
            clearCountryCodeSource();
            clearPreferredDomesticCarrierCode();
            return this;
        }

        public PhoneNumber clearCountryCode() {
            this.hasCountryCode = false;
            this.countryCode_ = 0;
            return this;
        }

        public PhoneNumber clearCountryCodeSource() {
            this.hasCountryCodeSource = false;
            this.countryCodeSource_ = CountryCodeSource.FROM_NUMBER_WITH_PLUS_SIGN;
            return this;
        }

        public PhoneNumber clearExtension() {
            this.hasExtension = false;
            this.extension_ = "";
            return this;
        }

        public PhoneNumber clearItalianLeadingZero() {
            this.hasItalianLeadingZero = false;
            this.italianLeadingZero_ = false;
            return this;
        }

        public PhoneNumber clearNationalNumber() {
            this.hasNationalNumber = false;
            this.nationalNumber_ = 0L;
            return this;
        }

        public PhoneNumber clearNumberOfLeadingZeros() {
            this.hasNumberOfLeadingZeros = false;
            this.numberOfLeadingZeros_ = 1;
            return this;
        }

        public PhoneNumber clearPreferredDomesticCarrierCode() {
            this.hasPreferredDomesticCarrierCode = false;
            this.preferredDomesticCarrierCode_ = "";
            return this;
        }

        public PhoneNumber clearRawInput() {
            this.hasRawInput = false;
            this.rawInput_ = "";
            return this;
        }

        public boolean equals(Object obj) {
            return (obj instanceof PhoneNumber) && exactlySameAs((PhoneNumber) obj);
        }

        public boolean exactlySameAs(PhoneNumber phoneNumber) {
            if (phoneNumber == null) {
                return false;
            }
            if (this == phoneNumber) {
                return true;
            }
            return this.countryCode_ == phoneNumber.countryCode_ && this.nationalNumber_ == phoneNumber.nationalNumber_ && this.extension_.equals(phoneNumber.extension_) && this.italianLeadingZero_ == phoneNumber.italianLeadingZero_ && this.numberOfLeadingZeros_ == phoneNumber.numberOfLeadingZeros_ && this.rawInput_.equals(phoneNumber.rawInput_) && this.countryCodeSource_ == phoneNumber.countryCodeSource_ && this.preferredDomesticCarrierCode_.equals(phoneNumber.preferredDomesticCarrierCode_) && hasPreferredDomesticCarrierCode() == phoneNumber.hasPreferredDomesticCarrierCode();
        }

        public int getCountryCode() {
            return this.countryCode_;
        }

        public CountryCodeSource getCountryCodeSource() {
            return this.countryCodeSource_;
        }

        public String getExtension() {
            return this.extension_;
        }

        public long getNationalNumber() {
            return this.nationalNumber_;
        }

        public int getNumberOfLeadingZeros() {
            return this.numberOfLeadingZeros_;
        }

        public String getPreferredDomesticCarrierCode() {
            return this.preferredDomesticCarrierCode_;
        }

        public String getRawInput() {
            return this.rawInput_;
        }

        public boolean hasCountryCode() {
            return this.hasCountryCode;
        }

        public boolean hasCountryCodeSource() {
            return this.hasCountryCodeSource;
        }

        public boolean hasExtension() {
            return this.hasExtension;
        }

        public boolean hasItalianLeadingZero() {
            return this.hasItalianLeadingZero;
        }

        public boolean hasNationalNumber() {
            return this.hasNationalNumber;
        }

        public boolean hasNumberOfLeadingZeros() {
            return this.hasNumberOfLeadingZeros;
        }

        public boolean hasPreferredDomesticCarrierCode() {
            return this.hasPreferredDomesticCarrierCode;
        }

        public boolean hasRawInput() {
            return this.hasRawInput;
        }

        public int hashCode() {
            int hashCode = (getExtension().hashCode() + ((Long.valueOf(getNationalNumber()).hashCode() + ((getCountryCode() + 2173) * 53)) * 53)) * 53;
            int i = 1231;
            int i2 = isItalianLeadingZero() ? 1231 : 1237;
            int numberOfLeadingZeros = getNumberOfLeadingZeros();
            int hashCode2 = (getPreferredDomesticCarrierCode().hashCode() + ((getCountryCodeSource().hashCode() + ((getRawInput().hashCode() + ((numberOfLeadingZeros + ((hashCode + i2) * 53)) * 53)) * 53)) * 53)) * 53;
            if (!hasPreferredDomesticCarrierCode()) {
                i = 1237;
            }
            return hashCode2 + i;
        }

        public boolean isItalianLeadingZero() {
            return this.italianLeadingZero_;
        }

        public PhoneNumber mergeFrom(PhoneNumber phoneNumber) {
            if (phoneNumber.hasCountryCode()) {
                setCountryCode(phoneNumber.getCountryCode());
            }
            if (phoneNumber.hasNationalNumber()) {
                setNationalNumber(phoneNumber.getNationalNumber());
            }
            if (phoneNumber.hasExtension()) {
                setExtension(phoneNumber.getExtension());
            }
            if (phoneNumber.hasItalianLeadingZero()) {
                setItalianLeadingZero(phoneNumber.isItalianLeadingZero());
            }
            if (phoneNumber.hasNumberOfLeadingZeros()) {
                setNumberOfLeadingZeros(phoneNumber.getNumberOfLeadingZeros());
            }
            if (phoneNumber.hasRawInput()) {
                setRawInput(phoneNumber.getRawInput());
            }
            if (phoneNumber.hasCountryCodeSource()) {
                setCountryCodeSource(phoneNumber.getCountryCodeSource());
            }
            if (phoneNumber.hasPreferredDomesticCarrierCode()) {
                setPreferredDomesticCarrierCode(phoneNumber.getPreferredDomesticCarrierCode());
            }
            return this;
        }

        public PhoneNumber setCountryCode(int i) {
            this.hasCountryCode = true;
            this.countryCode_ = i;
            return this;
        }

        public PhoneNumber setCountryCodeSource(CountryCodeSource countryCodeSource) {
            if (countryCodeSource != null) {
                this.hasCountryCodeSource = true;
                this.countryCodeSource_ = countryCodeSource;
                return this;
            }
            throw new NullPointerException();
        }

        public PhoneNumber setExtension(String str) {
            if (str != null) {
                this.hasExtension = true;
                this.extension_ = str;
                return this;
            }
            throw new NullPointerException();
        }

        public PhoneNumber setItalianLeadingZero(boolean z) {
            this.hasItalianLeadingZero = true;
            this.italianLeadingZero_ = z;
            return this;
        }

        public PhoneNumber setNationalNumber(long j) {
            this.hasNationalNumber = true;
            this.nationalNumber_ = j;
            return this;
        }

        public PhoneNumber setNumberOfLeadingZeros(int i) {
            this.hasNumberOfLeadingZeros = true;
            this.numberOfLeadingZeros_ = i;
            return this;
        }

        public PhoneNumber setPreferredDomesticCarrierCode(String str) {
            if (str != null) {
                this.hasPreferredDomesticCarrierCode = true;
                this.preferredDomesticCarrierCode_ = str;
                return this;
            }
            throw new NullPointerException();
        }

        public PhoneNumber setRawInput(String str) {
            if (str != null) {
                this.hasRawInput = true;
                this.rawInput_ = str;
                return this;
            }
            throw new NullPointerException();
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Country Code: ");
            outline107.append(this.countryCode_);
            outline107.append(" National Number: ");
            outline107.append(this.nationalNumber_);
            if (hasItalianLeadingZero() && isItalianLeadingZero()) {
                outline107.append(" Leading Zero(s): true");
            }
            if (hasNumberOfLeadingZeros()) {
                outline107.append(" Number of leading zeros: ");
                outline107.append(this.numberOfLeadingZeros_);
            }
            if (hasExtension()) {
                outline107.append(" Extension: ");
                outline107.append(this.extension_);
            }
            if (hasCountryCodeSource()) {
                outline107.append(" Country Code Source: ");
                outline107.append(this.countryCodeSource_);
            }
            if (hasPreferredDomesticCarrierCode()) {
                outline107.append(" Preferred Domestic Carrier Code: ");
                outline107.append(this.preferredDomesticCarrierCode_);
            }
            return outline107.toString();
        }
    }

    private Phonenumber() {
    }
}
