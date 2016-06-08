 	<!-- Top navigation -->
 	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a href="#" class="navbar-brand" href="#">Online TA</a>
        </div>
        
        <!-- Top menu items -->
		<%@include file="modal_user_create.jsp" %>
		
        <div id="navbarR" class="nav navbar-right top-nav">
          <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
						<a class="dropdown-toggle" href="#" data-toggle="dropdown" style="margin-right:10px"><span class="glyphicon glyphicon-log-in"></span> Login <strong class="caret"></strong></a>
						<div class="dropdown-menu" style="padding: 15px; padding-bottom: 10px; margin-right:10px">
							<form id="loginForm" method="post" action="login" accept-charset="UTF-8">
								<input style="margin-bottom: 15px;" type="text" placeholder="Username" id="username" name="user">
								<input style="margin-bottom: 15px;" type="password" placeholder="Password" id="password" name="password">
								<label id="loginError" style="display:none;">A user with the given username/password could not be found.</label>
								<input style="margin-bottom: 5px;" class="btn btn-primary btn-block" type="submit" id="sign-in" value="Sign In" name="user">
								<input style="margin-bottom: 5px;" class="btn btn-primary btn-block" type="button" id="createUserBtn" value="Create user" data-toggle="modal" data-target="#createUser">
							</form>							
						</div>
			</li>
          </ul>
        </div><!--/.nav-collapse -->
    </nav>
    <script>
            
$(document).ready(function(){
	$("form#loginForm").submit(function(event) {
		event.preventDefault();
		$.ajax({
			url: "login",
			type: 'post',
			dataType: 'text',
			data: $("#loginForm").serialize(),
			success: function(data) {
				if (data == "success") {
					$("#loginError").css('display', 'none');
					location.reload(true);
				} else {
					$("#loginError").css('display', 'block');
				}
			}, error: function(data) {
				$("#loginError").css('display', 'block');
			}
		});
	});
});
		            
		            </script>