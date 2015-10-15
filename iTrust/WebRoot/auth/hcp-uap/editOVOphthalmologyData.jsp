
<%@page import="edu.ncsu.csc.itrust.action.EditOphthalmologyDataAction"%>
<%@page import="edu.ncsu.csc.itrust.beans.OphthalmologyDataBean"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="edu.ncsu.csc.itrust.beans.forms.EditOphthalmologyDataForm"%>

<% { %>

<%
	String updateMessage = "";
//To hold the last value the user input for the fields
String lastOphthalmologyDataID = "";
String lastAcuityNumerator = "";
String lastAcuityDenominator = "";
String lastODSphere = "";
String lastOSSphere = "";
String lastODCylinder = "";
String lastOSCylinder = "";
String lastODAxis = "";
String lastOSAxis = "";
String lastODAdd = "";
String lastOSAdd = "";

String errorMsg = "";

boolean editingExistingData = false;
boolean cancelledEditingData = false;

if (null != request.getParameter("cancelOphthalmologyDataButton") &&
   !"".equals(request.getParameter("cancelOphthalmologyDataButton"))) {
	cancelledEditingData = true;
}

//only allow Optometrists and Ophthalmologists edit Ophthalmology Office Visits
//Any HCP can view existing Ophthalmology Office Visits however
PersonnelBean loggedInUser = pDAO.getPersonnel(loggedInMID);
if (!loggedInUser.getSpecialty().equals("Optometrist") && !loggedInUser.getSpecialty().equals("Ophthalmologist")) {
	disableSubformsString = "disabled=\"true\"";
}

if (ovaction.ophthalmologyData().getOphthalmologyData().size() != 0) {
	OphthalmologyDataBean bean = ovaction.ophthalmologyData().getOphthalmologyData().get(0);
	
			DecimalFormat df = new DecimalFormat("+#,##0.00;-#");

			lastOphthalmologyDataID = Long.toString(bean.getId());
			lastAcuityNumerator = bean.getAcuityNumerator().toString();
			lastAcuityDenominator = bean.getAcuityDenominator().toString();
			lastODSphere = df.format(bean.getODSphere());
			lastOSSphere = df.format(bean.getOSSphere());
			lastODCylinder = df.format(bean.getODCylinder());
			lastOSCylinder = df.format(bean.getOSCylinder());
			lastODAxis = bean.getODAxis().toString();
			lastOSAxis = bean.getOSAxis().toString();
			lastODAdd = df.format(bean.getODAdd());
			lastOSAdd = df.format(bean.getOSAdd());
			editingExistingData = true;
 	
}

// The "OphthalmologyDataForm" form is submitted when the user has submitted 
// a new or a edited entry.  
if (!cancelledEditingData && "OphthalmologyDataForm".equals(submittedFormName)) {

	EditOphthalmologyDataAction oDataAction = ovaction.ophthalmologyData();

	if (null != request.getParameter("ophthalmologyDataID") && 
	   !("".equals(request.getParameter("ophthalmologyDataID")))) {
		editingExistingData = true;
	}
	
	EditOphthalmologyDataForm form = new BeanBuilder<EditOphthalmologyDataForm>().build(request.getParameterMap(), new EditOphthalmologyDataForm());
	
    try {
    	
    	OphthalmologyDataBean bean = oDataAction.formToBean(form);
    	bean.setVisitID(ovaction.getOvID());	

    	//if user submitted an entry to an existing entry
    	if (editingExistingData == true) {
    		long id = Long.parseLong(request.getParameter("ophthalmologyDataID"));
    		bean.setId(id);
    		oDataAction.editOphthalmologyData(bean);
    		//log event that the office visit was edited
            ovaction.logOfficeVisitEvent(TransactionType.EDIT_OPHTHALMOLOGY_OFFICE_VISIT);
    		editingExistingData = true; 
    	} else {	//if user submitted a new entry
    		oDataAction.addOphthalmologyData(bean);
    	//log event that the office visit was created
            ovaction.logOfficeVisitEvent(TransactionType.CREATE_OPHTHALMOLOGY_OFFICE_VISIT);
            editingExistingData = true; 
    	}

	    updateMessage = "Ophthalmology Data successfully updated.";
	    response.sendRedirect("/iTrust/auth/hcp-uap/documentOfficeVisit.jsp");
    } catch (FormValidationException e) {
        errorMsg = e.printHTMLasString();
        lastOphthalmologyDataID = request.getParameter("OphthalmologyDataID");
        lastAcuityNumerator = request.getParameter("acuityNumerator");
        lastAcuityDenominator = request.getParameter("acuityDenominator");
        lastODSphere = request.getParameter("odsphere");
        lastOSSphere = request.getParameter("ossphere");
        lastODCylinder = request.getParameter("odcylinder");
        lastOSCylinder = request.getParameter("oscylinder");
        lastODAxis = request.getParameter("odaxis");
        lastOSAxis = request.getParameter("osaxis");
        lastODAdd = request.getParameter("odadd");
        lastOSAdd = request.getParameter("osadd");
    }
   
}
%>


<a name="OphthalmologyDataAnchor"></a>
<div align=center>
<%
	if (!"".equals(updateMessage)) {
%>  <span class="iTrustMessage"><%=updateMessage%></span>  <%
  	}
  %>

<form action="editOfficeVisit.jsp#OphthalmologyDataAnchor" method="post" id="editOphthalmologyDataForm">
<input type="hidden" name="formName" value="editOphthalmologyDataForm" />
<input type="hidden" id="editOphthalmologyDataID" name="editOphthalmologyDataID" value="" />
<input type="hidden" id="editOphthalmologyDataAction" name="editOphthalmologyDataAction" value="" />
<input type="hidden" name="ovID" value="<%=StringEscapeUtils.escapeHtml("" + (ovaction.getOvID()))%>" />
</form>

<%-- If there is an error --%>
<%
	if (!"".equals(errorMsg)) {
%>
    <div style="background-color:yellow;color:black" align="center">
        <%=errorMsg%>
    </div>
<%
	}
%>

<form action="editOfficeVisit.jsp#OphthalmologyDataAnchor" method="post" id="OphthalmologyDataForm">

<input type="hidden" name="formIsFilled" value="true" />
<input type="hidden" name="formName" value="OphthalmologyDataForm" />
<input type="hidden" name="ovID" value="<%=StringEscapeUtils.escapeHtml("" + (ovaction.getOvID()))%>" />
<%
	if (editingExistingData==true) {
%>
    <input type="hidden" name="ophthalmologyDataID" value="<%=lastOphthalmologyDataID%>" />
<%
	}
%>


<table class="fTable" align="center" id="OphthalmologyDataTable">
    <tr>
        <th colspan="9"><a href="#" class="topLink">[Top]</a>Ophthalmology Data</th>
    </tr>
    <tr class="subHeader">
        <td>Acuity</td>
        <td>OD Sphere</td>
        <td>OS Sphere</td>
        <td>OD Cylinder</td>
        <td>OS Cylinder</td>
        <td>OD Axis</td>
        <td>OS Axis</td>
        <td>OD Add</td>
        <td>OS Add</td>
    </tr>

    <%
    	if(ovaction.ophthalmologyData().getOphthalmologyData().size()==0) {
    %>
    <tr>
        <td  colspan="9" style="text-align: center;">No Ophthalmology Data on record</td>
    </tr>
    <%
    	} else {
        		OphthalmologyDataBean odbean = ovaction.ophthalmologyData().getOphthalmologyData().get(0);
        		DecimalFormat df = new DecimalFormat("+#,##0.00;-#");
    %>
    <tr>
    	<td align=center><%=StringEscapeUtils.escapeHtml("" + (odbean.getAcuityNumerator()))%> /
    					 <%=StringEscapeUtils.escapeHtml("" + (odbean.getAcuityDenominator()))%></td>
    	<td align=center><%= StringEscapeUtils.escapeHtml("" + (df.format(odbean.getODSphere()))) %></td>
    	<td align=center><%= StringEscapeUtils.escapeHtml("" + (df.format(odbean.getOSSphere()))) %></td>
    	<td align=center><%= StringEscapeUtils.escapeHtml("" + (df.format(odbean.getODCylinder())))%></td>
    	<td align=center><%= StringEscapeUtils.escapeHtml("" + (df.format(odbean.getOSCylinder()))) %></td>
    	<td align=center><%= StringEscapeUtils.escapeHtml("" + (odbean.getODAxis())) %></td>
    	<td align=center><%= StringEscapeUtils.escapeHtml("" + (odbean.getOSAxis())) %></td>
    	<td align=center><%= StringEscapeUtils.escapeHtml("" + (df.format(odbean.getODAdd()))) %></td>
    	<td align=center><%= StringEscapeUtils.escapeHtml("" + (df.format(odbean.getOSAdd()))) %></td>
    	       
    </tr>
    <% } // end of if..else statements %>
    <tr>
        <th colspan="9" style="text-align: center;">New</th>
    </tr>
    <tr>
        <td vertical-align: top;>Acuity:</td>
        <td colspan="2">
            <input type="text" name="acuityNumerator" id="acuityNumerator" 
                   style="width: 15em; min-width: 15em;" 
                   value="<%= StringEscapeUtils.escapeHtml(lastAcuityNumerator) %>"
                   <%= disableSubformsString %> >
        </td>
        <td style="text-align: center;"> / </td>
        <td colspan="2">
            <input type="text" name="acuityDenominator" id="acuityDenominator" 
                   style="width: 15em; min-width: 15em;" 
                   value="<%= StringEscapeUtils.escapeHtml(lastAcuityDenominator) %>"
                   <%= disableSubformsString %> >                 
        </td>
        <td colspan="3"></td>
    </tr>
    <tr>
        <td vertical-align: top;>ODSphere:</td>
        <td colspan="3">
            <input type="text" name="odsphere" id="odsphere" 
                   style="width: 15em; min-width: 15em;" 
                   value="<%= StringEscapeUtils.escapeHtml(lastODSphere) %>"
                   <%= disableSubformsString %> >
        </td>

        <td vertical-align: top;>OSSphere:</td>
        <td colspan="4">
            <input type="text" name="ossphere" id="ossphere" 
                   style="width: 15em; min-width: 15em;" 
                   value="<%= StringEscapeUtils.escapeHtml(lastOSSphere) %>"
                   <%= disableSubformsString %> >
        </td>
    </tr>
    <tr>
        <td vertical-align: top;>ODCylinder:</td>
        <td colspan="3">
            <input type="text" name="odcylinder" id="odcylinder" 
                   style="width: 15em; min-width: 15em;"
                   value="<%= StringEscapeUtils.escapeHtml(lastODCylinder) %>"
                   <%= disableSubformsString %> >
        </td>

        <td vertical-align: top;>OSCylinder:</td>
        <td colspan="4">
            <input type="text" name="oscylinder" id="oscylinder" 
                   style="width: 15em; min-width: 15em;" 
                   value="<%= StringEscapeUtils.escapeHtml(lastOSCylinder) %>"
                   <%= disableSubformsString %> >
        </td>
    </tr>
    <tr>
        <td vertical-align: top;>ODAxis:</td>
        <td colspan="3">
            <input type="text" name="odaxis" id="odaxis" 
                   style="width: 15em; min-width: 15em;"
                   value="<%= StringEscapeUtils.escapeHtml(lastODAxis) %>"
                   <%= disableSubformsString %> >
        </td>

        <td vertical-align: top;>OSAxis:</td>
        <td colspan="4">
            <input type="text" name="osaxis" id="osaxis" 
                   style="width: 15em; min-width: 15em;" 
                   value="<%= StringEscapeUtils.escapeHtml(lastOSAxis) %>"
                   <%= disableSubformsString %> >
        </td>
    </tr>
    <tr>
        <td vertical-align: top;>ODAdd:</td>
        <td colspan="3">
            <input type="text" name="odadd" id="odadd" 
                   style="width: 15em; min-width: 15em;" 
                   value="<%= StringEscapeUtils.escapeHtml(lastODAdd) %>"
                   <%= disableSubformsString %> >
        </td>

        <td vertical-align: top;>OSAdd:</td>
        <td colspan="4">
            <input type="text" name="osadd" id="osadd" 
                   style="width: 15em; min-width: 15em;"
                   value="<%= StringEscapeUtils.escapeHtml(lastOSAdd) %>"
                   <%= disableSubformsString %> >
        </td>
    </tr>
  
    <tr>
        <td colspan="9" style="text-align: center;">
            <% if (editingExistingData == true) { %>
                <input type="submit" name="updateOphthalmologyDataButton" 
                       id="updateOphthalmologyDataButton"
                       value="Update Ophthalmology Data" 
                       <%= disableSubformsString %> >
                <input type="submit" name="cancelOphthalmologyDataButton" 
                       value="Cancel" 
                       <%= disableSubformsString %> >
            <% } else { %>
                <input type="submit" id="addOphthalmologyDataButton" 
                       value="Add Ophthalmology Data" 
                       <%= disableSubformsString %> >
            <% } %>
        </td>
    </tr>
</table>

</form>

</div>

<% } %>

