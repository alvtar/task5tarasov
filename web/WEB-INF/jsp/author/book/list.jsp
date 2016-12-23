<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:choose>
	<c:when test="${not empty author}">
		<c:choose>
			<c:when test="${not empty author.yearOfDeath}">
				<c:set var="yearsOfLife" value="(${author.yearOfBirth}&nbsp;&mdash; ${author.yearOfDeath})"/>
			</c:when>
			<c:otherwise>
				<c:set var="yearsOfLife" value="(${author.yearOfBirth})"/>
			</c:otherwise>
		</c:choose>
		<c:set var="title" value="Список книг автора: ${author.surname} ${author.name} ${author.patronymic} ${yearsOfLife}"/>
	</c:when>
	<c:otherwise>
		<c:set var="title" value="Список книг без авторства"/>
	</c:otherwise>
</c:choose>
<u:html title="${title}" message="${message}">
	<H2>${title}</H2>
	<c:if test="${not empty author}">
		<c:url value="/author/edit.html" var="authorEditUrl"/>
		<FORM action="${authorEditUrl}" method="post">
			<INPUT type="hidden" name="identity" value="${author.identity}">
			<BUTTON type="submit">Изменить автора</BUTTON>
		</FORM>
	</c:if>
	<c:url value="/author/book/edit.html" var="authorBookEditUrl"/>
	<c:choose>
		<c:when test="${not empty books}">
			<TABLE>
				<TR>
					<TH>Инвентарный номер</TH>
					<TH>Название</TH>
					<TH>Год издания</TH>
				</TR>
				<c:forEach items="${books}" var="book">
					<TR onclick="submitFormById('form-${book.identity}')">
						<TD>
							${book.inventoryNumber}
							<FORM id="form-${book.identity}" action="${authorBookEditUrl}" method="post">
								<INPUT type="hidden" name="identity" value="${book.identity}">
							</FORM>
						</TD>
						<TD>${book.title}</TD>
						<TD>${book.year}</TD>
					</TR>
				</c:forEach>
			</TABLE>
		</c:when>
		<c:otherwise>
			<P>Список пуст</P>
		</c:otherwise>
	</c:choose>
	<FORM action="${authorBookEditUrl}" method="post">
		<c:if test="${not empty author}">
			<INPUT type="hidden" name="authorIdentity" value="${author.identity}">
		</c:if>
		<BUTTON type="submit">Добавить книгу</BUTTON>
	</FORM>
</u:html>