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
        <script>
		function setURL(url){
		    document.getElementById('iframe').src = url;
		}
		</script>
        <div id="navbar" class="nav navbar-left top-nav">
          <ul class="nav navbar-nav">
            <li><a href="#">Dashboard</a></li>
          </ul>
        </div>
        <div id="navbarR" class="nav navbar-right top-nav">
          <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
						<a class="dropdown-toggle" href="#" data-toggle="dropdown" style="margin-right:10px"><span class="glyphicon glyphicon-log-in"></span> Login <strong class="caret"></strong></a>
						<div class="dropdown-menu" style="padding: 15px; padding-bottom: 10px; margin-right:10px">
							<form method="post" action="login" accept-charset="UTF-8">
								<input style="margin-bottom: 15px;" type="text" placeholder="Username" id="username" name="username">
								<input style="margin-bottom: 15px;" type="password" placeholder="Password" id="password" name="password">
								<input style="float: left; margin-right: 10px;" type="checkbox" name="remember-me" id="remember-me" value="1">
								<label class="string optional" for="user_remember_me"> Remember me</label>
								<input style="margin-bottom: 5px;" class="btn btn-primary btn-block" type="button" id="sign-in" value="Sign In" onclick="setURL(google.dk)">
							</form>
							<input type="button" action="CreateUser.jsp" target="mainWindow">
							<form target="mainWindow" action="createUser.jsp" method="post">
								<input class="btn btn-primary btn-block" type="button" value="Create user" onclick=setURL(www.google.dk)>
							</form>
						</div>
					</li>
			
          </ul>
        </div><!--/.nav-collapse -->
        <!-- Sidepanel navigation -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li>
                        <a href="#"><i class="fa fa-fw fa-dashboard"></i> 1</a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-fw fa-bar-chart-o"></i> 2</a>
                    </li>
                </ul>
            </div>
    </nav>