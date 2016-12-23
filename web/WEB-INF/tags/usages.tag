<%@tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="usages" required="true" rtexprvalue="true" type="java.util.Collection"%>
<%@attribute name="spec" required="false" rtexprvalue="true" type="java.lang.Boolean"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<c:if test="${not empty spec and spec}">
	<c:set var="classname" value="special"/>
</c:if>
<c:url value="/author/book/usages.html" var="bookUsagesUrl"/>
<c:forEach items="${usages}" var="usage">
	<TR onclick="submitFormById('form-${usage.book.identity}')" class="${classname}">
		<TD>
			<c:choose>
				<c:when test="${not empty usage.book.author}">
					<u:person value="${usage.book.author}"/>
				</c:when>
				<c:otherwise>
					Без автора
				</c:otherwise>
			</c:choose>
		</TD>
		<TD>«${usage.book.title}»
			<FORM action="${bookUsagesUrl}" id="form-${usage.book.identity}" method="post">
				<INPUT type="hidden" name="identity" value="${usage.book.identity}">
			</FORM>
		</TD>
		<TD><fmt:formatDate value="${usage.deliveryDate}" pattern="dd.MM.yyyy"/></TD>
		<TD><fmt:formatDate value="${usage.planReturnDate}" pattern="dd.MM.yyyy"/></TD>
		<TD>
			<c:choose>
				<c:when test="${not empty usage.returnDate}">
					<fmt:formatDate value="${usage.returnDate}" pattern="dd.MM.yyyy"/>
				</c:when>
				<c:otherwise>
					не возвращена
				</c:otherwise>
			</c:choose>
		</TD>
	</TR>
</c:forEach>
<c:remove var="bookUsagesUrl"/>
<c:remove var="classname"/>
