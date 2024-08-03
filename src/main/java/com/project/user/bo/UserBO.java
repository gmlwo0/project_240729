package com.project.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.user.entity.UserEntity;
import com.project.user.repository.UserRepository;

@Service
public class UserBO {

	@Autowired
	private UserRepository userRepository;
	
	
	// input: login
	// output: UserEntity 채워져 있거나 null
	public UserEntity getUserEntityByLoginId(String loginId) {
		return userRepository.findByLoginId(loginId);
	}
	
	
	//  input: 파라미터 5
	// output: UserEntity
	public UserEntity addUser(String loginId, String password, String name, String email, String phoneNumber) {
				return userRepository.save(UserEntity.builder()
						.loginId(loginId)
						.password(password)
						.name(name)
						.email(email)
						.phoneNumber(phoneNumber)
						.build());
	}
	public UserEntity getUserEntityByLoginIdPassword(String loginId, String password) {
		return userRepository.findByLoginIdAndPassword(loginId, password);
	}
	public UserEntity getUserEntityById(int userId) {
		return userRepository.findById(userId).orElse(null);
	}
}
