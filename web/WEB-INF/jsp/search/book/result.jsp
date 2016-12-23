<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html title="Результат поиска книг">
	<H2>Результат поиска книг</H2>
	<c:url value="/author/book/usages.html" var="bookUsagesUrl"/>
	<c:choose>
		<c:when test="${not empty books}">
			<TABLE>
				<TR>
					<TH>Шифр</TH>
					<TH>Автор</TH>
					<TH>Название</TH>
					<TH>Год издания</TH>
				</TR>
				<c:forEach items="${books}" var="book">
					<c:choose>
						<c:when test="${not empty book.currentUsage}">
							<c:set var="classname" value="special"/>
						</c:when>
						<c:otherwise>
							<c:remove var="classname"/>
						</c:otherwise>
					</c:choose>
					<TR onclick="submitFormById('form-${book.identity}')" class="${classname}">
						<TD>${book.inventoryNumber}
							<FORM action="${bookUsagesUrl}" id="form-${book.identity}" method="post">
								<INPUT type="hidden" name="identity" value="${book.identity}">
							</FORM>
						</TD>
						<TD>
							<c:choose>
								<c:when test="${not empty book.author}">
									<u:person value="${book.author}"/>
								</c:when>
								<c:otherwise>
									Без автора
								</c:otherwise>
							</c:choose>
						</TD>
						<TD>${book.title}</TD>
						<TD>${book.year}</TD>
					</TR>
				</c:forEach>
			</TABLE>
		</c:when>
		<c:otherwise>
			<P>По запросу ничего не найдено</P>
		</c:otherwise>
	</c:choose>
</u:html>