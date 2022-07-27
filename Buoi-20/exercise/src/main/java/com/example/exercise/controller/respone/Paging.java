package com.example.exercise.controller.respone;

import java.util.ArrayList;
import java.util.List;

public class Paging {
    public String title;
    public int index;
    public String active;
    public Paging(String title, int index, String active) {
        this.title = title;
        this.index = index;
        this.active = active;
    }

    public static List<Paging> generatePages(int selectedPage, int totalPages) {
        //https://codereview.stackexchange.com/questions/240235/java-pagination-algorithm
        int offset = Math.min(5, totalPages); // total =7, offset =5
        // set start index relative to selected
        int start = selectedPage - (offset / 2); //start = 3 - (5/2) = 1
        // adjust for first pages
        start = Math.max(start, 0); // start = 1
        // set end index relative to start
        int end = start + offset; // end = 1 + 5 = 6
        // adjust start and end for last pages
        if (end > totalPages) {
            end = totalPages;
            start = end - offset + 1;
        }
        ArrayList<Paging> pagings = new ArrayList<>();
        pagings.add(new Paging("Prev", selectedPage > 0 ? selectedPage - 1: 0, ""));
        for (int i = start; i < end; i++){
            Paging paging = new Paging(String.valueOf(i + 1), i, (i == selectedPage) ? "active" : "");
            pagings.add(paging);
        }
        pagings.add(new Paging("Next", Math.min(selectedPage + 1, totalPages - 1), ""));
        return pagings;
    }
}