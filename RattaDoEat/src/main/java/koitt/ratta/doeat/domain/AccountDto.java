package koitt.ratta.doeat.domain;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 회원 정보 DTO
 * 회원 가입할 때, 회원 정보 변경 시에 사용.
 * user_info 테이블
 * @author GW
 *
 */
@NoArgsConstructor
@Data
public class AccountDto {
	/**
	 * user_info 테이블의 u_idx 컬럼 : 유저 인덱스, 시퀀스
	 */
	private BigDecimal uIdx;

	/**
	 * user_info 테이블의 user_id 컬럼 : 유저 아이디
	 */
	private String userId;
	
	/**
	 * users 테이블의 password 컬럼으로 들어갈 인코딩 전 유저의 비밀번호
	 */
	private String userPw;
	
	/**
	 * user_info 테이블의 name 컬럼 : 유저의 실명
	 */
	private String name;
	
	/**
	 * user_info 테이블의 nickname 컬럼 : 유저의 닉네임
	 */
	private String nickname;
	
	/**
	 * user_info 테이블의 regdate 컬럼 : 유저의 회원가입 날짜
	 */
	private Date regdate;
	
	/**
	 * user_info 테이블의 ip 컬럼 : 유저 회원가입 시에 등록되는 아이피, 회원가입시 스팸 방지, 연속으로 가입할 시에 한 아이피로 3개 까지의 아이디 등록 가능.
	 */
	private String ip;
	
	/**
	 * user_info 테이블의 social 컬럼 : 유저의 소셜 아이디 가입 정보, 네이버 n,구글 g 알파벳으로 값을 표현한다.
	 */
	private String social;
	
	/**
	 * user_info 테이블의 phone 컬럼 : 유저의 휴대폰 번호. 하이픈(-)을 포함한다. 010-0000-0000 총 13byte 까지 등록 가능.
	 */
	private String phone;
	
	/**
	 * user_info 테이블의 birth 컬럼 : 유저의 생일
	 */
	private Date birth;
	
	/**
	 * user_info 테이블의 address 컬럼 : 유저의 집 주소
	 */
	private String address;
	
	/**
	 * user_info 테이블의 profile_picture 컬럼 : 유저의 프로필 대표 사진. 기본값으로 NoPictures.jpg 을 사용한다. 
	 */
	private String profilePicture;
	
	//엔티티를 dto로 변환
	@Builder
	public AccountDto(AccountEntity entity) {
		this.uIdx = entity.getUIdx();
		this.userId = entity.getUserId();
		this.name = entity.getName();
		this.nickname = entity.getNickname();
		this.regdate = entity.getRegdate();
		this.ip = entity.getIp();
		this.social = entity.getSocial();
		this.phone = entity.getPhone();
		this.birth = entity.getBirth();
		this.address = entity.getAddress();
		this.profilePicture = entity.getProfilePicture();
	}
	
	
	
}
