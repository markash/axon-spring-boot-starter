/*
 * This is free and unencumbered software released into the public domain.
 *
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 *
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 * For more information, please refer to <http://unlicense.org/>.
 */

package hm.binkley.spring.axon;

import org.axonframework.eventhandling.EventBus;
import org.axonframework.springmessaging.eventbus.SpringMessagingEventBus;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition
        .ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.SubscribableChannel;

/**
 * {@code AxonSpringMessagingAutoConfiguration} autoconfigures Axon Framework
 * for Spring Boot with a distributed event bus using Spring Messaging
 * (typically JMS). Use {@link EnableAutoConfiguration} on your configuration
 * class, and define a bean for {@link SubscribableChannel}.
 * <p>
 * A minimal configuration is: <pre>   &#64;Configuration
 * &#64;EnableAutoConfiguration
 * public class AConfiguration {
 *     &#64;Bean
 *     public SubscribableChannel subscribableChannel() {
 *         return ...;
 *     }
 * }</pre> In other classes inject Axon types
 * normally with {@code @Autowired}.
 *
 * @author <a href="mailto:binkley@alumni.rice.edu">B. K. Oxley (binkley)</a>
 */
@AutoConfigureBefore(AxonQueryAutoConfiguration.class)
@ConditionalOnClass(EventBus.class)
@Configuration
public class AxonSpringMessagingAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public EventBus springMessagingEventBus(
            final SubscribableChannel channel) {
        final SpringMessagingEventBus eventBus
                = new SpringMessagingEventBus();
        eventBus.setChannel(channel);
        return eventBus;
    }
}
