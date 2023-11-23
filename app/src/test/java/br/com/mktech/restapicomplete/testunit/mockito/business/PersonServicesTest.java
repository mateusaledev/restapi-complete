package br.com.mktech.restapicomplete.testunit.mockito.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import br.com.mktech.restapicomplete.business.PersonServices;
import br.com.mktech.restapicomplete.data.vo.v1.PersonVO;
import br.com.mktech.restapicomplete.exception.RequiredObjectsNullException;
import br.com.mktech.restapicomplete.model.Person;
import br.com.mktech.restapicomplete.repositories.PersonRepository;
import br.com.mktech.restapicomplete.testunit.mapper.mock.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

	MockPerson input;
	
	@InjectMocks
	private PersonServices service;
	
	@Mock
	PersonRepository repository;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockPerson();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		var result = service.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());
		
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}
	
	@Test
	void testCreate() {
		Person entity = input.mockEntity(1); 
		entity.setId(1L);
		
		Person persisted = entity;
		persisted.setId(1L);
		
		PersonVO vo = input.mockVO(1);
		vo.setId(1L);
		
		when(repository.save(entity)).thenReturn(persisted);
		
		var result = service.create(vo);
		
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());
		
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}
	
	@Test
	void testCreateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectsNullException.class, () -> {
			service.create(null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}


	@Test
	void testUpdate() {
		Person entity = input.mockEntity(1); 
		
		Person persisted = entity;
		persisted.setId(1L);
		
		PersonVO vo = input.mockVO(1);
		vo.setId(1L);
		

		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);
		
		var result = service.update(vo);
		
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());
		
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}
	
	@Test
	void testUpdateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectsNullException.class, () -> {
			service.update(null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testDelete() {
		Person entity = input.mockEntity(1); 
		entity.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		service.delete(1L);
	}
}