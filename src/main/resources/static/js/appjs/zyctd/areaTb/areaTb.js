var prefix = "/zyctd/areaTb"
$(function () {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTreeTable(
            {
                id: 'id',
                code: 'id',
                parentCode: 'parentId',
                type: "GET", // 请求数据的ajax类型
                url: prefix + '/list', // 请求数据的ajax的url
                ajaxParams: {sort:'order_num'}, // 请求数据的ajax的data属性
                expandColumn: '1',// 在哪一列上面显示展开按钮
                striped: true, // 是否各行渐变色
                bordered: true, // 是否显示边框
                expandAll: true, // 是否全部展开
                //    id: 'id',
                //    code: 'id',
                //    parentCode: 'parentId',
                // 	method : 'get', // 服务器数据的请求方式 get or post
                // 	url : prefix + "/list", // 服务器数据的加载地址
                // //	showRefresh : true,
                // //	showToggle : true,
                // //	showColumns : true,
                // 	iconSize : 'outline',
                // 	toolbar : '#exampleToolbar',
                // 	striped : true, // 设置为true会有隔行变色效果
                // 	dataType : "json", // 服务器返回的数据类型
                // 	pagination : true, // 设置为true会在底部显示分页条
                // 	// queryParamsType : "limit",
                // 	// //设置为limit则会发送符合RESTFull格式的参数
                // 	singleSelect : false, // 设置为true将禁止多选
                // 	// contentType : "application/x-www-form-urlencoded",
                // 	// //发送到服务器的数据编码类型
                // 	pageSize : 10, // 如果设置了分页，每页数据条数
                // 	pageNumber : 1, // 如果设置了分布，首页页码
                // 	//search : true, // 是否显示搜索框
                // 	showColumns : false, // 是否显示内容下拉框（选择显示的列）
                // 	sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                // 	queryParams : function(params) {
                // 		return {
                // 			//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                // 			limit: params.limit,
                // 			offset:params.offset
                //            // name:$('#searchName').val(),
                //            // username:$('#searchName').val()
                // 		};
                // 	},
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
                        field: 'name',
                        title: '区域名称'
                    },
                    {
                        field: 'level',
                        title: '区域行政级别',
                        align: 'center',
                        formatter: function (item, index) {
                            if (item.level === 0) {
                                return '<span>国家</span>';
                            }
                            if (item.level === 1) {
                                return '<span>省</span>';
                            }
                            if (item.level === 2) {
                                return '<span>市</span>';
                            }
                            if (item.level === 3) {
                                return '<span>区县</span>';
                            }
                            if (item.level === 4) {
                                return '<span>乡镇</span>';
                            }
                            if (item.level === 5) {
                                return '<span>村社</span>';
                            }
                        }

                    },
                    // {
                    //     field: 'isDelete',
                    //     title: '删除标志',
                    //     align: 'center',
                    //     formatter: function (item, index) {
                    //         if (item.isDelete === 0) {
                    //             return '<span class="label label-danger">已删除</span>';
                    //         }
                    //         if (item.isDelete === 1) {
                    //             return '<span class="label label-success">正常</span>';
                    //         }
                    //     }
                    // },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (item, index) {
                            var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + item.id
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + item.id
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
                                + item.id
                                + '\')"><i class="fa fa-key"></i></a> ';
                            var p = '<a class="btn btn-primary btn-sm '
                                + s_add_h
                                + '" href="#" mce_href="#" title="添加下级" onclick="add(\''
                                + item.id
                                + '\')"><i class="fa fa-plus"></i></a> ';
                            return e + d + p;
                        }
                    }]
            });
}

function reLoad() {
    // $('#exampleTable').bootstrapTable('refresh');
    load();
}

// function add() {
//     layer.open({
//         type: 2,
//         title: '增加',
//         maxmin: true,
//         shadeClose: false, // 点击遮罩关闭层
//         area: ['800px', '520px'],
//         content: prefix + '/add' // iframe的url
//     });
// }

function add(pId) {
    layer.open({
        type: 2,
        title: '增加菜单',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/add/' + pId // iframe的url
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