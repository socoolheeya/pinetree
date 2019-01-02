<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cocoa::또다른 즐거움의 시작</title>
<script type="text/javascript">
	$(document).ready(function(){
		fnGrid();
	});
	
	function fnGrid() {
		$("#grid").jqGrid({
			url: '/home/grid.json',
			datatype: 'json',
			mtype: 'POST',
			//width: 900,
			//height: 400,
			//rownumbers: true,
			colNames: ['id', 'name', 'phone'],
			colModel: [
				{ name: 'id', index: 'id', editable: false, sortable: true, width: 20, align: 'left' },
				{ name: 'name', index: 'name', editable: true, sortable: true, search: true, width: 50, align: 'left' },
				{ name: 'phone', index: 'phone', editable: true, sortable: true, search: true, width: 50, align: 'left' }
			],
			/* pager		: "grid1Pager"	,
			rowNum 		: 20			,
			rowList		: [ 10, 20, 30 ], */
			cmTemplate	: { sortable:true },
			autowidth	: true,
			multiselect	: true,
			viewrecords	: true,
			rownumbers	: false,
			jsonReader	: {
				page		: function (rJson) {console.log(rJson);return rJson.pageIndex;	},
				total		: function (rJson) {return rJson.total; 		},
				records 	: function (rJson) {return rJson.records; 		},
				repeatitems	: false
			},
			loadComplete: function(data) {
			}
		});
	}
</script>
<!-- <script type="text/javascript" src="/resources/common/frozen.js"></script> -->
</head>
<body>
	<table id="grid"></table>
	<div id="gridPager"></div>
	<button id="hackFrozenHeight">버튼1</button>
</body>
</html>