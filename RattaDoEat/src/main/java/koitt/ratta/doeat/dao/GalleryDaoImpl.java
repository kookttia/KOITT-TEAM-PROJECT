package koitt.ratta.doeat.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import koitt.ratta.doeat.domain.GalleryListVo;
import koitt.ratta.doeat.domain.GalleryVo;

@Repository
public class GalleryDaoImpl implements GalleryDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	String path = "koitt.ratta.doeat.mapper.GalleryMapper.";

	@Override
	public List<GalleryListVo> viewAll() {
		return sqlSession.selectList(path + "viewAll");
	}

	@Override
	public List<GalleryListVo> viewAllByUser(int u_idx) {
		return sqlSession.selectList(path + "viewAllByUser", u_idx);
	}

	@Override
	public int insertG(GalleryVo galleryVo) {
		return sqlSession.insert("insertG", galleryVo);
	}

	@Override
	public GalleryVo viewDetail(int gIdx) {
		GalleryVo galleryVo = sqlSession.selectOne("viewDetail", gIdx);
		return galleryVo;
	}

	@Override
	public GalleryListVo viewByGIdx(int gIdx) {
		// TODO Auto-generated method stub
		return null;
	}
}
