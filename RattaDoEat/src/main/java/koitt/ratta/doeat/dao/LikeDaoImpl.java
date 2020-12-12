package koitt.ratta.doeat.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import koitt.ratta.doeat.domain.GalleryLikeVo;
import koitt.ratta.doeat.domain.RecipeLikeVo;

/**
 * 
 * @author 진민영
 * @since
 *
 */
@Repository
public class LikeDaoImpl implements LikeDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	String path = "koitt.ratta.doeat.mapper.LikeMapper.";

	/**
	 * 좋아요 데이터 행 수를 조회하는 쿼리
	 * 
	 * @return 존재시 무조건 1, 존재하지 않을 시 0
	 */
	@Override
	public int isLike(GalleryLikeVo galleryLikeVo) {
		return sqlSession.selectOne(path + "isLike", galleryLikeVo);
	}

	/**
	 * 좋아요 데이터를 삽입하는 쿼리
	 */
	@Override
	public int addLike(GalleryLikeVo galleryLikeVo) {
		return sqlSession.insert(path + "addLike", galleryLikeVo);
	}

	/**
	 * 좋아요 횟수를 조회하는 쿼리
	 */
	@Override
	public int viewLike(int gIdx) {
		return sqlSession.selectOne(path + "viewLike", gIdx);
	}
	
	/**
	 * 좋아요 데이터를 삭제하는 쿼리
	 */
	@Override
	public int unLike(GalleryLikeVo galleryLikeVo) {
		return sqlSession.delete(path + "unLike", galleryLikeVo);
	}
	
	/**
	 * 좋아요 누른 사람으로 좋아요한 갤러리 게시글을 조회하는 쿼리
	 */
	@Override
	public List<GalleryLikeVo> viewLikesByUIdx(int uIdx) {
		return sqlSession.selectList(path + "viewLikesByUIdx", uIdx);
	}

	/**
	 * 좋아요 누른 사람으로 좋아요한 레시피 게시글을 조회하는 쿼리
	 */
	@Override
	public List<RecipeLikeVo> getRecipeByUIdx(int uIdx) {
		return sqlSession.selectList(path + "getByUIdx", uIdx);
	}
}
