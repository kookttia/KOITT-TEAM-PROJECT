package koitt.ratta.doeat.dao;

import java.util.List;

import koitt.ratta.doeat.domain.ComCommentVO;

public interface CommentDao {

	List<ComCommentVO> viewAllByGIdx(int gIdx);

	List<ComCommentVO> viewAllByRIdx(int rIdx);

}
