# HRShopping



<img src="https://github.com/qqhahaboy/HRShopping/raw/master/app/images/image2.jpg" width="33%" height="33%"> <img src="https://github.com/qqhahaboy/HRShopping/raw/master/app/images/image5.png" width="33%" height="33%"> <img src="https://github.com/qqhahaboy/HRShopping/raw/master/app/images/image6.png" width="33%" height="33%">
 
  
<img src="https://github.com/qqhahaboy/HRShopping/raw/master/app/images/image7.png" width="35%" height="33%">

整体框架 : MVP

图片加载库: Glide

网络请求库: Retrofit + OkHttp

##### 一些有趣的地方:
 * 在编写加载头像图片代码的时候，经常会出现OOM(out of memory) 导致程序崩掉。
  解决方法: [封装了一个工具类](/app/)，在加载头像图片时先压缩图片的比例，再加载

<img src="https://github.com/qqhahaboy/HRShopping/raw/master/app/images/image8.png" width="33%" height="33%">  

2017-4-17  修复购物车选择购买商品时的一些bug,添加显示购物车待付款的金额, 添加支付时向后台发送请求

2017-4-14  修复首页显示bug，添加完成交易的订单可以评论或追评的功能,添加PhotoView 浏览图片

2017-4-12  完善了提交评论的功能
