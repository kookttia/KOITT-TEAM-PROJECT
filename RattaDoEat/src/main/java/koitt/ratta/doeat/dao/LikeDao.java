package koitt.ratta.doeat.dao;

import java.util.List;

import koitt.ratta.doeat.domain.GalleryLikeVo;
import koitt.ratta.doeat.domain.RecipeLikeVo;

public interface LikeDao {

	int addLike(GalleryLikeVo galleryLikeVo);

	int viewLike(int gIdx);

	int isLike(GalleryLikeVo galleryLikeVo);

	int unLike(GalleryLikeVo galleryLikeVo);

	List<GalleryLikeVo> viewLikesByUIdx(int uIdx);

	List<RecipeLikeVo> getRecipeByUIdx(int uIdx);

}
