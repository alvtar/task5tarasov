<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:choose>
	<c:when test="${not empty reader}">
		<c:set var="libraryCardNumber" value="${reader.libraryCardNumber}"/>
		<c:set var="surname" value="${reader.surname}"/>
		<c:set var="name" value="${reader.name}"/>
		<c:set var="patronymic" value="${reader.patronymic}"/>
		<c:set var="address" value="${reader.address}"/>
		<c:set var="phone" value="${reader.phone}"/>
		<c:set var="title" value="Читатель ${reader.surname} ${reader.name} ${reader.patronymic}"/>
	</c:when>
	<c:otherwise>
		<c:set var="title" value="Новый читатель"/>
	</c:otherwise>
</c:choose>
<u:html title="${title}" message="${message}" validator="validator-of-edit-reader-form.js">
	<H2>${title}</H2>
	<c:url value="/reader/save.html" var="readerSaveUrl"/>
	<FORM action="${readerSaveUrl}" method="post" onsubmit="return validateEditReader(this)">
		<c:if test="${not empty reader}">
			<INPUT type="hidden" name="identity" value="${reader.identity}">
		</c:if>
		<LABEL for="libraryCardNumber">Номер читательского билета:</LABEL>
		<INPUT type="text" id="libraryCardNumber" name="libraryCardNumber" value="${libraryCardNumber}">
		<LABEL for="surname">Фамилия:</LABEL>
		<INPUT type="text" id="surname" name="surname" value="${surname}">
		<LABEL for="name">Имя:</LABEL>
		<INPUT type="text" id="name" name="name" value="${name}">
		<LABEL for="patronymic">Отчество:</LABEL>
		<INPUT type="text" id="patronymic" name="patronymic" value="${patronymic}">
		<LABEL for="address">Адрес:</LABEL>
		<INPUT type="text" id="address" name="address" value="${address}">
		<LABEL for="phone">Телефон:</LABEL>
		<INPUT type="text" id="phone" name="phone" value="${phone}">
		<BUTTON type="submit">Сохранить</BUTTON>
		<c:if test="${not empty reader}">
			<c:if test="${not empty reader.returnedUsages or not empty reader.currentUsages or not empty reader.overdueUsages}">
				<c:set var="disabled" value="disabled"/>
			</c:if>
			<BUTTON type="button" onclick="submitFormById('form-delete')" ${disabled}>Удалить</BUTTON>
		</c:if>
		<BUTTON type="reset">Сбросить</BUTTON>
	</FORM>
	<c:if test="${not empty reader}">
		<c:url value="/reader/delete.html" var="readerDeleteUrl"/>
		<FORM action="${readerDeleteUrl}" method="post" id="form-delete" onsubmit="return confirmation(this, 'Вы уверены, что хотите удалить читателя?')">
			<INPUT type="hidden" name="identity" value="${reader.identity}">
		</FORM>
		<c:if test="${not empty reader.overdueUsages}">
			<H2>Список невозвращённых вовремя книг</H2>
			<TABLE>
				<TR>
					<TH>Автор</TH>
					<TH>Название</TH>
					<TH>Дата выдачи</TH>
					<TH>Планируемая дата возврата</TH>
				</TR>
				<c:forEach items="${reader.overdueUsages}" var="usage">
					<TR>
						<TD>
							<c:choose>
								<c:when test="${not empty usage.book.author}">
									<u:person value="${usage.book.author}"/>
								</c:when>
								<c:otherwise>
									Без автора
								</c:otherwise>
							</c:choose>
						</TD>
						<TD>${usage.book.title}</TD>
						<TD><fmt:formatDate value="${usage.deliveryDate}" pattern="dd.MM.yyyy"/></TD>
						<TD><fmt:formatDate value="${usage.planReturnDate}" pattern="dd.MM.yyyy"/></TD>
					</TR>
				</c:forEach>
			</TABLE>
		</c:if>
	</c:if>
</u:html>