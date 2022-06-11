package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.css.RGBColor;

import com.example.exception.BadRequest;

@Service
public class ColorService {
    public String radnomColor(int type) {
        return switch (type) {
            case 1 ->
                randomColoroName();
            case 2 ->
                randomHexColor();
            case 3 ->
                randomRgbColor();
            default ->
                throw new BadRequest("Type không hợp lệ");
        };

    }

    public String randomColoroName() {
        String[] color = {"red","yellow","cyan","blue","magenta"};
        int random = (int) (Math.random()*color.length);
        String randomColor = color[random];
        return randomColor;
    }

    public String randomHexColor() {
        String[] color = {"a","b","c","d","f","0","1","2","3","4","5","6","7","8","9"};
        String randomColor = "#";
        for (int i = 0; i < 6; i++) {
            int random = (int) (Math.random()*color.length);
            randomColor += color[random];
        }
        return randomColor;
    }

    public String randomRgbColor() {
        int red = randomRGB();
        int green = randomRGB();
        int blue = randomRGB();
        int[] a = {red,green,blue};
        String str = "rgb(";
        for (int i = 0; i < a.length; i++) {
            if(i == 2){
                str += String.valueOf(a[i]) + ")";
            }
            else{
                str += String.valueOf(a[i]) + ",";
            }
        }
        return str;
    }

    public int randomRGB(){
        return (int) (Math.random()*256);
    }
}
