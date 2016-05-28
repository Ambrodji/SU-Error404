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
            <div class="modal-body gameDeleteDiv" >
                <form id="gameDeleteForm" enctype="multipart/form-data" class="form-horizontal" role="form" method="POST" action="DeleteProg">
                  <div class="form-group noBottomMargin">
                    <table class="table table-hover noBottomMargin" id="tableDeleteGame">
						<% ProgramDao dao2 = new ProgramDao(); 
						ArrayList<Program> programs2 = dao2.findAll();
						for (Program p : programs2) {%>
							<tr class="clickable-row " data-href='<%=p.getId() + ""%>'>
								<th><%=p.getName() %></th>
							</tr>
						<%}%>
						<script>
							$('#tableDeleteGame').on('click', '.clickable-row', function(event) {
								$('.selected').removeClass('selected');
								$(this).children(":first").addClass('selected');
								$("#programId").val($(this).data("href"));
							});
				            
						    $(document).ready(function(){
						    	$('form#gameDeleteForm').submit(function(event) {
						        	event.preventDefault();
						        	jQuery.validator.addMethod("programSelected", function(value, element, param) {
					            	    return $("#programId").val().length > 0;
					            	}, "Please select a program to delete");
				            	    if ($("form#gameDeleteForm").valid()) {
				            	    	$.ajax({
				                            url: "DeleteProg",
				                            type: 'post',
				                            dataType: 'text',
				                            data: $("#programId").serialize(),
				                            success: function(data) {
				                            	if (data == "success") {
				                            		alert("The selected program was deleted successfully.");
				                            	} else {
				                            		alert("The selected program could not be deleted");
				                            	}
				                                
				                                location.reload(true);
				                            },
				                            error: function(data) {
				                            	alert("The selected program could not be deleted");
				                            }
				                    	});
					            	}
					            });

				            	$("form#gameDeleteForm").validate({
				            		ignore: [],
				            	    rules: {
				            	        programId: {
				            	        	programSelected: true
				            	        }
				            	    },
				            	    messages: {}
				            	});
				            });
						</script>
					</table> 
					<input type="text" name="programId" id="programId" style="display:none;">
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