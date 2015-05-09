package model;

import managers.ApplicationManager;

public enum Day {
    MON,
    TUE,
    WED,
    THU,
    FRI,
    SAT,
    SUN;

    @Override
    public String toString() {
        switch (this) {
            case MON:
                return ApplicationManager.Strings.DAY_MON;
            case TUE:
                return ApplicationManager.Strings.DAY_TUE;
            case WED:
                return ApplicationManager.Strings.DAY_WED;
            case THU:
                return ApplicationManager.Strings.DAY_THU;
            case FRI:
                return ApplicationManager.Strings.DAY_FRI;
            case SAT:
                return ApplicationManager.Strings.DAY_SAT;
            case SUN:
                return ApplicationManager.Strings.DAY_SUN;
            default:
                return ApplicationManager.Strings.DAY_UNDEFINED;
        }
    }
}
