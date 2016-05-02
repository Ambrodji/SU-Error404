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
                        id="inputTitle" name="name" placeholder="Title of game"/>
                    </div>
                  </div>
                  <div class="form-group">
                  	<label class="col-sm-2 control-label" for="inputGame" >Upload Game</label>
                  	<div class="col-sm-offset-2 col-sm-10">
                  		<input type="file" name="file"/>
                  	</div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                      <ul class="checkbox">
                        <li><label>
                            <input type="checkbox"/> Difficulty 1
                        </label></li>
                        <li><label>
                            <input type="checkbox"/> Difficulty 2
                        </label></li>
                        <li><label>
                            <input type="checkbox"/> Difficulty 3
                        </label></li>
                      </ul>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label"
                          for="inputDescription" >Description</label>
                    <div class="col-sm-10">
                    	<textarea class="form-control" id="inputDescription" name="description" placeholder="Description"></textarea>
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

        </div>
    </div>
</div>