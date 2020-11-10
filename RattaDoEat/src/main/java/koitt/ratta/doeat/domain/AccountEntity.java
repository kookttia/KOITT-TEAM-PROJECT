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
 * 
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
	
	
	@Id //해당필드 pk설정
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "USER_INFO_SEQ_GEN")
	@Column(name="u_idx")
	private BigDecimal uIdx; //long 에서 빅데시마로 변경, 오라클 오류 대비.
	
	
	@Column(name="user_id")
	private String userId;
	@Column
	private String name;
	@Column
	private String nickname;
	@Column
	private Date regdate;
	@Column
	private String ip;
	@Column
	private int social;
	@Column
	private String phone;
	@Column
	private Date birth;
	@Column
	private String address;
	
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
	}
	
	
	
}
