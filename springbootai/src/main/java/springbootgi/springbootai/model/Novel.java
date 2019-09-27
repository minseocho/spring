package springbootgi.springbootai.model;

/**
 * 정렬테스트를 위해 implements Comparable<Novel> 선언
 * compareTo 오버라이드
 *
 *
 */
public class Novel implements Comparable<Novel> {
	public String subject;
	public String writer;

	public Novel(String subject, String writer) {
		this.subject = subject;
		this.writer = writer;
	}

	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public int compareTo(Novel o) {
		// TODO Auto-generated method stub
		return subject.compareTo(o.getSubject());
	}

}
