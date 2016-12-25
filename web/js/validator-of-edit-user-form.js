function validateEditUser(form) {
	return validateForm(form, [{
		id: "login",
		message: "Поле «Логин» не заполнено",
		checker: checkNotEmpty
	}, {
		id: "fullName",
		message: "Поле «Ф.И.О.» не заполнено",
		checker: checkNotEmpty
	}, {
        id: "role",
		message: "Поле «Роль» не заполнено",
		checker: checkNotEmpty
	}, {
		id: "zipCode",
		message: "Поле «Почтовый индекс» должно содержать 6 цифр",
		checker: checkZipCode
	}, {
		id: "address",
		message: "Поле «Адрес» не заполнено",
		checker: checkNotEmpty
	}]);
}

function checkLibraryCardNumber(value) {
	//return checkRegexp(value, "^\\d{4}-\\d{2}-\\d{2}-\\d{3}$");
    return checkRegexp(value, ".");
}

function checkZipCode(value) {
	return checkRegexp(value, "^\\d{6}$");
}