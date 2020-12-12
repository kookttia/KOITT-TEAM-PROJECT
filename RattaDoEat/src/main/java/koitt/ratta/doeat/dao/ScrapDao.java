package koitt.ratta.doeat.dao;

import java.util.List;

import koitt.ratta.doeat.domain.GalleryScrapVo;
import koitt.ratta.doeat.domain.RecipeScrapVo;

public interface ScrapDao {

	int addScrap(GalleryScrapVo galleryScrapVo);

	int viewScrap(int gIdx);

	List<RecipeScrapVo> getRecipeByUIdx(int uIdx);

}
