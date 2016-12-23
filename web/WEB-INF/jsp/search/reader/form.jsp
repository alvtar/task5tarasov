<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html title="Поиск читателей">
	<H2>Поиск читателей</H2>
	<c:url value="/search/reader/result.html" var="searchReaderResultUrl"/>
	<FORM action="${searchReaderResultUrl}" method="post">
		<LABEL for="libraryCardNumber">Поиск по номеру читательского билета:</LABEL>
		<INPUT type="text" id="libraryCardNumber" name="libraryCardNumber" class="search">
		<BUTTON type="submit" class="search">Найти</BUTTON>
	</FORM>
	<HR>
	<FORM action="${searchReaderResultUrl}" method="post">
		<LABEL for="search">Поиск по фамилии:</LABEL>
		<INPUT type="text" id="search" name="search" class="search">
		<BUTTON type="submit" class="search">Найти</BUTTON>
	</FORM>
</u:html>