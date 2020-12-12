package koitt.ratta.doeat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import koitt.ratta.doeat.dao.CommentDao;
import koitt.ratta.doeat.dao.ContentDao;
import koitt.ratta.doeat.dao.GalleryDao;
import koitt.ratta.doeat.domain.ContentVO;
import koitt.ratta.doeat.domain.GalleryListVo;
import koitt.ratta.doeat.domain.ComCommentVO;

/**
 * 
 * @author 진민영
 *
 */
@Service
public class MyPageServiceImpl implements MyPageService {
	
	@Autowired
	GalleryDao galleryDao;
	
	@Autowired
	ContentDao contentDao;
	
	@Autowired
	CommentDao commentDao;
	
	/**
	 * 유저가 작성한 갤러리 글 조회
	 */
	@Override
	public List<GalleryListVo> viewMyGallery(int uIdx) {
		return galleryDao.viewAllByUser(uIdx);
	}

	@Override
	public List<ContentVO> viewMyRecipe(int uIdx) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 팔로우한 유저들이 작성한 갤러리, 레시피 글 조회
	 * @return CommunityVO 갤러리 or 커뮤니티(인덱스로 구분)
	 */
	@Override
	public Object viewByFollowing(int uIdx) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 새로운 덧글이 달린 갤러리, 레시피 글 조회
	 * @return List<comCommentVO> 
	 */
	@Override
	public List<ComCommentVO> viewNewComment(int uIdx) {
		
		List<ComCommentVO> comments = new ArrayList<ComCommentVO>();
		
		// 로그인 유저가 쓴 게시글 조회
		for (GalleryListVo post : galleryDao.viewAllByUser(uIdx)) {
			// 조회한 게시글에 최근 72시간 내에 달린 덧글 조회
			for (ComCommentVO galleryComment : commentDao.viewAllByGIdx(post.getGIdx())) {
				comments.add(galleryComment);
			}
		}
		
		for (ContentVO post : contentDao.getAllByUser(uIdx)) {
			for (ComCommentVO recipeComment : commentDao.viewAllByRIdx(post.getRIdx())) {
				comments.add(recipeComment);
			}
		}
		
		return comments;
	}
	
	/**
	 * 좋아요한 갤러리, 레시피 글 조회
	 * @return CommunityVO 보류
	 */
	@Override
	public Object viewIsLike(int uIdx) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 스크랩한 갤러리, 레시피 글 조회
	 * @return CommunityVO 보류
	 */
	@Override
	public Object viewIsScrap(int uIdx) {
		// TODO Auto-generated method stub
		return null;
	}

}
