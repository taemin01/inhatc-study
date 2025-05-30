package org.example.BookMarket.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Slf4j
public class AuditingInterceptor implements HandlerInterceptor {
    private String user;
    private String bookId;
    public boolean preHandle(HttpServletRequest request, HttpServletResponse arg1,
                             Object handler) throws Exception {
        if(request.getRequestURI().endsWith("books/add") && request.getMethod().equals("POST")) {
            user = request.getRemoteUser();
            bookId = request.getParameterValues("bookId")[0];
        }
        return true;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception arg3) throws Exception {
        if(request.getRequestURI().endsWith("books/add")) {
            log.warn(String.format("신규등록 도서 ID : %s, 접근자 : %s, 접근시각 : %s", bookId, user, getCurrentTime()));
        }
    }

    private String getCurrentTime() {
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        return formatter.format(calendar.getTime());
    }
}
