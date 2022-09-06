package chip.setuppayload;

import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes.dex */
public class SetupPayloadParser {
    private static final Logger LOGGER = Logger.getLogger(SetupPayloadParser.class.getSimpleName());

    /* loaded from: classes.dex */
    public static class InvalidEntryCodeFormatException extends Exception {
        private static final long serialVersionUID = 1;

        public InvalidEntryCodeFormatException(String str) {
            super(String.format("Invalid format for entry code string: %s", str), null);
        }
    }

    /* loaded from: classes.dex */
    public static class UnrecognizedQrCodeException extends Exception {
        private static final long serialVersionUID = 1;

        public UnrecognizedQrCodeException(String str) {
            super(String.format("Invalid QR code string: %s", str), null);
        }
    }

    static {
        try {
            System.loadLibrary("SetupPayloadParser");
        } catch (UnsatisfiedLinkError e) {
            LOGGER.log(Level.SEVERE, "Cannot load library.", (Throwable) e);
        }
    }

    private native SetupPayload fetchPayloadFromManualEntryCode(String str) throws InvalidEntryCodeFormatException;

    private native SetupPayload fetchPayloadFromQrCode(String str) throws UnrecognizedQrCodeException;

    public SetupPayload parseManualEntryCode(String str) throws InvalidEntryCodeFormatException {
        return fetchPayloadFromManualEntryCode(str);
    }

    public SetupPayload parseQrCode(String str) throws UnrecognizedQrCodeException {
        return fetchPayloadFromQrCode(str);
    }
}
