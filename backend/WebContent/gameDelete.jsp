<%@page import="dk.error404.dao.ProgramDao" %>
<%@page import="dk.error404.model.Program" %>
<%@page import="java.util.ArrayList" %>

<div class="modal fade" id="deleteGame" tabindex="-1" role="dialog"  
     aria-labelledby="deleteGameLabel" aria-hidden="true">
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
                    Delete a game
                </h4>
            </div>
            
            <!-- Modal Body -->
            <div class="modal-body" >
                
                <form id="gameDeleteForm" enctype="multipart/form-data" class="form-horizontal" role="form" method="POST" action="DeleteProg">
                  <div class="form-group">
                    <label  class="col-sm-2 control-label"
                              for="activeGameTitles">Active games</label>
                    <table class="table table-hover" id="tableDeleteGame">
						<% ProgramDao dao2 = new ProgramDao(); 
						ArrayList<Program> programs2 = dao2.findAll();
						for (Program p : programs2) {%>
							<tr class="clickable-row" data-href="#">
								<th><%=p.getName() %></th>
							</tr>
						<%}%>
						<script>
							$('#tableDeleteGame').on('click', '.clickable-row', function(event) {
							  if($(this).hasClass('active')){
							    $(this).removeClass('active'); 
							  } else {
							    $(this).addClass('active').siblings().removeClass('active');
							  }
							});
						</script>
					</table> 
                  </div>
                  
                  <!-- Modal Footer -->
            	  <div class="modal-footer">
                	<button type="button" class="btn btn-default"
                        data-dismiss="modal">
                            Close
                	</button>
                	<button type="submit" class="btn btn-primary">
                    Delete
                	</button>
            	  </div>
                </form>           
            </div>
        </div>
    </div>
</div>