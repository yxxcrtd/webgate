<input type="hidden" id="ctx" value="${pageContext.request.contextPath}"/>
<%
    Cookie[] cookies = request.getCookies();
    String[] cookieValueArray = null;
    String cookieName = null;
    String cookieValue = null;

    if (cookies != null) {
        for(int i=0;i<cookies.length;i++) {
            Cookie cookie = cookies[i];
            cookieName = cookie.getName();
            if ("username".equals(cookieName)) {
                cookieValue = cookie.getValue();
            }
        }
    }
    if (cookieName != null && cookieValue != null) {
        out.println("<input type='hidden' id='cookieName' value='" + cookieName + "'/>");
        out.println("<input type='hidden' id='cookieValue' value='" + cookieValue + "'/>");
    }
%>