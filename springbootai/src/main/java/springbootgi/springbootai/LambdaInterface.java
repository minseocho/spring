package springbootgi.springbootai;


/**
 * 함수형 인터페이스
 * 하나의 변수에 대입할 때 사용하는 참조 변수의 타입
 * 추상 클래스와는 달리 단 하나의 추상 메소드만을 가져야 한다.
 *
 */
@FunctionalInterface//함수형 인터페이스 체크 어노테이션
public interface LambdaInterface {

	public int compareTo(int a, int b);

}
