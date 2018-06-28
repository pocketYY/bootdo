var prefix = "/zyctd/cameraTb"
$(function () {
    var baseId = '';
    getTreeData();
    load(baseId);
});


function load(baseId) {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/list", // 服务器数据的加载地址
                // showRefresh : true,
                // showToggle : true,
                // showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                // search : true, // 是否显示搜索框
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者
                // "server"
                queryParams: function (params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset,
                        searchName: $('#searchName').val(),
                        baseId: baseId
                    };
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'id',
                        title: ''
                    },
                    {
                        field: 'model',
                        title: '摄像头型号'
                    },
                    {
                        field: 'code',
                        title: '摄像头编号'
                    },
                    {
                        field: 'originalStreamUrl',
                        title: '摄像头原始流地址'
                    },
                    {
                        field: 'turnedStreamUrl',
                        title: '摄像头转码后流地址'
                    },
                    {
                        field: 'baseName',
                        title: '关联基地名称'
                    },
                    {
                        field: 'status',
                        title: '摄像头状态',
                        formatter: function (value, row, index) {
                            if (value == 10) {
                                return '<span class="label label-danger">故障</span>';
                            } else if (value == 0) {
                                return '<span class="label label-success">正常</span>';
                            } else {
                                return '<span class="label label-primary">未知</span>';
                            }
                        }
                    },
                    // {
                    //     field: 'isDelete',
                    //     title: '删除标志',
                    //     formatter: function (value, row, index) {
                    //         if (value == 0) {
                    //             return '<span class="label label-danger">已删除</span>';
                    //         }
                    //         if (value == 1) {
                    //             return '<span class="label label-success">正常</span>';
                    //         }
                    //     }
                    // },
                    {
                        title: '操作',
                        field: 'hahha',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a  class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.id
                                + '\')"><i class="fa fa-edit "></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.id
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            var p = '<a class="btn btn-warning btn-sm ' + s_play_h + '" href="#" title="播放"  mce_href="#" onclick="play(\''
                                + row.id + ','
                                + row.turnedStreamUrl + '\')"><i class="fa fa-play"></i></a> ';
                            return e + d + p;
                        }
                    }
                ]
            });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function add() {
    layer.open({
        type: 2,
        title: '增加',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/add' // iframe的url
    });
}

function edit(id) {
    layer.open({
        type: 2,
        title: '编辑',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/edit/' + id // iframe的url
    });
}

function play(id) {
    var ids = id.split(",");
    if (typeof ids[1] == "undefined" || ids[1] == null || ids[1] == "") {
        layer.msg("摄像头播放地址为空！");
        return;
    }
    layer.open({
        type: 2,
        title: '播放',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/play/' + ids[0]// iframe的url
    });
}

function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'id': id
            },
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}

function resetPwd(id) {
}

function batchRemove() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn: ['确定', '取消']
        // 按钮
    }, function () {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function (i, row) {
            ids[i] = row['id'];
        });
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids
            },
            url: prefix + '/batchRemove',
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function () {

    });
}

/**
 * 批量设置摄像头状态
 * @param status
 */
function updateStatus(status) {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要修改的数据");
        return;
    }
    layer.confirm("确认要修改选中的'" + rows.length + "'条数据吗?", {
        btn: ['确定', '取消']
        // 按钮
    }, function () {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function (i, row) {
            if (status != row.status) {
                ids[i] = row['id'];
            }
        });
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids,
                "status": status
            },
            url: prefix + '/updateStatus',
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function () {

    });
}

function getTreeData() {
    $.ajax({
        type: "GET",
        url: "/zyctd/baseTb/tree",
        success: function (tree) {
            loadTree(tree);
        }
    });
}

function loadTree(tree) {
    $('#jstree').jstree({
        'core': {
            'data': tree
        },
        "plugins": ["search"]
    });
    $('#jstree').jstree().open_all();
}

$('#jstree').on("changed.jstree", function (e, data) {
    if (data.selected == -1) {
        var opt = {
            query: {
                baseId: '',
            }
        }
        $('#exampleTable').bootstrapTable('refresh', opt);
    } else {
        var opt = {
            query: {
                baseId: data.selected[0],
            }
        }
        $('#exampleTable').bootstrapTable('refresh', opt);
    }

});