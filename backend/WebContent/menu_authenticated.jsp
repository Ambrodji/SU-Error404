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
        <div id="navbar" class="nav navbar-left top-nav">
          <ul class="nav navbar-nav">
          	<li><button type="button" class="button-tabs" id="loadDash">Dashboard</button></li>
          	<li><button type="button" class="button-tabs" id="loadHist">History</button></li>
          </ul>
        </div>
        <div id="navbarR" class="nav navbar-right top-nav">
          <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
						<a class="dropdown-toggle" href="#" data-toggle="dropdown" style="margin-right:10px"><span class="glyphicon glyphicon-user"></span> <%=request.getSession().getAttribute("user") %> <strong class="caret"></strong></a>
						<ul class="dropdown-menu" style="padding: 15px; padding-bottom: 10px; margin-right:10px">
							<li style="padding-bottom:2px">
								<a href="#"><span style="font-size:1em" class="glyphicon glyphicon-user"></span> <%=request.getSession().getAttribute("user") %></a>
							</li>
							<li style="padding-bottom:2px">
								<a href="#"><span style="font-size:1em" class="glyphicon glyphicon-cog"></span> Settings</a>
							</li>
							<li >
								<a href="logout"><span style="font-size:1em" class="glyphicon glyphicon-log-out"></span> Logout</a>
							</li>
						</ul>
					</li>
          </ul>
        </div><!--/.nav-collapse -->
        <!-- Sidepanel navigation -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li>
                        <a href="#"> 1</a>
                    </li>
                    <li>
                        <a href="#"> 2</a>
                    </li>
                    <li>
                        <a href="#"> 3</a>
                    </li>
                    <li>
                        <a href="#"> 4</a>
                    </li>
                    <li>
                        <a href="#"> 5</a>
                    </li>
                    <li>
                        <a href="#"> 6</a>
                    </li>
                    <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#demo"><i class="fa fa-fw fa-arrows-v"></i> 7 <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="demo" class="collapse">
                            <li>
                                <a href="#">7.1</a>
                            </li>
                            <li>
                                <a href="#">7.2</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#"> 8</a>
                    </li>
                    <li>
                        <a href="#"> 9</a>
                    </li>
                </ul>
            </div>
    </nav>