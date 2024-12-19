<%@ page contentType="text/html;charset=UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="Nguyễn Quân - Dự án laptopshop" />
                <meta name="author" content="Nguyễn Quân" />
                <title>Update User - Nguyễn Quân</title>
                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
            </head>

            <body class="sb-nav-fixed">
                <!-- Header -->
                <jsp:include page="../layout/header.jsp" />
                <!-- Header -->
                <div id="layoutSidenav">
                    <!-- SideBar -->
                    <jsp:include page="../layout/sidebar.jsp" />
                    <!-- SideBar -->
                    <div id="layoutSidenav_content">
                        <main>
                            <div class="container-fluid px-4">
                                <h1 class="mt-4">Update User</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item active"><a href="/admin">Dashboard</a></li>
                                    <li class="breadcrumb-item active">Update User</li>
                                </ol>
                                <!-- Content -->
                                <div class="row">
                                    <!--  -->
                                    <div class="col-md-6 col-12 mx-auto">
                                        <h3>Update User</h3>
                                        <hr />
                                        <!-- form -->
                                        <form:form method="POST" action="/admin/user/update" modelAttribute="newUser">
                                            <div class="mb-3" style="display: none">
                                                <label class="form-label">ID: </label>
                                                <form:input type="text" class="form-control" path="id" />
                                            </div>

                                            <div class="mb-3">
                                                <label class="form-label">Email Address</label>
                                                <form:input type="email" class="form-control" path="email"
                                                    disabled="true" />

                                            </div>

                                            <div class="mb-3">
                                                <!-- validate -->
                                                <c:set var="errorPhone">
                                                    <form:errors path="phone" cssClass="invalid-feedback" />
                                                </c:set>

                                                <label class="form-label">Phone</label>
                                                <form:input type="text"
                                                    class="form-control ${not empty errorPhone ? 'is-invalid' : ''}"
                                                    path="phone" />
                                                ${errorPhone}
                                            </div>

                                            <div class="mb-3">
                                                <!-- validate -->
                                                <c:set var="errorName">
                                                    <form:errors path="fullName" cssClass="invalid-feedback" />
                                                </c:set>

                                                <label class="form-label">Full Name</label>
                                                <form:input type="text"
                                                    class="form-control ${not empty errorName ? 'is-invalid' : ''}"
                                                    path="fullName" />
                                                ${errorName}
                                            </div>

                                            <div class="mb-3">
                                                <!-- validate -->
                                                <c:set var="errorAddress">
                                                    <form:errors path="address" cssClass="invalid-feedback" />
                                                </c:set>

                                                <label class="form-label">Address</label>
                                                <form:input type="text"
                                                    class="form-control ${not empty errorAddress ? 'is-invalid' : ''}"
                                                    path="address" />
                                                ${errorAddress}
                                            </div>

                                            <button type="submit" class="btn btn-success">Submit</button>
                                            <a href="/admin/user" class="btn btn-primary">Back</a>
                                        </form:form>
                                    </div>
                                </div>
                                <!-- Content -->
                            </div>
                        </main>
                        <!-- footer -->
                        <jsp:include page="../layout/footer.jsp" />
                        <!-- footer -->
                    </div>
                </div>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                    crossorigin="anonymous"></script>
                <script src="/js/scripts.js"></script>
            </body>

            </html>