$("#find_employee").click(function () {
    $("#operation_status").empty();
    $.ajax({
        url: "/employees/" + $("#id").val(),
        method: "GET",
        success: showResult,
        error: function () {
            $("#operation_status").append("Find error");
        }
    });
});

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

function showError() {
    alert("Sorry! Error on server!");
}
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
            let d = new Date(employee.dateOfBirth);
            output += "<tr>" +
                "<td>" + employee.employeeId + "</td>" +
                "<td>" + employee.firstName + "</td>" +
                "<td>" + employee.lastName + "</td>" +
                "<td>" + employee.departmentId + "</td>" +
                "<td>" + employee.jobTitle + "</td>" +
                "<td>" + employee.gender + "</td>" +
                "<td>" + d.toISOString().substring(0, 10) + "</td>" +
                "</tr>";
        }
    );
    $("#employee_list").html(output);
}

$("#add_employee").click(function () {
    $("#eployee_adding").empty();
    let data = JSON.stringify({
        "firstName": $("#first_name").val(),
        "lastName": $("#last_name").val(),
        "departmentId": $("#department_id").val(),
        "jobTitle": $("#job_title").val(),
        "gender": $("#gender").val(),
        "dateOfBirth": $("#date_of_birth").val()
    });

    $.ajax({
        url: "/employees",
        method: "POST",
        contentType: "application/json; charset=utf-8",
        data: data,
        success: function () {
            $("#eployee_adding").append("Employee added");
        },
        error: function () {
            $("#eployee_adding").append("Operation failed, please check the entered data is correct");
        }
    });
});

$("#delete_employee").click(function () {
    $("#operation_status").empty();
    $.ajax({
        url: "/employees/" + $("#id").val(),
        method: "DELETE",
        success: function () {
            $("#operation_status").append("Employee deleted");
        },
        error: function () {
            $("#operation_status").append("Deleting error");
        }
    });
});









