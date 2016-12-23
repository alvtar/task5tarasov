<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:if test="${not empty book}">
	<c:set var="inventoryNumber" value="${book.inventoryNumber}"/>
	<c:set var="title" value="${book.title}"/>
	<c:set var="year" value="${book.year}"/>
	<c:if test="${not empty book.author}">
		<c:set var="selectedAuthorIdentity" value="${book.author.identity}"/>
	</c:if>
</c:if>
<u:html title="Книга" message="${message}" validator="validator-of-edit-book-form.js">
	<H2>Книга</H2>
	<c:url value="/author/book/save.html" var="authorBookSaveUrl"/>
	<FORM action="${authorBookSaveUrl}" method="post" onsubmit="return validateEditBook(this)">
		<c:if test="${not empty book}">
			<INPUT type="hidden" name="identity" value="${book.identity}">
		</c:if>
		<LABEL for="inventoryNumber">Инвентарный номер:</LABEL>
		<INPUT type="text" id="inventoryNumber" name="inventoryNumber" value="${inventoryNumber}">
		<LABEL for="author">Автор:</LABEL>
		<SELECT id="author" name="author">
			<OPTION value="">Без автора</OPTION>
			<c:forEach items="${authors}" var="author">
				<c:choose>
					<c:when test="${not empty author.yearOfDeath}">
						<c:set var="yearsOfLife" value="(${author.yearOfBirth}&nbsp;&mdash; ${author.yearOfDeath})"/>
					</c:when>
					<c:otherwise>
						<c:set var="yearsOfLife" value="(${author.yearOfBirth})"/>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${author.identity == selectedAuthorIdentity}">
						<c:set var="selected" value="selected"/>
					</c:when>
					<c:otherwise>
						<c:remove var="selected"/>
					</c:otherwise>
				</c:choose>
				<OPTION value="${author.identity}" ${selected}><u:person value="${author}"/> ${yearsOfLife}</OPTION>
			</c:forEach>
		</SELECT>
		<LABEL for="title">Название:</LABEL>
		<INPUT type="text" id="title" name="title" value="${title}">
		<LABEL for="year">Год издания:</LABEL>
		<INPUT type="text" id="year" name="year" value="${year}">
		<BUTTON type="submit">Сохранить</BUTTON>
		<c:if test="${not empty book}">
			<c:if test="${not empty book.currentUsage or not empty book.usages}">
				<c:set var="disabled" value="disabled"/>
			</c:if>
			<BUTTON type="button" onclick="submitFormById('form-delete')" ${disabled}>Удалить</BUTTON>
		</c:if>
		<BUTTON type="reset">Сбросить</BUTTON>
	</FORM>
	<c:if test="${not empty book}">
		<c:url value="/author/book/delete.html" var="authorBookDeleteUrl"/>
		<FORM action="${authorBookDeleteUrl}" method="post" id="form-delete" onsubmit="return confirmation(this, 'Вы уверены, что хотите удалить книгу?')">
			<INPUT type="hidden" name="identity" value="${book.identity}">
		</FORM>
	</c:if>
</u:html>