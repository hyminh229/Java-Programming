<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Manage Users</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport" />
    <meta content="" name="author" />
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/resources/css/layout.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/resources/css/darkblue.css" rel="stylesheet" type="text/css" id="style_color" />
    <!-- END THEME LAYOUT STYLES -->
    <!-- <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" /> -->
    <link href="${pageContext.request.contextPath}/resources/css/gof.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/resources/css/modal_message.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div class="clearfix"></div>
    
    <div style="padding: 20px;">
        <h1 style="margin-bottom: 20px;">
            <img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="Users" style="width: 32px; height: 32px; vertical-align: middle; margin-right: 10px;" />
            Manage Users
        </h1>
        
        <!-- User Lists Section -->
        <div style="margin-bottom: 20px;">
            <h3 style="cursor: pointer;" onclick="toggleUserList()">
                <span id="toggleIcon">▼</span> User Lists
            </h3>
            <div id="userListSection" style="display: block;">
                <table border="1" cellpadding="10" cellspacing="0" style="width: 100%; border-collapse: collapse;">
                    <thead>
                        <tr style="background-color: #337ab7; color: white;">
                            <th style="text-align: center;">Choose</th>
                            <th style="text-align: center;">ID</th>
                            <th style="text-align: center;">First Name</th>
                            <th style="text-align: center;">Last Name</th>
                            <th style="text-align: center;">Mark</th>
                        </tr>
                    </thead>
                    <tbody id="userTableBody">
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td style="text-align: center;">
                                    <input type="radio" name="selectedUser" value="${user.id}" onclick="fillFormFromRow(this)" />
                                </td>
                                <td style="text-align: center;">${user.id}</td>
                                <td style="text-align: center;">${user.firstName}</td>
                                <td style="text-align: center;">${user.lastName}</td>
                                <td style="text-align: center;">${user.mark}</td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty users}">
                            <tr>
                                <td colspan="5" style="text-align: center; padding: 20px;">No users found</td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
        </div>
        
        <!-- User Input Form -->
        <div style="margin-top: 30px;">
            <form id="userForm" method="POST" action="${pageContext.request.contextPath}/user">
                <table border="1" cellpadding="10" cellspacing="0" style="width: 100%; border-collapse: collapse;">
                    <thead>
                        <tr style="background-color: #f0f0f0;">
                            <th>Key Name</th>
                            <th>Key Value</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>ID</td>
                            <td><input type="text" name="id" id="userId" style="width: 100%; padding: 5px; box-sizing: border-box;" /></td>
                        </tr>
                        <tr>
                            <td>First Name</td>
                            <td><input type="text" name="firstName" id="firstName" style="width: 100%; padding: 5px; box-sizing: border-box;" /></td>
                        </tr>
                        <tr>
                            <td>Last Name</td>
                            <td><input type="text" name="lastName" id="lastName" style="width: 100%; padding: 5px; box-sizing: border-box;" /></td>
                        </tr>
                        <tr>
                            <td>Mark</td>
                            <td><input type="text" name="mark" id="mark" style="width: 100%; padding: 5px; box-sizing: border-box;" /></td>
                        </tr>
                    </tbody>
                </table>
                
                <div style="margin-top: 20px;">
                    <input type="hidden" name="action" id="action" value="add" />
                    <button type="button" onclick="setActionAndSubmit('add')" style="background-color: #337ab7; color: white; padding: 10px 20px; border: none; cursor: pointer; margin-right: 10px; border-radius: 4px;">Add</button>
                    <button type="button" onclick="setActionAndSubmit('update')" style="background-color: #f0ad4e; color: white; padding: 10px 20px; border: none; cursor: pointer; margin-right: 10px; border-radius: 4px;">Update</button>
                    <button type="button" onclick="setActionAndSubmit('delete')" style="background-color: #d9534f; color: white; padding: 10px 20px; border: none; cursor: pointer; border-radius: 4px;">Delete</button>
                </div>
            </form>
        </div>
    </div>
    
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script>
        function toggleUserList() {
            var section = document.getElementById('userListSection');
            var icon = document.getElementById('toggleIcon');
            if (section.style.display === 'none') {
                section.style.display = 'block';
                icon.textContent = '▼'; // Mũi tên xuống khi mở
            } else {
                section.style.display = 'none';
                icon.textContent = '▶'; // Mũi tên phải khi đóng
            }
        }
        
        function setActionAndSubmit(action) {
            document.getElementById('action').value = action;
            if (action === 'delete') {
                if (confirm('Are you sure you want to delete this user?')) {
                    document.getElementById('userForm').submit();
                }
            } else {
                document.getElementById('userForm').submit();
            }
        }
        
        function fillFormFromRow(radio) {
            var row = radio.closest('tr');
            if (row && row.cells.length >= 5) {
                document.getElementById('userId').value = row.cells[1].textContent.trim();
                document.getElementById('firstName').value = row.cells[2].textContent.trim();
                document.getElementById('lastName').value = row.cells[3].textContent.trim();
                document.getElementById('mark').value = row.cells[4].textContent.trim();
            }
        }
    </script>
</body>
</html>

