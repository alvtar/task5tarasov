<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html title="Результат поиска читателей">
	<H2>Результат поиска читателей</H2>
	<c:url value="/reader/usages.html" var="readerUsagesUrl"/>
	<c:choose>
		<c:when test="${not empty readers}">
			<TABLE>
				<TR>
					<TH>Читательский билет</TH>
					<TH>Фамилия</TH>
					<TH>Имя</TH>
					<TH>Отчество</TH>
					<TH>Адрес</TH>
					<TH>Телефон</TH>
					<TH>Количество просроченных книг</TH>
				</TR>
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
							<FORM id="form-${reader.identity}" action="${readerUsagesUrl}" method="post">
								<INPUT type="hidden" name="identity" value="${reader.identity}">
							</FORM>
						</TD>
						<TD>${reader.surname}</TD>
						<TD>${reader.name}</TD>
						<TD>${reader.patronymic}</TD>
						<TD>${reader.address}</TD>
						<TD>${reader.phone}</TD>
						<TD>${fn:length(reader.overdueUsages)}</TD>
					</TR>
				</c:forEach>
			</TABLE>
		</c:when>
		<c:otherwise>
			<P>По запросу ничего не найдено</P>
		</c:otherwise>
	</c:choose>
</u:html>