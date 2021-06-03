package tw.yukina.agent.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

@RestController
@Validated
public class Agent {

    private static final Object processLock = new Object();

    @GetMapping(path="/api/joplin/note/{uuid}")
    public String openNote(@PathVariable @Pattern(regexp = "^([a-zA-Z\\d]{8})$") String uuid){

        StringBuilder log = new StringBuilder();
        log.append("<pre>");

        try {
            command(log, "i3-msg", "[class=Joplin]", "focus");
            command(log, "xdotool", "key", "F6");
            command(log, "xdotool", "key", "BackSpace");
            Thread.sleep(100);
            command(log, "xdotool", "type", uuid);
        } catch (Exception e) {
                e.printStackTrace();
                log.append("</pre>");
                return log.toString();
        }

        log.append("</pre>");
        return "<script> window.close() </script>";
    }

    private void command(StringBuilder log, String ... args) throws Exception {
        synchronized (processLock) {
            log.append("Execute command: ");
            log.append(Arrays.toString(args));
            log.append(System.lineSeparator());
            ProcessBuilder processBuilder = new ProcessBuilder(args);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ( (line = reader.readLine()) != null) {
                log.append(line);
                log.append(System.lineSeparator());
            }

            int exitCode = process.waitFor();
            if(exitCode != 0)throw new Exception("command return non zero exit code " + exitCode);
        }
    }
}
