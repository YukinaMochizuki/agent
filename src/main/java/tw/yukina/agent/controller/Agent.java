package tw.yukina.agent.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.io.IOException;

@RestController
@Validated
public class Agent {
    @GetMapping(path="/api/joplin/note/{uuid}")
    public void openNote(@PathVariable @Pattern(regexp = "^([a-zA-Z\\d]{8})$") String uuid){
        try {
            new ProcessBuilder("i3-msg", "[class=Joplin]", "focus").start();
            new ProcessBuilder("xdotool", "key", "F6").start();
            Thread.sleep(10);
            new ProcessBuilder("xdotool", "key", "BackSpace").start();
            new ProcessBuilder("xdotool", "type", uuid).start();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
