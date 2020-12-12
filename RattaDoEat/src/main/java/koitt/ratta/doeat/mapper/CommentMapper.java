package koitt.ratta.doeat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import koitt.ratta.doeat.domain.ComCommentVO;

@Mapper
public interface CommentMapper {
	
	@Select("SELECT *"
			+ " FROM com_comment"
			+ " WHERE SYSDATE-reg_date < 3 AND g_idx = #{gIdx}")
	public List<ComCommentVO> viewAllByGIdx(int gIdx);
	
	@Select("SELECT *"
			+ " FROM com_comment"
			+ " WHERE SYSDATE-reg_date < 3 AND r_idx = #{rIdx}")
	public List<ComCommentVO> viewAllByRIdx(int rIdx);
	
}
