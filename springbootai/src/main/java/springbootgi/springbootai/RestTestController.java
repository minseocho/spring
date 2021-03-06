package springbootgi.springbootai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springbootgi.springbootai.model.ApiResponseMessage;
import springbootgi.springbootai.model.Novel;

@RestController
@Api(value = "RestTestController", description = "헬로 에이피아이")
public class RestTestController {

	// http://localhost:8081/
	// http://localhost:8081/swagger-ui.html

	private static final Logger log = LoggerFactory.getLogger(RestTestController.class);

	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

	@RequestMapping(value="/restTest", method=RequestMethod.GET)
	@ApiOperation(value="rest API 테스트", notes="rest API 테스트 확인용")
	public Greeting restTest(@RequestParam(value="name", defaultValue="World") String name) {

//		LambdaInterface li1 = (String s1, String s2, String s3) -> {
//			log.info(s1 + "" + s2 + "" + s3);
//		};
//		li1.method("Hello", "java", "World");

        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

	@RequestMapping(value="/paramTest", method=RequestMethod.GET)
	@ApiOperation(value="파라미터 테스트", notes="라파미터 테스트 확인용")
	public int paramTest(@RequestParam(value="a") int a, @RequestParam(value="b") int b) {

		log.info("a : "+ a);
		log.info("b : "+ b);

		return a+b;

    }

	@RequestMapping(value="/lambdaTest", method= RequestMethod.GET)
	@ApiOperation(value="람다 테스트", notes="람다 테스트 확인용")
	public ResponseEntity<ApiResponseMessage> lambdaTest() {
		// https://coding-factory.tistory.com/265
		/**
		 * 람다식 : 식별자없이 실행가능한 함수
		 *          함수인데 함수를 따로 만들지 않고 코드한줄에 함수를 써서 그것을 호출하는 방식
		 * ex 메소드)
		 * int min(int x, int y) {
		 *    return x < y ? x : y;
		 * }
		 * ex 람다 표현식)
		 * (x, y) -> x < y ? x : y;
		 */

		LambdaInterface li1 = (x,y) -> (x>=y)? x:y;

		log.info("수는"+li1.compareTo(4, 6));

		List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");

		// https://jeong-pro.tistory.com/165
		// 스트림 생성
		myList.stream();

		// filter() : 필터링, 맞는것만 거른다. 컬렉션을 조건에 의한 선택을 할때 유용하다.
		// a가 포함된것만 거른다.
		Stream<String> a = myList.stream().filter(x -> x.contains("a"));
		a.sorted().forEach(name -> log.info("a포함:"+name));


		// map() : 입력 컬렉션을 출력 컬렉션으로 매핑하거나 변경할때 유용하다.
		// 문자열 중에 c로 시작하는 것들을 대문자로 바꾸고 정렬해서 출력해라
		myList.stream().filter(s -> s.startsWith("c")).map(String::toUpperCase).sorted().forEach(name -> log.info("대문자:"+name));

		// collect() : 값을 하나로 모으는
		List<String> startsWithC = myList.stream().filter(name -> name.startsWith("c")).collect(Collectors.toList());

		log.info("startsWithC : {}", startsWithC);

		// 문자열 1번부터 잘라서 수로 변환후 최고값 구하기
		Stream.of("a1", "a2", "a3")
	    .map(s -> s.substring(1))
	    .mapToInt(Integer::parseInt)
	    .max()
	    .ifPresent(name -> log.info("최고값:"+name));  // 3

		ApiResponseMessage message = new ApiResponseMessage("Success", "Hello, World", "", "", "");

		return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);

    }

	/**
	 * 스트림은 데이터소스를 추상화하고, 데이터를 다루는데 자주 사용되는 메서들을 정의해 놓았다.
	 * 데이터소스를 추상화하였다는 것은, 데이터 소스가 무엇이든 같은 방식으로 다룰 수 있게 되었다는 것과
	 * 코드의 재사용성이 높아진다는 것을 의미한다.
	 */
	@RequestMapping(value="/streamTest", method=RequestMethod.GET)
	@ApiOperation(value="스트림 테스트", notes="스트림 테스트 확인용")
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

	/**
	 * https://cwondev.tistory.com/15
	 * https://manorgass.tistory.com/60
	 * list 정렬 테스트
	 * 모델 정렬
	 */
	@RequestMapping(value="/listTest", method=RequestMethod.GET)
	@ApiOperation(value="리스트 sort 테스트", notes="리스트 sort 테스트 확인용")
	public ArrayList<Novel> listTest() {
		ArrayList<Novel> strList = new ArrayList<>();

		Novel novel1 = new Novel("SPARK OF LIFE", "ERICH MARIA");
		strList.add(novel1);

		// Rich Man, Poor Man
		// IRWIN SHAW
		Novel novel2 = new Novel("Rich Man, Poor Man", "IRWIN SHAW");
		strList.add(novel2);

		Novel novel3 = new Novel("Aich Man, Poor Man", "ERWIN SHAW");
		strList.add(novel3);

		Novel novel4 = new Novel("Cich Man, Poor Man", "HRWIN SHAW");
		strList.add(novel4);

		Collections.sort(strList);

		for(int i=0; i<strList.size(); i++){
			log.info(strList.get(i).getSubject());
		}

		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(4);
		list.add(6);
		list.add(2);
		list.add(5);

		log.info("정렬 전  : " + list.toString());
		list.sort(Comparator.naturalOrder());
		log.info("오름차순 : " + list.toString());
		list.sort(Comparator.reverseOrder());
		log.info("내림차순 : " + list.toString());



		return strList;
	}


	/**
	 * https://nota.tistory.com/49
	 * 형변환 테스트
	 */
	@RequestMapping(value="/castTest", method=RequestMethod.GET)
	@ApiOperation(value="형변환 테스트", notes="형변환 테스트 확인용")
	public Map<String, Object> castTest() {

		Map<String, Object> param = new HashMap<>();

		String str = "345";

		param.put("String", Integer.parseInt(str));

		int nmb = 123;

		param.put("int", Integer.toString(nmb));

		log.info("param : {}", param);

		return param;
	}

	/**
	 * https://steemit.com/kr-dev/@igna84/spring-boot-responseentity
	 * ResponseEity 테스트
	 */
	@RequestMapping(value="/respTest", method=RequestMethod.GET)
	@ApiOperation(value="ResponseEity 테스트", notes="ResponseEity 테스트 확인용")
	public ResponseEntity<ApiResponseMessage> responseEntityTest(@RequestParam(value="number1") int number1,
																 @RequestParam(value="number2") int number2) {

		ApiResponseMessage message = new ApiResponseMessage("Success", "Hello, World", number1 + number2, "", "");

		log.info("message : {}", message);

		return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
	}

}
