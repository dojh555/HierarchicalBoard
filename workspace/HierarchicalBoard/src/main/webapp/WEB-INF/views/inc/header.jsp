<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="inner_wrap clear">
        <ul class="gnb_top_ul clear">
        	<c:if test="${empty memNum}">
            <li class="gnb_top_li">
                <a href="/member/joinForm" class="gnb_top_a">join</a>
            </li>
            <li class="gnb_top_li">
                <a href="/member/loginForm" class="gnb_top_a">login</a>
            </li>
            </c:if>
            
            <c:if test="${!empty memNum}">
            <li class="gnb_top_li">
                <a href="/member/logout" class="gnb_top_a">logout</a>
            </li>
            <li class="gnb_top_li">
                <a href="/member/info" class="gnb_top_a">mypage</a>
            </li>
            </c:if>
        </ul>
        <nav class="gnb clear">
            <h1 class="logo">
                <a href="/" class="logo_link">
                    <img src="/resources/imgs/logo.png" alt="logo">
                </a>
            </h1>
            <ul class="gnb_ul clear">
                <li class="gnb_li">
                    <a href="/board/list" class="gnb_a">
                        일반 게시판
                    </a>
                </li>
<!--                 <li class="gnb_li">
                    <a href="/gal-list" class="gnb_a">
                        갤러리 게시판1
                    </a>
                </li>
                <li class="gnb_li">
                    <a href="/notice-list" class="gnb_a">
                        공지사항
                    </a>
                </li>
                <li class="gnb_li">
                    <a href="/qna-list" class="gnb_a">
                        Q&A
                    </a>
                </li>
                <li class="gnb_li">
                    <a href="/faq" class="gnb_a">
                        FAQ
                    </a>
                </li> -->
            </ul>
        </nav>
    </div>