package com.example.qllh.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ExaminationStatus {
    CHUA_KHAM("Chưa khám"),
    DANG_KHAM("Đang khám"),
    DA_CO_CHUAN_DOAN("Đã có chẩn đoán"),
    DANG_XET_NGHIEM("Đang xét nghiệm"),
    DA_HOAN_THANH_KHAM("Đã hoàn thành khám"),
    HUY_LICH_KHAM("Hủy lịch khám");

    private final String status;

    ExaminationStatus(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }

    @JsonCreator
    public static ExaminationStatus fromValue(String value) {
        for (ExaminationStatus status : ExaminationStatus.values()) {
            if (status.getStatus().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + value);
    }
}
