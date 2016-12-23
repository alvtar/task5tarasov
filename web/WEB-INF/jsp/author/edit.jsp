<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:choose>
	<c:when test="${not empty author}">
		<c:set var="surname" value="${author.surname}"/>
		<c:set var="name" value="${author.name}"/>
		<c:set var="patronymic" value="${author.patronymic}"/>
		<c:set var="yearOfBirth" value="${author.yearOfBirth}"/>
		<c:set var="yearOfDeath" value="${author.yearOfDeath}"/>
		<c:set var="title" value="Автор ${author.surname} ${author.name} ${author.patronymic}"/>
	</c:when>
	<c:otherwise>
		<c:set var="title" value="Новый автор"/>
	</c:otherwise>
</c:choose>
<u:html title="${title}" validator="validator-of-edit-author-form.js">
	<H2>${title}</H2>
	<c:url value="/author/save.html" var="authorSaveUrl"/>
	<FORM action="${authorSaveUrl}" method="post" onsubmit="return validateEditAuthor(this)">
		<c:if test="${not empty author}">
			<INPUT type="hidden" name="identity" value="${author.identity}">
		</c:if>
		<LABEL for="surname">Фамилия:</LABEL>
		<INPUT type="text" id="surname" name="surname" value="${surname}">
		<LABEL for="name">Имя:</LABEL>
		<INPUT type="text" id="name" name="name" value="${name}">
		<LABEL for="patronymic">Отчество:</LABEL>
		<INPUT type="text" id="patronymic" name="patronymic" value="${patronymic}">
		<LABEL for="yearOfBirth">Год рождения:</LABEL>
		<INPUT type="text" id="yearOfBirth" name="yearOfBirth" value="${yearOfBirth}">
		<LABEL for="yearOfDeath">Год смерти:</LABEL>
		<INPUT type="text" id="yearOfDeath" name="yearOfDeath" value="${yearOfDeath}">
		<BUTTON type="submit">Сохранить</BUTTON>
		<c:if test="${not empty author}">
			<c:if test="${not empty books}">
				<c:set var="disabled" value="disabled"/>
			</c:if>
			<BUTTON type="button" onclick="submitFormById('form-delete')" ${disabled}>Удалить</BUTTON>
		</c:if>
		<BUTTON type="reset">Сбросить</BUTTON>
	</FORM>
	<c:if test="${not empty author}">
		<c:url value="/author/delete.html" var="authorDeleteUrl"/>
		<FORM action="${authorDeleteUrl}" method="post" id="form-delete" onsubmit="return confirmation(this, 'Вы уверены, что хотите удалить автора?')">
			<INPUT type="hidden" name="identity" value="${author.identity}">
		</FORM>
	</c:if>
</u:html>