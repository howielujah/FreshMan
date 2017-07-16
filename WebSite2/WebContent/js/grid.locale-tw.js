;(function($){
/**
 * jqGrid Chinese Translation for v4.2
 * henryyan 2011.11.30
 * http://www.wsria.com
 * Dual licensed under the MIT and GPL licenses:
 * http://www.opensource.org/licenses/mit-license.php
 * http://www.gnu.org/licenses/gpl.html
 * 
 * update 2011.11.30
 *		add double u3000 SPACE for search:odata to fix SEARCH box display err when narrow width from only use of eq/ne/cn/in/lt/gt operator under IE6/7
**/
$.jgrid = $.jgrid || {};
$.extend($.jgrid,{
	defaults : {
		recordtext: "{0} - {1}\u3000共 {2} 筆",	// 共字前是全角空格
		emptyrecords: "沒有資料",
		loadtext: "讀取中...",
		pgtext : " {0} 共 {1} 頁"
	},
	search : {
		caption: "搜尋條件",
		Find: "搜尋",
		Reset: "重置",
		odata : ['等於\u3000\u3000', '不等\u3000\u3000', '小於\u3000\u3000', '小於等於','大於\u3000\u3000','大於等於', 
			'開始於','不開始於','屬於\u3000\u3000','不屬於','結束於','不結束於','包含\u3000\u3000','不包含','空值於\u3000\u3000','非空值'],
		groupOps: [	{ op: "AND", text: "所有" },	{ op: "OR",  text: "任一" }	],
		matchText: " 匹配",
		rulesText: " 規則"
	},
	edit : {
		addCaption: "新增資料",
		editCaption: "編輯資料",
		bSubmit: "送出",
		bCancel: "取消",
		bClose: "關閉",
		saveData: "資料已改變，是否存檔？",
		bYes : "是",
		bNo : "否",
		bExit : "取消",
		msg: {
			required:"此欄位不可為空白",
			number:"請輸入有效數字",
			minValue:"輸入值必須大於等於",
			maxValue:"輸入值必須小於等於",
			email: "這不是有效的e-mail地址",
			integer: "請輸入有效整數",
			date: "請輸入正確時間",
			url: "網址錯誤。必須為 ('http://' 或 'https://')開頭",
			nodefined : " 未定義！",
			novalue : " 需要返回值！",
			customarray : "自定义函数需要返回数组！",
			customfcheck : "Custom function should be present in case of custom checking!"
			
		}
	},
	view : {
		caption: "查看資料",
		bClose: "關閉"
	},
	del : {
		caption: "删除",
		msg: "删除選擇的資料？",
		bSubmit: "删除",
		bCancel: "取消"
	},
	nav : {
		edittext: "",
		edittitle: "編輯選擇的資料",
		addtext:"",
		addtitle: "新增資料",
		deltext: "",
		deltitle: "刪除選擇的資料",
		searchtext: "",
		searchtitle: "搜尋",
		refreshtext: "",
		refreshtitle: "重新讀取表格",
		alertcap: "注意",
		alerttext: "請選擇資料",
		viewtext: "",
		viewtitle: "查看選擇資料"
	},
	col : {
		caption: "选择列",
		bSubmit: "確定",
		bCancel: "取消"
	},
	errors : {
		errcap : "錯誤",
		nourl : "沒有設定url",
		norecords: "沒有要處理的資料",
		model : "colNames 和 colModel 長度不符！"
	},
	formatter : {
		integer : {thousandsSeparator: " ", defaultValue: '0'},
		number : {decimalSeparator:".", thousandsSeparator: " ", decimalPlaces: 2, defaultValue: '0.00'},
		currency : {decimalSeparator:".", thousandsSeparator: " ", decimalPlaces: 2, prefix: "", suffix:"", defaultValue: '0.00'},
		date : {
			dayNames:   [
				"Sun", "Mon", "Tue", "Wed", "Thr", "Fri", "Sat",
		         "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
			],
			monthNames: [
				"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec",
				"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
			],
			AmPm : ["am","pm","AM","PM"],
			S: function (j) {return j < 11 || j > 13 ? ['st', 'nd', 'rd', 'th'][Math.min((j - 1) % 10, 3)] : 'th'},
			srcformat: 'Y-m-d',
			newformat: 'm-d-Y',
			masks : {
				ISO8601Long:"Y-m-d H:i:s",
				ISO8601Short:"Y-m-d",
				ShortDate: "Y/j/n",
				LongDate: "l, F d, Y",
				FullDateTime: "l, F d, Y g:i:s A",
				MonthDay: "F d",
				ShortTime: "g:i A",
				LongTime: "g:i:s A",
				SortableDateTime: "Y-m-d\\TH:i:s",
				UniversalSortableDateTime: "Y-m-d H:i:sO",
				YearMonth: "F, Y"
			},
			reformatAfterEdit : false
		},
		baseLinkUrl: '',
		showAction: '',
		target: '',
		checkbox : {disabled:true},
		idName : 'id'
	}
});
})(jQuery);
