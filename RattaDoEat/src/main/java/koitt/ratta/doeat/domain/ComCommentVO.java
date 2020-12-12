package koitt.ratta.doeat.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * (민영) 임시로 만든거라..galComment에 갤러리, 레시피 아무 내용이나 들어올 수 있다고 일단 가정하고 씀
 * 정은씨 보시면 의도대로 수정해주세여
 *
 */
@Getter
@ToString
@NoArgsConstructor
public class ComCommentVO {

	int cIdx;
	int gIdx;
	int rIdx;
	int uIdx;
	String galComment;
	LocalDateTime regDate;
	
	@Builder
	public ComCommentVO(int cIdx, int gIdx, int rIdx, int uIdx, String galComment, LocalDateTime regDate) {
		this.cIdx = cIdx;
		this.gIdx = gIdx;
		this.rIdx = rIdx;
		this.uIdx = uIdx;
		this.galComment = galComment;
		this.regDate = regDate;
	}
	
}
