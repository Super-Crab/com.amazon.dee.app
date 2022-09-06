package com.amazon.deecomms.oobe;

import com.amazon.deecomms.common.util.Utils;
/* loaded from: classes12.dex */
public class OOBEStateMachine {
    private STATE currentState;

    /* loaded from: classes12.dex */
    public enum STATE {
        INTRODUCTION(0) { // from class: com.amazon.deecomms.oobe.OOBEStateMachine.STATE.1
            @Override // com.amazon.deecomms.oobe.OOBEStateMachine.STATE
            STATE next(Person person) {
                if (person.isFriendsAndFamily) {
                    return STATE.FNF_TOU;
                }
                return STATE.USER_SELECTION;
            }

            @Override // com.amazon.deecomms.oobe.OOBEStateMachine.STATE
            STATE prev() {
                return this;
            }
        },
        FNF_TOU(1) { // from class: com.amazon.deecomms.oobe.OOBEStateMachine.STATE.2
        },
        USER_SELECTION(2) { // from class: com.amazon.deecomms.oobe.OOBEStateMachine.STATE.3
            @Override // com.amazon.deecomms.oobe.OOBEStateMachine.STATE
            STATE next(Person person) {
                if (person.isCommsProvisioned) {
                    return STATE.PERMISSIONS;
                }
                if (person.isNewUser()) {
                    return STATE.NEW_PROFILE;
                }
                return next();
            }
        },
        NAME_CONFIRMATION(3) { // from class: com.amazon.deecomms.oobe.OOBEStateMachine.STATE.4
            @Override // com.amazon.deecomms.oobe.OOBEStateMachine.STATE
            STATE next() {
                return STATE.PERMISSIONS;
            }
        },
        NEW_PROFILE(4) { // from class: com.amazon.deecomms.oobe.OOBEStateMachine.STATE.5
            @Override // com.amazon.deecomms.oobe.OOBEStateMachine.STATE
            STATE prev() {
                return STATE.USER_SELECTION;
            }
        },
        PERMISSIONS(5) { // from class: com.amazon.deecomms.oobe.OOBEStateMachine.STATE.6
            @Override // com.amazon.deecomms.oobe.OOBEStateMachine.STATE
            STATE next(Person person) {
                return STATE.ACCESSORY_PERMISSIONS;
            }
        },
        ACCESSORY_PERMISSIONS(6) { // from class: com.amazon.deecomms.oobe.OOBEStateMachine.STATE.7
            @Override // com.amazon.deecomms.oobe.OOBEStateMachine.STATE
            STATE next(Person person) {
                if (person.isCommsProvisioned) {
                    return STATE.FINISH;
                }
                if (!Utils.isNullOrEmpty(person.phoneNumber)) {
                    return STATE.PHONE_VERIFIED;
                }
                return next();
            }
        },
        MOBILE_VERIFICATION(7) { // from class: com.amazon.deecomms.oobe.OOBEStateMachine.STATE.8
            @Override // com.amazon.deecomms.oobe.OOBEStateMachine.STATE
            STATE next(Person person) {
                return STATE.FINISH;
            }
        },
        PHONE_VERIFIED(8) { // from class: com.amazon.deecomms.oobe.OOBEStateMachine.STATE.9
            @Override // com.amazon.deecomms.oobe.OOBEStateMachine.STATE
            STATE next(Person person) {
                return STATE.FINISH;
            }
        },
        FINISH(9) { // from class: com.amazon.deecomms.oobe.OOBEStateMachine.STATE.10
            @Override // com.amazon.deecomms.oobe.OOBEStateMachine.STATE
            STATE next() {
                return this;
            }
        };
        
        final int order;

        STATE next() {
            return values()[this.order + 1];
        }

        STATE prev() {
            return values()[this.order - 1];
        }

        STATE(int i) {
            this.order = i;
        }

        STATE next(Person person) {
            return next();
        }
    }

    public OOBEStateMachine() {
        this.currentState = STATE.INTRODUCTION;
    }

    public STATE getState() {
        return this.currentState;
    }

    public void next() {
        this.currentState = this.currentState.next();
    }

    public void prev() {
        this.currentState = this.currentState.prev();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCurrentState(STATE state) {
        this.currentState = state;
    }

    public void next(Person person) {
        this.currentState = this.currentState.next(person);
    }

    public OOBEStateMachine(int i) {
        STATE state = STATE.INTRODUCTION;
        if (i < state.order) {
            this.currentState = state;
            return;
        }
        STATE state2 = STATE.FINISH;
        if (i > state2.order) {
            this.currentState = state2;
        } else {
            this.currentState = STATE.values()[i];
        }
    }
}
