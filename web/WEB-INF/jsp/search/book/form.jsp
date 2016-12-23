<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html title="Поиск книг">
	<H2>Поиск книг</H2>
	<c:url value="/search/book/result.html" var="searchBookResultUrl"/>
	<FORM action="${searchBookResultUrl}" method="post">
		<LABEL for="inventoryNumber">Поиск по шифру:</LABEL>
		<INPUT type="text" id="inventoryNumber" name="inventoryNumber" class="search">
		<BUTTON type="submit" class="search">Найти</BUTTON>
	</FORM>
	<HR>
	<FORM action="${searchBookResultUrl}" method="post">
		<LABEL for="search">Поиск по названию:</LABEL>
		<INPUT type="text" id="search" name="search" class="search">
		<BUTTON type="submit" class="search">Найти</BUTTON>
	</FORM>
	<HR>
	<P><STRONG>Поиск по автору:</STRONG></P>
	<OL>
		<c:if test="${not empty numberOfBooksWithoutAuthor}">
			<LI>
				<A href="${searchBookResultUrl}">Без автора</A> (${numberOfBooksWithoutAuthor})
			</LI>
		</c:if>
		<c:forEach items="${authors}" var="author">
			<LI>
				<A href="${searchBookResultUrl}" onclick="return submitFormById('form-${author.key.identity}')">${author.key.surname} ${author.key.name} ${author.key.patronymic}</A> (${author.value})
				<FORM id="form-${author.key.identity}" action="${searchBookResultUrl}" method="post">
					<INPUT type="hidden" name="identity" value="${author.key.identity}">
				</FORM>
			</LI>
		</c:forEach>
	</OL>
</u:html>