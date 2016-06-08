<div class="modal fade" id="createUser" tabindex="-1" role="dialog" 
		     aria-labelledby="createUserLabel" aria-hidden="true" style="z-index:100;">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <!-- Modal Header -->
		            <div class="modal-header">
		                <button type="button" class="close" 
		                   data-dismiss="modal">
		                       <span aria-hidden="true">&times;</span>
		                       <span class="sr-only">Close</span>
		                </button>
		                <h4 class="modal-title" id="myModalLabel">
		                    Create new user
		                </h4>
		            </div>
		            
		            <!-- Modal Body -->
		            <div class="modal-body">
		                
		                <form class="form-horizontal" id="registerForm" role="form">
		                  <div class="form-group">
		                    <label  class="col-sm-2 control-label"
		                              for="inputName">Full name</label>
		                    <div class="col-sm-10">
		                        <input type="text" class="form-control" 
		                        id="user" name="user" placeholder="Enter your full name"/>
		                    </div>
		                  </div>
		                  <div class="form-group">
		                    <label class="col-sm-2 control-label"
		                          for="inputID" >KU-ID</label>
		                    <div class="col-sm-10">
		                        <input type="text" class="form-control"
		                            id="id" name="id" placeholder="Etc. atv420"/>
		                    </div>
		                  </div>
		                  <div class="form-group">
		                    <label class="col-sm-2 control-label"
		                          for="email" >Email</label>
		                    <div class="col-sm-10">
		                        <input type="email" class="form-control"
		                            id="email" name="email" placeholder="Etc. anders@hotmail.com"/>
		                    </div>
		                  </div>
		                  <div class="form-group">
		                    <label class="col-sm-2 control-label"
		                          for="school" >Skole</label>
		                    <div class="col-sm-10">
		                        <input type="text" class="form-control"
		                            id="school" name="school" placeholder="Etc. Københavns Universitet"/>
		                    </div>
		                  </div>
		                  <div class="form-group">
		                    <label class="col-sm-2 control-label"
		                          for="password" >Password</label>
		                    <div class="col-sm-10">
		                        <input type="password" name="password" class="form-control"
		                            id="password"/>
		                    </div>
		                  </div>
		                  <!-- Modal Footer -->
		            	  <div class="modal-footer">
		                	<button type="button" class="btn btn-default"
		                        data-dismiss="modal">
		                            Close
		                	</button>
		                	<button type="submit" class="btn btn-primary">
		                    Create user
		                	</button>
		               	  </div>
		            	</form>           
		            </div>
		            <script>
		            
		            $(document).ready(function(){
		            	$("form#registerForm").submit(function(event) {
		            	    event.preventDefault()

		            	    if ($("form").valid()) {
		            	    	$.ajax({
		                            url: "RegisterServlet",
		                            type: 'post',
		                            dataType: 'text',
		                            data: $("#registerForm").serialize(),
		                            success: function(data) {
		                                alert("Your new account has been created");
		                                location.reload(true);
		                            },
		                            error: function(data) {
		                            	alert("User could not be created");
		                            }
		                    	});
		            	    }
		            	});

		            	jQuery.validator.addMethod("licensePlate", function(value, element, param) {
		            	    var regex = new RegExp(param)
		            	    return Boolean(regex.test(value));
		            	}, "Please enter a valid ku id (3 letters and 3 numbers)");

		            	jQuery.validator.addMethod("minLength", function(value, element, param) {
		            	    return Boolean(value == "" || value.length >= param)
		            	}, "The password must be at least {0} characters long");
		            	$("form").validate({
		            	    rules: {
		            	        user: {
		            	        	required: true,
		            	        },
		            	        id: {
		            	        	required: true,
		            	            licensePlate: '^[a-z]{3}[0-9]{3}$',
		            	        },
		            	        email: {
		            	        	required: true,
		            	        },
		            	        school: {
		            	        	required: true,
		            	        },
		            	        password: {
		            	        	required: true,
		            	        	minLength: 6,
		            	        }
		            	    },
		            	    messages: {}
		            	});
		            });
		            
		            </script>
		            
		        </div>
		    </div>
		</div>