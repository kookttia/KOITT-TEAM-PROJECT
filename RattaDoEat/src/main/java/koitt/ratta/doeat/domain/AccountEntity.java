package koitt.ratta.doeat.domain;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 회원 정보 엔티티
 * user_info 테이블
 * @author GW
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="user_info")
@SequenceGenerator(name="USER_INFO_SEQ_GEN", sequenceName = "USER_INFO_SEQ", initialValue = 1, allocationSize = 1)
public class AccountEntity {
	
	/**
	 * user_info 테이블의 u_idx 컬럼 : 유저 인덱스, 시퀀스
	 */
	@Id //해당필드 pk설정
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "USER_INFO_SEQ_GEN")
	@Column(name="u_idx")
	private BigDecimal uIdx; //long 에서 빅데시마로 변경, 오라클 오류 대비.
	
	/**
	 * user_info 테이블의 user_id 컬럼 : 유저 아이디
	 */
	@Column(name="user_id")
	private String userId;
	
	/**
	 * user_info 테이블의 name 컬럼 : 유저의 실명
	 */
	@Column
	private String name;
	
	/**
	 * user_info 테이블의 nickname 컬럼 : 유저의 닉네임
	 */
	@Column
	private String nickname;
	
	/**
	 * user_info 테이블의 regdate 컬럼 : 유저의 회원가입 날짜
	 */
	@Column
	private Date regdate;
	
	/**
	 * user_info 테이블의 ip 컬럼 : 유저 회원가입 시에 등록되는 아이피, 회원가입시 스팸 방지, 연속으로 가입할 시에 한 아이피로 3개 까지의 아이디 등록 가능.
	 */
	@Column
	private String ip;
	
	/**
	 * user_info 테이블의 social 컬럼 : 유저의 소셜 아이디 가입 정보, 네이버 n,구글 g 알파벳으로 값을 표현한다.
	 */
	@Column
	private String social;
	
	/**
	 * user_info 테이블의 phone 컬럼 : 유저의 휴대폰 번호. 하이픈(-)을 포함한다. 010-0000-0000 총 13byte 까지 등록 가능.
	 */
	@Column
	private String phone;
	
	/**
	 * user_info 테이블의 birth 컬럼 : 유저의 생일
	 */
	@Column
	private Date birth;
	
	/**
	 * user_info 테이블의 address 컬럼 : 유저의 집 주소
	 */
	@Column
	private String address;
	
	/**
	 * user_info 테이블의 profile_picture 컬럼 : 유저의 프로필 대표 사진. 기본값으로 NoPictures.jpg 을 사용한다. 
	 */
	@Column(name = "profile_picture")
	private String profilePicture;
	
	//계정 엔티티 빌더, dto에서 엔티티로 
	@Builder
	public void accountEntityFromDto(AccountDto dto) {
		this.uIdx = dto.getUIdx();
		this.userId = dto.getUserId();
		this.name = dto.getName();
		this.nickname = dto.getNickname();
		this.regdate = dto.getRegdate();
		this.ip = dto.getIp();
		this.social = dto.getSocial();
		this.phone = dto.getPhone();
		this.birth = dto.getBirth();
		this.address = dto.getAddress();
		this.profilePicture = dto.getProfilePicture();
	}
	
	
	
}
