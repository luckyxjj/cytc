$(document).ready(
		function() {
			/* 获取联系方式信息 */
			var loc = location.href;
			var length = loc.length; // 地址的总长度
			var position = loc.indexOf("="); // 取得=号的位置
			var memberId = loc.substring(position + 1); // 截取id
			var localUrl = encodeURIComponent(loc.split("#")[0]);
			$.get("member/getWechatInfo?url=" + localUrl, function(data, status) {
				if (status == "success") {
					wx.config({
						debug : false, // 调试阶段建议开启
						appId : data[0].appId,// APPID
						timestamp : data[0].timestamp,// 上面main方法中拿到的时间戳timestamp
						nonceStr : data[0].noncestr,// 上面main方法中拿到的随机数nonceStr
						signature : data[0].signature,// 上面main方法中拿到的签名signature

						jsApiList : [
						// 所有要调用的 API 都要加到这个列表中
						'onMenuShareTimeline',// 分享到朋友圈
						'onMenuShareAppMessage',// 分享给微信好友
						'onMenuShareQQ',// 分享给QQ好友
						'onMenuShareQZone',// 分享到QQ空间
						'showOptionMenu',
						'hideOptionMenu'
						]
					});
					// 判断当前客户端版本是否支持指定JS接口
					wx.checkJsApi({
						jsApiList : [ 'onMenuShareTimeline',
								'onMenuShareAppMessage',
								'onMenuShareQQ',
								'onMenuShareQZone' ], // 需要检测的JS接口列表，所有JS接口列表见附录2,
						success : function(res) {
							// 以键值对的形式返回，可用的api值true，不可用为false
							// 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
						}
					});

					// config信息验证成功

					wx.ready(function() {
						//分享到朋友圈
						wx.onMenuShareTimeline({
							title : '常青藤教育',
							link : 'http://www.ioivy.com/share.html?id='
									+ memberId,
							imgUrl : 'http://www.ioivy.com/images/logo.ico',
							trigger : function(res) {

							},
							success : function(res) {

							},
							cancel : function(res) {
							},
							fail : function(res) {

							}
						});
						//分享给微信好友
						wx.onMenuShareAppMessage({
							title : '常青藤教育', // 分享标题
							desc : '常青藤教育，艺术改变人生', // 分享描述
							link : 'http://www.ioivy.com/share.html?id='
									+ memberId, // 分享链接
							imgUrl : 'http://www.ioivy.com/images/logo.ico', // 分享图标
							success : function() {

							},
							cancel : function() {

							}
						});
						//分享到QQ
						wx.onMenuShareQQ({
						    title: '常青藤教育', // 分享标题
						    desc: '常青藤教育，艺术改变人生', // 分享描述
						    link: 'http://www.ioivy.com/share.html?id='+memberId, // 分享链接
						    imgUrl: 'http://www.ioivy.com/images/logo.ico', // 分享图标
						    success: function () { 
						       // 用户确认分享后执行的回调函数
						    },
						    cancel: function () { 
						       // 用户取消分享后执行的回调函数
						    }
						});
						//分享到QQ空间
						wx.onMenuShareQZone({
						    title: '常青藤教育', // 分享标题
						    desc: '常青藤教育，艺术改变人生', // 分享描述
						    link: 'http://www.ioivy.com/share.html?id='+memberId, // 分享链接
						    imgUrl: 'http://www.ioivy.com/images/logo.ico', // 分享图标
						    success: function () { 
						       // 用户确认分享后执行的回调函数
						    },
						    cancel: function () { 
						        // 用户取消分享后执行的回调函数
						    }
						});
						

					});

					// config信息验证失败
					wx.error(function(res) {

						// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。

					});

				} else {
					alert("获取失败，请重试");
				}
			});

		});
