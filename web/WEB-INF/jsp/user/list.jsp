<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html title="сотрудники" message="${message}">
	<H2>Список сотрудников</H2>
	<TABLE>
		<TR>
			<TH>Имя пользователя</TH>
			<TH>Роль</TH>
		</TR>
		<c:url value="/user/edit.html" var="userEditUrl"/>
		<c:forEach items="${users}" var="user">
			<TR onclick="submitFormById('form-${user.identity}')">
				<TD>
					${user.login}
					<FORM id="form-${user.identity}" action="${userEditUrl}" method="post">
						<INPUT type="hidden" name="identity" value="${user.identity}">
					</FORM>
				</TD>
				<TD>${user.role.name}</TD>
			</TR>
		</c:forEach>
	</TABLE>
	<FORM action="${userEditUrl}" method="post">
		<BUTTON type="submit">Добавить</BUTTON>
	</FORM>
</u:html>