$(function() {
	'use strict';

	// 初始化jQuery文件上传窗口小部件:
	$('#fileupload').fileupload({
		async : false,
		options : {
			acceptFileTypes : /(\.|\/)(gif|jpe?g|png)$/i,
			formData : $('#fileupload').serializeArray()
		}
	}).bind('fileuploadadd', function(e, data) {


		console.log($(".indexCal").size());
		console.log('fileuploadadd');

		if($(".indexCal").size() > 4){ // 5個pic
			alert('pic max size 5');
			return false;
		}
	}).bind('fileuploadsubmit', function(e, data) {

		// data.formData = $('#fileupload').serializeArray();

		// console.log(data.formData);
		console.log('fileuploadsubmit');
	}).bind('fileuploadsend', function(e, data) {
		console.log('fileuploadsend  start');

		console.log(e);
		console.log(data);

		console.log('fileuploadsend  end');
	}).bind('fileuploaddone', function(e, data) {
		console.log('fileuploaddone');
	}).bind('fileuploadfail', function(e, data) {
		console.log('fileuploadfail');
	}).bind('fileuploadalways', function(e, data) {
		console.log('fileuploadalways');
	}).bind('fileuploadprogress', function(e, data) {
		console.log('fileuploadprogress');
	}).bind('fileuploadprogressall', function(e, data) {
		console.log('fileuploadprogressall');
	}).bind('fileuploadstart', function(e) {
		console.log('fileuploadstart');
	}).bind('fileuploadstop', function(e) {
		console.log('fileuploadstop');
	}).bind('fileuploadchange', function(e, data) {

		$.each(data.files, function(index, file) {
			console.log('Selected file: ' + index + "	" + file.name);


		});

		console.log('fileuploadchange');
	}).bind('fileuploadpaste', function(e, data) {
		console.log('fileuploadpaste');
	}).bind('fileuploaddrop', function(e, data) {
		console.log('fileuploaddrop');
	}).bind('fileuploaddragover', function(e) {
		console.log('fileuploaddragover');
	}).bind('fileuploadchunksend', function(e, data) {
		console.log('fileuploadchunksend');
	}).bind('fileuploadchunkdone', function(e, data) {
		console.log('fileuploadchunkdone');
	}).bind('fileuploadchunkfail', function(e, data) {
		console.log('fileuploadchunkfail');
	}).bind('fileuploadchunkalways', function(e, data) {
		console.log('fileuploadchunkalways');
	});
});