$().ready(function () {
    validateRule();
});

$.validator.setDefaults({
    submitHandler: function () {
        update();
    }
});

//设置全局变量保存基地编码修改前的值
var baseCode;
//是否是第一次修改
var firstChange = true;

function update() {
    $.ajax({
        cache: true,
        type: "POST",
        url: "/zyctd/baseTb/update",
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

// 基地编号是否重复验证
jQuery.validator.addMethod("baseCodeRepeatCheck", function (value, element) {

    var result = true;
    //当基地编号发生改变时，判断是否重复
    if (baseCode != undefined && value != baseCode
    ) {
        $.ajax({
            cache: true,
            type: "POST",
            url: "/zyctd/baseTb/baseCodeRepeatCheck/" + value,
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
}, "基地编号重复，请重新输入！");

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            name: {
                required: true
            },
            areaName: {
                required: true
            },
            detailAddress: {
                required: true
            },
            code: {
                required: true,
                baseCodeRepeatCheck: true
            },
            clientUrl: {
                required: true
            }
        },
        messages: {
            name: {
                required: icon + "请输入基地名称"
            },
            areaName: {
                required: icon + "请选择基地所属区域"
            },
            detailAddress: {
                required: icon + "请输入基地详细地址"
            },
            code: {
                required: icon + "请输入基地编号",
                baseCodeRepeatCheck: "基地编号重复，请重新输入！"
            },
            clientUrl: {
                required: icon + "请输入基地所属客户端服务器推流地址"
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
        url: "/zyctd/areaTb/list", // 请求的URL
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
            $('#areaTree').treeview({
                color: "#428bca",
                data: [tree],
                showCheckbox: false,
                multiSelect: false,
                onNodeSelected: function (event, data) {
                    var str = $("#areaName").val();
                    var ids = $("#areaId").val();
                    if (str.length > 0) {
                        $("#areaName").val(str + data.text + ',');
                    } else {
                        $("#areaName").val(data.text + ',');
                    }
                    if (ids.length > 0) {
                        $("#areaId").val(ids + data.id + ',');
                    } else {
                        $("#areaId").val(data.id + ',');
                    }
                },
                nodeUnselected: function (event, data) {
                    var str = $("#areaName").val();
                    var ids = $("#areaId").val();
                    $("#areaId").val(ids.replace(data.id + ",", ""));
                    $("#areaName").val(str.replace(data.text + ",", ""));
                }
            });
        }
    });
}