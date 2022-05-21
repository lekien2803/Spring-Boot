package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Location {
    AllCity("All cities"),
    HaNoi("Ha Noi"),
    HoChiMinh("Ho Chi Minh"),
    HaiPhong("Hai Phong"),
    DaNang("Da Nang"),
    Others("Others");

    public final String label;

}
