package ru.alan.library.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alan.library.dao.PersonDAO;
import ru.alan.library.models.Person;

@Component
public class PersonValidator implements Validator {

	private final PersonDAO personDAO;

	@Autowired
	public PersonValidator(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Person.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Person person = (Person) o;

		if (personDAO.getPersonByFullName(person.getFull_name()).isPresent())
			errors.rejectValue("fullName", "", "Человек с таким ФИО уже существует");
	}
}
