package se.systementor.supershoppen1.shop.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

class CrisisServiceTest {
    private CrisisService sut;

    @BeforeEach
    private void initService() {
        sut = new CrisisService();
    }

    @Test
    void getLatestCrisisInfo() throws IOException {
        //ARRANGE

        //ACT
        boolean result = sut.approvedTimeInterval();
        //ASSERT
        assertTrue(result);
    }
}