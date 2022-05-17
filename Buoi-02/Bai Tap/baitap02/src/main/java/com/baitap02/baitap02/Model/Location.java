package com.baitap02.baitap02.Model;

public enum Location {
    HANOI("Hà Nội"),
    HAIPHONG("Hải Phòng"),
    DANANG("Đà Nẵng"),
    HOCHIMINH("Hồ Chí Minh");

    String value;

    private Location(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
