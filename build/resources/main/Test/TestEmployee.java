package Test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import utcn.labs.sd.bankingservice.domain.data.entity.enums.UserType;
import utcn.labs.sd.bankingservice.domain.dto.UserDTO;
import utcn.labs.sd.bankingservice.domain.service.UserService;

public class TestEmployee {
	
//	@Autowired
//	UserService m;
//	
//	@Test
//	public void insertEmployeeTest() throws Exception {
//		UserDTO u=new UserDTO(34,"employee3","labsd2019employee3",UserType.EMPLOYEE);
//		m.insertEmployee(u);
//		assert(null!=m.getEmployeeById(34));
//	}
	
	@Test
    public void test01(){
        String maria = "maria";
        Assert.assertEquals(maria.equals("maria"),true);
    }

}
