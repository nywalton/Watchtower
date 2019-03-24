package watchtower.controller;

import watchtower.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class UserController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/user")
    public User greeting(@RequestParam(value="emailAddress", defaultValue="user@test.com") String emailAddress) {
        return new User(counter.incrementAndGet(),
                String.format(template, emailAddress));
    }

    @RequestMapping("/createUser")
    public User createUser(@RequestParam(value="emailAddress", required = true) String emailAddress) {


        return new User(0, String.format(template, emailAddress));
    }

}
