package com.excilys.cdb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.dao.UserRepository;
import com.excilys.cdb.dto.UserDTO;
import com.excilys.cdb.exception.UserAlreadyExistException;
import com.excilys.cdb.mapper.UserMapper;
import com.excilys.cdb.model.User;

@Service
@Transactional
public class UserService {
	
	private UserRepository userRepository;
	private UserMapper userMapper;
		
	public UserService(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	public User registerNewUserAccount(UserDTO userDTO) throws UserAlreadyExistException {
		
		if (userExist(userDTO.getFirstName(),userDTO.getLastName())) {
            throw new UserAlreadyExistException("This user " + userDTO.getFirstName() + " " + userDTO.getLastName() + " already exists");
        }
		
		return userRepository.save(this.userMapper.toUser(userDTO));
	}
	
	private boolean userExist(String firstName, String lastName) {
        return userRepository.findByFirstName(firstName) != null && userRepository.findByLastName(lastName) != null;
    }
	
    private static List<GrantedAuthority> getAuthorities (List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

}
