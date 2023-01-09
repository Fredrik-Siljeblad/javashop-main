package se.systementor.supershoppen1.shop.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CrisisServiceTest {
    private CrisisService sut;

    @BeforeEach
    private void initService() {
        sut = new CrisisService();
    }

    @Test
    void shouldBeOKIfTimeIntervalIsUnderOrEqual1Hour() {
        //ARRANGE

        //ACT
        boolean result = sut.approvedTimeInterval();
        //ASSERT
        assertTrue(result);
    }

    @Test
    void shouldBeOKIfConnectionIsUnder300() {
        //ARRANGE

        //ACT
        boolean result = sut.approvedConnectionStatus();
        //ASSERT
        assertTrue(result);
    }
}