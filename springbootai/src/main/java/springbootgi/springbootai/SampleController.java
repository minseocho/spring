package springbootgi.springbootai;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import thymeleaf.User;

@Controller
@EnableAutoConfiguration
public class SampleController {

	@RequestMapping(value="/")
    @ResponseBody
    public String sampleHome() {

        return "Hello Spring Boot!!!test";

    }

	@GetMapping("/test")
    public String getUser(Model model) {
		// https://eblo.tistory.com/54
        User user = new User("kkaok", "테스트", "web") ;
        model.addAttribute("user", user);
        return "test";
    }

}
