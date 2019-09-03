package springbootgi.springbootai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import thymeleaf.Content;
import thymeleaf.User;

@Controller
@EnableAutoConfiguration
//@Api(value = "SampleController", description = "헬로 타임리프")
public class SampleController {

	@RequestMapping(value="/")
    @ResponseBody
    public String sampleHome() {

		// http://localhost:8081/
		// TODO API Swagger 설정해보기

        return "Hello Spring Boot!!!test";

    }

	@GetMapping("/test")
    public String getUser(Model model) throws Exception {
		// https://eblo.tistory.com/54
        User user = new User("kkaok", "테스트", "web") ;
        model.addAttribute("user", user);

        List<User> userList = new ArrayList<>();
        userList.add(user);

        if(ObjectUtils.isEmpty(userList)){
        	throw new Exception("비었따");
        }

        // TODO todo 테스트
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("test1", "test11");
        paramMap.put("test2", "test22");



        return "test";
    }

	@GetMapping("/brandex")
    public String brandex(Model model) {
		Content content = new Content("CONTENTS OF THE DIOR") ;
		Content content2 = new Content("디올과 예술가 친구들") ;
        model.addAttribute("content", content);
        model.addAttribute("content2", content2);
        return "pr_brandex";
    }

}
