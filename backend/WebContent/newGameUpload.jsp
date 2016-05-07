<div class="modal fade" id="uploadGame" tabindex="-1" role="dialog"  
     aria-labelledby="uploadGameLabel" aria-hidden="true">
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
                    Upload new game
                </h4>
            </div>
            
            <!-- Modal Body -->
            <div class="modal-body" >
                
                <form id="gameUploadForm" enctype="multipart/form-data" class="form-horizontal" role="form" method="POST" action="UploadProg">
                  <div class="form-group">
                    <label  class="col-sm-2 control-label"
                              for="inputTitle">Title</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" 
                        id="name" name="name" placeholder="Title of game"/>
                    </div>
                  </div>
                  <div class="form-group">
                  	<label class="col-sm-2 control-label" for="inputGame" >Upload Game</label>
                  	<div class="col-sm-offset-2 col-sm-10">
                  		<input type="file" id="file" name="file"/>
                  	</div>
                  </div>
                  <div class="form-group">
                    <label  class="col-sm-2 control-label"
                              for="inputDifficulty">Number of difficulty levels</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" 
                        id="difficulties" name="difficulties" placeholder="Ex. 3"/>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label"
                          for="inputDescription" >Description</label>
                    <div class="col-sm-10">
                    	<textarea class="form-control" id="description" name="description" placeholder="Description"></textarea>
                    </div>
                  </div>
                  <!-- Modal Footer -->
            	  <div class="modal-footer">
                	<button type="button" class="btn btn-default"
                        data-dismiss="modal">
                            Close
                	</button>
                	<button type="submit" class="btn btn-primary">
                    Upload
                	</button>
            	  </div>
                </form>           
            </div>
            <script>
		            
		    $(document).ready(function(){
		    	$("form#gameUploadForm").submit(function(event) {
		        	event.preventDefault();
            	    if ($("form").valid()) {
            	    	$.ajax({
                            url: "UploadProg",
                            type: 'post',
                            dataType: 'text',
                            data: $("#gameUploadForm").serialize(),
                            success: function(data) {
                                alert("Your new game has been uploaded");
                                location.reload(true);
                            },
                            error: function(data) {
                            	alert("Game could not be created");
                            }
	                    });
	            	}
	            });

            	$("form").validate({
            	    rules: {
            	        name: {
            	        	required: true,
            	        },
            	        file: {
            	        	required: true,
            	        },
            	        difficulties: {
            	        	required: true,
            	        },
            	        description: {
            	        	required: true,
            	        }
            	    },
            	    messages: {}
            	});
            });
	            
            </script>

        </div>
    </div>
</div>