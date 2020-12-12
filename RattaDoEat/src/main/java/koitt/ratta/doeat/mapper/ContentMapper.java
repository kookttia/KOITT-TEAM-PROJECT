package koitt.ratta.doeat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import koitt.ratta.doeat.domain.ContentVO;

@Mapper
public interface ContentMapper {
	
	@Select("select * from com_recipe")
	List<ContentVO> getAll();
	
	@Insert("insert into com_recipe values ( r_idx_seq.nextval, u_idx_seq.nextval, #{content}, sysdate, sysdate, #{hit}, #{likeNum}, #{scrapNum} )")
	ContentVO write(ContentVO vo);
	
	@Select("select * from com_recipe where r_idx=#{rIdx}")
	List<ContentVO> read();
	
	@Update("update com_recipe set hit=hit+1 where r_idx=#{rIdx}")
	void hitUpdate();
	
	@Update("update com_recipe set content=#{content}, modify_date=sysdate where r_idx=#{rIdx}")
	void update();
	
	@Delete("delete from com_recipe where r_idx=#{rIdx}")
	void delete();
	
	@Update("update com_recipe set like_num=like_num+1 where r_idx=#{rIdx}")
	void likeNumUpdate();
	
	@Update("update com_recipe set scrap_num=scrap_num+1 where r_idx=#{rIdx}")
	void scrapNumUpdate();
	
	/**
	 * @author 진민영
	 */
	@Select("SELECT *"
			+ " FROM com_recipe"
			+ " WHERE u_idx = #{uIdx}")
	List<ContentVO> getAllByUser(int uIdx);
	
	/**
	 * @author 진민영
	 */
	@Select("SELECT *"
			+ " FROM com_recipe"
			+ " WHERE r_idx = #{rIdx}")
	ContentVO getByRIdx(int rIdx);

}
