<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html title="Книга">
	<H2>Книга</H2>
	<TABLE>
		<TR>
			<TH>Название</TH>
			<TD>«${book.title}»</TD>
		</TR>
		<TR>
			<TH>Автор</TH>
			<TD>
				<c:choose>
					<c:when test="${not empty book.author}">
						${book.author.surname} ${book.author.name} ${book.author.patronymic}
						<c:choose>
							<c:when test="${not empty book.author.yearOfDeath}">
								(${book.author.yearOfBirth}&nbsp;&mdash; ${book.author.yearOfDeath})
							</c:when>
							<c:otherwise>
								(${book.author.yearOfBirth})
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						Без автора
					</c:otherwise>
				</c:choose>
			</TD>
		</TR>
		<TR>
			<TH>Год издания</TH>
			<TD>${book.year}</TD>
		</TR>
	</TABLE>
	<c:choose>
		<c:when test="${empty book.currentUsage}">
			<c:url value="/author/book/deliver.html" var="bookDeliverUrl"/>
			<FORM action="${bookDeliverUrl}" method="post">
				<INPUT type="hidden" name="bookIdentity" value="${book.identity}">
				<BUTTON type="submit">Выдать</BUTTON>
			</FORM>
		</c:when>
		<c:otherwise>
			<c:url value="/author/book/return.html" var="bookReturnUrl"/>
			<FORM action="${bookReturnUrl}" method="post">
				<INPUT type="hidden" name="usageIdentity" value="${book.currentUsage.identity}">
				<BUTTON type="submit">Принять</BUTTON>
			</FORM>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${not empty book.currentUsage or not empty book.usages}">
			<c:url value="/reader/usages.html" var="readerUsagesUrl"/>
			<H2>История использования книги</H2>
			<TABLE>
				<TR>
					<TH>Читатель</TH>
					<TH>Дата выдачи</TH>
					<TH>Планируемая дата возврата</TH>
					<TH>Фактическая дата возврата</TH>
				</TR>
				<c:if test="${not empty book.currentUsage}">
					<TR class="special" onclick="submitFormById('form-${book.currentUsage.reader.identity}')">
						<TD>
							<u:person value="${book.currentUsage.reader}"/>
							<FORM action="${readerUsagesUrl}" id="form-${book.currentUsage.reader.identity}" method="post">
								<INPUT type="hidden" name="identity" value="${book.currentUsage.reader.identity}">
							</FORM>
						</TD>
						<TD><fmt:formatDate value="${book.currentUsage.deliveryDate}" pattern="dd.MM.yyyy"/></TD>
						<TD><fmt:formatDate value="${book.currentUsage.planReturnDate}" pattern="dd.MM.yyyy"/></TD>
						<TD>не возвращена</TD>
					</TR>
				</c:if>
				<c:if test="${not empty book.usages}">
					<c:forEach items="${book.usages}" var="usage">
						<TR onclick="submitFormById('form-${usage.reader.identity}')">
							<TD>
								<u:person value="${usage.reader}"/>
								<FORM action="${readerUsagesUrl}" id="form-${usage.reader.identity}" method="post">
									<INPUT type="hidden" name="identity" value="${usage.reader.identity}">
								</FORM>
							</TD>
							<TD><fmt:formatDate value="${usage.deliveryDate}" pattern="dd.MM.yyyy"/></TD>
							<TD><fmt:formatDate value="${usage.planReturnDate}" pattern="dd.MM.yyyy"/></TD>
							<TD><fmt:formatDate value="${usage.returnDate}" pattern="dd.MM.yyyy"/></TD>
						</TR>
					</c:forEach>
				</c:if>
			</TABLE>
		</c:when>
		<c:otherwise>
			<P>Книга ни разу не выдавалась читателям</P>
		</c:otherwise>
	</c:choose>
</u:html>