/**自定义模块*/
layui.define(['layer','upload'], function (exports) {
    var $ = layui.jquery,
        layer = layui.layer,
        upload = layui.upload;

    var image = {
            upload:function(data){
                if(data.selectEle && data.uploadUrl){
                    upload.render({
                        elem: data.selectEle
                        ,auto:true
                        ,accept: 'images'
                        ,url: data.uploadUrl
                        ,choose: function(obj){
                            var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                            if(files != null){
                                $(data.selectEle).html("重新选择");
                                obj.preview(function(index, file, result){

                                });
                            }else{
                                $(data.selectEle).html("选择图片");
                            }
                        }
                        ,done:function(res, index, upload){
                            console.log(this.files);
                            //假设code=0代表上传成功
                            if(res.code == 0){
                                delete this.files[index]; //删除文件队列已经上传成功的文件
                                layer.msg('上传成功', {time: 3000,offset: "t",icon: 6});
                                $(data.selectEle).html("重新选择");
                                if(data.saveEle){
                                    $(data.saveEle).val(res.data.src);
                                }
                                if(data.showEle){
                                    $(data.showEle).show();
                                }
                                if(data.imgEle){
                                    $(data.imgEle).attr("src",res.data.src);
                                }
                            }else{
                                layer.msg(res.msg, {time: 3000,offset: "t",icon: 5});
                            }
                            var item = this.item;//获取当前触发上传的元素，一般用于 elem 绑定 class 的情况，注意：此乃 layui 2.1.0 新增
                        }
                    })
                }
            },
            show:function(data){
                layer.open({
                    type: 1,
                    title: false,
                    closeBtn: 0,
                    shadeClose: true,
                    content: '<img src="'+data.path+'" alt="加载失败"/>'
                });
            }
    };
    exports('image', image)
})