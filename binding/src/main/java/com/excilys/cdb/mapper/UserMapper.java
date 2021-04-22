package com.excilys.cdb.mapper;

import org.springframework.stereotype.Component;

import com.excilys.cdb.dto.UserDTO;
import com.excilys.cdb.model.Role;
import com.excilys.cdb.model.User;

@Component
public class UserMapper {

	public User toUser(UserDTO userDTO) {

		return new User(userDTO.getFirstName(),userDTO.getLastName(),userDTO.getPassword(),this.parseRoles(userDTO.getRoles()));
	}

	public UserDTO toUserDTO(User user) {

		return new UserDTO(user.getFirstName(),user.getLastName(),user.getPassword(),user.getRoles().name());
	}

	public Role parseRoles(String rolesString){

		switch(rolesString) {
		case "USER":
			System.out.println("user");
			return Role.USER;
		case "ADMIN":
			System.out.println("admin");
			return Role.ADMIN;
		default:
			break;
		}

		return null;
	}

}
