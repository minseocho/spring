package springbootgi.springbootai;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTestController {

	private static final Logger log = LoggerFactory.getLogger(RestTestController.class);

	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/restTest")
	public Greeting restTest(@RequestParam(value="name", defaultValue="World") String name) {

//		LambdaInterface li1 = (String s1, String s2, String s3) -> {
//			log.info(s1 + "" + s2 + "" + s3);
//		};
//		li1.method("Hello", "java", "World");

        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

	@RequestMapping("/paramTest")
	public int paramTest(@RequestParam(value="a") int a, @RequestParam(value="b") int b) {

		log.info("a : "+ a);
		log.info("b : "+ b);

		return a+b;

    }

	@RequestMapping("/lambdaTest")
	public void lambdaTest() {
		int a = 4;
		int b = 8;

		LambdaInterface li1 = (x,y) -> (x>=y)? x:y;

		log.info("수는"+li1.compareTo(a, b));

		List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");

		myList.stream().filter(s -> s.startsWith("c")).map(String::toUpperCase).sorted().forEach(System.out::println);

		Stream.of("a1", "a2", "a3")
	    .map(s -> s.substring(1))
	    .mapToInt(Integer::parseInt)
	    .max()
	    .ifPresent(System.out::println);  // 3

    }

	/**
	 * 스트림은 데이터소스를 추상화하고, 데이터를 다루는데 자주 사용되는 메서들을 정의해 놓았다.
	 * 데이터소스를 추상화하였다는 것은, 데이터 소스가 무엇이든 같은 방식으로 다룰 수 있게 되었다는 것과
	 * 코드의 재사용성이 높아진다는 것을 의미한다.
	 */
	@RequestMapping("/streamTest")
	public void streamTest() {

		//Stream 사용 전
		String[] strArr = { "mash-up", "backend", "codingsquid" };
		List<String> strList = Arrays.asList(strArr);

		Arrays.sort(strArr);
		Collections.sort(strList);

		for(String str: strArr) {
			log.info("strArr = " + str);
		}

		for(String str : strList) {
			log.info("strList = " + str);
		}

		//Stream 사용 후
		Stream<String> listStream = strList.stream();
		Stream<String> arrayStream = Arrays.stream(strArr);

		listStream.sorted().forEach(System.out::println);
		arrayStream.sorted().forEach(System.out::println);

//		log.info("수는"+li1.compareTo(a, b));



    }

}
