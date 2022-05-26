package com.jobhunt.jobhunt;

import com.jobhunt.jobhunt.service.StorageService;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class StorageServiceTest {
    @Test
    public void test_getFileExtension(){
        StorageService s = new StorageService();
        String ex = s.getFileExtension("pic1.png");
        System.out.println(ex);

        assertThat(ex).isEqualTo("png");
    }
}
