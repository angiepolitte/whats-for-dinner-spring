//package com.angie.whats_for_dinner;
//
//
//import com.angie.whats_for_dinner.controllers.AuthenticationController;
//import com.angie.whats_for_dinner.data.UserRepository;
//import com.angie.whats_for_dinner.models.User;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//
//public class AuthenticationFilter implements HandlerInterceptor {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    AuthenticationController authenticationController;
//
//    private static final List<String> whitelist = Arrays.asList("/login", "/register", "/logout", "/css");
//
//    //a way to check whether or not a given request is whitelisted
//    private static boolean isWhitelisted(String path) {
//        for (String pathRoot : whitelist) {
//            if (path.startsWith(pathRoot)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    // check all requests against the whitelist within preHandle
//    //preHandle
//
//    @Override
//    public boolean preHandle(HttpServletRequest request,
//                             HttpServletResponse response,
//                             Object handler) throws IOException {
//
//        // Don't require sign-in for whitelisted pages
//        if (isWhitelisted(request.getRequestURI())) {
//            // returning true indicates that the request may proceed
//            return true;
//        }
//
//        HttpSession session = request.getSession();
//        User user = authenticationController.getUserFromSession(session);
//
//        // The user is logged in
//        if (user != null) {
//            return true;
//        }
//
//        // The user is NOT logged in
//        response.sendRedirect("/login");
//        return false;
//    }
//}
