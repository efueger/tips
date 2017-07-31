<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<h1>Teams</h1>
<ul class="demo-list-item mdl-list">
  <c:forEach items="${teams}" var="team">
  <li class="mdl-list__item">
    <span class="mdl-list__item-primary-content">
      ${fn:escapeXml(team.city())} ${fn:escapeXml(team.mascot())}
    </span>
  </li>
  </c:forEach>
</ul>
