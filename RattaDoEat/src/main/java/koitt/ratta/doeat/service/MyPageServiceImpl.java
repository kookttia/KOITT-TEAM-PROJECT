package koitt.ratta.doeat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import koitt.ratta.doeat.dao.CommentDao;
import koitt.ratta.doeat.dao.ContentDao;
import koitt.ratta.doeat.dao.GalleryDao;
import koitt.ratta.doeat.dao.LikeDao;
import koitt.ratta.doeat.dao.ScrapDao;
import koitt.ratta.doeat.domain.ContentVO;
import koitt.ratta.doeat.domain.GalleryLikeVo;
import koitt.ratta.doeat.domain.GalleryListVo;

import koitt.ratta.doeat.domain.GalleryScrapVo;
import koitt.ratta.doeat.domain.RecipeLikeVo;
import koitt.ratta.doeat.domain.RecipeScrapVo;
import koitt.ratta.doeat.domain.ComCommentVO;
import koitt.ratta.doeat.domain.CommunityVO;


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
	
	@Autowired
	LikeDao likeDao;
	
	@Autowired
	ScrapDao scrapDao;
	
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
	 * @param uIdx 로그인 유저
	 * @return 커뮤니티 통합 vo 리스트
	 */
	@Override
	public List<CommunityVO> viewLikes(int uIdx) {
		
		List<CommunityVO> communitys = new ArrayList<CommunityVO>();
		
		// 로그인 유저가 좋아요 한 갤러리 게시글 조회
		for (GalleryLikeVo like : likeDao.viewLikesByUIdx(uIdx)) {
			GalleryListVo gallery = galleryDao.viewByGIdx(like.getGIdx());
			CommunityVO tmp = CommunityVO.builder().gIdx(gallery.getGIdx())
												   .uIdx(gallery.getUIdx())
												   .content(gallery.getContent())
												   .imgUuid(gallery.getImgUuid()).build();
			communitys.add(tmp);
		}
		
		// 로그인 유저가 좋아요 한 레시피 게시글 조회
		for (RecipeLikeVo like : likeDao.getRecipeByUIdx(uIdx)) {
			ContentVO content = contentDao.viewByRIdx(like.getRIdx());
			CommunityVO tmp = CommunityVO.builder().gIdx(content.getRIdx())
												   .uIdx(content.getUIdx())
												   .content(content.getContent()).build();
			communitys.add(tmp);
		}
		return communitys;
	}
	
	/**
	 * 스크랩한 갤러리, 레시피 글 조회
	 * @return CommunityVO 보류
	 */
	@Override
	public List<CommunityVO> viewIsScrap(int uIdx) {
		
		List<CommunityVO> communitys = new ArrayList<CommunityVO>();
				
		for (RecipeScrapVo scrap : scrapDao.getRecipeByUIdx(uIdx)) {
			
		}
		
		return null;
	}

}
