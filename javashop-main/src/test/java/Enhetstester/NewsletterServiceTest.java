package Enhetstester;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import se.systementor.supershoppen1.shop.model.Newsletter;
import se.systementor.supershoppen1.shop.model.NewsletterRepository;
import se.systementor.supershoppen1.shop.model.Subscription;
import se.systementor.supershoppen1.shop.model.SubscriptionsRepository;
import se.systementor.supershoppen1.shop.services.NewsletterService;
import se.systementor.supershoppen1.shop.services.SubscriptionsService;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class NewsletterServiceTest {

    @Mock
    SubscriptionsService subscriptionsService;
    @Mock
    NewsletterRepository newsletterRepository;

    @Mock
    SubscriptionsRepository subscriptionsRepository;
    @InjectMocks
    NewsletterService newsletterService;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        subscriptionsRepository = mock(SubscriptionsRepository.class);
        newsletterService = Mockito.spy(new NewsletterService(newsletterRepository,subscriptionsService));

    }

    @Test
    public void testSend_newsletterSent() {
        // arrange
        subscriptionsService = Mockito.spy(new SubscriptionsService(subscriptionsRepository));
        newsletterRepository = mock(NewsletterRepository.class);
        List<String> subscriptions  =  new ArrayList<>();
        subscriptions.add("user1@example.com");
        subscriptions.add("user2@example.com");
        doReturn(subscriptions).when(subscriptionsService).getSignedUp();
        LocalDateTime date = LocalDateTime.now();
        Newsletter newsletter = new Newsletter();
        newsletter.setId(1);
        newsletter.setSentDate(date);
        newsletter.setGreeting("subject");
        newsletter.setBody("content");
        doReturn(Optional.of(newsletter)).when(newsletterRepository).findById(1);
        doReturn(newsletter).when(newsletterService).getById(1);

        // act
        String result = newsletterService.send(1);

        // assert
        assertEquals("Newsletter was already sent " + date.toString() +".", result);
    }
}

