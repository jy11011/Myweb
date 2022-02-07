<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ include file ="../include/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
	

	<div class="container">
		<h3>My Web게시판</h3>
		
		<div>
			<select onchange="handleChange(this)">
				<option value = "10"  ${pagevo.amount eq 10 ? 'selected' : '' }>10개씩 보기</option>
				<option value = "20"  ${pagevo.amount eq 20 ? 'selected' : '' }>20개씩 보기</option>
				<option value = "50"  ${pagevo.amount eq 50 ? 'selected' : '' }>50개씩 보기</option>
				<option value = "100" ${pagevo.amount eq 100 ? 'selected' : '' }>100개씩 보기</option>
			</select>
		</div>
		
		
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>글 번호</th>
					<th>작성자</th>
					<th>제목</th>
					<th>날짜</th>
					<th>조회수</th>
				</tr>
			</thead>
			
			
			<c:forEach var="vo" items="${list}" >
			<tbody>
				<tr>
					<td>${vo.bno}</td>
					<td>${vo.writer }</td>
					<td><a href = "content.board?bno=${vo.bno }">${vo.title }</a></td>
					<td>
					<fmt:formatDate value="${vo.regdate }" pattern="yyyy년MM월dd일 HH시mm분"/>
					</td>
					<td>${vo.hit }</td>
				</tr>
			</tbody>
			</c:forEach>
			
			<tbody>
				<tr>
					<td colspan="5" align="right">
						<form action="" class="form-inline" >
						  <div class="form-group">
						    <input type="text" name="search" placeholder="제목검색" class="form-control" >
						  	<input type="submit" value="검색" class="btn btn-default">
						  	<c:if test="${sessionScope.uservo.id !=null }">
							<input type="button" value="글 작성" class="btn btn-default" onclick = "location.href='writer.board'">
						  	</c:if>
						  </div>
						</form> 
					</td>
				</tr>
			</tbody>
			
			<tbody>
				<tr>
					<td colspan = "5" align = "center">
						<ul class="pagination">
							
							<!-- 2.이전버튼 활성화 -->
							<c:if test="${pagevo.prev }">
								<li><a href="list.board?pageNum=${pagevo.startPage - 1 }&amount=${pagevo.amount}">prev</a></li>
							</c:if>
							
							<!-- 1.페이지 번호처리 -->
							<c:forEach var="num" begin="${pagevo.startPage }" end ="${pagevo.endPage }">
  							<li class="${pagevo.pageNum eq num ? 'active':'' }">
  								<a href="list.board?pageNum=${num }&amount=${pagevo.amount}">${num }</a>
  							</li>
  							</c:forEach>
  							
  							<!-- 3.다음버튼 활성화 -->
  							<c:if test="${pagevo.next }">
  							<li><a href="list.board?pageNum=${pagevo.endPage + 1 }&amount=${pagevo.amount}">next</a></li>
  							</c:if>
						</ul>						
					</td>
				</tr>
			</tbody>
			
			
		</table>
	</div>
	
	<script>
		//js코드
		function handleChange(a){
			//console.log(a); //출력문
			//console.log(a.value);
			location.href = 'list.board?pageNum=1&amount='+a.value;
		}
	
	</script>
	
<%@ include file ="../include/footer.jsp" %>





