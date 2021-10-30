package tc97939.lab2_3c;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public tc97939.lab2_3c.Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new tc97939.lab2_3c.Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}