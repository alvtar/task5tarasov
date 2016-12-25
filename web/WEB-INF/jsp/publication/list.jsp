<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html title="Издания" message="${message}">
	<H2>Список периодических изданий</H2>
	<TABLE>
		<TR>
            
			<TH>Индекс</TH>
			<TH>Наименование</TH>
            <TH>Цена подписки за месяц, руб</TH>
			<TH>Доступно</TH>

		</TR>
		<c:url value="/index.html" var="publicationListUrl"/>
		<c:forEach items="${publications}" var="publication">

			<TR onclick="submitFormById('form-${publication.id}')" class="${classname}">
				<TD>
					${publication.issn}
					<FORM id="form-${publication.id}" action="${publicationListUrl}" method="post">
						<INPUT type="hidden" name="id" value="${publication.id}">
					</FORM>
				</TD>
				
                <TD>${publication.title}</TD>
                <TD>${publication.monthCost}</TD>
				<TD>${publication.active}</TD>
				
			</TR>
		</c:forEach>
	</TABLE>
	<FORM action="${publicationListUrl}" method="post">
		<BUTTON type="submit">Добавить</BUTTON>
	</FORM>
</u:html>