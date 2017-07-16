$(document).ready(function() {
    $("#grid_id").jqGrid({
        url: "fetchList.do",
        datatype: "json",
        colNames:['主鍵','名稱','描述'],
        colModel:[
            { name:'PK',
              index: 'PK',
              width:100,
              editable:true,
              hidden:true
            },
            { name:'IND_NAME',
              index:'IND_NAME',
              width:100,
              editable:true,
              sorttable:false,
              editrules: {
            	  required: true
              }
            },
            { name:'IND_DESC',
              index:'IND_DESC',
              width:300,
              editable:true,
              sorttable:false
            },
        ],
        rowNum:10,
        rowList:[10,20,30],
        pager: '#pager',
        sortname: 'IND_NAME',
        sortorder: "desc",
        height: "auto",
        autowidth: true,
        viewrecords: true,
        jsonReader:{
        	repeatitems : false
        },
        editurl: 'edit.do',
        caption: "Industry List"

    });
    $("#grid_id").jqGrid('navGrid',"#pager",
    		{edit:true,add:true,del:true,search:false},
    		{closeAfterEdit: true},
    		{closeAfterAdd: true},
    		{serializeDelData: function (postdata) {
    				var rowdata = jQuery('#grid_id').getRowData(postdata.id);
    				return {id: postdata.id, oper: postdata.oper, PK: rowdata.PK};
    }});
});
