package com.bdt.blog;

import com.blog.blog.GreetingMessage;
import com.blog.blog.ServiceBdt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Method;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ServiceBdt.class})
@ActiveProfiles("test")
public class ServiceBdtTest {
    @Test
    public void testTranslatedMessage() {
        GreetingMessage helloWorld = new GreetingMessage();
        helloWorld.setMessage("Hello World");
        GreetingMessage goodbyeWorld = new GreetingMessage();
        goodbyeWorld.setMessage("Not Hello World");
        try {
            assertThat(invokePrivateMethod(helloWorld).getMessage(), equalTo("Hello World"));
        } catch (Exception e) {
            fail();
        }
        try {
            assertThat(invokePrivateMethod(goodbyeWorld).getMessage(), equalTo("Goodbye World"));
        } catch (Exception e) {
            fail();
        }
    }

    private GreetingMessage invokePrivateMethod(final GreetingMessage greetingMessage) throws Exception {
        ServiceBdt bdtService = new ServiceBdt();
        Method method = bdtService.getClass().getDeclaredMethod("translateMessage",
                GreetingMessage.class);
        method.setAccessible(true);
        return (GreetingMessage) method.invoke(bdtService, greetingMessage);
    }
}
