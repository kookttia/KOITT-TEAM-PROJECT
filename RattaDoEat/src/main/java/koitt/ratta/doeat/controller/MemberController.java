
package koitt.ratta.doeat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import koitt.ratta.doeat.domain.AccountDto;
import koitt.ratta.doeat.domain.AccountEntity;
import koitt.ratta.doeat.service.AccountService;
import koitt.ratta.doeat.service.AccountServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * 멤버 컨트롤러
 * 기능 : 로그인과 회원가입 관련 기능, 멤버 정보 CRUD, 권한 대응 오류 페이지
 * 
 * @author GW
 * @since 2020-10-26
 */
@Slf4j
@Controller
public class MemberController {
	
	@Autowired
	private AccountService accountService;
	

	//GW 의 테스트 페이지 이동
	@RequestMapping("GWtest")
	public String GWtest(Model model) throws Exception {
		//model.addAttribute("hello", "model 안녕하세요.");
		return "GW_test_page";
	}
	
	//기본 홈 이동
	@RequestMapping(value = {"/", "home"})
	public String main(Model model) throws Exception {
		//model.addAttribute("hello", "model 안녕하세요.");
		return "home";
	}
	
	//로그인 성공시에 로그인 세션 정보 저장. 
	@RequestMapping("login.do")
	public String doLogin(Model model, HttpServletRequest request) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails user = (UserDetails)principal; //유저디테일즈로 강제 타입 변환
		log.info(" ~~~~~ 컨트롤러 로그인 user : " + user);
		
		try {
			 AccountEntity userInfo = accountService.findByUserIdService(user.getUsername());
			HttpSession session = request.getSession(true);
			session.setAttribute("userInfo", userInfo);
			log.info(" ~~~~~ login.do 성공, 유저 인포 : " + userInfo);
			} 
		catch (Exception e) {
			log.error("~~~~~ login.do try catch 에러");
			e.printStackTrace();
		}
		return "/hello";
	}
	
	
	//테스트 페이지, 로그인 성공시 들어가서 확인. 
	@RequestMapping("hello")
	public String goHello(Model model) {
		
		return "hello";
	}
	
	//로그인폼 페이지 이동
	@RequestMapping("login.go")
	public String goLogin() {
		return "auth/login";
	}
	

	//회원가입폼 페이지 이동
	@RequestMapping(value = {"join.go","join2.go"})
	public String goJoin(Model model) {
		model.addAttribute("accountDto", new AccountDto());
		return "auth/join";
	}

	//회원가입, 유저 인포 저장.
	@RequestMapping("join.do")
	public String doJoin(@ModelAttribute AccountDto accountDto, Model model) throws Exception {
		
		log.info("회원가입 폼에서 넘어오는 값 : " + accountDto);
		
		try {
			accountService.saveUser(accountDto);
			model.addAttribute("userName", accountDto.getName());
		} catch (Exception e) {
			model.addAttribute("failed", "가입실패. 다시 시도해주세요.");
			return "join.go";
		}
		
		return "hello"; //환영 페이지, 추후에 user info 폼 이동으로 변경 예정.
	}
		
	//아이디 찾기 폼 이동
	@RequestMapping("userFindId.go")
	public String goUserFindId (Model model) {
		return "auth/userFindId";
	}
		
	//비밀번호 찾기 폼 이동 	
	@RequestMapping("userFindPw.go")
	public String goUserFindPw (Model model) {
		return "auth/userFindPw";
	}	
	
	//로그인 에러 메세지 처리
	@RequestMapping("/error.go") //loginError.go 변경예정
	public String goLoginError(Model model, HttpSession session) {
		
		// Spring CustomProvider 인증(Auth) 에러 메시지 처리
    	log.info("~~~~~ 로그인 에러 처리 페이지");
        Object secuSess = session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");

        log.info("~~~~~ 인증 오류 메시지 : " + secuSess);
        log.info("~~~~~ 인증 오류 메시지 : " + secuSess.toString());

        model.addAttribute("error", "true");
        model.addAttribute("msg", secuSess);
        
		//return "error/error";
        return "auth/login";
	}	
	
	//AuthSecurityConfig 설정 권한 오류 대응 페이지. 
	@RequestMapping("/accessDenied.go")
	public String goAccessDenied() {
		
		// Spring CustomProvider 인증(Auth) 에러 메시지 처리
    	log.info("##### 접근금지, 에러 처리 페이지 #####");
		return "error/error";
        //return "auth/login";
	}
	
	//어드민 테스트 페이지 
	@RequestMapping("admin.go")
	public String goAdminPage(Model model) {
		return "admin/admin";
	}
	//user 테스트 페이지 
	@RequestMapping("user/mypage.go")
	public String goMyPage(Model model) {
		return "user/mypage";
	}
	//user 테스트 페이지 2
	@Secured({"ROLE_USER"})
	@RequestMapping("mypage.go")
	public String goJoinMyPage(Model model) {
		model.addAttribute("환영메세지", "가입을 축하합니다.");
		return "user/mypage";
	}
	
	//시큐리티 이동, 로그아웃 성공시 페이지 단순이동 
	@RequestMapping("logout.go") 
	public String doLogout() {
		return "auth/logout";
	}
	
	//실제 로그아웃 기능.
	@RequestMapping("logout_proc.go") 
	public String goLogout(HttpServletRequest request,
							HttpServletResponse response) {
		Authentication auth 
    	= SecurityContextHolder.getContext().getAuthentication();
    
    log.info("auth : "+auth);
    
    // logout !
    if (auth != null) {    
        new SecurityContextLogoutHandler().logout(request, response, auth);
    }
		return "auth/logout";
	}
	
	//유저 서비스
	
	//비밀번호 변경
	//인코딩된 값을 비교합니다. 
	
	//유저 프로필 사진 변경 
	@RequestMapping("user/profile.do")
	public String changeProfilePicture() {
		
		return "";
	}
}
