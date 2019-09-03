package springbootgi.springbootai.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class BookResultMessage<T> {

//	@ApiModelProperty(notes = "성공 여부")
	private String successYn; // 성공 여부
//	@ApiModelProperty(notes = "HttpStatus code")
	private String statusCode; // HttpStatus code
//	@ApiModelProperty(notes = "업무성 코드")
	private String code; // 업무성 코드
	private T data; // 데이터 객체
//	@ApiModelProperty(notes = "메세지")
	private String message; // 메세지
//	@ApiModelProperty(notes = "개발자용 메세지")
	private String devMessage; // 개발자용 메세지

	public BookResultMessage() {
	}

	public BookResultMessage(String successYn) {
		this.successYn = successYn;
	}

	public BookResultMessage(String successYn, String message) {
		this.successYn = successYn;
		this.message = message;
	}

	public BookResultMessage(String successYn, String code, T data, String message) {
		this.successYn = successYn;
		this.code = code;
		this.data = data;
		this.message = message;
	}

	public BookResultMessage(String successYn, String code, String message, String devMessage) {
		this.successYn = successYn;
		this.code = code;
		this.message = message;
		this.devMessage = devMessage;
	}

	public BookResultMessage(T data) {
		this.successYn = "Y";
        this.statusCode = "200";
        this.code = null;
        this.data = data;
        this.message = null;
        this.devMessage = "Success";
    }

}
