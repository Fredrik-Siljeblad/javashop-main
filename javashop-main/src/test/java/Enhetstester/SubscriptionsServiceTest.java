package Enhetstester;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import se.systementor.supershoppen1.shop.model.Subscription;
import se.systementor.supershoppen1.shop.model.SubscriptionsRepository;
import se.systementor.supershoppen1.shop.services.SubscriptionsService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SubscriptionsServiceTest {

    @Mock
    SubscriptionsRepository subscriptionsRepository;
    @Mock
    SubscriptionsService subscriptionsService;
    List<String> subscriptions;
    List<Subscription> subscriptionList;
    Subscription subscription1;
    Subscription subscription2;
    Subscription newSub;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        subscriptionsService = Mockito.spy(new SubscriptionsService(subscriptionsRepository));
        newSub = new Subscription("newSub@user.com",true);
        subscription1 = new Subscription();
        subscription1.setActive(true);
        subscription1.setEmail("user1@example.com");
        subscription2 = new Subscription();
        subscription1.setActive(false);
        subscription1.setEmail("user2@example.com");
        subscriptions  =  new ArrayList<>();
        subscriptionList = new ArrayList<>();
        subscriptionList.add(subscription1);
        subscriptionList.add(subscription2);

    }


    @Test
    void getSignedUp() {
        //Arrange
        doReturn(subscriptionList).when(subscriptionsRepository).findAll();
        subscriptions.add(subscription1.getEmail());
        doReturn(subscriptions).when(subscriptionsService).getSignedUp();
        //Act
        List<String> returnSubscriptions = subscriptionsService.getSignedUp();

        //Assert
        assertEquals(subscriptions,returnSubscriptions);
    }

    @Test
    void getAll() {

        //Arrange
        doReturn(subscriptionList).when(subscriptionsRepository).findAll();
        doReturn(subscriptions).when(subscriptionsService).getAll();

        //Act
        List<String> returnSubscriptions = subscriptionsService.getAll();

        //Assert
        assertEquals(subscriptions,returnSubscriptions);

    }

    @Test
    void add() {
        //Arrange
        doReturn(newSub).when(subscriptionsService).add(newSub.getEmail());
        doReturn(newSub).when(subscriptionsRepository).save(newSub);
        //Act
        subscriptionsRepository.save(newSub);
        verify(subscriptionsRepository,times(1)).save(newSub);
        Subscription result = subscriptionsService.add(newSub.getEmail());

        //Assert
        assertEquals(newSub,result);
    }

    @Test
    void save() {

        //Act & verify
        subscriptionsRepository.save(newSub);
        verify(subscriptionsRepository,times(1)).save(newSub);

    }

    @Test
    void isSubscriber() {
        // Arrange
        String user = "user1@example.com";
        doReturn(subscriptionList).when(subscriptionsRepository).findAll();
        doReturn(true).when(subscriptionsService).isSubscriber(user);

        //Act
        boolean result = subscriptionsService.isSubscriber(user);

        // Assert
        assertEquals(true,result);

    }
}