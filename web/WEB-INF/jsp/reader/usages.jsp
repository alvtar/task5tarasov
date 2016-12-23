<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html title="Читатель" message="${message}">
	<H2>Читатель</H2>
	<TABLE>
		<TR>
			<TH>Фамилия</TH>
			<TD>${reader.surname}</TD>
		</TR>
		<TR>
			<TH>Имя</TH>
			<TD>${reader.name}</TD>
		</TR>
		<TR>
			<TH>Отчество</TH>
			<TD>${reader.patronymic}</TD>
		</TR>
		<TR>
			<TH>Адрес</TH>
			<TD>${reader.address}</TD>
		</TR>
		<TR>
			<TH>Телефон</TH>
			<TD>${reader.phone}</TD>
		</TR>
	</TABLE>
	<c:if test="${not empty reader.overdueUsages}">
		<c:set var="disabled" value="disabled"/>
	</c:if>
	<c:url value="/author/book/deliver.html" var="bookDeliverUrl"/>
	<FORM action="${bookDeliverUrl}" method="post">
		<INPUT type="hidden" name="readerIdentity" value="${reader.identity}">
		<BUTTON type="submit" ${disabled}>Выдать книгу</BUTTON>
	</FORM>
	<c:choose>
		<c:when test="${not empty reader.returnedUsages or not empty reader.currentUsages or not empty reader.overdueUsages}">
			<H2>Абонемент читателя</H2>
			<TABLE>
				<TR>
					<TH>Автор</TH>
					<TH>Название</TH>
					<TH>Дата выдачи</TH>
					<TH>Планируемая дата возврата</TH>
					<TH>Фактическая дата возврата</TH>
				</TR>
				<u:usages usages="${reader.overdueUsages}" spec="true"/>
				<u:usages usages="${reader.currentUsages}"/>
				<u:usages usages="${reader.returnedUsages}"/>
			</TABLE>
		</c:when>
		<c:otherwise>
			<P>Читатель не брал ни одной книги</P>
		</c:otherwise>
	</c:choose>
</u:html>