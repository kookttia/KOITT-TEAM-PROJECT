package koitt.ratta.doeat.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecipeLikeVo {
	
	int rLIdx;
	int uIdx;
	int rIdx;
	
	@Builder
	public RecipeLikeVo(int rLIdx, int uIdx, int rIdx) {
		this.rLIdx = rLIdx;
		this.uIdx = uIdx;
		this.rIdx = rIdx;
	}

}
