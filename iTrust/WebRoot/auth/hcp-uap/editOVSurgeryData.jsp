<%@page import="edu.ncsu.csc.itrust.enums.TransactionType"%>
<%@page import="edu.ncsu.csc.itrust.action.EditSurgeryDataAction"%>
<%@page import="edu.ncsu.csc.itrust.beans.SurgeryDataBean"%>
<%@page import="edu.ncsu.csc.itrust.beans.SurgeryTypeBean"%>
<%@page import="edu.ncsu.csc.itrust.dao.mysql.SurgeryTypeDAO"%>
<% { %>

<%

String updateMessage = "";
String updateErrorMessage = "";
%>
<div align="center">

<form action="editOfficeVisit.jsp" method="post" id="removeSurgeryDataForm">
	<input type="hidden" name="formName" value="removeSurgeryDataForm" />
	<input type="hidden" id="removeSurgeryID" name="removeSurgeryID" value="" />
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
	        <td>Description</td>
	        <td>Surgery Notes</td>
	        <td style="width: 60px;">Action</td>
	    </tr>
	
	    <%if(ovaction.surgeryData().getSurgeryData().size()==0){ %>
	    <tr>
	        <td  colspan="3" style="text-align: center;">No Surgeries on record</td>
	    </tr>
	    <%}else { 
            for(SurgeryDataBean s : ovaction.surgeryData().getSurgeryData()) { %>
    <tr>
        <td align=center><%= StringEscapeUtils.escapeHtml("" + (s.getSurgeryID())) %></td>
        <td ><%= StringEscapeUtils.escapeHtml("" + (s.getSurgeryNotes())) %></td>
    </tr>
    <%      }
        }%>
        
        <tr>
        <th colspan="3" style="text-align: center;">New</th>
    </tr>
    <tr>
        <td colspan="3" align="center">
        <%SurgeryTypeDAO stDAO = new SurgeryTypeDAO(prodDAO);
    	List<SurgeryTypeBean> surgeryTypes = stDAO.getSurgeryTypes(); %>
            <select name="SurgeryType" style="font-size: 10px;" <%= disableSubformsString %> >
                <option value="">-- Please Select a Surgery --</option>
                <% for (SurgeryTypeBean surgery : surgeryTypes) { %>
                <option value=" <%=surgery.getSurgeryID() %>"><%= StringEscapeUtils.escapeHtml("" + (surgery.getSurgeryID() )) %> - <%= StringEscapeUtils.escapeHtml("" + (surgery.getSurgeryName() )) %></option>
                <% }  %>
            </select>
            <input type="submit" id="add_surgery_data" name="addP" value="Add Surgery Data" <%= disableSubformsString %> >
        </td>
    </tr>
	</table>

</form>

</div>

<% } %>