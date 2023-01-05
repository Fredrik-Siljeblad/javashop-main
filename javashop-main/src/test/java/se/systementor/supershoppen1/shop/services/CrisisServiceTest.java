package se.systementor.supershoppen1.shop.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.systementor.supershoppen1.shop.model.Crisis;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CrisisServiceTest {

    private CrisisService sut;

    @BeforeEach
    void initService() {
        sut = new CrisisService();
    }

    @Test
    void shouldReturnOKIfTimeDiffEqualsOneHour() {
        //ARRANGE
        ArrayList<Crisis> crisisArray = new ArrayList<>();
        Crisis c1 = new Crisis(1, "Crisis1", "", "", "", 0, false);
        Crisis c2 = new Crisis(2, "Crisis2", "", "", "", 0, false);
        crisisArray.add(c1);
        crisisArray.add(c2);




        //ACT

        //ASSERT
        //assertEquals(crisisArray, result);

    }
}