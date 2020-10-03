$(function () {
        $.ajax({
                url: "/employees",
                type: "GET",
                success: showResult,
                error: showError
            }
        );
    }
);

function showResult(employees) {
    let output = "<tr>" +
        "<th>Id</th>" +
        "<th>Name</th>" +
        "<th>Surname</th>" +
        "<th>Dep.id</th>" +
        "<th>Job title</th>" +
        "<th>Gender</th>" +
        "<th>Birthday</th>" +
        "</tr>";

    $.each(employees,
        function (idx, employee) {
            output += "<tr>" +
                "<td>" + employee.employeeId + "</td>" +
                "<td>" + employee.firstName + "</td>" +
                "<td>" + employee.lastName + "</td>" +
                "<td>" + employee.departmentId + "</td>" +
                "<td>" + employee.jobTitle + "</td>" +
                "<td>" + employee.gender + "</td>" +
                "<td>" + employee.dateOfBirth + "</td>" +
                "</tr>";
        }
    );
    $("#details").html(output);
}

function showError(xhr) {
    alert("Sorry! Error on server!");
}

