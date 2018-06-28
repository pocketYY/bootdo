$().ready(function () {
    validateRule();
});

$.validator.setDefaults({
    submitHandler: function () {
        save();
    }
});

function save() {
    $.ajax({
        cache: true,
        type: "POST",
        url: "/zyctd/cameraTb/save",
        data: $('#signupForm').serialize(),// 你的formid
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);

            } else {
                parent.layer.alert(data.msg)
            }

        }
    });

}

//设置全局变量保存摄像头编码修改前的值
var code;
//设置全局变量保存摄像头原始流地址修改前的值
var osUrl;

// 摄像头编号是否重复验证
jQuery.validator.addMethod("cameraCodeRepeatCheck", function (value, element) {

    var result = true;
    //当摄像头编号发生改变时，判断是否重复
    if (value != code) {
        $.ajax({
            cache: true,
            type: "POST",
            url: "/zyctd/cameraTb/cameraCodeRepeatCheck/" + value,
            async: false,
            error: function (request) {
                parent.layer.alert("Connection error");
            },
            success: function (data) {
                result = data;
            }
        });
    }
    return this.optional(element) || result;
}, "摄像头编号重复，请重新输入！");

// 摄像头原始流地址是否重复验证
jQuery.validator.addMethod("osUrlRepeatCheck", function (value, element) {

    var result = true;
    //当摄像头编号发生改变时，判断是否重复
    if (value != osUrl) {
        $.ajax({
            cache: true,
            type: "POST",
            url: "/zyctd/cameraTb/osUrlRepeatCheck/",
            data: {originalStreamUrl: value},
            async: false,
            error: function (request) {
                parent.layer.alert("Connection error");
            },
            success: function (data) {
                result = data;
            }
        });
    }
    return this.optional(element) || result;
}, "摄像头原始流地址重复，请重新输入！");

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            model: {
                required: true
            },
            originalStreamUrl: {
                required: true,
                osUrlRepeatCheck: true
            },
            baseName: {
                required: true
            },
            code: {
                required: true,
                cameraCodeRepeatCheck: true
            }
        },
        messages: {
            model: {
                required: icon + "请输入名称"
            },
            originalStreamUrl: {
                required: icon + "请输入摄像头原始流地址",
                osUrlRepeatCheck: "摄像头原始流地址重复，请重新输入！"
            },
            baseName: {
                required: icon + "请选择摄像头所属基地"
            },
            code: {
                required: icon + "请输入摄像头编号",
                cameraCodeRepeatCheck: "摄像头编号重复，请重新输入！"
            }
        }
    })
}

$(function () {
    getTree('1');
})

/**
 * 建立树节点
 * @param datas
 */
function buildTree(parentNode, datas) {
    for (var key in datas) {
        var data = datas[key];
        if (data.parentId == parentNode.id) {
            var node = {text: data.name, id: data.id, nodes: [], selectable: true};
            parentNode.nodes.push(node);
            buildTree(node, datas);
        }
    }

    if (parentNode.nodes.length == 0) {
        delete parentNode.nodes;
    }
}

function getTree() {
    $.ajax({
        url: "/zyctd/baseTb/list", // 请求的URL
        dataType: 'json',
        type: "get",
        success: function (datas) {
            //遍历找出根节点
            for (var key in datas) {
                var data = datas[key];
                if (data.parentId == 0) {
                    var tree = {text: data.name, id: data.id, nodes: [], selectable: true};
                    break;
                }
            }
            buildTree(tree, datas);
            $('#baseTree').treeview({
                color: "#428bca",
                data: [tree],
                showCheckbox: false,
                multiSelect: false,
                onNodeSelected: function (event, data) {
                    var str = $("#baseName").val();
                    var ids = $("#baseId").val();
                    if (str.length > 0) {
                        $("#baseName").val(str + data.text + ',');
                    } else {
                        $("#baseName").val(data.text + ',');
                    }
                    if (ids.length > 0) {
                        $("#baseId").val(ids + data.id + ',');
                    } else {
                        $("#baseId").val(data.id + ',');
                    }
                },
                nodeUnselected: function (event, data) {
                    var str = $("#baseName").val();
                    var ids = $("#baseId").val();
                    $("#baseId").val(ids.replace(data.id + ",", ""));
                    $("#baseName").val(str.replace(data.text + ",", ""));
                }
            });
        }
    });
}