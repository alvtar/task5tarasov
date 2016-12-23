function validateEditAuthor(form) {
	if(!validateForm(form, [{
		id: "surname",
		message: "Поле «Фамилия» не заполнено",
		checker: checkNotEmpty
	}, {
		id: "name",
		message: "Поле «Имя» не заполнено",
		checker: checkNotEmpty
	}, {
		id: "patronymic",
		message: "Поле «Отчество» не заполнено",
		checker: checkNotEmpty
	}, {
		id: "yearOfBirth",
		message: "Поле «Год рождения» не заполнено",
		checker: checkNotEmpty
	}, {
		id: "yearOfBirth",
		message: "Поле «Год рождения» должно быть целым положительным числом",
		checker: checkPositiveInteger
	}, {
		id: "yearOfDeath",
		message: "Поле «Год смерти» должно быть целым положительным числом",
		checker: checkEmptyOrPositiveInteger
	}])) {
		return false;
	}
	if(checkNotEmpty(form["yearOfDeath"].value) && parseInt(form["yearOfDeath"].value, 10) <= parseInt(form["yearOfBirth"].value, 10)) {
		errorMessage(form["yearOfDeath"].value, "Значение поля «Год смерти» должно быть больше значения поля «Год рождения»");
		return false;
	}
	return true;
}
