# HRShopping



<img src="https://github.com/qqhahaboy/HRShopping/raw/master/app/images/image2.jpg" width="33%" height="33%"> <img src="https://github.com/qqhahaboy/HRShopping/raw/master/app/images/image5.png" width="33%" height="33%"> <img src="https://github.com/qqhahaboy/HRShopping/raw/master/app/images/image6.png" width="33%" height="33%">
 
  
<img src="https://github.com/qqhahaboy/HRShopping/raw/master/app/images/image7.png" width="35%" height="33%">  <img src="https://github.com/qqhahaboy/HRShopping/raw/master/app/images/image8.png" width="33%" height="33%"> 

整体框架 : MVP

图片加载库: Glide

网络请求库: Retrofit + OkHttp

### 踩过的一些坑:
 * 在编写加载头像图片代码的时候，经常会出现OOM(out of memory) 导致程序崩掉。
   
      解决方法: [封装了一个工具类](/app/src/main/java/com/example/helloworld/huaruanshopping/util/createBitmapUtil.java/)，在加载头像图片时利用                BitmapFactory.Options()的inSampleSize属性,先压缩图片的比例，再加载

 
* 用了Sharepreference + gson 来存储地址。因为关联性不强，用SQlite有点大动作，而IO文件存储消耗性能。
  
* 当ListView滚动时自动调用 onCheckedChanged 导致CheckBox 状态不停变化 的解决办法
   
       // 1、 在初始化CheckBox状态和设置状态变化监听事件之前，先把状态变化监听事件设置为null  ，
       holder.checkBox.setOnCheckedChangeListener(null);  
       // 2、 然后设置CheckBox状态     
       if(isChecked) { 
           holder.checkbox.setChecked(true);   
       } else {  
           holder.checkbox.setChecked(false);   
       } 
       // 3、 然后设置状态变化监听事件 
      holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
          @Override
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
       
           //....         

       });  
 
 
2017-4-17  修复购物车选择购买商品时的一些bug,添加显示购物车待付款的金额, 添加支付时向后台发送请求

2017-4-14  修复首页显示bug，添加完成交易的订单可以评论或追评的功能,添加PhotoView 浏览图片

2017-4-12  完善了提交评论的功能
