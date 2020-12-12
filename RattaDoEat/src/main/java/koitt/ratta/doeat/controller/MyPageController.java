package koitt.ratta.doeat.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import koitt.ratta.doeat.domain.AccountEntity;
import koitt.ratta.doeat.service.MyPageService;

@Controller
public class MyPageController {
	
	@Autowired
	MyPageService service;
	
	@GetMapping("mypage")
	public String viewMyPage(Model model, HttpSession session) {
		
		// 로그인 정보
		AccountEntity userInfo = (AccountEntity) session.getAttribute("userInfo");
		int uIdx = userInfo.getUIdx().intValue();
		
		// 모델 2. 유저 작성 갤러리 리스트
		model.addAttribute("myGallerys", service.viewMyGallery(uIdx));
		
		// 모델 3. 유저 작성 레시피북 리스트
		model.addAttribute("myRecipes", service.viewMyRecipe(uIdx));
		
		// 모델 4. 팔로우한 유저들의 새 글 (게시판명, 제목)
		model.addAttribute("byFollowing", service.viewByFollowing(uIdx));
		
		// 모델 5. 덧글달린 게시글 (제목만)
		model.addAttribute("newComment", service.viewNewComment(uIdx));
		
		// 모델 6. 좋아요한 글 전체
		model.addAttribute("like", service.viewLikes(uIdx));
		
		// 모델 7. 스크랩한 글 전체
		model.addAttribute("scrap", service.viewIsScrap(uIdx));
		
		return "myPage";
	}
}
