<%@page import="edu.ncsu.csc.itrust.enums.TransactionType"%>
<%@page import="edu.ncsu.csc.itrust.action.EditSurgeryDataAction"%>
<%@page import="edu.ncsu.csc.itrust.beans.SurgeryDataBean"%>
<%@page import="edu.ncsu.csc.itrust.beans.SurgeryTypeBean"%>
<%@page import="edu.ncsu.csc.itrust.dao.mysql.SurgeryTypeDAO"%>
<%@page import="edu.ncsu.csc.itrust.validate.SurgeryDataFormValidator"%>
<%@page import="edu.ncsu.csc.itrust.beans.forms.EditSurgeryDataForm"%>

<% { %>

<%

String updateMessage = "";
String updateErrorMessage = "";

//only allows Ophthalmologists edit Surgery Visits
//Any HCP can view existing Ophthalmology Office Visits however
PersonnelBean loggedInUser = pDAO.getPersonnel(loggedInMID);
if (!loggedInUser.getSpecialty().equals("Ophthalmologist")) {
	disableSubformsString = "disabled=\"true\"";
}


if("removeSurgeryDataForm".equals(submittedFormName)){
	EditSurgeryDataAction sDataAction = ovaction.surgeryData();
	
	String remID = request.getParameter("removeSurgeryDataID");
	SurgeryDataBean bean = new SurgeryDataBean();
	bean.setSurgeryID(Long.parseLong(remID));
	
	sDataAction.deleteSurgeryData(bean);
	ovaction.logOfficeVisitEvent(TransactionType.OFFICE_VISIT_EDIT);
   	ovaction.logOfficeVisitEvent(TransactionType.SURGERY_REMOVE);
    updateMessage = "Surgery information successfully updated.";
}

//The "OphthalmologyDataForm" form is submitted when the user has submitted 
//a new or a edited entry.  
if ("surgeryDataForm".equals(submittedFormName)) {
	EditSurgeryDataAction sDataAction = ovaction.surgeryData();
	EditSurgeryDataForm form = new BeanBuilder<EditSurgeryDataForm>().build(request.getParameterMap(), new EditSurgeryDataForm());
	if(form.getSurgeryNotes().equals("")){
		form.setSurgeryNotes("no description");
	}
	try{
		SurgeryDataFormValidator validator = new SurgeryDataFormValidator();
		validator.validate(form);
		SurgeryDataBean	bean = sDataAction.formToBean(form);
		bean.setVisitID(ovaction.getOvID());
		sDataAction.addSurgeryData(bean);
		ovaction.logOfficeVisitEvent(TransactionType.OFFICE_VISIT_EDIT);
       	ovaction.logOfficeVisitEvent(TransactionType.SURGERY_ADD);
        updateMessage = "Surgery information successfully updated.";
	} catch(FormValidationException e){
		updateErrorMessage = e.printHTMLasString();
	}
}

 if (!"".equals(updateErrorMessage)) { %>
<div style="background-color:yellow;color:black" align="center">
    <%= updateErrorMessage %>
</div>
<% } %>
 
 
<script type="text/javascript">
    function removeSurgeryDataID(value) {
        document.getElementById("removeSurgeryDataID").value = value;
        document.forms["removeSurgeryDataForm"].submit();
    }
</script>


<div align="center">

<form action="editOfficeVisit.jsp" method="post" id="removeSurgeryDataForm">
	<input type="hidden" name="formName" value="removeSurgeryDataForm" />
	<input type="hidden" id="removeSurgeryDataID" name="removeSurgeryDataID" value="" />
	<input type="hidden" name="ovID" value="<%= StringEscapeUtils.escapeHtml("" + (ovaction.getOvID())) %>" />
</form>

<form action="editOfficeVisit.jsp" method="post" id="surgeryDataForm">
	<input type="hidden" name="formIsFilled" value="true" />
	<input type="hidden" name="formName" value="surgeryDataForm" />
	<input type="hidden" name="ovID" value="<%= StringEscapeUtils.escapeHtml("" + (ovaction.getOvID())) %>" />	

	<table class="fTable" align="center" id="surgeryDataTable">
	    <tr>
	        <th colspan="3"><a href="#" class="topLink">[Top]</a>Surgery</th>
	    </tr>
	    <tr class="subHeader">
	        <td>Surgery Type</td>
	        <td>Surgery Notes</td>
	        <td style="width: 60px;">Action</td>
	    </tr>
	
	    <%if(ovaction.surgeryData().getSurgeryData().size()==0){ %>
	    <tr>
	        <td  colspan="3" style="text-align: center;">No Surgeries on record</td>
	    </tr>
	    <%}else { 
	    	SurgeryTypeDAO stDAO = new SurgeryTypeDAO(prodDAO);
	    	List<SurgeryTypeBean> surgeryTypes = stDAO.getSurgeryTypes();
            for(SurgeryDataBean s : ovaction.surgeryData().getSurgeryData()) { %>
    <tr>
        <td><% %>
        <%= StringEscapeUtils.escapeHtml("" + stDAO.getSurgeryTypes().get((int)(s.getSurgeryID()-1)).getSurgeryName()) %></td>
        <td ><%= StringEscapeUtils.escapeHtml("" + (s.getSurgeryNotes())) %></td>
        <td >
        <% 	//only allow Optometrists and Ophthalmologists edit Ophthalmology Office Visits
			//Any HCP can view existing Ophthalmology Office Visits however
			if (loggedInUser.getSpecialty().equals("Ophthalmologist")) {
				%> <a
		            href="javascript:removeSurgeryDataID('<%= StringEscapeUtils.escapeHtml("" + (s.getId())) %>');">Remove</a></td> <%
			} %>
       
    </tr>
    <%      }
        }%>
        
        <tr>
        <th colspan="3" style="text-align: center;">New</th>
    </tr>
    <tr align = center>
    	<td>Surgery Type</td>
        <td align="center">
        <%SurgeryTypeDAO stDAO = new SurgeryTypeDAO(prodDAO);
    	List<SurgeryTypeBean> surgeryTypes = stDAO.getSurgeryTypes(); %>
           <select name="surgeryID" style="font-size: 10px;" <%= disableSubformsString %> >
                <option value="">-- Please Select a Surgery --</option>
                <% for (SurgeryTypeBean surgery : surgeryTypes) { %>
                <option value="<%=surgery.getSurgeryID() %>"><%= StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeHtml("" + (surgery.getSurgeryName()))) %></option>
                <% }  %>
            </select>
        </td>
        <td></td>
      </tr>
     <tr colspan = 3>
     	<td>Notes:</td>
     	<td>
     	 <textarea name="surgeryNotes" id="surgeryNotes" style=" height: 5em; min-width: 30em; font-family: sans-serif;"
                      <%= disableSubformsString %> 
                      ></textarea>
            <input type="submit" id="add_surgery_data" name="addP" value="Add Surgery Data" <%= disableSubformsString %> >
        </td>
        <td></td>
    </tr>
	</table>

</form>

</div>

<% } %>