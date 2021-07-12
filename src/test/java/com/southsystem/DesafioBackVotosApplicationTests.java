package com.southsystem;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.southsystem.dto.AssociateDTO;
import com.southsystem.service.AssociateService;

@RunWith(SpringRunner.class)
@SpringBootTest
class AssociateControllerTest {
	
	@Autowired
	private AssociateService associateService;
	
	@Test
	public void deveSalvarAssociate_QuandoCPFCorreto(){
		AssociateDTO newAssociate = new AssociateDTO();
		newAssociate.setCpf("206.836.360-73");
		
		newAssociate = associateService.createAssociate(newAssociate);
		
		assertThat(newAssociate).isNotNull();
		
	}
  
}
