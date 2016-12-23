<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html title="читатели" message="${message}">
	<H2>Список читателей</H2>
	<TABLE>
		<TR>
			<TH>Читательский билет</TH>
			<TH>Фамилия</TH>
			<TH>Имя</TH>
			<TH>Отчество</TH>
			<TH>Адрес</TH>
			<TH>Телефон</TH>
		</TR>
		<c:url value="/reader/edit.html" var="readerEditUrl"/>
		<c:forEach items="${readers}" var="reader">
			<c:choose>
				<c:when test="${not empty reader.overdueUsages}">
					<c:set var="classname" value="special"/>
				</c:when>
				<c:otherwise>
					<c:remove var="classname"/>
				</c:otherwise>
			</c:choose>
			<TR onclick="submitFormById('form-${reader.identity}')" class="${classname}">
				<TD>
					${reader.libraryCardNumber}
					<FORM id="form-${reader.identity}" action="${readerEditUrl}" method="post">
						<INPUT type="hidden" name="identity" value="${reader.identity}">
					</FORM>
				</TD>
				<TD>${reader.surname}</TD>
				<TD>${reader.name}</TD>
				<TD>${reader.patronymic}</TD>
				<TD>${reader.address}</TD>
				<TD>${reader.phone}</TD>
			</TR>
		</c:forEach>
	</TABLE>
	<FORM action="${readerEditUrl}" method="post">
		<BUTTON type="submit">Добавить</BUTTON>
	</FORM>
</u:html>