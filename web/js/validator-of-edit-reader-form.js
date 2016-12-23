function validateEditReader(form) {
	return validateForm(form, [{
		id: "libraryCardNumber",
		message: "Поле «Номер читательского билета» должно быть задано в формате ####-##-##-###<BR>(# - обязательная цифра)",
		checker: checkLibraryCardNumber
	}, {
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
		id: "address",
		message: "Поле «Адрес» не заполнено",
		checker: checkNotEmpty
	}, {
		id: "phone",
		message: "Поле «Телефон» должно быть задано в одном из следующих форматов:<BR>+@@# (##) ###-##-##<BR>+@@# (###) ##-##-##<BR>+@@# (####) #-##-##<BR>(# - обязательная цифра, @ - необязательная цифра)",
		checker: checkPhone
	}]);
}

function checkLibraryCardNumber(value) {
	return checkRegexp(value, "^\\d{4}-\\d{2}-\\d{2}-\\d{3}$");
}

function checkPhone(value) {
	return checkRegexp(value, "^\\+\\d{1,3} \\(((\\d{2}\\) \\d{3})|(\\d{3}\\) \\d{2})|(\\d{4}\\) \\d))-\\d{2}-\\d{2}$");
}