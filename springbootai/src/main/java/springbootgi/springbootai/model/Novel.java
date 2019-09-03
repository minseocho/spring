package springbootgi.springbootai.model;

// TODO lombok 설정 하기
public class Novel {
	public String subject;
	public String writer;

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

}
