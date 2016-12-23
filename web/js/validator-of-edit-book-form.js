function validateEditBook(form) {
	if(!validateForm(form, [{
		id: "inventoryNumber",
		message: "Поле «Инвентарный номер» должно быть задано в формате АБ###### или ЧЗ######<BR>(# - обязательная цифра)",
		checker: checkInventoryNumber
	}, {
		id: "title",
		message: "Поле «Название» не заполнено",
		checker: checkNotEmpty
	}, {
		id: "year",
		message: "Поле «Год издания» не заполнено",
		checker: checkNotEmpty
	}, {
		id: "year",
		message: "Поле «Год издания» должно быть целым положительным числом",
		checker: checkPositiveInteger
	}])) {
		return false;
	}
	return true;
}

function checkInventoryNumber(value) {
	return checkRegexp(value, "^(АБ|ЧЗ)\\d{6}$");
}