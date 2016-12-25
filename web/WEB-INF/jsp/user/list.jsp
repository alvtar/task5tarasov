<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html title="Пользователи" message="${message}">
	<H2>Список пользователей</H2>
	<TABLE>
		<TR>
			<TH>Id</TH>
			<TH>Логин</TH>
			<TH>Пароль</TH>
            <TH>Роль</TH>
			<TH>Ф.И.О.</TH>
			<TH>Почтовый индекс</TH>
			<TH>Адрес</TH>
		</TR>
		<c:url value="/user/edit.html" var="userEditUrl"/>
		<c:forEach items="${users}" var="user">
     <%--       //<c:choose>
				//<c:when test="${not empty user.overdueUsages}">
				//	<c:set var="classname" value="special"/>
				//</c:when>
				//<c:otherwise>
				//	<c:remove var="classname"/>
				//</c:otherwise>
			//</c:choose>--%>
			<TR onclick="submitFormById('form-${user.id}')" class="${classname}">
				<TD>
					${user.id}
					<FORM id="form-${user.id}" action="${userEditUrl}" method="post">
						<INPUT type="hidden" name="id" value="${user.id}">
					</FORM>
				</TD>
				<TD>${user.login}</TD>
                <TD>${user.password}</TD>
                <TD>${user.role.name}</TD>
				<TD>${user.fullName}</TD>
				<TD>${user.zipCode}</TD>
                <TD>${user.address}</TD>
				
			</TR>
		</c:forEach>
	</TABLE>
	<FORM action="${userEditUrl}" method="post">
		<BUTTON type="submit">Добавить</BUTTON>
	</FORM>
</u:html>