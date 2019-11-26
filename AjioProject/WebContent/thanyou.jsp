<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html lang="en">

<head>
<title>AJIO/CHECKOUT</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="icon" href="img/ajio_icon.png" type="image/png">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/iconmonstr-iconic-font.css">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
</head>

<body onload="clrLocalStorage()" style="background-color:#f0eef1;">
	<script>
function clrLocalStorage(){	
	localStorage.clear();	
}
</script>
	<nav class="navbar navbar-expand-lg navbar-light bg-white">
		<a class="navbar-brand" href="index.jsp"> <img
			src="img/ajio_logo.svg" width="60%" height="50"
			class="d-inline-block align-top" alt="">
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<form action="CategoryController" method="post">
				<ul class="navbar-nav mr-auto">

					<li class="nav-item">
						<button type="submit" class="nav-link" name="getProducts"
							value="Formal_Shoes">FORMAL SHOES</button>
					</li>
					<li class="nav-item"><button type="submit" class="nav-link"
							name="getProducts" value="Casual_Shoes">CASUAL SHOES</button></li>
					<li class="nav-item"><button type="submit" class="nav-link"
							name="getProducts" value="Sport_Shoes">SPORT SHOES</button></li>
					<li class="nav-item"><button type="submit" class="nav-link"
							name="getProducts" value="Watches">WATCHES</button></li>
					<li class="nav-item"><button type="submit" class="nav-link"
							name="getProducts" value="Sun_Glasses">SUN GLASSES</button></li>

				</ul>
			</form>

			<ul class="navbar-nav ml-auto">
				<c:choose>
					<c:when test="${session == null}">
						<li class="nav-item"><a class="nav-link" href="#"
							data-toggle="modal" data-target="#login_model">Sign In / Join
								AJIO</a></li>
					</c:when>
					<c:when test="${session != null}">
						<li class="nav-item"><a class="nav-link" href="#">Welcome
								<c:out value="${username}"></c:out>
						</a></li>

						<li class="nav-item"><a class="nav-link"
							href="user?action=logout">Sign Out</a></li>
						<li class="nav-item active"><a class="nav-link" href="#">Home
								<span class="sr-only">(current)</span>
						</a></li>
						<li class="nav-item">
							<button type="button" class="nav-link btn btn-primary"
								data-toggle="modal" data-target="#cart">
								<i class="im im-shopping-cart"></i>{<span class="total-count"></span>}
							</button>
						</li>
					</c:when>
				</c:choose>
			</ul>

			<!-- LOGIN Modal -->
			<div class="modal fade" id="login_model">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Welcome to
								AJIO</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form action="user" method="POST">
								<div class="form-group">
									<label for="exampleInputEmail1">Enter Your Email</label> <input
										type="email" name="usr_email" class="form-control"
										id="exampleInputEmail1" required>
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">Password</label> <input
										type="password" name="usr_password" class="form-control"
										id="exampleInputPassword1">
								</div>
								<button type="submit" name="action" value="signin"
									class="btn btn_submit">Submit</button>
							</form>
							<small>Don't Have An Account? <a href="#" class="close"
								id="register_button" data-dismiss="modal" aria-label="Close"
								data-toggle="modal" data-target="#signup_model">Register
									Now!</a></small>
						</div>
					</div>
				</div>
			</div>

			<!-- SIGNUP Modal -->
			<div class="modal fade" id="signup_model">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">USER
								REGISTRATION</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form action="user" method="POST">
								<div class="form-group">
									<label for="exampleInputName1">Enter Your Name</label> <input
										type="text" name="usr_name" class="form-control"
										id="exampleInputName1" required>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Email address</label> <input
										type="email" name="usr_email" class="form-control"
										id="exampleInputEmail1" required> <small
										id="emailHelp" class="form-text font-italic text-muted">We'll
										never share your email with anyone else.</small>
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">Password</label> <input
										type="password" name="usr_password" class="form-control"
										id="exampleInputPassword1" required>
								</div>
								<button type="submit" name="action" value="signup"
									class="btn btn_submit">Submit</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</nav>

<img src="img/tqs.jpeg" width="1000px"/>


	<jsp:include page="footer.jsp"></jsp:include>
	<script src="js/main.js"></script>
	<script src="js/script.js"></script>
	<script src="js/jquery-3.4.1.slim.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.js"></script>
</html>