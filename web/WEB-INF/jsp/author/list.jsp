<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html title="Список авторов" message="${message}">
	<H2>Список авторов</H2>
	<TABLE>
		<TR>
			<TH>Фамилия</TH>
			<TH>Имя</TH>
			<TH>Отчество</TH>
			<TH>Год рождения</TH>
			<TH>Год смерти</TH>
		</TR>
		<c:url value="/author/book/list.html" var="authorBookListUrl"/>
		<TR onclick="submitFormById('form')">
			<TD colspan="5">
				Без автора
				<FORM id="form" action="${authorBookListUrl}" method="post"></FORM>
			</TD>
		</TR>
		<c:forEach items="${authors}" var="author">
			<TR onclick="submitFormById('form-${author.identity}')">
				<TD>
					${author.surname}
					<FORM id="form-${author.identity}" action="${authorBookListUrl}" method="post">
						<INPUT type="hidden" name="identity" value="${author.identity}">
					</FORM>
				</TD>
				<TD>${author.name}</TD>
				<TD>${author.patronymic}</TD>
				<TD>${author.yearOfBirth}</TD>
				<TD>${author.yearOfDeath}</TD>
			</TR>
		</c:forEach>
	</TABLE>
	<c:url value="/author/edit.html" var="authorEditUrl"/>
	<FORM action="${authorEditUrl}" method="post">
		<BUTTON type="submit">Добавить</BUTTON>
	</FORM>
</u:html>