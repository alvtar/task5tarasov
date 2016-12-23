<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:choose>
	<c:when test="${not empty user}">
		<c:set var="roleIdentity" value="${user.role.identity}"/>
		<c:set var="login" value="${user.login}"/>
		<c:set var="title" value="Сотрудник «${user.login}»"/>
	</c:when>
	<c:otherwise>
		<c:set var="title" value="Новый сотрудник"/>
	</c:otherwise>
</c:choose>
<u:html title="${title}" message="${message}" validator="validator-of-edit-user-form.js">
	<H2>${title}</H2>
	<c:url value="/user/save.html" var="userSaveUrl"/>
	<FORM action="${userSaveUrl}" method="post" onsubmit="return validateEditUser(this)">
		<c:if test="${not empty user}">
			<INPUT type="hidden" name="identity" value="${user.identity}">
		</c:if>
		<LABEL for="login">Имя пользователя:</LABEL>
		<INPUT type="text" id="login" name="login" value="${login}">
		<LABEL for="role">Роль:</LABEL>
		<SELECT id="role" name="role">
			<c:forEach items="${roles}" var="role">
				<c:remove var="selected"/>
				<c:if test="${not empty roleIdentity and roleIdentity eq role.identity}">
					<c:set var="selected" value="selected"/>
				</c:if>
				<OPTION value="${role.identity}" ${selected}>${role.name}</OPTION>
			</c:forEach>
		</SELECT>
		<BUTTON type="submit">Сохранить</BUTTON>
		<c:if test="${not empty user}">
			<BUTTON type="button" onclick="submitFormById('form-delete')">Удалить</BUTTON>
		</c:if>
		<BUTTON type="reset">Сбросить</BUTTON>
	</FORM>
	<c:if test="${not empty user}">
		<c:url value="/user/delete.html" var="userDeleteUrl"/>
		<FORM action="${userDeleteUrl}" method="post" id="form-delete" onsubmit="return confirmation(this, 'Вы уверены, что хотите удалить сотрудника?')">
			<INPUT type="hidden" name="identity" value="${user.identity}">
		</FORM>
	</c:if>
</u:html>