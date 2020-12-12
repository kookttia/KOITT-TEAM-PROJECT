package koitt.ratta.doeat.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import koitt.ratta.doeat.domain.GalleryScrapVo;
import koitt.ratta.doeat.domain.RecipeScrapVo;

@Repository
public class ScrapDaoImpl implements ScrapDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	String path = "koitt.ratta.doeat.mapper.ScrapMapper.";

	@Override
	public int addScrap(GalleryScrapVo galleryScrapVo) {
		return sqlSession.update(path + "addScrap", galleryScrapVo);
	}

	@Override
	public int viewScrap(int gIdx) {
		return sqlSession.selectOne(path + "viewScrap", gIdx);
	}

	@Override
	public List<RecipeScrapVo> getRecipeByUIdx(int uIdx) {
		// TODO Auto-generated method stub
		return null;
	}
}
