/**
 * 联系我们
 */
$(function(){
	//获取更新时间
	$.get("company/getCompanyInfo",function(data,status){
		$("#update_time").text(data[0].updateTime);
	});
	
	//获取公司名称
	$.get("company/getCompanyInfo",function(data,status){
		$("#company_name").text(data[0].companyName);
	});
	
	//获取公司地址
	$.get("company/getCompanyInfo",function(data,status){
		$("#address").text(data[0].address);
	});
	
	//获取公司电话
	$.get("company/getCompanyInfo",function(data,status){
		$("#phone").text(data[0].phone);
		$("#tel").attr("tel",data[0].phone);
	});
	
	
	//获取公司QQ
	$.get("company/getCompanyInfo",function(data,status){
		$("#qq").text(data[0].qq);
	});
	
	//获取公司邮箱
	$.get("company/getCompanyInfo",function(data,status){
		$("#mail").text(data[0].mail);
	});
	
	//获取公司微博
	$.get("company/getCompanyInfo",function(data,status){
		$("#microblog").text(data[0].microblog);
	});
	
	//获取公司微信
	$.get("company/getCompanyInfo",function(data,status){
		$("#wechat").text(data[0].wechat);
	});
	
	//获取公司logo
	$.get("company/getCompanyInfo",function(data,status){
		$("#logo").attr("src",data[0].logo);
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
});