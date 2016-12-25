<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:choose>
	<c:when test="${not empty user}">
		<c:set var="id" value="${user.id}"/>
		<c:set var="login" value="${user.login}"/>
		<c:set var="password" value="${user.password}"/>

        
        <c:set var="roleId" value="${user.role.id}"/>
        
		<c:set var="fullName" value="${user.fullName}"/>
		<c:set var="zipCode" value="${user.zipCode}"/>
		<c:set var="address" value="${user.address}"/>
		<c:set var="title" value="Пользователь ${user.fullName} ${user.zipCode} ${user.address}"/>
	</c:when>
	<c:otherwise>
		<c:set var="title" value="Новый пользователь"/>
	</c:otherwise>
</c:choose>
<u:html title="${title}" message="${message}" validator="validator-of-edit-user-form.js">
	<H2>${title}</H2>
	<c:url value="/user/save.html" var="userSaveUrl"/>
	<FORM action="${userSaveUrl}" method="post" onsubmit="return validateEditUser(this)">
		<c:if test="${not empty user}">
			<INPUT type="hidden" name="id" value="${user.id}">
		</c:if>
		<LABEL for="login">Логин:</LABEL>
		<INPUT type="text" id="login" name="login" value="${login}">
		<LABEL for="password">Пароль:</LABEL>
		<INPUT type="text" id="password" name="password" value="${password}">
        
        <LABEL for="role">Роль:</LABEL>
		<INPUT type="text" id="role" name="role" value="${user.role.id}">
        
        
   <%--     <LABEL for="role">Роль:</LABEL>
		<SELECT id="role" name="role">
			<c:forEach items="${roles}" var="role">
				<c:remove var="selected"/>
				<c:if test="${not empty roleId and roleId eq role.id}">
					<c:set var="selected" value="selected"/>
				</c:if>
				<OPTION value="${role.id}" ${selected}>${role.name}</OPTION>
			</c:forEach>
		</SELECT>
        
        
        --%>
        
		<LABEL for="fullName">Ф.И.О.:</LABEL>
		<INPUT type="text" id="fullName" name="fullName" value="${fullName}">
		<LABEL for="zipCode">Индекс:</LABEL>
		<INPUT type="text" id="zipCode" name="zipCode" value="${zipCode}">
        
        <LABEL for="address">Адрес:</LABEL>
		<INPUT type="text" id="address" name="address" value="${address}">
		
		<BUTTON type="submit">Сохранить</BUTTON>
		<c:if test="${not empty user}">
		<%--	<c:if test="${not empty user.returnedUsages or not empty user.currentUsages or not empty user.overdueUsages}">
				<c:set var="disabled" value="disabled"/>
			</c:if> --%>
			<BUTTON type="button" onclick="submitFormById('form-delete')" ${disabled}>Удалить</BUTTON>
		</c:if>
		<BUTTON type="reset">Сбросить</BUTTON>
	</FORM>
	<c:if test="${not empty user}">
		<c:url value="/user/delete.html" var="userDeleteUrl"/>
		<FORM action="${userDeleteUrl}" method="post" id="form-delete" onsubmit="return confirmation(this, 'Вы уверены, что хотите удалить пользователя?')">
			<INPUT type="hidden" name="id" value="${user.id}">
		</FORM>
	<%--	<c:if test="${not empty user.overdueUsages}">
			<H2>Список невозвращённых вовремя книг</H2>
			<TABLE>
				<TR>
					<TH>Автор</TH>
					<TH>Название</TH>
					<TH>Дата выдачи</TH>
					<TH>Планируемая дата возврата</TH>
				</TR>
				<c:forEach items="${user.overdueUsages}" var="usage">
					<TR>
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
						<TD>${usage.book.title}</TD>
						<TD><fmt:formatDate value="${usage.deliveryDate}" pattern="dd.MM.yyyy"/></TD>
						<TD><fmt:formatDate value="${usage.planReturnDate}" pattern="dd.MM.yyyy"/></TD>
					</TR>
				</c:forEach>
			</TABLE>
		</c:if>  --%>
	</c:if>
</u:html>