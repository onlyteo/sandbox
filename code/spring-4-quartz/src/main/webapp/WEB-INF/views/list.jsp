<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Batch list</title>
</head>
<body>
<h1>Batch list</h1>
<ul>
    <c:forEach var="batchGroup" items="${batchGroups}">
        <li>${batchGroup.name}</li>
        <ul>
            <c:forEach var="batchJob" items="${batchGroup.batchJobs}">
                <c:set var="jobDetail" value="${batchJob.jobDetail}"/>
                <c:set var="jobKey" value="${jobDetail.key}"/>
                <c:set var="batchTriggers" value="${batchJob.batchTriggers}"/>
                <li>${jobKey.name}</li>
                <ul>
                    <c:forEach var="batchTrigger" items="${batchTriggers}">
                        <c:set var="trigger" value="${batchTrigger.trigger}"/>
                        <li>${trigger.key.name} (${batchTrigger.state}) [${trigger.previousFireTime} > ${trigger.nextFireTime}]</li>
                    </c:forEach>
                </ul>
            </c:forEach>
        </ul>
    </c:forEach>
</ul>
</body>
</html>
