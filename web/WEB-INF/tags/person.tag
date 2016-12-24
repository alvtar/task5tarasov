<%@tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="value" required="true" rtexprvalue="true" type="domain.User"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

${value.fullName}&nbsp;${fn:substring(value.zipCode, 0, 1)}.&nbsp;${fn:substring(value.address, 0, 1)}.