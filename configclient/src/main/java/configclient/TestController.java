package configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by THINK on 2017/3/8.
 */
@RefreshScope
@RestController
public class TestController {
    @Value("${from}")
    private String from;
    @RequestMapping("/from")
    public String from() {
        return this.from;
    }
}
