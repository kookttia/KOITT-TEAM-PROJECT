package koitt.ratta.doeat.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * 
 * 게시판 통합 조회용 VO
 * @author 진민영
 *
 */
@Getter
@ToString
public class CommunityVO {

	int gIdx;
	int rIdx;
	int uIdx;
	String content;
	String imgUuid;
	
	@Builder
	public CommunityVO(int gIdx, int rIdx, int uIdx, String content, String imgUuid) {
		this.gIdx = gIdx;
		this.rIdx = rIdx;
		this.uIdx = uIdx;
		this.content = content;
		this.imgUuid = imgUuid;
	}

}