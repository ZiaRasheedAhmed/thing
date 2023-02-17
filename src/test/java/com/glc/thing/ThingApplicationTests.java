package com.glc.thing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.BDDMockito.*;

@SpringBootTest
class ThingApplicationTests {
	@Mock
	private ThingRepository thingRepository;
	@InjectMocks
	private ThingServiceImpl thingServiceImpl;
	

	@Test
	void contextLoads() {
	}

// Pojo Testings \\

	@Test 
	void getAndSetThingID(){
		Thing thing = new Thing();
		Long myId = 1L;
		thing.setId(myId);
		assertEquals(myId, thing.getId());
	}
	@Test
	void getAndSetThingName(){
		Thing thing = new Thing();
		String myName = "Laptop";
		thing.setName(myName);
		assertEquals(myName, thing.getName());
	}
	@Test
	void getAndSetThingDescription(){
		Thing thing = new Thing();
		String myDescription = "This is my Laptop";
		thing.setDescription(myDescription);
		assertEquals(myDescription, thing.getDescription());
	}
	@Test
	void getAndSetThingQuantity(){
		Thing thing = new Thing();
		Long myQuantity = 10L;
		thing.setQuantity(myQuantity);
		assertEquals(myQuantity, thing.getQuantity());
	}

// Test all the pojo feilds at once by using @AllArgsConstructor at Thing Model \\
	@Test
	void allArgsConstructorThing(){
		Long myId = 1L;
		String myName = "Laptop";
		String myDescription = "This is my Laptop";
		Long myQuantity = 10L;
		Thing thing = new Thing(myId  ,  myName,  myDescription,  myQuantity);
		assertEquals(myId, thing.getId());
		assertEquals(myName, thing.getName());
		assertEquals(myDescription, thing.getDescription());
		assertEquals(myQuantity, thing.getQuantity());
	}

// Test the Pojo feilds by using the @builder \\
	@Test
	void builderThings(){
		Long myId = 1L;
		String myName = "Laptop";
		String myDescription = "This is my Laptop";
		Long myQuantity = 10L;
		Thing thing = Thing.builder()
								.Id(myId)
								.name(myName)
								.description(myDescription)
								.quantity(myQuantity)
								.build();
		assertEquals(myId, thing.getId());
		assertEquals(myName, thing.getName());
		assertEquals(myDescription, thing.getDescription());
		assertEquals(myQuantity, thing.getQuantity());
	}
	@Test
	void canSaveThings(){
		Long myId = 1L;
		String myName = "Laptop";
		String myDescription = "This is my Laptop";
		Long myQuantity = 10L;
		Thing thing = Thing.builder()
								.Id(myId)
								.name(myName)
								.description(myDescription)
								.quantity(myQuantity)
								.build();
		given(thingRepository.findByName(myName)).willReturn(Optional.empty());
		given(thingRepository.save(thing)).willReturn(thing);
		Thing savedThing =  thingServiceImpl.saveThing(thing);
		assertNotNull(savedThing);
	}
}
