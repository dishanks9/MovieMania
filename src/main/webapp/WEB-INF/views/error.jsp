<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<div id="myModal1" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">${messageType}</h4>
      </div>
      <div class="modal-body">
        <p>${messageDetails}</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="goBack()">Close</button>
      </div>
    </div>

  </div>
</div>
<script type="text/javascript">
    $(window).on('load',function(){
        $('#myModal1').modal('show');
    });
    function goBack() {
    	 $('#myModal').modal('show');
    }
</script>


