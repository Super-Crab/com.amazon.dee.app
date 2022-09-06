package chip.devicecontroller;
/* loaded from: classes.dex */
public enum ChipCommandType {
    OFF(0),
    ON(1),
    TOGGLE(2),
    LEVEL(3);
    
    private int value;

    ChipCommandType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
