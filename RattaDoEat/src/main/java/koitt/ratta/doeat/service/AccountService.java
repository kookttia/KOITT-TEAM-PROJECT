package koitt.ratta.doeat.service;

import org.springframework.stereotype.Service;

import koitt.ratta.doeat.domain.AccountDto;
import koitt.ratta.doeat.domain.AccountEntity;

@Service
public interface AccountService {

	/**
	 * 유저 저장, 로그인
	 */
	public void saveUser(AccountDto user) throws Exception;

	/**
	 * 유저 이메일 아이디로 엔티티 불러오기.
	 * @param userId
	 */
	public AccountEntity findByuserId(String userId) throws Exception;


}
