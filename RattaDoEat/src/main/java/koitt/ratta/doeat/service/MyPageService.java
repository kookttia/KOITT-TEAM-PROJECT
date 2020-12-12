package koitt.ratta.doeat.service;

import java.util.List;

import koitt.ratta.doeat.domain.ContentVO;
import koitt.ratta.doeat.domain.GalleryListVo;

public interface MyPageService {
	
	List<GalleryListVo> viewMyGallery(int uIdx);

	List<ContentVO> viewMyRecipe(int uIdx);

	Object viewByFollowing(int uIdx);

	Object viewNewComment(int uIdx);

	Object viewLikes(int uIdx);

	Object viewIsScrap(int uIdx);

}
