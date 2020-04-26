var totalpage = 1;
var nowpage = 1;

loadToDo();

// 마감 날짜 선택 시 지난 날짜는 선택하지 못하도록 함.
var today = new Date();
var dd = today.getDate();
var mm = today.getMonth()+1; //January is 0!
var yyyy = today.getFullYear();

if(dd<10){
    dd='0'+dd
}
if(mm<10){
    mm='0'+mm
}
today = yyyy+'-'+mm+'-'+dd;
document.getElementById("deadline-date").setAttribute("min", today);
document.getElementById("deadline-date-update").setAttribute("min", today);

// 페이지의 데이터를 가져온다.
function loadToDo(page){
    var url = '/api/tasks'
    if(page != null){
        url = url + '?page=' + page
    }
    $.ajax({
        url: url,
        method: 'get',
        async: true,
        contentType: "application/json",
        success: function (resp) {
            console.log("tasks 조회 성공");
            hideDisplay();
            totalpage = resp.totalPages;
            $('#now-page').text(nowpage);
            $('#total-page').text(totalpage);
            var todoDiv = "#todo-div"
            var title = ".title"
            var desc = ".desc"
            var deadline = ".dead-line-date"
            var created = ".created-date"
            var priority = "#priority"
            var complete = "#customCheck"
            var todoid = "#todo-id"
            for(var i = 0; i < resp.numberOfElements; i++){
                if( resp.content[i].complete == true){
                    $( complete+i ).prop("checked" , true);
                    $( title+i ).addClass("todo-done-text");
                    $( desc+i ).addClass("todo-done-text");
                }else{
                    $( complete+i ).prop("checked" , false);
                    $( title+i ).removeClass("todo-done-text");
                    $( desc+i ).removeClass("todo-done-text");
                }
                $( todoDiv+i ).removeClass('todo-display-none');
                $( todoid+i ).val(resp.content[i].id);
                $( title+i ).text(resp.content[i].title);
                $( desc+i).text(resp.content[i].description);
                var deadlineDate =resp.content[i].deadlineDate.slice(0,10);
                var createdDate =resp.content[i].createdDate.slice(0,10);
                $( created+i).text(createdDate);
                $( deadline+i ).text(deadlineDate);
                $( priority+i ).text(resp.content[i].priority);
                removePriorityClass(priority+i);
                $( priority+i ).addClass(setPriorityClass(resp.content[i].priority));
                if(resp.content[i].timeOver == true){
                    $( '#time-over' + i ).removeClass("todo-display-none");
                }else{
                    $( '#time-over' + i ).addClass("todo-display-none");
                }
            }
        },
        error: function (err) {
            console.log(err.toString());
        }
    });
}
// task를 추가한다.
$("#submit-todo").click( function() {
    console.log('추가 시작');
    var title = $("#form-title").val();
    var desc = $("#form-desc").val();
    var deadlineDate = $("#deadline-date").val();
    var priority = $('input[name="inlineRadioOptions"]:checked').val();
    console.log('modal  ' + title + '/'+ desc + '/' + deadlineDate +'/' + priority);
    var JSONObject = { 'title': title, 'description' : desc, 'priority' : priority, 'deadlineDate' : deadlineDate};
    var jsonData = JSON.stringify(JSONObject);
    $.ajax({
        url: '/api/tasks',
        method: 'post',
        data: jsonData,
        async: true,
        contentType: "application/json",
        success: function (resp) {
            console.log("추가..");
            alert("추가 완료");
            loadToDo(nowpage)
        },
        error: function (err) {
            console.log(err.toString());
        }
    });
    $("#form-title").val('');
    $("#form-desc").val('');
    $('input[name="inlineRadioOptions"]').prop("checked" , false);
    $("#deadline-date").val('');
    $('#myModal').modal('toggle');
} );

// task의 checkbox 클릭 시 완료->완료X , 완료X->완료 로 상태 변경
$("input[type='checkbox']").change(function(){
    var index = $(this).val();
    var todoid = "#todo-id" + index;
    var title = ".title" + index;
    var desc = ".desc" + index;
    if(this.checked) {
        $(title).addClass("todo-done-text");
        $(desc).addClass("todo-done-text");
    }else {
        $(title).removeClass("todo-done-text");
        $(desc).removeClass("todo-done-text");
    }
    var id = $( todoid ).val();
    console.log("index : "+index + "id : "+id);
    var JSONObject = { 'id' : id};
    var jsonData = JSON.stringify(JSONObject);

    $.ajax({
        url: '/api/tasks',
        method: 'put',
        data: jsonData,
        async: true,
        contentType: "application/json",
        success: function (resp) {
            console.log("상태 변경..");
        },
        error: function (err) {
            console.log(err.toString());
        }
    });

});
// 중요도에 따라 클래스를 추가해준다.
function setPriorityClass(priority){
    if(priority == "High"){
        return "badge-danger";
    }else if (priority == "Medium"){
        return "badge-warning";
    }else{
        return "badge-info";
    }
}

function removePriorityClass(badgeId) {
    $(badgeId).removeClass("badge-danger");
    $(badgeId).removeClass("badge-warning");
    $(badgeId).removeClass("badge-info");
}

function hideDisplay(){
    $('.todo-div').addClass('todo-display-none');
}

// 다음 페이지
function next(){
    if(nowpage < totalpage){
        nowpage = nowpage + 1;
        loadToDo(nowpage);
    }else{
        alert("마지막 페이지 입니다.");
    }
}
// 이전 페이지
function before() {
    if(nowpage <= totalpage && nowpage > 1){
        nowpage = nowpage - 1;
        loadToDo(nowpage);
    }else{
        alert("첫 페이지 입니다.");
    }
}
// task 삭제
function deleteTodo(index) {
    var id = $("#todo-id" + index).val();
    if(id == null) return;
    $.ajax({
        url: '/api/tasks/' + id,
        method: 'delete',
        async: true,
        contentType: "application/json",
        success: function (resp) {
            alert("삭제 되었습니다.");
            loadToDo(nowpage);
        },
        error: function (err) {
            console.log(err.toString());
        }
    });
}
// 업데이트 시 task 정보 가져오기.
function loadTask(index) {
    var id = $("#todo-id" + index).val();
    $("#update-todo-id").val(id);
    if(id == null) return;
    $.ajax({
        url: '/api/tasks/' + id,
        method: 'get',
        async: true,
        contentType: "application/json",
        success: function (resp) {
            $("#form-title-update").val(resp.title);
            $("#form-desc-update").val(resp.description);
            var priority = resp.priority;
            $('[name=priorityRadioUpdate][value='+priority+']').prop('checked',true);
            $("#deadline-date-update").val(resp.deadlineDate.slice(0,10));
        },
        error: function (err) {
            console.log(err.toString());
        }
    })
}
// 업데이트 (수정 )
$("#update-todo").click( function() {
    console.log('업데이트 시작');
    var id = $("#update-todo-id").val();
    var title = $("#form-title-update").val();
    var desc = $("#form-desc-update").val();
    var deadlineDate = $("#deadline-date-update").val();
    var priority = $('input[name="priorityRadioUpdate"]:checked').val();
    console.log('update modal  '+id+'/' + title + '/'+ desc + '/' + deadlineDate +'/' + priority);
    var JSONObject = { 'id': id, 'title': title, 'description' : desc, 'priority' : priority, 'deadlineDate' : deadlineDate};
    var jsonData = JSON.stringify(JSONObject);
    $.ajax({
        url: '/api/tasks/' + id,
        method: 'put',
        data: jsonData,
        async: true,
        contentType: "application/json",
        success: function (resp) {
            console.log("업데이트 완료");
            alert("수정 완료");
            loadToDo(nowpage);
        },
        error: function (err) {
            console.log(err.toString());
        }
    });
    $("#form-title-update").val('');
    $("#form-desc-update").val('');
    $('input[name="priorityRadioUpdate"]').prop("checked" , false);
    $("#deadline-date-update").val('');
    $("#update-todo-index").val('');
    $('#updateModal').modal('toggle');
});

// 마감 기한이 지나고 처리하지 않은 일에 대해 알람 보내기..
$.ajax({
    url: '/api/tasks/overtime',
    method: 'get',
    async: true,
    contentType: "application/json",
    success: function (resp) {
        console.log("past tasks : " + resp.count);
        if(resp.count == 0) return;
        var text = '';
        for(var i = 0; i < resp.count -1; i++){
            text = text + resp.titles[i] +', </br>';
        }
        text = text + resp.titles[i];
        toastr.options = {
            "closeButton": true,
            "debug": false,
            "newestOnTop": false,
            "progressBar": false,
            "positionClass": "toast-bottom-right",
            "preventDuplicates": false,
            "onclick": null,
            "showDuration": "300",
            "hideDuration": "1000",
            "timeOut": "4000",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        };
        var text2 = '마감기한이 지났으나 완료하지 못한 TODO : ' + resp.count + '개';
        toastr.info(text, text2);
    },
    error: function (err) {
        console.log(err.toString());
    }
});
