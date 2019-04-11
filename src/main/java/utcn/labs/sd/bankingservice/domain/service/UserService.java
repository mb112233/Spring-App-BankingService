package utcn.labs.sd.bankingservice.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import utcn.labs.sd.bankingservice.domain.data.converter.UserConverter;
import utcn.labs.sd.bankingservice.domain.data.entity.ActivityReport;
import utcn.labs.sd.bankingservice.domain.data.entity.User;
import utcn.labs.sd.bankingservice.domain.data.entity.enums.UserType;
import utcn.labs.sd.bankingservice.domain.data.repository.UserRepository;
import utcn.labs.sd.bankingservice.domain.dto.UserDTO;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	
	public List<UserDTO> getAllEmployee() {
		List<User> userList=userRepository.findAll();
		List<User> goodList=new ArrayList();
		for(User u:userList) {
			if (u.getUserType()==UserType.EMPLOYEE) {
				goodList.add(u);
			}
		}
		return UserConverter.toDto(goodList);
	}

	public UserDTO getEmployeeById(Integer userId) throws Exception {
		User user = userRepository.findById(userId).orElse(null);
		if (user == null)
			throw new NotFoundException("No employee found with that userId");
		UserType a = user.getUserType();
		if (a == UserType.EMPLOYEE)
			return UserConverter.toDto(user);
		return null;
		
	}
	

	public UserDTO insertEmployee(UserDTO userDto) throws Exception {
		User newuser = null;
		PasswordEncoder pe = new Pbkdf2PasswordEncoder();
		if (userDto.getType() == UserType.EMPLOYEE) {
			User user = new User(userDto.getUserId(), userDto.getUsername(), pe.encode(userDto.getPassword()), userDto.getType(),userDto.getReportList());
			newuser = userRepository.save(user);
		}
		return UserConverter.toDto(newuser);
	}

	public UserDTO updateEmployee(Integer userId, UserDTO userDto) throws Exception {
		User user = userRepository.findById(userId).orElse(null);
		PasswordEncoder pe = new Pbkdf2PasswordEncoder();
		if (user == null) {
			throw new NotFoundException("No user found with that id");
		}
		if (user.getUserType()==UserType.EMPLOYEE) {

			user.setPassword(pe.encode(userDto.getPassword()));
			user.setUsername(userDto.getUsername());
			return UserConverter.toDto(userRepository.save(user));
		}
		return null;
	}

	public void deleteEmployee(Integer userId) throws Exception {
		User user = userRepository.findById(userId).orElse(null);
		if (user == null) {
			throw new NotFoundException("No user with that userId");
		}
		if (user.getUserType()==UserType.EMPLOYEE) {
			userRepository.delete(user);
		}
	}
	 public   List<String> convertListToString(Integer userId) throws NotFoundException{
		 List<String> list=new ArrayList();
		 List<ActivityReport> reportList=new ArrayList();
		 User user = userRepository.findById(userId).orElse(null);
		 if (user == null) {
				throw new NotFoundException("No user with that userId");
			}
			if (user.getUserType()==UserType.EMPLOYEE) {
				reportList=user.getReportList();
				for (ActivityReport i:reportList) {
					list.add(i.toString());
				}
			}
			
		return list;
	 }
	 

}
