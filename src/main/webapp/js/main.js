$(function () {
    'use strict';


    // 初始化jQuery文件上传窗口小部件:
    $('#fileupload').fileupload(
        {
        	async: false ,
//            done:function(e,result){
//                //done方法就是上传完毕的回调函数，其他回调函数可以自行查看api
//                //注意result要和jquery的ajax的data参数区分，这个对象包含了整个请求信息
//                //返回的数据在result.result中，假设我们服务器返回了一个json对象
//                console.log(JSON.stringify(result.result));
//            },
        	options : {
        		acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
        		// Set the following option to true to force iframe transport uploads:
                forceIframeTransport: true,

                // Set the following option to true to force iframe transport uploads:
                forceIframeTransport: true,
                // By default, each file of a selection is uploaded using an individual
                // request for XHR type uploads. Set to false to upload file
                // selections in one request each:
                singleFileUploads: false,

                // To limit the number of files uploaded with one XHR request,
                // set the following option to an integer greater than 0:
                limitMultiFileUploads: 1,
                // Set the following option to true to issue all file upload requests
                // in a sequential order:
                sequentialUploads: true,

                // To limit the number of concurrent uploads,
                // set the following option to an integer greater than 0:
                limitConcurrentUploads: 2,

                // Interval in milliseconds to calculate and trigger progress events:
                progressInterval: 50,
                // Interval in milliseconds to calculate progress bitrate:
                bitrateInterval: 50,



                formData: $('#fileupload').serializeArray()
            }
        }
    ).bind('fileuploadadd', function (e, data) {
        console.log('fileuploadadd');
    })
        .bind('fileuploadsubmit', function (e, data) {


            data.formData = $('#fileupload').serializeArray();

            console.log(data.formData);
            console.log('fileuploadsubmit');
        })
        .bind('fileuploadsend', function (e, data) {
            console.log('fileuploadsend');
        })
        .bind('fileuploaddone', function (e, data) {
            console.log('fileuploaddone');
        })
        .bind('fileuploadfail', function (e, data) {
            console.log('fileuploadfail');
        })
        .bind('fileuploadalways', function (e, data) {
            console.log('fileuploadalways');
        })
        .bind('fileuploadprogress', function (e, data) {
            console.log('fileuploadprogress');
        })
        .bind('fileuploadprogressall', function (e, data) {
            console.log('fileuploadprogressall');
        })
        .bind('fileuploadstart', function (e) {
            console.log('fileuploadstart');
        })
        .bind('fileuploadstop', function (e) {
            console.log('fileuploadstop');
        })
        .bind('fileuploadchange', function (e, data) {
            console.log('fileuploadchange');
        })
        .bind('fileuploadpaste', function (e, data) {
            console.log('fileuploadpaste');
        })
        .bind('fileuploaddrop', function (e, data) {
            console.log('fileuploaddrop');
        })
        .bind('fileuploaddragover', function (e) {
            console.log('fileuploaddragover');
        })
        .bind('fileuploadchunksend', function (e, data) {
            console.log('fileuploadchunksend');
        })
        .bind('fileuploadchunkdone', function (e, data) {
            console.log('fileuploadchunkdone');
        })
        .bind('fileuploadchunkfail', function (e, data) {
            console.log('fileuploadchunkfail');
        })
        .bind('fileuploadchunkalways', function (e, data) {
            console.log('fileuploadchunkalways');
        });
});