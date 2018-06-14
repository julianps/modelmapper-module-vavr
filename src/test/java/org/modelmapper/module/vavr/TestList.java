package org.modelmapper.module.vavr;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.module.vavr.testdata.CompanyGroup;
import org.modelmapper.module.vavr.testdata.Employee;
import org.modelmapper.module.vavr.testdata.FriendsGroup;
import org.modelmapper.module.vavr.testdata.MidSizeCompany;
import org.modelmapper.module.vavr.testdata.Person;

import io.vavr.collection.List;

/**
 * @author Julian Stücker
 */
public class TestList {

	private ModelMapper modelMapper;

	@Before
	public void init() {
		modelMapper = new ModelMapper();
		modelMapper.registerModule(new VavrModule());
	}

	@Test
	public void testSimpleList() {
		FriendsGroup friendsGroup = getFriendsGroup();
		CompanyGroup companyGroup = modelMapper.map(friendsGroup, CompanyGroup.class);
		checkSeqResult(companyGroup);
	}

	@Test
	public void testDerivedList() {
		FriendsGroup friendsGroup = getFriendsGroup();
		MidSizeCompany midSizeCompany = modelMapper.map(friendsGroup, MidSizeCompany.class);
		checkSeqResult(midSizeCompany);
	}

	private <T extends CompanyGroup> void checkSeqResult(T companyGroup) {
		assertNotNull(companyGroup.persons);
		assertNotNull(companyGroup.persons.get(0));
		assertNotNull(companyGroup.persons.get(1));
		assertTrue(companyGroup.persons.get(0) instanceof Employee);
		assertTrue(companyGroup.persons.get(1) instanceof Employee);
		assertTrue(companyGroup.getPersons().get(0).getYearsOfExperience() == 2);
		assertTrue(companyGroup.getPersons().get(1).getYearsOfExperience() == 5);
	}

	private FriendsGroup getFriendsGroup() {
		Person person1 = Person.of(2);
		Person person2 = Person.of(5);
		return FriendsGroup.of(List.of(person1, person2));
	}

}
